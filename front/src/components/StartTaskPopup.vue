<template>

  <v-dialog max-width="600px ">
    <template v-slot:activator="{ on, attrs }">
      <div class="text-center">

        <v-btn :text="iconShow" :outlined="!iconShow" slot="activator" class="mr-0" v-bind="attrs" v-on="on">
          <span v-if="iconShow === false"> Start A New Task</span>
          <v-icon v-if="iconShow === true" color="grey">mdi-bookmark-plus-outline</v-icon>
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
            <v-btn outlined class="mx-1 mt-3" :loading="submitLoading" @click="queryTask(999)">Query Project</v-btn>
            <v-btn outlined class="mx-1 mt-3" :loading="submitLoading" @click="submitTask">Add Project</v-btn>
          </v-row>
        </v-form>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
import axios from 'axios'
import format from 'date-fns/format'

export default {
  props: {
    iconShow: Boolean
  },
  data () {
    return {
      menu: false,
      title: '',
      description: '',
      due: '',
      date: new Date(),
      submitLoading: false
    }
  },
  methods: {
    submitTask: function () {
      this.submitLoading = true
      this.date = new Date()
      axios.post('http://localhost:8081/task/submitPersonal', {
        description: this.description,
        taskTitle: this.title,
        deadline: new Date(this.due),
        // id: parseInt(this.$store.state.id)
        taskSender: this.$store.state.userID,
        userList: this.$store.state.id,
        statesList: 0
      }).then(response => {
        console.log(response)
        this.$emit('taskAdd', 'Task added ' + response.data)
        this.submitLoading = false
      }).catch(response => {
        this.$emit('taskAdd', 'Error in creating')
        this.submitLoading = false
      }
      )
      console.log(typeof (this.due))
      this.due = ''
      this.title = ''
      this.description = ''
    },
    queryTask: function (id) {
      axios.get('http://localhost:8081/task/queryTask/' + id).then(response => {
        console.log(response)
      })
    }
  },
  computed: {
    formattedDate () {
      return this.due !== '' ? format(new Date(this.due), 'do MMM YYY') : ''
    }
  }
}
</script>
