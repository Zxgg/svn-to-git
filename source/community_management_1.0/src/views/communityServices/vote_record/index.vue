<template>
  <div class="app-container">
    <statistical />
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch"
             label-width="88px">
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
      <el-form-item label="投票人姓名11"
                    prop="name">
        <el-input v-model="queryParams.name"
                  placeholder="请输入投票人姓名"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="唯一标识"
                    prop="uuid">
        <el-input v-model="queryParams.uuid"
                  placeholder="请输入投票记录的唯一标识"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="投票项目ID"
                    prop="voteProjectId">
        <el-input v-model="queryParams.voteProjectId"
                  placeholder="请输入投票项目ID"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <el-form-item label="投票选项"
                    prop="voteProjectOptionId">
        <el-select v-model="queryParams.voteProjectOptionId"
                   clearable
                   placeholder="请选择投票选项">
          <el-option v-for="dict in vote_optionList"
                     :key="dict.id"
                     :label="dict.sort + '  ' + dict.name"
                     :value="dict.id"></el-option>
        </el-select>
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
                   v-hasPermi="['communityServices:vote_record:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['communityServices:vote_record:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityServices:vote_record:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['communityServices:vote_record:export']">导出</el-button>
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
              :data="vote_recordList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="投票人姓名"
                       align="center"
                       width="240"
                       prop="name" />
      <el-table-column label="唯一标识"
                       align="center"
                       width="260"
                       prop="uuid" />
      <!-- <el-table-column label="投票项目ID"
                       align="center"
                       prop="voteProjectId" /> -->
      <el-table-column label="选项"
                       align="center"
                       prop="voteProjectOptionId"
                       :formatter="voteProjectOptionFormat" />
      <el-table-column label="备注"
                       align="center"
                       prop="remark" />
      <el-table-column label="操作"
                       align="center"
                       width="120"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['communityServices:vote_record:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['communityServices:vote_record:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改投票记录 对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="600px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="120px">

        <el-form-item label="投票人姓名"
                      prop="name">
          <el-input v-model="form.name"
                    placeholder="请输入投票人姓名" />
        </el-form-item>
        <el-form-item label="唯一标识"
                      prop="uuid">
          <el-input v-model="form.uuid"
                    placeholder="唯一标识" />
        </el-form-item>
        <!-- <el-form-item label="投票项目ID"
                      prop="voteProjectId">
          <el-input v-model="form.voteProjectId"
                    placeholder="请输入投票项目ID" />
        </el-form-item> -->
        <el-form-item label="投票选项"
                      prop="voteProjectOptionId">
          <el-select v-model="form.voteProjectOptionId"
                     class="comboxLong"
                     placeholder="请选择投票选项">
            <el-option v-for="dict in vote_optionList"
                       :key="dict.id"
                       :label="dict.sort + '  ' + dict.name"
                       :value="dict.id"></el-option>
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
import { listVote_record, getVote_record, delVote_record, addVote_record, updateVote_record, exportVote_record, statisticalVote_record } from "@/api/communityServices/vote_record";
import { listVote_option, getVote_option, delVote_option, addVote_option, updateVote_option, exportVote_option } from "@/api/communityServices/vote_option";
import statistical from '@/views/communityServices/vote_record/statistical'
export default {
  name: "Vote_record",
  components: { statistical },
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
      // 投票记录 表格数据
      vote_recordList: [],
      // 选项列表
      vote_optionList: [],
      //投票结果统计
      statisticalVote_record: {},
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        uuid: null,
        voteProjectId: null,
        voteProjectOptionId: null,
        voteProjectName: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        uuid: [
          { required: true, message: "唯一ID，实名制投票为人员ID，否则为设备ID，不能为空", trigger: "blur" }
        ],
        voteProjectId: [
          { required: true, message: "投票项目ID不能为空", trigger: "blur" }
        ],
        voteProjectOptionId: [
          { required: true, message: "选项ID不能为空", trigger: "blur" }
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
      let param = {
        voteProjectId: this.queryParams.voteProjectId
      }
      listVote_option(param).then(response => {
        this.vote_optionList = response.rows;
      });
    }

  },
  mounted () {
    this.getList();
    this.getStatistical();
  },
  methods: {
    // 投票选项字典翻译
    voteProjectOptionFormat (row, column, cellValue) {
      let result = ''
      if (this.vote_optionList && this.vote_optionList.length > 0) {
        let selectOptionIds = this.$cf.getListFromString(cellValue)
        if (selectOptionIds && selectOptionIds.length > 0) {
          selectOptionIds.forEach(element => {
            let rows = this.vote_optionList.filter(item => {
              return item.id === element
            })
            if (rows && rows.length > 0) {
              result += rows[0].name + ','
            }
          });
          result = result.trim(',')
        }
      }
      return result
    },
    getStatistical () {
      statisticalVote_record(this.queryParams).then(response => {
        let result = response.data
      });
    },
    /** 查询投票记录 列表 */
    getList () {
      this.loading = true;
      listVote_record(this.queryParams).then(response => {
        this.vote_recordList = response.rows;
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
        uuid: null,
        voteProjectId: null,
        voteProjectOptionId: null,
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
      this.open = true;
      this.title = "添加投票记录 ";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const id = row.id || this.ids
      getVote_record(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改投票记录 ";
      });
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateVote_record(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addVote_record(this.form).then(response => {
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
      this.$confirm('是否确认删除投票记录 编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delVote_record(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有投票记录 数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportVote_record(queryParams);
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
