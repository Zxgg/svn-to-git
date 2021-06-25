<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch"
             label-width="68px">
      <!-- <el-form-item label="国籍"
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
      <el-form-item label="证件类型"
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
      </el-form-item>
      <el-form-item label="证件号码"
                    prop="identificationNumber">
        <el-input v-model="queryParams.identificationNumber"
                  placeholder="请输入证件号码"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="人员类型"
                    prop="special">
        <el-select v-model="specialType"
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
      <!-- <el-form-item label="民族"
                    v-if="moreselect && !isForeign"
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
                    v-if="moreselect"
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
               style="width: auto;padding-right: 0px;">--</label>
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
                    v-if="moreselect &&!isForeign"
                    prop="location">
        <el-input v-model="queryParams.location"
                  placeholder="请输入籍贯"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="户籍详址"
                    v-if="moreselect && !isForeign"
                    prop="residenceDetail">
        <el-input v-model="queryParams.residenceDetail"
                  placeholder="请输入户籍详址"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="现住地"
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
                    v-if="moreselect && !isForeign"
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
                    v-if="moreselect"
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
                    v-if="moreselect"
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
                    v-if="moreselect"
                    prop="profession">
        <el-input v-model="queryParams.profession"
                  placeholder="请输入职业类别"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="职业信息"
                    v-if="moreselect"
                    prop="professioDetail">
        <el-input v-model="queryParams.professioDetail"
                  placeholder="请输入职业信息"
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
      <el-form-item label="流动人员"
                    v-if="!isForeign"
                    prop="floating">
        <el-select v-model="queryParams.floating"
                   placeholder="请选择是否是流动人员"
                   clearable
                   size="small">
          <el-option v-for="dict in yesNoOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <!-- 流动人员 -->
      <!-- <el-row v-if="moreselect && !isForeign && queryParams.floating==='1'"
              class="commonBorder">
        <el-row>
          <!-- <div class="el-form-item el-form-item--medium VerticalCenter ">
          <label class="el-form-item__label commonTextBlock"
                 style="width: auto;">流动人员信息</label>
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

      </el-row>
      <el-row v-if="moreselect && isForeign"
              class="commonBorder">
        <el-row>
          <label class="el-form-item__label commonTextBlock"
                 style="width: auto;">国外人员入境信息</label>
        </el-row>
        <el-form-item label="最后入国境"
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
                   placeholder="请选择管控限制人员"
                   clearable
                   size="small">
          <el-option v-for="dict in yesNoOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <!-- <el-form-item label="关爱人员"
                    v-if="moreselect"
                    prop="caring">
        <el-select v-model="queryParams.caring"
                   placeholder="请选择关爱人员"
                   clearable
                   size="small">
          <el-option v-for="dict in yesNoOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>

      <el-form-item label="审核"
                    prop="checked">
        <el-select v-model="queryParams.checked"
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

    <el-table v-loading="loading"
              stripe
              :data="personnel_informationList"
              :height="dataHeight"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="姓名"
                       align="center"
                       prop="name" />
      <!-- <el-table-column label="曾用名"
                       align="center"
                       prop="oldName" /> -->
      <el-table-column label="性别"
                       align="center"
                       prop="sex"
                       width="auto"
                       :formatter="sexFormat" />
      <el-table-column label="国籍"
                       v-if="!queryParams.nationality"
                       align="center"
                       prop="nationality"
                       :formatter="nationalityFormat" />
      <el-table-column label="证件类型"
                       align="center"
                       prop="idType"
                       :formatter="idTypeFormat" />
      <el-table-column label="证件号码"
                       align="center"
                       width="120px"
                       prop="identificationNumber" />
      <!-- <el-table-column label="籍贯"
                       align="center"
                       prop="location" /> -->
      <!-- <el-table-column label="户籍详址"
                       align="center"
                       prop="residenceDetail" /> -->
      <el-table-column label="现住地"
                       align="center"
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
                       prop="phoneNumber" />
      <!-- <el-table-column label="流动人员"
                       align="center"
                       prop="floating"
                       :formatter="yesNoFormat" /> -->
      <el-table-column label="流入原因"
                       v-if="queryParams.floating==='1'"
                       align="center"
                       prop="floatingReasons" />
      <!-- <el-table-column label="办证类型"
                       v-if="queryParams.floating==='1'"
                       align="center"
                       prop="cardType" />
      <el-table-column label="办证机关"
                       v-if="queryParams.floating==='1'"
                       align="center"
                       prop="cardHandlingAgency" />
      <el-table-column label="证件号码"
                       v-if="queryParams.floating==='1'"
                       align="center"
                       prop="cardNum" />
      <el-table-column label="登记日期"
                       v-if="queryParams.floating==='1'"
                       align="center"
                       prop="cardRecordDate" />
      <el-table-column label="最后入境"
                       v-if="queryParams.floating==='1'"
                       align="center"
                       prop="arrivalProvinceDate"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.arrivalProvinceDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后入国境"
                       v-if="isForeign"
                       align="center"
                       prop="arrivalCountryDate"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.arrivalCountryDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预计离开日期"
                       v-if="isForeign"
                       align="center"
                       prop="departureDate"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.departureDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->

      <el-table-column label="特殊人员"
                       align="center"
                       prop="special"
                       :formatter="specialFormat" />
      <!-- <el-table-column label="管控人员"
                       align="center"
                       prop="limited"
                       :formatter="yesNoFormat" /> -->
      <!-- <el-table-column label="关爱人员"
                       align="center"
                       prop="caring"
                       :formatter="yesNoFormat" /> -->
      <el-table-column label="照片"
                       align="center"
                       prop="pics">
        <template slot-scope="scope">
          <el-image v-if="scope.row.pics && scope.row.pics.length>0"
                    style="width: 100px; height: 80px"
                    :src="getFirstUrlFromJson(scope.row.pics)"
                    fit="fill"></el-image>
          <!-- scope.row.pics -->
          <div v-else>无图</div>
        </template>
      </el-table-column>
      <!-- <el-table-column label="备注"
                       align="center"
                       prop="remark" /> -->
      <!-- <el-table-column label="审核"
                       align="center"
                       prop="checked"
                       :formatter="yesNoFormat" /> -->
      <el-table-column label="操作"
                       width="120px"
                       align="center"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
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

    <pagination v-show="total>0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改人员信息对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="1200px"
               append-to-body>
      <div class="app-container">
        <el-form ref="form"
                 :model="form"
                 :rules="rules"
                 label-width="140px"
                 size="medium"
                 label-position="right">
          <el-form-item label="特殊人员类型"
                        prop="special">
            <el-select v-model="form.special"
                       :disabled="form.id && form.id.length>0"
                       placeholder="请选择特殊人员类型"
                       clearable
                       size="small">
              <el-option v-for="dict in specialOptions"
                         :key="dict.dictValue"
                         :label="dict.dictLabel"
                         :value="dict.dictValue"
                         :disabled="dict.disabled" />
            </el-select>
            <!-- <el-input v-model="form.special" placeholder="特殊人员类型值" /> -->
          </el-form-item>
        </el-form>
      </div>
      <div>
        <!-- 刑满释放 -->
        <specialReleasedPeople v-if="form.special==='01'"
                               ref="specialReleasedPeople"
                               :personInfo="form" />
        <!-- 社区矫正人员 -->
        <speciaCorrect v-if="form.special==='02'"
                       ref="speciaCorrect"
                       :personInfo="form" />

        <!-- 肇事肇祸重度精神疾病 -->
        <speciaMental v-if="form.special==='03'"
                      ref="speciaMental"
                      :personInfo="form" />
        <!-- 吸毒人员 -->
        <specialDrugAddicts v-if="form.special==='04'"
                            ref="specialDrugAddicts"
                            :personInfo="form" />
        <!-- 艾滋病患者 -->
        <speciaAids v-if="form.special==='05'"
                    ref="speciaAids"
                    :personInfo="form" />
      </div>
      <div slot="footer"
           class="dialog-footer">
        <!-- <el-button type="danger"
                   @click="deleteForm"
                   v-if="form.id && form.id.length>0">删除</el-button> -->
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
  </div>
