<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch"
             label-width="68px">
      <el-form-item label="社区名称"
                    prop="communityId">
        <el-select v-model="queryParams.communityId"
                   filterable
                   clearable
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
                   placeholder="请选择小区">
          <el-option v-for="dict in communityChildOptions"
                     :key="dict.id"
                     :label="dict.name"
                     :value="dict.id" />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="姓名"
                    prop="name">
        <el-input v-model="queryParams.name"
                  placeholder="请输入姓名"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <el-form-item label="性别"
                    prop="gender">
        <el-select v-model="queryParams.gender"
                   placeholder="请选择性别"
                   clearable
                   size="small">
          <el-option v-for="dict in genderCodeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="证件号码"
                    prop="idnumber">
        <el-input v-model="queryParams.idnumber"
                  placeholder="请输入证件号码"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->

      <el-form-item label="位置标记时间"
                    label-width="auto"
                    prop="locationMarkTime">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="queryParams.locationMarkTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择位置标记时间">
        </el-date-picker>
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
                   v-hasPermi="['communityUnit:personnel:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityUnit:personnel:remove']">删除</el-button>
        <!-- <el-button icon="el-icon-refresh"
                   size="mini"
                   @click="resetQuery">重置</el-button> -->
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10"
            class="mb8">
      <el-col :span="1.5">

      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['communityUnit:personnel:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">

      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['communityUnit:personnel:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading"
              stripe
              :data="personnelList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="社区名称"
                       align="center"
                       prop="communityName" />
      <el-table-column label="小区名称"
                       align="center"
                       prop="communityChildName" />

      <el-table-column label="楼栋名称"
                       align="center"
                       prop="communityBuildingName" />
      <el-table-column label="性别"
                       align="center"
                       prop="gender"
                       :formatter="genderFormat" />
      <el-table-column label="标记位置"
                       align="center"
                       prop="locationMarkAddr" />
      <el-table-column label="位置标记时间"
                       align="center"
                       prop="locationMarkTime"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.locationMarkTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <!-- <el-table-column label="年龄值"
                       align="center"
                       prop="agevalue" /> -->
      <!-- <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="肤色"
                       align="center"
                       prop="id" />
      <el-table-column label="人脸id"
                       align="center"
                       prop="faceId" />
      <el-table-column label="楼栋id"
                       align="center"
                       prop="communityBuildingId" />
      
      <el-table-column label="小区id"
                       align="center"
                       prop="communityChildId" />
      
      <el-table-column label="房屋id"
                       align="center"
                       prop="communityHousesId" />
      <el-table-column label="房屋名称"
                       align="center"
                       prop="communityHousesName" />
      <el-table-column label="社区id"
                       align="center"
                       prop="communityId" />
      
      <el-table-column label="设备编码"
                       align="center"
                       prop="deviceId" />
      <el-table-column label="人脸出现时间"
                       align="center"
                       prop="faceAppearTime"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.faceAppearTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="人脸消失时间"
                       align="center"
                       prop="faceDisAppearTime"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.faceDisAppearTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="脸型"
                       align="center"
                       prop="faceStyle" />
      <el-table-column label="脸部特征"
                       align="center"
                       prop="facialFeature" />
      
      
      
      <el-table-column label="表情"
                       align="center"
                       prop="expression" />
      
       -->
      <!--
      <el-table-column label="眼镜"
                       align="center"
                       prop="eyeglass" />
      <el-table-column label="口罩"
                       align="center"
                       prop="mouthmask" />
      <el-table-column label="年龄段"
                       align="center"
                       prop="age" />
      <el-table-column label="来源标识"
                       align="center"
                       prop="sourceId" />
      <el-table-column label="对应的人员表里的人员ID"
                       align="center"
                       prop="personId" />
      <el-table-column label="来源表"
                       align="center"
                       prop="sourceTableName" />
      <el-table-column label="照片列表，多张照片以逗号隔开，默认显示第一张"
                       align="center"
                       prop="pics" />
      <el-table-column label="眉型"
                       align="center"
                       prop="eyebrowStyle" />
      <el-table-column label="黑痣胎记"
                       align="center"
                       prop="freckleBirthmark" />
      <el-table-column label="眼镜颜色"
                       align="center"
                       prop="glassColor" />
      <el-table-column label="眼镜款式"
                       align="center"
                       prop="glassStyle" />
      <el-table-column label="发色"
                       align="center"
                       prop="hairColor" />
      <el-table-column label="发型"
                       align="center"
                       prop="hairStyle" />
      <el-table-column label="信息分类"
                       align="center"
                       prop="infoKind" />
      <el-table-column label="同行人脸数"
                       align="center"
                       prop="accompanyNumber" />
      <el-table-column label="痤疮色斑"
                       align="center"
                       prop="acneStain" />
      <el-table-column label="姿态分布"
                       align="center"
                       prop="attitude" />
      <el-table-column label="体表特殊标记"
                       align="center"
                       prop="bodySpeciallMark" />
      <el-table-column label="帽子颜色"
                       align="center"
                       prop="capColor" />
      <el-table-column label="帽子款式"
                       align="center"
                       prop="capStyle" />
      <el-table-column label="左上角X坐标"
                       align="center"
                       prop="leftTopX" />
      <el-table-column label="左上角Y坐标"
                       align="center"
                       prop="leftTopY" />
      <el-table-column label="嘴唇"
                       align="center"
                       prop="lipStyle" />
      <el-table-column label="成员类型代码"
                       align="center"
                       prop="memberTypeCode" /> -->
      <el-table-column label="操作"
                       align="center"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['communityUnit:personnel:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['communityUnit:personnel:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改陌生人对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="500px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="80px">
        <el-form-item label="人脸id"
                      prop="faceId">
          <el-input v-model="form.faceId"
                    placeholder="请输入人脸id" />
        </el-form-item>
        <el-form-item label="社区名称"
                      prop="communityId">
          <el-select v-model="form.communityId"
                     filterable
                     clearable
                     :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                     @change="getCommunityChildDictory2"
                     placeholder="请选择社区">
            <el-option v-for="dict in communityOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>

        <el-form-item label="小区名称"
                      prop="communityChildId">
          <el-select v-model="form.communityChildId"
                     filterable
                     clearable
                     :disabled="form.id &&form.id.length > 0 &&form.checked &&form.checked === '1'"
                     @change="setCommunityChildName"
                     placeholder="请选择小区">
            <el-option v-for="dict in communityChildOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>

        <el-form-item label="社区楼栋"
                      prop="communityBuildingId">
          <el-select v-model="form.communityBuildingId"
                     filterable
                     placeholder="请选择楼栋">
            <el-option v-for="dict in communityBuildingOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="房屋名称"
                      prop="communityHousesName">
          <el-input v-model="form.communityHousesName"
                    placeholder="请输入房屋名称" />
        </el-form-item>

        <el-form-item label="设备编码"
                      prop="deviceId">
          <el-input v-model="form.deviceId"
                    placeholder="请输入设备编码" />
        </el-form-item>
        <el-form-item label="人脸出现时间"
                      prop="faceAppearTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.faceAppearTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择人脸出现时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="人脸消失时间"
                      prop="faceDisAppearTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.faceDisAppearTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择人脸消失时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="脸型"
                      prop="faceStyle">
          <el-input v-model="form.faceStyle"
                    placeholder="请输入脸型" />
        </el-form-item>
        <el-form-item label="脸部特征"
                      prop="facialFeature">
          <el-input v-model="form.facialFeature"
                    placeholder="请输入脸部特征" />
        </el-form-item>
        <el-form-item label="性别"
                      prop="gender">
          <el-select v-model="form.gender"
                     placeholder="请选择性别">
            <el-option v-for="dict in genderCodeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="证件号码"
                      prop="idnumber">
          <el-input v-model="form.idnumber"
                    placeholder="请输入证件号码" />
        </el-form-item>
        <el-form-item label="证件种类"
                      prop="idType">
          <el-select v-model="form.idType"
                     placeholder="请选择证件种类">
            <el-option v-for="dict in idTypeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="表情"
                      prop="expression">
          <el-input v-model="form.expression"
                    placeholder="请输入表情" />
        </el-form-item>
        <el-form-item label="位置标记时间"
                      prop="locationMarkTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.locationMarkTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择位置标记时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="标记位置"
                      prop="locationMarkAddr">
          <el-input v-model="form.locationMarkAddr"
                    placeholder="请输入标记位置" />
        </el-form-item>
        <el-form-item label="年龄值"
                      prop="agevalue">
          <el-input v-model="form.agevalue"
                    placeholder="请输入年龄值" />
        </el-form-item>
        <el-form-item label="眼镜"
                      prop="eyeglass">
          <el-input v-model="form.eyeglass"
                    placeholder="请输入眼镜" />
        </el-form-item>
        <el-form-item label="口罩"
                      prop="mouthmask">
          <el-input v-model="form.mouthmask"
                    placeholder="请输入口罩" />
        </el-form-item>
        <el-form-item label="年龄段"
                      prop="age">
          <el-input v-model="form.age"
                    placeholder="请输入年龄段" />
        </el-form-item>
        <el-form-item label="来源标识"
                      prop="sourceId">
          <el-input v-model="form.sourceId"
                    placeholder="请输入来源标识" />
        </el-form-item>
        <el-form-item label="对应的人员表里的人员ID"
                      prop="personId">
          <el-input v-model="form.personId"
                    placeholder="请输入对应的人员表里的人员ID" />
        </el-form-item>
        <el-form-item label="来源表"
                      prop="sourceTableName">
          <el-input v-model="form.sourceTableName"
                    placeholder="请输入来源表" />
        </el-form-item>
        <el-form-item label="照片列表，多张照片以逗号隔开，默认显示第一张"
                      prop="pics">
          <el-input v-model="form.pics"
                    type="textarea"
                    placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="眉型"
                      prop="eyebrowStyle">
          <el-input v-model="form.eyebrowStyle"
                    placeholder="请输入眉型" />
        </el-form-item>
        <el-form-item label="黑痣胎记"
                      prop="freckleBirthmark">
          <el-input v-model="form.freckleBirthmark"
                    placeholder="请输入黑痣胎记" />
        </el-form-item>
        <el-form-item label="眼镜颜色"
                      prop="glassColor">
          <el-input v-model="form.glassColor"
                    placeholder="请输入眼镜颜色" />
        </el-form-item>
        <el-form-item label="眼镜款式"
                      prop="glassStyle">
          <el-input v-model="form.glassStyle"
                    placeholder="请输入眼镜款式" />
        </el-form-item>
        <el-form-item label="发色"
                      prop="hairColor">
          <el-input v-model="form.hairColor"
                    placeholder="请输入发色" />
        </el-form-item>
        <el-form-item label="发型"
                      prop="hairStyle">
          <el-input v-model="form.hairStyle"
                    placeholder="请输入发型" />
        </el-form-item>
        <el-form-item label="信息分类"
                      prop="infoKind">
          <el-input v-model="form.infoKind"
                    placeholder="请输入信息分类" />
        </el-form-item>
        <el-form-item label="同行人脸数"
                      prop="accompanyNumber">
          <el-input v-model="form.accompanyNumber"
                    placeholder="请输入同行人脸数" />
        </el-form-item>
        <el-form-item label="痤疮色斑"
                      prop="acneStain">
          <el-input v-model="form.acneStain"
                    placeholder="请输入痤疮色斑" />
        </el-form-item>
        <el-form-item label="姿态分布"
                      prop="attitude">
          <el-input v-model="form.attitude"
                    placeholder="请输入姿态分布" />
        </el-form-item>
        <el-form-item label="体表特殊标记"
                      prop="bodySpeciallMark">
          <el-input v-model="form.bodySpeciallMark"
                    placeholder="请输入体表特殊标记" />
        </el-form-item>
        <el-form-item label="帽子颜色"
                      prop="capColor">
          <el-input v-model="form.capColor"
                    placeholder="请输入帽子颜色" />
        </el-form-item>
        <el-form-item label="帽子款式"
                      prop="capStyle">
          <el-input v-model="form.capStyle"
                    placeholder="请输入帽子款式" />
        </el-form-item>
        <el-form-item label="左上角X坐标"
                      prop="leftTopX">
          <el-input v-model="form.leftTopX"
                    placeholder="请输入左上角X坐标" />
        </el-form-item>
        <el-form-item label="左上角Y坐标"
                      prop="leftTopY">
          <el-input v-model="form.leftTopY"
                    placeholder="请输入左上角Y坐标" />
        </el-form-item>
        <el-form-item label="嘴唇"
                      prop="lipStyle">
          <el-input v-model="form.lipStyle"
                    placeholder="请输入嘴唇" />
        </el-form-item>
        <el-form-item label="成员类型代码"
                      prop="memberTypeCode">
          <el-input v-model="form.memberTypeCode"
                    placeholder="请输入成员类型代码" />
        </el-form-item>
        <el-form-item label="胡型"
                      prop="mustacheStyle">
          <el-input v-model="form.mustacheStyle"
                    placeholder="请输入胡型" />
        </el-form-item>
        <el-form-item label="鼻型"
                      prop="noseStyle">
          <el-input v-model="form.noseStyle"
                    placeholder="请输入鼻型" />
        </el-form-item>
        <el-form-item label="其他特征"
                      prop="otherFeature">
          <el-input v-model="form.otherFeature"
                    placeholder="请输入其他特征" />
        </el-form-item>
        <el-form-item label="体貌特征"
                      prop="physicalFeature">
          <el-input v-model="form.physicalFeature"
                    placeholder="请输入体貌特征" />
        </el-form-item>
        <el-form-item label="口罩颜色"
                      prop="respiratorColor">
          <el-input v-model="form.respiratorColor"
                    placeholder="请输入口罩颜色" />
        </el-form-item>
        <el-form-item label="右下角X坐标"
                      prop="rightBtmX">
          <el-input v-model="form.rightBtmX"
                    placeholder="请输入右下角X坐标" />
        </el-form-item>
        <el-form-item label="右下角Y坐标"
                      prop="rightBtmY">
          <el-input v-model="form.rightBtmY"
                    placeholder="请输入右下角Y坐标" />
        </el-form-item>
        <el-form-item label="疤痕酒窝"
                      prop="scarDimple">
          <el-input v-model="form.scarDimple"
                    placeholder="请输入疤痕酒窝" />
        </el-form-item>
        <el-form-item label="肤色"
                      prop="skinColor">
          <el-input v-model="form.skinColor"
                    placeholder="请输入肤色" />
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
import { listPersonnel, getPersonnel, delPersonnel, addPersonnel, updatePersonnel, exportPersonnel } from "@/api/passManage/stranger_personnel.js";
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import { listCommunity_building } from "@/api/communityUnit/community_building";
export default {
  name: "Personnel",
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
      // 陌生人表格数据
      personnelList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 社区
      communityOptions: [],
      //小区列表
      communityChildOptions: [],
      // 性别字典
      genderCodeOptions: [],
      // 证件种类字典
      idTypeOptions: [],
      //社区楼栋列表
      communityBuildingOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        faceId: null,
        communityBuildingId: null,
        communityBuildingName: null,
        communityChildId: null,
        communityChildName: null,
        communityHousesId: null,
        communityHousesName: null,
        communityId: null,
        communityName: null,
        deviceId: null,
        faceAppearTime: null,
        faceDisAppearTime: null,
        faceStyle: null,
        facialFeature: null,
        gender: null,
        idnumber: null,
        idType: null,
        expression: null,
        locationMarkTime: null,
        locationMarkAddr: null,
        agevalue: null,
        eyeglass: null,
        mouthmask: null,
        age: null,
        sourceId: null,
        personId: null,
        sourceTableName: null,
        pics: null,
        eyebrowStyle: null,
        freckleBirthmark: null,
        glassColor: null,
        glassStyle: null,
        hairColor: null,
        hairStyle: null,
        infoKind: null,
        accompanyNumber: null,
        acneStain: null,
        attitude: null,
        bodySpeciallMark: null,
        capColor: null,
        capStyle: null,
        leftTopX: null,
        leftTopY: null,
        lipStyle: null,
        memberTypeCode: null,
        mustacheStyle: null,
        noseStyle: null,
        otherFeature: null,
        physicalFeature: null,
        respiratorColor: null,
        rightBtmX: null,
        rightBtmY: null,
        scarDimple: null,
        skinColor: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        // faceId: [
        //   { required: true, message: "人脸id不能为空", trigger: "blur" }
        // ],
        // personId: [
        //   { required: true, message: "对应的人员表里的人员ID不能为空", trigger: "blur" }
        // ],
        // delFlag: [
        //   { required: true, message: "删除标志不能为空", trigger: "blur" }
        // ],

      }
    };
  },
  created () {
    this.getDicts("sys_user_sex").then((response) => {
      this.genderCodeOptions = response.data;
      console.log(this.genderCodeOptions);
    });
    this.getDicts("base_idCard").then((response) => {
      this.idTypeOptions = response.data;
    });
    this.getCommunityDictory();
    this.getList();
  },
  methods: {
    getCommunityDictory () {
      this.communityOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
      };
      listCommunity(param).then((response) => {
        this.communityOptions = response.rows;
      });
    },
    listCommunity_building (communityChildId) {
      this.communityBuildingOptions = [];
      if (!(communityChildId && communityChildId.length > 0)) {
        return false;
      }
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: null,
        communityChildId: communityChildId,
      };
      if (this.form.communityId) {
        param.communityId = this.form.communityId;
      }

      listCommunity_building(param).then((response) => {
        this.communityBuildingOptions = response.rows;
      });
    },
    getCommunityChildDictory (communityId) {
      this.communityChildOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId,
      };
      listCommunity_child(param).then((response) => {
        this.communityChildOptions = response.rows;
      });
    },
    getCommunityChildDictory2 (communityId) {
      let self = this;
      if (this.communityOptions && this.communityOptions.length > 0 && communityId != null) {
        console.log(this.communityOptions);
        let rows = this.communityOptions.filter((item) => {
          return item.id === communityId;
        });
        self.form.communityName = rows[0].name;
      }
      this.communityChildOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId,
      };
      listCommunity_child(param).then((response) => {
        this.communityChildOptions = response.rows;
      });
    },
    setCommunityChildName (communityChildId) {
      if (this.communityChildOptions && this.communityChildOptions.length > 0 && communityChildId != null) {
        let rows = this.communityChildOptions.filter((item) => {
          return item.id === communityChildId;
        });
        this.form.communityChildName = rows[0].name;
      }
      this.listCommunity_building(communityChildId);
    },
    setBuildingChildName (communityChildId) {
      if (this.communityChildOptions && this.communityChildOptions.length > 0 && communityChildId != null) {
        let rows = this.communityChildOptions.filter((item) => {
          return item.id === communityChildId;
        });
        this.form.communityChildName = rows[0].name;
      }
      this.listCommunity_building(communityChildId);
    },
    communityNameFormat (row, column, cellValue) {
      if (this.communityOptions && this.communityOptions.length > 0) {
        let rows = this.communityOptions.filter((item) => {
          return item.id === cellValue;
        });
        if (rows && rows.length > 0) {
          return rows[0].name;
        }
      }
      return "??";
    },
    communityChildNameFormat (row, column, cellValue) {
      if (
        this.allCommunityChildOptions &&
        this.allCommunityChildOptions.length > 0
      ) {
        let rows = this.allCommunityChildOptions.filter((item) => {
          return item.id === cellValue;
        });
        if (rows && rows.length > 0) {
          return rows[0].name;
        }
      }
      return "";
    },
    genderFormat (row, column, cellValue) {
      // console.log(this.genderCodeOptions);
      return this.selectDictLabel(this.genderCodeOptions, row.gender);
    },
    /** 查询陌生人列表 */
    getList () {
      this.loading = true;
      listPersonnel(this.queryParams).then(response => {
        // console.log(response.rows);
        this.personnelList = response.rows;
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
        id: null,
        faceId: null,
        communityBuildingId: null,
        communityBuildingName: null,
        communityChildId: null,
        communityChildName: null,
        communityHousesId: null,
        communityHousesName: null,
        communityId: null,
        communityName: null,
        deviceId: null,
        faceAppearTime: null,
        faceDisAppearTime: null,
        faceStyle: null,
        facialFeature: null,
        gender: null,
        idnumber: null,
        idType: null,
        expression: null,
        locationMarkTime: null,
        locationMarkAddr: null,
        agevalue: null,
        eyeglass: null,
        mouthmask: null,
        age: null,
        sourceId: null,
        personId: null,
        delFlag: null,
        sourceTableName: null,
        pics: null,
        eyebrowStyle: null,
        freckleBirthmark: null,
        glassColor: null,
        glassStyle: null,
        hairColor: null,
        hairStyle: null,
        infoKind: null,
        accompanyNumber: null,
        acneStain: null,
        attitude: null,
        bodySpeciallMark: null,
        capColor: null,
        capStyle: null,
        leftTopX: null,
        leftTopY: null,
        lipStyle: null,
        memberTypeCode: null,
        mustacheStyle: null,
        noseStyle: null,
        otherFeature: null,
        physicalFeature: null,
        respiratorColor: null,
        rightBtmX: null,
        rightBtmY: null,
        scarDimple: null,
        skinColor: null
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
      this.title = "添加陌生人";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const id = row.id || this.ids
      getPersonnel(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改陌生人";
      });
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePersonnel(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addPersonnel(this.form).then(response => {
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
      this.$confirm('是否确认删除陌生人编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delPersonnel(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有陌生人数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportPersonnel(queryParams);
      }).then(response => {
        this.download(response.msg);
      }).catch(function () { });
    }
  }
};
</script>
