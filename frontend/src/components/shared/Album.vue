<template>
  <div class="album">
    <v-card hover>

      <v-list-item>
        <v-list-item-avatar
            tile
            size="300"
            color="grey"
        >
          <v-img :src="album.image"></v-img>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-card-title>
            <h2>{{ album.title.toUpperCase() }}</h2>
          </v-card-title>

          <v-card-text>
            Artist: {{ album.artist }}<br>
            Release date: {{ new Date(album.date).toLocaleDateString() }}<br>
            Total tracks: {{ album.duration }}<br>
            <span v-if="!isSearch && album.tags">
<!--              Tags: {{ album.tags }}-->
              <span class="tag" v-for="tag in album.tags" :key="tag.tagId">{{tag.name}}</span>
            </span>
          </v-card-text>

          <v-card-actions v-if="isSearch">
            <v-btn v-if="!album.personal" color=primary @click="add()">Add to personal list</v-btn>
            <v-btn v-else color=primary @click="remove()">Remove from personal list</v-btn>
          </v-card-actions>
          <v-card-actions v-else>
            <v-checkbox v-model="album.selected"></v-checkbox>
          </v-card-actions>
        </v-list-item-content>

      </v-list-item>

    </v-card>
  </div>
</template>

<script>
import {addAlbumToPersonalList, removeAlbumFromPersonalList} from "@/api/album-api";

export default {
  name: 'Album',
  props: {
    album: {type: Object, required: true},
    isSearch: {type: Boolean, default: false}
  },
  methods: {
    add() {
      addAlbumToPersonalList(this.album.albumId)
          .then(() => {
            this.album.personal = true;
          })
          .catch(() => {
            // Handle error
          });
    },
    remove() {
      removeAlbumFromPersonalList(this.album.albumId)
          .then(() => {
            this.album.personal = false;
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

.tag {
  margin-right: 10px;
  font-style: italic;
}
</style>