<template>
  <div class="search-form">
    <div class="search">

      <v-text-field
          v-model="searchFilter"
          filled
          label="Search"
          @keyup.enter="search"
      ></v-text-field>

      <v-btn icon @click="search">
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

    </div>
  </div>
</template>

<script>
export default {
  name: 'SearchForm',
  data() {
    return {
      searchFilter: '',
    };
  },
  methods: {
    search() {
      this.$emit('search', this.searchFilter);
    }
  }
};
</script>

<style scoped>
.search-form {
  margin-top: 5%;
  display: flex;
  justify-content: center;
}

.search {
  width: 50%;
  display: flex;
  justify-content: center;
  align-items: baseline;
}
</style>