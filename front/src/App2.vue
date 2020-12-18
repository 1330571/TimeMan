  <template>
  <v-app>
    <v-app-bar app
               color="primary"
               dark>
      <div class="d-flex align-center">
        <v-img alt="Vuetify Logo"
               class="shrink mr-2"
               contain
               src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
               transition="scale-transition"
               width="40" />

        <v-img alt="Vuetify Name"
               class="shrink mt-1 hidden-sm-and-down"
               contain
               min-width="100"
               src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"
               width="100" />
      </div>

      <v-spacer></v-spacer>

      <v-btn href="https://github.com/vuetifyjs/vuetify/releases/latest"
             target="_blank"
             text>
        <span class="mr-2">Latest Release</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar>

    <v-main>

      <v-sheet tile
               height="54"
               class="d-flex">
        <v-btn icon
               class="ma-2"
               @click="$refs.calendar.prev()">
          <v-icon>mdi-chevron-left</v-icon>
        </v-btn>
        <v-select v-model="type"
                  :items="types"
                  dense
                  outlined
                  hide-details
                  class="ma-2"
                  label="type"></v-select>
        <v-select v-model="mode"
                  :items="modes"
                  dense
                  outlined
                  hide-details
                  label="event-overlap-mode"
                  class="ma-2"></v-select>
        <v-select v-model="weekday"
                  :items="weekdays"
                  dense
                  outlined
                  hide-details
                  label="weekdays"
                  class="ma-2"></v-select>
        <v-spacer></v-spacer>
        <v-btn icon
               class="ma-2"
               @click="$refs.calendar.next()">
          <v-icon>mdi-chevron-right</v-icon>
        </v-btn>
      </v-sheet>
      <v-sheet height="600">
        <v-calendar ref="calendar"
                    v-model="value"
                    :weekdays="weekday"
                    :type="type"
                    :events="events"
                    :event-overlap-mode="mode"
                    :event-overlap-threshold="30"
                    :event-color="getEventColor"
                    @change="getEvents"></v-calendar>
      </v-sheet>

      <HelloWorld />
      <v-avatar color="primary"
                size="56"> {{ name }}</v-avatar>
      <v-btn color="secondary"
             elevation="2"
             icon
             x-large
             @click="HuangShenNB"></v-btn>
      <template>
        <v-timeline>
          <v-timeline-item>10点 学语文</v-timeline-item>
          <v-timeline-item class="text-right">
            12点 学数学
          </v-timeline-item>
          <v-timeline-item>17点 学英语</v-timeline-item>
        </v-timeline>
      </template>
    </v-main>
  </v-app>
</template>

<script>
import HelloWorld from './components/HelloWorld'

export default {
  name: 'App',

  components: {
    HelloWorld
  },

  data: () => ({
    name: 'XYS'
  }),

  methods: {
    HuangShenNB: function () {
      alert('黄神武帝')
    }
  }
}
</script>
