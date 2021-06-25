<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default  {
  name:  'App',
  data() {
    return {
    }
  },
  methods: {
  },
  mounted () {
    let regStr=/^.icon-[a-zA-Z0-9-]+\:\:before/
    let self=this
    // setTimeout(() => {
    //     self.selectInconStyle(regStr)
    //   }, 1000)
  }
}
</script>
