<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch"
             label-width="68px">
      <!-- <el-form-item label="社区ID"
                    v-if="!showAllHouse"
                    prop="communityId">
        <el-input v-model="queryParams.communityId"
                  placeholder="请输入社区ID"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="小区ID"
                    v-if="!showAllHouse"
                    prop="communityChildId">
        <el-input v-model="queryParams.communityChildId"
                  placeholder="请输入小区ID"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="社区楼栋ID"
                    v-if="!showAllHouse"
                    prop="communityBuildingId">
        <el-input v-model="queryParams.communityBuildingId"
                  placeholder="请输入社区楼栋ID"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->

      <el-form-item label="社区名称"
                    v-if="showAllHouse"
                    prop="communityName">
        <el-input v-model="queryParams.communityName"
                  placeholder="请输入社区名称"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="小区名称"
                    v-if="showAllHouse"
                    prop="communityChildName">
        <el-input v-model="queryParams.communityChildName"
                  placeholder="请输入小区名称"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="小区名称"
                        prop="name">
            <el-input v-model="queryParams.name"
                      placeholder="请输入小区名称"
                      clearable
                      size="small"
                      @keyup.enter.native="handleQuery" />
          </el-form-item> -->

      <!-- <el-form-item label="楼栋编号"
                    prop="serialNumber">
        <el-input v-model="queryParams.serialNumber"
                  placeholder="请输入楼栋编号"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="楼栋名称"
                    prop="buildingName">
        <el-input v-model="queryParams.buildingName"
                  placeholder="请输入楼栋名称"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="单元"
                    prop="unit">
        <el-input v-model="queryParams.unit"
                  placeholder="请输入单元"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="楼层"
                    prop="floor">
        <el-input v-model="queryParams.floor"
                  placeholder="请输入楼层"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="门牌号"
                    prop="houseNumber">
        <el-input v-model="queryParams.houseNumber"
                  placeholder="请输入门牌号"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <el-form-item label="电话"
                    prop="phone">
        <el-input v-model="queryParams.phone"
                  placeholder="请输入电话"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="使用性质"
                    prop="natureOfUse">
        <el-select v-model="queryParams.natureOfUse"
                   placeholder="请选择使用性质"
                   clearable
                   size="small">
          <el-option v-for="dict in usePropertyOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="厅室"
                    prop="houseStructure">
        <el-select v-model="queryParams.houseStructure"
                   placeholder="请选择厅室"
                   clearable
                   size="small">
          <el-option v-for="dict in hallRoomOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="面积"
                    prop="area">
        <el-input v-model="queryParams.area"
                  placeholder="请输入面积(平方米)"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="建设时间"
                    prop="constructionTime">
        <el-input v-model="queryParams.constructionTime"
                  placeholder="请输入房屋建设时间"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="产权时间"
                    prop="housePropertyRight">
        <el-input v-model="queryParams.housePropertyRight"
                  placeholder="请输入产权时间"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="使用性质"
                    prop="natureOfUse">
        <el-select v-model="queryParams.natureOfUse"
                   placeholder="请选择使用性质"
                   clearable
                   size="small">
          <el-option v-for="dict in usePropertyOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="是否出租"
                    prop="rent">
        <el-select v-model="queryParams.rent"
                   placeholder="请选择是否出租"
                   clearable
                   size="small">
          <el-option v-for="dict in baseYesNoOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="出租用途"
                    prop="rentalPurpose">
        <el-select v-model="queryParams.rentalPurpose"
                   placeholder="请选择出租用途"
                   clearable
                   size="small">
          <el-option v-for="dict in rentalUseOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="隐患类型"
                    prop="hazardType">
        <el-select v-model="queryParams.hazardType"
                   placeholder="请选择隐患类型"
                   clearable
                   size="small">
          <el-option v-for="dict in hiddenDangerOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="电梯"
                    prop="elevator">
        <el-select v-model="queryParams.elevator"
                   placeholder="请选择是否有电梯"
                   clearable
                   size="small">
          <el-option v-for="dict in baseYesNoOptions"
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
                   v-hasPermi="['communityUnit:community_houses:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityUnit:community_houses:remove']">删除</el-button>
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAddList"
                   v-hasPermi="['communityUnit:community_houses:addList']">批量新增</el-button>
        <!-- <el-button icon="el-icon-refresh"
                   v-if="showAllHouse"
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
                   v-hasPermi="['communityUnit:community_houses:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['communityUnit:community_houses:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityUnit:community_houses:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['communityUnit:community_houses:export']">导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAddList"
                   v-hasPermi="['communityUnit:community_houses:addList']">批量新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading"
              stripe
              :data="community_housesList"
              :height="dataHeight"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="社区名称"
                       v-if="showAllHouse"
                       align="center"
                       prop="communityId"
                       :formatter="communityNameFormat" />
      <el-table-column label="小区名称"
                       v-if="showAllHouse"
                       align="center"
                       prop="communityChildId"
                       :formatter="communityChildNameFormat" />
      <!-- <el-table-column label="楼栋编号"
                       align="center"
                       prop="serialNumber" /> -->
      <el-table-column label="楼栋名称"
                       align="center"
                       prop="buildingName" />
      <el-table-column label="具体位置"
                       width="120"
                       align="center"
                       :formatter="houseAddressFormat" />
      <!-- <el-table-column label="单元"
                       align="center"
                       prop="unit" />
      <el-table-column label="楼层"
                       align="center"
                       prop="floor" />
      <el-table-column label="门牌号"
                       align="center"
                       prop="houseNumber" /> -->
      <el-table-column label="电话"
                       align="center"
                       width="100"
                       prop="phone" />
      <el-table-column label="厅室"
                       align="center"
                       prop="houseStructure"
                       width="100"
                       :formatter="hallRoomFormat" />
      <el-table-column label="面积"
                       align="center"
                       width="100"
                       prop="area" />
      <el-table-column label="电梯"
                       width="60"
                       align="center"
                       prop="elevator"
                       :formatter="baseYesNoFormat" />
      <el-table-column label="建设时间"
                       align="center"
                       prop="constructionTime" />
      <el-table-column label="产权时间"
                       align="center"
                       prop="housePropertyRight" />
      <el-table-column label="使用性质"
                       align="center"
                       prop="natureOfUse"
                       :formatter="usePropertyFormat" />
      <el-table-column label="是否出租"
                       align="center"
                       prop="rent"
                       :formatter="baseYesNoFormat" />
      <el-table-column label="出租用途"
                       align="center"
                       prop="rentalPurpose"
                       :formatter="rentalUseFormat" />
      <el-table-column label="隐患类型"
                       align="center"
                       prop="hazardType"
                       :formatter="hiddenDangerFormat" />
      <!-- <el-table-column label="照片列表" align="center" prop="pics" /> -->
      <el-table-column label="操作"
                       width="160"
                       align="center"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-attract"
                     @click="relatedPerson(scope.row)"
                     v-hasPermi="['communityUnit:personnel_information:related']">关联人员</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['communityUnit:community_houses:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['communityUnit:community_houses:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改社区房屋信息对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="600px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="100px">
        <!-- 选择社区 -->
        <el-form-item label="社区名称"
                      prop="communityId">
          <el-select v-model="form.communityId"
                     :disabled="!showAllHouse"
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
                     :disabled="!showAllHouse"
                     @click="getCommunityDictory"
                     v-hasPermi="['communityUnit:community:query']">刷新</el-button>
        </el-form-item>
        <el-form-item label="小区名称"
                      prop="communityChildId">
          <el-select v-model="form.communityChildId"
                     :disabled="!showAllHouse"
                     filterable
                     clearable
                     @change="setcommunityBuildingDictory"
                     placeholder="请选择小区">
            <el-option v-for="dict in communityChildOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="小区楼栋"
                      prop="communityBuildingId">
          <el-select v-model="form.communityBuildingId"
                     :disabled="!showAllHouse"
                     filterable
                     placeholder="请选择楼栋">
            <el-option v-for="dict in communityBuildingOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <!-- 指定社区，不需要选择 -->
        <!-- <el-form-item v-if="!showAllHouse"
                      label="社区ID"
                      prop="communityId">
          <el-input v-model="form.communityId"
                    placeholder="请输入社区ID" />
        </el-form-item>
        <el-form-item v-if="!showAllHouse"
                      label="社区名称"
                      prop="communityName">
          <el-input v-model="form.communityName"
                    placeholder="请输入社区名称" />
        </el-form-item>
        <el-form-item v-if="!showAllHouse"
                      label="社区楼栋ID"
                      prop="communityBuildingId">
          <el-input v-model="form.communityBuildingId"
                    placeholder="请输入社区楼栋ID" />
        </el-form-item>
        <el-form-item v-if="!showAllHouse"
                      label="楼栋编号"
                      prop="serialNumber">
          <el-input v-model="form.serialNumber"
                    placeholder="请输入楼栋编号" />
        </el-form-item>
        <el-form-item v-if="!showAllHouse"
                      label="楼栋名称"
                      prop="buildingName">
          <el-input v-model="form.buildingName"
                    placeholder="请输入楼栋名称" />
        </el-form-item> -->

        <el-form-item label="单元"
                      prop="unit">
          <el-input v-model="form.unit"
                    placeholder="请输入单元" />
        </el-form-item>
        <el-form-item label="楼层"
                      prop="floor">
          <el-input v-model="form.floor"
                    placeholder="请输入楼层" />
        </el-form-item>
        <el-form-item label="门牌号"
                      prop="houseNumber">
          <el-input v-model="form.houseNumber"
                    placeholder="请输入门牌号" />
        </el-form-item>
        <el-form-item label="电话"
                      prop="phone">
          <el-input v-model="form.phone"
                    placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="厅室"
                      prop="houseStructure">
          <el-select v-model="form.houseStructure"
                     placeholder="请选择厅室">
            <el-option v-for="dict in hallRoomOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="建筑面积"
                      prop="buildingArea">
          <el-input v-model="form.buildingArea"
                    placeholder="请输入面积(平方米)" />
        </el-form-item>
        <el-form-item label="套内面积"
                      prop="area">
          <el-input v-model="form.area"
                    placeholder="请输入面积(平方米)" />
        </el-form-item>
        <el-form-item label="是否有电梯">
          <el-radio-group v-model="form.elevator">
            <el-radio v-for="dict in baseYesNoOptions"
                      :key="dict.dictValue"
                      :label="dict.dictValue">{{ dict.dictLabel }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="房屋建设时间"
                      prop="constructionTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.constructionTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择房屋建设时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="产权时间"
                      prop="housePropertyRight">
          <el-input v-model="form.housePropertyRight"
                    placeholder="请输入产权时间" />
        </el-form-item>
        <el-form-item label="使用性质"
                      prop="natureOfUse">
          <el-select v-model="form.natureOfUse"
                     placeholder="请选择使用性质">
            <el-option v-for="dict in usePropertyOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="是否出租"
                      prop="rent">
          <el-radio-group v-model="form.rent"
                          @change="setRentalPurposeHave()">
            <el-radio v-for="dict in baseYesNoOptions"
                      :key="dict.dictValue"
                      :label="dict.dictValue">{{ dict.dictLabel }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="出租用途"
                      prop="rentalPurpose"
                      v-if="rentalPurposeShow">
          <el-select v-model="form.rentalPurpose"
                     placeholder="请选择出租用途">
            <el-option v-for="dict in rentalUseOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"
                       clearable />
          </el-select>
        </el-form-item>
        <el-form-item label="隐患类型"
                      prop="hazardType">
          <el-select v-model="form.hazardType"
                     placeholder="请选择隐患类型">
            <el-option v-for="dict in hiddenDangerOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="经度"
                      prop="longitud">
          <el-input v-model="form.longitud"
                    placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度"
                      prop="latitude">
          <el-input v-model="form.latitude"
                    placeholder="请输入纬度" />
        </el-form-item>
        <el-form-item label="照片列表"
                      prop="pics">
          <!-- <el-input
            v-model="form.pics"
            type="textarea"
            placeholder="请输入内容"
          /> -->
          <imageListUpload :upLoadUrl="fileUpLoadUrl"
                           :key="form.id"
                           :uploadState='uploadState'
                           :filesJson.sync="form.pics" />
        </el-form-item>
        <el-form-item label="平面图"
                      prop="planarGraph">
          <fileListUpload :upLoadUrl="fileUpLoadUrl"
                          :key="form.id"
                          :uploadState='uploadState'
                          :filesJson.sync="form.planarGraph" />
        </el-form-item>
        <el-form-item label="方位图"
                      prop="azimuthGraph">
          <fileListUpload :upLoadUrl="fileUpLoadUrl"
                          :key="form.createTime"
                          :uploadState='uploadState'
                          :filesJson.sync="form.azimuthGraph" />
        </el-form-item>

        <el-form-item label="备注"
                      prop="remark">
          <el-input v-model="form.remark"
                    type="textarea"
                    placeholder="请输入内容" />
        </el-form-item>

        <!-- 关联人员 人员房屋 -->
        <!-- <el-button
          size="mini"
          type="text"
          icon="el-icon-edit"
          @click="handleRelationClick"
          v-hasPermi="['passManage:visitPerson:edit']"
          >关联人员</el-button
        > -->
        <el-form-item label="选择起止时间"
                      v-if="relatedShow"
                      prop="relateTime">
          <el-date-picker clearable
                          value-format="yyyy-MM-dd"
                          v-model="relatedForm.relateTime"
                          type="daterange"
                          range-separator="至"
                          start-placeholder="开始日期"
                          end-placeholder="结束日期"
                          @change="setRelateTime()">
          </el-date-picker>
        </el-form-item>
        <!-- <span>关联到人员</span> -->
        <el-form-item label="关联人员"
                      v-if="relatedShow"
                      prop="personnelId">
          <el-select v-model="relatedForm.personnelId"
                     filterable
                     :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                     remote
                     :remote-method="remotePersonInfoMethod"
                     :loading="loading_remotePersonInfo"
                     placeholder="请选择人员姓名">
            <el-option v-for="dict in personOptions"
                       :key="dict.id"
                       :label="
                dict.name +
                ' ' +
                dict.phoneNumber +
                ' ' +
                dict.serialNumber +
                '号楼' +
                dict.unit +
                '单元' +
                dict.houseNumber
              "
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="开始时间" v-if="relatedShow" prop="startTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"  
            v-model="relatedForm.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择开始时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="终止时间" v-if="relatedShow" prop="endTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="relatedForm.endTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择终止时间"
          >
          </el-date-picker>
        </el-form-item> -->

        <el-form-item label="居住类型"
                      v-if="relatedShow"
                      prop="typeOfResidence">
          <el-select v-model="relatedForm.typeOfResidence"
                     placeholder="请选择居住类型">
            <el-option v-for="dict in typeOfResidenceOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否是房主"
                      v-if="relatedShow"
                      prop="homeowner">
          <el-select v-model="relatedForm.homeowner"
                     placeholder="请选择是否是房主">
            <el-option v-for="dict in homeownerOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="住户与房主关系"
                      v-if="relatedShow"
                      prop="relationshipWithHomeowner">
          <el-select v-model="relatedForm.relationshipWithHomeowner"
                     placeholder="请选择住户与房主关系">
            <el-option v-for="dict in relationshipWithHomeownerOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button type="primary"
                   @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog :title="title"
               :visible.sync="openList"
               width="600px"
               append-to-body>
      <addHouses ref="addHouses"
                 :showAllHouse="showAllHouse"
                 :building="buildingInfo"
                 :baseYesNoOptions="baseYesNoOptions"
                 :hiddenDangerOptions="hiddenDangerOptions"
                 :hallRoomOptions="hallRoomOptions"
                 :usePropertyOptions="usePropertyOptions"
                 :rentalUseOptions="rentalUseOptions"
                 :rules="rules" />
      <div slot="footer"
           class="dialog-footer">
        <el-button type="primary"
                   @click="submitFormList">确 定</el-button>
        <el-button @click="cancelList">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="关联人员信息"
               :visible.sync="relatedOpen"
               :before-close="beforeCloseRelatedOpen"
               width="65%"
               append-to-body>
      <RelatedPerson :houseInfo="houseInfo" />
    </el-dialog>
  </div>
</template>

<script>
import {
  listCommunity_houses,
  getCommunity_houses,
  delCommunity_houses,
  addCommunity_houses,
  updateCommunity_houses,
  exportCommunity_houses,
} from "@/api/communityUnit/community_houses";
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_building } from "@/api/communityUnit/community_building";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import { getInfoByName } from "@/api/communityUnit/personnel_information";
import addHouses from "@/views/communityUnit/community_houses/addHouses";
import RelatedPerson from "@/views/communityUnit/community_houses/relatedPerson";
import fileListUpload from "@/components/upload/uploadFileList";
import configInfo from "@/webconfig/configInfo";
import imageListUpload from "@/components/upload/uploadImageList";
export default {
  name: "Community_houses",
  components: { addHouses, RelatedPerson, fileListUpload, imageListUpload },
  props: {
    communityBuildingInfo: {
      type: Object,
      default: null,
    },
  },
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
      // 社区房屋信息表格数据
      community_housesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 批量添加弹出层
      openList: false,
      // 是否有电梯(0否1是,默认0)字典
      baseYesNoOptions: [],
      // 隐患类型字典
      hiddenDangerOptions: [],
      //厅室
      hallRoomOptions: [],
      //使用性质
      usePropertyOptions: [],
      //出租用途
      rentalUseOptions: [],
      //社区列表
      communityOptions: [],
      //小区列表
      communityChildOptions: [],
      //小区列表全集
      allCommunityChildOptions: [],
      //社区楼栋列表
      communityBuildingOptions: [],
      //删除提示语
      deleteNotces: [],
      //关联时间
      relateTime: "",
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        remark: null,
        communityId: null,
        communityName: null,
        communityChildId: null,
        communityChildName: null,
        communityBuildingId: null,
        serialNumber: null,
        buildingName: null,
        unit: null,
        floor: null,
        houseNumber: null,
        phone: null,
        houseStructure: null,
        area: null,
        constructionTime: null,
        housePropertyRight: null,
        natureOfUse: null,
        rent: null,
        rentalPurpose: null,
        hazardType: null,
        elevator: null,
        delFlag: "0",
      },
      //最大单元号
      unitMax: 1,
      //最大楼层号
      floorMax: 1,
      //是否有电梯
      elevator: null,
      // 批量添加房屋时的建筑信息
      buildingInfo: {},
      // 表单参数
      form: {},
      //出租类型是否必填
      rentalPurposeShow: false,
      // 表单校验
      rules: {
        communityId: [
          { required: true, message: "社区不能为空", trigger: "blur" },
        ],
        communityChildId: [
          { required: true, message: "小区不能为空", trigger: "blur" },
        ],
        communityBuildingId: [
          { required: true, message: "小区楼栋不能为空", trigger: "blur" },
        ],
        unit: [{ required: true, message: "单元不能为空", trigger: "blur" }],
        floor: [{ required: true, message: "楼层不能为空", trigger: "blur" }],
        houseNumber: [
          { required: true, message: "门牌号不能为空", trigger: "blur" },
        ],
        phone: [
          { required: true, message: "电话不能为空", trigger: "blur" },
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的电话",
            trigger: "blur",
          },
        ],
        houseStructure: [
          { required: true, message: "厅室不能为空", trigger: "blur" },
        ],
        buildingArea: [
          { required: true, message: "建筑面积不能为空", trigger: "blur" },
        ],
        area: [
          { required: true, message: "套内面积不能为空", trigger: "blur" },
        ],
        natureOfUse: [
          { required: true, message: "使用性质不能为空", trigger: "blur" },
        ],
        rent: [
          { required: true, message: "是否出租不能为空", trigger: "blur" },
        ],
        rentalPurpose: [
          { required: true, message: "出租用途不能为空", trigger: "blur" },
        ],
        hazardType: [
          { required: true, message: "隐患类型不能为空", trigger: "change" },
        ],
      },
      //文件上传地址
      fileUpLoadUrl: null,
      //关联信息表单
      relatedForm: {},
      //是否显示关联信息
      relatedShow: false,
      //远程人人员搜索
      loading_remotePersonInfo: false,
      //远程搜索人员列表
      personOptions: [],
      // 居住类型字典
      typeOfResidenceOptions: [],
      // 是否是房主,(0否1是,默认0)字典
      homeownerOptions: [],
      // 住户与房主关系字典
      relationshipWithHomeownerOptions: [],
      // 关联人员弹窗
      relatedOpen: false,
      // 关联前房屋信息
      houseInfo: {},
      // 搜索表单控件高度
      formHeight: 0,
      // 上传图片状态
      uploadState: true
    };
  },
  created () {
    this.getAllCommunityChildDictory();
    this.getCommunityDictory();
    this.getDicts("base_yes_no").then((response) => {
      this.baseYesNoOptions = response.data;
    });
    this.getDicts("base_hidden_danger").then((response) => {
      this.hiddenDangerOptions = response.data;
    });
    this.getDicts("base_use_property").then((response) => {
      this.usePropertyOptions = response.data;
    });
    this.getDicts("base_rental_use").then((response) => {
      this.rentalUseOptions = response.data;
    });
    this.getDicts("base_hall_room").then((response) => {
      this.hallRoomOptions = response.data;
    });
    this.getDicts("base_type_of_residence").then((response) => {
      this.typeOfResidenceOptions = response.data;
    });
    this.getDicts("base_yes_no").then((response) => {
      this.homeownerOptions = response.data;
    });
    this.getDicts("base_relationship_resident_homeowner").then((response) => {
      this.relationshipWithHomeownerOptions = response.data;
    });
  },
  mounted () {
    this.getList();
    this.$Notice.$on(
      "personhouserelatedvisible",
      () => (this.relatedOpen = false)
    );
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight;
    });
    this.fileUpLoadUrl = configInfo.FileUpLoadUri;
  },
  methods: {
    // 使用类型字典翻译
    usePropertyFormat (row, column, cellValue) {
      return this.selectDictLabel(this.usePropertyOptions, cellValue);
    },
    //出租用途字典翻译
    rentalUseFormat (row, column, cellValue) {
      return this.selectDictLabel(this.rentalUseOptions, cellValue);
    },
    // 厅室字典翻译
    hallRoomFormat (row, column, cellValue) {
      return this.selectDictLabel(this.hallRoomOptions, cellValue);
    },
    // 是否有电梯(0否1是,默认0)字典翻译
    baseYesNoFormat (row, column, cellValue) {
      return this.selectDictLabel(this.baseYesNoOptions, cellValue);
    },
    // 隐患类型
    hiddenDangerFormat (row, column, cellValue) {
      return this.selectDictLabel(this.hiddenDangerOptions, cellValue);
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
    setRentalPurposeHave () {
      if (this.form.rent == 1) {
        this.rentalPurposeShow = true;
      } else {
        this.rentalPurposeShow = false;
      }
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
    houseAddressFormat (row) {
      return row.unit + "单元" + row.floor + "层" + row.houseNumber;
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
        // console.log("child", this.communityChildOptions);
      });
    },
    setcommunityBuildingDictory (communityChildId) {
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
    getAllCommunityChildDictory (communityId) {
      this.allCommunityChildOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: null,
      };
      listCommunity_child(param).then((response) => {
        this.allCommunityChildOptions = response.rows;
      });
    },
    /** 查询社区房屋信息列表 */
    getList () {
      this.loading = true;
      listCommunity_houses(this.queryParams).then((response) => {
        this.community_housesList = response.rows;
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
        name: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: "0",
        remark: null,
        communityId: null,
        communityName: null,
        communityChildId: null,
        communityChildName: null,
        communityBuildingId: null,
        serialNumber: null,
        buildingName: null,
        unit: null,
        floor: null,
        houseNumber: null,
        phone: null,
        houseStructure: null,
        area: null,
        constructionTime: null,
        housePropertyRight: null,
        natureOfUse: null,
        rent: "0",
        rentalPurpose: null,
        hazardType: null,
        longitud: null,
        latitude: null,
        pics: null,
        elevator: "1",
      };
      this.resetForm("form");
      if (
        this.queryParams &&
        this.queryParams.communityId &&
        this.queryParams.communityId.length > 0
      ) {
        this.form.communityId = this.queryParams.communityId;
      }
      if (
        this.queryParams &&
        this.queryParams.communityChildId &&
        this.queryParams.communityChildId.length > 0
      ) {
        this.form.communityChildId = this.queryParams.communityChildId;
      }
      if (
        this.queryParams &&
        this.queryParams.communityBuildingId &&
        this.queryParams.communityBuildingId.length > 0
      ) {
        this.form.communityBuildingId = this.queryParams.communityBuildingId;
      }
      if (
        this.queryParams &&
        this.queryParams.serialNumber &&
        this.queryParams.serialNumber.length > 0
      ) {
        this.form.serialNumber = this.queryParams.serialNumber;
      }
      if (
        this.queryParams &&
        this.queryParams.buildingName &&
        this.queryParams.buildingName.length > 0
      ) {
        this.form.buildingName = this.queryParams.buildingName;
      }
      if (
        this.queryParams &&
        this.queryParams.communityName &&
        this.queryParams.communityName.length > 0
      ) {
        this.form.communityName = this.queryParams.communityName;
      }
      if (
        this.queryParams &&
        this.queryParams.elevator &&
        this.queryParams.elevator.length > 0
      ) {
        this.form.elevator = this.queryParams.elevator;
      }
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
      this.deleteNotces = selection.map(
        (item) =>
          item.serialNumber + "号楼" + item.unit + "单元" + item.houseNumber
      );
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.uploadState = true
      this.reset();
      if (!this.showAllHouse) {
        //不是展示所有房屋，而是指定了社区、小区、楼栋
        this.getCommunityChildDictory(this.form.communityId);
        this.setcommunityBuildingDictory(this.form.communityChildId);
      }
      this.open = true;
      this.title = "添加社区房屋信息";
      this.uploadState = true
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.uploadState = false
      this.reset();
      const id = row.id || this.ids;
      getCommunity_houses(id).then((response) => {
        this.form = response.data;
        console.log("this.form", this.form);
        this.setcommunityBuildingDictory(this.form.communityChildId);
        this.open = true;
        this.title = "修改社区房屋信息";
        this.getCommunityChildDictory(this.form.communityId);
      });
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateCommunity_houses(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addCommunity_houses(this.form).then((response) => {
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
      let deleteNotce = "";
      if (row.id) {
        deleteNotce =
          row.serialNumber + "号楼" + row.unit + "单元" + row.houseNumber;
      } else {
        deleteNotce = this.deleteNotces;
      }
      this.$confirm('是否确认删除"' + deleteNotce + '"房屋数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return delCommunity_houses(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(function () { });
    },
    //设置关联其实时间
    setRelateTime () {
      the.relatedForm.startTime = this.relatedForm.relateTime[0];
      the.relatedForm.endTime = this.relatedForm.relateTime[1];
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有社区房屋信息数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportCommunity_houses(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () { });
    },
    /** 修改关联信息是否展示 */
    handleRelationClick () {
      this.relatedShow = !this.relatedShow;
    },
    // 关联房屋
    relatedPerson (houseInfo) {
      // console.log('houseinfo:', houseInfo)
      this.houseInfo = houseInfo;
      this.relatedOpen = true;
    },
    remotePersonInfoMethod (name) {
      this.loading_remotePersonInfo = true;
      let param = {
        name: name,
        communityId: null,
        communityChildId: null,
      };
      if (this.form.communityId) {
        param.communityId = this.form.communityId;
      }
      if (this.form.communityChildId) {
        param.communityChildId = this.form.communityChildId;
      }
      // if (this.form.communityChildId) {
      //   param.communityChildId = this.form.communityChildId
      // }
      getInfoByName(param).then((response) => {
        this.personOptions = response.rows;
        this.loading_remotePersonInfo = false;
      });
    },
    /** 新增按钮操作 */
    handleAddList () {
      let param = {
        communityId: null,
        communityName: null,
        communityChildId: null,
        communityChildName: null,
        communityBuildingId: null,
        communityBuildingName: null,
        floor: null,
        unit: null,
      };
      if (
        this.queryParams &&
        this.queryParams.communityId &&
        this.queryParams.communityId.length > 0
      ) {
        param.communityId = this.queryParams.communityId;
      }
      if (
        this.queryParams &&
        this.queryParams.communityName &&
        this.queryParams.communityName.length > 0
      ) {
        param.communityName = this.queryParams.communityName;
      }
      if (
        this.queryParams &&
        this.queryParams.communityChildId &&
        this.queryParams.communityChildId.length > 0
      ) {
        param.communityChildId = this.queryParams.communityChildId;
      }
      if (
        this.queryParams &&
        this.queryParams.communityChildName &&
        this.queryParams.communityChildName.length > 0
      ) {
        param.communityChildName = this.queryParams.communityChildName;
      }
      if (
        this.queryParams &&
        this.queryParams.communityBuildingId &&
        this.queryParams.communityBuildingId.length > 0
      ) {
        param.communityBuildingId = this.queryParams.communityBuildingId;
      }
      if (
        this.queryParams &&
        this.queryParams.communityBuildingName &&
        this.queryParams.communityBuildingName.length > 0
      ) {
        param.communityBuildingName = this.queryParams.communityBuildingName;
      }
      if (this.floorMax && this.floorMax > 0) {
        param.floor = this.floorMax;
      }
      if (this.unitMax && this.unitMax > 0) {
        param.unit = this.unitMax;
      }
      if (this.elevator) {
        param.elevator = this.elevator;
      }
      this.buildingInfo = param;
      this.openList = true;
      this.title = "添加社区房屋信息";
    },
    submitFormList () {
      let houseList = this.$refs.addHouses.getHouseList();
      let successNum = 0;
      let errorNum = 0;
      let self = this;
      this.$confirm(
        '是否确认添加这"' + houseList.length + '"条房屋信息?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      ).then(async function () {
        // let waitList = []
        houseList.forEach((element) => {
          // waitList.push(
          addCommunity_houses(element).then((response) => {
            if (response.code === 200) {
              self.msgSuccess("新增成功");
              successNum++;
            } else {
              errorNum++;
            }
          });
          // )
        });
      });
    },
    cancelList () {
      this.openList = false;
    },
    beforeCloseRelatedOpen (done) {
      this.$Notice.$emit("addFormItemVisible");
      done();
    },
  },
  computed: {
    // 计算属性的 getter
    showAllHouse: function () {
      // `this` 指向 vm 实例
      if (
        this.queryParams &&
        this.queryParams.communityId &&
        this.queryParams.communityId.length > 0
      )
        return false;
      else {
        if (!(this.communityOptions && this.communityOptions.length > 0)) {
          this.getCommunityDictory();
        }
        return true;
      }
    },
    dataHeight () {
      let searchFormHeight = 0;
      if (this.showSearch) searchFormHeight = this.formHeight;
      if (!this.showAllHouse) searchFormHeight += 200;
      return this.$store.getters.dataEleHeight - searchFormHeight - 115;
    },
  },
  watch: {
    //作为弹窗使用时会传入上级建筑楼栋信息
    communityBuildingInfo: {
      handler: function (info) {
        if (info && info.id && info.id.length > 0) {
          //有效信息
          this.queryParams.communityId = info.communityId;
          this.queryParams.communityName = info.communityName;
          this.queryParams.communityChildId = info.communityChildId;
          this.queryParams.communityChildName = info.communityChildName;
          this.queryParams.communityBuildingId = info.id;
          this.queryParams.serialNumber = info.serialNumber;
          this.queryParams.buildingName = info.name;
          this.floorMax = info.floorNumber;
          this.unitMax = info.unitNumber;
          this.elevator = info.elevator;
          // this.queryParams.elevator = info.elevator//电梯
          // this.queryParams.floor = info.floorNumber//楼层
          // this.queryParams.unit = info.unitNumber//单元
          this.handleQuery();
        }
      },
      deep: true,
      immediate: true, //路由发生变化时界面还未重绘完成，刷新布局没有意义，当前已经在重绘时处理
    },
  },
};
</script>
