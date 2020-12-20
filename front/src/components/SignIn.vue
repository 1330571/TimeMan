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
export default {
  components: {},
  data: function () {
    return {
      loginLoading: false,
      account: '',
      password: ''
    }
  },
  methods: {
    login: function () {
      this.loginLoading = true
      this.$emit('alertFromNavVar', 'Login Success')
      this.loginLoading = false
    }
  }
}
</script>
