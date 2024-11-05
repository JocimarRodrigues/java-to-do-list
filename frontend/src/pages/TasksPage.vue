<template>
  <div
    class="flex flex-col items-center justify-center w-full h-[100vh] rounded"
  >
    <div class="w-full lg:w-[90%]">
      <NavbarComponent />
      <q-card class="min-w-full rounded">
        <div class="flex w-full flex-nowrap max-w-[90vw] text-white font-light">
          <div class="bg-[#9D4EDD] min-w-[20vw] q-pa-md">
            <ul
              class="flex flex-col w-full text-center justify-center gap-4 text-lg font-serif"
            >
              <li
                class="cursor-pointer"
                @click="() => (profilePage = !profilePage)"
              >
                Perfil
              </li>
              <li>
                <span> Sair </span>
              </li>
              <li></li>
              <li></li>
            </ul>
          </div>
          <div class="flex w-full" v-if="!profilePage">
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
                mobile-arrows
              >
                <q-tab
                  v-for="tab in tabs"
                  :key="tab.name"
                  :name="tab.name"
                  :label="tab.label"
                />
              </q-tabs>
              <div>
                <q-btn color="purple" label="Adicionar nova tarefa" />
              </div>
            </div>

            <q-separator />
            <q-tab-panels v-model="tab" animated class="flex w-full h-[50vh]">
              <q-tab-panel v-for="tab in tabs" :key="tab.name" :name="tab.name">
                <q-scroll-area style="height: 100%; max-width: 100%">
                  <q-table
                    flat
                    bordered
                    :title="`Tarefas ${tab.tableTitle}`"
                    :rows="rows"
                    :columns="columns"
                    row-key="name"
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
                            <q-chip
                              class="glossy"
                              :color="getColor(props.row.status)"
                              text-color="white"
                              size="sm"
                            >
                              {{ props.row.status }}
                            </q-chip>
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
                          key="actions"
                          class="max-w-[200px] truncate"
                          :props="props"
                        >
                          <div>
                            <q-btn
                              v-if="props.row.status === 'PENDING'"
                              flat
                              dense
                              color="positive"
                              icon="done"
                            >
                              <q-tooltip> Confirmar </q-tooltip>
                            </q-btn>
                            <q-btn
                              v-else
                              flat
                              dense
                              color="negative"
                              icon="cancel"
                            >
                              <q-tooltip> Cancelar </q-tooltip>
                            </q-btn>
                          </div>
                        </q-td>
                      </q-tr>
                    </template>
                  </q-table>
                </q-scroll-area>
              </q-tab-panel>
            </q-tab-panels>
          </div>
          <ProfileComponent v-else/>
        </div>
      </q-card>
    </div>
  </div>
</template>
<script setup lang="ts">
import { computed, onMounted, ref } from 'vue';
import * as UserService from 'src/services/UserService';
import { useUserStore } from 'src/stores/user';
import NavbarComponent from 'src/components/NavbarComponent.vue';
import ProfileComponent from 'src/components/ProfileComponent.vue';

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

type OptionBtn = {
  name: string;
  label: string;
  value: string;
};

const optionsBtn = <OptionBtn[]>[
  {
    name: 'confirm',
    label: 'Confirmar',
    value: 'confirm',
  },
  {
    name: 'cancel',
    label: 'Cancelar',
    value: 'cancel',
  },
];

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
const profilePage = ref(false);

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
    align: 'center',
    label: 'Status',
    field: (row: Row) => row.status,
  },
  {
    name: 'created_at',
    label: 'Data de Criação',
    align: 'left',
    field: (row: Row) => row.created_at,
  },
  {
    name: 'actions',
    label: 'Ações',
    align: 'center',
    field: 'sodium',
  },
];

const updateTasks = async () => {
  try {
    const { data } = await UserService.GetUserById(useUserStore().userData.id);
    rows.value = data.tasks;

    console.log('🚀 ~ updateTasks ~ tasks.value:', rows.value);
  } catch (error) {
    console.log('🚀 ~ updateTasks ~ error:', error);
  }
};

const getColor = computed(() => {
  return (status: string) => {
    if (status == 'FINISH') return 'positive';
    else return 'negative';
  };
});

onMounted(async () => {
  await updateTasks();
});
</script>
<style lang="scss">
/* body {
  background-color: black;
} */

.bg-primary {
  color: #bf1162;
}
.bg-secondary {
  color: #f244c4;
}
.bg-secondary {
  color: #0d1026;
}
</style>
