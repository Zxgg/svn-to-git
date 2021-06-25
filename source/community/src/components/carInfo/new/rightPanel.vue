<template>
  <!-- 右侧盒子 -->
  <div class="carLeftBox">
    <!-- 车辆类别 -->
    <div class="vehicleCategory">
      <div class="car-boxTitleM">
        <span>车辆进出口流量统计</span>
        <span>VEHICLE IMPORT AND EXPORT FLOW</span>
      </div>
      <el-row>
        <el-col :span="12">
          <div style="width:100%;height:180px">
            <vehicleIOFlow :currentComunity="currentComunity"
                           :xData="vehicleCategoryIn"
                           :labels="'车辆进口'" />
          </div>
        </el-col>
        <el-col :span="12">
          <div style="width:100%;height:180px">
            <vehicleIOFlow :currentComunity="currentComunity"
                           :xData="vehicleCategoryOut"
                           :labels="'车辆出口'" />
          </div>
        </el-col>
      </el-row>
    </div>

    <!-- 小区车辆出入时间段统计 -->
    <div class="communityVehicleStatisticsTime">
      <div class="car-boxTitleM">
        <span>车辆出入时间段统计</span>
        <span>VEHICLE ACCESS TIME STATISTICS</span>
      </div>
      <div style="width:100%;height:100%">
        <!-- <statisticsOfVehiclesCharts :currentComunity="currentComunity" /> -->
        <IODoubleLine :data="vehicleFlowInfo"
                      :height="'200px'" />
      </div>
    </div>
    <!-- 管控车辆 -->
    <div class="controlOfTheVehicle">
      <div class="car-boxTitle">
        <span>布控车辆</span>
        <span>CONTROL OF VEHICLES</span>
      </div>
      <div class="controlOfTheVehicleBox">
        <el-carousel :interval="3000"
                     type="card"
                     indicator-position="none"
                     class="controlOfTheVehicleBox-carousel">
          <el-carousel-item v-for="(item,index) in controlOfTheVehicleData"
                            :key="index">
            <div class="controlOfTheVehicle-box"
                 @click="getDetail(item)">
              <el-col class="controlOfTheVehicle-img-div"
                      :span="12">
                <img class="controlOfTheVehicle-img"
                     :src="item.limitPic">
              </el-col>
              <el-col :span="12"
                      class="controlOfTheVehicle-name-div">
                <div>
                  <span class="controlOfTheVehicle-name">车牌: </span>
                  <span class="controlOfTheVehicle-name">{{item.plate}}</span>
                </div>
                <div>
                  <span class="controlOfTheVehicle-name">车主: </span>
                  <span class="controlOfTheVehicle-name">{{item.ownerName}}</span>
                </div>
                <div>
                  <span class="controlOfTheVehicle-name">联系方式</span>
                </div>
                <div>
                  <span class="controlOfTheVehicle-name">{{item.phone}}</span>
                </div>
              </el-col>
            </div>
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>
    <!-- 车辆入驻时间 -->
    <div class="vehicleEnergy">
      <div class="car-boxTitleM">
        <span>车辆入驻时间</span>
        <span>VEHICLE ENTRY TIME</span>
      </div>
      <div style="width:100%;height:100%">
        <!-- <vehicleYearsCharts :currentComunity="currentComunity" /> -->
        <carYears />
      </div>
    </div>

    <el-dialog :visible.sync="open"
               width="45%"
               append-to-body
               :show-close="false"
               class="dialogCarClass">
      <div class="dialog_title">
        <el-col :span="23">{{title}}</el-col>
        <el-col :span="1"><span @click="open = false">{{"×"}}</span></el-col>
      </div>
      <div style="width:100%;height:100%">
        <el-col :span="10">
          <div class="dialogCarClass_pic">
            <img style="width:94%;height:92%;margin:3%"
                 :src="form.limitPic">
          </div>
        </el-col>
        <el-col :span="13">
          <div class="dialogCarClass_info">
            <el-row class="dialogCarClass_info_1">
              <el-form ref="form"
                       :model="form"
                       label-width="100px">

                <div class="el_form_itemDiv">
                  <el-form-item label="车牌号:"
                                prop="plate">
                    <span class="addr">{{ form.plate || '暂无'}}</span>
                  </el-form-item>
                  <el-form-item label="颜色:"
                                prop="zipinpai">
                    <span class="addr">{{ form.carColor || '暂无'}}</span>
                  </el-form-item>

                </div>
                <div class="el_form_itemDiv">
                  <el-form-item label="车位号:"
                                prop="pinpai">
                    <span class="addr">{{ form.parkingLot || '暂无'}}</span>
                  </el-form-item>
                  <el-form-item label="所属网格:"
                                prop="carType">
                    <span class="addr">{{ form.wg || '暂无'}}</span>
                  </el-form-item>
                </div>

              </el-form>
            </el-row>

            <el-row class="dialogCarClass_info_2">
              <el-form ref="form"
                       :model="form"
                       label-width="100px">

                <div class="el_form_itemDiv">
                  <el-form-item label="车主:"
                                prop="name">
                    <span class="addr">{{ form.ownerName || '暂无'}}</span>
                  </el-form-item>
                  <el-form-item label="性别:"
                                prop="sex">
                    <span class="addr">{{ form.sex || '暂无'}}</span>
                  </el-form-item>
                </div>
                <div class="el_form_itemDiv">
                  <el-form-item label="年龄:"
                                prop="age">
                    <span class="addr">{{ form.age || '暂无'}}</span>
                  </el-form-item>
                  <el-form-item label="联系方式:"
                                prop="phone"
                                style="width:200px;">
                    <span class="addr">{{ form.phone || '暂无'}}</span>
                  </el-form-item>
                </div>

                <div class="el_form_itemDiv">
                  <el-form-item label="籍贯:"
                                prop="jiguan">
                    <span class="addr">{{ form.jiguan || '暂无'}}</span>
                  </el-form-item>
                  <el-form-item label="身份证:"
                                prop="cardID"
                                style="width:250px;">
                    <span class="addr">{{ form.cardID || '暂无' | ellipsis }}</span>
                  </el-form-item>
                </div>

                <div class="el_form_itemDiv">
                  <el-form-item label="房屋地址:"
                                class="addressform"
                                prop="fangwudizhi">
                    <span class="address">{{ form.fangwudizhi|| '暂无' }}</span>
                  </el-form-item>
                </div>

              </el-form>
            </el-row>
          </div>
        </el-col>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
