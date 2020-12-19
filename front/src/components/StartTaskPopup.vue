<template>

  <v-dialog max-width="600px ">
    <template v-slot:activator="{ on, attrs }">
      <div class="text-center">
        <v-btn outlined slot="activator" class="mr-0" v-bind="attrs" v-on="on">Start A New Task</v-btn>
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
          <v-row>
            <v-spacer></v-spacer>
            <v-btn outlined class="mx-1 mt-3" @click="queryTask(999)">Query Project</v-btn>
            <v-btn outlined class="mx-1 mt-3" @click="submitTask">Add Project</v-btn>
          </v-row>
        </v-form>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      title: '',
      description: '',
      date: new Date()
    }
  },
  methods: {
    submitTask: function () {
      this.date = new Date()
      axios.post('http://localhost:8081/task/submitPersonal', {
        description: this.description,
        taskTitle: this.title,
        deadline: new Date()
      }).then(response => {
        console.log(response)
      })
      this.title = ''
      this.description = ''
    },
    queryTask: function (id) {
      axios.get('http://localhost:8081/task/queryTask/' + id).then(response => {
        console.log(response)
      })
    }
  }
}
</script>
