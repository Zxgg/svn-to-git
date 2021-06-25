<template>
  <div>
    <div ref="popup"
         class="feature-popup">
      <a ref="popup-closer"
         class="feature-popup-closer"
         @click="popClose"></a>
      <div ref="popup_content">
        <el-col class="col">
          <el-row class="spanText"> <label class="camera_title">设备信息</label></el-row>
          <el-row class="spanText"><span>名称：</span>
            <span v-if="feature && feature.get('name')==='device'">{{feature.getProperties().cameraName}}</span>
          </el-row>
          <el-row class="spanText"><span>状态：</span><span v-if="feature && feature.get('name')==='device'">{{feature.getProperties().uiStatus}}</span></el-row>
          <el-row class="spanText"><span>地址：</span><span v-if="feature && feature.get('name')==='device'">{{feature.getProperties().cameraName}}</span></el-row>
          <el-row class="spanText">
            <span>坐标：</span>
            <span v-if="feature && feature.get('name')==='device'">{{feature.getProperties().geometry.flatCoordinates[0].toFixed(2)}}</span>
            <span v-if="feature && feature.get('name')==='device'">{{feature.getProperties().geometry.flatCoordinates[1].toFixed(2)}}</span>
          </el-row>
          <!-- <el-row class="row">
            <a id="play"
               class="icon iconfont iconfontColor"
               @click="playVideo(feature.getProperties().cameraSn)">&#xe609;</a>
            <a id="correction"
               class="icon iconfont iconfontColor"
               @click="correction(cameraPoint)">&#xe672;</a>
          </el-row> -->
        </el-col>
      </div>
      <div>
      </div>
    </div>
    <el-dialog style="opacity: 0.8"
               title="视频播放"
               :visible.sync="dialogVideoInfoVisible"
               width="800px"
               :before-close="handleClose"
               :append-to-body="true">
      <div class="dialogFeatureDev">
        <el-col>
          <!-- 告警视频 -->
          <el-row id="dialogFeatureVideo">
            <div class="dialogFeaturePop">

            </div>
          </el-row>
        </el-col>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'featurePopup',
  components: {},
  props: ['feature'],
  data () {
    return {
      dialogVideoInfoVisible: false
    }
  },
  mounted () {
    this.initPopup()
  },
  methods: {
    init () {

    },
    initPopup () {
      var element = this.$refs.popup
      this.$emit('featureElement', element)
    },
    popClose () {
      this.$emit('popClose')
    },
    playVideo (cameraSN) {
      console.log(cameraSN, 'cameraSn')
      // this.dialogVideoInfoVisible = true
      // this.playVideoFuction(cameraSN)
    },
    async playVideoFuction (cameraSN) {
      let hls = await this.commonFunction.getHlsStreamByCameraCode(cameraSN)
      // this.$refs.videoPlayer.playVideo(hls)
    },
    correction (point) {

    },
    handleClose (done) {
      done()
    }

  }
}
</script>

<style lang='scss'>
.feature-popup {
  position: absolute;
  // background-color: #032941;
  background: linear-gradient(2deg, rgba(4, 8, 21, 1), rgba(3, 8, 20, 0.5));
  padding: 15px;
  // border-radius: 10px;
  border: 1px solid #fff;
  bottom: 12px;
  left: -50px;
  min-width: 240px;
  opacity: 0.7;
}

.feature-popup:after,
.feature-popup:before {
  top: 100%;
  border: solid transparent;
  content: " ";
  height: 0;
  width: 0;
  position: absolute;
  pointer-events: none;
}

.feature-popup:after {
  border-top-color: #fff;
  border-width: 10px;
  left: 48px;
  margin-left: -10px;
}

.feature-popup:before {
  border-top-color: #fff;
  border-width: 11px;
  left: 48px;
  margin-left: -11px;
}
.feature-popup-closer:after {
  content: "✖";
}
.feature-popup-closer {
  position: absolute;
  top: 5px;
  right: 5px;
  color: #fff;
}
.feature-popup a:active {
  color: #fff !important;
}
.feature-popup a:hover {
  color: #fff !important;
}
.camera_title {
  color: #fff;
  font-size: 16px;
  line-height: 30px;
}
.col {
  span {
    color: #fff;
    font-size: 14px;
    line-height: 30px;
    margin-left: 5px;
  }
}
.row {
  margin-top: 10px;
  #correction {
    font-size: 20px;
    margin-left: 100px;
    // text-align: right;
    // margin-right: 30px;
    color: #fff;
  }
  #correction：active {
    color: #fff !important;
  }
  #correction：hover {
    color: #fff !important;
  }
  #play {
    font-size: 20px;
    margin-left: 30px;
    color: #fff;
  }
  #play：active {
    color: #fff !important;
  }
  #play：hover {
    color: #fff !important;
  }
}
// .dialogFeatureDev {
//   width: 104%;
//   height: 400px;
// }
// #dialogFeatureVideo {
//   width: 100%;
//   height: 380px;
// }
// .dialogFeaturePop {
//   width: 100%;
//   height: 380px;
//   border: 0.5px solid rgba(0, 89, 130, 0.5);
// }
</style>