// import statisticsOfVehiclesCharts from '@/components/echarts/carInfo/statisticsOfVehicles'
// import doubleLine from '@/components/echarts/carInfo/double_Line'
// import vehicleCategoryCharts from '@/components/echarts/carInfo/vehicleCategory'
import vehicleIOFlow from "@/components/echarts/carInfo/new/vehicleIOFlow"
import IODoubleLine from "@/components/echarts/carInfo/new/IODoubleLine"
// import vehicleEnergyTypeCharts from '@/components/echarts/carInfo/vehicleEnergyType'
// import vehicleYearsCharts from '@/components/echarts/carInfo/vehicleYears'
import carYears from './carYears'
import { getcontrolVehicle } from '@/api/vehicle/controlvehicle'
export default {
  components: { vehicleIOFlow, IODoubleLine, carYears },
  data () {
    return {
      content_background_image: {
        backgroundImage: "url(" + require("../../../assets/images/car/content.png") + ")",
        backgroundRepeat: "no-repeat",
        backgroundSize: "420px 285px",
        marginTop: "0px",
        paddingTop: "25px"
      },
      controlOfTheVehicleData: [],
      form: {},
      open: false,
      vehicleCategoryIn: [],
      vehicleCategoryOut: [],
      title: '',
      vehicleFlowInfo: null
    };
  },
  computed: {
    ...mapGetters(["currentComunity"]),
  },
  filters: {
    ellipsis (value) {
      if (!value) return ''
      if (value.length > 13) {
        return value.slice(0, 6) + '******' + value.slice(-4)
      }
      return value
    }
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        // this.getcontrolsvehicleApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  mounted () {
    this.controlOfTheVehicleData = this.testData.car.controlOfTheVehicleData
    this.vehicleCategoryIn = this.testData.car.vehicleCategoryIn
    this.vehicleCategoryOut = this.testData.car.vehicleCategoryOut
    // this.getcontrolsvehicleApi()
    this.vehicleFlowInfo = this.testData.car.vehicleFlowInfo
  },
  methods: {
    getcontrolsvehicleApi () {
      this.controlOfTheVehicleData = []
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getcontrolVehicle(communityId, communityChildId).then(res => {
        console.log(res, 'control')
        if (res && res.data.length > 0) {
          this.controlOfTheVehicleData = res.data
        }
      })
    },
    getDetail (info) {
      // debugger
      this.form = info
      this.title = '布控车辆信息'
      this.open = true
    }
  }
}
</script>

<style scoped="true">
@import "~@/assets/styles/main/carInfo.css";

.car_backage {
  margin-top: 10%;
  padding-bottom: 5%;
  background-image: url(../../../assets/images/car/cae_backage_pop.png);
  background-repeat: no-repeat;
  background-size: 200px 220px;
}

.phone_class .el-form-item__content {
  width: 100px;
}

.el-form-item__label {
  padding: 0 5px 0 0 !important;
}

.cutHeight {
  margin-top: -5px;
}
</style>
