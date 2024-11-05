<template>
  <header class="row no-wrap shadow-1 w-full">
    <q-toolbar class="bg-[#000000] glossy text-white max-w-[20vw] rounded-tl">
      <q-avatar>
        <img src="https://cdn.quasar.dev/logo-v2/svg/logo-mono-white.svg">
      </q-avatar>

      <q-toolbar-title class="text-lg">To Do List Java/Vue</q-toolbar-title>
      </q-toolbar>
      <q-toolbar class="bg-[#000000] glossy p-0 rounded-tr">
        <q-input
        class="bg-white pl-1 rounded w-[50%]"
        label="Digite um parâmetro para a busca..."
        dense
        borderless
        color="black"
      />
      </q-toolbar>
  </header>
</template>
<script lang="ts" setup>
import { ref } from 'vue';

defineOptions({
  name: 'NavbarComponent',
});

const search = ref('');

</script>
<style lang="">

</style>
