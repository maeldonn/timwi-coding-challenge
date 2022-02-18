module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
  devServer: {
    port: 4200,
    proxy: 'https://localhost:8080/'
  }
}
