<template>
  <div class="dashboard">

    <h1 class="subheading grey--text">Dashboard</h1>
    <div class="text-center">
      <!--          <v-btn-->
      <!--            dark-->
      <!--            color="orange darken-2"-->
      <!--            @click="snackbar = true"-->
      <!--          >-->
      <!--            Open Snackbar-->
      <!--          </v-btn>-->

      <v-snackbar :color="'black'"
                  v-model="snackbar"
                  :timeout="3000"
                  top>
        {{ text }}

        <template v-slot:action="{ attrs }">
          <v-btn color="pink"
                 text
                 v-bind="attrs"
                 @click="snackbar = false">
            CLOSE
          </v-btn>
        </template>
      </v-snackbar>
    </div>
    <v-overlay :value="overlay">
      <v-progress-circular
        indeterminate
        size="64"
      ></v-progress-circular>
    </v-overlay>

    <v-container class="my-5">
      <div class="text-h6 mb-5">You can see all of your tasks here, You can
        sort them, search for some keywords. Go ahead and complete your tasks, The better Life is waiting for you
      </div>
      <!--      <v-layout row>-->
      <!--        <v-flex xs12 md6>-->
      <!--          <v-btn outlined block color="grey">1</v-btn>-->
      <!--        </v-flex>-->
      <!--        <v-flex xs4 md2>-->
      <!--          <v-btn outlined block color="grey">2</v-btn>-->
      <!--        </v-flex>-->
      <!--        <v-flex xs4 md2>-->
      <!--          <v-btn outlined block color="grey">2</v-btn>-->
      <!--        </v-flex>-->
      <!--        <v-flex xs4 md2>-->
      <!--          <v-btn outlined block color="grey">2</v-btn>-->
      <!--        </v-flex>-->
      <!--      </v-layout>-->
      <div v-if="userTasks===null">
        <v-row class="mb-3">
          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-btn small
                     text
                     color="grey"
                     @click="sortByProjectName()"
                     v-bind="attrs"
                     v-on="on">
                <v-icon left
                        small>mdi-folder
                </v-icon>
                <span class="caption text-lowercase">By Project Name</span>
              </v-btn>
            </template>
            <span>Sort Projects By Project Name</span>
          </v-tooltip>

          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-btn small
                     text
                     color="grey"
                     v-bind="attrs"
                     v-on="on"
                     @click="sortByAssigner()">
                <v-icon left
                        small>mdi-account-check-outline
                </v-icon>
                <span class="caption text-lowercase">By Assigner</span>
              </v-btn>

            </template>
            <span>Sort Projects By Assigner's Name</span>
          </v-tooltip>

          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-btn small
                     text
                     color="grey"
                     v-bind="attrs"
                     v-on="on"
                     @click="sortByDeadline()">
                <v-icon left
                        small>mdi-av-timer
                </v-icon>
                <span class="caption text-lowercase">By Deadline</span>
              </v-btn>

            </template>
            <span>Sort Projects By Task Deadline</span>
          </v-tooltip>

          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-btn small
                     text
                     color="grey"
                     v-bind="attrs"
                     v-on="on"
                     @click="sortByStatus()">
                <v-icon left
                        small>mdi-account-check-outline
                </v-icon>
                <span class="caption text-lowercase">By Status</span>
              </v-btn>

            </template>
            <span>Sort Projects By Task Status</span>
          </v-tooltip>

        </v-row>

        <v-card flat
                v-for="task in tasks"
                :key="task.title">
          <v-row :class="`rounded-0 pa-3 task ${task.status}`">
            <v-flex xs12
                    md6>
              <div class="caption grey--text">Project Title</div>
              <div class="mx-1">{{ task.title }}</div>
            </v-flex>
            <v-flex xs6
                    sm4
                    md2>
              <div class="caption grey--text">Assigner</div>
              <div class="mx-1">{{ task.assigner }}</div>
            </v-flex>
            <v-flex xs6
                    sm4
                    md2>
              <div class="caption grey--text">Deadline</div>
              <div class="mx-1">{{ task.deadline }}</div>
            </v-flex>
            <!--          <v-flex xs6 sm4 md2>-->
            <!--            <div class="caption grey&#45;&#45;text">Status</div>-->
            <!--            <div class="mx-1">{{ task.status }}</div>-->
            <!--          </v-flex>-->
            <v-flex>
              <div>
                <v-chip outlined
                        small
                        :color="getColor(task.status)"
                        class="white--text caption my-2 float-right">
                  {{ task.status }}
                </v-chip>
              </div>
            </v-flex>
          </v-row>
          <v-divider></v-divider>
        </v-card>
      </div>

      <div v-if="userTasks">
        <v-row class="mb-3">
          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-btn small
                     text
                     color="grey"
                     @click="sortByProjectNamePro()"
                     v-bind="attrs"
                     v-on="on">
                <v-icon left
                        small>mdi-folder
                </v-icon>
                <span class="caption text-lowercase">By Project Name</span>
              </v-btn>
            </template>
            <span>Sort Projects By Project Name</span>
          </v-tooltip>

          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-btn small
                     text
                     color="grey"
                     v-bind="attrs"
                     v-on="on"
                     @click="sortByAssignerPro()">
                <v-icon left
                        small>mdi-account-check-outline
                </v-icon>
                <span class="caption text-lowercase">By Assigner</span>
              </v-btn>

            </template>
            <span>Sort Projects By Assigner's Name</span>
          </v-tooltip>

          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-btn small
                     text
                     color="grey"
                     v-bind="attrs"
                     v-on="on"
                     @click="sortByDeadlinePro()">
                <v-icon left
                        small>mdi-av-timer
                </v-icon>
                <span class="caption text-lowercase">By Deadline</span>
              </v-btn>

            </template>
            <span>Sort Projects By Task Deadline</span>
          </v-tooltip>

          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-btn small
                     text
                     color="grey"
                     v-bind="attrs"
                     v-on="on"
                     @click="sortByStatusPro()">
                <v-icon left
                        small>mdi-account-check-outline
                </v-icon>
                <span class="caption text-lowercase">By Status</span>
              </v-btn>

            </template>
            <span>Sort Projects By Task Status</span>
          </v-tooltip>

          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-btn small
                     :text="!filter.next7Day"
                     color="grey"
                     v-bind="attrs"
                     v-on="on"
                     @click="nextSevenDayFilter()">
                <v-icon left color="blue darken-3"
                        small>mdi-email-search-outline
                </v-icon>
                <span class="caption text-lowercase">Next 7 Days</span>
              </v-btn>

            </template>
            <span>See what you should do in the next 7 days</span>
          </v-tooltip>

          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-btn small
                     :text="!filter.notFinish"
                     color="grey"
                     v-bind="attrs"
                     v-on="on"
                     @click="notFinish()">
                <v-icon left color="green darken-4"
                        small>mdi-check
                </v-icon>
                <span class="caption text-lowercase">Not complete</span>
              </v-btn>

            </template>
            <span>See which tasks you have not completed yet</span>
          </v-tooltip>

          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-btn small
                     :text="!filter.outdated"
                     color="grey"
                     v-bind="attrs"
                     v-on="on"
                     @click="setOutdated()">
                <v-icon left
                        small color="red darken-3">mdi-alert-decagram-outline
                </v-icon>
                <span class="caption text-lowercase">Only Outdated</span>
              </v-btn>

            </template>
            <span>See which tasks are already outdated and need to do</span>
          </v-tooltip>

        </v-row>
        <v-card flat
                v-for="task in ComputedUserTasks"
                :key="task.id">
          <v-row :class="`rounded-0 pa-3 task ${getStatus(task.status)}`">
            <v-flex xs12
                    md6>
              <div class="caption grey--text">Project Title</div>
              <div class="mx-1">{{ task.taskTitle }}</div>
            </v-flex>
            <v-flex xs6
                    sm4
                    md2>
              <div class="caption grey--text">Assigner</div>
              <div class="mx-1">{{ task.taskSender }}</div>
            </v-flex>
            <v-flex xs6
                    sm4
                    md2>
              <div class="caption grey--text">Deadline</div>
              <div class="mx-1">{{ formatDate(task.deadline) }}</div>
            </v-flex>
            <!--          <v-flex xs6 sm4 md2>-->
            <!--            <div class="caption grey&#45;&#45;text">Status</div>-->
            <!--            <div class="mx-1">{{ task.status }}</div>-->
            <!--          </v-flex>-->
            <v-flex>
              <div>
                <v-col>
                  <template>

                    <div class="text-center">
                      <v-menu offset-y>
                        <template v-slot:activator="{ on, attrs }">
                          <v-btn outlined
                                 x-small
                                 :color="getColor(getStatus(task.status))"
                                 class="white--text caption my-2 float-right"
                                 v-bind="attrs"
                                 v-on="on">
                            {{ getStatus(task.status) }}
                          </v-btn>
                        </template>
                        <v-list dense>
                          <v-list-item size="20">
                            <v-list-item-icon class="mx-3">
                              <v-icon color="green">mdi-book-check-outline</v-icon>
                            </v-list-item-icon>
                            <v-list-item-content>
                              <v-btn outlined x-small color="green" class="mx-0" @click="markTask(task.id,2)">complete
                              </v-btn>
                            </v-list-item-content>
                          </v-list-item>

                          <v-list-item>
                            <v-list-item-icon class="mx-3">
                              <v-icon color="blue">mdi-book-minus</v-icon>
                            </v-list-item-icon>
                            <v-list-item-content>
                              <v-btn outlined x-small color="blue" class="mx-0" @click="markTask(task.id,1)">ongoing
                              </v-btn>
                            </v-list-item-content>
                          </v-list-item>

                          <v-list-item>
                            <v-list-item-icon class="mx-3">
                              <v-icon color="orange darken-1">mdi-book-remove-multiple-outline</v-icon>
                            </v-list-item-icon>
                            <v-list-item-content>
                              <v-btn outlined x-small color="orange darken-1" class="mx-0" @click="markTask(task.id,0)">
                                ready-2-go
                              </v-btn>
                            </v-list-item-content>
                          </v-list-item>

                          <v-list-item>
                            <v-list-item-icon class="mx-3">
                              <v-icon color="red">mdi-delete</v-icon>
                            </v-list-item-icon>
                            <v-list-item-content>
                              <v-btn outlined x-small color="red" class="mx-0" @click="deleteTask(task.id)">
                                Delete
                              </v-btn>
                            </v-list-item-content>
                          </v-list-item>

                        </v-list>
                      </v-menu>
                    </div>
                  </template>
                </v-col>
              </div>
            </v-flex>
            <!--            mark the status-->
          </v-row>
          <v-divider></v-divider>
        </v-card>
      </div>

    </v-container>

    <!--    <v-row class="text-center">-->
    <!--      <v-col cols="12">-->
    <!--        <v-card class="d-flex flex-nowrap py-3" color="grey lighten-2" flat tile>-->
    <!--          <v-card v-for="n in 5" :key="n" class="pa-2" outlined tile>Flex item</v-card>-->
    <!--        </v-card>-->
    <!--      </v-col>-->
    <!--    </v-row>-->

  </div>
