<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch"
             label-width="68px">
      <el-form-item label="社区名称"
                    prop="targetPersonCommunityId">
        <el-select v-model="queryParams.targetPersonCommunityId"
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
                    prop="targetPersonCommunityChildId">
        <el-select v-model="queryParams.targetPersonCommunityChildId"
                   filterable
                   clearable
                   placeholder="请选择小区">
          <el-option v-for="dict in communityChildOptions"
                     :key="dict.id"
                     :label="dict.name"
                     :value="dict.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="车牌号码"
                    prop="plate">
        <el-input v-model="queryParams.plate"
                  placeholder="请输入车牌号码"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="驾驶员"
                    prop="driverName">
        <el-input v-model="queryParams.driverName"
                  placeholder="请输入驾驶员姓名"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="拜访业主"
                    prop="targetPersonName">
        <el-input v-model="queryParams.targetPersonName"
                  placeholder="请输入被拜访人名称"
                  clearable
                  style="width: 170px"
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="车型" prop="model">
        <el-select
          v-model="queryParams.model"
          placeholder="请选择车型"
          clearable
          size="small"
        >
          <el-option
            v-for="dict in modelOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="车品牌" prop="brand">
        <el-input
          v-model="queryParams.brand"
          placeholder="请输入车品牌"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="品牌型号" prop="specificBrand">
        <el-input
          v-model="queryParams.specificBrand"
          placeholder="请输入具体品牌型号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="座位数" prop="numberOfSeats">
        <el-input
          v-model="queryParams.numberOfSeats"
          placeholder="请输入座位数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="驾驶员"
                    prop="driverName">
        <el-input v-model="queryParams.driverName"
                  placeholder="请输入驾驶员姓名"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="性别"
                    prop="driverSex">
        <el-select v-model="queryParams.driverSex"
                   placeholder="请选择性别"
                   clearable
                   size="small">
          <el-option v-for="dict in driverSexOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="身份证"
                    prop="driverIdentificationNumber">
        <el-input v-model="queryParams.driverIdentificationNumber"
                  placeholder="请输入驾驶员身份证号码"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="图片许可"
                    prop="visitorPicPermit">
        <el-select v-model="queryParams.visitorPicPermit"
                   placeholder="访客图片是否有闸机许可"
                   clearable
                   size="small">
          <el-option v-for="dict in visitorPicPermitOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="车牌许可"
                    prop="visitorPlatePermit">
        <el-select v-model="queryParams.visitorPlatePermit"
                   placeholder="访客车牌号是否有闸机许可"
                   clearable
                   size="small">
          <el-option v-for="dict in visitorPlatePermitOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="社区名称"
                    prop="targetPersonCommunityId">
        <el-select v-model="queryParams.targetPersonCommunityId"
                   filterable
                   clearable
                   style="width: 120px"
                   @change="getCommunityChildDictory"
                   placeholder="请选择社区">
          <el-option v-for="dict in communityOptions"
                     :key="dict.id"
                     :label="dict.name"
                     :value="dict.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="拜访小区"
                    prop="targetPersonCommunityChildId">
        <el-select v-model="queryParams.targetPersonCommunityChildId"
                   filterable
                   clearable
                   style="width: 180px"
                   placeholder="请选择小区">
          <el-option v-for="dict in communityChildOptions"
                     :key="dict.id"
                     :label="dict.name"
                     :value="dict.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="拜访业主"
                    prop="targetPersonName">
        <el-input v-model="queryParams.targetPersonName"
                  placeholder="请输入被拜访人名称"
                  clearable
                  style="width: 170px"
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="拜访地址"
                    prop="targetPersonHouseId">
        <el-input v-model="queryParams.targetPersonHouseId"
                  placeholder="请输入拜访地址"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="开始时间"
                    prop="visitStartTime">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="queryParams.visitStartTime"
                        type="datetime"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        placeholder="选择拜访开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间"
                    prop="visitEndTime">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="queryParams.visitEndTime"
                        type="datetime"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        placeholder="选择拜访结束时间">
        </el-date-picker>
      </el-form-item>
      </el-row>
      <el-form-item label="审核状态"
                    prop="checked">
        <el-select v-model="queryParams.checked"
                   placeholder="请输入是否已经审核确认"
                   clearable
                   size="small">
          <el-option v-for="dict in checkedOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="审核人"
                    prop="checkedPersonName">
        <el-input v-model="queryParams.checkedPersonName"
                  placeholder="请输入审核人员名称"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <!-- <el-form-item label="审核时间"
                    prop="checkedTime">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="queryParams.checkedTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择审核时间">
        </el-date-picker>
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
                   v-hasPermi="['passManage:visitVehicle:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['passManage:visitVehicle:remove']">删除</el-button>
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
                   v-hasPermi="['passManage:visitVehicle:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['passManage:visitVehicle:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['passManage:visitVehicle:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['passManage:visitVehicle:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading"
              stripe
              :height="dataHeight"
              :data="visitVehicleList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="车牌号码"
                       align="center"
                       width="120"
                       prop="plate" />
      <el-table-column label="车型"
                       align="center"
                       prop="model"
                       :formatter="modelFormat" />
      <!-- <el-table-column label="座位数"
                       align="center"
                       prop="numberOfSeats" /> -->
      <el-table-column label="驾驶员"
                       align="center"
                       prop="driverName" />
      <el-table-column label="性别"
                       align="center"
                       width="80"
                       prop="driverSex"
                       :formatter="driverSexFormat" />
      <el-table-column label="身份证"
                       align="center"
                       prop="driverIdentificationNumber" />
      <el-table-column label="手机号码"
                       align="center"
                       width="110"
                       prop="driverPhoneNumber" />
      <el-table-column label="拜访业主"
                       align="center"
                       width="auto"
                       prop="targetPersonName" />
      <el-table-column label="拜访社区"
                       align="center"
                       prop="targetPersonCommunityId"
                       :formatter="communityNameFormat" />
      />
      <el-table-column label="拜访地址"
                       align="center"
                       prop="targetPersonHouseAddress" />
      <el-table-column label="拜访开始时间"
                       align="center"
                       prop="visitStartTime"
                       width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.visitStartTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="拜访结束时间"
                       align="center"
                       prop="visitEndTime"
                       width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.visitEndTime) }}</span>
        </template>
      </el-table-column>

      <el-table-column label="照片"
                       align="center"
                       prop="pics">
        <template slot-scope="scope">
          <el-image v-if="scope.row.pics && scope.row.pics.length > 0"
                    style="width: 100px; height: 80px"
                    :src="getImgUrl(scope.row.pics)"
                    fit="fill"></el-image>
          <!-- scope.row.pics -->
          <div v-else>无图</div>
        </template>
      </el-table-column>
      <el-table-column label="审核状态"
                       align="center"
                       width="auto"
                       prop="checked"
                       :formatter="checkedFormat">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     :disabled="scope.row.checked === '1'"
                     @click="handleUpdate_check(scope.row)"
                     v-hasPermi="['passManage:visitPerson:edit']">{{ scope.row.checked | checkedFilters(that) }}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="审核人"
                       align="center"
                       width="auto"
                       prop="checkedPersonName" />
      <el-table-column label="审核时间"
                       align="center"
                       prop="checkedTime"
                       width="120">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkedTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作"
                       align="center"
                       width="120"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['passManage:visitVehicle:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['passManage:visitVehicle:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改访客车辆信息对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="550px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="100px">
        <el-form-item label="车牌号码"
                      prop="plate">
          <el-input v-model="form.plate"
                    :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                    placeholder="请输入车牌号码" />
        </el-form-item>
        <el-form-item label="车型"
                      prop="model">
          <el-select v-model="form.model"
                     placeholder="请选择车型">
            <el-option v-for="dict in modelOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车品牌"
                      prop="brand">
          <el-input v-model="form.brand"
                    placeholder="请输入车品牌" />
        </el-form-item>
        <el-form-item label="具体型号"
                      prop="specificBrand">
          <el-input v-model="form.specificBrand"
                    placeholder="请输入具体品牌型号" />
        </el-form-item>
        <el-form-item label="座位数"
                      prop="numberOfSeats">
          <el-input v-model="form.numberOfSeats"
                    placeholder="请输入座位数" />
        </el-form-item>
        <el-form-item label="驾驶员"
                      prop="driverName">
          <el-input v-model="form.driverName"
                    :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                    placeholder="请输入驾驶员姓名" />
        </el-form-item>
        <el-form-item label="性别"
                      prop="driverSex">
          <el-select v-model="form.driverSex"
                     :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                     placeholder="请选择性别">
            <el-option v-for="dict in driverSexOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="身份证"
                      prop="driverIdentificationNumber">
          <el-input v-model="form.driverIdentificationNumber"
                    :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                    placeholder="请输入驾驶员身份证号码" />
        </el-form-item>
        <el-form-item label="手机号码"
                      prop="driverPhoneNumber">
          <el-input v-model="form.driverPhoneNumber"
                    placeholder="请输入手机号码" />
        </el-form-item>

        <!-- 选择社区 -->
        <el-form-item label="社区名称"
                      prop="targetPersonCommunityId">
          <el-select v-model="form.targetPersonCommunityId"
                     filterable
                     :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
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
          <el-select v-model="form.targetPersonCommunityChildId"
                     filterable
                     :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                     placeholder="请选择小区">
            <el-option v-for="dict in communityChildOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="拜访业主"
                      prop="targetPersonId">
          <el-select v-model="form.targetPersonId"
                     filterable
                     :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                     @change="setTargetPerson"
                     remote
                     :remote-method="remotePersonInfoMethod"
                     :loading="loading_remotePersonInfo"
                     placeholder="请选择人员姓名">
            <el-option v-for="dict in personOptions"
                       :key="dict.id"
                       :label="dict.name + ' ' + dict.phone"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="拜访具体地址"
                      prop="targetPersonHouseAddress">
          <el-input v-model="form.targetPersonHouseAddress"
                    :readonly="true"
                    placeholder="请输入拜访具体地址" />
        </el-form-item>
        <el-form-item label="拜访开始时间"
                      prop="visitStartTime">
          <el-date-picker clearable
                          :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                          size="small"
                          style="width: 200px"
                          v-model="form.visitStartTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择拜访开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="拜访结束时间"
                      prop="visitEndTime">
          <el-date-picker clearable
                          :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                          size="small"
                          style="width: 200px"
                          v-model="form.visitEndTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择拜访结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="照片列表"
                      prop="pics">
          <!-- <el-input v-model="form.pics"
                    :disabled="form.id && form.id.length>0 && form.checked && form.checked === '1'"
                    type="textarea"
                    placeholder="请输入内容" /> -->
          <imageListUpload :upLoadUrl="fileUpLoadUrl"
                           :key="form.id"
                           :uploadState='uploadState'
                           :filesJson.sync="form.pics" />
        </el-form-item>
        <el-form-item v-if="form.id && form.id.length > 0"
                      label="图片许可"
                      prop="visitorPicPermit">
          <el-select v-model="form.visitorPicPermit"
                     :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                     placeholder="访客图片是否有闸机许可">
            <el-option v-for="dict in visitorPicPermitOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item v-if="form.id && form.id.length > 0"
                      label="车牌许可"
                      prop="visitorPlatePermit">
          <el-select v-model="form.visitorPlatePermit"
                     :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                     placeholder="访客车牌号是否有闸机许可">
            <el-option v-for="dict in visitorPlatePermitOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item v-if="form.id && form.id.length > 0"
                      label="审核状态"
                      prop="checked">
          <el-select v-model="form.checked"
                     :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                     placeholder="请输入是否已经审核确认">
            <el-option v-for="dict in checkedOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item v-if="
            form.id &&
            form.id.length > 0 &&
            form.checked &&
            form.checked === '1'
          "
                      label="审核人"
                      prop="checkedPersonName">
          <el-input v-model="form.checkedPersonName"
                    :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                    placeholder="请输入审核人员名称" />
        </el-form-item>
        <el-form-item v-if="
            form.id &&
            form.id.length > 0 &&
            form.checked &&
            form.checked === '1'
          "
                      label="审核时间"
                      prop="checkedTime">
          <el-date-picker clearable
                          :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                          size="small"
                          style="width: 200px"
                          v-model="form.checkedTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择审核时间">
          </el-date-picker>
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
  listVisitVehicle,
  getVisitVehicle,
  delVisitVehicle,
  addVisitVehicle,
  updateVisitVehicle,
  exportVisitVehicle,
  updateVisitVehicleCheck,
} from "@/api/passManage/visitVehicle";
import { listCommunity } from "@/api/communityUnit/community";
import { getInfoByName } from "@/api/communityUnit/personnel_information";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import imageListUpload from "@/components/upload/uploadImageList";
import configInfo from "@/webconfig/configInfo";
export default {
  name: "VisitVehicle",
  components: { imageListUpload },
  data () {
    return {
      that: this,
      // 遮罩层
      loading: true,
      //远程人人员搜索
      loading_remotePersonInfo: false,
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
      // 访客车辆信息表格数据
      visitVehicleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 车型字典
      modelOptions: [],
      // 性别字典
      driverSexOptions: [],
      // 是否已经审核确认,(0否1是,默认0)字典
      checkedOptions: [],
      // 社区
      communityOptions: [],
      //小区列表
      communityChildOptions: [],
      //小区列表全集
      allCommunityChildOptions: [],
      //远程搜索人员列表
      personOptions: [],
      // 访客的图片是否已经加入到闸机许可中，0否1是，默认为0字典
      visitorPicPermitOptions: [],
      // 访客的车牌号是否已经加入到闸机许可中，0否1是，默认为0字典
      visitorPlatePermitOptions: [],
      //删除提示
      deleteNotices: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        plate: null,
        model: null,
        brand: null,
        specificBrand: null,
        numberOfSeats: null,
        driverName: null,
        driverSex: null,
        driverIdentificationNumber: null,
        checked: null,
        pics: null,
        checkedPersonId: null,
        checkedPersonName: null,
        checkedTime: null,
        targetPersonId: null,
        targetPersonName: null,
        targetPersonHouseId: null,
        targetPersonCommunityId: null,
        visitStartTime: null,
        visitEndTime: null,
        visitorPicPermit: null,
        visitorPlatePermit: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        delFlag: [
          { required: true, message: "删除标志不能为空", trigger: "blur" },
        ],
        plate: [
          { required: true, message: "车牌号不能为空", trigger: "blur" },
          // {pattern:/(^[\u4E00-\u9FA5]{1}[A-Z0-9]{6}$)|(^[A-Z]{2}[A-Z0-9]{2}[A-Z0-9\u4E00-\u9FA5]{1}[A-Z0-9]{4}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{5}[挂学警军港澳]{1}$)|(^[A-Z]{2}[0-9]{5}$)|(^(08|38){1}[A-Z0-9]{4}[A-Z0-9挂学警军港澳]{1}$)/,message: '常规格式：京A12345'},
          {
            pattern: /(^[\u4E00-\u9FA5]{1}[A-Z0-9]{6}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{7}$)|(^[A-Z]{2}[A-Z0-9]{2}[A-Z0-9\u4E00-\u9FA5]{1}[A-Z0-9]{4}$)|(^[A-Z]{2}[A-Z0-9]{2}[A-Z0-9\u4E00-\u9FA5]{1}[A-Z0-9]{5}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{5}[挂学警军港澳]{1}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{6}[挂学警军港澳]{1}$)|(^[A-Z]{2}[0-9]{5}$)|(^[A-Z]{2}[0-9]{6}$)|(^(08|38){1}[A-Z0-9]{4}[A-Z0-9挂学警军港澳]{1}$)|(^(08|38){1}[A-Z0-9]{5}[A-Z0-9挂学警军港澳]{1}$)/,
            message: "常规格式：京A12345,新能源格式: 京A123456",
          },
        ],
        driverIdentificationNumber: [
          { required: true, message: "身份证号码不能为空", trigger: "blur" },
          {
            pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
            message: "你的身份证格式不正确",
          },
        ],
        driverPhoneNumber: [
          { required: true, message: "手机号码不能为空", trigger: "blur" },
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur",
          },
        ],
        targetPersonCommunityId: [
          { required: true, message: "社区名称不能为空", trigger: "blur" },
        ],
        targetPersonCommunityChildId: [
          { required: true, message: "小区名称不能为空", trigger: "blur" },
        ],
        targetPersonId: [
          { required: true, message: "拜访业主名称不能为空", trigger: "blur" },
        ],
        driverName: [
          { required: true, message: "驾驶员名称不能为空", trigger: "blur" },
        ],
        targetPersonHouseAddress: [
          { required: true, message: "拜访地址不能为空", trigger: "blur" },
        ],
      },
      formItemShow: false,
      //图片上传接口地址
      fileUpLoadUrl: null,
      // 搜索表单控件高度
      formHeight: 0,
      // 上传图片状态
      uploadState: true
    };
  },
  filters: {
    checkedFilters: function (value, self) {
      return self.checkedFormat(null, null, value);
    },
  },
  created () {
    this.getList();
    this.getCommunityDictory();
    this.getDicts("base_vehicle_model").then((response) => {
      this.modelOptions = response.data;
    });
    this.getDicts("sys_user_sex").then((response) => {
      this.driverSexOptions = response.data;
    });
    this.getDicts("base_checked_stat").then((response) => {
      this.checkedOptions = response.data;
    });
    this.getDicts("base_yes_no").then((response) => {
      this.visitorPicPermitOptions = response.data;
    });
    this.getDicts("base_yes_no").then((response) => {
      this.visitorPlatePermitOptions = response.data;
    });
  },
  mounted () {
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight;
    });
    this.fileUpLoadUrl = configInfo.FileUpLoadUri;
  },
  computed: {
    dataHeight () {
      let searchFormHeight = 0;
      if (this.showSearch) searchFormHeight = this.formHeight;
      return this.$store.getters.dataEleHeight - searchFormHeight - 115;
    },
  },
  methods: {
    getImgUrl (url) {
      let urlList = this.$cf.returnJson(url)
      if (urlList.length > 0) {
        return configInfo.imgEchoAddress + urlList[0].url
      } else {
        return ''
      }
    },
    /** 查询访客车辆信息列表 */
    getList () {
      this.loading = true;
      listVisitVehicle(this.queryParams).then((response) => {
        console.log(response);
        this.visitVehicleList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
      getInfoByName(param).then((response) => {
        this.personOptions = response.rows;
        this.loading_remotePersonInfo = false;
      });
    },
    setTargetPerson (targetPersonId) {
      if (targetPersonId && targetPersonId.length > 0) {
        let target = this.personOptions.filter((item) => {
          return item.id === targetPersonId;
        });
        if (target && target.length > 0) {
          this.form.targetPersonName = target[0].name;
          this.form.targetPersonHouseId = target[0].houseId;
          this.form.targetPersonCommunityId = target[0].communityId;
          this.form.targetPersonCommunityChildId = target[0].communityChildId;
          this.form.targetPersonHouseAddress =
            target[0].buildingName +
            target[0].unit +
            "单元" +
            target[0].floor +
            "层" +
            target[0].houseNumber;
        }
      }
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
      return "";
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
    // 车型字典翻译
    modelFormat (row, column) {
      return this.selectDictLabel(this.modelOptions, row.model);
    },
    // 性别字典翻译
    driverSexFormat (row, column) {
      return this.selectDictLabel(this.driverSexOptions, row.driverSex);
    },
    // 是否已经审核确认,(0否1是,默认0)字典翻译
    checkedFormat (row, column, cellValue) {
      return this.selectDictLabel(this.checkedOptions, cellValue);
    },
    // 访客的图片是否已经加入到闸机许可中，0否1是，默认为0字典翻译
    visitorPicPermitFormat (row, column) {
      return this.selectDictLabel(
        this.visitorPicPermitOptions,
        row.visitorPicPermit
      );
    },
    // 访客的车牌号是否已经加入到闸机许可中，0否1是，默认为0字典翻译
    visitorPlatePermitFormat (row, column) {
      return this.selectDictLabel(
        this.visitorPlatePermitOptions,
        row.visitorPlatePermit
      );
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
    communityNameFormat (row, column, cellValue) {
      if (this.communityOptions && this.communityOptions.length > 0) {
        let rows = this.communityOptions.filter((item) => {
          return item.id === cellValue;
        });
        if (rows && rows.length > 0) {
          return rows[0].name;
        }
      }
      return "查询失败";
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
        plate: null,
        model: null,
        brand: null,
        specificBrand: null,
        numberOfSeats: null,
        driverName: null,
        driverSex: null,
        driverIdentificationNumber: null,
        driverPhoneNumber: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        checked: null,
        pics: null,
        checkedPersonId: null,
        checkedPersonName: null,
        checkedTime: null,
        targetPersonId: null,
        targetPersonName: null,
        targetPersonHouseId: null,
        targetPersonCommunityId: null,
        targetPersonHouseAddress: null,
        visitStartTime: null,
        visitEndTime: null,
        visitorPicPermit: null,
        visitorPlatePermit: null,
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
      this.ids = selection.map((item) => item.id);
      this.deleteNotices = selection.map((item) => item.plate);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.uploadState = true
      this.reset();
      this.open = true;
      this.title = "添加访客车辆信息";
      this.formItemShow = false;
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.uploadState = false
      this.reset();
      const id = row.id || this.ids;
      getVisitVehicle(id).then((response) => {
        this.form = response.data;
        this.getCommunityChildDictory(this.form.targetPersonCommunityId);
        this.remotePersonInfoMethod(this.form.targetPersonName);
        this.open = true;
        this.title = "修改访客车辆信息";
        this.formItemShow = true;
      });
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateVisitVehicle(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addVisitVehicle(this.form).then((response) => {
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
      let deleteNotice = row.plate || this.deleteNotices;
      this.$confirm(
        '是否确认删除访客车辆车牌号为"' + deleteNotice + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delVisitVehicle(ids);
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
      this.$confirm("是否确认导出所有访客车辆信息数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportVisitVehicle(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () { });
    },
    /** 修改审核状态操作 */
    handleUpdate_check (row) {
      const name = row.driverName + " " + row.plate;
      let self = this;
      this.$confirm('是否确认同意"' + name + '"的拜访?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(function () {
        let param = {
          id: row.id,
          checked: "1",
          checkedTime: self.$cf.getNowTime(),
        };
        updateVisitVehicleCheck(param)
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
  },
};
</script>
