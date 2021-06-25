<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'

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
    data: {
      type: Object,
      default () {
        return {
          title: 'title',
          dataList: [
            {
              value: 120,
              name: '图例1'
            }, {
              value: 120,
              name: '图例2'
            }
          ]
        }
      }
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
  watch: {
    data: {
      handler () {
        this.initChart()
      },
      deep: true
    }
  },
  methods: {
    initChart () {
      var color = ['#394DFD', '#00D9FF']
      let titleTemp = ''
      let dataTemp = [{
        value: 120,
        name: '比例图'
      }
      ]
      if (this.data && this.data.dataList && this.data.dataList.length > 0) {
        dataTemp = this.data.dataList
        titleTemp = this.data.title
      }
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        color: color,
        // backgroundColor: '#0A173B',
        title: {
          text: titleTemp,
          right: '59%',
          top: '45%',
          textStyle: {
            fontSize: 12,
            color: '#0091ff',
            fontWeight: 'normal'
          }
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          right: 30,
          top: 10,
          textStyle: {
            color: '#0091ff',
          },
          itemWidth: 15,
          itemHeight: 10,
        },
        series: [{
          type: 'pie',
          roseType: 'radius',
          radius: ['30%', '50%'],
          center: ['35%', '50%'],
          data: dataTemp,
          label: {
            normal: {
              formatter: '{font|{b}}-{font|{c}}\n{hr|}\n{font|{d}%}',
              // formatter: (params) => {
              //     return '{font| ' + params.name + ':' + params.value + '} ' + '(' + '{c|' + params.percent.toFixed(0) + '%}' + ')'
              //   },
              rich: {
                font: {
                  fontSize: 12,
                  padding: [5, 0],
                  color: '#0091ff'
                },
                hr: {
                  height: 0,
                  borderWidth: 1,
                  width: '100%',
                  borderColor: '#fff'
                }
              }
            },
          },
          labelLine: {
            lineStyle: {
              color: '#0091ff'
            },
            normal: {
              length: 5,
              length2: 7,
              lineStyle: {
                width: 1,
              },

            }
          },
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0,0,0,0.5)'
            }
          }
        }]
      })
    }
  }
}
</script>
