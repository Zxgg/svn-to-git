<template>
  <div class="caryear">
    <div class="carYeardiv">
      <span>
        入驻时间0~6年
      </span>
      <div class="carYearImg">
        <span style=" font-family: DINCondensed-Bold;">
          {{x}}
        </span>
        <span>
          辆
        </span>
      </div>
    </div>
    <div class="carYeardiv">
      <span>
        入驻时间6~15年
      </span>
      <div class="carYearImg">
        <span style=" font-family: DINCondensed-Bold;">
          {{y}}
        </span>
        <span>
          辆
        </span>
      </div>
    </div>
    <div class="carYeardiv">
      <span>
        入驻时间>15年
      </span>
      <div class="carYearImg">
        <span style=" font-family: DINCondensed-Bold;">
          {{z}}
        </span>
        <span>
          辆
        </span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      x: '',
      y: '',
      z: ''
    };
  },
  computed: {
  },
  filters: {
  },
  watch: {
  },
  mounted () {
    this.x = this.testData.car.VehicleYears.x
    this.y = this.testData.car.VehicleYears.y
    this.z = this.testData.car.VehicleYears.z
  },
  methods: {
  }
}
</script>

<style scoped="true">
@import "~@/assets/styles/main/carInfo.css";
</style>