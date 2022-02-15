<template>
  <div class="home">
    <h1>Page d'erreur 404</h1>
  </div>
</template>

<script>
import Router from '@/services/router';

export default {
  name: 'pageNotFound',

  mounted() {
    this.redirectToHomePage();
  },
  methods: {
    redirectToHomePage() {
      setTimeout(function () {
        Router.push({ name: 'home' });
      }, 5000);
    },
  },
};
</script>
