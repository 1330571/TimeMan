<template>
  <nav>
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
                      class="mx-1">{{ link.icon }}</v-icon>
              <span class="ml-1">{{ link.text }}</span>
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>

      <v-btn text
             color="grey"
             @click="signIn"
             v-if="!logged">
        <span> Sign In</span>
        <v-icon right>mdi-login</v-icon>
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
            <v-avatar size="130">
              <img src="../../public/yoshi.png"
                   elt="network Error">
            </v-avatar>
          </div>
          <p class="text-center grey--text text-h6 mt-1">
            Yoshi
          </p>
        </v-flex>
        <v-flex class="mt-4 mb-3">
          <Popup></Popup>
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

export default {
  components: { Popup },
  data: function () {
    return {
      drawer: false,
      logged: false,
      links: [
        { icon: 'mdi-view-dashboard', text: 'DashBoard', router: '/' },
        { icon: 'mdi-folder', text: 'My Projects', router: '/projects' },
        { icon: 'mdi-account-supervisor', text: 'Team', router: '/team' },
        { icon: 'mdi-home', text: 'Home', router: '/home' }
      ]
    }
  },
  methods: {
    changeTheme: function () {
      this.$vuetify.theme.dark = !this.$vuetify.theme.dark
    },
    signIn: function () {
      alert('Sign In, WIP')
    },
    signOut: function () {
      alert('Sign Out, WIP')
    }
  }
}
</script>
