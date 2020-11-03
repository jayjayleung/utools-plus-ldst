module.exports = {
    // webpack配置，此处是因为我的项目打包时报错（WARNING in asset size limit: The following asset(s) exceed the recommended size limit ，因此有了这个webpack配置，若不存在静态资源过大，可不要这部分代码）
    configureWebpack: {
      // 警告 webpack 的性能提示
      performance: {
        hints: 'warning',
        // 入口起点的最大体积
        maxEntrypointSize: 50000000,
        // 生成文件的最大体积
        maxAssetSize: 30000000,
        // 只给出 js 文件的性能提示
        assetFilter: function (assetFilename) {
          return assetFilename.endsWith('.js');
        }
      }
    },
    publicPath: './',
    // 1.默认为 "/":部署在一个域名的根路径上 ; 2. "./":所有的资源都会被链接为相对路径，这样打出来的包可以被部署在任意路径
    // outputDir: 'dist',
    // // 默认为 "dist",指打包后的资源放置的路径，放在dist文件夹下
    // assetsDir: 'static',
    // // 默认为:'' ,放置打包后生成的静态资源 (js、css、img、fonts) 的 (相对于 outputDir 的) 目录。
    // indexPath: 'index.html',
    // // Default: 'index.html' ,指定生成的 index.html 的输出路径 (相对于 outputDir)
    // filenameHashing: true,
    // // Default: true ,生成的静态资源在它们的文件名中包含了 hash 以便更好的控制缓存
    // // pages:undefined,//在 multi-page 模式下构建应用
    // lintOnSave: true,
    // // Type: boolean|'error';Default: true; true:将 lint 错误输出为编译警告;'error':错误输出会导致编译失败
    // runtimeCompiler: false,
    // // Default: false, 设置为 true 后你就可以在 Vue 组件中使用 template 选项了，但是这会让你的应用额外增加 10kb 左右
    // // transpileDependencies:[],//Default: [], 默认情况下 babel-loader 会忽略所有 node_modules 中的文件
    // css: {
    //   modules: false,
    //   // Default: false, 设置为 true 后你就可以去掉文件名中的 .module 并将所有的 *.(css|scss|sass|less|styl(us)?) 文件视为 CSS Modules 模块。
    //   sourceMap: false // Default: false, 是否为 CSS 开启 source map。设置为 true 之后可能会影响构建的性能。
    // },
    // // productionSourceMap：{ type:Bollean,default:true } 生产源映射
    // // 如果您不需要生产时的源映射，那么将此设置为false可以加速生产构建
    // productionSourceMap: false,
    // // devServer:{type:Object} 3个属性host,port,https
  
    // // 以下为跨域配置（跨域是在开发时用到的，项目打包后是不需要配置跨域的），简单介绍下这里跨域配置的意思，我在本地开发的时候地址为localhost:8088,但是服务器的端口为localhost:80,要访问服务器的上的数据，就要跨域。将'/MVS’代理为服务器的地址
    // devServer: {
    //   port: 8088, // 端口号
    //   host: 'localhost',
    //   https: false, // https:{type:Boolean}
    //   open: true, // 配置自动启动浏览器
    //    // 配置跨域处理,只有一个代理
    //   proxy: {
    //     '/MVS': {
    //       target: 'http://127.0.0.1:80/',
    //       ws: false, //为true会让websocket默认连接
    //       changeOrigin: true,
    //       pathRewrite: {
    //         '^/MVS': '/MVS' // 重写接口访问
    //       }
    //     },
    //     '/foo': {
    //       target: '<other_url>'
    //     }
    //   } // 配置多个代理
    // }
  };