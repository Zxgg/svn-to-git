<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getPertype } from '@/api/PerInformation/perType'

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
        // this.getTypeApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      communityId: '',
      communityChildId: '',
      chart: null,
      xData: [{
        name: "关爱人员",
        value: 0,
      },
      {
        name: "管控人员",
        value: 0,
      },
      {
        name: "特殊人员",
        value: 0,
      }]
    }
  },
  mounted () {
    this.xData = this.testData.renyuan.personTypeData
    console.log('123:', this.xData)
    this.$nextTick(() => {
      this.initChart()
    })
    // this.getTypeApi()
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
      // let color = ['#ffc300', '#00e473', '#009DFF', '#0034ff'];
      let color = [
        ["rgb(255,195,0)", "rgb(0,228,115)", "rgb(0,157,255)", "rgb(0,52,255)"],
        ["rgba(255,195,0,0.1)", "rgba(0,228,115,0.1)", "rgba(0,157,255,0.1)", "rgba(0,52,255,0.1)"]
      ]
      var chartData = xData;
      let arrName = [];
      let arrValue = [];
      let sum = 0;
      let pieSeries = [],
        lineYAxis = [];

      // 数据处理
      chartData.forEach((v, i) => {
        arrName.push(v.name);
        arrValue.push(v.value);
        sum = sum + v.value;
      })

      // 图表option整理
      chartData.forEach((v, i) => {
        pieSeries.push({
          name: '人员类型',
          type: 'pie',
          clockWise: false,
          hoverAnimation: false,
          radius: [75 - i * 15 + '%', 67 - i * 15 + '%'],
          center: ["40%", "50%"],
          label: {
            show: false
          },
          data: [{
            value: v.value,
            name: v.name
          }, {
            value: sum - v.value,
            name: '',
            itemStyle: {
              color: "rgba(0,0,0,0)"
            }
          }]
        });
        pieSeries.push({
          name: '',
          type: 'pie',
          silent: true,
          z: 1,
          clockWise: false, //顺时加载
          hoverAnimation: false, //鼠标移入变大
          radius: [75 - i * 15 + '%', 67 - i * 15 + '%'],
          center: ["40%", "50%"],
          label: {
            show: false
          },
          data: [{
            value: 7.5,
            itemStyle: {
              color: color[1][i]
            }
          }, {
            value: 2.5,
            name: '',
            itemStyle: {
              color: "rgba(0,0,0,0)"
            }
          }]
        });
        v.percent = (v.value / sum * 100).toFixed(1) + "%";
        lineYAxis.push({
          value: i,
          textStyle: {
            rich: {
              circle: {
                color: color[0][i],
                padding: [0, 5]
              }
            }
          }
        });
      })
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        color: color[0],
        grid: {
          top: '12%',
          bottom: '54%',
          left: "40%",
          height: '70%',
          containLabel: false
        },
        tooltip: {
          show: true,
          trigger: "item",
          formatter: "{a}<br>{b}:{c}({d}%)"
        },
        yAxis: [{
          type: 'category',
          inverse: true,
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            formatter: function (params) {
              let item = chartData[params];
              return '{line|}{circle|●}{name|' + item.name + '}'
            },
            interval: 0,
            inside: true,
            textStyle: {
              fontSize: 14,
              rich: {
                line: {
                  width: '50%',
                  height: 10
                },
                name: {
                  fontSize: 14,
                },
              }
            },
            show: true
          },
          data: lineYAxis
        }],
        xAxis: [{
          show: false
        }],
        series: pieSeries
      })
    },
    getTypeApi () {
      this.xData = [{
        name: "关爱人员",
        value: 0,
      },
      {
        name: "管控人员",
        value: 0,
      },
      {
        name: "特殊人员",
        value: 0,
      }]
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getPertype(communityId, communityChildId).then(res => {
        // console.log(res, 'kkkkkk')
        if (res && res.data.length > 0) {
          for (let index = 0; index < res.data.length; index++) {
            this.xData[index].value = res.data[index].num
          }
        }
        // res.data.forEach(data => {
        //   let xData = {
        //     name: data.name,
        //     value: data.num
        //   }
        //   this.xData.push(xData)
        // });
        this.$nextTick(() => {
          this.initChart()
        })
      })
    }
  }
}
</script>
