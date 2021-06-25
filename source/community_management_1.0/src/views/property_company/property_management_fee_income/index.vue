<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="社区ID" prop="communityId">
        <el-input
          v-model="queryParams.communityId"
          placeholder="请输入社区ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="小区ID" prop="communityChildId">
        <el-input
          v-model="queryParams.communityChildId"
          placeholder="请输入小区ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="房屋ID" prop="communityHousesId">
        <el-input
          v-model="queryParams.communityHousesId"
          placeholder="请输入房屋ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="应缴费用" prop="shouldPay">
        <el-input
          v-model="queryParams.shouldPay"
          placeholder="请输入应缴费用"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="实缴费用" prop="realityPay">
        <el-input
          v-model="queryParams.realityPay"
          placeholder="请输入实缴费用"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="缴费区间  yyyy-MM,yyyy-MM" prop="payCostRange">
        <el-input
          v-model="queryParams.payCostRange"
          placeholder="请输入缴费区间  yyyy-MM,yyyy-MM"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="房屋面积" prop="area">
        <el-input
          v-model="queryParams.area"
          placeholder="请输入房屋面积"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物业费单价/每平方米" prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="请输入物业费单价/每平方米"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="缴费人姓名" prop="payerName">
        <el-input
          v-model="queryParams.payerName"
          placeholder="请输入缴费人姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="缴费人手机号" prop="payerPhoneNumber">
        <el-input
          v-model="queryParams.payerPhoneNumber"
          placeholder="请输入缴费人手机号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="房屋业主姓名" prop="ownerName">
        <el-input
          v-model="queryParams.ownerName"
          placeholder="请输入房屋业主姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="业主身份证号码" prop="ownerIdentificationNumber">
        <el-input
          v-model="queryParams.ownerIdentificationNumber"
          placeholder="请输入业主身份证号码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收费人，" prop="cashierName">
        <el-input
          v-model="queryParams.cashierName"
          placeholder="请输入收费人，"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="计算公式" prop="strategy">
        <el-select v-model="queryParams.strategy" placeholder="请选择计算公式" clearable size="small">
          <el-option
            v-for="dict in strategyOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="缴费方式" prop="payWay">
        <el-select v-model="queryParams.payWay" placeholder="请选择缴费方式" clearable size="small">
          <el-option
            v-for="dict in payWayOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['property_company:property_management_fee_income:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['property_company:property_management_fee_income:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['property_company:property_management_fee_income:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['property_company:property_management_fee_income:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="property_management_fee_incomeList" :height="dataHeight" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="唯一标识" align="center" prop="id" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="社区ID" align="center" prop="communityId" />
      <el-table-column label="小区ID" align="center" prop="communityChildId" />
      <el-table-column label="房屋ID" align="center" prop="communityHousesId" />
      <el-table-column label="应缴费用" align="center" prop="shouldPay" />
      <el-table-column label="实缴费用" align="center" prop="realityPay" />
      <el-table-column label="缴费区间  yyyy-MM,yyyy-MM" align="center" prop="payCostRange" />
      <el-table-column label="房屋面积" align="center" prop="area" />
      <el-table-column label="物业费单价/每平方米" align="center" prop="price" />
      <el-table-column label="缴费人姓名" align="center" prop="payerName" />
      <el-table-column label="缴费人手机号" align="center" prop="payerPhoneNumber" />
      <el-table-column label="房屋业主姓名" align="center" prop="ownerName" />
      <el-table-column label="业主身份证号码" align="center" prop="ownerIdentificationNumber" />
      <el-table-column label="收费人，" align="center" prop="cashierName" />
      <el-table-column label="计算公式" align="center" prop="strategy" :formatter="strategyFormat" />
      <el-table-column label="缴费方式" align="center" prop="payWay" :formatter="payWayFormat" />
      <el-table-column label="照片列表" align="center" prop="pics" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['property_company:property_management_fee_income:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['property_company:property_management_fee_income:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改物业费用收入记录 对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="180px">
        <el-form-item label="删除标志" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="社区ID" prop="communityId">
          <el-input v-model="form.communityId" placeholder="请输入社区ID" />
        </el-form-item>
        <el-form-item label="小区ID" prop="communityChildId">
          <el-input v-model="form.communityChildId" placeholder="请输入小区ID" />
        </el-form-item>
        <el-form-item label="房屋ID" prop="communityHousesId">
          <el-input v-model="form.communityHousesId" placeholder="请输入房屋ID" />
        </el-form-item>
        <el-form-item label="应缴费用" prop="shouldPay">
          <el-input v-model="form.shouldPay" placeholder="请输入应缴费用" />
        </el-form-item>
        <el-form-item label="实缴费用" prop="realityPay">
          <el-input v-model="form.realityPay" placeholder="请输入实缴费用" />
        </el-form-item>
        <el-form-item label="缴费区间  yyyy-MM,yyyy-MM" prop="payCostRange">
          <el-input v-model="form.payCostRange" placeholder="请输入缴费区间  yyyy-MM,yyyy-MM" />
        </el-form-item>
        <el-form-item label="房屋面积" prop="area">
          <el-input v-model="form.area" placeholder="请输入房屋面积" />
        </el-form-item>
        <el-form-item label="物业费单价/每平方米" prop="price">
          <el-input v-model="form.price" placeholder="请输入物业费单价/每平方米" />
        </el-form-item>
        <el-form-item label="缴费人姓名" prop="payerName">
          <el-input v-model="form.payerName" placeholder="请输入缴费人姓名" />
        </el-form-item>
        <el-form-item label="缴费人手机号" prop="payerPhoneNumber">
          <el-input v-model="form.payerPhoneNumber" placeholder="请输入缴费人手机号" />
        </el-form-item>
        <el-form-item label="房屋业主姓名" prop="ownerName">
          <el-input v-model="form.ownerName" placeholder="请输入房屋业主姓名" />
        </el-form-item>
        <el-form-item label="业主身份证号码" prop="ownerIdentificationNumber">
          <el-input v-model="form.ownerIdentificationNumber" placeholder="请输入业主身份证号码" />
        </el-form-item>
        <el-form-item label="收费人，" prop="cashierName">
          <el-input v-model="form.cashierName" placeholder="请输入收费人，" />
        </el-form-item>
        <el-form-item label="计算公式" prop="strategy">
          <el-select v-model="form.strategy" placeholder="请选择计算公式">
            <el-option
              v-for="dict in strategyOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="缴费方式" prop="payWay">
          <el-select v-model="form.payWay" placeholder="请选择缴费方式">
            <el-option
              v-for="dict in payWayOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="照片列表" prop="pics">
          <el-input v-model="form.pics" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listProperty_management_fee_income, getProperty_management_fee_income, delProperty_management_fee_income, addProperty_management_fee_income, updateProperty_management_fee_income, exportProperty_management_fee_income } from "@/api/property_company/property_management_fee_income";

