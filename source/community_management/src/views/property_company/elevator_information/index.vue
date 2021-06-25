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
      <el-form-item label="楼栋id" prop="communityBuildingId">
        <el-input
          v-model="queryParams.communityBuildingId"
          placeholder="请输入楼栋id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单元" prop="unit">
        <el-input
          v-model="queryParams.unit"
          placeholder="请输入单元"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电梯编号" prop="elevatorNumber">
        <el-input
          v-model="queryParams.elevatorNumber"
          placeholder="请输入电梯编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电梯型号" prop="elevatorModel">
        <el-input
          v-model="queryParams.elevatorModel"
          placeholder="请输入电梯型号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电梯安装时间" prop="elevatorInstallDate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.elevatorInstallDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择电梯安装时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="最后一次年检时间" prop="lastCheckData">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.lastCheckData"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择最后一次年检时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="最后一次年检人员" prop="lastCheckBy">
        <el-input
          v-model="queryParams.lastCheckBy"
          placeholder="请输入最后一次年检人员"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最后一次年检人员联系电话" prop="lastCheckByPhone">
        <el-input
          v-model="queryParams.lastCheckByPhone"
          placeholder="请输入最后一次年检人员联系电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最后一次年检人员编号" prop="lastCheckByNumber">
        <el-input
          v-model="queryParams.lastCheckByNumber"
          placeholder="请输入最后一次年检人员编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="年检间隔" prop="checkInterval">
        <el-input
          v-model="queryParams.checkInterval"
          placeholder="请输入年检间隔"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电梯厂家名称" prop="elevatorFactoryName">
        <el-input
          v-model="queryParams.elevatorFactoryName"
          placeholder="请输入电梯厂家名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电梯厂家联系人" prop="elevatorFactoryBy">
        <el-input
          v-model="queryParams.elevatorFactoryBy"
          placeholder="请输入电梯厂家联系人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电梯厂家联系电话" prop="elevatorFactoryPhone">
        <el-input
          v-model="queryParams.elevatorFactoryPhone"
          placeholder="请输入电梯厂家联系电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['property_company:elevator_information:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['property_company:elevator_information:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['property_company:elevator_information:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['property_company:elevator_information:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="elevator_informationList" :height="dataHeight" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="唯一标识" align="center" prop="id" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="社区id" align="center" prop="communityId" />
      <el-table-column label="小区id" align="center" prop="communityChildId" />
      <el-table-column label="楼栋id" align="center" prop="communityBuildingId" />
      <el-table-column label="单元" align="center" prop="unit" />
      <el-table-column label="电梯编号" align="center" prop="elevatorNumber" />
      <el-table-column label="电梯型号" align="center" prop="elevatorModel" />
      <el-table-column label="电梯安装时间" align="center" prop="elevatorInstallDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.elevatorInstallDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="年检记录json，年检人员，年检人员联系电话，年检人员编号，年检时间，年检结果" align="center" prop="checkRecord" />
      <el-table-column label="最后一次年检时间" align="center" prop="lastCheckData" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastCheckData, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后一次年检人员" align="center" prop="lastCheckBy" />
      <el-table-column label="最后一次年检人员联系电话" align="center" prop="lastCheckByPhone" />
      <el-table-column label="最后一次年检人员编号" align="center" prop="lastCheckByNumber" />
      <el-table-column label="最后一次年检结果" align="center" prop="lastCheckResult" />
      <el-table-column label="年检间隔" align="center" prop="checkInterval" />
      <el-table-column label="电梯厂家名称" align="center" prop="elevatorFactoryName" />
      <el-table-column label="电梯厂家联系人" align="center" prop="elevatorFactoryBy" />
      <el-table-column label="电梯厂家联系电话" align="center" prop="elevatorFactoryPhone" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['property_company:elevator_information:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['property_company:elevator_information:remove']"
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

    <!-- 添加或修改电梯信息对话框 -->
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
        <el-form-item label="楼栋id" prop="communityBuildingId">
          <el-input v-model="form.communityBuildingId" placeholder="请输入楼栋id" />
        </el-form-item>
        <el-form-item label="单元" prop="unit">
          <el-input v-model="form.unit" placeholder="请输入单元" />
        </el-form-item>
        <el-form-item label="电梯编号" prop="elevatorNumber">
          <el-input v-model="form.elevatorNumber" placeholder="请输入电梯编号" />
        </el-form-item>
        <el-form-item label="电梯型号" prop="elevatorModel">
          <el-input v-model="form.elevatorModel" placeholder="请输入电梯型号" />
        </el-form-item>
        <el-form-item label="电梯安装时间" prop="elevatorInstallDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.elevatorInstallDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择电梯安装时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="年检记录json，年检人员，年检人员联系电话，年检人员编号，年检时间，年检结果" prop="checkRecord">
          <el-input v-model="form.checkRecord" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="最后一次年检时间" prop="lastCheckData">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.lastCheckData"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择最后一次年检时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="最后一次年检人员" prop="lastCheckBy">
          <el-input v-model="form.lastCheckBy" placeholder="请输入最后一次年检人员" />
        </el-form-item>
        <el-form-item label="最后一次年检人员联系电话" prop="lastCheckByPhone">
          <el-input v-model="form.lastCheckByPhone" placeholder="请输入最后一次年检人员联系电话" />
        </el-form-item>
        <el-form-item label="最后一次年检人员编号" prop="lastCheckByNumber">
          <el-input v-model="form.lastCheckByNumber" placeholder="请输入最后一次年检人员编号" />
        </el-form-item>
        <el-form-item label="最后一次年检结果" prop="lastCheckResult">
          <el-input v-model="form.lastCheckResult" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="年检间隔" prop="checkInterval">
          <el-input v-model="form.checkInterval" placeholder="请输入年检间隔" />
        </el-form-item>
        <el-form-item label="电梯厂家名称" prop="elevatorFactoryName">
          <el-input v-model="form.elevatorFactoryName" placeholder="请输入电梯厂家名称" />
        </el-form-item>
        <el-form-item label="电梯厂家联系人" prop="elevatorFactoryBy">
          <el-input v-model="form.elevatorFactoryBy" placeholder="请输入电梯厂家联系人" />
        </el-form-item>
        <el-form-item label="电梯厂家联系电话" prop="elevatorFactoryPhone">
          <el-input v-model="form.elevatorFactoryPhone" placeholder="请输入电梯厂家联系电话" />
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
import { listElevator_information, getElevator_information, delElevator_information, addElevator_information, updateElevator_information, exportElevator_information } from "@/api/property_company/elevator_information";

export default {
  name: "Elevator_information",
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
      // 电梯信息表格数据
      elevator_informationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        communityId: null,
        communityChildId: null,
        communityBuildingId: null,
        unit: null,
        elevatorNumber: null,
        elevatorModel: null,
        elevatorInstallDate: null,
        checkRecord: null,
        lastCheckData: null,
        lastCheckBy: null,
        lastCheckByPhone: null,
        lastCheckByNumber: null,
        lastCheckResult: null,
        checkInterval: null,
        elevatorFactoryName: null,
        elevatorFactoryBy: null,
        elevatorFactoryPhone: null
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
  },
  methods: {
    /** 查询电梯信息列表 */
    getList() {
      this.loading = true;
      listElevator_information(this.queryParams).then(response => {
        this.elevator_informationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        communityBuildingId: null,
        unit: null,
        elevatorNumber: null,
        elevatorModel: null,
        elevatorInstallDate: null,
        checkRecord: null,
        lastCheckData: null,
        lastCheckBy: null,
        lastCheckByPhone: null,
        lastCheckByNumber: null,
        lastCheckResult: null,
        checkInterval: null,
        elevatorFactoryName: null,
        elevatorFactoryBy: null,
        elevatorFactoryPhone: null
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
      this.title = "添加电梯信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
        let self=this
        self.reset();
      const id = row.id || self.ids
      getElevator_information(id).then(response => {
          self.form = response.data;
        self.open = true;
        self.title = "修改电梯信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
        let self=this
        self.$refs["form"].validate(valid => {
        if (valid) {
          if (self.form.id != null) {
            updateElevator_information(self.form).then(response => {
              if (response.code === 200) {
                  self.msgSuccess("修改成功");
                  self.open = false;
                  self.getList();
              }
            });
          } else {
            addElevator_information(self.form).then(response => {
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
      this.$confirm('是否确认删除电梯信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delElevator_information(ids);
        }).then(() => {
          self.getList();
        self.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
        let self=this
      const queryParams = self.queryParams;
        self.$confirm('是否确认导出所有电梯信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportElevator_information(queryParams);
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
