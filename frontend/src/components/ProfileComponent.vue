<template>
  <div class="flex w-full text-black h-[50vh]">
    <q-card class="w-full q-pa-md">
      <q-form class="flex flex-col gap-2">
        <q-input class="w-full" label="Nome" outlined readonly />
        <q-input class="w-full" label="Email" outlined readonly />
        <q-input class="w-full" label="Senha" outlined readonly />
        <q-card-actions align="right">
          <div v-if="!isEdit">
            <q-btn
            @click="() => (isEdit = !isEdit)"
              class="rounded-lg"
              label="Editar"
              color="accent"
              push
              icon-right="edit"
            />
          </div>
          <div class="flex gap-2" v-else>
            <q-btn
              class="rounded-lg"
              label="Voltar"
              color="accent"
              outline
            />
            <q-btn
              label="Salvar"
              type="submit"
              color="accent"
              push
            />

          </div>
        </q-card-actions>
      </q-form>
    </q-card>
  </div>
</template>
<script setup>
import { ref } from 'vue';

defineOptions({
  name: 'ProfileComponent',
})
const isEdit = ref(false)

</script>
<style lang="scss">

</style>
