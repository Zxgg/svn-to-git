<template  >
  <div class="templateClass">
    <el-tree :data="data"
             node-key="ID"
             ref="tree"
             show-checkbox
             default-expand-all
             highlight-current:true
             :props="defaultProps"
             @node-click="handleNodeClick"
             :expand-on-click-node="false"
             class="tree">
      <span class="custom-tree-node"
            slot-scope="{ node, data }">
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
          <span class="icon iconfont">
            &#xe62b;
          </span>
          <!-- 名称 -->
          <span class="icon iconfont">
            {{ node.label }}
          </span>

        </span>
      </span>
    </el-tree>
  </div>

</template>

<script >
import { GetFaceRepositoriesList } from '@/api/pvMonitor/IntelligentAnalysis'
export default {
  name: 'ComplexTable',
  props: {

  },
  data () {
    return {
      data: [{
        children: [],
        name: '人脸库白名单',
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
    getCheckedFaceDB () {
      let dataS = this.$refs.tree.getCheckedNodes()
      return dataS
    },
    getOrgList () {
      let queryData = {
        no: 1, size: 999, sort: 'asc', ordername: 'name'
      }
      let self = this
      GetFaceRepositoriesList(queryData).then((faceRepositoriesdata) => {
        if (faceRepositoriesdata.data) {
          for (var i = 0; i < faceRepositoriesdata.data.length; i++) {
            let dataType = faceRepositoriesdata.data[i].type

            switch (dataType) {
              case '3':
                self.data[0].children.push(faceRepositoriesdata.data[i])
                break
            }
          }

          self.data[0].Count = this.data[0].children.length
        }
      })
    },
    handleNodeClick (data) {
      /* let dataId = data.id */
      this.GetWhiteFacesLibary(data)
    },
    GetWhiteFacesLibary (dataId) {
      this.$emit('GetWhiteFacesLibary', dataId)
    }

  },

  mounted () {
    this.getOrgList()
  }
}
</script>
<style lang="scss" scoped>
.templateClass {
  height: 80%;
  min-height: 200px;
}
.tree {
  height: 80%;
  min-height: 200px;
  overflow: auto;
  overflow-x: auto;
}
</style>
