import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    count: 0,
    id: 0,
    userID: ''
  },
  mutations: {
    increment (state) {
      state.count++
      console.log('increment commit receive -> ' + state.count)
    },
    loginUser (state, userID) {
      state.userID = userID
      console.log('login user success, global userinfo modified -> ' + userID)
    },
    setUserID (state, id) {
      state.id = parseInt(id)
      console.log('login user success, private user modified -> ' + id)
    }
  },
  actions: {},
  modules: {}
})
