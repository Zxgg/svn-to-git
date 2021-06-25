<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch"
             label-width="68px">
      <el-form-item label="社区名称"
                    prop="name">
        <el-input v-model="queryParams.name"
                  placeholder="请输入社区名称"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="社区地址">
        <v-distpicker :province.sync="queryParams.addressProvincial"
                      :city.sync="queryParams.addressCitie"
                      :area.sync="queryParams.addressCountie"></v-distpicker>
      </el-form-item>
      <!-- <el-form-item label="社区地址"
                    prop="addressProvincial">
        
        <el-select v-model="queryParams.addressProvincial"
                   placeholder="请选择社区地址-省"
                   clearable
                   size="small">
          <el-option label="请选择字典生成"
                     value="" />
        </el-select>
      </el-form-item> -->
      <!-- <el-form-item label="社区地址-市"
                    prop="addressCitie">
        <el-input v-model="queryParams.addressCitie"
                  placeholder="请输入社区地址-市"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="社区地址-县"
                    prop="addressCountie">
        <el-input v-model="queryParams.addressCountie"
                  placeholder="请输入社区地址-县"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <el-form-item label="行政区划"
                    prop="code">
        <el-input v-model="queryParams.code"
                  placeholder="请输入行政区划"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="街道"
                    prop="untitled">
        <el-input v-model="queryParams.untitled"
                  placeholder="请输入街道"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
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
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['communityUnit:community:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading"
              :data="communityList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />

      <el-table-column label="社区名称"
                       align="center"
                       prop="name" />
      <el-table-column label="社区地址-省"
                       width="140"
                       align="center"
                       prop="addressProvincial" />
      <el-table-column label="社区地址-市"
                       width="140"
                       align="center"
                       prop="addressCitie" />
      <el-table-column label="社区地址-县"
                       width="140"
                       align="center"
                       prop="addressCountie" />
      <el-table-column label="行政区划"
                       width="140"
                       align="center"
                       prop="code" />
      <el-table-column label="街道"
                       align="center"
                       prop="untitled" />
      <el-table-column label="更新时间"
                       align="center"
                       prop="updateTime"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
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
                     @click="showBuildingList(scope.row)"
                     v-hasPermi="['communityUnit:community_building:query']">楼栋列表</el-button>
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

    <pagination v-show="total>0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改社区信息对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="500px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="80px">

        <el-form-item label="社区名称"
                      prop="name">
          <el-input v-model="form.name"
                    placeholder="请输入社区名称" />
        </el-form-item>
        <el-form-item label="社区地址">
          <v-distpicker :province.sync="form.addressProvincial"
                        :city.sync="form.addressCitie"
                        :area.sync="form.addressCountie"></v-distpicker>
        </el-form-item>
        <el-form-item label="行政区划"
                      prop="code">
          <el-input v-model="form.code"
                    placeholder="请输入行政区划" />
        </el-form-item>
        <el-form-item label="街道"
                      prop="untitled">
          <el-input v-model="form.untitled"
                    placeholder="请输入街道" />
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
  </div>
</template>

<script>
import { listCommunity, getCommunity, delCommunity, addCommunity, updateCommunity, exportCommunity } from "@/api/communityUnit/community";
import VDistpicker from '@/components/address_distpicke'
import communityBuilding from '@/views/communityUnit/community_building'
export default {
  name: "Community",
  components: { VDistpicker, communityBuilding },
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
      // 社区信息表格数据
      communityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
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
        addressProvincial: '江苏省',
        addressCitie: '扬州市',
        addressCountie: null,
        code: null,
        untitled: null,
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
      }
    };
  },
  created () {
    this.getList();
  },
  methods: {
    /** 查询社区信息列表 */
    getList () {
      this.loading = true;
      listCommunity(this.queryParams).then(response => {
        this.communityList = response.rows;
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
        code: null,
        untitled: null,
        longitud: null,
        latitude: null
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
      this.title = "添加社区信息";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const id = row.id || this.ids
      getCommunity(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改社区信息";
      });
    },
    /** 另存一条 提交按钮 */
    otherSubmitForm () {
      this.form.id = null
      this.submitForm()
    },
    /** 提交按钮 */
    submitForm () {
      let self = this
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (self.form.id != null) {
            updateCommunity(this.form).then(response => {
              if (response.code === 200) {
                self.msgSuccess("修改成功");
                self.open = false;
                self.getList();
              }
            });
          } else {
            addCommunity(this.form).then(response => {
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
    handleDelete (row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除社区信息编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delCommunity(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有社区信息数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportCommunity(queryParams);
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
    }
  },
  watch: {
  }
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
