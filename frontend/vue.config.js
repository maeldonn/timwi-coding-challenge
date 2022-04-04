module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
  devServer: {
    port: 3000,
    // proxy: 'http://localhost:8080/api/v1/',
    // proxy: {
    //   '/api': {
    //     target: 'http://localhost:8080/',
    //     ws: true,
    //     changeOrigin: true,
    //     pathRewrite: {
    //       '^/api': ''
    //     }
    //   }
    // }
  }
}
