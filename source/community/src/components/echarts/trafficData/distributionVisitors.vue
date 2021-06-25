<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getVistorNum } from '@/api/trafficData/visitorNum';

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
    }
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        this.getVistorNumData()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      chart: null,
      data: {
        visitPersonList: [],
        visitVehicleList: []
      }
    }
  },
  created () {

  },
  mounted () {
    this.getVistorNumData()
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
      //日期列表
      let xAxis = ['12.25', '12.26', '12.27', '12.28', '12.29', '12.30', '12.31']
      let yAxis_vehicle = [0, 0, 0, 0, 0, 0, 0]
      let yAxis_person = [0, 0, 0, 0, 0, 0, 0]
      if (this.data && this.data.visitPersonList && this.data.visitVehicleList) {
        //取X轴
        xAxis = this.data.visitPersonList.map(item => item.name)
        //取Y轴
        yAxis_person = this.data.visitPersonList.map(item => item.num)
        yAxis_vehicle = this.data.visitVehicleList.map(item => item.num)
      }
      this.chart.setOption({
        grid: {
          left: "3%",
          //   right: "2%",
          bottom: "20%",
          top: "20%",
          containLabel: true
        },
        tooltip: {
          trigger: 'axis',
          backgroundColor: 'rgba(0, 0, 0, 0.1)',
          extraCssText: 'box-shadow: 0 0 3px rgba(0, 0, 0, 0.3)',
          textStyle: {
            color: '#FFF',
          },
          axisPointer: {
            type: 'none'
          }
        },
        legend: {
          top: '0%',
          left: '40%',
          itemWidth: 15,
          itemHeight: 4,
          data: ['车辆', '人员'],
          textStyle: {
            color: 'white',
          }
        },
        xAxis: {
          type: "category",
          boundaryGap: true,
          data: xAxis,
          show: true,
          axisLabel: {
            // show: true,
            textStyle: {
              color: "#FFF",
              fontSize: 10
            }
          },
          axisLine: {
            lineStyle: {
              color: 'rgba(41,138,231,0.9)',
            }
          },
          axisTick: {
            show: true,
            alignWithLabel: true,
          },
        },
        yAxis: {
          type: 'value',
          splitNumber: 5,
          axisLabel: {
            textStyle: {
              color: '#FFF',
              fontSize: 10
            }
          },
          splitArea: { show: false },//设置网格区域
          axisLine: {
            lineStyle: {
              color: 'transparent',
            }
          },
          splitLine: {
            show: false,
            lineStyle: {
              color: '#B4B3C0',
              type: "dashed"
            }
          }
        },
        series: [{
          backgroundColor: 'rgba(36, 53, 135, 0.298039215686275)',
          name: '人员',
          type: 'line',
          smooth: true,
          stack: '使用情况',
          data: yAxis_person,
          itemStyle: {
            normal: { color: "rgba(52,177,95,0.9)" },
          },


        }, {
          name: '车辆',
          type: 'line',
          smooth: false,
          stack: '使用情况',
          data: yAxis_vehicle,
          itemStyle: {
            normal: { color: "rgba(41,138,231,0.9)" },
          },

        }
        ]
        // grid: {
        //   left: '5%',
        //   right: '10%',
        //   top: '20%',
        //   bottom: '15%',
        //   height: '60%',
        //   containLabel: true
        // },
        // tooltip: {
        //   show: true,
        //   trigger: 'item'
        // },
        // legend: {
        //   show: true,
        //   x: 'center',
        //   y: '5',
        //   icon: 'circle',
        //   itemWidth: 8,
        //   itemHeight: 8,
        //   textStyle: {
        //     color: '#fff'
        //   },
        //   data: ['车辆', '人员']
        // },
        // xAxis: [{
        //   type: 'category',
        //   boundaryGap: false,
        //   axisLabel: {
        //     color: '#30eee9'
        //   },
        //   axisLine: {
        //     show: true,
        //     lineStyle: {
        //       color: '#397cbc'
        //     }
        //   },
        //   axisTick: {
        //     show: false,
        //   },
        //   splitLine: {
        //     show: false,
        //     lineStyle: {
        //       color: '#195384'
        //     }
        //   },
        //   data: xAxis,
        // }],
        // yAxis: [{
        //   type: 'value',
        //   name: '',
        //   min: 0,
        //   // max: 1000,
        //   axisLabel: {
        //     formatter: '{value}',
        //     textStyle: {
        //       color: '#2ad1d2'
        //     }
        //   },
        //   axisLine: {
        //     lineStyle: {
        //       color: '#27b4c2'
        //     }
        //   },
        //   axisTick: {
        //     show: true,
        //   },
        //   splitLine: {
        //     show: true,
        //     lineStyle: {
        //       color: '#11366e'
        //     }
        //   },
        //   splitArea: { show: false }//设置网格区域
        // },

        // ],
        // series: [{
        //   name: '车辆',
        //   type: 'line',
        //   stack: '总量',
        //   symbol: 'circle',
        //   symbolSize: 5,
        //   smooth: true,
        //   itemStyle: {
        //     normal: {
        //       color: 'plum',
        //       lineStyle: {
        //         color: "plum",
        //         width: 1
        //       },
        //     }
        //   },
        //   // markPoint: {
        //   //   itemStyle: {
        //   //     normal: {
        //   //       color: 'red'
        //   //     }
        //   //   }
        //   // },
        //   data: yAxis_vehicle
        // },
        // {
        //   name: '人员',
        //   type: 'line',
        //   stack: '总量',
        //   symbol: 'circle',
        //   symbolSize: 5,
        //   smooth: false,
        //   itemStyle: {
        //     normal: {
        //       color: '#00d4c7',
        //       lineStyle: {
        //         color: "#00d4c7",
        //         width: 1
        //       },
        //     }
        //   },
        //   data: yAxis_person
        // },
        // ]

      })
    },
    getVistorNumData () {
      this.data = []
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getVistorNum(communityId, communityChildId).then(res => {
        // console.log(res, 'resninnn')
        if (res && res.data) {
          this.data = res.data
        }
        this.$nextTick(() => {
          this.initChart()
        })
      })
    }
  }
}
</script>
