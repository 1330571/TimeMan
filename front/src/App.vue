<template>
  <v-app>
    <nav-bar></nav-bar>
    <v-main class="mx-4 my-4">
      <router-view v-if="RouterState">

      </router-view>
    </v-main>
  </v-app>
</template>
<script>
// src/plugins/vuetify.js

import Vue from 'vue'
import Vuetify from 'vuetify/lib'
import axios from 'axios'
import NavBar from './components/NavBar.vue'
// import HelloWorld from './components/HelloWorld.vue'

Vue.use(Vuetify)

export default {

  name: 'App',
  created () {
    this.$vuetify.theme.dark = true
    axios.get('http://localhost:8081/user/getTaskIds/1').then(function (response) {
      console.log(response)
      axios.post('http://localhost:8081/task/queryTaskByIdArr', { data: response.data }).then(response => {
        console.log(response)
      })
    })
  },
  components: {
    // HelloWorld
    NavBar
  },
  provide () {
    return {
      reload: this.reload
    }
  },
  data: () => ({
    RouterState: true,
    name: 'XYS',
    drawer: null,
    tags: ['Study', 'Game', 'Animation', 'Draw'],
    icons: ['page-next', 'play', 'cached', 'check-circle', 'email', 'heart', 'star', 'sword', 'shield']
  }),

  methods: {
    reload () {
      this.RouterState = false
      this.$nextTick(() => {
        this.RouterState = true
      })
    },
    Query: function () {
      axios.get('http://localhost:8081/user/getName/admin').then(function (response) {
        console.log(response)
      })
    },
    Login: function () {
      axios.post('http://localhost:8081/user/login', {
        userAccount: 'admin',
        password: 'admin'
      }).then(function (response) {
        console.log(response)
      }).catch(function (error) {
        console.log(error)
      })
    },
    HuangShenNB: function () {
      alert('黄神武帝')
    },
    messageIt: function (name) {
      alert('you click ' + name)
    },
    loginAccount: function (account, password) {
      // deprecated cause write it in the SignIn.vue and use vuex to manage state
    }
  }
}
</script>
