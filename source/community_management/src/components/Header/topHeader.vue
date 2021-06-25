<template>
  <div class="Main"
       ref="MainView">
    <div class="divStackPanel HorizontalLeft textLeft SystemTitle"
         style="width:900px;height:100%;justify-content:flex-start;overflow-y: hidden;">
      <img style="width:50px;height:50px;"
           :src="Systemlogo" />
      <h1 class="Title">{{SystemTitle}}</h1>
    </div>
    <div class="userInfoPanel HorizontalRight VerticalCenter">
      <div class="divStackPanel HorizontalRight textLeft LightTextBlock ">
        <div class="HorizontalMargin">
          <i class="el-icon-user"></i> 单位: <span>{{ currentUserDeptName }} </span>
        </div>
        <div class="HorizontalMargin">
          <span>用户: </span><span>{{ currentUserName }}</span>
        </div>
        <div @click="logoutClick"
             class="cursor_hander logout">退出</div>
      </div>
    </div>
  </div>
</template>
<script>
import SystemlogoImage from '@/assets/images/home/color.png'
export default {
  components: {},
  data () {
    return {
      that: this,
      SystemTitle: '智慧网格管理系统',
      Systemlogo: SystemlogoImage,
      centContainerHeight: '100px',
      currentUserName: '',
      currentUserNickName: '',
      currentUserphoneNumber: '',
      currentUserDeptName: '',
    }
  },
  created () { },
  filters: {},
  methods: {
    refreshCenterContainerHeight () {
      this.centContainerHeight = (this.$refs.MainView.offsetHeight - 72 - 331 - 4) + 'px'
    },
    loadUserInfo () {
      if (this.$cf.checkIsNotNull(this.$store.state.user)) {
        //用户本身信息
        this.currentUserName = this.$store.state.user.name
        this.currentUserNickName = this.$store.state.user.nickName
        this.currentUserphoneNumber = this.$store.state.user.phoneNumber
        //部门信息
        if (this.$cf.checkIsNotNull(this.$store.state.user.dept)) {
          this.currentUserDeptName = this.$store.state.user.dept.name
        }
      } else {

      }
    },
    logoutClick () {
      // this.$store.dispatch('LogOut').then(() => {
      //   this.$router.push({ path: '/' })
      // })
      this.$confirm('确定注销并退出系统吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.dispatch('LogOut').then(() => {
          location.href = '/index';
        })
      })
    }
  },
  mounted () {
    const the_page = this;
    this.loadUserInfo()
    // window.onresize = () => { return (() => { the_page.refreshCenterContainerHeight() })() }; this.refreshCenterContainerHeight()
  }
}
</script>
<style lang="scss" scoped>
@import "@/assets/styles/variables.scss";
.Main {
  width: 100%;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
  background: $titleBoderC;
}
.SystemTitle {
  margin-left: 25px;
  width: 900px;
  height: 100%;
  justify-content: flex-start;
  overflow-y: hidden;
}
.Title {
  margin-left: 15px;
  letter-spacing: 2px;
  font-size: 25px;
  color: rgba($color: $fontC, $alpha: 0.85);
  // width: 144px;
  // height: 22px;
  // font-size: 16px;
  // font-family: PingFangSC-Semibold, PingFang SC;
  // font-weight: 600;
  // color: rgba(255, 255, 255, 0.85);
  // line-height: 22px;
  // letter-spacing: 1px;
}
</style>