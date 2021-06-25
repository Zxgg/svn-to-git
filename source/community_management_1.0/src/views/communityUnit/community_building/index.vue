<template>
  <div class="app-container">
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
      <!-- <el-form-item label="小区编号"
                    prop="communityChildId">
        <el-input v-model="queryParams.communityChildId"
                  placeholder="请输入小区编号"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="小区名称"
                    prop="communityChildName">
        <el-input v-model="queryParams.communityChildName"
                  placeholder="请输入小区名称"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <el-form-item label="楼栋编号"
                    prop="serialNumber">
        <el-input v-model="queryParams.serialNumber"
                  placeholder="请输入楼栋编号"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="楼栋名称"
                    prop="name">
        <el-input v-model="queryParams.name"
                  placeholder="请输入楼栋名称"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="楼层数"
                    prop="floorNumber">
        <el-input v-model="queryParams.floorNumber"
                  placeholder="请输入楼层数"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="单元数"
                    prop="unitNumber">
        <el-input v-model="queryParams.unitNumber"
                  placeholder="请输入单元数"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="电梯"
                    prop="elevator">
        <el-select v-model="queryParams.elevator"
                   placeholder="请选择是否有电梯"
                   clearable
                   size="small">
          <el-option v-for="dict in elevatorOptions"
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
        <el-button icon="el-icon-refresh"
                   size="mini"
                   @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10"
            class="mb8">
      <el-col :span="1.5">
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAdd"
                   v-hasPermi="['communityUnit:community_building:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['communityUnit:community_building:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityUnit:community_building:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['communityUnit:community_building:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading"
              :data="community_buildingList"
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
                       width="auto"
                       prop="communityChildName" />
      <el-table-column label="楼栋名称"
                       align="center"
                       width="80"
                       prop="name" />
      <el-table-column label="楼栋编号"
                       width="80"
                       align="center"
                       prop="serialNumber" />
      <el-table-column label="楼层数"
                       align="center"
                       width="80"
                       prop="floorNumber" />
      <el-table-column label="单元数"
                       align="center"
                       width="80"
                       prop="unitNumber" />
      <el-table-column label="是否有电梯"
                       align="center"
                       width="100"
                       prop="elevator"
                       :formatter="elevatorFormat" />
      <el-table-column label="备注"
                       align="center"
                       prop="remark" />
      <el-table-column label="操作"
                       align="center"
                       width="200"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="showHouseList(scope.row)"
                     v-hasPermi="['communityUnit:community_houses:query']">房屋列表</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['communityUnit:community_building:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['communityUnit:community_building:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />
    <!-- 添加或修改社区楼栋对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="500px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="80px">
        <!-- <el-form-item label="社区ID"
                      prop="communityId">
          <el-input v-model="form.communityId"
                    placeholder="请输入社区ID"
                    readonly />
        </el-form-item> -->
        <!-- 选择社区 -->
        <el-form-item label="社区名称"
                      prop="communityId">
          <el-select v-model="form.communityId"
                     filterable
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
            <el-option v-for="dict in communityChildOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <!--         
        <el-form-item label="小区编号"
                      prop="communityChildId">
          <el-input v-model="form.communityChildId"
                    placeholder="请输入小区编号" />
        </el-form-item>
        <el-form-item label="小区名称"
                      prop="communityChildName">
          <el-input v-model="form.communityChildName"
                    placeholder="请输入小区名称" />
        </el-form-item> -->
        <el-form-item label="楼栋编号"
                      prop="serialNumber">
          <el-input v-model="form.serialNumber"
                    placeholder="请输入楼栋编号" />
        </el-form-item>
        <el-form-item label="楼栋名称"
                      prop="name">
          <el-input v-model="form.name"
                    placeholder="请输入内容" />
        </el-form-item>

        <el-form-item label="楼层数"
                      prop="floorNumber">
          <el-input v-model="form.floorNumber"
                    placeholder="请输入楼层数" />
        </el-form-item>
        <el-form-item label="单元数"
                      prop="unitNumber">
          <el-input v-model="form.unitNumber"
                    placeholder="请输入单元数" />
        </el-form-item>
        <el-form-item label="是否有电梯">
          <el-radio-group v-model="form.elevator">
            <el-radio v-for="dict in elevatorOptions"
                      :key="dict.dictValue"
                      :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="经度"
                      prop="longitud">
          <el-input v-model="form.longitud"
                    placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度"
                      prop="latitude">
          <el-input v-model="form.latitude"
                    placeholder="请输入纬度" />
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
    <el-dialog :title="houseListTitle"
               :visible.sync="houseListOpen"
               width="85%"
               :close-on-click-modal=false
               append-to-body>
      <div slot="footer"
           class="dialog-footer">
        <communityHouses :communityBuildingInfo="currentCommunityBuildingInfo" />
        <el-button @click="houseListCancel">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCommunity_building, getCommunity_building, delCommunity_building, addCommunity_building, updateCommunity_building, exportCommunity_building } from "@/api/communityUnit/community_building";
