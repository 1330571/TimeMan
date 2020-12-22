<template>
  <v-dialog max-width="600px">
    <template v-slot:activator="{ on , attrs }">
      <v-btn text
             color="grey"
             v-bind="attrs"
             v-on="on">
        <span> Sign In</span>
        <v-icon right>mdi-login</v-icon>
      </v-btn>
    </template>

    <v-card>
      <v-card-title>
        <h4>Sign in</h4>
      </v-card-title>
      <v-card-text>
        <v-form class="px-3">
          <v-text-field label="account" v-model="account" prepend-icon="mdi-account"></v-text-field>
          <v-text-field label="password" v-model="password" prepend-icon="mdi-onepassword"></v-text-field>
          <v-row class="px-3">
            <v-spacer></v-spacer>
            <v-btn outlined class="mx-1 mt-3" :loading="loginLoading" @click="login">Sign in</v-btn>
          </v-row>
        </v-form>
      </v-card-text>
    </v-card>

  </v-dialog>
</template>

<script>
import axios from 'axios'

export default {
  components: {},
  data: function () {
    return {
      loginLoading: false,
      account: '',
      password: ''
    }
  },
  inject: ['reload'],
  methods: {
    login: function () {
      if (this.account === '' || this.password === '') {
        this.$emit('alertFromNavVar', 'Empty Username or Password')
        return
      }
      this.loginLoading = true

      axios.post('http://localhost:8081/user/login', {
        userAccount: this.account,
        password: this.password
      }).then(response => {
        console.log('Login Function ' + response.data)
        if (response.data !== 'Error') {
          this.$store.commit('setUserID', parseInt(response.data))
          this.$store.commit('loginUser', this.account)
          this.$store.commit('setImgURL', this.$store.state.id + 'avatar.png')
          this.loginLoading = false
          this.reload()
          this.$emit('alertFromNavVar', 'Login Success')
        } else {
          this.$emit('alertFromNavVar', 'Wrong Username or Password')
          this.loginLoading = false
        }
      })
    }
  }
}
</script>
