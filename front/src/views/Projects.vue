<template>

  <div class="projects">
    <h1 class="subheading grey--text">Task</h1>
    <v-container class="my-5">
      <template>
        <v-row justify="center">
          <v-dialog
            v-model="dialog"
            max-width="290"
          >
            <v-card>
              <v-card-title class="headline">
                <v-icon color="red"> {{ dialogIcon }}</v-icon>
                <div class="mx-2">Tip</div>
              </v-card-title>

              <v-card-text class="text-body-1 text-center">

                {{ alertMessage }}
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>

                <!--                            <v-btn-->
                <!--                                color="green darken-1"-->
                <!--                                text-->
                <!--                                @click="dialog = false"-->
                <!--                            >-->
                <!--                                Cancel-->
                <!--                            </v-btn>-->
                <v-btn
                  color="grey darken-1"
                  outlined
                  @click="dialog = false"
                >
                  Ok
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>

      <div>

          <div class="text-center d-flex pb-4 ">

            <v-btn outlined class="mr-2" @click="all" large>
              Expand All
            </v-btn>
            <!--                <div>{{ panel }}</div>-->
            <v-btn outlined class="mx-2" large @click="none">
              Fold All
            </v-btn>

            <v-btn outlined class="mx-2" large @click="mostRecent">
              Most Recent
            </v-btn>

            <v-btn outlined class="mx-2" large @click="notWorry">
              Not Worry
            </v-btn>

              <v-btn outlined class="mx-2" large @click="notWorry">
              Out Dated
            </v-btn>
          </div>

      <v-expansion-panels
        v-model="panel"
        multiple v-if="tasks===null"
      >
        <v-expansion-panel
          v-for="(item,i) in items"
          :key="i"
        >
          <v-expansion-panel-header>Header {{ item }}</v-expansion-panel-header>
          <v-expansion-panel-content>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
            labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
            laboris nisi ut aliquip ex ea commodo consequat.
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
  </div>

  <div>
    <v-expansion-panels
      v-model="panel"
      multiple v-if="tasks!==null"
    >
      <v-expansion-panel
        v-for="(task,i) in tasks"
        :key="i"
      >
        <v-expansion-panel-header>

          <v-row align="center" class="text-capitalize">
            <v-icon color="orange" v-if="getPersonInfo(task).status ==='0'">mdi-help-network</v-icon>
            <v-icon color="blue" v-if="getPersonInfo(task).status ==='1'">mdi-help-network</v-icon>
            <v-icon color="green" v-if="getPersonInfo(task).status ==='2'">mdi-check-network</v-icon>
            <v-icon color="red" v-if="getPersonInfo(task).status ==='3'">mdi-close-network</v-icon>
            <v-icon color="black" v-if="getPersonInfo(task).status === '4'">mdi-close-network</v-icon>
            <div class="text-h6 mx-1"> {{ task.taskTitle }}</div>
            <v-spacer></v-spacer>
            <div class="mx-3 text-caption">
              <!--              {{ getPersonInfo(task).status }}-->
              <v-btn small outlined :color="colors[getPersonInfo(task).status]">{{
                  txt[getPersonInfo(task).status]
                }}
              </v-btn>
            </div>
            <div class="mx-5 text-body-2">Work In Progress</div>
          </v-row>

        </v-expansion-panel-header>
        <v-expansion-panel-content>

          <div class="text-body-1 mb-2 mx-2">{{ task.description }}</div>
          <v-row>
            <div class="mx-3 text-body-2">Assigner
              <span class="blue-grey--text"> {{ ' ' + task.taskSender }}</span>
            </div>
            <v-spacer></v-spacer>
            <div class="text-right text-caption">
              {{ calcDay(task.deadline) }}
              <v-icon small>mdi-calendar-alert</v-icon>
              {{ formattedDate(task.deadline) }}
            </div>
          </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </div>
  </v-container>
  </div>
</template>

<script>
import axios from 'axios'
import format from 'date-fns/format'
import myTime from '@/lib/time'
import globalConfig from '@/lib/globalConfig'

export default {
  name: 'Projects',
  components: {},
  data: () => ({
    items: ['Ocaml Course', 'Java Course', 'Linux Kernel', 'The Compilers'],
    tasks: null,
    panel: [],
    dialog: false,
    dialogIcon: '',
    alertMessage: '',
    txt: ['ready to go', 'ongoing', 'complete', 'overdue', 'removed'],
    colors: [globalConfig.col_readyToGo, globalConfig.col_ongoing, globalConfig.col_complete, globalConfig.col_overdue, globalConfig.col_removed]
  }),
  methods: {
    // Create an array the length of our items
    // with all values as true
    getPersonInfo (task) {
      if (task === null || task.userList === null || task.statesList === null) return
      const usersArr = task.userList.split('_')
      const statusArr = task.statesList.split('_')
      const len = usersArr.length
      for (let i = 0; i < len; ++i) {
        if (parseInt(usersArr[i]) === this.$store.state.id) {
          return {
            id: this.$store.state.id,
            status: statusArr[i]
          }
        }
      }
      return null
    },
    calcDay (due) {
      const diff = myTime.getDayDistance(new Date(), new Date(due))
      if (diff < 0.0) return 'outdated '
      else return parseInt(diff) + ' days remain'
    },
    formattedDate (due) {
      return due !== '' ? format(new Date(due), 'do MMM YYY') : ''
    },
    all () {
      const arr = []
      if (this.tasks !== null) {
        for (let i = 0; i < this.tasks.length; ++i) {
          arr.push(i)
        }
      } else {
        for (let i = 0; i < this.items.length; ++i) {
          arr.push(i)
        }
      }
      this.panel = arr
    },
    // Reset the panel
    none () {
      this.panel = []
    },
    mostRecent () {
      this.alertMessage = 'Work In Progress'
      this.dialogIcon = 'mdi-alert-circle'
      this.dialog = true
    },
    notWorry () {
      this.alertMessage = 'Work In Progress'
      this.dialogIcon = 'mdi-alert-circle'
      this.dialog = true
    }
  },
  created () {
    if (this.$store.state.userID !== '') {
      console.log('Task')
      axios.get('http://localhost:8081/user/getTaskIds/' + this.$store.state.id).then(response => {
        console.log(response)
        axios.post('http://localhost:8081/task/queryTaskByIdArr', { data: response.data }).then(response => {
          console.log(response)
          this.tasks = response.data
          if (this.tasks.length === 0) this.tasks = null
        })
      })
    }
  }
}
</script>
