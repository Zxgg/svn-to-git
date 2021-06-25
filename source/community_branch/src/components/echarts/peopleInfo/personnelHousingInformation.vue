<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getPerHousingStatistics } from '@/api/PerInformation/perHousingStatistics'


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
        // this.getHousingApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      chart: null,
      xData: [{
        "name": "自住",
        "value": 0
      }, {
        "name": "租住",
        "value": 0
      }, {
        "name": "寄住",
        "value": 0
      }, {
        "name": "其他",
        "value": 0
      }]
    }
  },
  mounted () {
    this.xData = this.testData.renyuan.perHousingData
    this.$nextTick(() => {
      this.initChart()
    })
    // this.getHousingApi()
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
      var data = this.xData
      let sum = 0
      data.forEach(item => {
        sum = item.value + sum
      });
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        color: ["rgb(255,195,0)", "rgb(0,228,115)", "rgb(0,157,255)", "rgb(0,52,255)"],
        title: {
          text: sum,
          subtext: '总数',
          textStyle: {
            color: '#f2f2f2',
            fontSize: 12,
          },
          subtextStyle: {
            fontSize: 12,
            color: ['#ff9d19']
          },
          x: 'center',
          y: '40%',
        },
        tooltip: {
          trigger: 'item',
          formatter: '{b}<br/> {c}  ({d}%)'
        },
        // legend: {
        //   orient: 'horizontal',
        //   top: 5,
        //   textStyle: {
        //     color: '#f2f2f2',
        //     fontSize: 12,
        //   },
        //   itemWidth: 10,
        //   itemHeight: 10,
        //   icon: 'roundRect',
        //   data: data,
        // },
        series: [
          // 主要展示层的
          {
            radius: ['35%', '60%'],
            center: ['50%', '45%'],
            type: 'pie',
            label: {
              normal: {
                show: false
              }
            },
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
            data: data,

          },
          // 边框的设置
          {
            radius: ['35%', '30%'],
            center: ['50%', '45%'],
            type: 'pie',
            label: {
              normal: {
                show: false
              },
              emphasis: {
                show: false
              }
            },
            labelLine: {
              normal: {
                show: false
              },
              emphasis: {
                show: false
              }
            },
            animation: false,
            tooltip: {
              show: false
            },
            data: [{
              value: 1,
              itemStyle: {
                color: "rgba(250,250,250,0.3)",
              },
            }],
          }, {
            name: '外边框',
            type: 'pie',
            clockWise: false, //顺时加载
            hoverAnimation: false, //鼠标移入变大
            center: ['50%', '45%'],
            radius: ['70%', '70%'],
            label: {
              normal: {
                show: false
              }
            },
            labelLine: {
              normal: {
                show: false
              },
              emphasis: {
                show: false
              }
            },
            data: [{
              value: 7789,
              name: '',
              itemStyle: {
                normal: {
                  borderWidth: 2,
                  borderColor: '#0b5263'
                }
              }
            }]
          },
        ]
      })
    },
    getHousingApi () {
      this.xData = [{
        "name": "自住",
        "value": 0
      }, {
        "name": "租住",
        "value": 0
      }, {
        "name": "寄住",
        "value": 0
      }, {
        "name": "其他",
        "value": 0
      }]
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getPerHousingStatistics(communityId, communityChildId).then(res => {
        // console.log(res, 'res0022')
        this.xData = []
        if (res && res.data.length > 0) {
          // for (let index = 0; index < res.data.length; index++) {
          //   this.xData[index].name = res.data[index].name
          //   this.xData[index].value = res.data[index].num
          // }
          res.data.forEach(data => {
            let xData = {
              name: data.name,
              value: data.num
            }
            this.xData.push(xData)
          });
          // console.log('租住：', this.xData)
        }

        this.$nextTick(() => {
          this.initChart()
        })
      })
    }
  }
}
</script>
