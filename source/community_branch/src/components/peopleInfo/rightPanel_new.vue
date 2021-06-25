<template>
  <!-- 右侧盒子 -->
  <div class="peopleRightBox">
    <el-row :gutter="0"
            style="width:100%;height:26%;margin-top:5px">
      <el-col :span="24"
              style="width:100%;height:100%;">
        <!-- 人员住房信息 -->
        <div class="personnelHousingInformation">
          <div class="people-boxTitleM">
            <!-- <span></span> -->
            <span>人员分布信息</span>
            <span>PERSONNEL DISTRIBUTION INFORMATION</span>
          </div>
          <!-- <div style="width:100%;height:100%;">
            <personnelHousingInformationCharts :currentComunity="currentComunity" />
          </div> -->
          <div class="person-table">
            <el-table :data="peopleHousingInfoList"
                      style="color:#ffffff;width:90%;margin-left:5%"
                      height="100%">
              <el-table-column prop="gridName"
                               label="所属网格">
              </el-table-column>
              <el-table-column prop="gridMember"
                               label="网格员">
              </el-table-column>
              <!-- <el-table-column prop="phone"
                               width="108"
                               label="联系方式">
              </el-table-column> -->
              <!-- <el-table-column prop="range"
                             label="网格范围">
            </el-table-column> -->
              <el-table-column prop="permanentPer"
                               label="常驻人员">
              </el-table-column>
              <el-table-column prop="partyMember"
                               width="48"
                               label="党员">
              </el-table-column>
              <el-table-column prop="over80"
                               width="100"
                               label="80岁以上老人">
              </el-table-column>
            </el-table>
          </div>
        </div>
      </el-col>
      <!-- 文化程度统计 -->
      <!-- <el-col :span="16"
              style="width:49%;height:100%;margin-left:2%">
        <div class="numberCommunity">
          <div class="people-boxTitle">
            <span></span>
            <span>人员类型统计</span>
          </div>
          <div style="width:100%;height:100%">
            <personTypeStatisticsCharts :currentComunity="currentComunity" />
          </div>
        </div>
      </el-col> -->
    </el-row>
    <!-- 关爱人员信息 -->
    <div class="carePeopleInfo">
      <div class="people-boxTitleM">
        <!-- <span></span> -->
        <span>关爱人员信息</span>
        <span>CARE STAFF INFORMATION</span>
      </div>
      <div style="width:100%;height:100%">
        <carePersonInformationCharts :currentComunity="currentComunity" />
      </div>
      <!-- <div style="width:100%;height:70%">
        <carePersonChart :currentComunity="currentComunity" />
      </div> -->
    </div>
    <!-- 人口流动情况 -->
    <div class="populationMobility">
      <!-- <div class="people-boxTitle">
        <span></span>
        <span>人口流动情况</span>
      </div>
      <div style="width:100%;height:100%">
        <populationMobilityCharts />
      </div> -->
      <div class="people-boxTitleM">
        <!-- <span></span> -->
        <span>关注人员信息</span>
        <span>STAFF INFORMATION</span>
      </div>
      <div class="controlperson">
        <el-carousel :interval="30000"
                     type="card"
                     height="80%"
                     indicator-position="none"
                     class="controlperson-carousel">
          <el-carousel-item v-for="(item,index) in peopleDetailsData"
                            :class="returnClass(item)"
                            :key="index">
            <div class="controlperson-box"
                 @click="getDetail(item)">
              <div class="controlperson-bliack"></div>
              <img class="controlperson-img"
                   :src="item.imageUrl">
              <div class="medium">
                <div style=" text-align: center;">
                  <span class="controlperson-nametext"
                        style="margin:0">{{item.name}}</span>
                  <span class="controlperson-name">{{' ' + item.taskType}}</span>
                </div>

                <div style=" text-align: center;">

                  <span class="controlperson-nametext"
                        :title="item.cardID">{{item.cardID | ellipsis}}</span>
                </div>
              </div>
            </div>
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>
    <!-- 特殊人群、管控人群 -->
    <div class="specialPeople">
      <div class="people-boxTitleM">
        <!-- <span></span> -->
        <span>特殊人群/管控人群</span>
        <span>SPECIAL POPULATION / CONTROL CROWD</span>
      </div>
      <div style="width:100%;height:100%">
        <specialPersonStatisticsCharts :currentComunity="currentComunity" />
      </div>
    </div>

    <!-- 查询布控人员信息 对话框 -->
    <!-- <el-dialog :title="title"
               :visible.sync="open"
               width="50%"
               append-to-body
               class="dialogPeopleInfoClass">
      <!-- <el-form ref="form"
               :model="form"
               :inline="true"
               label-width="85px">
        <el-form-item label="头像:"
                      prop="avatarId">
          <div style="width: 400px; height: 200px; position: absolve">
            <img style="margin: 0 20px; width: 180px; height: 200px"
                 :src="form.imageUrl">
          </div>
        </el-form-item>
        <el-form-item label="姓名:"
                      prop="name">
          <span class="addr">{{ form.name }}</span>
        </el-form-item>
        <el-form-item label="性别:"
                      prop="sex">
          <span class="addr">{{ form.sex }}</span>
        </el-form-item>
        <el-form-item label="年龄:"
                      prop="age">
          <span class="addr">{{ form.age }}</span>
        </el-form-item>
        <el-form-item label="联系方式:"
                      prop="phone">
          <span class="addr">{{ form.phone }}</span>
        </el-form-item>
        <el-form-item label="籍贯:"
                      prop="jiguan">
          <span class="addr">{{ form.jiguan }}</span>
        </el-form-item>
        <el-form-item label="身份证:"
                      prop="cardID">
          <span class="addr">{{ form.cardID | ellipsis }}</span>
        </el-form-item>
        <el-form-item label="任务类型:"
                      prop="taskType">
          <span class="addr">{{ form.taskType }}</span>
        </el-form-item>
        <el-form-item label="房屋地址:"
                      prop="fangwudizhi">
          <span class="addr">{{ form.fangwudizhi }}</span>
        </el-form-item>
      </el-form> -->
    <!-- <div slot="footer"
           class="dialog-footer">
        <el-button type="primary"
                   @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div> -->
    <!-- </el-dialog> -->
    <el-dialog :visible.sync="open"
               width="45%"
               append-to-body
               :show-close="false"
               class="dialogPeopleInfoClass">
      <div class="dialog_title">
        <el-col :span="23">{{title}}</el-col>
        <el-col :span="1"><span @click="open = false">{{"×"}}</span></el-col>
      </div>
      <div style="width:100%;height:100%">
        <el-col :span="10">
          <div class="dialogPeopleInfoClass_pic">
            <img style="width:94%;height:92%;margin:3%"
                 :src="form.imageUrl">
          </div>
        </el-col>
        <el-col :span="13">
          <div class="dialogPeopleInfoClass_info">
            <el-form ref="form"
                     :model="form"
                     label-width="100px">
              <el-form-item label="姓名:"
                            prop="name"
                            style=" padding-top: 15px;">
                <span class="addr">{{ form.name || '暂无'}}</span>
              </el-form-item>
              <el-form-item label="性别:"
                            prop="sex">
                <span class="addr">{{ form.sex || '暂无'}}</span>
              </el-form-item>
              <el-form-item label="年龄:"
                            prop="age">
                <span class="addr">{{ form.age || '暂无'}}</span>
              </el-form-item>
              <el-form-item label="联系方式:"
                            prop="phone">
                <span class="addr">{{ form.phone || '暂无'}}</span>
              </el-form-item>
              <el-form-item label="籍贯:"
                            prop="jiguan">
                <span class="addr">{{ form.jiguan || '暂无'}}</span>
              </el-form-item>
              <el-form-item label="身份证:"
                            prop="cardID">
                <span class="addr">{{ form.cardID || '暂无' | ellipsis }}</span>
              </el-form-item>
              <el-form-item label="任务类型:"
                            prop="taskType">
                <span class="addr">{{ form.taskType || '暂无'}}</span>
              </el-form-item>
              <el-form-item label="房屋地址:"
                            prop="fangwudizhi">
                <span class="addr">{{ form.fangwudizhi || '暂无'}}</span>
              </el-form-item>
            </el-form>
          </div>
        </el-col>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import $ from "jquery";
