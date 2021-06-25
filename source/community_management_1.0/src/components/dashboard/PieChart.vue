<template>
  <div :class="className"
       :style="{height:height,width:width,background:'transparent'}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'

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
      default: '250px'
    },
    data: {
      type: Array,
      default: null
    },
    colors: {
      type: Array,
      default: null
    },
    radius: {
      type: Array,
      default () {
        return [50, 95]
      }
    },
    title: {
      type: String,
      default () {
        return '统计'
      }
    }
  },
  data () {
    return {
      chart: null,
      showData: null
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
    getXList () {
      let result = []
      if (!this.showData) return result
      this.showData.forEach(element => {
        result.push(element.name)
      });
      return result
    },
    initChart () {
      if (!(this.showData && this.showData.length > 0)) return false
      let xList = this.getXList()
      let itemStyle = null
      if (this.colors && this.colors.length > 0) {
        let colors = this.colors
        itemStyle = {
          normal: {
            color: function (params) {
              // //自定义颜色
              // var colorList = [
              //   '#00FFFF', '#00FF00', '#FFFF00', '#FF8C00', '#FF0000', '#FE8463',
              // ];
              var colorList = colors
              return colorList[params.dataIndex]
            }
          }
        }
      }
      // this.$el表示获取当前根组件
      this.chart = echarts.init(this.$el, 'macarons')

      this.chart.setOption({
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          left: 'center',
          bottom: 0,
          data: xList,
          textStyle: {
            color: '#11bbf3',
          },
        },
        series: [
          {
            name: this.title,
            type: 'pie',
            areaStyle: {
              normal: {
                color: '#11bbf3' //改变区域颜色
              }
            },
            // roseType: 'radius',//添加之后会改变每个饼块的半径（比例越大半径越大）
            radius: this.radius,
            center: ['50%', '50%'],
            data: this.showData,
            animationEasing: 'cubicInOut',
            animationDuration: 2600,
            label: {
              normal: {
                formatter: (params) => {
                  return '{b| ' + params.name + ':' + params.value + '} ' + '(' + '{c|' + params.percent.toFixed(0) + '%}' + ')'
                },
                borderWidth: 0,
                borderRadius: 0,
                padding: [0, -50],
                height: 30,
                fontSize: 12,
                align: 'center',
                // color: '#00FFC3',
                rich: {
                  b: {
                    fontSize: 12,
                    lineHeight: 0,
                    // color: '#00FFC3',
                    padding: [0, 0, 0, 0]
                  },
                  c: {
                    fontSize: 12,
                    lineHeight: 0,
                    // color: '#00FFC3'
                  }

                }
              }
            },
            labelLine: {
              normal: {
                length: 20,
                length2: 40,
                lineStyle: {
                  type: 'solid'
                }
              }
            },
            itemStyle: itemStyle
          }
        ]
      })
    }
  },
  watch: {
    data: {
      handler: function (newValue) {
        console.log('new value change')
        if (!newValue) return false
        this.showData = newValue
        this.$nextTick(() => {
          this.initChart()
        })
      },
      deep: true,
      immediate: true
    }
  },
}
</script>
