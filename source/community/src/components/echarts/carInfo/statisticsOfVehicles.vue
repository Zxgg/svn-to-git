<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getVehiclePass } from '@/api/vehicle/vehiclePass'

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
 
      },
      deep: true
    }
  },
  data () {
    return {
      chart: null,
      data: {
        visitVehicleList: []
      }
    }
  },
  created () {

  },
  mounted () {
    // this.getVehiclePassData()
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
      // let xData = this.testData.car.VehiclePassData.x
      // let yData = this.testData.car.VehiclePassData.y
      let xData = ['2020-12-15', '2020-12-16', '2020-12-17', '2020-12-18', '2020-12-19', '2020-12-20', '2020-12-21']
      let yData = [4, 3, 2, 5, 9, 3, 2]
      if (this.data && this.data.visitVehicleList) {
        // console.log(this.data, 'this.data')
        yData = []
        //取X轴
        xData = this.data.visitVehicleList.map(item => item.name)
        console.log(xData, ',xda')
        //取Y轴
        let vehicleListNum
        if (this.data.vehicleList) vehicleListNum = this.data.vehicleList.map(item => item.num)
        let visitVehicleListNum = this.data.visitVehicleList.map(item => item.num)
        for (let index = 0; index < visitVehicleListNum.length; index++) {
          // console.log(visitVehicleListNum[index] + vehicleListNum[index])
          yData.push(visitVehicleListNum[index] + vehicleListNum[index])
        }
        console.log(yData, ',yda')
      }
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            label: {
              backgroundColor: '#6a7985'
            }
          }
        },
        title: {
          right: '3%',
          text: '周/7days',
          top: '5%',
          textStyle: {
            color: '#fff',
            fontStyle: 'normal',
            fontFamily: '微软雅黑',
            fontSize: 12,
          },
        },
        legend: {
          textStyle: {
            color: '#ffffff',
          },
        },
        grid: {
          left: '6%',
          right: '7%',
          bottom: '20%',
          height: '60%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            boundaryGap: false,
            data: xData,
            axisLabel: {
              show: true,
              textStyle: {
                color: '#ccc',  //更改坐标轴文字颜色
                fontSize: 10      //更改坐标轴文字大小
              }
            },
          },
        ],
        yAxis: [
          {
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
                // color: ['#00DCFF'],
                width: 1,
                opacity: 0.1
                // type: 'dashed'
              }
            },
            "axisLine": {       //y轴
              "show": false

            },
            //设置刻度线
            axisTick: {
              show: false
            },
            splitArea: { show: false }//设置网格区域
          }
        ],
        series: [
          {
            // name: '',
            type: 'line',
            stack: '总量',
            smooth: false,
            areaStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  { offset: 0, color: 'rgba(0,220,255,0.2)' }
                ])
              }
            },
            data: yData
          },
        ]
      })
    },
    getVehiclePassData () {
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getVehiclePass(communityId, communityChildId).then(res => {
        // console.log(res, 'nihaores')
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
