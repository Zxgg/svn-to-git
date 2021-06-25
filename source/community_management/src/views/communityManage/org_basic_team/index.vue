<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
    >
      <el-form-item label="机构名称" prop="orgName">
        <el-input
          v-model="queryParams.orgName"
          placeholder="请输入机构名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号码" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入手机号码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="性别"
                    prop="sex">
        <el-select v-model="queryParams.sex"
                   placeholder="请选择性别"
                   clearable
                   size="small">
          <el-option v-for="dict in sexOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="民族"
                    prop="nation">
        <el-select v-model="queryParams.nation"
                   placeholder="请选择民族"
                   clearable
                   size="small">
          <el-option v-for="dict in nationOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="政治面貌"
                    prop="political">
        <el-select v-model="queryParams.political"
                   placeholder="请选择政治面貌"
                   clearable
                   size="small">
          <el-option v-for="dict in politicalOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <!-- <el-form-item label="公民身份证号码"
                    prop="idNum">
        <el-input v-model="queryParams.idNum"
                  placeholder="请输入公民身份证号码"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="级别"
                    prop="positionLevel">
        <el-input v-model="queryParams.positionLevel"
                  placeholder="请输入级别"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="职务"
                    prop="position">
        <el-select v-model="queryParams.position"
                   placeholder="请选择职务"
                   clearable
                   size="small">
          <el-option label="请选择字典生成"
                     value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="学历"
                    prop="degree">
        <el-select v-model="queryParams.degree"
                   placeholder="请选择学历"
                   clearable
                   size="small">
          <el-option v-for="dict in degreeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="手机号码"
                    prop="phone">
        <el-input v-model="queryParams.phone"
                  placeholder="请输入手机号码"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <el-form-item>
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['communityManage:org_basic_team:add']"
          >新增</el-button
        >
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['communityManage:org_basic_team:remove']"
          >删除</el-button
        >
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
                   v-hasPermi="['communityManage:org_basic_team:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['communityManage:org_basic_team:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityManage:org_basic_team:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['communityManage:org_basic_team:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table
      v-loading="loading"
      :data="org_basic_teamList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="机构名称" align="center" prop="orgName" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column
        label="性别"
        align="center"
        prop="sex"
        :formatter="sexFormat"
      />
      <el-table-column
        label="民族"
        align="center"
        prop="nation"
        :formatter="nationFormat"
      />
      <el-table-column
        label="政治面貌"
        align="center"
        prop="political"
        :formatter="politicalFormat"
      />
      <!-- <el-table-column label="公民身份证号码" align="center" prop="idNum" /> -->
      <el-table-column
        label="出生日期"
        align="center"
        prop="brithDate"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.brithDate, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column label="级别" align="center" prop="positionLevel" />
      <el-table-column label="职务" align="center" prop="position" />
      <!-- <el-table-column label="专业特长" align="center" prop="majorStrength" /> -->
      <el-table-column
        label="学历"
        align="center"
        prop="degree"
        :formatter="degreeFormat"
      />
      <el-table-column label="手机号码" align="center" prop="phone" />

      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['communityManage:org_basic_team:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['communityManage:org_basic_team:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改综治队伍对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="220px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="机构名称" prop="orgName">
              <!-- <el-input v-model="form.orgName"
                    placeholder="请输入机构名称" /> -->
              <el-select v-model="form.orgName" filterable placeholder="请选择">
                <el-option
                  v-for="item in orgOptions"
                  :key="item.orgName"
                  :label="item.orgName"
                  :value="item.orgName"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="form.name" placeholder="请输入姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别" prop="sex">
              <el-select v-model="form.sex" placeholder="请选择性别">
                <el-option
                  v-for="dict in sexOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="民族" prop="nation">
              <el-select v-model="form.nation" placeholder="请选择民族">
                <el-option
                  v-for="dict in nationOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="政治面貌" prop="political">
              <el-select v-model="form.political" placeholder="请选择政治面貌">
                <el-option
                  v-for="dict in politicalOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="公民身份证号码" prop="idNum">
              <el-input
                v-model="form.idNum"
                @blur="idNumChange"
                placeholder="请输入公民身份证号码"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="出生日期" prop="brithDate">
              <el-date-picker
                clearable
                size="small"
                style="width: 200px"
                v-model="form.brithDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择出生日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="级别" prop="positionLevel">
              <!-- <el-input v-model="form.positionLevel"
                    placeholder="请输入级别" /> -->
              <el-select v-model="form.positionLevel" placeholder="请选择职务">
                <el-option
                  v-for="dict in positionLevelOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="职务" prop="position">
              <el-select v-model="form.position" placeholder="请选择职务">
                <el-option
                  v-for="dict in positionOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="专业特长" prop="majorStrength">
              <el-select
                v-model="majorStrength"
                multiple
                clearable
                placeholder="请选择专业特长"
              >
                <el-option
                  v-for="dict in majorStrengthOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="学历" prop="degree">
              <el-select v-model="form.degree" placeholder="请选择学历">
                <el-option
                  v-for="dict in degreeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手机号码" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入手机号码" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="固定电话" prop="tel">
              <el-input v-model="form.tel" placeholder="请输入固定电话" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listOrg_basic_team,
  getOrg_basic_team,
  delOrg_basic_team,
  addOrg_basic_team,
  updateOrg_basic_team,
  exportOrg_basic_team
} from "@/api/communityManage/org_basic_team";
import { listOrg_basic, getOrg_basic } from "@/api/communityManage/org_basic";

