<template>
  <v-container>
    <v-row class="text-center">
      <v-col class="mb-4">
        <h1 class="display-2 font-weight-bold mb-3" v-html="title"></h1>
      </v-col>
    </v-row>

    <v-row class="text-center">
      <v-col class="mb-4">
        <p class="display-1 mb-3" v-html="devExcusesMessage"></p>
      </v-col>
    </v-row>

    <v-row align="center" justify="space-around">
      <ButtonComponent @generateMessage="displayMessage" :excuseId="id" />
    </v-row>
  </v-container>
</template>

<script>
import ButtonComponent from '@/components/ButtonComponent';

export default {
  name: 'MainComponent',
  components: {
    ButtonComponent,
  },

  props: {
    id: {
      type: Number,
      default: () => {
        return this.id instanceof Number ? this.id : -1;
      },
    },
    title: {
      type: String,
      default: () => {
        return '';
      },
    },
  },

  data() {
    return {
      devExcusesMessage: '',
    };
  },

  methods: {
    displayMessage(message) {
      this.devExcusesMessage = message;
    },
  },
};
</script>
