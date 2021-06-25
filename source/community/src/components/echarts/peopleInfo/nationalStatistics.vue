<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getNationStatistics } from '@/api/PerInformation/nationStatistics'

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
        // this.getNationApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      xData: [{
        "name": "汉族",
        "value": 55
      }, {
        "name": "回族",
        "value": 30
      }, {
        "name": "苗族",
        "value": 10
      }, {
        "name": "哈萨克斯族",
        "value": 5
      }],
      yData: [],
      chart: null
    }
  },
  mounted () {
    this.xData = this.testData.renyuan.nationXData
    this.$nextTick(() => {
      this.initChart()
    })
    // this.getNationApi()
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
      let yData = [{
        "offset": 0,
        "color": "#32C5FF"
      }, {
        "offset": 0.2,
        "color": "#01A4F7"
      }, {
        "offset": 0.5,
        "color": "#3B4AFF"
      }, {
        "offset": 0.8,
        "color": "#FEE449"
      }, {
        "offset": 1,
        "color": "#00FFE9"
      }]
      let xData = []
      xData = this.xData
      yData = this.yData
      var total = 459
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        "animation": true,
        "title": {
          "text": "民族统计",
          // "subtext": "火点类型",
          "x": "center",
          "y": "center",
          "textStyle": {
            "color": "#fff",
            "fontSize": 12,
            "fontWeight": "normal",
            "align": "center",
            "width": "200px"
          },
          "subtextStyle": {
            "color": "#fff",
            "fontSize": 20,
            "fontWeight": "normal",
            "align": "center"
          }
        },
        "series": [{
          "type": "pie",
          "center": ["50%", "50%"],
          "radius": ["50%", "63%"],
          "color": ["#01FFFD", "#35A8FF", "#006CED", "#F6B200",],
          "startAngle": 120,
          "labelLine": {
            "normal": {
              "length": 25
            }
          },
          "hoverOffset": 3,
          "label": {
            "normal": {
              "formatter": "{b|{b}:}  {per|{d}%} ",
              "backgroundColor": "rgba(255, 147, 38, 0)",
              "borderColor": "transparent",
              "borderRadius": 4,
              "rich": {
                "a": {
                  "color": "#999",
                  "lineHeight": 22,
                  "align": "center"
                },
                "hr": {
                  "borderColor": "#aaa",
                  "width": "100%",
                  "borderWidth": 1,
                  "height": 0
                },
                "b": {
                  "color": "#b3e5ff",
                  "fontSize": 12,
                  "lineHeight": 33
                },
                "c": {
                  "fontSize": 12,
                  "color": "#eee"
                },
                "per": {
                  "color": "#FDF44E",
                  "fontSize": 12,
                  "padding": [5, 8],
                  "borderRadius": 2
                }
              },
              "textStyle": {
                "color": "#fff",
                "fontSize": 16
              }
            }
          },
          "emphasis": {
            "label": {
              "show": true,
              "formatter": "{b|{b}:}  {per|{d}%}  ",
              "backgroundColor": "rgba(255, 147, 38, 0)",
              "borderColor": "transparent",
              "borderRadius": 4,
              "rich": {
                "a": {
                  "color": "#999",
                  "lineHeight": 22,
                  "align": "center"
                },
                "hr": {
                  "borderColor": "#aaa",
                  "width": "100%",
                  "borderWidth": 1,
                  "height": 0
                },
                "b": {
                  "color": "#fff",
                  "fontSize": 14,
                  "lineHeight": 33
                },
                "c": {
                  "fontSize": 14,
                  "color": "#eee"
                },
                "per": {
                  "color": "#FDF44E",
                  "fontSize": 14,
                  "padding": [5, 6],
                  "borderRadius": 2
                }
              }
            }
          },
          "data": xData
        }, {
          "type": "pie",
          "center": ["50%", "50%"],
          "radius": ["35%", "38%"],
          "label": {
            "show": false
          },
          "hoverOffset": 2,
          "data": [{
            "value": 1,
            "name": "实例1",
            "itemStyle": {
              "normal": {
                "color": {
                  "x": 0,
                  "y": 0,
                  "x2": 1,
                  "y2": 0,
                  "type": "linear",
                  "global": false,
                  "colorStops": [{
                    "offset": 0,
                    "color": "#32C5FF"
                  }, {
                    "offset": 0.2,
                    "color": "#01A4F7"
                  }, {
                    "offset": 0.5,
                    "color": "#3B4AFF"
                  }, {
                    "offset": 0.8,
                    "color": "#FEE449"
                  }, {
                    "offset": 1,
                    "color": "#00FFE9"
                  }]
                }
              }
            }
          }]
        }]
      })
    },
    getNationApi () {
      this.xData = [{
        "name": "汉族",
        "value": 0
      }, {
        "name": "回族",
        "value": 0
      }, {
        "name": "苗族",
        "value": 0
      }, {
        "name": "哈萨克斯族",
        "value": 0
      }]
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getNationStatistics(communityId, communityChildId).then(res => {
        if (res && res.data.length > 0) {
          this.xData = []
          res.data.forEach(data => {
            let xData = {
              name: data.name,
              value: data.num * 100
            }
            this.xData.push(xData)
          });
        }
        this.$nextTick(() => {
          this.initChart()
        })
      })
    }
  }
}
</script>
