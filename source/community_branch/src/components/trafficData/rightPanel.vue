<template>
  <!-- 右侧盒子 -->
  <div class="trafficRightBox">
    <!-- 车辆监控视频 -->
    <div class="peopleSurveillance">
      <div class="traffic-boxTitle">
        <span></span>
        <span>人员通行抓拍</span>
      </div>
      <div class="vehicleVideoAndImage">
        <div class="outer-container">
          <div class="inner-container">
            <el-row class="vehicleVideoAndImage_image">
              <el-col class="vehicleVideoAndImage_image_first"
                      v-for="(item,index) in peopleDetailsData"
                      :key="index">
                <img class="vehicleImage"
                     :src="item.imageUrl"></img>
                <el-row>
                  <el-col>
                    <span class="vehiclePlate">访客类型：</span>
                    <span class="vehiclePlate"
                          style="margin:0">{{item.visitorType}}</span>
                  </el-col>
                  <el-col>
                    <span class="vehicleDetails">地点：</span>
                    <span class="vehicleDetails"
                          style="margin:0"
                          :title="item.address">{{item.address}}</span>
                  </el-col>
                  <el-col>
                    <span class="vehicleDetails">入场时间：</span>
                    <span class="vehicleDetails"
                          style="margin:0"
                          :title="item.admissionTime">{{item.einlass|ellipsis}}</span>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
          </div>
        </div>
      </div>
    </div>
    <div class="vehicleSurveillance">
      <div class="traffic-boxTitle">
        <span></span>
        <span>车辆通行抓拍</span>
      </div>
      <div class="vehicleVideoAndImage">
        <div class="outer-container">
          <div class="inner-container">
            <el-row class="vehicleVideoAndImage_image">
              <el-col class="vehicleVideoAndImage_image_first"
                      v-for="(item,index) in vehicleDetailsData"
                      :key="index">
                <img class="vehicleImage"
                     :src="item.imageUrl"></img>
                <el-row>
                  <el-col>
                    <span class="vehiclePlate">车牌：</span>
                    <span class="vehiclePlate"
                          style="margin:0">{{item.vehiclePlate}}</span>
                  </el-col>
                  <el-col>
                    <span class="vehicleDetails">通道名称：</span>
                    <span class="vehicleDetails"
                          style="margin:0"
                          :title="item.passagewayName">{{item.channelName|ellipsis}}</span>
                  </el-col>
                  <el-col>
                    <span class="vehicleDetails">入场时间：</span>
                    <span class="vehicleDetails"
                          style="margin:0"
                          :title="item.admissionTime">{{item.einlass|ellipsis}}</span>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
import { getpeocremera } from '@/api/trafficData/peocremera'
import { getcarcremera } from '@/api/trafficData/carcremera'
export default {
  name: "Index",
  filters: {
    ellipsis (value) {
      if (!value) return ''
      if (value.length > 10) {
        return value.slice(0, 10) + '...'
      }
      return value
    }
  },
  computed: {
    ...mapGetters(["currentComunity"]),
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        this.getcarcremeraApi()
        this.getpeocremeraApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      vehicleDetailsData: [
        //   {
        //   img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg',
        //   plate: '苏k.000001',
        //   passagewayName: '东门出口车道2',
        //   admissionTime: '2020-11-23 09-52-05'
        // }, {
        //   img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg',
        //   plate: '苏k.000001',
        //   passagewayName: '东门出口车道2',
        //   admissionTime: '2020-11-23 09-52-05'
        // }, {
        //   img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg',
        //   plate: '苏k.000001',
        //   passagewayName: '东门出口车道2',
        //   admissionTime: '2020-11-23 09-52-05'
        // }, {
        //   img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg',
        //   plate: '苏k.000001',
        //   passagewayName: '东门出口车道2',
        //   admissionTime: '2020-11-23 09-52-05'
        // }, {
        //   img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg',
        //   plate: '苏k.000001',
        //   passagewayName: '东门出口车道2',
        //   admissionTime: '2020-11-23 09-52-05'
        // }, {
        //   img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg',
        //   plate: '苏k.000001',
        //   passagewayName: '东门出口车道2',
        //   admissionTime: '2020-11-23 09-52-05'
        // }, {
        //   img: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=381883863,77809968&fm=26&gp=0.jpg',
        //   plate: '苏k.000001',
        //   passagewayName: '东门出口车道2',
        //   admissionTime: '2020-11-23 09-52-05'
        // },
      ],
      peopleDetailsData: [
        //   {
        //   img: 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3848855028,2661177078&fm=26&gp=0.jpg',
        //   visitorType: '陌生人员',
        //   address: '某某社区',
        //   admissionTime: '2020-11-23 09-52-05'
        // }, {
        //   img: 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3848855028,2661177078&fm=26&gp=0.jpg',
        //   visitorType: '预约人员',
        //   address: '某某社区',
        //   admissionTime: '2020-11-23 09-52-05'
        // }, {
        //   img: 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3848855028,2661177078&fm=26&gp=0.jpg',
        //   visitorType: '临时人员',
        //   address: '某某社区',
        //   admissionTime: '2020-11-23 09-52-05'
        // }, {
        //   img: 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3848855028,2661177078&fm=26&gp=0.jpg',
        //   visitorType: '临时人员',
        //   address: '某某社区',
        //   admissionTime: '2020-11-23 09-52-05'
        // }, {
        //   img: 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3848855028,2661177078&fm=26&gp=0.jpg',
        //   visitorType: '陌生人员',
        //   address: '某某社区',
        //   admissionTime: '2020-11-23 09-52-05'
        // }, {
        //   img: 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3848855028,2661177078&fm=26&gp=0.jpg',
        //   visitorType: '陌生人员',
        //   address: '某某社区',
        //   admissionTime: '2020-11-23 09-52-05'
        // }, {
        //   img: 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3848855028,2661177078&fm=26&gp=0.jpg',
        //   visitorType: '陌生人员',
        //   address: '某某社区',
        //   admissionTime: '2020-11-23 09-52-05'
        // }
      ],
    };
  },
  created () { },
  mounted () {
    this.getcarcremeraApi()
    this.getpeocremeraApi()
  },
  methods: {
    getcarcremeraApi () {
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getcarcremera(communityId, communityChildId).then(res => {
        // console.log(res, 'car')
        if (res && res.rows.length > 0) {
          this.vehicleDetailsData = res.rows
        }
      })
    },
    getpeocremeraApi () {
      // let param = {
      //   pageNum: 1,
      //   pageSize: 6
      // }
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getpeocremera(communityId, communityChildId).then(res => {
        console.log(res.rows, 'rows')
        if (res && res.rows.length > 0) {
          res.rows.forEach(row => {
            if (row.visitorType == '1') {
              row.visitorType = '小区人员'
            }
            if (row.visitorType == '2') {
              row.visitorType = '访客'
            }
            if (row.visitorType == '3') {
              row.visitorType = '临时访客'
            }
          });
          this.peopleDetailsData = res.rows
        }
      })
    }
  }
}
</script>

<style>
</style>