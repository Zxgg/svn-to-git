<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getEducationStatistics } from '@/api/PerInformation/educationStatistics'

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
        // this.geteducationApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      chart: null,
      yData: [],
      xData: [{
        "name": "专科",
        "max": 999999999
      }, {
        "name": "中专"
      }, {
        "name": "初中"
      }, {
        "name": "本科"
      }, {
        "name": "小学"
      }, {
        "name": "文盲"
      }, {
        "name": "研究生"
      }, {
        "name": "高中"
      }]
    }
  },
  mounted () {
    // this.xData = this.testData.renyuan.eduData
    // this.yData = this.testData.renyuan.eduData.y
    // this.geteducationApi()
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
    initChartOld () {
      // let yData = [
      //   [80, 50, 55, 80, 50, 80, 48, 43]
      // ]
      let yData = []
      let xData = []
      yData = this.yData
      xData = this.xData
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        // backgroundColor: '#0A2E5D',
        "normal": {
          "top": 200,
          "left": 300,
          "width": 500,
          "height": 350,
          "zIndex": 6,
          "backgroundColor": ""
        },
        "color": ["rgba(245, 166, 35, 1)", "rgba(19, 173, 255, 1)"],
        "title": {
          "show": true,
          // "text": "学生行为习惯分析",
          "left": "40%",
          "top": "1%",
          "textStyle": {
            "fontSize": 18,
            "color": "#fff",
            "fontStyle": "normal",
            "fontWeight": "normal"
          }
        },
        "tooltip": {
          "show": true,
          "trigger": "item"
        },
        // "legend": {
        //   "show": true,
        //   "icon": "circle",
        //   "left": "35%",
        //   "top": "90%",
        //   "orient": "horizontal",
        //   "textStyle": {
        //     "fontSize": 14,
        //     "color": "#fff"
        //   },
        //   "data": ["高一(1)班"]
        // },
        "radar": {
          "center": ["50%", "45%"],
          "radius": "50%",
          "startAngle": 180,
          "splitNumber": 4,
          "shape": "circle",
          "splitArea": {
            "areaStyle": {
              "color": ["transparent"]
            },
          },
          "axisLabel": {
            "show": false,
            "fontSize": 18,
            "color": "#fff",
            "fontStyle": "normal",
            "fontWeight": "normal"
          },
          "axisLine": {
            "show": true,
            "lineStyle": {
              "color": "#6C78D1"//
            }
          },
          name: {
            formatter: '{value}',
            textStyle: {
              color: '#0CCDFF' // 文字颜色
            }
          },
          "splitLine": {
            "show": true,
            "lineStyle": {
              "color": "#6C78D1"//
            }
          },
          "indicator": xData
        },
        "series": [{
          "name": "文化程度统计",
          "type": "radar",
          "symbol": "circle",
          "symbolSize": 10,
          "areaStyle": {
            "normal": {
              "color": "rgba(0, 217, 255, 0.4)"
            }
          },
          itemStyle: {
            color: 'rgba(245, 166, 35, 1)',
            borderColor: 'rgba(245, 166, 35, 0.3)',
            borderWidth: 8,
          },
          "lineStyle": {
            "normal": {
              "type": "solid",

              "color": "rgba(108, 120, 209, 1)",
              "width": 2
            }
          },
          label: {
            show: true,
            formatter: function (params) {
              return params.value;
            }
          },
          // "data": yData,
          data: [
            {
              value: yData,
              name: '文化程度统计'
            },
          ]
        }]
      })
    },
    initChart () {
      var trafficWay = this.testData.renyuan.eduData

      var data = [];
      var color = ['#00ffff', '#00cfff', '#006ced', '#ffe000', '#ffa800', '#ff5b00', '#ff3000']
      for (var i = 0; i < trafficWay.length; i++) {
        data.push({
          value: trafficWay[i].value,
          name: trafficWay[i].name,
          itemStyle: {
            normal: {
              borderWidth: 5,
              shadowBlur: 20,
              borderColor: color[i],
              shadowColor: color[i]
            }
          }
        }, {
          value: 2,
          name: '',
          itemStyle: {
            normal: {
              label: {
                show: false
              },
              labelLine: {
                show: false
              },
              color: 'rgba(0, 0, 0, 0)',
              borderColor: 'rgba(0, 0, 0, 0)',
              borderWidth: 0
            }
          }
        });
      }
      var seriesOption = [{
        name: '',
        type: 'pie',
        clockWise: false,
        radius: [35, 39],
        hoverAnimation: false,
        startAngle: 230,
        itemStyle: {
          normal: {
            label: {
              show: true,
              position: 'outside',
              color: '#ddd',
              formatter: function (params) {
                var percent = 0;
                var total = 0;
                for (var i = 0; i < trafficWay.length; i++) {
                  total += trafficWay[i].value;
                }
                percent = ((params.value / total) * 100).toFixed(0);
                if (params.name !== '') {
                  return params.name + '\n' + '\n' + params.value;
                } else {
                  return '';
                }
              },
            },
            labelLine: {
              length: 20,
              length2: 30,
              show: true,
              color: '#00ffff'
            }
          }
        },
        data: data
      }];
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        // backgroundColor: '#0A2E5D',
        color: color,
        title: {
          text: '文化程度',
          top: '48%',
          textAlign: "center",
          left: "49%",
          textStyle: {
            color: '#fff',
            fontSize: 12,
            fontWeight: '400'
          }
        },
        graphic: {
          elements: [{
            type: "image",
            z: 3,
            style: {
              width: 58,
              height: 58
            },
            left: 'center',
            top: 'center',
            position: [20, 20]
          }]
        },
        tooltip: {
          show: true
        },
        // legend: {
        //   icon: "circle",
        //   orient: 'horizontal',
        //   // x: 'left',
        //   data: ['火车', '飞机', '客车', '轮渡'],
        //   right: 340,
        //   bottom: 150,
        //   align: 'right',
        //   textStyle: {
        //     color: "#fff"
        //   },
        //   itemGap: 20
        // },
        toolbox: {
          show: false
        },
        series: seriesOption
      })
    },
    geteducationApi () {
      this.yData = []
      this.xData = [{
        "name": "专科"
      }, {
        "name": "中专"
      }, {
        "name": "初中"
      }, {
        "name": "本科"
      }, {
        "name": "小学"
      }, {
        "name": "文盲"
      }, {
        "name": "研究生"
      }, {
        "name": "高中"
      }]
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getEducationStatistics(communityId, communityChildId).then(res => {
        // console.log(res, 'ressssss3323')
        let num = []
        if (res && res.data.length > 0) {
          this.xData = []
          res.data.forEach(data => {
            this.xData.push({
              name: data.degree_name
            })
            num.push(data.num)
          });
          this.yData.push(num)
        }
        // if (res && res.data.length > 0) {
        //   res.data.forEach(data => {
        //     num.push(data.num)
        //   });
        // }
        this.$nextTick(() => {
          this.initChart()
        })
      })
    }
  }
}
</script>
