<template>
  <div>
    <div ref="abnormal"
         class="abnormal-popup">
      <a ref="popup-closer"
         class="abnormal-popup-closer"
         @click="abnormalClose"></a>
      <div ref="popup_content">
        <el-col class="col">
          <el-row class="spanText"> <label class="camera_title">异常事件信息</label></el-row>
          <el-row class="spanText"><span>事件名称：</span>
            <span v-if="abnormalFeature && abnormalFeature.get('name')==='alarm'">{{abnormalFeature.getProperties().eventName}}</span>
          </el-row>
          <el-row class="spanText"><span>时间：</span><span v-if="abnormalFeature && abnormalFeature.get('name')==='alarm'">{{abnormalFeature.getProperties().eventTime}}</span></el-row>
          <el-row class="spanText"><span>地址：</span><span v-if="abnormalFeature && abnormalFeature.get('name')==='alarm'">{{abnormalFeature.getProperties().eventAdrress}}</span></el-row>
        </el-col>
      </div>
      <div>
      </div>
    </div>
    <!-- <div ref="abnormals"
         class="abnormal-popup">
      <a ref="popup-closer"
         class="abnormal-popup-closer"
         @click="popClose"></a>
      <div ref="popup_content">
        <el-col class="col">
          <el-row class="spanText"> <label class="camera_title">设备信息</label></el-row>
          <el-row class="spanText"><span>名称：</span>
            <span v-if="abnormalFeature && abnormalFeature.get('name')==='alarm'">{{abnormalFeature.getProperties().cameraName}}</span>
          </el-row>
          <el-row class="spanText"><span>状态：</span><span v-if="abnormalFeature && abnormalFeature.get('name')==='alarm'">{{abnormalFeature.getProperties().uistaus}}</span></el-row>
          <el-row class="spanText"><span>地址：</span><span v-if="abnormalFeature && abnormalFeature.get('name')==='alarm'">{{abnormalFeature.getProperties().cameraAddress}}</span></el-row>
          <el-row class="spanText">
            <span>坐标：</span>
            <span v-if="abnormalFeature && abnormalFeature.get('name')==='alarm'">{{abnormalFeature.getProperties().geometry.flatCoordinates[0].toFixed(2)}}</span>
            <span v-if="abnormalFeature && abnormalFeature.get('name')==='alarm'">{{abnormalFeature.getProperties().geometry.flatCoordinates[1].toFixed(2)}}</span>
          </el-row>
        </el-col>
      </div>
      <div>
      </div>
    </div> -->
  </div>
</template>

<script>
export default {
  name: 'abnormalPopup',
  components: {},
  props: ['abnormalFeature'],
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
      var element = this.$refs.abnormal
      this.$emit('abnormalElement', element)
    },
    abnormalClose () {
      this.$emit('abnormalClose')
    },
  }
}
</script>

<style lang='scss'>
.abnormal-popup {
  position: absolute;
  background-color: #032941;
  padding: 15px;
  // border-radius: 10px;
  border: 1px solid #005982;
  bottom: 12px;
  left: -50px;
  min-width: 240px;
  opacity: 0.7;
}

.abnormal-popup:after,
.abnormal-popup:before {
  top: 100%;
  border: solid transparent;
  content: " ";
  height: 0;
  width: 0;
  position: absolute;
  pointer-events: none;
}

.abnormal-popup:after {
  border-top-color: #032941;
  border-width: 10px;
  left: 48px;
  margin-left: -10px;
}

.abnormal-popup:before {
  border-top-color: #005982;
  border-width: 11px;
  left: 48px;
  margin-left: -11px;
}
.abnormal-popup-closer:after {
  content: "✖";
}
.abnormal-popup-closer {
  position: absolute;
  top: 5px;
  right: 5px;
  color: #11bbf3;
}
.abnormal-popup a:active {
  color: #00ffff !important;
}
.abnormal-popup a:hover {
  color: #00ffff !important;
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
    color: #11bbf3;
  }
  #correction：active {
    color: #00ffff !important;
  }
  #correction：hover {
    color: #00ffff !important;
  }
  #play {
    font-size: 20px;
    margin-left: 30px;
    color: #11bbf3;
  }
  #play：active {
    color: #00ffff !important;
  }
  #play：hover {
    color: #00ffff !important;
  }
}
</style>
