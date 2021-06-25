<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch"
             label-width="68px">
      <!-- <el-form-item label="投票项目ID"
                    prop="voteProjectId">
        <el-input v-model="queryParams.voteProjectId"
                  placeholder="请输入投票项目ID"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <el-form-item label="投票项目"
                    prop="voteProjectName">
        <el-input class="imputLong"
                  v-model="queryParams.voteProjectName"
                  placeholder="请输入投票项目"
                  clearable
                  :disabled="queryParams.voteProjectId && queryParams.voteProjectId.length>0"
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan"
                   icon="el-icon-search"
                   size="mini"
                   @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh"
                   size="mini"
                   @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10"
            class="mb8">
      <el-col :span="1.5">
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAdd"
                   v-hasPermi="['communityServices:vote_option:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['communityServices:vote_option:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityServices:vote_option:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['communityServices:vote_option:export']">导出</el-button>
      </el-col>
      <el-button style="position: relative;float: right;"
                 type="primary"
                 icon="el-icon-back"
                 size="mini"
                 @click="handleBack">返回</el-button>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading"
              :data="vote_optionList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="排列序号"
                       align="center"
                       width="80"
                       prop="sort" />
      <el-table-column label="名称"
                       align="center"
                       prop="name" />

      <el-table-column label="状态"
                       align="center"
                       width="80"
                       prop="status"
                       :formatter="statusFormat" />
      <!-- <el-table-column label="投票项目ID"
                       align="center"
                       width="200"
                       prop="voteProjectId" /> -->
      <el-table-column label="备注"
                       align="center"
                       prop="remark" />
      <el-table-column label="操作"
                       width="120"
                       align="center"
                       class-name="small-padding fixed-width">

        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['communityServices:vote_option:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['communityServices:vote_option:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改投票项的选项 对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="500px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="80px">
        <el-form-item label="名称"
                      prop="name">
          <el-input v-model="form.name"
                    placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="显示排序"
                      prop="dictSort">
          <el-input-number v-model="form.sort"
                           controls-position="right"
                           :min="0"
                           @keyup.enter.native="submitForm" />
        </el-form-item>
        <el-form-item label="状态"
                      prop="status">
          <el-radio-group v-model="form.status"
                          @keyup.enter.native="submitForm">
            <el-radio v-for="dict in statusOptions"
                      :key="dict.dictValue"
                      :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <!-- <el-form-item label="投票项目ID"
                      prop="voteProjectId">
          <el-input v-model="form.voteProjectId"
                    placeholder="请输入投票项目ID" />
        </el-form-item> -->
        <el-form-item label="投票项目"
                      prop="voteProjectName">
          <el-input v-model="form.voteProjectName"
                    :disabled="form.voteProjectId && form.voteProjectId.length>0"
                    placeholder="请输入投票项目" />
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
import { listVote_option, getVote_option, delVote_option, addVote_option, updateVote_option, exportVote_option } from "@/api/communityServices/vote_option";
import { listVote_project, getVote_project, delVote_project, addVote_project, updateVote_project, exportVote_project } from "@/api/communityServices/vote_project";

export default {
  name: "Vote_option",
  data () {
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
      // 投票项的选项 表格数据
      vote_optionList: [],
      // 状态数据字典
      statusOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        voteProjectId: null,
        voteProjectName: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        voteProjectId: [
          { required: true, message: "投票项目ID不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created () {
    const voteProjectId = this.$route.params && this.$route.params.projectId;
    const voteProjectName = this.$route.params && this.$route.params.projectName;
    if (voteProjectId && voteProjectId.length > 0) {
      this.queryParams.voteProjectId = voteProjectId
      this.queryParams.voteProjectName = voteProjectName
    }
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
  },
  mounted () {
    this.getList();
  },
  methods: {
    // 数据状态字典翻译
    statusFormat (row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    /** 查询投票项的选项 列表 */
    getList () {
      this.loading = true;
      listVote_option(this.queryParams).then(response => {
        this.vote_optionList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        delFlag: null,
        remark: null,
        name: null,
        sort: 0,
        status: "0",
        voteProjectId: null,
        voteProjectName: null
      };
      this.resetForm("form");
      if (this.queryParams.voteProjectId && this.queryParams.voteProjectId.length > 0) {
        this.form.voteProjectId = this.queryParams.voteProjectId
      }
      if (this.queryParams.voteProjectName && this.queryParams.voteProjectName.length > 0) {
        this.form.voteProjectName = this.queryParams.voteProjectName
      }
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset();
      if (this.vote_optionList && this.vote_optionList.length > 0)
        this.form.sort = Math.max.apply(Math, this.vote_optionList.map(item => { return item.sort })) + 1
      this.open = true;
      this.title = "添加投票项的选项 ";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const id = row.id || this.ids
      getVote_option(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改投票项的选项 ";
      });
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateVote_option(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addVote_option(this.form).then(response => {
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
      this.$confirm('是否确认删除投票项的选项 编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delVote_option(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有投票项的选项 数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportVote_option(queryParams);
      }).then(response => {
        this.download(response.msg);
      }).catch(function () { });
    },
    /** 返回上一页 */
    handleBack () {
      this.$router.go(-1);//返回上一层
    }
  }
};
</script>
