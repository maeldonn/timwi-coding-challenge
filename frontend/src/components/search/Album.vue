<template>
  <div class="album">
    <v-card hover>
      <v-card-title>
        <h2>{{ album.name }}</h2>
      </v-card-title>

      <v-card-text>
        Release date: {{ new Date(album.release_date).toLocaleDateString() }}<br>
        Total tracks: {{ album.total_tracks }}<br>
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
          .post(`http://localhost:8080/api/v1/albums/remove/${this.album.id}`,{})
          .then(() => {
            this.personalList = !this.personalList;
          })
          .catch(() => {
            // Handle error
          });
      this.personalList = !this.personalList;
    },
    remove() {
      axios
          .delete(`http://localhost:8080/api/v1/albums/remove/${this.album.id}`)
          .then(() => {
            this.personalList = !this.personalList;
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