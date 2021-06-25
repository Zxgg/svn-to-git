<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch"
             label-width="68px">
      <!-- <el-form-item label="文件名"
                    prop="name">
        <el-input v-model="queryParams.name"
                  placeholder="请输入文件名"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <el-form-item label="姓名"
                    prop="personName">
        <el-input v-model="queryParams.personName"
                  placeholder="请输入用户名称"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="身份证号"
                    prop="identificationNumber">
        <el-input v-model="queryParams.identificationNumber"
                  placeholder="请输入身份证号"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="文件类型"
                    prop="fileType">
        <el-select v-model="queryParams.fileType"
                   placeholder="请选择文件类型"
                   clearable
                   size="small">
          <el-option v-for="dict in fileTypeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="文件来源"
                    prop="fileSource">
        <el-input v-model="queryParams.fileSource"
                  placeholder="请输入文件来源"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
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
                   v-hasPermi="['imagesInformationLibrarys:ImagePerinforLibrary:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['imagesInformationLibrarys:ImagePerinforLibrary:remove']">删除</el-button>
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
                   v-hasPermi="['imagesInformationLibrarys:ImagePerinforLibrary:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['imagesInformationLibrarys:ImagePerinforLibrary:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['imagesInformationLibrarys:ImagePerinforLibrary:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['imagesInformationLibrarys:ImagePerinforLibrary:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading"
              :data="ImagePerinforLibraryList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="文件名"
                       width="140"
                       align="center"
                       prop="name" />
      <el-table-column label="姓名"
                       width="120"
                       align="center"
                       prop="personName" />
      <el-table-column label="身份证号"
                       width="180"
                       align="center"
                       prop="identificationNumber" />
      <el-table-column label="文件类型"
                       width="100"
                       align="center"
                       prop="fileType"
                       :formatter="fileTypeFormat" />
      <el-table-column label="文件大小(KB)"
                       width="120"
                       align="center"
                       prop="fileSize">
        <template slot-scope="scope">
          <span>{{ Math.round(scope.row.fileSize*100)/ 100  }}</span>
        </template>
      </el-table-column>
      <el-table-column label="文件来源"
                       align="center"
                       prop="fileSource" />
      <el-table-column label="创建人"
                       align="center"
                       width="80"
                       prop="createBy" />
      <el-table-column label="创建时间"
                       align="center"
                       prop="createTime"
                       width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注"
                       width="120"
                       align="center"
                       prop="remark" />
      <el-table-column label="文件预览"
                       align="center"
                       width="120"
                       prop="filePath">
        <template slot-scope="scope">
          <el-image v-if="scope.row.filePath && scope.row.filePath.length>0 && checkIsImage(scope.row)"
                    style="width: 100px; height: 80px"
                    :src="getImgUrl(scope.row.filePath)"
                    fit="fill"></el-image>
          <!-- scope.row.pics -->
          <div v-else>无图</div>
        </template>
      </el-table-column>
      <el-table-column label="文件索引"
                       align="center"
                       prop="filePath">
        <template slot-scope="scope">
          <el-tooltip class="item"
                      effect="dark"
                      :content="scope.row.filePath"
                      placement="top">
            <span>{{ textLimit(scope.row.filePath,8) }}</span>
          </el-tooltip>

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
                     v-hasPermi="['imagesInformationLibrarys:ImagePerinforLibrary:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['imagesInformationLibrarys:ImagePerinforLibrary:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改人员图像信息库对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="500px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="80px">
        <el-form-item label="文件">
          <uploadImage :upLoadUrl="fileUpLoadUrl"
                       :filesJson.sync="uploadImageJson"
                       :acceptType="acceptType" />
        </el-form-item>
        <el-form-item label="文件名"
                      prop="name">
          <el-input v-model="form.name"
                    placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="姓名"
                      prop="personName">
          <el-input v-model="form.personName"
                    placeholder="请输入用户名称" />
        </el-form-item>
        <el-form-item label="身份证号"
                      prop="identificationNumber">
          <el-input v-model="form.identificationNumber"
                    placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="文件类型"
                      prop="fileType">
          <el-select v-model="form.fileType"
                     placeholder="请选择文件类型">
            <el-option v-for="dict in fileTypeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="文件大小"
                      prop="fileSize">
          <el-input v-model="form.fileSize"
                    placeholder="请输入文件大小">
            <template slot="append">KB</template>
          </el-input>
        </el-form-item>
        <el-form-item label="文件来源"
                      prop="fileSource">
          <el-input v-model="form.fileSource"
                    placeholder="请输入文件来源" />
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
import { listImagePerinforLibrary, getImagePerinforLibrary, delImagePerinforLibrary, addImagePerinforLibrary, updateImagePerinforLibrary, exportImagePerinforLibrary } from "@/api/imagesInformationLibrarys/ImagePerinforLibrary";
import uploadImage from "@/components/upload/uploadImage"
import configInfo from '@/webconfig/configInfo'
export default {
  name: "ImagePerinforLibrary",
  components: { uploadImage },
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
      // 人员图像信息库表格数据
      ImagePerinforLibraryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 文件类型字典
      fileTypeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        personName: null,
        identificationNumber: null,
        deviceId: null,
        filePath: null,
        fileType: null,
        fileSize: null,
        fileSource: null,
        libsId: null,
        libsType: null,
      },
      //允许上传的文件类型
      acceptType: '.jpg,.jpeg,.png,.gif,.bmp,.mp4,.3gp,.avi,.JPG,.JPEG,.PBG,.GIF,.BMP,.MP4,.3GP,.AVI',
      //当前上传图片
      uploadImageJson: null,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        fileSize: [
          { required: true, message: "文件大小，单位KB不能为空", trigger: "blur" }
        ],
        delFlag: [
          { required: true, message: "删除标志不能为空", trigger: "blur" }
        ]
      },
      //图片上传接口地址
      fileUpLoadUrl: null,
      // 搜索表单控件高度
      formHeight: 0
    };
  },
  created () {
    this.getDicts("base_image_info_lib_file_type").then(response => {
      this.fileTypeOptions = response.data;
    });
  },
  mounted () {
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight
    })
    this.fileUpLoadUrl = configInfo.FileUpLoadUri
    this.getList();
  },
  methods: {
    getImgUrl (url) {
      return configInfo.imgEchoAddress + url
    },
    /** 查询人员图像信息库列表 */
    getList () {
      this.loading = true;
      listImagePerinforLibrary(this.queryParams).then(response => {
        this.ImagePerinforLibraryList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 文件类型字典翻译
    fileTypeFormat (row, column) {
      return this.selectDictLabel(this.fileTypeOptions, row.fileType);
    },
    //检查文件是不是图片
    checkIsImage (row) {
      // let type = this.fileTypeFormat(row, null)
      // if (type && type.length > 0) return true
      // else return false
      return true
    },
    // 取消按钮
    cancel () {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset () {
      this.uploadImageJson = null
      this.form = {
        id: null,
        name: null,
        personName: null,
        identificationNumber: null,
        deviceId: null,
        filePath: null,
        fileType: null,
        fileSize: null,
        fileSource: null,
        libsId: null,
        libsType: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
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
      this.title = "添加人员图像信息库";
    },
    setPicsInfo (form) {
      if (form && form.filePath && form.filePath.length > 0) {
        let list = [{
          uid: '00',
          status: "success",
          name: form.name,
          url: form.filePath
        }]
        this.uploadImageJson = JSON.stringify(list);
      }
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const id = row.id || this.ids
      getImagePerinforLibrary(id).then(response => {
        this.form = response.data;
        this.setPicsInfo(this.form)
        this.open = true;
        this.title = "修改人员图像信息库";
      });
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateImagePerinforLibrary(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addImagePerinforLibrary(this.form).then(response => {
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
      this.$confirm('是否确认删除人员图像信息库编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delImagePerinforLibrary(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有人员图像信息库数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportImagePerinforLibrary(queryParams);
      }).then(response => {
        this.download(response.msg);
      }).catch(function () { });
    }
  },
  watch: {
    uploadImageJson: {
      handler: function (newJson) {
        if (newJson && newJson.length > 0) {
          let file = JSON.parse(newJson)
          if (file && file.length > 0) {
            file = file[0]
            if (file && file.size && file.size > 0) {//去除修改页面时更新此属性的变化
              this.form.name = file.name
              this.form.filePath = file.url
              this.form.fileType = file.type
              if (file.size > 0) {
                this.form.fileSize = file.size / 1024
              }
              else {
                this.form.fileSize = 0
              }
            }
          }
        }
        else {
          this.form.name = null
          this.form.fileSize = 0
          this.form.filePath = null
          this.form.fileType = null
        }
      },
      immediate: true
    }
  },
};
</script>
