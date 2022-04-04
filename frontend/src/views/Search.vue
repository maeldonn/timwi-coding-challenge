<template>
  <div class="search">
    <SearchForm v-on:search="search"/>
    <AlbumList :albums="albums" :isSearch="true"/>
  </div>
</template>

<script>
import SearchForm from '@/components/search/SearchForm';
import AlbumList from '@/components/shared/AlbumList';
import {getAlbums} from "@/api/album-api";

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
    search(filter) {
      if (!filter) return;
      getAlbums(filter)
        .then((result) => {
          this.albums = result.data;
        })
        .catch((error) => {
          if (error.response.status === 401) {
            window.location.href = error.response.data;
          }
        });
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
