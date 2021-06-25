<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch">
      <el-form-item label="菜单名称"
                    prop="name">
        <el-input v-model="queryParams.name"
                  placeholder="请输入菜单名称"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="状态"
                    prop="status">
        <el-select v-model="queryParams.status"
                   placeholder="菜单状态"
                   clearable
                   size="small">
          <el-option v-for="dict in statusOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="cyan"
                   icon="el-icon-search"
                   size="mini"
                   @click="handleQuery">搜索</el-button>
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAdd"
                   v-hasPermi="['system:menu:add']">新增</el-button>
        <!-- <right-toolbar :showSearch.sync="showSearch"
                       @queryTable="getList"></right-toolbar> -->
        <!-- <el-button icon="el-icon-refresh"
                   size="mini"
                   @click="resetQuery">重置</el-button> -->
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10"
            class="mb8">
      <el-col :span="1.5">
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAdd"
                   v-hasPermi="['system:menu:add']">新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading"
              stripe
              :height="dataHeight"
              :data="menuList"
              row-key="id"
              :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
      <el-table-column prop="name"
                       label="菜单名称"
                       :show-overflow-tooltip="true"
                       width="160"></el-table-column>
      <el-table-column prop="icon"
                       label="图标"
                       align="center"
                       width="100">
        <template slot-scope="scope">
          <!-- <svg-icon :icon-class="scope.row.icon"
                    v-if="scope.row.icon.indexOf('icon')===-1" /> -->
          <span :class="'iconfont '+scope.row.icon"
                v-if="scope.row.icon.indexOf('icon')!=-1" />
        </template>
      </el-table-column>
      <el-table-column prop="orderNum"
                       label="排序"
                       width="60"></el-table-column>
      <el-table-column prop="perms"
                       label="权限标识"
                       :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="component"
                       label="组件路径"
                       :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="status"
                       label="状态"
                       :formatter="statusFormat"
                       width="80"></el-table-column>
      <el-table-column label="创建时间"
                       align="center"
                       prop="createTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作"
                       align="center"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['system:menu:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-plus"
                     @click="handleAdd(scope.row)"
                     v-hasPermi="['system:menu:add']">新增</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['system:menu:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改菜单对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="600px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="90px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="上级菜单">
              <treeselect v-model="form.parentId"
                          :options="menuOptions"
                          :normalizer="normalizer"
                          :show-count="true"
                          placeholder="选择上级菜单" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="菜单类型"
                          prop="type">
              <el-radio-group v-model="form.type">
                <el-radio label="M">目录</el-radio>
                <el-radio label="C">菜单</el-radio>
                <el-radio label="F">按钮</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item v-if="form.type != 'F'"
                          label="菜单图标">
              <el-popover placement="bottom-start"
                          width="460"
                          trigger="click"
                          @show="$refs['iconSelect'].reset()">
                <IconSelect ref="iconSelect"
                            @selected="selected" />
                <el-input slot="reference"
                          v-model="form.icon"
                          placeholder="点击选择图标"
                          readonly>
                  <!-- <svg-icon v-if="form.icon && form.icon.indexOf('icon')===-1"
                            slot="prefix"
                            :icon-class="form.icon"
                            class="el-input__icon"
                            style="height: 32px;width: 16px;" /> -->
                  <span slot="prefix"
                        :class="'iconfont '+form.icon"
                        v-if="form.icon && form.icon.indexOf('icon')!=-1" />
                  <i v-else
                     slot="prefix"
                     class="el-icon-search el-input__icon" />
                </el-input>
              </el-popover>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="菜单名称"
                          prop="name">
              <el-input v-model="form.name"
                        placeholder="请输入菜单名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="显示排序"
                          prop="orderNum">
              <el-input-number v-model="form.orderNum"
                               controls-position="right"
                               :min="0" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item v-if="form.type === 'C'"
                          label="是否外链">
              <el-radio-group v-model="form.isFrame">
                <el-radio label="0">是</el-radio>
                <el-radio label="1">否</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="权限标识">
              <el-input v-model="form.perms"
                        placeholder="请权限标识"
                        maxlength="50" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item v-if="form.type === 'C'"
                          label="路由地址"
                          prop="path">
              <el-input v-model="form.path"
                        placeholder="请输入路由地址" />
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item v-if="form.type === 'C' && form.isFrame==='1'"
                          label="多组件路由">
              <el-radio-group v-model="multi_component">
                <el-radio label="0">否</el-radio>
                <el-radio label="1">是</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="3">
            <!-- <el-button v-if="form.type === 'C' && form.isFrame==='1' && multi_component === '1'"
                       @click="componentAdd">+</el-button> -->
          </el-col>

        </el-row>
        <el-row>
          <!-- <li> -->
          <div v-for="(item, index) in routerComponents"
               :key="index"
               style="text-align: left">

            <el-col :span="11"
                    v-if="form.type == 'C' && form.isFrame !== '0'">
              <el-form-item :label="item.routerViewName"
                            prop="componentPath">
                <el-input v-model="item.componentPath"
                          placeholder="请输入组件路径" />
              </el-form-item>
            </el-col>
            <el-col :span="11"
                    v-if="form.type == 'C' && form.isFrame !== '0'">
              <el-form-item label="宽高"
                            prop="vh">
                <el-input v-model="item.vh"
                          placeholder="请输入路由组件宽高" />
              </el-form-item>
            </el-col>
            <el-col :span="2">
              <!-- <el-button v-if="form.type === 'C' && form.isFrame==='1' && multi_component === '1'"
                         :disabled="!(routerComponents.length > 1)"
                         @click="componentDelete(index)">-</el-button> -->
            </el-col>
          </div>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item v-if="form.type != 'F'"
                          label="显示状态">
              <el-radio-group v-model="form.visible">
                <el-radio v-for="dict in visibleOptions"
                          :key="dict.dictValue"
                          :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item v-if="form.type != 'F'"
                          label="菜单状态">
              <el-radio-group v-model="form.status">
                <el-radio v-for="dict in statusOptions"
                          :key="dict.dictValue"
                          :label="dict.dictValue">{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button type="primary"
                   @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMenu, getMenu, delMenu, addMenu, updateMenu } from "@/api/system/menu";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import IconSelect from "@/components/IconSelect";

export default {
  name: "Menu",
  components: { Treeselect, IconSelect },
  data () {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 菜单表格树数据
      menuList: [],
      // 菜单树选项
      menuOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 显示状态数据字典
      visibleOptions: [],
      // 菜单状态数据字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        name: undefined,
        visible: undefined
      },
      // 表单参数
      form: {},
      multi_component: '0',
      routerComponents: [],//组件列表
      // 表单校验
      rules: {
        name: [
          { required: true, message: "菜单名称不能为空", trigger: "blur" }
        ],
        orderNum: [
          { required: true, message: "菜单顺序不能为空", trigger: "blur" }
        ],
        path: [
          { required: true, message: "路由地址不能为空", trigger: "blur" }
        ]
      },
      // 搜索表单控件高度
      formHeight: 0
    };
  },
  created () {
    this.getList();
    this.getDicts("sys_show_hide").then(response => {
      this.visibleOptions = response.data;
    });
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
  },
  mounted () {
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight
    })
  },
  computed: {
    dataHeight () {
      let searchFormHeight = 0
      if (this.showSearch) searchFormHeight = this.formHeight
      return this.$store.getters.dataEleHeight - searchFormHeight - 115
    }
  },
  methods: {
    // 选择图标
    selected (name) {
      this.form.icon = name;
    },
    /** 查询菜单列表 */
    getList () {
      this.loading = true;
      let self = this
      listMenu(this.queryParams).then(response => {
        self.menuList = self.handleTree(response.data, "id");
        self.loading = false;
      });
    },
    /** 转换菜单数据结构 */
    normalizer (node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.id,
        label: node.name,
        children: node.children
      };
    },
    /** 查询菜单下拉树结构 */
    getTreeselect () {
      listMenu().then(response => {
        this.menuOptions = [];
        const menu = { id: 0, name: '主类目', children: [] };
        menu.children = this.handleTree(response.data, "id");
        this.menuOptions.push(menu);
      });
    },
    // 显示状态字典翻译
    visibleFormat (row, column) {
      if (row.type == "F") {
        return "";
      }
      return this.selectDictLabel(this.visibleOptions, row.visible);
    },
    // 菜单状态字典翻译
    statusFormat (row, column) {
      if (row.type == "F") {
        return "";
      }
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 取消按钮
    cancel () {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset () {
      this.form = {
        id: undefined,
        parentId: 0,
        name: undefined,
        icon: undefined,
        type: "M",
        orderNum: undefined,
        isFrame: "1",
        visible: "0",
        status: "0",
        multiComponent: "0"
      };
      this.multi_component = '0'
      this.componentReset('-1')

      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery () {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery () {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd (row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.id) {
        this.form.parentId = row.id;
      } else {
        this.form.parentId = 0;
      }
      this.open = true;
      this.title = "添加菜单";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      this.getTreeselect();
      let self = this
      this.componentReset('-1')
      getMenu(row.id).then(response => {
        self.form = response.data;
        self.multi_component = self.form.multiComponent
        self.open = true;
        self.title = "修改菜单";
        self.componentReset(self.multi_component)
        self.loadRouterComponents()
      });
    },
    setRouterComponentsValue (routerView, path, vh) {
      for (let i = 0; i < this.routerComponents.length; i++) {
        if (this.routerComponents[i].routerView === routerView) {
          this.routerComponents[i].componentPath = path
          this.routerComponents[i].vh = vh
        }
      }
    },
    loadRouterComponents () {
      let componentList = this.$cf.getListFromString(this.form.component)
      let self = this
      //多路由组件
      componentList.forEach(element => {
        let dict = self.$cf.getListFromString(element, ':')
        if (dict && dict.length === 1) {
          let routerView = 'default'
          let componentPath = dict[0]
          this.setRouterComponentsValue(routerView, componentPath, -1)
        } else if (dict && dict.length === 2) {
          let routerView = dict[0]
          let componentPath = dict[1]
          this.setRouterComponentsValue(routerView, componentPath, -1)
        } else if (dict && dict.length === 3) {
          let routerView = dict[0]
          let componentPath = dict[1]
          let vh = dict[2]
          this.setRouterComponentsValue(routerView, componentPath, vh)
        }
      });
    },
    getRouterComponents () {
      if (this.form.type !== 'C') {//菜单
        this.form.multiComponent = '0'
        return ''
      }
      this.form.multiComponent = this.multi_component
      let routerViewCount = 1
      if (this.multi_component === '1') {
        routerViewCount = this.routerComponents.length
      }
      let components = []
      let haveDefault = false
      for (let i = 0; i < routerViewCount; i++) {
        if (this.routerComponents[i].componentPath && this.routerComponents[i].componentPath.length > 0) {
          components.push(this.routerComponents[i].routerView + ':' + this.routerComponents[i].componentPath + ':' + this.routerComponents[i].vh)
        }
      }
      return this.$cf.getStringFromList(components)
    },
    /** 提交按钮 */
    submitForm: function () {
      let self = this
      this.$refs["form"].validate(valid => {
        if (valid) {
          self.form.component = this.getRouterComponents()
          if (self.form.id !== undefined) {
            updateMenu(self.form).then(response => {
              if (response.code === 200) {
                self.msgSuccess("修改成功");
                self.open = false;
                self.getList();
              }
            });
          } else {
            addMenu(self.form).then(response => {
              if (response.code === 200) {
                self.msgSuccess("新增成功");
                self.open = false;
                self.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      this.$confirm('是否确认删除名称为"' + row.name + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delMenu(row.id);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 路由组件设定为单路由组件 */
    componentReset (flag = '0') {
      if (flag === '-1') {//代表初始化，默认为单路由
        this.routerComponents = [{
          routerViewName: '默认面板',
          routerView: 'default',
          componentPath: '',
          vh: -1
        }]
      }
      else if (flag === '0') {//多路由切换为单路由
        if (this.routerComponents.length > 1) {
          this.routerComponents.splice(1, this.routerComponents.length - 1)
        }
      } else if (flag === '1') {//单路由切换为多路由
        if (this.routerComponents.length === 0) {
          this.routerComponents = [{
            routerViewName: '默认面板',
            routerView: 'default',
            componentPath: '',
            vh: -1
          }]
          this.routerComponents.push(
            {
              routerViewName: '上侧面板',
              routerView: 'top_panel_router_view',
              componentPath: '',
              vh: 300
            },
            {
              routerViewName: '左侧面板',
              routerView: 'left_panel_router_view',
              componentPath: '',
              vh: 300
            },
            {
              routerViewName: '右侧面板',
              routerView: 'right_panel_router_view',
              componentPath: '',
              vh: 300
            },
          )
        } else if (this.routerComponents.length === 1) {
          this.routerComponents.push(
            {
              routerViewName: '上侧面板',
              routerView: 'top_panel_router_view',
              componentPath: '',
              vh: 300
            },
            {
              routerViewName: '左侧面板',
              routerView: 'left_panel_router_view',
              componentPath: '',
              vh: 300
            },
            {
              routerViewName: '右侧面板',
              routerView: 'right_panel_router_view',
              componentPath: '',
              vh: 300
            },
          )
        } else {

        }

      }

    }
    /** 添加一个路由组件 */
    // componentAdd () {
    //   this.routerComponents.push({
    //     routerViewName: 'routerView' + this.routerComponents.length,
    //     componentPath: ''
    //   })
    // },
    /** 删除一个路由组件 */
    // componentDelete (index) {
    //   this.routerComponents.splice(index, 1)
    // }
  },
  watch: {
    multi_component: {
      handler: function (newValue) {
        //0代表否  不是多路由
        this.componentReset(newValue)
      },
      immediate: true//路由发生变化时界面还未重绘完成，刷新布局没有意义，当前已经在重绘时处理
    }
  }
};
</script>