import { mapGetters, mapState } from "vuex";
import populationMobilityCharts from '@/components/echarts/peopleInfo/populationMobility';
import carePersonInformationCharts from '@/components/echarts/peopleInfo/carePersonInformation_new';
import personTypeStatisticsCharts from '@/components/echarts/peopleInfo/personTypeStatistics';
import specialPersonStatisticsCharts from '@/components/echarts/peopleInfo/specialPersonStatistics_new';
import personnelHousingInformationCharts from '@/components/echarts/peopleInfo/personnelHousingInformation';
import carePersonChart from '@/components/echarts/main/carePerson'
import { getBlackPerson } from '@/api/PerInformation/monitorPeo'
import { getPeopleInfoApi } from '@/api/PerInformation/peopleInfo'

export default {
  name: "Index",
  components: { populationMobilityCharts, carePersonInformationCharts, personTypeStatisticsCharts, personnelHousingInformationCharts, specialPersonStatisticsCharts, carePersonChart },
  data () {
    return {
      peopleDetailsData: [],
      peopleHousingInfoList: [],
      title: '',
      open: false,
      form: {}
    }
  },
  computed: {
    ...mapGetters(["currentComunity"]),
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        // this.getMonitorApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  filters: {
    ellipsis (value) {
      if (!value) return ''
      if (value.length > 13) {
        return value.slice(0, 6) + '********' + value.slice(-4)
      }
      return value
    }
  },
  created () { },
  mounted () {
    // this.getMonitorApi()
    this.peopleDetailsData = this.testData.renyuan.blackPersonData
    this.peopleHousingInfoList = this.testData.renyuan.peopleHousingInfoList
    this.$Notice.$on('communityGrid', (val) => {
      // this.getCode(val)
    })
  },
  methods: {
    returnClass (item) {
      if (item.taskType === "人员黑名单") return "bukongbox"
      else return ""
    },
    ellipsis (value) {
      if (!value) return ''
      if (value.length > 13) {
        return value.slice(0, 6) + '********' + value.slice(-4)
      }
      return value
    },
    getRowClass ({ row, column, rowIndex, columnIndex }) {
      return "background:#222751;color:#fff;border-bottom-color:#182751;";
    },
    getMonitorApi () {
      this.peopleDetailsData = []
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getBlackPerson(communityId, communityChildId).then(res => {
        // console.log(res, 'monitor')
        if (res.data != null) {
          this.peopleDetailsData = res.data
        }
      })
    },
    getDetail (info) {
      this.form = info
      this.title = '关注人员信息'
      this.open = true
    },
    getCode (code) {
      getPeopleInfoApi(code).then(res => {
        if (res && res.data.blackPersonData.length > 0) {
          this.peopleDetailsData = res.data.blackPersonData
        }
      })
    }
  }
};
</script>

<style scoped="true">
@import "~@/assets/styles/main/peopleInfo.css";
</style>
<style>
.guanaibox {
  border: yellow;
}
.bukongbox {
  border: red !important;
}
.el-carousel__item--card {
  border-color: red !important;
}
</style>>