<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch">
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
      <el-form-item label="名称"
                    prop="name">
        <el-input v-model="queryParams.name"
                  clearable
                  size="small"
                  placeholder="请输入名称"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="统一社会信用代码"
                    prop="unifiedSocialCreditCode">
        <el-input v-model="queryParams.unifiedSocialCreditCode"
                  placeholder="请输入统一社会信用代码"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="登记证号"
                    prop="registrationCode">
        <el-input v-model="queryParams.registrationCode"
                  placeholder="请输入登记证号(15位登记证号)"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="成立日期"
                    prop="dateOfEstablishment">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="queryParams.dateOfEstablishment"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择成立日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="负责人姓名"
                    prop="nameOfPersonInCharge">
        <el-input v-model="queryParams.nameOfPersonInCharge"
                  placeholder="请输入负责人姓名"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <!-- <el-form-item label="负责人身份证"
                    prop="identificationNumberOfPersonInCharge">
        <el-input v-model="queryParams.identificationNumberOfPersonInCharge"
                  placeholder="请输入负责人身份证"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <!-- <el-form-item label="负责人性别"
                    prop="sexOfPersonInCharge">
        <el-select v-model="queryParams.sexOfPersonInCharge"
                   placeholder="请选择负责人性别"
                   clearable
                   size="small">
          <el-option v-for="dict in sexOfPersonInChargeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <!-- <el-form-item label="负责人联系方式"
                    prop="phonenumOfPersonInCharge">
        <el-input v-model="queryParams.phonenumOfPersonInCharge"
                  placeholder="请输入负责人联系方式"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <!-- <el-form-item label="治安负责人姓名"
                    prop="securityResponsibleName">
        <el-input v-model="queryParams.securityResponsibleName"
                  placeholder="请输入治安负责人姓名"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <!-- <el-form-item label="治安负责人联系方式"
                    prop="securityResponsiblePhone">
        <el-input v-model="queryParams.securityResponsiblePhone"
                  placeholder="请输入治安负责人联系方式"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <!-- <el-form-item label="有境外背景关系"
                    prop="haveForeignRelationship">
        <el-select v-model="queryParams.haveForeignRelationship"
                   placeholder="请选择有境外背景关系"
                   clearable
                   size="small">
          <el-option v-for="dict in haveForeignRelationshipOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <!-- <el-form-item label="员工人数"
                    prop="numberOfEmployees">
        <el-input v-model="queryParams.numberOfEmployees"
                  placeholder="请输入员工人数"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <!-- <el-form-item label="社会组织类型"
                    prop="socialOrganizationType">
        <el-select v-model="queryParams.socialOrganizationType"
                   placeholder="请选择社会组织类型"
                   clearable
                   size="small">
          <el-option v-for="dict in socialOrganizationTypeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <!-- <el-form-item label="状态"
                    prop="status">
        <el-select v-model="queryParams.status"
                   placeholder="请选择状态"
                   clearable
                   size="small">
          <el-option v-for="dict in statusOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="关注程度"
                    prop="caringLevel">
        <el-select v-model="queryParams.caringLevel"
                   placeholder="请选择关注程度"
                   clearable
                   size="small">
          <el-option v-for="dict in caringLevelOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <!-- <el-form-item label="是否具备条件建立中国共产党支部"
                    prop="canCreateChineseCommunistPartyBranch">
        <el-select v-model="queryParams.canCreateChineseCommunistPartyBranch"
                   placeholder="请选择是否具备条件建立中国共产党支部"
                   clearable
                   size="small">
          <el-option v-for="dict in canCreateChineseCommunistPartyBranchOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="是否有中国共产党支部"
                    prop="haveChineseCommunistPartyBranch">
        <el-select v-model="queryParams.haveChineseCommunistPartyBranch"
                   placeholder="请选择是否有中国共产党支部"
                   clearable
                   size="small">
          <el-option v-for="dict in haveChineseCommunistPartyBranchOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="共产党支部人员数量"
                    prop="chineseCommunistPartyBranchPeopleNum">
        <el-input v-model="queryParams.chineseCommunistPartyBranchPeopleNum"
                  placeholder="请输入共产党支部人员数量"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="是否有公会"
                    prop="haveUnion">
        <el-select v-model="queryParams.haveUnion"
                   placeholder="请选择是否有公会"
                   clearable
                   size="small">
          <el-option v-for="dict in haveUnionOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="公会人数"
                    prop="unionPeopleNum">
        <el-input v-model="queryParams.unionPeopleNum"
                  placeholder="请输入公会人数"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="是否有共青团"
                    prop="haveCommunistYouthLeague">
        <el-select v-model="queryParams.haveCommunistYouthLeague"
                   placeholder="请选择是否有共青团"
                   clearable
                   size="small">
          <el-option v-for="dict in haveCommunistYouthLeagueOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="共青团员数量"
                    prop="communistYouthLeaguePeopleNum">
        <el-input v-model="queryParams.communistYouthLeaguePeopleNum"
                  placeholder="请输入共青团员数量"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="是否有妇联组织"
                    prop="haveWomenSFederation">
        <el-select v-model="queryParams.haveWomenSFederation"
                   placeholder="请选择是否有妇联组织"
                   clearable
                   size="small">
          <el-option v-for="dict in haveWomenSFederationOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="妇联组织成员人数"
                    prop="womenSFederationPeopleNum">
        <el-input v-model="queryParams.womenSFederationPeopleNum"
                  placeholder="请输入妇联组织成员人数"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
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
                   v-hasPermi="['communityManage:social_organization:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityManage:social_organization:remove']">删除</el-button>
        <!-- <el-button icon="el-icon-refresh"
                   size="mini"
                   @click="resetQuery">重置</el-button> -->
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10"
            class="mb8">
      <el-col :span="1.5">
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAdd"
                   v-hasPermi="['communityManage:social_organization:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['communityManage:social_organization:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityManage:social_organization:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['communityManage:social_organization:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading"
              stripe
              :height="dataHeight"
              :data="social_organizationList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="名称"
                       align="center"
                       prop="name" />
      <el-table-column label="统一社会信用代码"
                       align="center"
                       prop="unifiedSocialCreditCode" />
      <el-table-column label="登记证号"
                       align="center"
                       prop="registrationCode" />
      <el-table-column label="注册地址"
                       align="center"
                       prop="registeredAddress" />
      <el-table-column label="成立日期"
                       align="center"
                       prop="dateOfEstablishment"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.dateOfEstablishment, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="法人代表"
                       align="center"
                       prop="legalRepresentative" />
      <el-table-column label="负责人姓名"
                       align="center"
                       prop="nameOfPersonInCharge" />
      <!-- <el-table-column label="负责人身份证"
                       align="center"
                       prop="identificationNumberOfPersonInCharge" /> -->
      <!-- <el-table-column label="负责人性别"
                       align="center"
                       prop="sexOfPersonInCharge"
                       :formatter="sexOfPersonInChargeFormat" /> -->
      <el-table-column label="负责人联系方式"
                       align="center"
                       prop="phonenumOfPersonInCharge" />
      <!-- <el-table-column label="负责人联系地址"
                       align="center"
                       prop="addressOfPersonInCharge" /> -->
      <!-- <el-table-column label="治安负责人姓名"
                       align="center"
                       prop="securityResponsibleName" />
      <el-table-column label="治安负责人联系方式"
                       align="center"
                       prop="securityResponsiblePhone" /> -->
      <el-table-column label="有境外背景关系"
                       align="center"
                       prop="haveForeignRelationship"
                       :formatter="haveForeignRelationshipFormat" />
      <el-table-column label="社会组织类型"
                       align="center"
                       prop="socialOrganizationType"
                       :formatter="socialOrganizationTypeFormat" />
      <el-table-column label="状态"
                       align="center"
                       prop="status"
                       :formatter="statusFormat" />
      <el-table-column label="关注程度"
                       align="center"
                       prop="caringLevel"
                       :formatter="caringLevelFormat" />
      <el-table-column label="操作"
                       align="center"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['communityManage:social_organization:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['communityManage:social_organization:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改社会组织信息对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="600px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="150px">
        <el-form-item label="社区名称"
                      prop="communityId">
          <el-select v-model="form.communityId"
                     filterable
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
                     placeholder="请选择小区">
            <el-option v-for="dict in communityChildOptions2"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="房屋"
                      prop="communityHousesId">
          <el-select v-model="form.communityHousesId"
                     filterable
                     clearable
                     remote
                     :remote-method="getHousesList"
                     :loading="loading_remoteHouseInfo"
                     placeholder="请选择房屋">
            <el-option v-for="dict in communityHousesOptions"
                       :key="dict.id"
                       :label="dict.serialNumber + '号楼' + dict.unit + '单元' + dict.floor + '层' + dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="名称"
                      prop="name">
          <el-input v-model="form.name"
                    type="textarea"
                    placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="统一社会信用代码"
                      prop="unifiedSocialCreditCode">
          <el-input v-model="form.unifiedSocialCreditCode"
                    placeholder="请输入统一社会信用代码(18位信用码)" />
        </el-form-item>
        <el-form-item label="登记证号"
                      prop="registrationCode">
          <el-input v-model="form.registrationCode"
                    placeholder="请输入登记证号(15位登记证号)" />
        </el-form-item>
        <el-form-item label="注册地址"
                      prop="registeredAddress">
          <el-input v-model="form.registeredAddress"
                    type="textarea"
                    placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="成立日期"
                      prop="dateOfEstablishment">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.dateOfEstablishment"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择成立日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="法人代表"
                      prop="legalRepresentative">
          <el-input v-model="form.legalRepresentative"
                    type="textarea"
                    placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="负责人姓名"
                      prop="nameOfPersonInCharge">
          <el-input v-model="form.nameOfPersonInCharge"
                    placeholder="请输入负责人姓名" />
        </el-form-item>
        <el-form-item label="负责人身份证"
                      prop="identificationNumberOfPersonInCharge">
          <el-input v-model="form.identificationNumberOfPersonInCharge"
                    placeholder="请输入负责人身份证(18位身份证号码)" />
        </el-form-item>
        <el-form-item label="负责人性别"
                      prop="sexOfPersonInCharge">
          <el-select v-model="form.sexOfPersonInCharge"
                     placeholder="请选择负责人性别">
            <el-option v-for="dict in sexOfPersonInChargeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="负责人联系方式"
                      prop="phonenumOfPersonInCharge">
          <el-input v-model="form.phonenumOfPersonInCharge"
                    placeholder="请输入负责人联系方式" />
        </el-form-item>
        <el-form-item label="负责人联系地址"
                      prop="addressOfPersonInCharge">
          <el-input v-model="form.addressOfPersonInCharge"
                    type="textarea"
                    placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="治安负责人姓名"
                      prop="securityResponsibleName">
          <el-input v-model="form.securityResponsibleName"
                    placeholder="请输入治安负责人姓名" />
        </el-form-item>
        <el-form-item label="治安负责人联系方式"
                      prop="securityResponsiblePhone">
          <el-input v-model="form.securityResponsiblePhone"
                    placeholder="请输入治安负责人联系方式" />
        </el-form-item>
        <el-form-item label="经度"
                      prop="longitude">
          <el-input v-model="form.longitude"
                    placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度"
                      prop="latitude">
          <el-input v-model="form.latitude"
                    placeholder="请输入纬度" />
        </el-form-item>
        <el-form-item label="有境外背景关系"
                      prop="haveForeignRelationship">
          <el-select v-model="form.haveForeignRelationship"
                     placeholder="请选择有境外背景关系">
            <el-option v-for="dict in haveForeignRelationshipOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="注册资金"
                      prop="registeredCapital">
          <el-input v-model="form.registeredCapital"
                    placeholder="请输入注册资金" />
        </el-form-item>
        <el-form-item label="员工人数"
                      prop="numberOfEmployees">
          <el-input v-model="form.numberOfEmployees"
                    placeholder="请输入员工人数 (比如“500-700人”)" />
        </el-form-item>
        <el-form-item label="社会组织类型"
                      prop="socialOrganizationType">
          <el-select v-model="form.socialOrganizationType"
                     placeholder="请选择社会组织类型">
            <el-option v-for="dict in socialOrganizationTypeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="状态"
                      prop="status">
          <el-select v-model="form.status"
                     placeholder="请选择状态">
            <el-option v-for="dict in statusOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="关注程度"
                      prop="caringLevel">
          <el-select v-model="form.caringLevel"
                     placeholder="请选择关注程度">
            <el-option v-for="dict in caringLevelOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="parseInt(dict.dictValue)"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否具备条件建立中国共产党支部"
                      prop="canCreateChineseCommunistPartyBranch">
          <el-select v-model="form.canCreateChineseCommunistPartyBranch"
                     placeholder="请选择是否具备条件建立中国共产党支部">
            <el-option v-for="dict in canCreateChineseCommunistPartyBranchOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否有中国共产党支部"
                      prop="haveChineseCommunistPartyBranch">
          <el-select v-model="form.haveChineseCommunistPartyBranch"
                     placeholder="请选择是否有中国共产党支部">
            <el-option v-for="dict in haveChineseCommunistPartyBranchOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="共产党支部人员数量"
                      prop="chineseCommunistPartyBranchPeopleNum">
          <el-input v-model="form.chineseCommunistPartyBranchPeopleNum"
                    placeholder="请输入共产党支部人员数量" />
        </el-form-item>
        <el-form-item label="是否有公会"
                      prop="haveUnion">
          <el-select v-model="form.haveUnion"
                     placeholder="请选择是否有公会">
            <el-option v-for="dict in haveUnionOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="公会人数"
                      prop="unionPeopleNum">
          <el-input v-model="form.unionPeopleNum"
                    placeholder="请输入公会人数" />
        </el-form-item>
        <el-form-item label="是否有共青团"
                      prop="haveCommunistYouthLeague">
          <el-select v-model="form.haveCommunistYouthLeague"
                     placeholder="请选择是否有共青团">
            <el-option v-for="dict in haveCommunistYouthLeagueOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="共青团员数量"
                      prop="communistYouthLeaguePeopleNum">
          <el-input v-model="form.communistYouthLeaguePeopleNum"
                    placeholder="请输入共青团员数量" />
        </el-form-item>
        <el-form-item label="是否有妇联组织"
                      prop="haveWomenSFederation">
          <el-select v-model="form.haveWomenSFederation"
                     placeholder="请选择是否有妇联组织">
            <el-option v-for="dict in haveWomenSFederationOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="妇联组织成员人数"
                      prop="womenSFederationPeopleNum">
          <el-input v-model="form.womenSFederationPeopleNum"
                    placeholder="请输入妇联组织成员人数" />
        </el-form-item>
        <el-form-item label="资金来源信息"
                      prop="sourcesOfFunds">
          <el-input v-model="form.sourcesOfFunds"
                    type="textarea"
                    placeholder="请输入内容" />
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
  </div>
</template>

<script>
import { listSocial_organization, getSocial_organization, delSocial_organization, addSocial_organization, updateSocial_organization, exportSocial_organization } from "@/api/communityManage/social_organization";
import { listCommunity_houses, getCommunity_houses, } from "@/api/communityUnit/community_houses";
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_building } from "@/api/communityUnit/community_building";
import { listCommunity_child } from "@/api/communityUnit/community_child";
export default {
  name: "Social_organization",
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
      // 社会组织信息表格数据
      social_organizationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 负责人性别字典
      sexOfPersonInChargeOptions: [],
      // 社会组织类型字典
      socialOrganizationTypeOptions: [],
      // 状态字典
      statusOptions: [],
      // 有境外背景关系(0为没有1为有，默认0)字典
      haveForeignRelationshipOptions: [],
      // 关注程度字典
      caringLevelOptions: [],
      // 是否具备条件建立中国共产党支部,(0否1是,默认0)字典
      canCreateChineseCommunistPartyBranchOptions: [],
      // 是否有中国共产党支部,(0否1是,默认0)字典
      haveChineseCommunistPartyBranchOptions: [],
      // 是否有公会,(0否1是,默认0)字典
      haveUnionOptions: [],
      // 是否有共青团,(0否1是,默认0)字典
      haveCommunistYouthLeagueOptions: [],
      // 是否有妇联组织,(0否1是,默认0)字典
      haveWomenSFederationOptions: [],
      //社区列表
      communityOptions: [],
      //小区列表
      communityChildOptions: [],
      //小区列表
      communityChildOptions2: [],
      //小区列表全集
      allCommunityChildOptions: [],
      //社区楼栋列表
      communityBuildingOptions: [],
      // 房屋列表
      communityHousesOptions: [],
      //房屋列表加载
      loading_remoteHouseInfo: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        communityId: null,
        communityName: null,
        communityChildId: null,
        communityChildName: null,
        communityBuildingId: null,
        serialNumber: null,
        buildingName: null,
        houseNumber: null,
        name: null,
        unifiedSocialCreditCode: null,
        registrationCode: null,
        registeredAddress: null,
        dateOfEstablishment: null,
        legalRepresentative: null,
        nameOfPersonInCharge: null,
        identificationNumberOfPersonInCharge: null,
        sexOfPersonInCharge: null,
        phonenumOfPersonInCharge: null,
        addressOfPersonInCharge: null,
        securityResponsibleName: null,
        securityResponsiblePhone: null,
        haveForeignRelationship: null,
        numberOfEmployees: null,
        socialOrganizationType: null,
        status: null,
        caringLevel: null,
        canCreateChineseCommunistPartyBranch: null,
        haveChineseCommunistPartyBranch: null,
        chineseCommunistPartyBranchPeopleNum: null,
        haveUnion: null,
        unionPeopleNum: null,
        haveCommunistYouthLeague: null,
        communistYouthLeaguePeopleNum: null,
        haveWomenSFederation: null,
        womenSFederationPeopleNum: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      // 搜索表单控件高度
      formHeight: 0
    };
  },
  created () {
    this.getAllCommunityChildDictory();
    this.getCommunityDictory()
    this.getList();
    this.getDicts("sys_user_sex").then(response => {
      this.sexOfPersonInChargeOptions = response.data;
    });
    this.getDicts("base_social_organization").then(response => {
      this.socialOrganizationTypeOptions = response.data;
    });
    this.getDicts("base_social_organization_status").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.haveForeignRelationshipOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.caringLevelOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.canCreateChineseCommunistPartyBranchOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.haveChineseCommunistPartyBranchOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.haveUnionOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.haveCommunistYouthLeagueOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.haveWomenSFederationOptions = response.data;
    });
  },
  mounted () {
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight
    })
  },
  computed: {
    dataHeight () {
      let searchFormHeight = 0
      if (this.showSearch) searchFormHeight = this.formHeight
      return this.$store.getters.dataEleHeight - searchFormHeight - 115
    }
  },
  methods: {
    communityNameFormat (cellValue) {
      if (this.communityOptions && this.communityOptions.length > 0) {
        let rows = this.communityOptions.filter(item => {
          return item.id === cellValue
        })
        if (rows && rows.length > 0) {
          return rows[0].name + '  '
        }
      }
      return '??'
    },
    communityChildNameFormat (cellValue) {
      if (this.allCommunityChildOptions && this.allCommunityChildOptions.length > 0) {
        let rows = this.allCommunityChildOptions.filter(item => {
          return item.id === cellValue
        })
        if (rows && rows.length > 0) {
          return rows[0].name + '  '
        }
      }
      return ''
    },
    houseAddressFormat (row) {
      return row.buildingName + ' ' + row.unit + '单元' + row.floor + '层' + row.houseNumber
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
      this.queryParams.communityChildId = null
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
      this.communityChildOptions = []
      this.form.communityChildId = null
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
    getHouseName (house) {
      if (house) {
        return house.serialNumber + '号楼' + house.unit + '单元' + house.houseNumber
      }
      else return ''
    },
    getHousesList (houseNumber) {
      this.communityHousesOptions = []
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: null,
        communityChildId: null,
        houseNumber: houseNumber
      }
      if (this.form.communityId) {
        param.communityId = this.form.communityId
      }
      if (this.form.communityChildId) {
        param.communityChildId = this.form.communityChildId
      }
      this.loading_remoteHouseInfo = true
      listCommunity_houses(param).then(response => {
        this.loading_remoteHouseInfo = false
        this.communityHousesOptions = response.rows;
      });
    },
    /** 查询社会组织信息列表 */
    getList () {
      this.loading = true;
      listSocial_organization(this.queryParams).then(response => {
        this.social_organizationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 负责人性别字典翻译
    sexOfPersonInChargeFormat (row, column) {
      return this.selectDictLabel(this.sexOfPersonInChargeOptions, row.sexOfPersonInCharge);
    },
    // 状态字典翻译
    statusFormat (row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 社会组织类型字典翻译
    socialOrganizationTypeFormat (row, column) {
      return this.selectDictLabel(this.socialOrganizationTypeOptions, row.socialOrganizationType);
    },
    // 有境外背景关系(0为没有1为有，默认0)字典翻译
    haveForeignRelationshipFormat (row, column) {
      return this.selectDictLabel(this.haveForeignRelationshipOptions, row.haveForeignRelationship);
    },
    // 关注程度字典翻译
    caringLevelFormat (row, column) {
      return this.selectDictLabel(this.caringLevelOptions, row.caringLevel);
    },
    // 是否具备条件建立中国共产党支部,(0否1是,默认0)字典翻译
    canCreateChineseCommunistPartyBranchFormat (row, column) {
      return this.selectDictLabel(this.canCreateChineseCommunistPartyBranchOptions, row.canCreateChineseCommunistPartyBranch);
    },
    // 是否有中国共产党支部,(0否1是,默认0)字典翻译
    haveChineseCommunistPartyBranchFormat (row, column) {
      return this.selectDictLabel(this.haveChineseCommunistPartyBranchOptions, row.haveChineseCommunistPartyBranch);
    },
    // 是否有公会,(0否1是,默认0)字典翻译
    haveUnionFormat (row, column) {
      return this.selectDictLabel(this.haveUnionOptions, row.haveUnion);
    },
    // 是否有共青团,(0否1是,默认0)字典翻译
    haveCommunistYouthLeagueFormat (row, column) {
      return this.selectDictLabel(this.haveCommunistYouthLeagueOptions, row.haveCommunistYouthLeague);
    },
    // 是否有妇联组织,(0否1是,默认0)字典翻译
    haveWomenSFederationFormat (row, column) {
      return this.selectDictLabel(this.haveWomenSFederationOptions, row.haveWomenSFederation);
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
        communityId: null,
        communityChildId: null,
        communityHousesId: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        remark: null,
        name: null,
        unifiedSocialCreditCode: null,
        registrationCode: null,
        registeredAddress: null,
        dateOfEstablishment: null,
        legalRepresentative: null,
        nameOfPersonInCharge: null,
        identificationNumberOfPersonInCharge: null,
        sexOfPersonInCharge: null,
        phonenumOfPersonInCharge: null,
        addressOfPersonInCharge: null,
        securityResponsibleName: null,
        securityResponsiblePhone: null,
        longitude: null,
        latitude: null,
        haveForeignRelationship: null,
        registeredCapital: null,
        numberOfEmployees: null,
        socialOrganizationType: null,
        status: null,
        caringLevel: null,
        canCreateChineseCommunistPartyBranch: null,
        haveChineseCommunistPartyBranch: null,
        chineseCommunistPartyBranchPeopleNum: null,
        haveUnion: null,
        unionPeopleNum: null,
        haveCommunistYouthLeague: null,
        communistYouthLeaguePeopleNum: null,
        haveWomenSFederation: null,
        womenSFederationPeopleNum: null,
        sourcesOfFunds: null
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
      this.title = "添加社会组织信息";
    },
    getHouseByID (id) {
      this.communityHousesOptions = []
      if (id && id.length > 0) {
        getCommunity_houses(id).then((response) => {
          this.communityHousesOptions.push(response.data)
        });
      }
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const id = row.id || this.ids
      getSocial_organization(id).then(response => {
        this.getCommunityChildDictory2(response.data.communityId)
        this.getHouseByID(response.data.communityHousesId)
        this.form = response.data;
        this.open = true;
        this.title = "修改社会组织信息";
      });
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSocial_organization(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addSocial_organization(this.form).then(response => {
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
      this.$confirm('是否确认删除社会组织信息编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delSocial_organization(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有社会组织信息数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportSocial_organization(queryParams);
      }).then(response => {
        this.download(response.msg);
      }).catch(function () { });
    }
  }
};
</script>
