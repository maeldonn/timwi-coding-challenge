<template>
  <div class="tag-dialog">
    <v-dialog v-if="!disabled()" v-model="dialog" max-width="290">
      <template v-slot:activator="{ on, attrs }">
        <v-btn color="primary" dark v-bind="attrs" v-on="on">
          Create tag
        </v-btn>
      </template>
      <v-card>
        <v-card-title class="text-h5">
          Create a new tag
        </v-card-title>
        <v-card-text>
          <v-text-field
              v-model="tagName"
              filled
              v-on:keyup.enter="create()"
          ></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              :disabled="!tagName"
              color="green darken-1"
              text
              @click="create()"
          >
            CREATE
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import {createTag} from "@/api/tag-api";

export default {
  name: 'TagDialog',
  props: {
    albums: {type: Array, required: true}
  },
  data() {
    return {
      dialog: false,
      tagName: '',
    }
  },
  methods: {
    disabled() {
      return this.albums.filter(album => album.selected).length === 0;
    },
    create() {
      if (!this.tagName) return;
      createTag({
        name: this.tagName,
        albums: this.albums.filter(album => album.selected)
      })
          .then(() => {
            this.$emit('created');
          })
          .catch(() => {
            // Handle error
          });
      this.dialog = false;
      this.tagName = '';
    }
  }
}
</script>

<style scoped>
.tag-dialog {
  margin: 30px;
  display: flex;
  justify-content: center;
}
</style>