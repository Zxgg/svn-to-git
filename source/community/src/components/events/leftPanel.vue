<template>
  <div style="width:100%;height:100%">
    <!-- <div>
      <el-tooltip class="item" effect="dark" content="隐藏显示" placement="top">
        <span @click="shrinkage()" class="switchImg el-icon-view"></span>
      </el-tooltip>
    </div> -->
    <div class="trafficLeftBox">
      <!-- 事件告警 -->
      <div class="numberOf">
        <div class="event-boxTitle">
          <span>受理事件</span>
          <span>ACCEPT EVENT</span>
        </div>
        <div class="events-content" >
          <leftevents :currentComunity="currentComunity"/>
        </div>
      </div>
      <!-- 事件分类 -->
      <div class="category">
        <div class="event-boxTitle">
          <span>事件分类</span>
          <span>EVENT CATEGORY</span>
        </div>
        <div class="events-content">
          <visitorCategoriesChart  :currentComunity="currentComunity" />
        </div>
      </div>
      <!-- 人车通行统计 -->
      <div class="abnormal">
        <div class="event-boxTitle">
          <span>事件分布</span>
          <span>EVENT DISTRIBUTION</span>
        </div>
        <div class="events-content">
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
import leftevents from "@/components/echarts/eventsData/leftevents";
import visitorCategoriesChart from "@/components/echarts/eventsData/visitorCategories";
import currentStatisticsChart from "@/components/echarts/eventsData/currentStatistics";
export default {
  name: "Index",
  components: {
    visitorCategoriesChart,
    currentStatisticsChart,
    leftevents
  },
  filters: {
    ellipsis(value) {
      if (!value) return "";
      if (value.length > 5) {
        return value.slice(0, 5) + "...";
      }
      return value;
    }
  },
  computed: {
    ...mapGetters(["currentComunity"])
  },
  data() {
    return {
      eventsList: [
        {
          id: 0,
          time: "2019-01-12 05:13:00",
          type: "社会治安",
          content: "XXX路灯泡坏了"
        },
        {
          id: 1,
          time: "2019-01-12 05:13:00",
          type: "社会治安",
          content: "XXX路灯泡坏了"
        },
        {
          id: 2,
          time: "2019-01-12 05:13:00",
          type: "社会治安",
          content: "XXX路灯泡坏了"
        },
        {
          id: 3,
          time: "2019-01-12 05:13:00",
          type: "社会治安",
          content: "XXX路灯泡坏了"
        }
      ],
      trafficStatisticsData: {
        day_person: 30,
        day_visit_person: 50,
        day_vehicle: 25
      }
    };
  },
  created() {},
  // mounted() {
  //   this.gettrafficApi();
  // },
  watch: {
    currentComunity: {
      handler(val, data) {
        this.gettrafficApi();
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  methods: {
    shrinkage() {
      $(".trafficLeftBox").fadeToggle(2000);
      $(".trafficRightBox").fadeToggle(2000);
      $(".topCenterBox").fadeToggle(2000);
      // this.$Notice.$emit('shrinkageHide', 'trafficData')
    }
    // gettrafficApi() {
    //   let communityId = this.currentComunity.communityId;
    //   let communityChildId = this.currentComunity.communityChildId;
    //   gettrafficdata(communityId, communityChildId).then(res => {
    //     // console.log(res, 'trafficStatisticsData')
    //     if (res.data != null) {
    //       this.trafficStatisticsData = res.data;
    //     }
    //   });
    // }
  }
};
</script>

<style>
@import "~@/assets/styles/main/events.css";</style>
