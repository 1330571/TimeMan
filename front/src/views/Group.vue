<template>
  <v-container fluid>

    <template>
      <v-row justify="center">
        <v-dialog
          v-model="commentShow"
          scrollable
          max-width="700px"
        >
          <!--      <template v-slot:activator="{ on, attrs }">-->
          <!--        <v-btn-->
          <!--          color="primary"-->
          <!--          dark-->
          <!--          v-bind="attrs"-->
          <!--          v-on="on"-->
          <!--        >-->
          <!--          Open Dialog-->
          <!--        </v-btn>-->
          <!--      </template>-->
          <v-card>
            <v-card-title>Comments</v-card-title>
            <v-divider></v-divider>
            <v-card-text style="height: 800px;">
              <!-- avatar contents user-->

              <template>
                <v-card
                  class="mx-auto"
                  max-width="900"
                  v-for="comment in comments"
                  :key="comment.id"
                  size="20"
                >
                  <v-card-title>
                    <v-icon
                      x-small
                      left
                    >
                      mdi-comment
                    </v-icon>
                    <span class="text-subtitle-1 font-weight-light">Comment</span>
                  </v-card-title>

                  <v-card-text class="text-body-2 font-weight-bold">
                    {{ comment.comment }}
                  </v-card-text>

                  <v-card-actions class="p-0">
                    <v-list-item class="gro p-0 m-0" dense>
                      <v-list-item-avatar color="grey darken-3">
                        <v-img
                          class="elevation-6"
                          alt=""
                          :src="getAvatar(comment.commenterId)"
                        ></v-img>
                      </v-list-item-avatar>

                      <v-list-item-content class="p-0">
                        <v-list-item-title class="text-body-2">{{ dateWork(comment.commentDate) }}</v-list-item-title>
                      </v-list-item-content>

                      <v-row
                        align="center"
                        justify="end"
                      >
                        <v-icon class="mr-1">
                          mdi-heart
                        </v-icon>
                        <span class="subheading mr-2">0</span>
                        <span class="mr-1">·</span>
                        <v-icon class="mr-1">
                          mdi-share-variant
                        </v-icon>
                        <span class="subheading">0</span>
                      </v-row>
                    </v-list-item>
                  </v-card-actions>
                </v-card>
              </template>

            </v-card-text>
            <v-card-actions>
              <v-btn
                color="blue darken-1"
                text
                @click="commentShow = false"
              >
                Close
              </v-btn>

              <v-btn
                color="blue darken-1"
                text
                @click="writeReview"
              >
                Write
              </v-btn>

              <v-text-field
                v-model="commentText"
                :rules="rules"
                counter="200"
                hint="Write Comment Here"
                label="Regular"
              ></v-text-field>

            </v-card-actions>
          </v-card>

        </v-dialog>
      </v-row>
    </template>

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

    <!--    <h1> {{ group }}</h1>-->
    <v-row align="center">
      <h1 class="grey--text">
        {{ group.groupName }}
      </h1>
      <v-spacer></v-spacer>
      <!--      <div>-->
      <!--        <v-btn small outlined>Start New Task</v-btn>-->
      <!--      </div>-->

      <!--   FIXME   Start Of Start Task-->
      <template>

        <v-dialog max-width="600px ">
          <template v-slot:activator="{ on, attrs }">
            <div class="text-center">

              <v-btn slot="activator" class="mr-0" v-bind="attrs" v-on="on">
                <span>Start A New Task</span>
              </v-btn>
            </div>
          </template>

          <v-card>
            <v-card-title>
              <h4>Start A New Task</h4>
            </v-card-title>
            <v-card-text>
              <v-form class="px-3">
                <v-text-field label="Title" v-model="title" prepend-icon="mdi-folder"></v-text-field>
                <v-text-field label="Description" v-model="description" prepend-icon="mdi-pencil"></v-text-field>

                <v-row class="px-3">
                  <v-menu
                    ref="menu"
                    v-model="menu"
                    :close-on-content-click="true"
                    transition="scale-transition"
                    offset-x
                    min-width="290px">
                    <template v-slot:activator="{ on, attrs }">
                      <v-text-field :value="formattedDate" slot="activator" label="Due Date" prepend-icon="mdi-calendar"
                                    readonly
                                    v-bind="attrs"
                                    v-on="on"></v-text-field>
                    </template>

                    <v-date-picker
                      ref="picker"
                      :max="new Date('2022-1-1').toISOString().substr(0, 10)"
                      min="1950-01-01"
                      v-model="due">
                    </v-date-picker>
                  </v-menu>

                  <v-spacer></v-spacer>
                  <v-btn outlined class="mx-1 mt-3" :loading="submitLoading">Query Project
                  </v-btn>
                  <v-btn outlined class="mx-1 mt-3" :loading="submitLoading" @click="submitTask">Add Task</v-btn>
                </v-row>
              </v-form>
            </v-card-text>
          </v-card>
        </v-dialog>
      </template>
      <!--  FIXME    End Of Start Task-->

    </v-row>
    <v-row no-gutters class="mt-5">
      <v-col
        cols="5"
        md="3"
      >
        <v-row>
          <v-card
            flat
            class="pa-2"
            outlined
            tile
            v-for="user in group.memberList.split('_')"
            :key="user"
          >
            <!--          .col-12 .col-sm-6 .col-md-8-->
            <v-card-title>
              <v-avatar>
                <img
                  :src="getAvatar(user)"
                  alt="Err"
                >
              </v-avatar>
            </v-card-title>
            <v-card-text>{{ nameList[user] }}</v-card-text>
          </v-card>
        </v-row>
      </v-col>
      <v-col
        cols="13"
        sm="6"
        md="9"
      >

        <v-card flat
                class="pa-2"
                outlined
                tile
                v-for="task in tasks"
                :key="task.id">
          <v-row :class="`rounded-0 pa-3 task ${getStatus(task.status)}`">
            <v-flex xs10
                    md5>
              <div class="caption grey--text">Project Title</div>
              <div class="mx-1">{{ task.taskTitle }}</div>
            </v-flex>
            <v-flex xs4
                    sm3
                    md2>
              <div class="caption grey--text">Assigner</div>
              <div class="mx-1">{{ task.taskSender }}</div>
            </v-flex>
            <v-flex xs8
                    sm6
                    md3>
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
                                 :disabled="task.status === 4"
                                 :color="getColor(getStatus(task.status))"
                                 class="white--text caption my-2 float-right"
                                 v-bind="attrs"
                                 v-on="on">
                            {{ getStatus(task.status) }}
                          </v-btn>
                        </template>

                        <v-list dense>

                          <v-list-item>
                            <v-list-item-icon class="mx-3">
                              <v-icon>mdi-comment</v-icon>
                            </v-list-item-icon>
                            <v-list-item-content>
                              <v-btn outlined x-small class="mx-0" @click="showComment(task.id)">
                                Comments
                              </v-btn>
                            </v-list-item-content>
                          </v-list-item>

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
          <!--          <v-divider></v-divider>-->
        </v-card>

        <!--          .col-6 .col-md-4-->
        <!--          Render List Here-->
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios'
// eslint-disable-next-line no-unused-vars
import myTime from '@/lib/time'
import format from 'date-fns/format'

