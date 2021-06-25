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
      <el-form-item label="提醒类型"
                    prop="type">
        <el-select v-model="queryParams.type"
                   placeholder="请选择提醒类型"
                   clearable
                   size="small">
          <el-option v-for="dict in typeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="告警层级"
                    prop="alarmtype">
        <el-select v-model="queryParams.alarmtype"
                   placeholder="请选择告警层级"
                   clearable
                   size="small">
          <el-option v-for="dict in alarmtypeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="是否处理"
                    prop="processed">
        <el-select v-model="queryParams.processed"
                   placeholder="请选择是否处理"
                   clearable
                   size="small">
          <el-option v-for="dict in processedOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
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
                   v-hasPermi="['caringPerson:caring_event:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['caringPerson:caring_event:remove']">删除</el-button>
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
                   v-hasPermi="['caringPerson:caring_event:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['caringPerson:caring_event:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['caringPerson:caring_event:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['caringPerson:caring_event:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading"
              stripe
              :height="dataHeight"
              :data="caring_eventList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="社区"
                       align="center"
                       prop="communityId"
                       :formatter="communityNameFormat" />
      <el-table-column label="小区"
                       align="center"
                       prop="communityChildId"
                       :formatter="communityChildNameFormat" />
      <el-table-column label="名称"
                       align="center"
                       prop="name" />
      <el-table-column label="事件类型"
                       align="center"
                       prop="type"
                       :formatter="typeFormat" />
      <el-table-column label="关联人员"
                       align="center"
                       prop="personid"
                       :formatter="voFormat" />
      <el-table-column label="关联车辆"
                       align="center"
                       prop="vehicleid"
                       :formatter="voFormat" />
      <el-table-column label="告警层级"
                       align="center"
                       prop="alarmtype"
                       :formatter="alarmtypeFormat" />
      <el-table-column label="事件具体内容"
                       align="center"
                       prop="content" />
      <el-table-column label="是否处理"
                       align="center"
                       prop="processed"
                       :formatter="processedFormat" />
      <el-table-column label="处理情况"
                       align="center"
                       prop="processedSituation" />

      <el-table-column label="备注"
                       align="center"
                       prop="remark" />
      <el-table-column label="操作"
                       align="center"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['caringPerson:caring_event:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['caringPerson:caring_event:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改关爱人员事件对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="600px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="120px">
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
        <el-form-item label="名称"
                      prop="name">
          <el-input v-model="form.name"
                    placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="事件类型"
                      prop="type">
          <el-select v-model="form.type"
                     placeholder="请选择事件类型">
            <el-option v-for="dict in typeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="关联人员"
                      prop="targetPerson">
          <el-select v-model="targetPerson"
                     class="comboxLong"
                     filterable
                     multiple
                     remote
                     @change="selectRelaPersonChange"
                     :remote-method="remotePersonInfoMethod"
                     :loading="loading_remotePersonInfo"
                     placeholder="请选择人员姓名">
            <el-option-group v-for="group in personGroupOptions"
                             :key="group.label"
                             :label="group.label">
              <el-option v-for="dict in group.options"
                         :key="dict.id"
                         :label="dict.name"
                         :value="dict.id+','+dict.name">
                <span>{{ dict.name }}</span>
                <span v-if="dict.phoneNumber">{{' ' + dict.phoneNumber }}</span>
                <span v-if="dict.houseNumber">{{' ' +  dict.serialNumber+'号楼' + dict.unit+'单元' + dict.houseNumber }}</span>
              </el-option>
            </el-option-group>
          </el-select>
        </el-form-item>
        <el-form-item label="关联车辆"
                      prop="targetVehicle">
          <el-select v-model="targetVehicle"
                     class="comboxLong"
                     filterable
                     multiple
                     remote
                     @change="selectRelaVehicleChange"
                     :remote-method="remoteVehicleInfoMethod"
                     :loading="loading_remoteVehicleInfo"
                     placeholder="请选择车辆">
            <el-option-group v-for="group in vehicleGroupOptions"
                             :key="group.label"
                             :label="group.label">
              <el-option v-for="dict in group.options"
                         :key="dict.id"
                         :label="dict.plate"
                         :value="dict.id+','+dict.plate">
                <span>{{ dict.plate }}</span>
                <span v-if="dict.brand">{{' ' + dict.brand }}</span>
              </el-option>
            </el-option-group>
          </el-select>
        </el-form-item>
        <el-form-item label="告警层级"
                      prop="alarmtype">
          <el-select v-model="form.alarmtype"
                     placeholder="请选择告警层级">
            <el-option v-for="dict in alarmtypeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="parseInt(dict.dictValue)"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="事件具体内容"
                      prop="content">
          <el-input v-model="form.content"
                    type="textarea"
                    placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="是否处理"
                      prop="processed">
          <el-select v-model="form.processed"
                     placeholder="请选择是否处理">
            <el-option v-for="dict in processedOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="处理情况"
                      prop="processedSituation">
          <el-input v-model="form.processedSituation"
                    type="textarea"
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
        <el-button type="primary"
                   @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCaring_event, getCaring_event, delCaring_event, addCaring_event, updateCaring_event, exportCaring_event } from "@/api/caringPerson/caring_event";
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import { getInfoByName } from "@/api/communityUnit/personnel_information";
import { listVehicle_information } from "@/api/communityUnit/vehicle_information";

