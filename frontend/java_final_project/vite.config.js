import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    proxy: {
      'myapi': {
        target: 'http://localhost:8080/', //接口域名
        changeOrigin: true,             //是否跨域
        ws: true,                       //是否代理 websockets
        secure: false,                   //是否https接口
        pathRewrite: {                  //路径重置
          '^/myapi': ''
        }
      }
    }
  }
})


// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true,
//   devServer: {
//     host: '0.0.0.0',
//     port: '6000',

//     proxy: {
//       'api1': {
//         target: 'http://101.43.181.83:5000/', //接口域名
//         changeOrigin: true,             //是否跨域
//         ws: true,                       //是否代理 websockets
//         secure: false,                   //是否https接口
//         pathRewrite: {                  //路径重置
//           '^/api1': ''
//         }
//       },
//       'api2': {
//         target: 'http://101.43.181.83/', //接口域名
//         changeOrigin: true,             //是否跨域
//         ws: true,                       //是否代理 websockets
//         secure: false,                   //是否https接口
//         pathRewrite: {                  //路径重置
//           '^/api2': ''
//         }
//       }
//     }
//   }
// })