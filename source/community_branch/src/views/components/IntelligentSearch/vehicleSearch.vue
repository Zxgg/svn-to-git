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
    <!-- 轨迹查询框返回按钮 -->
    <div class="backHistroy"
         v-if="backHistroyFlag"
         @click="backHistroy()">
      <span class="el-icon-refresh-left"></span>
      <span>返回</span>
    </div>
    <div class="mainContent">
      <div>
        <!-- 车辆搜索 -->
        <div class="searchPic">
          <div class="boxTitle">
            <span></span>
            <span>车辆搜索</span>
            <div class="carTitle"
                 :style="carTitleBackground">
              <label class="carTile_label">{{"LICENSE PLATE"}}</label>
            </div>
          </div>
          <div class="licensePlate">
            <el-input placeholder="请输入车牌号"
                      suffix-icon="el-icon-search"
                      v-model="plateInput">
            </el-input>
          </div>
        </div>
        <!-- 特征查询 -->
        <div class="characteristicsOfQuery">
          <div class="boxTitle">
            <span></span>
            <span>特征查询</span>
          </div>
          <div class="characteristicsOfQueryBox">
            <el-form :model="characteristicsData"
                     label-width="68px">
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="品牌:"
                                prop="name">
                    <el-select v-model="characteristicsData.brandValue"
                               placeholder="请选择品牌">
                      <el-option v-for="item in characteristicsData.vehicleBrand"
                                 :key="item.value"
                                 :label="item.label"
                                 :value="item.value">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="型号:"
                                prop="age">
                    <el-input v-model="characteristicsData.model"
                              placeholder="请输入型号"
                              clearable
                              size="small" />
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="车身颜色:"
                                prop="vehicleColor"
                                label-width="75px">
                    <el-select v-model="characteristicsData.colorValue"
                               placeholder="请选择颜色">
                      <el-option v-for="item in characteristicsData.color"
                                 :key="item.value"
                                 :label="item.label"
                                 :value="item.value">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="特殊车辆:"
                                prop="name"
                                label-width="100px">
                    <el-select v-model="characteristicsData.specialVehiclesValue"
                               placeholder="请选择">
                      <el-option v-for="item in characteristicsData.specialVehicles"
                                 :key="item.value"
                                 :label="item.label"
                                 :value="item.value">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="时间范围:"
                                label-width="80px">
                    <div class="block">
                      <el-date-picker v-model="characteristicsData.beginTime"
                                      type="datetime"
                                      placeholder="年-月-日  时-分-秒"
                                      :picker-options="startDatePicker"></el-date-picker>--
                      <el-date-picker v-model="characteristicsData.endTime"
                                      type="datetime"
                                      placeholder="年-月-日  时-分-秒"
                                      :picker-options="endDatePicker"></el-date-picker>
                    </div>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row class="searchButtonBox">
                <div class="searchButton"
                     :style="searchButtonBackground"
                     @click="search"><span class="el-icon-search"></span>搜索</div>
              </el-row>
            </el-form>
          </div>
          <div class="controlInformation">

          </div>
        </div>
      </div>
      <!-- 轨迹展示 -->
      <div class="trackSecondBox"
           v-if="characteristicsOfQueryFlag">
        <div class="trackQueryTitle">
          <div class="trackSearchButton"
               :style="searchButtonBackground"
               @click="trackDisplay"><span class="icon iconfont">&#xe66d;</span>轨迹展示</div>
          <div class="carTitle"
               :style="carTitleBackground"><label class="carTile_label">{{"TRAJECTORY"}}</label></div>
        </div>
        <div class="featureInformation">
          <div class="outer-container">
            <div class="inner-container">
              <div class="featureInformationBox"
                   v-for="(item, index) in featureInformation">
                <el-row :gutter="10">
                  <el-col :span="13">
                    <div class="featureInformationPic">
                      <img :src="item.thumPicUrl"
                           alt="">
                    </div>
                  </el-col>
                  <el-col :span="11">
                    <div class="similarPicContent">
                      <div>特征信息</div>
                      <div>
                        <span>品牌:</span>
                        <span>{{item.vehicleBrand}}</span>
                      </div>
                      <div>
                        <span>型号:</span>
                        <span>{{item.carType}}</span>
                      </div>
                      <div>
                        <span>车身颜色:</span>
                        <span>{{item.carColor}}</span>
                      </div>
                      <div>
                        <span>车牌号:</span>
                        <span>{{item.lprInfo.plateNumber}}</span>
                      </div>
                      <div class="trackSearchButton"
                           :style="searchButtonBackground"
                           @click="trackSearch(item)"><span class="el-icon-search"></span>轨迹查询</div>
                    </div>
                  </el-col>
                </el-row>
              </div>
            </div>
          </div>
        </div>
        <div>
          <div class="similarPic">
            抓拍图片
          </div>
        </div>
        <!-- 轨迹查询 -->
        <div class="outer-container">
          <div class="inner-container">
            <div class="vehiclePicBox">
              <el-row :gutter="10">
                <el-col :span="6"
                        v-if="vehiclePicBox"
                        v-for="(item, index) in vehiclePicBox"
                        style="margin-top: 5px;">
                  <div class="featureInformationBox">
                    <el-row :gutter="10">
                      <el-col :span="12">
                        <div class="featureInformationPic">
                          <img :src="item.thumPicUrl"
                               alt="">
                        </div>
                      </el-col>
                      <el-col :span="12">
                        <div class="featureInformationContent">
                          <div>特征信息</div>
                          <div>
                            <span>车牌号:</span>
                            <span>{{item.lprInfo.plateNumber}}</span>
                          </div>
                          <div>
                            <span>品牌:</span>
                            <span>{{item.vehicleBrand}}</span>
                          </div>
                          <div>
                            <span>型号:</span>
                            <span>{{item.carType}}</span>
                          </div>
                          <div>
                            <span>车身颜色:</span>
                            <span>{{item.carColor}}</span>
                          </div>
                          <div class="trackLocation"
                               :style="searchButtonBackground"
                               @click="trackLocation(item)"><span class="icon iconfont">&#xe786;&nbsp;&nbsp;定位</span></div>
                        </div>
                      </el-col>
                    </el-row>
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
import { getVehicleByParameter } from "@/api/smartSearch/vehicleSearch"
import configInfo from '@/webconfig/configInfo'
export default {
  name: "Index",

  data () {
    return {
      carTitleBackground: {
        backgroundImage:
          "url(" + require("@/assets/images/IntelligentSearch/faceTitlePic.png") + ")",

        backgroundRepeat: "repeat",

        backgroundSize: "100% 100%"
      },
      carContentBackground: {
        backgroundImage:
          "url(" + require("@/assets/images/IntelligentSearch/backgroundPic.png") + ")",

        backgroundRepeat: "repeat",

        backgroundSize: "100% 100%"
      },
      searchButtonBackground: {
        backgroundImage:
          "url(" + require("@/assets/images/IntelligentSearch/searchButton.png") + ")",

        backgroundRepeat: "repeat",

        backgroundSize: "100% 100%"
      },
      plateInput: '',
      characteristicsData: {
        vehicleBrand: [{
          value: '领克',
          label: '领克'
        }, {
          value: '本田',
          label: '本田'
        }, {
          value: '奥迪',
          label: '奥迪'
        }],
        color: [{
          value: '黑色',
          label: '黑色'
        }, {
          value: '白色',
          label: '白色'
        }, {
          value: '红色',
          label: '红色'
        }],
        brandValue: '',
        colorValue: "",
        model: "",
        specialVehicles: [{
          value: '是',
          label: '是'
        }, {
          value: '否',
          label: '否'
        }],
        specialVehiclesValue: "",
        beginTime: this.commonFunction.addThreeS(this.commonFunction.getNowTime(), -604800),
        endTime: this.commonFunction.getNowTime()
      },
      startDatePicker: this.beginDate(),
      endDatePicker: this.processDate(),
      featureInformation: [],
      vehiclePicBox: [],
      backHistroyFlag: false,
      characteristicsOfQueryFlag: true,
    };
  },
  created () { },
  mounted () {
    this.search()
  },
  methods: {
    beginDate () {
      const self = this
      return {
        disabledDate (time) {
          if (self.characteristicsData.end_time) { // 如果结束时间不为空，则小于结束时间
            return new Date(self.characteristicsData.end_time).getTime() < time.getTime()
          } else {
            // return time.getTime() > Date.now()//开始时间不选时，结束时间最大值小于等于当天
            // return time.getTime() < Date.now() - 1000*3600*24//开始时间不选时，结束时间最大值大于等于当天
          }
        }
      }
    },
    processDate () {
      const self = this
      return {
        disabledDate (time) {
          if (self.characteristicsData.start_time) { // 如果开始时间不为空，则结束时间大于开始时间
            return new Date(self.characteristicsData.start_time).getTime() > time.getTime()
          } else {
            // return time.getTime() < Date.now() - 1000*3600*24//开始时间不选时，结束时间最大值大于等于当天
          }
        }
      }
    },
    search () {
      const self = this
      self.featureInformation = []
      const startTimeInt = this.commonFunction.TimeToTimeStamp(self.characteristicsData.beginTime)
      const endTimeInt = this.commonFunction.TimeToTimeStamp(self.characteristicsData.endTime)
      let orgDate = {
        page: {
          sortOrder: 'desc',
          pageSize: 10,
          currentPage: 1
        },
        condition: {
          startTime: startTimeInt,
          endTime: endTimeInt,
          deviceSource: '-1',
          VehicleBrand: self.characteristicsData.brandValue,
          colorList: {
            color: self.characteristicsData.colorValue
          },
          vehicleSub: self.characteristicsData.model,
          pnr: self.characteristicsData.plateInput,
          specialVehicleTypeIdx: self.characteristicsData.specialVehiclesValue
        }
      }
      getVehicleByParameter(orgDate).then((dataTwo) => { // 根据特征获取车辆数据
        if (dataTwo) {
          console.log(dataTwo, 'data')
          if (dataTwo.data) {
            self.featureInformation = dataTwo.data
            // self.myInfo = dataTwo.data
            // this.params.total = dataTwo.page.total
            // this.params.pageIndex = dataTwo.page.currentPage
            // this.params.pageSize = dataTwo.page.rows
            for (let i = 0; i < dataTwo.data.length; i++) {
              dataTwo.data[i].imageTimestamp = this.commonFunction.getTimeByTimeStamp(dataTwo.data[i].imageTimestamp)
              // dataTwo.data[i].fileId.substring(dataTwo.data[i].fileId.lastIndexOf('%') + 1)
              // this.getImageByFileId(i, dataTwo.data[i].fileId)
              if (configInfo.isUseImageProxy) {
                dataTwo.data[i].thumPicUrl = configInfo.vcmImageUrl + encodeURIComponent(dataTwo.data[i].thumPicUrl)
                dataTwo.data[i].picUrl = configInfo.vcmImageUrl + encodeURIComponent(dataTwo.data[i].picUrl)
              } else {
                dataTwo.data[i].thumPicUrl = encodeURIComponent(dataTwo.data[i].thumPicUrl)
                dataTwo.data[i].picUrl = encodeURIComponent(dataTwo.data[i].picUrl)
              }
            }
            this.trackSearch(self.featureInformation[0])
          } else {
            // this.messageTips('无符合条件数据')
          }
        }
      }).catch(err => {
        console.log('vehicleErr:', err)
      })

      // let data = [
      //   {
      //     img: "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg",
      //     vehiclePlate: "京N.88888",
      //     vehicleBrand: "领克",
      //     vehicleType: "领克03",
      //     vehicleColor: "黑色",
      //   },
      //   {
      //     img: "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1613497156,32523314&fm=26&gp=0.jpg",
      //     vehiclePlate: "京N.66666",
      //     vehicleBrand: "领克",
      //     vehicleType: "领克05",
      //     vehicleColor: "黑色",
      //   }
      // ]
      // // alert('我是特征查询!');
      // // let sn = '1'
      // // this.$Notice.$emit('VehicleTrack', sn)
      // if (self.plateInput || self.characteristicsData.brandValue || self.characteristicsData.model) {
      //   data.forEach(vehicle => {
      //     self.featureInformation.push(vehicle)
      //   });
      //   this.trackSearch(data[0])
      // } else {
      //   alert('无符合条件的数据')
      // }
    },
    backHistroy () {
      this.characteristicsOfQueryFlag = true;
      this.backHistroyFlag = false;
    },
    trackSearch (data) {
      this.vehiclePicBox = []
      const self = this
      const startTimeInt = this.commonFunction.TimeToTimeStamp(self.characteristicsData.beginTime)
      const endTimeInt = this.commonFunction.TimeToTimeStamp(self.characteristicsData.endTime)
      let orgDate = {
        page: {
          sortOrder: 'desc',
          pageSize: 16,
          currentPage: 1
        },
        condition: {
          startTime: startTimeInt,
          endTime: endTimeInt,
          pnr: data.lprInfo.plateNumber,
        }
      }
      getVehicleByParameter(orgDate).then((dataTwo) => { // 根据特征获取车辆数据
        if (dataTwo) {
          console.log(dataTwo, 'data')
          if (dataTwo.data) {
            self.vehiclePicBox = dataTwo.data
            // self.myInfo = dataTwo.data
            // this.params.total = dataTwo.page.total
            // this.params.pageIndex = dataTwo.page.currentPage
            // this.params.pageSize = dataTwo.page.rows
            for (let i = 0; i < dataTwo.data.length; i++) {
              dataTwo.data[i].imageTimestamp = this.commonFunction.getTimeByTimeStamp(dataTwo.data[i].imageTimestamp)
              // dataTwo.data[i].fileId.substring(dataTwo.data[i].fileId.lastIndexOf('%') + 1)
              // this.getImageByFileId(i, dataTwo.data[i].fileId)
              if (configInfo.isUseImageProxy) {
                dataTwo.data[i].thumPicUrl = configInfo.vcmImageUrl + encodeURIComponent(dataTwo.data[i].thumPicUrl)
                dataTwo.data[i].picUrl = configInfo.vcmImageUrl + encodeURIComponent(dataTwo.data[i].picUrl)
              } else {
                dataTwo.data[i].thumPicUrl = encodeURIComponent(dataTwo.data[i].thumPicUrl)
                dataTwo.data[i].picUrl = encodeURIComponent(dataTwo.data[i].picUrl)
              }
            }
          } else {
            // this.messageTips('无符合条件数据')
          }
        }
      }).catch(err => {
        console.log('vehicleErr:', err)
      })
      // let trackData = [
      //   {
      //     img: "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg",
      //     vehiclePlate: "京N.88888",
      //     vehicleBrand: "领克",
      //     vehicleType: "领克03",
      //     vehicleColor: "黑色",
      //     sn: '1'
      //   },
      //   {
      //     img: "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1613497156,32523314&fm=26&gp=0.jpg",
      //     vehiclePlate: "京N.66666",
      //     vehicleBrand: "领克",
      //     vehicleType: "领克05",
      //     vehicleColor: "黑色",
      //     sn: '2'
      //   },
      //   {
      //     img: "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg",
      //     vehiclePlate: "京N.88888",
      //     vehicleBrand: "领克",
      //     vehicleType: "领克03",
      //     vehicleColor: "黑色",
      //     sn: '3'
      //   },
      //   {
      //     img: "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg",
      //     vehiclePlate: "京N.88888",
      //     vehicleBrand: "领克",
      //     vehicleType: "领克03",
      //     vehicleColor: "黑色",
      //     sn: '4'
      //   },
      //   {
      //     img: "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1613497156,32523314&fm=26&gp=0.jpg",
      //     vehiclePlate: "京N.66666",
      //     vehicleBrand: "领克",
      //     vehicleType: "领克05",
      //     vehicleColor: "黑色",
      //     sn: '5'
      //   },
      //   {
      //     img: "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1613497156,32523314&fm=26&gp=0.jpg",
      //     vehiclePlate: "京N.66666",
      //     vehicleBrand: "领克",
      //     vehicleType: "领克05",
      //     vehicleColor: "黑色",
      //     sn: '6'
      //   }
      // ]
      // trackData.forEach(dataT => {
      //   if (dataT.vehicleType === data.vehicleType) {
      //     this.vehiclePicBox.push(dataT)
      //   }
      // });

    },
    trackDisplay () {
      this.characteristicsOfQueryFlag = false;
      this.backHistroyFlag = true;
      if (this.vehiclePicBox) {
        this.$Notice.$emit('VehicleTrack', this.vehiclePicBox)
      }
    },
    trackLocation (item) {
      this.characteristicsOfQueryFlag = false;
      this.backHistroyFlag = true;
      if (item.sn) {
        this.$Notice.$emit('VehicleLocation', item)
      }
    },
    shrinkage () {
      $(".mainContent > div:first-child").fadeToggle(2000);
      $(".mainContent > div:nth-child(2)").fadeToggle(2000);
      // this.$Notice.$emit('shrinkageHide', 'vehicleSearch')
    },
  }
};
</script>

