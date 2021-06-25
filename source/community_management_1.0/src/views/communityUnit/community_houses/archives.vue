<template>
  <div class="archivesMain divStackPanel_HorizontalLeft">
    <!-- 左侧搜索列表 -->
    <div class="searchDiv">
      <el-form :model="queryParams"
               ref="queryForm"
               v-show="showSearch"
               :inline="true"
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
        <el-form-item label="楼栋名称"
                      prop="buildingName">
          <el-input v-model="queryParams.buildingName"
                    placeholder="请输入楼栋名称"
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
        </el-form-item>
      </el-form>
      <el-row :gutter="10"
              class="mb8">
        <el-col :span="1.5"
                v-show="showSearch">
          <el-button type="cyan"
                     icon="el-icon-search"
                     size="mini"
                     @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh"
                     size="mini"
                     @click="resetQuery">重置</el-button>
        </el-col>

        <right-toolbar :showSearch.sync="showSearch"
                       @queryTable="getList"></right-toolbar>
      </el-row>
      <el-table v-loading="loading"
                stripe
                :show-header="false"
                :data="community_housesList"
                :height="dataHeight"
                @row-click="rowClick">
        <el-table-column label="简要信息"
                         fit
                         align="center"
                         class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-row class="textLeft HorizontalMargin">
              <span>{{communityNameFormat(scope.row.communityId)}}</span>
              <span>{{communityChildNameFormat(scope.row.communityChildId)}}</span>
              <span>{{houseAddressFormat(scope.row )}}</span>
            </el-row>
          </template>
        </el-table-column>
      </el-table>
      <pagination v-show="total>0"
                  :layout="'prev, pager, next'"
                  :total="total"
                  :page.sync="queryParams.pageNum"
                  :limit.sync="queryParams.pageSize"
                  @pagination="getList" />
    </div>
    <!-- 中间的关联数据展示 -->
    <div class="showRelation">
      <el-scrollbar style="height:100%"
                    wrap-class="scrollbar-wrapper "
                    wrap-style="overflow-x:hidden;">
        <el-card class="box-card"
                 :body-style="{ padding: '4px 20px'}">
          <div slot="header"
               class="clearfix">
            <span class="textLeft commonTitleText">关联人员</span>
            <!-- <el-button style="float: right; padding: 3px 0"
                     type="text">操作按钮</el-button> -->
          </div>
          <el-table v-loading="loadingVoPersonList"
                    stripe
                    :show-header="false"
                    :data="personInformationVoList">
            <el-table-column align="center">
              <template slot-scope="scope">
                <span>
                  {{ scope.row.name }}
                  {{ scope.row.sex | sexFilters(that) }}
                  {{ scope.row.phoneNumber }}
                  {{ scope.row.identificationNumber }}
                </span>
              </template>
            </el-table-column>
            <el-table-column label="操作"
                             width="120"
                             align="center"
                             class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button size="mini"
                           type="text"
                           icon="el-icon-position"
                           @click="handlePersonPassRecord(scope.row)">通行记录</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
        <el-card class="box-card"
                 :body-style="{ padding: '4px 20px'}">
          <div slot="header"
               class="clearfix">
            <span class="textLeft commonTitleText">关联车辆</span>
            <!-- <el-button style="float: right; padding: 3px 0"
                     type="text">操作按钮</el-button> -->
          </div>
          <!-- <el-scrollbar style="height:86px"
                      wrap-class="scrollbar-wrapper "
                      wrap-style="overflow-x:hidden;"> -->
          <!-- <div v-for="(item ,index) in vehicleInformationVoList"
               :key="index"
               class="textLeft text item commonTextBlock">
            {{(index+1)+ ',' }}
            {{item.plate}}
            {{modelFormat(item.model)}}
            {{item.brand}}
            {{item.specificBrand}}
            {{item.numberOfSeats?(item.numberOfSeats+'座'):''}}
            {{item.parkingSpaceNumber?(' 车库'+item.parkingSpaceNumber):''}}
          </div> -->
          <el-table v-loading="loading"
                    stripe
                    :show-header="false"
                    :data="vehicleInformationVoList">
            <el-table-column align="center">
              <template slot-scope="scope">
                {{ scope.row.plate }}
                {{ scope.row.model | vehicleModelFilters(that) }}
                {{ scope.row.brand }}
                {{ scope.row.specificBrand }}
                {{ scope.row.numberOfSeats?(scope.row.numberOfSeats+'座'):'' }}
                {{ scope.row.parkingSpaceNumber?(' 车库'+scope.row.parkingSpaceNumber):'' }}
              </template>
            </el-table-column>
            <el-table-column label="操作"
                             width="120"
                             align="center"
                             class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button size="mini"
                           type="text"
                           icon="el-icon-position"
                           @click="handleVehiclePassRecord(scope.row)">通行记录</el-button>
              </template>
            </el-table-column>
          </el-table>
          <!-- </el-scrollbar> -->
        </el-card>
      </el-scrollbar>
    </div>
    <!-- 右侧的关系图标 -->
    <div class="chartRelation">
      <PHVRelationChart v-show="!passRecordShow"
                        :data="pieData"
                        :width="'100%'"
                        :height="'100%'" />
      <div class="passRecordContainer"
           v-show="passRecordShow">
        <div class="divStackPanel_left ">
          <el-button class="VerticalMargin"
                     @click="closePassRecord">关 闭</el-button>
        </div>
        <el-table stripe
                  :data="passRecordList"
                  :height="dataHeight">
          <el-table-column type="selection"
                           width="55"
                           align="center" />
          <el-table-column label="社区名称"
                           align="center"
                           prop="communityName" />
          <el-table-column label="小区名称"
                           align="center"
                           prop="communityChildName" />
          <el-table-column label="设备编码"
                           align="center"
                           prop="deviceId" />
          <el-table-column label="时间"
                           align="center"
                           prop="locationMarkTime"
                           width="180">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.locationMarkTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="姓名"
                           v-if="vo_queryParams.searchType === 'person'"
                           align="center"
                           prop="name" />
          <el-table-column label="性别"
                           v-if="vo_queryParams.searchType === 'person'"
                           align="center"
                           prop="genderCode"
                           :formatter="sexFormat" />
          <el-table-column label="证件号码"
                           v-if="vo_queryParams.searchType === 'person'"
                           align="center"
                           prop="idnumber" />
          <el-table-column label="车牌号"
                           v-if="vo_queryParams.searchType === 'vehicle'"
                           align="center"
                           width="220"
                           prop="plateNo" />
          <el-table-column label="近景照片"
                           v-if="vo_queryParams.searchType === 'vehicle'"
                           align="center"
                           width="220"
                           prop="storageUrl1" />s
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
          <!-- <el-table-column label="操作"
                       align="center"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
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
      </el-table-column> -->
        </el-table>
        <pagination v-show="vo_total > 0"
                    :total="vo_total"
                    :page.sync="vo_queryParams.pageNum"
                    :limit.sync="vo_queryParams.pageSize"
                    @pagination="searchPassRecode" />
      </div>
    </div>
  </div>
