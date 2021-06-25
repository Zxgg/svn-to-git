<template>
  <div class="Main"
       ref="MainView">
    <div class="TopContainer">
      <el-select class="searchSelect"
                 id="searchInput"
                 v-model="selectRemoteDevice"
                 filterable
                 clearable
                 remote
                 reserve-keyword
                 placeholder="输入设备名称（最多展示10条）"
                 :remote-method="remoteMethodGetDevice"
                 :loading="loading"
                 @change="selectRemoteDeviceChange">
        <el-option v-for="item in remoteDeviceList"
                   :key="item.sn"
                   :label="item.captureName"
                   :value="item.sn">
        </el-option>
      </el-select>
      <span class="icon iconfont"
            :style="{ display: cameraSearchButtonVisible }"
            @click="cameraSearchPlay(searchCamera)">
        &#xe605;
      </span>
      <span class="icon iconfont"
            :style="{ display: cameraSearchButtonVisible }"
            @click="mapLocation(searchCamera)">
        &#xe755;
      </span>
    </div>
    <div class="CenterContainer">
      <cameraTree :analysisIconVisible="analysisIconVisible"
                  :showCheckBox="showCheckBox" />
    </div>
    <!-- <div class="BottomContainer">
      <PTZControl />
    </div> -->
  </div>
</template>
<script>
import cameraTree from '@/components/SmartVideo/RelatimeVideo/cameraTree'
import PTZControl from '@/components/SmartVideo/RelatimeVideo/PTZControl'
import { getRole, getDeviceList, getDeviceOrg } from "@/api/smartVideo/device"
import { getOrgList } from "@/api/system/organization"
// import { getRole } from "@/api/system/role"

export default {
  components: { cameraTree, PTZControl },
  data () {
    return {
      orgList: [],  // 组织机构列表
      orgIDsStr: '', // 组织机构字符串
      searchInputVisible: 'none',
      cameraSearchButtonVisible: 'none',  // 
      remoteDeviceList: [],  // 搜索框选项
      selectRemoteDevice: '',  // 搜索框内容
      loading: false,  // 搜索框加载状态
      searchCamera: null,
      analysisIconVisible: true,
      showCheckBox: false,
    }
  },
  created () { },
  filters: {},
  methods: {
    // 获取选项
    remoteMethodGetDevice (queryStr) {
      if (!(this.orgList && this.orgList !== null && this.orgList.length > 0)) {
        this.getOrgList()
      }
      if (queryStr && queryStr !== null && queryStr.length > 0) {
        // let orgCode = this.$store.getters.getCurrentOrganizationIDs
        // if (this.orgIDsStr === '') this.getRoleInfo()
        this.loading = true
        let queryData = {
          currentPage: 1,
          pageSize: 10,
          body: {
            'orgId': this.orgIDsStr,
            'captureName': queryStr
          }
        }

        // 获取组织下直属设备(设备名称中包含指定字段)
        getDeviceList(queryData).then(response => {
          if (response.data != null) {
            this.remoteDeviceList = response.data
          }
          this.loading = false
        }).catch(err => {
          this.$message.error(err)
          this.remoteDeviceList = []
          this.loading = false
        })
      }
    },
    // 获取组织机构列表
    getOrgList () {
      let objData = {
        currentPage: 1,
        pageSize: 20000
      }
      getOrgList(objData).then(response => {
        if (response.data != null) {
          this.orgList = response.data
        }
      })
    },
    // 获取角色信息
    getRoleInfo () {
      getRole('f0a051d9-e6b6-4652-a5fa-dd6ce0d23b30').then(response => {
        if (response.data != null) {
          this.orgIDsStr = response.data.organizationID
        }
      })
    },
    // 搜索框选项切换回调
    selectRemoteDeviceChange (value) {
      if (value && value !== null && value.length > 0) {
        let list = this.remoteDeviceList.filter(item => item.sn === value)
        if (list && list != null && list.length > 0) {
          this.searchCamera = list[0]
          if (this.searchCamera.uiStatus != null && this.searchCamera.uiStatus === 1) {
            this.cameraSearchButtonVisible = ''
          } else {
            this.cameraSearchButtonVisible = 'none'
          }
          let deviceName = list[0].captureName
          let queryData = {
            currentPage: 1,
            pageSize: 2,
            body: {
              'deviceId': value
            }
          }
          /// 获取设备直属组织
          let self = this
          getDeviceOrg(queryData).then(data => {
            if (data && data !== null && data.data && data.data !== null && data.data.length > 0) {
              // this.infoTableData = data.data
              let paraentOrgId = data.data[0].orgId// orgId的最初值从 组织架构-设备 关系表中来
              if (paraentOrgId === '-1' || paraentOrgId === '') { // 未分配
                for (let i = 1; i < data.data.length; i++) {
                  if (data.data[i].orgId && data.data[i].orgId !== null && data.data[i].orgId !== '-1' && data.data[i].orgId !== '') paraentOrgId = data.data[i].orgId
                }
              }
              if (!(self.orgList && self.orgList !== null && self.orgList.length > 0)) return false
              let paraentOrgNames = '      '
              // 循环获取直到最顶层，从组织架构表中取值
              while (paraentOrgId !== '-1' && paraentOrgId !== '') {
                let organ = self.orgList.filter(item => item.code === paraentOrgId)
                if (organ && organ != null && organ.length > 0) {
                  paraentOrgId = organ[0].superiorID
                  paraentOrgNames += (organ[0].name + '/')
                } else {
                  break
                }
              }
              if (paraentOrgNames.lastIndexOf('/') === (paraentOrgNames.length - 1)) {
                paraentOrgNames = paraentOrgNames.substring(0, paraentOrgNames.length - 1)
              }
              self.selectRemoteDevice = deviceName + paraentOrgNames
            }
          })
        }
      }
    },
    cameraSearchPlay (data) {
      this.$Notice.$emit('cameravideoplay', data)
    },
    mapLocation (data) {
      this.$Notice.$emit('mapLocation', data)
    },
    test () {
      console.log("左半父组件收到")
    }
  },
  beforeMount () {
    this.getOrgList()
    this.getRoleInfo()
  },
  mounted () {
  }
}
</script>
<style lang="scss" scoped>
.Main {
  width: 100%;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
  // background: rgb(156, 28, 135);
}
.TopContainer {
  width: 100%;
  height: 50px;
  border: 1px solid #005982;
  margin: 0 auto;
  overflow: auto;
  // background: red;
}
.CenterContainer {
  width: 100%;
  // height: 100%;
  height: calc(100% - 50px);
  // background: green;
}
.BottomContainer {
  width: 100%;
  height: 320px;
  border: 1px solid #005982;
  margin: 0 auto;
  overflow: auto;
  // background: blue;
}
// 搜索框
.TopContainer .searchSelect {
  top: 25%;
  width: 85%;
  float: center;
}
.iconfont {
  margin-left: 2px;
  cursor: pointer;
  line-height: 48px;
}
</style>