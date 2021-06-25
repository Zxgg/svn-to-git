<template>
  <div>
    <div class="search">
      <div>
        <el-input placeholder="请输入车牌号"
                  v-model="queryparams.plate"
                  suffix-icon="el-icon-search">
        </el-input>
      </div>
      <div>
        <el-input placeholder="请输入姓名"
                  v-model="queryparams.name"
                  suffix-icon="el-icon-search">
        </el-input>
      </div>
      <div>
        <el-button type="primary"
                   @click="searchBtn(queryparams)">搜索</el-button>
      </div>
    </div>
    <div class="botList">
      <el-table :data="tableData"
                @row-click="rowClick"
                height="400">
        <el-table-column prop="community_child_id"
                         label="所属小区"
                         align="center"
                         :formatter="communityChildNameFormat">
        </el-table-column>

        <el-table-column prop="plate"
                         label="车牌号">
        </el-table-column>
        <el-table-column prop="brand"
                         label="品牌">
        </el-table-column>
        <el-table-column prop="name"
                         label="车主姓名">
        </el-table-column>
      </el-table>

    </div>
    <!--详情对话框-->
        <el-dialog :title="title"  :visible.sync="open"
                              width="1000px"
                              append-to-body
                              class="dialogClass dialogHouseClass">
      <el-form ref="form"
               :model="form"
               :rules="rules"
               :inline="true"
               label-width="80px">
        <!-- 选择社区 -->
        <el-form-item label="社区名称:"
                      prop="community_id">
          <span class="addr">{{ communityNameFormat(null,null,form.community_id) }}</span>
        </el-form-item>
        <el-form-item label="小区名称:"
                      prop="person_community_child_id">
          <span class="addr">{{ communityChildNameFormat(null,null,form.person_community_child_id) }}</span>
        </el-form-item>
        <el-form-item label="车牌号码:"
                      prop="plate">
          <!-- <el-input v-model="form.plate"
                    placeholder="请输入车牌号码" /> -->
          <span class="addr">{{ form.plate }}</span>
        </el-form-item>
        <el-form-item label="车型:"
                      prop="model">
          <!-- <el-select v-model="form.model"
                     :disabled=" form.id &&form.id.length > 0 &&form.checked && form.checked === '1' "
                     placeholder="请选择车型">
            <el-option v-for="dict in modelOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select> -->
          <span class="addr">{{modelFormat(null,null,form.model)  }}</span>
        </el-form-item>
        <el-form-item label="车品牌:"
                      prop="brand">
          <!-- <el-input v-model="form.brand"
                    placeholder="请输入车品牌" /> -->
          <span class="addr">{{ form.brand }}</span>
        </el-form-item>
        <el-form-item label="具体型号:"
                      prop="specific_brand">
          <!-- <el-input v-model="form.specificBrand"
                    placeholder="请输入具体品牌型号" /> -->
          <span class="addr">{{ form.specific_brand }}</span>
        </el-form-item>
        <el-form-item label="座位数:"
                      prop="number_of_seats">
          <!-- <el-input v-model="form.numberOfSeats"
                    placeholder="请输入座位数" /> -->
          <span class="addr">{{ form.number_of_seats }}</span>
        </el-form-item>
        <el-form-item label="购买时间:"
                      prop="buy_time">
          <!-- <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.buyTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择购买时间">
          </el-date-picker> -->
          <span class="addr">{{ form.buy_time }}</span>
        </el-form-item>
        <!-- 关联人员 车主 车主房屋 -->
        <el-form-item label="备注"
                      prop="remark">
          <span class="addr">{{ form.remark }}</span>
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item label="车主姓名:"
                      prop="name">
          <span class="addr">{{ form.name }}</span>
        </el-form-item>
        <el-form-item label="性别:"
                      prop="sex">
          <span class="addr">{{sexFormat(null,null, form.sex) }}</span>
        </el-form-item>
        <el-form-item label="手机号码:"
                      prop="phone_number">
          <span class="addr">{{ form.phone_number }}</span>
        </el-form-item>
        <el-form-item label="证件号码:"
                      prop="identification_number">
          <span class="addr">{{ ellipsis(form.identification_number) }}</span>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
