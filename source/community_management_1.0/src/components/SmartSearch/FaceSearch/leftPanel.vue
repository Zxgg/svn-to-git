<template>
  <div class="Main"
       ref="MainView">
  </div>
</template>
<script>
// import cameraTreeLazy from '@/components/cameraTree/cameraTree_lazy'
export default {
  // components: { ckvideo },
  data () {
    return {
      that: this,
      centContainerHeight: '100px',
    }
  },
  created () { },
  filters: {},
  methods: {
    refreshCenterContainerHeight () {
      this.centContainerHeight = (this.$refs.MainView.offsetHeight - 72 - 331 - 4) + 'px'
    }
  },
  mounted () {
    const the_page = this;
    // window.onresize = () => { return (() => { the_page.refreshCenterContainerHeight() })() }; 
    this.refreshCenterContainerHeight()
  }
}
</script>
<style lang="scss" scoped>
.Main {
  width: 100%;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
  background: darkseagreen;
}
</style>