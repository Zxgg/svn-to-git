<template>
  <div style="width:100%;height:100%">
    <div>
      <el-tooltip class="item"
                  effect="dark"
                  content="隐藏显示"
                  placement="top">
        <span @click="shrinkage()"
              class="switchImg el-icon-view"></span>
      </el-tooltip>
    </div>
    <!-- 左侧盒子 -->
    <div class="carLeftBox">
      <!-- 车辆分布 -->
      <div class="communityVehicleStatistics">
        <div class="car-boxTitle">
          <span></span>
          <span>车辆分布</span>
        </div>
        <el-table :data="communityVehicleStatisticsData">
          <el-table-column prop="name"
                           label="所属网格">
          </el-table-column>
          <!-- <el-table-column prop="outnum"
                           label="外来车辆">
          </el-table-column> -->
          <el-table-column prop="insideNum"
                           label="登记车辆">
          </el-table-column>
          <el-table-column prop="truckSpace"
                           label="车位数">
          </el-table-column>
        </el-table>
        <!-- <div class="carCrossBox">
          <div v-for="(item,index) in communityVehicleStatisticsData"
               class="carCross">
            <span></span>
            <span>{{item.name}}</span>
            <span>{{item.num}}</span>
          </div>
        </div> -->
      </div>
      <!-- 车辆使用年限 -->
      <!-- <div class="vehicleYears">
        <div class="car-boxTitle">
          <span></span>
          <span>车辆入驻时间</span>
        </div>
        <div style="width:100%;height:100%">
          <vehicleYearsCharts :currentComunity="currentComunity" />
        </div>
      </div>
      <!-- 访客车辆类型统计 -->
      <!-- <div class="activeVehicle">
        <div class="car-boxTitle">
          <span></span>
          <span>访客车辆类型统计</span>
        </div>
        <div style="width:100%;height:100%">
          <activeVehicleCharts :currentComunity="currentComunity" />
        </div>
      </div> -->

      <!--车辆搜索-->
      <div class='caresearch'>
        <div class="care-boxTitle">
          <span></span>
          <span>车辆信息搜索</span>
        </div>
        <search />
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import { mapGetters, mapState } from "vuex";
import activeVehicleCharts from '@/components/echarts/carInfo/activeVehicle'
import vehicleYearsCharts from '@/components/echarts/carInfo/vehicleYears'
import { getCommunityVehicle } from '@/api/vehicle/communityVehicle'
import search from '@/components/carInfo/search'
export default {
  name: "Index",
  components: { activeVehicleCharts, vehicleYearsCharts, search },
  data () {
    return {
      communityVehicleStatisticsData: []
    };
  },
  computed: {
    ...mapGetters(["currentComunity"]),
  },
  mounted () {
    // this.getcomcarApi()
    this.communityVehicleStatisticsData = this.testData.car.communityVehicleStatisticsData
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        // this.getcomcarApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  methods: {
    shrinkage () {
      $(".carLeftBox").fadeToggle(2000);
      $(".carRightBox").fadeToggle(2000);
      this.$Notice.$emit('shrinkageHide', 'carInfo')
    },
    getcomcarApi () {
      this.communityVehicleStatisticsData = []
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getCommunityVehicle(communityId, communityChildId).then(res => {
        // console.log(res, 'res...')
        if (res && res.data.length > 0) {
          this.communityVehicleStatisticsData = res.data
          console.log('communityVehicleStatisticsData:', this.communityVehicleStatisticsData)
        }
      })
    }
  }
}
</script>

<style scoped>
@import "~@/assets/styles/main/carInfo.css";
</style>