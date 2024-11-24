<template>
  <div>
    <q-dialog ref="dialogRef" @hide="onDialogHide" persistent>

      <q-card class="q-dialog-plugin" >
        <div class="flex w-full justify-end">

          <q-btn icon="close" color="red" flat rounded dense v-close-popup />
        </div>
        <div class="q-pa-sm">
          <h1 class="text-2xl font-bold my-2 text-center">Nova Tarefa</h1>
          <div class="flex flex-col gap-2">
            <q-input label="Nome" v-model="form.name" outlined dense/>
            <q-input label="Descrição" v-model="form.description" outlined dense/>
            <!-- <q-input label="Status" outlined dense/> -->
          </div>

        </div>
        <q-card-actions align="right">
          <q-btn color="primary" label="Salvar" @click="onSubmit" />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </div>
</template>
<script lang="ts" setup>
import { useDialogPluginComponent } from 'quasar'
import { ref } from 'vue';

type ITask = {
  name: string,
  description: string
}

defineEmits([...useDialogPluginComponent.emits])
defineOptions({
  name: 'NewTaskDialogComponent',
});

const { dialogRef, onDialogHide, onDialogOK, onDialogCancel } = useDialogPluginComponent()
const form = ref<ITask>({
  name: '',
  description: '',
})

const onSubmit = () => {
  onDialogOK({
    task: form.value
  });
};

</script>
<style lang="">

</style>
