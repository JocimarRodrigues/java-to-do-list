<template>
  <header class="row no-wrap shadow-1 w-full">
    <q-toolbar class="bg-[#000000] glossy text-white max-w-[20vw] rounded-tl">
      <q-avatar>
        <img src="../assets/images/logo-dark.svg">
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
        v-model="filter"
        @update:model-value="() => $emit('search', filter)"
      >
    <template v-slot:append>
      <q-btn  icon="search" color="blue" flat />
    </template>
    </q-input>
      </q-toolbar>
  </header>
</template>
<script lang="ts" setup>
import { ref } from 'vue';

defineOptions({
  name: 'NavbarComponent',
});

defineEmits(['search']);

const filter = ref('');

</script>
<style lang="">

</style>