export default {
  name: "Org_basic_team",
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
      // 综治队伍表格数据
      org_basic_teamList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 性别字典
      sexOptions: [],
      // 民族字典
      nationOptions: [],
      // 政治面貌字典
      politicalOptions: [],
      // 学历字典
      degreeOptions: [],
      //专业专长字典
      majorStrengthOptions: [],
      //职务字典
      positionOptions: [],
      //级别字典
      positionLevelOptions: [],
      //综治组织列表
      orgOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orgName: null,
        name: null,
        sex: null,
        nation: null,
        political: null,
        idNum: null,
        brithDate: null,
        positionLevel: null,
        position: null,
        majorStrength: null,
        degree: null,
        phone: null,
        tel: null,
        createBy: null,
        createByDeptId: null,
        createTime: null,
        updateBy: null,
        updateByDeptId: null,
        updateTime: null,
        delFlag: null
      },
      // 表单参数
      form: {},
      //专业专长
      majorStrength: [],
      // 表单校验
      rules: {
        orgName: [
          { required: true, message: "综治组织不能为空", trigger: "blur" }
        ],
        name: [{ required: true, message: "姓名不能为空", trigger: "blur" }],
        sex: [{ required: true, message: "性别不能为空", trigger: "change" }],
        nation: [
          { required: true, message: "民族不能为空", trigger: "change" }
        ],
        political: [
          { required: true, message: "政治面貌不能为空", trigger: "change" }
        ],
        brithDate: [
          { required: true, message: "出生日期不能为空", trigger: "blur" }
        ],
        positionLevel: [
          { required: true, message: "级别不能为空", trigger: "blur" }
        ],
        position: [
          { required: true, message: "职务不能为空", trigger: "change" }
        ],
        degree: [
          { required: true, message: "学历不能为空", trigger: "change" }
        ],
        phone: [
          { required: true, message: "手机号码不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    let param = {
      pageNum: 1,
      pageSize: 10000
    };
    listOrg_basic(param).then(response => {
      this.orgOptions = response.data;
    });
    this.getList();
    this.getDicts("sys_user_sex").then(response => {
      this.sexOptions = response.data;
    });
    this.getDicts("base_nation").then(response => {
      this.nationOptions = response.data;
    });
    this.getDicts("base_specialty_ability").then(response => {
      this.majorStrengthOptions = response.data;
    });
    this.getDicts("base_position").then(response => {
      this.positionOptions = response.data;
    });
    this.getDicts("base_position_level").then(response => {
      this.positionLevelOptions = response.data;
    });
    this.getDicts("base_political").then(response => {
      this.politicalOptions = response.data;
    });
    this.getDicts("base_degree").then(response => {
      this.degreeOptions = response.data;
    });
  },
  methods: {
    idNumChange(event) {
      if (this.form.idNum && this.form.idNum.length === 18) {
        let year = this.form.idNum.substr(6, 4);
        let month = this.form.idNum.substr(10, 2);
        let day = this.form.idNum.substr(12, 2);
        this.form.brithDate = year + "-" + month + "-" + day;
      }
    },
    /** 查询综治队伍列表 */
    getList() {
      this.loading = true;
      listOrg_basic_team(this.queryParams).then(response => {
        this.org_basic_teamList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 性别字典翻译
    sexFormat(row, column) {
      return this.selectDictLabel(this.sexOptions, row.sex);
    },
    // 民族字典翻译
    nationFormat(row, column) {
      return this.selectDictLabel(this.nationOptions, row.nation);
    },
    // 政治面貌字典翻译
    politicalFormat(row, column) {
      return this.selectDictLabel(this.politicalOptions, row.political);
    },
    // 学历字典翻译
    degreeFormat(row, column) {
      return this.selectDictLabel(this.degreeOptions, row.degree);
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
        orgName: null,
        name: null,
        sex: null,
        nation: null,
        political: null,
        idNum: null,
        brithDate: null,
        positionLevel: null,
        position: null,
        majorStrength: null,
        degree: null,
        phone: null,
        tel: null,
        createBy: null,
        createByDeptId: null,
        createTime: null,
        updateBy: null,
        updateByDeptId: null,
        updateTime: null,
        delFlag: null
      };
      this.resetForm("form");
      this.majorStrength = [];
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
      this.ids = selection.map(item => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加综治队伍";
    },
    getShowFromDB() {
      this.majorStrength = this.$cf.getListFromString(this.form.majorStrength);
    },
    getDBFromShow() {
      this.form.majorStrength = this.$cf.getStringFromList(this.majorStrength);
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getOrg_basic_team(id).then(response => {
        this.form = response.data;
        this.getShowFromDB();
        this.open = true;
        this.title = "修改综治队伍";
      });
    },
    getOrgIdByName() {
      if (this.form.orgName && this.form.orgName.length > 0) {
        let targetName = this.form.orgName;
        let res = this.orgOptions.filter(item => {
          return item.orgName === targetName;
        });
        if (res && res.length > 0) {
          return res[0].id;
        }
      }
      return null;
    },
    /** 提交按钮 */
    submitForm() {
      this.getDBFromShow();
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.orgId = this.getOrgIdByName();
          if (!this.form.orgId) {
            this.msgInfo("当前综治组织不可用，请重新选择综治组织！");
            return false;
          }
          if (this.form.id != null) {
            updateOrg_basic_team(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addOrg_basic_team(this.form).then(response => {
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
      this.$confirm(
        '是否确认删除综治队伍编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(function() {
          return delOrg_basic_team(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有综治队伍数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return exportOrg_basic_team(queryParams);
        })
        .then(response => {
          this.download(response.msg);
        })
        .catch(function() {});
    }
  }
};
</script>
