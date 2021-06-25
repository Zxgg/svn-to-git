<template>
  <el-row class="Main">
    <el-row class="topContainer">
      <el-col class="topLeft">
        <el-row class="titleContain">
          <div class="textLeft">
            <i class="icon iconfont  iconshuxian-copy title1Icon"></i>
            <span class="title1 VerticalCenter">疫情信息填报</span>
          </div>
        </el-row>
        <PieChart :title="'疫情信息填报'"
                  :data="personEpidemicStateRecordStatistics"
                  :radius="personPieRadius" />
      </el-col>
      <el-col class="topCenter">
        <!-- <el-row class="titleContain">
          <div class="textLeft">
            <i class="icon iconfont  iconshuxian-copy title1Icon"></i>
            <span class="title1 VerticalCenter">核酸检测</span>
          </div>
        </el-row> -->
        <el-row class="NucleicAcidTestingInfoContain">
          <el-col class="otherFullCol" />
          <el-col class="NucleicAcidTestingInfo hesuan1">
            <div class="HorizontalRight hesuanAera">
              <span class="hesuanValue">{{nucleicAcidTestingPersonnelNum}}</span>
              <span class="hesuanUnit">人次</span>
            </div>
          </el-col>
          <el-col class="otherFullCol" />
          <el-col class="NucleicAcidTestingInfo hesuan2">
            <div class="HorizontalRight hesuanAera">
              <span class="hesuanValue">{{nucleicAcidTestingFamilyNum}}</span>
              <span class="hesuanUnit">家庭次</span>
            </div>
          </el-col>
        </el-row>
        <el-row class="reasonTypeInfo">
          <PieChart :title="'隔离原因'"
                    :data="epidemicIsolationFamilyReasonStatistics"
                    :colors="epidemicIsolationFamilyReasonColors" />
        </el-row>
      </el-col>
      <el-col class="topRight">
        <el-row class="titleContain">
          <div class=" textLeft">
            <i class="icon iconfont  iconshuxian-copy title1Icon"></i>
            <span class="title1 VerticalCenter">当前隔离信息</span>
          </div>
        </el-row>
        <el-row class="contentContain">
          <el-row class="halfcontentContain">
            <Percentagecircle :data="epidemicIsolationFamilyTotalStatistics" />
          </el-row>
          <el-row class="halfcontentContain">
            <Percentagecircle :data="epidemicIsolationFamilyPersonTotalStatistics" />
          </el-row>
        </el-row>
      </el-col>
    </el-row>
    <el-row class="bottomContainer">
      <el-col class="bottomLeft"
              :span="12">
        <el-row class="titleContain">
          <div class="textLeft">
            <i class="icon iconfont  iconshuxian-copy title1Icon"></i>
            <span class="title1 VerticalCenter">上报信息风险变化</span>
          </div>
        </el-row>
        <el-row class="contentContain">
          <doubleLine :data="personEpidemicStateRecordFlowLine" />
        </el-row>
      </el-col>
      <el-col class="bottomRight"
              :span="12">
        <el-row class="titleContain">
          <div class="textLeft">
            <i class="icon iconfont  iconshuxian-copy title1Icon"></i>
            <span class="title1 VerticalCenter">新增隔离家庭</span>
          </div>
        </el-row>
        <el-row class="contentContain">
          <doubleLine :data="isolationFamilyFlowLine" />
        </el-row>
      </el-col>
    </el-row>
  </el-row>
