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
        // this.getAgeApi()
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
      data: this.testData.renyuan.ageData
    }
  },
  mounted () {
    let num = []
    this.testData.renyuan.ageData.y.forEach(item => {
      num.push((Number(item) / 100) * 12608)
    })
    this.data.y = num
    let numList = []
    this.data.y.forEach(element => {
      numList.push(Math.floor(element))
    });
    this.data.y = numList
    // this.getPeopleInfoApi()
    this.$nextTick(() => {
      this.initChart()
    })
    this.$Notice.$on('communityGrid', (val) => {
      this.getCode(val)
    })
    this.$Notice.$on('communityGridReturn', (val) => {
      this.data = this.testData.renyuan.ageData
      let num = []
      this.testData.renyuan.ageData.y.forEach(item => {
        num.push((Number(item) / 100) * 12608)
      })
      // this.data.y = num
      let numList = []
      this.data.y.forEach(element => {
        numList.push(Math.floor(element))
      });
      this.data.y = numList
      this.$nextTick(() => {
        this.initChart()
      })
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
      const createSvg = (shadowColor, shadowBlur) => `
        <svg version="1.1" xmlns="http://www.w3.org/2000/svg" 
            x="0px" y="0px" 
            viewBox="0 0 32 128"
            xml:space="preserve"
        >
            <style>
                .st2 {
                    fill: transparent;
                    stroke: ${shadowColor};
                    stroke-width: ${shadowBlur}px;
                    filter: url(#chart-inset-shadow);
                }
            </style>
            <defs>
                <filter id="chart-inset-shadow" width="200%" height="200%" x="-50%" y="-50%">
                <feGaussianBlur in="SourceGraphic" result="gass" stdDeviation="${shadowBlur * 0.75}" />
                <feMerge>
                  <feMergeNode in="gass" />
                </feMerge>
              </filter>
            </defs>
            <path class="st2" d="M0 0 L32 0 L32 128 L0 128 Z" />
        </svg>
      `;

      const svgString = createSvg('#156dff', 8);
      const svg = new Blob([svgString], { type: "image/svg+xml;charset=utf-8" });

      const DOMURL = window.URL || window.webkitURL || window;
      const insetShadowUrl = DOMURL.createObjectURL(svg);
      const dataSet = [
        this.data.x,
        this.data.y
      ];
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        grid: {
          left: '2%',
          top: '15%',
          right: '1%',
          bottom: '25%',
          // height: '90%'
        },
        xAxis: {
          type: 'category',
          axisLine: {
            show: false,
          },
          axisTick: {
            show: false,
          },
          axisLabel: {
            color: 'rgba(255,255,255, 0.5)',
          },
          splitLine: {
            show: false,
          },
          data: dataSet[0],
          splitArea: { show: false }//设置网格区域
        },
        yAxis: {
          type: "value",
          // max: '100',
          axisLabel: {
            textStyle: {
              color: "#fff"
            },
            formatter: "{value}%"
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: ['#434A7E'],
              width: 1,
              type: 'dashed'
            }
          },
          axisLine: {
            show: false
          },
          splitArea: { show: false }//设置网格区域
        },
        series: [
          {
            data: dataSet[1],
            type: 'pictorialBar',
            symbol: 'image://' + insetShadowUrl,
            barWidth: 14,
            label: {
              show: true,
              position: "top",
              distance: 10,
              color: '#C7DFE1'
            },
          },
          {
            data: dataSet[1],
            type: 'bar',
            barWidth: 14,
            itemStyle: {
              color: 'transparent',
              borderWidth: 3,
              borderColor: new echarts.graphic.LinearGradient(
                0, 0, 0, 1,
                [{
                  offset: 0,
                  color: '#2E7CDD'
                },
                {
                  offset: 1,
                  color: '#1D5EAE'
                },
                ]
              ),
              shadowColor: 'blue',
              shadowBlur: 12,
              shadowOffsetX: 0,
              shadowOffsetY: 0,
            },
          },
        ],
      })
    },
    getPeopleInfoApi () {
      getPeopleInfoApi('321002004013001').then(res => {
        if (res && res.data.ageData) {
          this.data = res.data.ageData
          this.$nextTick(() => {
            this.initChart()
          })
        } else {
          this.data = this.testData.renyuan.ageData
          this.$nextTick(() => {
            this.initChart()
          })
        }
      }).catch(err => {
        this.data = this.testData.renyuan.ageData
        this.$nextTick(() => {
          this.initChart()
        })
      })
    },
    getCode (code) {
      getPeopleInfoApi(code).then(res => {
        if (res && res.data.ageData) {
          this.data = res.data.ageData
          let num = []
          res.data.ageData.y.forEach(item => {
            num.push((Number(item) / 100) * 12608)
          })
          // this.data.y = num
          let numList = []
          this.data.y.forEach(element => {
            numList.push(Math.floor(element))
          });
          this.data.y = numList
          this.$nextTick(() => {
            this.initChart()
          })
        }
      })
    }
  }
}
</script>
