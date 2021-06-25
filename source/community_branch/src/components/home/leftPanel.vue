<template>
  <div class="Main"
       ref="MainView">
    <el-tooltip class="item"
                effect="dark"
                content="隐藏显示"
                placement="top">
      <span @click="shrinkage()"
            class="switchImg el-icon-view"></span>
    </el-tooltip>
    <!-- <div class="notice"
         v-if="false"
         :style="noticeBackground">
      <textScroll :dataList="noticeList" />
    </div> -->
    <div class="leftBox">
      <div class="houseNumber">
        <!-- 房屋数量 -->
        <span class="iconfont iconfangwu2 houseIcon">&#xe705;</span>
        <span class="icon iconfont">房屋</span>
        <!-- <div class="lineOne"></div>
        <div class="lineTwo"></div> -->
        <!-- <div class="text">{{houseNumber+ "" |ellipsis}}</div> -->
        <!-- <div class="lineThree"></div>
        <div class="lineFour"></div> -->
        <!-- <span></span>
          <span>{{houseNumber}}</span> -->
        <!-- <div style="width:50%;height:90%;float:left"> -->
        <div style="width:100%;height:90%;float:left">
          <!-- <occupancyRateChart :currentComunity="currentComunity" /> -->
          <gridHouseStatistical :currentComunity="currentComunity" />
        </div>
        <!-- <div style="width:50%;height:90%;float:right">
          <houseNatureChart :currentComunity="currentComunity" />
        </div> -->
        <!-- 增加入住率统计 -->
        <!-- <div class='occpancy'>
          <div class="occupancyTitle">
            <span></span>
            <span>入住率统计展示</span>
          </div>
          <occupancy />
        </div> -->
      </div>
      <div class="personBox">
        <span class="iconfont iconfangwu2 houseIcon">&#xe700;</span>
        <span class="icon iconfont">人员</span>
        <div class="lineOne"></div>
        <div class="lineTwo"></div>
        <div class="text">{{personNum+ "" |ellipsis}}</div>
        <div class="lineThree"></div>
        <div class="lineFour"></div>
        <!-- <span></span>
          <span>{{houseNumber}}</span> -->
        <!-- <div style="width:50%;height:95%;float:left">
          <liveTypeStatisticsChart :currentComunity="currentComunity" />
        </div>
        <div style="width:50%;height:95%;float:right">
          <populationMobilityChart :currentComunity="currentComunity" />
        </div> -->
        <div style="width:99%;height:95%;float:left"
             class="botList">
          <el-table :data="personnelDistribution"
                    height="100%"
                    :show-header="false"
                    :header-cell-style="{background:'#182751',color:'#606266'}"
                    :cell-style="{background:'#182751',color:'#fff'}">
            <el-table-column prop="gridName"
                             label="网格"
                             width="220px"
                             align="center">
            </el-table-column>

            <el-table-column prop="permanentPer"
                             label="常驻人口"
                             :formatter="permanentPerFormat">
            </el-table-column>
            <el-table-column prop="partyMember"
                             label="党员"
                             :formatter="partyMemberFormat">
            </el-table-column>
          </el-table>
        </div>
      </div>
      <!-- 最近车辆活动数 -->
      <div class='vehicleflow'>
        <div class="vehicleflowTitle">
          <span class="iconfont iconfangwu2 houseIcon">&#xe702;</span>
          <span class="icon iconfont">车辆</span>
          <div class="lineOne"></div>
          <div class="lineTwo"></div>
          <div class="text">{{vehicleflowNum+ "" |ellipsis}}</div>
          <div class="lineThree"></div>
          <div class="lineFour"></div>
        </div>
        <!-- <carstatistical :currentComunity="currentComunity"></carstatistical> -->
        <div>
          <div class="trafficStatisticsData">
            <span style="margin-top: 8px;margin-left: 112px;">
              <div>{{carParking}}</div>
              <div>停车位</div>
            </span>
            <span style="width:auto;">
              <div style="width:auto;">日车流峰值</div>
            </span>
            <span style="width:auto;font-size: 24px;margin-left: 18px;">
              <div style="width:auto;font-size: 24px;">{{dayPeakTraffic}}</div>
              <div />
            </span>
          </div>
        </div>
      </div>
      <!-- 特殊人员 -->
      <!-- 特殊人员 -->
      <div class="carePeople">
        <div class="rightTitle">
          <span></span>
          <span>特殊人员</span>
        </div>
        <div class="specialPer">
          <ul class="specialPerLBox">
            <li v-for="(item,index) in specialNums"
                :key="index"
                class="specialPerLi">
              <span>{{item.name}}</span>
              <span>{{item.num}}</span>
            </li>
          </ul>
        </div>
      </div>
      <!-- 关爱人员 -->
      <div class="populationFlow">
        <div class="rightTitle">
          <span></span>
          <span>关爱人员</span>
        </div>
        <div style="width:100%;height:130px;">
          <carePersonChart :currentComunity="currentComunity" />
        </div>
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
import gridHouseStatistical from '@/components/echarts/main/gridHouseStatistical'
import realOrganizationChart from '@/components/echarts/main/realOrganization'
import carstatistical from '@/components/echarts/carStatistical/carstatistical'
import textScroll from '@/components/textScroll/index'
import carePersonChart from '@/components/echarts/main/carePerson'

import { getRealInfomation } from '@/api/homePage/realInfomation'
export default {
  name: "Index",
  components: { textScroll, carePersonChart, occupancyRateChart, houseNatureChart, liveTypeStatisticsChart, populationMobilityChart, gridHouseStatistical, realOrganizationChart, carstatistical },
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
    };
  },
  computed: {
    ...mapGetters(["currentComunity"]),
  },
  created () { },
  filters: {
    ellipsis (value) {
      if (!value) return ''
      if (value.length > 4) {
        return value.slice(0, 4) + ''
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
</style>