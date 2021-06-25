<template>
  <div class="track-Central">
    <el-button href="#"
               id="popup-closer"
               title="关闭"
               type="primary"
               class="ol-popup-closer"
               @click="CloseTrack"></el-button>
    <div style="text-align: left;margin-top:5px">
      <label class="pop-title">轨迹展示</label>
    </div>
    <el-col class="track_pop-col02">
      <div class="outer-container-pop">
        <div class="inner-container-pop">
          <div v-if="myInfo && myInfo.pass"
               v-for="(info,index) in myInfo.pass"
               :key="index">
            <div class="dequery-Out">
              <div id="dequery-inside">
                <div id="dequery-inside-two">
                  <img class="trackImage"
                       v-if="info.passImage"
                       :src="info.passImage">
                  <!-- <img id="ImageFaceDate"
                       v-if="info.thumPicUrl && info.thumPicUrl.length<500"
                       src="../../../assets/images/IntelligentAnalysis/imageBackground.png" /> -->
                </div>
                <div id="dequery-inside-four">
                  <el-col>
                    <el-row>
                      <label>{{myInfo.passName+info.passName}}</label>
                    </el-row>
                    <el-row>
                      <label>设备地址：{{info.passAddress}}</label>
                    </el-row>
                    <el-row>
                      <label>时间：{{info.passTime}}</label>
                    </el-row>
                  </el-col>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </el-col>
    <!-- <el-dialog title="大图"
               :visible.sync="fullImagedialogVisible"
               :before-close="handleClose">
      <div class="full-img">
        <img :src="BigImgSrc"
             width="800px"
             height="550px">
      </div>
      <span slot="footer"
            class="dialog-footer">
        <el-button type="primary"
                   @click="fullImagedialogVisible = false">确 定</el-button>
      </span>
    </el-dialog> -->
  </div>
</template>

<script>
export default {
  props: ['myInfo'],
  data () {
    return {

    }
  },
  methods: {
    CloseTrack () {
      console.log(this.myInfo, 'ninosj')
      this.$emit('CloseTrack')
    }
  }
}
</script>

<style lang="scss">
#popup-closer {
  position: absolute;
  top: 2px;
  right: 5px;
  width: 22px;
  height: 20px !important;
  padding: 2px 2px !important;
}
.ol-popup-closer:after {
  text-align: left;
  font-size: 12px !important;
  content: "✖";
}

.pop-title {
  color: #00ffff;
  font-size: 14px;
  margin-left: 20px;
  // margin-left: -175px;
}
.track_pop-col02 {
  width: 100% !important;
  border: 1px solid #005982;
  border-bottom: none;
  margin-top: 5px;
  margin-left: 0px;
  height: 90% !important;
}
.outer-container-pop {
  width: 100%;
  border: 1px solid #000;
  height: 90%;
  text-align: left !important;
  position: relative;
  overflow: hidden;
  display: flex;
}
.inner-container-pop {
  position: absolute;
  left: 0;
  top: 0;
  right: -17px;
  width: 100%;
  bottom: 0;
  display: flex;
  overflow-y: hidden;
  overflow-x: scroll;
}
.dequery-Out {
  width: 160px;
  height: 100%;
  border: 0.5px solid #005982;
  float: left;
}
#dequery-inside {
  width: 100%;
  height: 100%;
  margin-top: 1.5%;
  border: 0.5px solid #005982;
  border-radius: 4px;
}
#dequery-Out:hover {
  border: 0.5px solid #00ffff;
}
#dequery-inside-Label {
  width: 100%;
  height: 80px;
  color: #005982;
  font-size: 14px;
  margin-top: 8px;
}
#dequery-inside-three-Label {
  color: #005982;
  font-size: 12px;
  line-height: 25px;
  margin-left: 10px;
}
#dequery-inside-three-xiangqing {
  color: #005982;
  margin-top: 5px;
  width: 16px;
  margin-right: 9px;
  height: 16px;
  float: right;
  border: none;
}
#dequery-inside-three-xiangqing:hover {
  border: 0.5px solid #00ffff;
}
#dequery-inside-three-dingwei:hover {
  border: 0.5px solid #00ffff;
}

#dequery-inside-three-dingwei {
  color: #005982;
  margin-top: 5px;
  width: 16px;
  margin-right: 5px;
  height: 16px;
  float: right;
  border: none;
}
#dequery-inside-One {
  width: 100%;
  height: 30px;
  text-align: center;
  margin-top: 8px;
}
#dequery-inside-two {
  width: 100%;
  height: 50%;
}
#dequery-inside-three {
  width: 99.8%;
  height: 25px;
  margin-top: 3px;
}
#dequery-inside-four {
  width: 99.8%;
  margin-top: 5%;
  label {
    margin-left: 10px;
    color: #005982;
    font-size: 12px;
  }
}
.trackImage {
  width: 100%;
  height: 100%;
}
</style>