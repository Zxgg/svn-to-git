<template>
  <el-row class="Main">
    <!-- 左侧社区小区信息 -->
    <el-col class="communityInfo">
      <div class="divStackPanel_left textLeft">
        <el-form :model="currentComunity"
                 ref="queryForm"
                 :inline="true"
                 label-width="68px">
          <el-form-item label="社区名称"
                        prop="communityId">
            <el-select v-model="currentComunity.communityId"
                       filterable
                       clearable
                       style="width:120px;"
                       @change="getCommunityChildDictory"
                       placeholder="请选择社区">
              <el-option v-for="dict in communityOptions"
                         :key="dict.id"
                         :label="dict.name"
                         :value="dict.id" />
            </el-select>
          </el-form-item>
          <el-form-item label="小区名称"
                        v-if="false"
                        prop="communityChildId">
            <el-select v-model="currentComunity.communityChildId"
                       filterable
                       clearable
                       style="width:180px;"
                       placeholder="请选择小区">
              <el-option v-for="dict in communityChildOptions"
                         :key="dict.id"
                         :label="dict.name"
                         :value="dict.id" />
            </el-select>
          </el-form-item>
          <el-button size="mini"
                     v-show="false"
                     type="text"
                     icon="el-icon-refresh"
                     @click="saveChange"
                     v-hasPermi="['communityUnit:community:query']">保存</el-button>
        </el-form>

      </div>
    </el-col>
    <!-- 中间填充 -->
    <el-col class="centerArea">
    </el-col>
    <!-- 右侧告警列表 -->
    <el-col class="alarmInfo">
      <div class="divStackPanel_HorizontalRight"
           v-if="false">
        <el-badge :value="unread"
                  class="item">
          <i class="icon iconfont  icongaojing HorizontalMargin alarmIcon"
             @click="alarmBtnClick"></i>
        </el-badge>

      </div>
      <div>

      </div>
    </el-col>
  </el-row>
