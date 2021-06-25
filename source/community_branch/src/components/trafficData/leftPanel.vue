<template>
  <div style="width:100%;height:100%">
    <div>
      <el-tooltip class="item"
                  effect="dark"
                  content="隐藏显示"
                  placement="top">
        <span @click="shrinkage()"
              class="switchImg el-icon-view"></span>
      </el-tooltip>
    </div>
    <div class="trafficLeftBox">
      <!-- 访客数量分布 -->
      <div class="numberOfVisitors">
        <div class="traffic-boxTitle">
          <span></span>
          <span>访客数量分布（周）</span>
        </div>
        <div style="width:100%;height:100%">
          <distributionVisitorsChart :currentComunity="currentComunity" />
        </div>
      </div>
      <!-- 访客类别 -->
      <div class="visitorCategory">
        <div class="traffic-boxTitle">
          <span></span>
          <span>访客类别（周）</span>
        </div>
        <div style="width:100%;height:100%">
          <visitorCategoriesChart :currentComunity="currentComunity" />
        </div>
      </div>
      <!-- 通行数量 -->
      <div class="trafficStatistics">
        <div class="traffic-boxTitle">
          <span></span>
          <span>通行数量（周）</span>
        </div>
        <div class="trafficStatisticsData">
          <span>
            <div>{{trafficStatisticsData.week_person}}</div>
            <div>人员通行</div>
          </span>
          <span>
            <div>{{trafficStatisticsData.week_vehicle}}</div>
            <div>车辆通行</div>
          </span>
          <span>
            <div>{{trafficStatisticsData.week_visit_vehicle}}</div>
            <div>访客通行</div>
          </span>
        </div>
      </div>
      <!-- 人车通行统计 -->
      <div class="abnormalStatistics">
        <div class="traffic-boxTitle">
          <span></span>
          <span>当天人车通行统计</span>
        </div>
        <div style="width:100%;height:100%">
          <!-- <abnormalStatisticsChart /> -->
          <currentStatisticsChart :currentComunity="currentComunity" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import videojs from "video.js";
import { mapGetters, mapState } from "vuex";
import visitorCategoriesChart from '@/components/echarts/trafficData/visitorCategories'
import distributionVisitorsChart from '@/components/echarts/trafficData/distributionVisitors'
import abnormalStatisticsChart from '@/components/echarts/trafficData/abnormalStatistics'
import currentStatisticsChart from '@/components/echarts/trafficData/currentStatistics'
import { gettrafficdata } from '@/api/trafficData/trafficNum'
export default {
  name: "Index",
  components: { visitorCategoriesChart, distributionVisitorsChart, abnormalStatisticsChart, currentStatisticsChart },
  filters: {
    ellipsis (value) {
      if (!value) return ''
      if (value.length > 5) {
        return value.slice(0, 5) + '...'
      }
      return value
    }
  },
  computed: {
    ...mapGetters(["currentComunity"]),
  },
  data () {
    return {
      switchImg: require('@/assets/images/peopleinfo/katong.png'),
      trafficStatisticsData: {
        day_person: 30,
        day_visit_person: 50,
        day_vehicle: 25
      },
    };
  },
  created () { },
  mounted () {
    this.gettrafficApi()
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        this.gettrafficApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  methods: {
    shrinkage () {
      $(".trafficLeftBox").fadeToggle(2000);
      $(".trafficRightBox").fadeToggle(2000);
      $(".topCenterBox").fadeToggle(2000);
      // this.$Notice.$emit('shrinkageHide', 'trafficData')
    },
    gettrafficApi () {
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      gettrafficdata(communityId, communityChildId).then(res => {
        // console.log(res, 'trafficStatisticsData')
        if (res.data != null) {
          this.trafficStatisticsData = res.data
        }
      })
    }
  }
}
</script>

<style>
</style>