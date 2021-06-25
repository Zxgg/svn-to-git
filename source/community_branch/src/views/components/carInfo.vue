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
    <div class="mainContent">
      <!-- 左侧盒子 -->
      <div>
        <!-- 小区车辆统计 -->
        <div class="communityVehicleStatistics">
          <div class="boxTitle">
            <span></span>
            <span>小区车辆统计</span>
          </div>
          <div class="carCrossBox">
            <div v-for="(item,index) in communityVehicleStatisticsData"
                 class="carCross">
              <span><img :src="item.pic"
                     alt=""></span>
              <span>{{item.name}}</span>
              <span>{{item.num}}</span>
            </div>
          </div>
        </div>
        <!-- 小区车辆出入时间段统计 -->
        <div class="communityVehicleStatisticsTime">
          <div class="boxTitle">
            <span></span>
            <span>小区车辆出入时间段统计</span>
          </div>
          <div>
            <statisticsOfVehiclesCharts />
          </div>
        </div>
        <!-- 活跃车辆 -->
        <div class="activeVehicle">
          <div class="boxTitle">
            <span></span>
            <span>活跃车辆</span>
          </div>
          <div>
            <activeVehicleCharts />
          </div>
        </div>
      </div>
      <!-- 右侧盒子 -->
      <div>
        <!-- 车辆类别 -->
        <div class="vehicleCategory">
          <div class="boxTitle">
            <span></span>
            <span>车辆类别</span>
          </div>
          <div>
            <vehicleCategoryCharts />
          </div>
        </div>
        <!-- 管控车辆 -->
        <div class="controlOfTheVehicle">
          <div class="boxTitle">
            <span></span>
            <span>管控车辆</span>
          </div>
          <div>
            <div class="controlOfTheVehicleBox">
              <el-row :gutter="5">
                <el-col :span="8"
                        v-for="(item,index) in controlOfTheVehicleData"
                        style="margin-top: 5px;">
                  <div class="controlOfTheVehicleBoxInside">
                    <div class="controlOfTheVehicleBoxInsidePic">
                      <img :src="item.pic"
                           alt="">
                    </div>
                    <div class="controlOfTheVehicleBoxInsideCarNum">
                      <span>车牌号:</span>
                      <span>{{item.carNum}}</span>
                    </div>
                    <div class="controlOfTheVehicleBoxInsideAlarm">{{item.carAlarm}}</div>
                    <div class="controlOfTheVehicleBoxInsideDetail">
                      <div>详情</div>
                    </div>
                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import $ from "jquery";
import statisticsOfVehiclesCharts from '@/components/echarts/carInfo/statisticsOfVehicles'
import activeVehicleCharts from '@/components/echarts/carInfo/activeVehicle'
import vehicleCategoryCharts from '@/components/echarts/carInfo/vehicleCategory'
export default {
  name: "Index",
  components: { statisticsOfVehiclesCharts, activeVehicleCharts, vehicleCategoryCharts },
  data () {
    return {
      switchImg: require('@/assets/images/peopleinfo/katong.png'),
      pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg",
      communityVehicleStatisticsData: [
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", name: "天通苑小区", num: "12辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", name: "利民小区", num: "2辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", name: "花家地小区", num: "452辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", name: "万达小区", num: "62辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", name: "天通苑小区", num: "12辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", name: "利民小区", num: "2辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", name: "花家地小区", num: "452辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", name: "万达小区", num: "62辆" },
      ],
      controlOfTheVehicleData: [
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", carNum: "冀FR339B", carAlarm: "多次违法未处理车辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", carNum: "冀FR339B", carAlarm: "多次违法未处理车辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", carNum: "冀FR339B", carAlarm: "多次违法未处理车辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", carNum: "冀FR339B", carAlarm: "多次违法未处理车辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", carNum: "冀FR339B", carAlarm: "多次违法未处理车辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", carNum: "冀FR339B", carAlarm: "多次违法未处理车辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", carNum: "冀FR339B", carAlarm: "多次违法未处理车辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", carNum: "冀FR339B", carAlarm: "多次违法未处理车辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", carNum: "冀FR339B", carAlarm: "多次违法未处理车辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", carNum: "冀FR339B", carAlarm: "多次违法未处理车辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", carNum: "冀FR339B", carAlarm: "多次违法未处理车辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", carNum: "冀FR339B", carAlarm: "多次违法未处理车辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", carNum: "冀FR339B", carAlarm: "多次违法未处理车辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", carNum: "冀FR339B", carAlarm: "多次违法未处理车辆" },
        { pic: "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3959736001,3050893089&fm=26&gp=0.jpg", carNum: "冀FR339B", carAlarm: "多次违法未处理车辆" },
      ]
    };
  },
  created () { },
  mounted () { },
  methods: {
    shrinkage () {
      $(".mainContent > div:first-child").fadeToggle(2000);
      $(".mainContent > div:last-child").fadeToggle(2000);
      this.$Notice.$emit('shrinkageHide', 'carInfo')
    }
  }
};
</script>

<style scoped>
@import "~@/assets/styles/main/carInfo.css";
</style>
