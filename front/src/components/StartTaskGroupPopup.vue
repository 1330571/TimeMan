<template>

  <v-dialog max-width="600px ">
    <template v-slot:activator="{ on, attrs }">
      <div class="text-center">

        <v-btn slot="activator" text class="mr-0" v-bind="attrs" v-on="on">
          <v-icon color="grey">mdi-account-group</v-icon>
        </v-btn>

      </div>
    </template>

    <v-card>
      <v-card-title>
        <h4>Start A New Group</h4>

      </v-card-title>
      <v-card-text>
        <v-form class="px-3">
          <v-text-field label="Title" v-model="title" prepend-icon="mdi-folder"></v-text-field>
          <v-text-field label="Description" v-model="description" prepend-icon="mdi-pencil"></v-text-field>
          <v-text-field label="JoinCOde" v-model="link_code" prepend-icon="mdi-barcode"></v-text-field>
          <v-row class="px-3">
            <v-spacer></v-spacer>
            <v-btn outlined class="mx-1 mt-3" :loading="submitLoading" @click="createGroup">Create Group</v-btn>
          </v-row>
        </v-form>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
import axios from 'axios'

export default {
  name: 'StartTaskGroupPopup',
  props: {
    iconShow: Boolean
  },
  data () {
    return {
      menu: false,
      title: '',
      description: '',
      link_code: '',
      classList: '',
      taskList: '',
      submitLoading: false
    }
  },
  methods: {
    createGroup: function () {
      // groupName linkCode groupDesc creatorID:auto
      axios.post('http://localhost:8081/task-group/CreateGroup', {
        creatorId: this.$store.state.id,
        groupName: this.title,
        groupDesc: this.description,
        linkCode: this.link_code
      }).then(response => {
        console.log(response.data)
      })
    }
  }
}

</script>
