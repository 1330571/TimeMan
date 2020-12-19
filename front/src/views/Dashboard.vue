<template>
  <div class="dashboard">
    <h1 class="subheading grey--text">Dashboard</h1>
    <v-container class="my-5">
      <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quae non soluta aliquid quam dolorem, suscipit
        laborum
        voluptatem minus incidunt aspernatur possimus aliquam in! Eveniet labore, culpa et hic id
        recusandae?</p>
      <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quae non soluta aliquid quam dolorem, suscipit
        laborum
        voluptatem minus incidunt aspernatur possimus aliquam in! Eveniet labore, culpa et hic id
        recusandae?</p>
      <!--      <v-layout row>-->
      <!--        <v-flex xs12 md6>-->
      <!--          <v-btn outlined block color="grey">1</v-btn>-->
      <!--        </v-flex>-->
      <!--        <v-flex xs4 md2>-->
      <!--          <v-btn outlined block color="grey">2</v-btn>-->
      <!--        </v-flex>-->
      <!--        <v-flex xs4 md2>-->
      <!--          <v-btn outlined block color="grey">2</v-btn>-->
      <!--        </v-flex>-->
      <!--        <v-flex xs4 md2>-->
      <!--          <v-btn outlined block color="grey">2</v-btn>-->
      <!--        </v-flex>-->
      <!--      </v-layout>-->
      <v-row class="mb-3">
        <v-tooltip top>
          <template v-slot:activator="{ on, attrs }">
            <v-btn small
                   text
                   color="grey"
                   @click="sortByProjectName()"
                   v-bind="attrs"
                   v-on="on">
              <v-icon left
                      small>mdi-folder</v-icon>
              <span class="caption text-lowercase">By Project Name</span>
            </v-btn>
          </template>
          <span>Sort Projects By Project Name</span>
        </v-tooltip>
        <v-btn small
               text
               color="grey"
               @click="sortByAssigner()">
          <v-icon left
                  small>mdi-account-check-outline</v-icon>
          <span class="caption text-lowercase">By Assigner</span>
        </v-btn>
        <v-btn small
               text
               color="grey"
               @click="sortByDeadline()">
          <v-icon left
                  small>mdi-av-timer</v-icon>
          <span class="caption text-lowercase">By Deadline</span>
        </v-btn>
        <v-btn small
               text
               color="grey"
               @click="sortByStatus()">
          <v-icon left
                  small>mdi-account-check-outline</v-icon>
          <span class="caption text-lowercase">By Status</span>
        </v-btn>
      </v-row>
      <v-card flat
              v-for="task in tasks"
              :key="task.title">
        <v-row :class="`rounded-0 pa-3 task ${task.status}`">
          <v-flex xs12
                  md6>
            <div class="caption grey--text">Project Title</div>
            <div class="mx-1">{{ task.title }}</div>
          </v-flex>
          <v-flex xs6
                  sm4
                  md2>
            <div class="caption grey--text">Assigner</div>
            <div class="mx-1">{{ task.assigner }}</div>
          </v-flex>
          <v-flex xs6
                  sm4
                  md2>
            <div class="caption grey--text">Deadline</div>
            <div class="mx-1">{{ task.deadline }}</div>
          </v-flex>
          <!--          <v-flex xs6 sm4 md2>-->
          <!--            <div class="caption grey&#45;&#45;text">Status</div>-->
          <!--            <div class="mx-1">{{ task.status }}</div>-->
          <!--          </v-flex>-->
          <v-flex>
            <div>
              <v-chip outlined
                      small
                      :color="getColor(task.status)"
                      class="white--text caption my-2 float-right">
                {{ task.status }}
              </v-chip>
            </div>
          </v-flex>
        </v-row>
        <v-divider></v-divider>
      </v-card>
    </v-container>

    <!--    <v-row class="text-center">-->
    <!--      <v-col cols="12">-->
    <!--        <v-card class="d-flex flex-nowrap py-3" color="grey lighten-2" flat tile>-->
    <!--          <v-card v-for="n in 5" :key="n" class="pa-2" outlined tile>Flex item</v-card>-->
    <!--        </v-card>-->
    <!--      </v-col>-->
    <!--    </v-row>-->

  </div>
</template>

<script>

export default {
  name: 'Dashboard',
  components: {},
  data: () => ({
    justify: ['start', 'end', 'center', 'baseline', 'auto', 'stretch'],
    tasks: [
      {
        title: 'Play Cyberpunk 2007',
        assigner: 'XYS',
        deadline: '2021-1-29',
        status: 'overdue'
      },
      {
        title: 'Create a new website',
        assigner: 'XYS',
        deadline: '2020-12-21',
        status: 'ongoing'
      }, {
        title: 'Finish a compiler',
        assigner: 'XYS',
        deadline: '2020-12-25',
        status: 'complete'
      }, {
        title: 'Play Cyberpunk 2077',
        assigner: 'GZ',
        deadline: '2021-2-13',
        status: 'overdue'
      },
      {
        title: 'Play Cyberpunk 2017',
        assigner: 'XYS',
        deadline: '2021-1-21',
        status: 'overdue'
      },
      {
        title: 'Look Animation',
        assigner: 'GZ',
        deadline: '2020-6-6',
        status: 'ready-to-start'
      }
    ]
  }),
  methods: {
    sortBy: function (prop) {
      this.tasks.sort((a, b) => a[prop] < b[prop] ? -1 : 1)
    },
    getColor: function (name) {
      if (name === 'complete') return 'blue'
      if (name === 'ongoing') return 'orange'
      if (name === 'overdue') return 'red'
      if (name === 'ready-to-start') return 'green darken-1'
      alert('check your code, by getColor')
    },
    sortByProjectName: function () {
      this.sortBy('title')
    },
    sortByDeadline: function () {
      this.sortBy('deadline')
    },
    sortByStatus: function () {
      this.sortBy('status')
    },
    sortByAssigner: function () {
      this.sortBy('assigner')
    }
  }
}
</script>

<style>
.task.complete {
  border-left: 4px solid #2196f3;
}

.task.ongoing {
  border-left: 4px solid #ff9800;
}

.task.overdue {
  border-left: 4px solid #f44336;
}

.task.ready-to-start {
  border-left: 4px solid #43a047;
}
</style>
