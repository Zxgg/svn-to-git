<template>
  <div class="caringList">
    <el-row class="el_row_">
      <el-col v-for="item in listData">
        <el-card :body-style="{ padding: '0px' }">
          <img :src="item.pics"
               class="image">
          <div style="padding: 1%;"
               class="list">
            <span>姓名：{{item.personName}}</span>
            <span>性别：{{item.sex}}</span>
            <span>年龄：{{item.age}}</span>
            <span>类型：{{item.type}}</span>
            <span>手机：{{item.phoneNumber}}</span>
            <span>住址：{{item.communityChildName}}</span>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import $ from "jquery";
import { mapGetters, mapState } from "vuex";
import { getCaringPeopleList } from '@/api/caringPeople/caringPeoList'
export default {
  data () {
    return {
      listData: [],
      caringList: []
    };
  },
  computed: {
    ...mapGetters(["currentComunity"]),
  },
  created () {
    this.getDicts("base_type_of_caring").then(response => {//关爱人员类型字典
      this.caringList = response.data;
    }),
      this.getDicts("sys_user_sex").then(response => {//用户性别字典
        this.sexList = response.data;
      })
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        this.getcaringlistApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  mounted () {
    // this.getcaringlistApi()
    this.listData = this.testData.guanai.careList;
  },
  methods: {
    getNameByType (value) {
      // // 关爱人员类型字典翻译
      return this.selectDictLabel(this.caringList, value);
    },
    getSexByType (value) {
      // // 关爱人员性别类型字典翻译
      return this.selectDictLabel(this.sexList, value);
    },
    getRowClass ({ row, column, rowIndex, columnIndex }) {
      return "background:#222751;color:#fff;border-bottom-color:#182751;";
    },
    getcaringlistApi () {
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getCaringPeopleList(communityId, communityChildId).then(res => {
        if (res.data != null) {
          // res.data.forEach(data => {
          //   data.sex = self.getSexByType(data.sex)
          //   data.type = self.getNameByType(data.type)
          //   // console.log(data.pics)
          //   // data.pics.forEach(pic => {
          //   // })
          // })
          this.listData = res.data
        }
      })
    }
  }
}
</script>
<style lang="scss" scoped>
.el-row {
  width: 100%;
}
.el-card img {
  width: 50%;
  float: left;
  height: 100%;
}
.el-col {
  width: 50%;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  margin-bottom: 15px;
}
.el-card {
  background: url("../../assets/images/IntelligentSearch/videoBackground.png")
    no-repeat center center / 100% 100% !important;
  width: 100%;
  border: none;
  border-radius: 0;
  padding: 3%;
}
.list {
  float: left;
  font-size: 12px;
  background: rgba(34, 39, 81, 0.8);
  width: 47%;
  margin-left: 3%;
  color: cadetblue;
  height: 100%;
}
.list > span {
  display: block;
  margin-bottom: 3%;
  margin-top: 2%;
}
</style>