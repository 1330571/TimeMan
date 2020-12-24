<template>
  <v-container fluid>
    <h1 class="grey--text">Tag</h1>

    <v-card-title>
      <v-row><span>Tag Interactive Management</span>
        <v-spacer></v-spacer>
        <v-btn class="mx-3" @click="add"> Add</v-btn>
        <v-btn class="mx-3" @click="submit"> Submit</v-btn>
      </v-row>
    </v-card-title>

    <v-card class="mx-0" v-for="(tag,idx) in tagList" :key="idx">

      <v-card-text>

        <v-row>
          <v-col
            cols="3"
            sm="3">

            <v-text-field
              label="Name"
              v-model="tag.tagName"
            ></v-text-field>

          </v-col>

          <v-col
            cols="4"
            sm="4">

            <v-text-field

              label="Description"
              v-model="tag.tagDescription"
            ></v-text-field>

          </v-col>
          <v-col
            cols="3"
            sm="3"
          >

            <v-text-field
              label="Color Name(Material Design)"
              v-model="tag.tagColor"
            ></v-text-field>

          </v-col>
          <v-col
            cols="1"
            sm="1"
          >
            <v-row class="mb-0">
              <p class="mb-0"> Preview </p>
            </v-row>
            <v-row>
              <v-btn fab :color="tag.tagColor"></v-btn>
            </v-row>
          </v-col>

          <v-col cols="1" sm="1">

            <v-btn outlined class="float-right" color="red" @click="_remove(idx)">X</v-btn>

          </v-col>

        </v-row>

      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Tag',
  data: function () {
    return {
      forceWait: true,
      tagList: null
    }
  },
  inject: ['reload'],
  methods: {
    _remove: function (idx) {
      const newTagList = []
      for (let i = 0; i < this.tagList.length; ++i) {
        if (idx !== i) {
          newTagList.push(this.tagList[i])
        }
      }
      this.tagList = newTagList
    },
    add: function () {
      this.tagList.push({
        tagName: 'New',
        tagDescription: 'Change this',
        tagColor: 'black'
      })
    },
    submit: function () {
      axios.get('http://localhost:8081/tag/da').then(response => {
        const len = this.tagList.length
        for (let i = 0; i < len; ++i) {
          axios.post('http://localhost:8081/tag/set', {
            tagName: this.tagList[i].tagName,
            id: this.tagList[i].id,
            tagDescription: this.tagList[i].tagDescription,
            tagColor: this.tagList[i].tagColor
          })
        }
      })
    }
  },
  created () {
    axios.get('http://localhost:8081/tag/getAll').then(response => {
      console.log(response)
      this.tagList = response.data
    })
  }
}
</script>

<style scoped>

</style>
