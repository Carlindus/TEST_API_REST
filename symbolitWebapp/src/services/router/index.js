import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '@/views/HomeView.vue';
import Lost from '@/views/LostView.vue';
import PageNotFound from '@/views/PageNotFoundView.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/lost',
    name: 'lost',
    component: Lost,
  },
  {
    path: '/:id',
    name: 'home',
    component: Home,
  },
  {
    path: '/',
    name: 'home',
    component: Home,
  },
  {
    path: '*',
    name: 'pageNotFound',
    component: PageNotFound,
  },
];

const vueRouter = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default vueRouter;
