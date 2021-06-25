<template>
  <div>
    <div>
      <el-tooltip class="item"
                  effect="dark"
                  content="隐藏显示"
                  placement="top">
        <span @click="shrinkage()"
              class="switchImg el-icon-view"></span>
      </el-tooltip>
    </div>
    <!-- 顶部中间盒子 -->
    <div class="topCenterBox">
      <span :style="topCenterPic">
        <div>{{topCenterData.todayVisitor}}</div>
        <div>今日访客</div>
      </span>
      <span :style="topCenterPic">
        <div>{{topCenterData.thisMontnVisitor}}</div>
        <div>本月访客</div>
      </span>
      <span :style="topCenterPic">
        <div>{{topCenterData.totalVisitor}}</div>
        <div>累计访客</div>
      </span>
      <span :style="topCenterPic">
        <div>{{topCenterData.appointmentVisitor}}</div>
        <div>预约访客</div>
      </span>
      <span :style="topCenterPic">
        <div>{{topCenterData.temporaryVisitor}}</div>
        <div>临时访客</div>
      </span>
      <span :style="topCenterPic">
        <div>{{topCenterData.stranger}}</div>
        <div>陌生人</div>
      </span>
    </div>
    <div class="mainContent">
      <!-- 左侧盒子 -->
      <div>
        <!-- 访客数量分布 -->
        <div class="numberOfVisitors">
          <div class="boxTitle">
            <span></span>
            <span>访客数量分布</span>
          </div>
          <div>
            <distributionVisitorsChart />
          </div>
        </div>
        <!-- 访客类别 -->
        <div class="visitorCategory">
          <div class="boxTitle">
            <span></span>
            <span>访客类别</span>
          </div>
          <div>
            <visitorCategoriesChart />
          </div>
        </div>
        <!-- 车辆统计 -->
        <div class="trafficStatistics">
          <div class="boxTitle">
            <span></span>
            <span>通行数量</span>
          </div>
          <div class="trafficStatisticsData">
            <span>
              <div>{{trafficStatisticsData.personNum}}</div>
              <div>人员通行</div>
            </span>
            <span>
              <div>{{trafficStatisticsData.vehicleNum}}</div>
              <div>车辆通行</div>
            </span>
            <span>
              <div>{{trafficStatisticsData.visitorNum}}</div>
              <div>访客通行</div>
            </span>
          </div>
        </div>
        <!-- 异常统计 -->
        <div class="abnormalStatistics">
          <div class="boxTitle">
            <span></span>
            <span>人车通行统计</span>
          </div>
          <div>
            <!-- <abnormalStatisticsChart /> -->
            <currentStatisticsChart />
          </div>
        </div>
      </div>
      <!-- 右侧盒子 -->
      <div>
        <!-- 车辆监控视频 -->
        <div class="peopleSurveillance">
          <div class="boxTitle">
            <span></span>
            <span>人员通行抓拍</span>
          </div>
          <div class="vehicleVideoAndImage">
            <div class="outer-container">
              <div class="inner-container">
                <el-row class="vehicleVideoAndImage_image">
                  <el-col class="vehicleVideoAndImage_image_first"
                          v-for="(item,index) in peopleDetailsData"
                          :key="index">
                    <img class="vehicleImage"
                         :src="item.img"></img>
                    <el-row>
                      <el-col>
                        <span class="vehiclePlate">访客类型：</span>
                        <span class="vehiclePlate"
                              style="margin:0">{{item.visitorType}}</span>
                      </el-col>
                      <el-col>
                        <span class="vehicleDetails">地点：</span>
                        <span class="vehicleDetails"
                              style="margin:0"
                              :title="item.address">{{item.address|ellipsis}}</span>
                      </el-col>
                      <el-col>
                        <span class="vehicleDetails">入场时间：</span>
                        <span class="vehicleDetails"
                              style="margin:0"
                              :title="item.admissionTime">{{item.admissionTime|ellipsis}}</span>
                      </el-col>
                    </el-row>
                  </el-col>
                </el-row>
              </div>
            </div>
          </div>
        </div>
        <div class="vehicleSurveillance">
          <div class="boxTitle">
            <span></span>
            <span>车辆通行抓拍</span>
          </div>
          <div class="vehicleVideoAndImage">
            <div class="outer-container">
              <div class="inner-container">
                <el-row class="vehicleVideoAndImage_image">
                  <el-col class="vehicleVideoAndImage_image_first"
                          v-for="(item,index) in vehicleDetailsData"
                          :key="index">
                    <img class="vehicleImage"
                         :src="item.img"></img>
                    <el-row>
                      <el-col>
                        <span class="vehiclePlate">车牌：</span>
                        <span class="vehiclePlate"
                              style="margin:0">{{item.plate}}</span>
                      </el-col>
                      <el-col>
                        <span class="vehicleDetails">通道名称：</span>
                        <span class="vehicleDetails"
                              style="margin:0"
                              :title="item.passagewayName">{{item.passagewayName|ellipsis}}</span>
                      </el-col>
                      <el-col>
                        <span class="vehicleDetails">入场时间：</span>
                        <span class="vehicleDetails"
                              style="margin:0"
                              :title="item.admissionTime">{{item.admissionTime|ellipsis}}</span>
                      </el-col>
                    </el-row>
                  </el-col>
                </el-row>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import $ from "jquery";
