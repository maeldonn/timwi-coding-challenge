<template>
  <div class="search-list">
    <Album v-for="album in albums" :key="album.id" :album="album"/>
  </div>
</template>

<script>
import Album from '@/components/search/Album';

export default {
  name: 'SearchList',
  components: {
    Album,
  },
  props: {
    albums: {type: Array, required: true}
  }
};
</script>