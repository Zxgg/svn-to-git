<template>
  <div class="Main"
       ref="MainView">
    <el-row>
      <el-table :data="presetTableData"
                :header-cell-class-name="getHeaderRowClass"
                :cell-class-name="getRowClass"
                border
                style="background: transparent;"
                height="170"
                highlight-current-row
                :default-sort="{prop: 'presetIndex', order: 'ascending'}"
                @row-click="PresetHandleRowClick">

        <el-table-column prop="presetName"
                         label="名字">
        </el-table-column>
        <el-table-column prop="presetIndex"
                         label="索引"
                         width="70">
        </el-table-column>
        <el-table-column label="操作"
                         width="100">
          <template slot-scope="scope">
            <span class="icon iconfont"
                  @click="PTZ_Control(PTZControlCommandEnum.PTZ_PREFAB_BIT_RUN, scope.row.presetIndex, '')">&#xe786;</span>
            <span class="icon iconfont"
                  @click="editPresetPos(scope.row)">&#xe652;</span>
            <span class="icon iconfont"
                  @click="delPresetPos(scope.row)">&#xe66f;</span>
          </template>
        </el-table-column>

      </el-table>
    </el-row>

    <el-row class="presetPositionButton">
      <el-button type="primary"
                 @click="setGuardPosition()">设置看守位</el-button>
    </el-row>

    <el-dialog :title="presetDialogTitle"
               :visible.sync="dialogPresetVisible"
               width="400px"
               center
               custom-class="presetDiaolg">
      <div class="presetDiv">
        <el-col class="presetdiv_col">
          <el-form id="presetForm"
                   :inline="true"
                   label-position="right"
                   label-width="100px"
                   ref="ptzPresetInfoData"
                   :rules="presetFormRule"
                   :validate-on-rule-change="false"
                   :model="ptzPresetInfoData">
            <div class="el_form_itemDiv">
              <el-form-item label="名称"
                            prop="presetName">
                <el-input class="presetDiaolg_input"
                          v-model="ptzPresetInfoData.presetName" />
              </el-form-item>
            </div>
          </el-form>
        </el-col>
        <span slot="footer"
              class="dialog_footer">
          <el-button :style="{ display: addPresetButtonVisible }"
                     @click="submitFormSaveAddPreset">保 存</el-button>
          <el-button :style="{ display: editPresetButtonVisible }"
                     @click="submitFormSaveEditPresetPos">保 存</el-button>
          <el-button @click="cancelMethod">取 消</el-button>
        </span>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { PTZAddPreset, editPresetPosInfo, deletePresetPos, setGuardPosition, getPresetPosList } from "@/api/smartVideo/PTZ"
