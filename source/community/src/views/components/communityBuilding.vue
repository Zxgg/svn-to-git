<template>
  <div>
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
        <!-- 社区户数及居住人数 -->
        <div class="communityNumber">
          <div class="boxTitle">
            <span></span>
            <span>社区户数及居住人数</span>
            <span></span>
            <span>户数</span>
            <span class="el-icon-share"></span>
            <span>居住人数</span>
          </div>
          <div class="communityResidents">
            <span></span>
            <span>{{numLiveData.homeNum}}</span>
            <span class="el-icon-share"></span>
            <span>{{numLiveData.liveNum}}</span>
          </div>
          <div>
            <communityResidentsChart />
          </div>
        </div>
        <!-- 社区（楼栋）的建筑面积m2 -->

        <div class="communityBuilding">
          <div class="boxTitle">
            <span></span>
            <span>社区（楼栋）的建筑面积m2</span>
          </div>
          <div>
            <img :src="communityArchitectureImg"
                 class="communityArchitectureImg">
            <div class="communityArchitectureImg_title"></div>
            <span class="communityArchitectureArea">{{communityArea}}</span>
          </div>
        </div>
      </div>
      <!-- 右侧盒子 -->
      <div>
        <!-- 出租房屋数量比例 -->
        <div class="rentalHousingNum">
          <div class="boxTitle">
            <span></span>
            <span>入住率</span>
          </div>
          <div>
            <houseNumberScaleChart />
          </div>
        </div>
        <!-- 社区设施展示 -->
        <div class=" communityFacilitiesDisplay">
          <div class="boxTitle">
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
          <div>
            <houseTypeChart />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import $ from "jquery";
import communityResidentsChart from '@/components/echarts/communityBuilding/communityResidents'
import houseNumberScaleChart from '@/components/echarts/communityBuilding/houseNumberScale'
import houseTypeChart from '@/components/echarts/communityBuilding/houseType'
export default {
  name: "Index",
  components: { communityResidentsChart, houseNumberScaleChart, houseTypeChart },
  data () {
    return {
      switchImg: require('@/assets/images/peopleinfo/katong.png'),
      communityArchitectureImg: require('@/assets/images/home/communityArchitecture.png'),
      communityArea: '300,000',
      communityFacilitiesData: [
        { name: '健康设施', img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg' },
        { name: '公共自行车', img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg' },
        { name: '公共厕所', img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg' },
        { name: '公共座椅', img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg' },
        { name: '公共喷泉', img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg' },
        { name: '公共照明设备', img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg' },
        { name: '公共设施', img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg' },
        { name: '公共小板凳', img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg' },

      ],
      numLiveData: {
        homeNum: 9999,
        liveNum: 8888
      }
    };
  },
  created () { },
  mounted () { },
  methods: {
    shrinkage () {
      $(".mainContent > div:first-child").fadeToggle(2000);
      $(".mainContent > div:last-child").fadeToggle(2000);
      this.$Notice.$emit('shrinkageHide', 'communityBuilding')
    }
  }
};
</script>

<style scoped="true">
@import "~@/assets/styles/main/communityBuilding.css";
</style>
