<template>
  <div class="Main"
       ref="MainView">
    <el-row>
      <el-table :data="cruiseTableData"
                :header-cell-class-name="getHeaderRowClass"
                :cell-class-name="getRowClass"
                border
                style="background: transparent;"
                height="170"
                highlight-current-row
                :default-sort="{prop: 'cruiseNo', order: 'ascending'}"
                @row-click="CruiseHandleRowClick">

        <el-table-column prop="name"
                         label="名字">
        </el-table-column>
        <el-table-column prop="cruiseNo"
                         label="索引"
                         width="70">
        </el-table-column>
        <el-table-column label="操作"
                         width="100">
          <template slot-scope="scope">
            <span class="icon iconfont"
                  @click="editCruise(scope.row)">&#xe652;</span>
            <span class="icon iconfont"
                  @click="delCruise(scope.row)">&#xe66f;</span>
          </template>
        </el-table-column>

      </el-table>
    </el-row>

    <el-row class="presetPositionButton">
      <!-- <el-button class="presetButton"
                 type="primary">
        <el-tooltip class="item"
                    effect="dark"
                    content="开启巡航"
                    placement="top"> -->
      <span class="icon iconfont"
            id="presetButtonSpan"
            v-if="startCruiseIconVisible == false"
            @click="startCruise()">&#xe660;</span>
      <!-- </el-tooltip> -->
      <!-- <el-tooltip class="item"
                    effect="dark"
                    content="停止巡航"
                    placement="top"> -->
      <span class="icon iconfont"
            id="presetButtonSpan"
            v-if="startCruiseIconVisible == true"
            @click="stopCruise()">&#xe65e;</span>
      <!-- </el-tooltip> -->
      <!-- </el-button> -->
      <el-button type="primary"
                 @click="addCruise()">添加巡航轨迹</el-button>
    </el-row>

    <el-dialog :title="cruiseDialogTitle"
               :visible.sync="dialogCruiseVisible"
               width="600px"
               center
               custom-class="cruiseDiaolg">
      <div class="cruiseDiv">
        <el-row class="cruisediv_col">
          <el-col :span="11">
            <el-form id="cruiseForm"
                     :inline="true"
                     label-position="right"
                     label-width="55px"
                     ref="cruiseInfoData"
                     :rules="cruiseFormRule"
                     :validate-on-rule-change="false"
                     :model="cruiseInfoData">
              <div class="el_form_itemDiv">
                <el-form-item label="名称"
                              prop="name">
                  <el-input class="cruiseDiaolg_input"
                            v-model="cruiseInfoData.name" />
                </el-form-item>
              </div>
            </el-form>
          </el-col>
          <el-col :span="9">
            <!-- <span class="icon iconfont"
                    v-if="startCruiseIconVisible == false"
                    @click="addCruisePoint()">&#xe6d5;</span> -->
            <el-button type="primary"
                       size="medium"
                       class="addCruisePointButton"
                       @click="addCruisePoint()">添加轨迹点</el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-table class="CruisePointTable"
                    :data="cruisePointTableData"
                    :header-cell-class-name="getHeaderRowClass"
                    :cell-class-name="getRowClass"
                    border
                    style="background: transparent; margin-top:0px;margin-bottom: 20px;"
                    height="150">

            <el-table-column prop="presetIndex"
                             label="预置位">
              <template slot-scope="scope">
                <el-select v-model="scope.row.presetIndex"
                           class="deviceInfoDiaolg_input"
                           no-data-text="empty">
                  <el-option v-for="item in CruisePointsOption"
                             :key="item.value"
                             :label="item.label"
                             :value="item.value">
                  </el-option>
                </el-select>
              </template>
            </el-table-column>
            <el-table-column prop="dwellTime"
                             label="停留时间"
                             width="120">
              <template slot-scope="scope">
                <el-input class="cruiseDiaolg_input"
                          placeholder="3~3600s"
                          v-model="scope.row.dwellTime" />
              </template>
            </el-table-column>
            <el-table-column prop="speed"
                             label="速度"
                             width="120">
              <template slot-scope="scope">
                <el-input class="cruiseDiaolg_input"
                          placeholder="1~10"
                          v-model="scope.row.speed" />
              </template>
            </el-table-column>
            <el-table-column label="操作"
                             width="90">
              <template slot-scope="scope">
                <span class="icon iconfont"
                      id="cruisePointDelIcon"
                      @click="deleteCruisePoint(scope.row)">&#xe66f;</span>
              </template>
            </el-table-column>
          </el-table>
        </el-row>
        <span slot="footer"
              class="dialog_footer">
          <el-button :style="{ display: addCruiseButtonVisible }"
                     @click="submitFormSaveAddCruise">保 存</el-button>
          <el-button :style="{ display: editCruiseButtonVisible }"
                     @click="submitFormSaveEditCruise">保 存</el-button>
          <el-button @click="cancelMethod">取 消</el-button>
        </span>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getCruiseTrack, deleteCruiseTrack, editCruiseTrack, getCruiseList, getPresetPosList, addCruiseTrack } from "@/api/smartVideo/PTZ"