</template>
<script>
import { listCommunity_houses, getCommunity_houses, delCommunity_houses, addCommunity_houses, updateCommunity_houses, exportCommunity_houses } from "@/api/communityUnit/community_houses";
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_building } from "@/api/communityUnit/community_building";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import { selectPerInformByHousesId } from "@/api/communityUnit/personnel_information";
import { listVehicle_information, getVehicle_information, selectVehicleInformationVoList } from "@/api/communityUnit/vehicle_information";
import PHVRelationChart from '../../../components/dashboard/P_H_V_RelationChart.vue'
import {
  listPass_personnel,
  getPass_personnel,
  exportPass_personnel,
} from "@/api/passManage/pass_personnel";
import {
  listPass_vehicle,
  getPass_vehicle,
  exportPass_vehicle,
} from "@/api/passManage/pass_vehicle";
//import cameraTreeLazy from '@/components/cameraTree/cameraTree_lazy'
export default {
  components: { PHVRelationChart },
  //props: {dataInfo: {type: Object,default: null}},
  data () {
    return {
      that: this,
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 性别字典
      sexOptions: [],
      // 车型字典
      modelOptions: [],
      // 证件类型代码字典
      idTypeOptions: [],
      //厅室
      hallRoomOptions: [],
      //使用性质
      usePropertyOptions: [],
      //社区列表
      communityOptions: [],
      //小区列表
      communityChildOptions: [],
      //小区列表全集
      allCommunityChildOptions: [],
      //社区楼栋列表
      communityBuildingOptions: [],
      // 居住类型字典
      typeOfResidenceOptions: [],
      // 车辆使用类型字典
      vehicleUseOptions: [],
      // 是否是房主,(0否1是,默认0)字典
      homeownerOptions: [],
      // 住户与房主关系字典
      relationshipWithHomeownerOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        delFlag: '0',
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
      },
      // 总条数
      total: 0,
      // 社区房屋信息表格数据
      community_housesList: [],
      // 表单展开层 关联车辆
      vehicleInformationVoList: [],
      // 表单展开层 关联房屋
      houseInformationVoList: [],
      // 表单展开层 关联人员
      personInformationVoList: [],
      //当前选中的行
      selectionHouse: null,
      //关联通行记录查询参数
      vo_queryParams: {
        searchType: 'person',//人员 person 车辆 vehicle
        pageNum: 1,
        pageSize: 10,
        communityId: null,
        communityChildId: null,
        plate: null,
        personnelId: null,
        identificationNumber: null,
        startTime: null,
        endTime: null
      },
      vo_total: 0,
      //通行记录是否显示
      passRecordShow: false,
      //通行记录列表
      passRecordList: [],
      // 图表数据
      pieData: {
        nodes: [{
        }],
        links: [{
        }]
      },

      // 搜索表单控件高度
      formHeight: 0
    }
  },
  created () {
    this.getAllCommunityChildDictory();
    this.getCommunityDictory()
    this.getDicts("sys_user_sex").then(response => {
      this.sexOptions = response.data;
    });
    this.getDicts("base_vehicle_use").then(response => {
      this.vehicleUseOptions = response.data;
    })
    this.getDicts("base_idCard").then(response => {
      this.idTypeOptions = response.data;
    });
    this.getDicts("base_vehicle_model").then(response => {
      this.modelOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.baseYesNoOptions = response.data;
    });
    this.getDicts("base_use_property").then(response => {
      this.usePropertyOptions = response.data;
    });
    this.getDicts("base_hall_room").then(response => {
      this.hallRoomOptions = response.data;
    });
    this.getDicts("base_type_of_residence").then(response => {
      this.typeOfResidenceOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.homeownerOptions = response.data;
    });
    this.getDicts("base_relationship_resident_homeowner").then(response => {
      this.relationshipWithHomeownerOptions = response.data;
    });

  },
  mounted () {
    this.getList();
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight
    })
  },
  filters: {
    communityNameFilters (value, self) {
      if (self.communityOptions && self.communityOptions.length > 0) {
        let rows = self.communityOptions.filter(item => {
          return item.id === value
        })
        if (rows && rows.length > 0) {
          return rows[0].name
        }
      }
      return '??'
    },
    communityChildNameFilters (value, self) {
      if (self.allCommunityChildOptions && self.allCommunityChildOptions.length > 0) {
        let rows = self.allCommunityChildOptions.filter(item => {
          return item.id === value
        })
        if (rows && rows.length > 0) {
          return rows[0].name
        }
      }
      return '??'
    },
    sexFilters (value, self) {
      return self.sexFormat(null, null, value)
    },
    vehicleModelFilters (value, self) {
      return self.modelFormat(value)
    },
  },
  methods: {
    // 性别字典翻译
    sexFormat (sex) {
      return this.selectDictLabel(this.sexOptions, sex);
    },
    // 证件类型代码字典翻译
    idTypeFormat (idType) {
      return this.selectDictLabel(this.idTypeOptions, idType);
    },
    // 居住类型字典翻译
    typeOfResidenceFormat (value) {
      return this.selectDictLabel(this.typeOfResidenceOptions, value);
    },
    // 车型字典翻译
    modelFormat (model) {
      return this.selectDictLabel(this.modelOptions, model);
    },
    vehicleUseFormat (value) {
      return this.selectDictLabel(this.vehicleUseOptions, value)
    },
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
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId
      }
      listCommunity_child(param).then(response => {
        this.communityChildOptions = response.rows;
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
    getVoHouseName (houseId) {
      if (this.selectionHouse && this.selectionHouse.id && this.selectionHouse.id.length > 0) {
        return this.selectionHouse.serialNumber + '号楼' + this.selectionHouse.unit + '单元' + this.selectionHouse.houseNumber
      }
      else return ''
    },
    rowClick (selectionHouse) {
      this.selectionHouse = selectionHouse
      this.personInformationVoList = []
      this.vehicleInformationVoList = []
      this.pieData = {
        nodes: [],
        links: []
      }
      //将当前房屋加入到节点
      let node = {
        name: this.getHouseName(selectionHouse),
        category: 1
      }
      this.pieData.nodes.push(node)
      this.getPersonVoList(selectionHouse)
      // this.getVehicleVoListByHouse(selectionHouse)
    },
    getPersonVoList (houseInfo) {
      let houseId = houseInfo.id
      let queryDate = {
        houseId: houseId,
      }
      this.loadingVoPersonList = true
      let nodes = []
      let links = []
      selectPerInformByHousesId(queryDate).then(response => {
        if (response && response.rows && response.rows.length > 0)
          response.rows = this.unique2(response.rows, 'name', 'identificationNumber')
        this.personInformationVoList = response.rows;
        this.loadingVoPersonList = false
        this.personInformationVoList.forEach(item => {
          let node = {
            name: item.name,
            category: 0
          }
          nodes.push(node)
          let link = {
            source: item.name,
            target: houseInfo.serialNumber + '号楼' + houseInfo.unit + '单元' + houseInfo.houseNumber,
            name: this.typeOfResidenceFormat(item.relationship_with_homeowner)
          }
          links.push(link)
          this.getVehicleVoListByPerson(item)
        })
        this.pieData.nodes = this.pieData.nodes.concat(nodes)
        this.pieData.links = this.pieData.links.concat(links)
      });
    },
    getVehicleVoListByHouse (row) {
      let houseId = row.id
      let param = {
        communityHousesId: houseId,
        // personnelId:
        isDelFlag: '0'
      }
      selectVehicleInformationVoList(param).then(response => {
        this.vehicleInformationVoList = response.rows;
        this.vehicleInformationVoList.forEach(item => {
          let node = {
            name: item.plate,
            category: 2
          }
          this.pieData.nodes.push(node)
          let link = {
            source: item.plate,
            target: this.getHouseName(row),
            name: this.vehicleUseFormat(item.typeOfResidence)
            // name: '属于'
          }
          console.log(link.source + '=>' + link.target + ' :' + link.name)
          this.pieData.links.push(link)
        })
      });
    },
    getVehicleVoListByPerson (row) {
      let personnelId = row.id
      let param = {
        // communityHousesId: houseId,
        personnelId: personnelId,
        isDelFlag: '0'
      }
      selectVehicleInformationVoList(param).then(response => {
        if (!(response.rows && response.rows.length > 0)) return false
        let rows = response.rows
        this.vehicleInformationVoList = this.vehicleInformationVoList.concat(rows)
        let nodes = []
        let links = []
        rows.forEach(item => {
          let node = {
            name: item.plate,
            category: 2
          }
          nodes.push(node)
          let link = {
            source: row.name,
            target: item.plate,
            name: this.vehicleUseFormat(item.typeOfResidence)
          }
          links.push(link)
          let linkToHouse = {
            source: item.plate,
            target: this.getVoHouseName(),
            name: this.vehicleUseFormat(item.typeOfResidence)
          }
          links.push(linkToHouse)
        })
        this.pieData.nodes = this.pieData.nodes.concat(nodes)
        this.pieData.links = this.pieData.links.concat(links)
      });
    },
    /** 查询社区房屋信息列表 */
    getList () {
      this.loading = true;
      listCommunity_houses(this.queryParams).then(response => {
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
        delFlag: null,
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
        houseStructure: '0',
        area: null,
        constructionTime: null,
        housePropertyRight: '70',
        natureOfUse: '0',
        rent: '0',
        rentalPurpose: null,
        hazardType: '00',
        longitud: null,
        latitude: null,
        pics: null,
        elevator: '1'
      };
      this.resetForm("form");
      if (this.queryParams && this.queryParams.communityId && this.queryParams.communityId.length > 0) {
        this.form.communityId = this.queryParams.communityId
      }
      if (this.queryParams && this.queryParams.communityChildId && this.queryParams.communityChildId.length > 0) {
        this.form.communityChildId = this.queryParams.communityChildId
      }
      if (this.queryParams && this.queryParams.communityBuildingId && this.queryParams.communityBuildingId.length > 0) {
        this.form.communityBuildingId = this.queryParams.communityBuildingId
      }
      if (this.queryParams && this.queryParams.serialNumber && this.queryParams.serialNumber.length > 0) {
        this.form.serialNumber = this.queryParams.serialNumber
      }
      if (this.queryParams && this.queryParams.buildingName && this.queryParams.buildingName.length > 0) {
        this.form.buildingName = this.queryParams.buildingName
      }
      if (this.queryParams && this.queryParams.communityName && this.queryParams.communityName.length > 0) {
        this.form.communityName = this.queryParams.communityName
      }
      if (this.queryParams && this.queryParams.elevator && this.queryParams.elevator.length > 0) {
        this.form.elevator = this.queryParams.elevator
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
    closePassRecord () {
      this.passRecordShow = false
    },
    ///查找记录
    searchPassRecode () {
      // searchType:'person',//人员 person 车辆 vehicle
      if (this.vo_queryParams.searchType === 'person') {
        listPass_personnel(this.vo_queryParams).then((response) => {
          this.passRecordList = response.rows;
          this.vo_total = response.total;
        });
      } else if (this.vo_queryParams.searchType === 'vehicle') {
        listPass_vehicle(this.vo_queryParams).then((response) => {
          this.passRecordList = response.rows;
          this.vo_total = response.total;
        });
      }
    },
    handlePersonPassRecord (row) {
      //获取通行记录
      this.vo_queryParams.searchType = 'person'
      this.vo_queryParams.pageNum = 1
      // this.vo_queryParams.communityId = row.communityId
      // this.vo_queryParams.communityChildId = row.communityChildId
      // this.vo_queryParams.personnelId = row.id
      this.vo_queryParams.idnumber = row.identificationNumber
      this.vo_queryParams.startTime = null
      this.vo_queryParams.endTime = null
      this.searchPassRecode()
      //显示在界面上
      this.passRecordShow = true
    },
    handleVehiclePassRecord (row) {
      this.vo_queryParams.searchType = 'vehicle'
      this.vo_queryParams.pageNum = 1
      // this.vo_queryParams.communityId = row.communityId
      // this.vo_queryParams.communityChildId = row.communityChildId
      this.vo_queryParams.plateNo = row.plate
      this.vo_queryParams.startTime = null
      this.vo_queryParams.endTime = null
      this.searchPassRecode()
      //显示在界面上
      this.passRecordShow = true
    }
  },
  computed: {
    dataHeight () {
      let searchFormHeight = 0
      if (this.showSearch) searchFormHeight = this.formHeight
      return this.$store.getters.dataEleHeight - searchFormHeight - 115
    }
  },
  watch: {
    //dataInfo: {
    //handler: function (newValue) {
    //console.log('new value change')
    //},
    //deep: true,
    //immediate: true
    //}
  },
  beforeDestroy () { }
}
</script>
<style lang="scss" scoped>
.archivesMain {
  width: 100%;
  height: 100%;
  overflow-x: auto;
  overflow-y: auto;
}
.box-card {
  width: 480px;
  height: auto;
  margin-bottom: 4px;
  min-height: 120px;
}

.searchDiv {
  // background: aquamarine;
  padding: 20px 20px;
  width: 440px;
  height: 100%;
}
.showRelation {
  // background: blanchedalmond;
  width: 500px;
  height: 100%;
}
.chartRelation {
  // background: cadetblue;
  width: calc(100% - 440px - 500px - 1px);
  height: 100%;
}
.pagination-container {
  height: 0px;
  padding: 0px 20px !important;
}
.passRecordContainer {
  width: 100%;
  height: 100%;
}
</style>