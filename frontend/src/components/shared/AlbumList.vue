<template>
  <div class="search-list">
    <Album v-for="album in albums" :key="album.albumId" :album="album" :isSearch="isSearch"/>
  </div>
</template>

<script>
import Album from '@/components/shared/Album';

export default {
  name: 'AlbumList',
  components: {
    Album,
  },
  props: {
    albums: {type: Array, required: true},
    isSearch: {type: Boolean, default: false}
  }
};
</script>