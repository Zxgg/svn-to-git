<template>
  <div :class="className"
       id="chart"
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
      default: '100%'
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
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        tooltip: { //提示框组件
          trigger: 'axis',
          formatter: '{b}<br />{a0}: {c0}<br />{a1}: {c1}',
          axisPointer: {
            type: 'shadow',
            label: {
              backgroundColor: '#6a7985'
            }
          },
          textStyle: {
            color: '#fff',
            fontStyle: 'normal',
            fontFamily: '微软雅黑',
            fontSize: 12,
          }
        },
        grid: {
          left: '15%',
          right: '0',
          bottom: '10%',
          top: '40%',
          //	padding:'0 0 10 0',
          containLabel: true,
        },
        legend: {//图例组件，颜色和名字
          right: '10%',
          top: '10%',
          itemGap: 16,
          itemWidth: 10,
          itemHeight: 10,
          data: [{
            name: '出行人数',
            //icon:'image://../wwwroot/js/url2.png', //路径
          },
          {
            name: '出行车辆',
          }],
          textStyle: {
            color: '#a8aab0',
            fontStyle: 'normal',
            fontFamily: '微软雅黑',
            fontSize: 12,
          }
        },
        xAxis: [
          {
            type: 'category',
            //	boundaryGap: true,//坐标轴两边留白
            data: ['8:00', '9:100', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00'],
            axisLabel: { //坐标轴刻度标签的相关设置。
              //		interval: 0,//设置为 1，表示『隔一个标签显示一个标签』
              //	margin:15,
              textStyle: {
                color: '#078ceb',
                fontStyle: 'normal',
                fontFamily: '微软雅黑',
                fontSize: 12,
              },
              rotate: 50,
            },
            axisTick: {//坐标轴刻度相关设置。
              show: false,
            },
            axisLine: {//坐标轴轴线相关设置
              lineStyle: {
                color: '#fff',
                opacity: 0.2
              }
            },
            splitLine: { //坐标轴在 grid 区域中的分隔线。
              show: false,
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            splitNumber: 5,
            axisLabel: {
              textStyle: {
                color: '#a8aab0',
                fontStyle: 'normal',
                fontFamily: '微软雅黑',
                fontSize: 12,
              }
            },
            splitArea: { show: false },
            axisLine: {
              show: false
            },
            axisTick: {
              show: false
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: ['#fff'],
                opacity: 0.06
              }
            }
          }
        ],
        series: [
          {
            name: '出行人数',
            type: 'bar',
            data: [1000, 150, 300, 450, 550, 600, 620, 800, 800, 620, 600, 505, 450, 300, 105, 100],
            barWidth: 10,
            barGap: 15,//柱间距离
            // label: {//图形上的文本标签
            //     normal: {
            //       show: true,
            //       position: 'top',
            //       textStyle: {
            //           color: '#a8aab0',
            //           fontStyle: 'normal',
            //           fontFamily: '微软雅黑',
            //           fontSize: 12,   
            //       },
            //     },
            // },
            itemStyle: {
              normal: {
                show: true,
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                  offset: 0,
                  color: '#5768EF'
                }, {
                  offset: 1,
                  color: '#5768EF'
                }]),
                barBorderRadius: 50,
                borderWidth: 0,
              }
            },
          },
          {
            name: '出行车辆',
            type: 'bar',
            data: [80, 50, 250, 300, 350, 550, 620, 780, 650, 550, 600, 450, 420, 150, 120, 50],
            barWidth: 10,
            barGap: 0,//柱间距离
            // label: {//图形上的文本标签
            //     normal: {
            //       show: true,
            //       position: 'top',
            //       textStyle: {
            //           color: '#a8aab0',
            //           fontStyle: 'normal',
            //           fontFamily: '微软雅黑',
            //           fontSize: 12,   
            //       },
            //     },
            // },
            itemStyle: {
              normal: {
                show: true,
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                  offset: 0,
                  color: '#69CBF2'
                }, {
                  offset: 1,
                  color: '#69CBF2'
                }]),
                barBorderRadius: 50,
                borderWidth: 0,
              }
            },
          }
        ]
      })
    }
  }
}
</script>
<style scoped>
#chart {
  margin-top: -15px;
  margin-left: -10%;
}
</style>