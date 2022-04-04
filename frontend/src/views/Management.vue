<template>
  <div class="management">
    <TagDialog :albums="albums" v-on:created="getAlbums()"/>
    <AlbumList :albums="albums"/>
  </div>
</template>

<script>
import AlbumList from "@/components/shared/AlbumList";
import TagDialog from "@/components/management/TagDialog";
import {getAlbums} from "@/api/album-api";

export default {
  name: 'Management',
  components: {
    TagDialog,
    AlbumList,
  },
  data() {
    return {
      albums: [],
    };
  },
  mounted() {
    this.getAlbums();
  },
  methods: {
    getAlbums() {
      getAlbums(undefined, true)
          .then((result) => {
            this.albums = result.data;
          })
          .catch(() => {
            this.albums = [];
          });
    },
  }
};
</script>