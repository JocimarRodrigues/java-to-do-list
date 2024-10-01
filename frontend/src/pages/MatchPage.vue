<template>
  <div class="flex w-full h-[95dvh] justify-center items-center">
    <div class="gradiant w-[50%] rounded">
      <header class="w-full px-4 pt-4">
        <q-card class="gradiantHeader w-full my-card rounded-xl my-4 shadow-lg">
          <q-card-section class="flex justify-center items-center w-full p-0">
            <q-card-section
              class="flex w-[40%] h-[100px] justify-center items-center"
            >
              <q-avatar size="70px">
                <q-img src="../assets/images/logoGremio.png" />
              </q-avatar>
            </q-card-section>
            <q-card-section
              class="gradiantScore text-white text-2xl flex w-[20%] h-[100px] border items-center justify-center"
            >
              <div class="flex flex-col">
                <span> {{ websocketStore.minuteDisplay }} </span>
                <span>
                  {{ websocketStore.GoalsA }} x {{ websocketStore.GoalsB }}
                </span>
              </div>
            </q-card-section>
            <q-card-section
              class="flex items-center justify-center w-[40%] h-[100px]"
            >
              <q-avatar size="70px">
                <q-img src="../assets/images/logoCorinthians.png" />
              </q-avatar>
            </q-card-section>
          </q-card-section>
        </q-card>
      </header>
      <div class="flex gap-4 w-full justify-center">
        <div class="flex flex-col text-white bg-blue-900 w-[30%]">
          <span class="p-2 bg-blue-800 text-end text-white font-bold">{{
            teamA.name
          }}</span>
          <div>
            <p
              class="flex w-full justify-between"
              v-for="item in websocketStore.teamA.starting_players"
              :key="item"
            >
              <span>{{ item.name }}</span
              ><span>2.0</span>
            </p>
          </div>
          <div class="pt-2">
            <span>Substituições</span>
            <q-separator w-full color="yellow" />
            <div v-for="item in teamA.substitutions" :key="item">
              <p class="flex w-full bg-blue-950">
                {{ item.substitutedPlayer }} ({{ item.time }})
              </p>
              <span>{{ item.substitutePlayer }}</span>
            </div>
          </div>
        </div>
        <div class="flex flex-col w-[30%] text-white gap-2">
          <q-card>
            <q-card-section class="bg-black rounded-md h-[80px]"
              >Anilado</q-card-section
            >
          </q-card>
          <q-card class="bg-blue-900">
            <!--Posso refatorar-->
            <div class="flex justify-center w-full">
              <span class="w-[80%] bg-blue-950 text-center"> Gols </span>
              <div class="lista flex flex-col w-full">
                <span class="text-end w-full">Maocyr 11 - 1</span>
                <span class="text-end w-full">Maocyr 41 - 1</span>
                <span>Bosco 48 - 1</span>
                <span>Emilio Facher 19 - 2</span>
              </div>
            </div>
            <!--Posso refatorar-->

            <div class="flex justify-center w-full">
              <span class="w-[80%] bg-blue-950 text-center"> Cartões </span>
              <div class="lista flex flex-col w-full">
                <span class="text-end w-full">Osman Silva - 1</span>
                <span class="text-end w-full">Maocyr Silva - 1</span>
                <span>Bosco 48 - 1</span>
                <span>Emilio Facher 19 - 2</span>
              </div>
            </div>
          </q-card>

          <div class="flex flex-col gap-2">
            <p class="flex w-full justify-between px-1 bg-gray-400">
              <span class="font-bold">48</span
              ><span class="text-center bg-black px-6 rounded w-[70%]"
                >posse de bola</span
              ><span>52</span>
            </p>
            <p class="flex w-full justify-between px-1 bg-gray-400">
              <span>15</span
              ><span class="text-center bg-black px-6 rounded w-[70%]"
                >Finalizações</span
              ><span>16</span>
            </p>
          </div>
          <div></div>
        </div>
        <div class="flex flex-col text-white bg-blue-900 w-[30%]">
          <span class="p-2 bg-black text-white font-bold">ASA</span>
          <div>
            <p
              class="flex w-full justify-between"
              v-for="item in websocketStore.teamB.starting_players"
              :key="item"
            >
              <span>{{ item.name }}</span
              ><span>5.8</span>
            </p>
            <!-- <p class="flex w-full justify-between">
              <span>G-Donald</span><span>5.8</span>
            </p>
            <p class="flex w-full justify-between">
              <span>G-Donald</span><span>5.8</span>
            </p>
            <p class="flex w-full justify-between">
              <span>G-Donald</span><span>5.8</span>
            </p>
            <p class="flex w-full justify-between">
              <span>G-Donald</span><span>5.8</span>
            </p>
            <p class="flex w-full justify-between">
              <span>G-Donald</span><span>5.8</span>
            </p>
            <p class="flex w-full justify-between">
              <span>G-Donald</span><span>5.8</span>
            </p>
            <p class="flex w-full justify-between">
              <span>G-Donald</span><span>5.8</span>
            </p>
            <p class="flex w-full justify-between">
              <span>G-Donald</span><span>5.8</span>
            </p> -->
          </div>
          <div class="pt-2">
            <span>Substituições</span>
            <q-separator w-full color="yellow" />
            <div>
              <p class="flex w-full bg-blue-950">
                M - Sylvio Marotta (interv.)
              </p>
              <span>M - Bosco</span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- <div v-for="(item, index) in websocketStore.matchEvents" :key="index">
      <p>{{ item.MinuteDisplay }} | {{ item.Description }}</p>
    </div> -->
  </div>