export default {
  name: "Caring_event",
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
      //新增的关联人员
      targetPerson: [],
      //新增的关联车辆
      targetVehicle: [],
      //远程人员搜索
      loading_remotePersonInfo: false,
      //远程人员搜索的返回结果
      remotePersonSearchResult: [],
      //远程人员车辆
      loading_remoteVehicleInfo: false,
      //远程人员搜索的返回结果
      remoteVehicleSearchResult: [],
      // 总条数
      total: 0,
      // 关爱人员事件表格数据
      caring_eventList: [],
      // 弹出层标题
      title: "",
      //删除提示
      deleteNotice: "",
      // 是否显示弹出层
      open: false,
      // 事件类型字典
      typeOptions: [],
      // 告警层级字典
      alarmtypeOptions: [],
      // 是否处理字典
      processedOptions: [],
      // 社区
      communityOptions: [],
      //小区列表
      communityChildOptions: [],
      //小区列表
      communityChildOptions2: [],
      //小区列表全集
      allCommunityChildOptions: [],
      //关联人员使用的搜索结果列表
      personGroupOptions: [
        {
          label: '已选',
          options: []
        },
        {
          label: '待选',
          options: []
        }],
      //关联车辆使用的搜索结果列表
      vehicleGroupOptions: [
        {
          label: '已选',
          options: []
        },
        {
          label: '待选',
          options: []
        }],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        remark: null,
        name: null,
        type: null,
        personid: null,
        vehicleid: null,
        alarmtype: null,
        content: null,
        processed: null,
        processedSituation: null,
        communityId: null,
        communityChildId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        communityId: [
          { required: true, message: "社区名称不能为空", trigger: "change" }
        ],
        communityChildId: [
          { required: true, message: "小区名称不能为空", trigger: "change" }
        ],
        name: [
          { required: true, message: "事件名称不能为空", trigger: "change" }
        ],
        type: [
          { required: true, message: "事件类型不能为空", trigger: "change" }
        ],
        // targetPerson: [
        //   { required: true, message: "关联人员不能为空", trigger: "change" }
        // ],
        alarmtype: [
          { required: true, message: "告警层级不能为空", trigger: "change" }
        ],
        processed: [
          { required: true, message: "是否处理不能为空", trigger: "change" }
        ],
      },
      // 搜索表单控件高度
      formHeight: 0
    };
  },
  created () {
    this.getAllCommunityChildDictory();
    this.getCommunityDictory()
    this.getDicts("base_caring_event").then(response => {
      this.typeOptions = response.data;
      console.log('typeOptions', this.typeOptions);
    });
    this.getDicts("base_alarm_level").then(response => {
      this.alarmtypeOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.processedOptions = response.data;
    });
  },
  mounted () {
    this.getList();
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
    remotePersonInfoMethod (name) {
      this.loading_remotePersonInfo = true
      let param = {
        name: name,
        communityId: null,
        communityChildId: null
      }
      if (this.form.communityId) {
        param.communityId = this.form.communityId
      }
      if (this.form.communityChildId) {
        param.communityChildId = this.form.communityChildId
      }
      getInfoByName(param).then(response => {
        this.remotePersonSearchResult = response.rows
        //去除已经选中的
        if (this.personGroupOptions[0].options && this.personGroupOptions[0].options.length > 0) {
          let filterResult = this.remotePersonSearchResult.filter(item => {
            let te = this.personGroupOptions[0].options.filter(selectItem => selectItem.id === item.id)
            if (te && te.length > 0)
              return false
            else return true
          })
          this.personGroupOptions[1].options = filterResult
        } else this.personGroupOptions[1].options = this.remotePersonSearchResult
        this.loading_remotePersonInfo = false
      })
    },
    selectRelaPersonChange (event) {
      if (!(this.targetPerson && this.targetPerson.length > 0)) {
        this.personGroupOptions[0].options = []
        return true
      }
      let items = this.getSelectPersonItemList(this.targetPerson)
      if (!(items && items.length > 0)) {
        this.personGroupOptions[0].options = []
        return true
      }
      this.personGroupOptions[0].options = items
      //将选中的结果从结果中过滤
      let filterResult = this.remotePersonSearchResult.filter(item => {
        let te = this.personGroupOptions[0].options.filter(selectItem => selectItem.id === item.id)
        if (te && te.length > 0)
          return false
        else return true
      })
      this.personGroupOptions[1].options = filterResult

    },
    getSelectPersonItemList (itemList) {
      let result = []
      itemList.forEach(element => {
        let item = this.$cf.getListFromString(element)
        if (item && item.length === 2) {
          result.push({
            id: item[0],
            name: item[1]
          })
        }
      });
      return result
    },
    remoteVehicleInfoMethod (name) {
      this.loading_remoteVehicleInfo = true
      let param = {
        name: name,
        communityId: null,
        communityChildId: null
      }
      if (this.form.communityId) {
        param.communityId = this.form.communityId
      }
      if (this.form.communityChildId) {
        param.communityChildId = this.form.communityChildId
      }
      // let items = this.getSelectPersonItemList(this.targetVehicle)
      // this.vehicleGroupOptions[0].options = items
      listVehicle_information(param).then(response => {
        this.remoteVehicleSearchResult = response.rows
        //去除已经选中的
        if (this.vehicleGroupOptions[0].options && this.vehicleGroupOptions[0].options.length > 0) {
          let filterResult = this.remoteVehicleSearchResult.filter(item => {
            let te = this.vehicleGroupOptions[0].options.filter(selectItem => selectItem.id === item.id)
            if (te && te.length > 0)
              return false
            else return true
          })
          this.vehicleGroupOptions[1].options = filterResult
        } else this.vehicleGroupOptions[1].options = this.remoteVehicleSearchResult
        this.loading_remoteVehicleInfo = false
      })
    },
    selectRelaVehicleChange (event) {
      if (!(this.targetVehicle && this.targetVehicle.length > 0)) {
        this.vehicleGroupOptions[0].options = []
        return true
      }
      let items = this.getSelectVehicleItemList(this.targetVehicle)
      if (!(items && items.length > 0)) {
        this.vehicleGroupOptions[0].options = []
        return true
      }
      this.vehicleGroupOptions[0].options = items
      //将选中的结果从结果中过滤
      let filterResult = this.remoteVehicleSearchResult.filter(item => {
        let te = this.vehicleGroupOptions[0].options.filter(selectItem => selectItem.id === item.id)
        if (te && te.length > 0)
          return false
        else return true
      })
      this.vehicleGroupOptions[1].options = filterResult

    },
    getSelectVehicleItemList (itemList) {
      let result = []
      itemList.forEach(element => {
        let item = this.$cf.getListFromString(element)
        if (item && item.length === 2) {
          result.push({
            id: item[0],
            plate: item[1]
          })
        }
      });
      return result
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
    /** 查询关爱人员事件列表 */
    getList () {
      this.loading = true;
      listCaring_event(this.queryParams).then(response => {
        this.caring_eventList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 事件类型字典翻译
    typeFormat (row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
    },
    // 告警层级字典翻译
    alarmtypeFormat (row, column) {
      return this.selectDictLabel(this.alarmtypeOptions, row.alarmtype);
    },
    // 是否处理字典翻译
    processedFormat (row, column) {
      return this.selectDictLabel(this.processedOptions, row.processed);
    },
    voFormat (row, column, cell) {
      if (!(cell && cell.length > 0)) return ''
      let targetList = this.$cf.getListFromString(cell, ';')
      let result = ''
      targetList.forEach(element => {
        if (element && element.length > 0) {
          let item = this.$cf.getListFromString(element)
          result += item[1] + ','
        }
      });
      result = result.trim(',')
      return result
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
        name: null,
        type: null,
        personid: null,
        vehicleid: null,
        alarmtype: null,
        content: null,
        processed: null,
        processedSituation: null,
        communityId: null,
        communityChildId: null
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
      this.deleteNotice = selection.map(item => item.name);
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset();
      this.open = true;
      this.title = "添加关爱提醒";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const id = row.id || this.ids
      getCaring_event(id).then(response => {
        this.form = response.data;
        this.getCommunityChildDictory2(this.form.communityId)
        this.setFormVoData()
        this.open = true;
        this.title = "修改关爱提醒";
      });
    },
    /* 设置关联人员和关联车辆 */
    setFormVoData () {
      let personNode = []
      let vehicleNode = []
      let targetPerson = this.$cf.getListFromString(this.form.personid, ';')
      let targetVehicle = this.$cf.getListFromString(this.form.vehicleid, ';')
      this.targetPerson = targetPerson
      this.targetVehicle = targetVehicle
      targetPerson.forEach(element => {
        let item = this.$cf.getListFromString(element)
        personNode.push({
          id: item[0],
          name: item[1]
        })
      });
      targetVehicle.forEach(element => {
        let item = this.$cf.getListFromString(element)
        vehicleNode.push({
          id: item[0],
          plate: item[1]
        })
      });
      // this.personGroupOptions[0].options.push(personNode)
      // this.vehicleGroupOptions[0].options.push(vehicleNode)
      this.personGroupOptions = [
        {
          label: '已选',
          options: personNode
        },
        {
          label: '待选',
          options: []
        }]
      //关联车辆使用的搜索结果列表
      this.vehicleGroupOptions = [
        {
          label: '已选',
          options: vehicleNode
        },
        {
          label: '待选',
          options: []
        }]
    },
    /* 获取关联人员和关联车辆 */
    getFormVoData () {
      //获取关联人员
      if (this.targetPerson && this.targetPerson.length > 0) {
        this.form.personid = this.$cf.getStringFromList(this.targetPerson, ';')
      } else {
        this.form.personid = ''
      }
      //获取关联车辆
      if (this.targetVehicle && this.targetVehicle.length > 0) {
        this.form.vehicleid = this.$cf.getStringFromList(this.targetVehicle, ';')
      } else {
        this.form.vehicleid = ''
      }
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.getFormVoData()
          if (this.form.id != null) {
            updateCaring_event(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addCaring_event(this.form).then(response => {
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
      // const ids = row.id || this.ids;
      const deleteNotice = row.name || this.deleteNotice;
      this.$confirm('是否确认删除关爱事件名称为"' + deleteNotice + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delCaring_event(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有关爱提醒数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportCaring_event(queryParams);
      }).then(response => {
        this.download(response.msg);
      }).catch(function () { });
    }
  }
};
</script>
<style lang="scss" scoped>
@import "@/assets/styles/inputSty.scss";
.el-textarea__inner {
  width: 200px !important;
}
</style>