export default {
  name: "Property_management_fee_income",
  data() {
    return {
      that:this,
      // 遮罩层
      loading: true,
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
      // 物业费用收入记录 表格数据
      property_management_fee_incomeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 计算公式字典
      strategyOptions: [],
      // 缴费方式字典
      payWayOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        remark: null,
        communityId: null,
        communityChildId: null,
        communityHousesId: null,
        shouldPay: null,
        realityPay: null,
        payCostRange: null,
        area: null,
        price: null,
        payerName: null,
        payerPhoneNumber: null,
        ownerName: null,
        ownerIdentificationNumber: null,
        cashierName: null,
        strategy: null,
        payWay: null,
        pics: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        delFlag: [
          { required: true, message: "删除标志不能为空", trigger: "blur" }
        ],
        communityId: [
          { required: true, message: "社区ID不能为空", trigger: "blur" }
        ],
        communityChildId: [
          { required: true, message: "小区ID不能为空", trigger: "blur" }
        ],
        shouldPay: [
          { required: true, message: "应缴费用不能为空", trigger: "blur" }
        ],
        realityPay: [
          { required: true, message: "实缴费用不能为空", trigger: "blur" }
        ],
        cashierName: [
          { required: true, message: "收费人，不能为空", trigger: "blur" }
        ],
        payWay: [
          { required: true, message: "缴费方式不能为空", trigger: "change" }
        ],
      } ,
      // 搜索表单控件高度
      formHeight: 0
    };
  },
  created() {
    // this.getList();
    this.getDicts("base_property_management_fee_income_strategy").then(response => {
      this.strategyOptions = response.data;
    });
    this.getDicts("base_pay_way").then(response => {
      this.payWayOptions = response.data;
    });
  },
  methods: {
    /** 查询物业费用收入记录 列表 */
    getList() {
      this.loading = true;
      listProperty_management_fee_income(this.queryParams).then(response => {
        this.property_management_fee_incomeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 计算公式字典翻译
    strategyFormat(row, column, cellValue ) {
        return this.selectDictLabel(this.strategyOptions, cellValue);
    },
    // 缴费方式字典翻译
    payWayFormat(row, column, cellValue ) {
        return this.selectDictLabel(this.payWayOptions, cellValue);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        remark: null,
        communityId: null,
        communityChildId: null,
        communityHousesId: null,
        shouldPay: null,
        realityPay: null,
        payCostRange: null,
        area: null,
        price: null,
        payerName: null,
        payerPhoneNumber: null,
        ownerName: null,
        ownerIdentificationNumber: null,
        cashierName: null,
        strategy: null,
        payWay: null,
        pics: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加物业费用收入记录 ";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
        let self=this
        self.reset();
      const id = row.id || self.ids
      getProperty_management_fee_income(id).then(response => {
          self.form = response.data;
        self.open = true;
        self.title = "修改物业费用收入记录 ";
      });
    },
    /** 提交按钮 */
    submitForm() {
        let self=this
        self.$refs["form"].validate(valid => {
        if (valid) {
          if (self.form.id != null) {
            updateProperty_management_fee_income(self.form).then(response => {
              if (response.code === 200) {
                  self.msgSuccess("修改成功");
                  self.open = false;
                  self.getList();
              }
            });
          } else {
            addProperty_management_fee_income(self.form).then(response => {
              if (response.code === 200) {
                  self.msgSuccess("新增成功");
                  self.open = false;
                  self.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
        let self=this
      const ids = row.id || self.ids;
      this.$confirm('是否确认删除物业费用收入记录 编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delProperty_management_fee_income(ids);
        }).then(() => {
          self.getList();
        self.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
        let self=this
      const queryParams = self.queryParams;
        self.$confirm('是否确认导出所有物业费用收入记录 数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportProperty_management_fee_income(queryParams);
        }).then(response => {
            self.download(response.msg);
        }).catch(function() {});
    }
  },
  computed: {
      dataHeight () {
        let searchFormHeight = 0
            if (this.showSearch) searchFormHeight = this.formHeight
            return this.$store.getters.dataEleHeight - searchFormHeight - 160
      }
  },
  mounted () {
    this.$nextTick(() => {
         this.formHeight = this.$refs.queryForm.$el.offsetHeight
    })
    this.getList();
  },
};
</script>
