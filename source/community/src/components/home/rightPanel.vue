<template>
  <div class="Main"
       ref="MainView">
    <div class="rightBox">
      <!-- 社区简介 -->
      <div class="carePeople"
           style="height:190px;">
        <div class="rightTitle">
          <span></span>
          <span>社区简介</span>
        </div>
        <div class="specialPer">
          <ul class="specialPerLBox">
            <li class="specialPerLi">
              <span>{{communityDescription}}</span>
            </li>
          </ul>
        </div>
      </div>
      <div class="communityDescriptionImageC">
        <el-image style="width: 100%; height: auto"
                  :src="communityDescriptionImage"
                  :fit="communityDescriptionImagefit"></el-image>
      </div>
      <div class="carePeople">
        <div class="rightTitle">
          <span></span>
          <span>社区光荣榜</span>
        </div>
        <div class="specialPer">
          <ul class="specialPerLBox">
            <el-row v-for="(item,index) in communityHonor"
                    :key="index"
                    class="specialPerLi">
              <el-col :span="3"
                      class="honorXuhao">{{item.xuhao}}</el-col>
              <el-col :span="21"
                      class="honorContent">{{item.honor}}</el-col>
            </el-row>
          </ul>
        </div>
      </div>
      <!-- 网格分布 -->
      <div class="eventsNum"
           style="height: 33%;">
        <div class="rightTitle">
          <span></span>
          <span>网格分布</span>
        </div>
        <!-- <div style="width:100%;height:100%">
          <realOrganizationChart :currentComunity="currentComunity" />
        </div> -->
        <div style="width:99%;height:95%;"
             class="botList">
          <el-table :data="personnelDistribution"
                    :show-header="false"
                    height="100%"
                    :header-cell-style="{background:'#182751',color:'#606266'}"
                    :cell-style="{background:'#182751',color:'#fff'}">
            <el-table-column prop="gridName"
                             label="网格"
                             width="120px"
                             align="center">
            </el-table-column>
            <el-table-column prop="range"
                             label="网格范围">
            </el-table-column>
            <el-table-column prop="area"
                             width="100px"
                             label="面积(km²)"
                             :formatter="areaFormat">
            </el-table-column>
            <el-table-column prop="gridMember"
                             width="100px"
                             label="网格员">
            </el-table-column>
          </el-table>
        </div>
      </div>
      <!-- 社区内组织统计（数量） -->
      <!-- <div class='rightbottom'>
        <div class="type1">
          <div class="rightTitle">
            <span class='s2'></span>
            <span>社区组织类型统计展示</span>
          </div>
          <community></community>
        </div>
        <div class="type2">
          <div class="rightTitle">
            <span class='s1'></span>
            <span>特殊人员类型统计展示</span>
          </div>
          <special></special>
        </div>
      </div> -->
      <!-- 出行统计展示 -->
      <!-- <div class='travel'>
        <div class="travelTitle">
          <span></span>
          <span>出行统计展示</span>
        </div>
        <div style="width:100%;height:100%">
          <travelStatisticsChart :currentComunity="currentComunity" />
        </div>
      </div> -->
    </div>
  </div>
</template>
<script>
import $ from "jquery";
import { mapGetters, mapState } from "vuex";
import populationFlowChart from '@/components/echarts/main/populationFlow'
import travelStatisticsChart from '@/components/echarts/main/travelStatistics'
import { getSpecialPersonNum } from "@/api/homePage/specialPerson"
import { getCareEventNum } from "@/api/homePage/careEventNum"
export default {
  components: { populationFlowChart, travelStatisticsChart },
  data () {
    return {
      that: this, centContainerHeight: '100px',
      specialNums: [],
      tableData: [{
        date: '2016-05-02',
        type: '长期未出现',
        num: '3'
      }, {
        date: '2016-05-04',
        type: '生日提醒',
        num: '1'
      }, {
        date: '2016-05-01',
        type: '长期未出现',
        num: '3'
      }, {
        date: '2016-05-03',
        type: '生日提醒',
        num: '1'
      }],
      communityDescription: "社区简介",
      communityDescriptionImage: null,
      communityDescriptionImagefit: "contain",
      communityHonor: [],//社区荣誉
      personnelDistribution: [],//人员分布 网格 常驻人口 党员
    }
  },
  created () { },
  filters: {},
  computed: {
    ...mapGetters(["currentComunity"]),
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        this.getData()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  methods: {
    areaFormat (row, col, cell) {
      return cell && cell.length > 0 ? cell + ' km²' : ''
    },
    refreshCenterContainerHeight () {
      this.centContainerHeight = (this.$refs.MainView.offsetHeight - 72 - 331 - 4) + 'px'
    },
    getRowClass ({ row, column, rowIndex, columnIndex }) {
      return "background:#222751;color:#fff;border-bottom-color:#182751;";
    },
    getData () {
      this.tableData = []
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      // getSpecialPersonNum(communityId, communityChildId).then((res) => {
      //   // console.log(res, 'ressss')
      //   if (res && res.data)
      //     this.specialNums = [{ name: '艾滋病人', num: res.data.aidsNum },
      //     { name: '社区矫正人员', num: res.data.correctNum },
      //     { name: '肇事肇祸倾向的精神病人', num: res.data.mentalNum },
      //     { name: '刑满释放人员', num: res.data.releaseNum },
      //     // { name: '闲散流浪人员信息', num: res.data.releaseNum },
      //     { name: '吸毒人员', num: res.data.drugNum },]
      // })
      this.specialNums = this.testData.shouye.specialNums
      this.tableData = this.testData.shouye.careEvent
      // getCareEventNum(communityId, communityChildId).then((res) => {
      //   // console.log(res, 'ressss111')
      //   if (res && res.data) {
      //     res.data.forEach(data => {
      //       let careEvent = {
      //         type: data.eventName,
      //         num: data.num
      //       }
      //       this.tableData.push(careEvent)
      //     });
      //   }
      // })
    },
  },
  mounted () {
    this.communityDescription = this.testData.shouye.communityDescription
    this.communityDescriptionImage = this.testData.shouye.communityDescriptionImage
    this.communityDescriptionImagefit = this.testData.shouye.communityDescriptionImagefit
    this.communityHonor = this.testData.shouye.communityHonor
    this.personnelDistribution = this.testData.renyuan.peopleHousingInfoList

    this.getData()
  }
  // mounted () { const the_page = this; window.onresize = () => { return (() => { the_page.refreshCenterContainerHeight() })() }; this.refreshCenterContainerHeight() }
}
</script>
<style lang="scss" scoped>
@import "~@/assets/styles/main/index.css";
.honorXuhao {
  color: #fff;
  font-size: 12px;
}
.honorContent {
  color: #fff;
  font-size: 12px;
}
.communityDescriptionImageC {
  width: 100%;
  height: 293px;
  padding-left: 5%;
  padding-right: 5%;
  background-color: #182751;
}
.Main {
  width: 100%;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
  background: transparent;
}
</style>