<template>
  <div class="Main"
       ref="MainView">
    <panel-group @handleSetLineChartData="handleSetLineChartData" />
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <line-chart :chart-data="lineChartData" />
    </el-row>
  </div>
</template>
<script>
import PanelGroup from '@/components/dashboard/PanelGroup'
import LineChart from '@/components/dashboard/LineChart'
const lineChartData = {
  newVisitis: {
    expectedData: [100, 120, 161, 134, 105, 160, 165],
    actualData: [120, 82, 91, 154, 162, 140, 145]
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130]
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130]
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130]
  }
}
export default {
  components: { PanelGroup, LineChart },
  data () {
    return {
      that: this,
      centContainerHeight: '100px',
      lineChartData: lineChartData.newVisitis
    }
  },
  created () { },
  filters: {},
  methods: {
    refreshCenterContainerHeight () {
      this.centContainerHeight = (this.$refs.MainView.offsetHeight - 72 - 331 - 4) + 'px'
    },
    handleSetLineChartData (type) {
      this.lineChartData = lineChartData[type]
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
  background: gray;
}
</style>