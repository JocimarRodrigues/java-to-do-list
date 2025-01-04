<template>
  <q-input
    class="w-full"
    v-model="inputValue"
    :label="label"
    outlined
    :type="isPwd ? 'password' : 'text'"
    :rules="[(val) => !!val || 'Campo obrigatório!']"
  >
    <template v-slot:append>
      <q-icon
        :name="isPwd ? 'visibility_off' : 'visibility'"
        class="cursor-pointer"
        @click="isPwd = !isPwd"
      />
    </template>
  </q-input>
</template>
<script setup lang="ts">
import { ref, watch } from 'vue';

defineOptions({
  name: 'PasswordInput',
});

const props = defineProps({
  modelValue: {
    type: String,
  },
  label: {
    type: String,
    required: true,
  },
});

const emit = defineEmits(['update:modelValue']);

const inputValue = ref<string>(props.modelValue ?? '');

watch(inputValue, (newValue) => {
  emit('update:modelValue', newValue);
});

const isPwd = ref<boolean>(true);
</script>
<style lang=""></style>
