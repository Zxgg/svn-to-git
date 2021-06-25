<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch"
             label-width="68px">
      <el-form-item label="社区名称"
                    prop="communityId">
        <el-select v-model="queryParams.communityId"
                   filterable
                   clearable
                   @change="getCommunityChildDictory"
                   placeholder="请选择社区">
          <el-option v-for="dict in communityOptions"
                     :key="dict.id"
                     :label="dict.name"
                     :value="dict.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="小区名称"
                    prop="communityChildId">
        <el-select v-model="queryParams.communityChildId"
                   filterable
                   clearable
                   placeholder="请选择小区">
          <el-option v-for="dict in communityChildOptions"
                     :key="dict.id"
                     :label="dict.name"
                     :value="dict.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="人员名称"
                    prop="personName">
        <el-input v-model="queryParams.personName"
                  placeholder="请输入关爱人员名称"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="关爱类型"
                    prop="type">
        <el-select v-model="queryParams.type"
                   placeholder="请选择关爱类型"
                   clearable
                   size="small">
          <el-option v-for="dict in typeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="cyan"
                   icon="el-icon-search"
                   size="mini"
                   @click="handleQuery">搜索</el-button>
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAdd"
                   v-hasPermi="['caringPerson:caring_person:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['caringPerson:caring_person:remove']">删除</el-button>
        <!-- <el-button icon="el-icon-refresh"
                   size="mini"
                   @click="resetQuery">重置</el-button> -->
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10"
            class="mb8">
      <el-col :span="1.5">
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAdd"
                   v-hasPermi="['caringPerson:caring_person:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['caringPerson:caring_person:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['caringPerson:caring_person:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['caringPerson:caring_person:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading"
              stripe
              :height="dataHeight"
              :data="caring_personList"
              row-key="id"
              :expand-row-keys="expandRowIds"
              @expand-change="handleExpandChange"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column type="expand">
        <template slot-scope="scope">
          <div class="divStackPanel_HorizontalLeft"
               style="width: 100%; height: 140px">
            <el-card class="box-card"
                     style="width: 140px; height: 100%">
              <el-image v-if="scope.row.pics && scope.row.pics.length > 0"
                        style="width: 100%; height: 100%"
                        :src="getFirstUrlFromJson(scope.row.pics)"
                        fit="contain">
                <div slot="placeholder"
                     class="image-slot">
                  <span class="iconfont icontuxiangziliao"
                        style="font-size: 36px" />
                </div>
                <div slot="error"
                     class="image-slot">
                  <span class="iconfont icontuxiangziliao"
                        style="font-size: 36px" />
                </div>
              </el-image>
              <el-image v-else
                        :src="defaultPersonImage"
                        style="width: 100%; height: 100%" />
              <!-- <span v-else
                    class="icon iconfont iconfl-renyuan"
                    style="width: 100%; height: 100%;font-size:80px;"></span> -->
            </el-card>
            <el-divider direction="vertical" />
            <el-card class="box-card"
                     :body-style="{ padding: '4px 20px' }"
                     style="width: 400px; height: 100%">
              <div slot="header"
                   class="clearfix">
                <span class="textLeft">关联人员</span>
                <!-- <el-button style="float: right; padding: 3px 0"
                           type="text">操作按钮</el-button> -->
              </div>
              <el-scrollbar style="height: 86px"
                            wrap-class="scrollbar-wrapper "
                            wrap-style="overflow-x:hidden;">
                <div v-for="(item, index) in personInformationVoList"
                     :key="index"
                     class="textLeft text item">
                  {{ index + 1 + "," + item.name + " " }}
                  {{sexFormat(null,null,item.sex)}}
                  {{ item.phoneNumber ? "  " + item.phoneNumber : "" }}
                </div>
              </el-scrollbar>
              <!-- <el-table v-loading="loadingVoPersonList"
                        :show-header="false"
                        :data="personInformationVoList">
                <el-table-column label="姓名"
                                 align="center"
                                 prop="name" />
                <el-table-column label="手机号"
                                 align="center"
                                 prop="phoneNumber" />
              </el-table> -->
            </el-card>
            <el-divider direction="vertical" />
            <el-card class="box-card"
                     :body-style="{ padding: '4px 20px' }"
                     style="width: 440px; height: 100%">
              <div slot="header"
                   class="clearfix">
                <span class="textLeft">关联房屋</span>
                <!-- <el-button style="float: right; padding: 3px 0"
                           type="text">操作按钮</el-button> -->
              </div>
              <el-scrollbar style="height: 86px"
                            wrap-class="scrollbar-wrapper "
                            wrap-style="overflow-x:hidden;">
                <div v-for="(item, index) in houseInformationVoList"
                     :key="index"
                     class="textLeft text item">
                  {{ index + 1 + "," }}
                  {{ item.communityId | communityNameFilters(that) }}
                  {{ item.communityChildId | communityChildNameFilters(that) }}
                  {{
                    item.serialNumber +
                    "号楼" +
                    item.unit +
                    "单元" +
                    item.houseNumber
                  }}
                </div>
              </el-scrollbar>
            </el-card>
            <el-divider direction="vertical" />
            <el-card class="box-card"
                     :body-style="{ padding: '4px 20px' }"
                     style="width: 300px; height: 100%">
              <div slot="header"
                   class="clearfix">
                <span class="textLeft">关联车辆</span>
                <!-- <el-button style="float: right; padding: 3px 0"
                           type="text">操作按钮</el-button> -->
              </div>
              <el-scrollbar style="height: 86px"
                            wrap-class="scrollbar-wrapper "
                            wrap-style="overflow-x:hidden;">
                <div v-for="(item, index) in vehicleInformationVoList"
                     :key="index"
                     class="textLeft text item">
                  {{ index + 1 + "," }}
                  {{ item.plate }}
                  {{ item.model | vehicleModelFilters(that) }}
                  {{ item.brand }}
                  {{ item.specificBrand }}
                  {{ item.numberOfSeats ? item.numberOfSeats + "座" : "" }}
                  {{
                    item.parkingSpaceNumber
                      ? " 车库" + item.parkingSpaceNumber
                      : ""
                  }}
                </div>
              </el-scrollbar>
            </el-card>
          </div>

          <!-- <el-form label-position="left"
                   inline
                   class="demo-table-expand">
            <el-form-item label="商品名称">
              <span>{{ props.row.name }}</span>
            </el-form-item>
            <el-form-item label="商品描述">
              <span>{{ props.row.desc }}</span>
            </el-form-item>
          </el-form> -->
        </template>
      </el-table-column>

      <el-table-column label="社区"
                       align="center"
                       prop="communityName" />
      <el-table-column label="小区"
                       align="center"
                       prop="communityChildName" />
      <el-table-column label="姓名"
                       align="center"
                       prop="personName" />
      <el-table-column label="性别"
                       align="center"
                       prop="sex"
                       width="auto"
                       :formatter="sexFormat" />
      <el-table-column label="手机号码"
                       align="center"
                       prop="phoneNumber" />

      <el-table-column label="证件类型"
                       align="center"
                       prop="idType"
                       :formatter="idTypeFormat" />
      <el-table-column label="证件号码"
                       align="center"
                       width="120px"
                       prop="identificationNumber" />
      <el-table-column label="关爱类型"
                       align="center"
                       prop="type"
                       :formatter="typeFormat" />
      <el-table-column label="详细地址"
                       align="center"
                       width="180px"
                       prop="addressDetail" />
      <!-- <el-table-column label="备注" align="center" prop="remark" /> -->
      <el-table-column label="操作"
                       align="center"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-location-information"
                     @click="getPassList(scope.row.personId)">通行</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['caringPerson:caring_person:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['caringPerson:caring_person:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <personTarck :trackData="trackObject" />
    <pagination v-show="total > 0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改关爱人员信息记录 对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="500px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="80px">
        <!-- 选择社区 -->
        <el-form-item label="社区名称"
                      prop="communityId">
          <el-select v-model="form.communityId"
                     filterable
                     @change="getCommunityChildDictory"
                     placeholder="请选择社区">
            <el-option v-for="dict in communityOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-refresh"
                     @click="getCommunityDictory"
                     v-hasPermi="['communityUnit:community:query']">刷新</el-button>
        </el-form-item>
        <el-form-item label="小区名称"
                      prop="communityChildId">
          <el-select v-model="form.communityChildId"
                     filterable
                     placeholder="请选择小区">
            <el-option v-for="dict in communityChildOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="社区ID"
                      prop="communityId">
          <el-input v-model="form.communityId"
                    placeholder="请输入社区ID" />
        </el-form-item> -->
        <el-form-item label="人员姓名"
                      prop="personId">
          <el-select v-model="form.personId"
                     filterable
                     remote
                     :remote-method="remotePersonInfoMethod"
                     :loading="loading_remotePersonInfo"
                     placeholder="请选择人员姓名">
            <el-option v-for="dict in personOptions"
                       :key="dict.id"
                       :label="
                dict.name +
                ' ' +
                dict.phoneNumber +
                ' ' +
                dict.serialNumber +
                '号楼' +
                dict.unit +
                '单元' +
                dict.houseNumber
              "
                       :value="dict.id" />
          </el-select>
          <!-- <el-input v-model="form.personId"
                    placeholder="请输入关爱人员ID" /> -->
        </el-form-item>
        <el-form-item label="关爱类型"
                      prop="type">
          <el-select v-model="form.type"
                     placeholder="请选择关爱类型">
            <el-option v-for="dict in typeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注"
                      prop="remark">
          <el-input v-model="form.remark"
                    type="textarea"
                    placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button type="primary"
                   @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listCaring_person,
  getCaring_person,
  delCaring_person,
  addCaring_person,
  updateCaring_person,
  exportCaring_person,
} from "@/api/caringPerson/caring_person";
import { listCommunity } from "@/api/communityUnit/community";
import { getInfoByName } from "@/api/communityUnit/personnel_information";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import personTarck from "@/views/passManage/pass_track/person_track";
import {
  listVehicle_information,
  getVehicle_information,
  selectVehicleInformationVoList,
} from "@/api/communityUnit/vehicle_information";
import {
  listCommunity_houses,
  getCommunity_houses,
  queryList_houses,
} from "@/api/communityUnit/community_houses";
import { selectPerInformByHousesId } from "@/api/communityUnit/personnel_information";

