<template>
  <div
    class="flex flex-col items-center justify-center w-full h-[100vh] rounded"
  >
    <div class="w-full lg:w-[90%]">
      <NavbarComponent />
      <q-card>
        <div class="flex w-full flex-nowrap h-[70vh] max-w-[90vw] text-white font-light">
          <q-card class="bg-gradient-to-b from-gray-800 to-black h-full  min-w-[20vw] q-pa-md" square>
            <ul
              class="flex flex-col w-full text-center justify-center gap-4 text-lg font-serif"
            >
            <q-btn name="perfil" label="Perfil" @click="() => (profilePage = !profilePage)" flat/>
            <q-btn label="Sair" @click="logout" flat/>
            </ul>
          </q-card>
          <div class="flex w-full" v-if="!profilePage">
            <div class="flex w-full justify-between q-pa-sm">
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
                <q-btn color="purple" label="Adicionar nova tarefa" @click="openNewTaskDialog" />
              </div>
            </div>

            <q-separator />
            <q-tab-panels v-model="tab" animated class="flex w-full">
              <q-tab-panel v-for="tab in tabs" :key="tab.name" :name="tab.name" class="h-full">
                <q-scroll-area style="height: 400px; max-width: 100%"   :thumbStyle="thumbStyle">
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
                              {{ formatStatus(props.row.status) }}
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
                            {{ props.row.createdAt }}
                          </span>
                          <q-tooltip>
                            {{ props.row.createdAt }}
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
                              @click="changeStatusTask(props.row.id, 'FINISH')"
                            >
                              <q-tooltip> Concluir </q-tooltip>
                            </q-btn>
                            <q-btn
                              flat
                              dense
                              color="negative"
                              icon="cancel"
                              @click="changeStatusTask(props.row.id, 'CANCELED')"
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
import { computed, onMounted, ref, watch } from 'vue';
import * as UserService from 'src/services/UserService';
import * as TaskService from 'src/services/TaskService';
import { useUserStore } from 'src/stores/user';
import NavbarComponent from 'src/components/NavbarComponent.vue';
import ProfileComponent from 'src/components/ProfileComponent.vue';
import { useQuasar } from 'quasar';
import NewTaskDialogComponent from 'src/components/NewTaskDialogComponent.vue';

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
  createdAt: string;
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
const tab = ref('PENDING');
const tabs = ref<Tab[]>([
  {
    name: 'PENDING',
    label: 'Pendentes',
    tableTitle: 'Pendentes',
  },
  {
    name: 'FINISH',
    label: 'Concluídas',
    tableTitle: 'Concluídas',
  },
  {
    name: 'CANCELED',
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
    field: (row: Row) => row.createdAt,
  },
  {
    name: 'actions',
    label: 'Ações',
    align: 'center',
    field: 'sodium',
  },
];

const $q = useQuasar();

const   thumbStyle = {
        borderRadius: '5px',
        backgroundColor: '#9D4EDD',
        width: '5px',
        opacity: '0.75'
      }


const formatStatus = (status: string) => {
  switch (status) {
    case 'PENDING':
      return 'PENDENTE';
    case 'FINISH':
      return 'CONCLUIDA';
    case 'CANCELED':
      return 'CANCELADA';
  }
}

const updateTasks = async () => {
  try {
    const {data} = await TaskService.GetUserTasks(useUserStore().userData.id, tab.value);
    rows.value = data;

    console.log('🚀 ~ updateTasks ~ tasks.value:', rows.value);
  } catch (error) {
    console.log('🚀 ~ updateTasks ~ error:', error);
  }
};

watch(() => tab.value, async () => {
  await updateTasks();
})

const getColor = computed(() => {
  return (status: string) => {
    if (status == 'FINISH') return 'positive';
    else return 'negative';
  };
});

const openNewTaskDialog = () => {
  $q.dialog({
    component: NewTaskDialogComponent,
  }).onOk(async ({task}) => {
    try {
      const {data} = await TaskService.CreateTask({
        userId: useUserStore().userData.id,
        name: task.name,
        description: task.description,
        status: 'PENDING',
        created_at: new Date().toISOString()
      });
      console.log('🚀 ~ openNewTaskDialog ~ data:', data)
    } catch (error) {
      console.log('🚀 ~ openNewTaskDialog ~ error:', error)

    }

  })
}

const changeStatusTask = (taskId : number, status: string) => {
  $q.dialog({
        title: `${status  == 'FINISH' ? 'Concluir' : 'Cancelar'} tarefa`,
        message: `Você tem certeza que deseja ${status  == 'FINISH' ? 'concluir' : 'cancelar'} essa tarefa?`,
        cancel: true,
        persistent: true
      }).onOk(async () => {
        try {
          const {data} = await TaskService.changeStatusTask(taskId, status)
          console.log('🚀 ~ finishTask ~ data:', data)
        } catch (error) {
          console.log('🚀 ~ finishTask ~ error:', error)
        }
        // console.log('>>>> OK')
      }).onCancel(() => {
        // console.log('>>>> Cancel')
      }).onDismiss(() => {
        // console.log('I am triggered on both OK and Cancel')
      })
}

const logout = () => {
  console.log('sair')
}

onMounted(async () => {
  await updateTasks();
});
</script>
<style lang="scss">
 body {
  background-image: url('../assets/images/rm222batch2-mind-03.jpg');
  background-size: cover;
}

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
