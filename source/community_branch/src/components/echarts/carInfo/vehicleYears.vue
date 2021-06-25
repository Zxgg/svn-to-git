<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getVehicleYears } from '@/api/vehicle/vehicleYears'

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
      default: '90%'
    },
    currentComunity: {
      communityId: String,
      communityChildId: String
    }
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        // this.getCaryearsApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      chart: null,
      zData: 0,
      xData: 0,
      yData: 0
    }
  },
  mounted () {
    this.xData = this.testData.car.VehicleYears.x
    this.yData = this.testData.car.VehicleYears.y
    this.zData = this.testData.car.VehicleYears.z
    // this.getCaryearsApi()
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
      let xData = 25
      let yData = 50
      let zData = 75
      zData = this.zData
      yData = this.yData
      xData = this.xData
      var dataStyle = {
        normal: {
          label: {
            show: false
          },
          labelLine: {
            show: false
          },
          shadowBlur: 0,
          shadowColor: '#203665'
        }
      }
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({

        series: [{
          name: '第一个圆环',
          type: 'pie',
          clockWise: false,
          radius: ['40%', '50%'],
          itemStyle: dataStyle,
          hoverAnimation: false,
          center: ['20%', '50%'],
          data: [{
            value: xData,
            label: {
              normal: {
                rich: {
                  a: {
                    color: '#3a7ad5',
                    align: 'center',
                    fontSize: 12,
                    fontWeight: "bold"
                  },
                  b: {
                    color: '#fff',
                    align: 'center',
                    fontSize: 12
                  }
                },
                formatter: function (params) {
                  return "{b|入驻时间}\n\n" + "{a|" + params.value + "辆}" + "\n\n{b|0-6年}";
                },
                position: 'center',
                show: true,
                textStyle: {
                  fontSize: '12',
                  fontWeight: 'normal',
                  color: '#fff'
                }
              }
            },
            itemStyle: {
              normal: {
                color: '#2c6cc4',
                shadowColor: '#2c6cc4',
                shadowBlur: 0
              }
            }
          }, {
            value: 75,
            name: 'invisible',
            itemStyle: {
              normal: {
                color: '#24375c'
              },
              emphasis: {
                color: '#24375c'
              }
            }
          }]
        }, {
          name: '第二个圆环',
          type: 'pie',
          clockWise: false,
          radius: ['40%', '50%'],
          itemStyle: dataStyle,
          hoverAnimation: false,
          center: ['50%', '50%'],
          data: [{
            value: yData,
            label: {
              normal: {
                rich: {
                  a: {
                    color: '#d03e93',
                    align: 'center',
                    fontSize: 12,
                    fontWeight: "bold"
                  },
                  b: {
                    color: '#fff',
                    align: 'center',
                    fontSize: 12
                  }
                },
                formatter: function (params) {
                  return "{b|入驻时间}\n\n" + "{a|" + params.value + "辆}" + "\n\n{b|6年到15年}";
                },
                position: 'center',
                show: true,
                textStyle: {
                  fontSize: '12',
                  fontWeight: 'normal',
                  color: '#fff'
                }
              }
            },
            itemStyle: {
              normal: {
                color: '#ef45ac',
                shadowColor: '#ef45ac',
                shadowBlur: 0
              }
            }
          }, {
            value: 50,
            name: 'invisible',
            itemStyle: {
              normal: {
                color: '#412a4e'
              },
              emphasis: {
                color: '#412a4e'
              }
            }
          }]
        }, {
          name: '第三个圆环',
          type: 'pie',
          clockWise: false,
          radius: ['40%', '50%'],
          itemStyle: dataStyle,
          hoverAnimation: false,
          center: ['80%', '50%'],
          data: [{
            value: zData,
            label: {
              normal: {
                rich: {
                  a: {
                    color: '#603dd0',
                    align: 'center',
                    fontSize: 12,
                    fontWeight: "bold"
                  },
                  b: {
                    color: '#fff',
                    align: 'center',
                    fontSize: 12
                  }
                },
                formatter: function (params) {
                  return "{b|入驻时间}\n\n" + "{a|" + params.value + "辆}" + "\n\n{b|15年以上}";
                },
                position: 'center',
                show: true,
                textStyle: {
                  fontSize: '14',
                  fontWeight: 'normal',
                  color: '#fff'
                }
              }
            },
            itemStyle: {
              normal: {
                color: '#613fd1',
                shadowColor: '#613fd1',
                shadowBlur: 0
              }
            }
          }, {
            value: 25,
            name: 'invisible',
            itemStyle: {
              normal: {
                color: '#453284'
              },
              emphasis: {
                color: '#453284'
              }
            }
          }]
        }]
      })
    },
    getCaryearsApi () {
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getVehicleYears(communityId, communityChildId).then(res => {
        // console.log(res, 'year')
        if (res && res.data.length > 0) {
          res.data.forEach(data => {
            switch (data.name) {
              case "fifteen":
                this.zData = data.num
                break;
              case "six":
                this.yData = data.num
                break;
              case "zero":
                this.xData = data.num
                break;
              default:
                break;
            }
          })
        }
        this.$nextTick(() => {
          this.initChart()
        })
      })
    }
  }
}
</script>