import $ from "jquery";
import { mapGetters, mapState } from "vuex";
import { getBottomlist } from '@/api/caringPeople/bottomList'
import { getCommunityVehicleList } from '@/api/vehicle/communityVehicle'
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_child } from "@/api/communityUnit/community_child";
export default {
  data () {
    return {
      tableData: [],
      targetPerson: [],
      caringeventtype: [
        { dictValue: 1, dictLabel: "长期未出现" },
        { dictValue: 2, dictLabel: "生日提醒" }
      ],
      communityOptions: [],
      allCommunityChildOptions: [],
      total: 0,
      queryparams: {
        pageNum: 1,
        pageSize: 20,
        personid: '',
        type: '',
        name: '',
      },
      open: false,
      title: "详情",
      form: {},
      //车型字典
      modelOptions: [],
      // 是否字典
      yesNoOptions: [],
      // 性别字典
      sexOptions: [],
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
    this.getCommunityDictory()
    this.getAllCommunityChildDictory()
    this.getDicts("base_vehicle_model").then((response) => {
      this.modelOptions = response.data;
    });
    this.getDicts("base_yes_no").then((response) => {
      this.yesNoOptions = response.data;
    });
    this.getDicts("sys_user_sex").then((response) => {
      this.sexOptions = response.data;
    });
  },
  mounted () {
    // this.getbotlistApi()
    this.tableData = this.testData.car.vehicleSearch;
    this.searchBtn()
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
    ellipsis (value) {
      if (!value) return ''
      if (value.length > 13) {
        return value.slice(0, 6) + '********' + value.slice(-4)
      }
      return value
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
    getAllCommunityChildDictory () {
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
    communityNameFormat (row, column, cellValue) {
      let communityName = ''
      if (this.communityOptions && this.communityOptions.length > 0) {
        let rows = this.communityOptions.filter((item) => {
          return item.id === cellValue;
        });
        if (rows && rows.length > 0) {
          communityName = rows[0].name;
        }
      }
      return communityName
    },
    communityChildNameFormat (row, column, cellValue) {
      if (
        this.allCommunityChildOptions &&
        this.allCommunityChildOptions.length > 0
      ) {
        let rows = this.allCommunityChildOptions.filter((item) => {
          return item.id === cellValue;
        });
        if (rows && rows.length > 0) {
          return rows[0].name;
        }
      }
      return "";
    },
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
    // 车型字典翻译
    modelFormat (row, column, cellValue) {
      // return this.selectDictLabel(this.modelOptions, row.model);
      let re = this.selectDictLabel(this.modelOptions, cellValue);
      return re && re.length > 0 ? re : cellValue
    },
    // 性别字典翻译
    sexFormat (row, column, cellValue) {
      let re = this.selectDictLabel(this.sexOptions, cellValue);
      return re && re.length > 0 ? re : cellValue
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
      getCommunityVehicleList(this.queryparams).then(response => {
        this.tableData = response.rows
        this.total = response.total;
      });
      // if (this.queryparams.plate) {
      //   let data = this.testData.car.vehicleSearch.filter(data => {
      //     return data.plate.indexOf(this.queryparams.plate) != -1;
      //   })
      //   // console.log(data);
      //   this.tableData = data;
      // } else {
      //   this.tableData = this.testData.car.vehicleSearch;
      // }
    },
    reset () {
      this.form = {
        name: '',
        address: "辽宁阜新市细河区四合镇",
        address_detail: "辽宁阜新市细河区四合镇",
        brand: "丰田",
        brith_date: "1984-06-23",
        buy_time: "2008-07-01",
        caring: "0",
        checked: "0",
        community_child_id: "11eacbf5-522a-11eb-8b0f-0242ac120002",
        community_id: "f2756e00-2a4f-11eb-8b0f-0242ac120002",
        create_by: "admin",
        create_time: "2021-01-08 17:57:14",
        degree: "10",
        del_flag: "0",
        energy_type: "01",
        floating: "0",
        id: "cc04e617-5226-11eb-8b0f-0242ac120030",
        id_type: "111",
        identification_number: "410203198406235727",
        is_key: "0",
        is_same: "0",
        limited: "0",
        location: "辽宁",
        marital: "0",
        model: "SUV",
        name: "夹谷双伟",
        nation: "01",
        nationality: "156",
        number_of_seats: 7,
        person_checked: "0",
        person_community_child_id: "11eacbf5-522a-11eb-8b0f-0242ac120002",
        person_community_id: "f2756e00-2a4f-11eb-8b0f-0242ac120002",
        person_del_flag: "0",
        person_id: "6e9f7h1b-2fbc-11eb-8b0f-0242ac120030",
        person_limited: "0",
        person_type: "01",
        phone_number: "15014552540",
        // pics: "[{"uid":1610092651982,"status":"success","name":"b1cb98997e4e80a5888e890820d2e13f.jpg","url":"http://10.17.13.103/group1/M00/00/01/ChB7PF_5TK6AIEOpAAESNvUSfO0924.png?token=e7c253e2106342ded2aa27df77aa4948&ts=1610173472"}]",
        plate: "豫LKE628",
        political: "01",
        residence_detail: "辽宁阜新市细河区四合镇",
        sex: "0",
        special: "0",
        stay_behind: "0"
      }
    },
    rowClick (row, col, cell) {
      this.reset()
      this.title = "详情"
      this.form = row
      this.open = true
    }
  }
}
</script>

<style scoped="scoped" lang="less">
/deep/ .el-table {
  font-size: 14px;
  // color: #606266;
  // background-color: #182751;
}
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
.addr {
  margin-left: 15px;
}
.dialogHouseClass .el-select .el-input .el-select__caret {
  color: #222751;
}
.dialogHouseClass .el-form-item__content {
  width: 180px;
}
</style>
