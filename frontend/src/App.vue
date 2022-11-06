<template>
  <v-app>
    <v-app-bar
        app
        color="primary"
        dark
    >
      <div class="d-flex align-center">
        <span>TIMWI Coding Challenge</span>
      </div>

      <v-spacer></v-spacer>

      <v-btn
          text
          to="/"
      >
        <span class="mr-2">Search</span>
      </v-btn>
      <v-btn
          text
          to="/management"
      >
        <span class="mr-2">Management</span>
      </v-btn>
    </v-app-bar>

    <div class="views">
      <keep-alive include="Search">
        <router-view/>
      </keep-alive>
    </div>

  </v-app>
</template>

<script>
export default {
  name: 'App'
};
</script>

<style>
.views {
  width: 100%;
  margin: 56px 0;
}
</style>
