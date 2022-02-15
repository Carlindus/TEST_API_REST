import Vue from 'vue';
import App from './App.vue';
import vuetify from './plugins/vuetify';
import router from '@/services/router';
import axios from 'axios';
import VueAxios from 'vue-axios';

Vue.use(VueAxios, axios);
Vue.prototype.$axios = axios;

Vue.config.productionTip = false;

new Vue({
  router,
  vuetify,
  render: (h) => h(App),
}).$mount('#app');
