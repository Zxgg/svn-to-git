<template>
  <div style="overflow-y:none;">
    <div>
      <el-tooltip class="item"
                  effect="dark"
                  content="隐藏显示"
                  placement="top">
        <span @click="shrinkage()"
              class="switchImg el-icon-view"></span>
      </el-tooltip>
    </div>
    <div class="mainContent">
      <!-- 左侧盒子 -->
      <div>
        <!-- 人员信息统计 -->
        <div class="personnelInformationStatistics">
          <div class="boxTitle">
            <span></span>
            <span>人员信息统计</span>
          </div>
          <div>
            <personnelInformationStatisticsCharts />
          </div>
        </div>
        <!-- 文化程度统计 -->
        <div class="levelOfEducation">
          <div class="boxTitle">
            <span></span>
            <span>文化程度统计</span>
          </div>
          <div>
            <educationLevelStatisticsCharts />
          </div>
        </div>
        <!-- 年龄统计 -->
        <div class="ageStatistics">
          <div class="boxTitle">
            <span></span>
            <span>年龄统计</span>
          </div>
          <div>
            <ageStatisticsCharts />
          </div>
        </div>
        <!-- 民族统计 -->
        <div class="nationalStatistics">
          <div class="boxTitle">
            <span></span>
            <span>民族统计</span>
          </div>
          <div>
            <nationalStatisticsCharts />
          </div>
        </div>
      </div>
      <!-- 右侧盒子 -->
      <div>
        <div>
          <el-row :gutter="10">
            <el-col :span="8">
              <!-- 人员住房信息 -->
              <div class="personnelHousingInformation">
                <div class="boxTitle">
                  <span></span>
                  <span>人员住房信息</span>
                </div>
                <div>
                  <personnelHousingInformationCharts />
                </div>
              </div>
            </el-col>
            <el-col :span="16">
              <div class="numberCommunity">
                <div class="boxTitle">
                  <span></span>
                  <span>各小区人员数量</span>
                </div>
                <div>
                  <ul class="eventNumsLBox"
                      style="height: 170px;">
                    <li v-for="(item,index) in communityData"
                        :key="index"
                        class="eventNumsLi">
                      <span style="background-color: #00BEFF;"></span>
                      <span>{{item.name}}</span>
                      <span>{{item.num}}</span>
                    </li>
                  </ul>
                </div>
              </div>
            </el-col>
          </el-row>
        </div>
        <!-- 关爱人员信息 -->
        <div class="carePeople">
          <div class="boxTitle">
            <span></span>
            <span>关爱人员信息</span>
          </div>
          <div class="carePeopleTable">
            <el-table :data="tableData"
                      style="width: 100%"
                      height="145"
                      :row-style="getRowClass"
                      :header-row-style="getRowClass"
                      :header-cell-style="getRowClass"
                      class="tableClass">
              <el-table-column prop="type"
                               label="关爱类型"
                               width="100">
              </el-table-column>
              <el-table-column prop="num"
                               label="数量"
                               width="100">
              </el-table-column>
              <el-table-column prop="address"
                               label="关爱提醒">
              </el-table-column>
              <el-table-column prop="address"
                               label="详情">
              </el-table-column>
            </el-table>
          </div>
        </div>
        <!-- 特殊人群、管控人群 -->
        <div class="specialPeople">
          <div class="boxTitle">
            <span></span>
            <span>特殊人群/管控人群</span>
          </div>
          <div class="carePeopleTable">
            <el-table :data="specialPopulationsData"
                      style="width: 100%"
                      height="150"
                      :row-style="getRowClass"
                      :header-row-style="getRowClass"
                      :header-cell-style="getRowClass"
                      class="tableClass">
              <el-table-column prop="type"
                               label="特殊人群/管控人群"
                               width="180">
              </el-table-column>
              <el-table-column prop="num"
                               label="数量"
                               width="100">
              </el-table-column>
              <el-table-column prop="address"
                               label="详情">
              </el-table-column>
            </el-table>
          </div>
        </div>
        <!-- 人口流动情况 -->
        <div class="populationMobility">
          <div class="boxTitle">
            <span></span>
            <span>人口流动情况</span>
          </div>
          <div>
            <populationMobilityCharts />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import $ from "jquery";
import personnelInformationStatisticsCharts from '@/components/echarts/peopleInfo/personnelInformationStatistics';
import educationLevelStatisticsCharts from '@/components/echarts/peopleInfo/educationLevelStatistics';
import ageStatisticsCharts from '@/components/echarts/peopleInfo/ageStatistics';
import nationalStatisticsCharts from '@/components/echarts/peopleInfo/nationalStatistics';
import populationMobilityCharts from '@/components/echarts/peopleInfo/populationMobility';
import personnelHousingInformationCharts from '@/components/echarts/peopleInfo/personnelHousingInformation';

export default {
  name: "Index",
  components: { personnelInformationStatisticsCharts, educationLevelStatisticsCharts, ageStatisticsCharts, nationalStatisticsCharts, populationMobilityCharts, personnelHousingInformationCharts },
  data () {
    return {
      switchImg: require('@/assets/images/peopleinfo/katong.png'),
      tableData: [{
        type: '孤寡老人',
        num: '32',
      }, {
        type: '残疾人士',
        num: '56',
      }, {
        type: '军人',
        num: '65',
      }, {
        type: '王小虎',
        num: '77',
      }],
      specialPopulationsData: [{
        type: '吸毒人员',
        num: '32',
      }, {
        type: '抢劫逃逸人员',
        num: '56',
      }, {
        type: '网络犯罪人员',
        num: '65',
      }, {
        type: '勒索敲诈人员',
        num: '77',
      }],
      communityData: [
        { name: '天通苑小区', num: 456 },
        { name: '花家地小区', num: 856 },
        { name: '名家小区', num: 86 },
        { name: '世纪城小区', num: 46 },
        { name: '回迁房小区', num: 456 },
        { name: '万达小区', num: 856 },
        { name: '景点小区', num: 86 },
        { name: '123小区', num: 46 },

      ],
    };
  },
  created () { },
  mounted () { },
  methods: {
    getRowClass ({ row, column, rowIndex, columnIndex }) {
      return "background:#222751;color:#fff;border-bottom-color:#182751;";
    },
    shrinkage () {
      $(".mainContent > div:first-child").fadeToggle(2000);
      $(".mainContent > div:last-child").fadeToggle(2000);
      this.$Notice.$emit('shrinkageHide', 'peopleInfo')
    }
  }
};
</script>

<style scoped="true">
@import "~@/assets/styles/main/peopleInfo.css";
</style>
