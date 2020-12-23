<template>
  <nav>
    <!-- FIXME open snackbar when alerts -->
    <div class="text-center">
      <!--          <v-btn-->
      <!--            dark-->
      <!--            color="orange darken-2"-->
      <!--            @click="snackbar = true"-->
      <!--          >-->
      <!--            Open Snackbar-->
      <!--          </v-btn>-->

      <v-snackbar :color="snackbarColor"
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

    <v-app-bar flat
               fixed
               app>
      <v-app-bar-nav-icon class="grey--text"
                          @click="drawer = !drawer"></v-app-bar-nav-icon>

      <!--            <v-tooltip bottom>-->
      <!--                <v-btn slot="activator" color="primary" dark>-->
      <!--                    Button-->
      <!--                </v-btn>-->
      <!--                <span>Tooltip</span>-->
      <!--            </v-tooltip>-->

      <v-toolbar-title class="text-uppercase grey--text">
        <span class="font-weight-light">Pro</span>
        <span> TimeMan</span>
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <JoinGroup @taskAdd="showMessage"></JoinGroup>

      <popup :iconShow=true @taskAdd="showMessage"></popup>

      <v-btn text
             color="grey"
             @click="func">
        <v-icon>mdi-refresh</v-icon>
      </v-btn>
      <v-btn text
             color="grey"
             @click="changeTheme()">
        <v-icon>mdi-theme-light-dark</v-icon>
      </v-btn>

      <!--      drop-down menu as same as drawer-->
      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-btn text
                 slot="activator"
                 color="grey"
                 v-bind="attrs"
                 v-on="on">
            <v-icon left>mdi-unfold-more-horizontal</v-icon>
            <span>Menu</span>
          </v-btn>
        </template>
        <v-list>
          <v-list-item v-for="link in links"
                       :key="link.text"
                       router
                       :to="link.router">
            <v-list-item-title>
              <v-icon small
                      class="mx-1">{{ link.icon }}
              </v-icon>
              <span class="ml-1">{{ link.text }}</span>
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>

      <SignIn ref="signInComponent"
              @alertFromNavVar="showMessage"
              v-if="!logged"></SignIn>

      <v-btn text
             color="grey darken-1"
             class="text-capitalize text-center"
             v-if="loginUser !== ''">
        Hello, {{ loginUser }}
      </v-btn>

      <v-btn text
             color="grey"
             @click="signOut"
             v-if="logged">
        <span> Sign Out</span>
        <v-icon right>mdi-exit-to-app</v-icon>
      </v-btn>

    </v-app-bar>

    <v-navigation-drawer absolute
                         temporary
                         v-model="drawer">
      <v-col align-self="center">
        <v-flex class="mt-5">
          <div class="text-center">
            <v-avatar size="130" class="headline">
              <img v-if="imgURL===''" src="../../public/yoshi.png"
                   alt="network Error">
              <img v-if="imgURL!==''" :src="'http://localhost:8081/file/read/' + imgURL"
                   :alt="'ID: ' + this.$store.state.id">
            </v-avatar>
          </div>
          <p class="text-center grey--text text-h6 mt-1">
            {{ this.$store.state.userID !== '' ? this.$store.state.userID : 'YoShi' }}
          </p>

          <v-row>
            <template>
              <v-file-input
                accept="image/*"
                label="File input"
                show-size
                v-model="fileURI">
              </v-file-input>
            </template>
            <v-spacer></v-spacer>
            <div class="align-self-center">
              <v-btn class="text-center" @click="submitFile">upload</v-btn>
            </div>
          </v-row>

        </v-flex>
        <v-flex class="mt-4 mb-3">
          <Popup @taskAdd="showMessage"></Popup>
        </v-flex>
      </v-col>

      <v-list>
        <v-list-item v-for="link in links"
                     :key="link.text"
                     router
                     :to="link.router">
          <v-list-item-action>
            <v-icon>{{ link.icon }}</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>
              {{ link.text }}
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </nav>
</template>

<script>
import Popup from '@/components/StartTaskPopup'
import SignIn from '@/components/SignIn'
// eslint-disable-next-line no-unused-vars
import axios from 'axios'
import fileUploader from '@/lib/fileUploader'
import JoinGroup from '@/components/JoinGroup'

export default {
  inject: ['reload'],
  components: { SignIn, Popup, JoinGroup },
  data: function () {
    return {
      fileURI: '',
      snackbar: false,
      drawer: false,
      // logged: false,
      snackbarColor: 'green',
      text: '',
      links: [
        { icon: 'mdi-view-dashboard', text: 'DashBoard', router: '/' },
        { icon: 'mdi-folder', text: 'Personal Tasks', router: '/projects' },
        { icon: 'mdi-account-supervisor', text: 'Team', router: '/team' },
        { icon: 'mdi-home', text: 'Home', router: '/home' },
        { icon: 'mdi-ab-testing', text: 'Group', router: '/group' }
      ]
    }
  },
  computed: {
    imgURL () {
      return this.$store.state.imgUrl
    },
    loginUser () {
      return this.$store.state.userID
    },
    logged () {
      return this.loginUser !== ''
    }
  },
  methods: {
    func: function () {
      this.reload()
      this.showMessage('This page was refreshed')
    },
    submitFile: async function () {
      if (this.$store.state.userID !== '') {
        await fileUploader.upload(this.fileURI, this.$store.state.id)
        this.showMessage('Upload success')
      } else {
        this.showMessage('Please log in first')
      }
    },
    changeTheme: function () {
      // below two lines are used for learning vuex and parent-child component value access
      // console.log(this.$refs.signInComponent.loginLoading)
      this.$store.commit('increment')
      this.$vuetify.theme.dark = !this.$vuetify.theme.dark
    },
    signIn: function () {
      alert('Sign In, WIP')
    },
    signOut: function () {
      this.$store.commit('loginUser', '')
      this.$store.commit('setImgURL', '')
      this.reload()
      this.showMessage('Logged Out')
    },
    alertFromNavBar: function () {
      alert('Just a nop information')
    },
    showMessage: function (message, status) {
      this.text = message
      this.snackbar = true
      if (status == null) {
        this.snackbarColor = 'black'
      } else {
        this.snackbarColor = status
      }
    }
  }
}
</script>
