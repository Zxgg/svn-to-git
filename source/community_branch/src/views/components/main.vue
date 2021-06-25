<template>
  <div>
    <el-tooltip class="item"
                effect="dark"
                content="隐藏显示"
                placement="top">
      <span @click="shrinkage()"
            class="switchImg el-icon-view"></span>
    </el-tooltip>
    <div class="notice"
         :style="noticeBackground">
      <textScroll :dataList="noticeList"
                  v-if="false" />
    </div>
    <div class="leftBox">
      <div class="houseNumber">
        <!-- 房屋数量 -->
        <div class='house'>
          <div class='houseuseTitle'>
            <span></span>
            <span>房屋使用属性统计展示</span>
            <span>
              <houseUse />
            </span>
          </div>
        </div>
        <!-- 增加入住率统计 -->
        <div class='occpancy'>
          <div class="occupancyTitle">
            <span></span>
            <span>入住率统计展示</span>
          </div>
        </div>
      </div>
      <div class="houseNumberBox">
        <el-row :gutter="5">
          <el-col :span="12">
            <div class="houseNumberBoxLeft">
              <div class="rightTitle">
                <span></span>
                <span>居住人口统计展示</span>
              </div>
              <div style="margin-top: 10px;">
                <inThePopulationChart />
              </div>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="houseNumberBoxRight">
              <div class="rightTitle">
                <span></span>
                <span>人口流动统计展示</span>
              </div>
              <div>
                <personFlowChart />
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
      <!-- 最近车辆活动数 -->
      <div class='vehicleflow'>
        <div class="vehicleflowTitle">
          <span></span>
          <span>车辆流动统计展示</span>
          <span class='vehicleflowNum'>车辆总数：{{vehicleflowNum}}</span>
        </div>
        <car-statistical></car-statistical>
      </div>
      <!-- 事件数量统计 -->
      <div class="eventsNum">
        <div class="rightTitle">
          <span></span>
          <span>事件数量统计展示</span>
        </div>
        <div>
          <ul class="eventNumsLBox">
            <li v-for="(item,index) in eventNums"
                :key="index"
                class="eventNumsLi">
              <span></span>
              <span>{{item.name}}</span>
              <span>{{item.num}}</span>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="rightBox">
      <!-- 关爱人员最近需要的关注的事件 -->
      <div class="carePeople">
        <div class="rightTitle">
          <span></span>
          <span>关爱提醒类型统计展示</span>
        </div>
        <div class="carePeopleTable">
          <el-table :data="tableData"
                    style="width: 100%"
                    height="160"
                    :cell-style="{'text-align':'center'}"
                    :row-style="getRowClass"
                    :header-row-style="getRowClass"
                    :header-cell-style="getRowClass"
                    class="tableClass">
            <el-table-column prop="level"
                             label="提醒等级"
                             width="90">
            </el-table-column>
            <el-table-column prop="type"
                             label="事件类型"
                             width="90">
            </el-table-column>
            <el-table-column prop="num"
                             label="事件数量">
            </el-table-column>
            <el-table-column prop="processing"
                             label="处理比例">
            </el-table-column>
            <el-table-column prop="toList"
                             label="查看列表">
            </el-table-column>
          </el-table>
        </div>
      </div>
      <!-- 人口流动情况 -->
      <div class="populationFlow">
        <div class="rightTitle">
          <span></span>
          <span>人口流动统计展示</span>
        </div>
        <div>
          <populationFlowChart />
        </div>
      </div>
      <!-- 出行统计展示 -->
      <div class='travel'>
        <div class="travelTitle">
          <span></span>
          <span>出行统计展示</span>
        </div>
        <travel></travel>
      </div>
      <!-- 社区内组织统计（数量） -->
      <div class='rightbottom'>
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
      </div>
    </div>
  </div>
