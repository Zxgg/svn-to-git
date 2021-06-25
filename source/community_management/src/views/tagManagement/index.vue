<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch"
             label-width="68px">
      <el-form-item label="标签类型"
                    prop="dictType">
        <el-select v-model="queryParams.dictType"
                   size="small">
          <el-option v-for="item in typeOptions"
                     :key="item.dictId"
                     :label="item.dictName"
                     :value="item.dictType" />
        </el-select>
      </el-form-item>
      <el-form-item label="标签名称"
                    prop="dictLabel">
        <el-input v-model="queryParams.dictLabel"
                  placeholder="请输入标签名称"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="状态"
                    prop="status">
        <el-select v-model="queryParams.status"
                   placeholder="数据状态"
                   clearable
                   size="small">
          <el-option v-for="dict in statusOptions"
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
                   v-hasPermi="['system:dict:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['system:dict:remove']">删除</el-button>
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['system:dict:export']">导出</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="loading"
              :data="dataList"
              :height="dataHeight"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="标签编码"
                       align="center"
                       prop="dictCode" />
      <el-table-column label="标签名称"
                       align="center"
                       prop="dictLabel" />
      <!-- <el-table-column label="标签值"
                       align="center"
                       prop="dictValue" /> -->
      <el-table-column label="标签排序"
                       align="center"
                       width="85"
                       prop="dictSort" />
      <!-- <el-table-column label="状态"
                       align="center"
                       width="85"
                       prop="status"
                       :formatter="statusFormat" /> -->
      <el-table-column label="备注"
                       align="center"
                       prop="remark"
                       :show-overflow-tooltip="true" />
      <el-table-column label="创建时间"
                       align="center"
                       prop="createTime"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
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
                     v-hasPermi="['system:dict:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['system:dict:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="500px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="80px">
        <el-form-item label="标签类型">
          <!-- <el-input v-model="form.dictType"
                    :disabled="true" /> -->
          <el-select v-model="form.dictType"
                     size="small"
                     :disabled="true">
            <el-option v-for="item in typeOptions"
                       :key="item.dictId"
                       :label="item.dictName"
                       :value="item.dictType" />
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="标签类型">
          <el-input v-model="form.dictLabel"
                    :disabled="true" />
        </el-form-item> -->
        <el-form-item label="标签名称"
                      prop="dictLabel">
          <el-input id='dictLabelID'
                    ref='dictLabel'
                    v-model="form.dictLabel"
                    placeholder="请输入标签名称"
                    @keyup.enter.native="submitForm" />
        </el-form-item>
        <el-form-item label="标签键值"
                      prop="dictValue">
          <el-input v-model="form.dictValue"
                    placeholder="请输入标签键值"
                    @keyup.enter.native="submitForm" />
        </el-form-item>
        <el-form-item label="显示排序"
                      prop="dictSort">
          <el-input-number v-model="form.dictSort"
                           controls-position="right"
                           :min="0"
                           @keyup.enter.native="submitForm" />
        </el-form-item>
        <!-- <el-form-item label="状态"
                      prop="status">
          <el-radio-group v-model="form.status"
                          @keyup.enter.native="submitForm">
            <el-radio v-for="dict in statusOptions"
                      :key="dict.dictValue"
                      :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item> -->
        <el-form-item label="备注"
                      prop="remark">
          <el-input v-model="form.remark"
                    type="textarea"
                    placeholder="请输入内容"
                    @keyup.enter.native="submitForm"></el-input>
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
import { listData, getData, delData, addData, updateData, exportData } from "@/api/system/dict/data";
import { listType, getType } from "@/api/system/dict/type";

export default {
  name: "Data",
  data () {
    return {
      // 遮罩层
      loading: false,
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
      // 字典表格数据
      dataList: [],
      // 默认标签类型
      defaultDictType: "",
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 状态数据字典
      statusOptions: [],
      // 类型数据字典
      typeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        dictName: undefined,
        dictType: 'sys_base_custom_tag',
        status: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        dictLabel: [
          { required: true, message: "标签名称不能为空", trigger: "blur" }
        ],
        dictValue: [
          { required: true, message: "标签键值不能为空", trigger: "blur" }
        ],
        dictSort: [
          { required: true, message: "数据顺序不能为空", trigger: "blur" }
        ]
      },
      editTagKey: [
        'base_special_person',
        'base_type_of_caring',
        'sys_base_custom_tag'
      ],
      // 搜索表单控件高度
      formHeight: 0,
    };
  },
  created () {
    // const dictId = this.$route.params && this.$route.params.dictId;
    // this.getType(dictId);
    this.getTypeList();
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
  },
  mounted () {
    this.getList();
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight;
    });
  },
  computed: {
    dataHeight () {
      let searchFormHeight = 0;
      return this.$store.getters.dataEleHeight - searchFormHeight - 115 - 16;
    },
  },
  methods: {
    /** 查询标签类型详细 */
    getType (dictId) {
      getType(dictId).then(response => {
        this.queryParams.dictType = response.data.dictType;
        this.defaultDictType = response.data.dictType;
        this.getList();
      });
    },
    getLabelNameByType (type) {
      let resu = type
      if (this.typeOptions && this.typeOptions.length > 0) {
        for (let i = 0; i < this.typeOptions.length; i++) {
          if (this.typeOptions[i].dictType === type) {
            resu = this.typeOptions[i].dictName
          }
        }
      }
      return resu
    },
    /** 查询标签类型列表 */
    async getTypeList () {
      let self = this
      for (let index = 0; index < self.editTagKey.length; index++) {
        const dictKey = self.editTagKey[index];
        let param = {
          dictType: dictKey,
        }
        let dict = await listType(param)
        if (dict && dict.code === 200 && dict.rows && dict.rows.length > 0) {
          dict.rows.forEach(element => {
            if (dictKey === element.dictType) {
              self.typeOptions.push(dict.rows[0])
            }
          });
        }
      }
    },
    /** 查询字典数据列表 */
    getList () {
      this.loading = true;
      listData(this.queryParams).then(response => {
        this.dataList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 数据状态字典翻译
    statusFormat (row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 取消按钮
    cancel () {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset () {
      this.form = {
        dictCode: undefined,
        dictLabel: undefined,
        dictValue: undefined,
        dictSort: 0,
        status: "0",
        remark: undefined
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
      this.queryParams.dictType = this.defaultDictType;
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset();
      let self = this
      if (this.dataList && this.dataList.length > 0)
        this.form.dictSort = Math.max.apply(Math, this.dataList.map(item => { return item.dictSort })) + 1
      this.open = true;

      this.title = "添加标签";
      this.form.dictType = this.queryParams.dictType;
      this.$nextTick(() => {
        this.$refs.dictLabel.focus()
        // document.getElementById('dictLabelID').focus()
      })
    },
    // 多选框选中数据
    handleSelectionChange (selection) {
      this.ids = selection.map(item => item.dictCode)
      this.single = selection.length != 1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const dictCode = row.dictCode || this.ids
      getData(dictCode).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改字典数据";
      });
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.dictCode != undefined) {
            updateData(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addData(this.form).then(response => {
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
      const dictCodes = row.dictCode || this.ids;
      this.$confirm('是否确认删除标签编码为"' + dictCodes + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delData(dictCodes);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportData(queryParams);
      }).then(response => {
        this.download(response.msg);
      }).catch(function () { });
    },
    /** 返回上一页 */
    handleBack () {
      this.$router.go(-1);//返回上一层
    }
  }
};
</script>