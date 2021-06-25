<template>
  <div class="app-container">
    <el-form ref="form"
             :model="form"
             :rules="rules"
             label-width="140px"
             :inline="true"
             size="medium"
             label-position="right">
      <el-form-item label="社区名称"
                    prop="communityId">
        <el-select v-model="queryParams.communityId"
                   filterable
                   clearable
                   :disabled="form.id && form.id.length>0 && form.checked && form.checked === '1'"
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
                   :disabled="form.id && form.id.length>0 && form.checked && form.checked === '1'"
                   placeholder="请选择小区">
          <el-option v-for="dict in communityChildOptions"
                     :key="dict.id"
                     :label="dict.name"
                     :value="dict.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="人员信息"
                    style="width:100%;"
                    prop="personnelId">
        <el-select style="width:900px;"
                   v-model="form.personnelId"
                   filterable
                   :disabled="form.id && form.id.length>0"
                   remote
                   :remote-method="remotePersonInfoMethod"
                   :loading="loading_remotePersonInfo"
                   placeholder="请选择人员姓名">
          <el-option v-for="dict in personOptions"
                     :key="dict.id"
                     :label="dict.name + '   tl:' + dict.phoneNumber + '     id:' + dict.identificationNumber"
                     :value="dict.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="人员ID"
                    prop="personnelId">
        <el-input v-model="form.personnelId"
                  placeholder="请输入人员ID" />
      </el-form-item>
      <el-form-item label="来源类型"
                    prop="sourceType">
        <el-select v-model="form.sourceType"
                   placeholder="请选择来源类型">
          <el-option v-for="dict in sourceTypeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否有效"
                    prop="enable">
        <el-select v-model="form.enable"
                   placeholder="请选择是否有效">
          <el-option v-for="dict in enableOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="初次发现时间"
                    prop="firstFindTime">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="form.firstFindTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择初次发现时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="管控情况"
                    prop="controllerSituation">
        <el-select v-model="form.controllerSituation"
                   placeholder="请选择管控情况">
          <el-option v-for="dict in controllerSituationOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="管控人姓名"
                    prop="controllerName">
        <el-input v-model="form.controllerName"
                  placeholder="请输入管控人姓名" />
      </el-form-item>
      <el-form-item label="管控人联系方式"
                    prop="controllerTel">
        <el-input v-model="form.controllerTel"
                  placeholder="请输入管控人联系方式" />
      </el-form-item>
      <el-form-item label="帮扶情况"
                    prop="helpSituation">
        <el-select multiple
                   clearable
                   v-model="form.helpSituation"
                   placeholder="请选择帮扶情况">
          <el-option v-for="dict in helpSituationOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="帮扶人姓名"
                    prop="helpName">
        <el-input v-model="form.helpName"
                  placeholder="请输入帮扶人姓名" />
      </el-form-item>
      <el-form-item label="帮扶人联系方式"
                    prop="helpPhone">
        <el-input v-model="form.helpPhone"
                  placeholder="请输入帮扶人联系方式" />
      </el-form-item>
      <el-form-item label="有无犯罪史"
                    prop="isCrimeHistory">
        <el-select v-model="form.isCrimeHistory"
                   placeholder="请选择有无犯罪史">
          <el-option v-for="dict in isCrimeHistoryOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="犯罪情况"
                    prop="crimeSituation">
        <el-input v-model="form.crimeSituation"
                  type="textarea"
                  placeholder="请输入内容" />
      </el-form-item>
      <el-form-item label="初次吸食日期"
                    prop="firstSuckDate">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="form.firstSuckDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择初次吸食日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="来源标注"
                    prop="sourceMark">
        <el-select v-model="form.sourceMark"
                   placeholder="请选择来源标注">
          <el-option label="请选择字典生成"
                     value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="吸食情况"
                    prop="vsituation">
        <el-input v-model="form.vsituation"
                  placeholder="请输入吸食情况" />
      </el-form-item>
      <el-form-item label="吸毒原因"
                    prop="drugCause">
        <el-select v-model="form.drugCause"
                   placeholder="请选择吸毒原因">
          <el-option v-for="dict in drugCauseOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="吸毒后果"
                    prop="drugResult">
        <el-select v-model="form.drugResult"
                   placeholder="请选择吸毒后果">
          <el-option label="请选择字典生成"
                     value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="备注"
                    prop="remark">
        <el-input v-model="form.remark"
                  type="textarea"
                  placeholder="请输入内容" />
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { listSpecial_drug_addicts, getSpecial_drug_addicts, delSpecial_drug_addicts, addSpecial_drug_addicts, updateSpecial_drug_addicts, exportSpecial_drug_addicts } from "@/api/special_personnel/special_drug_addicts";
import { getPersonnel_information, listPersonnel_information } from "@/api/communityUnit/personnel_information";
import { listCommunity } from "@/api/communityUnit/community";
import { getInfoByName } from "@/api/communityUnit/personnel_information";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import { updatePersonnel_specialType } from "@/api/communityUnit/personnel_information";
export default {
  name: "Special_drug_addicts",
  props: {
    personInfo: {
      type: Object,
      default: null
    }
  },
  data () {
    return {
      // 遮罩层
      loading: true,
      //获取人员信息
      loading_remotePersonInfo: false,
      // 远程搜索人员列表
      personOptions: [],
      // 总条数
      total: 0,
      // 吸毒人员信息 表格数据
      special_drug_addictsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 来源类型字典
      sourceTypeOptions: [],
      // 是否有效字典
      enableOptions: [],
      // 管控情况字典
      controllerSituationOptions: [],
      // 帮扶情况字典
      helpSituationOptions: [],
      // 有无犯罪史字典
      isCrimeHistoryOptions: [],
      // 吸毒原因字典
      drugCauseOptions: [],
      // 社区
      communityOptions: [],
      //小区列表
      communityChildOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        createBy: null,
        updateBy: null,
        personnelId: null,
        sourceType: null,
        enable: null,
        remark: null,
        firstFindTime: null,
        controllerSituation: null,
        controllerName: null,
        controllerTel: null,
        helpSituation: null,
        helpName: null,
        helpPhone: null,
        isCrimeHistory: null,
        crimeSituation: null,
        firstSuckDate: null,
        sourceMark: null,
        vsituation: null,
        drugCause: null,
        drugResult: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        personnelId: [
          { required: true, message: "人员ID不能为空", trigger: "blur" }
        ],
        enable: [
          { required: true, message: "是否有效不能为空", trigger: "change" }
        ],
        firstFindTime: [
          { required: true, message: "初次发现时间不能为空", trigger: "blur" }
        ],
        controllerSituation: [
          { required: true, message: "管控情况不能为空", trigger: "change" }
        ],
        controllerName: [
          { required: true, message: "管控人姓名不能为空", trigger: "blur" }
        ],
        controllerTel: [
          { required: true, message: "管控人联系方式不能为空", trigger: "blur" }
        ],
        helpName: [
          { required: true, message: "帮扶人姓名不能为空", trigger: "blur" }
        ],
        helpPhone: [
          { required: true, message: "帮扶人联系方式不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created () {
    this.getCommunityDictory()
    this.getDicts("base_special_person_data_source").then(response => {
      this.sourceTypeOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.enableOptions = response.data;
      this.isCrimeHistoryOptions = response.data;
    });
    this.getDicts("base_control_situation").then(response => {
      this.controllerSituationOptions = response.data;
    });
    this.getDicts("base_help_situation").then(response => {
      this.helpSituationOptions = response.data;
    });
    this.getDicts("base_reasons_for_taking_drugs").then(response => {
      this.drugCauseOptions = response.data;
    });
  },
  methods: {
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
    communityChildChange (communityChildId) {
      this.form.personnelId = null
    },
    /** 查询吸毒人员信息 列表 */
    getList () {
      this.loading = true;
      listSpecial_drug_addicts(this.queryParams).then(response => {
        this.special_drug_addictsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 表单重置
    reset () {
      this.form = {
        id: null,
        createBy: null,
        createTime: null,
        deptId: null,
        updateBy: null,
        updateDeptId: null,
        updateTime: null,
        delFlag: null,
        personnelId: null,
        sourceType: null,
        enable: null,
        remark: null,
        firstFindTime: null,
        controllerSituation: null,
        controllerName: null,
        controllerTel: null,
        helpSituation: null,
        helpName: null,
        helpPhone: null,
        isCrimeHistory: null,
        crimeSituation: null,
        firstSuckDate: null,
        sourceMark: null,
        vsituation: null,
        drugCause: null,
        drugResult: null
      };
      this.resetForm("form");
    },
    changeStringToList (form) {
      form.helpSituation = this.$cf.getListFromString(form.helpSituation)
    },
    changeListToString (form) {
      form.helpSituation = this.$cf.getStringFromList(form.helpSituation)
    },
    /** 提交按钮 */
    submitForm () {
      let self = this
      this.$refs["form"].validate(valid => {
        if (valid) {
          self.changeListToString(self.form)
          if (self.form.id != null) {
            updateSpecial_drug_addicts(self.form).then(response => {
              if (response.code === 200) {
                self.msgSuccess("修改成功");
                updatePersonnel_specialType(self.form.personnelId, '04')
                this.$Notice.$emit('special_personnel_submitForm', true)
              }
            });
          } else {
            addSpecial_drug_addicts(self.form).then(response => {
              if (response.code === 200) {
                self.msgSuccess("新增成功");
                updatePersonnel_specialType(self.form.personnelId, '04')
                this.$Notice.$emit('special_personnel_submitForm', true)
              }
            });
          }
        }
      });
    },
    /** 远程人员搜索*/
    remotePersonInfoMethod (name) {
      this.loading_remotePersonInfo = true
      let param = {
        communityId: null,
        communityChildId: null,
        name: name,
        id: null
      }
      if (this.queryParams.personnelId && this.queryParams.personnelId.length > 0) {
        param.id = this.queryParams.personnelId
      }
      if (this.queryParams.communityId) {
        param.communityId = this.queryParams.communityId
      }
      if (this.queryParams.communityChildId) {
        param.communityChildId = this.queryParams.communityChildId
      }
      getInfoByName(param).then(response => {
        this.personOptions = response.rows
        this.loading_remotePersonInfo = false
      })

    },
    /** 获取信息 */
    getInfo () {
      this.loading = true;
      let self = this
      listSpecial_drug_addicts(this.queryParams).then(response => {
        if (response.rows && response.rows.length > 0) {
          self.changeStringToList(response.rows[0])
          self.form = response.rows[0];
        } else {
          self.form.personnelId = self.queryParams.personnelId
        }
        self.total = response.total;
        self.loading = false;
      });
    },
    /** 删除按钮操作 */
    deleteInfo () {
      if (!(this.form.id && this.form.id.length > 0)) {
        this.msgError("失败！当前信息不存在")
        return false
      }
      const ids = this.form.id
      this.$confirm('是否确认删除吸毒人员信息 编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delSpecial_drug_addicts(ids);
      }).then(() => {
        this.msgSuccess("删除成功");
        this.$Notice.$emit('special_personnel_submitForm', true)
      }).catch(function () { });
    }
  },
  watch: {
    personInfo: {
      handler: function (newValue) {
        if (newValue && newValue.id && newValue.id.length > 0) {
          this.queryParams.personnelId = newValue.id
          this.personOptions = [newValue]
          this.reset()
          this.getInfo()
        } else {
          this.reset()
        }
      },
      immediate: true//路由发生变化时界面还未重绘完成，刷新布局没有意义，当前已经在重绘时处理
    }
  }
};
</script>
<style lang="scss" scoped>
@import "@/assets/styles/inputSty.scss";
.el-textarea__inner {
  width: 200px;
}
</style>
