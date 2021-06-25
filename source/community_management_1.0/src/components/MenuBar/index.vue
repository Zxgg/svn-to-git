<template>
  <div class="Main"
       ref="MainView">
    <el-scrollbar wrap-class="scrollbar-wrapper "
                  wrap-style="overflow-x:hidden;">
      <el-menu :default-active="activeMenu"
               mode="vertical"
               @select="menuSelect"
               class="menuList"
               :text-color="text_color"
               :unique-opened="true"
               :active-text-color="active_text_color"
               :collapse-transition="false"
               :collapse="isCollapse">
        <menuItem :menus="menusList" />
      </el-menu>
    </el-scrollbar>
  </div>
</template>
<script>
import path from 'path'
import { isExternal } from '@/utils/validate'
import menuItem from "./menuItem";
import { mapGetters, mapState } from "vuex";
export default {
  name: 'menuBar',
  props: {
    menuData: {
      type: Array,
      default () {
        return []
      }
    },
  },
  components: { menuItem },
  data () {
    return {
      that: this,
      activeIndex: '1',
      text_color: "#87899e",
      active_text_color: "#ffffff",
      isCollapse: false,// 以及菜单是否收缩成小面板
      menusList: []
    }
  },
  created () { },
  filters: {
    CheckIcon: function (value, self) {
      return self.commonFunction.UnicodeToChineseStr(value)
    },
    // CheckMenuText: function (value, self) {
    //   if (self.isCollapse !== undefined && self.isCollapse !== null) {
    //     return self.isCollapse ? '' : value
    //   } else {
    //     return value
    //   }
    // }
  },
  methods: {
    toCollapseChange () {
      this.isCollapse = !this.isCollapse
      // this.$store.dispatch('app/closeSideBar', { withoutAnimation: false })
    },
    // 点击选中当前顶部tab项
    menuSelect (key, keyPath) {
      // console.log(key, keyPath);
      if (this.activeIndex !== key) {
        // console.log("选择菜单：" + key);
        this.activeIndex = key;
        if (isExternal(key)) {
          // window.open(key)
          var link = document.createElement('a');
          link.href = key;
          link.target = '_blank'
          // link.rel = 'noopener'
          link.click();
        } else {
          this.$router.push({ path: key })
        }

      }

    }
  },
  watch: {
    activeIndex: {
      handler: function (newValue) {
      },
      immediate: true//路由发生变化时界面还未重绘完成，刷新布局没有意义，当前已经在重绘时处理
    },
    menuData: {
      handler: function (newValue) {
        this.menusList = newValue
        // console.log('hahah:', this.menusList)
      },
      immediate: true//路由发生变化时界面还未重绘完成，刷新布局没有意义，当前已经在重绘时处理
    }
  },
  computed: {
    ...mapGetters(["sidebar"]),
    // isCollapse () {
    //   return !this.sidebar.opened;
    // },
    activeMenu () {
      const route = this.$route;
      const { meta, path } = route;
      // if set path, the sidebar will highlight the path you set
      if (meta.activeMenu) {
        return meta.activeMenu;
      }
      return path;
    },
    // isCollapse () {
    //   console.log('haxihaxihaix:', !this.sidebar.opened)
    //   return !this.sidebar.opened;
    // }
  },
  mounted () {
    const the_page = this;
    this.$Notice.$on('toCollapseChange', () => this.toCollapseChange())
  }
}
</script>
<style lang="scss" scoped>
@import "@/assets/styles/menu.scss";
.Main {
  width: 100%;
  height: 100%;
  background: $menuBackgroundColor;
  overflow: hidden;
  // color: #fff;
  // overflow-x: hidden;
  // overflow-y: scroll;
}
.el-scrollbar {
  height: 100%;
  .el-scrollbar__wrap {
    overflow-x: hidden !important;
    margin-right: -222px;
  }
}
.el-scrollbar__wrap {
  overflow-x: hidden !important;
}
</style>
<style lang="scss">
@import "@/assets/styles/menu.scss";
// .menuList {
//   background-color: $menuBackgroundColor;
//   .el-menu-item.is-active {
//     background-color: $menuBackgroundActiveColor !important;
//   }
//   .el-menu-item:hover {
//     background-color: $menuBackgroundActiveColor !important;
//   }
//   .el-submenu__title:hover {
//     background: $menuBackgroundActiveColor !important;
//     background-color: $menuBackgroundActiveColor !important;
//     // color: red !important;
//   }
//   .el-submenu__title.is-active {
//     background: $menuBackgroundActiveColor !important;
//     background-color: $menuBackgroundActiveColor !important;
//     // color: red !important;
//   }
//   .el-submenu__title:hover,
//   .el-submenu__title:focus {
//     background: $menuBackgroundActiveColor !important;
//     background-color: $menuBackgroundActiveColor !important;
//   }
//   // menuList el-submenu is-active is-opened
//   .el-submenu.is-active.is-opened {
//     background: $menuBackgroundActiveColor !important;
//     background-color: $menuBackgroundActiveColor !important;
//     // color: red !important;
//   }
// }
// .menuList .el-submenu.is-active.is-opened:hover {
//   background: $menuBackgroundActiveColor !important;
//   background-color: $menuBackgroundActiveColor !important;
// }
// .menuList .el-submenu.is-active.is-opened:focus {
//   background: $menuBackgroundActiveColor !important;
//   background-color: $menuBackgroundActiveColor !important;
// }
// .el-menu--popup {
//   padding: 0px;
//   margin: 0px;
//   &:active {
//     background-color: $menuBackgroundActiveColor !important;
//     background: $menuBackgroundActiveColor !important;
//   }
// }
// .el-menu--vertical {
//   .nest-menu .el-submenu > .el-submenu__title,
//   .el-menu-item {
//     &:hover {
//       // you can use $menuBackgroundActiveColor
//       background-color: $menuBackgroundActiveColor !important;
//       background: $menuBackgroundActiveColor !important;
//     }
//   }
// }
// .el-menu-item.is-active {
//   background: $menuBackgroundActiveColor;
// }
</style>