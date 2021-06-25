<template>
  <div class="Main"
       ref="MainView">
    <!-- <el-tooltip class="item"
                effect="dark"
                content="隐藏显示"
                placement="top">
      <span @click="shrinkage()"
            class="switchImg el-icon-view"></span>
    </el-tooltip> -->
    <div class="notice"
         v-if="false"
         :style="noticeBackground">
      <textScroll :dataList="noticeList" />
    </div>
    <div class="leftBox">
      <!-- 房屋数量 -->
      <div class="houseNumber">
        <el-row>
          <el-col :span="24">
            <el-row class="titleBackground">
              <span>房屋数量</span>
              <span class="totalNumber">{{houseNumber+ "" |ellipsis}}</span>
            </el-row>
          </el-col>

        </el-row>

        <div style="width:100%;height:90%;float:left">
          <gridHouseStatistical :currentComunity="currentComunity" />
        </div>

      </div>
      <div class="personBox">
        <el-col :span="24">
          <el-row class="titleBackground">
            <span>人员数量</span>
            <span class="totalNumber">{{personNum+ "" |ellipsis}}</span>
          </el-row>
        </el-col>
        <!-- 网格人员数量、党员数量 -->
        <el-row class="gridPersonRow">
          <div class="outer-container">
            <div class="inner-container">
              <div class="gridPerson"
                   v-for="(data, index) in personnelDistribution"
                   :key="index">

                <div class="gridPersonRowDiv">
                  <el-row class="gridPersonRowDivGrid">
                    <span>{{data.gridName}}</span>
                  </el-row>
                  <el-row class="gridPersonRowDivNumber">
                    <el-col :span="12">
                      <div class="gridPersonRowDivNumberNumber"> {{data.permanentPer}}</div>
                      <div class="gridPersonRowDivNumberText">人</div>

                    </el-col>
                    <el-col :span="12">
                      <div class="gridPersonRowDivNumberNumber"> {{data.partyMember}}</div>
                      <div class="gridPersonRowDivNumberText">党员</div>

                    </el-col>

                  </el-row>

                </div>

              </div>
            </div>
          </div>
        </el-row>
      </div>

      <!-- 最近车辆活动数 -->
      <div class='vehicleflow'>
        <el-row style="margin-left: 7px;">
          <el-col :span="24">
            <el-row class="titleBackground">
              <span>车辆数量</span>
              <span class="totalNumber">{{vehicleflowNum+ "" |ellipsis}}</span>
            </el-row>
          </el-col>

        </el-row>

        <el-row class="vehicleflowTotal">
          <el-col :span="11"
                  class="parkingSpaceNumber">
            <span class="vehicleflowTotalDigital">{{carParking}}</span>
            <span class="vehicleflowTotalText">停车位数量</span>
          </el-col>
          <el-col :span="11"
                  class="dayCarNumber">
            <span class="vehicleflowTotalDigital">{{dayPeakTraffic}}</span>
            <span class="vehicleflowTotalText">日车流峰值</span>
          </el-col>
        </el-row>

      </div>

      <!-- 特殊人员 -->
      <div class="carePeople">
        <el-col :span="24"
                class="car-boxTitle">
          <span>特殊人员</span>
           <span>SPECIAL PERSONNEL</span>
        </el-col>

        <el-row class="specialIconRow">
          <div class="outer-container">
            <div class="inner-container">
              <div class="specialIconRowList"
                   v-for="(data, index) in specialNums"
                   :key="index">
                <el-col class="specialText"
                        :span="4"
                        v-if="youfuncton(index+1)===true">
                  <div class="specialTextDiv">
                    <div class="specialTextNumber">{{data.num}}</div>
                    <div class="specialTextName">{{data.name}}</div>
                  </div>

                </el-col>

                <el-col class="specialIcon"
                        v-if="youfuncton(index+1)===false"
                        :span="6">
                  <div class="specialTextNameDiv">
                    <el-row class="specialTextNameDivNumber">
                      <span>{{data.num}}</span>
                    </el-row>
                    <el-row class="specialTextNameDivName">
                      <span> {{data.name}}</span>
                    </el-row>

                  </div>
                </el-col>

              </div>
            </div>
          </div>
        </el-row>

      </div>
      <!-- 关爱人员 -->
      <div class="populationFlow">
        <el-col class="car-boxTitle">
          <span>关爱人员</span>
           <span>TAKE CARE OF PEOPLE</span>
        </el-col>

        <!-- 关爱人员 -->
        <el-row class="care_of_peopleRow">
          <div class="outer-container">
            <div class="inner-container">
              <div class="care_of_peopleRowList"
                   v-for="(data, index) in carePersonItem"
                   :key="index">

                <div class="care_of_peopleRowDiv">
                  <el-row class="care_of_peopleRowCareName">
                    <span>{{data.name}}</span>
                  </el-row>
                  <el-row class="care_of_peopleRowCareValue">
                    <span> {{data.value}}</span>
                  </el-row>

                </div>

              </div>
            </div>
          </div>
        </el-row>
        <!-- <div style="width:100%;height:130px;">
          <carePersonChart :currentComunity="currentComunity" />
        </div> -->
      </div>
    </div>
  </div>