</template>
<script>
import $ from "jquery";
import eventsFromChart from '@/components/echarts/main/eventsFrom'
import populationFlowChart from '@/components/echarts/main/populationFlow'
import cameraNumChart from '@/components/echarts/main/cameraNum'
import inThePopulationChart from '@/components/echarts/main/InThePopulation'
import personFlowChart from '@/components/echarts/main/personFlow'
import textScroll from '@/components/textScroll/index'
import carStatistical from '@/components/echarts/carStatistical/carstatistical'
import occupancy from '@/components/echarts/occupancy/occupancy'
import travel from '@/components/echarts/travel/travel'
import community from '@/components/echarts/community/community'
import special from '@/components/echarts/community/special'
import houseUse from '@/components/echarts/houseUse/houseUse'
export default {
  name: "Index",
  components: { eventsFromChart, populationFlowChart, cameraNumChart, textScroll, inThePopulationChart, personFlowChart, carStatistical, occupancy, travel, community, special, houseUse },
  data () {
    return {
      noticeBackground: {
        backgroundImage:
          "url(" + require("@/assets/images/home/notice.png") + ")",

        backgroundRepeat: "repeat",

        backgroundSize: "100% 100%"
      },
      switchImg: require('@/assets/images/peopleinfo/katong.png'),
      houseNumber: 123456,
      eventNums: [
        { name: '违法停车', num: 456 },
        { name: '违法停车', num: 856 },
        { name: '违法停车', num: 86 },
        { name: '违法停车', num: 46 },
        { name: '违法停车', num: 456 },
        { name: '违法停车', num: 456 },
        { name: '违法停车', num: 66 },
        { name: '违法停车', num: 456 },
        { name: '违法停车', num: 456 },
        { name: '违法停车', num: 456 },
        { name: '违法停车', num: 126 },
        { name: '违法停车', num: 456 },
        { name: '违法停车', num: 6 },
      ],
      actionCarNumbers: [
        { name: '社会组织', num: 456 },
        { name: '综治组织', num: 856 },
        { name: '群防群治机构', num: 86 },
        { name: '境外非政府组织', num: 46 },
        { name: '社会组织', num: 456 },
        { name: '综治组织', num: 856 },
        { name: '群防群治机构', num: 86 },
        { name: '境外非政府组织', num: 46 },

      ],
      vehicleflowNum: 456789,
      tableData: [{
        type: '孤寡老人',
        num: '32',
        level: '5',
        processing: '30%',
        toList: ''
      }, {
        type: '残疾人士',
        num: '56',
        level: '5',
        processing: '15%',
        toList: ''
      }, {
        type: '军人',
        num: '65',
        level: '3',
        processing: '40%',
        toList: ''
      }, {
        type: '王小虎',
        num: '77',
        level: '1',
        processing: '0.1%',
        toList: ''
      }],
      noticeList: [
        '公益绘画课堂走进通济社区',
        '印媒提醒莫迪政府:印度不是中国 ',
        '北京地铁设口罩自助售货机',
        '商务部:美国打压特定中国企业'
      ]
    };
  },
  created () { },
  mounted () { },
  methods: {
    getRowClass ({ row, column, rowIndex, columnIndex }) {
      return "background:#222751;color:#fff;border-bottom-color:#182751;";
    },
    shrinkage () {
      $(".leftBox").fadeToggle(1500);
      $(".rightBox").fadeToggle(1500);
      $(".notice").fadeToggle(1500);
      this.$Notice.$emit('shrinkageHide', 'index')
    }
  }
};
</script>

<style scoped>
@import "~@/assets/styles/main/index.css";
.notice {
  width: 450px;
  height: 70px;
  position: absolute;
  left: 470px;
  top: 50px;
}
.houseNumberBoxLeft,
.houseNumberBoxRight {
  padding-top: 5px;
  height: 220px;
}
</style>
<style>
/* //滚动条的宽度 */
::-webkit-scrollbar {
  width: 5px;
  height: 10px;
}
/* //滚动条的滑块 */
::-webkit-scrollbar-thumb {
  background-color: #a1a3a9;
  border-radius: 3px;
}
/* .el-table .el-table__header-wrapper th,
.el-table .el-table__fixed-header-wrapper th {
  background: none;
}
.el-table--enable-row-hover .el-table__body tr:hover > td {
  background: none;
}
.el-table th.is-leaf,
.el-table td {
  border-bottom: 0;
}
.el-table--group::after,
.el-table--border::after,
.el-table::before {
  /* background-color: #182751;
  background: none;
}
*/
</style>
