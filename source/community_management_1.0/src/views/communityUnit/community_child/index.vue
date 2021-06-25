<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--树形数据-->
      <el-col :span="5"
              :xs="24">
        <div class="head-container">
          <el-input v-model="treeSearchName"
                    placeholder="请输入社区或小区名称"
                    clearable
                    size="small"
                    prefix-icon="el-icon-search"
                    style="margin-bottom: 20px" />
        </div>
        <div class="head-container">
          <el-row :gutter="10"
                  class="mb8">
            <el-col :span="1.5">
              <el-button type="primary"
                         icon="el-icon-plus"
                         size="mini"
                         @click="handleAdd_community"
                         v-hasPermi="['communityUnit:community:add']">新增</el-button>
            </el-col>
            <el-col :span="1.5">
              <el-button type="success"
                         icon="el-icon-edit"
                         size="mini"
                         @click="handleUpdate_community"
                         v-hasPermi="['communityUnit:community:edit']">修改</el-button>
            </el-col>
            <el-col :span="1.5">
              <el-button type="danger"
                         icon="el-icon-delete"
                         size="mini"
                         @click="handleDelete_community"
                         v-hasPermi="['communityUnit:community:remove']">删除</el-button>
            </el-col>
          </el-row>
        </div>
        <div class="head-container">
          <el-tree :data="treeOptions"
                   :props="defaultProps"
                   :expand-on-click-node="false"
                   :filter-node-method="filterNode"
                   ref="tree"
                   highlight-current
                   node-key="id"
                   :current-node-key="currentTreeKey"
                   default-expand-all
                   @node-click="handleNodeClick" />
        </div>
      </el-col>
      <el-col :span="19"
              :xs="24">
        <el-form :model="queryParams"
                 ref="queryForm"
                 :inline="true"
                 v-show="showSearch"
                 label-width="68px">
          <!-- 选择社区 -->
          <el-form-item label="社区名称"
                        prop="communityId">
            <el-select v-model="queryParams.communityId"
                       filterable
                       placeholder="请选择社区">
              <el-option v-for="dict in communityOptions"
                         :key="dict.id"
                         :label="dict.name"
                         :value="dict.id" />
            </el-select>
          </el-form-item>
          <!-- <el-form-item label="社区ID"
                        prop="communityId">
            <el-input v-model="queryParams.communityId"
                      placeholder="请输入社区ID"
                      clearable
                      size="small"
                      @keyup.enter.native="handleQuery" />
          </el-form-item> -->
          <el-form-item label="小区类型"
                        prop="type">
            <el-select v-model="queryParams.type"
                       placeholder="请选择小区类型"
                       clearable
                       size="small">
              <el-option v-for="dict in typeOptions"
                         :key="dict.dictValue"
                         :label="dict.dictLabel"
                         :value="dict.dictValue" />
            </el-select>
          </el-form-item>
          <el-form-item label="小区名称"
                        prop="name">
            <el-input v-model="queryParams.name"
                      placeholder="请输入小区名称"
                      clearable
                      size="small"
                      @keyup.enter.native="handleQuery" />
          </el-form-item>
          <!-- <el-form-item label="楼宇数量"
                        prop="buildingNum">
            <el-input v-model="queryParams.buildingNum"
                      placeholder="请输入楼宇数量"
                      clearable
                      size="small"
                      @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="房屋数量"
                        prop="houseNum">
            <el-input v-model="queryParams.houseNum"
                      placeholder="请输入房屋数量"
                      clearable
                      size="small"
                      @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="行政区划"
                        prop="code">
            <el-input v-model="queryParams.code"
                      placeholder="请输入行政区划"
                      clearable
                      size="small"
                      @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="竣工时间"
                        prop="buildEndTime">
            <el-date-picker clearable
                            size="small"
                            style="width: 200px"
                            v-model="queryParams.buildEndTime"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="选择竣工时间">
            </el-date-picker>
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
                       v-hasPermi="['communityUnit:community_child:add']">新增</el-button>
            <el-button type="danger"
                       icon="el-icon-delete"
                       size="mini"
                       :disabled="multiple"
                       @click="handleDelete"
                       v-hasPermi="['communityUnit:community_child:remove']">删除</el-button>
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
                       v-hasPermi="['communityUnit:community_child:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success"
                       icon="el-icon-edit"
                       size="mini"
                       :disabled="single"
                       @click="handleUpdate"
                       v-hasPermi="['communityUnit:community_child:edit']">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger"
                       icon="el-icon-delete"
                       size="mini"
                       :disabled="multiple"
                       @click="handleDelete"
                       v-hasPermi="['communityUnit:community_child:remove']">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning"
                       icon="el-icon-download"
                       size="mini"
                       @click="handleExport"
                       v-hasPermi="['communityUnit:community_child:export']">导出</el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch"
                         @queryTable="getList"></right-toolbar>
        </el-row> -->

        <el-table v-loading="loading"
                  stripe
                  :height="dataHeight"
                  :data="community_childList"
                  @selection-change="handleSelectionChange">
          <el-table-column type="selection"
                           width="55"
                           align="center" />
          <el-table-column label="社区名称"
                           align="center"
                           width="120"
                           prop="communityId"
                           :formatter="communityNameFormat" />
          <el-table-column label="小区名称"
                           align="center"
                           width="120"
                           prop="name" />
          <el-table-column label="小区类型"
                           align="center"
                           width="80"
                           prop="type"
                           :formatter="typeFormat" />
          <el-table-column label="楼宇数量"
                           align="center"
                           width="80"
                           prop="buildingNum" />
          <el-table-column label="房屋数量"
                           align="center"
                           width="80"
                           prop="houseNum" />
          <el-table-column label="详细地址"
                           align="center"
                           prop="detailedAddress" />
          <!-- <el-table-column label="行政区划"
                           align="center"
                           prop="code" /> -->
          <!-- <el-table-column label="注册时间"
                           align="center"
                           prop="registeredTime"
                           width="180">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.registeredTime, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column> -->
          <!-- <el-table-column label="开始建设时间"
                           align="center"
                           prop="buildTime"
                           width="180">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.buildTime, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column> -->
          <!-- <el-table-column label="竣工时间"
                           align="center"
                           prop="buildEndTime"
                           width="120">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.buildEndTime, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column> -->
          <el-table-column label="建设单位"
                           align="center"
                           width="120"
                           prop="buildUnit" />
          <!-- <el-table-column label="施工单位"
                           align="center"
                           width="auto"
                           prop="constructionUnit" /> -->
          <!-- <el-table-column label="备注"
                           align="center"
                           width="100"
                           prop="remark" /> -->
          <el-table-column label="操作"
                           align="center"
                           width="200"
                           class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini"
                         type="text"
                         icon="el-icon-edit"
                         @click="showBuildingList(scope.row)"
                         v-hasPermi="['communityUnit:community_building:query']">楼栋列表</el-button>
              <el-button size="mini"
                         type="text"
                         icon="el-icon-edit"
                         @click="handleUpdate(scope.row)"
                         v-hasPermi="['communityUnit:community_child:edit']">修改</el-button>
              <el-button size="mini"
                         type="text"
                         icon="el-icon-delete"
                         @click="handleDelete(scope.row)"
                         v-hasPermi="['communityUnit:community_child:remove']">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination v-show="total>0"
                    :total="total"
                    :page.sync="queryParams.pageNum"
                    :limit.sync="queryParams.pageSize"
                    @pagination="getList" />
      </el-col>
    </el-row>

    <!-- 添加或修改小区信息  对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="500px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="100px">
        <!-- 选择社区 -->
        <el-form-item label="社区名称"
                      prop="communityId">
          <el-select v-model="form.communityId"
                     filterable
                     @change="setCommunityInfo"
                     placeholder="请选择社区">
            <el-option v-for="dict in communityOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="小区名称"
                      prop="name">
          <el-input v-model="form.name"
                    placeholder="请输入小区名称" />
        </el-form-item>
        <el-form-item label="小区类型"
                      prop="type">
          <el-select v-model="form.type"
                     placeholder="请选择小区类型">
            <el-option v-for="dict in typeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="楼宇数量"
                      prop="buildingNum">
          <el-input v-model="form.buildingNum"
                    placeholder="请输入楼宇数量" />
        </el-form-item>
        <el-form-item label="房屋数量"
                      prop="houseNum">
          <el-input v-model="form.houseNum"
                    placeholder="请输入房屋数量" />
        </el-form-item>
        <el-form-item label="详细地址"
                      prop="detailedAddress">
          <el-input v-model="form.detailedAddress"
                    type="textarea"
                    placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="行政区划"
                      prop="code">
          <el-input v-model="form.code"
                    placeholder="请输入行政区划" />
        </el-form-item>
        <el-form-item label="社区管理单位"
                      prop="deptId">
          <el-select v-model="form.deptId"
                     placeholder="请选择管理单位"
                     clearable
                     size="small"
                     style="width: 240px">
            <el-option v-for="dict in communityDeptOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="注册时间"
                      prop="registeredTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.registeredTime"
                          type="datetime"
                          placeholder="选择注册时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="开始建设时间"
                      prop="buildTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.buildTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择开始建设时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="竣工时间"
                      prop="buildEndTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.buildEndTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择竣工时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="建设单位"
                      prop="buildUnit">
          <el-input v-model="form.buildUnit"
                    placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="施工单位"
                      prop="constructionUnit">
          <el-input v-model="form.constructionUnit"
                    placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="监理单位"
                      prop="supervisionUnit">
          <el-input v-model="form.supervisionUnit"
                    placeholder="请输入内容" />
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
        <el-button @click="otherSubmitForm"
                   v-if="form.id && form.id.length>0">另存一条</el-button>
        <el-button type="primary"
                   @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog :title="buildingListTitle"
               :visible.sync="buildingListOpen"
               width="90%"
               :close-on-click-modal=false
               append-to-body>
      <div slot="footer"
           class="dialog-footer">
        <communityBuilding :communityInfo="currentCommunityInfo" />
        <el-button @click="buildingListCancel">关 闭</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改社区信息对话框 -->
    <el-dialog :title="title"
               :visible.sync="open_community"
               width="600px"
               append-to-body>
      <el-form ref="form_community"
               :model="form_community"
               :rules="rules"
               label-width="120px">

        <el-form-item label="社区名称"
                      prop="name">
          <el-input v-model="form_community.name"
                    placeholder="请输入社区名称" />
        </el-form-item>
        <el-form-item label="社区地址">
          <v-distpicker :province.sync="form_community.addressProvincial"
                        :city.sync="form_community.addressCitie"
                        :area.sync="form_community.addressCountie"
                        :code.sync="form_community.code"></v-distpicker>
        </el-form-item>
        <el-form-item label="行政区划"
                      prop="code">
          <el-input v-model="form_community.code"
                    placeholder="请输入行政区划" />
        </el-form-item>
        <el-form-item label="街道"
                      prop="untitled">
          <el-input v-model="form_community.untitled"
                    placeholder="请输入街道" />
        </el-form-item>
        <el-form-item label="社区管理单位"
                      prop="untitled">
          <el-tree :data="deptOptions"
                   show-checkbox
                   default-expand-all
                   ref="dept"
                   node-key="id"
                   empty-text="加载中，请稍后"
                   :props="deptProps"></el-tree>
        </el-form-item>
        <el-form-item label="经度"
                      prop="longitud">
          <el-input v-model="form_community.longitud"
                    placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度"
                      prop="latitude">
          <el-input v-model="form_community.latitude"
                    placeholder="请输入纬度" />
        </el-form-item>
        <el-form-item label="备注"
                      prop="remark">
          <el-input v-model="form_community.remark"
                    type="textarea"
                    placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button type="primary"
                   @click="submitForm_community">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCommunity_child, getCommunity_child, delCommunity_child, addCommunity_child, updateCommunity_child, exportCommunity_child } from "@/api/communityUnit/community_child";