import videojs from "video.js";
import visitorCategoriesChart from '@/components/echarts/trafficData/visitorCategories'
import distributionVisitorsChart from '@/components/echarts/trafficData/distributionVisitors'
import abnormalStatisticsChart from '@/components/echarts/trafficData/abnormalStatistics'
import currentStatisticsChart from '@/components/echarts/trafficData/currentStatistics'
import { gettrafficdata } from '@/api/trafficData/trafficNum'
export default {
  name: "Index",
  components: { visitorCategoriesChart, distributionVisitorsChart, abnormalStatisticsChart, currentStatisticsChart },
  filters: {
    ellipsis (value) {
      if (!value) return ''
      if (value.length > 5) {
        return value.slice(0, 5) + '...'
      }
      return value
    }
  },
  data () {
    return {
      switchImg: require('@/assets/images/peopleinfo/katong.png'),
      topCenterPic: {
        backgroundImage:
          "url(" + require("@/assets/images/home/dataBackground.png") + ")",

        backgroundRepeat: "repeat",

        backgroundSize: "100% 100%"
      },
      topCenterData: {
        todayVisitor: 5,
        thisMontnVisitor: 12,
        totalVisitor: 20,
        appointmentVisitor: 6,
        temporaryVisitor: 8,
        stranger: 6
      },
      trafficStatisticsData: {},
      vehicleDetailsData: [{
        img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg',
        plate: '苏k.000001',
        passagewayName: '东门出口车道2',
        admissionTime: '2020-11-23 09-52-05'
      }, {
        img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg',
        plate: '苏k.000001',
        passagewayName: '东门出口车道2',
        admissionTime: '2020-11-23 09-52-05'
      }, {
        img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg',
        plate: '苏k.000001',
        passagewayName: '东门出口车道2',
        admissionTime: '2020-11-23 09-52-05'
      }, {
        img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg',
        plate: '苏k.000001',
        passagewayName: '东门出口车道2',
        admissionTime: '2020-11-23 09-52-05'
      }, {
        img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg',
        plate: '苏k.000001',
        passagewayName: '东门出口车道2',
        admissionTime: '2020-11-23 09-52-05'
      }, {
        img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg',
        plate: '苏k.000001',
        passagewayName: '东门出口车道2',
        admissionTime: '2020-11-23 09-52-05'
      }, {
        img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg',
        plate: '苏k.000001',
        passagewayName: '东门出口车道2',
        admissionTime: '2020-11-23 09-52-05'
      },],
      peopleDetailsData: [{
        img: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3135215632,750931275&fm=26&gp=0.jpg',
        visitorType: '陌生人员',
        address: '某某社区',
        admissionTime: '2020-11-23 09-52-05'
      }, {
        img: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3135215632,750931275&fm=26&gp=0.jpg',
        visitorType: '预约人员',
        address: '某某社区',
        admissionTime: '2020-11-23 09-52-05'
      }, {
        img: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3135215632,750931275&fm=26&gp=0.jpg',
        visitorType: '临时人员',
        address: '某某社区',
        admissionTime: '2020-11-23 09-52-05'
      }, {
        img: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3135215632,750931275&fm=26&gp=0.jpg',
        visitorType: '临时人员',
        address: '某某社区',
        admissionTime: '2020-11-23 09-52-05'
      }, {
        img: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3135215632,750931275&fm=26&gp=0.jpg',
        visitorType: '陌生人员',
        address: '某某社区',
        admissionTime: '2020-11-23 09-52-05'
      }, {
        img: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3135215632,750931275&fm=26&gp=0.jpg',
        visitorType: '陌生人员',
        address: '某某社区',
        admissionTime: '2020-11-23 09-52-05'
      }, {
        img: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3135215632,750931275&fm=26&gp=0.jpg',
        visitorType: '陌生人员',
        address: '某某社区',
        admissionTime: '2020-11-23 09-52-05'
      },],
    };
  },
  created () { },
  mounted () { this.gettrafficApi() },
  methods: {
    shrinkage () {
      $(".mainContent > div:first-child").fadeToggle(2000);
      $(".mainContent > div:last-child").fadeToggle(2000);
      $(".topCenterBox").fadeToggle(2000);
      this.$Notice.$emit('shrinkageHide', 'trafficData')
    },
    gettrafficApi () {
      gettrafficdata().then(res => {
        // console.log(res, 'trafficdata')
        if (res.data != null) {
          this.trafficStatisticsData = res.data
        }
      })
    }
  }
};
</script>

<style scoped="true">
@import "~@/assets/styles/main/trafficData.css";
</style>
