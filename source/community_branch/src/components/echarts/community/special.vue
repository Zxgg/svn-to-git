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
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
          // orient: 'vertical',
          // top: 'middle',
          top: 145,
          left: 'center',
          data: ['刑满释放人员', '社区矫正人员', '肇事肇祸等严重精神障碍患者', '吸毒人员', '艾滋病危险人员'],
          textStyle: {
            color: '#ffffff',
            fontSize: "10"
          },
          itemHeight: '10',
        },
        series: [{
          name: '特殊人员',
          type: 'pie',
          radius: '40%',
          center: ['50%', '50%'],
          clockwise: false,
          data: [{
            value: 45,
            name: '刑满释放人员'
          }, {
            value: 25,
            name: '社区矫正人员'
          }, {
            value: 15,
            name: '肇事肇祸等严重精神障碍患者'
          }, {
            value: 8,
            name: '吸毒人员'
          }, {
            value: 7,
            name: '艾滋病危险人员'
          }],
          label: {
            normal: {
              show: false
            }
          },
          labelLine: {
            normal: {
              show: false
            }
          },
          itemStyle: {
            emphasis: {
              borderWidth: 0,
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }],
        color: [
          '#00acee',
          '#52cdd5',
          '#79d9f1',
          '#a7e7ff',
          '#c8efff'
        ],
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