<template>
  <div class="Main"
       ref="MainView">
    <div class="TopContainer"></div>
    <div class="CenterContainer"
         :style="{height:centContainerHeight}"> </div>
    <div class="BottomContainer"> </div>
  </div>
</template>
<script>
// import cameraTreeLazy from '@/components/cameraTree/cameraTree_lazy'
export default {
  // components: { cameraTreeLazy },
  data () { return { that: this, centContainerHeight: '100px' } },
  created () { },
  filters: {},
  methods: { refreshCenterContainerHeight () { this.centContainerHeight = (this.$refs.MainView.offsetHeight - 72 - 331 - 4) + 'px' } },
  mounted () {
    const the_page = this;
    // window.onresize = () => {return (() => {the_page.refreshCenterContainerHeight()})()};
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
}
.TopContainer {
  width: 100%;
  height: 72px;
  border: 1px solid #005982;
  margin: 0 auto;
  background-repeat: repeat;
  background-size: cover;
  overflow: auto;
  background: red;
}
.CenterContainer {
  width: 100%;
  position: inherit;
  height: 100%;
  background: green;
}
.BottomContainer {
  width: 100%;
  height: 331px;
  border: 1px solid #005982;
  margin: 0 auto;
  background-repeat: repeat;
  background-size: cover;
  overflow: auto;
  background: blue;
}
</style>