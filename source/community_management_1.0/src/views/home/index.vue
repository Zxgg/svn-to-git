<template>
  <div class="main">
    <el-scrollbar style="height:100%"
                  wrap-class="scrollbar-wrapper "
                  wrap-style="overflow-x:hidden;">
      <div class="title1Contain textLeft">
        <i class="icon iconfont  iconshuxian-copy title1Icon"></i>
        <span class="title1 VerticalCenter">今日数据统计</span>
      </div>
      <el-row class="passStatistic divStackPanel_left noWarp">
        <div class="passStatisticCard"
             v-for="(item,index) in passStatisticList"
             :key="index"
             :class="index===(passStatisticList.length-1)?'noMarginRight':''">
          <div class="passStatisticCardTitle ">
            <i :class="'svg-external-icon icon iconfont HorizontalMargin  ' + item.iconClass"></i>
            <!-- <i :class="'icon iconfont   passStatisticCardTitleIcon'+item.iconClass"></i> -->
            <span style="color:#000000">{{item.title}}</span>
          </div>
          <div :class="item.spanClass">
            <span>{{item.value}}</span>
          </div>
        </div>
      </el-row>
      <div class="title1Contain textLeft">
        <i class="icon iconfont  iconshuxian-copy title1Icon"></i>
        <span class="title1 VerticalCenter">今日通行</span>
      </div>
      <el-row class="alarmList">
        <el-col :span="10">
          <el-carousel :interval="3000"
                       :loop="false"
                       :autoplay="false"
                       :arrow="'hover'"
                       :indicator-position="'outside'"
                       type="card"
                       height="200px">
            <el-carousel-item v-for="(item,index) in personAlarmList"
                              :key="index"
                              class="alarmCard">
              <div class="alarmCardImage">
                <el-image class="el_image"
                          :src="item.imageUrl"
                          fit="fill" />
              </div>
              <div class="alarmCardTitle textLeft">
                <span>姓名：</span>
                <span>{{item.name}}</span>
              </div>
              <div class=" textLeft">
                <div class="alarmCardContent">
                  <span>时间：</span>
                  <span>{{item.einlass}}</span>
                </div>
                <div class="alarmCardContent">
                  <span>地点：</span>
                  <span>{{item.address}}</span>
                </div>
              </div>
            </el-carousel-item>
            <!-- <el-carousel-item v-for="(item,index) in personAlarmList"
                              :key="index"
                              class="alarmCard">
              <div class="alarmCardImage">
                <el-image class="fill"
                          :src="item.pic"
                          fit="fill" />
              </div>
              <div class="alarmCardTitle textLeft">
                <span>姓名：</span>
                <span>{{item.name}}</span>
              </div>
              <div class=" textLeft">
                <div class="alarmCardContent">
                  <span>时间：</span>
                  <span>{{item.locationMarkTime}}</span>
                </div>
                <div class="alarmCardContent">
                  <span>地点：</span>
                  <span>{{item.deviceId}}</span>
                </div>
                <div class="alarmCardContent">
                  <span>布控任务：</span>
                  <span>{{item.taskId}}</span>
                </div>

              </div>
            </el-carousel-item> -->
          </el-carousel>
          <!-- <div class="alarmPing" /> -->
        </el-col>
        <el-col :span="2">
          <div class="empty2">
          </div>
        </el-col>
        <el-col :span="10">
          <el-carousel :interval="3000"
                       :loop="false"
                       :autoplay="false"
                       :arrow="'hover'"
                       :indicator-position="'outside'"
                       type="card"
                       height="200px">
            <el-carousel-item v-for="(item,index) in vehicleAlarmList"
                              :key="index"
                              class="alarmCard">
              <div class="alarmCardImage">
                <el-image class="el_image"
                          :src="item.imageUrl"
                          fit="fill" />
              </div>
              <div class="alarmCardTitle textLeft">
                <span>车牌：</span>
                <span>{{item.vehiclePlate}}</span>
              </div>
              <div class=" textLeft">
                <div class="alarmCardContent">
                  <span>时间：</span>
                  <span>{{item.einlass}}</span>
                </div>
                <div class="alarmCardContent">
                  <span>地点：</span>
                  <span>{{item.channelName}}</span>
                </div>
              </div>
            </el-carousel-item>
            <!-- <el-carousel-item v-for="(item,index) in vehicleAlarmList"
                              :key="index"
                              class="alarmCard">
              <div class="alarmCardImage">
                <el-image class="fill"
                          :src="item.pic"
                          fit="fill" />
              </div>
              <div class="alarmCardTitle textLeft">
                <span>车牌：</span>
                <span>{{item.plate}}</span>
              </div>
              <div class=" textLeft">
                <div class="alarmCardContent">
                  <span>时间：</span>
                  <span>{{item.locationMarkTime}}</span>
                </div>
                <div class="alarmCardContent">
                  <span>地点：</span>
                  <span>{{item.deviceId}}</span>
                </div>
                <div class="alarmCardContent">
                  <span>布控任务：</span>
                  <span>{{item.taskId}}</span>
                </div>

              </div>
            </el-carousel-item> -->
          </el-carousel>
          <!-- <div class="alarmPing" /> -->
        </el-col>
      </el-row>
      <el-row class="event"
              :style="{height:(dataHeight+28)+'px'}">
        <el-col :span="11">
          <el-row>
            <el-col :span="14"
                    class="box-col">
              <div class="title  VerticalCenter HorizontalLeft">
                <i class="icon iconfont  icongaojing"></i>
                <span class="commonTitle">社区事件</span>
              </div>
            </el-col>
            <el-col :span="10"
                    class="box-col">
              <div class="value  VerticalCenter HorizontalRight">
                <span class="alarmNum_Title">{{alarmEventNum}}</span>
                <span class="commonTitle">起</span>
              </div>
            </el-col>
          </el-row>
          <el-table stripe
                    :show-header="true"
                    :data="alarmEventTypeStatistic"
                    :height="dataHeight">
            <el-table-column label="社区事件类型"
                             fit
                             align="center"
                             class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <div class="title   HorizontalLeft">
                  <i class="icon iconfont  iconguangquanfangda"></i>
                  {{scope.row.typeName}}
                </div>
              </template>
            </el-table-column>
            <el-table-column label="数量"
                             fit
                             align="center"
                             class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <div class="value   HorizontalRight alarmNum_content">
                  {{scope.row.value}}
                </div>
              </template>
            </el-table-column>
          </el-table>
        </el-col>
        <el-col :span="2">
          <div class="empty1">
          </div>
        </el-col>
        <el-col :span="11">
          <el-row>
            <el-col :span="14"
                    class="box-col">
              <div class="title  VerticalCenter HorizontalLeft">
                <i class="icon iconfont  iconshijian"></i>
                <span class="commonTitle">关爱提醒</span>
              </div>
            </el-col>
            <el-col :span="10"
                    class="box-col">
              <div class="value  VerticalCenter HorizontalRight">
                <span class="alarmNum_Title">{{caringEventNum}}</span>
                <span class="commonTitle">起</span>
              </div>
            </el-col>
          </el-row>
          <el-table stripe
                    :show-header="true"
                    :data="caringEventTypeStatistic"
                    :height="dataHeight">
            <el-table-column label="关爱提醒类型"
                             fit
                             align="center"
                             class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <div class="title   HorizontalLeft">
                  <i class="icon iconfont  iconguangquanfangda"></i>
                  {{scope.row.type | typeFormat(that)}}
                </div>
              </template>
            </el-table-column>
            <el-table-column label="数量"
                             fit
                             align="center"
                             class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <div class="value   HorizontalRight alarmNum_content">
                  {{scope.row.value}}
                </div>
              </template>
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
      <!-- <div class="title1Contain textLeft">
        <i class="icon iconfont  iconshuxian-copy title1Icon"></i>
        <span class="title1 VerticalCenter">人口流动情况</span>
      </div> -->
      <div class="title1Contain textLeft">
        <i class="icon iconfont  iconshuxian-copy title1Icon"></i>
        <span class="title1 VerticalCenter">人口流动情况</span>
      </div>
      <el-row class="personFloat">
        <el-col :span="12">
          <div class="houseNumberBoxRight">
            <personFlowChart :data="personFlowInfo"
                             :height="'250px'" />
          </div>
        </el-col>
        <el-col :span="12">
          <div class="houseNumberBoxRight">
            <valueAndRatioPie :data="personFloatInfo"
                              :height="'250px'" />
          </div>
        </el-col>
      </el-row>
    </el-scrollbar>
  </div>
