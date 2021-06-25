<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
const animationDuration = 6000
export default {
  name: 'double_ring_pie',
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
      default: null
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
    // data: {
    //   handler: function (newValue) {
    //     console.log('double_ring_pie new value change')
    //     // if (!newValue) return false
    //     // this.data = newValue
    //     this.$nextTick(() => {
    //       this.initChart()
    //     })
    //   },
    //   deep: true,
    //   immediate: true
    // }
  },
  methods: {
    initChart () {
      // import echarts from 'echarts'
      // let data = this.data
      var labelData = [];
      var labelData1 = [];
      for (var i = 0; i < 150; ++i) {
        labelData.push({
          value: 1,
          name: i,
          itemStyle: {
            normal: {
              color: 'rgba(0,209,228,0)',
            }
          }
        });
      }
      for (var i = 0; i < labelData.length; ++i) {
        if (labelData[i].name < 50) {
          labelData[i].itemStyle = {
            normal: {
              color: new echarts.graphic.LinearGradient(
                0, 0, 0, 1,
                [{
                  offset: 0,
                  color: '#0ff'
                },
                {
                  offset: 1,
                  color: '#5467df'
                }
                ]
              )
            },

          }
        }
      }
      for (var i = 0; i < 150; ++i) {
        labelData1.push({
          value: 1,
          name: i,
          itemStyle: {
            normal: {
              color: 'rgba(0,209,228,0)',
            }
          }
        });
      }
      for (var i = 0; i < labelData1.length; ++i) {
        if (labelData1[i].name < 150) {
          labelData1[i].itemStyle = {
            normal: {
              color: new echarts.graphic.LinearGradient(
                0, 0, 0, 1,
                [{
                  offset: 0,
                  color: '#251f45'
                },
                {
                  offset: 1,
                  color: '#251f45'
                }
                ]
              )
            },

          }
        }
      }
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption(
        {
          backgroundColor: '#180f2a',
          title: [{
            text: '118',
            x: '50%',
            y: '37%',
            textAlign: 'center',
            textStyle: {
              fontSize: '70',
              fontWeight: '100',
              color: '#0FF',
              textAlign: 'center',
            },
          }, {
            text: 'DESIGN ELEMENTS',
            left: '50%',
            top: '52%',
            textAlign: 'center',
            textStyle: {
              fontSize: '18',
              fontWeight: '400',
              color: '#8d8793',
              textAlign: 'center',
            },
          }, {
            text: 'DONUT CHART',
            left: '50%',
            top: '57%',
            textAlign: 'center',
            textStyle: {
              fontSize: '14',
              fontWeight: '400',
              color: 'rgba(65,63,112,1)',
              textAlign: 'center',
            },
          },],
          polar: {
            radius: ['76%', '52%'],
            center: ['30%', '50%'],
          },
          angleAxis: {
            max: 100,
            show: true,
          },
          radiusAxis: {
            type: 'category',
            show: true,
            axisLabel: {
              show: true,
            },
            axisLine: {
              show: false,

            },
            axisTick: {
              show: false
            },
          },
          series: [{
            name: 'test',
            type: 'bar',
            roundCap: true,
            barWidth: 60,
            showBackground: true,
            backgroundStyle: {
              color: '#ffffff',
            },
            data: [68],
            coordinateSystem: 'polar',
            itemStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                  offset: 0,
                  color: '#FFFFFF'
                }, {
                  offset: 1,
                  color: '#ff00aa'
                }]),
              }
            }
          },
          {
            hoverAnimation: false,
            type: 'pie',
            z: 2,
            data: labelData,
            radius: ['43%', '50%'],
            zlevel: -2,
            itemStyle: {
              normal: {
                borderColor: '#180f2a',
                borderWidth: 2,
              }
            },
            label: {
              normal: {
                position: 'inside',
                show: false,
              }
            },
          },
          {
            hoverAnimation: false,
            type: 'pie',
            z: 1,
            data: labelData1,
            radius: ['43%', '50%'],
            zlevel: -2,
            itemStyle: {
              normal: {
                borderColor: '#180f2a',
                borderWidth: 2,
              }
            },
            label: {
              normal: {
                position: 'inside',
                show: false,
              }
            },
          },

          ]
        }
      )
    }
  }
}
</script>
