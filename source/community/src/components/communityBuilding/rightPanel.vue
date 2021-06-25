<template>
  <!-- 右侧盒子 -->
  <div class="communityRightBox">
    <!-- 出租房屋数量比例 -->
    <div class="rentalHousingNum">
      <!-- <div class="community-boxTitle">
        <span></span>
        <span>入住率</span>
      </div> -->
      <!-- <div style="width:100%;height:100%">
        <houseNumberScaleChart :currentComunity="currentComunity" />
      </div> -->
      <div class="community-boxTitle">
        <span></span>
        <span>社区网格面积分布</span>
      </div>
      <el-table :data="peopleHousingInfoList"
                style="color:#ffffff"
                height="90%">
        <el-table-column prop="gridName"
                         width="90"
                         label="所属网格">
        </el-table-column>
        <!-- <el-table-column prop="gridMember"
                         label="网格员">
        </el-table-column> -->
        <!-- <el-table-column prop="phone"
                         label="联系方式">
        </el-table-column> -->
        <el-table-column prop="range"
                         label="网格范围">
        </el-table-column>
        <el-table-column prop="buildingNum"
                         width="70"
                         label="楼栋数">
        </el-table-column>
        <el-table-column prop="area"
                         width="70"
                         label="面积">
        </el-table-column>
      </el-table>
    </div>
    <!-- 社区设施展示 -->
    <div class=" communityFacilitiesDisplay">

      <div class="community-boxTitle">
        <span></span>
        <span>小区户数</span>
      </div>
      <el-table :data="xiaoquhushuList"
                style="color:#ffffff"
                height="90%">
        <el-table-column prop="xuhao"
                         width="50"
                         label="序号">
        </el-table-column>
        <el-table-column prop="name"
                         label="小区名称">
        </el-table-column>
        <el-table-column prop="hushu"
                         label="户数">
        </el-table-column>
        <el-table-column prop="renshu"
                         label="人数">
        </el-table-column>
        <el-table-column prop="area"
                         label="面积">
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import { mapGetters, mapState } from "vuex";
import houseNumberScaleChart from '@/components/echarts/communityBuilding/houseNumberScale'
import houseTypeChart from '@/components/echarts/communityBuilding/houseType'
import { listCommunity_houses, selectVehicleInformationVoList, queryHouseAndPersonList } from '@/api/communityUnit/community_houses' // 查询社区房屋信息列表、根据房屋id查询关联车辆
export default {
  name: "Index",
  components: { houseNumberScaleChart, houseTypeChart },
  data () {
    return {
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
      peopleHousingInfoList: [],
      xiaoquhushuList: []
    };
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
  computed: {
    ...mapGetters(["currentComunity"]),
  },
  mounted () {
    this.xiaoquhushuList = this.testData.house.xiaoquhushuList
    this.peopleHousingInfoList = this.testData.renyuan.peopleHousingInfoList
    console.log('454:', this.peopleHousingInfoList)
  }
}
</script>

<style>
</style>