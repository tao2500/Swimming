'use strict'
// Template version: 1.3.1
// see http://vuejs-templates.github.io/webpack for documentation.

const path = require('path')

module.exports = {
  dev: {

    // Paths
    assetsSubDirectory: 'static',
    assetsPublicPath: '/',
    // 用于跨域配置
    proxyTable: {
      '/bigHomeWork':{
        target: 'http://localhost:8080',
        // 是否允许跨域
        changeOrigin: true,
        // 重写请求格式（此处为匹配proxy并替换为空）
        pathRewrite: {
          // 匹配bigHomeWork并不做处理
          '^/bigHomeWork': "/bigHomeWork/"
        }
      }
    },

    // 各种开发服务器设置
    host: 'localhost', // 可以被process.env.HOST覆盖
    port: 8080, // 可以被process.env.PORT覆盖，如果端口正在使用，将确定一个空闲端口
    autoOpenBrowser: false,
    errorOverlay: true,
    notifyOnErrors: true,
    poll: false, // https://webpack.js.org/configuration/dev-server/#devserver-watchoptions-


    /**
     * Source Maps
     */

    // https://webpack.js.org/configuration/devtool/#development
    devtool: 'cheap-module-eval-source-map',

    //如果您在devtools中调试vue文件时遇到问题，
    //将其设置为false-这可能会有所帮助
    // https://vue-loader.vuejs.org/en/options.html#cachebusting
    cacheBusting: true,

    cssSourceMap: true
  },

  build: {
    // Template for index.html
    index: path.resolve(__dirname, '../dist/index.html'),

    // Paths
    assetsRoot: path.resolve(__dirname, '../dist'),
    assetsSubDirectory: 'static',
    assetsPublicPath: '/',

    /**
     * Source Maps
     */

    productionSourceMap: true,
    // https://webpack.js.org/configuration/devtool/#production
    devtool: '#source-map',

    //Gzip默认关闭许多流行的静态主机，例如
    //Surge或Netlify已经为您打包了所有静态资产。
    //在设置为“true”之前，请确保：
    //npm安装—保存dev压缩webpack插件
    productionGzip: false,
    productionGzipExtensions: ['js', 'css'],

    //运行带有额外参数的build命令以
    //生成完成后查看捆绑包分析器报告：
    //`npm运行生成--报告`
    //设置为“true”或“false”以始终打开或关闭它
    bundleAnalyzerReport: process.env.npm_config_report
  }
}
