module.exports = {
    transpileDependencies: [
        'vuetify'
    ],
    devServer: {
        port: 4200,
        proxy: {
            '^/api': {
                ws: true,
                target: 'http://localhost:8080',
                changeOrigin: true,
                secure: false,
                pathRewrite: {'^/api': '/api'},
                logLevel: 'debug'
            }
        }
    }
}
