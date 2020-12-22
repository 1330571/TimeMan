<template>

  <v-dialog max-width="600px ">
    <template v-slot:activator="{ on, attrs }">
      <div class="text-center">

        <v-btn text
               color="grey"
               slot="activator"
               v-bind="attrs"
               v-on="on">
          <v-icon>mdi-steam</v-icon>
        </v-btn>

      </div>
    </template>

    <v-card>
      <v-card-title>
        <h4>Join Group</h4>

      </v-card-title>
      <v-card-text>
        <v-form class="px-3">
          <v-row>
            <v-text-field label="Join Code" v-model="code" prepend-icon="mdi-pencil"></v-text-field>
            <v-btn outlined class="mx-1 mt-3" :loading="submitLoading" @click="submitTask">Join</v-btn>
          </v-row>
        </v-form>
      </v-card-text>
    </v-card>

  </v-dialog>
</template>

<script>
import axios from 'axios'
// import format from 'date-fns/format'

export default {
  inject: ['reload'],
  data () {
    return {
      code: '',
      menu: false,
      submitLoading: false
    }
  },
  methods: {
    submitTask: function () {
      if (this.$store.state.userID === '') {
        this.$emit('taskAdd', 'Please log in first')
        return
      }
      this.submitLoading = true
      axios.get('http://localhost:8081/task-group/joinGroup/' + this.$store.state.id + '/' +
        parseInt(this.code)).then(response => {
        this.$emit('taskAdd', 'Success' + 'Log: ' + response.data)
        this.submitLoading = false
      })
    }
  }
}

</script>
