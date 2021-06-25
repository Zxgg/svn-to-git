<template>
  <div class="Main"
       ref="MainView">
    <div id="ChildSearchFaceByImage-Right-Central-inside"
         v-for="(alarmItem,index) in alarmData"
         :key="index">
      <div id="ChildSearchFaceByImage-Right-Central-inside-two">
        <img id="ImageFaceDate"
             v-if="alarmItem.alarm.thumPicUrl.length>0"
             :src="alarmItem.alarm.thumPicUrl"
             @click="showBigImage(alarmItem.taskinfo.cameraname, alarmItem.alarm.alarmtime, alarmItem.alarm.picUrl)" />

        <img id="ImageFaceDateTwo"
             v-if="alarmItem.alarm.thumPicUrl.length<=0"
             src="../../assets/images/home/imageBackground.png" />
      </div>
      <div id="ChildSearchFaceByImage-Right-Central-inside-three">
        <label id="ChildSearchFaceByImage-Right-Central-inside-three-Label">告警详情</label>
        <span class="icon iconfont iconfontColor"
              @click="showAlarmDetail(alarmItem)">&#xe659;</span>
        <span class="icon iconfont iconfontColor"
              @click="mapPositioning(alarmItem)">&#xe755;</span>
        <span class="icon iconfont iconfontColor"
              @click="addClue(alarmItem)">&#xe62a;</span>
      </div>
      <div id="ChildSearchFaceByImage-Right-Central-inside-four">
        <el-col class="alarmTextDetail">
          <el-row class="spanText"> <span>告警时间：</span><span>{{alarmItem.alarm.alarmtime | formatDate(that)}}</span></el-row>
          <el-row class="spanText"><span>设备名称:</span><span>{{alarmItem.taskinfo.cameraname}}</span></el-row>
          <!--  -->
          <el-row class="spanText"><span>告警地点：</span><span></span></el-row>
        </el-col>
      </div>

    </div>
  </div>
</template>
<script>
//import cameraTreeLazy from '@/components/cameraTree/cameraTree_lazy'
export default {
  //components: { cameraTreeLazy },
  //props: {dataInfo: {type: Object,default: null}},
  data () {
    return {
      that: this,
      showData: {}
    }
  },
  created () { },
  mounted () { },
  filters: {},
  methods: {},
  computed: {},
  watch: {
    //dataInfo: {
    //handler: function (newValue) {
    //console.log('new value change')
    //},
    //deep: true,
    //immediate: true
    //}
  },
  beforeDestroy () { }
}
</script>
<style lang="scss" scoped>
.Main {
  width: 100%;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
}

//告警弹窗
.iconfontColor {
  color: #1085b5;
}
#ChildSearchFaceByImage-Right-Central-inside {
  border: 0.5px solid #005982;
  border-radius: 4px;
  width: 229px;
  height: 340px;
  float: left;
  margin-left: 5px;
  margin-top: 8px;
}
#ChildSearchFaceByImage-Right-Central-inside-two {
  width: 98%;
  margin: 1px;
  height: 170px;
  border: 0.5px solid #005982;
  text-align: center;
}
#ImageFaceDate {
  width: 100%;
  height: 100%;
  margin-left: 0;
  margin-top: 0;
}
#ImageFaceDateTwo {
  width: 85%;
  height: 90%;
  margin-left: 0;
  margin-top: 0;
}
#ChildSearchFaceByImage-Right-Central-inside-three-Label {
  color: #0f86ae;
  font-size: 14px;
  line-height: 30px;
  margin-left: 10px;
  margin-right: 60px;
}
#ChildSearchFaceByImage-Right-Central-inside-three {
  height: 30px;
  width: 98%;
  margin: 1px;
  margin-top: 3px;
  border: 0.5px solid #005982;
}

#ChildSearchFaceByImage-Right-Central-inside-three span {
  margin-right: 10px;
  cursor: pointer;
}

#ChildSearchFaceByImage-Right-Central-inside-three span:hover {
  color: #00ffff !important;
}
#ChildSearchFaceByImage-Right-Central-inside-four {
  width: 99.8%;
  height: 115px;
  /* border: 0.5px solid red; */
}

.alarmTextDetail {
  height: 100%;
}

.alarmTextDetail span {
  margin-left: 10px;
  // margin-top: 10px;
  color: #0f86ae;
  font-size: 13px;
}

.alarmTextDetail .spanText {
  height: 29%;
  line-height: 29px;
}
</style>