<style scoped="true">
.mainContent {
  padding-top: 10px;
}
.mainContent > div:first-child {
  left: 10px;
  width: 450px;
  height: 800px;
  position: absolute;
}
.mainContent > div:nth-child(2) {
  left: 470px;
  top: 60px;
  width: 1430px;
  height: 700px;
  background-color: rgba(34, 39, 81, 0.8);
  position: absolute;
}
.searchPic {
  width: 100%;
  height: 15%;
  background-color: rgba(34, 39, 81, 0.8);
}
.characteristicsOfQuery {
  height: 85%;
  background-color: rgba(34, 39, 81, 0.8);
  margin-top: 5px;
}
.boxTitle {
  padding: 12px 0px 0px 15px;
}
.boxTitle span:first-child {
  display: inline-block;
  width: 2px;
  height: 13px;
  background-color: #fff;
}
.boxTitle span:nth-child(2) {
  color: #fff;
  margin-left: 10px;
}
.carTitle {
  width: 200px;
  height: 15px;
  position: absolute;
  right: 2%;
  top: 2%;
  text-align: center;
  line-height: 14px;
}
.carTile_label {
  color: #00d9ff;
  margin-left: 80px;
  font-size: 6px;
}
.characteristicsOfQueryBox {
  padding: 20px;
}
.searchButtonBox {
  padding-left: 35%;
  margin-top: 8%;
}
.searchButton {
  text-align: center;
  color: #fff;
  line-height: 30px;
  width: 100px;
  height: 30px;
}
.searchButton span {
  margin-right: 5px;
}
.searchButton:hover {
  cursor: pointer;
  color: rgba(0, 217, 255, 1);
}
.switchImg {
  display: inline-block;
  width: 30px;
  height: 30px;
  border-radius: 50%;
  text-align: center;
  line-height: 30px;
  background-color: #32376d;
  color: #fff;
  position: absolute;
  left: 480px;
  top: 10px;
}
.switchImg:hover {
  cursor: pointer;
  color: #29cbc0;
}
.switchImg img {
  width: 100%;
  height: 100%;
}

