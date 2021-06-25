<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch"
             label-width="68px">
      <el-form-item label="车牌号码"
                    prop="plate">
        <el-input v-model="queryParams.plate"
                  placeholder="请输入车牌号码"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="车型"
                    prop="model"
                    label-width="auto">
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
                    prop="brand"
                    label-width="auto">
        <el-input v-model="queryParams.brand"
                  placeholder="请输入车品牌"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="具体型号"
                    prop="specificBrand">
        <el-input v-model="queryParams.specificBrand"
                  placeholder="请输入具体型号"
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
      </el-form-item> -->
      <el-form-item label="驾驶员姓名"
                    prop="driverName"
                    label-width="auto">
        <el-input v-model="queryParams.driverName"
                  placeholder="请输入驾驶员姓名"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="驾驶员身份证号码"
                    prop="driverIdentificationNumber"
                    label-width="auto">
        <el-input v-model="queryParams.driverIdentificationNumber"
                  placeholder="请输入驾驶员身份证号码"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="驾驶员性别"
                    prop="driverSex"
                    label-width="auto">
        <el-select v-model="queryParams.driverSex"
                   placeholder="请选择驾驶员性别"
                   clearable
                   size="small">
          <el-option v-for="dict in driverSexOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <el-form-item label="审核状态"
                    prop="checked"
                    label-width="auto">
        <el-select v-model="queryParams.checked"
                   placeholder="请选择是否已审核"
                   clearable
                   size="small">
          <el-option v-for="dict in checkedOptions"
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
                   v-hasPermi="['passManage:temp_visitor_vehicle:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['passManage:temp_visitor_vehicle:remove']">删除</el-button>
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
                   v-hasPermi="['passManage:temp_visitor_vehicle:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['passManage:temp_visitor_vehicle:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['passManage:temp_visitor_vehicle:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['passManage:temp_visitor_vehicle:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading"
              stripe
              :height="dataHeight"
              :data="temp_visitor_vehicleList"
              @selection-change="handleSelectionChange">
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
      <el-table-column label="驾驶员姓名"
                       align="center"
                       prop="driverName" />
      <el-table-column label="驾驶员身份证号码"
                       align="center"
                       prop="driverIdentificationNumber" />
      <el-table-column label="性别"
                       align="center"
                       prop="driverSex"
                       :formatter="driverSexFormat" />
      <el-table-column label="创建时间"
                       align="center"
                       prop="createTime"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="更新时间"
                       align="center"
                       prop="updateTime"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="审核状态"
                       align="center"
                       width="auto"
                       prop="checked"
                       :formatter="checkedFormat">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     :disabled="scope.row.checked === '1'"
                     @click="handleUpdate_check(scope.row)"
                     v-hasPermi="['passManage:temp_visitor_vehicle:edit']">{{ scope.row.checked | checkedFilters(that) }}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作"
                       align="center"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['passManage:temp_visitor_vehicle:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['passManage:temp_visitor_vehicle:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改临时访客车辆信息对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="500px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="140px">
        <el-form-item label="车牌号码"
                      prop="plate">
          <el-input v-model="form.plate"
                    placeholder="请输入车牌号码" />
        </el-form-item>
        <el-form-item label="车型"
                      prop="model">
          <el-select v-model="form.model"
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
        <el-form-item label="具体品牌型号"
                      prop="specificBrand">
          <el-input v-model="form.specificBrand"
                    placeholder="请输入具体品牌型号" />
        </el-form-item>
        <el-form-item label="座位数"
                      prop="numberOfSeats">
          <el-input v-model="form.numberOfSeats"
                    placeholder="请输入座位数" />
        </el-form-item>
        <el-form-item label="驾驶员姓名"
                      prop="driverName">
          <el-input v-model="form.driverName"
                    placeholder="请输入驾驶员姓名" />
        </el-form-item>
        <el-form-item label="驾驶员身份证号码"
                      prop="driverIdentificationNumber">
          <el-input v-model="form.driverIdentificationNumber"
                    placeholder="请输入驾驶员身份证号码" />
        </el-form-item>
        <el-form-item label="驾驶员性别"
                      prop="driverSex">
          <el-select v-model="form.driverSex"
                     placeholder="请选择性别">
            <el-option v-for="dict in driverSexOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审核"
                      prop="checked">
          <el-select v-model="form.checked"
                     placeholder="请选择是否已经审核确认">
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
  listTemp_visitor_vehicle,
  getTemp_visitor_vehicle,
  delTemp_visitor_vehicle,
  addTemp_visitor_vehicle,
  updateTemp_visitor_vehicle,
  exportTemp_visitor_vehicle,
  updateTemp_visit_vehicle_check,
} from "@/api/passManage/temp_visitor_vehicle";

