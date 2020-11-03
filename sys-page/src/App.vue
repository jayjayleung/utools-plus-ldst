<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<style lang="scss">
// 高度控制，不然其他地方设置100%不生效,padding、margin设置为0
html,body,#app{
  height: 100%;
  padding: 0;
  margin: 0;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  // text-align: center;
  color: #2c3e50;
}

</style>
