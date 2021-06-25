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
                    prop="communityChildId">
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

      <el-form-item label="家庭经济状况"
                    prop="economicSituation">
        <el-select v-model="form.economicSituation"
                   placeholder="请选择家庭经济状况">
          <el-option v-for="dict in economicSituationOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否纳入低保"
                    prop="isEnjoySubsidy">
        <el-select v-model="form.isEnjoySubsidy"
                   placeholder="请选择是否纳入低保">
          <el-option v-for="dict in isEnjoySubsidyOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="监护人身份证号码"
                    prop="custodyIdCard">
        <el-input v-model="form.custodyIdCard"
                  placeholder="请输入监护人身份证号码" />
      </el-form-item>
      <el-form-item label="监护人姓名"
                    prop="custodyName">
        <el-input v-model="form.custodyName"
                  placeholder="请输入监护人姓名" />
      </el-form-item>
      <el-form-item label="监护人联系方式"
                    prop="custodyPhone">
        <el-input v-model="form.custodyPhone"
                  placeholder="请输入监护人联系方式" />
      </el-form-item>
      <el-form-item label="初次发病日期"
                    prop="firstIllnessDate">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="form.firstIllnessDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择初次发病日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="目前诊断类型"
                    prop="diagnosisType">
        <el-select v-model="form.diagnosisType"
                   placeholder="请选择目前诊断类型">
          <el-option v-for="dict in diagnosisTypeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="有无肇事肇祸史"
                    prop="isHaveTrouble">
        <el-select v-model="form.isHaveTrouble"
                   placeholder="请选择有无肇事肇祸史">
          <el-option v-for="dict in isHaveTroubleOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="肇事肇祸次数"
                    prop="troubleCount">
        <el-input v-model="form.troubleCount"
                  placeholder="请输入肇事肇祸次数" />
      </el-form-item>
      <el-form-item label="上次肇事肇祸日期"
                    prop="upTroubleDate">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="form.upTroubleDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择上次肇事肇祸日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="目前危险性评估等级"
                    prop="dangerLevel">
        <el-select v-model="form.dangerLevel"
                   placeholder="请选择目前危险性评估等级">
          <el-option v-for="dict in dangerLevelOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="治疗情况"
                    prop="treatmentSituation">
        <el-select v-model="form.treatmentSituation"
                   placeholder="请选择治疗情况">
          <el-option v-for="dict in treatmentSituationOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="治疗医院名称"
                    prop="hospitalName">
        <el-input v-model="form.hospitalName"
                  placeholder="请输入治疗医院名称" />
      </el-form-item>
      <el-form-item label="实施住院治疗原因"
                    prop="hospitalizationReason">
        <el-select multiple
                   clearable
                   v-model="form.hospitalizationReason"
                   placeholder="请选择实施住院治疗原因">
          <el-option v-for="dict in hospitalizationReasonOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="接受康复训练机构名称"
                    prop="recoveryName">
        <el-input v-model="form.recoveryName"
                  placeholder="请输入接受康复训练机构名称" />
      </el-form-item>
      <el-form-item label="参与管理人员"
                    prop="adminName">
        <el-select multiple
                   clearable
                   v-model="form.adminName"
                   placeholder="请选择参与管理人员">
          <el-option v-for="dict in adminNameOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
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
      <el-form-item label="控制情况"
                    prop="controlSituation">
        <el-input v-model="form.controlSituation"
                  placeholder="请输入控制情况" />
      </el-form-item>
      <el-form-item label="是否收容"
                    prop="isAsylum">
        <el-select v-model="form.isAsylum"
                   placeholder="请选择是否收容">
          <el-option v-for="dict in isAsylumOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
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
import { listSpecia_mental, getSpecia_mental, delSpecia_mental, addSpecia_mental, updateSpecia_mental, exportSpecia_mental } from "@/api/special_personnel/specia_mental";
import { getPersonnel_information, listPersonnel_information } from "@/api/communityUnit/personnel_information";
import { listCommunity } from "@/api/communityUnit/community";
import { getInfoByName } from "@/api/communityUnit/personnel_information";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import { updatePersonnel_specialType } from "@/api/communityUnit/personnel_information";
export default {
  name: "Specia_mental",
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
      // 易肇事肇祸精神病人 表格数据
      specia_mentalList: [],
      // 来源类型字典
      sourceTypeOptions: [],
      // 是否有效字典
      enableOptions: [],
      // 家庭经济状况字典
      economicSituationOptions: [],
      // 是否纳入低保字典
      isEnjoySubsidyOptions: [],
      // 目前诊断类型字典
      diagnosisTypeOptions: [],
      // 有无肇事肇祸史字典
      isHaveTroubleOptions: [],
      // 目前危险性评估等级字典
      dangerLevelOptions: [],
      // 治疗情况字典
      treatmentSituationOptions: [],
      // 实施住院治疗原因字典
      hospitalizationReasonOptions: [],
      // 参与管理人员字典
      adminNameOptions: [],
      // 帮扶情况字典
      helpSituationOptions: [],
      // 是否收容字典
      isAsylumOptions: [],
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
        economicSituation: null,
        isEnjoySubsidy: null,
        custodyIdCard: null,
        custodyName: null,
        custodyPhone: null,
        firstIllnessDate: null,
        diagnosisType: null,
        isHaveTrouble: null,
        troubleCount: null,
        upTroubleDate: null,
        dangerLevel: null,
        treatmentSituation: null,
        hospitalName: null,
        hospitalizationReason: null,
        recoveryName: null,
        adminName: null,
        helpSituation: null,
        controlSituation: null,
        isAsylum: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        personnelId: [
          { required: true, message: "人员信息不能为空", trigger: "blur" }
        ],
        enable: [
          { required: true, message: "是否有效不能为空", trigger: "change" }
        ],
        isEnjoySubsidy: [
          { required: true, message: "是否纳入低保不能为空", trigger: "change" }
        ],
        custodyName: [
          { required: true, message: "监护人姓名不能为空", trigger: "blur" }
        ],
        custodyPhone: [
          { required: true, message: "监护人联系方式不能为空", trigger: "blur" }
        ],
        diagnosisType: [
          { required: true, message: "目前诊断类型不能为空", trigger: "change" }
        ],
        isHaveTrouble: [
          { required: true, message: "有无肇事肇祸史不能为空", trigger: "change" }
        ],
        dangerLevel: [
          { required: true, message: "目前危险性评估等级不能为空", trigger: "change" }
        ],
        treatmentSituation: [
          { required: true, message: "治疗情况不能为空", trigger: "change" }
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
      this.isEnjoySubsidyOptions = response.data;
      this.isHaveTroubleOptions = response.data;
      this.isAsylumOptions = response.data;
    });
    this.getDicts("base_help_situation").then(response => {
      this.helpSituationOptions = response.data;
    });
    this.getDicts("base_family_financial_situation").then(response => {
      this.economicSituationOptions = response.data;
    });
    this.getDicts("base_current_diagnosis_type").then(response => {
      this.diagnosisTypeOptions = response.data;
    });
    this.getDicts("base_current_risk_assessment_level").then(response => {
      this.dangerLevelOptions = response.data;
    });
    this.getDicts("base_treatment_situation").then(response => {
      this.treatmentSituationOptions = response.data;
    });
    this.getDicts("base_reasons_for_hospitalization").then(response => {
      this.hospitalizationReasonOptions = response.data;
    });
    this.getDicts("base_participating_managers").then(response => {
      this.adminNameOptions = response.data;
    });
    this.getDicts("base_help_situation").then(response => {
      this.helpSituationOptions = response.data;
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
    // 表单重置
    reset () {
      this.form = {
        id: null,
        createBy: null,
        deptId: null,
        createTime: null,
        updateBy: null,
        updateDeptId: null,
        updateTime: null,
        delFlag: null,
        personnelId: null,
        sourceType: null,
        enable: null,
        remark: null,
        economicSituation: null,
        isEnjoySubsidy: null,
        custodyIdCard: null,
        custodyName: null,
        custodyPhone: null,
        firstIllnessDate: null,
        diagnosisType: null,
        isHaveTrouble: null,
        troubleCount: null,
        upTroubleDate: null,
        dangerLevel: null,
        treatmentSituation: null,
        hospitalName: null,
        hospitalizationReason: null,
        recoveryName: null,
        adminName: null,
        helpSituation: null,
        controlSituation: null,
        isAsylum: null
      };
      this.resetForm("form");
    },
    changeStringToList (form) {
      form.adminName = this.$cf.getListFromString(form.adminName)
      form.hospitalizationReason = this.$cf.getListFromString(form.hospitalizationReason)
      form.helpSituation = this.$cf.getListFromString(form.helpSituation)
    },
    changeListToString (form) {
      form.adminName = this.$cf.getStringFromList(form.adminName)
      form.hospitalizationReason = this.$cf.getStringFromList(form.hospitalizationReason)
      form.helpSituation = this.$cf.getStringFromList(form.helpSituation)
    },
    /** 提交按钮 */
    submitForm () {
      let self = this
      this.$refs["form"].validate(valid => {
        if (valid) {
          self.changeListToString(self.form)
          if (self.form.id != null) {
            updateSpecia_mental(self.form).then(response => {
              if (response.code === 200) {
                self.msgSuccess("修改成功");
                updatePersonnel_specialType(self.form.personnelId, '03')
                this.$Notice.$emit('special_personnel_submitForm', true)
              }
            });
          } else {
            addSpecia_mental(self.form).then(response => {
              if (response.code === 200) {
                self.msgSuccess("新增成功");
                updatePersonnel_specialType(self.form.personnelId, '03')
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
      listSpecia_mental(this.queryParams).then(response => {
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
      this.$confirm('是否确认删除易肇事肇祸精神病人 编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delSpecia_mental(ids);
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
