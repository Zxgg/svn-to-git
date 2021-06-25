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
      <el-form-item label="姓名"
                    prop="name">
        <el-input v-model="queryParams.name"
                  placeholder="请输入姓名"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="性别"
                    prop="sex">
        <el-select v-model="queryParams.sex"
                   placeholder="请选择性别"
                   clearable
                   size="small">
          <el-option v-for="dict in sexOptions"
                     :key="dict.dictLabel"
                     :label="dict.dictLabel"
                     :value="dict.dictLabel" />
        </el-select>
      </el-form-item>
      <el-form-item label="手机号"
                    prop="phone">
        <el-input v-model="queryParams.phone"
                  placeholder="请输入手机号"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <el-form-item label="身份证号"
                    prop="idCard">
        <el-input v-model="queryParams.idCard"
                  placeholder="请输入身份证号码"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="现住地址"
                    prop="addressDetail">
        <el-input v-model="queryParams.addressDetail"
                  placeholder="请输入现住地址"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="工作地址"
                    prop="addressJobDetail"
                    label-width="auto">
        <el-input v-model="queryParams.addressJobDetail"
                  placeholder="请输入工作地址"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="是否有防疫绿码"
                    class="longStringShutSelect"
                    prop="epidemicGreenCode"
                    label-width="auto">
        <el-select v-model="queryParams.epidemicGreenCode"
                   placeholder="请选择是否"
                   clearable
                   size="small">
          <el-option v-for="dict in epidemicGreenCodeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="合租"
                    class="longStringShutSelect"
                    prop="roommates">
        <el-select v-model="queryParams.roommates"
                   placeholder="请选择是否"
                   clearable
                   size="small">
          <el-option v-for="dict in roommatesOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-row class="longStringShutSelect">
        <el-form-item label="核酸检测"
                      prop="nucleicAcidTesting">
          <el-select v-model="queryParams.nucleicAcidTesting"
                     placeholder="请选择"
                     clearable
                     size="small">
            <el-option v-for="dict in nucleicAcidTestingOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="第一针疫苗"
                      prop="injectionVaccineOne"
                      label-width="auto">
          <el-select v-model="queryParams.injectionVaccineOne"
                     placeholder="请选择是否"
                     clearable
                     size="small">
            <el-option v-for="dict in injectionVaccineOneOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="第二针疫苗"
                      prop="injectionVaccineTwo"
                      label-width="auto">
          <el-select v-model="queryParams.injectionVaccineTwo"
                     placeholder="请选择是否"
                     clearable
                     size="small">
            <el-option v-for="dict in injectionVaccineTwoOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="医学观察"
                      prop="medicalWatch"
                      label-width="auto">
          <el-select v-model="queryParams.medicalWatch"
                     placeholder="请选择是否"
                     clearable
                     size="small">
            <el-option v-for="dict in medicalWatchOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="14天内离开本市"
                      prop="leaveLocal"
                      label-width="auto">
          <el-select v-model="queryParams.leaveLocal"
                     placeholder="请选择是否"
                     clearable
                     size="small">
            <el-option v-for="dict in leaveLocalOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="14天内去过中高风险区"
                      prop="havaBeenRiskArea"
                      label-width="auto">
          <el-select v-model="queryParams.havaBeenRiskArea"
                     placeholder="请选择是否"
                     clearable
                     size="small">
            <el-option v-for="dict in havaBeenRiskAreaOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="14天内回国"
                      prop="backHome"
                      label-width="auto">
          <el-select v-model="queryParams.backHome"
                     placeholder="请选择是否"
                     clearable
                     size="small">
            <el-option v-for="dict in backHomeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
      </el-row>
      <el-row class="longStringShutSelect">
        <el-form-item label="同住人员疑似症状"
                      prop="roommatesSuspectedSymptoms"
                      label-width="auto">
          <el-select v-model="queryParams.roommatesSuspectedSymptoms"
                     placeholder="请选择是否"
                     clearable
                     size="small">
            <el-option v-for="dict in roommatesSuspectedSymptomsOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="同住人员病患接触史"
                      prop="roommatesPatientExposureHistory"
                      label-width="auto">
          <el-select v-model="queryParams.roommatesPatientExposureHistory"
                     placeholder="请选择是否"
                     clearable
                     size="small">
            <el-option v-for="dict in roommatesPatientExposureHistoryOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="同住人员14天内去过中高风险区"
                      prop="roommatesHavaBeenRiskArea"
                      label-width="auto">
          <el-select v-model="queryParams.roommatesHavaBeenRiskArea"
                     placeholder="请选择是否"
                     clearable
                     size="small">
            <el-option v-for="dict in roommatesHavaBeenRiskAreaOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="同住人员14天内回国"
                      prop="roommatesBackHome"
                      label-width="183px">
          <el-select v-model="queryParams.roommatesBackHome"
                     placeholder="请选择是否"
                     clearable
                     size="small">
            <el-option v-for="dict in roommatesBackHomeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item> -->
      <el-form-item class="HorizontalMargin">
        <el-button type="cyan"
                   icon="el-icon-search"
                   size="mini"
                   @click="handleQuery">搜索</el-button>
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAdd"
                   v-hasPermi="['epidemic:person_epidemic_state_record:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['epidemic:person_epidemic_state_record:remove']">删除</el-button>
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleCreate_QRCode"
                   v-hasPermi="['epidemic:person_epidemic_state_record:add']">生成二维码</el-button>
        <!-- <el-button icon="el-icon-refresh"
                   size="mini"
                   @click="resetQuery">重置</el-button> -->
      </el-form-item>
      </el-row>
    </el-form>

    <!-- <el-row :gutter="10"
            class="mb8">
      <el-col :span="1.5">
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAdd"
                   v-hasPermi="['epidemic:person_epidemic_state_record:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['epidemic:person_epidemic_state_record:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['epidemic:person_epidemic_state_record:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['epidemic:person_epidemic_state_record:export']">导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleCreate_QRCode"
                   v-hasPermi="['epidemic:person_epidemic_state_record:add']">生成二维码</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading"
              :height="dataHeight"
              :data="person_epidemic_state_recordList"
              @selection-change="handleSelectionChange"
              :cell-class-name="getCellClassName">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="社区名称"
                       align="center"
                       prop="communityId"
                       :formatter="communityNameFormat" />
      <!-- <el-table-column label="社区名称"
                       align="center"
                       prop="communityName"/> -->
      <el-table-column label="小区名称"
                       align="center"
                       prop="communityChildId"
                       :formatter="communityChildNameFormat" />
      <!-- <el-table-column label="小区名称"
                       align="center"
                       prop="communityChildName"
                       /> -->
      <el-table-column label="姓名"
                       align="center"
                       prop="name" />
      <el-table-column label="性别"
                       align="center"
                       width="55"
                       prop="sex"
                       :formatter="sexFormat" />
      <el-table-column label="手机号"
                       align="center"
                       width="120"
                       prop="phone" />
      <el-table-column label="身份证号码"
                       align="center"
                       width="160px"
                       prop="idCard" />
      <!-- <el-table-column label="省市县三级"
                       align="center"
                       prop="addressArea" /> -->
      <el-table-column label="现住地址"
                       align="center"
                       prop="addressDetail">
        <template slot-scope="scope">
          <el-tooltip class="item"
                      effect="dark"
                      :content="scope.row.addressArea+scope.row.addressDetail"
                      placement="top">
            <span>{{ scope.row.addressDetail}}</span>
          </el-tooltip>
        </template>
      </el-table-column>
      <!-- <el-table-column label="工作地点-省市区三级"
                       align="center"
                       prop="addressJobArea" /> -->
      <el-table-column label="工作地址"
                       align="center"
                       prop="addressJobDetail">
        <template slot-scope="scope">
          <el-tooltip class="item"
                      effect="dark"
                      :content="scope.row.addressJobArea+scope.row.addressJobDetail"
                      placement="top">
            <span>{{ scope.row.addressJobDetail}}</span>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="绿码"
                       align="center"
                       width="55"
                       prop="epidemicGreenCode"
                       :formatter="epidemicGreenCodeFormat" />
      <el-table-column label="合租"
                       align="center"
                       width="55"
                       prop="roommates"
                       :formatter="roommatesFormat" />
      <el-table-column label="疑似症状"
                       align="center"
                       width="80"
                       prop="roommatesSuspectedSymptoms"
                       :formatter="roommatesSuspectedSymptomsFormat" />
      <el-table-column label="病患接触史"
                       align="center"
                       width="100"
                       prop="roommatesPatientExposureHistory"
                       :formatter="roommatesPatientExposureHistoryFormat" />
      <el-table-column label="14天内是否离开本市"
                       align="center"
                       prop="leaveLocal"
                       :formatter="leaveLocalFormat" />
      <!-- <el-table-column label="14天内出行信息"
                       align="center"
                       prop="leaveInformation" /> -->
      <el-table-column label="中高风险区"
                       align="center"
                       prop="havaBeenRiskArea"
                       :formatter="havaBeenRiskAreaFormat" />
      <!-- <el-table-column label="14天内去过中高风险区的记录"
                       align="center"
                       prop="havaBeenRiskAreaInformation" /> -->
      <!-- <el-table-column label="14天内同住人员是否去过中高风险区"
                       align="center"
                       prop="roommatesHavaBeenRiskArea"
                       :formatter="roommatesHavaBeenRiskAreaFormat" /> -->
      <!-- <el-table-column label="同住人员14天内去过中高风险区的记录"
                       align="center"
                       prop="roommatesHavaBeenRiskAreaInformation" /> -->
      <!-- <el-table-column label="是否14天内回国"
                       align="center"
                       prop="backHome"
                       :formatter="backHomeFormat" /> -->
      <!-- <el-table-column label="同住人员是否14天内回国"
                       align="center"
                       prop="roommatesBackHome"
                       :formatter="roommatesBackHomeFormat" /> -->
      <el-table-column label="医学观察"
                       align="center"
                       width="80"
                       prop="medicalWatch"
                       :formatter="medicalWatchFormat" />
      <el-table-column label="核酸检测"
                       align="center"
                       width="80"
                       prop="nucleicAcidTesting"
                       :formatter="nucleicAcidTestingFormat" />
      <el-table-column label="疫苗注射"
                       align="center"
                       width="80"
                       prop="injectionVaccineOne"
                       :formatter="injectionVaccineOneFormat" />
      <!-- <el-table-column label="是否已经注射第一针疫苗"
                       align="center"
                       prop="injectionVaccineOne"
                       :formatter="injectionVaccineOneFormat" /> -->
      <!-- <el-table-column label="第一针疫苗 注射时间"
                       align="center"
                       prop="injectionVaccineOneTime"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.injectionVaccineOneTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="是否已经注射第二针疫苗"
                       align="center"
                       prop="injectionVaccineTwo"
                       :formatter="injectionVaccineTwoFormat" /> -->
      <!-- <el-table-column label="第二针疫苗 注射时间"
                       align="center"
                       prop="injectionVaccineTwoTime"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.injectionVaccineTwoTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="备注"
                       align="center"
                       prop="remark" /> -->

      <el-table-column label="操作"
                       align="center"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['epidemic:person_epidemic_state_record:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['epidemic:person_epidemic_state_record:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改疫情信息收集对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="800px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="240px">
        <el-form-item label="社区名称"
                      prop="communityId">
          <el-select v-model="form.communityId"
                     filterable
                     clearable
                     :disabled="form.id && form.id.length>0 && form.checked && form.checked === '1'"
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
                     :disabled="form.id && form.id.length>0 && form.checked && form.checked === '1'"
                     placeholder="请选择小区">
            <el-option v-for="dict in communityChildOptions2"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="姓名"
                      prop="name">
          <el-input v-model="form.name"
                    placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="form.sex">
            <el-radio v-for="dict in sexOptions"
                      :key="dict.dictLabel"
                      :label="dict.dictLabel">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="手机号"
                      prop="phone">
          <el-input v-model="form.phone"
                    placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="身份证号码"
                      prop="idCard">
          <el-input v-model="form.idCard"
                    placeholder="请输入身份证号码" />
        </el-form-item>
        <el-form-item label="当前住址"
                      prop="addressArea">
          <v-distpicker :province.sync="address.provincial"
                        :city.sync="address.citie"
                        :area.sync="address.countie"
                        :code.sync="address.code"></v-distpicker>
          <el-input v-model="addressArea"
                    v-show="false"
                    placeholder="请输入住址的省市区" />
          <el-input class="VerticalMargin"
                    v-model="form.addressDetail"
                    type="textarea"
                    placeholder="请输入现住详址" />
        </el-form-item>
        <el-form-item label="工作地点"
                      prop="addressJobArea">
          <v-distpicker :province.sync="addressJob.provincial"
                        :city.sync="addressJob.citie"
                        :area.sync="addressJob.countie"
                        :code.sync="addressJob.code"></v-distpicker>
          <el-input v-model="addressJobArea"
                    v-show="false"
                    placeholder="请输入工作地点的省市区" />
          <el-input class="VerticalMargin"
                    v-model="form.addressJobDetail"
                    type="textarea"
                    placeholder="请输入工作地点详址" />
        </el-form-item>
        <el-form-item label="是否有防疫绿码">
          <el-radio-group v-model="form.epidemicGreenCode">
            <el-radio v-for="dict in epidemicGreenCodeOptions"
                      :key="dict.dictValue"
                      :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否合租">
          <el-radio-group v-model="form.roommates">
            <el-radio v-for="dict in roommatesOptions"
                      :key="dict.dictValue"
                      :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="合租/同住人员是否有疑似症状">
          <el-radio-group v-model="form.roommatesSuspectedSymptoms">
            <el-radio v-for="dict in roommatesSuspectedSymptomsOptions"
                      :key="dict.dictValue"
                      :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="合租/同住人员是否有病患接触史">
          <el-radio-group v-model="form.roommatesPatientExposureHistory">
            <el-radio v-for="dict in roommatesPatientExposureHistoryOptions"
                      :key="dict.dictValue"
                      :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="14天内是否离开本市">
          <el-radio-group v-model="form.leaveLocal">
            <el-radio v-for="dict in leaveLocalOptions"
                      :key="dict.dictValue"
                      :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
          <el-button type="primary"
                     v-if="form.leaveLocal==='是'"
                     icon="el-icon-plus"
                     size="mini"
                     @click="handleAdd_leaveLocal">新增记录</el-button>
        </el-form-item>
        <el-form-item label="新增14天内出行信息"
                      v-if="form.leaveLocal==='是' && open_form_leaveInformation">
          <!-- 到达城市  到达日期 交通工具类型 交通工具车次  离开日期 交通工具 交通工具类型 交通工具车次-->
          <el-form ref="form_leaveInformation"
                   :model="form_leaveInformation"
                   :rules="rules_leaveInformation"
                   label-width="100px">
            <el-form-item label="到达城市"
                          prop="arriveCityArea">
              <el-input v-show="false"
                        v-model="arriveCityArea"
                        placeholder="请输入到达城市" />
              <v-distpicker :province.sync="arriveCity.provincial"
                            :city.sync="arriveCity.citie"
                            :area.sync="arriveCity.countie"
                            :code.sync="arriveCity.code"></v-distpicker>
            </el-form-item>
            <el-form-item label="到达日期"
                          prop="arriveData">
              <el-date-picker clearable
                              size="small"
                              style="width: 200px"
                              v-model="form_leaveInformation.arriveData"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="选择到达日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="交通工具类型"
                          prop="arriveTransportationTypes">
              <el-checkbox-group v-model="form_leaveInformation.arriveTransportationTypes">
                <el-checkbox v-for="dict in transportationTypesOptions"
                             :label="dict.dictLabel"></el-checkbox>
              </el-checkbox-group>
            </el-form-item>
            <el-form-item label="交通工具车次"
                          prop="arriveTransportationValues">
              <el-input class="VerticalMargin"
                        v-model="form_leaveInformation.arriveTransportationValues"
                        placeholder="请输入交通工具车次,多个交通工具用空格隔开" />
            </el-form-item>
            <el-form-item label="离开日期"
                          prop="leaveData">
              <el-date-picker clearable
                              size="small"
                              style="width: 200px"
                              v-model="form_leaveInformation.leaveData"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="选择离开日期">
              </el-date-picker>
            </el-form-item>
            <div class="dialog-footer">
              <el-button type="primary"
                         @click="submitForm_leaveInformation">确 定</el-button>
              <el-button @click="cancel_leaveInformation">取 消</el-button>
            </div>
          </el-form>
        </el-form-item>
        <el-form-item label="14天内出行信息"
                      v-if="form.leaveLocal==='是'"
                      prop="leaveInformation">
          <el-table stripe
                    :show-header="false"
                    :data="leaveInformation">
            <el-table-column label="简要信息"
                             fit
                             align="center"
                             class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-row class="textLeft HorizontalMargin">
                  <span>{{getShowAddressInfo(scope.row)}}</span>
                </el-row>
              </template>
            </el-table-column>
            <el-table-column label="操作"
                             width="55"
                             align="center"
                             class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button size="mini"
                           type="text"
                           icon="el-icon-delete"
                           @click="handleDelete_leaveLocal(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
        <el-form-item label="14天内是否去过中高风险区">
          <el-radio-group v-model="form.havaBeenRiskArea">
            <el-radio v-for="dict in havaBeenRiskAreaOptions"
                      :key="dict.dictValue"
                      :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
          </el-radio-group>

        </el-form-item>
        <el-form-item label="14天内去过的中高风险区"
                      v-if="form.havaBeenRiskArea==='是'"
                      prop="havaBeenRiskAreaInformation">
          <el-select v-model="havaBeenRiskAreaInformation"
                     multiple
                     filterable
                     clearable
                     placeholder="请选择去过的中高风险区"
                     style="width:280px;">
            <el-option-group v-for="group in beenRiskAreaInformationGroup"
                             :key="group.value"
                             :label="group.label">
              <el-option v-for="item in group.options"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
              </el-option>
            </el-option-group>
          </el-select>
        </el-form-item>
        <el-form-item label="14天内同住人员是否去过中高风险区">
          <el-radio-group v-model="form.roommatesHavaBeenRiskArea">
            <el-radio v-for="dict in roommatesHavaBeenRiskAreaOptions"
                      :key="dict.dictValue"
                      :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="同住人员14天内去过的中高风险区"
                      v-if="form.roommatesHavaBeenRiskArea==='是'"
                      prop="roommatesHavaBeenRiskAreaInformation">
          <el-select v-model="roommatesHavaBeenRiskAreaInformation"
                     multiple
                     filterable
                     clearable
                     placeholder="请选择去过的中高风险区"
                     style="width:280px;">
            <el-option-group v-for="group in beenRiskAreaInformationGroup"
                             :key="group.value"
                             :label="group.label">
              <el-option v-for="item in group.options"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
              </el-option>
            </el-option-group>
          </el-select>
          <!-- <el-input v-model="form.roommatesHavaBeenRiskAreaInformation"
                    type="textarea"
                    placeholder="请输入内容" /> -->
        </el-form-item>
        <el-form-item label="是否14天内回国">
          <el-radio-group v-model="form.backHome">
            <el-radio v-for="dict in backHomeOptions"
                      :key="dict.dictValue"
                      :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="同住人员是否14天内回国">
          <el-radio-group v-model="form.roommatesBackHome">
            <el-radio v-for="dict in roommatesBackHomeOptions"
                      :key="dict.dictValue"
                      :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否被要求医学观察">
          <el-radio-group v-model="form.medicalWatch">
            <el-radio v-for="dict in medicalWatchOptions"
                      :key="dict.dictValue"
                      :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否做过核酸检测  ">
          <el-radio-group v-model="form.nucleicAcidTesting">
            <el-radio v-for="dict in nucleicAcidTestingOptions"
                      :key="dict.dictValue"
                      :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否已经注射第一针疫苗">
          <el-radio-group v-model="form.injectionVaccineOne">
            <el-radio v-for="dict in injectionVaccineOneOptions"
                      :key="dict.dictValue"
                      :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="第一针疫苗 注射时间"
                      v-if="form.injectionVaccineOne==='是'"
                      prop="injectionVaccineOneTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.injectionVaccineOneTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择第一针疫苗 注射时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否已经注射第二针疫苗">
          <el-radio-group v-model="form.injectionVaccineTwo">
            <el-radio v-for="dict in injectionVaccineTwoOptions"
                      :key="dict.dictValue"
                      :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="第二针疫苗 注射时间"
                      v-if="form.injectionVaccineTwo==='是'"
                      prop="injectionVaccineTwoTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.injectionVaccineTwoTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择第二针疫苗 注射时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注"
                      prop="remark">
          <el-input v-model="form.remark"
                    type="textarea"
                    placeholder="请输入内容" />
        </el-form-item>

      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button type="primary"
                   @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 生成二维码 -->
    <el-dialog :title="title"
               :visible.sync="createQRShow"
               width="800px"
               append-to-body>
      <el-form ref="form_QR"
               :model="form_QR"
               :rules="rules_QR"
               label-width="240px">
        <el-form-item label="社区名称"
                      prop="communityChildId">
          <el-select v-model="form_QR.communityId"
                     filterable
                     clearable
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
          <el-select v-model="form_QR.communityChildId"
                     filterable
                     clearable
                     placeholder="请选择小区">
            <el-option v-for="dict in communityChildOptions2"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="生成人员"
                      prop="createBy">
          <el-input v-model="form_QR.createBy"
                    class="inputLongWidth"
                    placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="防疫信息填报地址"
                      prop="baseUrl">
          <el-input v-model="form_QR.baseUrl"
                    class="inputLongWidth"
                    placeholder="请输入防疫信息填报地址" />
          <el-button size="mini"
                     class="HorizontalLeftMargin"
                     type="primary"
                     icon="el-icon-refresh"
                     @click="createQR"
                     v-hasPermi="['epidemic:person_epidemic_state_record:add']">生成二维码</el-button>
        </el-form-item>
        <div class="full-img HorizontalCenter"
             v-if="form_QR.QRBase64">
          <img id="QRImage"
               :src="form_QR.QRBase64"
               alt=""
               width="100%"
               height="100%">
          <el-button @click="Save_QR">保存到本地</el-button>
        </div>
      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button @click="cancel_QR">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { generateQRCodeToBase64, listPerson_epidemic_state_record, getPerson_epidemic_state_record, delPerson_epidemic_state_record, addPerson_epidemic_state_record, updatePerson_epidemic_state_record, exportPerson_epidemic_state_record } from "@/api/epidemic/person_epidemic_state_record";
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import VDistpicker from '@/components/address_distpicke'
import configInfo from '@/webconfig/configInfo'
export default {
  name: "Person_epidemic_state_record",
  components: { VDistpicker },
  data () {
    return {
      // 遮罩层
      loading: false,
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
      // 疫情信息收集表格数据
      person_epidemic_state_recordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 性别字典
      sexOptions: [],
      // 是否有防疫绿码字典
      epidemicGreenCodeOptions: [],
      // 是否合租字典
      roommatesOptions: [],
      // 合租/同住人员是否有疑似症状字典
      roommatesSuspectedSymptomsOptions: [],
      // 合租/同住人员是否有病患接触史字典
      roommatesPatientExposureHistoryOptions: [],
      // 14天内是否离开本市字典
      leaveLocalOptions: [],
      // 14天内是否去过中高风险区字典
      havaBeenRiskAreaOptions: [],
      // 14天内同住人员是否去过中高风险区字典
      roommatesHavaBeenRiskAreaOptions: [],
      // 是否14天内回国字典
      backHomeOptions: [],
      // 同住人员是否14天内回国字典
      roommatesBackHomeOptions: [],
      // 是否被要求医学观察字典
      medicalWatchOptions: [],
      // 是否做过核酸检测  字典
      nucleicAcidTestingOptions: [],
      // 是否已经注射第一针疫苗字典
      injectionVaccineOneOptions: [],
      // 是否已经注射第二针疫苗字典
      injectionVaccineTwoOptions: [],
      //交通工具类型
      transportationTypesOptions: [],
      //中高风险区
      beenRiskAreaInformationOptions: [],
      //中高风险区选项分组
      beenRiskAreaInformationGroup: [],
      // 社区
      communityOptions: [],
      //小区列表全集
      allCommunityChildOptions: [],
      //小区列表
      communityChildOptions: [],
      //小区列表
      communityChildOptions2: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        sex: null,
        phone: null,
        idCard: null,
        addressDetail: null,
        addressJobDetail: null,
        communityChildId: null,
        communityChildName: null,
        epidemicGreenCode: null,
        roommates: null,
        roommatesSuspectedSymptoms: null,
        roommatesPatientExposureHistory: null,
        leaveLocal: null,
        leaveInformation: null,
        havaBeenRiskArea: null,
        havaBeenRiskAreaInformation: null,
        roommatesHavaBeenRiskArea: null,
        roommatesHavaBeenRiskAreaInformation: null,
        backHome: null,
        roommatesBackHome: null,
        medicalWatch: null,
        nucleicAcidTesting: null,
        injectionVaccineOne: null,
        injectionVaccineOneTime: null,
        injectionVaccineTwo: null,
        injectionVaccineTwoTime: null,
        addressArea: null,
        addressJobArea: null,
        communityId: null,
        communityName: null
      },
      // 表单参数
      form: {},
      //到达城市表单
      form_leaveInformation: {},
      //到达城市表单是否显示
      open_form_leaveInformation: false,
      //住址
      address: {
        provincial: null,
        citie: null,
        countie: null,
        code: null
      },
      //工作地址
      addressJob: {
        provincial: null,
        citie: null,
        countie: null,
        code: null
      },
      // 到达地址
      arriveCity: {
        provincial: null,
        citie: null,
        countie: null,
        code: null
      },
      // 表单校验
      rules: {
        name: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        sex: [
          { required: true, message: "性别不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "手机号不能为空", trigger: "blur" },
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur",
          },
        ],
        idCard: [
          { required: true, message: "身份证号码不能为空", trigger: "blur" },
          {
            pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
            message: "你的身份证格式不正确",
          },
        ],
        addressDetail: [
          { required: true, message: "现住详址不能为空", trigger: "blur" }
        ],
        epidemicGreenCode: [
          { required: true, message: "是否有防疫绿码不能为空", trigger: "blur" }
        ],
        roommates: [
          { required: true, message: "是否合租不能为空", trigger: "blur" }
        ],
        roommatesSuspectedSymptoms: [
          { required: true, message: "合租/同住人员是否有疑似症状不能为空", trigger: "blur" }
        ],
        roommatesPatientExposureHistory: [
          { required: true, message: "合租/同住人员是否有病患接触史不能为空", trigger: "blur" }
        ],
        leaveLocal: [
          { required: true, message: "14天内是否离开本市不能为空", trigger: "blur" }
        ],
        leaveInformation: [
          { required: true, message: "14天内出行信息不能为空", trigger: "blur" }
        ],
        havaBeenRiskArea: [
          { required: true, message: "14天内是否去过中高风险区不能为空", trigger: "blur" }
        ],
        havaBeenRiskAreaInformation: [
          { required: true, message: "14天内去过中高风险区的记录不能为空", trigger: "blur" }
        ],
        roommatesHavaBeenRiskArea: [
          { required: true, message: "14天内同住人员是否去过中高风险区不能为空", trigger: "blur" }
        ],
        roommatesHavaBeenRiskAreaInformation: [
          { required: true, message: "同住人员14天内去过中高风险区的记录不能为空", trigger: "blur" }
        ],
        backHome: [
          { required: true, message: "是否14天内回国不能为空", trigger: "blur" }
        ],
        roommatesBackHome: [
          { required: true, message: "同住人员是否14天内回国不能为空", trigger: "blur" }
        ],
        medicalWatch: [
          { required: true, message: "是否被要求医学观察不能为空", trigger: "blur" }
        ],
        nucleicAcidTesting: [
          { required: true, message: "是否做过核酸检测  不能为空", trigger: "blur" }
        ],
        injectionVaccineOne: [
          { required: true, message: "是否已经注射第一针疫苗不能为空", trigger: "blur" }
        ],
        injectionVaccineOneTime: [
          { required: true, message: "第一针疫苗 注射时间不能为空", trigger: "blur" }
        ],
        injectionVaccineTwo: [
          { required: true, message: "是否已经注射第二针疫苗不能为空", trigger: "blur" }
        ],
        injectionVaccineTwoTime: [
          { required: true, message: "第二针疫苗 注射时间不能为空", trigger: "blur" }
        ],
        addressArea: [
          { required: true, message: "请选择省市区", trigger: "blur" }
        ],
        addressJobArea: [
          { required: true, message: "请选择省市区", trigger: "blur" }
        ],
      },
      leaveInformation: [],//出行信息
      havaBeenRiskAreaInformation: [],//去过的中高风险区
      roommatesHavaBeenRiskAreaInformation: [],//同住去过的中高风险区
      // 到达城市表单校验
      rules_leaveInformation: {
        arriveCityArea: [
          { required: true, message: "到达城市不能为空", trigger: "blur" }
        ],
      },
      currentUser: {
        currentUserName: '',
        currentUserNickName: '',
        currentUserphoneNumber: '',
        currentUserDeptName: '',
      },
      //遮罩层，生成二维码
      createQRShow: false,
      form_QR: {
        communityId: null,
        communityChildId: null,
        // communityName: null,
        // communityChildName: null,
        createBy: null,
        baseUrl: null,
        QRBase64: null
      },
      rules_QR: {
        communityId: [
          { required: true, message: "社区不为为空", trigger: "blur" }
        ],
        communityChildId: [
          { required: true, message: "小区不能为空", trigger: "blur" }
        ],
        createBy: [
          { required: true, message: "生成人不能为空", trigger: "blur" }
        ],
        baseUrl: [
          { required: true, message: "填报地址不能为空", trigger: "blur" }
        ],
      },
      // 搜索表单控件高度
      formHeight: 0
    };
  },
  created () {
    this.getCommunityDictory()
    this.getAllCommunityChildDictory()
    // this.getList();
    this.getDicts("sys_user_sex").then(response => {
      this.sexOptions = response.data;
    });
    this.getDicts("base_epidemic_green_code").then(response => {
      this.epidemicGreenCodeOptions = response.data;
    });
    this.getDicts("base_shi_fou").then(response => {
      this.roommatesOptions = response.data;
      this.roommatesSuspectedSymptomsOptions = response.data;
      this.roommatesPatientExposureHistoryOptions = response.data;
      this.leaveLocalOptions = response.data;
      this.havaBeenRiskAreaOptions = response.data;
      this.roommatesHavaBeenRiskAreaOptions = response.data;
      this.backHomeOptions = response.data;
      this.roommatesBackHomeOptions = response.data;
      this.medicalWatchOptions = response.data;
      this.injectionVaccineOneOptions = response.data;
      this.injectionVaccineTwoOptions = response.data;
    });
    this.getDicts("yq_nucleic_acid_amplification_testing").then(response => {
      this.nucleicAcidTestingOptions = response.data;
    });
    this.getDicts("base_transportation_type").then(response => {
      this.transportationTypesOptions = response.data;
    });
    this.getDicts("yq_medium_and_high_risk_area").then(response => {
      this.beenRiskAreaInformationOptions = response.data;
      this.getBeenRiskAreaInformationGroup(this.beenRiskAreaInformationOptions)
    });
  },
  mounted () {
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight
    })
    this.getList();
    this.loadUserInfo()
  },
  methods: {
    getBeenRiskAreaInformationGroup (beenRiskAreaInformationList) {
      this.beenRiskAreaInformationGroup = []
      if (beenRiskAreaInformationList && beenRiskAreaInformationList.length > 0) {
        let areaGroup = []
        for (let index = 0; index < beenRiskAreaInformationList.length; index++) {
          const element = beenRiskAreaInformationList[index];
          let area = this.$cf.getAddressArea(element.dictLabel)
          let sheng = area[0]
          if (area && area.length > 0) {
            let groupIndex = this.$cf.findIndex(areaGroup, 'label', sheng)
            if (groupIndex < 0) {
              //还没有这个省
              areaGroup.push({
                label: sheng,
                options: []
              })
              groupIndex = areaGroup.length - 1
            }
            let label = element.dictLabel.substr(area[0].length, element.dictLabel.length - area[0].length)
            if (!(label && label.length > 0)) label = element.dictLabel
            areaGroup[groupIndex].options.push({
              value: element.dictLabel,
              label: label
            })
          }
        }
        this.beenRiskAreaInformationGroup = areaGroup
      }
    },
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
    getCommunityChildDictory2 (communityId) {
      this.communityChildOptions2 = []
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId
      }
      listCommunity_child(param).then(response => {
        this.communityChildOptions2 = response.rows;
      });
    },
    getAllCommunityChildDictory (communityId) {
      this.allCommunityChildOptions = []
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: null
      }
      listCommunity_child(param).then(response => {
        this.allCommunityChildOptions = response.rows;
      });
    },
    //搜索条件转换
    searchParamTras () {
      //单独的地址搜索字段
      this.queryParams.address = null
      this.queryParams.addressJob = null
      if (this.queryParams.addressDetail) {
        this.queryParams.address = null
      }
      if (this.queryParams.addressDetail) {
        this.queryParams.addressJob = null
      }
    },
    /** 查询疫情信息收集列表 */
    getList () {
      this.searchParamTras()
      this.loading = true;
      listPerson_epidemic_state_record(this.queryParams).then(response => {
        this.person_epidemic_state_recordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    communityNameFormat (row, column, cellValue) {
      if (this.communityOptions && this.communityOptions.length > 0) {
        let rows = this.communityOptions.filter(item => {
          return item.id === cellValue
        })
        if (rows && rows.length > 0) {
          return rows[0].name
        }
      }
      return '??'
    },
    communityChildNameFormat (row, column, cellValue) {
      if (this.allCommunityChildOptions && this.allCommunityChildOptions.length > 0) {
        let rows = this.allCommunityChildOptions.filter(item => {
          return item.id === cellValue
        })
        if (rows && rows.length > 0) {
          return rows[0].name
        }
      }
      return ''
    },
    // 性别字典翻译
    sexFormat (row, column) {
      return this.selectDictLabelByLable(this.sexOptions, row.sex);
    },
    // 是否有防疫绿码字典翻译
    epidemicGreenCodeFormat (row, column) {
      return this.selectDictLabel(this.epidemicGreenCodeOptions, row.epidemicGreenCode);
    },
    // 是否合租字典翻译
    roommatesFormat (row, column) {
      return this.selectDictLabel(this.roommatesOptions, row.roommates);
    },
    // 合租/同住人员是否有疑似症状字典翻译
    roommatesSuspectedSymptomsFormat (row, column) {
      return this.selectDictLabel(this.roommatesSuspectedSymptomsOptions, row.roommatesSuspectedSymptoms);
    },
    // 合租/同住人员是否有病患接触史字典翻译
    roommatesPatientExposureHistoryFormat (row, column) {
      return this.selectDictLabel(this.roommatesPatientExposureHistoryOptions, row.roommatesPatientExposureHistory);
    },
    // 14天内是否离开本市字典翻译
    leaveLocalFormat (row, column) {
      return this.selectDictLabel(this.leaveLocalOptions, row.leaveLocal);
    },
    // 14天内是否去过中高风险区字典翻译
    havaBeenRiskAreaFormat (row, column) {
      return this.selectDictLabel(this.havaBeenRiskAreaOptions, row.havaBeenRiskArea);
    },
    // 14天内同住人员是否去过中高风险区字典翻译
    roommatesHavaBeenRiskAreaFormat (row, column) {
      return this.selectDictLabel(this.roommatesHavaBeenRiskAreaOptions, row.roommatesHavaBeenRiskArea);
    },
    // 是否14天内回国字典翻译
    backHomeFormat (row, column) {
      return this.selectDictLabel(this.backHomeOptions, row.backHome);
    },
    // 同住人员是否14天内回国字典翻译
    roommatesBackHomeFormat (row, column) {
      return this.selectDictLabel(this.roommatesBackHomeOptions, row.roommatesBackHome);
    },
    // 是否被要求医学观察字典翻译
    medicalWatchFormat (row, column) {
      return this.selectDictLabel(this.medicalWatchOptions, row.medicalWatch);
    },
    // 是否做过核酸检测  字典翻译
    nucleicAcidTestingFormat (row, column) {
      return this.selectDictLabel(this.nucleicAcidTestingOptions, row.nucleicAcidTesting);
    },
    // 是否已经注射第一针疫苗字典翻译
    injectionVaccineOneFormat (row, column) {
      return this.selectDictLabel(this.injectionVaccineOneOptions, row.injectionVaccineOne);
    },
    // 是否已经注射第二针疫苗字典翻译
    injectionVaccineTwoFormat (row, column) {
      return this.selectDictLabel(this.injectionVaccineTwoOptions, row.injectionVaccineTwo);
    },
    addressFormat (row, column) {
      let address = ''
      if (row.addressArea) address += row.addressArea
      if (row.addressDetail) address += row.addressDetail
      return address
    },
    addressJobFormat (row, column) {
      let addressJob = ''
      if (row.addressJobArea) addressJob += row.addressJobArea
      if (row.addressJobDetail) addressJob += row.addressJobDetail
      return addressJob
    },
    // 取消按钮
    cancel () {
      this.open = false;
      // this.reset();
    },
    // 到达城市 表单重置
    reset_LeaveInformation () {
      this.form_leaveInformation = {
        arriveCityArea: null,
        arriveData: null,
        arriveTransportationTypes: [],//到达时乘坐的交通工具类型
        arriveTransportationValues: null,//到达时乘坐的交通工具车次
        leaveData: null,
        leaveTransportationTypes: null,//离开时乘坐的交通工具类型
        leaveTransportationValues: null,//离开时乘坐的交通工具车次
      }
    },
    //新增一条出行地点
    handleAdd_leaveLocal () {
      this.reset_LeaveInformation()
      this.open_form_leaveInformation = true
    },
    //删除一条通行地点
    handleDelete_leaveLocal (row) {
      if (this.form && this.leaveInformation && this.leaveInformation.length > 0) {
        this.leaveInformation.remove(row)
      }
    },
    //提交一条出行地点
    submitForm_leaveInformation () {
      let self = this
      this.$refs["form_leaveInformation"].validate(valid => {
        if (valid) {
          // form.leaveInformation=[] form_leaveInformation
          self.leaveInformation.push({
            id: self.$cf.Guid(),
            arriveCityArea: self.form_leaveInformation.arriveCityArea,
            arriveData: self.form_leaveInformation.arriveData,
            arriveTransportationTypes: self.form_leaveInformation.arriveTransportationTypes,//到达时乘坐的交通工具类型
            arriveTransportationValues: self.form_leaveInformation.arriveTransportationValues,//到达时乘坐的交通工具车次
            leaveData: self.form_leaveInformation.leaveData,
            leaveTransportationTypes: self.form_leaveInformation.leaveTransportationTypes,//离开时乘坐的交通工具类型
            leaveTransportationValues: self.form_leaveInformation.leaveTransportationValues,//离开时乘坐的交通工具车次
          })
          this.open_form_leaveInformation = false
        }
      })
    },
    //取消新增出行地点
    cancel_leaveInformation () {
      this.open_form_leaveInformation = false
    },
    //展示出行地点信息
    getShowAddressInfo (row) {
      if (row && row.arriveCityArea) {
        return row.arriveCityArea + ' ' + row.arriveData + ' -- ' + row.leaveData + '  (' + row.arriveTransportationValues + ')'
      } else return ''
    },
    //获取填报信息的单元格类名
    getCellClassName ({ row, column, rowIndex, columnIndex }) {
      let propertyName = column.property //"communityId"
      let className = ''
      switch (propertyName) {
        //绿码
        case 'epidemicGreenCode': if (row[propertyName] && row[propertyName] === '无') className = 'yellowText'
        //疑似症状
        case 'roommatesSuspectedSymptoms': if (row[propertyName] && row[propertyName] === '是') className = 'yellowText'
          break
        //病患接触史
        case 'roommatesPatientExposureHistory': if (row[propertyName] && row[propertyName] === '是') className = 'redText'
          break
        //中高风险区
        case 'havaBeenRiskArea': if (row[propertyName] && row[propertyName] === '是') className = 'redText'
          break
        //医学观察
        case 'medicalWatch': if (row[propertyName] && row[propertyName] === '是') className = 'redText'
          break
        //核酸检测
        case 'nucleicAcidTesting': if (row[propertyName] && row[propertyName] === '阳性') className = 'redText'
          break
        //14天内是否离开本市
        case 'leaveLocal': if (row[propertyName] && row[propertyName] === '是') className = 'yellowText'
          break
        default: break
      }
      return className
    },
    // 表单重置
    reset () {
      this.form = {
        id: null,
        name: null,
        sex: '男',
        phone: null,
        idCard: null,
        addressDetail: null,
        addressJobDetail: null,
        communityChildId: null,
        communityChildName: null,
        epidemicGreenCode: "有",
        roommates: "否",
        roommatesSuspectedSymptoms: "否",
        roommatesPatientExposureHistory: "否",
        leaveLocal: "否",
        leaveInformation: null,//出行信息
        havaBeenRiskArea: "否",
        havaBeenRiskAreaInformation: null,//去过的中高风险区
        roommatesHavaBeenRiskArea: "否",
        roommatesHavaBeenRiskAreaInformation: null,//去过的中高风险区
        backHome: "否",
        roommatesBackHome: "否",
        medicalWatch: "否",
        nucleicAcidTesting: "未检测",
        injectionVaccineOne: "否",
        injectionVaccineOneTime: null,
        injectionVaccineTwo: "否",
        injectionVaccineTwoTime: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        remark: null,
        addressArea: null,
        addressJobArea: null,
        communityId: null,
        communityName: null
      };
      this.resetForm("form");
      this.addressJobArea = ''
      this.havaBeenRiskAreaInformation = null//去过的中高风险区
      this.leaveInformation = []//出行信息
      this.roommatesHavaBeenRiskAreaInformation = []//去过的中高风险区
      this.reset_LeaveInformation()
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
      this.title = "添加疫情信息收集";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const id = row.id || this.ids
      getPerson_epidemic_state_record(id).then(response => {
        this.getShowEpidemicInfoFromDB(response.data)
        this.form = response.data;
        this.getCommunityChildDictory2(this.form.communityId)
        this.open = true;
        this.title = "修改疫情信息收集";
      });
    },
    //将数据库的信息转换成可以展示的
    getShowEpidemicInfoFromDB (form) {
      this.addressJobArea = form.addressJobArea
      this.addressArea = form.addressArea
      if (form.leaveInformation && form.leaveInformation.length > 0) {
        this.leaveInformation = JSON.parse(form.leaveInformation)
      } else {
        this.leaveInformation = []
      }
      this.havaBeenRiskAreaInformation = this.$cf.getListFromString(form.havaBeenRiskAreaInformation)
      this.roommatesHavaBeenRiskAreaInformation = this.$cf.getListFromString(form.roommatesHavaBeenRiskAreaInformation)
    },
    //将展示的字段转换成数据库可以存储的
    getDBEpidemicInfoFromShow (form) {
      form.leaveInformation = ''
      if (this.leaveInformation && this.leaveInformation.length > 0)
        form.leaveInformation = JSON.stringify(this.leaveInformation)
      form.havaBeenRiskAreaInformation = this.$cf.getStringFromList(this.havaBeenRiskAreaInformation)
      form.roommatesHavaBeenRiskAreaInformation = this.$cf.getStringFromList(this.roommatesHavaBeenRiskAreaInformation)
    },
    /**补齐需要的信息 */
    makeUpInfo (form) {
      if (form.communityId) {
        form.communityName = this.communityNameFormat(null, null, form.communityId)
      }
      if (form.communityChildId) {
        form.communityChildName = this.communityChildNameFormat(null, null, form.communityChildId)
      }
    },
    /** 提交按钮 */
    submitForm () {
      let self = this
      this.makeUpInfo(self.form)
      self.getDBEpidemicInfoFromShow(self.form)
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (self.form.id != null) {
            updatePerson_epidemic_state_record(self.form).then(response => {
              if (response.code === 200) {
                self.msgSuccess("修改成功");
                self.open = false;
                self.getList();
              }
            });
          } else {
            addPerson_epidemic_state_record(self.form).then(response => {
              if (response.code === 200) {
                self.msgSuccess("新增成功");
                self.open = false;
                self.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除疫情信息收集编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delPerson_epidemic_state_record(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有疫情信息收集数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportPerson_epidemic_state_record(queryParams);
      }).then(response => {
        this.download(response.msg);
      }).catch(function () { });
    },
    /**重置二维码输入参数 */
    resetQR () {
      this.form_QR = {
        communityId: null,
        communityChildId: null,
        createBy: null,
        baseUrl: null,
        QRBase64: null
      }
      if (this.currentUser.currentUserName) this.form_QR.createBy = this.currentUser.currentUserName
      this.form_QR.baseUrl = configInfo.epidemicDeclareUrl
      if (this.communityOptions && this.communityOptions.length > 0) {
        this.form_QR.communityId = this.communityOptions[0].id
        this.getCommunityChildDictory2(this.form_QR.communityId)
      }
      if (this.communityChildOptions2 && this.communityChildOptions2.length > 0) {
        this.form_QR.communityChildId = this.communityChildOptions2[0].id
      }
    },
    /** 生成录入信息页面的二维码 */
    handleCreate_QRCode () {
      this.resetQR()
      this.createQRShow = true
    },
    /**关闭生成二维码窗口 */
    cancel_QR () {
      this.createQRShow = false
    },
    /**保存到本地 */
    Save_QR () {
      let communityName = this.communityNameFormat(null, null, this.form_QR.communityId)
      let communityChildName = this.communityChildNameFormat(null, null, this.form_QR.communityChildId)
      let name = communityName + communityChildName + '_' + this.form_QR.createBy + '_' + this.$cf.getNowTime()
      var image = document.getElementById('QRImage')
      var url = image.src
      var a = document.createElement('a')
      var event = new MouseEvent('click')
      a.download = name || '下载图片名称'
      a.href = url
      a.dispatchEvent(event) //根据A标签的属性来搞事情
    },
    /**生成二维码 */
    createQR () {
      let self = this
      this.$refs["form_QR"].validate(valid => {
        if (valid) {
          //获取完整的url
          let communityName = this.communityNameFormat(null, null, this.form_QR.communityId)
          let communityChildName = this.communityChildNameFormat(null, null, this.form_QR.communityChildId)

          let communityId = this.form_QR.communityId ? ('=' + this.form_QR.communityId) : ''
          let communityChildId = this.form_QR.communityChildId ? ('=' + this.form_QR.communityChildId) : ''
          communityName = communityName ? ('=' + communityName) : ''
          communityChildName = communityChildName ? ('=' + communityChildName) : ''
          let createBy = this.form_QR.createBy ? ('=' + this.form_QR.createBy) : ''
          let param = {
            url: this.form_QR.baseUrl + '?communityId' + communityId + '&communityChildId' + communityChildId
              + '&communityName' + communityName + '&communityChildName' + communityChildName
              + '&createBy' + createBy
          }
          generateQRCodeToBase64(param).then(response => {
            if (response && response.code === 200) {
              this.form_QR.QRBase64 = response.data
              this.msgSuccess("生成成功");
            } else {
              this.msgError("生成失败");
            }
          });
        }
      })
    },
    loadUserInfo () {
      if (this.$cf.checkIsNotNull(this.$store.state.user)) {
        //用户本身信息
        this.currentUser.currentUserName = this.$store.state.user.name
        this.currentUser.currentUserNickName = this.$store.state.user.nickName
        this.currentUser.currentUserphoneNumber = this.$store.state.user.phoneNumber
        //部门信息
        if (this.$cf.checkIsNotNull(this.$store.state.user.dept)) {
          this.currentUser.currentUserDeptName = this.$store.state.user.dept.name
        }
      } else {

      }
    },
  },
  computed: {
    dataHeight () {
      let searchFormHeight = 0
      if (this.showSearch) searchFormHeight = this.formHeight
      return this.$store.getters.dataEleHeight - searchFormHeight - 160
    },
    addressArea: {
      // getter
      get: function () {
        if (this.address && this.address.provincial && this.address.citie && this.address.countie) {
          this.form.addressArea = this.address.provincial + this.address.citie + this.address.countie

        } else {
          this.form.addressArea = null
        }
        return this.form.addressArea
      },
      // setter
      set: function (newValue) {
        let areas = this.$cf.getAddressArea(newValue)
        if (areas && areas.length > 2) {
          this.address.provincial = areas[0]
          this.address.citie = areas[1]
          this.address.countie = areas[2]
        }
      }

    },
    addressJobArea: {
      // getter
      get: function () {
        if (this.addressJob && this.addressJob.provincial && this.addressJob.citie && this.addressJob.countie) {
          this.form.addressJobArea = this.addressJob.provincial + this.addressJob.citie + this.addressJob.countie
        } else {
          this.form.addressJobArea = null
        }
        return this.form.addressJobArea
      },
      // setter
      set: function (newValue) {
        let areas = this.$cf.getAddressArea(newValue)
        if (areas && areas.length > 2) {
          this.addressJob.provincial = areas[0]
          this.addressJob.citie = areas[1]
          this.addressJob.countie = areas[2]
        }
      }
    },
    arriveCityArea () {
      if (this.arriveCity && this.arriveCity.provincial && this.arriveCity.citie && this.arriveCity.countie) {
        this.form_leaveInformation.arriveCityArea = this.arriveCity.provincial + this.arriveCity.citie + this.arriveCity.countie
      } else {
        this.form_leaveInformation.arriveCityArea = null
      }
      return this.form_leaveInformation.arriveCityArea
    },
  }
};
</script>
<style scoped>
.full-img {
  width: 518px;
  height: 518px;
}
</style>
