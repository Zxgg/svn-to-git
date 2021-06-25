<template>
  <!-- <div class="app-container"> -->
  <el-container class="app-container">
    <el-aside width="20%">
      <el-form v-show="showSearch"
               ref="queryForm"
               :model="queryParams"
               size="mini"
               :inline="true"
               label-width="40px">
        <el-form-item label="社区"
                      prop="communityId">
          <el-select v-model="queryParams.communityId"
                     filterable
                     clearable
                     placeholder="请选择社区"
                     @change="getCommunityChildDictory">
            <el-option v-for="dict in communityOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
          <el-button v-hasPermi="['communityUnit:community:query']"
                     size="mini"
                     type="text"
                     icon="el-icon-refresh"
                     @click="getCommunityDictory">刷新</el-button>
        </el-form-item>
        <el-form-item label="小区"
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
        <el-form-item label="车型"
                      prop="model">
          <el-select v-model="queryParams.model"
                     placeholder="请选择车型"
                     clearable
                     size="small">
            <el-option v-for="dict in modelOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="车牌"
                      prop="plate">
          <el-input v-model="queryParams.plate"
                    placeholder="请输入车牌号码"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery" />
        </el-form-item>
        <!-- <el-form-item label="品牌"
                      prop="brand">
          <el-input v-model="queryParams.brand"
                    placeholder="请输入车品牌"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery" />
        </el-form-item> -->
      </el-form>
      <el-row :gutter="10"
              class="mb8">
        <el-button type="cyan"
                   v-show="showSearch"
                   icon="el-icon-search"
                   size="mini"
                   @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh"
                   v-show="showSearch"
                   size="mini"
                   @click="resetQuery">重置</el-button>
        <right-toolbar :show-search.sync="showSearch"
                       @queryTable="getList" />
      </el-row>

      <el-table v-loading="loading"
                ref="multipleTable"
                stripe
                border
                :show-header="false"
                :data="vehicle_informationList"
                :height="dataHeight"
                @row-click="handleRowClickChange">
        <el-table-column align="left">
          <template slot-scope="scope">
            <span>
              {{ scope.row.plate }}
              {{ scope.row.model | vehicleModelFilters(that) }}
              {{ scope.row.brand }}
              {{ scope.row.specificBrand }}
              {{ scope.row.numberOfSeats?(scope.row.numberOfSeats+'座'):'' }}
              {{ scope.row.parkingSpaceNumber?(' 车库'+scope.row.parkingSpaceNumber):'' }}
            </span>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total>0"
                  small
                  layout="prev, pager, next"
                  :total="total"
                  :page.sync="queryParams.pageNum"
                  :limit.sync="queryParams.pageSize"
                  @pagination="getList" />
      </el-pagination>

    </el-aside>
    <el-container>
      <el-aside width='27%'>
        <el-scrollbar style="height:100%"
                      wrap-class="scrollbar-wrapper "
                      wrap-style="overflow-x:hidden;">
          <el-card>
            <span>关联房屋</span>
            <el-table v-loading="loading"
                      stripe
                      :show-header="false"
                      :data="houseInformationVoList">
              <el-table-column align="center">
                <template slot-scope="scope">
                  <span>
                    {{ scope.row.communityId | communityNameFilters(that) }}
                    {{ scope.row.communityChildId | communityChildNameFilters(that) }}
                    {{ scope.row.serialNumber + '号楼' + scope.row.unit + '单元' + scope.row.houseNumber }}
                  </span>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
          <el-card>
            <span>关联人员</span>
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
            </el-table>
          </el-card>
          <el-card>
            <span>关联车辆</span>
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

            </el-table>
          </el-card>
        </el-scrollbar>
      </el-aside>
      <el-container>
        <PHVRelationChart v-show="!passRecordShow"
                          :data="pieData" />
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
      </el-container>
    </el-container>
  </el-container>
  <!-- </div> -->
</template>

<script>
import PHVRelationChart from '../../../components/dashboard/P_H_V_RelationChart.vue'
import { listPersonnel_information, getPersonnel_information } from '@/api/communityUnit/personnel_information'
import communityHousesPersonnel from '../community_houses_personnel/index'
import { listVehicle_houses_personnel } from '@/api/communityUnit/vehicle_houses_personnel'
import { listCommunity } from '@/api/communityUnit/community'
import { listCommunity_child } from '@/api/communityUnit/community_child'
import { getInfoByName } from '@/api/communityUnit/personnel_information'
import { listVehicle_information, getVehicle_information, selectVehicleInformationVoList } from '@/api/communityUnit/vehicle_information'
import { listCommunity_houses, getCommunity_houses, queryList_houses } from '@/api/communityUnit/community_houses'
import { selectPerInformByHousesId } from '@/api/communityUnit/personnel_information'
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

