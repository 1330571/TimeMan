<template>

  <div class="projects">
    <h1 class="subheading grey--text">Project</h1>

    <template>
      <v-row justify="center">
        <v-dialog
          v-model="dialog"
          max-width="290"
        >
          <v-card>
            <v-card-title class="headline">
              <v-icon color="red"> {{ dialogIcon }}</v-icon>
              <div class="mx-2">Tip</div>
            </v-card-title>

            <v-card-text class="text-body-1 text-center">

              {{ alertMessage }}
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>

              <!--                            <v-btn-->
              <!--                                color="green darken-1"-->
              <!--                                text-->
              <!--                                @click="dialog = false"-->
              <!--                            >-->
              <!--                                Cancel-->
              <!--                            </v-btn>-->
              <v-btn
                color="grey darken-1"
                text
                @click="dialog = false"
              >
                Ok
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>
    </template>

    <div>

      <div class="text-center d-flex pb-4">

        <v-btn text outlined @click="all">
          Expand All
        </v-btn>
        <!--                <div>{{ panel }}</div>-->
        <v-btn text outlined class="mx-2" @click="none">
          Fold All
        </v-btn>

        <v-btn text outlined class="mx-2" @click="mostRecent">
          Most Recent
        </v-btn>

        <v-btn text outlined class="mx-2" @click="notWorry">
          Not Worry
        </v-btn>
      </div>

      <v-expansion-panels
        v-model="panel"
        multiple
      >
        <v-expansion-panel
          v-for="(item,i) in items"
          :key="i"
        >
          <v-expansion-panel-header>Header {{ item }}</v-expansion-panel-header>
          <v-expansion-panel-content>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
            labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
            laboris nisi ut aliquip ex ea commodo consequat.
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
    </div>

  </div>
</template>

<script>
export default {
  name: 'Projects',
  components: {},
  data: () => ({
    items: ['Ocaml Course', 'Java Course', 'Linux Kernel', 'The Compilers'],
    panel: [],
    dialog: false,
    dialogIcon: '',
    alertMessage: ''
  }),
  methods: {
    // Create an array the length of our items
    // with all values as true
    all () {
      const arr = []
      for (let i = 0; i < this.items.length; ++i) {
        arr.push(i)
      }
      this.panel = arr
    },
    // Reset the panel
    none () {
      this.panel = []
    },
    mostRecent () {
      this.alertMessage = 'Work In Progress'
      this.dialogIcon = 'mdi-alert-circle'
      this.dialog = true
    },
    notWorry () {
      this.alertMessage = 'Work In Progress'
      this.dialogIcon = 'mdi-alert-circle'
      this.dialog = true
    }
  }
}
</script>
