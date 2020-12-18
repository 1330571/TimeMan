  <v-app id="inspire">
    <v-system-bar app
                  dark
                  height="50">
      <v-avatar color="primary"
                rounded
                size="56"
                left
                tile>XYS Logo</v-avatar>
      <div class="grey--text text-uppercase">
        <span class="font-weight-light">Pro</span>
        <span>Time Manager</span>
      </div>
      <v-btn text
             color="grey">click</v-btn>
      <v-spacer></v-spacer>
      <v-badge>QAQ</v-badge>
      <v-spacer></v-spacer>
      <v-col cols="12"
             sm="6"
             class="py-2">
        <p>Exclusive</p>

        <v-btn-toggle v-model="toggle_exclusive">
          <v-btn>
            <v-icon>mdi-format-align-left</v-icon>
          </v-btn>

          <v-btn @click="Login">
            <v-icon>mdi-format-align-center</v-icon>
          </v-btn>

          <v-btn @click="Query">
            <v-icon>mdi-format-align-right</v-icon>
          </v-btn>

          <v-btn>
            <v-icon>mdi-format-align-justify</v-icon>
          </v-btn>
        </v-btn-toggle>
      </v-col>
      <v-spacer></v-spacer>
      <v-icon>mdi-square</v-icon>

      <v-icon>mdi-circle</v-icon>

      <v-icon @click="HuangShenNB">mdi-triangle</v-icon>
      Pro Time Manage Tools
    </v-system-bar>

    <v-app-bar app
               clipped-right
               flat
               height="72">
      <v-spacer></v-spacer>

      <v-responsive max-width="156">
        <v-text-field dense
                      flat
                      hide-details
                      rounded
                      solo-inverted></v-text-field>
      </v-responsive>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer"
                         app
                         width="300">
      <v-navigation-drawer v-model="drawer"
                           absolute
                           color="grey lighten-3"
                           mini-variant>
        <v-avatar class="d-block text-center mx-auto mt-4"
                  color="grey darken-1"
                  size="36"></v-avatar>

        <v-divider class="mx-3 my-5"></v-divider>

        <v-avatar v-for="n in 6"
                  :key="n"
                  class="d-block text-center mx-auto mb-9"
                  color="grey lighten-1"
                  size="28"></v-avatar>
      </v-navigation-drawer>

      <v-sheet color="grey lighten-5"
               height="128"
               width="100%"></v-sheet>

      <v-list class="pl-14"
              shaped>
        <v-list-item v-for="n in 5"
                     :key="n"
                     link>
          <v-list-item-content>
            <v-list-item-title>Item {{ n }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-navigation-drawer app
                         clipped
                         right>
      <v-list>
        <v-list-item v-for="n in 5"
                     :key="n"
                     link>
          <v-list-item-content>
            <v-list-item-title>Item {{ n }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-main>
      <!--  -->
      <!-- <router-view></router-view> -->
      <!--  <router-view> -->
      <p class="red lighten-1 white--text text-darken-4">Lorem ipsum dolor sit amet consectetur adipisicing elit. Et recusandae odio dolores placeat, blanditiis aliquam doloremque omnis quas ab architecto itaque eius nobis! Molestias autem eius culpa sit quam et.</p>

      <v-row justify="center">
        <v-col cols="12"
               sm="7"
               md="6"
               lg="5">
          <v-sheet elevation="10"
                   rounded="xl">
            <v-sheet class="pa-3 primary text-right"
                     dark
                     rounded="t-xl">
              <v-btn icon>
                <v-icon>mdi-content-save-cog-outline</v-icon>
              </v-btn>

              <v-btn class="ml-2"
                     icon>
                <v-icon>mdi-check-bold</v-icon>
              </v-btn>
            </v-sheet>

            <div class="pa-4">
              <v-chip-group active-class="primary--text"
                            column>
                <v-chip v-for="(tag,index) in tags"
                        :key="tag"
                        @click="messageit(tag)">
                  {{ index + ' ' + tag }}
                </v-chip>
              </v-chip-group>
            </div>
          </v-sheet>
        </v-col>
      </v-row>

      <div>
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
      </div>
      <!-- fab use for button -->
      <v-btn depressed
             color=yellow
             v-for="icon in icons"
             :key="icon">
        <v-icon left>mdi-{{icon}}</v-icon>
        <span>{{icon + ' Icon'}}</span>
      </v-btn>
      <!-- </router-view> -->
      <p> hello? </p>
      <v-icon>mdi-email</v-icon>
      <hello-world></hello-world>
    </v-main>

    <v-footer app
              color="transparent"
              height="72"
              inset>
      <v-text-field background-color="grey lighten-1"
                    dense
                    flat
                    hide-details
                    rounded
                    solo></v-text-field>
    </v-footer>
  </v-app>