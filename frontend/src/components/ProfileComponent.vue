<template>
  <div class="flex w-full text-black h-[60vh]">
    <q-card class="w-full q-pa-md">
      <q-form class="flex flex-col gap-2">
        <q-input class="w-full" label="Nome" outlined readonly v-model="form.name" />
        <q-input class="w-full" label="Email" outlined readonly v-model="form.email" />
        <div class="flex flex-col gap-2" v-if="isEdit">
          <q-input class="w-full" label="Senha Atual" outlined readonly v-model="form.password"/>
          <q-input class="w-full" label="Nova Senha" outlined readonly v-model="form.newPassword"/>
          <q-input class="w-full" label="Confirmar Senha" outlined readonly v-model="form.confirmPassword"/>
        </div>
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
            <q-btn class="rounded-lg" label="Voltar" color="accent" outline />
            <q-btn label="Salvar" type="submit" color="accent" push />
          </div>
        </q-card-actions>
      </q-form>
    </q-card>
  </div>
</template>
<script setup>
import { useUserStore } from 'src/stores/user';
import { onMounted, ref } from 'vue';

defineOptions({
  name: 'ProfileComponent',
});
const isEdit = ref(false);
const form = ref({});

onMounted(() => {
  try {
    form.value = useUserStore().userData;
  } catch (error) {
    console.log('🚀 ~ onMounted ~ error:', error);
  }
});
</script>
<style lang="scss"></style>
