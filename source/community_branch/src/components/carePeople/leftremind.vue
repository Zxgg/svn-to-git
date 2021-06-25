<template>
  <div class="carePeople">
    <el-table
      :data="tableData"
      style="width: 100%"
      :header-cell-style="{
        background: '#182751',
        color: '#606266',
        textAlign: 'center'
      }"
      :cell-style="{
        background: '#182751',
        color: '#fff',
        textAlign: 'center'
      }"
    >
      <el-table-column prop="type" label="事件类型"> </el-table-column>
      <el-table-column prop="num" label="事件数量"> </el-table-column>
      <el-table-column prop="processedNum" label="处理比例"> </el-table-column>
    </el-table>
  </div>
</template>
<script>
import $ from "jquery";
import { mapGetters, mapState } from "vuex";
import { getCaringPeopleevent } from "@/api/caringPeople/caringpeopleevent";
export default {
  data() {
    return {
      tableData: [],
      caringremindList: []
    };
  },
  computed: {
    ...mapGetters(["currentComunity"])
  },
  created() {
    this.getDicts("base_caring_event").then(response => {
      this.caringremindList = response.data;
    });
  },
  mounted() {
    // this.getcarRemApi()
    this.tableData = this.testData.guanai.noticeCount;
  },
  watch: {
    currentComunity: {
      handler(val, data) {
        this.getcarRemApi();
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  methods: {
    // 提醒类型字典翻译
    typeFormat: function(value) {
      return this.selectDictLabel(this.caringremindList, value);
    },
    getRowClass({ row, column, rowIndex, columnIndex }) {
      return "background:#222751;color:#fff;border-bottom-color:#182751;";
    },
    getcarRemApi() {
      let self = this;
      let communityId = this.currentComunity.communityId;
      let communityChildId = this.currentComunity.communityChildId;
      getCaringPeopleevent(communityId, communityChildId).then(res => {
        if (res.data != null) {
          res.data.forEach(datas => {
            datas.type = self.typeFormat(datas.type);
          });
          this.tableData = res.data;
        }
      });
    }
  }
};
</script>
<style scoped="true">
@import "~@/assets/styles/main/index.css";
</style>
