<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import $ from "jquery";
import { mapGetters, mapState } from "vuex";
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getCaringPeopleType } from '@/api/caringPeople/caringpeoType'

const animationDuration = 6000

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '100%'
    }
  },
  data () {
    return {
      chart: null,
      xData: [],
    }
  },
  computed: {
    ...mapGetters(["currentComunity"]),
  },
  mounted () {
    // this.$nextTick(() => {
    //   this.initChart()
    // })
    // this.getCarpeoApi()
    this.xData = this.testData.guanai.typeData;
    this.$nextTick(() => {
      this.initChart()
    })
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        this.getCarpeoApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  beforeDestroy () {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart () {
      let xData = []
      xData = this.xData
      var arr = xData
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        color: ['#cd4692', '#9658c3', '#6c6be2', '#01aebf', '#18b794'],
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)",
          textStyle: {
            fontSize: 12,
          }
        },
        legend: {
          data: xData.name,
          orient: 'vertical',
          right: '5%',
          top: '17%',
          bottom: '0%',
          itemWidth: 5,
          itemHeight: 5,
          textStyle: {
            color: 'white',
            fontSize: 12,
          },
          icon: 'circle'
        },
        series: [{
          name: '类型',
          type: 'pie',
          clockwise: false,
          radius: '50%',
          center: ['35%', '50%'],
          hoverAnimation: false,
          roseType: 'radius', //area
          data: xData,
          itemStyle: {
            normal: {
              borderColor: '#273454',
              borderWidth: '1',
            },
          },
          labelLine: {
            normal: {
              length: 12,
              length2: 19,
              lineStyle: {
                width: 1,
              },

            }
          },
          // label: {
          //     normal: {
          //         show: true, 
          //         position: 'inside', 
          //         formatter: '{d}%', 
          //         formatter: function(data){
          //             return data.percent.toFixed(0)+"%"; 
          //         },
          //         textStyle : { 
          //             align : 'center',
          //             baseline : 'middle',
          //             fontFamily : '微软雅黑',
          //             fontSize : 15,
          //             fontWeight : 'bolder'
          //         }
          //     },
          // },
        }],
      })
    },
    getCarpeoApi () {
      this.xData = [
        {
          name: '独居老人',
          value: '0'
        },
        {
          name: '残疾人员',
          value: '0'
        },
        {
          name: '重点治疗人员',
          value: '0'
        },
        {
          name: '安置帮教人员',
          value: '0'
        },
        {
          name: '知名人士',
          value: '0'
        },
        {
          name: '其他关爱人员',
          value: '0'
        }
      ]
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getCaringPeopleType(communityId, communityChildId).then(res => {
        if (res && res.data.length > 0) {
          for (let i = 0; i < res.data.length; i++) {
            this.xData[i].value = res.data[i].num
          }
        }
        this.$nextTick(() => {
          this.initChart()
        })
      })
    }
  }
}
</script>
