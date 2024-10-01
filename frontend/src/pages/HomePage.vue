<template>
  <div>
    <q-page class="">
      <div
        class="row q-pa-md q-gutter-md justify-center items-center w-full h-[95dvh]"
      >
        <div class="bg-white w-[60vw] h-[70vh] rounded-lg flex flex-nowrap">
          <div class="w-[50%] h-[98%] m-2">
            <q-img :src="`${bgImage}`" class="h-full rounded" />
          </div>
          <div class="flex flex-col w-[50%] flex-nowrap">
            <div
              class="flex flex-col items-center justify-center h-full flex-nowrap q-pa-md"
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
                  placeholder="Digite seu email..."
                  outlined
                  dense
                  v-model="user.email"
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
                  @click="login"
                />
                <q-btn
                  v-else
                  label="Enviar"
                  color="black"
                  no-caps
                  class="font-medium"
                  @click="createUser"
                />
                <p
                  class="text-center w-full text-blue-400 cursor-pointer"
                  v-if="!isCreate"
                  @click="() => (isCreate = !isCreate)"
                >
                  Não tem uma conta? Clique aqui para se cadastar.
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </q-page>
  </div>
</template>
<script setup lang="ts">
import bgImage from 'src/assets/images/bg2.jpg';
import * as UserService from 'src/services/UserService';
import { User } from 'src/interfaces/IUser';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { AxiosError } from 'axios';
import { useUserStore } from 'src/stores/user';

// interface User {
//   name: string;
//   email: string;
//   password: string;
// }

defineOptions({
  name: 'HomePage',
});

const user = ref<User>({
  name: '',
  email: '',
  password: '',
});
const userFound = ref<User>({
  name: '',
  email: '',
  password: '',
});
const isCreate = ref<boolean>(false);
const errors = ref<[]>([]);
const userStore = useUserStore();
const router = useRouter();

const login = async () => {
  try {
    const { data } = await UserService.Login(user.value);
    // user.value = data;
    userStore.storageUserSave({
      id: data.id,
      name: data.name,
      email: data.email,
      password: data.password,
    });
    if (data) router.push('/tasks');
    console.log('🚀 ~ login ~ userFound:', data);
  } catch (error: AxiosError | unknown) {
    if (error instanceof AxiosError && error.response) {
      errors.value = error.response.data.errors;
      console.log('🚀 ~ login ~ error:', error.response.data.errors);
    } else {
      console.error('Ocorreu um erro inesperado', error);
    }
  }
};

const createUser = async () => {
  try {
    const { data } = await UserService.CreateUser(user.value);
    console.log('🚀 ~ createUser ~ data:', data);
  } catch (error) {
    console.log('🚀 ~ createUser ~ error:', error);
  }
};
</script>
<style lang="scss">
body {
  background: black;
}
</style>
