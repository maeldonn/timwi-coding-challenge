<template>
  <div class="management">
    <TagDialog :albums="albums" v-on:created="getAlbums()"/>
    <AlbumList :albums="albums"/>
  </div>
</template>

<script>
import AlbumList from "@/components/shared/AlbumList";
import TagDialog from "@/components/management/TagDialog";
import {getPersonalAlbums} from "@/controllers/album.controller";

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
  async mounted() {
    this.getAlbums();
  },
  methods: {
    async getAlbums() {
      this.albums = await getPersonalAlbums();
    }
  }
};
</script>