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
      <el-form-item label="申请人姓名" prop="personName">
        <el-input
          v-model="queryParams.personName"
          placeholder="请输入申请人姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请人房屋ID" prop="communityHousesId">
        <el-input
          v-model="queryParams.communityHousesId"
          placeholder="请输入申请人房屋ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="stat">
        <el-select v-model="queryParams.stat" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in statOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="申请理由" prop="reason">
        <el-select v-model="queryParams.reason" placeholder="请选择申请理由" clearable size="small">
          <el-option
            v-for="dict in reasonOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="领取时间" prop="receiveTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.receiveTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择领取时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="审核人" prop="checkerName">
        <el-input
          v-model="queryParams.checkerName"
          placeholder="请输入审核人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核时间" prop="checkTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.checkTime"
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
          v-hasPermi="['property_company:entrance_guard_apply:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['property_company:entrance_guard_apply:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['property_company:entrance_guard_apply:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['property_company:entrance_guard_apply:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="entrance_guard_applyList" :height="dataHeight" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="唯一标识" align="center" prop="id" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="社区id" align="center" prop="communityId" />
      <el-table-column label="小区id" align="center" prop="communityChildId" />
      <el-table-column label="申请人personID" align="center" prop="personId" />
      <el-table-column label="申请人姓名" align="center" prop="personName" />
      <el-table-column label="申请人房屋ID" align="center" prop="communityHousesId" />
      <el-table-column label="状态" align="center" prop="stat" :formatter="statFormat" />
      <el-table-column label="申请理由" align="center" prop="reason" :formatter="reasonFormat" />
      <el-table-column label="照片列表" align="center" prop="pics" />
      <el-table-column label="领取时间" align="center" prop="receiveTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.receiveTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核人" align="center" prop="checkerName" />
      <el-table-column label="审核时间" align="center" prop="checkTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['property_company:entrance_guard_apply:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['property_company:entrance_guard_apply:remove']"
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

    <!-- 添加或修改 门禁申请对话框 -->
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
        <el-form-item label="申请人姓名" prop="personName">
          <el-input v-model="form.personName" placeholder="请输入申请人姓名" />
        </el-form-item>
        <el-form-item label="申请人房屋ID" prop="communityHousesId">
          <el-input v-model="form.communityHousesId" placeholder="请输入申请人房屋ID" />
        </el-form-item>
        <el-form-item label="状态" prop="stat">
          <el-select v-model="form.stat" placeholder="请选择状态">
            <el-option
              v-for="dict in statOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="申请理由" prop="reason">
          <el-select v-model="form.reason" placeholder="请选择申请理由">
            <el-option
              v-for="dict in reasonOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="照片列表" prop="pics">
          <el-input v-model="form.pics" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="领取时间" prop="receiveTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.receiveTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择领取时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="审核人" prop="checkerName">
          <el-input v-model="form.checkerName" placeholder="请输入审核人" />
        </el-form-item>
        <el-form-item label="审核时间" prop="checkTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.checkTime"
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
import { listEntrance_guard_apply, getEntrance_guard_apply, delEntrance_guard_apply, addEntrance_guard_apply, updateEntrance_guard_apply, exportEntrance_guard_apply } from "@/api/property_company/entrance_guard_apply";

export default {
  name: "Entrance_guard_apply",
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
      //  门禁申请表格数据
      entrance_guard_applyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 状态字典
      statOptions: [],
      // 申请理由字典
      reasonOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        communityId: null,
        communityChildId: null,
        personId: null,
        personName: null,
        communityHousesId: null,
        stat: null,
        reason: null,
        pics: null,
        receiveTime: null,
        checkerName: null,
        checkTime: null
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
    this.getDicts("base_entrance_guard_apply_stat").then(response => {
      this.statOptions = response.data;
    });
    this.getDicts("base_entrance_guard_apply_reason").then(response => {
      this.reasonOptions = response.data;
    });
  },
  methods: {
    /** 查询 门禁申请列表 */
    getList() {
      this.loading = true;
      listEntrance_guard_apply(this.queryParams).then(response => {
        this.entrance_guard_applyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 状态字典翻译
    statFormat(row, column, cellValue ) {
        return this.selectDictLabel(this.statOptions, cellValue);
    },
    // 申请理由字典翻译
    reasonFormat(row, column, cellValue ) {
        return this.selectDictLabel(this.reasonOptions, cellValue);
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
        personName: null,
        communityHousesId: null,
        stat: null,
        reason: null,
        pics: null,
        receiveTime: null,
        checkerName: null,
        checkTime: null
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
      this.title = "添加 门禁申请";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
        let self=this
        self.reset();
      const id = row.id || self.ids
      getEntrance_guard_apply(id).then(response => {
          self.form = response.data;
        self.open = true;
        self.title = "修改 门禁申请";
      });
    },
    /** 提交按钮 */
    submitForm() {
        let self=this
        self.$refs["form"].validate(valid => {
        if (valid) {
          if (self.form.id != null) {
            updateEntrance_guard_apply(self.form).then(response => {
              if (response.code === 200) {
                  self.msgSuccess("修改成功");
                  self.open = false;
                  self.getList();
              }
            });
          } else {
            addEntrance_guard_apply(self.form).then(response => {
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
      this.$confirm('是否确认删除 门禁申请编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delEntrance_guard_apply(ids);
        }).then(() => {
          self.getList();
        self.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
        let self=this
      const queryParams = self.queryParams;
        self.$confirm('是否确认导出所有 门禁申请数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportEntrance_guard_apply(queryParams);
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
