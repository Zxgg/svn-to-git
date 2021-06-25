<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getPeopleInfoApi } from '@/api/PerInformation/peopleInfo'

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
        // this.getCarpeoApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  created () {
    this.getDicts("base_type_of_caring").then(response => {
      this.carePersonList = response.data;
    });
  },
  data () {
    return {
      communityId: '',
      communityChildId: '',
      chart: null,
      carePersonList: [],
      xData: ['留守老人', '留守妇女', '留守儿童', '精神疾病', '疾病', '残疾', '瘫痪', '孕妇'],
      yData: [0, 0, 0, 0, 0, 0, 0, 0],
      data: this.testData.renyuan.carePersonData
    }
  },
  mounted () {
    this.xData = this.data.x
    this.yData = this.data.y
    // this.getPeopleInfoApi()
    this.$nextTick(() => {
      this.initChart()
    })
    this.$Notice.$on('communityGrid', (val) => {
      // this.getCode(val)
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
    visitVehicleTypeFormat (value) {
      return this.selectDictLabel(this.carePersonList, value);
    },
    initChart () {
      let yList = [32, 58, 64, 64, 64];
      let xList = [10, 55, 44, 55, 9, 8];
      yList = this.data.y
      xList = this.data.x
      let color = ['#97B9C9', '#97B9C9', '#97B9C9', '#97B9C9', '#97B9C9', '#97B9C9', '#97B9C9'];
      let xData = xList.map((item, index) => {
        return {
          value: item,
          textStyle: {
            color: '#97B9C9'
          }
        };
      });
      let dom = 400;
      let barWidth = dom / 20;
      let colors = [{
        type: 'linear',
        x: 0,
        x2: 1,
        y: 0,
        y2: 0,
        colorStops: [{
          offset: 0,
          color: 'rgba(79, 172, 148, 1)'
        }, {
          offset: 0.5,
          color: 'rgba(79, 172, 148, 0.5)'
        }, {
          offset: 0.5,
          color: 'rgba(79, 172, 148, 1)'
        }, {
          offset: 1,
          color: 'rgba(79, 172, 148, 1)'
        }]
      },
      {
        type: 'linear',
        x: 0,
        x2: 1,
        y: 0,
        y2: 0,
        colorStops: [{
          offset: 0,
          color: 'rgba(79, 172, 148, 1)'
        }, {
          offset: 0.5,
          color: 'rgba(79, 172, 148, 0.5)'
        }, {
          offset: 0.5,
          color: 'rgba(79, 172, 148, 1)'
        }, {
          offset: 1,
          color: 'rgba(79, 172, 148, 1)'
        }]
      },
      {
        type: 'linear',
        x: 0,
        x2: 1,
        y: 0,
        y2: 0,
        colorStops: [{
          offset: 0,
          color: 'rgba(79, 172, 148, 1)'
        }, {
          offset: 0.5,
          color: 'rgba(79, 172, 148, 0.5)'
        }, {
          offset: 0.5,
          color: 'rgba(79, 172, 148, 1)'
        }, {
          offset: 1,
          color: 'rgba(79, 172, 148, 1)'
        }]
      },
      {
        type: 'linear',
        x: 0,
        x2: 1,
        y: 0,
        y2: 0,
        colorStops: [{
          offset: 0,
          color: 'rgba(79, 172, 148, 1)'
        }, {
          offset: 0.5,
          color: 'rgba(79, 172, 148, 0.5)'
        }, {
          offset: 0.5,
          color: 'rgba(79, 172, 148, 1)'
        }, {
          offset: 1,
          color: 'rgba(79, 172, 148, 1)'
        }]
      },
      {
        type: 'linear',
        x: 0,
        x2: 1,
        y: 0,
        y2: 0,
        colorStops: [{
          offset: 0,
          color: 'rgba(79, 172, 148, 1)'
        }, {
          offset: 0.5,
          color: 'rgba(79, 172, 148, 0.5)'
        }, {
          offset: 0.5,
          color: 'rgba(79, 172, 148, 1)'
        }, {
          offset: 1,
          color: 'rgba(79, 172, 148, 1)'
        }]
      },
      {
        type: 'linear',
        x: 0,
        x2: 1,
        y: 0,
        y2: 0,
        colorStops: [{
          offset: 0,
          color: 'rgba(79, 172, 148, 1)'
        }, {
          offset: 0.5,
          color: 'rgba(79, 172, 148, 0.5)'
        }, {
          offset: 0.5,
          color: 'rgba(79, 172, 148, 1)'
        }, {
          offset: 1,
          color: 'rgba(79, 172, 148, 1)'
        }]
      },
      {
        type: 'linear',
        x: 0,
        x2: 1,
        y: 0,
        y2: 0,
        colorStops: [{
          offset: 0,
          color: 'rgba(79, 172, 148, 1)'
        }, {
          offset: 0.5,
          color: 'rgba(79, 172, 148, 0.5)'
        }, {
          offset: 0.5,
          color: 'rgba(79, 172, 148, 1)'
        }, {
          offset: 1,
          color: 'rgba(79, 172, 148, 1)'
        }]
      }
      ];
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        // backgroundColor: '#031245',
        //提示框
        // tooltip: {
        //   // trigger: 'axis',
        //   formatter: "{b} : {c}",
        //   axisPointer: { // 坐标轴指示器，坐标轴触发有效
        //     type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
        //   }
        // },
        /**区域位置*/
        grid: {
          left: '10%',
          right: '10%',
          top: '10%',
          bottom: '40%',
        },
        /**图例大小*/
        //  legend: {
        //   type: 'plain',
        //   left: 0,
        //   top: 20,
        //   itemGap: 20,
        //   itemWidth: 35,
        //   itemHeight: 20
        // },
        //X轴
        xAxis: {
          // name: 'X',
          // nameTextStyle: {
          //   color: '#FFFFFF',
          //   padding: [0, 0, 0, 20]

          // },
          data: xData,
          show: true,
          type: 'category',
          axisLine: {
            show: false,
            lineStyle: {
              color: 'rgba(255,255,255,1)',
              shadowColor: 'rgba(255,255,255,1)',
              shadowOffsetX: '20',
            },
            symbol: ['none', 'arrow'],
            symbolOffset: [0, 25],
          },
          splitLine: {
            show: false,
            lineStyle: {
              color: 'rgba(255,255,255,0.2)'
            },
          },
          axisLabel: {
            rotate: 30,
            // interval: 1,
            fontSize: 12,
          },
          splitArea: { show: false }//设置网格区域
        },
        yAxis: {
          show: true,
          splitNumber: 4,
          axisLine: {
            show: false,
            lineStyle: {
              color: 'rgba(255,255,255,0.2)'
            },
          },
          splitLine: {
            lineStyle: {
              color: ['#434A7E'],
              width: 1,
              type: 'dashed'
            }
          },
          axisLabel: {
            color: '#C7DFE1',
          },
          splitArea: { show: false }//设置网格区域
        },
        series: [{
          type: 'bar',
          barWidth: barWidth,
          itemStyle: {
            normal: {
              color: function (params) {
                return colors[params.dataIndex % 7];
              }
            }
          },
          label: {
            show: true,
            position: [barWidth / 2, -(barWidth)],
            color: '#ffffff',
            fontSize: 12,
            fontStyle: 'bold',
            align: 'center',
          },
          data: yList
        },
        {
          z: 2,
          type: 'pictorialBar',
          data: yList,
          symbol: 'diamond',
          symbolOffset: [0, '50%'],
          symbolSize: [barWidth, barWidth * 0.5],
          itemStyle: {
            normal: {
              color: function (params) {
                return colors[params.dataIndex % 7];
              },
            }
          },
        },
        {
          z: 3,
          type: 'pictorialBar',
          symbolPosition: 'end',
          data: yList,
          symbol: 'diamond',
          symbolOffset: [0, '-50%'],
          symbolSize: [barWidth, barWidth * 0.5],
          itemStyle: {
            normal: {
              borderWidth: 0,
              color: function (params) {
                return colors[params.dataIndex % 7].colorStops[0].color;
              },

            }
          },
        },
          // {
          //   z: 4,
          //   type: 'pictorialBar',
          //   symbolPosition: 'end',
          //   data: yList,
          //   symbol: 'circle',
          //   symbolOffset: [0, -barWidth],
          //   symbolSize: [barWidth * 0.5, barWidth * 0.5],
          //   itemStyle: {
          //     normal: {
          //       borderWidth: 0,
          //       color: function (params) {
          //         return colors[params.dataIndex % 7].colorStops[0].color;
          //       },

          //     }
          //   },
          // },
        ]
      })
    },
    getCode (code) {
      getPeopleInfoApi(code).then(res => {
        if (res && res.data.carePersonData) {
          this.data = res.data.carePersonData
          this.$nextTick(() => {
            this.initChart()
          })
        }
      })
    },
    getCarpeoApi () {
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      this.xData = ['独居老人', '残疾人员', '重点治疗人员', '安置帮教人员', '知名人士', '其他关爱人员', '瘫痪', '孕妇']
      this.yData = [0, 0, 0, 0, 0, 0, 0, 0]
      getCaringPeopleType(communityId, communityChildId).then(res => {
        // console.log(res, 'caring')
        if (res && res.data.length > 0) {
          this.xData = []
          this.yData = []
          res.data.forEach(data => {
            this.xData.push(data.name)
            this.yData.push(data.num)
          })
          this.$nextTick(() => {
            console.log('6x:', this.xData)
            console.log('6y:', this.yData)
            this.initChart()
          })
        }
      })
    }
  }
}
</script>
