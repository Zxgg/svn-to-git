<template>
  <!-- <div class="app-container"> -->
  <el-container class="app-container">
    <el-aside width="320px">
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
                         :key="dict.dictValue"
                         :label="dict.dictLabel"
                         :value="dict.dictValue" />
            </el-select>
          </el-form-item> -->
        <!-- <el-form-item label="证件号码"
                      prop="identificationNumber">
          <el-input v-model="queryParams.identificationNumber"
                    placeholder="请输入证件号码"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery" />
        </el-form-item> -->
        <el-form-item label="手机"
                      prop="phoneNumber">
          <el-input v-model="queryParams.phoneNumber"
                    placeholder="请输入手机号码"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery" />
        </el-form-item>
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
                :data="personnel_informationList"
                :height="dataHeight"
                @row-click="handleRowClickChange">
        <el-table-column label="姓名"
                         align="left">
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
      <el-aside width='500px'>
        <el-scrollbar style="height:100%"
                      wrap-class="scrollbar-wrapper "
                      wrap-style="overflow-x:hidden;">
          <el-card class="box-card"
                   :body-style="{ padding: '4px 20px'}">
            <div slot="header"
                 class="clearfix">
              <span class="textLeft commonTitleText">自身信息</span>
            </div>
            <div v-if="currentRow"
                 class="divStackPanel_HorizontalLeft">
              <div style="width: 140px; height: 100%;">
                <el-image v-if="currentRow.pics && currentRow.pics.length>0"
                          style="width: 100%; height: 100%;"
                          :src="getFirstUrlFromJson(currentRow.pics)"
                          fit="contain">
                  <div slot="placeholder"
                       class="image-slot">
                    <span class="iconfont icontuxiangziliao"
                          style="font-size: 36px;" />
                  </div>
                  <div slot="error"
                       class="image-slot">
                    <span class="iconfont icontuxiangziliao"
                          style="font-size: 36px;" />
                  </div>
                </el-image>
                <el-image v-else
                          :src="defaultPersonImage"
                          style="width: 100%; height: 100%" />
              </div>
              <div style="width: 340px; height: 100%;">
                <div class="textLeft HorizontalMargin">
                  <span>
                    {{ currentRow.name }}
                    {{ sexFormat(null,null,currentRow.sex) }}
                    {{'('+ currentRow.phoneNumber +')'}}
                    {{' ['+ nationalityFormat(currentRow.nationality)+'-'+nationFormat(currentRow.nation) +']'}}
                  </span>
                </div>
                <div class="textLeft HorizontalMargin">
                  <span>
                    证件号码：{{ currentRow.identificationNumber }}
                  </span>
                </div>
                <div class="textLeft HorizontalMargin">
                  <span>
                    注册小区：
                    {{ currentRow.communityId | communityNameFilters(that) }}
                    {{ currentRow.communityChildId | communityChildNameFilters(that) }}
                  </span>
                </div>
              </div>
            </div>
          </el-card>
          <el-card class="box-card"
                   :body-style="{ padding: '4px 20px'}">
            <div slot="header"
                 class="clearfix">
              <span class="textLeft commonTitleText">关联房屋</span>
            </div>
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
          <el-card class="box-card"
                   :body-style="{ padding: '4px 20px'}">
            <div slot="header"
                 class="clearfix">
              <span class="textLeft commonTitleText">关联人员</span>
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
            </div>
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
                    :height="passDataHeight">
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
import vehicleHousesPersonnel from '@/views/communityUnit/vehicle_houses_personnel/index'
import { listCommunity } from '@/api/communityUnit/community'
import { listCommunity_child } from '@/api/communityUnit/community_child'
import { getInfoByName } from '@/api/communityUnit/personnel_information'
import { listVehicle_information, getVehicle_information, selectVehicleInformationVoList } from '@/api/communityUnit/vehicle_information'
import { listCommunity_houses, getCommunity_houses, queryList_houses } from '@/api/communityUnit/community_houses'
import { selectPerInformByHousesId } from '@/api/communityUnit/personnel_information'
import defaultPersonPic from "@/assets/images/home/default-renyuan.png"
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
      defaultPersonImage: defaultPersonPic,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 人员信息表格数据
      personnel_informationList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 性别字典
      sexOptions: [],
      // 特殊人员字典
      specialOptions: [],
      // 车型字典
      modelOptions: [],
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
      // 国籍字典
      nationalityOptions: [],
      // 民族字典
      nationOptions: [],
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
      //当前选中行
      currentRow: null,
      // 搜索表单控件高度
      formHeight: 0,
      // 表单展开层 关联车辆
      vehicleInformationVoList: [],
      // 表单展开层 关联房屋
      houseInformationVoList: [],
      // 表单展开层 关联人员
      personInformationVoList: [],
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
    },
    passDataHeight () {
      return this.$store.getters.dataEleHeight - 155
    }
  },
  created () {
    this.getCommunityDictory()
    this.getAllCommunityChildDictory()
    this.getDicts('sys_user_sex').then(response => {
      this.sexOptions = response.data
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
    this.getDicts("base_nationality").then(response => {
      this.nationalityOptions = response.data;
    });
    this.getDicts("base_nation").then(response => {
      this.nationOptions = response.data;
    });
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
    /** 查询人员信息列表 */
    getList () {
      const username = this.$route.query.username;
      const communityId = this.$route.query.communityId;
      this.getCommunityChildDictory(communityId);
      const communityChildId = this.$route.query.communityChildId;
      console.log('username', username);
      if (username) {
        this.queryParams.name = username;
        this.queryParams.communityId = communityId;
        this.queryParams.communityChildId = communityChildId;
      }
      this.loading = true
      listPersonnel_information(this.queryParams).then(response => {
        this.personnel_informationList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 车型字典翻译
    modelFormat (row, column) {
      return this.selectDictLabel(this.modelOptions, row.model);
    },
    // 性别字典翻译
    sexFormat (row, column, cellValue) {
      return this.selectDictLabel(this.sexOptions, cellValue);
    },
    // 居住类型字典翻译
    typeOfResidenceFormat (value) {
      return this.selectDictLabel(this.typeOfResidenceOptions, value);
    },
    // 车辆使用类型
    vehicleUseFormat (value) {
      return this.selectDictLabel(this.vehicleUseOptions, value)
    },
    // 国籍字典翻译
    nationalityFormat (value) {
      return this.selectDictLabel(this.nationalityOptions, value);
    },
    // 民族字典翻译
    nationFormat (value) {
      return this.selectDictLabel(this.nationOptions, value);
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
    async handleRowClickChange (row) {
      this.pieData.nodes = []
      this.pieData.links = []
      // 表单展开层 关联车辆
      this.vehicleInformationVoList = []
      // 表单展开层 关联房屋
      this.houseInformationVoList = []
      // 表单展开层 关联人员
      this.personInformationVoList = []
      this.currentRow = row
      await this.getHouseVoList(row)
      this.getVehicleVoList(row)
    },
    async getHouseVoList (row) {
      const param = {
        personnelId: row.id
      }
      await queryList_houses(param).then(response => {
        if (response && response.rows && response.rows.length > 0)
          response.rows = this.unique5(response.rows, 'communityId', 'communityChildId', 'serialNumber', 'unit', 'houseNumber')
        this.houseInformationVoList = response.rows
        this.houseInformationVoList.forEach(item => {
          this.getPersonVoList(item)
          let node = {
            name: item.serialNumber + '号楼' + item.unit + '单元' + item.houseNumber,
            category: 1
          }
          this.pieData.nodes.push(node)
        })
      })
    },
    getPersonVoList (houseInfo) {
      const queryDate = {
        houseId: houseInfo.id
      }
      this.loadingVoPersonList = true
      selectPerInformByHousesId(queryDate).then(response => {
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
          this.pieData.nodes.push(node)
          let link = {
            source: personName,
            target: houseInfo.serialNumber + '号楼' + houseInfo.unit + '单元' + houseInfo.houseNumber,
            name: this.typeOfResidenceFormat(item.relationship_with_homeowner)
          }
          this.pieData.links.push(link)
        })
      })
    },
    getVehicleVoList (row) {
      const param = {
        personnelId: row.id
      }
      selectVehicleInformationVoList(param).then(response => {
        if (response && response.rows && response.rows.length > 0)
          response.rows = this.unique(response.rows, 'plate')
        this.vehicleInformationVoList = response.rows
        this.vehicleInformationVoList.forEach(item => {
          this.houseInformationVoList.filter((houseItem, i) => {
            if (houseItem.communityHousesId === item.communityHousesId) {
              let link = {
                source: item.plate,
                target: houseItem.serialNumber + '号楼' + houseItem.unit + '单元' + houseItem.houseNumber,
                // name: this.vehicleUseFormat(item.typeOfResidence)
                name: '登记'
              }
              this.pieData.links.push(link)
            }
          })
          let node = {
            name: item.plate,
            category: 2
          }
          this.pieData.nodes.push(node)
          let link = {
            source: row.name,
            target: item.plate,
            name: this.vehicleUseFormat(item.typeOfResidence)
          }
          this.pieData.links.push(link)
        })
      })
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
.box-card {
  width: 480px;
  height: auto;
  margin-bottom: 4px;
  min-height: 120px;
}
.el-scrollbar {
  height: 100%;
  .el-scrollbar__wrap {
    overflow-x: hidden !important;
    margin-right: -222px;
  }
}
.el-scrollbar__wrap {
  overflow-x: hidden !important;
  margin-right: -222px;
}
.passRecordContainer {
  width: 100%;
  height: 100%;
}
</style>
