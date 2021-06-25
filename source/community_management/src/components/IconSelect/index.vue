<!-- @author zhengjie -->
<template>
  <div class="icon-body">
    <el-row>
      <el-col :span="14">
        <el-input v-model="name"
                  style="position: relative;"
                  clearable
                  placeholder="请输入图标名称"
                  @clear="filterIcons"
                  @input.native="filterIcons">
          <i slot="suffix"
             class="el-icon-search el-input__icon" />
        </el-input>
      </el-col>
      <el-col :span="10">
        <el-select v-model="currentIconProject"
                   @change="iconProjectSelectChange"
                   placeholder="选择图标项目">
          <el-option v-for="item in iconProjectList"
                     :key="item.value"
                     :label="item.name"
                     :value="item.value">
          </el-option>
        </el-select>
      </el-col>
    </el-row>

    <div class="icon-list">
      <div v-for="(item, index) in iconList"
           :key="index"
           @click="selectedIcon(item)">
        <span :class="'iconfont '+item.font_class" />
        <span class="span-icon">{{ item | getIconClassName(that)}}</span>
      </div>
    </div>
  </div>
</template>

<script>
import reqireIconProjects from './reqireIconProjects'
export default {
  components: {},
  name: 'IconSelect',
  data () {
    return {
      that: this,
      name: '',
      //图标项目列表
      iconProjectList: reqireIconProjects,
      iconList: [],
      //当前图标项目，默认选中default，即若依自带图标
      currentIconProject: 0
    }
  },
  filters: {
    getIconClassName: function (value, self) {
      if (value.font_class !== undefined && value.font_class !== null && value.font_class.length > 0) {
        return value.name
      }
      else {
        return value
      }
    }
  },
  methods: {
    filterIcons () {
      let list = this.getIconList()
      if (this.name) {
        this.iconList = list.filter(item => item.name.includes(this.name))
      } else {
        this.iconList = list
      }
    },
    selectedIcon (name) {
      this.$emit('selected', name.font_class ? name.font_class : name)
      document.body.click()
    },
    reset () {
      this.name = ''
      this.iconList = this.getIconList()
    },
    iconProjectSelectChange () {
      this.iconList = this.getIconList()
    },
    getIconList () {
      return this.iconProjectList[this.currentIconProject].classNameList
    }
  },
  mounted () {

  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.icon-body {
  width: 100%;
  padding: 10px;
  .icon-list {
    height: 200px;
    overflow-y: scroll;
    div {
      height: 30px;
      line-height: 30px;
      margin-bottom: -5px;
      cursor: pointer;
      width: 33%;
      float: left;
    }
    span {
      display: inline-block;
      vertical-align: -0.15em;
      fill: currentColor;
      overflow: hidden;
    }
  }
}
.span-icon {
  max-width: 82px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis; /*超出部分用...代替*/
}
</style>
