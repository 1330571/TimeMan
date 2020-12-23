<template>
  <v-container fluid>
    <h1 class="grey--text">Tag</h1>
    <v-card>
      <v-card-title><span>Tag Interactive Management</span>
        <v-btn class="mx-3" @click="add"> Add</v-btn>
        <v-btn class="mx-3" @click="submit"> Submit</v-btn>
      </v-card-title>

      <v-card-text v-for="(tag,idx) in tagList" :key="idx">

        <v-row>
          <v-col
            cols="3"
            sm="3">
            <v-chip label outlined>
              <v-text-field
                label="Name"
                v-model="tag.tagName"
              ></v-text-field>
            </v-chip>
          </v-col>

          <v-col
            cols="3"
            sm="3">
            <v-chip label outlined>
              <v-text-field

                label="Description"
                v-model="tag.tagDescription"
              ></v-text-field>
            </v-chip>
          </v-col>
          <v-col
            cols="3"
            sm="3"
          >
            <v-chip label outlined>
              <v-text-field
                label="Description"
                v-model="tag.tagColor"
              ></v-text-field>
            </v-chip>
          </v-col>
          <v-col
            cols="2"
            sm="2"
          >

            <v-chip label outlined>
              <v-btn outlined :color="tag.tagColor">preview</v-btn>
            </v-chip>
          </v-col>

          <v-col cols="1" sm="1">
            <v-chip label outlined>
              <v-btn outlined class="float-right" color="red" @click="_remove(idx)">X</v-btn>
            </v-chip>
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
