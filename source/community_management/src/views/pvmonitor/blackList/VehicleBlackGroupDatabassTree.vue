<template  >
  <div class="templateClass">
    <el-tree :data="data"
             node-key="ID"
             ref="tree"
             default-expand-all
             highlight-current:true
             :props="defaultProps"
             @node-click="handleNodeClick"
             :expand-on-click-node="false"
             class="tree">
      <span class="custom-tree-node"
            slot-scope="{ node, data }">
        <!-- 设备 or 组织机构 -->
        <span v-if="data.ID==='1'">
          <span class="icon iconfont">
            &#xe654;
          </span>
          <!-- 名称 -->
          <span class="icon iconfont">
            {{ node.label }}
          </span>

          <span class="icon iconfont">
            {{ '(' +data.Count+ ')' }}
          </span>
        </span>
        <span v-if="data.ID!=='1'">
          <!-- 设备 or 组织机构 -->
          <span class="icon iconfont">
            &#xe62c;
          </span>
          <!-- 名称 -->
          <span class="icon iconfont">
            {{ node.label }}
          </span>
          <!-- 操作按钮 -->
          <a style="font-size: 20px; }"
             title="修改"
             @click="updatebutton(data)"
             class="icon iconfont"> &#xe652;</a>
        </span>
      </span>
    </el-tree>
  </div>

</template>

<script >
import { GetVehicleRepositoriesList } from '@/api/pvMonitor/IntelligentAnalysis'
export default {
  name: 'ComplexTable',
  props: {

  },
  data () {
    return {
      data: [{
        children: [],
        name: '车辆黑名单分组',
        Count: 0,
        ID: '1'
      }],
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  },

  methods: {
    getCheckedFaceStaticDB () {
      let dataS = this.$refs.tree.getCheckedNodes()
      return dataS
    },
    getOrgList () {
      // 获取车辆黑名单列表
      let queryData = {
        page: {
          no: 1,
          pageSize: 900,
          sort: 'asc',
          orderName: 'time'
        }
      }
      let self = this
      self.data = [{
        children: [],
        name: '车辆黑名单分组',
        Count: 0,
        ID: '1'
      }]
      GetVehicleRepositoriesList(queryData).then((faceStaticRepositoriesdata) => { // 获取根节点组织机构数据
        for (var i = 0; i < faceStaticRepositoriesdata.data.length; i++) {
          let dataType = faceStaticRepositoriesdata.data[i].type
          self.data[0].children.push(faceStaticRepositoriesdata.data[i])
        }
        self.data[0].Count = this.data[0].children.length
      })
    },
    getOrgListByName (data) {
      let queryData = {
        page: {
          no: 1,
          pageSize: 900,
          sort: 'asc',
          orderName: 'time'
        },
        name: data
      }
      let self = this
      self.data = [{
        children: [],
        name: '车辆黑名单分组',
        Count: 0,
        ID: '1'
      }]
      GetVehicleRepositoriesList(queryData).then((faceStaticRepositoriesdata) => { // 获取根节点组织机构数据
        for (var i = 0; i < faceStaticRepositoriesdata.data.length; i++) {
          let dataType = faceStaticRepositoriesdata.data[i].type
          self.data[0].children.push(faceStaticRepositoriesdata.data[i])
        }
        self.data[0].Count = this.data[0].children.length
      })
    },
    handleNodeClick (data) {
      this.GetBlackList(data)
    },
    GetBlackList (dataId) {
      this.$emit('GetBlackList', dataId)
    },
    updatebutton (data) {
      this.$emit('UpdataBlackList', data)
    }

  },

  mounted () {
    this.getOrgList()
  }
}
</script>
<style lang="scss" scoped>
.templateClass {
  height: 100%;
  min-height: 200px;
}
.tree {
  height: 99%;
  min-height: 200px;
  overflow: auto;
}
</style>
