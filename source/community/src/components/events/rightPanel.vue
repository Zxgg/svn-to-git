<template>
  <!-- 右侧盒子 -->
  <div class="trafficRightBox">
    <div class="eventRightBox">
      <div class="event-boxTitle">
        <span>社区事件</span>
        <span>COMMUNITY EVENTS</span>
      </div>
      <div class="events-content">
        <rightremind :currentComunity="currentComunity"/>
      </div>
    </div>
    <div class="vehicle">
      <div class="event-boxTitle">
        <span>社区志愿者</span>
        <span>COMMUNITY VOLUNTEER</span>
      </div>
      <div class="events-content">
        <rightgrid style="width:100%;height:100%" :currentComunity="currentComunity"/>
      </div>
    </div>
       <!-- <div class="topxianshi">
        <div>
           <topgrid style="width:100%;height:100%" :currentComunity="currentComunity"/>
        </div>
      </div> -->
    </div>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
import rightremind from "@/components/echarts/eventsData/rightremind";
import rightgrid from "@/components/echarts/eventsData/rightgrid";
import topgrid from "./components/topgrid";
export default {
  name: "Index",
  components: { rightremind, rightgrid,topgrid },
  filters: {
    ellipsis(value) {
      if (!value) return "";
      if (value.length > 10) {
        return value.slice(0, 10) + "...";
      }
      return value;
    }
  },
  computed: {
    ...mapGetters(["currentComunity"])
  },
    watch: {
    currentComunity: {
      handler (val, data) {
        this.getcomcarApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  created() {},
  mounted() {},
  methods: {
        getcomcarApi () {
      this.communityVehicleStatisticsData = []
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getCommunityVehicle(communityId, communityChildId).then(res => {
        // console.log(res, 'res...')
        if (res && res.data.length > 0) {
          this.communityVehicleStatisticsData = res.data
          console.log('communityVehicleStatisticsData:', this.communityVehicleStatisticsData)
        }
      })
    }
  }
};
</script>
<style>
@import "~@/assets/styles/main/events.css";
</style>
