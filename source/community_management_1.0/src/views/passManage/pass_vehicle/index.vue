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
      <!-- <el-form-item label="设备编码" prop="deviceId">
        <el-input
          v-model="queryParams.deviceId"
          placeholder="请输入设备编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="车辆出现时间" prop="appearTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.appearTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择车辆出现时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="车辆消失时间" prop="disappearTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.disappearTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择车辆消失时间">
        </el-date-picker>
      </el-form-item> -->
      <!-- <el-form-item label="经过时刻"
                    prop="passTime">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="queryParams.passTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择经过时刻">
        </el-date-picker>
      </el-form-item> -->

      <el-form-item label="车牌号"
                    prop="plateNo">
        <el-input v-model="queryParams.plateNo"
                  placeholder="请输入车牌号"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="车辆类型"
                    prop="sourceTableName">
        <el-select v-model="queryParams.sourceTableName"
                   placeholder="请选择车辆类型"
                   clearable
                   size="small">
          <el-option v-for="dict in sourceTableNameOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <!--       
      <el-form-item label="车辆品牌" prop="vehicleBrand">
        <el-input
          v-model="queryParams.vehicleBrand"
          placeholder="请输入车辆品牌"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!--       
      <el-form-item label="车辆类型" prop="vehicleClass">
        <el-input
          v-model="queryParams.vehicleClass"
          placeholder="请输入车辆类型"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="车身颜色" prop="vehicleColor">
        <el-input
          v-model="queryParams.vehicleColor"
          placeholder="请输入车身颜色"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!--       
      <el-form-item label="车辆型号" prop="vehicleModel">
        <el-input
          v-model="queryParams.vehicleModel"
          placeholder="请输入车辆型号"
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
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAdd"
                   v-hasPermi="['passManage:pass_vehicle:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['passManage:pass_vehicle:remove']">删除</el-button>
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
                   v-hasPermi="['passManage:pass_vehicle:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['passManage:pass_vehicle:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['passManage:pass_vehicle:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['passManage:pass_vehicle:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading"
              stripe
              :data="pass_vehicleList"
              :height="dataHeight"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <!-- <el-table-column label="社区id"
                       align="center"
                       prop="communityId" /> -->
      <el-table-column label="社区名称"
                       align="center"
                       prop="communityName" />
      <!-- <el-table-column label="小区id"
                       align="center"
                       prop="communityChildId" /> -->
      <el-table-column label="小区名称"
                       align="center"
                       prop="communityChildName" />
      <el-table-column label="设备"
                       align="center"
                       prop="deviceId" />
      <el-table-column label="车牌号"
                       align="center"
                       prop="plateNo" />
      <el-table-column label="近景照片"
                       align="center"
                       width="220"
                       prop="storageUrl1" />
      <!--     
      <el-table-column label="关联卡口编号"
                       align="center"
                       prop="tollgateId" /> -->
      <!--      
      <el-table-column label="车身颜色"
                       align="center"
                       prop="vehicleColor" /> -->
      <!-- <el-table-column label="车辆型号"
                       align="center"
                       prop="vehicleModel" /> -->
      <!-- <el-table-column label="对应的在车辆表里的车辆ID"
                       align="center"
                       prop="vehicleId" /> -->
      <el-table-column label="操作"
                       align="center"
                       width="120"
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
                     v-hasPermi="['passManage:pass_vehicle:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['passManage:pass_vehicle:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <el-dialog :title="title"
               :visible.sync="gisOpen"
               append-to-body
               :before-close="closeDialog">
      <gisView :passLocationObject="passLocation_"
               :passTrackObject=null
               style="height:600px;width:800px; margin:0 auto" />
    </el-dialog>

    <!-- 添加或修改车辆通行对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="700px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="140px">
        <el-form-item label="车辆出现时间"
                      prop="appearTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.appearTime"
                          type="date"
                          placeholder="选择车辆出现时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="品牌标志识别可信度"
                      prop="brandReliability">
          <el-input v-model="form.brandReliability"
                    placeholder="请输入品牌标志识别可信度" />
        </el-form-item>
        <el-form-item label="打电话状态"
                      prop="calling">
          <el-input v-model="form.calling"
                    placeholder="请输入打电话状态" />
        </el-form-item>
        <el-form-item label="车厢"
                      prop="carOfVehicle">
          <el-input v-model="form.carOfVehicle"
                    placeholder="请输入车厢" />
        </el-form-item>
        <el-form-item label="车前部物描述"
                      prop="descOfFrontItem">
          <el-input v-model="form.descOfFrontItem"
                    placeholder="请输入车前部物描述" />
        </el-form-item>
        <el-form-item label="车后部物品描述"
                      prop="descOfRearItem">
          <el-input v-model="form.descOfRearItem"
                    placeholder="请输入车后部物品描述" />
        </el-form-item>
        <el-form-item label="设备编码"
                      prop="deviceId">
          <el-input v-model="form.deviceId"
                    placeholder="请输入设备编码" />
        </el-form-item>
        <el-form-item label="行驶方向"
                      prop="direction">
          <el-input v-model="form.direction"
                    placeholder="请输入行驶方向" />
        </el-form-item>
        <el-form-item label="车辆消失时间"
                      prop="disappearTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.disappearTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择车辆消失时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="行驶状态代码"
                      prop="drivingStatusCode">
          <el-input v-model="form.drivingStatusCode"
                    placeholder="请输入行驶状态代码" />
        </el-form-item>
        <el-form-item label="贴膜颜色"
                      prop="filmColor">
          <el-input v-model="form.filmColor"
                    placeholder="请输入贴膜颜色" />
        </el-form-item>
        <el-form-item label="有无车牌"
                      prop="hasPlate">
          <el-input v-model="form.hasPlate"
                    placeholder="请输入有无车牌" />
        </el-form-item>
        <el-form-item label="撞痕信息"
                      prop="hitMarkInfo">
          <el-input v-model="form.hitMarkInfo"
                    placeholder="请输入撞痕信息" />
        </el-form-item>
        <el-form-item label="信息分类"
                      prop="infoKind">
          <el-input v-model="form.infoKind"
                    placeholder="请输入信息分类" />
        </el-form-item>
        <el-form-item label="是否涂改"
                      prop="isAltered">
          <el-input v-model="form.isAltered"
                    placeholder="请输入是否涂改" />
        </el-form-item>
        <el-form-item label="是否遮挡"
                      prop="isCovered">
          <el-input v-model="form.isCovered"
                    placeholder="请输入是否遮挡" />
        </el-form-item>
        <el-form-item label="是否套牌"
                      prop="isDecked">
          <el-input v-model="form.isDecked"
                    placeholder="请输入是否套牌" />
        </el-form-item>
        <el-form-item label="改装标志"
                      prop="isModified">
          <el-input v-model="form.isModified"
                    placeholder="请输入改装标志" />
        </el-form-item>
        <el-form-item label="是否可疑车"
                      prop="isSuspicious">
          <el-input v-model="form.isSuspicious"
                    placeholder="请输入是否可疑车" />
        </el-form-item>
        <el-form-item label="车道号"
                      prop="laneNo">
          <el-input v-model="form.laneNo"
                    placeholder="请输入车道号" />
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
        <el-form-item label="位置标记时间-人工采集时有效"
                      prop="markTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.markTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择位置标记时间-人工采集时有效">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="经过道路名称"
                      prop="nameOfPassedRoad">
          <el-input v-model="form.nameOfPassedRoad"
                    placeholder="请输入经过道路名称" />
        </el-form-item>
        <el-form-item label="车内人数"
                      prop="numOfPassenger">
          <el-input v-model="form.numOfPassenger"
                    placeholder="请输入车内人数" />
        </el-form-item>
        <el-form-item label="经过时刻"
                      prop="passTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.passTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择经过时刻">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="每位号牌号码可信度"
                      prop="plateCharReliabil">
          <el-input v-model="form.plateCharReliabil"
                    placeholder="请输入每位号牌号码可信度" />
        </el-form-item>
        <el-form-item label="号牌种类"
                      prop="plateClass">
          <el-input v-model="form.plateClass"
                    placeholder="请输入号牌种类" />
        </el-form-item>
        <el-form-item label="车牌颜色"
                      prop="plateColor">
          <el-input v-model="form.plateColor"
                    placeholder="请输入车牌颜色" />
        </el-form-item>
        <el-form-item label="车牌描述"
                      prop="plateDescribe">
          <el-input v-model="form.plateDescribe"
                    placeholder="请输入车牌描述" />
        </el-form-item>
        <el-form-item label="车牌号"
                      prop="plateNo">
          <el-input v-model="form.plateNo"
                    placeholder="请输入车牌号" />
        </el-form-item>
        <el-form-item label="挂车牌号"
                      prop="plateNoAttach">
          <el-input v-model="form.plateNoAttach"
                    placeholder="请输入挂车牌号" />
        </el-form-item>
        <el-form-item label="号牌识别可信度"
                      prop="plateReliability">
          <el-input v-model="form.plateReliability"
                    placeholder="请输入号牌识别可信度" />
        </el-form-item>
        <el-form-item label="后视镜"
                      prop="rearviewMirror">
          <el-input v-model="form.rearviewMirror"
                    placeholder="请输入后视镜" />
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
        <el-form-item label="安全带状态"
                      prop="safetyBelt">
          <el-input v-model="form.safetyBelt"
                    placeholder="请输入安全带状态" />
        </el-form-item>
        <el-form-item label="车侧"
                      prop="sideOfVehicle">
          <el-input v-model="form.sideOfVehicle"
                    placeholder="请输入车侧" />
        </el-form-item>
        <el-form-item label="来源标识"
                      prop="sourceId">
          <el-input v-model="form.sourceId"
                    placeholder="请输入来源标识" />
        </el-form-item>
        <el-form-item label="行驶速度"
                      prop="speed">
          <el-input v-model="form.speed"
                    placeholder="请输入行驶速度" />
        </el-form-item>
        <el-form-item label="近景照片"
                      prop="storageUrl1">
          <el-input v-model="form.storageUrl1"
                    placeholder="请输入近景照片" />
        </el-form-item>
        <el-form-item label="车牌照片"
                      prop="storageUrl2">
          <el-input v-model="form.storageUrl2"
                    placeholder="请输入车牌照片" />
        </el-form-item>
        <el-form-item label="远景照片"
                      prop="storageUrl3">
          <el-input v-model="form.storageUrl3"
                    placeholder="请输入远景照片" />
        </el-form-item>
        <el-form-item label="合成图"
                      prop="storageUrl4">
          <el-input v-model="form.storageUrl4"
                    placeholder="请输入合成图" />
        </el-form-item>
        <el-form-item label="缩略图"
                      prop="storageUrl5">
          <el-input v-model="form.storageUrl5"
                    placeholder="请输入缩略图" />
        </el-form-item>
        <el-form-item label="遮阳板状态"
                      prop="sunvisor">
          <el-input v-model="form.sunvisor"
                    placeholder="请输入遮阳板状态" />
        </el-form-item>
        <el-form-item label="关联卡口编号"
                      prop="tollgateId">
          <el-input v-model="form.tollgateId"
                    placeholder="请输入关联卡口编号" />
        </el-form-item>
        <el-form-item label="车辆使用性质代码"
                      prop="usingPropertiesCode">
          <el-input v-model="form.usingPropertiesCode"
                    placeholder="请输入车辆使用性质代码" />
        </el-form-item>
        <el-form-item label="车身描述"
                      prop="vehicleBodyDesc">
          <el-input v-model="form.vehicleBodyDesc"
                    placeholder="请输入车身描述" />
        </el-form-item>
        <el-form-item label="车辆品牌"
                      prop="vehicleBrand">
          <el-input v-model="form.vehicleBrand"
                    placeholder="请输入车辆品牌" />
        </el-form-item>
        <el-form-item label="底盘"
                      prop="vehicleChassis">
          <el-input v-model="form.vehicleChassis"
                    placeholder="请输入底盘" />
        </el-form-item>
        <el-form-item label="车辆类型"
                      prop="vehicleClass">
          <el-input v-model="form.vehicleClass"
                    placeholder="请输入车辆类型" />
        </el-form-item>
        <el-form-item label="车身颜色"
                      prop="vehicleColor">
          <el-input v-model="form.vehicleColor"
                    placeholder="请输入车身颜色" />
        </el-form-item>
        <el-form-item label="颜色深浅"
                      prop="vehicleColorDepth">
          <el-input v-model="form.vehicleColorDepth"
                    placeholder="请输入颜色深浅" />
        </el-form-item>
        <el-form-item label="车门"
                      prop="vehicleDoor">
          <el-input v-model="form.vehicleDoor"
                    placeholder="请输入车门" />
        </el-form-item>
        <el-form-item label="车前部物品"
                      prop="vehicleFrontItem">
          <el-input v-model="form.vehicleFrontItem"
                    placeholder="请输入车前部物品" />
        </el-form-item>
        <el-form-item label="车辆高度"
                      prop="vehicleHeight">
          <el-input v-model="form.vehicleHeight"
                    placeholder="请输入车辆高度" />
        </el-form-item>
        <el-form-item label="车前盖"
                      prop="vehicleHood">
          <el-input v-model="form.vehicleHood"
                    placeholder="请输入车前盖" />
        </el-form-item>
        <el-form-item label="车辆长度"
                      prop="vehicleLength">
          <el-input v-model="form.vehicleLength"
                    placeholder="请输入车辆长度" />
        </el-form-item>
        <el-form-item label="车辆型号"
                      prop="vehicleModel">
          <el-input v-model="form.vehicleModel"
                    placeholder="请输入车辆型号" />
        </el-form-item>
        <el-form-item label="车后部物品"
                      prop="vehicleRearItem">
          <el-input v-model="form.vehicleRearItem"
                    placeholder="请输入车后部物品" />
        </el-form-item>
        <el-form-item label="车顶"
                      prop="vehicleRoof">
          <el-input v-model="form.vehicleRoof"
                    placeholder="请输入车顶" />
        </el-form-item>
        <el-form-item label="遮挡"
                      prop="vehicleShielding">
          <el-input v-model="form.vehicleShielding"
                    placeholder="请输入遮挡" />
        </el-form-item>
        <el-form-item label="车辆年款"
                      prop="vehicleStyles">
          <el-input v-model="form.vehicleStyles"
                    placeholder="请输入车辆年款" />
        </el-form-item>
        <el-form-item label="车后盖"
                      prop="vehicleTrunk">
          <el-input v-model="form.vehicleTrunk"
                    placeholder="请输入车后盖" />
        </el-form-item>
        <el-form-item label="车轮"
                      prop="vehicleWheel">
          <el-input v-model="form.vehicleWheel"
                    placeholder="请输入车轮" />
        </el-form-item>
        <el-form-item label="车辆宽度"
                      prop="vehicleWidth">
          <el-input v-model="form.vehicleWidth"
                    placeholder="请输入车辆宽度" />
        </el-form-item>
        <el-form-item label="车窗"
                      prop="vehicleWindow">
          <el-input v-model="form.vehicleWindow"
                    placeholder="请输入车窗" />
        </el-form-item>
        <el-form-item label="车轮印花纹"
                      prop="wheelPrintedPattern">
          <el-input v-model="form.wheelPrintedPattern"
                    placeholder="请输入车轮印花纹" />
        </el-form-item>
        <el-form-item label="对应的在车辆表里的车辆ID"
                      prop="vehicleId">
          <el-input v-model="form.vehicleId"
                    placeholder="请输入对应的在车辆表里的车辆ID" />
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
  listPass_vehicle,
  getPass_vehicle,
  delPass_vehicle,
  addPass_vehicle,
  updatePass_vehicle,
  exportPass_vehicle,
} from "@/api/passManage/pass_vehicle";
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import gisView from '@/views/GISView/GISView'
export default {
  name: "Pass_vehicle",
  components: { gisView },
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
      // 车辆通行表格数据
      pass_vehicleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      gisOpen: false,
      passLocation_: null,
      // 社区
      communityOptions: [],
      //小区列表
      communityChildOptions: [],
      //车辆类型表列表
      sourceTableNameOptions: [],
      //删除提示
      deleteNotices: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        appearTime: null,
        brandReliability: null,
        calling: null,
        carOfVehicle: null,
        descOfFrontItem: null,
        descOfRearItem: null,
        deviceId: null,
        direction: null,
        disappearTime: null,
        drivingStatusCode: null,
        filmColor: null,
        hasPlate: null,
        hitMarkInfo: null,
        infoKind: null,
        isAltered: null,
        isCovered: null,
        isDecked: null,
        isModified: null,
        isSuspicious: null,
        laneNo: null,
        leftTopX: null,
        leftTopY: null,
        markTime: null,
        nameOfPassedRoad: null,
        numOfPassenger: null,
        passTime: null,
        plateCharReliabil: null,
        plateClass: null,
        plateColor: null,
        plateDescribe: null,
        plateNo: null,
        plateNoAttach: null,
        plateReliability: null,
        rearviewMirror: null,
        rightBtmX: null,
        rightBtmY: null,
        safetyBelt: null,
        sideOfVehicle: null,
        sourceId: null,
        speed: null,
        storageUrl1: null,
        storageUrl2: null,
        storageUrl3: null,
        storageUrl4: null,
        storageUrl5: null,
        sunvisor: null,
        tollgateId: null,
        usingPropertiesCode: null,
        vehicleBodyDesc: null,
        vehicleBrand: null,
        vehicleChassis: null,
        vehicleClass: null,
        vehicleColor: null,
        vehicleColorDepth: null,
        vehicleDoor: null,
        vehicleFrontItem: null,
        vehicleHeight: null,
        vehicleHood: null,
        vehicleLength: null,
        vehicleModel: null,
        vehicleRearItem: null,
        vehicleRoof: null,
        vehicleShielding: null,
        vehicleStyles: null,
        vehicleTrunk: null,
        vehicleWheel: null,
        vehicleWidth: null,
        vehicleWindow: null,
        wheelPrintedPattern: null,
        vehicleId: null,
        sourceTableName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        plateNo: [
          { required: true, message: "车牌号不能为空", trigger: "blur" },
          {
            pattern: /(^[\u4E00-\u9FA5]{1}[A-Z0-9]{6}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{7}$)|(^[A-Z]{2}[A-Z0-9]{2}[A-Z0-9\u4E00-\u9FA5]{1}[A-Z0-9]{4}$)|(^[A-Z]{2}[A-Z0-9]{2}[A-Z0-9\u4E00-\u9FA5]{1}[A-Z0-9]{5}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{5}[挂学警军港澳]{1}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{6}[挂学警军港澳]{1}$)|(^[A-Z]{2}[0-9]{5}$)|(^[A-Z]{2}[0-9]{6}$)|(^(08|38){1}[A-Z0-9]{4}[A-Z0-9挂学警军港澳]{1}$)|(^(08|38){1}[A-Z0-9]{5}[A-Z0-9挂学警军港澳]{1}$)/,
            message: "常规格式：京A12345,新能源格式: 京A123456",
          },
        ],
        vehicleId: [
          {
            required: true,
            message: "对应的在车辆表里的车辆ID不能为空",
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
    this.sourceTableNameOptions = [
      {
        dictValue: "base_personnel_information",
        dictLabel: "住户",
      },
      {
        dictValue: "base_visitor_personnel_information",
        dictLabel: "预约访客",
      },
      {
        dictValue: "base_temp_visitor_personnel_information",
        dictLabel: "临时访客",
      },
    ];
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
    closeDialog (done) {
      console.log('dialog close ..');
      // this.passTrack = [];
      this.passLocation_ = null;
      this.gisOpen = false;
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
    /** 查询车辆通行列表 */
    getList () {
      this.loading = true;
      listPass_vehicle(this.queryParams).then((response) => {
        this.pass_vehicleList = response.rows;
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
        motorVehicleId: null,
        appearTime: null,
        brandReliability: null,
        calling: null,
        carOfVehicle: null,
        descOfFrontItem: null,
        descOfRearItem: null,
        deviceId: null,
        direction: null,
        disappearTime: null,
        drivingStatusCode: null,
        filmColor: null,
        hasPlate: null,
        hitMarkInfo: null,
        infoKind: null,
        isAltered: null,
        isCovered: null,
        isDecked: null,
        isModified: null,
        isSuspicious: null,
        laneNo: null,
        leftTopX: null,
        leftTopY: null,
        markTime: null,
        nameOfPassedRoad: null,
        numOfPassenger: null,
        passTime: null,
        plateCharReliabil: null,
        plateClass: null,
        plateColor: null,
        plateDescribe: null,
        plateNo: null,
        plateNoAttach: null,
        plateReliability: null,
        rearviewMirror: null,
        rightBtmX: null,
        rightBtmY: null,
        safetyBelt: null,
        sideOfVehicle: null,
        sourceId: null,
        speed: null,
        storageUrl1: null,
        storageUrl2: null,
        storageUrl3: null,
        storageUrl4: null,
        storageUrl5: null,
        sunvisor: null,
        tollgateId: null,
        usingPropertiesCode: null,
        vehicleBodyDesc: null,
        vehicleBrand: null,
        vehicleChassis: null,
        vehicleClass: null,
        vehicleColor: null,
        vehicleColorDepth: null,
        vehicleDoor: null,
        vehicleFrontItem: null,
        vehicleHeight: null,
        vehicleHood: null,
        vehicleLength: null,
        vehicleModel: null,
        vehicleRearItem: null,
        vehicleRoof: null,
        vehicleShielding: null,
        vehicleStyles: null,
        vehicleTrunk: null,
        vehicleWheel: null,
        vehicleWidth: null,
        vehicleWindow: null,
        wheelPrintedPattern: null,
        vehicleId: null,
      };
      this.resetForm("form");
    },
    //通行数据定位
    passLocation (row) {
      let passLoacation = { //通行记录定位
        cameraSn: row.deviceId, //设备编号
        passType: 2, //通行类型 1、人 2、车
        passTime: row.locationMarkTime, //抓拍时间
        passImage: row.storageUrl3, //抓拍图片
        passName: row.plateNo, //车牌号或者人的姓名
        passAddress: '', //抓拍地点
      };
      this.passLocation_ = passLoacation;
      this.gisOpen = true
      console.log('location', passLoacation);
      // this.$Notice.$emit('passLoacation', passLoacation);
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
      this.deleteNotices = selection.map((item) => item.plateNo);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset();
      this.open = true;
      this.title = "添加车辆通行";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const id = row.id || this.ids;
      getPass_vehicle(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车辆通行";
      });
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.motorVehicleId != null) {
            updatePass_vehicle(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addPass_vehicle(this.form).then((response) => {
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
      const motorVehicleIds = row.id || this.ids;
      let deleteNotice = row.plateNo || this.deleteNotices;
      this.$confirm(
        '是否确认删除车辆车牌号为"' + deleteNotice + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delPass_vehicle(motorVehicleIds);
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
      this.$confirm("是否确认导出所有车辆通行数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportPass_vehicle(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () { });
    },
  },
};
</script>
