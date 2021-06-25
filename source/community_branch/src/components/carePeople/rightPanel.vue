<template>
  <!-- 右侧盒子 -->
  <div class='careright'>
    <div class="care-boxTitle">
      <span></span>
      <span>关爱人员列表展示</span>
    </div>
    <ul>
      <li>

      </li>
    </ul>
    <div>
      <rightList />
    </div>
  </div>
</template>
<script>
import rightList from '@/components/carePeople/rightList'
export default {
  name: "Index",
  components: { rightList },
  data () {
    return {

    }
  }
}
</script>