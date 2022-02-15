<template>
  <div class="home">
    <main-component title="Une bien belle et utile application" :id="id" />
  </div>
</template>

<script>
// @ is an alias to /src
import MainComponent from '@/components/MainComponent.vue';

export default {
  name: 'home',
  components: {
    MainComponent,
  },

  computed: {
    id() {
      console.log(parseInt(this.$route.params.id));
      return parseInt(this.$route.params.id);
    },
  },
};
</script>
