<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getVehicleType } from '@/api/vehicle/vehicleType'

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
    },
    xData: {
      type: Array,
      default: []
    },
    labels: {
      type: String,
      default: '车辆进口'
    }
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        // this.getVehicleTypeSApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      chart: null,
      // xData: []
    }
  },
  mounted () {
    // this.getVehicleTypeSApi()
    // this.xData = this.testData.car.vehicleCategory
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
      let xData = []
      xData = this.xData
      let color1 = {
        type: 'radial',
        x: 0.25,
        y: 0.7,
        r: 1,
        colorStops: [{
          offset: 0, color: '#FFCC0000' // 0% 处的颜色
        }, {
          offset: 1, color: '#FFCC00' // 100% 处的颜色
        }],
        global: false // 缺省为 false
      }
      let color2 = {
        type: 'radial',
        x: 0.35,
        y: 0.2,
        r: 1,
        colorStops: [{
          offset: 0, color: '#00CBFF00' // 0% 处的颜色
        }, {
          offset: 1, color: '#00CBFF' // 100% 处的颜色
        }],
        global: false // 缺省为 false
      }
      let color3 = {
        type: 'radial',
        x: 0.7,
        y: 0.35,
        r: 1,
        colorStops: [{
          offset: 0, color: '#FF660000' // 0% 处的颜色
        }, {
          offset: 1, color: '#FF6600' // 100% 处的颜色
        }],
        global: false // 缺省为 false
      }
      let color4 = {
        type: 'radial',
        x: 0.8,
        y: 0.6,
        r: 1,
        colorStops: [{
          offset: 0, color: '#28F2E600' // 0% 处的颜色
        }, {
          offset: 1, color: '#28F2E6' // 100% 处的颜色
        }],
        global: false // 缺省为 false
      }
      let colorList = [color1, color2, color3, color4]
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        // backgroundColor: "#0B1837",
        // color: ["#FFCC00", "#00CBFF", "#FF6600", "#28F2E6"],
        color: colorList,
        // startAngle: 90,
        // grid: {
        //   left: -100,
        //   top: 100,
        //   bottom: 10,
        //   right: 10,
        //   containLabel: true
        // },
        tooltip: {
          trigger: 'item',
          formatter: "{b} : {c} ({d}%)"
        },

        calculable: true,
        series: [{
          type: 'pie',
          radius: ["16%", "17%"],
          hoverAnimation: false,
          labelLine: {
            normal: {
              show: false,
              length: 10,
              length2: 25
            },
            emphasis: {
              show: false
            }
          },
          data: [{
            name: '',
            value: 0,
            itemStyle: {
              normal: {
                color: "#C2D7FD"
              }
            }
          }]
        }, {
          type: 'pie',
          radius: ["0%", "9%"],
          hoverAnimation: false,
          labelLine: {
            normal: {
              show: false,
              length: 10,
              length2: 25
            },
            emphasis: {
              show: false
            }
          },
          data: [{
            name: '',
            value: 0,
            itemStyle: {
              normal: {
                color: "#C2D7FD"
              }
            }
          }]
        }, {
          type: 'pie',
          radius: ["60%", "61%"],
          hoverAnimation: false,
          labelLine: {
            normal: {
              show: false,
              length: 30,
              length2: 55
            },
            emphasis: {
              show: false
            }
          },
          name: "",
          data: [{
            name: '',
            value: 0,
            itemStyle: {
              normal: {
                color: "#B7CFFC"
              }
            }
          }]
        }, {
          stack: 'a',
          type: 'pie',
          radius: ['20%', '50%'],
          roseType: 'radius',
          zlevel: 10,
          label: {
            normal: {
              show: true,
              formatter: "{b}\n{d}%",
              textStyle: {
                fontSize: 10,
                color: "#B7D0FC"
              },
              position: 'outside'
            },
            emphasis: {
              show: true
            }
          },
          labelLine: {
            show: false,
            // normal: {
            //   show: true,
            //   length: 20,
            //   length2: 55,
            //   lineStyle: {
            //     width: 3
            //   }
            // },
            // emphasis: {
            //   show: false
            // }
          },
          data: this.xData
        },]
      })
    },
    getVehicleTypeSApi () {
      this.xData = [
        {
          value: 0,
          name: '东门'
        },
        {
          value: 0,
          name: '西门'
        },
        {
          value: 0,
          name: '南门'
        },
        {
          value: 0,
          name: '北门'
        },
      ]
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getVehicleType(communityId, communityChildId).then(res => {
        if (res && res.data.length > 0) {
          for (let index = 0; index < res.data.length; index++) {
            this.xData[index].value = res.data[index].num
          }
        }
        console.log('x:', this.xData)
        this.$nextTick(() => {
          this.initChart()
        })
      })
    }
  }
}
</script>
<style scoped>
.re {
  color: #ffcc00;
  color: #00cbff;
  color: #ff6600;
  color: #28f2e6;
}
</style>