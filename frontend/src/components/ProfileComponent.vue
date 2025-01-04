<template>
  <div class="flex w-full h-full">
    <q-card class="w-full q-pa-md" flat>
      <q-form
        class="flex flex-col gap-2"
        v-if="form?.id"
        @submit.prevent="onSubmit"
      >
        <q-input
          class="w-full"
          label="Nome"
          outlined
          :readonly="!isEdit"
          v-model="form.name"
          hint=""
        />
        <q-input
          class="w-full"
          label="Email"
          outlined
          :readonly="!isEdit"
          v-model="form.email"
          hint=""
        />
        <div class="flex flex-col gap-2" v-if="isEdit">
          <PasswordInput v-model="form.password" label="Senha Atual" />
          <PasswordInput v-model="form.newPassword" label="Nova Senha" />
          <PasswordInput
            v-model="form.confirmPassword"
            label="Confirmar Senha"
            :rules="[(val: string) => form?.newPassword === val || 'Senhas não conferem!', (val: string) => !!val || 'Campo obrigatório!']"
          />
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
<script setup lang="ts">
import { useUserStore } from 'src/stores/user';
import { onMounted, ref } from 'vue';
import { triggerSuccess } from 'src/utils/triggers';
import PasswordInput from './PasswordInput.vue';
import * as UserService from 'src/services/UserService';
import { AxiosError } from 'axios';
import { handleAxiosError } from 'src/utils/handleAxiosError';

type Form = {
  id: number;
  name: string;
  email: string;
  password: string;
  newPassword?: string;
  confirmPassword?: string;
};

defineOptions({
  name: 'ProfileComponent',
});
const isEdit = ref(false);
const form = ref<Form>();

const onSubmit = async () => {
  try {
    if (form.value) {
      const { data } = await UserService.UpdateUser(form.value);
      useUserStore().storageUserSave(data);
      triggerSuccess('Perfil atualizado com sucesso');
    }
  } catch (error) {
    if (error instanceof AxiosError) {
      handleAxiosError(error);
    }
  }
};

onMounted(() => {
  try {
    form.value = useUserStore().userData;
  } catch (error) {
    console.log('🚀 ~ onMounted ~ error:', error);
  }
});
</script>
<style lang="scss"></style>
