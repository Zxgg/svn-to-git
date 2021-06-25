<template>
  <el-container>
    <el-aside width="40%">
      <el-form size="mini"
               label-width="100px"
               :model="model">
        <el-form-item label="任务名称"
                      prop="name">
          <el-input readonly
                    v-model="model.name" />
        </el-form-item>
        <el-form-item label="社区"
                      prop="communityId">
          <el-select v-model="model.communityId"
                     filterable
                     disabled
                     clearable
                     placeholder="请选择社区"
                     @change="getCommunityChildDictory">
            <el-option v-for="dict in communityOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
          <el-button v-hasPermi="['communityUnit:community:query']"
                     size="mini"
                     type="text"
                     icon="el-icon-refresh"
                     @click="getCommunityDictory">刷新</el-button>
        </el-form-item>
        <el-form-item label="小区"
                      prop="communityChildId">
          <el-select v-model="model.communityChildId"
                     filterable
                     disabled
                     clearable
                     placeholder="请选择小区">
            <el-option v-for="dict in communityChildOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="布控开始时间">
          <el-date-picker v-model="model.startDate"
                          type="date"
                          readonly
                          size="mini"
                          format="yyyy-MM-dd"
                          placeholder="年-月-日">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="布控结束时间">
          <el-date-picker v-model="model.endDate"
                          type="date"
                          readonly
                          size="mini"
                          format="yyyy-MM-dd"
                          placeholder="年-月-日">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="任务类型"
                      prop="rosterType">
          <el-select v-model="model.rosterType"
                     placeholder="请选择任务类型"
                     disabled
                     size="small">
            <el-option v-for="dict in RosterTypeList"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item v-if="!model.thresholds"
                      label="相似度"
                      prop="confidenceThreshold">
          <el-input readonly
                    v-model="model.confidenceThreshold" />
        </el-form-item>
        <el-form-item v-if="model.thresholds"
                      label="相似度"
                      prop="thresholds.threshold.thresholdValue">
          <el-input readonly
                    v-model="model.thresholds.threshold.thresholdValue" />
        </el-form-item>
        <!-- <el-form-item>
          <el-switch v-model="model.hitType"
                     disabled
                     active-value="1"
                     inactive-value="2"
                     active-text="命中告警"
                     inactive-text="不命中告警">
          </el-switch>
        </el-form-item> -->
        <el-form-item label="布控卡组">
          <el-input readonly
                    type="textarea"
                    :autosize="{ minRows: 2, maxRows: 4}"
                    :value="getMonitorBayonetList(model.bayonetNames)" />
        </el-form-item>
        <el-form-item label="布控目标">
          <div>
            <el-tag class="monitorTag"
                    v-for="tag in model.groupList"
                    :key="tag.id"
                    type="info"
                    color="transparent"
                    @click="GroupClick(tag)">
              {{tag.name}}
            </el-tag>
          </div>
        </el-form-item>
        <el-form-item label="目标图像">
          <el-scrollbar class="monitorScrollbar"
                        wrap-class="scrollbar-wrapper "
                        wrap-style="overflow-x:hidden;">
            <div class="monitorImgDiv"
                 v-for="(groupInfo,index) in GroupInfoList"
                 :key="index">
              <img id="monitorImg"
                   :src="groupInfo.createTime"
                   @click="showBigImage(previewList[index])" />
              <el-row class="imgSpan"
                      v-if="groupInfo.name">
                <span>
                  {{ groupInfo.name }}
                </span>
              </el-row>
              <el-row class="imgSpan"
                      v-else-if="groupInfo.lpn">
                <span>
                  {{ groupInfo.lpn }}
                </span>
              </el-row>
              <el-row class="imgSpan"
                      v-else>
                <span>
                  {{ '无' }}
                </span>
              </el-row>
            </div>
          </el-scrollbar>
        </el-form-item>
      </el-form>
    </el-aside>

    <el-container class="rightView"
                  v-loading="loading">
      <span>告警图像</span>
      <el-scrollbar class="alarmScrollbar"
                    wrap-class="scrollbar-wrapper "
                    wrap-style="overflow-x:hidden;">
        <div class="alarmImgDiv"
             v-for="(item,index) in AlarmInfoList"
             :key="index">
          <el-tooltip placement="top">
            <div slot="content">{{item | getNamefromAlarm(that)}}<br />{{item.taskinfo.cameraname}}<br />{{item | getDate(that)}}</div>
            <img id="alarmImg"
                 :src="item.alarm.picUrl"
                 @click="showBigImage(alarmPreviewList[index])" />
          </el-tooltip>
          <!-- <el-row class="alarmSpan">
            <span>{{item.taskinfo.cameraname}}</span>
          </el-row> -->
          <el-row class="alarmSpan">
            <span>{{item | getNamefromAlarm(that)}}</span>
          </el-row>
          <!-- <el-row class="alarmSpan">
            <span>{{item | getDate(that)}}</span>
          </el-row> -->
        </div>
      </el-scrollbar>

      <el-footer class="userListFooter">
        <!-- <el-row id="Pagination"> -->
        <pagination v-show="params.total>0"
                    small
                    :total="params.total"
                    :page.sync="params.pageIndex"
                    :limit.sync="params.pageSize"
                    @pagination="searchAlarm" />

        <!-- </el-row> -->
      </el-footer>

    </el-container>
    <!-- <div slot="footer"
         class="dialog-footer">
      <el-button @click="ShowTaskInfoClose">关闭</el-button>
    </div> -->

    <el-dialog title="大图"
               :visible.sync="fullImagedialogVisible"
               :modal='false'
               top='100px'
               width="60%"
               append-to-body>
      <div class="full-img">
        <img :src="fullImagePath"
             width="100%"
             height="100%">
      </div>
    </el-dialog>
    <!-- </div> -->
  </el-container>
