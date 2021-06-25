<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch"
             label-width="68px">
      <el-form-item label="名称"
                    prop="name">
        <el-input v-model="queryParams.name"
                  placeholder="请输入名称"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="身份证号码"
                    label-width="auto"
                    prop="identificationNumber">
        <el-input v-model="queryParams.identificationNumber"
                  placeholder="请输入身份证号码"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="手机号码"
                    label-width="auto"
                    prop="phoneNumber">
        <el-input v-model="queryParams.phoneNumber"
                  placeholder="请输入手机号码"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <!-- <el-form-item label="性别"
                    label-width="auto"
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
      </el-form-item> -->
      <el-form-item label="审核状态"
                    prop="checked">
        <el-select v-model="queryParams.checked"
                   placeholder="请选择是否已审核"
                   clearable
                   size="small">
          <el-option v-for="dict in checkedOptions"
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
                   v-hasPermi="['passManage:temp_visitor_personnel:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['passManage:temp_visitor_personnel:remove']">删除</el-button>
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
                   v-hasPermi="['passManage:temp_visitor_personnel:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['passManage:temp_visitor_personnel:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['passManage:temp_visitor_personnel:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['passManage:temp_visitor_personnel:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading"
              stripe
              :height="dataHeight"
              :data="temp_visitor_personnelList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="名称"
                       align="center"
                       prop="name" />

      <el-table-column label="身份证号"
                       align="center"
                       prop="identificationNumber" />
      <el-table-column label="手机号码"
                       align="center"
                       prop="phoneNumber" />
      <el-table-column label="性别"
                       align="center"
                       prop="sex"
                       :formatter="sexFormat" />
      <el-table-column label="照片列表"
                       align="center"
                       prop="pics">
        <template slot-scope="scope">
          <el-image v-if="scope.row.pics && scope.row.pics.length > 0"
                    style="width: 100px; height: 80px"
                    :src="getImgUrl(scope.row.pics)"
                    fit="fill"></el-image>
          <!-- scope.row.pics -->
          <div v-else>无图</div>
        </template>
      </el-table-column>

      <el-table-column label="创建时间"
                       align="center"
                       prop="createTime"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="更新时间"
                       align="center"
                       prop="updateTime"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime) }}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="审核状态"
                       align="center"
                       width="auto"
                       prop="checked"
                       :formatter="checkedFormat">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     :disabled="scope.row.checked === '1'"
                     @click="handleUpdate_check(scope.row)"
                     v-hasPermi="['passManage:temp_visitor_personnel:edit']">{{ scope.row.checked | checkedFilters(that) }}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作"
                       align="center"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['passManage:temp_visitor_personnel:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['passManage:temp_visitor_personnel:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改临时访客人员信息对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="600px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="100px">
        <el-form-item label="名称"
                      prop="name">
          <el-input v-model="form.name"
                    placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="性别"
                      prop="sex">
          <el-select v-model="form.sex"
                     placeholder="请选择性别">
            <el-option v-for="dict in sexOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="身份证号码"
                      prop="identificationNumber">
          <el-input v-model="form.identificationNumber"
                    placeholder="请输入身份证号码" />
        </el-form-item>
        <el-form-item label="手机号码"
                      prop="phoneNumber">
          <el-input v-model="form.phoneNumber"
                    placeholder="请输入手机号码" />
        </el-form-item>

        <!-- <el-form-item label="特殊人员,(0否1是,默认0)"
                      prop="special">
          <el-select v-model="form.special"
                     placeholder="请选择特殊人员,(0否1是,默认0)">
            <el-option label="请选择字典生成"
                       value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="管控限制人员,(0否1是,默认0)"
                      prop="limited">
          <el-input v-model="form.limited"
                    placeholder="请输入管控限制人员,(0否1是,默认0)" />
        </el-form-item>
        <el-form-item label="关爱人员,(0否1是,默认0)"
                      prop="caring">
          <el-input v-model="form.caring"
                    placeholder="请输入关爱人员,(0否1是,默认0)" />
        </el-form-item> -->
        <el-form-item label="照片"
                      prop="pics">
          <!-- <el-input
            v-model="form.pics"
            type="textarea"
            placeholder="请输入内容"
          /> -->
          <imageListUpload :upLoadUrl="fileUpLoadUrl"
                           :key="form.id"
                           :uploadState='uploadState'
                           :filesJson.sync="form.pics" />
        </el-form-item>
        <el-form-item label="审核"
                      prop="checked">
          <el-select v-model="form.checked"
                     placeholder="请选择是否已经审核确认">
            <el-option v-for="dict in checkedOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button @click="otherSubmitForm"
                   v-if="form.id && form.id.length > 0">另存一条</el-button>
        <el-button type="primary"
                   @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listTemp_visitor_personnel,
  getTemp_visitor_personnel,
  delTemp_visitor_personnel,
  addTemp_visitor_personnel,
  updateTemp_visitor_personnel,
  exportTemp_visitor_personnel,
  updateTemp_visit_personnel_check,
} from "@/api/passManage/temp_visitor_personnel";
import imageListUpload from "@/components/upload/uploadImageList";
import configInfo from "@/webconfig/configInfo";
export default {
  name: "Temp_visitor_personnel",
  components: {
    imageListUpload,
  },
  data () {
    return {
      that: this,
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
      // 临时访客人员信息表格数据
      temp_visitor_personnelList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 性别字典
      sexOptions: [],
      // 是否已经审核确认,(0否1是,默认0)字典
      checkedOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        identificationNumber: null,
        phoneNumber: null,
        sex: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "访客姓名不能为空", trigger: "blur" },
        ],
        identificationNumber: [
          { required: true, message: "身份证号码不能为空", trigger: "blur" },
          {
            pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
            message: "你的身份证格式不正确",
          },
        ],
        phoneNumber: [
          { required: true, message: "手机号码不能为空", trigger: "blur" },
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur",
          },
        ],
        checked: [
          {
            required: true,
            message: "是否已经审核确认 不能为空",
            trigger: "change",
          },
        ],
      },
      //图片上传接口地址
      fileUpLoadUrl: null,
      // 搜索表单控件高度
      formHeight: 0,
      // 上传图片状态
      uploadState: true
    };
  },
  filters: {
    checkedFilters: function (value, self) {
      return self.checkedFormat(null, null, value);
    },
  },
  created () {
    this.getList();
    this.getDicts("sys_user_sex").then((response) => {
      this.sexOptions = response.data;
    });
    this.getDicts("base_checked_stat").then((response) => {
      this.checkedOptions = response.data;
    });
  },
  mounted () {
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight;
    });
    this.fileUpLoadUrl = configInfo.FileUpLoadUri;
  },
  computed: {
    dataHeight () {
      let searchFormHeight = 0;
      if (this.showSearch) searchFormHeight = this.formHeight;
      return this.$store.getters.dataEleHeight - searchFormHeight - 115;
    },
  },
  methods: {
    getImgUrl (url) {
      let urlList = this.$cf.returnJson(url)
      if (urlList.length > 0) {
        return configInfo.imgEchoAddress + urlList[0].url
      } else {
        return ''
      }
    },
    /** 查询临时访客人员信息列表 */
    getList () {
      this.loading = true;
      listTemp_visitor_personnel(this.queryParams).then((response) => {
        this.temp_visitor_personnelList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 性别字典翻译
    sexFormat (row, column) {
      return this.selectDictLabel(this.sexOptions, row.sex);
    },
    // 是否已经审核确认,(0否1是,默认0)字典翻译
    checkedFormat (row, column, cellValue) {
      return this.selectDictLabel(this.checkedOptions, cellValue);
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
        identificationNumber: null,
        phoneNumber: null,
        sex: null,
        special: null,
        limited: null,
        caring: null,
        pics: null,
        checked: "0",
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
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.uploadState = true
      this.reset();
      this.open = true;
      this.title = "添加临时访客人员信息";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.uploadState = false
      this.reset();
      const id = row.id || this.ids;
      getTemp_visitor_personnel(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改临时访客人员信息";
      });
    },
    /** 另存一条 提交按钮 */
    otherSubmitForm () {
      this.form.id = null;
      this.submitForm();
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateTemp_visitor_personnel(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addTemp_visitor_personnel(this.form).then((response) => {
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
      this.$confirm(
        '是否确认删除临时访客人员信息编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delTemp_visitor_personnel(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有临时访客人员信息数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportTemp_visitor_personnel(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () { });
    },
    /** 修改审核状态操作 */
    handleUpdate_check (row) {
      const name = row.name;
      let self = this;
      this.$confirm('是否确认同意"' + name + '"的拜访?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(function () {
        let param = {
          id: row.id,
          checked: "1",
          checkedTime: self.$cf.getNowTime(),
        };
        updateTemp_visit_personnel_check(param)
          .then((response) => {
            if (response.code === 200) {
              self.msgSuccess("修改成功");
              self.open = false;
              self.getList();
            }
          })
          .catch(function () { });
      });
    },
  },
};
</script>
