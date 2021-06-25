<template  >
  <div class="templateClass">
    <el-tree :data="data"
             node-key="ID"
             ref="tree"
             default-expand-all
             highlight-current:true
             :props="defaultProps"
             :expand-on-click-node="false"
             @node-click="handleNodeClick"
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
          <!-- 操作按钮 -->
          <a style="font-size: 20px; }"
             title="修改"
             @click="updatebutton(data)"
             class="icon iconfont"> &#xe652;</a>
          <span v-if="data.status === 'true'">
            <a style="font-size: 20px; }"
               title="布控中"
               class="icon iconfont"> &#xe6c4;</a>
          </span>
        </span>
      </span>
    </el-tree>
  </div>

</template>
<script>
import { GetFaceRepositoriesList } from '@/api/pvMonitor/IntelligentAnalysis'
export default {
  name: 'ComplexTable',
  props: {

  },
  data () {
    return {
      Control: false,
      data: [{
        children: [],
        name: '人脸库黑名单',
        Count: 0,
        ID: '1'
      }],
      defaultProps: {
        children: 'children',
        label: 'name',
        Controlstatus: 'status'
      }
    }
  },

  methods: {
    getCheckedFaceDB () {
      let dataS = this.$refs.tree.getCurrentNode()
      return dataS
    },
    getOrgList () {
      let queryData = {
        no: 1, size: 999, sort: 'asc', ordername: 'name'
      }
      let self = this
      self.data = [{
        children: [],
        name: '人脸库黑名单',
        Count: 0,
        ID: '1'
      }]
      GetFaceRepositoriesList(queryData).then((faceRepositoriesdata) => {
        if (faceRepositoriesdata.data) {
          for (var i = 0; i < faceRepositoriesdata.data.length; i++) {
            let dataType = faceRepositoriesdata.data[i].type

            switch (dataType) {
              case '2':
                self.data[0].children.push(faceRepositoriesdata.data[i])
                break
            }
          }
          self.data[0].Count = this.data[0].children.length
        }
      })
    },
    getOrgListByName (data) {
      let queryData = {
        no: 1, size: 999, sort: 'asc', ordername: 'name', name: data
      }
      let self = this
      self.data = [{
        children: [],
        name: '人脸库黑名单',
        Count: 0,
        ID: '1'
      }]
      GetFaceRepositoriesList(queryData).then((faceRepositoriesdata) => {
        if (faceRepositoriesdata.data) {
          for (var i = 0; i < faceRepositoriesdata.data.length; i++) {
            let dataType = faceRepositoriesdata.data[i].type

            switch (dataType) {
              case '2':
                self.data[0].children.push(faceRepositoriesdata.data[i])
                break
            }
            self.data[0].Count = this.data[0].children.length
          }
        }
      })
    },
    handleNodeClick (data) {
      this.GetBlackFacesLibary(data)
    },
    GetBlackFacesLibary (dataId) {
      this.$emit('GetBlackFacesLibary', dataId)
    },
    updatebutton (data) {
      this.$emit('UpdataFacesLibary', data)
    }

  },

  mounted () {
    this.getOrgList()
    console.log('heiheiheiehei')
  }
}
</script>
<style lang="scss" scoped>
.templateClass {
  min-height: 200px;
}
.tree {
  min-height: 200px;
  overflow: auto;
  overflow-x: auto;
}
/* .templateClass {
  height: 100%;
  min-height: 100px;
}
.tree {
  height: 100%;
  min-height: 100px;
  overflow: auto;
  overflow-x: auto;
}
.templateClass {
  height: 700px !important;
  overflow-y: scroll;
}
.templateClass::-webkit-scrollbar {
  width: 13px;
  height: 16px;
  background-color: #f5f5f5;
} */
/*定义滚动条轨道 内阴影+圆角*/
/* .templateClass::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  background-color: #1b384a;
} */
/*定义滑块 内阴影+圆角*/
/* .templateClass::-webkit-scrollbar-thumb {
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  background-color: #555;
} */
</style>
