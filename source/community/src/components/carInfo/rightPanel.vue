<template>
  <!-- 右侧盒子 -->
  <div class="carLeftBox">
    <!-- 车辆类别 -->
    <div class="vehicleCategory">
      <div class="car-boxTitle">
        <span></span>
        <span>进出口车流量统计</span>
      </div>
      <el-row>
        <el-col :span="12">
          <div style="width:100%;height:180px">
            <vehicleCategoryCharts :currentComunity="currentComunity"
                                   :xData="vehicleCategoryIn"
                                   :labels="'车辆进口'" />
          </div>
        </el-col>
        <el-col :span="12">
          <div style="width:100%;height:180px">
            <vehicleCategoryCharts :currentComunity="currentComunity"
                                   :xData="vehicleCategoryOut"
                                   :labels="'车辆出口'" />
          </div>
        </el-col>
      </el-row>
    </div>

    <!-- 小区车辆出入时间段统计 -->
    <div class="communityVehicleStatisticsTime">
      <div class="car-boxTitle">
        <span></span>
        <span>车辆出入时间段统计</span>
      </div>
      <div style="width:100%;height:100%">
        <!-- <statisticsOfVehiclesCharts :currentComunity="currentComunity" /> -->
        <doubleLine :data="vehicleFlowInfo"
                    :height="'250px'" />
      </div>
    </div>
    <!-- 管控车辆 -->
    <div class="controlOfTheVehicle">
      <div class="car-boxTitle">
        <span></span>
        <span>管控车辆</span>
      </div>
      <!-- <div class="controlOfTheVehicleBox">
        <el-row :gutter="5"
                style="height:50%">
          <el-col :span="8"
                  v-for="(item,index) in controlOfTheVehicleData"
                  style="margin-top: 5px;height:100%">
            <div class="controlOfTheVehicleBoxInside">
              <div class="controlOfTheVehicleBoxInsidePic">
                <img :src="item.pic"
                     alt="">
              </div>
              <div class="controlOfTheVehicleBoxInsideCarNum">
                <span>车牌号:</span>
                <span>{{item.carNum}}</span>
              </div>
              <div class="controlOfTheVehicleBoxInsideCarNum">
                <span>车牌号:</span>
                <span>{{item.carNum}}</span>
              </div>
              <div class="controlOfTheVehicleBoxInsideCarNum">
                <span>车牌号:</span>
                <span>{{item.carNum}}</span>
              </div>
            </div>
          </el-col>
        </el-row>
      </div> -->
      <div class="controlOfTheVehicleBox">
        <el-carousel :interval="3000"
                     type="card"
                     height="70%"
                     class="controlOfTheVehicleBox-carousel">
          <el-carousel-item v-for="(item, index) in controlOfTheVehicleData"
                            :key="index">
            <div class="controlOfTheVehicle-box"
                 @click="getDetail(item)">
              <img class="controlOfTheVehicle-img"
                   :src="item.limitPic" />
              <div class="medium">
                <div>
                  <span class="controlOfTheVehicle-name">车牌号：</span>
                  <span class="controlOfTheVehicle-name"
                        style="margin:0">{{
                    item.plate
                  }}</span>
                </div>
                <div>
                  <span class="controlOfTheVehicle-name">车主姓名：</span>
                  <span class="controlOfTheVehicle-name"
                        style="margin:0">{{
                    item.ownerName | ellipsis
                  }}</span>
                </div>
              </div>
            </div>
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>
    <!-- 车辆入驻时间 -->
    <div class="vehicleEnergy">
      <div class="car-boxTitle">
        <span></span>
        <span>车辆入驻时间</span>
      </div>
      <div style="width:100%;height:100%">
        <vehicleYearsCharts :currentComunity="currentComunity" />
      </div>
    </div>

    <!-- 查询布控人员信息 对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="600px"
               append-to-body
               class="dialogHouseClass">
      <el-form ref="form"
               :model="form"
               :inline="true"
               label-width="85px">
        <el-form-item label="头像:"
                      prop="avatarId">
          <div style="width: 400px; height: 200px; position: absolve">
            <img style="margin: 0 20px; width: 180px; height: 200px"
                 :src="form.limitPic" />
          </div>
        </el-form-item>
        <el-form-item label="车牌号:"
                      prop="plate">
          <span class="addr">{{ form.plate }}</span>
        </el-form-item>
        <el-form-item label="车型:"
                      prop="carType">
          <span class="addr">{{ form.carType }}</span>
        </el-form-item>
        <el-form-item label="品牌:"
                      prop="pinpai">
          <span class="addr">{{ form.pinpai }}</span>
        </el-form-item>
        <el-form-item label="子品牌:"
                      prop="zipinpai">
          <span class="addr">{{ form.zipinpai }}</span>
        </el-form-item>
        <el-form-item label="车主:"
                      prop="name">
          <span class="addr">{{ form.ownerName }}</span>
        </el-form-item>
        <el-form-item label="性别:"
                      prop="sex">
          <span class="addr">{{ form.sex }}</span>
        </el-form-item>
        <el-form-item label="年龄:"
                      prop="age">
          <span class="addr">{{ form.age }}</span>
        </el-form-item>
        <el-form-item label="联系方式:"
                      prop="phone">
          <span class="addr">{{ form.phone }}</span>
        </el-form-item>
        <el-form-item label="籍贯:"
                      prop="jiguan">
          <span class="addr">{{ form.jiguan }}</span>
        </el-form-item>
        <el-form-item label="身份证:"
                      prop="cardID">
          <span class="addr">{{ form.cardID | ellipsis }}</span>
        </el-form-item>
        <el-form-item label="房屋地址:"
                      prop="fangwudizhi">
          <span class="addr">{{ form.fangwudizhi }}</span>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
