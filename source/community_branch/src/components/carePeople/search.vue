<template>
  <div class="searchBox">
    <div class="searchBot">
      <div class="botInp">
        <el-input placeholder="请输入名称"
                  v-model="queryparams.name"
                  suffix-icon="el-icon-search">
        </el-input>
      </div>
      <!-- <div class="botSel">
        <el-select v-model="queryparams.type"
                   clearable
                   placeholder="请选择"
                   filterable>
          <el-option v-for="item in caringeventtype"
                     :key="item.dictValue"
                     :label="item.dictLabel"
                     :value="item.dictValue">
          </el-option>
        </el-select>
      </div> -->
      <div class="botBut">
        <el-button type="primary"
                   @click="searchBtn(queryparams)">搜索</el-button>
      </div>
    </div>
    <div class="botList">
      <el-table :data="tableData"
                :header-cell-style="{background:'#182751',color:'#606266'}"
                :cell-style="{background:'#182751',color:'#fff'}">
        <el-table-column prop="name"
                         label="事件名称"
                         align="center">
        </el-table-column>

        <el-table-column prop="personid"
                         label="关联人员">
        </el-table-column>
        <el-table-column prop="createTime"
                         label="生成时间">
        </el-table-column>
      </el-table>

    </div>
  </div>
</template>
<script>
import $ from "jquery";
import { mapGetters, mapState } from "vuex";
import { getBottomlist } from '@/api/caringPeople/bottomList'
export default {
  data () {
    return {
      tableData: [],
      targetPerson: [],
      caringeventtype: [
        { dictValue: 1, dictLabel: "长期未出现" },
        { dictValue: 2, dictLabel: "生日提醒" }
      ],
      queryparams: {
        personid: '',
        type: '',
        name: '',
      }
    }
  },
  computed: {
    ...mapGetters(["currentComunity"]),
  },
  created () {
    // this.getDicts("base_caring_event").then(response => {
    //   // console.log(response.data);
    //   this.caringeventtype = response.data;
    // })
  },
  mounted () {
    // this.getbotlistApi()
    this.tableData = this.testData.guanai.noticeSearch;
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        this.getbotlistApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  methods: {
    // 人员名称字典翻译
    nameFormat (row, column, cellValue) {
      let result = ''
      let items = this.$cf.getListFromString(cellValue, ';')
      if (items && items.length > 0) {
        items.forEach(item => {
          let nameList = this.$cf.getListFromString(item, ',')
          if (nameList && nameList.length)
            result += nameList[1] + ','
        });
      }
      return result.trim(',')
    },
    getbotlistApi () {
      let self = this
      this.tableData = []
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      // console.log(this.queryparams, 'this.queryparams')
      getBottomlist(communityId, communityChildId, this.queryparams).then(res => {
        if (res && res.rows.length > 0) {
          this.tableData = res.rows
        }
      })
    },
    searchBtn () {
      // this.getbotlistApi()
      // debugger;
      if (this.queryparams.name) {
        let data = this.testData.guanai.noticeSearch.filter(data => {
          return data.name.indexOf(this.queryparams.name) != -1;
        })
        // console.log(data);
        this.tableData = data;
      } else {
        this.tableData = this.testData.guanai.noticeSearch;
      }
    },
  }
}
</script>

<style scoped="scoped" lang="less">
</style>
