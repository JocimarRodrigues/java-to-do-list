<template>
  <div class="flex items-center justify-center w-full h-[100vh]">
    <div class="bg-white w-[50%]">
      <div class="flex p-2 w-full flex-nowrap items-center bg-purple-800">
        <span class="text-white mx-9">To Do List</span>
        <q-input
          class="w-[50%] bg-white pl-1 rounded"
          label="Search..."
          dense
          borderless
          color="black"
        />
      </div>
      <div class="flex w-full">
        <div class="bg-purple-400 w-[20%]">
          <ul class="flex flex-col w-full text-center justify-center gap-4">
            <li>teste</li>
            <li>teste</li>
            <li>teste</li>
            <li>teste</li>
          </ul>
        </div>
        <div class="q-pa-md">teste</div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
defineOptions({
  name: 'TasksPage',
});
</script>
<style lang="scss">
body {
  background-color: black;
}
</style>
