<template>
  <fragment>
    <template v-for="item in menus"
              v-if="!item.hidden">
      <!-- 有二级菜单 -->
      <el-submenu ref="subMenu"
                  class="menuList"
                  popper-append-to-body
                  :index="getRouterPath(item)"
                  :key="getRouterPath(item)"
                  :text-color="text_color"
                  :active-text-color="active_text_color"
                  v-if="item.children!==undefined && item.children!==null ">
        <template slot="title"
                  v-if="item.meta">
          <i :class="'svg-external-icon icon iconfont  ' + item.meta.icon"></i>
          <span class="marginLeft"
                slot="title">{{item.meta.title}}</span>
        </template>
        <menuItem class="menuList"
                  :menus="item.children">
        </menuItem>
      </el-submenu>
      <!-- 没有二级菜单 -->
      <el-menu-item class="menuList"
                    v-else
                    :index="getRouterPath(item)"
                    :key="getRouterPath(item)">
        <i :class="'svg-external-icon icon iconfont  ' + item.meta.icon"></i>
        <span class="marginLeft"
              slot="title">{{item.meta.title}}</span>
      </el-menu-item>
    </template>
  </fragment>
</template>
<script>
import { mapGetters, mapState } from "vuex";
import FixiOSBug from './FixiOSBug'
export default {
  name: 'menuItem',
  props: {
    // menu_item: {
    //   type: Object,
    //   default () {
    //     return {
    //       "name": "菜单1",
    //       "path": "/menu/menu1",
    //       "hidden": false,
    //       "meta": {
    //         "title": "菜单1",
    //         "icon": "iconquxiaofabu",
    //       },
    //     }
    //   },
    //   required: true
    // },
    menus: {
      type: Array,
      default: function () {
        return [];
      },
      required: true
    }
  },
  // components: { item },
  mixins: [FixiOSBug],
  data () {
    return {
      that: this,
      // item: {
      //   "name": "菜单1",
      //   "path": "/menu/menu1",
      //   "hidden": true,
      //   "meta": {
      //     "title": "菜单1",
      //     "icon": "iconquxiaofabu",
      //   },
      // },
      activeIndex: '1',
      text_color: "#87899e",
      active_text_color: "#ffffff",
      isCollapse: false,// 以及菜单是否收缩成小面板
    }
  },
  created () { },
  filters: {
    CheckIcon: function (value, self) {
      return self.commonFunction.UnicodeToChineseStr(value)
    },
    CheckMenuText: function (meta, self) {
      if (meta && meta.title) {
        return meta.title
        if (self.isCollapse !== undefined && self.isCollapse !== null) {
          return self.isCollapse ? '' : meta.title
        } else {
          return meta.title
        }
      } else {
        return '未知'
      }

    }
  },
  methods: {
    // 点击选中当前顶部tab项
    menuSelect (key, keyPath) {
      // console.log(key, keyPath);
      this.activeIndex = key;
      let menuIndexPaths = key.split(',')
      let selectPath = ''
      let width = '400px'
      this.currentMenuIndex = key
      this.selectMenuName = this.menuData[menuIndexPaths[0]].name
      this.dataPanelBorderWidth = '1px solid #11bbf3'
      if (menuIndexPaths.length > 1) {
        selectPath = this.menuData[menuIndexPaths[0]].children[menuIndexPaths[1]].path
        width = this.menuData[menuIndexPaths[0]].children[menuIndexPaths[1]].meta.viewWidth
        this.selectMenuName = this.menuData[menuIndexPaths[0]].children[menuIndexPaths[1]].name
      }
      else {
        selectPath = this.menuData[menuIndexPaths[0]].path
        width = this.menuData[menuIndexPaths[0]].meta.viewWidth
        this.selectMenuName = this.menuData[menuIndexPaths[0]].name
      }
      // console.log("path:", selectPath);
      this.currentRouterViewDefaultWidth = width;
      this.showMenuView = true
      // this.dataPanelWidth = width;
      this.$router.push({ path: selectPath })

    },
    getRouterPath (item) {
      if (item.path && item.path.length > 0) {
        return item.path
      }
      return this.$cf.Guid()
    }
  },
  watch: {
    activeIndex: {
      handler: function (newValue) {
        // console.log('new activeIndex:' + newValue)
      },
      immediate: true//路由发生变化时界面还未重绘完成，刷新布局没有意义，当前已经在重绘时处理
    },
    // menu_item: {
    //   handler: function (newValue) {
    //     // console.log('new activeIndex:' + newValue)
    //     this.item = newValue
    //   },
    //   immediate: true//路由发生变化时界面还未重绘完成，刷新布局没有意义，当前已经在重绘时处理
    // }
  },
  mounted () {
  },
  computed: {
    // ...mapGetters(["sidebar"]),
    // // isCollapse () {
    // //   return !this.sidebar.opened;
    // // }
  }
}
</script>

<style lang="scss" scoped>
.el-menu--popup-right-start {
  margin-left: 5px;
  margin-right: 5px;
  background: red;
  color: red;
}
.marginLeft {
  margin-left: 8px;
}
</style>