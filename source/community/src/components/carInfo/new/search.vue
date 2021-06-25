<template>
  <div class="carSearch">
    <el-row class="search">
      <el-col :span="8">
        <el-input placeholder="请输入车牌号"
                  class="searchInput"
                  v-model="queryparams.plate">
        </el-input>
      </el-col>
      <el-col :span="8">
        <el-input placeholder="请输入姓名"
                  class="searchInput"
                  v-model="queryparams.name">
        </el-input>
      </el-col>
      <el-col :span="4">
        <el-button type="primary"
                   class="searchbutton"
                   @click="searchBtn(queryparams)">搜 索</el-button>
      </el-col>
    </el-row>
    <div class="carSearchList">
      <el-table :data="tableData"
                @row-click="rowClick"
                class="carSearchListTable"
                :row-style="{height: '40px'}"
                :cell-style="{padding: '0'}"
                height="100%">
        <el-table-column prop="communityChildName"
                         label="所属小区"
                         align="center">
          <!-- :formatter="communityChildNameFormat"> -->
        </el-table-column>

        <el-table-column prop="plate"
                         align="center"
                         label="车牌号">
        </el-table-column>
        <el-table-column prop="buildingName"
                         align="center"
                         label="楼栋">
        </el-table-column>
        <el-table-column prop="name"
                         align="center"
                         label="车主姓名">
        </el-table-column>
      </el-table>
    </div>
    <!--详情对话框-->
    <el-dialog :visible.sync="open"
               width="45%"
               append-to-body
               :show-close="false"
               class="dialogCarClass">
      <div class="dialog_title">
        <el-col :span="23">{{title}}</el-col>
        <el-col :span="1"><span @click="open = false">{{"×"}}</span></el-col>
      </div>
      <div style="width:100%;height:100%">
        <el-col :span="10">
          <div class="dialogCarClass_pic">
            <img style="width:94%;height:92%;margin:3%"
                 :src="picUrl">
          </div>
        </el-col>
        <el-col :span="13">
          <div class="dialogCarClass_info">
            <el-row class="dialogCarClass_info_1">
              <el-form ref="form"
                       :model="form"
                       label-width="90px">

                <div class="el_form_itemDiv">
                  <el-form-item label="车牌号:"
                                prop="plate">
                    <span class="addr">{{ form.plate || '暂无'}}</span>
                  </el-form-item>
                  <el-form-item label="颜色:"
                                prop="zipinpai">
                    <span class="addr">{{ form.color || '暂无'}}</span>
                  </el-form-item>

                </div>
                <div class="el_form_itemDiv">
                  <el-form-item label="车位号:"
                                prop="pinpai">
                    <span class="addr">{{ form.parkingSpaceNum || '暂无'}}</span>
                  </el-form-item>
                  <el-form-item label="所属网格:"
                                prop="carType">
                    <span class="addr">{{ form.gridName || '暂无'}}</span>
                  </el-form-item>
                </div>
              </el-form>
            </el-row>
            <el-row class="dialogCarClass_info_2">
              <el-form ref="form"
                       :model="form"
                       label-width="100px">

                <div class="el_form_itemDiv">
                  <el-form-item label="车主:"
                                prop="name">
                    <span class="addr">{{ form.name|| '暂无' }}</span>
                  </el-form-item>
                  <el-form-item label="性别:"
                                prop="sex">
                    <span class="addr">{{ form.sex || '暂无'}}</span>
                  </el-form-item>
                </div>
                <div class="el_form_itemDiv">
                  <el-form-item label="年龄:"
                                prop="age">
                    <span class="addr">{{ form.age || '暂无'}}</span>
                  </el-form-item>
                  <el-form-item label="联系方式:"
                                prop="phone"
                                style="width:200px;">
                    <span class="addr">{{ form.phoneNumber || '暂无'}}</span>
                  </el-form-item>
                </div>

                <div class="el_form_itemDiv">
                  <el-form-item label="籍贯:"
                                prop="jiguan">
                    <span class="addr">{{ form.location || '暂无'}}</span>
                  </el-form-item>
                  <el-form-item label="身份证:"
                                prop="cardID"
                                style="width:250px;">
                    <span class="addr">{{ form.idNumber | ellipsis}}</span>
                  </el-form-item>
                </div>

                <div class="el_form_itemDiv">
                  <el-form-item label="房屋地址:"
                                class="addressform"
                                prop="fangwudizhi">
                    <span class="address">{{ form.address || '暂无'}}</span>
                  </el-form-item>
                </div>

              </el-form>
            </el-row>
          </div>
        </el-col>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import $ from "jquery";
import { mapGetters, mapState } from "vuex";
import { getBottomlist } from '@/api/caringPeople/bottomList'
import { getCommunityVehicleList_new } from '@/api/vehicle/communityVehicle'
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_child } from "@/api/communityUnit/community_child";
export default {
  data () {
    return {
      content_background_image: {
        backgroundImage: "url(" + require("../../../assets/images/car/content.png") + ")",
        backgroundRepeat: "no-repeat",
        backgroundSize: "420px auto",
        marginTop: "5px"
      },
      /* content_background_car_image: {
            backgroundImage: "url(" + require("../../../assets/images/car/cae_backage_pop.png") + ")",
            backgroundRepeat: "no-repeat",
            backgroundSize: "200px 210px",
            marginTop: "10px 10px 20px 10px"
      }, */
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
      picUrl: ''
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
    this.picUrl = this.testData.car.defaultCarPicUrl
  },
  mounted () {
    // this.getbotlistApi()
    // this.tableData = this.testData.car.vehicleSearch;
    // console.log()
    this.search()
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        // this.getbotlistApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  filters: {
    ellipsis (value) {
      if (!value) return ''
      if (value.length > 13) {
        return value.slice(0, 6) + '********' + value.slice(-4)
      }
      return value
    },
  },
  methods: {
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
    search () {
      // this.getbotlistApi()
      // debugger;
      getCommunityVehicleList_new(this.queryparams).then(response => {
        this.tableData = response.rows
        this.total = response.total;
        // console.log('cheliang:', this.tableData)
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
    searchBtn () {
      // console.log(this.queryparams.plate.toUpperCase(), 'this.queryparams.plate.toUpperCase()')
      this.queryparams.plate = this.queryparams.plate.toUpperCase();
      getCommunityVehicleList_new(this.queryparams).then(response => {
        this.tableData = response.rows
        this.total = response.total;
        // console.log('cheliang:', this.tableData)
      });
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
      this.title = "车辆信息"
      this.form = row
      this.open = true
      this.picUrl = 'http://172.21.15.54:7801' + row.vehicleImg
      if (row.sex == 0) {
        row.sex = '男'
      } else {
        row.sex = '女'
      }
    }
  }
}
</script>

<style scoped="scoped" lang="less">
@import "~@/assets/styles/main/carInfo.css";
</style>
<style lang="less">
.el-form-item {
  margin-bottom: 0px;
}
.carSearch .el-input--medium .el-input__inner {
  height: 26px;
  line-height: 26px;
  border: 0;
  color: #d3e3ec;
  // background-image: url("../../../assets/images/car/车辆信息搜索.png");
}

.first_area_data {
  margin-left: 15px;
  margin-top: 0px;
}

.car_backage {
  margin-top: 10%;
  padding-bottom: 5%;
  background-image: url(../../../assets/images/car/cae_backage_pop.png);
  background-repeat: no-repeat;
  background-size: 200px 220px;
}
</style>
