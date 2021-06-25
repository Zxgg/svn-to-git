<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="社区id" prop="communityId">
        <el-input
          v-model="queryParams.communityId"
          placeholder="请输入社区id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="小区id" prop="communityChildId">
        <el-input
          v-model="queryParams.communityChildId"
          placeholder="请输入小区id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请人personID" prop="personId">
        <el-input
          v-model="queryParams.personId"
          placeholder="请输入申请人personID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请办理的证件类型" prop="certificateType">
        <el-select v-model="queryParams.certificateType" placeholder="请选择申请办理的证件类型" clearable size="small">
          <el-option
            v-for="dict in certificateTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="所需申请人提供的材料信息" prop="requiredMaterial">
        <el-input
          v-model="queryParams.requiredMaterial"
          placeholder="请输入所需申请人提供的材料信息"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="办理状态" prop="transactionStatus">
        <el-select v-model="queryParams.transactionStatus" placeholder="请选择办理状态" clearable size="small">
          <el-option
            v-for="dict in transactionStatusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="申请时间" prop="applyDate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.applyDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择申请时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="领取时间" prop="receiveDate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.receiveDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择领取时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="审核人" prop="auditBy">
        <el-input
          v-model="queryParams.auditBy"
          placeholder="请输入审核人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核时间" prop="auditDate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.auditDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择审核时间">
        </el-date-picker>
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
          v-hasPermi="['property_company:certify_application_form:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['property_company:certify_application_form:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['property_company:certify_application_form:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['property_company:certify_application_form:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="certify_application_formList" :height="dataHeight" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="唯一标识" align="center" prop="id" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="社区id" align="center" prop="communityId" />
      <el-table-column label="小区id" align="center" prop="communityChildId" />
      <el-table-column label="申请人personID" align="center" prop="personId" />
      <el-table-column label="申请办理的证件类型" align="center" prop="certificateType" :formatter="certificateTypeFormat" />
      <el-table-column label="所需申请人提供的材料信息" align="center" prop="requiredMaterial" />
      <el-table-column label="申请人上传的图片列表" align="center" prop="pics" />
      <el-table-column label="办理状态" align="center" prop="transactionStatus" :formatter="transactionStatusFormat" />
      <el-table-column label="申请时间" align="center" prop="applyDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.applyDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="领取时间" align="center" prop="receiveDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.receiveDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核人" align="center" prop="auditBy" />
      <el-table-column label="审核时间" align="center" prop="auditDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.auditDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['property_company:certify_application_form:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['property_company:certify_application_form:remove']"
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

    <!-- 添加或修改证明办理申请单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="180px">
        <el-form-item label="删除标志" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="社区id" prop="communityId">
          <el-input v-model="form.communityId" placeholder="请输入社区id" />
        </el-form-item>
        <el-form-item label="小区id" prop="communityChildId">
          <el-input v-model="form.communityChildId" placeholder="请输入小区id" />
        </el-form-item>
        <el-form-item label="申请人personID" prop="personId">
          <el-input v-model="form.personId" placeholder="请输入申请人personID" />
        </el-form-item>
        <el-form-item label="申请办理的证件类型" prop="certificateType">
          <el-select v-model="form.certificateType" placeholder="请选择申请办理的证件类型">
            <el-option
              v-for="dict in certificateTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="所需申请人提供的材料信息" prop="requiredMaterial">
          <el-input v-model="form.requiredMaterial" placeholder="请输入所需申请人提供的材料信息" />
        </el-form-item>
        <el-form-item label="申请人上传的图片列表" prop="pics">
          <el-input v-model="form.pics" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="办理状态" prop="transactionStatus">
          <el-select v-model="form.transactionStatus" placeholder="请选择办理状态">
            <el-option
              v-for="dict in transactionStatusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="申请时间" prop="applyDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.applyDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择申请时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="领取时间" prop="receiveDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.receiveDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择领取时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="审核人" prop="auditBy">
          <el-input v-model="form.auditBy" placeholder="请输入审核人" />
        </el-form-item>
        <el-form-item label="审核时间" prop="auditDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.auditDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择审核时间">
          </el-date-picker>
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
import { listCertify_application_form, getCertify_application_form, delCertify_application_form, addCertify_application_form, updateCertify_application_form, exportCertify_application_form } from "@/api/property_company/certify_application_form";

export default {
  name: "Certify_application_form",
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
      // 证明办理申请单表格数据
      certify_application_formList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 申请办理的证件类型字典
      certificateTypeOptions: [],
      // 办理状态字典
      transactionStatusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        communityId: null,
        communityChildId: null,
        personId: null,
        certificateType: null,
        requiredMaterial: null,
        pics: null,
        transactionStatus: null,
        applyDate: null,
        receiveDate: null,
        auditBy: null,
        auditDate: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      } ,
      // 搜索表单控件高度
      formHeight: 0
    };
  },
  created() {
    // this.getList();
    this.getDicts("base_idCard").then(response => {
      this.certificateTypeOptions = response.data;
    });
    this.getDicts("base_entrance_guard_apply_stat").then(response => {
      this.transactionStatusOptions = response.data;
    });
  },
  methods: {
    /** 查询证明办理申请单列表 */
    getList() {
      this.loading = true;
      listCertify_application_form(this.queryParams).then(response => {
        this.certify_application_formList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 申请办理的证件类型字典翻译
    certificateTypeFormat(row, column, cellValue ) {
        return this.selectDictLabel(this.certificateTypeOptions, cellValue);
    },
    // 办理状态字典翻译
    transactionStatusFormat(row, column, cellValue ) {
        return this.selectDictLabel(this.transactionStatusOptions, cellValue);
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
        personId: null,
        certificateType: null,
        requiredMaterial: null,
        pics: null,
        transactionStatus: null,
        applyDate: null,
        receiveDate: null,
        auditBy: null,
        auditDate: null
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
      this.title = "添加证明办理申请单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
        let self=this
        self.reset();
      const id = row.id || self.ids
      getCertify_application_form(id).then(response => {
          self.form = response.data;
        self.open = true;
        self.title = "修改证明办理申请单";
      });
    },
    /** 提交按钮 */
    submitForm() {
        let self=this
        self.$refs["form"].validate(valid => {
        if (valid) {
          if (self.form.id != null) {
            updateCertify_application_form(self.form).then(response => {
              if (response.code === 200) {
                  self.msgSuccess("修改成功");
                  self.open = false;
                  self.getList();
              }
            });
          } else {
            addCertify_application_form(self.form).then(response => {
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
      this.$confirm('是否确认删除证明办理申请单编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCertify_application_form(ids);
        }).then(() => {
          self.getList();
        self.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
        let self=this
      const queryParams = self.queryParams;
        self.$confirm('是否确认导出所有证明办理申请单数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCertify_application_form(queryParams);
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
