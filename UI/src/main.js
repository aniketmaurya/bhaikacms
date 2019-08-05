import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/index'
import VueSession from 'vue-session'
import VueSwal from 'vue-swal'

Vue.use(VueSession)
Vue.use(VueSwal)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