export default {
  data () {
    return {
      presetTableData: [],
      PTZControlCommandEnum: {
        PTZ_STOP: 1, // 云台停止转动
        PTZ_UP: 2, // 云台向上转动，连续模式下可以使用PTZ_STOP停止转动
        PTZ_DOWN: 3, // 云台向下转动，连续模式下可以使用PTZ_STOP停止转动
        PTZ_LEFT: 4, // 云台向左转动，连续模式下可以使用PTZ_STOP停止转动
        PTZ_UP_LEFT: 5, // 云台左上转动，连续模式下可以使用PTZ_STOP停止转动
        PTZ_DOWN_LEFT: 6, // 云台左下转动，连续模式下可以使用PTZ_STOP停止转动
        PTZ_RIGHT: 7, // 云台向右转动，连续模式下可以使用PTZ_STOP停止转动
        PTZ_UP_RIGHT: 8, // 云台右上转动，连续模式下可以使用PTZ_STOP停止转动
        PTZ_DOWN_RIGHT: 9, // 云台右下转动，连续模式下可以使用PTZ_STOP停止转动
        PTZ_AUTO: 10, // 云台自动控制
        PTZ_PREFAB_BIT_RUN: 11, // 调用预置位
        PTZ_CRUISE_RUN: 12, // 巡航轨迹运行/模式运行
        PTZ_CRUISE_STOP: 13, // 巡航轨迹停止
        PTZ_MODE_CRUISE_RUN: 14, // 模式轨迹运行
        PTZ_MODE_CRUISE_STOP: 15, // 模式轨迹停止
        PTZ_MENU_OPEN: 16, // 打开镜头菜单
        PTZ_MENU_EXIT: 17, // 关闭镜头菜单
        PTZ_MENU_ENTER: 18, // 进入镜头菜单
        PTZ_FLIP: 19, // 云台翻转180度
        PTZ_START: 20, // PTZ_START

        // 镜头控制，包括光圈、缩放、聚焦
        PTZ_LENS_APERTURE_OPEN: 21, // 光圈放大
        PTZ_LENS_APERTURE_CLOSE: 22, // 光圈缩小
        PTZ_LENS_ZOOM_IN: 23, // 范围放大
        PTZ_LENS_ZOOM_OUT: 24, // 范围缩小
        PTZ_LENS_FOCAL_NEAR: 25, // 聚焦近
        PTZ_LENS_FOCAL_FAR: 26, // 聚焦远
        PTZ_AUX_OPEN: 27, // 打开辅助设备 1雨刷，2灯光
        PTZ_AUX_STOP: 28, // 关闭辅助设备

        // 模式
        MODE_SET_START: 29, // 模式设置开始
        MODE_SET_STOP: 30, // 模式设置停止

        // 增强操作
        PTZ_FAST_LOCATE: 31, // 快速定位对焦
        PTZ_HORIZONTAL_SCAN: 32, // 水平扫描开始
        PTZ_VERTICAL_SCAN: 33, // 垂直扫描开始
        PTZ_LOCK: 34, // 云台加锁
        PTZ_UNLOCK: 35, // 云台解锁
        PTZ_ABSOLUTE_MOVE: 36, // 转动到指定位置
        PTZ_DIRECTION_MOVE: 37 // 定向转动
      },
      // cameraInfoData: {
      //   onlineCount: 0,
      //   taskType: null,
      //   code: '',
      //   parentOrgCode: '',
      //   deviceCount: 0,
      //   children: null,
      //   captureType: 2,
      //   dataType: '',
      //   name: '',
      //   monitorPointType: 0,
      //   sn: '',
      //   uiStatus: 0
      // },
      cameraInfoData: null,
      dialogPresetVisible: false,
      presetDialogTitle: '',
      addPresetButtonVisible: 'none',
      editPresetButtonVisible: 'none',
      presetFormRule: {
        // presetName: [{ required: true, validator: this.commonFunction.checkName, trigger: 'blur' }]
        presetName: [{ required: true, message: '不能为空', trigger: 'blur' }]
      },
      ptzPresetInfoData: {
        presetName: '',
        presetIndex: 0
      },
    }
  },
  created () { },
  filters: {},
  methods: {
    getRowClass ({ row, rowIndex }) {
      // return 'RealTimeVideoPlayView_table_cellRow_New'
    },
    getHeaderRowClass ({ row, rowIndex }) {
      // return 'RealTimeVideoPlayView_table_HeaderRow_New'
    },
    PresetHandleRowClick (row) {
      this.ptzPresetInfoData = row
    },
    getPresetPositionList () {
      if (this.cameraInfoData === null) {
        this.$message.warning('请选择一个相机！')
        return
      }
      let SnCodeList = this.cameraInfoData.sn.split('#')
      // 预置位
      let entity2 = {
        cameracode: SnCodeList[0],
        nvrcode: SnCodeList[1]
      }

      getPresetPosList(entity2).then(data2 => {
        if (data2.status === '200') {
          this.PresetTableData = data2.data.ptzPresetInfoList.ptzPresetInfo
        } else {
          this.PresetTableData = []
        }
      })
    },
    // 跳转预置位
    PTZ_Control (PTZ_ControlCode, controlPara1, controlPara2) {
      let entity = {
        'camera': this.cameraInfoData,
        'controlCode': PTZ_ControlCode,
        'controlPara1': controlPara1,
        'controlPara2': controlPara2
      }
      this.$Notice.$emit('PTZ_Control', entity)
    },
    // 添加预置位
    AddPreset () {
      if (this.cameraInfoData === null || this.cameraInfoData === undefined || this.cameraInfoData.dataType !== '0') {
        this.$message.warning('请选择一个摄像机！')
        return
      }
      this.$nextTick(() => {
        this.$refs.ptzPresetInfoData.clearValidate() // 移除校验结果
      })
      this.dialogPresetVisible = true
      this.addPresetButtonVisible = ''
      this.editPresetButtonVisible = 'none'
      this.presetDialogTitle = '添加预置位'
      this.ptzPresetInfoData.presetName = ''
    },
    submitFormSaveAddPreset () {
      this.$refs.ptzPresetInfoData.validate((valid) => {
        if (valid) {
          this.saveAddPreset()
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    saveAddPreset () {
      let entity = {
        'cameraCode': this.cameraInfoData.sn,
        'presetName': this.ptzPresetInfoData.presetName,
        'focusSwitch': 1
      }
      PTZAddPreset(entity).then(data => {
        if (data != null) {
          if (data.status === '200') {
            this.$message.success('添加成功！')
          } else {
            this.$message.error('添加失败！')
          }
        } else {
          this.$message.error('添加失败！')
        }
        // this.PTZTabHandleClick()
        this.getPresetPositionList()
      })
    },
    // 修改预置位信息
    editPresetPos (presetData) {
      this.$nextTick(() => {
        this.$refs.ptzPresetInfoData.clearValidate() // 移除校验结果
      })
      this.ptzPresetInfoData = presetData
      this.dialogPresetVisible = true
      this.addPresetButtonVisible = 'none'
      this.editPresetButtonVisible = ''
      this.presetDialogTitle = '修改预置位'
    },
    // 保存修改
    saveEditPresetPos () {
      let entity = {
        'cameraCode': this.cameraInfoData.sn,
        'ptzPresetInfo': {
          'presetName': this.ptzPresetInfoData.presetName,
          'presetIndex': this.ptzPresetInfoData.presetIndex
        }
      }
      editPresetPosInfo(entity).then(data => {
        if (data != null) {
          if (data.status === '200') {
            this.$message.success('修改成功！')
          } else {
            this.$message.error('修改失败！')
          }
        } else {
          this.$message.error('修改失败！')
        }
        // this.PTZTabHandleClick()
        this.getPresetPositionList()
      })
    },
    submitFormSaveEditPresetPos () {
      this.$refs.ptzPresetInfoData.validate((valid) => {
        if (valid) {
          this.saveEditPresetPos()
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    // 删除预置位
    delPresetPos (presetData) {
      this.ptzPresetInfoData = presetData

      this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.saveDelPresetPos()
      }).catch(() => {
      })
    },
    saveDelPresetPos () {
      let SnCodeList = this.cameraInfoData.sn.split('#')
      let entity = {
        cameracode: SnCodeList[0],
        domaincode: SnCodeList[1],
        presetindex: this.ptzPresetInfoData.presetIndex
      }

      deletePresetPos(entity).then(async data => {
        if (data != null) {
          if (data.status === '200') {
            this.$message.success('删除成功！')
          } else {
            this.$message.error('删除失败！')
          }
        } else {
          this.$message.error('删除失败！')
        }
        // this.PTZTabHandleClick()
        this.getPresetPositionList()
      })
    },
    // 设置看守位
    setGuardPosition () {
      if (this.ptzPresetInfoData === null || this.ptzPresetInfoData === undefined || this.ptzPresetInfoData.presetName === '') {
        this.$message.warning('请选择一个预置位！')
        return
      }
      let entity = {
        'cameraCode': this.cameraInfoData.sn,
        'guardPosInfo': {
          'presetIndex': this.ptzPresetInfoData.presetIndex,
          'enableGuardPosition': 1,
          'waitTime': 1800
        }
      }

      setGuardPosition(entity).then(async data => {
        if (data != null) {
          if (data.status === '200') {
            this.$message.success('设置成功！')
          } else {
            this.$message.error('设置失败！')
          }
        } else {
          this.$message.error('设置失败！')
        }
        // this.PTZTabHandleClick()
        this.getPresetPositionList()
      })
    },


    submitFormSaveEditPresetPos () {
      this.$refs.ptzPresetInfoData.validate((valid) => {
        if (valid) {
          this.saveEditPresetPos()
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },

    saveEditPresetPos () {
      let entity = {
        'cameraCode': this.cameraInfoData.sn,
        'ptzPresetInfo': {
          'presetName': this.ptzPresetInfoData.presetName,
          'presetIndex': this.ptzPresetInfoData.presetIndex
        }
      }
      editPresetPosInfo(entity).then(data => {
        if (data != null) {
          if (data.status === '200') {
            this.$message.success('修改成功！')
          } else {
            this.$message.error('修改失败！')
          }
        } else {
          this.$message.error('修改失败！')
        }
        // this.PTZTabHandleClick()
        this.getPresetPositionList()
      })
    },
    cancelMethod () {
      this.dialogPresetVisible = false
    },
  },
  mounted () {
    this.$Notice.$on('camTreeNodeClick', (camera) => {
      this.cameraInfoData = camera
    })
    // this.$Notice.$on('cameravideoplay', (camera) => {
    //   this.cameraInfoData = camera
    // })
    this.$Notice.$on('AddPreset', this.AddPreset)
    this.$Notice.$on('getPresetPositionList', this.getPresetPositionList)
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
</style>