</template>
<script>
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import { mapGetters, mapState } from "vuex";
//import cameraTreeLazy from '@/components/cameraTree/cameraTree_lazy'
export default {
  //components: { cameraTreeLazy },
  //props: {dataInfo: {type: Object,default: null}},
  data () {
    return {
      that: this,
      showData: {},
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 100000,
        communityId: null,
        communityName: null,
        communityChildId: null,
        communityChildName: null
      },
      //社区列表
      communityOptions: [],
      //小区列表
      communityChildOptions: [],
      //当前是否已经展示告警列表
      isShowAlarmPanel: false,
      //当前告警弹窗的实例
      alarmPanel: null,
      //告警列表
      alarmPersonData: [{
        Name: '张三',
        DeviceID: '闸北小区二期南门入口',
        LocationMarkTime: '2020/03/25 12:23:22',
        PersonAppearTime: '2020/03/25 12:23:22',
        taskName: '测试任务'
      }],
      alarmHtml: '',
      //存储的最大告警数量
      maxAlarmDataLength: 5,
      //未读的告警数量
      unread: 0
    }
  },
  created () {
    this.getCommunityDictory()
  },
  mounted () {
    let self = this
    // this.currentComunity.communityId = this.testData.shouye.currentCommunity.id
    if (this.currentComunity.communityId && this.currentComunity.communityId.length > 0) {
      this.getCommunityChildDictory(this.currentComunity.communityId, false)
    }
    this.alarmPersonData = []
    this.$WebScoket.$on('PersonAlarmEvent', function (AlarmData) { // 接收实时告警
      if (AlarmData !== null && AlarmData.length > 0) {
        AlarmData.forEach(AlarmDataItem => {
          if (JSON.stringify([AlarmDataItem]) !== '{}') {
            if (JSON.stringify(AlarmDataItem.privateInfo) !== '{}' && JSON.stringify(AlarmDataItem.commonInfo) !== '{}') {
              self.getScoketPersonAlarmData(AlarmDataItem)
            }
          }
        })
      }
    })
  },
  filters: {},
  methods: {
    getCommunityDictory () {
      this.communityOptions = []
      let param = {
        pageNum: 1,
        pageSize: 100000
      }
      listCommunity(param).then(response => {
        this.communityOptions = response.rows;
      });
    },
    getCommunityChildDictory (communityId, resetFlag = true) {
      console.log(communityId, 'nisdakjdsklajdlasj')
      if (resetFlag)
        this.currentComunity.communityChildId = null
      this.communityChildOptions = []
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId
      }
      listCommunity_child(param).then(response => {
        this.communityChildOptions = response.rows;
      });
    },
    saveChange (newValue, oldValue) {
      if (newValue && oldValue) {
        let communityId = this.currentComunity.communityId
        if (communityId && communityId.length > 0) {
          this.$store.dispatch('settings/changeCommunityId', communityId)
        } else {
          this.$store.dispatch('settings/changeCommunityId', '')
        }
        let communityChildId = this.currentComunity.communityChildId
        if (communityChildId && communityChildId.length > 0) {
          this.$store.dispatch('settings/changeCommunityChildId', communityChildId)
        } else {
          this.$store.dispatch('settings/changeCommunityChildId', '')
        }

      }
    },
    alarmBtnClick () {
      this.unread = 0
      if (this.alarmPanel) {
        // this.alarmPanel.message = this.alarmHtml + this.alarmHtml
        this.alarmPanel.close()
        this.alarmPanel = null
      }
      else {
        let autoClose = 2000
        this.alarmHtml = this.getAlarmHtml()
        this.isShowAlarmPanel = false
        if (this.alarmPersonData && this.alarmPersonData.length > 0) {
          autoClose = 0
          this.isShowAlarmPanel = true
        }
        // type: 'warning',
        this.alarmPanel = this.$notify({
          title: '实时告警',
          duration: autoClose,
          offset: 35,
          customClass: 'elNotification',
          position: 'bottom-right',
          dangerouslyUseHTMLString: true,
          onClose: this.panelClose,
          message: this.alarmHtml
        });
      }

    },
    getAlarmHtml () {
      let htmlTxt = '<strong style="width: 80px;color: white;font-size: 16px;">当前无告警</strong>'
      if (this.alarmPersonData && this.alarmPersonData.length > 0) {
        htmlTxt = ''
        this.alarmPersonData.forEach(alarm => {
          // 最外层DIV
          htmlTxt += '<div style="width: 300px;height: 170px;margin-right: 20px;background: rgba(50, 55, 109, 0.4);;display: flex;flex-flow: nowrap;justify-content: flex-start;align-items: center;text-align: center;">'
          // 左侧图片DIV
          htmlTxt += '<div style="width: 50%;height: 100%;background: transparent;padding: 4px;">'
          htmlTxt += '<img src="'
          htmlTxt += 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1608291358078&di=80b16a72cdb00b77d6bbcb4e0dfabd58&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn10%2F309%2Fw534h575%2F20180926%2Fa837-hhuhisn1021919.jpg'
          htmlTxt += '" alt="some_text" style="width: 100%;height: 100%;">'
          htmlTxt += '</div>'
          // 右侧告警信息
          htmlTxt += '<div style="width: 50%;height: 165px;background: transparent;">'
          // 姓名
          htmlTxt += '<div style="text-align: left;margin-left: 8px;line-height: 32px;">'
          htmlTxt += '<span style="width: 80px;color: white;font-size: 16px;">'
          htmlTxt += '姓名:'
          htmlTxt += '</span>'
          htmlTxt += '<span style="color: white;font-size: 16px;">'
          htmlTxt += alarm.Name
          htmlTxt += '</span>'
          htmlTxt += '</div>'
          // 时间
          htmlTxt += '<div style="text-align: left;margin-left: 8px;">'
          htmlTxt += '<span style="width: 80px;color: white;font-size: 14px;">'
          htmlTxt += '时间:'
          htmlTxt += '</span>'
          htmlTxt += '<span style="color: white;font-size: 14px;">'
          htmlTxt += alarm.LocationMarkTime
          htmlTxt += '</span>'
          htmlTxt += '</div>'
          //地点
          htmlTxt += '<div style="text-align: left;margin-left: 8px;">'
          htmlTxt += '<span style="width: 80px;color: white;font-size: 14px;">'
          htmlTxt += '地点:'
          htmlTxt += '</span>'
          htmlTxt += '<span style="color: white;font-size: 14px;">'
          htmlTxt += alarm.DeviceID
          htmlTxt += '</span>'
          htmlTxt += '</div>'
          htmlTxt += '</div>'

          htmlTxt += '</div>'
        });
      }
      // htmlTxt = '<div style="width: 200px;height: 170px;background: red;"></div>'
      return htmlTxt
    },
    panelClose () {
      console.log('panelClose')
      this.isShowAlarmPanel = false
      if (this.alarmPanel) {
        this.alarmPanel = null
      }
    },
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
    refreshAlarmData () {
      if (this.alarmPanel) {
        this.alarmPanel.message = this.getAlarmHtml()
      }
      else {
        this.unread++;
      }
    }
  },
  computed: {
    ...mapGetters(["currentComunity"]),
  },
  watch: {
    currentComunity: {
      handler: function (val, oldVal) {
        this.saveChange(val, oldVal)
      },
      deep: true,
      immediate: true
    }
  },
  beforeDestroy () { }
}
</script>
<style lang="scss" scoped>
.Main {
  width: 100%;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
  // background-image: titleImage;
  background: -webkit-linear-gradient(
      bottom,
      hsla(234, 54, 41, 1),
      hsla(234, 54, 41, 0),
      rgba(48, 54, 105, 0.8)
    )
    no-repeat;
  // background-repeat: repeat;
  // background-size: 100% 100%;
}
.communityInfo {
  width: 700px;
  height: 100%;
  margin: 0px 0px;
  padding: 0px;
}
.alarmInfo {
  width: 610px;
  height: 100%;
  margin: 0px 0px;
  padding: 0px;
}
.centerArea {
  width: calc(100% - 700px - 610px);
  height: 100%;
  margin: 0px 0px;
  padding: 0px;
}
.el-form-item__label {
  color: red;
}
.el-badge {
  margin-top: 4px;
  margin-top: 12px;
  margin-right: 22px;
}
.alarmIcon {
  color: #f7b500;
  font-size: 25px;
  // margin-top: 20px;
  margin-right: 10px;
}
</style>
<style lang="scss">
.el-notification {
  background: rgba(50, 55, 109, 0.95);
  width: 380px;
}
.el-notification__title {
  font-weight: bold;
  font-size: 16px;
  color: #00ffff;
  margin: 0;
}
</style>