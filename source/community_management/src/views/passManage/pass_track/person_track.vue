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
        <el-table-column prop="name"
                         label="人员名称"
                         width="180">
        </el-table-column>
        <el-table-column prop="genderCode"
                         label="性别">
        </el-table-column>
        <el-table-column prop="idnumber"
                         label="证件号码">
        </el-table-column>
        <el-table-column prop="deviceId"
                         label="设备编码"
                         width="180">
        </el-table-column>
        <el-table-column prop="faceAppearTime"
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
               :passLocationObject=null
               style="height:600px;width:800px; margin:0 auto" />
    </el-dialog>
  </div>
</template>
<script>
import { listPass_personnel } from "@/api/passManage/pass_personnel";
import gisView from '@/views/GISView/GISView'
export default {
  props: {
    trackData: {
      type: Object,
      default: {
        personId: null,
        drawShow: false
      }
    }
  },
  components: { gisView },
  data () {
    return {
      // 弹出层标题
      title: "轨迹",
      // 是否显示弹出层
      open: false,
      drawer: this.trackData.drawShow,
      innerDrawer: false,
      tableData: null,
      total: 0,
      personId: null,
      passTrack: [],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        personId: null,
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
    getPassList (personId) {
      if (personId != null) {
        this.personId = personId;
      }
      // this.communityOptions = [];
      this.queryParams.personId = this.personId;
      listPass_personnel(this.queryParams).then((response) => {
        this.total = response.total;
        this.tableData = response.rows;
      });
    },
    toPassTrack () {
      this.open = true;
      if (this.tableData != null && this.tableData.length > 0) {
        this.tableData.forEach(data => {
          let pass = {
            passName: data.name,
            cameraSn: data.deviceId,
            passImage: data.pics,
            passTime: data.locationMarkTime,
            passType: 1,
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
          this.getPassList(this.trackData.personId);
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