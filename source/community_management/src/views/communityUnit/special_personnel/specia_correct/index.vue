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
      <!-- <el-form-item label="人员ID" prop="personnelId">
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

      <el-form-item label="社区矫正人员编号"
                    prop="communityPersonnelNumber">
        <el-input v-model="form.communityPersonnelNumber"
                  placeholder="请输入社区矫正人员编号" />
      </el-form-item>
      <el-form-item label="原羁押场所"
                    prop="originalDetentionPlace">
        <el-input v-model="form.originalDetentionPlace"
                  placeholder="请输入原羁押场所" />
      </el-form-item>
      <el-form-item label="矫正类别"
                    prop="correctType">
        <el-select v-model="form.correctType"
                   placeholder="请选择矫正类别">
          <el-option v-for="dict in correctTypeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="案件类别"
                    prop="caseType">
        <el-select v-model="form.caseType"
                   filterable
                   placeholder="请选择案件类别">
          <el-option v-for="dict in caseTypeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictValue+'    '+dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="罪犯类型"
                    prop="crimeType">
        <el-select v-model="form.crimeType"
                   placeholder="请选择罪犯类型">
          <el-option v-for="dict in crimeTypeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="具体罪名"
                    prop="specificCrime">
        <el-input v-model="form.specificCrime"
                  placeholder="请输入具体罪名" />
      </el-form-item>
      <el-form-item label="原判刑期"
                    prop="termDate">
        <el-input v-model="form.termDate"
                  placeholder="请输入原判刑期" />
      </el-form-item>
      <el-form-item label="刑种"
                    prop="punishmentKinds">
        <el-input v-model="form.punishmentKinds"
                  placeholder="请输入刑种" />
      </el-form-item>
      <el-form-item label="原判刑开始时间"
                    prop="termStartDate">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="form.termStartDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择原判刑开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="原判刑结束时间"
                    prop="termEndDate">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="form.termEndDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择原判刑结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="矫正开始时间"
                    prop="correctStartDate">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="form.correctStartDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择矫正开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="矫正结束时间"
                    prop="correctEndDate">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="form.correctEndDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择矫正结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="接收方式"
                    prop="acceptMode">
        <el-select v-model="form.acceptMode"
                   placeholder="请选择接收方式">
          <el-option v-for="dict in acceptModeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="四史情况"
                    prop="fourHistoriesSituation">
        <el-select multiple
                   clearable
                   v-model="form.fourHistoriesSituation"
                   placeholder="请选择四史情况">
          <el-option v-for="dict in fourHistoriesSituationOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否累惯犯"
                    prop="sfgf">
        <el-select v-model="form.sfgf"
                   placeholder="请选择是否累惯犯">
          <el-option v-for="dict in sfgfOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="三涉情况"
                    prop="threeInvolveSituation">
        <el-select multiple
                   clearable
                   v-model="form.threeInvolveSituation"
                   placeholder="请选择三涉情况">
          <el-option v-for="dict in threeInvolveSituationOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否建立矫正小组"
                    prop="isBuildCorrectTeam">
        <el-select v-model="form.isBuildCorrectTeam"
                   placeholder="请选择是否建立矫正小组">
          <el-option v-for="dict in isBuildCorrectTeamOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="矫正小组人员组成"
                    prop="correctTeamSituation">
        <el-select v-model="form.correctTeamSituation"
                   multiple
                   clearable
                   placeholder="请选择矫正小组人员组成情况">
          <el-option v-for="dict in correctTeamSituationOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="矫正解除(终止)类型"
                    prop="correctRemoveType">
        <el-select v-model="form.correctRemoveType"
                   placeholder="请选择矫正解除(终止)类型">
          <el-option v-for="dict in correctRemoveTypeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否脱管"
                    prop="isTrusteeship">
        <el-select v-model="form.isTrusteeship"
                   placeholder="请选择是否脱管">
          <el-option v-for="dict in isTrusteeshipOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="脱管原因"
                    prop="trusteeshipReason">
        <el-input v-model="form.trusteeshipReason"
                  placeholder="请输入脱管原因" />
      </el-form-item>
      <el-form-item label="检查监督脱管情况"
                    prop="checkTrusteeshipSituation">
        <el-input v-model="form.checkTrusteeshipSituation"
                  placeholder="请输入检查监督脱管情况" />
      </el-form-item>
      <el-form-item label="脱管纠正情况"
                    prop="trusteeshipCorrectSituation">
        <el-input v-model="form.trusteeshipCorrectSituation"
                  placeholder="请输入脱管纠正情况" />
      </el-form-item>
      <el-form-item label="是否漏管"
                    prop="isLeak">
        <el-select v-model="form.isLeak"
                   placeholder="请选择是否漏管">
          <el-option v-for="dict in isLeakOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="漏管原因"
                    prop="leakReason">
        <el-input v-model="form.leakReason"
                  placeholder="请输入漏管原因" />
      </el-form-item>
      <el-form-item label="检查监督漏管情况"
                    prop="checkLeakSituation">
        <el-input v-model="form.checkLeakSituation"
                  placeholder="请输入检查监督漏管情况" />
      </el-form-item>
      <el-form-item label="漏管纠正情况"
                    prop="correctSituation">
        <el-input v-model="form.correctSituation"
                  placeholder="请输入漏管纠正情况" />
      </el-form-item>
      <el-form-item label="奖惩情况"
                    prop="rewardPunishmentSituation">
        <el-input v-model="form.rewardPunishmentSituation"
                  placeholder="请输入奖惩情况" />
      </el-form-item>
      <el-form-item label="刑罚变更执行情况"
                    prop="punishmentChangeSituation">
        <el-input v-model="form.punishmentChangeSituation"
                  placeholder="请输入刑罚变更执行情况" />
      </el-form-item>
      <el-form-item label="重新犯罪名称"
                    prop="crimeName">
        <el-input v-model="form.crimeName"
                  placeholder="请输入重新犯罪名称" />
      </el-form-item>
      <el-form-item label="是否重新犯罪"
                    prop="isAgainCrime">
        <el-select v-model="form.isAgainCrime"
                   placeholder="请选择是否重新犯罪">
          <el-option v-for="dict in isAgainCrimeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="社区矫正决定机关"
                    prop="correctOffice">
        <el-input v-model="form.correctOffice"
                  placeholder="请输入社区矫正决定机关" />
      </el-form-item>
      <el-form-item label="矫正期限"
                    prop="correctTerm">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="form.correctTerm"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择矫正期限">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="法律文书收到日期"
                    prop="documentReceiptDate">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="form.documentReceiptDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择法律文书收到日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="被收规定时间"
                    prop="receivedTime">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="form.receivedTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择被收规定时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="具体报到时间"
                    prop="specificTime">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="form.specificTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择具体报到时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="主要犯罪事实"
                    prop="mainCrime">
        <el-input v-model="form.mainCrime"
                  placeholder="请输入主要犯罪事实" />
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
import { listSpecia_correct, getSpecia_correct, delSpecia_correct, addSpecia_correct, updateSpecia_correct, exportSpecia_correct } from "@/api/special_personnel/specia_correct";
import { getPersonnel_information, listPersonnel_information } from "@/api/communityUnit/personnel_information";
import { listCommunity } from "@/api/communityUnit/community";
import { getInfoByName } from "@/api/communityUnit/personnel_information";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import { updatePersonnel_specialType } from "@/api/communityUnit/personnel_information";
export default {
  name: "Specia_correct",
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
      // 社区矫正人员信息 表格数据
      specia_correctList: [],
      // 来源类型字典
      sourceTypeOptions: [],
      // 是否有效字典
      enableOptions: [],
      // 矫正类别字典
      correctTypeOptions: [],
      // 罪犯类型字典
      crimeTypeOptions: [],
      // 接收方式字典
      acceptModeOptions: [],
      // 四史情况字典
      fourHistoriesSituationOptions: [],
      // 是否累惯犯字典
      sfgfOptions: [],
      // 请选择案件类别
      caseTypeOptions: [],
      // 三涉情况字典
      threeInvolveSituationOptions: [],
      // 是否建立矫正小组字典
      isBuildCorrectTeamOptions: [],
      // 矫正小组人员组成情况字典
      correctTeamSituationOptions: [],
      // 矫正解除(终止)类型字典
      correctRemoveTypeOptions: [],
      // 是否脱管字典
      isTrusteeshipOptions: [],
      // 是否漏管字典
      isLeakOptions: [],
      // 是否重新犯罪字典
      isAgainCrimeOptions: [],
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
        communityPersonnelNumber: null,
        originalDetentionPlace: null,
        correctType: null,
        caseType: null,
        crimeType: null,
        specificCrime: null,
        termDate: null,
        punishmentKinds: null,
        termStartDate: null,
        termEndDate: null,
        correctStartDate: null,
        correctEndDate: null,
        acceptMode: null,
        fourHistoriesSituation: null,
        sfgf: null,
        threeInvolveSituation: null,
        isBuildCorrectTeam: null,
        correctTeamSituation: null,
        correctRemoveType: null,
        isTrusteeship: null,
        trusteeshipReason: null,
        checkTrusteeshipSituation: null,
        trusteeshipCorrectSituation: null,
        isLeak: null,
        leakReason: null,
        checkLeakSituation: null,
        correctSituation: null,
        rewardPunishmentSituation: null,
        punishmentChangeSituation: null,
        crimeName: null,
        isAgainCrime: null,
        correctOffice: null,
        correctTerm: null,
        documentReceiptDate: null,
        receivedTime: null,
        specificTime: null,
        mainCrime: null
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
        correctType: [
          { required: true, message: "矫正类别不能为空", trigger: "change" }
        ],
        caseType: [
          { required: true, message: "案件类别不能为空", trigger: "change" }
        ],
        specificCrime: [
          { required: true, message: "具体罪名不能为空", trigger: "blur" }
        ],
        correctStartDate: [
          { required: true, message: "矫正开始时间不能为空", trigger: "blur" }
        ],
        correctEndDate: [
          { required: true, message: "矫正结束时间不能为空", trigger: "blur" }
        ],
        acceptMode: [
          { required: true, message: "接收方式不能为空", trigger: "change" }
        ],
        isBuildCorrectTeam: [
          { required: true, message: "是否建立矫正小组不能为空", trigger: "change" }
        ],
        correctTeamSituation: [
          { required: true, message: "矫正小组人员组成情况不能为空", trigger: "change" }
        ],
        isTrusteeship: [
          { required: true, message: "是否脱管不能为空", trigger: "change" }
        ],
        isLeak: [
          { required: true, message: "是否漏管不能为空", trigger: "change" }
        ],
        isAgainCrime: [
          { required: true, message: "是否重新犯罪不能为空", trigger: "change" }
        ],
      }
    };
  },
  created () {
    this.getCommunityDictory()
    this.getDicts("base_special_person_data_source").then(response => {
      this.sourceTypeOptions = response.data;
    });
    this.getDicts("base_correction_type").then(response => {
      this.correctTypeOptions = response.data;
    });
    this.getDicts("base_criminal_type").then(response => {
      this.crimeTypeOptions = response.data;
    });
    this.getDicts("base_receiving_mode").then(response => {
      this.acceptModeOptions = response.data;
    });
    this.getDicts("base_four_histories").then(response => {
      this.fourHistoriesSituationOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.enableOptions = response.data;
      this.sfgfOptions = response.data;
      this.isBuildCorrectTeamOptions = response.data;
      this.isTrusteeshipOptions = response.data;
      this.isLeakOptions = response.data;
      this.isAgainCrimeOptions = response.data;
    });
    this.getDicts("base_case_type").then(response => {
      this.caseTypeOptions = response.data;// 请选择案件类别
    });
    this.getDicts("base_three_involvement").then(response => {
      this.threeInvolveSituationOptions = response.data;
    });
    this.getDicts("base_correction_team_composition_type").then(response => {
      this.correctTeamSituationOptions = response.data;
    });
    this.getDicts("base_correction_release_termination_type").then(response => {
      this.correctRemoveTypeOptions = response.data;
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
        personnelId: null,
        sourceType: null,
        enable: '1',
        remark: null,
        communityPersonnelNumber: null,
        originalDetentionPlace: null,
        correctType: null,
        caseType: null,
        crimeType: null,
        specificCrime: null,
        termDate: null,
        punishmentKinds: null,
        termStartDate: null,
        termEndDate: null,
        correctStartDate: null,
        correctEndDate: null,
        acceptMode: null,
        fourHistoriesSituation: null,
        sfgf: null,
        threeInvolveSituation: null,
        isBuildCorrectTeam: null,
        correctTeamSituation: null,
        correctRemoveType: null,
        isTrusteeship: null,
        trusteeshipReason: null,
        checkTrusteeshipSituation: null,
        trusteeshipCorrectSituation: null,
        isLeak: null,
        leakReason: null,
        checkLeakSituation: null,
        correctSituation: null,
        rewardPunishmentSituation: null,
        punishmentChangeSituation: null,
        crimeName: null,
        isAgainCrime: null,
        correctOffice: null,
        correctTerm: null,
        documentReceiptDate: null,
        receivedTime: null,
        specificTime: null,
        mainCrime: null
      };
      this.resetForm("form");
    },
    changeStringToList (form) {
      form.fourHistoriesSituation = this.$cf.getListFromString(form.fourHistoriesSituation)
      form.threeInvolveSituation = this.$cf.getListFromString(form.threeInvolveSituation)
      form.correctTeamSituation = this.$cf.getListFromString(form.correctTeamSituation)
    },
    changeListToString (form) {
      form.fourHistoriesSituation = this.$cf.getStringFromList(form.fourHistoriesSituation)
      form.threeInvolveSituation = this.$cf.getStringFromList(form.threeInvolveSituation)
      form.correctTeamSituation = this.$cf.getStringFromList(form.correctTeamSituation)
    },
    /** 提交按钮 */
    submitForm () {
      let self = this
      this.$refs["form"].validate(valid => {
        if (valid) {
          self.changeListToString(self.form)
          if (self.form.id != null) {
            updateSpecia_correct(self.form).then(response => {
              if (response.code === 200) {
                self.msgSuccess("修改成功");
                updatePersonnel_specialType(self.form.personnelId, '02')
                this.$Notice.$emit('special_personnel_submitForm', true)
              }
            });
          } else {
            addSpecia_correct(self.form).then(response => {
              if (response.code === 200) {
                self.msgSuccess("新增成功");
                updatePersonnel_specialType(self.form.personnelId, '02')
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
      listSpecia_correct(this.queryParams).then(response => {
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
      this.$confirm('是否确认删除社区矫正人员信息 编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delSpecia_correct(ids);
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
