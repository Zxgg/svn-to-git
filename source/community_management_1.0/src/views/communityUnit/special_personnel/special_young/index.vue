<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="创建人" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入创建人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新人" prop="updateBy">
        <el-input
          v-model="queryParams.updateBy"
          placeholder="请输入更新人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="人员ID" prop="personnelId">
        <el-input
          v-model="queryParams.personnelId"
          placeholder="请输入人员ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="来源类型" prop="sourceType">
        <el-select v-model="queryParams.sourceType" placeholder="请选择来源类型" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="是否有效" prop="enable">
        <el-input
          v-model="queryParams.enable"
          placeholder="请输入是否有效"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="人员类型" prop="youngType">
        <el-select v-model="queryParams.youngType" placeholder="请选择人员类型" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="家庭情况" prop="familyType">
        <el-select v-model="queryParams.familyType" placeholder="请选择家庭情况" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="监护人身份证号" prop="guardianIdcard">
        <el-input
          v-model="queryParams.guardianIdcard"
          placeholder="请输入监护人身份证号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="监护人姓名" prop="guardianName">
        <el-input
          v-model="queryParams.guardianName"
          placeholder="请输入监护人姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="与监护人关系" prop="isGuardianRel">
        <el-select v-model="queryParams.isGuardianRel" placeholder="请选择与监护人关系" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="监护人联系方式" prop="guardianTel">
        <el-input
          v-model="queryParams.guardianTel"
          placeholder="请输入监护人联系方式"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="监护人居住详址" prop="guardianAddressDetail">
        <el-input
          v-model="queryParams.guardianAddressDetail"
          placeholder="请输入监护人居住详址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否违法犯罪" prop="isCrime">
        <el-select v-model="queryParams.isCrime" placeholder="请选择是否违法犯罪" clearable size="small">
          <el-option
            v-for="dict in isCrimeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="帮扶人姓名" prop="helperName">
        <el-input
          v-model="queryParams.helperName"
          placeholder="请输入帮扶人姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="帮扶人联系方式" prop="helperTel">
        <el-input
          v-model="queryParams.helperTel"
          placeholder="请输入帮扶人联系方式"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="帮扶人手段" prop="helperMeans">
        <el-select v-model="queryParams.helperMeans" placeholder="请选择帮扶人手段" clearable size="small">
          <el-option
            v-for="dict in helperMeansOptions"
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
          v-hasPermi="['special_personnel:special_young:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['special_personnel:special_young:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['special_personnel:special_young:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['special_personnel:special_young:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="special_youngList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="唯一标识" align="center" prop="id" />
      <el-table-column label="创建单位" align="center" prop="deptId" />
      <el-table-column label="更新单位" align="center" prop="updateDeptId" />
      <el-table-column label="人员ID" align="center" prop="personnelId" />
      <el-table-column label="来源类型" align="center" prop="sourceType" />
      <el-table-column label="是否有效" align="center" prop="enable" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="人员类型" align="center" prop="youngType" />
      <el-table-column label="家庭情况" align="center" prop="familyType" />
      <el-table-column label="监护人身份证号" align="center" prop="guardianIdcard" />
      <el-table-column label="监护人姓名" align="center" prop="guardianName" />
      <el-table-column label="与监护人关系" align="center" prop="isGuardianRel" />
      <el-table-column label="监护人联系方式" align="center" prop="guardianTel" />
      <el-table-column label="监护人居住详址" align="center" prop="guardianAddressDetail" />
      <el-table-column label="是否违法犯罪" align="center" prop="isCrime" :formatter="isCrimeFormat" />
      <el-table-column label="违法犯罪情况" align="center" prop="crimeCase" />
      <el-table-column label="帮扶人姓名" align="center" prop="helperName" />
      <el-table-column label="帮扶人联系方式" align="center" prop="helperTel" />
      <el-table-column label="帮扶人手段" align="center" prop="helperMeans" :formatter="helperMeansFormat" />
      <el-table-column label="帮扶人情况" align="center" prop="helperCase" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['special_personnel:special_young:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['special_personnel:special_young:remove']"
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

    <!-- 添加或修改重点青少年 对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="创建单位" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入创建单位" />
        </el-form-item>
        <el-form-item label="更新单位" prop="updateDeptId">
          <el-input v-model="form.updateDeptId" placeholder="请输入更新单位" />
        </el-form-item>
        <el-form-item label="删除标志" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
        </el-form-item>
        <el-form-item label="人员ID" prop="personnelId">
          <el-input v-model="form.personnelId" placeholder="请输入人员ID" />
        </el-form-item>
        <el-form-item label="来源类型" prop="sourceType">
          <el-select v-model="form.sourceType" placeholder="请选择来源类型">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="是否有效" prop="enable">
          <el-input v-model="form.enable" placeholder="请输入是否有效" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="人员类型" prop="youngType">
          <el-select v-model="form.youngType" placeholder="请选择人员类型">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="家庭情况" prop="familyType">
          <el-select v-model="form.familyType" placeholder="请选择家庭情况">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="监护人身份证号" prop="guardianIdcard">
          <el-input v-model="form.guardianIdcard" placeholder="请输入监护人身份证号" />
        </el-form-item>
        <el-form-item label="监护人姓名" prop="guardianName">
          <el-input v-model="form.guardianName" placeholder="请输入监护人姓名" />
        </el-form-item>
        <el-form-item label="与监护人关系" prop="isGuardianRel">
          <el-select v-model="form.isGuardianRel" placeholder="请选择与监护人关系">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="监护人联系方式" prop="guardianTel">
          <el-input v-model="form.guardianTel" placeholder="请输入监护人联系方式" />
        </el-form-item>
        <el-form-item label="监护人居住详址" prop="guardianAddressDetail">
          <el-input v-model="form.guardianAddressDetail" placeholder="请输入监护人居住详址" />
        </el-form-item>
        <el-form-item label="是否违法犯罪" prop="isCrime">
          <el-select v-model="form.isCrime" placeholder="请选择是否违法犯罪">
            <el-option
              v-for="dict in isCrimeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="违法犯罪情况" prop="crimeCase">
          <el-input v-model="form.crimeCase" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="帮扶人姓名" prop="helperName">
          <el-input v-model="form.helperName" placeholder="请输入帮扶人姓名" />
        </el-form-item>
        <el-form-item label="帮扶人联系方式" prop="helperTel">
          <el-input v-model="form.helperTel" placeholder="请输入帮扶人联系方式" />
        </el-form-item>
        <el-form-item label="帮扶人手段" prop="helperMeans">
          <el-select v-model="form.helperMeans" placeholder="请选择帮扶人手段">
            <el-option
              v-for="dict in helperMeansOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="帮扶人情况" prop="helperCase">
          <el-input v-model="form.helperCase" type="textarea" placeholder="请输入内容" />
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
import { listSpecial_young, getSpecial_young, delSpecial_young, addSpecial_young, updateSpecial_young, exportSpecial_young } from "@/api/special_personnel/special_young";

export default {
  name: "Special_young",
  data() {
    return {
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
      // 重点青少年 表格数据
      special_youngList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否违法犯罪字典
      isCrimeOptions: [],
      // 帮扶人手段字典
      helperMeansOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        createBy: null,
        updateBy: null,
        personnelId: null,
        sourceType: null,
        enable: null,
        remark: null,
        youngType: null,
        familyType: null,
        guardianIdcard: null,
        guardianName: null,
        isGuardianRel: null,
        guardianTel: null,
        guardianAddressDetail: null,
        isCrime: null,
        crimeCase: null,
        helperName: null,
        helperTel: null,
        helperMeans: null,
        helperCase: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        delFlag: [
          { required: true, message: "删除标志不能为空", trigger: "blur" }
        ],
        personnelId: [
          { required: true, message: "人员ID不能为空", trigger: "blur" }
        ],
        enable: [
          { required: true, message: "是否有效不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("base_yes_no").then(response => {
      this.isCrimeOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.helperMeansOptions = response.data;
    });
  },
  methods: {
    /** 查询重点青少年 列表 */
    getList() {
      this.loading = true;
      listSpecial_young(this.queryParams).then(response => {
        this.special_youngList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 是否违法犯罪字典翻译
    isCrimeFormat(row, column) {
      return this.selectDictLabel(this.isCrimeOptions, row.isCrime);
    },
    // 帮扶人手段字典翻译
    helperMeansFormat(row, column) {
      return this.selectDictLabel(this.helperMeansOptions, row.helperMeans);
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
        deptId: null,
        createTime: null,
        updateBy: null,
        updateDeptId: null,
        updateTime: null,
        delFlag: null,
        personnelId: null,
        sourceType: null,
        enable: null,
        remark: null,
        youngType: null,
        familyType: null,
        guardianIdcard: null,
        guardianName: null,
        isGuardianRel: null,
        guardianTel: null,
        guardianAddressDetail: null,
        isCrime: null,
        crimeCase: null,
        helperName: null,
        helperTel: null,
        helperMeans: null,
        helperCase: null
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
      this.title = "添加重点青少年 ";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSpecial_young(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改重点青少年 ";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSpecial_young(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addSpecial_young(this.form).then(response => {
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
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除重点青少年 编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delSpecial_young(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有重点青少年 数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportSpecial_young(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
