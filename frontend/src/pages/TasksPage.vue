<template>
  <div class="flex items-center justify-center w-full h-[100vh]">
    <div class="bg-white">
      <!-- <div
        class="grid grid-cols-4 py-2 w-full flex-nowrap items-center bg-purple-800"
      >
        <span class="text-white text-center">To Do List</span>
        <q-input
          class="bg-white pl-1 rounded col-span-2"
          label="Search..."
          dense
          borderless
          color="black"
        />
      </div> -->
      <NavbarComponent />
      <div class="flex w-full flex-nowrap max-w-[90vw]">
        <div class="bg-purple-400 min-w-[20vw] q-pa-md">
          <ul class="flex flex-col w-full text-center justify-center gap-4 text-lg font-serif">
            <li>Perfil</li>
            <li>Sair</li>
            <li></li>
            <li></li>
          </ul>
        </div>
        <div>
          <q-card class="flex w-full">
            <div class="flex w-full justify-between q-pa-md">
              <q-tabs
                v-model="tab"
                flat
                dense
                class="text-grey"
                active-color="primary"
                indicator-color="primary"
                align="justify"
                narrow-indicator
              >
                <q-tab
                  v-for="tab in tabs"
                  :key="tab.name"
                  :name="tab.name"
                  :label="tab.label"
                />
              </q-tabs>
              <div>Adicionar Nova Tarefa</div>
            </div>

            <q-separator />

            <q-tab-panels v-model="tab" animated class="flex w-full">
              <q-tab-panel v-for="tab in tabs" :key="tab.name" :name="tab.name">
                <q-table
                  flat
                  bordered
                  :title="`Tarefas ${tab.tableTitle}`"
                  :rows="rows"
                  :columns="columns"
                  row-key="name"
                  dark
                  color="amber"
                >
                  <template v-slot:body="props">
                    <q-tr :props="props">
                      <q-td key="name" :props="props">
                        <span>
                          {{ props.row.name }}
                        </span>
                        <q-tooltip>
                          {{ props.row.name }}
                        </q-tooltip>
                      </q-td>
                      <q-td
                        key="description"
                        class="max-w-[200px] truncate"
                        :props="props"
                      >
                        <span>
                          {{ props.row.description }}
                        </span>
                        <q-tooltip>
                          {{ props.row.description }}
                        </q-tooltip>
                      </q-td>
                      <q-td
                        key="status"
                        class="max-w-[200px] truncate"
                        :props="props"
                      >
                        <span>
                          {{ props.row.status }}
                        </span>
                        <q-tooltip>
                          {{ props.row.status }}
                        </q-tooltip>
                      </q-td>
                      <q-td
                        key="created_at"
                        class="max-w-[200px] truncate"
                        :props="props"
                      >
                        <span>
                          {{ props.row.created_at }}
                        </span>
                        <q-tooltip>
                          {{ props.row.created_at }}
                        </q-tooltip>
                      </q-td>
                      <q-td
                        key="dt_finalizacao"
                        class="max-w-[200px] truncate"
                        :props="props"
                      >
                        teste
                      </q-td>
                      <q-td
                        key="dt_finalizacao"
                        class="max-w-[200px] truncate"
                        :props="props"
                      >
                        acao
                      </q-td>
                    </q-tr>
                  </template>
                </q-table>
              </q-tab-panel>
            </q-tab-panels>
          </q-card>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { onMounted, ref } from 'vue';
import * as UserService from 'src/services/UserService';
import { useUserStore } from 'src/stores/user';
import NavbarComponent from 'src/components/NavbarComponent.vue';

type Column = {
  name: string;
  label: string;
  field: string | ((row: any) => any);
  required?: boolean;
  align?: 'left' | 'center' | 'right';
  sortable?: boolean;
  sort?: (a: string, b: string, rowA?: string, rowB?: string) => number;
};

type Row = {
  name: string;
  description: string;
  status: number;
  created_at: string;
  updated_at?: string;
};

type Tab = {
  name: string;
  label: string;
  tableTitle: string;
};

type Task = {
  name: string;
  description: string;
  status: string;
  user: string;
};

defineOptions({
  name: 'TasksPage',
});


