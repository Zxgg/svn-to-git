<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
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
      <el-form-item label="房屋ID" prop="communityHousesId">
        <el-input
          v-model="queryParams.communityHousesId"
          placeholder="请输入房屋ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择关联类型" clearable size="small">
          <el-option
            v-for="dict in typeOptions"
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
          v-hasPermi="['property_company:community_child_user_person:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['property_company:community_child_user_person:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['property_company:community_child_user_person:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['property_company:community_child_user_person:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="community_child_user_personList" :height="dataHeight" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="唯一标识" align="center" prop="id" />
      <el-table-column label="业主的personID" align="center" prop="personId" />
      <el-table-column label="社区ID" align="center" prop="communityId" />
      <el-table-column label="小区ID" align="center" prop="communityChildId" />
      <el-table-column label="房屋ID" align="center" prop="communityHousesId" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="产权证或购房合同或租房合同图片列表" align="center" prop="credentials" />
      <el-table-column label="关联类型" align="center" prop="type" :formatter="typeFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['property_company:community_child_user_person:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['property_company:community_child_user_person:remove']"
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

    <!-- 添加或修改小区用户和小区人员房屋关联 对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="180px">
        <el-form-item label="删除标志" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
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
        <el-form-item label="房屋ID" prop="communityHousesId">
          <el-input v-model="form.communityHousesId" placeholder="请输入房屋ID" />
        </el-form-item>
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="产权证或购房合同或租房合同图片列表" prop="credentials">
          <el-input v-model="form.credentials" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="关联类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择关联类型">
            <el-option
              v-for="dict in typeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
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
import { listCommunity_child_user_person, getCommunity_child_user_person, delCommunity_child_user_person, addCommunity_child_user_person, updateCommunity_child_user_person, exportCommunity_child_user_person } from "@/api/property_company/community_child_user_person";

export default {
  name: "Community_child_user_person",
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
      // 小区用户和小区人员房屋关联 表格数据
      community_child_user_personList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 关联类型字典
      typeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        personId: null,
        communityId: null,
        communityChildId: null,
        communityHousesId: null,
        userId: null,
        credentials: null,
        type: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        delFlag: [
          { required: true, message: "删除标志不能为空", trigger: "blur" }
        ],
        personId: [
          { required: true, message: "业主的personID不能为空", trigger: "blur" }
        ],
        communityId: [
          { required: true, message: "社区ID不能为空", trigger: "blur" }
        ],
        communityChildId: [
          { required: true, message: "小区ID不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "用户ID不能为空", trigger: "blur" }
        ],
      } ,
      // 搜索表单控件高度
      formHeight: 0
    };
  },
  created() {
    // this.getList();
    this.getDicts("base_community_child_user_authentication_type").then(response => {
      this.typeOptions = response.data;
    });
  },
  methods: {
    /** 查询小区用户和小区人员房屋关联 列表 */
    getList() {
      this.loading = true;
      listCommunity_child_user_person(this.queryParams).then(response => {
        this.community_child_user_personList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 关联类型字典翻译
    typeFormat(row, column, cellValue ) {
        return this.selectDictLabel(this.typeOptions, cellValue);
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
        personId: null,
        communityId: null,
        communityChildId: null,
        communityHousesId: null,
        userId: null,
        credentials: null,
        type: null
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
      this.title = "添加小区用户和小区人员房屋关联 ";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
        let self=this
        self.reset();
      const id = row.id || self.ids
      getCommunity_child_user_person(id).then(response => {
          self.form = response.data;
        self.open = true;
        self.title = "修改小区用户和小区人员房屋关联 ";
      });
    },
    /** 提交按钮 */
    submitForm() {
        let self=this
        self.$refs["form"].validate(valid => {
        if (valid) {
          if (self.form.id != null) {
            updateCommunity_child_user_person(self.form).then(response => {
              if (response.code === 200) {
                  self.msgSuccess("修改成功");
                  self.open = false;
                  self.getList();
              }
            });
          } else {
            addCommunity_child_user_person(self.form).then(response => {
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
      this.$confirm('是否确认删除小区用户和小区人员房屋关联 编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCommunity_child_user_person(ids);
        }).then(() => {
          self.getList();
        self.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
        let self=this
      const queryParams = self.queryParams;
        self.$confirm('是否确认导出所有小区用户和小区人员房屋关联 数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCommunity_child_user_person(queryParams);
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