</template>
<script>
//import cameraTreeLazy from '@/components/cameraTree/cameraTree_lazy'
import personFlowChart from '@/components/dashboard/double_Line'
import water from '@/components/dashboard/water'
import valueAndRatioPie from '@/components/dashboard/valueAndRatioPie'
import { countPass, personnelPassSnap, vehiclePassSnap, eventNumberStatistics, countCaringEventByType, flowPopulation, getFlowPopulation, getPerHouseInformStatisticse } from "@/api/home"
import defaultPersonPic from "@/assets/images/home/ren.png"
import defaultVehiclePic from "@/assets/images/home/che.png"
import configInfo from '@/webconfig/configInfo'
export default {
  components: { personFlowChart, water, valueAndRatioPie },
  //props: {dataInfo: {type: Object,default: null}},
  data () {
    return {
      that: this,
      formHeight: 50,
      //通行统计 
      passStatisticList: [],
      //社区事件按类型统计
      alarmEventTypeStatistic: [],
      //社区事件总数
      alarmEventNum: 0,
      //事件类型
      typeOptions: [],
      //关爱提醒按类型统计
      caringEventTypeStatistic: [],
      //关爱提醒总数
      caringEventNum: 0,
      //人员告警列表
      personAlarmList: [],
      //车辆告警列表
      vehicleAlarmList: [],
      //存储的最大告警数量
      maxAlarmDataLength: 5,

      //人员流动情况信息
      personFloatInfo: {},
      // 人口流动情况详情
      personFlowInfo: null,
      defaultPersonImg: defaultPersonPic,
      defaultVehicleImg: defaultVehiclePic,
      // testPersonImage: '../../assets/images/home/testPerson.png',
      testPersonImage: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1608291358078&di=80b16a72cdb00b77d6bbcb4e0dfabd58&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn10%2F309%2Fw534h575%2F20180926%2Fa837-hhuhisn1021919.jpg',
      testVehicleImage: 'http://5b0988e595225.cdn.sohucs.com/images/20180602/c46897a650d14a058e1d689e628c260d.jpeg'
    }
  },
  created () {
    this.getDicts("base_caring_event").then(response => {
      this.typeOptions = response.data;
    });
  },
  mounted () {
    this.$nextTick(() => {
      this.formHeight = 27.3 + 64 + 27.3 + 274 + 27.3 + 250
    })
    // this.getTestData()
    this.getCountPass()
    this.getPersonnelPassSnap()
    this.getVehiclePassSnap()
    this.getEventNumberStatistics()
    this.getCountCaringEventByType()
    this.getFlowPopulation()
    this.getPerHouseInformStatisticse()
    // this.alarmPersonData = []
    // this.$WebScoket.$on('PersonAlarmEvent', function (AlarmData) { // 接收实时告警
    //   if (AlarmData !== null && AlarmData.length > 0) {
    //     AlarmData.forEach(AlarmDataItem => {
    //       if (JSON.stringify([AlarmDataItem]) !== '{}') {
    //         if (JSON.stringify(AlarmDataItem.privateInfo) !== '{}' && JSON.stringify(AlarmDataItem.commonInfo) !== '{}') {
    //           self.getScoketPersonAlarmData(AlarmDataItem)
    //         }
    //       }
    //     })
    //   }
    // })
    // this.$WebScoket.$on('VehicleAlarmEvent', function (AlarmData) { // 接收实时告警
    //   if (AlarmData !== null && AlarmData.length > 0) {
    //     AlarmData.forEach(AlarmDataItem => {
    //       if (JSON.stringify([AlarmDataItem]) !== '{}') {
    //         if (JSON.stringify(AlarmDataItem.privateInfo) !== '{}' && JSON.stringify(AlarmDataItem.commonInfo) !== '{}') {
    //           self.getScoketVehicleAlarmData(AlarmDataItem)
    //         }
    //       }
    //     })
    //   }
    // })
  },
  filters: {
    // 事件类型字典翻译
    typeFormat: function (value, self) {
      return self.selectDictLabel(self.typeOptions, value);
    },
  },
  methods: {
    async getScoketPersonAlarmData (AlarmData) {
      let self = this
      self.personAlarmData.taskinfo.taskId = AlarmData.commonInfo.taskId
      self.personAlarmData.taskinfo.cameraId = AlarmData.commonInfo.cameraId
      self.personAlarmData.taskinfo.cameraName = AlarmData.commonInfo.cameraName
      self.personAlarmData.alarm.alarmId = AlarmData.commonInfo.alarmId
      self.personAlarmData.alarm.alarmTime = AlarmData.commonInfo.alarmTime
      self.personAlarmData.alarm.fileId = AlarmData.privateInfo.pic.fileId
      self.personAlarmData.alarm.frinfo.faceId = AlarmData.commonInfo.faceId
      self.personAlarmData.alarm.frinfo.score = AlarmData.privateInfo.metaData.scr + ''//
      self.personAlarmData.alarm.picUrl = AlarmData.privateInfo.pic.imageUrl
      self.personAlarmData.alarm.thumPicUrl = AlarmData.privateInfo.pic.thumImageUrl
      self.personAlarmData.alarm.rule.type = AlarmData.commonInfo.ruleType + ''
      self.personAlarmData.domainCode = AlarmData.commonInfo.domainCode
      self.personAlarmData.alarm.pictureId = AlarmData.privateInfo.pic.casefileId
      self.alarmPersonData.push(self.personAlarmData)
      if (self.alarmPersonData.length > self.maxAlarmDataLength) {
        self.alarmPersonData.splice(0, 1)
      }
    },
    async getScoketAlarmData (AlarmData) {
      let self = this
      self.VehicleAlarmData.taskinfo.taskId = AlarmData.commonInfo.taskId
      self.VehicleAlarmData.taskinfo.cameraId = AlarmData.commonInfo.cameraId
      self.VehicleAlarmData.taskinfo.cameraName = AlarmData.commonInfo.cameraName
      self.VehicleAlarmData.alarm.alarmId = AlarmData.commonInfo.alarmId
      self.VehicleAlarmData.alarm.alarmTime = AlarmData.commonInfo.alarmTime
      self.VehicleAlarmData.alarm.fileId = AlarmData.privateInfo.pic.fileId
      self.VehicleAlarmData.alarm.lprinfo.carColor = AlarmData.privateInfo.metaData.color + ''
      self.VehicleAlarmData.alarm.lprinfo.licensePlate = AlarmData.privateInfo.metaData.pnr
      self.VehicleAlarmData.alarm.lprinfo.plateColor = AlarmData.privateInfo.metaData.pcolor + ''
      self.VehicleAlarmData.alarm.lprinfo.plateType = AlarmData.privateInfo.metaData.ptype + ''
      self.VehicleAlarmData.alarm.lprinfo.vSub = AlarmData.privateInfo.metaData.vehicleSub + ''
      self.VehicleAlarmData.alarm.lprinfo.year = AlarmData.privateInfo.metaData.year
      self.VehicleAlarmData.alarm.lprinfo.vsb = AlarmData.privateInfo.metaData.vehiclebrand + ''// ?
      self.VehicleAlarmData.alarm.lprinfo.oid = AlarmData.privateInfo.pic.casefileId
      self.VehicleAlarmData.alarm.lprinfo.yl = AlarmData.privateInfo.metaData.yearlog + ''// ?
      if (configInfo.isUseImageProxy) {
        self.VehicleAlarmData.alarm.picUrl = configInfo.vcmImageUrl + encodeURIComponent(AlarmData.privateInfo.pic.imageUrl)
        self.VehicleAlarmData.alarm.thumPicUrl = configInfo.vcmImageUrl + encodeURIComponent(AlarmData.privateInfo.pic.thumImageUrl)
      }
      self.alarmData.push(self.personAlarmData)
    },
    getCountPass () {
      countPass().then(res => {
        if (res.data) {
          this.passStatisticList = [
            {
              title: '居民',
              iconClass: 'iconfl-renyuan passStatisticCardTitleIcon',
              value: res.data.day_person,
              unit: '人次',
              spanClass: 'passStatisticCardValue',
            },
            {
              title: '访客',
              iconClass: 'iconfl-renyuan passStatisticCardTitleIcon',
              value: res.data.day_visit_person,
              unit: '人次',
              spanClass: 'passStatisticCardValue',
            },
            {
              title: '居民',
              iconClass: 'icondkw_qiche passStatisticCardTitleIcon_',
              value: res.data.day_vehicle,
              unit: '车次',
              spanClass: 'passStatisticCardValue_',
            },
            {
              title: '访客',
              iconClass: 'icondkw_qiche passStatisticCardTitleIcon_',
              value: res.data.day_visit_vehicle,
              unit: '车次',
              spanClass: 'passStatisticCardValue_',
            },
            // {
            //   title: '陌生人',
            //   iconClass: 'iconfl-renyuan',
            //   value: 0,
            //   unit: '人次'
            // },
          ]
        }
      })
    },
    getPersonnelPassSnap () {
      let param = {
        pageNum: 1,
        pageSize: this.maxAlarmDataLength
      }
      this.personAlarmList = []
      personnelPassSnap(param).then(res => {
        if (res.rows && res.rows.length > 0) {
          this.personAlarmList = res.rows
        } else {
          this.personAlarmList = [{
            address: '',
            communityChildId: '',
            visitorType: '',
            name: '',
            communityId: '',
            einlass: '',
            imageUrl: this.defaultPersonImg
          }]
          // this.personAlarmList = [{
          //   taskId: '',
          //   name: '',
          //   idnumber: '',
          //   genderCode: '',
          //   deviceId: '',
          //   locationMarkTime: '',
          //   pic: this.defaultPersonImg
          // }]
        }
      })
    },
    getVehiclePassSnap () {
      let param = {
        pageNum: 1,
        pageSize: this.maxAlarmDataLength
      }
      vehiclePassSnap(param).then(res => {
        if (res.rows && res.rows.length > 0) {
          this.vehicleAlarmList = res.rows
        } else {
          this.vehicleAlarmList = [{
            channelName: '',
            einlass: '',
            imageUrl: this.defaultVehicleImg,
            vehiclePlate: ''
          }]
          // this.vehicleAlarmList = [{
          //   taskId: '',
          //   plate: 'asd',
          //   name: '',
          //   idnumber: '',
          //   genderCode: '',
          //   deviceId: '',
          //   locationMarkTime: '',
          //   pic: this.defaultVehicleImg
          // }]
        }
      })
    },
    getEventNumberStatistics () {
      eventNumberStatistics().then(res => {
        let a = 0
        this.alarmEventTypeStatistic = []
        if (res.data) {
          // {"msg":"操作成功","code":200,"data":[{"num":1,"name":"人群聚集"},{"num":1,"name":"凌晨出行"},{"num":1,"name":"昼伏夜出"},{"num":1,"name":"频繁出入"}]}
          res.data.forEach(element => {
            let aInfo = {
              typeName: element.name,
              value: element.num
            }
            this.alarmEventTypeStatistic.push(aInfo)
            a += element.num
          });
          this.alarmEventNum = a
        }
      })
    },
    getCountCaringEventByType () {
      countCaringEventByType().then(res => {
        let a = 0
        this.caringEventTypeStatistic = []
        if (res.data) {
          res.data.forEach(element => {
            let aInfo = {
              type: element.type,
              value: element.num
            }
            this.caringEventTypeStatistic.push(aInfo)
            a += element.num
          });
          this.caringEventNum = a
        }
      })
    },
    ///流动人口
    getFlowPopulation () {
      getFlowPopulation().then(res => {
        let timeSpanString = []
        let floatIncreaseList = []
        let floatList = []
        if (res.data) {
          res.data.floatIncreaseList.forEach(IncItem => {
            timeSpanString.push(IncItem.name)
            floatIncreaseList.push(IncItem.num)
          })
          res.data.floatList.forEach(flItem => {
            floatList.push(flItem.num)
          })
        }
        this.personFlowInfo = {
          xAxis: timeSpanString,
          lines: [
            {
              name: '新增流动人口（百）',
              data: floatIncreaseList
            }, {
              name: '租住人口（个）',
              data: floatList
            }
          ]
        }
      })
    },
    ///居住类型
    getPerHouseInformStatisticse () {
      getPerHouseInformStatisticse().then(res => {
        let infoList = []
        if (res.data) {
          res.data.forEach(item => {
            let info = {
              value: item.num,
              name: item.name
            }
            infoList.push(info)
          })
          this.checkPersonFloatInfo(infoList)
          this.personFloatInfo = {
            title: '居住类型',
            dataList: infoList
          }
        }
      })
    },
    //对像数组排序
    compare (pre, next) {
      if (pre.value < next.value) {
        return 1;
      } else if (pre.value > next.value) {
        return -1;
      } else {
        return 0;
      }
    },
    isHaveItem (list, prop, checkPropName) {
      let result = -1
      if (list && list.length > 0) {
        for (let index = 0; index < list.length; index++) {
          const element = list[index];
          if (element[prop] === checkPropName) {
            result = index
            break
          }
        }
      }
      return result
    },
    ///check居住类型，最多只留4+1类（各种居住类型+其他）
    checkPersonFloatInfo (list) {
      let maxLength = 4
      if (list) {
        let unitPropName = 'name'
        let unitItem = '其他'
        let unitItemValue = 0
        let unitItemIndex = this.isHaveItem(list, unitPropName, unitItem)
        //判断是否已经存在‘其他’
        if (unitItemIndex >= 0) {
          let temp = list.splice(unitItemIndex, 1)
          unitItemValue = temp[0].value
        } else {
        }
        if (list.length > maxLength) {
          let sortList = list.sort(this.compare)
          for (let index = (maxLength - 1); index < sortList.length;) {
            let temp = sortList.splice(index, 1)
            unitItemValue += temp[0].value
          }
          list.push({
            value: unitItemValue,
            name: unitItem
          })
        }
      }
      return list
    },
    getTestData () {
      this.alarmEventTypeStatistic = [
        {
          type: '01',
          typeName: '凌晨出行',
          value: 3,
        },
        {
          type: '02',
          typeName: '频繁出入',
          value: 12,
        },
        {
          type: '03',
          typeName: '人群聚集',
          value: 3,
        },
        {
          type: '04',
          typeName: '其他',
          value: 2,
        }
      ]
      this.alarmEventNum = 20
      this.caringEventTypeStatistic = [
        {
          type: '01',
          typeName: '长期未出现',
          value: 5,
        },
        {
          type: '02',
          typeName: '生日提醒',
          value: 8,
        },
        {
          type: '03',
          typeName: '频繁出现',
          value: 3,
        },
        {
          type: '04',
          typeName: '其他',
          value: 2,
        }
      ]
      this.caringEventNum = 18
      this.personAlarmList = [
        {
          taskId: '可疑人员',
          name: '张丽',
          idnumber: '410112198012116542',
          genderCode: '女',
          deviceId: '小区南门入口',
          locationMarkTime: '2020/12/23 12:50:23',
          pic: this.testPersonImage
        },
        {
          taskId: '可疑人员',
          name: '王弦',
          idnumber: '4110112198012116542',
          genderCode: '女',
          deviceId: '小区南门入口',
          locationMarkTime: '2020/12/23 12:41:31',
          pic: this.testPersonImage
        },
        {
          taskId: '可疑人员',
          name: '张美丽',
          idnumber: '310112198012116542',
          genderCode: '女',
          deviceId: '小区南门入口',
          locationMarkTime: '2020/12/23 12:30:34',
          pic: this.testPersonImage
        },
        {
          taskId: '可疑人员',
          name: '李二小',
          idnumber: '310112198012116546',
          genderCode: '女',
          deviceId: '小区南门入口',
          locationMarkTime: '2020/12/23 12:25:34',
          pic: this.testPersonImage
        },
      ]
      this.vehicleAlarmList = [
        {
          taskId: '可疑车辆',
          plate: '苏P0523N',
          name: '张丽',
          idnumber: '410112198012116542',
          genderCode: '女',
          deviceId: '小区南门入口',
          locationMarkTime: '2020/12/23 12:50:23',
          pic: this.testVehicleImage
        },
        {
          taskId: '可疑人员',
          name: '王弦',
          idnumber: '4110112198012116542',
          genderCode: '女',
          deviceId: '小区南门入口',
          locationMarkTime: '2020/12/23 12:41:31',
          pic: this.testVehicleImage
        },
        {
          taskId: '可疑人员',
          name: '张美丽',
          idnumber: '310112198012116542',
          genderCode: '女',
          deviceId: '小区南门入口',
          locationMarkTime: '2020/12/23 12:30:34',
          pic: this.testVehicleImage
        },
        {
          taskId: '可疑人员',
          name: '李二小',
          idnumber: '310112198012116546',
          genderCode: '女',
          deviceId: '小区南门入口',
          locationMarkTime: '2020/12/23 12:25:34',
          pic: this.testVehicleImage
        },
      ]
      this.personFloatInfo = {
        title: '居住类型',
        dataList: [
          {
            value: 1232,
            name: '非自有住房人口'
          }, {
            value: 2320,
            name: '自有住房人口'
          }
        ]
      }
      let beginTime = new Date()
      let endTime = new Date()
      beginTime.setTime(beginTime.getTime() - 7 * 24 * 3600 * 1000)
      endTime.setTime(endTime.getTime() - 1 * 24 * 3600 * 1000)
      let timeSpanString = this.$cf.getTimeSpanStringList(beginTime, endTime)
      this.personFlowInfo = {
        xAxis: timeSpanString,
        lines: [
          {
            name: '新增流动人口（百）',
            data: [120, 132, 101, 134, 90, 230, 210]
          }, {
            name: '租住人口（个）',
            data: [220, 182, 191, 234, 290, 330, 310]
          }
        ]
      }
    }
  },
  computed: {
    dataHeight () {
      let tableHeight = this.$store.getters.dataEleHeight - this.formHeight - 100
      if (tableHeight < 80) tableHeight = 80
      return tableHeight
    }
  },
  watch: {
    //dataInfo: {
    //handler: function (newValue) {
    //console.log('new value change')
    //},
    //deep: true,
    //immediate: true
    //}
  },
  beforeDestroy () { }
}
</script>
<style lang="scss" scoped>
@import "../../assets/styles/table.scss";
.main {
  padding: 0px 24px;
  width: 100%;
  height: 100%;
  flex-flow: row nowrap;
  overflow-x: auto;
  overflow-y: auto;
}
.passStatistic {
  width: 100%;
  height: 64px;
}
.alarmList {
  width: 100%;
  height: 264px;
}
.event {
  width: 100%;
  height: 160px;
}
.personFloat {
  width: 100%;
  height: 270px;
}
.passStatisticCard {
  // background-image: url("../../assets/images/home/statisticCardbg.png");
  width: 168px;
  height: 22px;
  margin-right: 51px;
  display: flex;
  flex-direction: row;
  line-height: 22px;
}
.noMarginRight {
  margin-right: 0px;
}
.passStatisticCardTitleIcon {
  font-size: 14px;
  color: #f7b500;
}
.passStatisticCardTitleIcon_ {
  font-size: 14px;
  color: #32c5ff;
}
.passStatisticCardTitle {
  width: 71px;
  font-size: 14px;
  color: #f7b500;
  border-right: 1px solid #979797;
}
.passStatisticCardValue {
  // width: 95px;
  width: auto;
  margin-left: 4px;
  font-size: 24px;
  color: #f7b500;
}
.passStatisticCardValue_ {
  // width: 95px;
  width: auto;
  margin-left: 4px;
  font-size: 24px;
  color: #32c5ff;
}
.alarmNum_Title {
  font-size: 30px;
  font-weight: bold;
  margin-right: 8px;
  color: #e02020;
}
.alarmNum_content {
  font-size: 14px;
  // font-weight: bold;
  margin-right: 8px;
  color: #e02020;
}
.alarmPing {
  width: 100%;
  height: 22px;
  margin-left: 64px;
  margin-right: 64px;
  background-image: url("../../assets/images/home/alarmRingBg.png");
  // background-size: 100%; //此处要注意，可以填cover也可以填100%。建议填100%
  background-repeat: no-repeat;
}
.empty1 {
  height: 125px;
}
.empty2 {
  height: 254px;
}
.box-col {
  height: 32px;
}
.title1Icon {
  color: #00c0ff;
  font-size: 24px;
}
.title1Contain {
  margin-top: 20px;
  margin-bottom: 0px;
}
.title1 {
  // font-size: 16px;
  // color: #ffffff;
  // font-weight: bolder;

  // width: 64px;
  height: 22px;
  font-size: 16px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: #000000;
  line-height: 22px;
}
.houseNumberBoxRight {
  padding-top: 5px;
  height: 260px;
  background-color: #ffffff;
  margin-right: 6px;
  margin-top: 4px;
}
.alarmCard {
  width: 181.07px;
  height: 200px;
  // background-image: url("../../assets/images/home/alarmCardBg.png");
  padding: 8px;
  background-color: #ffffff;
}
.alarmCardImage {
  width: 161px;
  height: 104px;
}
.alarmCardTitle {
  width: 100%;
  height: 20px;
  font-size: 14px;
  font-weight: 600;
  margin-left: 4px;
  margin-top: 9px;
  color: #999999;
}
.alarmCardContent {
  margin-left: 4px;
  margin-top: 3px;
  font-size: 12px;
  color: #999999;
}

.el-scrollbar {
  height: 100%;
  .el-scrollbar__wrap {
    overflow-x: hidden !important;
  }
}
.el-scrollbar__wrap {
  overflow-x: hidden !important;
}

.commonTitle {
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: #000000;
  font-size: 16px;
  margin-left: 4px;
  margin-right: 4px;
}

.icongaojing {
  color: #00c0ff;
}
.iconshijian {
  color: #00c0ff;
}
.el-table {
  color: #999999;
}

.el_image {
  widows: 80px;
  height: 80px;
}
</style>