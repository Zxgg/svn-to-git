<template>
  <div>
    <div ref="cameraPopup"
         class="feature-popup">
      <a ref="popup-closer"
         class="feature-popup-closer"
         @click="cameraClose"></a>
      <div ref="popup_content">
        <el-col class="col">
          <el-row class="spanText"> <label class="camera_title">设备信息</label></el-row>
          <el-row class="spanText"><span>设备名称：</span><span v-if="locationData&&locationData.camera">{{locationData.camera.cameraName}}</span></el-row>
          <el-row class="spanText"><span>设备类型：</span><span v-if="locationData&&locationData.camera">{{locationData.camera.cameraType}}</span></el-row>
          <el-row class="spanText"><span>设备状态：</span><span v-if="locationData&&locationData.camera">{{locationData.camera.cameraState}}</span></el-row>
          <el-row class="spanText"><span>设备地址：</span><span v-if="locationData&&locationData.camera">{{locationData.camera.cameraAddress}}</span></el-row>
        </el-col>
      </div>
      <div>
      </div>
    </div>
    <div ref="alarmPopup"
         class="feature-popup">
      <a ref="popup-closer"
         class="feature-popup-closer"
         @click="alarmClose"></a>
      <div ref="popup_content">
        <el-col class="col">
          <el-row class="spanText"> <label class="camera_title">告警信息</label></el-row>
          <el-row class="spanText"><span>设备名称：</span><span v-if="locationData&&locationData.alarm">{{locationData.alarm.cameraName}}</span></el-row>
          <el-row class="spanText"><span>告警类型：</span><span v-if="locationData&&locationData.alarm">{{locationData.alarm.alarmType}}</span></el-row>
          <el-row class="spanText"><span>告警时间：</span><span v-if="locationData&&locationData.alarm">{{locationData.alarm.alarmTime}}</span></el-row>
          <el-row class="spanText"><span>告警地址：</span><span v-if="locationData&&locationData.alarm">{{locationData.alarm.alarmAddress}}</span></el-row>
        </el-col>
        <el-col class="alarmpop_right">
          <img class="alarmpop_right_img"
               v-if="locationData&&locationData.alarm"
               :src="locationData.alarm.alarmImage">
        </el-col>
      </div>
      <div>
      </div>
    </div>
    <div ref="passPopup"
         class="feature-popup">
      <a ref="popup-closer"
         class="feature-popup-closer"
         @click="passClose"></a>
      <div ref="popup_content">
        <el-col class="col">
          <el-row class="spanText"> <label class="camera_title">通讯记录信息</label></el-row>
          <el-row class="spanText"><span v-if="locationData&&locationData.passName">{{locationData.passName}}</span><span v-if="locationData&&locationData.pass">{{locationData.pass.passName |ellipsis}}</span></el-row>
          <el-row class="spanText"><span>通行类型：</span><span v-if="locationData&&locationData.pass">{{locationData.pass.passType |passConverter}}</span></el-row>
          <el-row class="spanText"><span>抓拍时间：</span><span v-if="locationData&&locationData.pass">{{locationData.pass.passTime}}</span></el-row>
          <el-row class="spanText"><span>抓拍地址：</span><span v-if="locationData&&locationData.pass"
                  :title="locationData.pass.cameraAddress">{{locationData.pass.cameraAddress |ellipsis}}</span></el-row>
        </el-col>
        <el-col class="alarmpop_right">
          <img class="alarmpop_right_img"
               v-if="locationData&&locationData.pass"
               :src="locationData.pass.passImage">
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
  filters: {
    ellipsis (value) {
      if (!value) return ''
      if (value.length > 8) {
        return value.slice(0, 8) + '...'
      }
      return value
    },
    passConverter (value) {
      if (!value) return ''
      if (value === 1) {
        return '人员'
      } else {
        return '车辆'
      }
    },
    alarmConverter (value) {
      if (!value) return ''
      if (value === 17) {
        return '车辆告警'
      } else if (value === 1) {
        return '拌线告警'
      } else if (value === 2) {
        return '人群密度告警'
      } else if (value === 8) {
        return '区域入侵告警'
      } else if (value === 9) {
        return '9'
      }
    }
  },
  props: {
    locationData: {
      camera: Object,
      pass: Object,
      alarm: Object,
      passName: ''
    }
  },
  data () {
    return {
      dialogVideoInfoVisible: false,
    }
  },
  mounted () {
    this.initPopup()
  },
  methods: {
    init () {

    },
    initPopup () {
      let camera = this.$refs.cameraPopup
      this.$emit('initCameraPopup', camera)
      let alarm = this.$refs.alarmPopup
      this.$emit('initAlarmPopup', alarm)
      let pass = this.$refs.passPopup
      this.$emit('initPassPopup', pass)
    },
    cameraClose () {
      this.$emit('cameraClose')
    },
    alarmClose () {
      this.$emit('alarmClose')
    },
    passClose () {
      this.$emit('passClose')
    },
    playVideo (cameraSN) {
      this.dialogVideoInfoVisible = true
      this.playVideoFuction(cameraSN)
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
  background-color: #032941;
  padding: 15px;
  border-radius: 10px;
  border: 1px solid #005982;
  bottom: 12px;
  left: -50px;
  min-width: 320px;
  opacity: 0.8;
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
  border-top-color: #032941;
  border-width: 10px;
  left: 48px;
  margin-left: -10px;
}

.feature-popup:before {
  border-top-color: #005982;
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
  color: #11bbf3;
}
.feature-popup a:active {
  color: #00ffff !important;
}
.feature-popup a:hover {
  color: #00ffff !important;
}
.camera_title {
  color: #00ffff;
  font-size: 16px;
  line-height: 30px;
}
.col {
  text-align: left !important;
  width: 160px;
  span {
    color: #11bbf3;
    font-size: 14px;
    line-height: 30px;
    margin-left: 5px;
  }
}
.alarmpop_right {
  width: 100px;
  height: 100%;
  margin-top: 10%;
}
.alarmpop_right_img {
  width: 100%;
  height: 100%;
}
</style>
