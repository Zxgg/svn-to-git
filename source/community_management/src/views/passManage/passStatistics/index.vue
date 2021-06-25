<template>
  <div class="app-container">
    <div class="TopContainer">
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
        <el-form-item>
          <el-button type="cyan"
                     v-show="showSearch"
                     icon="el-icon-search"
                     size="mini"
                     @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh"
                     v-show="showSearch"
                     size="mini"
                     @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="CenterContainer">
      <p class="title0">通行统计</p>
      <el-row :gutter="20">
        <el-col :span="6">
          <p class="title1">当日居民通行</p>
          <p class="title2">{{ dayPerson }} 人 / {{ dayVehicle }} 车</p>
        </el-col>
        <div class="line1"></div>
        <el-col :span="6">
          <p class="title1">当月居民通行</p>
          <p class="title2">{{ monthPerson }} 人 / {{ monthVehicle }} 车</p>
        </el-col>
        <div class="line2"></div>
        <el-col :span="6">
          <p class="title1">当日访客数量</p>
          <p class="title2">{{ dayVisitPerson }} 人 / {{ dayVisitVehicle }} 车</p>
        </el-col>
        <div class="line3"></div>
        <el-col :span="6">
          <p class="title1">当月访客数量</p>
          <p class="title2">{{ monthVisitPerson }} 人 / {{ monthVisitVehicle }} 车</p>
        </el-col>
      </el-row>
    </div>
    <div class="BottomContainer">
      <lineChart :data="passInfo" />
    </div>
  </div>
</template>

<script>
import lineChart from '@/components/dashboard/passStatisticsLineChart.vue'
import { listPersonnel_information, getPersonnel_information } from '@/api/communityUnit/personnel_information'
// import communityHousesPersonnel from '../community_houses_personnel/index'
import vehicleHousesPersonnel from '@/views/communityUnit/vehicle_houses_personnel/index'
import { listCommunity } from '@/api/communityUnit/community'
import { listCommunity_child } from '@/api/communityUnit/community_child'
import { getInfoByName } from '@/api/communityUnit/personnel_information'
import { listVehicle_information, getVehicle_information, selectVehicleInformationVoList } from '@/api/communityUnit/vehicle_information'
import { listCommunity_houses, getCommunity_houses, queryList_houses } from '@/api/communityUnit/community_houses'
import { selectPerInformByHousesId } from '@/api/communityUnit/personnel_information'
import { passStatisticsOverview, passTrends } from '@/api/passManage/passStatistics'


export default {
  components: { lineChart },
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
      // 查询参数
      queryParams: {
        communityId: null,
        communityChildId: null
      },
      //通行信息统计
      passInfo: null,
      // 总览
      dayPerson: 0,
      dayVehicle: 0,
      monthPerson: 0,
      monthVehicle: 0,
      dayVisitPerson: 0,
      dayVisitVehicle: 0,
      monthVisitPerson: 0,
      monthVisitVehicle: 0
    }
  },
  // computed: {
  //   dataHeight () {
  //     let searchFormHeight = 0
  //     if (this.showSearch) searchFormHeight = this.formHeight
  //     return this.$store.getters.dataEleHeight - searchFormHeight - 155
  //   }
  // },
  created () {
    this.getCommunityDictory()
    // this.getAllCommunityChildDictory()
  },
  mounted () {
    this.getList()
    // this.$nextTick(() => {
    //   this.formHeight = this.$refs.queryForm.$el.offsetHeight
    // })
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
    /** 查询总览统计 */
    getList () {
      passStatisticsOverview(this.queryParams).then(response => {
        let data = response.data
        data.forEach(item => {
          switch (item.name) {
            case 'day_person':
              this.dayPerson = item.num
              break;
            case 'day_visit_person':
              this.dayVisitPerson = item.num
              break;
            case 'day_vehicle':
              this.dayVehicle = item.num
              break;
            case 'day_visit_vehicle':
              this.dayVisitVehicle = item.num
              break;
            case 'month_person':
              this.monthPerson = item.num
              break;
            case 'month_visit_person':
              this.monthVisitPerson = item.num
              break;
            case 'month_vehicle':
              this.monthVehicle = item.num
              break;
            case 'month_visit_vehicle':
              this.monthVisitVehicle = item.num
              break;
            default:
              break;
          }
        })
      })
      this.getPassInfo()
    },
    /*获取通行信息  线*/
    getPassInfo () {
      let beginTime = new Date()
      let endTime = new Date()
      beginTime.setTime(beginTime.getTime() - 7 * 24 * 3600 * 1000)
      endTime.setTime(endTime.getTime() - 1 * 24 * 3600 * 1000)
      let timeSpanString = this.$cf.getTimeSpanStringList(beginTime, endTime)
      this.passInfo = {
        xAxis: timeSpanString,
        lines: [
          {
            name: '居民',
            data: [120, 132, 101, 134, 90, 230, 210]
          }, {
            name: '居民车辆',
            data: [220, 182, 191, 234, 290, 330, 310]
          },
          {
            name: '访客',
            data: [35, 20, 25, 18, 19, 23, 9]
          }, {
            name: '访客车辆',
            data: [2, 7, 8, 5, 12, 14, 8]
          }
        ]
      }
      let startTimeStr = this.$cf.DataFormat(beginTime, 'yyyy-MM-dd') + ' 00:00:00'
      let endTimeStr = this.$cf.DataFormat(endTime, 'yyyy-MM-dd') + ' 23:59:59'
      let param = {
        startTime: startTimeStr,
        endTime: endTimeStr,
        type: 1//type=1代表按照日统计 type=2代表按照小时
      }
      passTrends(param).then(response => {

      })
    },
    /** 搜索按钮操作 */
    handleQuery () {
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery () {
      this.resetForm('queryForm')
      this.handleQuery()
    }
  }
}
</script>

<style lang="scss" scoped>
.TopContainer {
  width: 100%;
  height: 50px;
  // background: cornflowerblue;
}
.CenterContainer {
  width: 100%;
  height: 200px;
  // background: darkcyan;
}
.BottomContainer {
  width: 100%;
  height: calc(100% - 50px - 200px);
}
.line1 {
  width: 2px;
  height: 120px;
  background-color: #006180;
  position: absolute;
  // top: 5px;
  left: 25%;
}
.line2 {
  width: 2px;
  height: 120px;
  background-color: #006180;
  position: absolute;
  // top: 5px;
  left: 50%;
}
.line3 {
  width: 2px;
  height: 120px;
  background-color: #006180;
  position: absolute;
  // top: 5px;
  left: 75%;
}
.title0 {
  font-size: 20px;
  text-align: left;
}
.title1 {
  font-size: 24px;
}
.title2 {
  font-size: 20px;
}
</style>