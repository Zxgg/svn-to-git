<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getAgeStatistics } from '@/api/PerInformation/ageStatistics'

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
        // this.getAgeApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      communityId: '',
      communityChildId: '',
      chart: null,
      xData: [],
      yData: []
    }
  },
  mounted () {
    this.xData = this.testData.renyuan.ageData.x
    this.yData = this.testData.renyuan.ageData.y
    // this.getAgeApi()
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
      let xData = ["20-29岁", "30-39岁", "40-49岁", "50岁以上"]
      let yData = [20, 30, 40, 50]
      xData = this.xData
      yData = this.yData
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        // backgroundColor: '#000E1A',
        color: ["#3cefff"],
        tooltip: {
          formatter (a) {
            return a.name + '的人员占比为:' + a.data.toFixed(2) + "%"
          }
        },
        grid: {
          containLabel: true,
          height: '70%',
          y: "3%",
          left: '2%',
          top: '5%'
        },
        xAxis: [{
          type: "category",
          data: xData,
          axisTick: {
            alignWithLabel: true
          },
          nameTextStyle: {
            color: "#82b0ec"
          },
          axisLine: {
            lineStyle: {
              color: "#82b0ec"
            }
          },
          axisLabel: {
            textStyle: {
              color: "#fff"
            },
            rotate: 0,//倾斜度 -90 至 90 默认为0
          },
          splitArea: { show: false }//设置网格区域
        }],
        yAxis: [{
          type: "value",
          // max: '100',
          axisLabel: {
            textStyle: {
              color: "#fff"
            },
            formatter: "{value}%"
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: ['#434A7E'],
              width: 1,
              type: 'dashed'
            }
          },
          axisLine: {
            show: false
          },
          splitArea: { show: false }//设置网格区域
        }],
        series: [
          {
            name: "",
            type: "pictorialBar",
            symbolSize: [20, 10],
            symbolOffset: [0, -5],
            symbolPosition: "end",
            z: 12,
            label: {
              normal: {
                show: true,
                position: "top",
                // formatter: "{c}%"
                formatter (c) {
                  return c.data.toFixed(2) + "%"
                }
              }
            },
            data: yData
          },
          {
            name: "",
            type: "pictorialBar",
            symbolSize: [20, 10],
            symbolOffset: [0, 5],
            z: 12,
            data: yData
          },
          {
            type: "bar",
            itemStyle: {
              normal: {
                opacity: 0.7
              }
            },
            barWidth: "20",
            data: yData,
            markLine: {
              silent: true,
              symbol: "none",
              label: {
                position: "middle",
                formatter: "{b}"
              },

            }
          },
          {
            type: 'effectScatter',
            silent: true,
            tooltip: {
              show: false
            },
            zlevel: 3,
            symbolSize: [20, 10],
            // symbolOffset: [0, 0],
            symbolPosition: 'end',
            showEffectOn: 'render',
            rippleEffect: {
              brushType: 'stroke',
              color: '#3cefff',
              scale: 4
            },
            itemStyle: {
              color: '#3cefff'
            },
            hoverAnimation: true,
            data: [0, 0, 0, 0, 0, 0, 0]
          },
        ]
      })
    },
    getAgeApi () {
      this.yData = []
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getAgeStatistics(communityId, communityChildId).then(res => {
        if (res && res.data) {
          // this.xData = res.data.name
          // this.yData = res.data.percent
          this.xData = ["19岁以下", '20-29岁', "30-39岁", "40-49岁", "50岁以上"]
          this.yData.push(res.data.y_19 * 100, res.data.y_29 * 100, res.data.y_39 * 100, res.data.y_49 * 100, res.data.y_50 * 100)
          // console.log(this.yData, 'ydata')
        }
        this.$nextTick(() => {
          this.initChart()
        })
      })
    }
  }
}
</script>
