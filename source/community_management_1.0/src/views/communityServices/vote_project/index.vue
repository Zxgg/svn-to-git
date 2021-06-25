<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch"
             label-width="68px">
      <el-form-item label="名称"
                    prop="name"
                    label-width="auto">
        <el-input v-model="queryParams.name"
                  placeholder="请输入名称"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="投票类型"
                    prop="type"
                    label-width="auto">
        <el-select v-model="queryParams.type"
                   placeholder="请选择投票类型"
                   clearable
                   size="small">
          <el-option v-for="dict in typeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="是否发布"
                    prop="toShow"
                    label-width="auto">
        <el-select v-model="queryParams.toShow"
                   placeholder="请选择"
                   clearable
                   size="small">
          <el-option v-for="dict in toShowOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="发布时间"
                    prop="toShowTime"
                    label-width="auto">
        <el-date-picker clearable
                        size="small"
                        style="width: 400px"
                        v-model="queryParams.toShowTime"
                        type="datetimerange"
                        placeholder="选择发布时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="状态"
                    prop="stat"
                    label-width="auto">
        <el-select v-model="queryParams.stat"
                   placeholder="请选择投票状态"
                   clearable
                   style="width:150px;"
                   size="small">
          <el-option v-for="dict in statOptions"
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
                   v-hasPermi="['communityServices:vote_project:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityServices:vote_project:remove']">删除</el-button>
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
                   v-hasPermi="['communityServices:vote_project:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['communityServices:vote_project:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityServices:vote_project:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['communityServices:vote_project:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading"
              stripe
              :height="dataHeight"
              :data="vote_projectList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="名称"
                       align="center"
                       prop="name">
        <template slot-scope="scope">
          <router-link :to="'/communityServices/vote_option/'+scope.row.id+'&'+scope.row.name"
                       class="link-type">
            <span>{{ scope.row.name }}</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="投票类型"
                       align="center"
                       prop="type"
                       width="auto"
                       :formatter="typeFormat" />
      <el-table-column label="是否发布"
                       align="center"
                       prop="toShow"
                       width="auto"
                       :formatter="toShowFormat" />
      <el-table-column label="发布时间"
                       align="center"
                       prop="toShowTime"
                       width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.toShowTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="投票开始时间"
                       align="center"
                       prop="startTime"
                       width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="投票截至时间"
                       align="center"
                       prop="endTime"
                       width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="投票状态"
                       align="center"
                       prop="stat"
                       width="auto"
                       :formatter="statFormat" />
      <el-table-column label="状态"
                       align="center"
                       prop="status"
                       width="auto"
                       :formatter="statusFormat" />
      <el-table-column label="备注"
                       align="center"
                       prop="remark" />
      <el-table-column label="投票结果"
                       align="center"
                       prop="result">
        <template slot-scope="scope">
          <span v-if="scope.row.result && scope.row.result.length>0">{{ scope.row.result }}</span>
          <router-link v-else
                       :to="'/communityServices/vote_record/'+scope.row.id+'&'+scope.row.name"
                       class="link-type">
            <span>投票记录</span>
          </router-link>
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
                     v-hasPermi="['communityServices:vote_project:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['communityServices:vote_project:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改投票项目信息 对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="600px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="120px">

        <el-form-item label="名称"
                      prop="name">
          <el-input v-model="form.name"
                    placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="投票类型"
                      prop="type">
          <el-select v-model="form.type"
                     placeholder="请选择投票类型">
            <el-option v-for="dict in typeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否发布"
                      prop="toShow">
          <el-select v-model="form.toShow"
                     placeholder="请选择是否发布">
            <el-option v-for="dict in toShowOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="投票开始时间"
                      prop="startTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.startTime"
                          type="datetime"
                          placeholder="选择投票开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="投票截至时间"
                      prop="endTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.endTime"
                          type="datetime"
                          placeholder="选择投票截至时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="投票状态"
                      prop="stat">
          <el-select v-model="form.stat"
                     placeholder="请选择投票状态">
            <el-option v-for="dict in statOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio v-for="dict in statusOptions"
                      :key="dict.dictValue"
                      :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注"
                      prop="remark">
          <el-input v-model="form.remark"
                    type="textarea"
                    placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="投票结果"
                      v-if="form && form.result && form.result.length>0"
                      prop="result">
          <el-input v-model="form.result"
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
import { listVote_project, getVote_project, delVote_project, addVote_project, updateVote_project, exportVote_project } from "@/api/communityServices/vote_project";
import voteOption from '@/views/communityServices/vote_option'
export default {
  name: "Vote_project",
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
      // 投票项目信息 表格数据
      vote_projectList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 投票类型字典
      typeOptions: [],
      // 是否发布字典
      toShowOptions: [],
      // 投票状态(0是创建，1是正在进行投票，2是投票截至)字典
      statOptions: [],
      // 状态(是否正常使用）字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        type: null,
        toShow: null,
        toShowTime: null,
        stat: null,
      },
      // 表单参数
      form: {},
      //修改前的 是否发布
      oldToShow: '0',
      // 表单校验
      rules: {
        delFlag: [
          { required: true, message: "删除标志不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "投票类型不能为空", trigger: "change" }
        ],
        toShow: [
          { required: true, message: "是否发布不能为空", trigger: "change" }
        ],
      },
      // 搜索表单控件高度
      formHeight: 0
    };
  },
  created () {
    this.getList();
    this.getDicts("base_vote_type").then(response => {
      this.typeOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.toShowOptions = response.data;
    });
    this.getDicts("base_vote_stat").then(response => {
      this.statOptions = response.data;
    });
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
  },
  mounted () {
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight
    })
  },
  computed: {
    dataHeight () {
      let searchFormHeight = 0
      if (this.showSearch) searchFormHeight = this.formHeight
      return this.$store.getters.dataEleHeight - searchFormHeight - 115
    }
  },
  methods: {
    /** 查询投票项目信息 列表 */
    getList () {
      this.loading = true;
      listVote_project(this.queryParams).then(response => {
        this.vote_projectList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 投票类型字典翻译
    typeFormat (row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
    },
    // 是否发布字典翻译
    toShowFormat (row, column) {
      return this.selectDictLabel(this.toShowOptions, row.toShow);
    },
    // 投票状态(0是创建，1是正在进行投票，2是投票截至)字典翻译
    statFormat (row, column) {
      return this.selectDictLabel(this.statOptions, row.stat);
    },
    // 状态(是否正常使用）字典翻译
    statusFormat (row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
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
        type: null,
        toShow: null,
        toShowTime: null,
        startTime: null,
        endTime: null,
        stat: null,
        status: "0",
        result: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset();
      this.open = true;
      this.title = "添加投票项目信息 ";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const id = row.id || this.ids
      getVote_project(id).then(response => {
        this.form = response.data;
        this.oldToShow = this.form.toShow
        this.open = true;
        this.title = "修改投票项目信息 ";
      });
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            if (this.form.toShow === '1' && this.form.toShow !== this.oldToShow) {
              this.form.toShowTime = this.$cf.getNowTime()
            }
            updateVote_project(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            if (this.form.toShow === '1') {
              this.form.toShowTime = this.$cf.getNowTime()
            }
            addVote_project(this.form).then(response => {
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
      this.$confirm('是否确认删除投票项目信息 编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delVote_project(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有投票项目信息 数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportVote_project(queryParams);
      }).then(response => {
        this.download(response.msg);
      }).catch(function () { });
    }
  }
};
</script>
