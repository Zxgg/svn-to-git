<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch"
             label-width="68px">
      <el-form-item label="社区名称"
                    prop="communityId">
        <el-select v-model="queryParams.communityId"
                   filterable
                   clearable
                   @change="getCommunityChildDictory"
                   placeholder="请选择社区">
          <el-option v-for="dict in communityOptions"
                     :key="dict.id"
                     :label="dict.name"
                     :value="dict.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="小区名称"
                    prop="communityChildId">
        <el-select v-model="queryParams.communityChildId"
                   filterable
                   clearable
                   placeholder="请选择小区">
          <el-option v-for="dict in communityChildOptions"
                     :key="dict.id"
                     :label="dict.name"
                     :value="dict.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="组织名称"
                    prop="orgName">
        <el-input v-model="queryParams.orgName"
                  placeholder="请输入组织名称"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="组织类型"
                    prop="orgType">
        <el-select v-model="queryParams.orgType"
                   placeholder="请选择组织类型"
                   clearable
                   size="small">
          <el-option v-for="dict in orgTypeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="组织层级"
                    prop="orgLevel">
        <el-select v-model="queryParams.orgLevel"
                   placeholder="请选择组织层级"
                   clearable
                   size="small">
          <el-option v-for="dict in orgLevelOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="业务指导部门"
                    prop="directionDep">
        <el-input v-model="queryParams.directionDep"
                  placeholder="请输入业务指导部门"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="人员数量"
                    prop="peopleCount">
        <el-input v-model="queryParams.peopleCount"
                  placeholder="请输入人员数量"
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
                    prop="division">
        <el-input v-model="queryParams.division"
                  placeholder="请输入行政区划"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
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
                   v-hasPermi="['communityManage:org_public:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityManage:org_public:remove']">删除</el-button>
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
                   v-hasPermi="['communityManage:org_public:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['communityManage:org_public:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityManage:org_public:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['communityManage:org_public:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading"
              stripe
              :height="dataHeight"
              :data="org_publicList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="组织名称"
                       align="center"
                       prop="orgName" />
      <el-table-column label="组织类型"
                       align="center"
                       prop="orgType"
                       :formatter="orgTypeFormat" />
      <el-table-column label="组织层级"
                       align="center"
                       prop="orgLevel"
                       :formatter="orgLevelFormat" />
      <el-table-column label="业务指导部门"
                       align="center"
                       prop="directionDep" />
      <el-table-column label="人员数量"
                       align="center"
                       prop="peopleCount" />
      <el-table-column label="序号"
                       align="center"
                       prop="sort" />
      <el-table-column label="主要职能"
                       align="center"
                       prop="res" />
      <el-table-column label="行政区划"
                       align="center"
                       prop="division" />
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
      <el-table-column label="操作"
                       align="center"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['communityManage:org_public:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['communityManage:org_public:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改群防群治组织对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="500px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="80px">
        <el-form-item label="社区名称"
                      prop="communityId">
          <el-select v-model="form.communityId"
                     filterable
                     @change="getCommunityChildDictory2"
                     placeholder="请选择社区">
            <el-option v-for="dict in communityOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="小区名称"
                      prop="communityChildId">
          <el-select v-model="form.communityChildId"
                     filterable
                     placeholder="请选择小区">
            <el-option v-for="dict in communityChildOptions2"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="房屋"
                      prop="communityHousesId">
          <el-select v-model="form.communityHousesId"
                     filterable
                     clearable
                     remote
                     :remote-method="getHousesList"
                     :loading="loading_remoteHouseInfo"
                     placeholder="请搜索关联的房屋">
            <el-option v-for="dict in communityHousesOptions"
                       :key="dict.id"
                       :label="dict.serialNumber + '号楼' + dict.unit + '单元' + dict.floor + '层' + dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="组织名称"
                      prop="orgName">
          <el-input v-model="form.orgName"
                    placeholder="请输入组织名称" />
        </el-form-item>
        <el-form-item label="组织类型"
                      prop="orgType">
          <el-select v-model="form.orgType"
                     placeholder="请选择组织类型">
            <el-option v-for="dict in orgTypeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="组织层级"
                      prop="orgLevel">
          <el-select v-model="form.orgLevel"
                     placeholder="请选择组织层级">
            <el-option v-for="dict in orgLevelOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="业务指导部门"
                      prop="directionDep">
          <el-input v-model="form.directionDep"
                    placeholder="请输入业务指导部门" />
        </el-form-item>
        <el-form-item label="人员数量"
                      prop="peopleCount">
          <el-input v-model="form.peopleCount"
                    placeholder="请输入人员数量" />
        </el-form-item>
        <!-- <el-form-item label="上级机构ID"
                      prop="parentId">
          <el-input v-model="form.parentId"
                    placeholder="请输入上级机构ID" />
        </el-form-item> -->
        <el-form-item label="序号"
                      prop="sort">
          <el-input v-model="form.sort"
                    placeholder="请输入序号" />
        </el-form-item>
        <el-form-item label="主要职能"
                      prop="res">
          <el-input v-model="form.res"
                    type="textarea"
                    placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="行政区划"
                      prop="division">
          <el-input v-model="form.division"
                    placeholder="请输入行政区划" />
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
import { listOrg_public, getOrg_public, delOrg_public, addOrg_public, updateOrg_public, exportOrg_public } from "@/api/communityManage/org_public";
import { listCommunity_houses, getCommunity_houses, } from "@/api/communityUnit/community_houses";
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_building } from "@/api/communityUnit/community_building";
import { listCommunity_child } from "@/api/communityUnit/community_child";
export default {
  name: "Org_public",
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
      // 群防群治组织表格数据
      org_publicList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 组织类型字典
      orgTypeOptions: [],
      // 组织层级字典
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
        directionDep: null,
        peopleCount: null,
        sort: null,
        res: null,
        division: null,
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
    this.getAllCommunityChildDictory();
    this.getCommunityDictory()
    this.getList();
    this.getDicts("base_mass_prevention_control_organization").then(response => {
      this.orgTypeOptions = response.data;
    });
    this.getDicts("base_comprehensive_management_organization_level").then(response => {
      this.orgLevelOptions = response.data;
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
    communityNameFormat (cellValue) {
      if (this.communityOptions && this.communityOptions.length > 0) {
        let rows = this.communityOptions.filter(item => {
          return item.id === cellValue
        })
        if (rows && rows.length > 0) {
          return rows[0].name + '  '
        }
      }
      return '??'
    },
    communityChildNameFormat (cellValue) {
      if (this.allCommunityChildOptions && this.allCommunityChildOptions.length > 0) {
        let rows = this.allCommunityChildOptions.filter(item => {
          return item.id === cellValue
        })
        if (rows && rows.length > 0) {
          return rows[0].name + '  '
        }
      }
      return ''
    },
    houseAddressFormat (row) {
      return row.buildingName + ' ' + row.unit + '单元' + row.floor + '层' + row.houseNumber
    },
    getCommunityDictory () {
      this.communityOptions = []
      let param = {
        pageNum: 1,
        pageSize: 100000
      }
      listCommunity(param).then(response => {
        this.communityOptions = response.rows;
      });
    },
    getCommunityChildDictory (communityId) {
      this.communityChildOptions = []
      this.queryParams.communityChildId = null
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId
      }
      listCommunity_child(param).then(response => {
        this.communityChildOptions = response.rows;
      });
    },
    getCommunityChildDictory2 (communityId) {
      this.communityChildOptions = []
      this.form.communityChildId = null
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId
      }
      listCommunity_child(param).then(response => {
        this.communityChildOptions2 = response.rows;
      });
    },
    getAllCommunityChildDictory (communityId) {
      this.allCommunityChildOptions = []
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: null
      }
      listCommunity_child(param).then(response => {
        this.allCommunityChildOptions = response.rows;
      });
    },
    getHouseName (house) {
      if (house) {
        return house.serialNumber + '号楼' + house.unit + '单元' + house.houseNumber
      }
      else return ''
    },
    getHousesList (houseNumber) {
      this.communityHousesOptions = []
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: null,
        communityChildId: null,
        houseNumber: houseNumber
      }
      if (this.form.communityId) {
        param.communityId = this.form.communityId
      }
      if (this.form.communityChildId) {
        param.communityChildId = this.form.communityChildId
      }
      this.loading_remoteHouseInfo = true
      listCommunity_houses(param).then(response => {
        this.loading_remoteHouseInfo = false
        this.communityHousesOptions = response.rows;
      });
    },
    /** 查询群防群治组织列表 */
    getList () {
      this.loading = true;
      listOrg_public(this.queryParams).then(response => {
        this.org_publicList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 组织类型字典翻译
    orgTypeFormat (row, column) {
      return this.selectDictLabel(this.orgTypeOptions, row.orgType);
    },
    // 组织层级字典翻译
    orgLevelFormat (row, column) {
      return this.selectDictLabel(this.orgLevelOptions, row.orgLevel);
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
        communityId: null,
        communityChildId: null,
        communityHousesId: null,
        orgName: null,
        orgType: null,
        orgLevel: null,
        directionDep: null,
        peopleCount: null,
        parentId: null,
        parentName: null,
        sort: null,
        res: null,
        division: null,
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
      this.title = "添加群防群治组织";
    },
    getHouseByID (id) {
      this.communityHousesOptions = []
      if (id && id.length > 0) {
        getCommunity_houses(id).then((response) => {
          this.communityHousesOptions.push(response.data)
        });
      }
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const id = row.id || this.ids
      getOrg_public(id).then(response => {
        this.getCommunityChildDictory2(response.data.communityId)
        this.getHouseByID(response.data.communityHousesId)
        this.form = response.data;
        this.open = true;
        this.title = "修改群防群治组织";
      });
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateOrg_public(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addOrg_public(this.form).then(response => {
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
      this.$confirm('是否确认删除群防群治组织编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delOrg_public(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有群防群治组织数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportOrg_public(queryParams);
      }).then(response => {
        this.download(response.msg);
      }).catch(function () { });
    }
  }
};
</script>