<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'

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
      default: '220px'
    }
  },
  data () {
    return {
      chart: null
    }
  },
  mounted () {
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
      var myData = ['外地人口', '本地人口', '访客量统计']
      var lineData = [100, 100, 100]
      var thisYearData = {
        1: [11, 38, 23]
      }
      var timeLineData = [1]
      this.chart = echarts.init(this.$el, 'macarons');
      this.chart.setOption({
        baseOption: {
          timeline: {
            show: false,
            top: 0,
            data: []
          },
          grid: [{
            show: false,
            left: '5%',
            top: '10%',
            bottom: '8%',
            containLabel: true,
            width: '37%'
          }, {
            show: false,
            left: '20%',
            top: '10%',
            bottom: '8%',
            width: '0%'
          }, {
            show: false,
            right: '12%',
            top: '10%',
            bottom: '8%',
            containLabel: true,
            width: '55%'
          }],
          xAxis: [{
            type: 'value',
            inverse: true,
            axisLine: {
              show: false
            },
            axisTick: {
              show: false
            },
            position: 'top',
            axisLabel: {
              show: false
            },
            splitLine: {
              show: false
            },
            splitArea: { show: false }//去除网格区域
          }, {
            gridIndex: 1,
            show: false
          }, {
            gridIndex: 2,
            axisLine: {
              show: false
            },
            axisTick: {
              show: false
            },
            splitArea: { show: false },//去除网格区域
            position: 'top',
            axisLabel: {
              show: false
            },
            splitLine: {
              show: false
            }
          }],
          yAxis: [{
            type: 'category',
            inverse: true,
            position: 'right',
            axisLine: {
              show: false
            },
            axisTick: {
              show: false
            },
            axisLabel: {
              show: false
            },
            splitArea: { show: false },//去除网格区域
            data: myData
          }, {
            gridIndex: 1,
            type: 'category',
            inverse: true,
            position: 'left',
            axisLine: {
              show: false
            },
            axisTick: {
              show: false
            },
            splitArea: { show: false },//去除网格区域
            axisLabel: {
              show: true,
              textStyle: {
                color: '#ffffff',
                fontSize: 12
              }

            },
            data: myData.map(function (value) {
              return {
                value: value,
                textStyle: {
                  align: 'center'
                }
              }
            })
          }, {
            gridIndex: 2,
            type: 'category',
            inverse: true,
            position: 'left',
            axisLine: {
              show: false
            },
            axisTick: {
              show: false
            },
            splitArea: { show: false },//去除网格区域
            axisLabel: {
              show: false

            },
            data: myData
          }],
          series: []

        },
        options: [
          {
            series: [
              {
                type: 'pictorialBar',
                xAxisIndex: 2,
                yAxisIndex: 2,
                symbol: 'rect',
                // itemStyle: {
                //   normal: {
                //     color: 'rgba(54,215,182,0.27)'
                //   }
                // },
                barWidth: 10,
                symbolRepeat: true,
                symbolSize: 14,
                data: lineData,
                barGap: '-100%',
                barCategoryGap: 0,
                label: {
                  normal: {
                    show: true,
                    formatter: (series) => {
                      return thisYearData[timeLineData[0]][series.dataIndex] + '%'
                    },
                    position: 'insideTopRight',
                    textStyle: {
                      color: '#ffffff',
                      fontSize: 12,
                    },
                    offset: [0, -35],
                  }
                },
                z: -100,
                animationEasing: 'elasticOut',
                animationDelay: function (dataIndex, params) {
                  return params.index * 30;
                }
              }, {
                name: '2018',
                type: 'pictorialBar',
                xAxisIndex: 2,
                yAxisIndex: 2,
                symbol: 'rect',
                barWidth: 8,
                itemStyle: {
                  normal: {
                    barBorderRadius: 5,
                    color: '#00beff'
                  }
                },
                symbolRepeat: true,
                symbolSize: 14,
                data: thisYearData[timeLineData[0]],
                animationEasing: 'elasticOut',
                animationDelay: function (dataIndex, params) {
                  return params.index * 30 * 1.1;
                }
              }
            ]
          }
        ]
      })
    }
  }
}
</script>
