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
                 v-show="showSearch">
          <el-form-item label="社区名称"
                        prop="communityId">
            <el-select v-model="queryParams.communityId"
                       filterable
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
                       placeholder="请选择小区">
              <el-option v-for="dict in communityChildOptions"
                         :key="dict.id"
                         :label="dict.name"
                         :value="dict.id" />
            </el-select>
          </el-form-item>
          <el-form-item label="预约访客"
                        prop="passstrategyVistor">
            <el-select v-model="queryParams.passstrategyVistor"
                       placeholder="预约访客通行策略"
                       clearable
                       size="small">
              <el-option v-for="dict in passstrategyVistorOptions"
                         :key="dict.dictValue"
                         :label="dict.dictLabel"
                         :value="dict.dictValue" />
            </el-select>
          </el-form-item>
          <el-form-item label="临时访客"
                        prop="passstrategyTempvistor">
            <el-select v-model="queryParams.passstrategyTempvistor"
                       placeholder="临时访客通行策略"
                       clearable
                       size="small">
              <el-option v-for="dict in passstrategyTempvistorOptions"
                         :key="dict.dictValue"
                         :label="dict.dictLabel"
                         :value="dict.dictValue" />
            </el-select>
          </el-form-item>
          <el-form-item label="黑名单人员"
                        label-width="auto"
                        prop="passstrategyBlackface">
            <el-select v-model="queryParams.passstrategyBlackface"
                       placeholder="黑名单人员通行策略"
                       clearable
                       size="small">
              <el-option v-for="dict in passstrategyBlackfaceOptions"
                         :key="dict.dictValue"
                         :label="dict.dictLabel"
                         :value="dict.dictValue" />
            </el-select>
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
                       v-hasPermi="['passManage:pass_stratyge:add']">新增</el-button>
            <el-button type="danger"
                       icon="el-icon-delete"
                       size="mini"
                       :disabled="multiple"
                       @click="handleDelete"
                       v-hasPermi="['passManage:pass_stratyge:remove']">删除</el-button>
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
                       v-hasPermi="['passManage:pass_stratyge:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success"
                       icon="el-icon-edit"
                       size="mini"
                       :disabled="single"
                       @click="handleUpdate"
                       v-hasPermi="['passManage:pass_stratyge:edit']">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger"
                       icon="el-icon-delete"
                       size="mini"
                       :disabled="multiple"
                       @click="handleDelete"
                       v-hasPermi="['passManage:pass_stratyge:remove']">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning"
                       icon="el-icon-download"
                       size="mini"
                       @click="handleExport"
                       v-hasPermi="['passManage:pass_stratyge:export']">导出</el-button>
          </el-col>
          <el-col :span="7.5">
            <div class="VerticalMargin">
              <span class="commonTextBlock">无通行策略的小区使用默认通行策略(见系统配置)</span>
            </div>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch"
                         @queryTable="getList"></right-toolbar>
        </el-row> -->

        <el-table v-loading="loading"
                  :height="dataHeight"
                  :data="pass_stratygeList"
                  @selection-change="handleSelectionChange">
          <el-table-column type="selection"
                           width="55"
                           align="center" />
          <el-table-column label="社区名称"
                           align="center"
                           prop="communityId"
                           :formatter="communityNameFormat" />
          <el-table-column label="小区名称"
                           align="center"
                           prop="communityChildId"
                           :formatter="communityChildNameFormat" />
          <el-table-column label="预约访客"
                           align="center"
                           prop="passstrategyVistor"
                           :formatter="passstrategyVistorFormat" />
          <!-- <el-table-column label="访问时间段"
                           align="center"
                           prop="passstrategyVistorTimespan" /> -->
          <el-table-column label="临时访客"
                           align="center"
                           prop="passstrategyTempvistor"
                           :formatter="passstrategyTempvistorFormat" />
          <!-- <el-table-column label="临时访客访问时间段"
                           align="center"
                           prop="passstrategyTempvistorTimespan" /> -->
          <el-table-column label="黑名单人员"
                           align="center"
                           prop="passstrategyBlackface"
                           width="100"
                           :formatter="passstrategyBlackfaceFormat" />
          <el-table-column label="最后修改"
                           align="center"
                           prop="createBy"
                           width="100">
            <template slot-scope="scope">
              <span>{{ getLastEditUserInfo(scope.row, 'name') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="修改时间"
                           align="center"
                           prop="createTime"
                           width="160">
            <template slot-scope="scope">
              <span>{{ getLastEditUserInfo(scope.row, 'time') }}</span>
            </template>
          </el-table-column>
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
                         v-hasPermi="['passManage:pass_stratyge:edit']">修改</el-button>
              <el-button size="mini"
                         type="text"
                         icon="el-icon-delete"
                         @click="handleDelete(scope.row)"
                         v-hasPermi="['passManage:pass_stratyge:remove']">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination v-show="total>0"
                    :total="total"
                    :page.sync="queryParams.pageNum"
                    :limit.sync="queryParams.pageSize"
                    @pagination="getList" />

        <!-- 添加或修改通行策略 对话框 -->
        <el-dialog :title="title"
                   :visible.sync="open"
                   width="800px"
                   append-to-body>
          <el-form ref="form"
                   :model="form"
                   :rules="rules"
                   label-width="120px">
            <!-- <el-form-item label="社区ID"
                          prop="communityId">
              <el-input v-model="form.communityId"
                        placeholder="请输入社区ID" />
            </el-form-item>
            <el-form-item label="小区ID"
                          prop="communityChildId">
              <el-input v-model="form.communityChildId"
                        placeholder="请输入小区ID" />
            </el-form-item> -->
            <el-form-item label="社区名称"
                          prop="communityId">
              <el-select v-model="form.communityId"
                         filterable
                         clearable
                         :disabled="form.id && form.id.length>0 && form.checked && form.checked === '1'"
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
                         clearable
                         :disabled="form.id && form.id.length>0 && form.checked && form.checked === '1'"
                         placeholder="请选择小区">
                <el-option v-for="dict in communityChildOptions2"
                           :key="dict.id"
                           :label="dict.name"
                           :value="dict.id" />
              </el-select>
            </el-form-item>
            <el-form-item label="预约访客"
                          prop="passstrategyVistor">
              <!-- <el-select v-model="form.passstrategyVistor" placeholder="请选择通行策略_预约访客">
            <el-option
              v-for="dict in passstrategyVistorOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select> -->
              <el-radio-group v-model="form.passstrategyVistor"
                              size="medium">
                <el-radio-button v-for="dict in passstrategyVistorOptions"
                                 :key="dict.dictValue"
                                 :label="dict.dictValue">{{dict.dictLabel}}</el-radio-button>
              </el-radio-group>
              <el-button type="cyan"
                         icon="el-icon-refresh-left"
                         size="mini"
                         class="HorizontalMargin"
                         @click="default_passStrategy_vistor">恢复默认</el-button>
            </el-form-item>
            <el-form-item label-width="140px"
                          label="时间范围"
                          v-if="form.passstrategyVistor==='timeSpan'"
                          prop="passstrategyVistorTimespan">
              <el-time-picker v-model="passstrategyVistorTimespan"
                              is-range
                              format="HH:mm:ss"
                              value-format="HH:mm:ss"
                              :style="{width: '100%'}"
                              start-placeholder="开始时间"
                              end-placeholder="结束时间"
                              range-separator="至"
                              @change="passstrategyVistorTimespanChange"
                              clearable></el-time-picker>
            </el-form-item>
            <el-form-item label="临时访客"
                          prop="passstrategyTempvistor">
              <!-- <el-select v-model="form.passstrategyTempvistor" placeholder="请选择通行策略_临时访客">
            <el-option
              v-for="dict in passstrategyTempvistorOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select> -->
              <el-radio-group v-model="form.passstrategyTempvistor"
                              size="medium">
                <el-radio-button v-for="dict in passstrategyTempvistorOptions"
                                 :key="dict.dictValue"
                                 :label="dict.dictValue">{{dict.dictLabel}}</el-radio-button>

              </el-radio-group>
              <el-button type="cyan"
                         icon="el-icon-refresh-left"
                         size="mini"
                         class="HorizontalMargin"
                         @click="default_passStrategy_tempVistor">恢复默认</el-button>
            </el-form-item>
            <!-- <el-form-item label="临时访客访问时间段" prop="passstrategyTempvistorTimespan">
          <el-input v-model="form.passstrategyTempvistorTimespan" placeholder="请输入通行策略_临时访客访问时间段" />
        </el-form-item> -->
            <el-form-item label-width="140px"
                          label="时间范围"
                          v-if="form.passstrategyTempvistor==='timeSpan'"
                          prop="passstrategyTempvistorTimespan">
              <el-time-picker v-model="passstrategyTempvistorTimespan"
                              is-range
                              format="HH:mm:ss"
                              value-format="HH:mm:ss"
                              :style="{width: '100%'}"
                              start-placeholder="开始时间"
                              end-placeholder="结束时间"
                              range-separator="至"
                              @change="passstrategyTempvistorTimespanChange"
                              clearable></el-time-picker>
            </el-form-item>
            <el-form-item label="黑名单人员"
                          prop="passstrategyBlackface">
              <!-- <el-select v-model="form.passstrategyBlackface" placeholder="请选择通行策略_黑名单人员">
            <el-option
              v-for="dict in passstrategyBlackfaceOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select> -->
              <el-radio-group v-model="form.passstrategyBlackface"
                              size="medium">
                <el-radio-button v-for="(dict,index) in passstrategyBlackfaceOptions"
                                 :key="dict.dictValue"
                                 :label="dict.dictValue">{{dict.dictLabel}}</el-radio-button>
              </el-radio-group>
              <el-button type="cyan"
                         icon="el-icon-refresh-left"
                         size="mini"
                         class="HorizontalMargin"
                         @click="default_passStrategy_blackFace">恢复默认</el-button>
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
      </el-col>
    </el-row>

  </div>
</template>

<script>
import { listPass_stratyge, getPass_stratyge, delPass_stratyge, addPass_stratyge, updatePass_stratyge, exportPass_stratyge } from "@/api/passManage/pass_stratyge";
import { listCommunity } from "@/api/communityUnit/community";
import { treeselect as treeList } from "@/api/communityUnit/community";
import { listCommunity_child } from "@/api/communityUnit/community_child";
export default {
  name: "Pass_stratyge",
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
      // 通行策略 表格数据
      pass_stratygeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
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
      // 通行策略_预约访客字典
      passstrategyVistorOptions: [],
      // 通行策略_临时访客字典
      passstrategyTempvistorOptions: [],
      // 通行策略_黑名单人员字典
      passstrategyBlackfaceOptions: [],
      // 社区
      communityOptions: [],
      //小区列表
      communityChildOptions: [],
      //小区列表
      communityChildOptions2: [],
      //预约访客允许通行时间段
      passstrategyVistorTimespan: ['00:00:00', '23:59:59'],
      //临时访客允许通行时间段
      passstrategyTempvistorTimespan: ['00:00:00', '23:59:59'],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        communityId: null,
        communityChildId: null,
        passstrategyVistor: null,
        passstrategyVistorTimespan: null,
        passstrategyTempvistor: null,
        passstrategyTempvistorTimespan: null,
        passstrategyBlackface: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        delFlag: [
          { required: true, message: "删除标志不能为空", trigger: "blur" }
        ],
        communityId: [
          { required: true, message: "社区ID不能为空", trigger: "blur" }
        ],
        communityChildId: [
          { required: true, message: "小区ID不能为空", trigger: "blur" }
        ],
        passstrategyVistor: [
          { required: true, message: "预约访客通行策略不能为空", trigger: "blur" }
        ],
        passstrategyVistorTimespan: [
          { required: true, message: "预约访客通行策略时间段不能为空", trigger: "blur" }
        ],
        passstrategyTempvistor: [
          { required: true, message: "临时访客通行策略不能为空", trigger: "blur" }
        ],
        passstrategyTempvistorTimespan: [
          { required: true, message: "临时访客通行策略时间段不能为空", trigger: "blur" }
        ],
        passstrategyBlackface: [
          { required: true, message: "黑名单人员通行策略不能为空", trigger: "blur" }
        ]
      },
      default: {
        passstrategyVistor: null,
        passstrategyTempvistor: null,
        passstrategyBlackface: null
      },
      // 搜索表单控件高度
      formHeight: 0
    };
  },
  created () {
    this.getConfigKey("default_pass_strategy_vistor").then((response) => {
      this.default.passstrategyVistor = response.msg;
    });
    this.getConfigKey("default_pass_strategy_tempVistor").then((response) => {
      this.default.passstrategyTempvistor = response.msg;
    });
    this.getConfigKey("default_pass_strategy_blackFace").then((response) => {
      this.default.passstrategyBlackface = response.msg;
    });

    this.getTreeList()
    this.getCommunityDictory();
    this.getAllCommunityChildDictory();
    this.getDicts("base_passStrategy_vistor").then(response => {
      this.passstrategyVistorOptions = response.data;
    });
    this.getDicts("base_passStrategy_tempVistor").then(response => {
      this.passstrategyTempvistorOptions = response.data;
    });
    this.getDicts("base_passStrategy_blackFace").then(response => {
      this.passstrategyBlackfaceOptions = response.data;
    });

  },
  mounted () {
    this.getList();
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight
    })
  },
  methods: {
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
        this.queryParams.communityChildId = null;
        this.getCommunityChildDictory(this.queryParams.communityId)
        this.queryParams.deptIds = data.deptIds
      } else if (data.type === 'base_community_child') {//选择的是小区节点
        let communityChild = data.id
        let node = this.filterNodeByID(data.parentId, this.treeOptions)
        if (node && node.id) {
          this.queryParams.communityId = node.id;
          this.queryParams.communityChildId = communityChild;
          this.getCommunityChildDictory(this.queryParams.communityId)
          this.queryParams.deptIds = node.deptIds
        }
      }
      // this.queryParams.communityId = data.id;
      this.handleQuery();
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
    getCommunityDictory () {
      this.communityOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
      };
      listCommunity(param).then((response) => {
        this.communityOptions = response.rows;
      });
    },
    getCommunityChildDictory (communityId) {
      this.communityChildOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId,
      };
      listCommunity_child(param).then((response) => {
        this.communityChildOptions = response.rows;
      });
    },
    getCommunityChildDictory2 (communityId) {
      this.communityChildOptions = []
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId
      }
      listCommunity_child(param).then(response => {
        this.communityChildOptions2 = response.rows;
      });
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
    communityChildNameFormat (row, column, cellValue) {
      if (this.allCommunityChildOptions && this.allCommunityChildOptions.length > 0) {
        let rows = this.allCommunityChildOptions.filter(item => {
          return item.id === cellValue
        })
        if (rows && rows.length > 0) {
          return rows[0].name
        }
      }
      return ''
    },
    getAllCommunityChildDictory () {
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
    default_passStrategy_vistor () {
      this.form.passstrategyVistor = this.default.passstrategyVistor
    },
    default_passStrategy_tempVistor () {
      this.form.passstrategyTempvistor = this.default.passstrategyTempvistor
    },
    default_passStrategy_blackFace () {
      this.form.passstrategyBlackface = this.default.passstrategyBlackface
    },
    /** 查询通行策略 列表 */
    getList () {
      this.loading = true;
      listPass_stratyge(this.queryParams).then(response => {
        this.pass_stratygeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 通行策略_预约访客字典翻译
    passstrategyVistorFormat (row, column) {
      if (row.passstrategyVistor === 'timeSpan') {
        if (row.passstrategyVistorTimespan && row.passstrategyVistorTimespan.length > 0) {
          let timeList = this.$cf.getListFromString(row.passstrategyVistorTimespan)
          let timeStr = this.$cf.getStringFromList(timeList, '至')
          return timeStr
        }
        return row.passstrategyVistorTimespan
      } else {
        return this.selectDictLabel(this.passstrategyVistorOptions, row.passstrategyVistor);
      }
    },
    // 通行策略_临时访客字典翻译
    passstrategyTempvistorFormat (row, column) {
      if (row.passstrategyTempvistor === 'timeSpan') {
        if (row.passstrategyTempvistorTimespan && row.passstrategyTempvistorTimespan.length > 0) {
          let timeList = this.$cf.getListFromString(row.passstrategyTempvistorTimespan)
          let timeStr = this.$cf.getStringFromList(timeList, '至')
          return timeStr
        }
        return row.passstrategyTempvistorTimespan
      } else {
        return this.selectDictLabel(this.passstrategyTempvistorOptions, row.passstrategyTempvistor);
      }
    },
    // 通行策略_黑名单人员字典翻译
    passstrategyBlackfaceFormat (row, column) {
      return this.selectDictLabel(this.passstrategyBlackfaceOptions, row.passstrategyBlackface);
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
        communityId: null,
        communityChildId: null,
        passstrategyVistor: this.default.passstrategyVistor,
        passstrategyVistorTimespan: null,
        passstrategyTempvistor: this.default.passstrategyTempvistor,
        passstrategyTempvistorTimespan: null,
        passstrategyBlackface: this.default.passstrategyBlackface
      };
      this.resetForm("form");
      //预约访客允许通行时间段
      this.passstrategyVistorTimespan = ['00:00:00', '23:59:59']
      //临时访客允许通行时间段
      this.passstrategyTempvistorTimespan = ['00:00:00', '23:59:59']
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
    // 将数据从页面展示的数据类型转换为数据库中的存储类型
    getDBDataFromShow (form) {
      form.passstrategyVistorTimespan = ''
      if (this.passstrategyVistorTimespan && this.passstrategyVistorTimespan.length > 0) {
        form.passstrategyVistorTimespan = this.$cf.getStringFromList(this.passstrategyVistorTimespan)
      }
      form.passstrategyTempvistorTimespan = ''
      if (this.passstrategyTempvistorTimespan && this.passstrategyTempvistorTimespan.length > 0) {
        form.passstrategyTempvistorTimespan = this.$cf.getStringFromList(this.passstrategyTempvistorTimespan)
      }
    },
    // 将数据从数据库中的存储类型转换为页面展示的数据类型
    getShowDataFromDB (form) {
      if (form.passstrategyVistorTimespan && form.passstrategyVistorTimespan.length > 0) {
        this.passstrategyVistorTimespan = this.$cf.getListFromString(form.passstrategyVistorTimespan)
      } else {
        this.passstrategyVistorTimespan = ['00:00:00', '23:59:59']
      }
      if (form.passstrategyTempvistorTimespan && form.passstrategyTempvistorTimespan.length > 0) {
        this.passstrategyTempvistorTimespan = this.$cf.getListFromString(form.passstrategyTempvistorTimespan)
      } else {
        this.passstrategyTempvistorTimespan = ['00:00:00', '23:59:59']
      }
    },
    passstrategyVistorTimespanChange (newValues) {
      this.form.passstrategyVistorTimespan = this.$cf.getStringFromList(newValues)
    },
    passstrategyTempvistorTimespanChange (newValues) {
      this.form.passstrategyTempvistorTimespan = this.$cf.getStringFromList(newValues)
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset();
      this.open = true;
      this.title = "添加通行策略 ";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      let self = this
      this.reset();
      const id = row.id || this.ids
      getPass_stratyge(id).then(response => {
        self.getShowDataFromDB(response.data)
        self.form = response.data;
        self.getCommunityChildDictory2(self.form.communityId)
        self.open = true;
        self.title = "修改通行策略 ";
      });
    },
    /** 提交按钮 */
    submitForm () {
      this.getDBDataFromShow(this.form)
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePass_stratyge(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addPass_stratyge(this.form).then(response => {
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
      this.$confirm('是否确认删除通行策略 编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delPass_stratyge(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有通行策略 数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportPass_stratyge(queryParams);
      }).then(response => {
        this.download(response.msg);
      }).catch(function () { });
    }
  },
  computed: {
    dataHeight () {
      let searchFormHeight = 0
      if (this.showSearch) searchFormHeight = this.formHeight
      return this.$store.getters.dataEleHeight - searchFormHeight - 160
    }
  }
};
</script>
