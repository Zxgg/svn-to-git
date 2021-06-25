<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getHouseNature } from "@/api/homePage/houseNature"
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
        this.getHouseNatureData()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      chart: null,
      houseNumData: [{
        name: "自住",
        value: 0,
      },
      {
        name: "出租",
        value: 0,
      },
      {
        name: "商用",
        value: 0,
      },
      {
        name: "其他",
        value: 0,
      }]
    }
  },
  mounted () {
    this.getHouseNatureData()
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
    getArrayValue (array, key) {
      var key = key || "value";
      var res = [];
      if (array) {
        array.forEach(function (t) {
          res.push(t[key]);
        });
      }
      return res;
    },
    array2obj (array, key) {
      var resObj = {};
      for (var i = 0; i < array.length; i++) {
        resObj[array[i][key]] = array[i];
      }
      return resObj;
    },
    getData (data) {
      var res = {
        series: [],
        yAxis: []
      };
      for (let i = 0; i < data.length; i++) {
        // console.log([70 - i * 15 + '%', 67 - i * 15 + '%']);
        res.series.push({
          name: '',
          type: 'pie',
          clockWise: false, //顺时加载
          hoverAnimation: false, //鼠标移入变大
          radius: [70 - i * 15 + '%', 60 - i * 15 + '%'],
          center: ["30%", "50%"],
          label: {
            show: false
          },
          itemStyle: {
            label: {
              show: false,
            },
            labelLine: {
              show: false
            },
            borderWidth: 5,
          },
          data: [{
            value: data[i].value,
            name: data[i].name
          }, {
            value: this.sumValue - data[i].value,
            name: '',
            itemStyle: {
              color: "rgba(0,0,0,0)",
              borderWidth: 0
            },
            tooltip: {
              show: false
            },
            hoverAnimation: false
          }]
        });
        res.series.push({
          name: '',
          type: 'pie',
          silent: true,
          z: 1,
          clockWise: false, //顺时加载
          hoverAnimation: false, //鼠标移入变大
          radius: [73 - i * 15 + '%', 68 - i * 15 + '%'],
          center: ["30%", "55%"],
          label: {
            show: false
          },
          itemStyle: {
            label: {
              show: false,
            },
            labelLine: {
              show: false
            },
            borderWidth: 5,
          },
          data: [{
            value: 7.5,
            itemStyle: {
              color: "rgba(0, 0, 0, 0)",
              borderWidth: 0
            },
            tooltip: {
              show: false
            },
            hoverAnimation: false
          }, {
            value: 2.5,
            name: '',
            itemStyle: {
              color: "rgba(0,0,0,0)",
              borderWidth: 0
            },
            tooltip: {
              show: false
            },
            hoverAnimation: false
          }]
        });
        res.yAxis.push((data[i].value / this.sumValue * 100).toFixed(2) + "%");
      }
      return res;
    },
    initChart () {
      let color = ['#F7B500', '#F47301', '#F23302', '#F2023B'];
      // let color = [
      //   ["rgb(255,195,0)", "rgb(0,228,115)", "rgb(0,157,255)", "rgb(0,52,255)"],
      //   ["rgba(255,195,0,0.1)", "rgba(0,228,115,0.1)", "rgba(0,157,255,0.1)", "rgba(0,52,255,0.1)"]
      // ]
      let data = [];
      data = this.houseNumData
      let arrName = this.getArrayValue(data, "name");
      let arrValue = this.getArrayValue(data, "value");
      this.sumValue = eval(arrValue.join('+'));
      let objData = this.array2obj(data, "name");
      let optionData = this.getData(data)
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        color: color,
        title: {
          subtext: '使用属性',
          subtextStyle: {
            fontSize: 14,
            color: ['#F2DE02']
          },
          x: '15%',
          y: 'center',
        },
        legend: {
          show: true,
          icon: "circle",
          top: "center",
          top: '15%',
          bottom: '50%',
          left: "30%",
          data: arrName,
          width: 40,
          padding: [0, 0],
          itemGap: 0,
          itemWidth: 0,
          formatter: ((name) => {
            let percent = (isNaN(objData[name].value) || !objData[name].value ? 0 : objData[name].value / this.sumValue * 100).toFixed(1) + "%";
            return "{title|" + name + "}{scale|(" + percent + ")}{value|  " + (objData[name].value) + "}  "
          }),
          textStyle: {
            rich: {
              title: {
                fontSize: 12,
                lineHeight: 0,
                color: color
              },
              scale: {
                fontSize: 12,
                lineHeight: 0,
                color: color
              },
              value: {
                fontSize: 12,
                lineHeight: 0,
                color: color
              }
            }
          },
        },
        tooltip: {
          show: true,
          trigger: "item",
          formatter: "{a}<br>{b}:{c}({d}%)"
        },
        xAxis: [{
          show: false
        }],
        series: optionData.series
      })
    },
    getHouseNatureData () {
      this.houseNumData = this.testData.shouye.renfangche.houseNumData
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      // getHouseNature(communityId, communityChildId).then((res) => {
      //   // console.log(res, '123545sadsa')
      //   if (res && res.data.length > 0) {
      //     for (let index = 0; index < res.data.length; index++) {
      //       this.houseNumData[index].value = res.data[index].num
      //     }
      //     // res.data.forEach(data => {
      //     //   let houseNum = {
      //     //     name: '',
      //     //     value: 0
      //     //   }
      //     //   houseNum.name = data.name
      //     //   houseNum.value = data.num
      //     //   this.houseNumData.push(houseNum)
      //     // });
      //   }
      //   this.$nextTick(() => {
      //     this.initChart()
      //   })
      // })
    },
  }
}
</script>