// const user = ref(useUserStore().userData);
// const tasks = ref<Task[]>([]);
const tab = ref('pendentes');
const tabs = ref<Tab[]>([
  {
    name: 'pendentes',
    label: 'Pendentes',
    tableTitle: 'Pendentes',
  },
  {
    name: 'concluidas',
    label: 'Concluídas',
    tableTitle: 'Concluídas',
  },
  {
    name: 'canceladas',
    label: 'Canceladas',
    tableTitle: 'Canceladas',
  },
]);
const rows = ref<Task[]>([]);

const columns: Column[] = [
  {
    name: 'name',
    required: true,
    label: 'Nome',
    align: 'left',
    field: (row: Row) => row.name,
    sortable: true,
  },
  {
    name: 'description',
    required: true,
    label: 'Descrição',
    align: 'left',
    field: (row: Row) => row.description,
    sortable: true,
  },
  {
    name: 'status',
    align: 'left',
    label: 'Status',
    field: (row: Row) => row.status,

    sortable: true,
  },
  {
    name: 'created_at',
    label: 'Data de Criação',
    align: 'left',
    field: (row: Row) => row.created_at,
  },
  {
    name: 'dt_finalizacao',
    label: 'Data de Finalização',
    align: 'left',
    field: 'protein',
  },
  {
    name: 'actions',
    label: 'Ações',
    align: 'center',
    field: 'sodium',
  },
];

// const rows = [
//   {
//     name: 'Frozen Yogurt',
//     calories: 159,
//     fat: 6.0,
//     carbs: 24,
//     protein: 4.0,
//     sodium: 87,
//     calcium: '14%',
//     iron: '1%',
//   },
//   {
//     name: 'Ice cream sandwich',
//     calories: 237,
//     fat: 9.0,
//     carbs: 37,
//     protein: 4.3,
//     sodium: 129,
//     calcium: '8%',
//     iron: '1%',
//   },
//   {
//     name: 'Eclair',
//     calories: 262,
//     fat: 16.0,
//     carbs: 23,
//     protein: 6.0,
//     sodium: 337,
//     calcium: '6%',
//     iron: '7%',
//   },
//   {
//     name: 'Cupcake',
//     calories: 305,
//     fat: 3.7,
//     carbs: 67,
//     protein: 4.3,
//     sodium: 413,
//     calcium: '3%',
//     iron: '8%',
//   },
//   {
//     name: 'Gingerbread',
//     calories: 356,
//     fat: 16.0,
//     carbs: 49,
//     protein: 3.9,
//     sodium: 327,
//     calcium: '7%',
//     iron: '16%',
//   },
//   {
//     name: 'Jelly bean',
//     calories: 375,
//     fat: 0.0,
//     carbs: 94,
//     protein: 0.0,
//     sodium: 50,
//     calcium: '0%',
//     iron: '0%',
//   },
//   {
//     name: 'Lollipop',
//     calories: 392,
//     fat: 0.2,
//     carbs: 98,
//     protein: 0,
//     sodium: 38,
//     calcium: '0%',
//     iron: '2%',
//   },
//   {
//     name: 'Honeycomb',
//     calories: 408,
//     fat: 3.2,
//     carbs: 87,
//     protein: 6.5,
//     sodium: 562,
//     calcium: '0%',
//     iron: '45%',
//   },
//   {
//     name: 'Donut',
//     calories: 452,
//     fat: 25.0,
//     carbs: 51,
//     protein: 4.9,
//     sodium: 326,
//     calcium: '2%',
//     iron: '22%',
//   },
//   {
//     name: 'KitKat',
//     calories: 518,
//     fat: 26.0,
//     carbs: 65,
//     protein: 7,
//     sodium: 54,
//     calcium: '12%',
//     iron: '6%',
//   },
// ];

const updateTasks = async () => {
  try {
    const { data } = await UserService.GetUserById(useUserStore().userData.id);
    rows.value = data.tasks;

    console.log('🚀 ~ updateTasks ~ tasks.value:', rows.value);
  } catch (error) {
    console.log('🚀 ~ updateTasks ~ error:', error);
  }
};

onMounted(async () => {
  await updateTasks();
});
</script>
<style lang="scss">
body {
  background-color: black;
}
</style>