</template>
<script>
// import cameraTreeLazy from '@/components/cameraTree/cameraTree_lazy'
import $ from "jquery";
import { mapGetters, mapState } from "vuex";
import occupancyRateChart from '@/components/echarts/main/occupancyRate'
import houseNatureChart from '@/components/echarts/main/houseNature'
import liveTypeStatisticsChart from '@/components/echarts/main/liveTypeStatistics'
import populationMobilityChart from '@/components/echarts/main/populationMobility'
import gridHouseStatistical from '@/components/echarts/main/gridHouseStatistical_New'
import realOrganizationChart from '@/components/echarts/main/realOrganization'
import carstatistical from '@/components/echarts/carStatistical/carstatistical'
import textScroll from '@/components/textScroll/index'
import carePersonChart from '@/components/echarts/main/carePerson'

import { getRealInfomation } from '@/api/homePage/realInfomation'
import App from '../../App.vue';
export default {
  name: "Index",
  components: { textScroll, carePersonChart, occupancyRateChart, houseNatureChart, liveTypeStatisticsChart, populationMobilityChart, gridHouseStatistical, realOrganizationChart },
  data () {
    return {
      noticeBackground: {
        backgroundImage:
          "url(" + require("@/assets/images/home/notice.png") + ")",

        backgroundRepeat: "repeat",

        backgroundSize: "100% 100%"
      },
      switchImg: require('@/assets/images/peopleinfo/katong.png'),
      houseNumber: 0,
      vehicleflowNum: 0,
      personNum: 0,
      noticeList: [
        '公益绘画课堂走进通济社区',
        '印媒提醒莫迪政府:印度不是中国 ',
        '北京地铁设口罩自助售货机',
        '商务部:美国打压特定中国企业'
      ],
      personnelDistribution: [],//人员分布 网格 常驻人口 党员
      carParking: 0,//停车位
      dayPeakTraffic: 0,//日车流峰值
      specialNums: [],
      carePersonItem: [],
      isOddNumber: true
    };
  },
  computed: {
    ...mapGetters(["currentComunity"]),
  },
  created () { },
  filters: {
    ellipsis (value) {
      if (!value) return ''
      if (value.length > 5) {
        return value.slice(0, 5) + ''
      }
      return value
    }
  },
  methods: {
    permanentPerFormat (row, col, cell) {
      return cell && cell.length > 0 ? cell + ' 人' : ''
    },
    partyMemberFormat (row, col, cell) {
      return cell && cell.length > 0 ? cell + ' 党员' : ''
    },
    resizeContainer () {
      this.centContainerHeight = (this.$refs.MainView.offsetHeight - 22 - 131 - 4) + 'px'
      console.log('收到resize事件')
    },
    shrinkage () {
      $(".leftBox").fadeToggle(1500);
      $(".rightBox").fadeToggle(1500);
      $(".notice").fadeToggle(1500);
    },
    getData () {
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      this.houseNumber = this.testData.shouye.renfangche.houseTotalNumber
      this.vehicleflowNum = this.testData.shouye.renfangche.vehicleflowNum
      this.personNum = this.testData.shouye.renfangche.personNum


      // getRealInfomation(communityId, communityChildId).then((res) => {
      //   if (res && res.data) {
      //     this.houseNumber = res.data.buildingCount
      //     this.vehicleflowNum = res.data.vehicleCount
      //     this.personNum = res.data.personnelCount
      //   }
      // })
    },

    youfuncton (number) {
      let res = true
      if (number % 2 == 1) {
        res = true
      }
      else { res = false }

      return res
    }
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        this.getData()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  mounted () {
    const the_page = this;
    this.specialNums = this.testData.shouye.specialNums
    // window.onresize = () => { return (() => { the_page.resizeContainer() })() };
    this.resizeContainer()
    this.$Notice.$on('resize', this.resizeContainer)
    this.getData()
    this.personnelDistribution = this.testData.renyuan.peopleHousingInfoList
    this.carParking = this.testData.shouye.renfangche.carParking
    this.dayPeakTraffic = this.testData.shouye.renfangche.dayPeakTraffic



    // this.carePersonItem = this.testData.shouye.carePerson

    this.carePersonItem.push({ name: this.testData.shouye.carePerson.oneNumTitle, value: this.testData.shouye.carePerson.oneNum })
    this.carePersonItem.push({ name: this.testData.shouye.carePerson.twoNumTitle, value: this.testData.shouye.carePerson.twoNum })
    this.carePersonItem.push({ name: this.testData.shouye.carePerson.threeNumTitle, value: this.testData.shouye.carePerson.threeNum })
    this.carePersonItem.push({ name: this.testData.shouye.carePerson.fourNumTitle, value: this.testData.shouye.carePerson.fourNum })

  }
}
</script>
<style lang="scss" scoped>
@import "~@/assets/styles/main/carepeople.css";
@import "~@/assets/styles/main/index.css";
.Main {
  width: 100%;
  height: 100%;
  background-color: transparent;
}

/* 左半侧 新增样式 */
.titleBackground {
  background: url("../../assets/images/index/房屋数量bg.png");
  filter: "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale')";
  -moz-background-size: 100% 100%;
  background-size: 100% 100%;
  padding-left: 30px;
  height: 30px;
  line-height: 30px;
}
.titleBackground span {
  font-size: 12px;
  color: white;
}
.totalNumber {
  color: white;
  font-size: 22px !important;
  line-height: 30px;
  font-family: DINCondensed-Bold;
  float: right;
  margin-right: 15px;
}

.gridPerson {
  background: url("../../assets/images/index/网格员.png");
  -moz-background-size: 100% 100%;
  background-size: 100% 100%;
  height: 99px;
  width: 101px;
  float: left;
  margin-top: 8px;
  margin-right: 8px;
}

.gridPersonRowDiv {
  height: 50px;
  margin-top: 52px;
  text-align: center;
}

.gridPersonRowDivGrid {
  height: 20px;
}

.gridPersonRowDivGrid span {
  color: white !important;
  font-size: 12px;
}

.gridPersonRowDivNumber {
  height: 30px;
  margin-top: 5px;
  text-align: center;
}

.gridPersonRowDivNumberNumber {
  font-size: 18px;
  font-family: DINCondensed-Bold;
  font-weight: bold;
  color: #4ef4ff;
  float: left;
  // margin-top: 5px;
  margin-left: 3px;
}

.gridPersonRowDivNumberText {
  font-size: 10px !important;
  -webkit-transform-origin-x: 0;
  -webkit-transform: scale(0.8);
  font-family: DINCondensed-Bold;
  color: #b7e2ee;
  margin-top: 5px;
  // margin-left: -8px;
  // margin-top: 5px;
}

.gridPersonRow {
  height: 85%;
  width: 100%;
}

.vehicleflowTotal {
  height: 50px;
  margin-top: 10px;
  width: 100%;
  text-align: center;
}

.parkingSpaceNumber {
  background: url("../../assets/images/index/停车位.png");
  -moz-background-size: 100% 100%;
  background-size: 100% 100%;
  height: 49px;
  width: 202px;
  margin-left: 10px;
  margin-right: 20px;
  line-height: 49px;
}

.vehicleflowTotalDigital {
  font-size: 24px;
  font-family: DINCondensed-Bold;
  font-weight: bold;
  color: #4ef4ff;
  margin-right: 5px;
  margin-left: -40px;
}

.vehicleflowTotalText {
  font-size: 14px;
  /* font-family: Source Han Sans CN; */
  font-weight: bold;
  color: #b8e2ee;
  line-height: 11px;
}
.dayCarNumber {
  background: url("../../assets/images/index/日车巅峰值.png");
  -moz-background-size: 100% 100%;
  background-size: 100% 100%;
  height: 49px;
  width: 202px;
  line-height: 49px;
}

.specialIconRow {
  width: 100%;
  height: 100%;
}

.specialIconRowList {
  float: left;
  height: 100%;
  margin-top: 5px;
  margin-left: 5px;
  margin-right: 5px;
  text-align: center;
}

.specialIcon {
  background: url("../../assets/images/index/首页特殊人员bg.png");
  -moz-background-size: 100% 100%;
  background-size: 100% 100%;
  height: 80px;
  width: 77px;
  margin-top: 10px;
  /* line-height: 49px; */
}

.specialText {
  height: 80px;
  width: 77px;
  background: url("../../assets/images/index/首页特殊人员bg.png");
  -moz-background-size: 100% 100%;
  background-size: 100% 100%;
  margin-top: 70px;
  position: relative;
}

.specialText .specialTextDiv {
  // position: absolute;
  // bottom: 0;
  /* height: 40px; */
  margin-top: 10px;
  text-align: center;
  padding-top: 3px;
}
.specialTextNumber {
  font-size: 24px;
  height: 20px;
  color: white;
  font-family: DINCondensed-Bold;
  font-weight: bold;
}

.specialTextName {
  font-size: 18px !important;
  -webkit-transform-origin-x: 0;
  -webkit-transform: scale(0.8);
  height: 28px;
  width: 116%;
  margin-left: 3px;
  color: white;
}

.specialTextNameDiv {
  height: 50px;
  text-align: center;
  margin-top: 10px;
}

.specialTextNameDivNumber {
  font-size: 22px;
  height: 24px;
  color: white;
  font-family: DINCondensed-Bold;
  font-weight: bold;
}

.specialTextNameDivName {
  font-size: 18px !important;
  -webkit-transform-origin-x: 0;
  -webkit-transform: scale(0.8);
  height: 23px;
  color: white;
  width: 100%;
  margin-left: 7px;
}

.care_of_peopleRow {
  height: 85%;
  width: 100%;
}

.care_of_peopleRowList {
  background: url("../../assets/images/index/关爱人员.png");
  -moz-background-size: 100% 100%;
  background-size: 100% 100%;
  height: 191px;
  width: 122px;
  float: left;
  margin-top: 30px;
  // margin-right: 30px;
  margin-left: 30px;
}

.care_of_peopleRowDiv {
  text-align: center;
}

.care_of_peopleRowCareName {
  background: url("../../assets/images/index/关爱人员1.png");
  -moz-background-size: 100% 100%;
  background-size: 100% 100%;
  height: 35px;
  line-height: 35px;
  font-size: 14px;
  color: #a6cceb;
  text-align: center;
  text-shadow: 0px 2px 2px rgba(20, 56, 83, 0.32);
}
.care_of_peopleRowCareValue {
  color: #264e6d;
  font-family: DINCondensed-Bold;
  font-weight: bold;
  font-size: 19px;
  text-align: center;
  margin-top: 20px;
  background: linear-gradient(
    3deg,
    #fed6a0 0%,
    #fdb03c 24.31640625%,
    #fdb03c 42.1630859375%,
    #fffcf3 42.1630859375%,
    #fffcf3 61.4013671875%,
    #feddb2 99.31640625%
  );
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}
</style>
