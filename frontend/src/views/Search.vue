<template>
  <div class="search">
    <SearchForm v-on:search="search"/>
    <SearchList :albums="albums"/>
  </div>
</template>

<script>
import SearchForm from '@/components/search/SearchForm';
import SearchList from '@/components/search/SearchList';
import {getAlbums} from "@/controllers/album.controller";

export default {
  name: 'Search',
  components: {
    SearchList,
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
