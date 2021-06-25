<template>
  <div class="Main"
       ref="MainView"
       @scroll="rollingLoad">
    <div class="CenterContainer">
      <div class="tree-wrapper1"
           v-loading="loading"
           element-loading-spinner="el-icon-loading"
           element-loading-background="rgba(0, 0, 0, 0.4)">
        <el-tree lazy
                 accordion
                 :data="data"
                 empty-text="暂无数据"
                 ref="deviceOrgTree"
                 node-key="sn"
                 highlight-current
                 :props="defaultProps"
                 :expand-on-click-node="false"
                 :show-checkbox="showCheckBox"
                 @check-change="handleCheckChange"
                 @node-click="handleNodeClick"
                 @node-expand="HandleNodeExpand"
                 class="tree">
          <span class="custom-tree-node"
                slot-scope="{ node, data }">
            <!-- 组织机构 -->
            <span v-if="data.dataType == 1">
              <span class="icon iconfont">
                &#xe654;
              </span>
              <!-- 名称 -->
              <span class="icon iconfont">
                {{ node.label }}
              </span>
              <!-- 组织机构下设备在线数/设备总数 -->
              <span class="icon iconfont">
                {{ '(' + data.onlineCount + '/' + data.deviceCount + ')' }}
              </span>
            </span>

            <span v-if="data.dataType == 0">
              <!-- 设备 -->
              <!-- 设备在线 -->
              <span v-if="data.uiStatus != null && data.uiStatus==1">
                <!-- 设备类型 -->
                <span v-if="data.captureType==1"
                      class="icon iconfont">
                  &#xe623;
                </span>
                <span v-if="data.captureType==2"
                      class="icon iconfont">
                  &#xe6ba;
                </span>
                <span v-if="data.captureType==3"
                      class="icon iconfont">
                  &#xe6c2;
                </span>
                <span v-if="data.captureType==4"
                      class="icon iconfont">
                  &#xe6c3;
                </span>
                <span v-if="data.captureType==5"
                      class="icon iconfont">
                  &#xe6e0;
                </span>
              </span>
              <!-- 设备离线 -->
              <span v-if="data.uiStatus != null && data.uiStatus==0">
                <!-- 设备类型 -->
                <span v-if="data.captureType==1"
                      style="color:#646464"
                      class="icon iconfont">
                  &#xe623;
                </span>
                <span v-if="data.captureType==2"
                      style="color:#646464"
                      class="icon iconfont">
                  &#xe6ba;
                </span>
                <span v-if="data.captureType==3"
                      style="color:#646464"
                      class="icon iconfont">
                  &#xe6c2;
                </span>
                <span v-if="data.captureType==4"
                      style="color:#646464"
                      class="icon iconfont">
                  &#xe6c3;
                </span>
                <span v-if="data.captureType==5"
                      style="color:#646464"
                      class="icon iconfont">
                  &#xe6e0;
                </span>

              </span>

              <!-- 名称 -->
              <!-- 在线 -->
              <span class="icon iconfont"
                    v-if="data.uiStatus != null && data.uiStatus==1">
                {{ node.label }}
              </span>
              <!-- 离线 -->
              <span class="icon iconfont"
                    style="color:#646464"
                    v-if="data.uiStatus != null && data.uiStatus==0">
                {{ node.label }}
              </span>
              <!-- 在线 -->
              <span v-if="data.uiStatus != null && data.uiStatus == 1">
                <!-- 操作按钮 -->
                <span v-if="analysisIconVisible"
                      class="icon iconfont"
                      @click="cameraIconClick(1,data)">
                  &#xe605;
                </span>
                <span class="icon iconfont"
                      @click="mapLocation(data)">
                  &#xe755;
                </span>
                <!-- <span class="icon iconfont"
                      @click="reportDevFault(data)">
                  &#xe687;
                </span> -->

                <!-- 智能分析状态 -->
                <!-- <span v-if="analysisIconVisible">
                  <span id="AnalysisIcon"
                        class="icon iconfont"
                        v-if="data.taskType != null && data.taskType.indexOf('0') != -1">
                    &#xe68c;
                  </span>
                  <span id="AnalysisIcon"
                        class="icon iconfont"
                        v-if="data.taskType != null && data.taskType.indexOf('1') != -1">
                    &#xe62c;
                  </span>
                  <span id="AnalysisIcon"
                        class="icon iconfont"
                        v-if="data.taskType != null && data.taskType.indexOf('2') != -1">
                    &#xe701;
                  </span>
                  <span id="AnalysisIcon"
                        class="icon iconfont"
                        v-if="data.taskType != null && data.taskType.indexOf('4') != -1">
                    &#xe602;
                  </span>
                  <span id="AnalysisIcon"
                        class="icon iconfont"
                        v-if="data.taskType != null && data.taskType.indexOf('9') != -1">
                    &#xe603;
                  </span>
                </span> -->

              </span>
            </span>
          </span>
        </el-tree>
      </div>
    </div>
    <!-- <el-dialog title="请输入"
               :visible.sync="DevFaultDialogVisible"
               width="400px"
               label-width="100px"
               append-to-body
               center
               custom-class="DevFaultDiaolg">
      <div class="DevFaultDiv">
        <el-col class="DevFaultdiv_col">
          <el-form id="DevFaultForm"
                   label-position="top"
                   label-width="160px"
                   ref="DevFaultData"
                   :model="DevFaultData">
            <div class="el_form_itemDiv">
              <el-form-item label="设备故障描述"
                            prop="note">
                <el-input class="DevFaultDiaolg_input"
                          type="textarea"
                          v-model="DevFaultData.note" />
              </el-form-item>
            </div>
          </el-form>
        </el-col>
        <span slot="footer"
              class="dialog_footer">
          <el-button @click="saveReportDevFault">保 存</el-button>
          <el-button @click="cancelMethod">取 消</el-button>
        </span>
      </div>
    </el-dialog> -->

    <el-dialog :visible.sync="gisOpen"
               append-to-body
               :before-close="closeDialog">
      <gisView :passLocationObject="passLocation_"
               :passTrackObject=null
               style="height:600px;width:800px; margin:0 auto" />
    </el-dialog>
  </div>