</template>

<script>

import axios from 'axios'
import format from 'date-fns/format'
import myTime from '@/lib/time'

export default {
  name: 'Dashboard',
  components: {},
  inject: ['reload'],
  computed: {
    ComputedUserTasks () {
      // Computed Based On filter
      let filteredUserTasks = this.userTasks
      // eslint-disable-next-line no-empty
      if (this.filter.next7Day) {
        const temp = []
        for (let iter = 0; iter < filteredUserTasks.length; ++iter) {
          const time = 7 * 24 * 60 * 60 * 1000
          if (new Date(filteredUserTasks[iter].deadline).getTime() - new Date().getTime() <= time &&
            new Date(filteredUserTasks[iter].deadline).getTime() - new Date().getTime() > 0) {
            temp.push(filteredUserTasks[iter])
          }
        }
        filteredUserTasks = temp
      }

      if (this.filter.notFinish) {
        const temp = []
        for (let iter = 0; iter < filteredUserTasks.length; ++iter) {
          if (filteredUserTasks[iter].status !== '2' && filteredUserTasks[iter].status !== '4') {
            temp.push(filteredUserTasks[iter])
          }
        }
        filteredUserTasks = temp
      }

      if (this.filter.outdated) {
        const temp = []
        for (let iter = 0; iter < filteredUserTasks.length; ++iter) {
          if (filteredUserTasks[iter].status === '3') {
            temp.push(filteredUserTasks[iter])
          }
        }
        filteredUserTasks = temp
      }

      return filteredUserTasks
    }
  },
  data: () => ({
    filter: {
      next7Day: false,
      notFinish: true,
      outdated: false
    },
    searchParams: '',
    text: '',
    overlay: false,
    justify: ['start', 'end', 'textcenter', 'baseline', 'auto', 'stretch'],
    snackbar: false,
    userTasks: null,
    tasks: [
      {
        title: 'Play Cyberpunk 2007',
        assigner: 'XYS',
        deadline: '2021-1-29',
        status: 'overdue'
      },
      {
        title: 'Create a new website',
        assigner: 'XYS',
        deadline: '2020-12-21',
        status: 'ongoing'
      }, {
        title: 'Finish a compiler',
        assigner: 'XYS',
        deadline: '2020-12-25',
        status: 'complete'
      }, {
        title: 'Play Cyberpunk 2077',
        assigner: 'GZ',
        deadline: '2021-2-13',
        status: 'overdue'
      },
      {
        title: 'Play Cyberpunk 2017',
        assigner: 'XYS',
        deadline: '2021-1-21',
        status: 'overdue'
      },
      {
        title: 'Look Animation',
        assigner: 'GZ',
        deadline: '2020-6-6',
        status: 'ready-to-start'
      }
    ]
  }),
  created () {
    this.queryAllTask()
  },
  methods: {
    nextSevenDayFilter () {
      this.filter.next7Day = !this.filter.next7Day
    },
    notFinish () {
      this.filter.notFinish = !this.filter.notFinish
    },
    setOutdated () {
      this.filter.outdated = !this.filter.outdated
    },
    formatDate (date) {
      return date !== ''
        ? (format(new Date(date), 'do MMM YYY') + '(' + parseInt(myTime.getDayDistance(new Date(), new Date(date))) + ' Days)')
        : ''
    },
    _queryAllTask: function () {
      if (this.$store.state.userID !== '') {
        // if logged, then get all the task in the dashboard
        axios.get('http://localhost:8081/user/getTaskIds/' + this.$store.state.id).then(response => {
          console.log(response)
          axios.post('http://localhost:8081/task/queryTaskByIdArr', { data: response.data }).then(response => {
            console.log(response)
            this.userTasks = response.data
            for (let j = 0; j < this.userTasks.length; ++j) {
              const v = this.userTasks[j].userList.split('_')
              const k = this.userTasks[j].statesList.split('_')
              for (let i = 0; i < v.length; ++i) {
                if (parseInt(v[i]) === this.$store.state.id) {
                  this.userTasks[j].status = k[i]
                }
              }
            }
            if (this.tasks.length === 0) this.userTasks = null
          })
        })
      } else {
        this.userTasks = null
      }
    },
    queryAllTask: async function () {
      this.overlay = true
      await this._queryAllTask()
      this.overlay = false
    },

    markTask: function (taskID, status) {
      axios.get('http://localhost:8081/task/setState/' + this.$store.state.id + '/' + taskID + '/' + status).then(response => {
        this.reload()
        this.showMessage('Mark the task successfully')
      }).catch(
        this.showMessage('Mark the task failed')
      )
    },

    showMessage: function (message, status) {
      this.text = message
      this.snackbar = true
      if (status == null) {
        this.snackbarColor = 'black'
      } else {
        this.snackbarColor = status
      }
    },
    // deadline taskTitle description taskSender
    getStatus: function (status) {
      status = parseInt(status)
      if (status === 0) return 'ready-to-start'
      if (status === 1) return 'ongoing'
      if (status === 2) return 'complete'
      if (status === 3) return 'overdue'
      if (status === 4) return 'removed'
    },

    sortBy: function (prop, param) {
      if (param === 'timeline') this.tasks.sort((a, b) => new Date(a[prop]) < new Date(b[prop]) ? -1 : 1)
      else this.tasks.sort((a, b) => a[prop] < b[prop] ? -1 : 1)
    },

    getColor: function (name) {
      if (name === 'complete') return 'green'
      if (name === 'ongoing') return 'blue'
      if (name === 'overdue') return 'red'
      if (name === 'ready-to-start') return 'orange darken-1'
      if (name === 'removed') return 'blue-gray'
      alert('check your code, by getColor')
    },
    sortByProjectNamePro: function () {
      this.userTasks.sort((a, b) => a.taskTitle < b.taskTitle ? -1 : 1)
    },
    sortByDeadlinePro: function () {
      this.userTasks.sort((a, b) => new Date(a.deadline) < new Date(b.deadline) ? -1 : 1)
    },
    sortByStatusPro: function () {
      this.userTasks.sort((a, b) => a.status < b.status ? -1 : 1)
    },
    sortByAssignerPro: function () {
      this.userTasks.sort((a, b) => a.taskSender < b.taskSender ? -1 : 1)
    },
    sortByProjectName: function () {
      this.sortBy('title')
    },

    sortByDeadline: function () {
      this.sortBy('deadline', 'timeLine')
    },

    sortByStatus: function () {
      this.sortBy('status')
    },

    sortByAssigner: function () {
      this.sortBy('assigner')
    },
    // deleteTask/{id}/{taskId}
    deleteTask: function (taskID) {
      axios.get('http://localhost:8081/user/deleteTask/' + this.$store.state.id + '/' + taskID).then(
        response => {
          this.reload()
          this.showMessage('Delete success')
        }
      )
    }
  }
}
</script>

<style>
.task.ongoing {
  border-left: 4px solid #2196f3;
}

.task.ready-to-start {
  border-left: 4px solid #ff9800;
}

.task.overdue {
  border-left: 4px solid #f44336;
}

.task.complete {
  border-left: 4px solid #43a047;
}

.task.removed {
  border-left: 4px solid #607D8B;
}

</style>
