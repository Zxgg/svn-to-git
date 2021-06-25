<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="评价月份  yyyy-MM,一般来说只能评价当月和上个月" prop="monthly">
        <el-input
          v-model="queryParams.monthly"
          placeholder="请输入评价月份  yyyy-MM,一般来说只能评价当月和上个月"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="业主的personID" prop="personId">
        <el-input
          v-model="queryParams.personId"
          placeholder="请输入业主的personID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
      <el-form-item label="评价等级，1-10分，10分为最满意，6分为及格" prop="evaluation">
        <el-select v-model="queryParams.evaluation" placeholder="请选择评价等级，1-10分，10分为最满意，6分为及格" clearable size="small">
          <el-option
            v-for="dict in evaluationOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否匿名评价" prop="anonymous">
        <el-select v-model="queryParams.anonymous" placeholder="请选择是否匿名评价" clearable size="small">
          <el-option
            v-for="dict in anonymousOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="回复人" prop="replyer">
        <el-input
          v-model="queryParams.replyer"
          placeholder="请输入回复人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="回复时间" prop="replyTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.replyTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择回复时间">
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
          v-hasPermi="['property_company:property_company_evaluation:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['property_company:property_company_evaluation:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['property_company:property_company_evaluation:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['property_company:property_company_evaluation:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="property_company_evaluationList" :height="dataHeight" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="唯一标识" align="center" prop="id" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="评价月份  yyyy-MM,一般来说只能评价当月和上个月" align="center" prop="monthly" />
      <el-table-column label="业主的personID" align="center" prop="personId" />
      <el-table-column label="社区ID" align="center" prop="communityId" />
      <el-table-column label="小区ID" align="center" prop="communityChildId" />
      <el-table-column label="评价等级，1-10分，10分为最满意，6分为及格" align="center" prop="evaluation" :formatter="evaluationFormat" />
      <el-table-column label="是否匿名评价" align="center" prop="anonymous" :formatter="anonymousFormat" />
      <el-table-column label="回复" align="center" prop="reply" />
      <el-table-column label="回复人" align="center" prop="replyer" />
      <el-table-column label="回复时间" align="center" prop="replyTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.replyTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['property_company:property_company_evaluation:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['property_company:property_company_evaluation:remove']"
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

    <!-- 添加或修改物业评价 对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="180px">
        <el-form-item label="删除标志" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="评价月份  yyyy-MM,一般来说只能评价当月和上个月" prop="monthly">
          <el-input v-model="form.monthly" placeholder="请输入评价月份  yyyy-MM,一般来说只能评价当月和上个月" />
        </el-form-item>
        <el-form-item label="业主的personID" prop="personId">
          <el-input v-model="form.personId" placeholder="请输入业主的personID" />
        </el-form-item>
        <el-form-item label="社区ID" prop="communityId">
          <el-input v-model="form.communityId" placeholder="请输入社区ID" />
        </el-form-item>
        <el-form-item label="小区ID" prop="communityChildId">
          <el-input v-model="form.communityChildId" placeholder="请输入小区ID" />
        </el-form-item>
        <el-form-item label="评价等级，1-10分，10分为最满意，6分为及格" prop="evaluation">
          <el-select v-model="form.evaluation" placeholder="请选择评价等级，1-10分，10分为最满意，6分为及格">
            <el-option
              v-for="dict in evaluationOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否匿名评价" prop="anonymous">
          <el-select v-model="form.anonymous" placeholder="请选择是否匿名评价">
            <el-option
              v-for="dict in anonymousOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="回复" prop="reply">
          <el-input v-model="form.reply" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="回复人" prop="replyer">
          <el-input v-model="form.replyer" placeholder="请输入回复人" />
        </el-form-item>
        <el-form-item label="回复时间" prop="replyTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.replyTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择回复时间">
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
import { listProperty_company_evaluation, getProperty_company_evaluation, delProperty_company_evaluation, addProperty_company_evaluation, updateProperty_company_evaluation, exportProperty_company_evaluation } from "@/api/property_company/property_company_evaluation";

export default {
  name: "Property_company_evaluation",
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
      // 物业评价 表格数据
      property_company_evaluationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 评价等级，1-10分，10分为最满意，6分为及格字典
      evaluationOptions: [],
      // 是否匿名评价字典
      anonymousOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        remark: null,
        monthly: null,
        personId: null,
        communityId: null,
        communityChildId: null,
        evaluation: null,
        anonymous: null,
        reply: null,
        replyer: null,
        replyTime: null
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
        anonymous: [
          { required: true, message: "是否匿名评价不能为空", trigger: "change" }
        ],
      } ,
      // 搜索表单控件高度
      formHeight: 0
    };
  },
  created() {
    // this.getList();
    this.getDicts("base_evaluation_level").then(response => {
      this.evaluationOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.anonymousOptions = response.data;
    });
  },
  methods: {
    /** 查询物业评价 列表 */
    getList() {
      this.loading = true;
      listProperty_company_evaluation(this.queryParams).then(response => {
        this.property_company_evaluationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 评价等级，1-10分，10分为最满意，6分为及格字典翻译
    evaluationFormat(row, column, cellValue ) {
        return this.selectDictLabel(this.evaluationOptions, cellValue);
    },
    // 是否匿名评价字典翻译
    anonymousFormat(row, column, cellValue ) {
        return this.selectDictLabel(this.anonymousOptions, cellValue);
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
        monthly: null,
        personId: null,
        communityId: null,
        communityChildId: null,
        evaluation: null,
        anonymous: null,
        reply: null,
        replyer: null,
        replyTime: null
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
      this.title = "添加物业评价 ";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
        let self=this
        self.reset();
      const id = row.id || self.ids
      getProperty_company_evaluation(id).then(response => {
          self.form = response.data;
        self.open = true;
        self.title = "修改物业评价 ";
      });
    },
    /** 提交按钮 */
    submitForm() {
        let self=this
        self.$refs["form"].validate(valid => {
        if (valid) {
          if (self.form.id != null) {
            updateProperty_company_evaluation(self.form).then(response => {
              if (response.code === 200) {
                  self.msgSuccess("修改成功");
                  self.open = false;
                  self.getList();
              }
            });
          } else {
            addProperty_company_evaluation(self.form).then(response => {
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
      this.$confirm('是否确认删除物业评价 编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delProperty_company_evaluation(ids);
        }).then(() => {
          self.getList();
        self.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
        let self=this
      const queryParams = self.queryParams;
        self.$confirm('是否确认导出所有物业评价 数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportProperty_company_evaluation(queryParams);
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
