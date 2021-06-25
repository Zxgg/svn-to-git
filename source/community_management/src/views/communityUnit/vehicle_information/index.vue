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
      <el-form-item label="车牌号码"
                    prop="plate">
        <el-input v-model="queryParams.plate"
                  placeholder="请输入车牌号码"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="重点关注"
                    prop="checked">
        <el-select v-model="queryParams.isKey"
                   placeholder="请选择是否重点关注"
                   clearable
                   size="small">
          <el-option v-for="dict in yesNoOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="车型"
                    prop="model">
        <el-select v-model="queryParams.model"
                   placeholder="请选择车型"
                   clearable
                   size="small">
          <el-option v-for="dict in modelOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="车品牌"
                    prop="brand">
        <el-input v-model="queryParams.brand"
                  placeholder="请输入车品牌"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="具体型号"
                    prop="specificBrand">
        <el-input v-model="queryParams.specificBrand"
                  placeholder="请输入具体品牌型号"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="座位数"
                    prop="numberOfSeats">
        <el-input v-model="queryParams.numberOfSeats"
                  placeholder="请输入座位数"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="车库序号"
                    prop="parkingSpaceNumber">
        <el-input v-model="queryParams.parkingSpaceNumber"
                  placeholder="请输入车库序号"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="购买时间"
                    prop="buyTime">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="queryParams.buyTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择购买时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="是否审核"
                    prop="checked">
        <el-select v-model="queryParams.checked"
                   placeholder="请选择是否审核"
                   clearable
                   size="small">
          <el-option v-for="dict in checkedOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="重点关注"
                    prop="checked">
        <el-select v-model="queryParams.isKey"
                   placeholder="请选择是否重点关注"
                   clearable
                   size="small">
          <el-option v-for="dict in yesNoOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <el-form-item>
        <el-button type="cyan"
                   icon="el-icon-search"
                   size="mini"
                   @click="handleQuery">搜索</el-button>
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAdd"
                   v-hasPermi="['communityUnit:vehicle_information:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityUnit:vehicle_information:remove']">删除</el-button>
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
                   v-hasPermi="['communityUnit:vehicle_information:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['communityUnit:vehicle_information:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityUnit:vehicle_information:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['communityUnit:vehicle_information:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading"
              stripe
              :data="vehicle_informationList"
              :height="dataHeight"
              @selection-change="handleSelectionChange"
              :cell-class-name="getCellClassName">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="车牌号码"
                       align="center"
                       prop="plate" />
      <el-table-column label="车型"
                       align="center"
                       prop="model"
                       :formatter="modelFormat" />
      <el-table-column label="车品牌"
                       align="center"
                       prop="brand" />
      <el-table-column label="具体型号"
                       align="center"
                       prop="specificBrand" />
      <el-table-column label="座位数"
                       align="center"
                       prop="numberOfSeats" />
      <el-table-column label="车库序号"
                       align="center"
                       prop="parkingSpaceNumber" />
      <el-table-column label="购买时间"
                       align="center"
                       prop="buyTime"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.buyTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="备注"
                       align="center"
                       prop="remark" /> -->
      <el-table-column label="审核状态"
                       align="center"
                       prop="checked"
                       :formatter="checkedFormat">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     :disabled="scope.row.checked === '1'"
                     @click="handleUpdate_check(scope.row)"
                     v-hasPermi="['passManage:visitPerson:edit']">{{ scope.row.checked | checkedFilters(that) }}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作"
                       align="center"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-location-information"
                     @click="getPassList(scope.row.plateNo)">通行</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['communityUnit:vehicle_information:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['communityUnit:vehicle_information:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <vehicleTrack :trackData="trackObject" />

    <pagination v-show="total > 0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改车辆信息对话框 -->
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
                     clearable
                     :disabled="form.id &&form.id.length > 0 &&form.checked &&form.checked === '1'"
                     @change="getCommunityChildDictory2"
                     placeholder="请选择社区">
            <el-option v-for="dict in communityOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="小区名称"
                      prop="communityChildId">
          <el-select v-model="form.communityChildId"
                     filterable
                     clearable
                     @change="remotePersonInfoMethod('',form.communityId,form.communityChildId)"
                     :disabled="form.id &&form.id.length > 0 &&form.checked && form.checked === '1'"
                     placeholder="请选择小区">
            <el-option v-for="dict in communityChildOptions2"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="车牌号码"
                      prop="plate">
          <el-input v-model="form.plate"
                    :disabled="form.id &&form.id.length > 0 &&form.checked && form.checked === '1'"
                    placeholder="请输入车牌号码" />
        </el-form-item>
        <el-form-item label="车型"
                      prop="model">
          <el-select v-model="form.model"
                     :disabled=" form.id &&form.id.length > 0 &&form.checked && form.checked === '1' "
                     placeholder="请选择车型">
            <el-option v-for="dict in modelOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车品牌"
                      prop="brand">
          <el-input v-model="form.brand"
                    placeholder="请输入车品牌" />
        </el-form-item>
        <el-form-item label="具体型号"
                      prop="specificBrand">
          <el-input v-model="form.specificBrand"
                    placeholder="请输入具体品牌型号" />
        </el-form-item>
        <el-form-item label="座位数"
                      prop="numberOfSeats">
          <el-input v-model="form.numberOfSeats"
                    placeholder="请输入座位数" />
        </el-form-item>
        <el-form-item label="车库序号"
                      prop="parkingSpaceNumber">
          <el-input v-model="form.parkingSpaceNumber"
                    placeholder="请输入车库序号" />
        </el-form-item>
        <el-form-item label="购买时间"
                      prop="buyTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.buyTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择购买时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="重点关注"
                      prop="isKey">
          <el-select v-model="form.isKey"
                     placeholder="请选择重点关注">
            <el-option v-for="dict in yesNoOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <!-- 关联人员 车主 车主房屋 -->
        <el-button size="mini"
                   type="text"
                   icon="el-icon-edit"
                   @click="handleRelationClick"
                   v-hasPermi="['passManage:visitPerson:edit']">关联人员</el-button>
        <!-- <span>关联到人员</span> -->

        <el-form-item label="关联人员"
                      v-if="relatedShow"
                      prop="personnelId">
          <el-select v-model="relatedForm.personnelId"
                     filterable
                     :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                     @change="setTargetPerson"
                     remote
                     :remote-method="remotePersonInfoMethod"
                     :loading="loading_remotePersonInfo"
                     placeholder="请选择人员姓名">
            <el-option v-for="dict in personOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id">
              <span>{{ dict.name }}</span>
              <span v-if="dict.phoneNumber">{{ " " + dict.phoneNumber }}</span>
              <span v-if="dict.houseNumber">{{
                " " +
                dict.serialNumber +
                "号楼" +
                dict.unit +
                "单元" +
                dict.houseNumber
              }}</span>
            </el-option>
          </el-select>
          <span v-if="houseInfo && houseInfo.length > 0">{{
            "  " + houseInfo
          }}</span>
        </el-form-item>
        <el-form-item label="是否车主"
                      v-if="relatedShow"
                      prop="homeowner">
          <el-select v-model="relatedForm.homeowner"
                     placeholder="请选择是否车主">
            <el-option v-for="dict in yesNoOptions"
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
        <el-form-item label="审核状态"
                      v-if="form.id && form.id.length > 0"
                      prop="checked">
          <el-select v-model="form.checked"
                     :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                     placeholder="请选择是否审核">
            <el-option v-for="dict in checkedOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button @click="otherSubmitForm"
                   v-if="form.id && form.id.length > 0">另存一条</el-button>
        <el-button type="primary"
                   @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listVehicle_information,
  getVehicle_information,
  delVehicle_information,
  addVehicle_information,
  updateVehicle_information,
  exportVehicle_information,
  updateVehicleCheck,
} from "@/api/communityUnit/vehicle_information";
import { listCommunity } from "@/api/communityUnit/community";
import { getInfoByName } from "@/api/communityUnit/personnel_information";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import {
  listVehicle_houses_personnel,
  getVehicle_houses_personnel,
  delVehicle_houses_personnel,
  addVehicle_houses_personnel,
} from "@/api/communityUnit/vehicle_houses_personnel";
import vehicleTrack from "@/views/passManage/pass_track/vehicle_track";

