<template>
  <div id="mapBox"
       ref="MainView">
    <div id="map"
         ref="rootMap">
      <div class="toolbar_box">
        <el-dropdown class="select_layer"
                     trigger="click">
          <el-button type="primary"
                     class="icon iconfont">&#xe791;&nbsp;标绘<i class="el-icon-arrow-down el-icon--right"></i></el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="drawMap('Point')">点</el-dropdown-item>
            <el-dropdown-item @click.native="drawMap('Rectangle')">框选</el-dropdown-item>
            <el-dropdown-item @click.native="drawMap('Circle')">圈选</el-dropdown-item>
            <el-dropdown-item @click.native="drawMap('Polygon')">多边形</el-dropdown-item>
            <el-dropdown-item @click.native="drawMap('LineString')">自由线</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <el-dropdown class="select_layer"
                     trigger="click">
          <el-button type="primary"
                     class="icon iconfont"><i class="el-icon-s-cooperation"></i>&nbsp;工具<i class="el-icon-arrow-down el-icon--right"></i></el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="measure('LineString')">测距</el-dropdown-item>
            <el-dropdown-item @click.native="measure('Polygon')">测面</el-dropdown-item>
            <!-- <el-dropdown-item @click.native="initFull">初始位</el-dropdown-item> -->
            <!-- <el-dropdown-item @click.native="initHeatMap">热力图</el-dropdown-item> -->
          </el-dropdown-menu>
        </el-dropdown>
        <el-button type="primary"
                   class="icon iconfont toolbar_button_clear"
                   @click="CloseVector"><i class="el-icon-delete"></i>清除</el-button>
      </div>

      <featurepop v-show="false"
                  :locationData="locationData"
                  @initCameraPopup="initCameraPopup"
                  @initAlarmPopup="initAlarmPopup"
                  @initPassPopup="initPassPopup"
                  @cameraClose="cameraClose"
                  @alarmClose="alarmClose"
                  @passClose="passClose" />
    </div>
    <passTrackPanel class="track-Central"
                    v-show="passTrackData.trackShow"
                    :myInfo="passTrackData"
                    @CloseTrack="CloseTrack" />
    <careTrackPanel class="track-Central"
                    v-show="careTrackData.trackShow"
                    :myInfo="careTrackData"
                    @CloseTrack="CloseTrack" />
  </div>
