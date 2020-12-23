<template>
  <div class="team">

    <v-overlay :value="overlay">
      <v-progress-circular
        indeterminate
        size="64"
      ></v-progress-circular>
    </v-overlay>

    <h1 class="subheading grey--text">Team</h1>
    <v-row wrap v-if="allGroups === null ||  this.$store.state.userID === ''">
      <v-flex xs12 sm6 md4 lg3 v-for="team in teams" :key="team.name">
        <v-card outlined class="ma-3">
          <v-responsive class="pt-4 text-sm-center">
            <v-tooltip top>
              <template v-slot:activator="{ on, attrs }">
                        <span>
                            <v-icon small :color="getColor(team.task)" v-bind="attrs"
                                    v-on="on">mdi-alert-box-outline</v-icon>
                            {{ team.task }}
                        </span>
              </template>
              <span>Task have not completed</span>
            </v-tooltip>
          </v-responsive>
          <v-card-text>
            <div class="text-sm-center text-h4">
              {{ team.name }}
            </div>
            <div class="text-sm-center grey--text mt-3">
              {{ team.desc }}
            </div>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-tooltip top>
              <template v-slot:activator="{ on, attrs }">
                <v-btn class="float-right" text color="blue" v-bind="attrs" v-on="on">
                  <v-icon small left>mdi-arrow-right-bold-hexagon-outline</v-icon>
                  <span class="text-sm-body-2">more</span>
                </v-btn>
              </template>
              <span>See Team Details</span>
            </v-tooltip>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-row>

    <v-row wrap v-if="allGroups !== null && this.$store.state.userID !== '' ">
      <v-flex xs12 sm6 md4 lg3 v-for="(group,index) in allGroups" :key="index">
        <v-card outlined class="ma-3">

          <v-responsive class="pt-4 text-sm-center">
            <v-tooltip top>
              <template v-slot:activator="{ on, attrs }">
                        <span>
                            <v-icon small :color="getColor(group.tasks)" v-bind="attrs"
                                    v-on="on">mdi-alert-box-outline</v-icon>
                            {{ group.tasks }}
                        </span>
              </template>
              <span>Task have not completed</span>
            </v-tooltip>

          </v-responsive>
          <v-card-text>
            <div class="text-sm-center text-h4">
              {{ group.groupName }}
            </div>
            <div class="text-sm-center grey--text mt-3">
              {{ group.groupDesc }}
            </div>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-tooltip top>
              <template v-slot:activator="{ on, attrs }">
                <v-btn class="float-right" text color="blue" v-bind="attrs" v-on="on">
                  <v-icon small left>mdi-arrow-right-bold-hexagon-outline</v-icon>
                  <span class="text-sm-body-2" @click="jump(group)">more</span>
                </v-btn>
              </template>
              <span>See Team Details</span>
            </v-tooltip>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-row>
  </div>

</template>

<script>
import axios from 'axios'

export default {
  name: 'Team',
  components: {},
  data () {
    return {
      overlay: false,
      allGroups: null,
      teams: [
        { name: 'Java Group', desc: 'Develop Spring Boot Application', task: 0 },
        { name: 'Ocaml Group', desc: 'Develop Ocaml Data Structure', task: 4 },
        { name: 'F# Group', desc: 'Somebody says F# is good at drawing', task: 0 },
        { name: 'Compilers Group', desc: 'Today I can learn how to use attributed grammar', task: 0 },
        { name: 'Chinese Group', desc: 'Learn poems, write poems', task: 2 },
        { name: 'Animation Group', desc: 'Watch animations, love animations,　最高です!', task: 0 },
        { name: 'Cloud Computing', desc: 'We use hadoop, write mapreduce, and spark', task: 7 }
      ]
    }
  },
  methods: {
    jump: function (groupInfo) {
      this.$router.push({
        name: 'group',
        params: { group: groupInfo }
      })
    },
    _calcAllTheTask: function () {
      axios.get('http://localhost:8081/user/getGroupIds/' + this.$store.state.id).then(response => {
        console.log('Groups Arr -> ', response.data)
        axios.post('http://localhost:8081/task-group/AllGroup', {
          data: response.data
        }).then(response => {
          this.allGroups = response.data
          // allGroups
          console.log(response.data)
          this.overlay = false
          for (let i = 0; i < this.allGroups.length; ++i) {
            this.allGroups[i].tasks = 0
            // first query all the task from now login user, then calculate the tasks remain
            const listArr = this.allGroups[i].taskList.split('_')
            for (let j = 0; j < listArr.length; ++j) {
              axios.get('http://localhost:8081/task/getState/' + this.$store.state.id + '/' + listArr[j]).then(response => {
                if (response.data !== 2 && response.data !== 4) {
                  // this.allGroups[i].tasks++ // will not work
                  // this.$set(this.allGroups[i], 'tasks', this.allGroups[i].tasks + 1)
                  const tmp = this.allGroups[i]
                  tmp.tasks++
                  this.$set(this.allGroups, i, tmp)
                }
              })
            }
          }
        })
      }).catch(
        this.overlay = false
      )
    },
    queryAllTheTask: async function () {
      this.overlay = true
      await this._calcAllTheTask()
    },
    getColor: function (num) {
      if (num === 0) return 'green'
      if (num <= 3) return 'blue '
      if (num <= 5) return 'orange darken-1 '
      return 'red'
    }
  },
  created () {
    this.queryAllTheTask()
  }
}
</script>
