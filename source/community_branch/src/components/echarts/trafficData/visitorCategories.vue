<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getVistorType } from '@/api/trafficData/visitorType'
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
        this.gettrafficApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      chart: null,
      xData: [],
      yData: []
    }
  },
  mounted () {
    this.$nextTick(() => {
      this.initChart()
    })
    this.gettrafficApi()
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
      let xData = ['临时访问车辆', '预约访问车辆', '临时访问人员', '预约访问人员']
      let yData = [300, 200, 100, 50]
      xData = this.xData
      yData = this.yData
      var getname = xData;
      var getvalue = yData;
      var data = [];
      for (var i = 0; i < getname.length; i++) {
        data.push({
          name: getname[i],
          value: getvalue[i]
        })
      }
      var colorList = ['#503EFF', '#3E82FF', '#8BF39A', '#00FCFD'];
      var rich = {
        name: {
          color: "#FFF",
          fontSize: 12,
          padding: [10, 15, 0, 10],
          fontWeight: '400',
          align: 'left'
        },
        value: {
          color: "#FFF",
          fontSize: 12,
          padding: [10, 10, 0, 15],
          fontWeight: '500',
          align: 'right'
        },
        percent: {
          color: "#FFF",
          align: 'right',
          fontSize: 12,
          fontWeight: '500',
          //padding: [0, 5]
        },
        hr: {
          width: '100%',
          height: 0,
        },
        cir: {
          fontSize: 26,
        }
      }
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        tooltip: {
          trigger: 'axis',
        },
        // title: {
        //   right: '10%',
        //   text: '周/7days',
        //   top: '5%',
        //   textStyle: {
        //     color: '#fff',
        //     fontStyle: 'normal',
        //     fontFamily: '微软雅黑',
        //     fontSize: 12,
        //   },
        // },
        series: [{
          tooltip: {
            trigger: 'item',
            formatter: function (params) {
              return params.name + '：' + params.value + '  人/车<br>占比：' + params.percent.toFixed(2) + '%'
            }
          },
          itemStyle: {
            normal: {
              borderColor: '#0A1934',
              borderWidth: 2,
              color: function (params) {
                return colorList[params.dataIndex]
              }
            }
          },
          type: 'pie',
          radius: ['30%', '50%'],
          center: ["50%", "50%"],
          label: {
            normal: {
              show: false,
              position: 'inner',
              formatter: params => {
                return (
                  '{percent|' + params.percent.toFixed(0) + '%}'
                );
              },
              rich: rich,
            }
          },
          data: data
        }, {
          itemStyle: {
            normal: {
              borderColor: '#0A1934',
              borderWidth: 5,
              color: function (params) {
                return colorList[params.dataIndex]
              }
            }
          },
          type: 'pie',
          silent: true, //取消高亮
          radius: ['30%', '50%'],
          center: ["50%", "50%"],
          labelLine: {
            normal: {
              length: 30,
              length2: 0,
              lineStyle: {
                color: 'transparent'
              }
            }
          },
          label: {
            normal: {

              formatter: params => {
                return '{name|' + params.name + '}{value|' + params.value + '}\n{hr|————————}';
              },
              rich: rich,
              padding: [-20, 25, 0, 25]
            }
          },
          data: data,
          z: -1
        }, {
          itemStyle: {
            normal: {
              borderColor: '#0A1934',
              borderWidth: 5,
              color: function (params) {
                return colorList[params.dataIndex]
              }
            }
          },
          type: 'pie',
          silent: true, //取消高亮
          radius: ['30%', '50%'],
          center: ["50%", "50%"],
          labelLine: {
            normal: {
              length: 30,
              length2: 0,
              lineStyle: {
                color: 'transparent'
              }
            }
          },
          label: {
            normal: {
              formatter: params => {
                return '\n{cir|●}\n';
              },
              rich: rich,
            }
          },
          data: data,
          z: -1
        }]
      })
    },
    gettrafficApi () {
      this.xData = ['临时访问车辆', '预约访问车辆', '临时访问人员', '预约访问人员']
      this.yData = [0, 0, 0, 0]
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getVistorType(communityId, communityChildId).then(res => {
        // console.log(res, 'ns')
        if (res && res.data) {
          this.yData = []
          this.yData.push(res.data.week_visit_vehicle_linshi, res.data.week_visit_vehicle_yuyue, res.data.week_visit_person_linshi, res.data.week_visit_person_yuyue)
          // for (let i = 0; i < res.data.length; i++) {
          //   this.yData[0].push()
          //   this.yData[1].push()
          //   this.yData[2].push()
          //   this.yData[3].push()
          // }
        }
        this.$nextTick(() => {
          this.initChart()
        })
      })
    }
  }
}
</script>
