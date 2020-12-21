<template>
  <div class="team">
    <h1 class="subheading grey--text">Team</h1>
    <v-row wrap>
      <v-flex xs12 sm6 md4 lg3 v-for="team in teams" :key="team.name">
        <v-card outlined class="ma-3">
          <v-responsive class="pt-4 text-sm-center">
            <v-tooltip top>
              <template v-slot:activator="{ on, attrs }">
                        <span>
                            <v-icon small :color="getColor(team.task)" v-bind="attrs"
                                    v-on="on">mdi-alert-box-outline</v-icon>
                            {{ team.task }}
                        </span>
              </template>
              <span>Task have not completed</span>
            </v-tooltip>
          </v-responsive>
          <v-card-text>
            <div class="text-sm-center text-h4">
              {{ team.name }}
            </div>
            <div class="text-sm-center grey--text mt-3">
              {{ team.desc }}
            </div>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-tooltip top>
              <template v-slot:activator="{ on, attrs }">
                <v-btn class="float-right" text color="blue" v-bind="attrs" v-on="on">
                  <v-icon small left>mdi-arrow-right-bold-hexagon-outline</v-icon>
                  <span class="text-sm-body-2">more</span>
                </v-btn>
              </template>
              <span>See Team Details</span>
            </v-tooltip>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-row>
  </div>
</template>

<script>
export default {
  name: 'Team',
  components: {},
  data () {
    return {
      teams: [
        { name: 'Java Group', desc: 'Develop Spring Boot Application', task: 0 },
        { name: 'Ocaml Group', desc: 'Develop Ocaml Data Structure', task: 4 },
        { name: 'F# Group', desc: 'Somebody says F# is good at drawing', task: 0 },
        { name: 'Compilers Group', desc: 'Today I can learn how to use attributed grammar', task: 0 },
        { name: 'Chinese Group', desc: 'Learn poems, write poems', task: 2 },
        { name: 'Animation Group', desc: 'Watch animations, love animations,　最高です!', task: 0 },
        { name: 'Cloud Computing', desc: 'We use hadoop, write mapreduce, and spark', task: 7 }
      ]
    }
  },
  methods: {
    getColor: function (num) {
      if (num === 0) return 'green'
      if (num <= 3) return 'blue '
      if (num <= 5) return 'orange darken-1 '
      return 'red'
    }
  }
}
</script>
