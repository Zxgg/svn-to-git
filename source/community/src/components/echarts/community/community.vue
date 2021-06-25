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
      var equipment = ['社区组织类型统计展示'];
      var project = ['社会组织', '综治组织', '群防群治机构', '境外非政府组织', '社会组织', '综治组织', '群防群治机构', '境外非政府组织'];
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        tooltip: {
          formatter: function (param) {
            if (param.data.type == null) {

              return param.data.name + ":" + param.value + '个';
            } else {

              return param.data.type + ":" + param.value + '个';
            }
          }
        },
        legend: {
          left: 10,
          top: 145,
          data: ['社会组织', '综治组织', '群防群治机构', '境外非政府组织'],
          itemHeight: 10, // 设置高度
          textStyle: {
            color: '#ffffff',
            fontSize: "10"
          },
        },
        series: [
          {
            name: '社区组织',
            type: 'pie',
            radius: ['25%', '40%'],
            labelLine: {
              normal: {
                show: true
              }
            },
            label: {
              normal: {
                show: false
              }
            },
            data: [
              {
                value: 135, name: '社会组织', type: equipment[0], itemStyle: {
                  color: '#54dde3'
                }
              },
              {
                value: 200, name: '综治组织', type: equipment[1], itemStyle: {
                  color: '#91d9e3'
                }
              },
              {
                value: 310, name: '群防群治机构', type: project[2], itemStyle: {
                  color: "#348ecf"
                }
              },
              {
                value: 234, name: '境外非政府组织', type: project[3], itemStyle: {
                  color: "#4e93cf"
                }
              },
              {
                value: 35, name: '社会组织', type: project[0], itemStyle: {
                  color: "#6e9dcf"
                }
              },
              {
                value: 100, name: '综治组织', type: project[1], itemStyle: {
                  color: "#89a3cf"
                }
              },

            ]
          }
        ]
      })
    }
  }
}
</script>
<style scoped>
#chart {
  margin-top: -45px;
}
</style>