import defaultPersonPic from "@/assets/images/home/default-renyuan.png";
export default {
  name: "Caring_person",
  components: {
    personTarck,
  },
  data () {
    return {
      //通行
      trackObject: {
        personId: null,
        drawShow: false,
      },
      that: this,
      // 遮罩层
      loading: true,
      //远程人人员搜索
      loading_remotePersonInfo: false,
      //加载关联人员
      loadingVoPersonList: false,
      // 选中数组
      ids: [],
      personNames: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 关爱人员信息记录 表格数据
      caring_personList: [],
      //社区列表
      communityOptions: [],
      //小区列表
      communityChildOptions: [],
      //小区列表全集
      allCommunityChildOptions: [],
      //远程搜索人员列表
      personOptions: [],
      // 关爱类型字典
      typeOptions: [],
      // 性别字典
      sexOptions: [],
      // 车型字典
      modelOptions: [],
      // 证件类型代码字典
      idTypeOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        personId: null,
        personName: null,
        name: "",
        type: null,
        communityId: null,
        communityName: null,
        communityChildId: null,
        communityChildName: null,
      },
      // 表单参数
      form: {},
      // 表单展开层图片
      // defaultPersonImage: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1608291358078&di=80b16a72cdb00b77d6bbcb4e0dfabd58&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn10%2F309%2Fw534h575%2F20180926%2Fa837-hhuhisn1021919.jpg',
      defaultPersonImage: defaultPersonPic,
      // 表单展开层 关联车辆
      vehicleInformationVoList: [],
      // 表单展开层 关联房屋
      houseInformationVoList: [],
      // 表单展开层 关联人员
      personInformationVoList: [],
      // 展开行ID列表，用来控制只展开一行
      expandRowIds: [],
      // 表单校验
      rules: {
        // communityId: [
        //   { required: true, message: "社区名称不能为空", trigger: "blur" },
        // ],
        // communityChildId: [
        //   { required: true, message: "小区名称不能为空", trigger: "blur" },
        // ],
        // personId: [
        //   { required: true, message: "关爱人员不能为空", trigger: "blur" },
        // ],
        type: [
          { required: true, message: "关爱类型不能为空", trigger: "blur" },
        ],
      },
      // 搜索表单控件高度
      formHeight: 0,
    };
  },
  created () {
    this.getAllCommunityChildDictory();
    this.getCommunityDictory();
    this.getDicts("base_type_of_caring").then((response) => {
      this.typeOptions = response.data;
    });
    this.getDicts("sys_user_sex").then((response) => {
      this.sexOptions = response.data;
    });
    this.getDicts("base_idCard").then((response) => {
      this.idTypeOptions = response.data;
    });
    this.getDicts("base_vehicle_model").then((response) => {
      this.modelOptions = response.data;
    });
  },
  mounted () {
    this.getList();
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight;
    });
  },
  computed: {
    dataHeight () {
      let searchFormHeight = 0;
      if (this.showSearch) searchFormHeight = this.formHeight;
      return this.$store.getters.dataEleHeight - searchFormHeight - 115;
    },
  },
  filters: {
    addressFilters: function (value, self) {
      if (value && value.id.length > 0) {
        let address =
          value.serialNumber + "号楼" + value.unit + "单元" + value.houseNumber;
        return address;
      } else {
        return "";
      }
    },
    communityNameFilters (value, self) {
      if (self.communityOptions && self.communityOptions.length > 0) {
        let rows = self.communityOptions.filter((item) => {
          return item.id === value;
        });
        if (rows && rows.length > 0) {
          return rows[0].name;
        }
      }
      return "??";
    },
    communityChildNameFilters (value, self) {
      if (
        self.allCommunityChildOptions &&
        self.allCommunityChildOptions.length > 0
      ) {
        let rows = self.allCommunityChildOptions.filter((item) => {
          return item.id === value;
        });
        if (rows && rows.length > 0) {
          return rows[0].name;
        }
      }
      return "??";
    },
    vehicleModelFilters (value, self) {
      return self.modelFormat(value);
    },
    sexFilters (value, self) {
      return self.sexFormat(null, null, value);
    },
  },
  methods: {
    // 车型字典翻译
    modelFormat (row, column) {
      return this.selectDictLabel(this.modelOptions, row.model);
    },
    //查询通行列表
    getPassList (personId) {
      this.trackObject.personId = personId;
      this.trackObject.drawShow = true;
      // console.log(this.trackObject);
    },
    getVehicleVoList (row) {
      let param = {
        personnelId: row.personId,
        isDelFlag: "0",
      };
      selectVehicleInformationVoList(param).then((response) => {
        this.vehicleInformationVoList = response.rows;
      });
    },
    getHouseVoList (row) {
      let param = {
        personnelId: row.personId,
        isDelFlag: "0",
      };
      queryList_houses(param).then((response) => {
        this.houseInformationVoList = response.rows;
        let houseIds = this.houseInformationVoList.map((item) => item.id);
        this.getPersonVoList(houseIds);
      });
    },
    getPersonVoList (houseIdArray) {
      let queryDate = {
        houseId: this.$cf.getStringFromList(houseIdArray),
      };
      this.loadingVoPersonList = true;
      selectPerInformByHousesId(queryDate).then((response) => {
        this.personInformationVoList = response.rows;
        console.log("关联人员：" + this.personInformationVoList.length);
        this.loadingVoPersonList = false;
      });
    },
    handleExpandChange (row, expandedRows) {
      this.expandRowIds = [];
      if (expandedRows.length > 0 && row) {
        this.expandRowIds.push(row.id);
        this.getVehicleVoList(row);
        this.getHouseVoList(row);
      }
    },
    communityNameFormat (row, column, cellValue) {
      if (this.communityOptions && this.communityOptions.length > 0) {
        let rows = this.communityOptions.filter((item) => {
          return item.id === cellValue;
        });
        if (rows && rows.length > 0) {
          return rows[0].name;
        }
      }
      return "??";
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
    getCommunityChildDictory (communityId) {
      this.communityChildOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId,
      };
      listCommunity_child(param).then((response) => {
        this.communityChildOptions = response.rows;
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
    remotePersonInfoMethod (name) {
      this.loading_remotePersonInfo = true;
      let param = {
        name: name,
        communityId: null,
        communityChildId: null,
      };
      if (this.form.communityId) {
        param.communityId = this.form.communityId;
      }
      if (this.form.communityChildId) {
        param.communityChildId = this.form.communityChildId;
      }
      getInfoByName(param).then((response) => {
        this.personOptions = response.rows;
        this.loading_remotePersonInfo = false;
      });
    },
    // 关爱类型字典翻译
    typeFormat (row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
    },
    // 性别字典翻译
    sexFormat (row, column, cellValue) {
      return this.selectDictLabel(this.sexOptions, cellValue);
    },
    // 证件类型代码字典翻译
    idTypeFormat (row, column) {
      return this.selectDictLabel(this.idTypeOptions, row.idType);
    },
    /** 查询关爱人员信息记录 列表 */
    getList () {
      this.loading = true;
      listCaring_person(this.queryParams).then((response) => {
        this.caring_personList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      // getInfoByName(this.queryParams).then(response => {
      //   this.caring_personList = response.rows;
      //   this.total = response.total;
      //   this.loading = false;
      // })
    },
    // 取消按钮
    cancel () {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset () {
      this.form = {
        id: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: "0",
        remark: null,
        personId: null,
        type: null,
        communityId: null,
        communityChildId: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery () {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery () {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange (selection) {
      this.ids = selection.map((item) => item.id);
      this.personNames = selection.map((item) => item.personName)
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset();
      this.open = true;
      this.title = "添加关爱人员信息记录 ";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const id = row.id || this.ids;
      getCaring_person(id).then((response) => {
        this.form = response.data;
        this.getCommunityChildDictory(this.form.communityId);
        this.remotePersonInfoMethod(row.personName);
        this.open = true;
        this.title = "修改关爱人员信息记录 ";
      });
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateCaring_person(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addCaring_person(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      const ids = row.id || this.ids;
      const personNames = row.personName || this.personNames;
      // this.$confirm( '是否确认删除关爱人员信息记录 编号为"' + ids + '"的数据项?', "警告", {
      this.$confirm('是否确认删除 姓名为"' + personNames + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }
      )
        .then(function () {
          return delCaring_person(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有关爱人员信息记录 数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportCaring_person(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () { });
    },
  },
};
</script>
<style lang="scss" scoped>
@import "@/assets/styles/variables.scss";
.el-divider {
  background-color: $titleC;
}
.el-divider--vertical {
  display: inline-block;
  width: 1px;
  height: 100px;
  margin: 0 8px;
  vertical-align: middle;
  position: relative;
}
.el-scrollbar {
  height: 100%;
  .el-scrollbar__wrap {
    overflow-x: hidden !important;
    margin-right: -222px;
  }
}
.el-scrollbar__wrap {
  overflow-x: hidden !important;
  margin-right: -222px;
}
.el-card box-card is-always-shadow {
  .el-card__header {
    padding-top: 4px;
    padding-bottom: 4px;
  }
  .el-card__body {
    padding-top: 4px;
    padding-bottom: 4px;
  }
}
.el-card__body {
  padding: 15px 4px 20px 4px !important;
}
.card_body {
  overflow-y: auto !important;
  padding: "4px 20px";
}
</style>
