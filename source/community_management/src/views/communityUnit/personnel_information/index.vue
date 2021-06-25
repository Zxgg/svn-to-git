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
        <!-- <el-button size="mini"
                   type="text"
                   icon="el-icon-refresh"
                   @click="getCommunityDictory"
                   v-hasPermi="['communityUnit:community:query']">刷新</el-button> -->
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
      <!-- <el-form-item label="人口类型"
                    prop="personType">
        <el-select v-model="queryParams.personType"
                   placeholder="请选择人口类型"
                   clearable
                   size="small">
          <el-option v-for="dict in personTypeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="国籍"
                    v-if="queryParams.personType === '03'"
                    prop="nationality">
        <el-select v-model="queryParams.nationality"
                   placeholder="请选择国籍"
                   clearable
                   size="small">
          <el-option v-for="dict in nationalityOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <el-form-item label="姓名"
                    prop="name">
        <el-input v-model="queryParams.name"
                  placeholder="请输入姓名"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="曾用名"
                    v-show="moreselect"
                    prop="oldName">
        <el-input v-model="queryParams.oldName"
                  placeholder="请输入曾用名"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="性别"
                    prop="sex">
        <el-select v-model="queryParams.sex"
                   placeholder="请选择性别"
                   clearable
                   size="small">
          <el-option v-for="dict in sexOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <!-- <el-form-item label="证件类型"
                    prop="idType">
        <el-select v-model="queryParams.idType"
                   placeholder="请选择证件类型代码"
                   clearable
                   size="small">
          <el-option v-for="dict in idTypeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <el-form-item label="身份证号"
                    prop="identificationNumber">
        <el-input v-model="queryParams.identificationNumber"
                  placeholder="请输入证件号码"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="手机号码"
                    prop="phoneNumber">
        <el-input v-model="queryParams.phoneNumber"
                  placeholder="请输入手机号码"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="民族"
                    v-if="queryParams.personType !== '03'"
                    prop="nation">
        <el-select v-model="queryParams.nation"
                   placeholder="请选择民族"
                   clearable
                   size="small">
          <el-option v-for="dict in nationOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="出生日期"
                    v-show="moreselect"
                    prop="brithDate">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="queryParams.brithDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择出生日期">
        </el-date-picker>
        <label class="el-form-item__label commonTextBlock"
               style="width: auto; padding-right: 0px">--</label>
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="queryParams.brithDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择出生日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="籍贯"
                    v-show="moreselect"
                    v-if="queryParams.personType !== '03'"
                    prop="location">
        <el-input v-model="queryParams.location"
                  placeholder="请输入籍贯"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="户籍详址"
                    v-show="moreselect"
                    prop="residenceDetail">
        <el-input v-model="queryParams.residenceDetail"
                  placeholder="请输入户籍详址"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="现住地"
                    v-show="moreselect"
                    prop="address">
        <el-input v-model="queryParams.address"
                  placeholder="请输入现住地区划"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="现住详址"
                    prop="addressDetail">
        <el-input v-model="queryParams.addressDetail"
                  placeholder="请输入现住详址"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="是否已婚"
                    v-show="moreselect"
                    prop="marital">
        <el-select v-model="queryParams.marital"
                   placeholder="请选择是否已婚"
                   clearable
                   size="small">
          <el-option v-for="dict in yesNoOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="政治面貌"
                    v-show="moreselect"
                    v-if="queryParams.personType !== '03'"
                    prop="political">
        <el-select v-model="queryParams.political"
                   placeholder="请选择政治面貌"
                   clearable
                   size="small">
          <el-option v-for="dict in politicalOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="学历"
                    v-show="moreselect"
                    prop="degree">
        <el-select v-model="queryParams.degree"
                   placeholder="请选择学历"
                   clearable
                   size="small">
          <el-option v-for="dict in degreeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="宗教信仰"
                    v-show="moreselect"
                    prop="religion">
        <el-select v-model="queryParams.religion"
                   placeholder="请选择宗教信仰"
                   clearable
                   size="small">
          <el-option v-for="dict in religionOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="职业类别"
                    v-show="moreselect"
                    prop="profession">
        <el-input v-model="queryParams.profession"
                  placeholder="请输入职业类别"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="职业信息"
                    v-show="moreselect"
                    prop="professioDetail">
        <el-input v-model="queryParams.professioDetail"
                  placeholder="请输入职业信息"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
     -->
      <!-- 流动人员 -->
      <!-- <el-row v-show="moreselect"
              v-if="queryParams.personType === '02'"
              class="commonBorder">
        <el-row>
          <label class="el-form-item__label commonTextBlock"
                 style="width: auto">流动人员信息</label>
        </el-row>
        <el-form-item label="流入原因"
                      prop="floatingReasons">
          <el-input v-model="queryParams.floatingReasons"
                    placeholder="请输入流入原因"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery" />
        </el-form-item>
        <el-form-item label="最后入境"
                      prop="arrivalProvinceDate">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="queryParams.arrivalProvinceDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择最后入境">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="办证类型"
                      label-width="auto"
                      prop="cardType">
          <el-select v-model="queryParams.cardType"
                     placeholder="请选择流入地办证类型"
                     clearable
                     size="small">
            <el-option label="请选择字典生成"
                       value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="办证机关"
                      label-width="auto"
                      prop="cardHandlingAgency">
          <el-input v-model="queryParams.cardHandlingAgency"
                    placeholder="请输入流入地办证机关"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery" />
        </el-form-item>
        <el-form-item label="登记日期"
                      label-width="auto"
                      prop="cardRecordDate">
          <el-input v-model="queryParams.cardRecordDate"
                    placeholder="请输入流入地证件登记日期"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery" />
        </el-form-item>
        <el-form-item label="证件号"
                      label-width="auto"
                      prop="cardNum">
          <el-input v-model="queryParams.cardNum"
                    placeholder="请输入流入地证件号码"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery" />
        </el-form-item>
      </el-row> -->
      <!-- 国外人员 -->
      <!-- <el-row v-if="queryParams.personType === '03'"
              v-show="moreselect"
              class="commonBorder">
        <el-row>
          <label class="el-form-item__label commonTextBlock"
                 style="width: auto">国外人员入境信息</label>
        </el-row> -->
      <!-- <el-form-item label="最后入国境"
                      prop="arrivalCountryDate">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="queryParams.arrivalCountryDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择最后入境时间">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="预计离开日期"
                      label-width="auto"
                      prop="departureDate">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="queryParams.departureDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择预计离开日期">
          </el-date-picker>
        </el-form-item>
      </el-row>
      -->
      <!-- <el-form-item label="管控人员"
                    prop="limited">
        <el-select v-model="queryParams.limited"
                   class="comboxMiddle"
                   placeholder="请选择管控限制人员"
                   clearable
                   size="small">
          <el-option v-for="dict in yesNoOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <!-- 
      <el-form-item label="留守人员"
                    prop="stayBehind">
        <el-select v-model="queryParams.stayBehind"
                   class="comboxMiddle"
                   placeholder="请选择留守人员类型"
                   clearable
                   size="small">
          <el-option v-for="dict in stayBehindOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->

      <!-- <el-form-item label="审核"
                    prop="checked">
        <el-select v-model="queryParams.checked"
                   class="comboxMiddle"
                   placeholder="请选择是否已经审核确认"
                   clearable
                   size="small">
          <el-option v-for="dict in yesNoOptions"
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
                   v-hasPermi="['communityUnit:personnel_information:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityUnit:personnel_information:remove']">删除</el-button>
        <!-- <el-button icon="el-icon-refresh"
                   size="mini"
                   @click="resetQuery">重置</el-button> -->
      </el-form-item>
      <el-row>
        <el-form-item label="关爱人员标签"
                      label-width="auto"
                      prop="caring">
          <el-select v-model="queryParams.caring"
                     placeholder="请选择关爱人员标签"
                     clearable
                     size="small">
            <el-option v-for="dict in yesNoOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="特殊人员标签"
                      label-width="auto"
                      prop="special">
          <el-select v-model="isSpecial"
                     placeholder="请选择特殊人员标签"
                     clearable
                     size="small">
            <el-option v-for="dict in yesNoOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="自定义标签"
                      label-width="auto"
                      prop="customTag">
          <el-select v-model="queryParams.customTag"
                     placeholder="请选择自定义标签"
                     clearable
                     size="small">
            <el-option v-for="dict in customTagOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
      </el-row>
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
                   v-hasPermi="['communityUnit:personnel_information:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityUnit:personnel_information:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['communityUnit:personnel_information:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->
    <!-- <div class='tableClass'> -->
    <el-table v-loading="loading"
              stripe
              highlight-current-row
              :data="personnel_informationList"
              :height="dataHeight"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="姓名"
                       align="center"
                       width="120"
                       prop="name" />
      <!-- <el-table-column label="曾用名"
                       align="center"
                       prop="oldName" /> -->
      <el-table-column label="性别"
                       align="center"
                       prop="sex"
                       width="50"
                       :formatter="sexFormat" />
      <el-table-column label="国籍"
                       v-if="searchPersonType === '03'"
                       align="center"
                       prop="nationality"
                       :formatter="nationalityFormat" />
      <el-table-column label="证件类型"
                       align="center"
                       v-if="searchPersonType === '03'"
                       prop="idType"
                       width="80"
                       :formatter="idTypeFormat" />
      <el-table-column label="证件号码"
                       align="center"
                       width="160px"
                       prop="identificationNumber" />
      <!-- <el-table-column label="出生日期"
                       align="center"
                       prop="brithDate"
                       width="auto">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.brithDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="证件有效期开始时间"
                       align="center"
                       prop="idcardStartDate" />
                       
      <el-table-column label="证件有效期结束时间"
                       align="center"
                       prop="idcardEndDate" />
      <el-table-column label="民族"
                       align="center"
                       prop="nation"
                       :formatter="nationFormat" /> -->
      <el-table-column label="籍贯"
                       v-if="searchPersonType !== '03'"
                       align="center"
                       width="100"
                       prop="location" />
      <!-- <el-table-column label="户籍详址"
                       align="center"
                       prop="residenceDetail" /> -->
      <el-table-column label="现住地"
                       align="center"
                       width="140"
                       prop="address" />
      <el-table-column label="现住详址"
                       align="center"
                       prop="addressDetail" />
      <!-- <el-table-column label="是否已婚"
                       align="center"
                       prop="marital"
                       :formatter="yesNoFormat" /> -->
      <!-- <el-table-column label="政治面貌"
                       align="center"
                       prop="political"
                       :formatter="politicalFormat" /> -->
      <!-- <el-table-column label="学历"
                       align="center"
                       prop="degree"
                       :formatter="degreeFormat" /> -->
      <!-- <el-table-column label="宗教信仰"
                       align="center"
                       prop="religion"
                       :formatter="religionFormat" /> -->
      <!-- <el-table-column label="职业类别"
                       align="center"
                       prop="profession" /> -->
      <!-- <el-table-column label="职业信息"
                       align="center"
                       prop="professioDetail" /> -->
      <el-table-column label="手机号码"
                       align="center"
                       width="120"
                       prop="phoneNumber" />
      <!-- <el-table-column label="流动人员"
                       align="center"
                       prop="floating"
                       :formatter="yesNoFormat" /> -->
      <el-table-column label="流入原因"
                       v-if="searchPersonType === '02'"
                       align="center"
                       prop="floatingReasons" />
      <el-table-column label="办证类型"
                       v-if="searchPersonType === '02'"
                       align="center"
                       prop="cardType" />
      <el-table-column label="办证机关"
                       v-if="searchPersonType === '02'"
                       align="center"
                       prop="cardHandlingAgency" />
      <el-table-column label="证件号码"
                       v-if="searchPersonType === '02'"
                       align="center"
                       prop="cardNum" />
      <!-- <el-table-column label="登记日期"
                       v-if="searchPersonType==='02'"
                       align="center"
                       prop="cardRecordDate" /> -->
      <!-- <el-table-column label="最后入境"
                       v-if="searchPersonType==='02'"
                       align="center"
                       prop="arrivalProvinceDate"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.arrivalProvinceDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="最后入国境"
                       v-if="searchPersonType==='03'"
                       align="center"
                       prop="arrivalCountryDate"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.arrivalCountryDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="预计离开日期"
                       v-if="searchPersonType==='03'"
                       align="center"
                       prop="departureDate"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.departureDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->

      <!-- <el-table-column label="特殊人员"
                       align="center"
                       prop="special"
                       :formatter="yesNoFormat" />
      <el-table-column label="管控人员"
                       align="center"
                       prop="limited"
                       :formatter="yesNoFormat" />
      <el-table-column label="关爱人员"
                       align="center"
                       prop="caring"
                       :formatter="yesNoFormat" /> -->
      <el-table-column label="照片"
                       align="center"
                       width="110"
                       prop="pics">
        <template slot-scope="scope">
          <el-image v-if="scope.row.pics && scope.row.pics !== ''"
                    style="width: 100px; height: 80px"
                    :src="getImgUrl(scope.row.pics)"
                    fit="fill"></el-image>
          <!-- scope.row.pics -->
          <div v-else>无图</div>
        </template>
      </el-table-column>
      <!-- <el-table-column label="备注"
                       align="center"
                       prop="remark" /> -->
      <el-table-column label="审核"
                       align="center"
                       prop="checked"
                       width="80">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     :disabled="scope.row.checked === '1'"
                     @click="handleUpdate_check(scope.row)"
                     v-hasPermi="['communityUnit:personnel_information:edit']">{{ scope.row.checked | checkedFilters(that) }}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作"
                       width="165"
                       align="center"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-attract"
                     @click="relatedHouses(scope.row)"
                     v-hasPermi="['communityUnit:personnel_information:related']">关联房屋</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-attract"
                     @click="relatedVehicles(scope.row)"
                     v-hasPermi="['communityUnit:personnel_information:related']">关联车辆</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-view"
                     @click="getDetail(scope.row)">详情</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-price-tag"
                     @click="specialTag(scope.row)">特殊人员标签</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-price-tag"
                     @click="careTag(scope.row)">关爱人员标签</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-location-information"
                     @click="getPassList(scope.row.id)">通行</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['communityUnit:personnel_information:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['communityUnit:personnel_information:remove']">删除</el-button>

        </template>
      </el-table-column>
    </el-table>
    <personTarck :trackData="trackObject" />

    <!-- <template>
      <div>
        <child @resetTrack="getPassList($event)"></child>
      </div>
    </template> -->
    <!-- </div> -->

    <pagination v-show="total > 0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改人员信息对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="600px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="140px">
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
          <el-button size="mini"
                     type="text"
                     icon="el-icon-refresh"
                     @click="getCommunityDictory"
                     v-hasPermi="['communityUnit:community:query']">刷新</el-button>
        </el-form-item>
        <el-form-item label="小区名称"
                      prop="communityChildId">
          <el-select v-model="form.communityChildId"
                     filterable
                     clearable
                     :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                     placeholder="请选择小区">
            <el-option v-for="dict in communityChildOptions2"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="人口类型"
                      prop="personType">
          <el-select v-model="form.personType"
                     placeholder="请选择人口类型"
                     clearable
                     size="small">
            <el-option v-for="dict in personTypeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="姓名"
                      prop="name">
          <el-input v-model="form.name"
                    placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="曾用名"
                      prop="oldName">
          <el-input v-model="form.oldName"
                    placeholder="请输入曾用名" />
        </el-form-item>
        <el-form-item label="性别"
                      prop="sex">
          <el-select v-model="form.sex"
                     placeholder="请选择性别">
            <el-option v-for="dict in sexOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="国籍"
                      v-if="form.personType === '03'"
                      prop="nationality">
          <el-select v-model="form.nationality"
                     placeholder="请选择国籍">
            <el-option v-for="dict in nationalityOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="证件类型"
                      prop="idType">
          <el-select v-model="form.idType"
                     placeholder="请选择证件类型代码">
            <el-option v-for="dict in idTypeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="证件号码"
                      prop="identificationNumber">
          <el-input v-model="form.identificationNumber"
                    placeholder="请输入证件号码" />
        </el-form-item>
        <el-form-item label="证件有效期开始时间"
                      prop="idcardStartDate">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.idcardStartDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择证件有效期开始时间" />
        </el-form-item>
        <el-form-item label="证件有效期结束时间"
                      prop="idcardEndDate">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.idcardEndDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择证件有效期开始时间" />
        </el-form-item>
        <el-form-item label="出生日期"
                      prop="brithDate">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.brithDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择出生日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="民族"
                      v-if="form.personType !== '03'"
                      prop="nation">
          <el-select v-model="form.nation"
                     placeholder="请选择民族">
            <el-option v-for="dict in nationOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="户号"
                      v-if="form.personType !== '03'"
                      prop="doorNo">
          <el-input v-model="form.doorNo"
                    placeholder="请输入户号" />
        </el-form-item>
        <el-form-item label="户主"
                      v-if="form.personType !== '03'"
                      prop="householdName">
          <el-input v-model="form.householdName"
                    placeholder="请输入户主姓名" />
        </el-form-item>
        <el-form-item label="户号身份证号码"
                      v-if="form.personType !== '03'"
                      prop="householdIdc">
          <el-input v-model="form.householdIdc"
                    placeholder="请输入户号身份证号码" />
        </el-form-item>
        <el-form-item label="户号手机号"
                      v-if="form.personType !== '03'"
                      prop="householdPhone">
          <el-input v-model="form.householdPhone"
                    placeholder="请输入户号手机号" />
        </el-form-item>
        <el-form-item label="与户主关系"
                      v-if="form.personType !== '03'"
                      prop="householdRelation">
          <el-select v-model="form.householdRelation"
                     placeholder="请选择与户主关系">
            <el-option v-for="dict in householdRelationOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="籍贯"
                      v-if="form.personType !== '03'"
                      prop="location">
          <el-input v-model="form.location"
                    placeholder="请输入籍贯" />
        </el-form-item>
        <el-form-item label="户籍详址"
                      v-if="form.personType !== '03'"
                      prop="residenceDetail">
          <el-input v-model="form.residenceDetail"
                    placeholder="请输入户籍详址" />
        </el-form-item>
        <el-form-item label="现住地"
                      prop="address">
          <el-input v-model="form.address"
                    placeholder="请输入现住地区划" />
        </el-form-item>
        <el-form-item label="现住详址"
                      prop="addressDetail">
          <el-input v-model="form.addressDetail"
                    placeholder="请输入现住详址" />
        </el-form-item>
        <el-form-item label="是否已婚"
                      prop="marital">
          <el-select v-model="form.marital"
                     placeholder="请选择是否已婚">
            <el-option v-for="dict in yesNoOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="政治面貌"
                      v-if="form.personType !== '03'"
                      prop="political">
          <el-select v-model="form.political"
                     placeholder="请选择政治面貌">
            <el-option v-for="dict in politicalOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学历"
                      prop="degree">
          <el-select v-model="form.degree"
                     placeholder="请选择学历">
            <el-option v-for="dict in degreeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="宗教信仰"
                      prop="religion">
          <el-select v-model="form.religion"
                     placeholder="请选择宗教信仰">
            <el-option v-for="dict in religionOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="职业类别"
                      prop="profession">
          <el-input v-model="form.profession"
                    placeholder="请输入职业类别" />
        </el-form-item>
        <el-form-item label="职业信息"
                      prop="professioDetail">
          <el-input v-model="form.professioDetail"
                    placeholder="请输入职业信息" />
        </el-form-item>
        <el-form-item label="手机号码"
                      prop="phoneNumber">
          <el-input v-model="form.phoneNumber"
                    placeholder="请输入手机号码" />
        </el-form-item>

        <el-form-item label="最后入国境"
                      v-if="form.personType === '03'"
                      prop="arrivalCountryDate">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.arrivalCountryDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择最后入国境">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预计离开日期"
                      v-if="form.personType === '03'"
                      prop="departureDate">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.departureDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择预计离开日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="最后入境"
                      v-if="form.personType === '02'"
                      prop="arrivalProvinceDate">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.arrivalProvinceDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择最后入境">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="流入原因"
                      v-if="form.personType === '02'"
                      prop="floatingReasons">
          <el-input v-model="form.floatingReasons"
                    placeholder="请输入流入原因" />
        </el-form-item>
        <el-form-item label="流入地办证类型"
                      v-if="form.personType === '02'"
                      prop="cardType">
          <el-select v-model="form.cardType"
                     placeholder="请选择流入地办证类型">
            <el-option label="请选择字典生成"
                       value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="流入地办证机关"
                      v-if="form.personType === '02'"
                      prop="cardHandlingAgency">
          <el-input v-model="form.cardHandlingAgency"
                    placeholder="请输入流入地办证机关" />
        </el-form-item>
        <el-form-item label="流入地证件号码"
                      v-if="form.personType === '02'"
                      prop="cardNum">
          <el-input v-model="form.cardNum"
                    placeholder="请输入流入地证件号码" />
        </el-form-item>
        <el-form-item label="流入地证件登记日期"
                      v-if="form.personType === '02'"
                      prop="cardRecordDate">
          <el-input v-model="form.cardRecordDate"
                    placeholder="请输入流入地证件登记日期" />
        </el-form-item>
        <el-form-item label="照片"
                      prop="pics">
          <!-- <el-input v-model="form.pics"
                    type="textarea"
                    placeholder="请输入内容" /> -->
          <imageListUpload :upLoadUrl="fileUpLoadUrl"
                           :key="form.id"
                           :uploadState='uploadState'
                           :filesJson.sync="form.pics" />
        </el-form-item>
        <el-form-item label="是否留守人员"
                      v-if="form.personType === '01'"
                      prop="stayBehind">
          <el-select v-model="form.stayBehind"
                     placeholder="请选择是否留守人员">
            <el-option v-for="dict in stayBehindOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="特殊人员"
                      prop="special">
          <el-select v-model="form.special"
                     placeholder="请选择是否是特殊人员">
            <el-option v-for="dict in specialOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="管控人员"
                      prop="limited">
          <el-select v-model="form.limited"
                     placeholder="请选择管控限制人员">
            <el-option v-for="dict in yesNoOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item> -->

        <el-form-item label="关爱人员"
                      prop="caring">
          <el-select v-model="form.caring"
                     placeholder="请选择关爱人员">
            <el-option v-for="dict in yesNoOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <!-- 留守人员属性 -->
        <el-form-item label="关爱类型"
                      v-if="form.caring + '' === '1'"
                      prop="type">
          <el-select v-model="form.type"
                     placeholder="请选择关爱类型">
            <el-option v-for="dict in typeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="自定义标签"
                      prop="customTag">
          <el-select v-model="form.customTag"
                     placeholder="请选择自定义标签"
                     clearable
                     size="small">
            <el-option v-for="dict in customTagOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="备注"
                      prop="remark">
          <el-input v-model="form.remark"
                    type="textarea"
                    placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="审核"
                      prop="checked">
          <el-select v-model="form.checked"
                     placeholder="请选择是否审核确认">
            <el-option v-for="dict in yesNoOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button @click="otherSubmitForm"
                   v-if="form.id && form.id.length > 0">另存一条</el-button>
        <el-button type="primary"
                   @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="关联房屋信息"
               :visible.sync="relatedOpen"
               :before-close="beforeCloseRelatedOpen"
               width="90%"
               append-to-body>
      <communityHousesPersonnel :personInfo="personInfo" />
    </el-dialog>
    <el-dialog title="关联车辆信息"
               :visible.sync="relatedVehiclesOpen"
               width="90%"
               append-to-body>
      <vehicleHousesPersonnel :personInfo="personInfo" />
    </el-dialog>
    <!-- 添加特殊人员信息对话框 -->
    <el-dialog :title="title"
               :visible.sync="specialopen"
               width="1200px"
               append-to-body>
      <div class="app-container">
        <el-form ref="specialForm"
                 :model="specialForm"
                 :rules="rules"
                 label-width="140px"
                 size="medium"
                 label-position="right">
          <el-form-item label="特殊人员类型"
                        prop="special">
            <el-select v-model="specialForm.special"
                       placeholder="请选择特殊人员类型"
                       clearable
                       size="small">
              <el-option v-for="dict in specialOptions"
                         :key="dict.dictValue"
                         :label="dict.dictLabel"
                         :value="dict.dictValue"
                         :disabled="dict.disabled" />
            </el-select>
          </el-form-item>
        </el-form>
      </div>
      <div>
        <!-- 刑满释放 -->
        <specialReleasedPeople v-if="specialForm.special==='01'"
                               ref="specialReleasedPeople"
                               :personInfo="specialForm" />
        <!-- 社区矫正人员 -->
        <speciaCorrect v-if="specialForm.special==='02'"
                       ref="speciaCorrect"
                       :personInfo="specialForm" />

        <!-- 肇事肇祸重度精神疾病 -->
        <speciaMental v-if="specialForm.special==='03'"
                      ref="speciaMental"
                      :personInfo="specialForm" />
        <!-- 吸毒人员 -->
        <specialDrugAddicts v-if="specialForm.special==='04'"
                            ref="specialDrugAddicts"
                            :personInfo="specialForm" />
        <!-- 艾滋病患者 -->
        <speciaAids v-if="specialForm.special==='05'"
                    ref="speciaAids"
                    :personInfo="specialForm" />
      </div>
      <div slot="footer"
           class="dialog-footer">
        <el-button type="primary"
                   @click="specialSubmitForm">确 定</el-button>
        <el-button @click="specialcancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 添加关爱人员信息记录 对话框 -->
    <el-dialog :title="title"
               :visible.sync="careopen"
               width="500px"
               append-to-body>
      <el-form ref="careForm"
               :model="careForm"
               :rules="rules"
               label-width="80px">
        <!-- 选择社区 -->
        <el-form-item label="社区名称"
                      prop="communityId">
          <el-select v-model="careForm.communityId"
                     filterable
                     @change="getCommunityChildDictory"
                     placeholder="请选择社区">
            <el-option v-for="dict in communityOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-refresh"
                     @click="getCommunityDictory"
                     v-hasPermi="['communityUnit:community:query']">刷新</el-button>
        </el-form-item>
        <el-form-item label="小区名称"
                      prop="communityChildId">
          <el-select v-model="careForm.communityChildId"
                     filterable
                     placeholder="请选择小区">
            <el-option v-for="dict in communityChildOptions2"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="社区ID"
                      prop="communityId">
          <el-input v-model="form.communityId"
                    placeholder="请输入社区ID" />
        </el-form-item> -->
        <el-form-item label="人员信息"
                      style="width:100%;"
                      prop="personnelId">
          <el-select style="width:900px;"
                     v-model="careForm.personId"
                     filterable
                     :disabled="careForm.id && careForm.id.length>0"
                     remote
                     :remote-method="remotePersonInfoMethod"
                     placeholder="请选择人员姓名">
            <el-option v-for="dict in personOptions"
                       :key="dict.id"
                       :label="dict.name + '   电话:' + dict.phoneNumber + '     id:' + dict.identificationNumber|| ''+ ' ' + dict.serialNumber || ''+'号楼' + dict.unit ||''+'单元' + dict.houseNumber|| ''"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="关爱类型"
                      prop="type">
          <el-select v-model="careForm.type"
                     placeholder="请选择关爱类型">
            <el-option v-for="dict in typeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注"
                      prop="remark">
          <el-input v-model="careForm.remark"
                    type="textarea"
                    placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button type="primary"
                   @click="careSubmitForm">确 定</el-button>
        <el-button @click="specialcancel">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog title="详情信息"
               :visible.sync="detailopen"
               width="50%"
               append-to-body>
      <el-form ref="form"
               :model="detailform"
               label-width="140px"
               :inline="true"
               size="medium"
               label-position="right">
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="社区名称">
              <span>{{detailform.communityName}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="小区名称">
              <span>{{detailform.communityChildName}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="所属网格">
              <span>{{detailform.grid || '--'}}</span>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <household :personInfo="detailform" />
    </el-dialog>
  </div>
</template>

<script>
import {
  listPersonnel_information,
  getPersonnel_information,
  delPersonnel_information,
  addPersonnel_information,
  updatePersonnel_information,
  exportPersonnel_information,
} from "@/api/communityUnit/personnel_information";
import {
  listCaring_person,
  getCaring_person,
  delCaring_person,
  addCaring_person,
  updateCaring_person,
  exportCaring_person,
} from "@/api/caringPerson/caring_person";
import communityHousesPersonnel from "../community_houses_personnel/index";
import vehicleHousesPersonnel from "@/views/communityUnit/vehicle_houses_personnel/index";
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import imageListUpload from "@/components/upload/uploadImageList";
import configInfo from "@/webconfig/configInfo";
import personTarck from "@/views/passManage/pass_track/person_track";

import specialReleasedPeople from '@/views/communityUnit/special_personnel/special_released_people'//刑满释放人员
import speciaAids from '@/views/communityUnit/special_personnel/specia_aids'//艾滋病患者
import speciaCorrect from '@/views/communityUnit/special_personnel/specia_correct'//社区矫正人员
import speciaMental from '@/views/communityUnit/special_personnel/specia_mental'//肇事肇祸重度精神疾病
import specialDrugAddicts from '@/views/communityUnit/special_personnel/special_drug_addicts'//吸毒人员
import { getInfoByName } from "@/api/communityUnit/personnel_information";
import household from "./personnel_household"
export default {
  name: "Personnel_information",
  components: {
    communityHousesPersonnel,
    vehicleHousesPersonnel,
    imageListUpload,
    personTarck,
    specialReleasedPeople,
    speciaAids,
    speciaCorrect,
    speciaMental,
    specialDrugAddicts,
    household
  },
  data () {
    return {
      //通行
      trackObject: {
        personId: null,
        drawShow: false,
      },
      detailform: {},
      that: this,
      // 遮罩层
      loading: true,
      //更多搜索条件
      moreselect: false,
      // 选中数组
      ids: [],
      names: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 是否已经审核确认,(0否1是,默认0)字典
      checkedOptions: [],
      // 人员信息表格数据
      personnel_informationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 性别字典
      sexOptions: [],
      // 国籍字典
      nationalityOptions: [],
      // 证件类型代码字典
      idTypeOptions: [],
      // 民族字典
      nationOptions: [],
      // 是否已婚字典
      yesNoOptions: [],
      // 政治面貌字典
      politicalOptions: [],
      // 学历字典
      degreeOptions: [],
      // 宗教信仰字典
      religionOptions: [],
      // 与户主关系字典
      householdRelationOptions: [],
      // 是否 字典
      yesNoOptions: [],
      // 特殊人员字典
      specialOptions: [],
      //自定义标签字典
      customTagOptions: [],
      //关爱类型
      typeOptions: [],
      //人员类型
      personTypeOptions: [],
      //留守类型
      stayBehindOptions: [],
      //是否是特殊人员
      isSpecial: null,
      // // 特殊人员字典
      // yesNoOptions: [],
      // // 管控限制人员字典
      // yesNoOptions: [],
      // // 关爱人员字典
      // yesNoOptions: [],
      // // 是否是流动人员字典
      // yesNoOptions: [],
      // // 是否已经审核确认字典
      // yesNoOptions: [],
      // 社区
      communityOptions: [],
      //小区列表
      communityChildOptions: [],
      //小区列表
      communityChildOptions2: [],
      searchPersonType: null,
      //删除提示语
      deleteNotces: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        communityId: null,
        communityChildId: null,
        personType: null,
        stayBehind: null,
        name: null,
        oldName: null,
        sex: null,
        // nationality: '156',//中国
        nationality: null,
        // idType: '111',//身份证
        idType: null,
        identificationNumber: null,
        // nation: '01',//汉族
        nation: null,
        location: null,
        residenceDetail: null,
        address: null,
        addressDetail: null,
        marital: null,
        political: null,
        degree: null,
        religion: null,
        profession: null,
        professioDetail: null,
        phoneNumber: null,
        special: null,
        limited: null,
        caring: null,
        floating: null,
        arrivalCountryDate: null,
        arrivalProvinceDate: null,
        departureDate: null,
        floatingReasons: null,
        cardType: null,
        cardHandlingAgency: null,
        cardNum: null,
        cardRecordDate: null,
        pics: null,
        checked: null,
        brithDate: null,
        customTag: null,
        delFlag: "0",
      },
      // 表单参数
      form: {},
      specialForm: {},
      careForm: {},
      specialopen: false,
      careopen: false,
      detailopen: false,
      //关爱人员信息
      caringInfo: {},
      // 表单校验
      rules: {
        communityId: [
          { required: true, message: "社区不能为空", trigger: "blur" },
        ],
        communityChildId: [
          { required: true, message: "小区不能为空", trigger: "blur" },
        ],
        personType: [
          { required: true, message: "人员类型不能为空", trigger: "blur" },
        ],
        name: [
          { required: true, message: "姓名不能为空", trigger: "blur" },
          {
            pattern: /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/,
            message: "请输入正确的姓名，只允许使用字母、数字、汉字、下划线",
            trigger: "blur",
          },
        ],
        sex: [{ required: true, message: "性别不能为空", trigger: "blur" }],
        identificationNumber: [
          { required: true, message: "证件号码不能为空", trigger: "blur" },
          {
            pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
            message: "你的身份证格式不正确",
          },
        ],
        brithDate: [
          { required: true, message: "出生日期不能为空", trigger: "blur" },
        ],
        nation: [{ required: true, message: "民族不能为空", trigger: "blur" }],
        doorNo: [{ required: true, message: "户号不能为空", trigger: "blur" }],
        householdRelation: [
          { required: true, message: "与户主关系不能为空", trigger: "blur" },
        ],
        location: [
          { required: true, message: "籍贯不能为空", trigger: "blur" },
        ],
        residenceDetail: [
          { required: true, message: "户籍地址不能为空", trigger: "blur" },
        ],
        address: [
          { required: true, message: "现住址不能为空", trigger: "blur" },
        ],
        addressDetail: [
          { required: true, message: "现住详址不能为空", trigger: "blur" },
        ],
        marital: [
          { required: true, message: "婚姻状况不能为空", trigger: "blur" },
        ],
        political: [
          { required: true, message: "政治面貌不能为空", trigger: "blur" },
        ],
        degree: [{ required: true, message: "学历不能为空", trigger: "blur" }],
        profession: [
          { required: true, message: "职业类别状况不能为空", trigger: "blur" },
        ],
        professioDetail: [
          { required: true, message: "职业信息不能为空", trigger: "blur" },
        ],
        phoneNumber: [
          { required: true, message: "手机号码不能为空", trigger: "blur" },
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur",
          },
        ],
      },
      // 关联房屋窗口 是否打开
      relatedOpen: false,
      // 要进行关联的人员的信息
      personInfo: {},
      // 关联车辆的窗口 是否打开
      relatedVehiclesOpen: false,
      //图片上传接口地址
      fileUpLoadUrl: null,
      // 搜索表单控件高度
      formHeight: 0,
      // 上传图片状态
      uploadState: true,
      personOptions: []
    };
  },
  created () {
    this.getCommunityDictory();
    this.getDicts("base_person_type").then((response) => {
      this.personTypeOptions = response.data;
    });
    this.getDicts("base_checked_stat").then((response) => {
      this.checkedOptions = response.data;
    });
    this.getDicts("sys_user_sex").then((response) => {
      this.sexOptions = response.data;
    });
    this.getDicts("base_nationality").then((response) => {
      this.nationalityOptions = response.data;
    });
    this.getDicts("base_idCard").then((response) => {
      this.idTypeOptions = response.data;
    });
    this.getDicts("base_nation").then((response) => {
      this.nationOptions = response.data;
    });
    this.getDicts("base_political").then((response) => {
      this.politicalOptions = response.data;
    });
    this.getDicts("base_degree").then((response) => {
      this.degreeOptions = response.data;
    });
    this.getDicts("base_religion").then((response) => {
      this.religionOptions = response.data;
    });
    this.getDicts("base_yes_no").then((response) => {
      this.yesNoOptions = response.data;
    });
    this.getDicts("base_special_person").then((response) => {
      this.specialOptions = response.data;
    });
    this.getDicts("base_family_relation").then((response) => {
      this.householdRelationOptions = response.data;
    });
    this.getDicts("base_type_of_caring").then((response) => {
      this.typeOptions = response.data;
    });
    this.getDicts("base_stay_behind").then((response) => {
      this.stayBehindOptions = response.data;
    });
    this.getDicts("sys_base_custom_tag").then((response) => {
      this.customTagOptions = response.data;
    });
    this.getDicts("base_person_marriage").then((response) => {
      this.marriageOptions = response.data;
    });
  },
  filters: {
    checkedFilters: function (value, self) {
      return self.checkedFormat(null, null, value);
    },
  },
  mounted () {
    this.getList();
    this.resetCaringInfo();
    this.$Notice.$on(
      "personhouserelatedvisible",
      () => (this.relatedOpen = false)
    );
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight;
    });
    this.fileUpLoadUrl = configInfo.FileUpLoadUri;
    this.getCommunityDictoryName()
    this.getCommunityChildDictoryName()
  },
  methods: {
    getImgUrl (url) {
      return configInfo.imgEchoAddress + url
      // let urlList = this.$cf.returnJson(url)
      // if (urlList.length > 0) {
      //   return configInfo.imgEchoAddress + urlList[0].url
      // } else {
      //   return ''
      // }
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
    getCommunityChildDictory2 (communityId) {
      this.communityChildOptions2 = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId,
      };
      listCommunity_child(param).then((response) => {
        this.communityChildOptions2 = response.rows;
      });
    },
    /** 查询人员信息列表 */
    getList () {
      this.loading = true;
      if (this.isSpecial) {
        this.queryParams.special = this.isSpecial === "0" ? "0" : "-1";
      } else {
        this.queryParams.special = null;
      }
      this.searchPersonType = this.queryParams.personType;
      listPersonnel_information(this.queryParams).then((response) => {
        console.log(response.rows,9090);
        this.personnel_informationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 是否已经审核确认,(0否1是,默认0)字典翻译
    checkedFormat (row, column, cellValue) {
      return this.selectDictLabel(this.checkedOptions, cellValue);
    },
    // 性别字典翻译
    sexFormat (row, column) {
      return this.selectDictLabel(this.sexOptions, row.sex);
    },
    // 国籍字典翻译
    nationalityFormat (row, column) {
      return this.selectDictLabel(this.nationalityOptions, row.nationality);
    },
    // 证件类型代码字典翻译
    idTypeFormat (row, column) {
      return this.selectDictLabel(this.idTypeOptions, row.idType);
    },
    // 民族字典翻译
    nationFormat (row, column) {
      return this.selectDictLabel(this.nationOptions, row.nation);
    },
    // 家庭关系字典翻译
    householdRelationFormat (row, column) {
      return this.selectDictLabel(
        this.householdRelationOptions,
        row.householdRelation
      );
    },

    // 政治面貌字典翻译
    politicalFormat (row, column) {
      return this.selectDictLabel(this.politicalOptions, row.political);
    },
    // 学历字典翻译
    degreeFormat (row, column) {
      return this.selectDictLabel(this.degreeOptions, row.degree);
    },
    // 宗教信仰字典翻译
    religionFormat (row, column) {
      return this.selectDictLabel(this.religionOptions, row.religion);
    },
    // 是否已婚字典翻译
    yesNoFormat (row, column, cellValue) {
      return this.selectDictLabel(this.yesNoOptions, row[column.property]);
    },
    // 是否已婚字典翻译
    maritalFormat (row, column, cellValue) {
      return this.selectDictLabel(this.marriageOptions, row.marital);
    },
    //查询通行列表
    getPassList (personId) {
      this.trackObject.personId = personId;
      this.trackObject.drawShow = true;
      // console.log(this.trackObject);
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
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: "0",
        remark: null,
        communityId: null,
        communityChildId: null,
        personType: "01",
        stayBehind: "0",
        name: null,
        oldName: null,
        sex: null,
        nationality: "156",
        idType: "111",
        identificationNumber: null,
        nation: null,
        idcardStartDate: null,
        idcardEndDate: null,
        location: null,
        residenceDetail: null,
        address: null,
        addressDetail: null,
        marital: null,
        political: null,
        degree: null,
        religion: null,
        profession: null,
        professioDetail: null,
        phoneNumber: null,
        special: "0",
        limited: "0",
        caring: "0",
        floating: null,
        arrivalCountryDate: null,
        arrivalProvinceDate: null,
        departureDate: null,
        floatingReasons: null,
        cardType: null,
        cardHandlingAgency: null,
        cardNum: null,
        cardRecordDate: null,
        pics: null,
        checked: "0",
        brithDate: null,
        customTag: null,
        type: null, //关爱类型，关爱人员信息挂靠过来的，方便验证
      };
      this.resetForm("form");
      this.resetCaringInfo();
    },
    resetCaringInfo () {
      this.caringInfo = {
        id: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: "0",
        remark: null,
        personId: null,
        type: null,
        communityId: null,
        communityChildId: null,
      };
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
      this.deleteNotces = selection.map((item) => item.name);
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset();
      this.open = true;
      this.title = "添加人员信息";
      this.uploadState = true
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      let id = "";
      let personId = "";
      if (row.id) {
        id = row.id;
        personId = id;
      } else {
        id = this.ids;
        personId = this.$cf.getStringFromList(id);
      }
      // const id = row.id || this.ids;
      getPersonnel_information(id).then((response) => {
        this.uploadState = false
        this.form = response.data;
           console.log(response,909090);
        Object.defineProperty(this.form, "type", {
          value: "99",
          writable: true,
        });
        this.getCommunityChildDictory2(this.form.communityId);
        //查询关爱人员
        let param = {
          personId: personId,
        };
        listCaring_person(param).then((response) => {
          if (response && response.rows && response.rows.length > 0) {
            this.caringInfo = response.rows[0];
            
            this.form.type = this.caringInfo.type;
         
          }
        });
        this.open = true;
        this.title = "修改人员信息";
      });
    },
    /** 另存一条 提交按钮 */
    otherSubmitForm () {
      this.form.id = null;
      this.submitForm();
    },
    /** 提交按钮 */
    submitForm () {
      let self = this;
      this.$refs["form"].validate((valid) => {
        if (valid) {
          console.log('person:', self.form)
          if (self.personType !== "03") self.nationality = "156"; //不是外国人就默认是中国人
          if (self.form.id != null) {
            updatePersonnel_information(self.form).then((response) => {
              if (response.code === 200) {
                self.addOrUpdataCaringType(self.form);
                self.msgSuccess("修改成功");
                self.open = false;
                self.getList();
              }
            });
          } else {
            addPersonnel_information(self.form).then((response) => {
              if (response.code === 200) {
                self.form.id = response.data;
                self.addOrUpdataCaringType(self.form);
                self.msgSuccess("新增成功");
                self.open = false;
                self.getList();
              }
            });
          }
        }
      });
    },
    addOrUpdataCaringType (form) {
      this.caringInfo.type = this.form.type;
      if (form.caring && form.caring === "1") {
        this.caringInfo.personId = form.id;
        this.caringInfo.communityId = form.communityId;
        this.caringInfo.communityChildId = form.communityChildId;

        if (this.caringInfo.id != null) {
          updateCaring_person(this.caringInfo).then((response) => {
            if (response.code === 200) {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }
          });
        } else {
          addCaring_person(this.caringInfo).then((response) => {
            if (response.code === 200) {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            }
          });
        }
      } else {
        if (this.caringInfo.id != null) {
          delCaring_person(this.caringInfo.id);
        }
      }
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      const ids = row.id || this.ids;
      const deleteNotice = row.name || this.deleteNotces;
      this.$confirm(
        '是否确认删除姓名为"' + deleteNotice + '"的人员数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delPersonnel_information(ids);
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
      this.$confirm("是否确认导出所有人员信息数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportPersonnel_information(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () { });
    },
    // 关联房屋
    relatedHouses (personInfo) {
      // this.$router.push({ name: '/communityUnit/community_houses_personnel' });
      // console.log('scoperow:', personInfo.id)
      this.personInfo = personInfo;
      // console.log('this.personid:', this.personInfo.id)
      this.relatedOpen = true;
    },
    beforeCloseRelatedOpen (done) {
      this.$Notice.$emit("addFormItemVisible");
      done();
    },
    relatedVehicles (personInfo) {
      this.personInfo = personInfo;
      this.relatedVehiclesOpen = true;
    },
    handleUpdate_check (row) {
      const name = row.name;
      let self = this;
      this.$confirm("是否确认审核人员信息?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(function () {
        let param = {
          id: row.id,
          checked: "1",
          checkedTime: self.$cf.getNowTime(),
        };
        updatePersonnel_information(param)
          .then((response) => {
            if (response.code === 200) {
              self.msgSuccess("修改成功");
              self.open = false;
              self.getList();
            }
          })
          .catch(function () { });
      });
    },
    specialTag (row) {
      this.specialopen = true
      console.log(row.id, 'row.id')
      this.specialForm = row
    },
    careTag (row) {
      // console.log(row, 'row')
      this.careForm.communityId = row.communityId
      this.careForm.communityChildId = row.communityChildId
      this.careopen = true
      this.getCommunityChildDictory2(row.communityId);
      this.careForm.personId = row.id
      this.personOptions = [row]
      // this.careForm.personId = row.id
    },
    remotePersonInfoMethod (name) {
      let param = {
        communityId: null,
        communityChildId: null,
        name: name,
        id: null
      }
      if (this.queryParams.personId && this.queryParams.personId.length > 0) {
        param.id = this.queryParams.personId
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
    /** 提交按钮 */
    specialSubmitForm () {
      switch (this.specialForm.special) {
        case '01'://
          this.$refs.specialReleasedPeople.submitForm()
          break;
        case '02'://
          this.$refs.speciaCorrect.submitForm()
          break;
        case '03'://
          this.$refs.speciaMental.submitForm()
          break;
        case '04'://
          this.$refs.specialDrugAddicts.submitForm()
          break;
        case '05'://
          this.$refs.speciaAids.submitForm()
          break;
      }
    },
    careSubmitForm () {
      this.$refs["careForm"].validate((valid) => {
        if (valid) {
          console.log(this.careForm, 'this.careForm')
          addCaring_person(this.careForm).then((response) => {
            if (response.code === 200) {
              this.msgSuccess("添加标签成功");
              this.careopen = false;
            }
          });
        }
      });
    },
    specialcancel () {
      this.careopen = false;
      this.specialopen = false;
    },
    getDetail (row) {
      console.log(row, 'row===row')
      this.detailform = {}
      this.detailopen = true
      this.detailform.grid = ''
      this.detailform = row
      this.detailform.communityName = this.communityOptions.find(p => p.id == row.communityId).name
      this.detailform.communityChildName = this.communityChildOptions2.find(p => p.id == row.communityChildId).name
      this.detailform.sex = this.sexFormat(row)
      this.detailform.nation = this.nationFormat(row)
      this.detailform.nationality = this.nationalityFormat(row)
      this.detailform.idType = this.idTypeFormat(row)
      this.detailform.householdRelation = this.householdRelationFormat(row)
      this.detailform.political = this.politicalFormat(row)
      this.detailform.degree = this.degreeFormat(row)
      this.detailform.religion = this.religionFormat(row)
      this.detailform.marital = this.maritalFormat(row)
      // this.detailform.sex = this.politicalFormat(row)
    },
    getCommunityDictoryName () {
      this.communityOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
      };
      listCommunity(param).then((response) => {
        this.communityOptions = response.rows;
      });
    },
    getCommunityChildDictoryName () {
      this.communityChildOptions2 = [];
      let param = {
        pageNum: 1,
        pageSize: 100000
      };
      listCommunity_child(param).then((response) => {
        this.communityChildOptions2 = response.rows;
      });
    }
  },
  computed: {
    dataHeight () {
      let searchFormHeight = 0;
      if (this.showSearch) searchFormHeight = this.formHeight;
      return this.$store.getters.dataEleHeight - searchFormHeight - 115;
    },
  },
};
</script>