.similarPic {
  width: 80px;
  height: 25px;
  border-radius: 200px 200px 200px 200px;
  text-align: center;
  font-size: 14px;
  line-height: 25px;
  border: 1px solid rgba(2, 217, 254, 1);
  background-color: rgba(27, 31, 69, 1);
  color: rgba(2, 217, 254, 1);
  margin: 10px 0px 0px 10px;
}
.vehiclePicBox {
  padding: 10px 0px 0px 10px;
  height: 400px;
  overflow-y: scroll;
  overflow-x: hidden;
}
.similarity {
  text-align: center;
  width: 150px;
  height: 30px;
  line-height: 30px;
  background-color: rgba(0, 217, 255, 1);
  border-radius: 200px 200px 200px 200px;
  color: black !important;
}
.licensePlate {
  width: 80%;
  margin-left: 10%;
  margin-top: 25px;
}
.controlInformation {
  height: 220px;
  border-top: 1px solid #0dd4f1;
  margin-top: 35px;
}
.trackQueryTitle {
  width: 100%;
  height: 50px;
  background-color: rgba(50, 55, 109, 1);
  padding: 11px 0px 0px 20px;
}
.trackSearchButton {
  text-align: center;
  color: #fff !important;
  line-height: 25px;
  width: 120px;
  height: 25px;
  margin-left: 5px;
}
.trackSearchButton span {
  margin-right: 5px;
}
.trackSearchButton:hover {
  cursor: pointer;
  color: rgba(0, 217, 255, 1) !important;
}
.trackLocation {
  text-align: center;
  color: #fff !important;
  line-height: 25px;
  width: 120px;
  height: 25px;
  /* margin-left: 5px; */
  /* margin-top: 20px !important; */
}
.trackLocation span {
  margin-right: 15px;
}
.trackLocation:hover {
  cursor: pointer;
  color: rgba(0, 217, 255, 1) !important;
}
.featureInformation {
  height: 200px;
  border-bottom: 1px solid rgba(50, 55, 109, 1);
  /* padding: 0px 0px 0px 10px; */
}
.featureInformationBox {
  width: 345px;
  height: 180px;
  border: 1px solid rgba(0, 217, 255, 1);
  background-color: rgba(0, 217, 255, 0.2);
  padding: 5px;
  float: left;
  margin-left: 10px;
  margin-top: 10px;
}
.featureInformationPic {
  width: 100%;
  height: 165px;
}
.featureInformationPic img {
  width: 100%;
  height: 100%;
}
.featureInformationContent div {
  color: rgba(0, 217, 255, 1);
  margin-top: 5px;
}
.featureInformationContent > div:nth-child(2) {
  margin-top: 8%;
}
.featureInformationContent > div span:last-child {
  margin-left: 10px;
}

