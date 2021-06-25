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
                     :label="dict.name + '   tl:' + dict.phoneNumber + '     id:' + dict.identificationNumber+ ' ' + dict.serialNumber+'号楼' + dict.unit+'单元' + dict.houseNumber"
                     :value="dict.id" />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="人员ID"
                    prop="personnelId">
        <el-input v-model="form.personnelId"
                  placeholder="请输入人员ID" />
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
                     :key="dict.dictValue+''"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否累犯"
                    prop="isRecidivism">
        <el-select v-model="form.isRecidivism"
                   placeholder="请选择是否累犯"
                   clearable
                   size="small">
          <el-option v-for="dict in isRecidivismOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>

      <el-form-item label="原罪名"
                    prop="originalCrimeName">
        <el-select v-model="form.originalCrimeName"
                   placeholder="请选择原罪名">
          <el-option v-for="dict in originalCrimeNameOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="原判刑期"
                    prop="punishmentTerm">
        <el-input v-model="form.punishmentTerm"
                  placeholder="请输入原判刑期" />
      </el-form-item>
      <el-form-item label="服刑场所"
                    prop="punishmentAddress">
        <el-input v-model="form.punishmentAddress"
                  placeholder="请输入服刑场所" />
      </el-form-item>
      <el-form-item label="释放日期"
                    prop="releaseDate">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="form.releaseDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择释放日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="危险性评估"
                    prop="dangerAssess">
        <el-select v-model="form.dangerAssess"
                   placeholder="请选择危险性评估">
          <el-option v-for="dict in dangerAssessOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="衔接日期"
                    prop="joinDate">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="form.joinDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择衔接日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="衔接情况"
                    prop="joinSituation">
        <el-select v-model="form.joinSituation"
                   placeholder="请选择衔接情况">
          <el-option v-for="dict in joinSituationOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="安置日期"
                    prop="placeDate">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="form.placeDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择安置日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="安置情况"
                    prop="placeSituation">
        <el-select v-model="form.placeSituation"
                   placeholder="请选择安置情况">
          <el-option v-for="dict in placeSituationOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="未安置原因"
                    prop="unplaceSituation">
        <el-input v-model="form.unplaceSituation"
                  placeholder="请输入未安置原因" />
      </el-form-item>
      <el-form-item label="帮教情况"
                    prop="helpSituation">
        <el-input v-model="form.helpSituation"
                  type="textarea"
                  placeholder="请输入内容" />
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
      <el-form-item label="重犯罪名称"
                    prop="crimeName">
        <el-input v-model="form.crimeName"
                  placeholder="请输入重犯罪名称" />
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

import { listSpecial_released_people, getSpecial_released_people, delSpecial_released_people, addSpecial_released_people, updateSpecial_released_people, exportSpecial_released_people } from "@/api/special_personnel/special_released_people";
import { getPersonnel_information, listPersonnel_information } from "@/api/communityUnit/personnel_information";
import { listCommunity } from "@/api/communityUnit/community";
import { getInfoByName } from "@/api/communityUnit/personnel_information";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import { updatePersonnel_specialType } from "@/api/communityUnit/personnel_information";
export default {
  name: "Special_released_people",
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
      // 总条数
      total: 0,
      // 远程搜索人员列表
      personOptions: [],
      // 刑满释放人员 表格数据
      special_released_peopleList: [],
      // 来源类型字典
      sourceTypeOptions: [],
      // 是否有效字典
      enableOptions: [],
      // 是否累犯字典
      isRecidivismOptions: [],
      // 原罪名字典
      originalCrimeNameOptions: [],
      // 危险性评估字典
      dangerAssessOptions: [],
      // 衔接情况字典
      joinSituationOptions: [],
      // 安置情况字典
      placeSituationOptions: [],
      // 是否重新犯罪字典
      isAgainCrimeOptions: [],
      // 重犯罪名称字典
      crimeNameOptions: [],
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
        isRecidivism: null,
        originalCrimeName: null,
        punishmentTerm: null,
        punishmentAddress: null,
        releaseDate: null,
        dangerAssess: null,
        joinDate: null,
        joinSituation: null,
        placeDate: null,
        placeSituation: null,
        unplaceSituation: null,
        helpSituation: null,
        isAgainCrime: null,
        crimeName: null
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
        isRecidivism: [
          { required: true, message: "是否累犯不能为空", trigger: "change" }
        ],
        originalCrimeName: [
          { required: true, message: "原罪名不能为空", trigger: "change" }
        ],
        punishmentTerm: [
          { required: true, message: "原判刑期不能为空", trigger: "blur" }
        ],
        punishmentAddress: [
          { required: true, message: "服刑场所不能为空", trigger: "blur" }
        ],
        releaseDate: [
          { required: true, message: "释放日期不能为空", trigger: "blur" }
        ],
        dangerAssess: [
          { required: true, message: "危险性评估不能为空", trigger: "change" }
        ],
        joinDate: [
          { required: true, message: "衔接日期不能为空", trigger: "blur" }
        ],
        joinSituation: [
          { required: true, message: "衔接情况不能为空", trigger: "change" }
        ],
        placeDate: [
          { required: true, message: "安置日期不能为空", trigger: "blur" }
        ],
        placeSituation: [
          { required: true, message: "安置情况不能为空", trigger: "blur" }
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
    this.getDicts("base_yes_no").then(response => {
      this.enableOptions = response.data;
      this.isRecidivismOptions = response.data;
      this.isAgainCrimeOptions = response.data;
    });
    this.getDicts("base_criminal_type").then(response => {
      this.originalCrimeNameOptions = response.data;
    });
    this.getDicts("base_risk_assessment").then(response => {
      this.dangerAssessOptions = response.data;
    });
    this.getDicts("base_connection").then(response => {
      this.joinSituationOptions = response.data;
    });
    this.getDicts("base_resettlement").then(response => {
      this.placeSituationOptions = response.data;
    });
    this.getDicts("base_criminal_type").then(response => {
      this.crimeNameOptions = response.data;
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
      this.resetForm("form");
      this.form = {
        id: null,
        createBy: null,
        deptId: null,
        createTime: null,
        updateBy: null,
        updateDeptId: null,
        updateTime: null,
        delFlag: '0',
        personnelId: null,
        sourceType: null,
        enable: '1',
        remark: null,
        isRecidivism: '0',
        originalCrimeName: null,
        punishmentTerm: null,
        punishmentAddress: null,
        releaseDate: null,
        dangerAssess: null,
        joinDate: null,
        joinSituation: null,
        placeDate: null,
        placeSituation: null,
        unplaceSituation: null,
        helpSituation: null,
        isAgainCrime: '0',
        crimeName: null
      };
    },

    /** 提交按钮 */
    submitForm () {
      let self = this
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (self.form.id != null) {
            updateSpecial_released_people(self.form).then(response => {
              if (response.code === 200) {
                self.msgSuccess("修改成功");
                updatePersonnel_specialType(self.form.personnelId, '01')
                this.$Notice.$emit('special_personnel_submitForm', true)
              }
            });
          } else {
            addSpecial_released_people(self.form).then(response => {
              if (response.code === 200) {
                self.msgSuccess("新增成功");
                updatePersonnel_specialType(self.form.personnelId, '01')
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
      listSpecial_released_people(this.queryParams).then(response => {
        if (response.rows && response.rows.length > 0) {
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
      this.$confirm('是否确认删除刑满释放人员 编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delSpecial_released_people(ids);
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
          this.queryParams.communityId = newValue.communityId
          this.queryParams.communityChildId = newValue.communityChildId
          this.getCommunityChildDictory(this.queryParams.communityId)
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