export default {
  name: "Temp_visitor_vehicle",
  data () {
    return {
      that: this,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      plates: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 临时访客车辆信息表格数据
      temp_visitor_vehicleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 车型字典
      modelOptions: [],
      // 性别字典
      driverSexOptions: [],
      // 审核字典
      checkedOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        plate: null,
        model: null,
        brand: null,
        specificBrand: null,
        numberOfSeats: null,
        driverName: null,
        driverIdentificationNumber: null,
        driverSex: null,
        checked: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        driverIdentificationNumber: [
          { required: true, message: "身份证号码不能为空", trigger: "blur" },
          {
            pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
            message: "你的身份证格式不正确",
          },
        ],
        plate: [
          { required: true, message: "车牌号不能为空", trigger: "blur" },
          // {pattern:/(^[\u4E00-\u9FA5]{1}[A-Z0-9]{6}$)|(^[A-Z]{2}[A-Z0-9]{2}[A-Z0-9\u4E00-\u9FA5]{1}[A-Z0-9]{4}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{5}[挂学警军港澳]{1}$)|(^[A-Z]{2}[0-9]{5}$)|(^(08|38){1}[A-Z0-9]{4}[A-Z0-9挂学警军港澳]{1}$)/,message: '常规格式：京A12345'},
          {
            pattern: /(^[\u4E00-\u9FA5]{1}[A-Z0-9]{6}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{7}$)|(^[A-Z]{2}[A-Z0-9]{2}[A-Z0-9\u4E00-\u9FA5]{1}[A-Z0-9]{4}$)|(^[A-Z]{2}[A-Z0-9]{2}[A-Z0-9\u4E00-\u9FA5]{1}[A-Z0-9]{5}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{5}[挂学警军港澳]{1}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{6}[挂学警军港澳]{1}$)|(^[A-Z]{2}[0-9]{5}$)|(^[A-Z]{2}[0-9]{6}$)|(^(08|38){1}[A-Z0-9]{4}[A-Z0-9挂学警军港澳]{1}$)|(^(08|38){1}[A-Z0-9]{5}[A-Z0-9挂学警军港澳]{1}$)/,
            message: "常规格式：京A12345,新能源格式: 京A123456",
          },
        ],
        checked: [
          {
            required: true,
            message: "是否已经审核确认 不能为空",
            trigger: "change",
          },
        ],
      },
      // 搜索表单控件高度
      formHeight: 0,
    };
  },
  filters: {
    checkedFilters: function (value, self) {
      return self.checkedFormat(null, null, value);
    },
  },
  created () {
    this.getList();
    this.getDicts("base_vehicle_model").then((response) => {
      this.modelOptions = response.data;
    });
    this.getDicts("sys_user_sex").then((response) => {
      this.driverSexOptions = response.data;
    });
    this.getDicts("base_checked_stat").then((response) => {
      this.checkedOptions = response.data;
    });
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
    /** 查询临时访客车辆信息列表 */
    getList () {
      this.loading = true;
      listTemp_visitor_vehicle(this.queryParams).then((response) => {
        this.temp_visitor_vehicleList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 车型字典翻译
    modelFormat (row, column) {
      return this.selectDictLabel(this.modelOptions, row.model);
    },
    // 性别字典翻译
    driverSexFormat (row, column) {
      return this.selectDictLabel(this.driverSexOptions, row.driverSex);
    },
    // 是否已经审核确认,(0否1是,默认0)字典翻译
    checkedFormat (row, column, cellValue) {
      return this.selectDictLabel(this.checkedOptions, cellValue);
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
        plate: null,
        model: null,
        brand: null,
        specificBrand: null,
        numberOfSeats: null,
        driverName: null,
        driverIdentificationNumber: null,
        driverSex: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        checked: "0",
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
      this.plates = selection.map((item) => item.plate);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset();
      this.open = true;
      this.title = "添加临时访客车辆信息";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const id = row.id || this.ids;
      getTemp_visitor_vehicle(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改临时访客车辆信息";
      });
    },
    /** 另存一条 提交按钮 */
    otherSubmitForm () {
      this.form.id = null;
      this.submitForm();
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateTemp_visitor_vehicle(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addTemp_visitor_vehicle(this.form).then((response) => {
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
      const vplate = row.plate || this.plates;
      this.$confirm(
        '是否确认删除临时访客车辆车牌号码为"' + vplate + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delTemp_visitor_vehicle(ids);
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
      this.$confirm("是否确认导出所有临时访客车辆信息数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportTemp_visitor_vehicle(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () { });
    },
    /** 修改审核状态操作 */
    handleUpdate_check (row) {
      const plate = row.plate;
      let self = this;
      this.$confirm('是否确认同意车牌号为"' + plate + '"的拜访?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(function () {
        let param = {
          id: row.id,
          checked: "1",
          checkedTime: self.$cf.getNowTime(),
        };
        updateTemp_visit_vehicle_check(param)
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
  },
};
</script>