</template>

<script>
import { listPersonnel_information, getPersonnel_information, delPersonnel_information, addPersonnel_information, updatePersonnel_information, exportPersonnel_information, updatePersonnel_specialType, updatePersonnel_removeSpecialType } from "@/api/communityUnit/personnel_information";
import communityHousesPersonnel from '../community_houses_personnel/index'
import vehicleHousesPersonnel from '@/views/communityUnit/vehicle_houses_personnel/index'
import specialReleasedPeople from './special_released_people'//刑满释放人员
import { listSpecial_released_people, getSpecial_released_people, delSpecial_released_people, addSpecial_released_people, updateSpecial_released_people, exportSpecial_released_people } from "@/api/special_personnel/special_released_people";
import speciaAids from './specia_aids'//艾滋病患者
import { listSpecia_aids, getSpecia_aids, delSpecia_aids, addSpecia_aids, updateSpecia_aids, exportSpecia_aids } from "@/api/special_personnel/specia_aids";
import speciaCorrect from './specia_correct'//社区矫正人员
import { listSpecia_correct, getSpecia_correct, delSpecia_correct, addSpecia_correct, updateSpecia_correct, exportSpecia_correct } from "@/api/special_personnel/specia_correct";
import speciaMental from './specia_mental'//肇事肇祸重度精神疾病
import { listSpecia_mental, getSpecia_mental, delSpecia_mental, addSpecia_mental, updateSpecia_mental, exportSpecia_mental } from "@/api/special_personnel/specia_mental";
// import speciaStray from './specia_stray'//闲散流浪人员
// import { listSpecia_stray, getSpecia_stray, delSpecia_stray, addSpecia_stray, updateSpecia_stray, exportSpecia_stray } from "@/api/special_personnel/specia_stray";
import specialDrugAddicts from './special_drug_addicts'//吸毒人员
import { listSpecial_drug_addicts, getSpecial_drug_addicts, delSpecial_drug_addicts, addSpecial_drug_addicts, updateSpecial_drug_addicts, exportSpecial_drug_addicts } from "@/api/special_personnel/special_drug_addicts";
// import specialYoung from './special_young'//重点青少年
// import { listSpecial_young, getSpecial_young, delSpecial_young, addSpecial_young, updateSpecial_young, exportSpecial_young } from "@/api/special_personnel/special_young";