export default {
  components: { PHVRelationChart },
  filters: {
    checkedFilters: function (value, self) {
      return self.checkedFormat(null, null, value)
    },
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
  // name: "Group_abroad",
  data () {
    return {
      that: this,
      // 遮罩层
      loading: true,
      loadingVoPersonList: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 是否已经审核确认,(0否1是,默认0)字典
      checkedOptions: [],
      // 人员信息表格数据
      personnel_informationList: [],
      // 弹出层标题
      title: '',
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
      // // 是否已婚字典
      // yesNoOptions: [],
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
      // 车型字典
      modelOptions: [],
      // 是否是特殊人员
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
      // 小区列表
      communityChildOptions: [],
      // 小区列表
      allCommunityChildOptions: [],
      // 居住类型字典
      typeOfResidenceOptions: [],
      // 车辆使用类型字典
      vehicleUseOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        delFlag: '0',
        communityId: null,
        communityChildId: null,
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
        brithDate: null
      },
      // 表单参数
      form: {},
      // 搜索表单控件高度
      formHeight: 0,
      // 表单展开层 关联车辆
      vehicleInformationVoList: [],
      // 表单展开层 关联房屋
      houseInformationVoList: [],
      // 表单展开层 关联人员
      personInformationVoList: [],
      // 表单展开层 关联车辆
      vehicle_informationList: [],
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
      }
    }
  },
  computed: {
    dataHeight () {
      let searchFormHeight = 0
      if (this.showSearch) searchFormHeight = this.formHeight
      return this.$store.getters.dataEleHeight - searchFormHeight - 155
    }
  },
  created () {
    this.getCommunityDictory()
    this.getAllCommunityChildDictory()
    this.getDicts('base_checked_stat').then(response => {
      this.checkedOptions = response.data
    })
    this.getDicts('sys_user_sex').then(response => {
      this.sexOptions = response.data
    })
    this.getDicts('base_nationality').then(response => {
      this.nationalityOptions = response.data
    })
    this.getDicts('base_idCard').then(response => {
      this.idTypeOptions = response.data
    })
    this.getDicts('base_nation').then(response => {
      this.nationOptions = response.data
    })
    this.getDicts('base_political').then(response => {
      this.politicalOptions = response.data
    })
    this.getDicts('base_degree').then(response => {
      this.degreeOptions = response.data
    })
    this.getDicts('base_religion').then(response => {
      this.religionOptions = response.data
    })
    this.getDicts('base_yes_no').then(response => {
      this.yesNoOptions = response.data
    })
    this.getDicts('base_special_person').then(response => {
      this.specialOptions = response.data
    })
    this.getDicts("base_vehicle_model").then(response => {
      this.modelOptions = response.data;
    })
    this.getDicts("base_type_of_residence").then(response => {
      this.typeOfResidenceOptions = response.data;
    })
    this.getDicts("base_vehicle_use").then(response => {
      this.vehicleUseOptions = response.data;
    })
  },
  mounted () {
    this.getList()
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight
    })
  },
  methods: {
    // 获取社区列表
    getCommunityDictory () {
      this.communityOptions = []
      const param = {
        pageNum: 1,
        pageSize: 100000
      }
      listCommunity(param).then(response => {
        this.communityOptions = response.rows
      })
    },
    // 获取小区列表
    getCommunityChildDictory (communityId) {
      this.communityChildOptions = []
      const param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId
      }
      listCommunity_child(param).then(response => {
        this.communityChildOptions = response.rows
      })
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
    /** 查询车辆信息列表 */
    getList () {
      this.loading = true;
      listVehicle_information(this.queryParams).then(response => {
        this.vehicle_informationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 车型字典翻译
    modelFormat (row, column) {
      return this.selectDictLabel(this.modelOptions, row.model);
    },
    // 是否已经审核确认,(0否1是,默认0)字典翻译
    checkedFormat (row, column, cellValue) {
      return this.selectDictLabel(this.checkedOptions, cellValue)
    },
    // 性别字典翻译
    sexFormat (row, column, cellValue) {
      return this.selectDictLabel(this.sexOptions, cellValue);
    },
    // 国籍字典翻译
    nationalityFormat (row, column) {
      return this.selectDictLabel(this.nationalityOptions, row.nationality)
    },
    // 证件类型代码字典翻译
    idTypeFormat (row, column) {
      return this.selectDictLabel(this.idTypeOptions, row.idType)
    },
    // 民族字典翻译
    nationFormat (row, column) {
      return this.selectDictLabel(this.nationOptions, row.nation)
    },
    // 政治面貌字典翻译
    politicalFormat (row, column) {
      return this.selectDictLabel(this.politicalOptions, row.political)
    },
    // 学历字典翻译
    degreeFormat (row, column) {
      return this.selectDictLabel(this.degreeOptions, row.degree)
    },
    // 宗教信仰字典翻译
    religionFormat (row, column) {
      return this.selectDictLabel(this.religionOptions, row.religion)
    },
    // 是否已婚字典翻译
    yesNoFormat (row, column, cellValue) {
      return this.selectDictLabel(this.yesNoOptions, row[column.property])
    },
    // 居住类型字典翻译
    typeOfResidenceFormat (value) {
      return this.selectDictLabel(this.typeOfResidenceOptions, value);
    },
    vehicleUseFormat (value) {
      return this.selectDictLabel(this.vehicleUseOptions, value)
    },
    // 取消按钮
    cancel () {
      this.open = false
      this.reset()
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
        name: null,
        oldName: null,
        sex: null,
        nationality: null,
        idType: null,
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
        brithDate: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery () {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery () {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    handleRowClickChange (row) {
      this.pieData.nodes = []
      this.pieData.links = []
      this.getRelationList(row)
      // this.getVehicleVoList(row)
    },
    getRelationList (row) {
      const param = {
        vehicleId: row.id
      }
      //获取所有关联的信息
      listVehicle_houses_personnel(param).then(response => {
        let listVehicleHousesPersonnel = response.rows
        listVehicleHousesPersonnel.forEach(item => {
          this.getHouseVoList(item.communityHousesId)

        })
      })
    },
    getHouseVoList (houseId) {
      const id = houseId
      let self = this
      self.houseInformationVoList = []
      getCommunity_houses(id).then(async response => {
        self.houseInformationVoList.push(response.data)
        let item = response.data;
        let houseName = item.serialNumber + '号楼' + item.unit + '单元' + item.houseNumber
        let node = {
          name: houseName,
          category: 1
        }
        self.pieData.nodes.push(node)
        let all = {
          nodes: [],
          links: []
        }
        let personNodes = await self.getPersonVoList(houseId, houseName)
        let vehicleNodes = await self.getVehicleVoList(houseId, houseName)
        if (personNodes.nodes && personNodes.nodes.length > 0) {
          all.nodes = all.nodes.concat(personNodes.nodes)
          all.links = all.links.concat(personNodes.links)
        }
        if (vehicleNodes.nodes && vehicleNodes.nodes.length > 0) {
          all.nodes = all.nodes.concat(vehicleNodes.nodes)
          all.links = all.links.concat(vehicleNodes.links)
        }
        all.nodes = this.unique(all.nodes, 'name')
        // all.links=unique(all.links ,'name')
        self.pieData.nodes = self.pieData.nodes.concat(all.nodes)
        self.pieData.links = self.pieData.links.concat(all.links)

      });
    },
    async getPersonVoList (houseId, houseName) {
      const queryDate = {
        houseId: houseId
      }
      let personNodes = {
        nodes: [],
        links: []
      }
      this.loadingVoPersonList = true
      await selectPerInformByHousesId(queryDate).then(async response => {
        // let resultList = this.unique2(response.rows, 'name', 'identificationNumber')
        if (response && response.rows && response.rows.length > 0)
          response.rows = this.unique2(response.rows, 'name', 'identificationNumber')
        this.personInformationVoList = response.rows
        this.loadingVoPersonList = false
        this.personInformationVoList.forEach(item => {
          let personName = item.name
          if (item.identificationNumber && item.identificationNumber.length > 4) personName += '(' + item.identificationNumber.substr(-4) + ')'
          let node = {
            name: personName,
            category: 0
          }
          personNodes.nodes.push(node)
          // this.pieData.nodes.push(node)
          let link = {
            source: personName,
            target: houseName,
            name: this.typeOfResidenceFormat(item.relationship_with_homeowner)
          }
          personNodes.links.push(link)
          // this.pieData.links.push(link)
        })
      })
      return personNodes
    },
    async getVehicleVoList (houseId, houseName) {
      const param = {
        communityHousesId: houseId
      }
      let vehicleNodes = {
        nodes: [],
        links: []
      }
      await selectVehicleInformationVoList(param).then(response => {
        let resultList = this.unique(response.rows, 'plate')
        this.vehicleInformationVoList = resultList
        this.vehicleInformationVoList.forEach(item => {
          let node = {
            name: item.plate,
            category: 2
          }
          vehicleNodes.nodes.push(node)
          // this.pieData.nodes.push(node)
          let link = {
            source: houseName,
            target: item.plate,
            name: this.vehicleUseFormat(item.typeOfResidence)
          }
          vehicleNodes.links.push(link)
          // this.pieData.links.push(link)
        })
      })
      return vehicleNodes
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
  }
}
</script>

<style lang="scss" scoped>
aside {
  background: none;
  margin-bottom: 0;
  padding: 0 5px;
}
.passRecordContainer {
  width: 100%;
  height: 100%;
}
</style>
