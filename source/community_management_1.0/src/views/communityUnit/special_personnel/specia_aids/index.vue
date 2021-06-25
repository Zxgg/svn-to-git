<template>
  <div class="app-container">

    <!-- 添加或修改艾滋病人 对话框 -->
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
      <!--        
        <el-form-item label="人员ID" prop="personnelId">
          <el-input v-model="form.personnelId" placeholder="请输入人员ID" />
        </el-form-item> -->
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
      <el-form-item label="感染途径"
                    prop="infectedWay">
        <el-select v-model="form.infectedWay"
                   placeholder="请选择感染途径">
          <el-option v-for="dict in infectedWayOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="有无违法犯罪史"
                    prop="isHaveCrime">
        <el-select v-model="form.isHaveCrime"
                   placeholder="请选择有无违法犯罪史">
          <el-option v-for="dict in isHaveCrimeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="违法犯罪情况"
                    prop="crimeSituation">
        <el-input v-model="form.crimeSituation"
                  type="textarea"
                  placeholder="请输入内容" />
      </el-form-item>
      <el-form-item label="案件类别"
                    prop="caseType">
        <el-select v-model="form.caseType"
                   filterable
                   placeholder="请选择案件类别">
          <el-option v-for="dict in caseTypeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="关注类型"
                    prop="followType">
        <el-select v-model="form.followType"
                   placeholder="请选择关注类型">
          <el-option v-for="dict in followTypeOptions"
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
      <el-form-item label="帮扶人姓名"
                    prop="helpName">
        <el-input v-model="form.helpName"
                  placeholder="请输入帮扶人姓名" />
      </el-form-item>
      <el-form-item label="帮扶人联系方式"
                    prop="helpTel">
        <el-input v-model="form.helpTel"
                  placeholder="请输入帮扶人联系方式" />
      </el-form-item>
      <el-form-item label="收治情况"
                    prop="admittedSituation">
        <el-select v-model="form.admittedSituation"
                   placeholder="请选择收治情况">
          <el-option v-for="dict in admittedSituationOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="收治机构名称"
                    prop="admittedName">
        <el-input v-model="form.admittedName"
                  placeholder="请输入收治机构名称" />
      </el-form-item>
      <el-form-item label="家庭主要经济来源"
                    prop="mainSource">
        <el-select v-model="form.mainSource"
                   placeholder="请选择家庭主要经济来源">
          <el-option v-for="dict in mainSourceOptions"
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
import { listSpecia_aids, getSpecia_aids, delSpecia_aids, addSpecia_aids, updateSpecia_aids, exportSpecia_aids } from "@/api/special_personnel/specia_aids";
import { getPersonnel_information, listPersonnel_information, updatePersonnel_specialType } from "@/api/communityUnit/personnel_information";

import { listCommunity } from "@/api/communityUnit/community";
import { getInfoByName } from "@/api/communityUnit/personnel_information";
import { listCommunity_child } from "@/api/communityUnit/community_child";
export default {
  name: "Specia_aids",
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
      // 来源类型字典
      sourceTypeOptions: [],
      // 艾滋病人 表格数据
      specia_aidsList: [],
      // 是否有效字典
      enableOptions: [],
      // 有无违法犯罪史字典
      isHaveCrimeOptions: [],
      // 关注类型字典
      followTypeOptions: [],
      // 帮扶情况字典
      helpSituationOptions: [],
      //请选择感染途径
      infectedWayOptions: [],
      // 请选择案件类别
      caseTypeOptions: [],
      //请选择收治情况
      admittedSituationOptions: [],
      //请选择家庭主要经济来源
      mainSourceOptions: [],
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
        infectedWay: null,
        isHaveCrime: null,
        crimeSituation: null,
        caseType: null,
        followType: null,
        helpSituation: null,
        helpName: null,
        helpTel: null,
        admittedSituation: null,
        admittedName: null,
        mainSource: null
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
        infectedWay: [
          { required: true, message: "感染途径不能为空", trigger: "change" }
        ],
        isHaveCrime: [
          { required: true, message: "有无违法犯罪史不能为空", trigger: "change" }
        ],
        followType: [
          { required: true, message: "关注类型不能为空", trigger: "change" }
        ],
        helpName: [
          { required: true, message: "帮扶人姓名不能为空", trigger: "blur" }
        ],
        helpTel: [
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
      this.isHaveCrimeOptions = response.data;
    });
    this.getDicts("base_attention_level").then(response => {
      this.followTypeOptions = response.data;
    });
    this.getDicts("base_help_situation").then(response => {
      this.helpSituationOptions = response.data;
    });
    this.getDicts("base_path_of_infection").then(response => {
      this.infectedWayOptions = response.data;// 请选择感染途径
    });
    this.getDicts("base_case_type").then(response => {
      this.caseTypeOptions = response.data;// 请选择案件类别
    });
    this.getDicts("base_admission_status").then(response => {
      this.admittedSituationOptions = response.data;//  请选择收治情况
    });
    this.getDicts("base_family_financial_situation").then(response => {
      this.mainSourceOptions = response.data;//请选择家庭主要经济来源
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
        createTime: null,
        deptId: null,
        updateBy: null,
        updateDeptId: null,
        updateTime: null,
        delFlag: '0',
        personnelId: null,
        sourceType: null,
        enable: '1',
        remark: null,
        infectedWay: null,
        isHaveCrime: null,
        crimeSituation: null,
        caseType: null,
        followType: null,
        helpSituation: null,
        helpName: null,
        helpTel: null,
        admittedSituation: null,
        admittedName: null,
        mainSource: null
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
            updateSpecia_aids(self.form).then(response => {
              if (response.code === 200) {
                self.msgSuccess("修改成功");
                updatePersonnel_specialType(self.personnelId, '05')
                this.$Notice.$emit('special_personnel_submitForm', true)

              }
            });
          } else {
            addSpecia_aids(self.form).then(response => {
              if (response.code === 200) {
                self.msgSuccess("新增成功");
                updatePersonnel_specialType(self.personnelId, '05')
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
    /** 获取信息*/
    getInfo () {
      this.loading = true;
      let self = this
      listSpecia_aids(this.queryParams).then(response => {
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
      this.$confirm('是否确认删除危险艾滋病人 编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delSpecia_aids(ids);
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
