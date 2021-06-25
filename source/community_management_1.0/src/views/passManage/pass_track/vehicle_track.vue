<template>
  <div style="float:left">
    <el-drawer :visible.sync="drawer"
               :before-close="handleClose"
               size="75%">
      <el-button size="mini"
                 type="text"
                 icon="el-icon-s-promotion"
                 class="trackButton"
                 @click="toPassTrack">查看轨迹</el-button>
      <el-table :data="tableData"
                style="width: 100%"
                class="trackTable">
        <el-table-column prop="communityName"
                         label="社区名称"
                         width="180">
        </el-table-column>
        <el-table-column prop="communityChildName"
                         label="小区名称"
                         width="180">
        </el-table-column>
        <el-table-column prop="plateNo"
                         label="车牌号码"
                         width="180">
        </el-table-column>
        <el-table-column prop="plateColor"
                         label="车牌颜色"
                         width="180">
        </el-table-column>
        <el-table-column prop="vehicleBrand"
                         label="车辆品牌">
        </el-table-column>
        <el-table-column prop="deviceId"
                         label="设备编码"
                         width="180">
        </el-table-column>
        <el-table-column prop="appearTime"
                         label="时间">
        </el-table-column>
      </el-table>
      <pagination :total="total"
                  :page.sync="queryParams.pageNum"
                  :limit.sync="queryParams.pageSize"
                  @pagination="getPassList(null)" />
    </el-drawer>
    <el-dialog :title="title"
               :visible.sync="open"
               append-to-body
               :before-close="closeDialog">
      <gisView :passTrackObject="passTrack"
               style="height:600px;width:800px; margin:0 auto" />
    </el-dialog>
  </div>
</template>
<script>
import { listPass_vehicle } from "@/api/passManage/pass_vehicle";
import gisView from '@/views/GISView/GISView'
export default {
  props: {
    trackData: {
      type: Object,
      default: {
        plateNo: null,
        drawShow: false
      }
    }
  },
  components: { gisView },
  data () {
    return {
      drawer: this.trackData.drawShow,
      open: false,
      title: "轨迹",
      innerDrawer: false,
      tableData: null,
      total: 0,
      personId: null,
      passTrack: [],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        plateNo: null,
      }
    }
  },
  methods: {
    handleClose (done) {
      this.trackData.personId = null;
      this.trackData.drawShow = false;
      this.tableData = null;
      this.$emit('getPassList', null);
    },
    closeDialog (done) {
      console.log('dialog close ..');
      this.passTrack = [];
      this.open = false;
    },
    getPassList (plateNo) {
      if (plateNo != null) {
        this.plateNo = plateNo;
      }
      // this.communityOptions = [];
      this.queryParams.plateNo = this.plateNo;
      listPass_vehicle(this.queryParams).then((response) => {
        this.total = response.total;
        this.tableData = response.rows;
      });
    },
    toPassTrack () {
      this.open = true;
      if (this.tableData != null && this.tableData.length > 0) {
        this.tableData.forEach(data => {
          let pass = {
            passName: data.plateNo,
            cameraSn: data.deviceId,
            passImage: data.storageUrl3,
            passTime: data.appearTime,
            passType: 2,
            cameraAddress: null,
          }
          this.passTrack.push(pass);
          // console.log(data);
        });
        console.log(this.passTrack);
        // this.$Notice.$emit('passTrack', this.passTrack);
      }
    },
  },
  watch: {
    trackData: {
      handler (n, o) {
        console.log(o);
        this.drawer = this.trackData.drawShow;
        if (this.drawer) {
          this.getPassList(this.trackData.plateNo);
        }
      },
      deep: true,
      // immediate: true
    }
  }
}
</script>
<style lang='scss' scoped>
/deep/ .el-drawer {
  // background-color: #0c1637;
}
/deep/ td {
  // background-color: rgba(45, 54, 163, 0.2);
}
.trackButton {
  float: left;
  font-size: 15px;
}
.trackTable {
  width: 100%;
  height: 80%;
  overflow-y: auto;
}
</style>