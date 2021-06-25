<template>
  <div class="Main"
       ref="MainView">
    <el-menu default-active="1"
             @select="menuSelect"
             class="menuList"
             text-color="#87899d"
             active-text-color="#434a7e"
             :collapse="isCollapse">
      <li v-for="(item, index) in menuData"
          :key="index"
          style="text-align: left"
          class="listStyle">
        <!-- 有二级菜单 -->
        <el-submenu :index="index+''"
                    v-if="item.children!==undefined && item.children!==null && item.children.length>0">
          <template slot="title">
            <i class="iconStyle"
               :class="'icon iconfont  '+ item.meta.icon"></i>
            <span slot="title">{{item.meta.title | CheckMenuText(that)}}</span>
          </template>
          <el-menu-item :index="index+','+childIndex"
                        :route="item.children[childIndex].path"
                        v-for="(child,childIndex) in item.children"
                        :key="childIndex">
            <i class="iconStyle"
               :class="'icon iconfont  '+ child.meta.icon"></i>
            <span slot="title">{{child.meta.title}}</span>
          </el-menu-item>
        </el-submenu>
        <!-- 没有二级菜单 -->
        <el-menu-item v-else
                      :index="index+''">
          <i class="iconStyle"
             :class="'icon iconfont  '+ item.meta.icon"></i>
          <span slot="title">{{item.meta.title| CheckMenuText(that)}}</span>
        </el-menu-item>
      </li>
    </el-menu>
  </div>
</template>
<script>
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
  components: {},
  data () {
    return {
      that: this,
      centContainerHeight: '100px',
      isCollapse: false,// 以及菜单是否收缩成小面板
    }
  },
  created () { },
  filters: {
    CheckIcon: function (value, self) {
      return self.commonFunction.UnicodeToChineseStr(value)
    },
    CheckMenuText: function (value, self) {
      if (self.isCollapse !== undefined && self.isCollapse !== null) {
        return self.isCollapse ? '' : value
      } else {
        return value
      }
    }
  },
  methods: {
    refreshCenterContainerHeight () {
      this.centContainerHeight = (this.$refs.MainView.offsetHeight - 72 - 331 - 4) + 'px'
    },
    // 点击选中当前顶部tab项
    menuSelect (key, keyPath) {
      console.log(key, keyPath, '123');
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
      console.log("path:", selectPath);
      this.currentRouterViewDefaultWidth = width;
      this.showMenuView = true
      // this.dataPanelWidth = width;
      this.$router.push({ path: selectPath })

    }
  },
  mounted () {
    const the_page = this;
    // window.onresize = () => { return (() => { the_page.refreshCenterContainerHeight() })() }; 
    this.refreshCenterContainerHeight()
  }
}

</script>
<style lang="scss" scoped>
.Main {
  width: 100%;
  background: linear-gradient(to bottom, #3242bb, #1193bb);
  overflow-x: hidden;
}
.Main span {
  color: #fff;
}
.Main li:hover {
  background-color: #28c8bb;
}
.menuList:not(.el-menu--collapse) {
  // width: 160px;
  min-height: 400px;
  background-color: transparent;
}
.iconStyle {
  color: #6775ef;
  margin-right: 5px;
}
</style>
<style>
.el-menu {
  background-color: #197dbb;
}
.el-submenu__title:hover {
  background-color: #28c8bb;
}
.is-active {
  background-color: #28c8bb !important;
}
.el-submenu__title i {
  color: #fff;
}
</style>