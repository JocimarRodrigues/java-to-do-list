<template>
  <header class="row no-wrap shadow-1">
    <q-toolbar class="bg-purple-800 glossy text-white max-w-[20vw]">
      <q-avatar>
        <img src="https://cdn.quasar.dev/logo-v2/svg/logo-mono-white.svg">
      </q-avatar>

      <q-toolbar-title>To Do List Java/Vue</q-toolbar-title>
      </q-toolbar>
      <q-toolbar class="bg-purple-800 glossy p-0">
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
