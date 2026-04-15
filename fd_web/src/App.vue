<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
/* 保持最基础的全屏样式，确保里面的页面能铺满屏幕 */
html, body {
  margin: 0;
  padding: 0;
  height: 100%;
}
#app {
  height: 100%;
}
</style>