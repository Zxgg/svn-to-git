<template>
  <!-- 左侧盒子 -->
  <div class="careleft">
    <!-- 事件统计 -->
    <div class="personEvent">
      <div class="care-boxTitle">
        <span></span>
        <span>关爱人员类型统计</span>
      </div>
      <div style="width:100%;height:100%">
        <lefttype />
      </div>
    </div>
    <!-- 人员统计 -->
    <div class="personNum">
      <div class="care-boxTitle">
        <span></span>
        <span>关爱提醒数量统计</span>
      </div>
      <div>
        <leftremind />
      </div>
    </div>
    <!-- 搜索 -->
    <div class='caresearch'>
      <div class="care-boxTitle">
        <span></span>
        <span>关爱提醒搜索</span>
      </div>
      <search />
    </div>
  </div>
</template>
<script>
import $ from "jquery";
import lefttype from '@/components/echarts/carePeople/lefttype'
import leftremind from '@/components/carePeople/leftremind'
import search from '@/components/carePeople/search'
export default {
  name: "Index",
  components: { lefttype, leftremind, search },
}
</script>
<style scoped="true">
@import "~@/assets/styles/main/carepeople.css";
</style>