<template>
  <div style="width:100%;height:100%">
    <!-- <div>
      <el-tooltip class="item"
                  effect="dark"
                  content="隐藏显示"
                  placement="top">
        <span @click="shrinkage()"
              class="switchImg el-icon-view"></span>
      </el-tooltip>
    </div> -->
    <!-- 左侧盒子 -->
    <div class="carLeftBox">
      <!-- 车辆分布 -->
      <div class="communityVehicleStatistics">
        <div class="car-boxTitle">
          <!-- <span></span> -->
          <span>车辆分布</span>
          <span>VEHICLE DISTRIBUTION</span>
        </div>
        <div class="cheliangfenbuDiv">
          <el-table :data="communityVehicleStatisticsData"
                    class="cheliangfenbu"
                    :row-style="{height: '40px'}"
                    :cell-style="{padding: '0'}"
                    height="90%">
            <el-table-column prop="name"
                             align="center"
                             label="所属网格">
            </el-table-column>
            <!-- <el-table-column prop="outnum"
                             align="center"
                             label="外来车辆">
            </el-table-column> -->
            <el-table-column prop="insideNum"
                             align="center"
                             label="登记车辆">
            </el-table-column>
            <el-table-column prop="truckSpace"
                             align="center"
                             label="车位数">
            </el-table-column>
          </el-table>
        </div>
      </div>

      <!--车辆搜索-->
      <div class='carsearch'>
        <div class="car-boxTitleM">
          <span>车辆信息搜索</span>
          <span>VEHICLE INFORMATION SEARCH</span>
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
import search from './search'
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

<style scoped="true">
@import "~@/assets/styles/main/carInfo.css";
</style>