</template>

<script>
import { GetImageBase64, GetFacesLibaryPicResultByFileID } from '@/api/pvMonitor/IntelligentAnalysis'
import { findBlackListList } from '@/api/pvMonitor/BlackListManagement'
import { searchAlarmHistory, getAlarmHistoryImage } from '@/api/pvMonitor/AlarmManagement'
import { listCommunity } from '@/api/communityUnit/community'
import { listCommunity_child } from '@/api/communityUnit/community_child'

export default {
  name: 'GrandsonMonitorTaskResultInfoShow',
  // 子组件访问父组件数据的唯一接口
  props: {
    model: Object,
    FaceGroupsList: {
      type: Array,
      required: true,
      default: () => []
    },
    VehicleGroupsList: {
      type: Array,
      required: true,
      default: () => []
    }
  },
  data () {
    return {
      that: this,
      GroupInfoList: [],
      previewList: [],
      AlarmInfoList: [],
      alarmPreviewList: [],
      // 任务花名册类型
      RosterTypeList: [],
      fullImagedialogVisible: false,
      fullImagePath: '',
      params: {
        pageIndex: 1,
        pageSize: 50,
        name: '',
        sortType: 'desc',
        total: 0
      },
      AllBlackTargetInfoList: [],
      // 遮罩层
      loading: true,
      // 社区
      communityOptions: [],
      // 小区列表
      communityChildOptions: [],
    }
  },
  created () {
    this.getCommunityDictory()
    this.getDicts("base_roster_type").then(response => {
      this.RosterTypeList = response.data;
    });
  },
  filters: {
    // 根据告警数据中的人脸信息找出人脸姓名
    getNamefromAlarm: function (value, self) {
      let alarmModel = value
      if (self.model.type === '1') {
        if (alarmModel && alarmModel.alarm && alarmModel.alarm != null && alarmModel.alarm.lprinfo && alarmModel.alarm.lprinfo != null && alarmModel.alarm.lprinfo.licenseplate && alarmModel.alarm.lprinfo.licenseplate != null) {
          return alarmModel.alarm.lprinfo.licenseplate
        }
      } else if (self.model.type === '2' || self.model.type === '3') {
        if (alarmModel && alarmModel.alarm && alarmModel.alarm != null && alarmModel.alarm.frinfo && alarmModel.alarm.frinfo != null && alarmModel.alarm.frinfo.faceid && alarmModel.alarm.frinfo.faceid != null) {
          let faceid = alarmModel.alarm.frinfo.faceid
          if (faceid && faceid.length >= 19) {
            faceid = faceid.substring(faceid.length - 19, faceid.length)
            // self.AllBlackTargetInfoList[2].faceList[0].featureList[0].featureId
            let peopleList = self.AllBlackTargetInfoList.filter(item =>
              item.faceList && item.faceList != null && item.faceList.length > 0 &&
              item.faceList[0].featureList && item.faceList[0].featureList !== null && item.faceList[0].featureList.length > 0 &&
              item.faceList[0].featureList[0].featureId && item.faceList[0].featureList[0].featureId === faceid)
            if (peopleList && peopleList !== null && peopleList.length > 0) {
              return peopleList[0].name
            }
          }
        }
      }
    },
    getDate: function (value, self) {
      let alarmtime = value.alarm.alarmtime
      let res = self.commonFunction.getTimeByTimeStamp(alarmtime)
      return res
    }
  },
  methods: {
    // 获取社区列表
    getCommunityDictory () {
      this.communityOptions = []
      const param = {
        pageNum: 1,
        pageSize: 100000
      }
      listCommunity(param).then(response => {
        this.communityOptions = response.rows
      })
    },
    // 获取小区列表
    getCommunityChildDictory (communityId) {
      this.communityChildOptions = []
      const param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId
      }
      listCommunity_child(param).then(response => {
        this.communityChildOptions = response.rows
      })
    },
    getMonitorGroup: function (value, self) {
      let result = value
      result = ''
      if (value && value.groupList && value.groupList.length > 0) {
        value.groupList.forEach(item => {
          if (item.enable === 'true') {
            result += (item.name + '，')
          }
        })
        if (result.lastIndexOf('，') === (result.length - 1)) {
          result = result.substring(0, result.length - 1)
        }
      }
      return result
    },
    // 布控卡组格式化
    getMonitorBayonetList: function (value, self) {
      let result = ''
      if (value && value.length > 0) {
        let bList = value.split(',')
        bList.forEach(item => {
          let itemList = item.split(':')
          result += itemList[1] + '，'
        })
        if (result.lastIndexOf('，') === (result.length - 1)) {
          result = result.substring(0, result.length - 1)
        }
      }
      return result
    },
    // 点击布控目标组切换目标图像
    GroupClick (group) {
      // alert(group.name)
      this.GroupInfoList = []
      this.previewList = []
      if (group && group.id && group.id.length > 0) {
        if (this.model.type === '1' && this.VehicleGroupsList && this.VehicleGroupsList != null && this.VehicleGroupsList.length > 0) { // 车辆
          // group.id = '5e9678131ae8160e7eeb7dcf'
          let vehicleGroup = this.VehicleGroupsList.filter(item => item.groupid === group.id)
          if (vehicleGroup !== null && vehicleGroup.length > 0) {
            this.loadBlackVehicleInfo(vehicleGroup[0])
          }
        } else if (this.model.type === '2' || this.model.type === '3') { // 2：人脸库黑名单 3：人脸库白名单
          if (this.FaceGroupsList && this.FaceGroupsList != null && this.FaceGroupsList.length > 0) {
            // group.id = '5ec3a1459c7caa0e3745a6e9'
            let faceGroup = this.FaceGroupsList.filter(item => item.id === group.id)
            if (faceGroup !== null && faceGroup.length > 0) {
              this.loadBlackFaceInfo(faceGroup[0])
            }
          }
        }
      }
    },
    // 取出此组所有车辆
    loadBlackVehicleInfo (blackGroupInfo) {
      let self = this
      let param = {
        condition: {
          limit: '250',
          page: '1'
        },
        type: '0',
        plate: {
          groupid: blackGroupInfo.groupid
        }
      }
      findBlackListList(param).then(data => { // 获取根节点组织机构数据
        if (data != null && data.status === '200') {
          console.log('groupList:', data.data)
          self.GroupInfoList = data.data
          for (let i = 0; i < self.GroupInfoList.length; i++) {
            self.previewList.push('')
            if (self.GroupInfoList[i].carList && self.GroupInfoList[i].carList !== null && self.GroupInfoList[i].carList.length > 0) {
              self.getImageByFileId(i, self.GroupInfoList[i].carList[0].carId)
            }
          }
        }
      })
    },
    // 取出此组所有人脸
    loadBlackFaceInfo (blackGroupInfo) {
      let self = this
      let orgCode = {
        repositorIds: blackGroupInfo.id,
        page: {
          no: 1,
          size: 500,
          sort: 'asc',
          orderName: 'time'
        }
      }

      GetFacesLibaryPicResultByFileID(orgCode).then(data => { // 查询人脸库

        if (data != null && data.status === '200') {
          self.GroupInfoList = data.data
          console.log('groupListperson:', data.data)
          for (let i = 0; i < self.GroupInfoList.length; i++) {
            self.previewList.push('')
            if (self.GroupInfoList[i].faceList && self.GroupInfoList[i].faceList !== null && self.GroupInfoList[i].faceList.length > 0) {
              self.getImageByFileId(i, self.GroupInfoList[i].faceList[0].fileId)
            }
          }
        }
      })
    },
    // 根据fileid获取图片bit64
    getImageByFileId (index, fileId) {

      let self = this
      GetImageBase64(fileId).then(dataThree => {

        let imageBasa64 = 'data:image/png;base64,' + dataThree.data
        self.GroupInfoList[index].createTime = imageBasa64
        self.previewList[index] = imageBasa64
      })
    },
    // 查询该布控下产生的告警信息
    searchAlarm () {
      this.loading = true
      let end_time = new Date()
      let start_time = new Date(this.model.startDate)
      // start_time.setDate(end_time.getDate() - 30)
      let startTimeInt = start_time.getTime()
      let endTimeInt = end_time.getTime()
      let entity = {
        pageIndex: this.params.pageIndex,
        pageSize: this.params.pageSize,
        startTimeInt: startTimeInt,
        endTimeInt: endTimeInt,
        ruleType: '16', // 16：人脸检测 17：车牌识别
        taskId: this.model.suspectId
      }
      if (this.model.type === '1') entity.ruleType = '17'
      this.AlarmInfoList = []
      this.alarmPreviewList = []
      let self = this
      searchAlarmHistory(entity).then(data => {
        if (data != null && data.status === '200') {
          // if (data && data !== null && data.data.length > 0) alert('kaka')
          this.loading = false
          self.AlarmInfoList = data.data
          self.params.total = data.page.total
          for (let i = 0; i < self.AlarmInfoList.length; i++) {
            self.getImageByAlarmId(i, self.AlarmInfoList[i].alarm.alarmid)
            this.alarmPreviewList.push('')
          }
        }
        console.log('预览告警列表：', this.alarmPreviewList)
        console.log('告警列表：', this.AlarmInfoList)
      })
    },
    // 获取告警抓拍图
    getImageByAlarmId (index, alarmId) {
      const self = this
      let entity = {
        alarmId: alarmId,
        type: '1'// 下载类型（人脸大小图0/1，车辆大小图2/3，行为分析大小图4/5）
      }
      if (self.model.type === '1') entity.type = '3'//
      getAlarmHistoryImage(entity).then(imgData => { // 根据fileid获取图片bit64
        let imageBasa64 = 'data:image/png;base64,' + imgData.data
        /// ???
        self.AlarmInfoList[index].alarm.picUrl = imageBasa64
      })

      let entityBig = {
        alarmId: alarmId,
        type: '0'// 下载类型（人脸大小图0/1，车辆大小图2/3，行为分析大小图4/5）
      }
      if (self.model.type === '1') entityBig.type = '2'//
      getAlarmHistoryImage(entityBig).then(imgData => { // 根据fileid获取图片bit64
        let imageBasa64 = 'data:image/png;base64,' + imgData.data
        this.alarmPreviewList[index] = imageBasa64
      })
    },
    // showBigImage (base64) {
    //   this.fullImagedialogVisible = true
    //   this.fullImagePath = base64
    // },
    // 查看大图（加水印）
    async showBigImage (bigImage) {
      this.fullImagedialogVisible = true
      // this.fullImagePath = bigImage
      this.fullImagePath = await this.commonFunction.GetImageBase64_AddWaterMarker(bigImage)
    },
    // 查询该布控下所有人脸库信息
    getPeopleInfo () {
      if (!(this.FaceGroupsList && this.FaceGroupsList != null && this.FaceGroupsList.length > 0)) return false
      if (!(this.model.groupList != null && this.model.groupList.length > 0)) return false
      let self = this
      for (let i = 0; i < this.model.groupList.length; i++) {
        let faceGroup = this.FaceGroupsList.filter(item => item.id === this.model.groupList[i].id)
        if (faceGroup !== null && faceGroup.length > 0) {
          // this.loadBlackFaceInfo(faceGroup[0])
          // 取出所有人脸
          let orgCode = {
            repositorIds: faceGroup[0].id,
            page: {
              no: 1,
              size: 500,
              sort: 'asc',
              orderName: 'time'
            }
          }
          GetFacesLibaryPicResultByFileID(orgCode).then(data => { // 查询人脸库
            if (data.data !== null && data.data.length > 0) {
              self.AllBlackTargetInfoList = self.AllBlackTargetInfoList.concat(data.data)
            }
          })
        }
      }
      console.log('人脸信息：', self.AllBlackTargetInfoList)
    },
    // 查询该布控下所有车辆库信息
    getVehicleInfo () {
      if (!(this.VehicleGroupsList && this.VehicleGroupsList != null && this.VehicleGroupsList.length > 0)) return false
      if (!(this.model.groupList != null && this.model.groupList.length > 0)) return false
      let self = this
      for (let i = 0; i < this.model.groupList.length; i++) {
        // 车辆
        // group.id = '5e9678131ae8160e7eeb7dcf'
        let vehicleGroup = this.VehicleGroupsList.filter(item => item.groupid === this.model.groupList[i].id)
        if (vehicleGroup !== null && vehicleGroup.length > 0) {
          // this.loadBlackVehicleInfo(vehicleGroup[0])
          // 取出此组所有车辆
          let param = {
            condition: {
              limit: '250',
              page: '1'
            },
            type: '0',
            plate: {
              groupid: vehicleGroup[0].groupid
            }
          }
          findBlackListList(param).then(data => { // 获取根节点组织机构数据
            if (data != null && data.status === '200') {
              // self.GroupInfoList = data.data
              self.AllBlackTargetInfoList = self.AllBlackTargetInfoList.concat(data.data)
            }
          })
        }
      }
    },
    // 获取所有黑名单信息(无图)
    initData () {
      if (this.model.groupList != null && this.model.groupList.length > 0) {
        if (this.model.type === '1') {
          this.getVehicleInfo()
        } else if (this.model.type === '2' || this.model.type === '3') {
          this.getPeopleInfo()
        }
      }
    }
  },
  watch: {
    model (val) {
      this.initData()
      this.searchAlarm()
      this.getCommunityChildDictory(val.communityId)
      if (this.model.groupList && this.model.groupList != null && this.model.groupList.length > 0) {
        this.GroupClick(this.model.groupList[0])
      }
    }
  },
  mounted () {
    this.initData()
    this.searchAlarm()
    this.getCommunityChildDictory(this.model.communityId)
    if (this.model.groupList && this.model.groupList != null && this.model.groupList.length > 0) {
      this.GroupClick(this.model.groupList[0])
    }
  }
}
</script>

<style lang="scss" scoped>
aside {
  background: none;
}
.monitorTag {
  margin: 2px;
  &:hover {
    cursor: pointer;
  }
}
.monitorScrollbar {
  height: 245px;
  width: 314px;
}
.monitorImgDiv {
  display: inline-block;
}
#monitorImg {
  width: 100px;
  height: 98px;
  margin: 1px;
}
.alarmImgDiv {
  display: inline-block;
}
#alarmImg {
  width: 72px;
  height: 70px;
  margin: 1px;
}
.alarmSpan {
  text-align: center;
  font-size: 2px;
}
.rightView {
  margin-left: 50px;
}
.imgSpan {
  line-height: 0px;
  text-align: center;
}
</style>
