<template>
  <div class="chart-wrapper">
    <pie-chart :data="data"
               :height="'280px'" />
  </div>
</template>
<script>
import PieChart from '@/components/dashboard/PieChart'
import { statisticalVote_record } from "@/api/communityServices/vote_record";
import { listVote_option } from "@/api/communityServices/vote_option";

export default {
  components: { PieChart },
  // props: { dataInfo: { type: Object, default: null } },
  data () {
    return {
      that: this,
      queryParams: {
        voteProjectId: null,
        voteProjectName: null
      },
      data: null,
      height: 260,
      vote_optionList: null
    }
  },
  created () {
    const voteProjectId = this.$route.params && this.$route.params.projectId;
    const voteProjectName = this.$route.params && this.$route.params.projectName;
    if (voteProjectId && voteProjectId.length > 0) {
      this.queryParams.voteProjectId = voteProjectId
      this.queryParams.voteProjectName = voteProjectName
      let param = {
        voteProjectId: this.queryParams.voteProjectId
      }
      listVote_option(param).then(response => {
        this.vote_optionList = response.rows;
      });
    }
  },
  mounted () {
    if (this.queryParams.voteProjectId && this.queryParams.voteProjectId.length) {
      let self = this
      setTimeout(function () {
        self.showData()
      }, 400)
    }
  },
  filters: {},
  methods: {
    showData () {
      statisticalVote_record(this.queryParams).then(response => {
        if (response.data) {
          let list = []
          for (let key in response.data) {
            console.log(key);
            list.push({
              name: this.genderCodeFormat(key),
              value: response.data[key]
            })
          }
          this.data = list
        }

      });
    },
    genderCodeFormat (optionId) {
      if (!(this.vote_optionList && this.vote_optionList.length > 0)) return optionId
      let rows = this.vote_optionList.filter(item => {
        return item.id === optionId
      })
      if (rows && rows.length > 0) return rows[0].name
    }
  },
  computed: {},
  watch: {
    // dataInfo: {
    //   handler: function (newValue) {
    //     console.log('new value change')
    //     if (!newValue) return false
    //     this.queryParams.voteProjectId = newValue.voteProjectId
    //     this.queryParams.voteProjectName = newValue.voteProjectName
    //   },
    //   deep: true,
    //   immediate: true
    // }
  },
  beforeDestroy () { }
}
</script>
<style lang="scss" scoped>
.chart-wrapper {
  background: transparent;
  padding: 8px 16px 0;
  margin-bottom: 8px;
}
</style>