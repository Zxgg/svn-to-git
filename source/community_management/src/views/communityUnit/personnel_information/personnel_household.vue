<template>
  <el-form ref="form"
           :model="personInfo"
           label-width="140px"
           label-position="right">
    <el-row :gutter="20">
      <el-col :span="8">
        <el-form-item label="姓名">
          <span>{{personInfo.name || '--'}}</span>
        </el-form-item>
      </el-col>
      <!-- <el-col :span="8">
        <el-form-item label="曾用名">
          <span>{{personInfo.oldName || '--'}}</span>
        </el-form-item>
      </el-col> -->
      <el-col :span="8">
        <el-form-item label="出生日期">
          <span>{{personInfo.cardRecordDate || '--'}}</span>
        </el-form-item>
      </el-col>

      <el-col :span="8">
        <el-form-item label="身份证号">
          <span>{{personInfo.cardNum || '--'}}</span>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="性别">
          <span>{{personInfo.sex || '--'}}</span>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="婚姻状况">
          <span>{{personInfo.marital || '--'}}</span>
        </el-form-item>
      </el-col>

      <el-col :span="8">
        <el-form-item label="政治面貌">
          <span>{{personInfo.political || '--'}}</span>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="民族">
          <span>{{personInfo.nation || '--'}}</span>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="户籍区划">
          <span>{{'--'}}</span>
        </el-form-item>
      </el-col>

      <el-col :span="8">
        <el-form-item label="现住区划">
          <span>{{personInfo.address || '--'}}</span>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="籍贯">
          <span>{{personInfo.location || '--'}}</span>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="服务处所">
          <span>{{personInfo.servicePremises || '--'}}</span>
        </el-form-item>
      </el-col>

      <el-col :span="8">
        <el-form-item label="宗教信仰">
          <span>{{personInfo.religion || '--'}}</span>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="学历">
          <span>{{personInfo.degree || '--'}}</span>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="职业类别">
          <span>{{personInfo.profession || '--'}}</span>
        </el-form-item>
      </el-col>

      <el-col :span="8">
        <el-form-item label="户主姓名">
          <span>{{personInfo.householdName || '--'}}</span>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="职业">
          <span>{{personInfo.professioDetail || '--'}}</span>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="联系方式">
          <span>{{personInfo.phoneNumber || '--'}}</span>
        </el-form-item>
      </el-col>

      <el-col :span="8">
        <el-form-item label="户籍门(楼)详址">
          <span>{{personInfo.residenceDetail || '--'}}</span>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="户号">
          <span>{{personInfo.doorNo || '--'}}</span>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="人户一致标志">
          <span>{{personInfo.isSame || '--'}}</span>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="户主身份证号码">
          <span>{{personInfo.householdIdc || '--'}}</span>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="与户主关系">
          <span>{{personInfo.householdRelation || '--'}}</span>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="户主联系方式">
          <span>{{personInfo.householdPhone || '--'}}</span>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="现住门(楼)详址">
          <span>{{personInfo.addressDetail || '--'}}</span>
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
</template>

<script>
export default {
  props: {
    personInfo: {
      type: Object,
      default: null
    }
  },
  data () {
    return {
      form: {}
    }
  }
}
</script>

<style>
</style>