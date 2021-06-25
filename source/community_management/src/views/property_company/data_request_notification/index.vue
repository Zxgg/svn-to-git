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
      <el-form-item label="领取人personID" prop="personId">
        <el-input
          v-model="queryParams.personId"
          placeholder="请输入领取人personID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="领取材料类型" prop="materialType">
        <el-select v-model="queryParams.materialType" placeholder="请选择领取材料类型" clearable size="small">
          <el-option
            v-for="dict in materialTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="领取状态" prop="getStatus">
        <el-select v-model="queryParams.getStatus" placeholder="请选择领取状态" clearable size="small">
          <el-option
            v-for="dict in getStatusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="领取时间点" prop="getTime">
        <el-input
          v-model="queryParams.getTime"
          placeholder="请输入领取时间点"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="领取地点" prop="getLocation">
        <el-input
          v-model="queryParams.getLocation"
          placeholder="请输入领取地点"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否必须本人领取" prop="getThe">
        <el-select v-model="queryParams.getThe" placeholder="请选择是否必须本人领取" clearable size="small">
          <el-option
            v-for="dict in getTheOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="待领人需要携带的证件" prop="getReplaceByCertificate">
        <el-input
          v-model="queryParams.getReplaceByCertificate"
          placeholder="请输入待领人需要携带的证件"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="领取人姓名" prop="getTheName">
        <el-input
          v-model="queryParams.getTheName"
          placeholder="请输入领取人姓名"
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
          v-hasPermi="['property_company:data_request_notification:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['property_company:data_request_notification:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['property_company:data_request_notification:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['property_company:data_request_notification:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="data_request_notificationList" :height="dataHeight" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="唯一标识" align="center" prop="id" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="社区id" align="center" prop="communityId" />
      <el-table-column label="小区id" align="center" prop="communityChildId" />
      <el-table-column label="领取人personID" align="center" prop="personId" />
      <el-table-column label="领取材料类型" align="center" prop="materialType" :formatter="materialTypeFormat" />
      <el-table-column label="领取状态" align="center" prop="getStatus" :formatter="getStatusFormat" />
      <el-table-column label="领取时间点" align="center" prop="getTime" />
      <el-table-column label="领取地点" align="center" prop="getLocation" />
      <el-table-column label="领取时需要的携带的材料" align="center" prop="getByCertificate" />
      <el-table-column label="是否必须本人领取" align="center" prop="getThe" :formatter="getTheFormat" />
      <el-table-column label="待领人需要携带的证件" align="center" prop="getReplaceByCertificate" />
      <el-table-column label="领取人姓名" align="center" prop="getTheName" />
      <el-table-column label="照片列表" align="center" prop="pics" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['property_company:data_request_notification:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['property_company:data_request_notification:remove']"
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

    <!-- 添加或修改资料领取通知对话框 -->
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
        <el-form-item label="领取人personID" prop="personId">
          <el-input v-model="form.personId" placeholder="请输入领取人personID" />
        </el-form-item>
        <el-form-item label="领取材料类型" prop="materialType">
          <el-select v-model="form.materialType" placeholder="请选择领取材料类型">
            <el-option
              v-for="dict in materialTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="领取状态" prop="getStatus">
          <el-select v-model="form.getStatus" placeholder="请选择领取状态">
            <el-option
              v-for="dict in getStatusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="领取时间点" prop="getTime">
          <el-input v-model="form.getTime" placeholder="请输入领取时间点" />
        </el-form-item>
        <el-form-item label="领取地点" prop="getLocation">
          <el-input v-model="form.getLocation" placeholder="请输入领取地点" />
        </el-form-item>
        <el-form-item label="领取时需要的携带的材料" prop="getByCertificate">
          <el-input v-model="form.getByCertificate" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="是否必须本人领取" prop="getThe">
          <el-select v-model="form.getThe" placeholder="请选择是否必须本人领取">
            <el-option
              v-for="dict in getTheOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="待领人需要携带的证件" prop="getReplaceByCertificate">
          <el-input v-model="form.getReplaceByCertificate" placeholder="请输入待领人需要携带的证件" />
        </el-form-item>
        <el-form-item label="领取人姓名" prop="getTheName">
          <el-input v-model="form.getTheName" placeholder="请输入领取人姓名" />
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
import { listData_request_notification, getData_request_notification, delData_request_notification, addData_request_notification, updateData_request_notification, exportData_request_notification } from "@/api/property_company/data_request_notification";

export default {
  name: "Data_request_notification",
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
      // 资料领取通知表格数据
      data_request_notificationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 领取材料类型字典
      materialTypeOptions: [],
      // 领取状态字典
      getStatusOptions: [],
      // 是否必须本人领取字典
      getTheOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        remark: null,
        communityId: null,
        communityChildId: null,
        personId: null,
        materialType: null,
        getStatus: null,
        getTime: null,
        getLocation: null,
        getByCertificate: null,
        getThe: null,
        getReplaceByCertificate: null,
        getTheName: null,
        pics: null
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
    this.getDicts("base_wating_receive_file_type").then(response => {
      this.materialTypeOptions = response.data;
    });
    this.getDicts("base_waiting_receive_file_stat").then(response => {
      this.getStatusOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.getTheOptions = response.data;
    });
  },
  methods: {
    /** 查询资料领取通知列表 */
    getList() {
      this.loading = true;
      listData_request_notification(this.queryParams).then(response => {
        this.data_request_notificationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 领取材料类型字典翻译
    materialTypeFormat(row, column, cellValue ) {
        return this.selectDictLabel(this.materialTypeOptions, cellValue);
    },
    // 领取状态字典翻译
    getStatusFormat(row, column, cellValue ) {
        return this.selectDictLabel(this.getStatusOptions, cellValue);
    },
    // 是否必须本人领取字典翻译
    getTheFormat(row, column, cellValue ) {
        return this.selectDictLabel(this.getTheOptions, cellValue);
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
        materialType: null,
        getStatus: null,
        getTime: null,
        getLocation: null,
        getByCertificate: null,
        getThe: null,
        getReplaceByCertificate: null,
        getTheName: null,
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
      this.title = "添加资料领取通知";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
        let self=this
        self.reset();
      const id = row.id || self.ids
      getData_request_notification(id).then(response => {
          self.form = response.data;
        self.open = true;
        self.title = "修改资料领取通知";
      });
    },
    /** 提交按钮 */
    submitForm() {
        let self=this
        self.$refs["form"].validate(valid => {
        if (valid) {
          if (self.form.id != null) {
            updateData_request_notification(self.form).then(response => {
              if (response.code === 200) {
                  self.msgSuccess("修改成功");
                  self.open = false;
                  self.getList();
              }
            });
          } else {
            addData_request_notification(self.form).then(response => {
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
      this.$confirm('是否确认删除资料领取通知编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delData_request_notification(ids);
        }).then(() => {
          self.getList();
        self.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
        let self=this
      const queryParams = self.queryParams;
        self.$confirm('是否确认导出所有资料领取通知数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportData_request_notification(queryParams);
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
