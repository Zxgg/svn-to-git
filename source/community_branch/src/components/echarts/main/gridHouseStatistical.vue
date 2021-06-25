<template>
  <div :class="className" :style="{ height: height, width: width }" />
</template>

<script>
//网格户数统计
import echarts from "echarts";
require("echarts/theme/macarons"); // echarts theme
import resize from "../mixins/resize";
import { getPerProportion } from "@/api/homePage/perProportion";
const animationDuration = 6000;

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: "chart"
    },
    width: {
      type: String,
      default: "100%"
    },
    height: {
      type: String,
      default: "100%"
    },
    currentComunity: {
      communityId: String,
      communityChildId: String
    }
  },
  watch: {
    currentComunity: {
      handler(val, data) {
        this.getPerProportionData();
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data() {
    return {
      chart: null,
      mobilityData: []
    };
  },
  mounted() {
    this.getPerProportionData();
    this.$nextTick(() => {
      this.initChart();
    });
  },
  beforeDestroy() {
    if (!this.chart) {
      return;
    }
    this.chart.dispose();
    this.chart = null;
  },
  methods: {
    initChart() {
      let data = [
        {
          name: "本地人",
          value: 4800
        },
        {
          name: "外地人",
          value: 1000
        },
        {
          name: "访客",
          value: 800
        }
      ];
      data = this.mobilityData;
      // console.log(data, 'data')
      var num = 75;
      this.chart = echarts.init(this.$el, "macarons");
      this.chart.setOption({
        name: "",
        type: "pie",
        clockWise: false,
        radius: [105, 109],
        hoverAnimation: false,
        itemStyle: {
          normal: {
            label: {
              show: true,
              position: "outside",
              color: "#ddd",
              formatter: function(params) {
                var percent = 0;
                var total = 0;
                for (var i = 0; i < trafficWay.length; i++) {
                  total += trafficWay[i].value;
                }
                percent = ((params.value / total) * 100).toFixed(0);
                if (params.name !== "") {
                  return (
                    "教师职称：" +
                    params.name +
                    "\n" +
                    "\n" +
                    "占百分比：" +
                    percent +
                    "%"
                  );
                } else {
                  return "";
                }
              }
            },
            labelLine: {
              length: 30,
              length2: 100,
              show: true,
              color: "#00ffff"
            }
          }
        },
        data: data
      });
    },
    getPerProportionData() {
      this.mobilityData = [];
      let temp = [];
      this.testData.renyuan.peopleHousingInfoList.forEach(element => {
        temp.push({
          name: element.gridName,
          value: element.households
        });
      });
      this.mobilityData = temp;
      this.$nextTick(() => {
        this.initChart();
      });
      let communityId = this.currentComunity.communityId;
      let communityChildId = this.currentComunity.communityChildId;
      // getPerProportion(communityId, communityChildId).then((res) => {
      //   // console.log(res, '123545sadsa')
      //   if (res && res.data) {
      //     this.mobilityData = [{
      //       name: "本地人",
      //       value: res.data.local
      //     }, {
      //       name: "外地人",
      //       value: res.data.nonlocal
      //     }, {
      //       name: "访客",
      //       value: res.data.visitors
      //     }]
      //   }
      //   this.$nextTick(() => {
      //     this.initChart()
      //   })
      // })
    }
  }
};
</script>
