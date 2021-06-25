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
        <!-- 以图搜图 -->
        <div class="searchPic">
          <div class="boxTitle">
            <span></span>
            <span>以图搜图</span>
            <div class="faceTitle"
                 :style="faceTitleBackground">
              <label class="perTile_label">{{"PICTURE SEARCH"}}</label>
            </div>
          </div>
          <div class="picContentBox">

            <div class="picContent"
                 :style="faceContentBackground">
              <div class="faceImage">
                <img style="width: 100%;height: 100%;"
                     v-if="imageUrl"
                     :src="imageUrl">
              </div>
              <el-upload class="picContentUpLoad"
                         ref="upload"
                         action="https://jsonplaceholder.typicode.com/posts/"
                         :show-file-list="false"
                         :on-change="handlePictureCardPreview"
                         :auto-upload="true">
                <i class="el-icon-picture upLoad"
                   slot="trigger"></i>
              </el-upload>
              <div class="line"></div>
            </div>
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
                  <el-form-item label="性别:"
                                prop="name">
                    <el-select v-model="characteristicsData.value"
                               placeholder="请选择性别">
                      <el-option v-for="item in characteristicsData.gender"
                                 :key="item.value"
                                 :label="item.label"
                                 :value="item.value">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="年龄:"
                                prop="age">
                    <el-select v-model="characteristicsData.ageValue"
                               placeholder="请选择性别">
                      <el-option v-for="item in characteristicsData.age"
                                 :key="item.value"
                                 :label="item.label"
                                 :value="item.value">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="发型:"
                                prop="clothColor"
                                label-width="68px">
                    <el-select v-model="characteristicsData.hairstyleValue"
                               placeholder="请选择性别">
                      <el-option v-for="item in characteristicsData.hairstyle"
                                 :key="item.value"
                                 :label="item.label"
                                 :value="item.value">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="是否带眼镜:"
                                prop="name"
                                label-width="100px">
                    <el-select v-model="characteristicsData.isEyeValue"
                               placeholder="请选择">
                      <el-option v-for="item in characteristicsData.isEye"
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
        </div>
        <div>

        </div>
      </div>
      <!-- 轨迹查询 -->
      <div class="trackSecondBox"
           v-if="characteristicsOfQueryFlag">
        <div class="trackQueryTitle">
          <div class="trackSearchButton"
               :style="searchButtonBackground"
               @click="trackDisplay"><span class="icon iconfont">&#xe66d;</span>轨迹展示</div>
          <div class="
                  faceTitle"
               :style="faceTitleBackground"><label class="perTile_label">{{"PICTURE SEARCH"}}</label></div>
        </div>
        <div class="featureInformation">
          <div class="outer-container">
            <div class="inner-container">
              <div class="featureInformationBox"
                   v-if="featureInformation"
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
                        <span>性别:</span>
                        <span>{{item.gender |transformationGenderData}}</span>
                      </div>
                      <div>
                        <span>年龄:</span>
                        <span>{{item.age |transformationAgeData}}</span>
                      </div>
                      <div>
                        <span>发型:</span>
                        <span>{{item.hair |transformationHairData}}</span>
                      </div>
                      <div>
                        <span>是否带眼镜:</span>
                        <span>{{item.eyeglass |transformationEyeglassData}}</span>
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
          <div class="similarPic">抓拍图片</div>
        </div>
        <div class="similarPicBox">
          <div class="outer-container">
            <div class="inner-container">
              <el-row :gutter="10">
                <el-col :span="6"
                        v-for="(item, index) in similarPicBox">
                  <div class="featureInformationBox">
                    <el-row :gutter="10">
                      <el-col :span="10">
                        <div class="featureInformationPic">
                          <img :src="item.thumPicUrl"
                               alt="">
                        </div>
                      </el-col>
                      <el-col :span="14">
                        <div class="featureInformationContent">
                          <div class="similarity">
                            <span>相似度:</span>
                            <span>{{item.similarity}}</span>
                          </div>
                          <!-- <div>
                        <span>数据来源:</span>
                        <span>{{item.dataFrom}}</span>
                      </div> -->
                          <div>
                            <span>性别:</span>
                            <span>{{item.gender |transformationGenderData}}</span>
                          </div>
                          <div>
                            <span>年龄:</span>
                            <span>{{item.age |transformationAgeData}}</span>
                          </div>
                          <div>
                            <span>发型:</span>
                            <span>{{item.hair |transformationHairData}}</span>
                          </div>
                          <div>
                            <span>是否带眼镜:</span>
                            <span>{{item.eyeglass |transformationEyeglassData}}</span>
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
import { getFaceByParameter, getUplodeImage, GetFaceImageBase64RestUri } from '@/api/smartSearch/faceSearch'
import configInfo from '@/webconfig/configInfo'
export default {
  name: "Index",
  filters: {
    ellipsis (value) {
      if (!value) return ''
      if (value.length > 10) {
        return value.slice(0, 10) + '...'
      }
      return value
    },
    transformationAgeData (value) {
      if (!value) return ''
      if (value === '-1') {
        return '不限'
      }
      if (value === '0') {
        return '未知'
      }
      if (value === '1') {
        return '儿童'
      }
      if (value === '2') {
        return '少年'
      }
      if (value === '3') {
        return '青年'
      }
      if (value === '4') {
        return '中年'
      }
      if (value === '5') {
        return '老年'
      }
      return value
    },
    transformationGenderData (value) {
      if (!value) return ''
      if (value === '-1') {
        return '不限'
      }
      if (value === '0') {
        return '未知'
      }
      if (value === '1') {
        return '男'
      }
      if (value === '2') {
        return '女'
      }
      return value
    }
    ,
    transformationHairData (value) {
      if (!value) return ''
      if (value === '-1') {
        return '不限'
      }
      if (value === '0') {
        return '未知'
      }
      if (value === '1') {
        return '长发'
      }
      if (value === '2') {
        return '短发'
      }
      return value
    }
    ,
    transformationEyeglassData (value) {
      if (!value) return ''
      else if (value === '-1') {
        return '不限'
      }
      else if (value === '0') {
        return '未知'
      }
      else if (value === '1') {
        return '是'
      }
      else if (value === '2') {
        return '否'
      }
    }
  },
  data () {
    return {
      backHistroyFlag: false,
      characteristicsOfQueryFlag: true,
      faceTitleBackground: {
        backgroundImage:
          "url(" + require("@/assets/images/IntelligentSearch/faceTitlePic.png") + ")",

        backgroundRepeat: "repeat",

        backgroundSize: "100% 100%"
      },
      faceContentBackground: {
        backgroundImage:
          "url(" + require("@/assets/images/IntelligentSearch/imgeBackground.png") + ")",

        backgroundRepeat: "repeat",

        backgroundSize: "100% 100%"
      },
      searchButtonBackground: {
        backgroundImage:
          "url(" + require("@/assets/images/IntelligentSearch/searchButton.png") + ")",

        backgroundRepeat: "repeat",

        backgroundSize: "100% 100%"
      },
      characteristicsData: {
        gender: [{
          value: '1',
          label: '男'
        }, {
          value: '2',
          label: '女'
        }, {
          value: '0',
          label: '未知'
        }],
        age: [{
          value: '0',
          label: '未知'
        }, {
          value: '1',
          label: '儿童'
        }, {
          value: '2',
          label: '少年'
        }, {
          value: '3',
          label: '青年'
        }, {
          value: '4',
          label: '中年'
        }, {
          value: '5',
          label: '老年'
        }],
        hairstyle: [{
          value: '1',
          label: '长发'
        }, {
          value: '2',
          label: '短发'
        }, {
          value: '0',
          label: '未知'
        }],
        value: '',
        ageValue: "",
        hairstyleValue: '',
        isEye: [{
          value: '1',
          label: '是'
        }, {
          value: '2',
          label: '否'
        }],
        isEyeValue: "",
        beginTime: this.commonFunction.addThreeS(this.commonFunction.getNowTime(), -604800),
        endTime: this.commonFunction.getNowTime()
      },
      startDatePicker: this.beginDate(),
      endDatePicker: this.processDate(),
      imageUrl: '',
      featureInformation: [],
      similarPicBox: [],
      loadFile: {},
    };
  },
  created () { },
  mounted () { this.search() },
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
    handlePictureCardPreview (file) {
      let reader = new FileReader()
      reader.readAsDataURL(file.raw)// 这里也可以直接写参数event.raw
      this.loadFile = file.raw
      // 转换成功后的操作，reader.result即为转换后的DataURL ，
      // 它不需要自己定义，你可以console.log(reader.result)看一下
      reader.onload = () => {
        this.imageUrl = reader.result
        // this.dialogImageUrlLength = reader.result.length
      }
    },
    search () {
      this.featureInformation = []
      if (this.startDatePicker && this.endDatePicker) {
        let imagedata = this.loadFile.size
        if (imagedata === undefined) {
          // this.handleClick('', false)
          this.handleClick('')
        } else if (imagedata !== undefined) {
          let files = this.loadFile
          var form = new FormData()
          form.append('files', files)
          form.append('source_system_id', '0')
          form.append('type', '1')
          getUplodeImage(form).then((filedata) => { // 上传图片
            let fileIdss = filedata
            let loadImageFile = fileIdss[0].casefile.casefileid
            this.handleClick(loadImageFile)
          })
        }
      } else {
        this.commonFunction.dialogSuccess('开始时间或结束时间为空！')
      }
    },
    handleClick (LoadFileId) {
      const startTimeInt = this.commonFunction.TimeToTimeStamp(this.characteristicsData.beginTime)
      const endTimeInt = this.commonFunction.TimeToTimeStamp(this.characteristicsData.endTime)
      let orgCode = {
        startTime: startTimeInt,
        endTime: endTimeInt,
        page: {
          no: 1,
          size: 10,
          sort: 'desc',
          orderName: 'time'
        },
        pictures: { picture: { 'fileId': LoadFileId } },
        searchType: '1',
        age: this.characteristicsData.ageValue,
        eyeglass: this.characteristicsData.isEyeValue,
        /*  mouthmask: this.valueMask, */
        hair: this.characteristicsData.hairstyleValue,
        gender: this.characteristicsData.value
      }
      getFaceByParameter(orgCode).then((data) => {
        if (data) {
          if (data.data) {
            this.featureInformation = data.data
            for (let i = 0; i < data.data.length; i++) {
              data.data[i].recordTime = this.commonFunction.getTimeByTimeStamp(data.data[i].recordTime)
              // this.getImageByFileId(i, data.data[i].fileId)
              if (configInfo.isUseImageProxy) {
                data.data[i].thumPicUrl = configInfo.vcmImageUrl + encodeURIComponent(data.data[i].thumPicUrl)
                data.data[i].picUrl = configInfo.vcmImageUrl + encodeURIComponent(data.data[i].picUrl)
              } else {
                data.data[i].thumPicUrl = encodeURIComponent(data.data[i].thumPicUrl)
                data.data[i].picUrl = encodeURIComponent(data.data[i].picUrl)
              }
            }
            this.trackSearch(this.featureInformation[0])
          }
        }
      })
    },
    backHistroy () {
      this.characteristicsOfQueryFlag = true;
      this.backHistroyFlag = false;
    },
    trackDisplay () {
      this.characteristicsOfQueryFlag = false;
      this.backHistroyFlag = true;
    },
    trackSearch (item) {
      const startTimeInt = this.commonFunction.TimeToTimeStamp(this.characteristicsData.beginTime)
      const endTimeInt = this.commonFunction.TimeToTimeStamp(this.characteristicsData.endTime)
      let orgCode = {
        startTime: startTimeInt,
        endTime: endTimeInt,
        page: {
          no: 1,
          size: 12,
          sort: 'desc',
          orderName: 'time'
        },
        pictures: { picture: item.fileId },
        searchType: '1',
        age: item.age,
        eyeglass: item.eyeglass,
        hair: item.hair,
        gender: item.gender
      }
      getFaceByParameter(orgCode).then((data) => {
        if (data) {
          if (data.data) {
            console.log(data, 'data')
            this.similarPicBox = data.data
            for (let i = 0; i < data.data.length; i++) {
              data.data[i].recordTime = this.commonFunction.getTimeByTimeStamp(data.data[i].recordTime)
              // this.getImageByFileId(i, data.data[i].fileId)
              if (configInfo.isUseImageProxy) {
                data.data[i].thumPicUrl = configInfo.vcmImageUrl + encodeURIComponent(data.data[i].thumPicUrl)
                data.data[i].picUrl = configInfo.vcmImageUrl + encodeURIComponent(data.data[i].picUrl)
              } else {
                data.data[i].thumPicUrl = encodeURIComponent(data.data[i].thumPicUrl)
                data.data[i].picUrl = encodeURIComponent(data.data[i].picUrl)
              }
            }
          }
        }
      })
    },
    trackLocation () {
      this.characteristicsOfQueryFlag = false;
      this.backHistroyFlag = true;
    },
    shrinkage () {
      $(".mainContent > div:first-child").fadeToggle(2000);
      $(".mainContent > div:nth-child(2)").fadeToggle(2000);
      this.$Notice.$emit('shrinkageHide', 'faceSearch')
    }
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
  height: 680px;
  background-color: rgba(34, 39, 81, 0.8);
  position: absolute;
}
.searchPic {
  width: 100%;
  height: 30%;
  background-color: rgba(34, 39, 81, 0.8);
}
.characteristicsOfQuery {
  height: 70%;
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
.faceTitle {
  width: 200px;
  height: 15px;
  position: absolute;
  right: 2%;
  top: 2%;
  line-height: 15px;
}
.picContentUpLoad {
  width: 82%;
  height: 22%;
  /* right: 90%; */
  position: absolute;
}
.picContent {
  width: 90%;
  height: 80%;
  overflow: hidden;
  border: 1px solid #00d9ff;
}
.picContent .line {
  height: calc(100% - 2px);
  width: 100%;
  background: linear-gradient(180deg, rgba(0, 255, 51, 0) 43%, #00d9ff 211%);
  border-bottom: 3px solid #00d9ff;
  transform: translateY(-100%);
  animation: radar-beam 3s infinite;
  animation-timing-function: cubic-bezier(0.53, 0, 0.43, 0.99);
  animation-delay: 1.4s;
}
@keyframes radar-beam {
  0% {
    transform: translateY(-100%);
  }

  100% {
    transform: translateY(0);
  }
}
.picContentBox {
  padding: 20px 0px 20px 40px;
  height: 260px;
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

.trackSearchButton {
  text-align: center;
  color: #fff !important;
  line-height: 25px;
  width: 120px;
  height: 25px;
}
.trackSearchButton span {
  margin-right: 5px;
}
.trackSearchButton:hover {
  cursor: pointer;
  color: rgba(0, 217, 255, 1) !important;
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
.trackQueryTitle {
  width: 100%;
  height: 50px;
  background-color: rgba(50, 55, 109, 1);
  padding: 11px 0px 0px 20px;
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
  margin-top: 15px;
}
.featureInformationContent > div span:last-child {
  margin-left: 10px;
}

.similarPicContent div {
  color: rgba(0, 217, 255, 1);
  margin-top: 5px;
}
.similarPicContent > div:nth-child(2) {
  margin-top: 15px;
}
.similarPicContent > div span:last-child {
  margin-left: 10px;
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
.similarPicBox {
  /* padding: 10px 0px 0px 0px; */
  height: 380px;
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
.faceImage {
  position: absolute;
  width: 130px;
  left: 33%;
  top: 7.5%;
  height: calc(100% - 80.5%);
}
.perTile_label {
  color: #00d9ff;
  margin-left: 89px;
  font-size: 6px;
}
.upLoad {
  color: #00d9ff;
  font-size: 200px;
  margin-left: 40%;
  margin-top: -10px;
  opacity: 0;
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
</style>

