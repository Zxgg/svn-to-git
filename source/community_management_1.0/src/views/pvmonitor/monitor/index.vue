<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch"
             label-width="68px">
      <el-form-item label="社区"
                    prop="communityId">
        <el-select v-model="queryParams.communityId"
                   filterable
                   clearable
                   placeholder="请选择社区"
                   @change="getCommunityChildDictory">
          <el-option v-for="dict in communityOptions"
                     :key="dict.id"
                     :label="dict.name"
                     :value="dict.id" />
        </el-select>
        <!-- <el-button v-hasPermi="['communityUnit:community:query']"
                   size="mini"
                   type="text"
                   icon="el-icon-refresh"
                   @click="getCommunityDictory">刷新</el-button> -->
      </el-form-item>
      <el-form-item label="小区"
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
      <el-form-item label="任务名称"
                    prop="name">
        <el-input v-model="queryParams.name"
                  clearable
                  size="small"
                  placeholder="请输入任务名称"
                  @keyup.enter.native="handleQuery"></el-input>
      </el-form-item>
      <el-form-item label="任务类型"
                    prop="rosterType">
        <el-select v-model="queryParams.rosterType"
                   placeholder="请选择任务类型"
                   clearable
                   size="small">
          <el-option v-for="dict in RosterTypeList"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue">
          </el-option>
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="创建时间"
                    prop="timeValue">
        <el-date-picker clearable
                        size="small"
                        style="width: 350px"
                        v-model="queryParams.timeValue"
                        type="datetimerange"
                        align="right"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
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
                   v-hasPermi="['communityUnit:community:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityUnit:community:remove']">删除</el-button>
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
                   v-hasPermi="['communityUnit:community:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['communityUnit:community:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityUnit:community:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['communityUnit:community:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->
    <el-table v-loading="loading"
              stripe
              :height="dataHeight"
              :data="monitorList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="任务名称"
                       align="center"
                       prop="name" />
      <el-table-column label="社区名称"
                       align="center"
                       prop="communityId"
                       :formatter="communityNameFormat" />
      <el-table-column label="小区名称"
                       align="center"
                       prop="communityChildId"
                       :formatter="communityChildNameFormat" />
      <el-table-column label="创建时间"
                       align="center"
                       prop="createTime"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{m}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建人"
                       width="140"
                       align="center"
                       prop="createBy" />
      <el-table-column label="布控名单"
                       width="240"
                       align="center"
                       prop="groupNames"
                       :formatter="getMonitorGroup" />
      <el-table-column label="布控卡组"
                       width="240"
                       align="center"
                       prop="bayonetNames"
                       :formatter="getMonitorBayonetList" />
      <el-table-column label="任务类型"
                       width="140"
                       align="center"
                       prop="rosterType"
                       :formatter="taskTypeFormat" />
      <el-table-column label="任务状态"
                       width="100"
                       align="center"
                       prop="enable"
                       :formatter="getMonitorTaskEnableFlag" />
      <el-table-column label="操作"
                       align="center"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-s-data"
                     @click="ShowTask(scope.row)"
                     v-hasPermi="['communityUnit:community_building:query']">详情</el-button>
          <el-button v-if="scope.row.enable === 'false'"
                     size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="EnableTask(scope.row)"
                     v-hasPermi="['communityUnit:community_building:query']">启用</el-button>
          <el-button v-if="scope.row.enable === 'true'"
                     size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="EnableTask(scope.row)"
                     v-hasPermi="['communityUnit:community_building:query']">停用</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['communityUnit:community:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['communityUnit:community:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination ref="pagination"
                v-show="total>0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改布控信息对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="37%"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="100px">
        <el-form-item label="社区"
                      prop="communityId">
          <el-select v-model="form.communityId"
                     filterable
                     :disabled="form.id && form.id.length>0"
                     clearable
                     placeholder="请选择社区"
                     @change="getCommunityChildDictory2">
            <el-option v-for="dict in communityOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
          <el-button v-hasPermi="['communityUnit:community:query']"
                     size="mini"
                     type="text"
                     icon="el-icon-refresh"
                     @click="getCommunityDictory">刷新</el-button>
        </el-form-item>
        <el-form-item label="小区"
                      prop="communityChildId">
          <el-select v-model="form.communityChildId"
                     filterable
                     :disabled="form.id && form.id.length>0"
                     clearable
                     placeholder="请选择小区">
            <el-option v-for="dict in communityChildOptions2"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="任务名称"
                      prop="name">
          <el-input v-model="form.name"
                    placeholder="任务名称不能重复" />
        </el-form-item>
        <el-form-item label="任务类型"
                      prop="rosterType">
          <el-select v-model="form.rosterType"
                     placeholder="请选择任务类型"
                     :disabled="form.suspectId && form.suspectId.length > 0"
                     @change="taskTypeChange"
                     clearable
                     size="small">
            <el-option v-for="dict in RosterTypeList"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="布控开始时间"
                      prop="startDate">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.startDate"
                          type="date"
                          placeholder="选择布控开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="布控结束时间"
                      prop="endDate">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.endDate"
                          type="date"
                          placeholder="选择布控结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item v-if="!form.thresholds"
                      label="相似度"
                      prop="confidenceThreshold">
          <el-slider v-model="form.confidenceThreshold"
                     :min="0"
                     :max="100"
                     show-input
                     :show-input-controls="false"
                     input-size="mini">
          </el-slider>
        </el-form-item>
        <el-form-item v-if="form.thresholds"
                      label="相似度"
                      prop="thresholds.threshold.thresholdValue">
          <el-slider v-model="form.thresholds.threshold.thresholdValue"
                     :min="0"
                     :max="100"
                     show-input
                     :show-input-controls="false"
                     input-size="mini">
          </el-slider>
        </el-form-item>
        <!-- <el-form-item>
          <el-switch v-model="form.hitType"
                     active-value="1"
                     inactive-value="2"
                     active-text="命中告警"
                     inactive-text="不命中告警">
          </el-switch>
        </el-form-item> -->

        <el-form-item v-if="form.rosterType === '01' || form.rosterType === '02'"
                      label="布控名单"
                      prop="selectTaskGroup">
          <div class="radius">
            <el-transfer id="transfer1"
                         v-model="selectTaskGroup"
                         style="width:628px;text-align: left; display: inline-block"
                         :props="GroupProps"
                         :data="TaskGroupList"
                         :titles="['全部', '选择']" />
          </div>
        </el-form-item>
        <el-form-item label="布控卡组"
                      prop="selectTaskBayonet">
          <div class="radius">
            <el-transfer id="transfer2"
                         v-model="selectTaskBayonet"
                         style="width:628px;"
                         :props="BayonetProps"
                         :data="TaskBayonetList"
                         :titles="['全部', '选择']" />
          </div>
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
        <!-- <el-button @click="otherSubmitForm"
                   v-if="form.id && form.id.length>0">另存一条</el-button> -->
        <el-button type="primary"
                   @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="布控详细信息"
               :visible.sync="monitorInfoOpen"
               width="60%"
               append-to-body>
      <GrandsonMonitorTaskResultInfoShow :model="showInfoModel"
                                         :FaceGroupsList="FaceRepositoriesList"
                                         :VehicleGroupsList="VehicleBlackGroupList" />
    </el-dialog>
  </div>
</template>

<script>
import { findList, updateByID, deleteByIdList, add, addBayonet, deleteBayonet, enableChange } from '@/api/pvMonitor/SuspectTaskManagement'
import { GetFaceRepositoriesList, GetVehicleRepositoriesList } from '@/api/pvMonitor/IntelligentAnalysis'
import { findBayonetList } from '@/api/pvMonitor/BayonetManagement'
import { listCommunity } from '@/api/communityUnit/community'
import { listCommunity_child } from '@/api/communityUnit/community_child'
import { getTaskList, addTask, editTask, delTask, updateEnable } from '@/api/pvMonitor/task'
import GrandsonMonitorTaskResultInfoShow from './GrandsonMonitorTaskResultInfoShow'
export default {
  // name: "Community",
  components: { GrandsonMonitorTaskResultInfoShow },
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
      // 布控信息表格数据
      monitorList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 布控任务类型列表
      TaskTypeList: [],
      // 任务花名册类型
      RosterTypeList: [],
      // 查询参数
      queryParams: {
        communityId: '',
        communityChildId: '',
        rosterType: '',
        timeValue: [],
        pageNum: 1,
        pageSize: 10,
        // page: {
        //   no: '',
        //   pageSize: ''
        // },
        name: '',
        startCreateTime: '',
        endCreateTime: '',
        type: '',
        sortType: 'desc'
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        // delFlag: [
        //   { required: true, message: "删除标志不能为空", trigger: "blur" }
        // ],
      },
      GroupProps: {
        key: 'value',
        label: 'name'
      },
      BayonetProps: {
        key: 'sn',
        label: 'name'
      },
      // 社区
      communityOptions: [],
      // 小区列表
      communityChildOptions: [],
      // 小区列表
      communityChildOptions2: [],
      // 小区列表全集
      allCommunityChildOptions: [],
      // 人员布控名单
      FaceRepositoriesList: [],
      // 车辆布控名单
      VehicleBlackGroupList: [],
      // 布控卡组
      BayonetList: [],
      // 任务下已有布控名单
      selectTaskGroup: [],
      // 任务下已有布控卡组
      selectTaskBayonet: [],
      // 任务类型下所有布控名单列表
      TaskGroupList: [],
      // 任务类型下所有布控卡组列表
      TaskBayonetList: [],
      // 是否显示布控信息弹出层
      monitorInfoOpen: false,
      // 布控详情传给子组件
      showInfoModel: {},
      // 搜索表单控件高度
      formHeight: 0
    };
  },
  created () {
    this.getCommunityDictory()
    this.getAllCommunityChildDictory()
    this.getFaceRepositories()
    this.getVehicleBlackGroupList()
    this.getBayonetList()
    this.getDicts("base_monitoring_type").then(response => {
      this.TaskTypeList = response.data;
    });
    this.getDicts("base_roster_type").then(response => {
      this.RosterTypeList = response.data;
    });
  },
  mounted () {
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight
      this.getList();
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
    // 获取社区列表
    getCommunityDictory () {
      this.communityOptions = []
      const param = {
        pageNum: 1,
        pageSize: 100000
      }
      listCommunity(param).then(response => {
        this.communityOptions = response.rows
      })
    },
    // 获取小区列表
    getCommunityChildDictory (communityId) {
      this.communityChildOptions = []
      const param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId
      }
      listCommunity_child(param).then(response => {
        this.communityChildOptions = response.rows
      })
    },
    getCommunityChildDictory2 (communityId) {
      this.communityChildOptions2 = []
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
    // 任务类型字典翻译
    taskTypeFormat (row, column, cellValue) {
      return this.selectDictLabel(this.RosterTypeList, cellValue);
    },
    // 布控名单翻译
    getMonitorGroup (row, column, value) {
      let result = ''
      if (value && value.length > 0) {
        let aList = value.split(',')
        aList.forEach(item => {
          let itemList = item.split(':')
          result += (itemList[1] + '，')
        })
        if (result.lastIndexOf('，') === (result.length - 1)) {
          result = result.substring(0, result.length - 1)
        }
      }
      return result
    },
    // 布控卡组翻译
    getMonitorBayonetList (row, column, value) {
      let result = ''
      if (value && value.length > 0) {
        let bList = value.split(',')
        bList.forEach(item => {
          let itemList = item.split(':')
          result += itemList[1] + '，'
        })
        if (result.lastIndexOf('，') === (result.length - 1)) {
          result = result.substring(0, result.length - 1)
        }
        // if (result.lastIndexOf('，') === (result.length - 1)) {
        //   result = result.substring(0, result.length - 1) + '；'
        // }
        // if (result.lastIndexOf('；') === (result.length - 1)) {
        //   result = result.substring(0, result.length - 1)
        // }
      }
      return result
    },
    // 任务状态翻译
    getMonitorTaskEnableFlag (row, column, cellValue, index) {
      let enableMsg = cellValue === 'true' ? '启用' : '未启用'
      return enableMsg
    },
    /** 查询布控信息列表 */
    getList () {
      this.loading = true;
      // this.queryParams.page.no = this.queryParams.pageNum
      // this.queryParams.page.pageSize = this.queryParams.pageSize
      if (this.queryParams.timeValue !== null && this.queryParams.timeValue.length > 0) {
        this.queryParams.startCreateTime = new Date(this.queryParams.timeValue[0]).getTime()
        this.queryParams.endCreateTime = new Date(this.queryParams.timeValue[1]).getTime()
      } else {
        this.queryParams.startCreateTime = ''
        this.queryParams.endCreateTime = ''
      }

      getTaskList(this.queryParams).then(response => {

        this.monitorList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 获取人员名单库列表
    getFaceRepositories () {
      let param = {
        no: 1,
        size: 900,
        sort: 'asc',
        ordername: 'name'
      }
      let self = this
      GetFaceRepositoriesList(param).then(data => {
        if (data.data != null && data.data.length > 0) {
          self.FaceRepositoriesList = data.data
        }
      })
    },
    // 获取车辆黑名单列表
    getVehicleBlackGroupList () {
      let param = {
        page: {
          no: 1,
          pageSize: 900,
          sort: 'asc',
          orderName: 'time'
        }
      }
      let self = this
      GetVehicleRepositoriesList(param).then(data => {
        if (data.data != null && data.data.length > 0) {
          self.VehicleBlackGroupList = data.data
        }
      })
    },
    // 获取设备卡组列表
    getBayonetList () {
      let param = {
        page: {
          no: 1,
          pageSize: 1000
        }
      }
      let self = this
      findBayonetList(param).then(data => {
        if (data != null && data.status === '200') {
          self.BayonetList = data.data
        }
      })
    },
    // 取消按钮
    cancel () {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset () {
      this.form = {
        communityId: '',
        communityChildId: '',
        bayonetNames: '',
        groupNames: '',
        timeList: '',
        suspectId: '',
        endDate: '',
        confidenceThreshold: 80,
        domains: '',
        groupList: '',
        type: '',
        hitType: '',
        createTime: '',
        enable: '',
        name: '',
        alarmLevel: '',
        createUser: '',
        srcDomainCode: '',
        startDate: '',
        thresholds: {
          threshold: {
            thresholdValue: ''
          }
        }
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
      this.ids = selection.map(item => item.suspectId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd () {

      this.reset();
      var now = new Date()
      var afterNow = new Date(now)
      afterNow.setDate(now.getDate() + 30)
      this.form = {
        // suspectId: null,
        name: '',
        startDate: now.getFullYear() + '-' + this.commonFunction.padLeft(now.getMonth() + 1, 2, '0') + '-' + this.commonFunction.padLeft(now.getDate(), 2, '0'),
        endDate: afterNow.getFullYear() + '-' + this.commonFunction.padLeft(afterNow.getMonth() + 1, 2, '0') + '-' + this.commonFunction.padLeft(afterNow.getDate(), 2, '0'),
        // hitType: '1',
        confidenceThreshold: 80,
        // type: '1',
        enable: 'true',
        alarmLevel: 1,
        srcDomainCode: ''
      }
      this.taskTypeChange(this.form.rosterType)
      this.open = true;
      this.title = "添加布控信息";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {

      this.reset();
      const id = row.suspectId || this.ids
      let entity = {
        // page: {
        //   no: 1,
        //   pageSize: 1
        // },
        suspectId: id
      }
      let self = this
      getTaskList(entity).then(response => {

        self.form = response.rows[0];
        self.getCommunityChildDictory2(self.form.communityId)
        if (self.form.thresholds) {
          self.form.thresholds.threshold.thresholdValue = parseFloat(self.form.thresholds.threshold.thresholdValue)
        } else {
          self.form.confidenceThreshold = parseFloat(self.form.confidenceThreshold)
        }
        self.taskTypeChange(self.form.rosterType)
        //设置选中的项
        if (self.form.selectGroups && self.form.selectGroups !== null && self.form.selectGroups.length > 0) {
          self.selectTaskGroup = self.form.selectGroups.split(',')
        }
        if (self.form.selectBayonets && self.form.selectBayonets !== null && self.form.selectBayonets.length > 0) {
          self.selectTaskBayonet = self.form.selectBayonets.split(',')
        }

        self.open = true;
        self.title = "修改社区信息";
      })
    },
    // 任务类型选择回调
    taskTypeChange (taskType) {

      this.selectTaskGroup = []
      this.selectTaskBayonet = []
      if (!(this.VehicleBlackGroupList != null && this.VehicleBlackGroupList.length > 0))
        this.getVehicleBlackGroupList()
      if (!(this.FaceRepositoriesList != null && this.FaceRepositoriesList.length > 0))
        this.getFaceRepositories()
      if (!(this.BayonetList != null && this.BayonetList.length > 0))
        this.getBayonetList()
      if (taskType === '01') {//车辆黑名单布控(命中)

        this.form.type = '1'
        this.form.hitType = '1'
        this.GroupProps = {
          key: 'groupid',
          label: 'name'
        }
        this.TaskGroupList = this.VehicleBlackGroupList
        if (this.BayonetList != null && this.BayonetList.length > 0)
          this.TaskBayonetList = this.BayonetList.filter((item) => { return item.type === '1' })
      } else if (taskType === '02') {//2：人脸库黑名单（命中）

        this.form.type = '2'
        this.form.hitType = '1'
        this.GroupProps = {
          key: 'id',
          label: 'name'
        }
        if (this.FaceRepositoriesList != null && this.FaceRepositoriesList.length > 0)
          this.TaskGroupList = this.FaceRepositoriesList.filter((item) => { return item.type === '2' })
        if (this.BayonetList != null && this.BayonetList.length > 0)
          this.TaskBayonetList = this.BayonetList.filter((item) => { return item.type === '2' })
      } else if (taskType === '03') {//3：通行任务（人脸库白名单命中）
        this.form.type = '3'
        this.form.hitType = '1'
        this.GroupProps = {
          key: 'id',
          label: 'name'
        }
        if (this.FaceRepositoriesList != null && this.FaceRepositoriesList.length > 0)
          this.TaskGroupList = this.FaceRepositoriesList.filter((item) => { return item.type === '3' })
        if (this.BayonetList != null && this.BayonetList.length > 0)
          this.TaskBayonetList = this.BayonetList.filter((item) => { return item.type === '2' })
      } else if (taskType === '04') {// 陌生人任务（人脸库白名单非命中）
        this.form.type = '3'
        this.form.hitType = '2'
        this.GroupProps = {
          key: 'id',
          label: 'name'
        }
        if (this.FaceRepositoriesList != null && this.FaceRepositoriesList.length > 0)
          this.TaskGroupList = this.FaceRepositoriesList.filter((item) => { return item.type === '3' })
        if (this.BayonetList != null && this.BayonetList.length > 0)
          this.TaskBayonetList = this.BayonetList.filter((item) => { return item.type === '2' })
      }
    },
    // 启用/未启用任务
    EnableTask (rowData) {

      let enableFlag = rowData.enable === 'false' ? 'true' : 'false'
      let msg = rowData.enable === 'false' ? '布控任务启用' : '布控任务停用'
      let param = {
        enable: enableFlag,
        suspectList: {
          suspectId: [rowData.suspectId]
        }
      }
      let self = this
      updateEnable(param).then(data => {
        if (data !== null && data.code === 200) {
          self.msgSuccess(msg + '成功')
          self.getList()
        }
        else {
          self.msgError(msg + '失败')
        }
      })
    },
    /** 提交按钮 */
    submitForm () {
      let self = this
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (self.form.suspectId && self.form.suspectId != null && self.form.suspectId.length > 1) {
            //修改
            self.GetEditTaskData()
            editTask(this.form).then(response => {
              if (response.code === 200) {
                self.msgSuccess("修改成功");
                self.open = false;
                self.getList();
              } else {
                this.msgError("修改失败");
              }
            });
          } else {
            // 新增
            self.GetEditTaskData()
            addTask(this.form).then(response => {
              if (response.code === 200) {
                self.msgSuccess("新增成功");
                self.open = false;
                self.getList();
              } else {
                this.msgError("新增失败");
              }
            });
          }
        }
      });
    },
    GetEditTaskData () {

      this.form.alarmLevel = '1'
      this.form.srcDomainCode = ""
      this.form.plateSimilar = "false"
      this.form.confidenceThreshold = this.form.confidenceThreshold + ''
      this.form.timeList = {
        time: [
          {
            "startTime": "00:00:00",
            "endTime": "23:59:59",
            "day": "1"
          },
          {
            "startTime": "00:00:00",
            "endTime": "23:59:59",
            "day": "2"
          },
          {
            "startTime": "00:00:00",
            "endTime": "23:59:59",
            "day": "3"
          },
          {
            "startTime": "00:00:00",
            "endTime": "23:59:59",
            "day": "4"
          },
          {
            "startTime": "00:00:00",
            "endTime": "23:59:59",
            "day": "5"
          },
          {
            "startTime": "00:00:00",
            "endTime": "23:59:59",
            "day": "6"
          },
          {
            "startTime": "00:00:00",
            "endTime": "23:59:59",
            "day": "0"
          }
        ]
      }
      if (this.selectTaskGroup != null && this.selectTaskGroup.length > 0) {
        this.form.groupList = {
          groupId: this.selectTaskGroup
        }

        let gStr = ''
        this.selectTaskGroup.forEach(sGItemId => {
          this.TaskGroupList.filter(aGItem => {
            if (this.form.rosterType === '01') { // 不同类型布控字段不一样
              if (aGItem.groupid === sGItemId) {
                gStr += sGItemId + ':' + aGItem.name + ','
              }
            } else {
              if (aGItem.id === sGItemId) {
                gStr += sGItemId + ':' + aGItem.name + ','
              }
            }
          })
        })
        if (gStr.lastIndexOf(',') === (gStr.length - 1)) {
          gStr = gStr.substring(0, gStr.length - 1)
        }
        this.form.groupNames = gStr
      }
      if (this.selectTaskBayonet != null && this.selectTaskBayonet.length > 0) {
        this.form.domains = {
          domain: [{
            dstDomainCode: '',
            bayonetList: [{
              bayonetSn: this.selectTaskBayonet
            }]
          }]
        }

        let bStr = ''
        this.selectTaskBayonet.forEach(sBItemId => {
          this.TaskBayonetList.filter(aBItem => {
            if (aBItem.sn === sBItemId) {
              bStr += sBItemId + ':' + aBItem.name + ','
            }
          })
        })
        if (bStr.lastIndexOf(',') === (bStr.length - 1)) {
          bStr = bStr.substring(0, bStr.length - 1)
        }
        this.form.bayonetNames = bStr
      }
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      const ids = row.suspectId || this.ids;
      let idsList = {
        suspectList: {
          suspectId: ids
        }
      }

      this.$confirm('是否确认删除布控信息编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delTask(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () { this.msgError("删除失败") });
    },
    ShowTask (rowData) {

      let infoData = rowData
      infoData.groupList = []
      if (infoData.groupNames != null && infoData.groupNames.length > 0) {
        let aList = infoData.groupNames.split(',')
        aList.forEach(aItem => {
          let aL = aItem.split(':')
          let aInfo = {
            id: aL[0],
            name: aL[1]
          }
          infoData.groupList.push(aInfo)
        })
      }
      this.showInfoModel = infoData

      this.monitorInfoOpen = true
    },
    ShowTaskInfoClose () {
      this.monitorInfoOpen = false
    }
    /** 导出按钮操作 */
    // handleExport () {
    //   const queryParams = this.queryParams;
    //   this.$confirm('是否确认导出所有社区信息数据项?', "警告", {
    //     confirmButtonText: "确定",
    //     cancelButtonText: "取消",
    //     type: "warning"
    //   }).then(function () {
    //     return exportCommunity(queryParams);
    //   }).then(response => {
    //     this.download(response.msg);
    //   }).catch(function () { });
    // },
  },
};
</script>
<style lang="scss" scoped>
.distpicker-address-wrapper {
  text-align: left;
  margin-bottom: 20px;
  select {
    margin-right: 20px;
  }
}
</style>
