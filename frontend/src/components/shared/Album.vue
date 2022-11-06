<template>
  <div class="album">
    <v-card hover>

      <v-list-item>
        <v-list-item-avatar tile size="300" color="grey">
          <v-img :src="album.image"></v-img>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-card-title>
            <h2>{{ album.title.toUpperCase() }}</h2>
          </v-card-title>

          <v-card-text>
            Artist: {{ album.artists }}<br>
            Release date: {{ getReleaseDate }}<br>
            Total tracks: {{ album.duration }}<br>
          </v-card-text>

          <v-card-text>
            <span v-if="!isSearch && album.tags">
              <v-chip v-for="tag in album.tags" :key="tag.id">{{ tag.name }}</v-chip>
            </span>
          </v-card-text>

          <v-card-actions v-if="isSearch">
            <v-btn color=primary @click="togglePersonal">{{ getTogglePersonalText }}</v-btn>
          </v-card-actions>

          <v-card-actions v-else>
            <v-checkbox v-model="album.selected"></v-checkbox>
            <v-btn class="ma-2" @click="toggleFavorite" x-large text icon :color="getFavoriteColor">
              <v-icon>{{ getFavoriteIcon }}</v-icon>
            </v-btn>
          </v-card-actions>
        </v-list-item-content>

      </v-list-item>

    </v-card>
  </div>
</template>
<script>
import {addAlbumToPersonalList, removeAlbumFromPersonalList, toggleFavoriteAlbum} from "@/controllers/album.controller";

export default {
  name: 'Album',
  props: {
    album: {type: Object, required: true},
    isSearch: {type: Boolean, default: false}
  },
  computed: {
    getReleaseDate() {
      const date = new Date(this.album.releaseDate);
      if (date.getDay() === 0 && date.getMonth() === 0) {
        return date.getFullYear();
      }
      return date.toLocaleDateString("fr-FR");
    },
    getTogglePersonalText() {
      if (this.album.personal) {
        return 'Remove from personal list';
      }
      return 'Add to personal list';
    },
    getFavoriteIcon() {
      if (this.album.favorite) {
        return 'mdi-thumb-down';
      }
      return 'mdi-thumb-up';
    },
    getFavoriteColor() {
      if (this.album.favorite) {
        return 'red lighten-2';
      }
      return 'blue lighten-2';
    }
  },
  methods: {
    togglePersonal() {
      if (this.album.personal) {
        this.remove();
      } else {
        this.add();
      }
    },
    async add() {
      await addAlbumToPersonalList(this.album.id, () => this.album.personal = true);
    },
    async remove() {
      await removeAlbumFromPersonalList(this.album.id, () => this.album.personal = false);
    },
    async toggleFavorite() {
      await toggleFavoriteAlbum(this.album, () => this.album.favorite = !this.album.favorite);
    }
  }
};
</script>

<style scoped>
.album {
  margin: 40px 5%;
}
</style>