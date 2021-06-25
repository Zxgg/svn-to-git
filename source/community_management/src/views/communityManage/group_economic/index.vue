<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
    >
      <el-form-item label="社区名称" prop="communityId">
        <el-select
          v-model="queryParams.communityId"
          filterable
          clearable
          @change="getCommunityChildDictory"
          placeholder="请选择社区"
        >
          <el-option
            v-for="dict in communityOptions"
            :key="dict.id"
            :label="dict.name"
            :value="dict.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="小区名称" prop="communityChildId">
        <el-select
          v-model="queryParams.communityChildId"
          filterable
          clearable
          placeholder="请选择小区"
        >
          <el-option
            v-for="dict in communityChildOptions"
            :key="dict.id"
            :label="dict.name"
            :value="dict.id"
          />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="企业类别"
                    prop="type">
        <el-select v-model="queryParams.type"
                   placeholder="请选择企业类别"
                   clearable
                   size="small">
          <el-option v-for="dict in typeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <el-form-item label="企业名称" prop="businessName">
        <el-input
          v-model="queryParams.businessName"
          placeholder="请输入企业名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="企业地址"
                    prop="address">
        <el-input v-model="queryParams.address"
                  placeholder="请输入企业地址"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="联系方式"
                    prop="tel">
        <el-input v-model="queryParams.tel"
                  placeholder="请输入企业联系方式"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <!-- <el-form-item label="员工数"
                    prop="count">
        <el-input v-model="queryParams.count"
                  placeholder="请输入企业员工数"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <el-form-item label="工商执照号码" prop="socialCode">
        <el-input
          v-model="queryParams.socialCode"
          placeholder="请输入工商执照号码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="组织机构代码"
                    prop="orgCode">
        <el-input v-model="queryParams.orgCode"
                  placeholder="请输入组织机构代码"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <!-- <el-form-item label="法人证件类型"
                    prop="legalType">
        <el-select v-model="queryParams.legalType"
                   placeholder="请选择法人证件类型"
                   clearable
                   size="small">
          <el-option v-for="dict in legalTypeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <!-- <el-form-item label="法人证件号码"
                    prop="legalIdcard">
        <el-input v-model="queryParams.legalIdcard"
                  placeholder="请输入法人证件号码"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <!-- <el-form-item label="法人姓名"
                    prop="legalName">
        <el-input v-model="queryParams.legalName"
                  placeholder="请输入法人姓名"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <!-- <el-form-item label="法人联系方式"
                    prop="legalTel">
        <el-input v-model="queryParams.legalTel"
                  placeholder="请输入法人联系方式"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <!-- <el-form-item label="治保负责人"
                    prop="chargePersonName">
        <el-input v-model="queryParams.chargePersonName"
                  placeholder="请输入治保负责人姓名"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <!-- <el-form-item label="治保负责人联系方式"
                    prop="chargePersonTel">
        <el-input v-model="queryParams.chargePersonTel"
                  placeholder="请输入治保负责人联系方式"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <!-- <el-form-item label="是否危化企业"
                    prop="isDanger">
        <el-select v-model="queryParams.isDanger"
                   placeholder="请选择是否危化企业"
                   clearable
                   size="small">
          <el-option v-for="dict in isDangerOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="安全隐患类型"
                    prop="dangerType">
        <el-select v-model="queryParams.dangerType"
                   placeholder="请选择安全隐患类型"
                   clearable
                   size="small">
          <el-option v-for="dict in dangerTypeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="关注程度"
                    prop="isFocus">
        <el-select v-model="queryParams.isFocus"
                   placeholder="请选择关注程度"
                   clearable
                   size="small">
          <el-option v-for="dict in isFocusOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <!-- <el-form-item label="是否具备建立党组织条件"
                    prop="isPartyConditions">
        <el-select v-model="queryParams.isPartyConditions"
                   placeholder="请选择是否具备建立党组织条件"
                   clearable
                   size="small">
          <el-option v-for="dict in isPartyConditionsOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="是否有党组织"
                    prop="isParty">
        <el-select v-model="queryParams.isParty"
                   placeholder="请选择是否有党组织"
                   clearable
                   size="small">
          <el-option v-for="dict in isPartyOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="党员数量"
                    prop="partyMemberCount">
        <el-input v-model="queryParams.partyMemberCount"
                  placeholder="请输入党员数量"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="是否有工会"
                    prop="isUnion">
        <el-select v-model="queryParams.isUnion"
                   placeholder="请选择是否有工会"
                   clearable
                   size="small">
          <el-option v-for="dict in isUnionOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="工会会员数量"
                    prop="unionMemberCount">
        <el-input v-model="queryParams.unionMemberCount"
                  placeholder="请输入工会会员数量"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="是否有共青团组织"
                    prop="isCommunist">
        <el-select v-model="queryParams.isCommunist"
                   placeholder="请选择是否有共青团组织"
                   clearable
                   size="small">
          <el-option v-for="dict in isCommunistOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="共青团团员数量"
                    prop="communistMemberCount">
        <el-input v-model="queryParams.communistMemberCount"
                  placeholder="请输入共青团团员数量"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="是否有妇联组织"
                    prop="isWomenFed">
        <el-select v-model="queryParams.isWomenFed"
                   placeholder="请选择是否有妇联组织"
                   clearable
                   size="small">
          <el-option v-for="dict in isWomenFedOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="妇联组织成员数量"
                    prop="womenFedCount">
        <el-input v-model="queryParams.womenFedCount"
                  placeholder="请输入妇联组织成员数量"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <el-form-item>
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['communityManage:group_economic:add']"
          >新增</el-button
        >
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['communityManage:group_economic:remove']"
          >删除</el-button
        >
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
                   v-hasPermi="['communityManage:group_economic:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['communityManage:group_economic:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityManage:group_economic:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['communityManage:group_economic:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table
      v-loading="loading"
      stripe
      :height="dataHeight"
      :data="group_economicList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="企业名称" align="center" prop="businessName" />
      <el-table-column
        label="企业类别"
        align="center"
        prop="type"
        :formatter="typeFormat"
      />
      <el-table-column label="企业地址" align="center" prop="address" />
      <el-table-column label="企业联系方式" align="center" prop="tel" />
      <!-- <el-table-column label="企业员工数"
                       align="center"
                       prop="count" /> -->
      <el-table-column label="工商执照号码" align="center" prop="socialCode" />
      <el-table-column label="组织机构代码" align="center" prop="orgCode" />
      <el-table-column label="法人姓名" align="center" prop="legalName" />
      <el-table-column label="法人联系方式" align="center" prop="legalTel" />
      <el-table-column
        label="治保负责人姓名"
        align="center"
        prop="chargePersonName"
      />
      <el-table-column
        label="治保负责人联系方式"
        align="center"
        prop="chargePersonTel"
      />
      <el-table-column
        label="是否危化企业"
        align="center"
        prop="isDanger"
        :formatter="isDangerFormat"
      />
      <el-table-column
        label="安全隐患类型"
        align="center"
        prop="dangerType"
        :formatter="dangerTypeFormat"
      />
      <el-table-column
        label="关注程度"
        align="center"
        prop="isFocus"
        :formatter="isFocusFormat"
      />
      <!-- <el-table-column label="创建人"
                       align="center"
                       prop="userId" />
      <el-table-column label="创建单位"
                       align="center"
                       prop="deptId" />
      <el-table-column label="创建日期"
                       align="center"
                       prop="createTime"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['communityManage:group_economic:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['communityManage:group_economic:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改非公经济组织信息对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="1200px"
      :inline="true"
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="220px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="社区名称" prop="communityId">
              <el-select
                v-model="form.communityId"
                filterable
                @change="getCommunityChildDictory2"
                placeholder="请选择社区"
              >
                <el-option
                  v-for="dict in communityOptions"
                  :key="dict.id"
                  :label="dict.name"
                  :value="dict.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="小区名称" prop="communityChildId">
              <el-select
                v-model="form.communityChildId"
                filterable
                placeholder="请选择小区"
              >
                <el-option
                  v-for="dict in communityChildOptions2"
                  :key="dict.id"
                  :label="dict.name"
                  :value="dict.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="房屋" prop="communityHousesId">
              <el-select
                v-model="form.communityHousesId"
                filterable
                clearable
                remote
                :remote-method="getHousesList"
                :loading="loading_remoteHouseInfo"
                placeholder="请选择房屋"
              >
                <el-option
                  v-for="dict in communityHousesOptions"
                  :key="dict.id"
                  :label="
                    dict.serialNumber +
                      '号楼' +
                      dict.unit +
                      '单元' +
                      dict.floor +
                      '层' +
                      dict.name
                  "
                  :value="dict.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="企业名称" prop="businessName">
              <el-input
                v-model="form.businessName"
                placeholder="请输入企业名称"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="企业类别" prop="type">
              <el-select v-model="form.type" placeholder="请选择企业类别">
                <el-option
                  v-for="dict in typeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="企业地址" prop="address">
              <el-input v-model="form.address" placeholder="请输入企业地址" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="企业联系方式" prop="tel">
              <el-input v-model="form.tel" placeholder="请输入企业联系方式" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="企业员工数" prop="count">
              <el-input v-model="form.count" placeholder="请输入企业员工数" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item
              label="工商执照号码(统一社会信用代码)"
              prop="socialCode"
            >
              <el-input
                v-model="form.socialCode"
                placeholder="请输入工商执照号码(统一社会信用代码)"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="组织机构代码" prop="orgCode">
              <el-input
                v-model="form.orgCode"
                placeholder="请输入组织机构代码"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="法人证件类型" prop="legalType">
              <el-select
                v-model="form.legalType"
                placeholder="请选择法人证件类型"
              >
                <el-option
                  v-for="dict in legalTypeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="法人证件号码" prop="legalIdcard">
              <el-input
                v-model="form.legalIdcard"
                placeholder="请输入法人证件号码"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="法人姓名" prop="legalName">
              <el-input v-model="form.legalName" placeholder="请输入法人姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="法人联系方式" prop="legalTel">
              <el-input
                v-model="form.legalTel"
                placeholder="请输入法人联系方式"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="治保负责人姓名" prop="chargePersonName">
              <el-input
                v-model="form.chargePersonName"
                placeholder="请输入治保负责人姓名"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="治保负责人联系方式" prop="chargePersonTel">
              <el-input
                v-model="form.chargePersonTel"
                placeholder="请输入治保负责人联系方式"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否危化企业" prop="isDanger">
              <el-select
                v-model="form.isDanger"
                placeholder="请选择是否危化企业"
              >
                <el-option
                  v-for="dict in isDangerOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="安全隐患类型" prop="dangerType">
              <el-select
                v-model="form.dangerType"
                placeholder="请选择安全隐患类型"
              >
                <el-option
                  v-for="dict in dangerTypeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="关注程度" prop="isFocus">
              <el-select v-model="form.isFocus" placeholder="请选择关注程度">
                <el-option
                  v-for="dict in isFocusOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item
              label="是否具备建立党组织条件"
              prop="isPartyConditions"
            >
              <el-select
                v-model="form.isPartyConditions"
                placeholder="请选择是否具备建立党组织条件"
              >
                <el-option
                  v-for="dict in isPartyConditionsOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否有党组织" prop="isParty">
              <el-select
                v-model="form.isParty"
                placeholder="请选择是否有党组织"
              >
                <el-option
                  v-for="dict in isPartyOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="党员数量" prop="partyMemberCount">
              <el-input
                v-model="form.partyMemberCount"
                placeholder="请输入党员数量"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否有工会" prop="isUnion">
              <el-select v-model="form.isUnion" placeholder="请选择是否有工会">
                <el-option
                  v-for="dict in isUnionOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="工会会员数量" prop="unionMemberCount">
              <el-input
                v-model="form.unionMemberCount"
                placeholder="请输入工会会员数量"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否有共青团组织" prop="isCommunist">
              <el-select
                v-model="form.isCommunist"
                placeholder="请选择是否有共青团组织"
              >
                <el-option
                  v-for="dict in isCommunistOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="共青团团员数量" prop="communistMemberCount">
              <el-input
                v-model="form.communistMemberCount"
                placeholder="请输入共青团团员数量"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否有妇联组织" prop="isWomenFed">
              <el-select
                v-model="form.isWomenFed"
                placeholder="请选择是否有妇联组织"
              >
                <el-option
                  v-for="dict in isWomenFedOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="妇联组织成员数量" prop="womenFedCount">
              <el-input
                v-model="form.womenFedCount"
                placeholder="请输入妇联组织成员数量"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="经度" prop="longitudes">
              <el-input v-model="form.longitudes" placeholder="请输入经度" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="纬度" prop="latitudes">
              <el-input
                v-model="form.latitudes"
                placeholder="请输入纬度"
              /> </el-form-item
          ></el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listGroup_economic,
  getGroup_economic,
  delGroup_economic,
  addGroup_economic,
  updateGroup_economic,
  exportGroup_economic
} from "@/api/communityManage/group_economic";
import {
  listCommunity_houses,
  getCommunity_houses
} from "@/api/communityUnit/community_houses";
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_building } from "@/api/communityUnit/community_building";
import { listCommunity_child } from "@/api/communityUnit/community_child";
export default {
  name: "Group_economic",
  data() {
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
      // 非公经济组织信息表格数据
      group_economicList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 非公经济组织企业类别字典
      typeOptions: [],
      // 法人证件类型字典
      legalTypeOptions: [],
      // 是否危化企业字典
      isDangerOptions: [],
      // 安全隐患类型字典
      dangerTypeOptions: [],
      // 关注程度字典
      isFocusOptions: [],
      // 是否具备建立党组织条件字典
      isPartyConditionsOptions: [],
      // 是否有党组织字典
      isPartyOptions: [],
      // 是否有工会字典
      isUnionOptions: [],
      // 是否有共青团组织字典
      isCommunistOptions: [],
      // 是否有妇联组织字典
      isWomenFedOptions: [],
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
        businessName: null,
        type: null,
        address: null,
        tel: null,
        count: null,
        socialCode: null,
        orgCode: null,
        legalType: null,
        legalIdcard: null,
        legalName: null,
        legalTel: null,
        chargePersonName: null,
        chargePersonTel: null,
        isDanger: null,
        dangerType: null,
        isFocus: null,
        isPartyConditions: null,
        isParty: null,
        partyMemberCount: null,
        isUnion: null,
        unionMemberCount: null,
        isCommunist: null,
        communistMemberCount: null,
        isWomenFed: null,
        womenFedCount: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      // 搜索表单控件高度
      formHeight: 0
    };
  },
  created() {
    this.getCommunityDictory();
    this.getAllCommunityChildDictory();
    this.getList();
    this.getDicts("base_enterprise_type").then(response => {
      this.typeOptions = response.data;
    });
    this.getDicts("base_idCard").then(response => {
      this.legalTypeOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.isDangerOptions = response.data;
    });
    this.getDicts("base_safety_hazard_type").then(response => {
      this.dangerTypeOptions = response.data;
    });
    this.getDicts("base_attention_level").then(response => {
      this.isFocusOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.isPartyConditionsOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.isPartyOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.isUnionOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.isCommunistOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.isWomenFedOptions = response.data;
    });
  },
  mounted() {
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight;
    });
  },
  computed: {
    dataHeight() {
      let searchFormHeight = 0;
      if (this.showSearch) searchFormHeight = this.formHeight;
      return this.$store.getters.dataEleHeight - searchFormHeight - 115;
    }
  },
  methods: {
    communityNameFormat(cellValue) {
      if (this.communityOptions && this.communityOptions.length > 0) {
        let rows = this.communityOptions.filter(item => {
          return item.id === cellValue;
        });
        if (rows && rows.length > 0) {
          return rows[0].name + "  ";
        }
      }
      return "??";
    },
    communityChildNameFormat(cellValue) {
      if (
        this.allCommunityChildOptions &&
        this.allCommunityChildOptions.length > 0
      ) {
        let rows = this.allCommunityChildOptions.filter(item => {
          return item.id === cellValue;
        });
        if (rows && rows.length > 0) {
          return rows[0].name + "  ";
        }
      }
      return "";
    },
    houseAddressFormat(row) {
      return (
        row.buildingName +
        " " +
        row.unit +
        "单元" +
        row.floor +
        "层" +
        row.houseNumber
      );
    },
    getCommunityDictory() {
      this.communityOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000
      };
      listCommunity(param).then(response => {
        this.communityOptions = response.rows;
      });
    },
    getCommunityChildDictory(communityId) {
      this.communityChildOptions = [];
      this.queryParams.communityChildId = null;
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId
      };
      listCommunity_child(param).then(response => {
        this.communityChildOptions = response.rows;
      });
    },
    getCommunityChildDictory2(communityId) {
      this.communityChildOptions = [];
      this.form.communityChildId = null;
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId
      };
      listCommunity_child(param).then(response => {
        debugger;
        this.communityChildOptions2 = response.rows;
      });
    },
    getAllCommunityChildDictory(communityId) {
      this.allCommunityChildOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: null
      };
      listCommunity_child(param).then(response => {
        this.allCommunityChildOptions = response.rows;
      });
    },
    getHouseName(house) {
      if (house) {
        return (
          house.serialNumber + "号楼" + house.unit + "单元" + house.houseNumber
        );
      } else return "";
    },
    getHousesList(houseNumber) {
      this.communityHousesOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: null,
        communityChildId: null,
        houseNumber: houseNumber
      };
      if (this.form.communityId) {
        param.communityId = this.form.communityId;
      }
      if (this.form.communityChildId) {
        param.communityChildId = this.form.communityChildId;
      }
      this.loading_remoteHouseInfo = true;
      listCommunity_houses(param).then(response => {
        this.loading_remoteHouseInfo = false;
        this.communityHousesOptions = response.rows;
      });
    },
    /** 查询非公经济组织信息列表 */
    getList() {
      this.loading = true;
      listGroup_economic(this.queryParams).then(response => {
        this.group_economicList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 非公经济组织企业类别字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
    },
    // 法人证件类型字典翻译
    legalTypeFormat(row, column) {
      return this.selectDictLabel(this.legalTypeOptions, row.legalType);
    },
    // 是否危化企业字典翻译
    isDangerFormat(row, column) {
      return this.selectDictLabel(this.isDangerOptions, row.isDanger);
    },
    // 安全隐患类型字典翻译
    dangerTypeFormat(row, column) {
      return this.selectDictLabel(this.dangerTypeOptions, row.dangerType);
    },
    // 关注程度字典翻译
    isFocusFormat(row, column) {
      return this.selectDictLabel(this.isFocusOptions, row.isFocus);
    },
    // 是否具备建立党组织条件字典翻译
    isPartyConditionsFormat(row, column) {
      return this.selectDictLabel(
        this.isPartyConditionsOptions,
        row.isPartyConditions
      );
    },
    // 是否有党组织字典翻译
    isPartyFormat(row, column) {
      return this.selectDictLabel(this.isPartyOptions, row.isParty);
    },
    // 是否有工会字典翻译
    isUnionFormat(row, column) {
      return this.selectDictLabel(this.isUnionOptions, row.isUnion);
    },
    // 是否有共青团组织字典翻译
    isCommunistFormat(row, column) {
      return this.selectDictLabel(this.isCommunistOptions, row.isCommunist);
    },
    // 是否有妇联组织字典翻译
    isWomenFedFormat(row, column) {
      return this.selectDictLabel(this.isWomenFedOptions, row.isWomenFed);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        communityId: null,
        communityChildId: null,
        communityHousesId: null,
        businessName: null,
        type: null,
        address: null,
        tel: null,
        count: null,
        socialCode: null,
        orgCode: null,
        legalType: null,
        legalIdcard: null,
        legalName: null,
        legalTel: null,
        chargePersonName: null,
        chargePersonTel: null,
        isDanger: null,
        dangerType: null,
        isFocus: null,
        isPartyConditions: null,
        isParty: null,
        partyMemberCount: null,
        isUnion: null,
        unionMemberCount: null,
        isCommunist: null,
        communistMemberCount: null,
        isWomenFed: null,
        womenFedCount: null,
        longitudes: null,
        latitudes: null,
        userId: null,
        deptId: null,
        createTime: null,
        uUserId: null,
        uDeptId: null,
        updateTime: null,
        delFlag: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加非公经济组织信息";
    },
    getHouseByID(id) {
      this.communityHousesOptions = [];
      if (id && id.length > 0) {
        getCommunity_houses(id).then(response => {
          this.communityHousesOptions.push(response.data);
        });
      }
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getGroup_economic(id).then(response => {
        this.getCommunityChildDictory2(response.data.communityId);
        this.getHouseByID(response.data.communityHousesId);
        this.form = response.data;
        this.open = true;
        this.title = "修改非公经济组织信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateGroup_economic(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addGroup_economic(this.form).then(response => {
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
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm(
        '是否确认删除非公经济组织信息编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(function() {
          return delGroup_economic(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有非公经济组织信息数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return exportGroup_economic(queryParams);
        })
        .then(response => {
          this.download(response.msg);
        })
        .catch(function() {});
    }
  }
};
</script>
