<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
// import resize from './mixins/resize'

const animationDuration = 6000

export default {
  name: 'P_H_V_RelationChart',
  // mixins: [resize],
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
    data: {
      type: Object,
      default () {
        return {
          xAxis: ['10.01', '10.02', '10.03', '10.04', '10.05', '10.06', '10.07'],
          lines: [
            {
              name: '新增流动人口（百）',
              data: [120, 132, 101, 134, 90, 230, 210]
            }, {
              name: '租住人口（个）',
              data: [220, 182, 191, 234, 290, 330, 310]
            }
          ]
        }
      }
    }
  },
  data () {
    return {
      chart: null,
      stack: '总量',
      areaStyle: {},
      type: 'line'
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
  watch: {
    data: {
      handler: function (newValue) {
        // console.log('new value change')
        // if (!newValue) return false
        // this.data = newValue
        this.$nextTick(() => {
          this.initChart()
        })
      },
      deep: true,
      immediate: true
    }
  },
  methods: {
    initChart () {
      // import echarts from 'echarts'
      this.chart = echarts.init(this.$el, 'macarons')

      let legend = this.data.lines.map(item => item.name)
      let xAxis = this.data.xAxis
      let series = []
      this.data.lines.forEach(element => {
        series.push({
          name: element.name,
          type: this.type,
          // stack: this.stack,//使用stack，效果是堆叠
          areaStyle: this.areaStyle,
          data: element.data
        })
      });

      this.chart.setOption({
        // backgroundColor: '#080b30',
        title: {
          text: '通行统计'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: legend,
          textStyle: {//图例文字的样式
            color: '#ccc'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: xAxis
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            show: true,
            textStyle: {
              color: '#ccc',  //更改坐标轴文字颜色
              fontSize: 10      //更改坐标轴文字大小
            }
          },
          //设置网格线颜色
          splitLine: {
            show: true,
            lineStyle: {
              color: ['#979797'],
              width: 1,
              type: 'dashed'
            }
          },
          "axisLine": {       //y轴
            "show": true
          },
          //设置刻度线
          axisTick: {
            show: false
          },
          splitArea: { show: false }//设置网格区域
        },
        series: series
      })
    }
  }
}
</script>
