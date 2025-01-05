<template>
  <q-page>
    <div
      class="row q-pa-md q-gutter-md justify-center items-center w-full h-[95dvh]"
    >
      <q-card
        class="bg-white w-[90vw] md:w-[60vw] h-[90vh] md:h-[70vh] rounded-lg flex flex-col md:flex-row flex-nowrap q-pa-sm"
      >
        <div class="h-[40%] md:w-[50%] md:h-[98%] m-2">
          <q-img :src="`${bgImage}`" class="q-pa-md h-full rounded" />
        </div>
        <div class="flex flex-col w-full md:w-[50%] flex-nowrap">
          <q-form
            class="flex flex-col items-center justify-center h-full flex-nowrap q-pa-md"
            @submit.prevent="onSubmit"
          >
            <h1 class="text-3xl mt-4">
              {{ isCreate ? 'Criar Conta' : 'Login' }}
            </h1>
            <div class="q-pa-md w-full">
              <q-input
                class="my-4"
                label="Nome"
                placeholder="Digite seu nome..."
                outlined
                dense
                v-model="user.name"
              />
              <q-input
                class="my-4"
                label="Email"
                type="email"
                placeholder="Digite seu email..."
                outlined
                dense
                v-model="user.email"
                :rules="[(val: string) => !!val || 'Campo obrigatório!']"
              />
              <q-input
                label="Senha"
                placeholder="Digite sua senha..."
                outlined
                dense
                v-model="user.password"
              />
            </div>
            <template v-if="errors">
              <span
                v-for="item in errors"
                :key="item"
                class="text-red-600 font-bold"
              >
                {{ item }}
              </span>
            </template>
            <div class="flex flex-col q-pa-md w-full gap-4">
              <q-btn
                v-if="!isCreate"
                label="Entrar"
                color="black"
                no-caps
                class="font-medium"
                type="submit"
              />
              <q-btn
                v-else
                label="Enviar"
                color="black"
                no-caps
                class="font-medium"
                type="submit"
              />
              <p
                class="text-center w-full text-blue-400 cursor-pointer"
                v-if="!isCreate"
                @click="() => (isCreate = !isCreate)"
              >
                Não tem uma conta? Clique aqui para se cadastar.
              </p>
            </div>
          </q-form>
        </div>
      </q-card>
    </div>
  </q-page>
</template>
<script setup lang="ts">
import bgImage from 'src/assets/images/bg2.jpg';
import * as UserService from 'src/services/UserService';
import { User } from 'src/interfaces/IUser';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { AxiosError } from 'axios';
import { useUserStore } from 'src/stores/user';
import { triggerNegative, triggerSuccess } from 'src/utils/triggers';
import { handleAxiosError } from 'src/utils/handleAxiosError';

defineOptions({
  name: 'HomePage',
});

const user = ref<User>({
  name: '',
  email: '',
  password: '',
});

const isCreate = ref<boolean>(false);
const errors = ref<[]>([]);
const userStore = useUserStore();
const router = useRouter();

const onSubmit = async () => {
  isCreate.value ? createUser() : login();
};

const login = async () => {
  try {
    const { data } = await UserService.Login(user.value);
    userStore.storageUserSave(data);
    if (data) router.push('/tasks');
  } catch (error) {
    error instanceof AxiosError
      ? handleAxiosError(error)
      : triggerNegative('Erro');
  }
};

const createUser = async () => {
  try {
    const { data } = await UserService.CreateUser(user.value);
    triggerSuccess(`Usuário ${data.name} criado com sucesso.`);
  } catch (error) {
    error instanceof AxiosError
      ? handleAxiosError(error)
      : triggerNegative('Erro');
  }
};
</script>
<style lang="scss"></style>