export default {
  name: "Vehicle_information",
  components: {
    vehicleTrack
  },
  data () {
    return {
      that: this,
      // 遮罩层
      loading: true,
      //远程人人员搜索
      loading_remotePersonInfo: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 车辆信息表格数据
      vehicle_informationList: [],
      // 社区
      communityOptions: [],
      //小区列表
      communityChildOptions: [],
      //小区列表
      communityChildOptions2: [],
      //远程搜索人员列表
      personOptions: [],
      // 是否 字典
      yesNoOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 车型字典
      modelOptions: [],
      // 是否审核字典
      checkedOptions: [],
      //删除提示语
      deleteNotces: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        communityId: null,
        communityChildId: null,
        plate: null,
        model: null,
        brand: null,
        specificBrand: null,
        numberOfSeats: null,
        parkingSpaceNumber: null,
        buyTime: null,
        checked: null,
        isKey: null,
        delFlag: "0",
      },
      // 表单参数
      form: {},
      //关联信息表单
      relatedForm: {},
      //是否显示关联信息
      relatedShow: false,
      // 关联房屋的信息
      houseInfo: "",
      //通行
      trackObject: {
        plateNo: null,
        drawShow: false,
      },
      // 表单校验
      rules: {
        communityId: [
          { required: true, message: "社区名称不能为空", trigger: "blur" },
        ],
        communityChildId: [
          { required: true, message: "小区名称不能为空", trigger: "blur" },
        ],
        plate: [
          { required: true, message: '车牌号不能为空', trigger: 'blur' },
          { pattern: /(^[\u4E00-\u9FA5]{1}[A-Z0-9]{6}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{7}$)|(^[A-Z]{2}[A-Z0-9]{2}[A-Z0-9\u4E00-\u9FA5]{1}[A-Z0-9]{4}$)|(^[A-Z]{2}[A-Z0-9]{2}[A-Z0-9\u4E00-\u9FA5]{1}[A-Z0-9]{5}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{5}[挂学警军港澳]{1}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{6}[挂学警军港澳]{1}$)|(^[A-Z]{2}[0-9]{5}$)|(^[A-Z]{2}[0-9]{6}$)|(^(08|38){1}[A-Z0-9]{4}[A-Z0-9挂学警军港澳]{1}$)|(^(08|38){1}[A-Z0-9]{5}[A-Z0-9挂学警军港澳]{1}$)/, message: '常规格式：京A12345,新能源格式: 京A123456' },
        ],
        buyTime: [
          { required: true, message: "请选择购买时间", trigger: "blur" },
        ],
        delFlag: [
          { required: true, message: "删除标志不能为空", trigger: "blur" },
        ],
      },
      // 搜索表单控件高度
      formHeight: 0,
    };
  },
  created () {
    this.getList();
    this.getCommunityDictory();
    this.getDicts("base_vehicle_model").then((response) => {
      this.modelOptions = response.data;
    });
    this.getDicts("base_checked_stat").then((response) => {
      this.checkedOptions = response.data;
    });
    this.getDicts("base_yes_no").then((response) => {
      this.yesNoOptions = response.data;
    });
  },
  filters: {
    checkedFilters: function (value, self) {
      return self.checkedFormat(null, null, value);
    },
  },
  mounted () {
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
  methods: {
    //查询通行列表
    getPassList (plateNo) {
      this.trackObject.plateNo = plateNo;
      this.trackObject.drawShow = true;
      // console.log(this.trackObject);
    },
    /** 查询车辆信息列表 */
    getList () {
      this.loading = true;
      listVehicle_information(this.queryParams).then((response) => {
        this.vehicle_informationList = response.rows;
        this.total = response.total;
        this.loading = false;
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
    getCommunityChildDictory2 (communityId) {
      this.communityChildOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId,
      };
      listCommunity_child(param).then((response) => {
        this.communityChildOptions2 = response.rows;
      });
    },
    remotePersonInfoMethod (name, communityId, communityChildId) {
      this.loading_remotePersonInfo = true;
      let param = {
        name: name,
        communityId: communityId,
        communityChildId: communityChildId,
      };

      getInfoByName(param).then((response) => {
        this.personOptions = response.rows;
        this.loading_remotePersonInfo = false;
        if (
          this.relatedForm.personnelId &&
          this.relatedForm.personnelId.length > 0
        ) {
          this.getHouseInfo(this.relatedForm.communityHousesId);
        }
      });
    },
    setTargetPerson (targetPersonId) {
      if (targetPersonId && targetPersonId.length > 0) {
        let target = this.personOptions.filter((item) => {
          return item.id === targetPersonId;
        });
        if (target && target.length > 0) {
          this.relatedForm.personnelId = target[0].id;
          this.relatedForm.communityId = target[0].communityId;
          this.relatedForm.communityChildId = target[0].communityChildId;
          this.relatedForm.communityHousesId = target[0].houseId;
          this.getHouseInfo(this.relatedForm.communityHousesId);
        }
      }
    },
    getHouseInfo (houseId) {
      this.houseInfo = "";
      if (this.personOptions && this.personOptions.length > 0) {
        let rows = this.personOptions.filter((item) => {
          return item.houseId === houseId;
        });
        if (rows && rows.length > 0) {
          let dict = rows[0];
          this.houseInfo =
            dict.serialNumber + "号楼" + dict.unit + "单元" + dict.houseNumber;
        }
      }
    },
    // 车型字典翻译
    modelFormat (row, column) {
      return this.selectDictLabel(this.modelOptions, row.model);
    },
    // 是否审核字典翻译
    checkedFormat (row, column, cellValue) {
      return this.selectDictLabel(this.checkedOptions, cellValue);
    },
    getCellClassName ({ row, column, rowIndex, columnIndex }) {
      let propertyName = column.property //"communityId"
      let className = ''
      let iskeyPropName = 'isKey'
      switch (propertyName) {
        //车牌重点关注
        case 'plate': if (row[iskeyPropName] && row[iskeyPropName] === '1') className = 'yellowText'
        default: break
      }
      return className
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
        communityId: null,
        communityChildId: null,
        plate: null,
        model: null,
        brand: null,
        specificBrand: null,
        numberOfSeats: null,
        parkingSpaceNumber: null,
        buyTime: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        remark: null,
        isKey: '0',
        checked: "0",
      };
      this.resetForm("form");
      this.relatedForm = {
        id: null,
        communityId: null,
        communityChildId: null,
        communityHousesId: null,
        personnelId: null,
        vehicleId: null,
        typeOfResidence: "1", //车辆使用类型（0未知,1自有车辆，2租赁,3临时使用）
        homeowner: "1", //是否是车主,(0否1是,默认1)
        parking_space_number: null, //停车位
      };
      this.houseInfo = null;
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
      this.deleteNotces = selection.map((item) => item.plate);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset();
      this.open = true;
      this.title = "添加车辆信息";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      let id = "";
      let vehicleId = "";
      if (row.id) {
        id = row.id;
        vehicleId = id;
      } else {
        id = this.ids;
        vehicleId = this.$cf.getStringFromList(id);
      }
      getVehicle_information(id).then((response) => {
        this.form = response.data;
        this.getCommunityChildDictory2(this.form.communityId);
        this.open = true;
        this.title = "修改车辆信息";
      });

      let ralationParam = {
        vehicleId: vehicleId,
      };
      listVehicle_houses_personnel(ralationParam).then((response) => {
        this.relatedShow = false;
        console.log(response, 'response')
        if (response.rows && response.rows.length > 0) {
          this.relatedForm = response.rows[0];
          this.relatedShow = true;
          console.log(this.relatedForm, 'this.relatedForm')
          this.getCommunityChildDictory(this.relatedForm.communityId);
          this.remotePersonInfoMethod(this.relatedForm.personName);
        }
      });
    },
    getPassList (vehicleId) {
      this.trackObject.vehicleId = vehicleId;
      this.trackObject.drawShow = true;
      // console.log(this.trackObject);
    },
    /** 另存一条 提交按钮 */
    otherSubmitForm () {
      this.form.id = null;
      this.submitForm();
    },
    /** 提交按钮 */
    submitForm () {
      let self = this;
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (self.form.id != null) {
            updateVehicle_information(self.form).then((response) => {
              if (response.code === 200) {
                self.msgSuccess("修改成功");
                self.open = false;
                self.getList();
              }
            });
            if (self.relatedForm.id && self.relatedForm.id.length > 0) {
              //原来有关联
            } else {
              //原来没有关联
              if (
                self.relatedForm.personnelId &&
                self.relatedForm.personnelId
              ) {
                //本次新增了关联
                self.submitRelationForm(newVehicleId);
              }
            }

            // self.submitRelationForm(self.form.id)
          } else {
            addVehicle_information(self.form).then((response) => {
              if (response.code === 200) {
                // self.submitRelationForm(self.form.id)
                self.msgSuccess("新增成功");
                self.open = false;
                self.getList();
                let newVehicleId = response.data;
                if (
                  self.relatedForm.personnelId &&
                  self.relatedForm.personnelId
                ) {
                  //本次新增了关联
                  self.submitRelationForm(newVehicleId);
                }
              }
            });
          }
        }
      });
    },
    async submitRelationForm (vehicleId) {
      this.relatedForm.vehicleId = vehicleId;
      if (this.relatedForm.id && this.relatedForm.id.length > 0) {
        let changeFlag = await this.checkVehicleRelationInfoChange(
          this.relatedForm.id
        );
        if (changeFlag) {
          delVehicle_houses_personnel(this.relatedForm.id);
          this.relatedForm.id = null;
        }
      }
      addVehicle_houses_personnel(this.relatedForm).then((response) => {
        if (response.code === 200) {
          this.msgSuccess("新增关联成功");
        }
      });
    },
    /* 当关联信息的ID存在时 检查此关联信息是否被改变 */
    async checkVehicleRelationInfoChange (id) {
      //查询当前车辆的关联信息是否呗改变，
      let data = await getVehicle_houses_personnel(id);
      return data.personnelId === this.relatedForm.personnelId ? false : true;
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      const ids = row.id || this.ids;
      let deleteNoctice = row.plate || this.deleteNotces;
      this.$confirm(
        '是否确认删除车牌号码为"' + deleteNoctice + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delVehicle_information(ids);
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
      this.$confirm("是否确认导出所有车辆信息数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportVehicle_information(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () { });
    },
    /** 修改审核状态操作 */
    handleUpdate_check (row) {
      const name = row.name;
      let self = this;
      this.$confirm("是否确认车辆信息?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(function () {
        let param = {
          id: row.id,
          checked: "1",
          checkedTime: self.$cf.getNowTime(),
        };
        updateVehicleCheck(param)
          .then((response) => {
            if (response.code === 200) {
              self.msgSuccess("修改成功");
              self.open = false;
              self.getList();
            }
          })
          .catch(function () { });
      });
    },
    /** 修改关联信息是否展示 */
    handleRelationClick () {
      this.relatedShow = !this.relatedShow;
    },
  },
};
</script>