export default {
  name: "special_personnel",
  components: { communityHousesPersonnel, vehicleHousesPersonnel, specialReleasedPeople, speciaAids, speciaCorrect, speciaMental, specialDrugAddicts },
  data () {
    return {
      moreselect: false,
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
      // 是否 字典
      yesNoOptions: [],
      // 特殊人员字典
      specialOptions: [],
      //当前展示或者编辑的人员信息
      // personInfo: null,
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
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        name: null,
        oldName: null,
        sex: null,
        nationality: null,
        idType: null,
        identificationNumber: null,
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
        special: '01',
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
        brithDate: null
      },
      //查询参数默认值
      specialType: "01",
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        delFlag: [
          { required: true, message: "删除标志不能为空", trigger: "blur" }
        ],
      },
      // 关联房屋窗口 是否打开
      relatedOpen: false,
      // 要进行关联的人员的信息
      personInfo: {},
      // 关联车辆的窗口 是否打开
      relatedVehiclesOpen: false,
      // 搜索表单控件高度
      formHeight: 0
    };
  },
  created () {
    this.getList();
    this.getDicts("sys_user_sex").then(response => {
      this.sexOptions = response.data;
    });
    this.getDicts("base_nationality").then(response => {
      this.nationalityOptions = response.data;
    });
    this.getDicts("base_idCard").then(response => {
      this.idTypeOptions = response.data;
    });
    this.getDicts("base_nation").then(response => {
      this.nationOptions = response.data;
    });
    this.getDicts("base_political").then(response => {
      this.politicalOptions = response.data;
    });
    this.getDicts("base_degree").then(response => {
      this.degreeOptions = response.data;
    });
    this.getDicts("base_religion").then(response => {
      this.religionOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.yesNoOptions = response.data;
    });
    this.getDicts("base_special_person").then(response => {
      let temp = response.data
      temp = temp.filter(item => {
        return item.dictValue !== '0'
      })
      this.specialOptions = temp;

    });

  },
  mounted () {
    let self = this
    this.$Notice.$on('personhouserelatedvisible', () => this.relatedOpen = false)
    this.$Notice.$on('special_personnel_submitForm', (flag) => {
      self.open = !flag
      this.getList()
      // self.open = true;
    })
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight
    })
  },
  methods: {
    /** 查询人员信息列表 */
    getList () {
      this.loading = true;
      this.queryParams.special = this.specialType ? this.specialType : '-1'
      listPersonnel_information(this.queryParams).then(response => {
        this.personnel_informationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
    // 是否字典翻译
    yesNoFormat (row, column, cellValue) {
      return this.selectDictLabel(this.yesNoOptions, row[column.property]);
    },
    // 特殊人员字典翻译
    specialFormat (row, column, cellValue) {
      return this.selectDictLabel(this.specialOptions, cellValue);
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
        delFlag: null,
        remark: null,
        name: null,
        oldName: null,
        sex: null,
        nationality: '156',
        idType: '111',
        identificationNumber: null,
        nation: '01',
        idcardStartDate: null,
        idcardEndDate: null,
        location: null,
        residenceDetail: null,
        address: null,
        addressDetail: null,
        marital: '0',
        political: null,
        degree: null,
        religion: '00',
        profession: null,
        professioDetail: null,
        phoneNumber: null,
        special: '01',
        limited: '0',
        caring: '0',
        floating: '0',
        arrivalCountryDate: null,
        arrivalProvinceDate: null,
        departureDate: null,
        floatingReasons: null,
        cardType: null,
        cardHandlingAgency: null,
        cardNum: null,
        cardRecordDate: null,
        pics: null,
        checked: '0',
        brithDate: null
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
      this.specialType = null
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
      this.title = "添加特殊人员信息";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const id = row.id || this.ids
      getPersonnel_information(id).then(response => {
        this.form = response.data;
        // this.personId = this.form.id
        this.open = true;
        this.title = "修改特殊人员信息";
      });
    },
    /** 删除当前form */
    deleteForm () {
      // this.$refs.specialReleasedPeople.deleteInfo()
      updatePersonnel_removeSpecialType()
    },
    /** 提交按钮 */
    submitForm () {
      switch (this.form.special) {
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
    /** 删除按钮操作 */
    handleDelete (row) {
      const deleteIds = row.id || this.ids;
      this.$confirm('是否确认删除选中人员的特殊人员记录?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return updatePersonnel_removeSpecialType(deleteIds);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有人员信息数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportPersonnel_information(queryParams);
      }).then(response => {
        this.download(response.msg);
      }).catch(function () { });
    },
    // 关联房屋
    relatedHouses (personInfo) {
      // this.$router.push({ name: '/communityUnit/community_houses_personnel' });
      // console.log('scoperow:', personInfo.id)
      this.personInfo = personInfo
      // console.log('this.personid:', this.personInfo.id)
      this.relatedOpen = true
    },
    beforeCloseRelatedOpen (done) {
      this.$Notice.$emit('addFormItemVisible')
      done()
    },
    relatedVehicles (personInfo) {
      this.personInfo = personInfo
      this.relatedVehiclesOpen = true
    },
  },
  computed: {
    isForeign: function () {
      // `this` 指向 vm 实例
      let domesticList = [
        '156', '344', '446', '158'
      ]
      return !(this.queryParams.nationality && this.queryParams.nationality !== '' && domesticList.includes(this.queryParams.nationality))
    },
    isForeign_form: function () {
      // `this` 指向 vm 实例
      let domesticList = [
        '156', '344', '446', '158'
      ]
      return !(this.form.nationality && this.form.nationality !== '' && domesticList.includes(this.form.nationality))
    },
    dataHeight () {
      let searchFormHeight = 0
      if (this.showSearch) searchFormHeight = this.formHeight
      return this.$store.getters.dataEleHeight - searchFormHeight - 115
    }
  }
};
</script>
<style lang="scss" scoped>
@import "@/assets/styles/inputSty.scss";
</style>