</template>
<script>
// import cameraTreeLazy from '@/components/cameraTree/cameraTree_lazy'
import 'ol/ol.css'
import "@/assets/styles/gismap.scss"
import initMap from '@/components/gismap/mapfunction/initMap'
import getData from '@/components/gismap/mapfunction/getData'
import cluster from '@/components/gismap/mapfunction/cluster'
import location from '@/components/gismap/mapfunction/location'
import track from '@/components/gismap/mapfunction/trackAnalysis'
import drawMap from '@/components/gismap/mapfunction/draw'
import measure from '@/components/gismap/mapfunction/measure'
import mapOverlay from '@/components/gismap/mapfunction/mapOverlay'
import featurepop from '@/components/gismap/mapcomponents/featurepop'
import passTrackPanel from '@/components/gismap/mapcomponents/passTrack'
import careTrackPanel from '@/components/gismap/mapcomponents/careTrack'
export default {
  // components: { cameraTreeLazy },
  props: {
    passTrackObject: Array,
    passLocationObject: {
      type: Object,
      default:
      {
        cameraSn: "",
        passAddress: "",
        passImage: "",
        passName: "",
        passTime: null,
        passType: 1,
      }
    },
  },
  data () {
    return {
      that: this,
      drawer: false,
      centContainerHeight: '100px',
      locationData: {
        camera: null,
        pass: null,
        alarm: null,
        passName: ''
      },
      passTrackData: {
        pass: null,
        passName: '',
        trackShow: false,
      },
      careTrackData: {
        care: null,
        trackShow: false
      }
    }
  },
  created () { },
  filters: {},
  mixins: [initMap, cluster, getData, location, track, drawMap, measure, mapOverlay],
  components: { featurepop, passTrackPanel, careTrackPanel },
  methods: {
    initCameraPopup (element) {
      this.cameraElement = element
    },
    initAlarmPopup (element) {
      this.alarmElement = element
    },
    initPassPopup (element) {
      this.passElement = element
    },
    cameraClose () {
      this.map.removeOverlay(this.cameraPop.pop)
      this.lc_source.clear()
    },
    alarmClose () {
      this.map.removeOverlay(this.alarmPop)
      this.lc_source.clear()
    },
    passClose () {
      this.map.removeOverlay(this.passPop.pop)
      this.lc_source.clear()
    },
    refreshCenterContainerHeight () { this.centContainerHeight = (this.$refs.MainView.offsetHeight - 72 - 331 - 4) + 'px' },
    CloseVector () {
      const me = this
      me.drawsource.clear()// 清除标绘
      me.measuresource.clear()// 清除测量
      me.map.getOverlays().clear()// 清除覆盖层
      me.map.removeLayer(this.tr_layer)
      me.map.removeLayer(this.lc_layer)
    },
    cameraLocation (data) {
      const me = this
      if (data) {
        me.cameraPop = me.location(data, this.cameraElement)
        if (me.cameraPop.data) {
          data.cameraAddress = me.cameraPop.data.getProperties().cameraAddress
          me.locationData.camera = data
        }
      }
    },
    passLoacation (data) {
      const me = this
      if (data) {
        me.passPop = me.location(data, this.passElement)
        if (me.passPop.data) {
          data.cameraAddress = me.passPop.data.getProperties().cameraAddress
          if (data.passType === 1) {
            me.locationData.passName = '姓名：'
          }
          else {
            me.locationData.passName = '车牌号：'
          }

        }
        me.locationData.pass = data
      }
    },
    alarmLocation (data) {
      const me = this
      if (data) {
        me.alarmPop = me.location(data, this.alarmElement)
        if (me.alarmPop.data) {
          data.alarmAddress = me.alarmPop.data.getProperties().cameraAddress
          me.locationData.alarm = data
        }
      }
    },
    passTrack (data) {
      const me = this
      if (data && data.length > 0) {
        me.passTrackData.trackShow = true
        data.passAddress = me.track(data).getProperties().cameraAddress
        if (data.passType === 1) {
          me.passTrackData.passName = '姓名：'
        }
        else {
          me.passTrackData.passName = '车牌号：'
        }
        me.passTrackData.pass = data
      }
    },
    careTrack (data) {
      const me = this
      if (data && data.length > 0) {
        me.careTrackData.trackShow = true
        data.careAddress = me.track(data).getProperties().cameraAddress
        me.passTrackData.care = data
      }
    },
    CloseTrack () {
      this.trackShow = false
    }
  },
  mounted () {
    const the_page = this;
    // window.onresize = () => { return (() => { the_page.refreshCenterContainerHeight() })() }; 
    the_page.refreshCenterContainerHeight()
    the_page.$Notice.$on('cameraLocation', (val) => the_page.cameraLocation(val));
    the_page.$Notice.$on('alarmLocation', (val) => the_page.alarmLocation(val));
    the_page.$Notice.$on('passLoacation', (val) => the_page.passLoacation(val));
    the_page.$Notice.$on('passTrack', (val) => the_page.passTrack(val));
    the_page.$Notice.$on('careTrack', (val) => the_page.careTrack(val));
  },
  watch: {
    passTrackObject: {
      handler (n, o) {
        console.log('old', this.passTrackObject);
        if (this.passTrackObject && this.passTrackObject.length > 0) {
          this.passTrack(this.passTrackObject);
        }
      },
      deep: true,
      // 
    },
    passLocationObject: {
      handler: function (newValue) {
        console.log('old_passLocationObject', newValue);
      },
      deep: true,
      immediate: true
    }
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
// /deep/ #mapBox {
//   height: 75%;
//   width: 90%;
// }
</style>