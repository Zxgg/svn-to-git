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
      <el-form-item label="机构名称" prop="orgName">
        <el-input
          v-model="queryParams.orgName"
          placeholder="请输入机构名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="机构类型" prop="orgType">
        <el-select
          v-model="queryParams.orgType"
          placeholder="请选择机构类型"
          clearable
          size="small"
        >
          <el-option
            v-for="dict in orgTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="机构层级"
                    prop="orgLevel">
        <el-select v-model="queryParams.orgLevel"
                   placeholder="请选择机构层级"
                   clearable
                   size="small">
          <el-option v-for="dict in orgLevelOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <!-- <el-form-item label="上级机构ID"
                    prop="parentId">
        <el-input v-model="queryParams.parentId"
                  placeholder="请输入上级机构ID"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <!-- <el-form-item label="上级机构名称"
                    prop="parentName">
        <el-input v-model="queryParams.parentName"
                  placeholder="请输入上级机构名称"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="序号"
                    prop="sort">
        <el-input v-model="queryParams.sort"
                  placeholder="请输入序号"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="行政区划"
                    prop="divison">
        <el-input v-model="queryParams.divison"
                  placeholder="请输入行政区划"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="创建人"
                    prop="userId">
        <el-input v-model="queryParams.userId"
                  placeholder="请输入创建人"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="创建单位"
                    prop="deptId">
        <el-input v-model="queryParams.deptId"
                  placeholder="请输入创建单位"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="更新人"
                    prop="uUserId">
        <el-input v-model="queryParams.uUserId"
                  placeholder="请输入更新人"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="更新单位"
                    prop="uDeptId">
        <el-input v-model="queryParams.uDeptId"
                  placeholder="请输入更新单位"
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
          v-hasPermi="['communityManage:org_basic:add']"
          >新增</el-button
        >
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['communityManage:org_basic:remove']"
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
                   v-hasPermi="['communityManage:org_basic:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['communityManage:org_basic:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityManage:org_basic:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['communityManage:org_basic:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table
      v-loading="loading"
      stripe
      :height="dataHeight"
      :data="org_basicList"
      row-key="id"
      default-expand-all
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="机构名称" align="center" prop="orgName" />
      <el-table-column
        label="机构类型"
        align="center"
        prop="orgType"
        :formatter="orgTypeFormat"
      />
      <el-table-column
        label="机构层级"
        align="center"
        prop="orgLevel"
        :formatter="orgLevelFormat"
      />
      <el-table-column label="上级机构名称" align="center" prop="parentName" />
      <el-table-column label="序号" align="center" prop="sort" />
      <el-table-column label="主要职能" align="center" prop="res" />
      <el-table-column label="行政区划" align="center" prop="divison" />
      <!-- <el-table-column label="创建人"
                       align="center"
                       prop="userId" />
      <el-table-column label="创建单位"
                       align="center"
                       prop="deptId" />
      <el-table-column label="更新人"
                       align="center"
                       prop="uUserId" />
      <el-table-column label="更新单位"
                       align="center"
                       prop="uDeptId" /> -->
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
            v-hasPermi="['communityManage:org_basic:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['communityManage:org_basic:remove']"
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

    <!-- 添加或修改综治机构管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="220px">
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
            <el-form-item label="机构名称" prop="orgName">
              <el-input v-model="form.orgName" placeholder="请输入机构名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="机构类型" prop="orgType">
              <el-select v-model="form.orgType" placeholder="请选择机构类型">
                <el-option
                  v-for="dict in orgTypeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="机构层级" prop="orgLevel">
              <el-select v-model="form.orgLevel" placeholder="请选择机构层级">
                <el-option
                  v-for="dict in orgLevelOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="上级机构" prop="parentId">
              <treeselect
                v-model="form.parentId"
                :options="org_basicOptions"
                :normalizer="normalizer"
                placeholder="请选择上级机构"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="序号" prop="sort">
              <el-input v-model="form.sort" placeholder="请输入序号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="主要职能" prop="res">
              <el-input
                v-model="form.res"
                type="textarea"
                placeholder="请输入内容"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="行政区划" prop="divison">
              <el-input v-model="form.divison" placeholder="请输入行政区划" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="经度" prop="longitudes">
              <el-input v-model="form.longitudes" placeholder="请输入经度" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="纬度" prop="latitudes">
              <el-input
                v-model="form.latitudes"
                placeholder="请输入纬度"
              /> </el-form-item
          ></el-col>
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
  listOrg_basic,
  getOrg_basic,
  delOrg_basic,
  addOrg_basic,
  updateOrg_basic,
  exportOrg_basic
} from "@/api/communityManage/org_basic";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import {
  listCommunity_houses,
  getCommunity_houses
} from "@/api/communityUnit/community_houses";
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_building } from "@/api/communityUnit/community_building";
import { listCommunity_child } from "@/api/communityUnit/community_child";
export default {
  name: "Org_basic",
  components: { Treeselect },
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
      // 综治机构管理表格数据
      org_basicList: [],
      // 综治机构管理树选项
      org_basicOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 机构类型字典
      orgTypeOptions: [],
      // 机构层级字典
      orgLevelOptions: [],
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
        orgName: null,
        orgType: null,
        orgLevel: null,
        parentId: null,
        parentName: null,
        sort: null,
        res: null,
        divison: null,
        userId: null,
        deptId: null,
        uUserId: null,
        uDeptId: null
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
    this.getDicts("base_comprehensive_management_organization").then(
      response => {
        this.orgTypeOptions = response.data;
      }
    );
    this.getDicts("base_comprehensive_management_organization_level").then(
      response => {
        this.orgLevelOptions = response.data;
      }
    );
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
      debugger
      this.communityChildOptions = [];
      this.form.communityChildId = null;
      let param = {
        pageNum: 1,
        pageSize: 100000,
        // communityId: communityId
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
    /** 查询综治机构管理列表 */
    getList() {
      this.loading = true;
      listOrg_basic(this.queryParams).then(response => {
        this.org_basicList = this.handleTree(response.data, "id", "parentId");
        this.loading = false;
      });
    },
    /** 转换综治机构管理数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.id,
        label: node.orgName,
        children: node.children
      };
    },
    /** 查询部门下拉树结构 */
    getTreeselect() {
      listOrg_basic().then(response => {
        this.org_basicOptions = [];
        const data = { id: 0, orgName: "顶级节点", children: [] };
        data.children = this.handleTree(response.data, "id", "parentId");
        this.org_basicOptions.push(data);
      });
    },
    // 机构类型字典翻译
    orgTypeFormat(row, column) {
      return this.selectDictLabel(this.orgTypeOptions, row.orgType);
    },
    // 机构层级字典翻译
    orgLevelFormat(row, column) {
      return this.selectDictLabel(this.orgLevelOptions, row.orgLevel);
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
        orgName: null,
        orgType: null,
        orgLevel: null,
        parentId: null,
        parentName: null,
        sort: null,
        res: null,
        divison: null,
        longitudes: null,
        latitudes: null,
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
      this.getTreeselect();
      this.open = true;
      this.title = "添加综治机构管理";
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
      this.getTreeselect();
      // if (row != null) {
      // this.form.parentId = row.id;
      const id = row.id || this.ids;
      // }
      getOrg_basic(id).then(response => {
        this.getCommunityChildDictory2(response.data.communityId);
        this.getHouseByID(response.data.communityHousesId);
        this.form = response.data;
        this.open = true;
        this.title = "修改综治机构管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateOrg_basic(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addOrg_basic(this.form).then(response => {
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
        '是否确认删除综治机构管理编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(function() {
          return delOrg_basic(ids);
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
      this.$confirm("是否确认导出所有综治机构管理数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return exportOrg_basic(queryParams);
        })
        .then(response => {
          this.download(response.msg);
        })
        .catch(function() {});
    }
  }
};
</script>
