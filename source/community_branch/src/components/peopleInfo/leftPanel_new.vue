<template>
  <div style="overflow-y:none;width:100%;height:100%">
    <!-- <div>
      <el-tooltip class="item"
                  effect="dark"
                  content="隐藏显示"
                  placement="top">
        <span @click="shrinkage()"
              class="switchImg el-icon-view"></span>
      </el-tooltip>
    </div> -->
    <!-- 左侧盒子 -->
    <div class="peopleLeftBox">
      <!-- 人员信息统计 -->
      <div class="nationalStatistics">
        <div class="people-boxTitleM">
          <span>人员信息统计</span>
          <span>PERSONNEL INFORMATION STATISTICS</span>
        </div>
        <div class="people-count">
          <div class="peopleManAndWomanIcon">
          </div>
          <div>
            <span class="people-countTotalText"> 人员总数：</span>
          </div>
          <div class="people-countTotal"
               v-if="count">{{count}}</div>
          <div>人</div>
        </div>
        <div class="people-sex"
             style="width:80%;height:60%;position: relative;">
          <div><span v-if="man"
                  style="margin-left:20px">{{"男："+man}}</span></div>
          <div><span v-if="girl"
                  style="margin-left:20px">{{"女："+girl}}</span></div>
          <!-- <personnelInformationStatisticsCharts :currentComunity="currentComunity" /> -->
          <div :style="{width:'100%'}"></div>
          <div v-if="girl"
               :style="{width:girl}"></div>
          <el-col :span="12"
                  v-if="manValue">{{"人数数量："+manValue+"人"}}</el-col>
          <el-col :span="12"
                  v-if="girlValue">{{"人数数量："+girlValue+"人"}}</el-col>
        </div>
      </div>
      <div class="personnelInformationStatistics">
        <div class="people-boxTitleM">
          <span>文化程度统计</span>
          <span>Educational LEVEL STATISTICS</span>
        </div>
        <div style="width:100%;height:100%">
          <educationLevelStatisticsCharts :currentComunity="currentComunity" />
        </div>
      </div>
      <!-- 年龄统计 -->
      <div class="ageStatistics">
        <div class="people-boxTitle">
          <span>年龄统计</span>
          <span>AGE STATISTICS</span>
        </div>
        <div style="width:90%;height:100%;margin-left:5%">
          <ageStatisticsCharts :currentComunity="currentComunity" />
        </div>
      </div>
      <!-- 民族统计 -->
      <div class="levelOfEducation">
        <div class="people-boxTitleM">
          <span>少数民族统计</span>
          <span>Ethnic STATISTICS</span>
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
import educationLevelStatisticsCharts from '@/components/echarts/peopleInfo/educationLevelStatistics_new1';
import ageStatisticsCharts from '@/components/echarts/peopleInfo/ageStatistics_new';
import nationalStatisticsCharts from '@/components/echarts/peopleInfo/nationalStatistics_new';
import specialPersonStatisticsCharts from '@/components/echarts/peopleInfo/specialPersonStatistics';
import personTypeStatisticsCharts from '@/components/echarts/peopleInfo/personTypeStatistics';
import { getPeopleInfoApi } from '@/api/PerInformation/peopleInfo'
export default {
  name: "Index",
  components: { personnelInformationStatisticsCharts, educationLevelStatisticsCharts, ageStatisticsCharts, nationalStatisticsCharts, specialPersonStatisticsCharts, personTypeStatisticsCharts },
  data () {
    return {
      switchImg: require('@/assets/images/peopleinfo/katong.png'),
      peopleDetailsData: [],
      man: 0,
      girl: 0,
      manValue: 0,
      girlValue: 0,
      count: 0,
      peopleCode: ''
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
  mounted () {
    this.$Notice.$on('communityGrid', (val) => {
      this.getCode(val)
    })
    this.$Notice.$on('communityGridReturn', (val) => {
      this.initSex();
    })
    this.initSex();
  },
  methods: {
    shrinkage () {
      $(".peopleLeftBox").fadeToggle(2000);
      $(".peopleRightBox").fadeToggle(2000);
    },
    getCode (code) {
      getPeopleInfoApi(code).then(res => {
        if (res && res.data.sexData.length > 0) {
          this.peopleDetailsData = res.data.sexData
          this.man = (this.peopleDetailsData[0].value / (Number(this.peopleDetailsData[0].value) + Number(this.peopleDetailsData[1].value))).toFixed(2) * 100 + '%'
          this.girl = (this.peopleDetailsData[1].value / (Number(this.peopleDetailsData[0].value) + Number(this.peopleDetailsData[1].value))).toFixed(2) * 100 + '%'
          this.count = Number(this.peopleDetailsData[0].value) + Number(this.peopleDetailsData[1].value)
          this.manValue = this.peopleDetailsData[0].value
          this.girlValue = this.peopleDetailsData[1].value
        }
        // else {
        //   this.peopleDetailsData = this.testData.renyuan.sexData
        //   this.man = (this.peopleDetailsData[0].value / (this.peopleDetailsData[0].value + this.peopleDetailsData[1].value)).toFixed(2) * 100 + '%'
        //   this.girl = (this.peopleDetailsData[1].value / (this.peopleDetailsData[0].value + this.peopleDetailsData[1].value)).toFixed(2) * 100 + '%'
        //   this.count = this.peopleDetailsData[0].value + this.peopleDetailsData[1].value
        //   this.manValue = this.peopleDetailsData[0].value
        //   this.girlValue = this.peopleDetailsData[1].value
        // }
      })
      // .catch(err => {
      //   this.peopleDetailsData = this.testData.renyuan.sexData
      //   this.man = (this.peopleDetailsData[0].value / (this.peopleDetailsData[0].value + this.peopleDetailsData[1].value)).toFixed(2) * 100 + '%'
      //   this.girl = (this.peopleDetailsData[1].value / (this.peopleDetailsData[0].value + this.peopleDetailsData[1].value)).toFixed(2) * 100 + '%'
      //   this.count = this.peopleDetailsData[0].value + this.peopleDetailsData[1].value
      //   this.manValue = this.peopleDetailsData[0].value
      //   this.girlValue = this.peopleDetailsData[1].value
      // })
    },
    initSex () {
      this.peopleDetailsData = this.testData.renyuan.sexData
      this.man = (this.peopleDetailsData[0].value / (this.peopleDetailsData[0].value + this.peopleDetailsData[1].value)).toFixed(2) * 100 + '%'
      this.girl = (this.peopleDetailsData[1].value / (this.peopleDetailsData[0].value + this.peopleDetailsData[1].value)).toFixed(2) * 100 + '%'
      this.count = this.peopleDetailsData[0].value + this.peopleDetailsData[1].value
      this.manValue = this.peopleDetailsData[0].value
      this.girlValue = this.peopleDetailsData[1].value
      // getPeopleInfoApi(1).then(res => {
      //   if (res && res.data) {
      //     this.peopleDetailsData = res.data.sexData
      //     this.man = (this.peopleDetailsData[0].value / (this.peopleDetailsData[0].value + this.peopleDetailsData[1].value)).toFixed(2) * 100 + '%'
      //     this.girl = (this.peopleDetailsData[1].value / (this.peopleDetailsData[0].value + this.peopleDetailsData[1].value)).toFixed(2) * 100 + '%'
      //     this.count = this.peopleDetailsData[0].value + this.peopleDetailsData[1].value
      //     this.manValue = this.peopleDetailsData[0].value
      //     this.girlValue = this.peopleDetailsData[1].value
      //   } else {

      //   }
      // }).catch(err => {
      //   this.peopleDetailsData = this.testData.renyuan.sexData
      //   this.man = (this.peopleDetailsData[0].value / (this.peopleDetailsData[0].value + this.peopleDetailsData[1].value)).toFixed(2) * 100 + '%'
      //   this.girl = (this.peopleDetailsData[1].value / (this.peopleDetailsData[0].value + this.peopleDetailsData[1].value)).toFixed(2) * 100 + '%'
      //   this.count = this.peopleDetailsData[0].value + this.peopleDetailsData[1].value
      //   this.manValue = this.peopleDetailsData[0].value
      //   this.girlValue = this.peopleDetailsData[1].value
      // })
    }
  }
}
</script>

<style scoped="true">
@import "~@/assets/styles/main/peopleInfo.css";
</style>