</template>
<script>
import PieChart from '@/components/dashboard/PieChart'
import doubleLine from '@/components/dashboard/double_Line'
import Percentagecircle from '@/components/dashboard/Percentagecircle'
import { getIsolationOfFamily, getIsolationOfPersonnel, getNucleicAcidTestingNum, getIsolationReason, getIsolationFamilyTimeCurve, getReportInfo } from '@/api/epidemic/statistics.js'
export default {
  components: { PieChart, doubleLine, Percentagecircle },
  //props: {dataInfo: {type: Object,default: null}},
  data () {
    return {
      that: this,
      personEpidemicStateRecordStatistics: [],//疫情信息填报统计
      personPieRadius: [0, 80],
      personPieColors: ['#3ca1fb', '#43cadd'],
      personEpidemicStateRecordFlowLine: null,//疫情信息填报记录
      epidemicIsolationFamilyTotalStatistics: null,//隔离家庭比例
      epidemicIsolationFamilyPersonTotalStatistics: null,//隔离人员比例
      epidemicIsolationFamilyReasonStatistics: [],//隔离家庭原因比例
      epidemicIsolationFamilyReasonColors: ['#44D7B6', '#FA6400', '#32C5FF', '#F7B500', '#00FFFF', '#00FF00', '#FFFF00', '#FF8C00', '#FF0000', '#FE8463'],
      nucleicAcidTestingFamilyNum: 0,//隔离家庭中已做过至少一次核酸的家庭数目
      nucleicAcidTestingPersonnelNum: 0,//隔离家庭中已做过至少一次核酸的人员数目
      isolationFamilyPieColors: ['#426ae1', '#19ffe0'],
      isolationFamilyFlowLine: null////隔离家庭信息记录
    }
  },
  created () { },
  mounted () {
    this.getTestData()
    this.getData()
  },
  filters: {},
  methods: {
    getTestData () {
      this.personEpidemicStateRecordStatistics = [
        { value: 35, name: '有风险记录', itemStyle: { color: this.personPieColors[0] } },
        { value: 800, name: '无风险记录', itemStyle: { color: this.personPieColors[1] } }
      ]
      this.epidemicIsolationFamilyTotalStatistics = {
        title: '隔离家庭占比',
        showRate: true,//是否按比例显示标题，不填或者false为按值显示
        totalNum: 324,
        totalName: '总计',
        attentionNum: 54,//关注项的数量
        attentionName: '尚在隔离家庭',//关注项的名称
        otherName: '已经解除隔离家庭'
      }
      this.epidemicIsolationFamilyPersonTotalStatistics = {
        title: '隔离人员占比',
        showRate: true,//是否按比例显示标题，不填或者false为按值显示
        totalNum: 324 * 3,
        totalName: '总计',
        attentionNum: 54 * 3,//关注项的数量
        attentionName: '尚在隔离人员',//关注项的名称
        otherName: '已经解除隔离人员'
      }
      this.epidemicIsolationFamilyReasonStatistics = [
        { value: 300, name: '密切接触者' },
        { value: 15, name: '中高风险区归家' },
        { value: 5, name: '国外回国' },
        { value: 4, name: '痊愈后归家隔离' }
      ]
      this.personEpidemicStateRecordFlowLine = {
        xAxis: ['10.01', '10.02', '10.03', '10.04', '10.05', '10.06', '10.07'],
        lines: [
          {
            name: '有风险记录',
            data: [30, 29, 25, 28, 30, 18, 27, 15, 19, 18, 18, 22, 18, 27],
            lineAreaColor: '#fff200',//区域颜色
            lineColor: '#fff200',//线颜色
            linePointColor: null,//线节点颜色
          }, {
            name: '无风险记录',
            lineAreaColor: '#2d6166',//区域颜色
            // lineColor:null,//线颜色
            // linePointColor:null,//线节点颜色
            data: [800, 805, 805, 802, 789, 756, 799, 802, 805, 795, 802, 789, 756, 799]

          }
        ]
      }
      this.isolationFamilyFlowLine = {
        xAxis: ['10.01', '10.02', '10.03', '10.04', '10.05', '10.06', '10.07'],
        lines: [
          {
            name: '新增隔离家庭',
            smooth: 0,//折现不设置平滑
            data: [30, 29, 25, 28, 30, 18, 27, 15, 19, 18, 18, 22, 18, 27],
            lineAreaColor: '#fff200',//区域颜色
            lineColor: '#fff200',//线颜色
            linePointColor: null,//线节点颜色
          }, {
            name: '尚在隔离家庭',
            smooth: 0,//折现不设置平滑
            lineAreaColor: '#2d6166',//区域颜色
            // lineColor:null,//线颜色
            // linePointColor:null,//线节点颜色
            data: [800, 805, 805, 802, 789, 756, 799, 802, 805, 795, 802, 789, 756, 799]
          }
        ]
      }
    },
    getData () {
      // 统计-正在隔离家庭/总共隔离家庭
      getIsolationOfFamily().then(response => {
        if (response.code === 200) {
          this.epidemicIsolationFamilyTotalStatistics = {
            title: '隔离家庭占比',
            showRate: true,//是否按比例显示标题，不填或者false为按值显示
            totalNum: response.data.allIsolationFamilyNum,
            totalName: '总计',
            totalColor: '#216455',
            attentionNum: response.data.isolationFamilyNum,//关注项的数量
            attentionName: '尚在隔离家庭',//关注项的名称
            attentionColor: '#44D7B6',
            otherName: '已经解除隔离家庭'
          }
        }
      })
      // 统计-正在隔离人员/总共隔离人员
      getIsolationOfPersonnel().then(response => {
        if (response.code === 200) {
          this.epidemicIsolationFamilyPersonTotalStatistics = {
            title: '隔离人员占比',
            showRate: true,//是否按比例显示标题，不填或者false为按值显示
            totalNum: response.data.allIsolationPersonnelNum,
            totalName: '总计',
            totalColor: '#66521A',
            attentionNum: response.data.isolationPersonnelNum,//关注项的数量
            attentionName: '尚在隔离人员',//关注项的名称
            attentionColor: '#F7B500',
            otherName: '已经解除隔离人员'
          }
        }
      })
      //统计-隔离家庭核酸检测总次数
      getNucleicAcidTestingNum().then(response => {
        if (response.code === 200) {
          this.nucleicAcidTestingFamilyNum = response.data.nucleicAcidTestingFamilyNum
          this.nucleicAcidTestingPersonnelNum = response.data.nucleicAcidTestingPersonnelNum
        }
      })
      //统计-隔离原因类型
      getIsolationReason().then(response => {
        if (response.code === 200) {
          let list = []
          for (let index = 0; index < response.data.length; index++) {
            let element = response.data[index];
            if (element.num !== undefined && element.num > 0) {
              list.push({
                name: element.name,
                value: element.num
              })
            }
          }
          this.epidemicIsolationFamilyReasonStatistics = list
        }
      })
      let currentTime = new Date()
      let param1 = {
        startTime: this.$cf.DataFormat(currentTime, 'yyyy-MM-dd'),
        endTime: this.$cf.DataFormat(currentTime, 'yyyy-MM-dd'),
      }
      //统计-个人上报 有风险人数/总共人数 时间曲线
      getReportInfo(param1).then(response => {
        if (response.code === 200) {
          let list = [
            { value: 0, name: '有风险记录', itemStyle: { color: this.personPieColors[0] } },
            { value: 0, name: '无风险记录', itemStyle: { color: this.personPieColors[1] } }
          ]

          if (response.data.riskInfoList && response.data.riskInfoList.length > 0) {
            list[0].value = response.data.riskInfoList[0].num
          }
          if (response.data.allReportInfoList && response.data.allReportInfoList.length > 0) {
            list[1].value = response.data.allReportInfoList[0].num - list[0].value
          }
          this.personEpidemicStateRecordStatistics = list
        }
      })
      let param = {
        startTime: '',
        endTime: ''
      }
      param.endTime = this.$cf.DataFormat(currentTime, 'yyyy-MM-dd')
      param.startTime = param.endTime
      currentTime.setTime(currentTime.getTime() - 14 * 24 * 3600 * 1000)//前推14天
      param.startTime = this.$cf.DataFormat(currentTime, 'yyyy-MM-dd')
      //统计 新增隔离家庭/已有隔离家庭 时间曲线
      getIsolationFamilyTimeCurve(param).then(response => {
        if (response.code === 200) {
          let xAxis = response.data.allIsolationFamilyList.map(item => item.name)
          let lines = []
          lines.push({
            name: '尚在隔离家庭',
            smooth: 0,//折现不设置平滑
            lineAreaColor: '#2d6166',//区域颜色
            lineColor: null,//线颜色
            linePointColor: null,//线节点颜色
            data: response.data.allIsolationFamilyList.map(item => item.num)
          })
          lines.push({
            name: '新增隔离家庭',
            smooth: 0,//折现不设置平滑
            data: response.data.newIsolationFamilyList.map(item => item.num),
            lineAreaColor: '#fff200',//区域颜色
            lineColor: '#fff200',//线颜色
            linePointColor: null,//线节点颜色
          })
          this.isolationFamilyFlowLine = {
            xAxis: xAxis,
            lines: lines
          }
        }
      })
      //统计-个人上报 有风险人数/总共人数 时间曲线
      getReportInfo(param).then(response => {
        if (response.code === 200) {
          let xAxis = response.data.allReportInfoList.map(item => item.name)
          let lines = []
          lines.push({
            name: '填报记录',
            lineAreaColor: '#2d6166',//区域颜色
            lineColor: null,//线颜色
            linePointColor: null,//线节点颜色
            data: response.data.allReportInfoList.map(item => item.num)
          })
          lines.push({
            name: '有风险记录',
            data: response.data.riskInfoList.map(item => item.num),
            lineAreaColor: '#fff200',//区域颜色
            lineColor: '#fff200',//线颜色
            linePointColor: null,//线节点颜色
          })
          // this.personEpidemicStateRecordStatistics
          this.personEpidemicStateRecordFlowLine = {
            xAxis: xAxis,
            lines: lines
          }
        }
      })
    }
  },
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
.topContainer {
  width: 100%;
  height: 60%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.bottomContainer {
  width: 100%;
  // height: calc(100% - 591px);
  height: 40%;
  display: flex;
  flex-direction: row;
  // justify-content: space-between;
}
.topLeft {
  width: 24.8%;
  height: 100%;
  background-color: #ffffff;
  margin: 10px 12px;
}
.topCenter {
  width: 44.4%;
  height: 100%;
  background-color: #ffffff;
  margin: 10px 12px;
}
.topRight {
  width: 24.8%;
  height: 100%;
  background-color: #ffffff;
  margin: 10px 12px;
}
.bottomLeft {
  height: 90%;
  background-color: #ffffff;
  margin-top: 20px;
  margin-right: 32px;
  width: 48%;
  margin-left: 20px;
}
.bottomRight {
  height: 90%;
  background-color: #ffffff;
  margin-top: 20px;
  width: 48%;
  // margin-right: 24px;
}
.titleContain {
  margin-top: 10px;
  margin-bottom: 10px;
  height: 27.5px;
}
.contentContain {
  height: calc(100% - 47.5px);
}
.halfcontentContain {
  height: 50%;
}
// 核酸检测信息
.NucleicAcidTestingInfoContain {
  width: 100%;
  height: 120px;
  margin-top: 30px;
  margin-bottom: 20px;
}
.NucleicAcidTestingInfo {
  background-repeat: no-repeat;
  // background-repeat: repeat;
  background-size: contain; /* 重点二 */
  width: 262px;
  height: 81px;
}

.hesuan1 {
  background-image: url("../../../assets/image/hesuan1.png");
}

.hesuan2 {
  background-image: url("../../../assets/image/hesuan2.png");
}

.reasonTypeInfo {
  height: calc(100% - 180px);
  margin-top: 20px;
  margin-bottom: 20px;
}
.fullCol {
  width: 80px;
  height: 81px;
}
.otherFullCol {
  width: calc((100% - 80px - 262px - 262px - 10px) / 2);
  // width: 80px;
  height: 81px;
}
.Main {
  width: 100%;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
}
.hesuanAera {
  margin-top: 12px;
  margin-right: 20px;
}
.hesuanTitle {
  font-size: 30px;
  color: red;
}
.hesuanValue {
  margin-left: 8px;
  margin-right: 8px;
  font-size: 48px;
  font-family: Impact;
  color: #ffffff;
  line-height: 58px;
  letter-spacing: 1px;
}
.hesuanUnit {
  margin-left: 8px;

  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #ffffff;
  line-height: 22px;
}

.title1Icon {
  color: #00c0ff;
  font-size: 24px;
}
.title1 {
  font-size: 16px;
  color: #000000;
  font-weight: bolder;
}
</style>