<template>
  <div class="album">
    <v-card hover>
      <v-card-title>
        <h2>{{ album.title }}</h2>
      </v-card-title>

      <v-card-text>
        Release date: {{ new Date(album.date).toLocaleDateString() }}<br>
        Total tracks: {{ album.duration }}<br>
        <span v-if="album.tags">Tags: {{ album.tags }}</span>
      </v-card-text>

      <v-card-actions>
        <v-btn v-if="!personalList" color=primary @click="add()">Add to personal list</v-btn>
        <v-btn v-else color=primary @click="remove()">Remove from personal list</v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Album',
  props: {
    album: {type: Object, required: true}
  },
  data() {
    return {
      personalList: false,
    };
  },
  methods: {
    add() {
      axios
          .post(`${process.env.VUE_APP_ROOT_API}/albums/add/${this.album.id}`)
          .then(() => {
            this.personalList = true;
          })
          .catch(() => {
            // Handle error
          });
    },
    remove() {
      axios
          .delete(`${process.env.VUE_APP_ROOT_API}/albums/remove/${this.album.id}`)
          .then(() => {
            this.personalList = false;
          })
          .catch(() => {
            // Handle error
          });
    }
  }
};
</script>

<style scoped>
.album {
  margin: 40px 5%;
}
</style>