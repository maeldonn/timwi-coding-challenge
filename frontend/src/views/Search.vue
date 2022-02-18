<template>
  <div class="search">
    <SearchForm v-on:search="search"/>
    <SearchList :albums="albums"/>
  </div>
</template>

<script>
import SearchForm from '@/components/search/SearchForm';
import axios from 'axios';
import SearchList from '@/components/search/SearchList';

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
    search(filter) {
      axios
        .get(`http://localhost:8080/api/v1/albums/search?searchFilter=${filter}`)
        .then((result) => {
          this.albums = result.data.payload;
        })
        .catch(() => {
          this.albums = [];
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
