<template>
  <v-container fluid>
    <h1 class="grey--text">Search</h1>
    <v-text-field label="Please Input Task ID"
                  prepend-inner-icon="mdi-text-search"
                  v-model="value"
                  @keydown="redo">
    </v-text-field>
    <!--    <h1>-->
    <!--      {{ task }}-->
    <!--    </h1>-->
    <json-viewer :value="task" :theme="this.$vuetify.theme.dark === false ? 'jv-light' : 'jv-dark'"
                 ></json-viewer>
  </v-container>
</template>

<script>
import axios from 'axios'
import JsonViewer from 'vue-json-viewer'

export default {
  components: { JsonViewer },
  name: 'Search',
  data: function () {
    return {
      jsonPretty: '',
      value: '',
      task: ''
    }
  },
  methods: {
    redo: function () {
      axios.get('http://localhost:8081/task/task/' + this.value).then(response => {
        this.task = response.data
        this.jsonPretty = JSON.stringify(JSON.parse(this.task), ',', 4)
      })
    }
  }
}
</script>

<style scoped>
</style>
