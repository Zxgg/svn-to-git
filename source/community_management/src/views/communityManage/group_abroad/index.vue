<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
    >
      <el-form-item label="社区名称" prop="communityId">
        <el-select
          v-model="queryParams.communityId"
          filterable
          clearable
          @change="getCommunityChildDictory"
          placeholder="请选择社区"
        >
          <el-option
            v-for="dict in communityOptions"
            :key="dict.id"
            :label="dict.name"
            :value="dict.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="小区名称" prop="communityChildId">
        <el-select
          v-model="queryParams.communityChildId"
          filterable
          clearable
          placeholder="请选择小区"
        >
          <el-option
            v-for="dict in communityChildOptions"
            :key="dict.id"
            :label="dict.name"
            :value="dict.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="组织名称" prop="organizationName">
        <el-input
          v-model="queryParams.organizationName"
          placeholder="请输入组织名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="组织类别" prop="organizationType">
        <el-select
          v-model="queryParams.organizationType"
          placeholder="请选择境外非政府组织类别"
          clearable
          size="small"
        >
          <el-option
            v-for="dict in organizationTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="来源地"
                    prop="source">
        <el-input v-model="queryParams.source"
                  placeholder="请输入来源地"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="涉及领域"
                    prop="involveField">
        <el-input v-model="queryParams.involveField"
                  placeholder="请输入涉及领域"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="主要联系人"
                    prop="mainLink">
        <el-input v-model="queryParams.mainLink"
                  placeholder="请输入主要联系人"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="性别"
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
      <el-form-item label="国籍"
                    prop="nationality">
        <el-select v-model="queryParams.nationality"
                   placeholder="请选择国籍"
                   clearable
                   size="small">
          <el-option v-for="dict in nationalityOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <!-- <el-form-item label="出生年月"
                    prop="brithDate">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="queryParams.brithDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择出生年月">
        </el-date-picker>
      </el-form-item> -->
      <!-- <el-form-item label="工作职务"
                    prop="jobPost">
        <el-input v-model="queryParams.jobPost"
                  placeholder="请输入工作职务"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="联系地址"
                    prop="address">
        <el-input v-model="queryParams.address"
                  placeholder="请输入联系地址"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="邮编"
                    prop="zipCode">
        <el-input v-model="queryParams.zipCode"
                  placeholder="请输入邮编"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="联系电话"
                    prop="tel">
        <el-input v-model="queryParams.tel"
                  placeholder="请输入联系电话"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
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
        <!-- <el-button icon="el-icon-refresh"
                   size="mini"
                   @click="resetQuery">重置</el-button> -->
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['communityManage:group_abroad:add']"
          >新增</el-button
        >
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['communityManage:group_abroad:remove']"
          >删除</el-button
        >
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10"
            class="mb8">
      <el-col :span="1.5">
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAdd"
                   v-hasPermi="['communityManage:group_abroad:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['communityManage:group_abroad:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityManage:group_abroad:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['communityManage:group_abroad:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table
      v-loading="loading"
      stripe
      :height="dataHeight"
      :data="group_abroadList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="组织名称"
        align="center"
        prop="organizationName"
      />
      <el-table-column
        label="境外非政府组织类别"
        align="center"
        prop="organizationType"
        :formatter="organizationTypeFormat"
      />
      <el-table-column label="来源地" align="center" prop="source" />
      <el-table-column label="涉及领域" align="center" prop="involveField" />
      <el-table-column label="主要联系人" align="center" prop="mainLink" />
      <el-table-column
        label="性别"
        align="center"
        prop="sex"
        :formatter="sexFormat"
      />
      <el-table-column
        label="国籍"
        align="center"
        prop="nationality"
        :formatter="nationalityFormat"
      />
      <el-table-column
        label="出生年月"
        align="center"
        prop="brithDate"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.brithDate, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column label="工作职务" align="center" prop="jobPost" />
      <el-table-column label="联系地址" align="center" prop="address" />
      <el-table-column label="邮编" align="center" prop="zipCode" />
      <el-table-column label="联系电话" align="center" prop="tel" />
      <el-table-column label="手机号码" align="center" prop="phone" />
      <!-- <el-table-column label="创建人"
                       align="center"
                       prop="userId" />
      <el-table-column label="创建单位"
                       align="center"
                       prop="deptId" />
      <el-table-column label="创建日期"
                       align="center"
                       prop="createTime"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人"
                       align="center"
                       prop="uUserId" />
      <el-table-column label="更新单位"
                       align="center"
                       prop="uDeptId" />
      <el-table-column label="更新日期"
                       align="center"
                       prop="updateTime"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
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
            v-hasPermi="['communityManage:group_abroad:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['communityManage:group_abroad:remove']"
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

    <!-- 添加或修改境内外非政府组织对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="1200px"
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="220px">
        <!-- 选择社区 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="社区名称" prop="communityId">
              <el-select
                v-model="form.communityId"
                filterable
                @change="getCommunityChildDictory2"
                placeholder="请选择社区"
              >
                <el-option
                  v-for="dict in communityOptions"
                  :key="dict.id"
                  :label="dict.name"
                  :value="dict.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="小区名称" prop="communityChildId">
              <el-select
                v-model="form.communityChildId"
                filterable
                placeholder="请选择小区"
              >
                <el-option
                  v-for="dict in communityChildOptions2"
                  :key="dict.id"
                  :label="dict.name"
                  :value="dict.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="房屋" prop="communityHousesId">
              <el-select
                v-model="form.communityHousesId"
                filterable
                clearable
                remote
                :remote-method="getHousesList"
                :loading="loading_remoteHouseInfo"
                placeholder="请选择房屋"
              >
                <el-option
                  v-for="dict in communityHousesOptions"
                  :key="dict.id"
                  :label="
                    dict.serialNumber +
                      '号楼' +
                      dict.unit +
                      '单元' +
                      dict.floor +
                      '层' +
                      dict.name
                  "
                  :value="dict.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="组织名称" prop="organizationName">
              <el-input
                v-model="form.organizationName"
                placeholder="请输入组织名称"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="境外非政府组织类别" prop="organizationType">
              <el-select
                v-model="form.organizationType"
                placeholder="请选择境外非政府组织类别"
              >
                <el-option
                  v-for="dict in organizationTypeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="境外非政府组织类别" prop="organizationType">
              <el-select
                v-model="form.organizationType"
                placeholder="请选择境外非政府组织类别"
              >
                <el-option
                  v-for="dict in organizationTypeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="来源地" prop="source">
              <el-input v-model="form.source" placeholder="请输入来源地" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="网址" prop="website">
              <el-input v-model="form.website" placeholder="请输入网址" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="涉及领域" prop="involveField">
              <el-input
                v-model="form.involveField"
                placeholder="请输入涉及领域"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="主要联系人" prop="mainLink">
              <el-input
                v-model="form.mainLink"
                placeholder="请输入主要联系人"
              />
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
            <el-form-item label="国籍" prop="nationality">
              <el-select v-model="form.nationality" placeholder="请选择国籍">
                <el-option
                  v-for="dict in nationalityOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="出生年月" prop="brithDate">
              <el-date-picker
                clearable
                size="small"
                style="width: 200px"
                v-model="form.brithDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择出生年月"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="工作职务" prop="jobPost">
              <el-input v-model="form.jobPost" placeholder="请输入工作职务" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系地址" prop="address">
              <el-input v-model="form.address" placeholder="请输入联系地址" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮编" prop="zipCode">
              <el-input v-model="form.zipCode" placeholder="请输入邮编" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话" prop="tel">
              <el-input v-model="form.tel" placeholder="请输入联系电话" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手机号码" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入手机号码" />
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
  listGroup_abroad,
  getGroup_abroad,
  delGroup_abroad,
  addGroup_abroad,
  updateGroup_abroad,
  exportGroup_abroad
} from "@/api/communityManage/group_abroad";
import {
  listCommunity_houses,
  getCommunity_houses
} from "@/api/communityUnit/community_houses";
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_building } from "@/api/communityUnit/community_building";
import { listCommunity_child } from "@/api/communityUnit/community_child";
export default {
  name: "Group_abroad",
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
      // 境内外非政府组织表格数据
      group_abroadList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 境外非政府组织类别字典
      organizationTypeOptions: [],
      // 性别字典
      sexOptions: [],
      // 国籍字典
      nationalityOptions: [],
      //社区列表
      communityOptions: [],
      //小区列表
      communityChildOptions: [],
      //小区列表
      communityChildOptions2: [],
      //小区列表全集
      allCommunityChildOptions: [],
      //社区楼栋列表
      communityBuildingOptions: [],
      // 房屋列表
      communityHousesOptions: [],
      //房屋列表加载
      loading_remoteHouseInfo: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        communityId: null,
        communityName: null,
        communityChildId: null,
        communityChildName: null,
        communityBuildingId: null,
        serialNumber: null,
        buildingName: null,
        houseNumber: null,
        organizationName: null,
        organizationType: null,
        source: null,
        involveField: null,
        mainLink: null,
        sex: null,
        nationality: null,
        brithDate: null,
        jobPost: null,
        address: null,
        zipCode: null,
        tel: null,
        phone: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      // 搜索表单控件高度
      formHeight: 0
    };
  },
  created() {
    this.getAllCommunityChildDictory();
    this.getCommunityDictory();
    this.getList();
    this.getDicts("base_overseas_NGOs_type").then(response => {
      this.organizationTypeOptions = response.data;
    });
    this.getDicts("sys_user_sex").then(response => {
      this.sexOptions = response.data;
    });
    this.getDicts("base_nationality").then(response => {
      this.nationalityOptions = response.data;
    });
  },
  mounted() {
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight;
    });
  },
  computed: {
    dataHeight() {
      let searchFormHeight = 0;
      if (this.showSearch) searchFormHeight = this.formHeight;
      return this.$store.getters.dataEleHeight - searchFormHeight - 115;
    }
  },
  methods: {
    communityNameFormat(cellValue) {
      if (this.communityOptions && this.communityOptions.length > 0) {
        let rows = this.communityOptions.filter(item => {
          return item.id === cellValue;
        });
        if (rows && rows.length > 0) {
          return rows[0].name + "  ";
        }
      }
      return "??";
    },
    communityChildNameFormat(cellValue) {
      if (
        this.allCommunityChildOptions &&
        this.allCommunityChildOptions.length > 0
      ) {
        let rows = this.allCommunityChildOptions.filter(item => {
          return item.id === cellValue;
        });
        if (rows && rows.length > 0) {
          return rows[0].name + "  ";
        }
      }
      return "";
    },
    houseAddressFormat(row) {
      return (
        row.buildingName +
        " " +
        row.unit +
        "单元" +
        row.floor +
        "层" +
        row.houseNumber
      );
    },
    getCommunityDictory() {
      this.communityOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000
      };
      listCommunity(param).then(response => {
        this.communityOptions = response.rows;
      });
    },
    getCommunityChildDictory(communityId) {
      this.communityChildOptions = [];
      this.queryParams.communityChildId = null;
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId
      };
      listCommunity_child(param).then(response => {
        this.communityChildOptions = response.rows;
      });
    },
    getCommunityChildDictory2(communityId) {
      this.communityChildOptions = [];
      this.form.communityChildId = null;
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId
      };
      listCommunity_child(param).then(response => {
        this.communityChildOptions2 = response.rows;
      });
    },
    getAllCommunityChildDictory(communityId) {
      this.allCommunityChildOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: null
      };
      listCommunity_child(param).then(response => {
        this.allCommunityChildOptions = response.rows;
      });
    },
    getHouseName(house) {
      if (house) {
        return (
          house.serialNumber + "号楼" + house.unit + "单元" + house.houseNumber
        );
      } else return "";
    },
    getHousesList(houseNumber) {
      this.communityHousesOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: null,
        communityChildId: null,
        houseNumber: houseNumber
      };
      if (this.form.communityId) {
        param.communityId = this.form.communityId;
      }
      if (this.form.communityChildId) {
        param.communityChildId = this.form.communityChildId;
      }
      this.loading_remoteHouseInfo = true;
      listCommunity_houses(param).then(response => {
        this.loading_remoteHouseInfo = false;
        this.communityHousesOptions = response.rows;
      });
    },
    /** 查询境内外非政府组织列表 */
    getList() {
      this.loading = true;
      listGroup_abroad(this.queryParams).then(response => {
        this.group_abroadList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 境外非政府组织类别字典翻译
    organizationTypeFormat(row, column) {
      return this.selectDictLabel(
        this.organizationTypeOptions,
        row.organizationType
      );
    },
    // 性别字典翻译
    sexFormat(row, column) {
      return this.selectDictLabel(this.sexOptions, row.sex);
    },
    // 国籍字典翻译
    nationalityFormat(row, column) {
      return this.selectDictLabel(this.nationalityOptions, row.nationality);
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
        communityId: null,
        communityChildId: null,
        communityHousesId: null,
        organizationName: null,
        organizationType: null,
        source: null,
        website: null,
        involveField: null,
        mainLink: null,
        sex: null,
        nationality: null,
        brithDate: null,
        jobPost: null,
        address: null,
        zipCode: null,
        tel: null,
        phone: null,
        userId: null,
        deptId: null,
        createTime: null,
        uUserId: null,
        uDeptId: null,
        updateTime: null,
        delFlag: null
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
      this.ids = selection.map(item => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加境内外非政府组织";
    },
    getHouseByID(id) {
      this.communityHousesOptions = [];
      if (id && id.length > 0) {
        getCommunity_houses(id).then(response => {
          this.communityHousesOptions.push(response.data);
        });
      }
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getGroup_abroad(id).then(response => {
        this.getCommunityChildDictory2(response.data.communityId);
        this.getHouseByID(response.data.communityHousesId);
        this.form = response.data;
        this.open = true;
        this.title = "修改境内外非政府组织";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateGroup_abroad(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addGroup_abroad(this.form).then(response => {
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
        '是否确认删除境内外非政府组织编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(function() {
          return delGroup_abroad(ids);
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
      this.$confirm("是否确认导出所有境内外非政府组织数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return exportGroup_abroad(queryParams);
        })
        .then(response => {
          this.download(response.msg);
        })
        .catch(function() {});
    }
  }
};
</script>
