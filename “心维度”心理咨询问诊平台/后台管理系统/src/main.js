// import Vue from 'vue'
// import VueRouter from 'vue-router'
// import router from './router'
// import App from './App.vue'
// import ElementUI from 'element-ui'
// import 'element-ui/lib/theme-chalk/index.css'
// import axios from 'axios'
// import VueAxios from 'vue-axios'



// Vue.use(VueAxios, axios)
// Vue.use(ElementUI).use(VueRouter)

// Vue.config.productionTip = false



// new Vue({
//   router,
//   render: h => h(App),
// }).$mount('#app')

import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import VueAxios from 'vue-axios'
import router from '@/router/index.js'


Vue.use(VueAxios, axios)
Vue.use(ElementUI)

Vue.config.productionTip = false



new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