export default {
  name: 'Group',
  inject: ['reload'],
  components: {},
  data: () => ({
    menu: false,
    title: '',
    description: '',
    submitLoading: false,
    commentText: '',
    selected: 0,
    commentShow: false,
    tasks: null,
    group: null,
    due: '',
    nameList: {},
    text: '',
    snackbar: false,
    snackbarColor: 'black',
    comments: ''
  }),
  created () {
    this.group = this.$route.params.group
    //  taskList is all the task you need to query
    const taskArr = this.group.taskList.split('_')
    axios.post('http://localhost:8081/task/queryTaskByIdArr', {
      data: taskArr
    }).then(response => {
      this.tasks = response.data
      const len = this.tasks.length
      for (let i = 0; i < len; ++i) {
        this.tasks[i].status = 4
        if (this.tasks[i].userList === null) continue
        const humanArr = this.tasks[i].userList.split('_')
        const stateArr = this.tasks[i].statesList.split('_')
        const length = humanArr.length
        for (let j = 0; j < length; ++j) {
          if (parseInt(humanArr[j]) === this.$store.state.id) {
            this.tasks[i].status = parseInt(stateArr[j])
          }
        }
      }
    })
  },
  methods: {
    submitTask: function () {
      const task = {}
      task.description = this.description
      task.taskSender = this.$store.state.userID
      task.deadline = new Date(this.due)
      task.taskTitle = this.title
      task.id = this.group.id
      axios.post('http://localhost:8081/task-group/sendTask', {
        description: task.description,
        taskSender: task.taskSender,
        deadline: task.deadline,
        taskTitle: task.taskTitle,
        id: task.id
      }).then(
        response => {
          console.log(response)
        }
      )
    },
    writeReview: function () {
      const cn = this.commentText
      axios.post('http://localhost:8081/comment/makeComment/',
        {
          comment: cn,
          commenterId: this.$store.state.id,
          commentDate: new Date()
        }).then(response => {
        console.log(response.data)
        axios.get('http://localhost:8081/task/insertComment/' + this.selected + '/' + response.data).then(
          response => {
            console.log(response)
          }
        )
      })
    },
    dateWork: function (d) {
      return format(new Date(d), 'do MMM YYY - HH:MM:SS')
    },
    reloadComments () {
      axios.get('http://localhost:8081/comment//getAllComments/' + this.selected).then(
        response => {
          this.comments = response.data
        }
      )
    },
    showComment: function (id) {
      this.commentShow = true
      this.selected = id
      this.reloadComments()
    },
    getAvatar: function (id) {
      this.getName(id)
      return 'http://localhost:8081/file/read/' + id + 'avatar.png'
    },
    getName: function (id) {
      this.innerGet(id)
    },
    innerGet: function (id) {
      axios.get('http://localhost:8081/user/query/' + id).then(
        response => {
          // this.nameList[id] = response.data.userAccount
          this.$set(this.nameList, id, response.data.userAccount)
        }
      )
    },
    getStatus: function (status) {
      status = parseInt(status)
      if (status === 0) return 'ready-to-start'
      if (status === 1) return 'ongoing'
      if (status === 2) return 'complete'
      if (status === 3) return 'overdue'
      if (status === 4) return 'removed'
    },
    getColor: function (name) {
      if (name === 'complete') return 'green'
      if (name === 'ongoing') return 'blue'
      if (name === 'overdue') return 'red'
      if (name === 'ready-to-start') return 'orange darken-1'
      if (name === 'removed') return 'blue-gray'
      // alert('check your code, by getColor')
      console.log('Check your code, receive ' + name)
    },
    // deleteTask/{id}/{taskId}
    deleteTask: function (taskID) {
      axios.get('http://localhost:8081/user/deleteTask/' + this.$store.state.id + '/' + taskID).then(
        response => {
          this.reload()
          this.showMessage('Delete success')
        }
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
    markTask: function (taskID, status) {
      axios.get('http://localhost:8081/task/setState/' + this.$store.state.id + '/' + taskID + '/' + status).then(response => {
        this.reload()
        this.showMessage('Mark the task successfully')
      }).catch(
        this.showMessage('Mark the task failed')
      )
    },
    formatDate (date) {
      return date !== '' ? (format(new Date(date), 'do MMM YYY')) : ''
    }
  },
  computed: {
    formattedDate () {
      return this.due !== '' ? format(new Date(this.due), 'do MMM YYY') : ''
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
