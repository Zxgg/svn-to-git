<template>
  <div style="overflow-y:none;width:100%;height:100%">
    <div>
      <el-tooltip class="item"
                  effect="dark"
                  content="隐藏显示"
                  placement="top">
        <span @click="shrinkage()"
              class="switchImg el-icon-view"></span>
      </el-tooltip>
    </div>
    <!-- 左侧盒子 -->
    <div class="peopleLeftBox">
      <!-- 人员信息统计 -->
      <div class="nationalStatistics">
        <div class="people-boxTitle">
          <span></span>
          <span>人员信息统计</span>
        </div>
        <div style="width:100%;height:100%">
          <personnelInformationStatisticsCharts :currentComunity="currentComunity" />
        </div>
      </div>
      <div class="personnelInformationStatistics">
        <div class="people-boxTitle">
          <span></span>
          <span>文化程度统计</span>
        </div>
        <div style="width:100%;height:100%">
          <educationLevelStatisticsCharts :currentComunity="currentComunity" />
        </div>
      </div>
      <!-- 年龄统计 -->
      <div class="ageStatistics">
        <div class="people-boxTitle">
          <span></span>
          <span>年龄统计</span>
        </div>
        <div style="width:100%;height:100%">
          <ageStatisticsCharts :currentComunity="currentComunity" />
        </div>
      </div>
      <!-- 民族统计 -->
      <div class="levelOfEducation">
        <div class="people-boxTitle">
          <span></span>
          <span>民族统计</span>
        </div>
        <div style="width:100%;height:100%">
          <nationalStatisticsCharts :currentComunity="currentComunity" />
        </div>
      </div>
      <!-- 人员类型统计 -->
      <!-- <div class="levelOfEducation">
        <div class="people-boxTitle">
          <span></span>
          <span>人员类型统计</span>
        </div>
        <div style="width:100%;height:100%">
          <personTypeStatisticsCharts :currentComunity="currentComunity" />
        </div>
      </div> -->

    </div>
  </div>
</template>

<script>
import $ from "jquery";
import { mapGetters, mapState } from "vuex";
import personnelInformationStatisticsCharts from '@/components/echarts/peopleInfo/personnelInformationStatistics';
import educationLevelStatisticsCharts from '@/components/echarts/peopleInfo/educationLevelStatistics';
import ageStatisticsCharts from '@/components/echarts/peopleInfo/ageStatistics';
import nationalStatisticsCharts from '@/components/echarts/peopleInfo/nationalStatistics';
import specialPersonStatisticsCharts from '@/components/echarts/peopleInfo/specialPersonStatistics';
import personTypeStatisticsCharts from '@/components/echarts/peopleInfo/personTypeStatistics';
export default {
  name: "Index",
  components: { personnelInformationStatisticsCharts, educationLevelStatisticsCharts, ageStatisticsCharts, nationalStatisticsCharts, specialPersonStatisticsCharts, personTypeStatisticsCharts },
  data () {
    return {
      switchImg: require('@/assets/images/peopleinfo/katong.png'),
    };
  },
  computed: {
    ...mapGetters(["currentComunity"]),
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        // this.getData()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  created () { },
  mounted () { },
  methods: {
    shrinkage () {
      $(".peopleLeftBox").fadeToggle(2000);
      $(".peopleRightBox").fadeToggle(2000);
    }
  }
}
</script>

<style scoped="true">
@import "~@/assets/styles/main/peopleInfo.css";
</style>