import statisticsOfVehiclesCharts from "@/components/echarts/carInfo/statisticsOfVehicles";
import doubleLine from "@/components/echarts/carInfo/double_Line";
import vehicleCategoryCharts from "@/components/echarts/carInfo/vehicleCategory";
import vehicleEnergyTypeCharts from "@/components/echarts/carInfo/vehicleEnergyType";
import vehicleYearsCharts from "@/components/echarts/carInfo/vehicleYears";
import { getcontrolVehicle } from "@/api/vehicle/controlvehicle";
export default {
  components: {
    statisticsOfVehiclesCharts,
    doubleLine,
    vehicleCategoryCharts,
    vehicleEnergyTypeCharts,
    vehicleYearsCharts
  },
  data () {
    return {
      controlOfTheVehicleData: [],
      form: {},
      open: false,
      vehicleCategoryIn: [],
      vehicleCategoryOut: [],
      title: "",
      vehicleFlowInfo: null
    };
  },
  computed: {
    ...mapGetters(["currentComunity"])
  },
  filters: {
    ellipsis (value) {
      if (!value) return "";
      if (value.length > 13) {
        return value.slice(0, 6) + "********" + value.slice(-4);
      }
      return value;
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
    this.controlOfTheVehicleData = this.testData.car.controlOfTheVehicleData;
    this.vehicleCategoryIn = this.testData.car.vehicleCategoryIn;
    this.vehicleCategoryOut = this.testData.car.vehicleCategoryOut;
    // this.getcontrolsvehicleApi()
    this.vehicleFlowInfo = this.testData.car.vehicleFlowInfo;
  },
  methods: {
    getcontrolsvehicleApi () {
      this.controlOfTheVehicleData = [];
      let communityId = this.currentComunity.communityId;
      let communityChildId = this.currentComunity.communityChildId;
      getcontrolVehicle(communityId, communityChildId).then(res => {
        console.log(res, "control");
        if (res && res.data.length > 0) {
          this.controlOfTheVehicleData = res.data;
        }
      });
    },
    getDetail (info) {
      this.form = info;
      this.title = "布控车辆信息";
      this.open = true;
    }
  }
};
</script>

<style scoped>
@import "~@/assets/styles/main/carInfo.css";
</style>