</template>
<script setup>
defineOptions({
  name: 'MatchPage',
});

import { useWebSocketStore } from 'src/stores/webSocket';

const websocketStore = useWebSocketStore();

const teamA = {
  name: 'União',
  players: [
    {
      name: 'G-Donald',
      power: '5.8',
    },
    {
      name: 'G-Donald',
      power: '5.8',
    },
    {
      name: 'G-Donald',
      power: '5.8',
    },
    {
      name: 'G-Donald',
      power: '5.8',
    },
    {
      name: 'G-Donald',
      power: '5.8',
    },
    {
      name: 'G-Donald',
      power: '5.8',
    },
    {
      name: 'G-Donald',
      power: '5.8',
    },
    {
      name: 'G-Donald',
      power: '5.8',
    },
  ],
  substitutions: [
    {
      substitutedPlayer: 'M - Sylvio Marotta',
      time: 'interv',
      substitutePlayer: 'M Bloco',
    },
  ],
};

const teamB = {
  name: 'Asa',
  players: [
    {
      name: 'G-Donald',
      power: '5.8',
    },
    {
      name: 'G-Donald',
      power: '5.8',
    },
    {
      name: 'G-Donald',
      power: '5.8',
    },
    {
      name: 'G-Donald',
      power: '5.8',
    },
    {
      name: 'G-Donald',
      power: '5.8',
    },
    {
      name: 'G-Donald',
      power: '5.8',
    },
    {
      name: 'G-Donald',
      power: '5.8',
    },
    {
      name: 'G-Donald',
      power: '5.8',
    },
  ],
  substitutions: [
    {
      substitutedPlayer: 'M - Sylvio Marotta',
      time: 'interv',
      substitutePlayer: 'M Bloco',
    },
  ],
};
// socket.onmessage = (event) => {
//   const data = JSON.parse(event.data);
//   matchEvents.value = data.Events;
//   MinuteDisplay.value = data.Events[data.Events.length - 1].MinuteDisplay;
//   TeamA.value = data.TeamA;
//   TeamB.value = data.TeamB;
//   console.log('🤬 ~ matchEvents.value:', matchEvents.value);

//   textEventos.value +=
//     '\n' +
//     data.Events[data.Events.length - 1].MinuteDisplay +
//     ' | ' +
//     data.Events[data.Events.length - 1].Description;
// };
</script>
<style lang="scss">
body {
  background-image: url('../assets/images/background.jpg');
  background-repeat: no-repeat;
  background-size: cover;
}

.gradiant {
  background-color: #e8e8e8;
  background-image: linear-gradient(135deg, #3d779b 40%, #00024b 100%);
}
.gradiantHeader {
  background-image: linear-gradient(to top, #09203f 0%, #537895 100%);
}

.gradiantScore {
  background: rgb(4, 0, 46);
  background: linear-gradient(
    0deg,
    rgba(4, 0, 46, 1) 0%,
    rgba(50, 41, 173, 1) 53%,
    rgba(4, 4, 87, 1) 100%
  );
}
</style>