import { treeselect as treeList } from "@/api/communityUnit/community";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import { listCommunity, getCommunity, delCommunity, addCommunity, updateCommunity, exportCommunity } from "@/api/communityUnit/community";
import { treeselect as deptTreeselect, roleDeptTreeselect } from "@/api/system/dept";
import communityBuilding from '@/views/communityUnit/community_building'
import VDistpicker from '@/components/address_distpicke'
export default {
  name: "Community_child",
  components: { VDistpicker, communityBuilding, Treeselect },
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
      // 小区信息  表格数据
      community_childList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示弹出层 社区
      open_community: false,
      // 小区类型字典
      typeOptions: [],
      //属性结构搜索关键词
      treeSearchName: '',
      // 树形控件数据源
      treeOptions: undefined,
      // 当前选中节点的key
      currentTreeKey: '',
      //树形控件参数
      defaultProps: {
        children: "children",
        label: "name",
      },
      //社区列表
      communityOptions: [],
      //楼栋列表弹出层标题
      buildingListTitle: '',
      //是否显示楼栋列表弹出层
      buildingListOpen: false,
      //展示楼栋信息时的社区信息
      currentCommunityInfo: null,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        communityId: null,
        deptIds: null,
        type: null,
        buildingNum: null,
        houseNum: null,
        detailedAddress: null,
        code: null,
        buildEndTime: null,
        buildUnit: null,
        constructionUnit: null,
      },
      // 表单参数
      form: {},
      // 表单参数 社区
      form_community: {},
      //社区和组织机构（部门）关联信息
      checkDeptIdList: [],
      //小区可选的组织机构，即社区选择的组织机构列表
      communityDeptOptions: [],
      // 部门列表
      deptOptions: [],
      deptProps: {
        children: "children",
        label: "label"
      },
      // 表单校验
      rules: {
        delFlag: [
          { required: true, message: "删除标志不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "小区类型不能为空", trigger: "change" }
        ],
        buildingNum: [
          { required: true, message: "楼宇数量不能为空", trigger: "blur" }
        ],
        houseNum: [
          { required: true, message: "房屋数量不能为空", trigger: "blur" }
        ],
      },
      // 搜索表单控件高度
      formHeight: 0
    };
  },
  created () {
    this.getTreeList()
    this.getCommunityDictory()
    this.getList();
    this.getDicts("base_community_child_type").then(response => {
      this.typeOptions = response.data;
    });
    this.getDeptTreeselect()
  },
  watch: {
    treeSearchName (val) {
      this.$refs.tree.filter(val);
    }
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
  watch: {
    currentTreeKey: {
      handler: function (newValue) {
        console.log('this.currentTreeKey change!', this.currentTreeKey)
      },
      immediate: true//路由发生变化时界面还未重绘完成，刷新布局没有意义，当前已经在重绘时处理
    }
  },
  methods: {
    /** 查询部门树结构 */
    getDeptTreeselect () {
      deptTreeselect().then(response => {
        this.deptOptions = response.data;
      });
    },
    /** 根据角色ID查询部门树结构 */
    getRoleDeptTreeselect (id) {
      return roleDeptTreeselect(id).then(response => {
        this.deptOptions = response.depts;
        return response;
      });
    },
    // 所有部门节点数据
    getDeptAllCheckedKeys () {
      // 目前被选中的部门节点
      // let halfCheckedKeys = this.$refs.dept.getHalfCheckedKeys();
      // 半选中的部门节点
      let checkedKeys = this.$refs.dept.getCheckedKeys();
      // checkedKeys.unshift.apply(checkedKeys, halfCheckedKeys);
      return checkedKeys;
    },
    /** 查询下拉树结构 */
    getTreeList () {
      treeList().then((response) => {
        this.treeOptions = response.data;
      });
    },
    // 筛选节点
    filterNode (value, data) {
      if (!value) return true;
      return data.name.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick (data) {
      if (data.type === 'base_community') {//选择的是社区节点
        this.queryParams.communityId = data.id;
        this.queryParams.deptIds = data.deptIds
      } else if (data.type === 'base_community_child') {//选择的是小区节点
        let node = this.filterNodeByID(data.parentId, this.treeOptions)
        if (node && node.id) {
          this.queryParams.communityId = node.id;
          this.queryParams.deptIds = node.deptIds
        }
      }
      // this.queryParams.communityId = data.id;
      this.handleQuery();
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
    /** 查询小区信息  列表 */
    getList () {
      this.loading = true;
      listCommunity_child(this.queryParams).then(response => {
        this.community_childList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 小区类型字典翻译
    typeFormat (row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
    },
    communityNameFormat (row, column, cellValue) {
      if (this.communityOptions && this.communityOptions.length > 0) {
        let rows = this.communityOptions.filter(item => {
          return item.id === cellValue
        })
        if (rows && rows.length > 0) {
          return rows[0].name
        }
      }
      return '??'
    },
    // 取消按钮
    cancel () {
      this.open = false;
      this.open_community = false;
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
        delFlag: '0',
        remark: null,
        name: null,
        communityId: null,
        type: null,
        buildingNum: null,
        houseNum: null,
        detailedAddress: null,
        code: null,
        registeredTime: null,
        buildTime: null,
        buildEndTime: null,
        buildUnit: null,
        constructionUnit: null,
        supervisionUnit: null
      };
      this.resetForm("form");
      if (this.queryParams.communityId && this.queryParams.communityId.length > 0) {
        this.form.communityId = this.queryParams.communityId
        //获取社区已选的组织机构
        this.setCommunityDeptOptions(this.queryParams.deptIds)
      }
    },
    setCommunityDeptOptions (deptIds) {
      let deptItemList = []
      if (deptIds && deptIds.length > 0) {
        let deptIdList = this.$cf.getListFromString(deptIds)
        deptIdList.forEach(deptId => {
          let node = this.filterNodeByID(deptId, this.deptOptions)
          if (node) {
            deptItemList.push({
              dictValue: node.id,
              dictLabel: node.label
            })
          }
        });
      }
      this.communityDeptOptions = deptItemList
    },
    setCommunityInfo (communityId) {
      let node = this.filterNodeByID(communityId, this.treeOptions)
      if (node && node.id) {
        this.setCommunityDeptOptions(node.deptIds)
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
      this.title = "添加小区信息  ";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const id = row.id || this.ids
      getCommunity_child(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改小区信息  ";
        let node = this.filterNodeByID(this.form.communityId, this.treeOptions)
        if (node && node.id) {
          this.setCommunityDeptOptions(node.deptIds)
        }
      });
    },
    /** 另存一条 提交按钮 */
    otherSubmitForm () {
      this.form.id = null
      this.submitForm()
    },

    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCommunity_child(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getTreeList()
                this.getList();
                this.getCommunityDictory()
              }
            });
          } else {
            addCommunity_child(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getTreeList()
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
      this.$confirm('是否确认删除小区信息  编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delCommunity_child(ids);
      }).then(() => {
        this.getTreeList()
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有小区信息  数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportCommunity_child(queryParams);
      }).then(response => {
        this.download(response.msg);
      }).catch(function () { });
    },
    /** 展示楼宇列表按钮操作 */
    showBuildingList (row) {
      this.currentCommunityInfo = row
      this.buildingListTitle = '楼栋列表'
      this.buildingListOpen = true
    },
    /** 取消展示楼宇列表按钮操作 */
    buildingListCancel () {
      this.buildingListOpen = false
    },
    // 表单重置
    reset_community () {
      this.form_community = {
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        remark: null,
        id: null,
        name: null,
        addressProvincial: '江苏省',
        addressCitie: '扬州市',
        addressCountie: '邗江区',
        deptIds: null,
        code: null,
        untitled: null,
        longitud: null,
        latitude: null
      };
      this.resetForm("form_community");
    },
    /** 提交按钮 社区的修改与新增 */
    submitForm_community () {
      let self = this
      this.$refs["form_community"].validate(valid => {
        if (valid) {
          if (self.form_community.id != null) {
            self.form_community.deptIds = self.$cf.getStringFromList(self.getDeptAllCheckedKeys())

            updateCommunity(self.form_community).then(response => {
              if (response.code === 200) {
                self.msgSuccess("修改成功");
                self.open_community = false;
                self.getTreeList();
                this.getCommunityDictory()
              }
            });
          } else {
            this.form.deptIds = this.getDeptAllCheckedKeys();
            addCommunity(this.form_community).then(response => {
              if (response.code === 200) {
                self.msgSuccess("新增成功");
                self.open_community = false;
                self.getTreeList();
                this.getCommunityDictory()
              }
            });
          }
        }
      });
    },
    /** 新增按钮操作 社区*/
    handleAdd_community () {
      this.reset_community();
      this.open_community = true;
      this.title = "添加社区信息";
    },
    filterNodeByID (value, dataList) {
      if (dataList && dataList.length > 0) {
        for (let index = 0; index < dataList.length; index++) {
          const data = dataList[index];
          if (data && data.id === value) {
            return data
          } else {
            if (data.children && data.children.length > 0) {
              let result = this.filterNodeByID(value, data.children)
              if (result) return result
            }
          }
        }
        return null
      } else {
        return null
      }
    },
    /** 修改按钮操作 社区*/
    handleUpdate_community () {
      let row = this.$refs.tree.getCurrentNode()
      if (!row) {
        this.msgInfo("请选择一个节点");
        return false
      }
      if (row.type === "base_community_child") {
        //如果选中小区节点，就使用此小区的社区
        row = this.filterNodeByID(row.parentId, this.treeOptions)
      }
      if (!row) {
        this.msgInfo("请选择一个节点");
        return false
      }
      this.reset_community();
      let self = this
      const id = row.id || this.ids
      getCommunity(id).then(response => {
        self.form_community = response.data;
        self.title = "修改社区信息";
        self.open_community = true;
        self.$nextTick(function () {
          self.checkDeptIdList = self.$cf.getListFromString(self.form_community.deptIds);
          self.$refs.dept.setCheckedKeys(self.checkDeptIdList);
        })

      });
    },
    /** 删除按钮操作 */
    handleDelete_community () {
      let row = this.$refs.tree.getCurrentNode()
      if (!row) {
        this.msgInfo("请选择一个节点");
        return false
      }
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除社区信息编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delCommunity(ids);
      }).then(() => {
        this.getTreeList();
        this.getCommunityDictory()
        this.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 转换菜单数据结构 */
    normalizer (node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.id,
        label: node.name,
        children: node.children
      };
    }
  }
};
</script>

<style lang="scss">
.addressHeader {
  background: transparent;
}
</style>