</template>
<script>
import { getRole, getDeviceList, addDeviceFault } from "@/api/smartVideo/device"
import { getOrgTree } from "@/api/system/organization"
import gisView from '@/views/GISView/GISView'
// import { getRole } from "@/api/system/role"

export default {
  props: {
    analysisIconVisible: {
      type: Boolean,
      default: true,
      required: false
    },
    showCheckBox: {
      type: Boolean,
      default: false,
      required: false
    }
  },
  components: { gisView },
  data () {
    return {
      passLocation_: null,
      data: [], // 树数据
      defaultProps: {
        children: 'children',
        label: 'name',
        isLeaf: 'isLeaf'
      },
      loading: false, // 加载状态
      scrollLoadingFlag: false,
      checkedData: null,
      scrollTimer: null,
      pl: 50, // 分页加载pageSize

      orgIDsStr: '',
      DevFaultDialogVisible: false,
      DevFaultData: {
        departmentName: '',
        note: '',
        cameraSN: '',
        declareTime: '',
        departmentID: '',
        notifier: '',
        faultStatus: '',
        id: '',
        cameraName: ''
      },
      cameraInfoData: {
        onlineCount: 0,
        taskType: null,
        code: '',
        parentOrgCode: '',
        deviceCount: 0,
        children: null,
        captureType: 2,
        dataType: '',
        name: '',
        monitorPointType: 0,
        sn: '',
        uiStatus: 0
      },
      gisOpen: false,
    }
  },
  created () { },
  filters: {},
  methods: {
    closeDialog (done) {
      console.log('dialog close ..');
      // this.passTrack = [];
      this.passLocation_ = null;
      this.gisOpen = false;
    },
    // 获取角色信息
    getRoleInfo () {
      getRole('f0a051d9-e6b6-4652-a5fa-dd6ce0d23b30').then(response => {
        if (response.data != null) {
          this.orgIDsStr = response.data.organizationID
          this.loadTreeTop()
        }
      })
    },
    // 获取组织机构树（只显示顶级）
    loadTreeTop () {
      let self = this
      let orgCodeList = self.orgIDsStr
      let queryData = {
        code: orgCodeList,
        codeList: orgCodeList
      }

      getOrgTree(queryData).then(responseTree => {// 获取根节点组织机构数据
        if (responseTree.data != null && responseTree.data.length > 0) {
          responseTree.data.forEach(itemOrg => {
            let newNode = {
              isLeaf: false,
              sn: itemOrg.code,
              name: itemOrg.name,
              icon: '\ue643',
              children: [],

              onlineCount: itemOrg.onlineCount,
              taskType: null,
              code: itemOrg.code,
              parentOrgCode: itemOrg.superiorID,
              deviceCount: itemOrg.deviceCount,
              dataType: '1', // 1 是组织机构，0是设备
              uiStatus: -1,

              total: 100,
              pageIndex: 1,
              pageSize: 50
            }
            self.data.push(newNode)
          })
        }
      })
    },
    /**
     * 节点被展开
     * @param {Object} e tree的node节点数据
     * @param {Object} node tree的node的props数据
     */
    HandleNodeExpand (e, node) {
      if (e.isLeaf || e.children.length > 0) return
      // if ((e.isLeaf && node.isLeaf) || (e.children && e.children.length > 0)) return
      this.checkedData = e
      this.loadTreeChildren(e, node)
      // this.loadDeviceData(e, node.data.pageIndex)
    },
    // 展示当前展开节点的子级
    loadTreeChildren (data, node) {
      let orgCode = this.orgIDsStr
      let orgCodeList = this.orgIDsStr
      // 将orgCode赋值
      if (node.level > 0) {
        if (node.data.isLeaf) {
          return false
        } else {
          orgCode = node.data.sn
        }
      }
      let entity1 = {
        code: orgCode,
        codeList: orgCodeList
      }
      // 不是叶子节点，即组织机构
      let self = this
      getOrgTree(entity1).then(responseTree => {
        if (responseTree.data != null && responseTree.data.length > 0) {
          if (node.level > 0) {
            // 不是根节点，就加载当前查询节点的子节点（组织机构）
            if (responseTree.data[0].children && responseTree.data[0].children !== null && responseTree.data[0].children.length > 0) {
              responseTree.data[0].children.forEach(item => {
                data.children.push({
                  isLeaf: false,
                  sn: item.code,
                  name: item.name,
                  icon: '\ue643',
                  children: [],

                  onlineCount: item.onlineCount,
                  taskType: null,
                  code: item.code,
                  parentOrgCode: item.superiorID,
                  deviceCount: item.deviceCount,
                  dataType: '1', // 1 是组织机构，0是设备
                  uiStatus: -1,

                  total: item.deviceCount,
                  pageIndex: 1,
                  pageSize: 50
                })
              })
            }
          }
        }
        this.loadDeviceData(data, node.data.pageIndex)
      })
    },
    // 分页加载组织机构下设备
    loadDeviceData (data = { children: [] }, pn = 0) {
      this.loading = true // 设置loading状态
      // 如果pn=0,data.children的长度大于0 ，说明该级数据已经加载过，应该是树形被合上又展开的操作
      // 正常加载数据时，pn==0的同时data.children==0说明第一次加载该级数据
      // pn!=0,data.children.length>0,分页加载该级数据
      if (pn === 0 && data.children.length && data.children.length > 0) {
        return
      }
      this.scrollLoadingFlag = false // 加载开关关闭
      let queryData = {
        currentPage: data.pageIndex,
        pageSize: this.pl,
        body: {
          'orgId': data.code
        }
      }
      let self = this
      /// 获取组织下直属设备
      getDeviceList(queryData).then(response => {
        this.scrollLoadingFlag = true // 加载开关打开

        this.loading = false
        if (response.data) {
          if (response.data.length > 0) {
            // 重置滚动加载事件开关
            if (response.data.length < this.pl) {
              this.scrollLoadingFlag = false // 如果获取到的数据小于pl，数据加载完，关闭开关
            }
            response.data.forEach(item => {
              data.children.push({
                isLeaf: true,
                icon: '\ue623',
                sn: item.sn,
                name: item.captureName,

                onlineCount: 0,
                taskType: item.taskType,
                code: item.sn,
                parentOrgCode: data.code,
                deviceCount: 0,
                children: null,
                captureType: item.captureType,
                dataType: '0', // 1 是组织机构，0是设备
                monitorPointType: item.monitorPointType,
                uiStatus: item.uiStatus,
                cameraAddress: item.monitorPointAddress,
                total: 0,
                pageIndex: 0,
                pageSize: 0
              })
            })
          }
          //  // 判断当前节点是要添加子节点还是滚动加载
          // if (this.departmentData.length === 0 && pn === 0) { // 如果树形图数据长度为0并且pn=0，说明第一次加载数据，直接赋值
          //   this.departmentData = list
          // } else if (data.children && data.children.length === 0) { // 如果当前点击项children长度等于0，说明第一次加载下一级
          //   this.$set(data, 'children', list)
          // } else {
          //   data.children = data.children.concat(list) // 其他情况为分页加载，拼接数据
          // }
        }
      }).catch(err => {
        console.log('err:', err)
        this.$message.closeAll()
        this.$message.error('请求超时')
      })
    },
    /**
     * 判断当前tab是否需要滚动加载
     * @param {String} name 那个地方触发了滚动加载
     */
    rollingLoad (event) {
      // clearTimeout(this.scrollTimer)
      // this.scrollTimer = setTimeout(() => {
      if (this.scrollLoad(event) && this.scrollLoadingFlag) { // scrollLoadingFlag为true说明可以进行下一次请求
        this.checkedData.pageIndex += 1 // 下一次请求时，pn++
        this.loadDeviceData(this.checkedData, this.checkedData.pageIndex) // 请求函数
      }
      // }, 300)
    },
    /**
     * 滚动加载
     * @return {Boolean} 滚动加载开关
     */
    scrollLoad (event) {
      let target = event.target
      const offsetHeight = target.offsetHeight
      const scrollTop = target.scrollTop
      const scrollHeight = target.scrollHeight
      if ((offsetHeight + scrollTop) - scrollHeight >= -100) {
        return true
      } else {
        return false
      }
    },

    handleNodeClick (data) {
      this.$Notice.$emit('camTreeNodeClick', data)
    },
    cameraIconClick (type, node) {
      console.log('node' + node)
      switch (type) {
        case 1:
          this.$Notice.$emit('cameravideoplay', node)
          break
        case 2:
          break
      }
    },
    // 二维定位
    mapLocation (data) {
      console.log(data);
      if (data === null || data === '') return
      // this.$router.push({ path: '/GisView', name: 'GisView', params: { cameraSN: data.sn } })
      let cameraLocation = {   //设备树定位
        cameraName: data.name, //设备名称
        cameraSn: data.sn,//设备编号
        cameraType: data.captureType,//设备类型
        cameraState: data.uiStatus,//设备状态
        cameraAddress: data.cameraAddress,//设备地址
      }
      console.log('cameraLocation', cameraLocation);
      this.passLocation_ = cameraLocation;
      this.gisOpen = true
      // this.$Notice.$emit('cameraLocation', cameraLocation);
    },
    // 报障按钮
    reportDevFault (data) {
      this.DevFaultDialogVisible = true
      this.cameraInfoData = data
      this.DevFaultData = {
        departmentName: '',
        note: '',
        cameraSN: '',
        declareTime: '',
        departmentID: '',
        notifier: '',
        faultStatus: '',
        cameraName: ''
      }
    },
    saveReportDevFault () {
      this.DevFaultData.departmentName = this.$store.getters.getCurrentOrg.name
      this.DevFaultData.cameraSN = this.cameraInfoData.sn
      this.DevFaultData.declareTime = this.commonFunction.getTimeByTimeStamp(Date.now())
      this.DevFaultData.departmentID = this.$store.getters.getCurrentOrg.code
      this.DevFaultData.notifier = this.$store.getters.loginUser.name
      this.DevFaultData.faultStatus = '0'
      // this.DevFaultData.id = this.commonFunction.Guid()
      this.DevFaultData.cameraName = this.cameraInfoData.name
      addDeviceFault(this.DevFaultData).then(data => {
        if (data != null) {
          if (data.status === '200') {
            this.params.confirmContent = '报障成功！'
          } else {
            this.params.confirmContent = '报障失败！'
          }
        } else {
          this.params.confirmContent = '报障失败！'
        }
        // this.tipsMethod()
        // this.$refs.cameraTree.getOrgList()
      })
    },
    cancelMethod () {
      this.DevFaultDialogVisible = false
    },
    handleCheckChange (data, checked, indeterminate) {
      // 判断只有在选中状态与选中设备类型时才进行传值
      if (checked === true && data.dataType === '0') {
        this.$Notice.$emit('checkCamLen', data)
      }
      if (checked === false && data.dataType === '0') {
        this.$Notice.$emit('uncheckCamLen', data)
      }
    },
    deleteCamLen (value) {
      this.$refs.deviceOrgTree.setChecked(value, false)
    }
  },
  beforeMount () {
    this.getRoleInfo()
  },
  mounted () {
    this.$Notice.$on('deleteCamLen', (value) => { this.deleteCamLen(value) })
  }
}
</script>
<style lang="scss" scoped>
.Main {
  width: 100%;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
}
.CenterContainer {
  width: 100%;
  // position: inherit;
  height: calc(100%);
  // background: green;
}
</style>