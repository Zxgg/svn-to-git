<template>
  <div class="Main"
       ref="MainView">
    <div class="TopContainer">
      <span>选中镜头</span>
      <!-- <el-card class="RealTimeVideoPlayView_card"> -->

      <div v-for="(city,index) in cameraSnList.slice(0,4)"
           :key="index"
           class="SelectCamLens"
           @click="checkedFun(city)">
        <el-tooltip class="item"
                    effect="dark"
                    content="点击删除"
                    placement="top-start">
          <div>
            <span>{{city.name}}</span>
            <span class="icon iconfont">&#xe6a0;</span>
          </div>

        </el-tooltip>

      </div>
      <!-- </el-card> -->
    </div>
    <div class="CenterContainer">
      <div class="block">
        <span class="dateRangeTitle">选择时间范围</span>
        <el-date-picker v-model="value"
                        type="datetimerange"
                        align="right"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
        </el-date-picker>
        <el-button @click="retrieve">检索</el-button>
      </div>
    </div>
    <div class="BottomContainer">
      <span class="el-icon-warning"></span>
      <span>请选择不大于8个小时的时间段进行检索</span>
    </div>
  </div>
</template>
<script>
import { getRecordList } from "@/api/smartVideo/vcnrtsp"
import CameraPanelVue from '../../VideoPlay/CameraPanel.vue'
import { getLiveGBSRecordlist, controlLiveGBSRecord } from '@/api/LiveGBS'
import configInfo from '@/webconfig/configInfo'
export default {
  // components: { cameraTreeLazy },
  data () {
    return {
      cameraSnList: [],
      value: [new Date().getTime() - 3600 * 1000 * 2, new Date().getTime()],
      progressOfValue: 0,
      playFormsDataList: [],
    }
  },
  created () { },
  filters: {},
  methods: {
    checkCamLen (data) {
      var arr = this.cameraSnList.filter((item, i) => {
        if (item.sn === data.sn) { return item }
      })
      if (arr.length === 0) { this.cameraSnList.push(data) }
    },
    unCheckCamLen (data) {
      // var arr = this.cameraSnList
      var index = this.cameraSnList.indexOf(data)
      if (index > -1) { // 大于0 代表存在，
        this.cameraSnList.splice(index, 1)// 存在就删除
      }
    },
    checkedFun (value) {
      this.$Notice.$emit('deleteCamLen', value)
    },
    async retrieve () { // 检索， 根据选中设备和 时间播放历史视频
      var self = this
      self.playFormsDataList = []
      if (self.cameraSnList.length === 0) {
        self.$message.warning('请先选择要播放的镜头!')
        return
      } else if (self.value === null) {
        self.$message.warning('请先选择要检索的时间!')
        return
      }
      // else if ((self.value[1] - self.value[0]) > 60 * 60 * 8 * 1000) {
      //   self.$message.warning('选择时间段不能大于8个小时')
      //   return
      // }
      let tableData = []
      let startTimeData = self.commonFunction.timeToStringT(self.value[0])
      let endTimeData = self.commonFunction.timeToStringT(self.value[1])
      console.log('time:', self.value, '------', 'startTimeData:', startTimeData, '---', 'endTimeData:', endTimeData)
      for (let i = 0; i < this.cameraSnList.length; i++) {
        let cameraCodeData = this.cameraSnList[i].code.split('#')
        // console.log(this.cameraSnList[i].code)
        // var res = await getRecordList(cameraCodeData[0], cameraCodeData[cameraCodeData.length - 1], startTimeData, endTimeData)
        // var res = await getRecordList('09020360394697740101', 'f9e8841e443441be87d99faa62f6bb65', '20200711102035', '20200713105035')

        let dataInfo = {
          serial: configInfo.deviceserial,
          code: cameraCodeData[0],
          starttime: startTimeData,
          endtime: endTimeData
        }
        var res = await getLiveGBSRecordlist(dataInfo)

        console.log('lishiliebiao:', res)
        if (res.RecordList) {
          res.RecordList.forEach(item => {
            let recordObj = {
              cameraCode: this.cameraSnList[i].sn,
              cameraName: this.cameraSnList[i].name,
              startTime: item.StartTime,
              endTime: item.EndTime
            }
            tableData.push(recordObj)
          })
        }
      }
      self.$Notice.$emit('recordtablegeted', tableData)
    }
  },
  mounted () {
    this.$Notice.$on('checkCamLen', (data) => { this.checkCamLen(data) })
    this.$Notice.$on('uncheckCamLen', data => { this.unCheckCamLen(data) })
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
  height: 200px;
  border: 1px solid #005982;
  margin: 0 auto;
  background-repeat: repeat;
  background-size: cover;
  // overflow: auto;
  // background: red;
  line-height: 34px;
}
.CenterContainer {
  width: 100%;
  position: inherit;
  height: calc(100% - 200px - 40px);
  // background: cadetblue;
}
.BottomContainer {
  width: 100%;
  height: 40px;
  border: 1px solid #005982;
  margin: 0 auto;
  background-repeat: repeat;
  background-size: cover;
  overflow: auto;
  // background: blue;
}

.RealTimeVideoPlayView_card {
  // background: transparent;
  // height: 100%;
  // position: absolute;
  // left: 0;
  // top: 0;
  // right: -17px;
  // bottom: 0;
  // overflow-x: hidden;
  // overflow-y: scroll;
}
.SelectCamLens {
  color: #0f86ae;
  font-size: 16px;
  cursor: pointer;
}
.dateRangeTitle {
  // top: 20px;
}
</style>