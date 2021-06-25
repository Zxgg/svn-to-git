<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch"
             label-width="68px">
      <el-form-item label="报修人姓名"
                    prop="repairPersonName"
                    label-width="auto">
        <el-input v-model="queryParams.repairPersonName"
                  placeholder="请输入报修人姓名"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="是否已经处理"
                    prop="deal"
                    label-width="auto">
        <el-select v-model="queryParams.deal"
                   placeholder="请选择"
                   clearable
                   size="small">
          <el-option v-for="dict in dealOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="处理人姓名"
                    prop="dealPersonName"
                    label-width="auto">
        <el-input v-model="queryParams.dealPersonName"
                  placeholder="请输入处理人姓名"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
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
                   v-hasPermi="['communityServices:repair_record:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityServices:repair_record:remove']">删除</el-button>
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
                   v-hasPermi="['communityServices:repair_record:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['communityServices:repair_record:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityServices:repair_record:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['communityServices:repair_record:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading"
              stripe
              :height="dataHeight"
              :data="repair_recordList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />

      <el-table-column label="报修人姓名"
                       align="center"
                       width="120"
                       prop="repairPersonName" />
      <el-table-column label="报修事项"
                       align="center"
                       prop="repairContent" />
      <el-table-column label="是否已经处理"
                       align="center"
                       prop="deal"
                       :formatter="dealFormat">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     :disabled="scope.row.deal==='1'"
                     @click="handleUpdate_deal(scope.row)"
                     v-hasPermi="['communityServices:opinion_record:edit']">{{scope.row.deal | checkedFilters(that)}}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="处理人姓名"
                       align="center"
                       width="120"
                       prop="dealPersonName" />
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
                     v-hasPermi="['communityServices:repair_record:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['communityServices:repair_record:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改报事报修记录对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="600px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="120px">

        <el-form-item label="报修人姓名"
                      prop="repairPersonName">
          <el-input v-model="form.repairPersonName"
                    placeholder="请输入报修人姓名" />
        </el-form-item>
        <el-form-item label="报修事项"
                      prop="repairContent">
          <el-input v-model="form.repairContent"
                    type="textarea"
                    placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="是否已经处理"
                      v-if="form.id && form.id.length>0"
                      prop="deal">
          <el-select v-model="form.deal"
                     placeholder="请选择是否已经处理">
            <el-option v-for="dict in dealOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="处理人姓名"
                      v-if="form.deal && form.deal==='1'"
                      prop="dealPersonName">
          <el-input v-model="form.dealPersonName"
                    placeholder="请输入处理人姓名" />
        </el-form-item>
        <el-form-item label="备注"
                      v-if="form.id && form.id.length>0"
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
import { listRepair_record, getRepair_record, delRepair_record, addRepair_record, updateRepair_record, exportRepair_record, updateRepair_record_deal } from "@/api/communityServices/repair_record";

export default {
  name: "Repair_record",
  data () {
    return {
      that: this,
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
      // 报事报修记录表格数据
      repair_recordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否已经处理(0未处理，1正在处理，2，已经处理，3不处理）字典
      dealOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        repairPersonName: null,
        repairContent: null,
        deal: null,
        dealPersonName: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      // 搜索表单控件高度
      formHeight: 0
    };
  },
  created () {
    this.getDicts("base_yes_no").then(response => {
      this.dealOptions = response.data;
    });
  },
  mounted () {
    this.getList();
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
  filters: {
    checkedFilters: function (value, self) {
      return self.dealFormat(null, null, value)
    },
  },
  methods: {
    /** 查询报事报修记录列表 */
    getList () {
      this.loading = true;
      listRepair_record(this.queryParams).then(response => {
        this.repair_recordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 是否已经处理(0未处理，1正在处理，2，已经处理，3不处理）字典翻译
    dealFormat (row, column, cellValue) {
      return this.selectDictLabel(this.dealOptions, cellValue);
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
        repairPersonName: null,
        repairContent: null,
        deal: null,
        dealPersonName: null
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
      this.title = "添加报事报修记录";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const id = row.id || this.ids
      getRepair_record(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改报事报修记录";
      });
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateRepair_record(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addRepair_record(this.form).then(response => {
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
      this.$confirm('是否确认删除报事报修记录编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delRepair_record(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有报事报修记录数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportRepair_record(queryParams);
      }).then(response => {
        this.download(response.msg);
      }).catch(function () { });
    },
    /** 修改审核状态操作 */
    handleUpdate_deal (row) {
      const name = row.name;
      let self = this
      this.$confirm('是否确认处理"', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        let param = {
          id: row.id,
          deal: '1'
        }
        updateRepair_record_deal(param).then(response => {
          if (response.code === 200) {
            self.msgSuccess("修改成功");
            self.open = false;
            self.getList();
          }
        }).catch(function () { });
      })
    }
  }
};
</script>
