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
      chart: null,
      data: this.testData.renyuan.nationXData
    }
  },
  mounted () {
    this.getDicts("base_nation").then((response) => {
      this.ditDegree = response.data;
    });
    this.xData = this.data
    this.$nextTick(() => {
      this.initChart()
    })
    this.$Notice.$on('communityGrid', (val) => {
      this.getCode(val)
    })
    this.$Notice.$on('communityGridReturn', (val) => {
      this.data = this.testData.renyuan.nationXData
      this.$nextTick(() => {
        this.initChart()
      })
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
      // var total = 459
      let img = require('@/assets/images/peopleinfo/national.png')
      var trafficWay = this.data
      var data = [];
      var color = ['#00EAFF', '#E7E1FF', '#FFA360', '#277EEA']
      for (var i = 0; i < trafficWay.length; i++) {
        data.push({
          value: trafficWay[i].value,
          name: trafficWay[i].name,
          itemStyle: {
            normal: {
              borderWidth: 5,
              shadowBlur: 5,
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
        startAngle: 240,
        clockWise: false,
        radius: ["60%", "65%"],
        center: ['50%', '50%'],
        hoverAnimation: false,
        itemStyle: {
          normal: {
            label: {
              show: true,
              position: 'outside',
              color: '#ddd',
              fontSize: 16,
              formatter: function (params) {
                var percent = 0;
                var total = 0;
                for (var i = 0; i < trafficWay.length; i++) {
                  total += trafficWay[i].value;
                }
                percent = ((params.value / total) * 100).toFixed(0);
                if (params.name !== '') {
                  return params.name + '：' + params.value + '人';
                } else {
                  return '';
                }
              },
            },
            labelLine: {
              length: 20,
              length2: 35,
              show: true,
              color: '#00ffff'
            }
          }
        },
        data: data
      }];
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        color: color,
        graphic: {
          elements: [{
            type: "image",
            z: 3,
            style: {
              image: img,
              width: 178,
              height: 178
            },
            left: 'center',
            top: 'center',
            position: [100, 100]
          }]
        },
        tooltip: {
          show: false
        },
        toolbox: {
          show: false
        },
        series: seriesOption
      })
    },
    getCode (code) {
      getPeopleInfoApi(code).then(res => {
        this.data = []
        if (res && res.data.nationXData.length > 0) {
          for (let index = 0; index < res.data.nationXData.length; index++) {
            res.data.nationXData[index].name = this.selectDictLabel(this.ditDegree, res.data.nationXData[index].name);
          }
          res.data.nationXData.forEach(item => {
            if (item.name != '汉族') {
              this.data.push(item)
            }
          });
          if (this.data.length <= 0) {
            this.data.push({ name: '无其他民族', value: 0 })
          }
          this.$nextTick(() => {
            this.initChart()
          })
        }
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
