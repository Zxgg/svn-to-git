<template>
  <div class="searchBox">
    <div class="searchBot">
      <div class="botInp">
        <el-input placeholder="请输入房间号"
                  v-model="queryparams.name"
                  clearable
                  suffix-icon="el-icon-search">
        </el-input>
      </div>
      <div class="botSel">
        <el-input placeholder="请输入房主姓名"
                  v-model="queryparams.personName"
                  clearable
                  suffix-icon="el-icon-search">
        </el-input>
      </div>
      <div class="botBut">
        <el-button type="primary"
                   @click="searchBtn(queryparams)">搜索</el-button>
      </div>
    </div>
    <div class="botList">
      <el-table :data="tableData"
                :header-cell-style="{background:'#182751',color:'#606266'}"
                :cell-style="{background:'#182751',color:'#fff'}"
                @row-click="rowClick"
                height="530px">
        <el-table-column prop="person_name"
                         width="70"
                         label="户主">
        </el-table-column>
        <el-table-column prop="identification_number"
                         label="身份证号"
                         width="150"
                         :formatter="cardIdFormatter">
        </el-table-column>
        <el-table-column prop="phone_number"
                         width="110"
                         label="电话">
        </el-table-column>
        <el-table-column label="房屋住址"
                         :formatter="houseAddressFormat">
        </el-table-column>
      </el-table>

    </div>

    <!-- 查询房屋信息 对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="600px"
               append-to-body
               class="dialogHouseClass">
      <el-form ref="form"
               :model="form"
               :inline="true"
               label-width="80px">
        <!-- <el-form-item label="头像"
                      prop="avatarId">
          <img style="width: 180px; height: 200px"
               :src="form.imageUrl">
        </el-form-item> -->
        <el-form-item label="户主:"
                      prop="person_name">
          <!-- <el-input v-model="form.name"
                    placeholder="姓名" /> -->
          <span class="addr">{{ form.person_name }}</span>
        </el-form-item>
        <el-form-item label="身份证号:"
                      prop="identification_number">
          <span class="addr">{{ form.identification_number | ellipsis }}</span>
        </el-form-item>
        <el-form-item label="电话:"
                      prop="phone_number">
          <span class="addr">{{ form.phone_number }}</span>
          <!-- <el-input v-model="form.taskType"
                    placeholder="请输入任务类型" /> -->
        </el-form-item>
        <el-form-item label="社区名称:"
                      prop="communityId">
          <el-select v-model="form.community_id"
                     disabled
                     placeholder="请选择社区">
            <el-option v-for="dict in communityOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="小区名称:"
                      prop="communityChildId">
          <el-select v-model="form.community_child_id"
                     disabled
                     placeholder="请选择小区">
            <el-option v-for="dict in allCommunityChildOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="小区楼栋:"
                      prop="communityBuildingId">
          <el-select v-model="form.community_building_id"
                     disabled
                     placeholder="请选择楼栋">
            <el-option v-for="dict in communityBuildingOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="单元:"
                      prop="unit">
          <!-- <el-input v-model="form.unit"
                    placeholder="请输入单元" /> -->
          <span class="addr">{{ form.unit }}</span>
        </el-form-item>
        <el-form-item label="楼层:"
                      prop="floor">
          <!-- <el-input v-model="form.floor"
                    placeholder="请输入楼层" /> -->
          <span class="addr">{{ form.floor }}</span>
        </el-form-item>
        <el-form-item label="门牌号:"
                      prop="houseNumber">
          <!-- <el-input v-model="form.house_number"
                    placeholder="请输入门牌号" /> -->
          <span class="addr">{{ form.house_number }}</span>
        </el-form-item>
        <el-form-item label="厅室:"
                      prop="houseStructure">
          <el-select v-model="form.house_structure"
                     disabled
                     placeholder="请选择厅室">
            <el-option v-for="dict in hallRoomOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="建筑面积:"
                      prop="buildingArea">
          <!-- <el-input v-model="form.building_area"
                    placeholder="请输入面积(平方米)" /> -->
          <span class="addr">{{ form.building_area }}</span>
        </el-form-item>
        <el-form-item label="套内面积:"
                      prop="area">
          <!-- <el-input v-model="form.area"
                    placeholder="请输入面积(平方米)" /> -->
          <span class="addr">{{ form.area }}</span>
        </el-form-item>
      </el-form>
      <el-table v-if="vehicleInformationVoList.length > 0"
                :data="vehicleInformationVoList"
                :header-cell-style="{background:'#182751',color:'#606266'}"
                :cell-style="{background:'#182751',color:'#fff'}">
        <el-table-column prop="plate"
                         align="center"
                         label="车牌">
        </el-table-column>
        <el-table-column prop="model"
                         align="center"
                         label="车型"
                         :formatter="modelFormat">
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>
<script>
import $ from "jquery";
import { mapGetters, mapState } from "vuex";
import { getBottomlist } from '@/api/caringPeople/bottomList'
import { listCommunity_houses, selectVehicleInformationVoList, queryHouseAndPersonList } from '@/api/communityUnit/community_houses' // 查询社区房屋信息列表、根据房屋id查询关联车辆
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import { listCommunity_building } from "@/api/communityUnit/community_building";
export default {
  data () {
    return {
      tableData: [],
      targetPerson: [],
      queryparams: {
        name: '',
        personName: '',
        pageNum: 1,
        pageSize: 20,
      },
      form: {},
      open: false,
      title: '',
      communityOptions: [],
      allCommunityChildOptions: [],
      vehicleInformationVoList: [],
      communityBuildingOptions: [],
      // 车型字典
      modelOptions: [],
      //厅室
      hallRoomOptions: [],
    }
  },
  created () {
    this.searchBtn()
    this.getCommunityDictory()
    this.getAllCommunityChildDictory()
    this.getCommunityBuildDictory()
    this.getDicts("base_vehicle_model").then((response) => {
      this.modelOptions = response.data;
    });
    this.getDicts("base_hall_room").then((response) => {
      this.hallRoomOptions = response.data;
    });
  },
  mounted () {
  },
  watch: {
  },
  filters: {
    ellipsis (value) {
      if (!value) return ''
      if (value.length > 13) {
        return value.slice(0, 6) + '********' + value.slice(-4)
      }
      return value
    }
  },
  methods: {
    // 车型字典翻译
    modelFormat (row, column) {
      return this.selectDictLabel(this.modelOptions, row.model);
    },
    cardIdFormatter (row, column, value) {
      if (!value) return ''
      if (value.length > 13) {
        return value.slice(0, 6) + '********' + value.slice(-4)
      }
      return value
    },
    houseAddressFormat (row, column, event) {
      let communityName = ''
      let communityChildName = ''
      if (this.communityOptions && this.communityOptions.length > 0) {
        let rows = this.communityOptions.filter((item) => {
          return item.id === row.community_id;
        });
        if (rows && rows.length > 0) {
          communityName = rows[0].name;
        }
      }
      if (
        this.allCommunityChildOptions &&
        this.allCommunityChildOptions.length > 0
      ) {
        let rows2 = this.allCommunityChildOptions.filter((item2) => {
          return item2.id === row.community_child_id;
        });
        if (rows2 && rows2.length > 0) {
          communityChildName = rows2[0].name;
        }
      }
      return communityName + communityChildName + row.unit + "单元" + row.floor + "层" + row.house_number;
    },
    searchBtn () {
      queryHouseAndPersonList(this.queryparams).then(res => {
        if (res.rows) {
          this.tableData = res.rows
        }
      })
    },
    rowClick (row, column, event) {
      this.form = row
      this.open = true
      this.title = '房屋信息'
      this.getVehicleVoListByHouse(row)
    },
    getVehicleVoListByHouse (row) {
      let houseId = row.id
      let param = {
        communityHousesId: houseId,
        // personnelId:
        isDelFlag: '0'
      }
      selectVehicleInformationVoList(param).then(response => {
        debugger
        this.vehicleInformationVoList = response.rows;
      });
    },
    getCommunityDictory () {
      this.communityOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
      };
      listCommunity(param).then((response) => {
        this.communityOptions = response.rows;
      });
    },
    getAllCommunityChildDictory (communityId) {
      this.allCommunityChildOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: null,
      };
      listCommunity_child(param).then((response) => {
        this.allCommunityChildOptions = response.rows;
      });
    },
    getCommunityBuildDictory () {
      let param = {
        pageNum: 1,
        pageSize: 100000,
      };
      listCommunity_building(param).then((response) => {
        this.communityBuildingOptions = response.rows;
      });
    }
  }
}
</script>

<style scoped="scoped" lang="less">
.searchBot {
  height: 10%;
}
.botInp {
  width: auto;
}
.botSel {
  width: auto;
}
</style>
<style lang="less">
.dialogHouseClass .el-input.is-disabled .el-input__inner {
  background-color: transparent;
  border: 0;
  // color: #c0c4cc;
  cursor: not-allowed;
}
.dialogHouseClass .el-form-item__label {
  color: #c0c4cc;
}
.dialogHouseClass .el-dialog__body {
  color: #c0c4cc;
}
.dialogHouseClass .addr {
  margin-left: 15px;
  width: 180px;
}
.dialogHouseClass .el-select .el-input .el-select__caret {
  color: #222751;
}
.dialogHouseClass .el-form-item__content {
  width: 90px;
}
</style>