import { listCommunity_child, getCommunity_child, delCommunity_child, addCommunity_child, updateCommunity_child, exportCommunity_child } from "@/api/communityUnit/community_child";
import { listCommunity } from "@/api/communityUnit/community";
import communityHouses from '@/views/communityUnit/community_houses'
export default {
  name: "Community_building",
  components: { communityHouses },
  props: {
    communityInfo: {
      type: Object,
      default: null
    }
  },
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
      // 社区楼栋表格数据
      community_buildingList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //社区列表
      communityOptions: [],
      //小区列表
      communityChildOptions: [],
      //楼栋列表弹出层标题
      houseListTitle: '',
      //是否显示楼栋列表弹出层
      houseListOpen: false,
      //展示楼栋信息时的社区信息
      currentCommunityBuildingInfo: null,
      // 是否是多小区社区字典
      multipleOptions: [],
      // 是否有电梯(0否1是,默认0)字典
      elevatorOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        communityId: null,
        communityName: null,
        // multiple: "0",
        communityChildId: null,
        communityChildName: null,
        serialNumber: null,
        floorNumber: null,
        unitNumber: null,
        elevator: null,
        longitud: null,
        latitude: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        delFlag: [
          { required: true, message: "删除标志不能为空", trigger: "blur" }
        ],
        multiple: [
          { required: true, message: "是否是多小区社区不能为空", trigger: "blur" }
        ],
        floorNumber: [
          { required: true, message: "楼层数不能为空", trigger: "blur" }
        ],
        unitNumber: [
          { required: true, message: "单元数不能为空", trigger: "blur" }
        ],
        elevator: [
          { required: true, message: "是否有电梯不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created () {
    this.getCommunityDictory()
    this.getList();
    this.getDicts("base_yes_no").then(response => {
      this.multipleOptions = response.data;
      this.elevatorOptions = response.data;
    });
  },
  methods: {
    // 是否是多小区社区字典翻译
    multipleFormat (row, column) {
      return this.selectDictLabel(this.multipleOptions, row.multiple);
    },
    // 是否有电梯(0否1是,默认0)字典翻译
    elevatorFormat (row, column) {
      return this.selectDictLabel(this.elevatorOptions, row.elevator);
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
    /** 查询社区楼栋列表 */
    getList () {
      this.loading = true;
      listCommunity_building(this.queryParams).then(response => {
        this.community_buildingList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        name: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        remark: null,
        communityId: null,
        communityName: null,
        // multiple: null,
        communityChildId: null,
        communityChildName: null,
        serialNumber: null,
        floorNumber: null,
        unitNumber: null,
        longitud: null,
        latitude: null,
        elevator: null
      };
      this.resetForm("form");
      if (this.queryParams && this.queryParams.communityId && this.queryParams.communityId.length > 0) {
        this.form.communityId = this.queryParams.communityId
        this.form.communityName = this.queryParams.communityName
      }
      if (this.queryParams && this.queryParams.communityChildId && this.queryParams.communityChildId.length > 0) {
        this.form.communityChildId = this.queryParams.communityChildId
        this.form.communityChildName = this.queryParams.communityChildName
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
      this.title = "添加社区楼栋";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      // this.reset();
      const id = row.id || this.ids
      getCommunity_building(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改社区楼栋";
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
            updateCommunity_building(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addCommunity_building(this.form).then(response => {
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
      this.$confirm('是否确认删除社区楼栋编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delCommunity_building(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有社区楼栋数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportCommunity_building(queryParams);
      }).then(response => {
        this.download(response.msg);
      }).catch(function () { });
    },
    /** 展示房屋列表按钮操作 */
    showHouseList (row) {
      let temp = row
      temp.communityName = this.queryParams.communityName
      this.currentCommunityBuildingInfo = temp
      this.houseListTitle = '房屋列表'
      this.houseListOpen = true
    },
    /** 取消展示房屋列表按钮操作 */
    houseListCancel () {
      this.houseListOpen = false
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
    }
  },
  watch: {
    communityInfo: {
      handler: function (info) {
        if (info && info.id && info.id.length > 0) {//有效信息
          this.queryParams.communityId = info.communityId
          this.queryParams.communityChildId = info.id
          this.queryParams.communityChildName = info.name
          this.getCommunityChildDictory(this.queryParams.communityId)
          this.handleQuery()
          this.reset();
        }
      },
      deep: true,
      immediate: true//路由发生变化时界面还未重绘完成，刷新布局没有意义，当前已经在重绘时处理
    }
  },
};
</script>
