<template>
  <nav>
    <v-app-bar flat
               fixed
               app>

      <v-app-bar-nav-icon class="grey--text"
                          @click="drawer = !drawer"></v-app-bar-nav-icon>

      <v-toolbar-title class="text-uppercase grey--text">
        <span class="font-weight-light">Pro</span>
        <span> TimeMan</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn text
             color="grey"
             @click="signOut">
        <span> Sign Out</span>
        <v-icon right>mdi-exit-to-app</v-icon>
      </v-btn>
    </v-app-bar>

    <v-navigation-drawer absolute
                         temporary
                         v-model="drawer"
                         color="blue-grey lighten-2"
                         class="white--text">
      <v-list>
        <v-list-item v-for="link in links"
                     :key="link.text"
                     router
                     :to="link.router">
          <v-list-item-action>
            <v-icon class="white--text">{{link.icon}}</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title class="white--text">
              {{link.text}}
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </nav>
</template>

<script>
export default {
  data () {
    return {
      drawer: false,
      links: [
        { icon: 'mdi-view-dashboard', text: 'DashBoard', router: '/' },
        { icon: 'mdi-folder', text: 'My Projects', router: '/projects' },
        { icon: 'mdi-account-supervisor', text: 'Team', router: '/team' },
        { icon: 'mdi-home', text: 'Home', router: '/home' }
      ]
    }
  },
  methods: {
    signOut: function () {
      alert('Sign Out, WIP')
    }
  }
}
</script>