.similarPicContent div {
  color: rgba(0, 217, 255, 1);
  margin-top: 5px;
}
.similarPicContent > div:nth-child(2) {
  margin-top: 8%;
}
.similarPicContent > div span:last-child {
  margin-left: 10px;
}
.backHistroy span {
  color: #fff;
}
.backHistroy:hover {
  cursor: pointer;
}
.backHistroy:hover span {
  color: #00d9ff;
}
.backHistroy span:last-child {
  margin-left: 5px;
}
.backHistroy {
  position: absolute;
  width: 70px;
  left: 480px;
  top: 50px;
}
.outer-container {
  width: 100%;
  height: 100%;
  position: relative;
  overflow: hidden;
}
.inner-container {
  position: absolute;
  left: 0;
  top: 0;
  right: -17px;
  bottom: 0;
  overflow-x: hidden;
  overflow-y: scroll;
}
</style>
<style>
.characteristicsOfQueryBox .el-form-item__label {
  color: #fff;
}
.characteristicsOfQueryBox .el-input__inner {
  background: rgba(0, 217, 255, 0.1);
  border-color: rgba(0, 217, 255, 1);
}
.characteristicsOfQueryBox input::-webkit-input-placeholder {
  color: rgba(0, 217, 255, 1);
}
.el-input__inner {
  background-color: transparent;
  border-radius: 10px;
  border: 1px solid#00D9FF;
  color: #00d9ff;
}
.licensePlate .el-input__inner:hover {
  border-color: #00d9ff;
}
.licensePlate .el-input__inner::-webkit-input-placeholder {
  color: #00d9ff;
}
.licensePlate .el-input__suffix {
  color: #00d9ff;
}
</style>
