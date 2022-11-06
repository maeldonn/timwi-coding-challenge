<template>
  <div class="search">
    <SearchForm v-on:search="search"/>
    <AlbumList :albums="albums" :isSearch="true"/>
  </div>
</template>

<script>
import SearchForm from '@/components/search/SearchForm';
import AlbumList from '@/components/shared/AlbumList';
import {getAlbums} from "@/controllers/album.controller";

export default {
  name: 'Search',
  components: {
    AlbumList,
    SearchForm,
  },
  data() {
    return {
      albums: [],
    };
  },
  methods: {
    async search(filter) {
      this.albums = await getAlbums(filter);
    }
  }
};
</script>

<style scoped>
.search {
  width: 100%;
  height: 600px;
}
</style>
