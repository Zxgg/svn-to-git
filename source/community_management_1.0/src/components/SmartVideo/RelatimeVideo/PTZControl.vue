<template>
  <div class="Main"
       ref="MainView">
    <div class="TopContainer">云台控制</div>
    <div class="CenterContainer">
      <el-tabs v-model="activeName"
               @tab-click="PTZTabHandleClick"
               stretch
               class="PTZTabs">
        <el-tab-pane label="云 台"
                     name="first">
          <PTZ class="PTZ" />
        </el-tab-pane>

        <el-tab-pane label="预置位"
                     name="second">
          <PresetPosition />
        </el-tab-pane>

        <el-tab-pane label="巡 航"
                     name="third">
          <CruisebyCamera />
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>
<script>
import PTZ from '@/components/SmartVideo/RelatimeVideo/PTZ'
import PresetPosition from '@/components/SmartVideo/RelatimeVideo/presetPosition'
import CruisebyCamera from '@/components/SmartVideo/RelatimeVideo/cruisebyCamera'
export default {
  components: { PTZ, PresetPosition, CruisebyCamera },
  data () {
    return {
      activeName: 'first',
      cameraSN: ''
    }
  },
  created () { },
  filters: {},
  methods: {
    PTZTabHandleClick () {
      // 预置位
      if (this.activeName === 'second') {
        this.$Notice.$emit('getPresetPositionList')
      }
      // 巡航
      if (this.activeName === 'third') {
        this.$Notice.$emit('getCruiseList')
      }
    },
  },
  mounted () {
    // this.$Notice.$on('cameravideoplay', (camera) => {
    //   this.cameraSN = camera.sn
    // })
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
  height: 40px;
  border: 1px solid #005982;
  margin: 0 auto;
  background-repeat: repeat;
  background-size: cover;
  // overflow: auto;
  background: red;
  line-height: 34px;
}
.CenterContainer {
  width: 100%;
  position: inherit;
  height: calc(100% - 40px);
  background: cadetblue;
}
// .BottomContainer {
//   width: 100%;
//   height: 331px;
//   border: 1px solid #005982;
//   margin: 0 auto;
//   background-repeat: repeat;
//   background-size: cover;
//   overflow: auto;
//   background: blue;
// }
.CenterContainer .PTZTabs {
  background: gold;
}
</style>