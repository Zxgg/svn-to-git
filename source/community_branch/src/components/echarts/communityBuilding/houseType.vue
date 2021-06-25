<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
import 'echarts-liquidfill'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getHouseType } from '@/api/community/houseType'

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
    },
    currentComunity: {
      communityId: String,
      communityChildId: String
    }
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        // this.gethousetypeApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      chart: null,
      ratioColor: ['#1991d2', '#3831f3', '#1991d2'],
      xData: []
    }
  },
  mounted () {
    this.xData = this.testData.house.HouseType
    // this.gethousetypeApi()
    this.$nextTick(() => {
      this.initChart()
    })
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
      let xData = [{
        name: "商用",
        value: "20000"
      }, {
        name: "其他",
        value: "10000"
      }, {
        name: "出租",
        value: "58000"
      }, {
        name: "自主",
        value: "60000"
      }]
      xData = this.xData
      var colorList = ["#35A8FF", "#006CED", "#F6B200", "#01FFFD"];
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        title: {
          text: '房屋',
          x: 'center',
          y: 'center',
          textStyle: {
            color: '#fff',
            fontSize: 14
          }
        },
        tooltip: {
          trigger: 'item',
          borderColor: 'rgba(255,255,255,.3)',
          backgroundColor: 'rgba(13,5,30,.6)',
          borderWidth: 1,
          padding: 0,
          formatter: function (parms) {
            var str = parms.marker + "" + parms.data.name + "</br>" +
              "数量：" + parms.data.value + "户</br>" +
              "占比：" + parms.percent + "%";
            return str;
          }
        },
        series: [{
          type: 'pie',
          z: 3,
          startAngle: 90,
          center: ['center', 'center'],
          radius: ['25%', '35%'],
          clockwise: true,
          avoidLabelOverlap: true,
          // hoverOffset: 5,
          itemStyle: {
            normal: {
              color: function (params) {
                return colorList[params.dataIndex]
              }
            }
          },
          label: {
            show: true,
            position: 'outside',
            formatter: '{a|{b}：{d}%}\n{hr|}',
            rich: {
              hr: {
                backgroundColor: 't',
                borderRadius: 3,
                fontSize: 16,
                width: 3,
                height: 3,
                padding: [3, 3, 0, -12]
              },
              a: {
                fontSize: 16,
                padding: [-30, 15, -20, 15]
              }
            }
          },
          labelLine: {
            normal: {
              length: 20,
              length2: 30,
              lineStyle: {
                width: 1
              }
            }
          },
          data: xData
        }]
      })
    },
    gethousetypeApi () {
      this.xData = [{
        name: "商用",
        value: "0"
      }, {
        name: "其他",
        value: "0"
      }, {
        name: "出租",
        value: "0"
      }, {
        name: "自主",
        value: "0"
      }]
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getHouseType(communityId, communityChildId).then(res => {
        // console.log(res, 'ressds')
        if (res && res.data.length > 0) {
          for (let i = 0; i < res.data.length; i++) {
            this.xData[i].value = res.data[i].num
          }
        }
        console.log('leixing:', this.xData)
        this.$nextTick(() => {
          this.initChart()
        })
      })
    }
  }
}
</script>
