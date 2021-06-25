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
      <el-form-item label="关爱类型"
                    prop="type">
        <el-select v-model="queryParams.type"
                   placeholder="请选择关爱类型"
                   clearable
                   size="small">
          <el-option v-for="dict in typeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="姓名"
                    prop="name">
        <el-input v-model="queryParams.name"
                  placeholder="请输入姓名"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="性别"
                    prop="genderCode">
        <el-select v-model="queryParams.genderCode"
                   placeholder="请选择性别"
                   clearable
                   size="small">
          <el-option v-for="dict in genderCodeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <el-form-item label="证件号码"
                    prop="idnumber">
        <el-input v-model="queryParams.idnumber"
                  placeholder="请输入证件号码"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>

      <!-- <el-form-item label="位置标记时间"
                    prop="locationMarkTime">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="queryParams.locationMarkTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择位置标记时间">
        </el-date-picker>
      </el-form-item> -->
      <!--       
      <el-form-item label="居住地行政区划" prop="residenceAdminDivision">
        <el-input
          v-model="queryParams.residenceAdminDivision"
          placeholder="请输入居住地行政区划"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!--      
      <el-form-item label="对应的人员表里的人员ID" prop="personId">
        <el-input
          v-model="queryParams.personId"
          placeholder="请输入对应的人员表里的人员ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item>
        <el-button type="cyan"
                   icon="el-icon-search"
                   size="mini"
                   @click="handleQuery">搜索</el-button>
        <!-- <el-button icon="el-icon-refresh"
                   size="mini"
                   @click="resetQuery">重置</el-button> -->
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAdd"
                   v-hasPermi="['passManage:pass_personnel:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['passManage:pass_personnel:remove']">删除</el-button>
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10"
            class="mb8">
      <el-col :span="1.5">
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAdd"
                   v-hasPermi="['passManage:pass_personnel:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['passManage:pass_personnel:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['passManage:pass_personnel:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['passManage:pass_personnel:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading"
              stripe
              :height="dataHeight"
              :data="pass_personnelList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <!-- <el-table-column
        label="对应的人员表里的人员ID"
        align="center"
        prop="personId"
      /> -->
      <el-table-column label="姓名"
                       align="center"
                       prop="name" />
      <el-table-column label="性别"
                       align="center"
                       prop="genderCode"
                       :formatter="genderCodeFormat" />
      <!-- <el-table-column label="社区id"
                       align="center"
                       prop="communityId" /> -->
      <el-table-column label="社区名称"
                       align="center"
                       prop="communityId"
                       :formatter="communityNameFormat" />
      <!-- <el-table-column label="小区id"
                       align="center"
                       prop="communityChildId" /> -->
      <el-table-column label="小区名称"
                       align="center"
                       prop="communityChildName" />
      <!-- <el-table-column label="楼栋id"
                       align="center"
                       prop="communityBuildingId" /> -->
      <el-table-column label="楼栋名称"
                       align="center"
                       prop="communityBuildingName" />
      <!-- <el-table-column label="房屋id"
                       align="center"
                       prop="communityHousesId" /> -->
      <el-table-column label="房屋名称"
                       align="center"
                       prop="communityHousesName" />

      <el-table-column label="设备编码"
                       align="center"
                       prop="deviceId" />
      <!-- <el-table-column label="人脸出现时间" align="center" prop="faceAppearTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.faceAppearTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="人脸消失时间" align="center" prop="faceDisAppearTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.faceDisAppearTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->

      <!-- <el-table-column label="眼镜颜色" align="center" prop="glassColor" /> -->
      <!-- <el-table-column label="发色" align="center" prop="hairColor" /> -->
      <el-table-column label="证件号码"
                       align="center"
                       prop="idnumber" />
      <!-- <el-table-column label="证件种类" align="center" prop="idType" :formatter="idTypeFormat" /> -->
      <el-table-column label="位置标记时间"
                       align="center"
                       prop="locationMarkTime"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.locationMarkTime) }}</span>
        </template>
      </el-table-column>

      <!-- <el-table-column label="国籍代码" align="center" prop="nationalityCode" :formatter="nationalityCodeFormat" /> -->
      <!-- <el-table-column label="居住地行政区划" align="center" prop="residenceAdminDivision" /> -->
      <el-table-column label="操作"
                       align="center"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-location-information"
                     @click="passLocation(scope.row)">定位</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['passManage:pass_personnel:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['passManage:pass_personnel:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改人员通行对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="500px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="80px">
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
        <el-form-item label="年龄下限"
                      prop="ageLowerLimit">
          <el-input v-model="form.ageLowerLimit"
                    placeholder="请输入年龄下限" />
        </el-form-item>
        <el-form-item label="年龄上限"
                      prop="ageUpLimit">
          <el-input v-model="form.ageUpLimit"
                    placeholder="请输入年龄上限" />
        </el-form-item>
        <el-form-item label="绰号"
                      prop="alias">
          <el-input v-model="form.alias"
                    placeholder="请输入绰号" />
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
        <el-form-item label="汉语口音代码"
                      prop="chineseAccentCode">
          <el-input v-model="form.chineseAccentCode"
                    placeholder="请输入汉语口音代码" />
        </el-form-item>
        <el-form-item label="楼栋id"
                      prop="communityBuildingId">
          <el-input v-model="form.communityBuildingId"
                    placeholder="请输入楼栋id" />
        </el-form-item>
        <el-form-item label="楼栋名称"
                      prop="communityBuildingName">
          <el-input v-model="form.communityBuildingName"
                    placeholder="请输入楼栋名称" />
        </el-form-item>
        <el-form-item label="小区id"
                      prop="communityChildId">
          <el-input v-model="form.communityChildId"
                    placeholder="请输入小区id" />
        </el-form-item>
        <el-form-item label="小区名称"
                      prop="communityChildName">
          <el-input v-model="form.communityChildName"
                    placeholder="请输入小区名称" />
        </el-form-item>
        <el-form-item label="房屋id"
                      prop="communityHousesId">
          <el-input v-model="form.communityHousesId"
                    placeholder="请输入房屋id" />
        </el-form-item>
        <el-form-item label="房屋名称"
                      prop="communityHousesName">
          <el-input v-model="form.communityHousesName"
                    placeholder="请输入房屋名称" />
        </el-form-item>
        <el-form-item label="社区id"
                      prop="communityId">
          <el-input v-model="form.communityId"
                    placeholder="请输入社区id" />
        </el-form-item>
        <el-form-item label="社区名称"
                      prop="communityName">
          <el-input v-model="form.communityName"
                    placeholder="请输入社区名称" />
        </el-form-item>
        <el-form-item label="尸体状况代码"
                      prop="corpseConditionCode">
          <el-input v-model="form.corpseConditionCode"
                    placeholder="请输入尸体状况代码" />
        </el-form-item>
        <el-form-item label="作案特点代码"
                      prop="crimeCharacterCode">
          <el-input v-model="form.crimeCharacterCode"
                    placeholder="请输入作案特点代码" />
        </el-form-item>
        <el-form-item label="作案手段"
                      prop="crimeMethod">
          <el-input v-model="form.crimeMethod"
                    placeholder="请输入作案手段" />
        </el-form-item>
        <el-form-item label="涉案人员专长代码"
                      prop="criminalInvolvedSpecilisationCode">
          <el-input v-model="form.criminalInvolvedSpecilisationCode"
                    placeholder="请输入涉案人员专长代码" />
        </el-form-item>
        <el-form-item label="在押人员身份"
                      prop="detaineesIdentity">
          <el-input v-model="form.detaineesIdentity"
                    placeholder="请输入在押人员身份" />
        </el-form-item>
        <el-form-item label="在押人员特殊身份"
                      prop="detaineesSpecialIdentity">
          <el-input v-model="form.detaineesSpecialIdentity"
                    placeholder="请输入在押人员特殊身份" />
        </el-form-item>
        <el-form-item label="看守所编码"
                      prop="detentionHouseCode">
          <el-input v-model="form.detentionHouseCode"
                    placeholder="请输入看守所编码" />
        </el-form-item>
        <el-form-item label="设备编码"
                      prop="deviceId">
          <el-input v-model="form.deviceId"
                    placeholder="请输入设备编码" />
        </el-form-item>
        <el-form-item label="在逃人员编号"
                      prop="escapedCriminalNumber">
          <el-input v-model="form.escapedCriminalNumber"
                    placeholder="请输入在逃人员编号" />
        </el-form-item>
        <el-form-item label="民族代码"
                      prop="ethicCode">
          <el-input v-model="form.ethicCode"
                    placeholder="请输入民族代码" />
        </el-form-item>
        <el-form-item label="眉型"
                      prop="eyebrowStyle">
          <el-input v-model="form.eyebrowStyle"
                    placeholder="请输入眉型" />
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
        <el-form-item label="黑痣胎记"
                      prop="freckleBirthmark">
          <el-input v-model="form.freckleBirthmark"
                    placeholder="请输入黑痣胎记" />
        </el-form-item>
        <el-form-item label="性别"
                      prop="genderCode">
          <el-select v-model="form.genderCode"
                     placeholder="请选择性别">
            <el-option v-for="dict in genderCodeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
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
        <el-form-item label="出入境人员类别代码"
                      prop="immigrantTypeCode">
          <el-input v-model="form.immigrantTypeCode"
                    placeholder="请输入出入境人员类别代码" />
        </el-form-item>
        <el-form-item label="信息分类"
                      prop="infoKind">
          <el-input v-model="form.infoKind"
                    placeholder="请输入信息分类" />
        </el-form-item>
        <el-form-item label="受伤害程度"
                      prop="injuredDegree">
          <el-input v-model="form.injuredDegree"
                    placeholder="请输入受伤害程度" />
        </el-form-item>
        <el-form-item label="是否涉案人员"
                      prop="isCriminalInvolved">
          <el-input v-model="form.isCriminalInvolved"
                    placeholder="请输入是否涉案人员" />
        </el-form-item>
        <el-form-item label="是否在押人员"
                      prop="isDetainees">
          <el-input v-model="form.isDetainees"
                    placeholder="请输入是否在押人员" />
        </el-form-item>
        <el-form-item label="是否驾驶员"
                      prop="isDriver">
          <el-input v-model="form.isDriver"
                    placeholder="请输入是否驾驶员" />
        </el-form-item>
        <el-form-item label="是否涉外人员"
                      prop="isForeigner">
          <el-input v-model="form.isForeigner"
                    placeholder="请输入是否涉外人员" />
        </el-form-item>
        <el-form-item label="是否涉恐人员"
                      prop="isSuspectedTerrorist">
          <el-input v-model="form.isSuspectedTerrorist"
                    placeholder="请输入是否涉恐人员" />
        </el-form-item>
        <el-form-item label="是否可疑人"
                      prop="isSuspiciousPerson">
          <el-input v-model="form.isSuspiciousPerson"
                    placeholder="请输入是否可疑人" />
        </el-form-item>
        <el-form-item label="是否被害人"
                      prop="isVictim">
          <el-input v-model="form.isVictim"
                    placeholder="请输入是否被害人" />
        </el-form-item>
        <el-form-item label="职业类别代码"
                      prop="jobCategory">
          <el-input v-model="form.jobCategory"
                    placeholder="请输入职业类别代码" />
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
        <el-form-item label="姓名"
                      prop="name">
          <el-input v-model="form.name"
                    placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="国籍代码"
                      prop="nationalityCode">
          <el-select v-model="form.nationalityCode"
                     placeholder="请选择国籍代码">
            <el-option v-for="dict in nationalityCodeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="籍贯省市县代码"
                      prop="nativeCityCode">
          <el-input v-model="form.nativeCityCode"
                    placeholder="请输入籍贯省市县代码" />
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
        <el-form-item label="护照证件种类"
                      prop="passportType">
          <el-select v-model="form.passportType"
                     placeholder="请选择护照证件种类">
            <el-option label="请选择字典生成"
                       value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="体貌特征"
                      prop="physicalFeature">
          <el-input v-model="form.physicalFeature"
                    placeholder="请输入体貌特征" />
        </el-form-item>
        <el-form-item label="居住地行政区划"
                      prop="residenceAdminDivision">
          <el-input v-model="form.residenceAdminDivision"
                    placeholder="请输入居住地行政区划" />
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
        <el-form-item label="人脸相似度"
                      prop="similaritydegree">
          <el-input v-model="form.similaritydegree"
                    placeholder="请输入人脸相似度" />
        </el-form-item>
        <el-form-item label="肤色"
                      prop="skinColor">
          <el-input v-model="form.skinColor"
                    placeholder="请输入肤色" />
        </el-form-item>
        <el-form-item label="来源标识"
                      prop="sourceId">
          <el-input v-model="form.sourceId"
                    placeholder="请输入来源标识" />
        </el-form-item>
        <el-form-item label="涉恐人员编号"
                      prop="suspectedTerroristNumber">
          <el-input v-model="form.suspectedTerroristNumber"
                    placeholder="请输入涉恐人员编号" />
        </el-form-item>
        <el-form-item label="曾用名"
                      prop="usedName">
          <el-input v-model="form.usedName"
                    placeholder="请输入曾用名" />
        </el-form-item>
        <el-form-item label="被害人种类"
                      prop="victimType">
          <el-select v-model="form.victimType"
                     placeholder="请选择被害人种类">
            <el-option label="请选择字典生成"
                       value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="皱纹眼袋"
                      prop="wrinklePouch">
          <el-input v-model="form.wrinklePouch"
                    placeholder="请输入皱纹眼袋" />
        </el-form-item>
        <el-form-item label="对应的人员表里的人员ID"
                      prop="personId">
          <el-input v-model="form.personId"
                    placeholder="请输入对应的人员表里的人员ID" />
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
import {
  listPass_personnel,
  getPass_personnel,
  delPass_personnel,
  addPass_personnel,
  updatePass_personnel,
  exportPass_personnel,
  listCarePass_personnel,
} from "@/api/passManage/pass_personnel";
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_child } from "@/api/communityUnit/community_child";
export default {
  name: "Pass_personnel",
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
      // 人员通行表格数据
      pass_personnelList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 性别字典
      genderCodeOptions: [],
      // 关爱类型字典
      typeOptions: [],
      // 证件种类字典
      idTypeOptions: [],
      // 国籍代码字典
      nationalityCodeOptions: [],
      // 社区
      communityOptions: [],
      //小区列表
      communityChildOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: null, //关爱人员类型
        faceId: null,
        accompanyNumber: null,
        acneStain: null,
        ageLowerLimit: null,
        ageUpLimit: null,
        alias: null,
        attitude: null,
        bodySpeciallMark: null,
        capColor: null,
        capStyle: null,
        chineseAccentCode: null,
        communityBuildingId: null,
        communityBuildingName: null,
        communityChildId: null,
        communityChildName: null,
        communityHousesId: null,
        communityHousesName: null,
        communityId: null,
        communityName: null,
        corpseConditionCode: null,
        crimeCharacterCode: null,
        crimeMethod: null,
        criminalInvolvedSpecilisationCode: null,
        detaineesIdentity: null,
        detaineesSpecialIdentity: null,
        detentionHouseCode: null,
        deviceId: null,
        escapedCriminalNumber: null,
        ethicCode: null,
        eyebrowStyle: null,
        faceAppearTime: null,
        faceDisAppearTime: null,
        faceStyle: null,
        facialFeature: null,
        freckleBirthmark: null,
        genderCode: null,
        glassColor: null,
        glassStyle: null,
        hairColor: null,
        hairStyle: null,
        idnumber: null,
        idType: null,
        immigrantTypeCode: null,
        infoKind: null,
        injuredDegree: null,
        isCriminalInvolved: null,
        isDetainees: null,
        isDriver: null,
        isForeigner: null,
        isSuspectedTerrorist: null,
        isSuspiciousPerson: null,
        isVictim: null,
        jobCategory: null,
        leftTopX: null,
        leftTopY: null,
        lipStyle: null,
        locationMarkTime: null,
        memberTypeCode: null,
        mustacheStyle: null,
        name: null,
        nationalityCode: null,
        nativeCityCode: null,
        noseStyle: null,
        otherFeature: null,
        passportType: null,
        physicalFeature: null,
        residenceAdminDivision: null,
        respiratorColor: null,
        rightBtmX: null,
        rightBtmY: null,
        scarDimple: null,
        similaritydegree: null,
        skinColor: null,
        sourceId: null,
        suspectedTerroristNumber: null,
        usedName: null,
        victimType: null,
        wrinklePouch: null,
        personId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        personId: [
          {
            required: true,
            message: "对应的人员表里的人员ID不能为空",
            trigger: "blur",
          },
        ],
      },
      // 搜索表单控件高度
      formHeight: 0,
    };
  },
  created () {
    this.getCommunityDictory();
    this.getDicts("sys_user_sex").then((response) => {
      this.genderCodeOptions = response.data;
    });
    this.getDicts("base_type_of_caring").then((response) => {
      this.typeOptions = response.data;
    });
    this.getDicts("base_idCard").then((response) => {
      this.idTypeOptions = response.data;
    });
    this.getDicts("base_nationality").then((response) => {
      this.nationalityCodeOptions = response.data;
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
      if (this.showSearch) searchFormHeight = this.formHeight;
      return this.$store.getters.dataEleHeight - searchFormHeight - 115;
    },
  },
  methods: {
    //通行数据定位
    passLocation (row) {
      let passLoacation = { //通行记录定位
        cameraSn: row.deviceId, //设备编号
        passType: 1, //通行类型 1、人 2、车
        passTime: row.locationMarkTime, //抓拍时间
        passImage: row.pics, //抓拍图片
        passName: row.name, //车牌号或者人的姓名
        passAddress: '', //抓拍地点
      };
      console.log('location', passLoacation);
      this.$Notice.$emit('passLoacation', passLoacation);
    },
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
      if (this.communityChildOptions && this.communityChildOptions.length > 0) {
        let rows = this.communityChildOptions.filter((item) => {
          return item.id === cellValue;
        });
        if (rows && rows.length > 0) {
          return rows[0].name;
        }
      }
      return "??";
    },
    /** 查询人员通行列表 */
    getList () {
      this.loading = true;
      listCarePass_personnel(this.queryParams).then((response) => {
        this.pass_personnelList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 性别字典翻译
    genderCodeFormat (row, column) {
      return this.selectDictLabel(this.genderCodeOptions, row.genderCode);
    },
    // 证件种类字典翻译
    idTypeFormat (row, column) {
      return this.selectDictLabel(this.idTypeOptions, row.idType);
    },
    // 国籍代码字典翻译
    nationalityCodeFormat (row, column) {
      return this.selectDictLabel(
        this.nationalityCodeOptions,
        row.nationalityCode
      );
    },
    // 取消按钮
    cancel () {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset () {
      this.form = {
        faceId: null,
        accompanyNumber: null,
        acneStain: null,
        ageLowerLimit: null,
        ageUpLimit: null,
        alias: null,
        attitude: null,
        bodySpeciallMark: null,
        capColor: null,
        capStyle: null,
        chineseAccentCode: null,
        communityBuildingId: null,
        communityBuildingName: null,
        communityChildId: null,
        communityChildName: null,
        communityHousesId: null,
        communityHousesName: null,
        communityId: null,
        communityName: null,
        corpseConditionCode: null,
        crimeCharacterCode: null,
        crimeMethod: null,
        criminalInvolvedSpecilisationCode: null,
        detaineesIdentity: null,
        detaineesSpecialIdentity: null,
        detentionHouseCode: null,
        deviceId: null,
        escapedCriminalNumber: null,
        ethicCode: null,
        eyebrowStyle: null,
        faceAppearTime: null,
        faceDisAppearTime: null,
        faceStyle: null,
        facialFeature: null,
        freckleBirthmark: null,
        genderCode: null,
        glassColor: null,
        glassStyle: null,
        hairColor: null,
        hairStyle: null,
        idnumber: null,
        idType: null,
        immigrantTypeCode: null,
        infoKind: null,
        injuredDegree: null,
        isCriminalInvolved: null,
        isDetainees: null,
        isDriver: null,
        isForeigner: null,
        isSuspectedTerrorist: null,
        isSuspiciousPerson: null,
        isVictim: null,
        jobCategory: null,
        leftTopX: null,
        leftTopY: null,
        lipStyle: null,
        locationMarkTime: null,
        memberTypeCode: null,
        mustacheStyle: null,
        name: null,
        nationalityCode: null,
        nativeCityCode: null,
        noseStyle: null,
        otherFeature: null,
        passportType: null,
        physicalFeature: null,
        residenceAdminDivision: null,
        respiratorColor: null,
        rightBtmX: null,
        rightBtmY: null,
        scarDimple: null,
        similaritydegree: null,
        skinColor: null,
        sourceId: null,
        suspectedTerroristNumber: null,
        usedName: null,
        victimType: null,
        wrinklePouch: null,
        personId: null,
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
      this.ids = selection.map((item) => item.faceId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset();
      this.open = true;
      this.title = "添加人员通行";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const faceId = row.faceId || this.ids;
      getPass_personnel(faceId).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改人员通行";
      });
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.faceId != null) {
            updatePass_personnel(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addPass_personnel(this.form).then((response) => {
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
      const faceIds = row.faceId || this.ids;
      this.$confirm(
        '是否确认删除人员通行编号为"' + faceIds + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delPass_personnel(faceIds);
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
      this.$confirm("是否确认导出所有人员通行数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportPass_personnel(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () { });
    },
  },
};
</script>
