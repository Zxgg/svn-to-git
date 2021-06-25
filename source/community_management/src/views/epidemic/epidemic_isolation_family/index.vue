<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch"
             label-width="88px">
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
      <!-- <el-form-item label="楼栋名称"
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
      <!-- <el-form-item label="开始隔离时间"
                    prop="isolatioStartTime">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="queryParams.isolatioStartTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择开始隔离时间">
        </el-date-picker>
      </el-form-item> -->
      <!-- <el-form-item label="隔离结束时间"
                    prop="isolationEndTime">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="queryParams.isolationEndTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择隔离结束时间">
        </el-date-picker>
      </el-form-item> -->
      <!-- <el-form-item label="隔离总天数"
                    prop="isolationDayNum">
        <el-input v-model="queryParams.isolationDayNum"
                  placeholder="请输入隔离总天数"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <el-form-item label="状态"
                    prop="stat">
        <el-select v-model="queryParams.stat"
                   placeholder="请选择状态"
                   clearable
                   size="small">
          <el-option v-for="dict in statOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="需要紧急外出"
                    label-width="auto"
                    prop="needOut">
        <el-select v-model="queryParams.needOut"
                   placeholder="请选择是否有可能需要紧急外出"
                   clearable
                   size="small">
          <el-option v-for="dict in needOutOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
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
                   v-hasPermi="['epidemic:epidemic_isolation_family:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['epidemic:epidemic_isolation_family:remove']">删除</el-button>
        <!-- <el-button icon="el-icon-refresh"
                   size="mini"
                   @click="resetQuery">重置</el-button> -->
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10"
            class="mb8">
      <el-col :span="1.5">

      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['epidemic:epidemic_isolation_family:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">

      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['epidemic:epidemic_isolation_family:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading"
              :height="dataHeight"
              :data="epidemic_isolation_familyList"
              @selection-change="handleSelectionChange"
              :cell-class-name="getCellClassName">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="社区名称"
                       align="center"
                       width="140"
                       prop="communityId"
                       :formatter="communityNameFormat" />
      <el-table-column label="小区名称"
                       align="center"
                       width="140"
                       prop="communityChildId"
                       :formatter="communityChildNameFormat" />
      <!-- <el-table-column label="社区楼栋ID" align="center" prop="communityBuildingId" /> -->
      <!-- <el-table-column label="楼栋编号" align="center" prop="serialNumber" /> -->
      <!-- <el-table-column label="楼栋名称" align="center" prop="buildingName" />
      <el-table-column label="单元" align="center" prop="unit" />
      <el-table-column label="楼层" align="center" prop="floor" />
      <el-table-column label="门牌号" align="center" prop="houseNumber" /> -->
      <el-table-column label="房屋地址"
                       align="center"
                       prop="houseNumber"
                       :formatter="houseFormat" />
      <el-table-column label="住户信息"
                       align="center"
                       prop="personinfo"
                       :formatter="personinfoFormat" />
      <!-- <el-table-column label="开始隔离时间"
                       align="center"
                       prop="isolatioStartTime"
                       width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.isolatioStartTime) }}</span>
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="隔离结束时间"
                       align="center"
                       prop="isolationEndTime"
                       width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.isolationEndTime) }}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="隔离天数"
                       align="center"
                       width="80"
                       prop="isolationDayNum" />
      <el-table-column label="还需隔离天数"
                       align="center"
                       prop="isolationEndTime"
                       width="120">
        <template slot-scope="scope">
          <el-tooltip class="item"
                      effect="dark"
                      :disabled="getDisableShowIsolationEndTooltip(scope.row.isolationEndTime)"
                      content="隔离期限已到，请更新隔离状态"
                      placement="top">
            <span>{{ getTraceDay(scope.row.isolationEndTime) }}</span>
          </el-tooltip>
        </template>
      </el-table-column>
      <!-- <el-table-column label="物资采购记录"
                       align="center"
                       prop="suppliesPurchaseRecord" /> -->
      <el-table-column label="最后一次采购"
                       align="center"
                       prop="lastSuppliesPurchase"
                       width="120">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastSuppliesPurchase, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态"
                       align="center"
                       width="80"
                       prop="stat"
                       :formatter="statFormat" />
      <el-table-column label="外出需求"
                       align="center"
                       width="100"
                       prop="needOut"
                       :formatter="needOutFormat" />
      <!-- <el-table-column label="核酸检测记录" align="center" prop="nucleicAcidTestingRecord" /> -->
      <el-table-column label="下次核酸检测时间"
                       align="center"
                       prop="nextNucleicAcidTestingData"
                       width="140">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.nextNucleicAcidTestingData, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="外出记录"
                       width="160"
                       align="center"
                       prop="outRecord"
                       :formatter="outRecordFormat" />
      <el-table-column label="隔离原因"
                       align="center"
                       width="160"
                       prop="isolationReason" />
      <el-table-column label="备注"
                       align="center"
                       prop="remark" />
      <el-table-column label="操作"
                       align="center"
                       width="100"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['epidemic:epidemic_isolation_family:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['epidemic:epidemic_isolation_family:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改隔离家庭 对话框 -->
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
                     @change="setcommunityBuildingDictory"
                     placeholder="请选择小区">
            <el-option v-for="dict in communityChildOptions2"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="社区楼栋"
                      prop="communityBuildingId">
          <el-select v-model="form.communityBuildingId"
                     filterable
                     @change="setcommunityBuildingInfo"
                     placeholder="请选择楼栋">
            <el-option v-for="dict in communityBuildingOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
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
        <el-form-item label="住户信息"
                      prop="personinfo">
          <el-button type="primary"
                     icon="el-icon-plus"
                     size="mini"
                     @click="handleAdd_personinfo">新增住户</el-button>
          <el-table stripe
                    :show-header="false"
                    :data="personinfo">
            <el-table-column label="简要信息"
                             fit
                             align="center"
                             class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-row class="textLeft HorizontalMargin">
                  <span>{{getShowPersonInfo(scope.row)}}</span>
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
                           @click="handleDelete_personinfo(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
        <el-form-item label=""
                      v-if="open_form_personInfo">
          <el-form ref="form_personinfo"
                   :model="form_personinfo"
                   :rules="rules_personinfo"
                   label-width="100px">
            <el-form-item label="姓名"
                          prop="name">
              <el-input v-model="form_personinfo.name"
                        placeholder="请输入姓名" />
            </el-form-item>
            <el-form-item label="性别"
                          prop="sex">
              <el-radio-group v-model="form_personinfo.sex">
                <el-radio v-for="dict in sexOptions"
                          :key="dict.dictLabel"
                          :label="dict.dictLabel">{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="身份证号码"
                          prop="identificationNumber">
              <el-input v-model="form_personinfo.identificationNumber"
                        placeholder="请输入身份证号码" />
            </el-form-item>
            <el-form-item label="手机号"
                          prop="phoneNumber">
              <el-input v-model="form_personinfo.phoneNumber"
                        placeholder="请输入手机号" />
            </el-form-item>
            <div class="dialog-footer">
              <el-button type="primary"
                         @click="submitForm_personinfo">确 定</el-button>
              <el-button @click="cancel_personinfo">取 消</el-button>
            </div>
          </el-form>
        </el-form-item>
        <el-form-item label="开始隔离时间"
                      prop="isolatioStartTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.isolatioStartTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择开始隔离时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="隔离结束时间"
                      prop="isolationEndTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.isolationEndTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          @change="isolationEndTimeChange"
                          placeholder="选择隔离结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="隔离总天数"
                      prop="isolationDayNum">
          <el-input v-model="form.isolationDayNum"
                    @change="isolationDayNumChange"
                    placeholder="请输入隔离总天数" />
        </el-form-item>
        <el-form-item label="物资采购记录"
                      prop="suppliesPurchaseRecord">
          <el-button type="primary"
                     icon="el-icon-plus"
                     size="mini"
                     @click="handleAdd_suppliesPurchaseRecord">新增物资采购记录</el-button>
          <el-table stripe
                    :border="true"
                    :show-header="false"
                    :data="suppliesPurchaseRecord">
            <el-table-column label="简要信息"
                             fit
                             align="center"
                             class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-row class="textLeft HorizontalMargin">
                  <span>{{getShowSuppliesPurchaseRecord(scope.row)}}</span>
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
                           @click="handleDelete_suppliesPurchaseRecord(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>

        </el-form-item>
        <el-form-item label=""
                      v-if="open_form_suppliesPurchaseRecord">
          <el-form ref="form_suppliesPurchaseRecord"
                   :model="form_suppliesPurchaseRecord"
                   :rules="rules_suppliesPurchaseRecord"
                   label-width="120px">
            <el-form-item label="采购时间"
                          prop="time">
              <el-date-picker clearable
                              size="small"
                              style="width: 200px"
                              v-model="form_suppliesPurchaseRecord.time"
                              type="datetime"
                              value-format="yyyy-MM-dd HH:mm:ss"
                              placeholder="选择采购时间" />
            </el-form-item>
            <!-- <el-form-item label="照片"
                          prop="imagePath">
            </el-form-item> -->
            <div class="dialog-footer">
              <el-button type="primary"
                         @click="submitForm_suppliesPurchaseRecord">确 定</el-button>
              <el-button @click="cancel_suppliesPurchaseRecord">取 消</el-button>
            </div>
          </el-form>
        </el-form-item>
        <el-form-item label="最后一次采购的时间"
                      prop="lastSuppliesPurchase">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.lastSuppliesPurchase"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择最后一次采购的时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="状态"
                      prop="stat">
          <el-select v-model="form.stat"
                     placeholder="请选择状态">
            <el-option v-for="dict in statOptions"
                       :key="dict.dictLabel"
                       :label="dict.dictLabel"
                       :value="dict.dictLabel"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否有需要紧急外出人员"
                      prop="needOut">
          <el-select v-model="form.needOut"
                     placeholder="请选择是否有需要紧急外出人员,外出类型写备注里">
            <el-option v-for="dict in needOutOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="核酸检测记录"
                      prop="nucleicAcidTestingRecord">
          <el-button type="primary"
                     icon="el-icon-plus"
                     size="mini"
                     @click="handleAdd_nucleicAcidTestingRecord">新增核酸检测记录</el-button>
          <el-table stripe
                    :show-header="false"
                    :data="nucleicAcidTestingRecord">
            <el-table-column label="简要信息"
                             fit
                             align="center"
                             class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-row class="textLeft HorizontalMargin">
                  <span>{{getShowNucleicAcidTestingRecord(scope.row)}}</span>
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
                           @click="handleDelete_nucleicAcidTestingRecord(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
        <el-form-item label=""
                      v-if="open_form_nucleicAcidTestingRecord">
          <el-form ref="form_nucleicAcidTestingRecord"
                   :model="form_nucleicAcidTestingRecord"
                   :rules="rules_nucleicAcidTestingRecord"
                   label-width="120px">
            <el-form-item label="姓名"
                          prop="name">
              <el-input v-model="form_nucleicAcidTestingRecord.name"
                        placeholder="请输入姓名" />
            </el-form-item>
            <el-form-item label="身份证号"
                          prop="identificationNumber">
              <el-input v-model="form_nucleicAcidTestingRecord.identificationNumber"
                        placeholder="请输入身份证号" />
            </el-form-item>
            <el-form-item label="检测时间"
                          prop="time">
              <el-date-picker clearable
                              size="small"
                              style="width: 200px"
                              type="datetime"
                              v-model="form_nucleicAcidTestingRecord.time"
                              value-format="yyyy-MM-dd HH:mm:ss"
                              placeholder="选择检测时间" />
            </el-form-item>
            <el-form-item label="检测结果"
                          prop="result">
              <el-radio-group v-model="form_nucleicAcidTestingRecord.result">
                <el-radio v-for="dict in nucleicAcidTestingOptions"
                          :key="dict.dictLabel"
                          :label="dict.dictLabel">{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
            <div class="dialog-footer">
              <el-button type="primary"
                         @click="submitForm_nucleicAcidTestingRecord">确 定</el-button>
              <el-button @click="cancel_nucleicAcidTestingRecord">取 消</el-button>
            </div>
          </el-form>
        </el-form-item>
        <el-form-item label="下次核酸检测时间"
                      prop="nextNucleicAcidTestingData">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.nextNucleicAcidTestingData"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择下次核酸检测时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="外出记录"
                      prop="outRecord">
          <el-button type="primary"
                     icon="el-icon-plus"
                     size="mini"
                     @click="handleAdd_outRecord">新增外出记录</el-button>
          <el-table stripe
                    :show-header="false"
                    :data="outRecord">
            <el-table-column label="简要信息"
                             fit
                             align="center"
                             class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-row class="textLeft HorizontalMargin">
                  <span>{{getShowOutRecord(scope.row)}}</span>
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
                           @click="handleDelete_outRecord(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
        <el-form-item label=""
                      v-if="open_form_outRecord">
          <el-form ref="form_outRecord"
                   :model="form_outRecord"
                   :rules="rules_outRecord"
                   label-width="120px">
            <el-form-item label="姓名"
                          prop="name">
              <el-input v-model="form_outRecord.name"
                        placeholder="请输入姓名" />
            </el-form-item>
            <!-- <el-form-item label="身份证号"
                          prop="identificationNumber">
              <el-input v-model="form_outRecord.identificationNumber"
                        placeholder="请输入身份证号" />
            </el-form-item> -->
            <el-form-item label="外出时间"
                          prop="time">
              <el-date-picker clearable
                              size="small"
                              style="width: 200px"
                              type="datetime"
                              v-model="form_outRecord.time"
                              value-format="yyyy-MM-dd HH:mm:ss"
                              placeholder="选择外出时间" />
            </el-form-item>
            <div class="dialog-footer">
              <el-button type="primary"
                         @click="submitForm_outRecord">确 定</el-button>
              <el-button @click="cancel_outRecord">取 消</el-button>
            </div>
          </el-form>
        </el-form-item>
        <el-form-item label="隔离原因"
                      prop="isolationReason">
          <!-- <el-input v-model="form.isolationReason"
                    type="textarea"
                    placeholder="请输入内容" /> -->
          <el-select v-model="isolationReason"
                     multiple
                     filterable
                     clearable
                     placeholder="请选择隔离原因"
                     style="width:280px;">
            <el-option v-for="item in isolationReasonOptions"
                       :key="item.dictValue"
                       :label="item.dictLabel"
                       :value="item.dictValue">
            </el-option>
          </el-select>
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
import { listEpidemic_isolation_family, getEpidemic_isolation_family, delEpidemic_isolation_family, addEpidemic_isolation_family, updateEpidemic_isolation_family, exportEpidemic_isolation_family } from "@/api/epidemic/epidemic_isolation_family";
import { listCommunity_houses, getCommunity_houses, delCommunity_houses, addCommunity_houses, updateCommunity_houses, exportCommunity_houses } from "@/api/communityUnit/community_houses";
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_building } from "@/api/communityUnit/community_building";
import { listCommunity_child } from "@/api/communityUnit/community_child";
export default {
  name: "Epidemic_isolation_family",
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
      // 隔离家庭 表格数据
      epidemic_isolation_familyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 状态字典
      statOptions: [],
      // 是否有可能需要紧急外出人员,外出类型写备注里字典
      needOutOptions: [],
      // 是否做过核酸检测  字典
      nucleicAcidTestingOptions: [],
      // 社区
      communityOptions: [],
      //小区列表全集
      allCommunityChildOptions: [],
      //小区列表
      communityChildOptions: [],
      //小区列表
      communityChildOptions2: [],
      //社区楼栋列表
      communityBuildingOptions: [],
      //性别字典
      sexOptions: [],
      //隔离原因
      isolationReasonOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        remark: null,
        communityId: null,
        communityChildId: null,
        communityBuildingId: null,
        serialNumber: null,
        buildingName: null,
        unit: null,
        floor: null,
        houseNumber: null,
        personinfo: null,
        isolatioStartTime: null,
        isolationEndTime: null,
        isolationDayNum: null,
        suppliesPurchaseRecord: null,
        lastSuppliesPurchase: null,
        stat: null,
        needOut: null,
        nucleicAcidTestingRecord: null,
        nextNucleicAcidTestingData: null,
        outRecord: null,
        isolationReason: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        communityId: [
          { required: true, message: "社区不能为空", trigger: "change" }
        ],
        communityChildId: [
          { required: true, message: "小区不能为空", trigger: "change" }
        ],
        communityBuildingId: [
          { required: true, message: "社区楼栋不能为空", trigger: "blur" }
        ],
        houseNumber: [
          { required: true, message: "门牌号不能为空", trigger: "blur" }
        ],
        personinfo: [
          { required: true, message: "住户信息不能为空", trigger: "blur" }
        ],
        isolatioStartTime: [
          { required: true, message: "开始隔离时间不能为空", trigger: "blur" }
        ],
        isolationEndTime: [
          { required: true, message: "隔离结束时间不能为空", trigger: "blur" }
        ],
        isolationDayNum: [
          { required: true, message: "隔离总天数不能为空", trigger: "blur" }
        ],
        stat: [
          { required: true, message: "状态不能为空", trigger: "change" }
        ],
        needOut: [
          { required: true, message: "是否需要紧急外出不能为空", trigger: "change" }
        ],
      },
      //住户
      form_personinfo: {
        name: null,
        sex: null,
        identificationNumber: null,
        phoneNumber: null
      },
      rules_personinfo: {
        name: [
          { required: true, message: "姓名不能为空", trigger: "blur" },
          {
            pattern: /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/,
            message: "请输入正确的姓名，只允许使用字母、数字、汉字、下划线",
            trigger: "blur",
          },
        ],
        sex: [
          { required: true, message: "性别不能为空", trigger: "blur" }
        ],
        identificationNumber: [
          { required: true, message: "身份证号码不能为空", trigger: "blur" },
          {
            pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
            message: "你的身份证格式不正确",
          },
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
      open_form_personInfo: false,
      //防疫物资
      form_suppliesPurchaseRecord: {
        time: null,
        imagePath: []
      },
      rules_suppliesPurchaseRecord: {
        time: [
          { required: true, message: "时间不能为空", trigger: "blur" }
        ],
      },
      open_form_suppliesPurchaseRecord: false,
      //核酸检测记录 人员，身份证号，时间，结果
      form_nucleicAcidTestingRecord: {
        name: null,
        identificationNumber: null,
        time: null,
        result: null
      },
      rules_nucleicAcidTestingRecord: {
        name: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        time: [
          { required: true, message: "时间不能为空", trigger: "blur" }
        ],
        result: [
          { required: true, message: "检测结果不能为空", trigger: "blur" }
        ],
      },
      open_form_nucleicAcidTestingRecord: false,
      //外出记录 人员，时间
      form_outRecord: {
        name: null,
        time: null
      },
      rules_outRecord: {
        name: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        time: [
          { required: true, message: "时间不能为空", trigger: "blur" }
        ],
      },
      open_form_outRecord: false,

      //中间表单
      isolationReason: [],//隔离原因
      personinfo: [],//住户信息
      suppliesPurchaseRecord: [],// 物资采购记录
      nucleicAcidTestingRecord: [],//核酸检测记录 
      outRecord: [],//外出记录
      // 搜索表单控件高度
      formHeight: 0
    };
  },
  created () {
    this.getCommunityDictory()
    this.getAllCommunityChildDictory()
    this.getDicts("base_isolatio_stat").then(response => {
      this.statOptions = response.data;
    });
    this.getDicts("base_shi_fou").then(response => {
      this.needOutOptions = response.data;
    });
    this.getDicts("sys_user_sex").then(response => {
      this.sexOptions = response.data;
    });
    this.getDicts("yq_nucleic_acid_amplification_testing").then(response => {
      this.nucleicAcidTestingOptions = response.data;
    });
    this.getDicts("base_isolation_reason").then(response => {
      this.isolationReasonOptions = response.data;
    });
  },
  mounted () {
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight
    })
    this.getList();
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
    setcommunityBuildingDictory (communityChildId) {
      this.communityBuildingOptions = []
      if (!(communityChildId && communityChildId.length > 0)) { return false }
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: null,
        communityChildId: communityChildId
      }
      if (this.form.communityId) {
        param.communityId = this.form.communityId
      }
      listCommunity_building(param).then(response => {
        this.communityBuildingOptions = response.rows;
      });
    },
    //根据选择的楼栋设置关联的楼栋名称等信息
    setcommunityBuildingInfo (communityBuildingId) {
      let buildInfos = this.communityBuildingOptions.filter(item => {
        return item.id === communityBuildingId
      })
      if (buildInfos && buildInfos.length > 0) {
        this.form.serialNumber = buildInfos[0].serialNumber
        this.form.buildingName = buildInfos[0].name
      }
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
    /** 查询隔离家庭 列表 */
    getList () {
      this.loading = true;
      listEpidemic_isolation_family(this.queryParams).then(response => {
        this.epidemic_isolation_familyList = response.rows;
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
    // 状态字典翻译
    statFormat (row, column) {
      return this.selectDictLabel(this.statOptions, row.stat);
    },
    // 是否有可能需要紧急外出人员,外出类型写备注里字典翻译
    needOutFormat (row, column) {
      return this.selectDictLabel(this.needOutOptions, row.needOut);
    },
    //外出记录
    outRecordFormat (row, column, cellValue) {
      if (cellValue && cellValue.length > 0) {
        let list = JSON.parse(cellValue)
        if (list && list.length > 0) {
          return list[list.length - 1].time
        }
      }
      return ''
    },
    //房屋地址翻译
    houseFormat (row, column) {
      return row.buildingName + ' ' + row.unit + '单元' + row.floor + '层' + row.houseNumber
    },
    //住户信息翻译
    personinfoFormat (row, column, cellValue) {
      if (cellValue && cellValue.length > 0) {
        let list = JSON.parse(cellValue)
        if (list && list.length > 0) {
          let personNames = ''
          for (let index = 0; index < list.length; index++) {
            const element = list[index];
            personNames += element.name + ','
          }
          return personNames.trim(',')
        }
      }
      return ''
    },
    //获取还需要隔离的天数
    getTraceDay (isolationEndTime) {
      let currentTime = new Date()
      let needDay = this.$cf.getDateSpan(currentTime, isolationEndTime)
      if (needDay > 0) {
        return Math.round(needDay * 100) / 100 //保留两位小数
        // return needDay
      } else {
        return 0
      }
    },
    getDisableShowIsolationEndTooltip (isolationEndTime) {
      let currentTime = new Date()
      let needDay = this.$cf.getDateSpan(currentTime, isolationEndTime)
      if (needDay > 0) {
        return true
      } else {
        return false
      }
    },
    //获取可以展示的数据
    getShowepidemicIsolationFamilyInfoFromDB (form) {
      //隔离原因
      if (form.isolationReason && form.isolationReason.length > 0) {
        this.isolationReason = this.$cf.getListFromString(form.isolationReason)
      } else {
        this.isolationReason = []
      }
      //住户信息
      if (form.personinfo && form.personinfo.length > 0) {
        this.personinfo = JSON.parse(form.personinfo)
      } else {
        this.personinfo = []
      }
      // 物资采购记录
      if (form.suppliesPurchaseRecord && form.suppliesPurchaseRecord.length > 0) {
        this.suppliesPurchaseRecord = JSON.parse(form.suppliesPurchaseRecord)
      } else {
        this.suppliesPurchaseRecord = []
      }
      //核酸检测记录 
      if (form.nucleicAcidTestingRecord && form.nucleicAcidTestingRecord.length > 0) {
        this.nucleicAcidTestingRecord = JSON.parse(form.nucleicAcidTestingRecord)
      } else {
        this.nucleicAcidTestingRecord = []
      }
      //外出记录 
      if (form.outRecord && form.outRecord.length > 0) {
        this.outRecord = JSON.parse(form.outRecord)
      } else {
        this.outRecord = []
      }
    },
    //获取可以保存的数据
    getDBpidemicIsolationFamilyInfoFromShowe (form) {

      form.isolationReason = ''
      if (this.isolationReason && this.isolationReason.length > 0) {
        form.isolationReason = this.$cf.getStringFromList(this.isolationReason)
      }
      //住户信息
      form.personinfo = ''
      if (this.personinfo && this.personinfo.length > 0) {
        form.personinfo = JSON.stringify(this.personinfo)
      }
      // 物资采购记录
      form.suppliesPurchaseRecord = ''
      if (this.suppliesPurchaseRecord && this.suppliesPurchaseRecord.length > 0) {
        form.suppliesPurchaseRecord = JSON.stringify(this.suppliesPurchaseRecord)
      }
      //核酸检测记录 
      form.nucleicAcidTestingRecord = ''
      if (this.nucleicAcidTestingRecord && this.nucleicAcidTestingRecord.length > 0) {
        form.nucleicAcidTestingRecord = JSON.stringify(this.nucleicAcidTestingRecord)
      }
      //外出记录 
      form.outRecord = ''
      if (this.outRecord && this.outRecord.length > 0) {
        form.outRecord = JSON.stringify(this.outRecord)
      }
    },
    getCellClassName ({ row, column, rowIndex, columnIndex }) {
      let propertyName = column.property //"communityId"
      let className = ''
      switch (propertyName) {
        //隔离结束时间  还需要隔离的天数
        case 'isolationEndTime':
          if (row[propertyName] && this.getTraceDay(row[propertyName]) <= 0) {
            className = 'yellowText'
          }
          break
        default: break
      }
      return className
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
        communityId: null,
        communityChildId: null,
        communityBuildingId: null,
        serialNumber: null,
        buildingName: null,
        unit: null,
        floor: null,
        houseNumber: null,
        personinfo: null,//住户信息
        isolatioStartTime: null,
        isolationEndTime: null,
        isolationDayNum: null,
        suppliesPurchaseRecord: null,// 物资采购记录
        lastSuppliesPurchase: null,
        stat: '隔离中',
        needOut: '否',
        nucleicAcidTestingRecord: null,//核酸检测记录 
        nextNucleicAcidTestingData: null,
        outRecord: null,//外出记录 
        isolationReason: null
      };
      this.resetForm("form");
      this.open_form_personInfo = false
      this.open_form_suppliesPurchaseRecord = false
      this.open_form_nucleicAcidTestingRecord = false
      this.open_form_outRecord = false

      //
      this.isolationReason = []//隔离原因
      this.personinfo = []//住户信息
      this.suppliesPurchaseRecord = []// 物资采购记录
      this.nucleicAcidTestingRecord = []//核酸检测记录 
      this.outRecord = []//外出记录
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
      this.title = "添加隔离家庭 ";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const id = row.id || this.ids
      getEpidemic_isolation_family(id).then(response => {
        this.getShowepidemicIsolationFamilyInfoFromDB(response.data)
        this.form = response.data;
        this.getCommunityChildDictory2(this.form.communityId)
        this.setcommunityBuildingDictory(this.form.communityChildId)

        this.open = true;
        this.title = "修改隔离家庭 ";
      });
    },
    /** 提交按钮 */
    submitForm () {
      let self = this
      self.getDBpidemicIsolationFamilyInfoFromShowe(self.form)
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateEpidemic_isolation_family(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addEpidemic_isolation_family(this.form).then(response => {
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
      this.$confirm('是否确认删除隔离家庭 编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delEpidemic_isolation_family(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有隔离家庭 数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportEpidemic_isolation_family(queryParams);
      }).then(response => {
        this.download(response.msg);
      }).catch(function () { });
    },
    /**隔离结束时间变化 */
    isolationEndTimeChange (value) {
      if (this.form.isolatioStartTime && value) {
        let needDay = this.$cf.getDateSpan(this.form.isolatioStartTime, value)
        if (needDay > 0) {
          this.form.isolationDayNum = Math.round(needDay)
        }
      }
    },
    /**隔离天数变化 */
    isolationDayNumChange (value) {
      if (this.form.isolatioStartTime && value && value > 0) {
        let timeSpan = value * 24 * 60 * 60 * 1000 //毫秒
        let startTime = new Date(this.form.isolatioStartTime)
        let endTime = new Date(startTime.getTime() + timeSpan)
        this.form.isolationEndTime = this.$cf.DataFormat(endTime)
      }
    },

    //重置住户信息
    reset_personInfo () {
      this.form_person = {
        // 张三,性别,身份证号,手机号;
        name: null,
        sex: '未知',
        identificationNumber: null,
        phoneNumber: null
      }
    },
    //获取展示的住户信息
    getShowPersonInfo (row) {
      if (row.name) {
        return row.name + '[' + row.sex + ']' + '(' + row.identificationNumber + ')' + row.phoneNumber
      } else {
        return ''
      }
    },
    /**在隔离家庭里添加一个住户 */
    handleAdd_personinfo () {
      this.reset_personInfo()
      this.open_form_personInfo = true
    },
    /**删除一个隔离家庭里的住户 */
    handleDelete_personinfo (row) {
      this.personinfo.remove(row)
    },
    //提交一个住户信息
    submitForm_personinfo () {
      let self = this
      this.$refs["form_personinfo"].validate(valid => {
        if (valid) {
          self.personinfo.push({
            id: self.$cf.Guid(),
            name: self.form_personinfo.name,
            sex: self.form_personinfo.sex,
            identificationNumber: self.form_personinfo.identificationNumber,
            phoneNumber: self.form_personinfo.phoneNumber
          })
          self.open_form_personInfo = false
        }
      })

    },
    //取消添加住户
    cancel_personinfo () {
      this.open_form_personInfo = false
    },


    //重置物资采购
    reset_suppliesPurchaseRecord () {
      this.form_suppliesPurchaseRecord = {
        time: null,
        imagePath: []
      }
    },
    //获取展示的物资采购信息
    getShowSuppliesPurchaseRecord (row) {
      if (row.time) {
        return row.time
      } else {
        return ''
      }
    },
    /**在隔离家庭里添加一个物资采购 */
    handleAdd_suppliesPurchaseRecord () {
      this.reset_suppliesPurchaseRecord()
      this.open_form_suppliesPurchaseRecord = true
    },
    /**删除一个物资采购 */
    handleDelete_suppliesPurchaseRecord (row) {
      this.suppliesPurchaseRecord.remove(row)
    },
    //提交一个物资采购
    submitForm_suppliesPurchaseRecord () {
      let self = this
      this.$refs["form_suppliesPurchaseRecord"].validate(valid => {
        if (valid) {
          self.form.lastSuppliesPurchase = self.form_suppliesPurchaseRecord.time
          self.suppliesPurchaseRecord.push({
            id: self.$cf.Guid(),
            time: self.form_suppliesPurchaseRecord.time,
            imagePath: self.form_suppliesPurchaseRecord.imagePath
          })
          self.open_form_suppliesPurchaseRecord = false
        }
      })

    },
    //取消添加物资采购
    cancel_suppliesPurchaseRecord () {
      this.open_form_suppliesPurchaseRecord = false
    },

    //重置核酸检测
    reset_nucleicAcidTestingRecord () {
      this.form_nucleicAcidTestingRecord = {
        name: null,
        identificationNumber: null,
        time: null,
        result: '未出结果'
      }
    },
    //获取展示的核酸检测信息
    getShowNucleicAcidTestingRecord (row) {
      if (row.name) {
        return row.name + '(' + row.identificationNumber + ')' + row.time
      } else {
        return ''
      }
    },
    /**在隔离家庭里添加一个核酸检测 */
    handleAdd_nucleicAcidTestingRecord () {
      this.reset_nucleicAcidTestingRecord()
      this.open_form_nucleicAcidTestingRecord = true
    },
    /**删除一个核酸检测 */
    handleDelete_nucleicAcidTestingRecord (row) {
      this.nucleicAcidTestingRecord.remove(row)
    },
    //提交一个核酸检测
    submitForm_nucleicAcidTestingRecord () {
      let self = this
      this.$refs["form_nucleicAcidTestingRecord"].validate(valid => {
        if (valid) {
          this.nucleicAcidTestingRecord.push({
            id: self.$cf.Guid(),
            name: self.form_nucleicAcidTestingRecord.name,
            identificationNumber: self.form_nucleicAcidTestingRecord.identificationNumber,
            time: self.form_nucleicAcidTestingRecord.time,
            result: self.form_nucleicAcidTestingRecord.result
          })
          this.open_form_nucleicAcidTestingRecord = false
        }
      })

    },
    //取消添加核酸检测
    cancel_nucleicAcidTestingRecord () {
      this.open_form_nucleicAcidTestingRecord = false
    },

    //重置外出记录
    reset_outRecord () {
      this.form_outRecord = {
        name: null,
        time: null
      }
    },
    //获取展示的外出记录信息
    getShowOutRecord (row) {
      if (row.time) {
        return row.name + '  ' + row.time
      } else {
        return ''
      }
    },
    /**在隔离家庭里添加一个外出记录 */
    handleAdd_outRecord () {
      this.reset_outRecord()
      this.open_form_outRecord = true
    },
    /**删除一个外出记录 */
    handleDelete_outRecord (row) {
      this.outRecord.remove(row)
    },
    //提交一个外出记录
    submitForm_outRecord () {
      let self = this
      this.$refs["form_outRecord"].validate(valid => {
        if (valid) {
          this.outRecord.push({
            id: self.$cf.Guid(),
            time: self.form_outRecord.time,
            name: self.form_outRecord.name
          })
          this.open_form_outRecord = false
        }
      })

    },
    //取消添加外出记录
    cancel_outRecord () {
      this.open_form_outRecord = false
    }
  },
  computed: {
    dataHeight () {
      let searchFormHeight = 0
      if (this.showSearch) searchFormHeight = this.formHeight
      return this.$store.getters.dataEleHeight - searchFormHeight - 160
    }
  }
};
</script>
