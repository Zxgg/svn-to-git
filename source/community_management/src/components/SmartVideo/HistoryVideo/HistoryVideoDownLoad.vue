<template>
  <div id="HistoryVideoDownLoad"
       class="templateClass">

    <el-button type="primary"
               class="btnSearch"
               @click="playHistoryVideo">
      播放
    </el-button>
    <!-- <el-scrollbar style="height:100%"> -->
    <el-table :data="tableData"
              :header-cell-class-name="getHeaderRowClass"
              :cell-class-name="getRowClass"
              style="width:100%;background:#032941;Opacity='0.5'"
              border
              height="90%"
              highlight-current-row
              :default-sort="{prop: 'videoState', order: 'descending'}"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="40">
      </el-table-column>
      <el-table-column prop="cameraName"
                       label="摄像机名称">
      </el-table-column>
      <el-table-column prop="startTime"
                       label="开始时间"
                       width="160">
      </el-table-column>
      <el-table-column prop="endTime"
                       label="结束时间"
                       width="160">
      </el-table-column>
      <!-- <el-table-column fixed="right"
                       label="操作"
                       width="80">
        <template slot-scope="scope">
          <el-button @click="stopLocalRecord(scope.row)"
                     v-if="scope.row.videoState===1"
                     type="text"
                     class="icon iconfont"
                     size="small">&#xe6c6;</el-button> -->
      <!-- <el-button @click="openFolder(scope.row.filePath)"
                     v-if="scope.row.videoState===2"
                     type="text"
                     class="icon iconfont"
                     size="small">&#xe69a;</el-button> -->

      <!-- </template>
      </el-table-column> -->
    </el-table>

    <!-- <el-row id="Pagination">
      <pagination v-show="total>0"
                  style=" float: right; margin-top:3px "
                  :total="params.total"
                  :page.sync="params.pageIndex"
                  :limit.sync="params.pageSize"
                  @pagination="getVideoDownLoadList" />
      <pagination v-show="total>0"
                  :total="total"
                  :page.sync="queryParams.pageNum"
                  :limit.sync="queryParams.pageSize"
                  @pagination="getList" />

    </el-row> -->
    <!-- </el-scrollbar> -->
  </div>
</template>

<script >
// import { VideoDowaLoadState } from '@/common/enum/enum'
// import Pagination from '@/components/Pagination/Pagination'
import { getRecordList } from "@/api/smartVideo/vcnrtsp"

export default {
  // components: { Pagination },
  data () {
    return {
      tableData: [],
      // VideoDowaLoadState: VideoDowaLoadState,
      that: this,
      params: {
        pageIndex: 1,
        pageSize: 20,
        total: 1
      },
      multipleSelection: [],
    }
  },
  filters: {
    getVideoState (value, self) {
      let videoStateName = ''
      self.VideoDowaLoadState.forEach(val => {
        if (val.value === value) { videoStateName = val.label }
      })
      return videoStateName
    }
  },
  methods: {
    getRowClass ({ row, rowIndex }) {
      // return 'elmentUI_table_cellRow'
    },
    getHeaderRowClass ({ row, rowIndex }) {
      // return 'elmentUI_table_HeaderRow'
    },
    handleSelectionChange (val) {
      if (val.length > 9) {
        this.$message.warning('最多选9个播放列表！')
        return
      }
      this.multipleSelection = val
    },
    playHistoryVideo () {
      if (this.multipleSelection.length <= 0) {
        this.$message.warning('最先选择播放列表！')
        return
      } else if (this.multipleSelection.length > 1) {
        this.$message.warning('最多选1个播放列表！')
        return
      }
      this.$Notice.$emit('playHistroyVideo', this.multipleSelection)

    },
  },
  mounted () {
    this.$Notice.$on('recordDialogVisible', (tableData) => this.tableData = tableData)
  }
}
</script>
<style lang="scss" scoped>
.templateClass {
  height: 100%;
  min-height: 200px;
  // padding: 0px 5px 0px 5px;
  background: #032941;
}
.templateClass .btnSearch {
  width: 120px;
  margin: 10px;
}

// 滚动条的宽度
</style>

<style >
/* .templateClass .el-scrollbar__wrap {
  overflow-x: hidden !important;
} */
#localRecordVideoDiv .el-scrollbar__view {
  height: 100%;
}

.el-table__body-wrapper::-webkit-scrollbar {
  width: 10px;
  height: 10px;
}

.el-table__body-wrapper::-webkit-scrollbar-thumb {
  background-color: rgba(144, 147, 153, 0.3);
  border-radius: 3px;
}
</style>
