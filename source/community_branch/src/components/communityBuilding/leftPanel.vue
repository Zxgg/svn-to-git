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
    <!-- 左侧盒子 -->
    <div class="communityLeftBox">
      <!-- 社区户数及居住人数 -->
      <div class="communityNumber">
        <div class="community-boxTitle">
          <span></span>
          <span>房屋类型</span>
        </div>
        <!-- <div class="communityFacilities">
            <div class="outer-container">
              <div class="inner-container">
                <div v-for="(item,index) in communityFacilitiesData"
                     :key="index">
                  <div class="communityFacilities_Out">
                    <div class="communityFacilities_inside">
                      <img class="communityFacilitiesImg"
                           :src="item.img">
                      <span>{{item.name}}</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div> -->
        <div style="width:100%;height:100%">
          <houseTypeChart :currentComunity="currentComunity" />
        </div>
        <!-- <div class="communityResidents">
          <span></span>
          <span class='community1'>小区：{{numLiveData.homeNum}}</span>
          <span class='community2'>户数：{{numLiveData.liveNum}}</span>
        </div>
        <div style="width:100%;height:80%">
          <communityResidentsChart :currentComunity="currentComunity" />
        </div> -->

      </div>
      <!-- 社区（楼栋）的建筑面积m2 -->

      <div class="communityBuilding">
        <div class="community-boxTitle">
          <span></span>
          <span>房屋信息搜索</span>
        </div>
        <!-- <div style="width:100%;height:100%">
          <img :src="communityArchitectureImg"
               class="communityArchitectureImg">
          <div class="communityArchitectureImg_title"></div>
          <span class="communityArchitectureArea">{{communityArea}}</span>
        </div> -->
        <search />
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import { mapGetters, mapState } from "vuex";
import communityResidentsChart from '@/components/echarts/communityBuilding/communityResidents'
import houseNumberScaleChart from '@/components/echarts/communityBuilding/houseNumberScale'
import houseTypeChart from '@/components/echarts/communityBuilding/houseType'
import search from './search'
import { getHouseBuilding } from '@/api/community/houseBuilding'
import { getHouseNum } from '@/api/community/houseNum'
import { listCommunity_houses, selectVehicleInformationVoList, queryHouseAndPersonList } from '@/api/communityUnit/community_houses' // 查询社区房屋信息列表、根据房屋id查询关联车辆

export default {
  name: "Index",
  components: { communityResidentsChart, houseNumberScaleChart, houseTypeChart, search },
  data () {
    return {
      switchImg: require('@/assets/images/peopleinfo/katong.png'),
      communityArchitectureImg: require('@/assets/images/home/communityArchitecture.png'),
      communityArea: '',
      numLiveData: {
        homeNum: 0,
        liveNum: 0
      },
      xiaoquhushuList: []
    };
  },
  computed: {
    ...mapGetters(["currentComunity"]),
  },
  created () { },
  mounted () {
    this.xiaoquhushuList = this.testData.house.xiaoquhushuList
    // this.gethouseareaApi()
    // this.gethousenumApi()
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        // this.gethouseareaApi()
        // this.gethousenumApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  methods: {
    shrinkage () {
      $(".communityLeftBox").fadeToggle(2000);
      $(".communityRightBox").fadeToggle(2000);
      this.$Notice.$emit('shrinkageHide', 'communityBuilding')
    },
    gethouseareaApi () {
      this.communityArea = ''
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getHouseBuilding(communityId, communityChildId).then(res => {
        console.log(res, 'area')
        if (res.data != null) {
          this.communityArea = res.data.community_area
        }
      })
    },
    gethousenumApi () {
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getHouseNum(communityId, communityChildId).then(res => {
        console.log(res, 'housenum')
        if (res.data != null) {
          this.numLiveData.liveNum = res.data.householdsCount
          this.numLiveData.homeNum = res.data.communityChildCount
        }
      })
    },
  }
};
</script>

<style scoped="true">
@import "~@/assets/styles/main/communityBuilding.css";
.community1 {
  position: relative;
  left: 5%;
}
.community2 {
  position: relative;
  right: 5%;
}
</style>