export default {
  data () {
    return {
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
      cruiseTableData: [],
      cruisePointTableData: [],
      CruisePointsOption: [],
      cruiseInfoData: {
        cruiseNo: '',
        name: '',
        cruiseType: 1,
        cruiseStatus: 0
      },
      startCruiseIconVisible: false,
      addCruiseButtonVisible: '',
      editCruiseButtonVisible: '',
      dialogCruiseVisible: false,
      cruiseDialogTitle: '',
      cruiseFormRule: {
        // name: [{ required: true, validator: this.commonFunction.checkName, trigger: 'blur' }]
        name: [{ required: true, message: '不能为空', trigger: 'blur' }]
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
    CruiseHandleRowClick (row) {
      this.cruiseInfoData = row
    },
    getCruiseList () {
      if (this.cameraInfoData === null) {
        this.$message.warning('请选择一个相机！')
        return
      }
      let SnCodeList = this.cameraInfoData.sn.split('#')
      this.CruisePointsOption = []
      let entity3 = {
        cameraCode: SnCodeList[0],
        domainCode: SnCodeList[1]
      }

      getCruiseList(entity3).then(data3 => {
        if (data3.status === '200') {
          this.CruiseTableData = data3.data.cruiseTrackList.cruiseTracks
        } else {
          this.CruiseTableData = []
        }
      })
      let entityOption = {
        cameracode: SnCodeList[0],
        nvrcode: SnCodeList[1]
      }

      getPresetPosList(entityOption).then(dataOption => {
        if (dataOption.status === '200') {
          dataOption.data.ptzPresetInfoList.ptzPresetInfo.forEach(item => {
            let optionItem = { value: '', label: '' }
            optionItem.value = item.presetIndex
            optionItem.label = item.presetName
            this.CruisePointsOption.push(optionItem)
          })
        }
      })
    },
    editCruise (row) {
      this.$nextTick(() => {
        this.$refs.cruiseInfoData.clearValidate() // 移除校验结果
      })
      this.cruiseInfoData = row
      this.dialogCruiseVisible = true
      this.cruiseDialogTitle = '修改巡航轨迹'
      this.addCruiseButtonVisible = 'none'
      this.editCruiseButtonVisible = ''

      let SnCodeList = this.cameraInfoData.sn.split('#')
      let entity = {
        cameraCode: SnCodeList[0],
        domainCode: SnCodeList[1],
        cruiseNo: this.cruiseInfoData.cruiseNo
      }

      getCruiseTrack(entity).then(data => {
        if (data != null) {
          this.cruisePointTableData = data.data.cruiseInfo.cruisePoints
        }
      })
    },
    delCruise (row) {
      this.cruiseInfoData = row

      this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.saveDelCruise()
      }).catch(() => {
      })

    },
    saveDelCruise () {
      let SnCodeList = this.cameraInfoData.sn.split('#')
      let entity = {
        cameraCode: SnCodeList[0],
        domainCode: SnCodeList[1],
        cruiseType: this.cruiseInfoData.cruiseType,
        cruiseNo: this.cruiseInfoData.cruiseNo
      }

      deleteCruiseTrack(entity).then(async data => {
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
        this.getCruiseList()
      })
    },
    startCruise () {
      if (this.cruiseInfoData === null || this.cruiseInfoData === undefined || this.cruiseInfoData.name === '') {
        this.$message.warning('请选择一条巡航轨迹！')
        return
      }
      this.PTZ_Control(PTZControlCommandEnum.PTZ_CRUISE_RUN, this.cruiseInfoData.cruiseNo, '')
      this.startCruiseIconVisible = true
    },
    stopCruise () {
      if (this.cruiseInfoData === null || this.cruiseInfoData === undefined || this.cruiseInfoData.name === '') {
        this.$message.warning('请选择一条巡航轨迹！')
        return
      }
      this.PTZ_Control(PTZControlCommandEnum.PTZ_CRUISE_STOP, '', '')
      this.startCruiseIconVisible = false
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
    addCruise () {
      if (this.cameraInfoData === null || this.cameraInfoData === undefined || this.cameraInfoData.dataType !== '0') {
        this.$message.warning('请选择一个摄像机！')
        return
      }
      if (this.CruisePointsOption.length <= 0) {
        return
      }
      this.$nextTick(() => {
        this.$refs.cruiseInfoData.clearValidate() // 移除校验结果
      })
      this.dialogCruiseVisible = true
      this.cruiseDialogTitle = '添加巡航轨迹'
      this.addCruiseButtonVisible = ''
      this.editCruiseButtonVisible = 'none'
      this.cruiseInfoData = {
        cruiseNo: '',
        name: '',
        cruiseType: 1,
        cruiseStatus: 0
      }
      this.cruisePointTableData = []
    },
    saveAddCruise () {
      let SnCodeList = this.cameraInfoData.sn.split('#')
      let entity = {
        'cameraCode': SnCodeList[0],
        'domainCode': SnCodeList[1],
        'name': this.cruiseInfoData.name,
        'cruiseType': this.cruiseInfoData.cruiseType,
        'cruisePoints': this.cruisePointTableData
      }
      addCruiseTrack(entity).then(data => {
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
        this.getCruiseList()
      })
    },
    submitFormSaveAddCruise () {
      this.$refs.cruiseInfoData.validate((valid) => {
        if (valid) {
          this.saveAddCruise()
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    submitFormSaveEditCruise () {
      this.$refs.cruiseInfoData.validate((valid) => {
        if (valid) {
          this.saveEditCruise()
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    saveEditCruise () {
      let SnCodeList = this.cameraInfoData.sn.split('#')
      let entity = {
        'cameraCode': SnCodeList[0],
        'domainCode': SnCodeList[1],
        'name': this.cruiseInfoData.name,
        'cruiseType': this.cruiseInfoData.cruiseType,
        'cruiseNo': this.cruiseInfoData.cruiseNo,
        'cruisePoints': this.cruisePointTableData
      }

      editCruiseTrack(entity).then(async data => {
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
        this.getCruiseList()
      })
    },
    addCruisePoint () {
      let cruisePoint = {
        presetIndex: 1,
        dwellTime: 30,
        speed: 5
      }
      this.cruisePointTableData.push(cruisePoint)
    },
    deleteCruisePoint (row) {
      for (let i = 0; i < this.cruisePointTableData.length; i++) {
        if (this.cruisePointTableData[i] === row) {
          this.cruisePointTableData.splice(i, 1)
        }
      }
    },
    cancelMethod () {
      this.dialogCruiseVisible = false
    },
  },
  mounted () {
    this.$Notice.$on('camTreeNodeClick', (camera) => {
      this.cameraInfoData = camera
    })
    this.$Notice.$on('getCruiseList', this.getCruiseList)
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