<template>
  <div class="app-container">
    <el-form ref="form"
             :model="form"
             :rules="rules"
             size="mini"
             inline
             label-width="80px">
      <el-form-item label="社区"
                    prop="communityId"
                    label-width="auto"
                    class="personHouseFormTable">
        <el-select v-model="form.communityId"
                   class="personHouseFormTableItem"
                   filterable
                   clearable
                   disabled
                   placeholder="请选择社区">
          <el-option v-for="dict in communityOptions"
                     :key="dict.id"
                     :label="dict.name"
                     :value="dict.id" />
        </el-select>
      </el-form-item>

      <el-form-item label="小区"
                    prop="communityChildId"
                    label-width="auto">
        <el-select v-model="form.communityChildId"
                   class="personHouseFormTableItem"
                   filterable
                   clearable
                   disabled
                   placeholder="请选择小区">
          <el-option v-for="dict in allCommunityChildOptions"
                     :key="dict.id"
                     :label="dict.name"
                     :value="dict.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="楼栋"
                    prop="communityBuildingId"
                    label-width="auto">
        <el-select v-model="form.communityBuildingId"
                   class="personHouseFormTableItem"
                   filterable
                   clearable
                   disabled
                   placeholder="请选择楼栋">
          <el-option v-for="dict in allCommunityBuildingOptions"
                     :key="dict.id"
                     :label="dict.name"
                     :value="dict.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="房屋"
                    prop="communityHousesId"
                    label-width="auto">
        <el-select v-model="form.communityHousesId"
                   class="personHouseFormTableItem"
                   filterable
                   clearable
                   disabled
                   placeholder="请选择房屋">
          <el-option v-for="dict in allCommunityHousesOptions"
                     :key="dict.id"
                     :label="dict.unit + '单元' + dict.floor + '层' + dict.name"
                     :value="dict.id" />
        </el-select>
      </el-form-item>
      <!-- <el-row :gutter="10"
              class="mb8">
        <el-col :span="1.5"> -->
      <el-form-item>
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAdd"
                   v-hasPermi="['communityUnit:community_houses_personnel:add']">新增</el-button>
      </el-form-item>
      <!-- </el-col> -->
      <!-- </el-row> -->
      <el-form-item label="关联人员"
                    v-if="addFormVisible"
                    prop="personnelId">
        <el-select v-model="form.personnelId"
                   filterable
                   :disabled="
            form.id &&
            form.id.length > 0 &&
            form.checked &&
            form.checked === '1'
          "
                   remote
                   :remote-method="remotePersonInfoMethod"
                   :loading="loading_remotePersonInfo"
                   placeholder="请选择人员姓名">
          <el-option v-for="dict in personOptions"
                     :key="dict.id"
                     :label="dict.name + ' ' + dict.phoneNumber + ' ' + dict.brithDate"
                     :value="dict.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="关联时间"
                    v-if="addFormVisible"
                    prop="relationTime">
        <el-date-picker v-model="form.relationTime"
                        style="height: 24px"
                        type="daterange"
                        value-format="yyyy-MM-dd"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期"
                        @change="setDate()">
        </el-date-picker>
      </el-form-item>
      <!-- <el-form-item label="终止时间" v-if="addFormVisible" prop="endTime">
        <el-date-picker
          clearable
          size="small"
          
          v-model="form.endTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择终止时间"
        >
        </el-date-picker>
      </el-form-item> -->
      <el-form-item label="居住类型"
                    v-if="addFormVisible"
                    prop="typeOfResidence">
        <el-select v-model="form.typeOfResidence"
                   placeholder="请选择居住类型">
          <el-option v-for="dict in typeOfResidenceOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否是房主"
                    v-if="addFormVisible"
                    prop="homeowner">
        <el-select v-model="form.homeowner"
                   placeholder="请选择是否是房主">
          <el-option v-for="dict in homeownerOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="住户与房主关系"
                    v-if="addFormVisible"
                    prop="relationshipWithHomeowner">
        <el-select v-model="form.relationshipWithHomeowner"
                   placeholder="请选择住户与房主关系">
          <el-option v-for="dict in relationshipWithHomeownerOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="备注"
                    v-if="addFormVisible"
                    prop="remark">
        <el-input v-model="form.remark"
                  type="textarea"
                  autosize
                  style="min-width: 250px; min-height: 150px"
                  placeholder="请输入内容" />
      </el-form-item>
    </el-form>
    <el-table v-loading="loading"
              :data="community_houses_personnelList"
              @selection-change="handleSelectionChange">
      <el-table-column label="姓名"
                       align="center"
                       prop="name" />
      <el-table-column label="性别"
                       align="center"
                       prop="sex"
                       :formatter="sexFormat" />
      <el-table-column label="手机号"
                       align="center"
                       prop="phoneNumber" />
      <el-table-column label="住户与房主关系"
                       align="center"
                       prop="relationshipWithHomeowner"
                       :formatter="relationshipWithHomeownerFormat" />
      <el-table-column label="开始时间"
                       align="center"
                       prop="startTime" />
      <el-table-column label="终止时间"
                       align="center"
                       prop="endTime" />
      <el-table-column label="居住类型"
                       align="center"
                       prop="typeOfResidence"
                       :formatter="typeOfResidenceFormat" />
      <el-table-column label="是否是房主"
                       align="center"
                       prop="homeowner"
                       :formatter="homeownerFormat" />
      <el-table-column label="备注"
                       align="center"
                       prop="homePerRemark" />
      <el-table-column label="操作"
                       align="center"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['communityUnit:community_houses_personnel:remove']">删除</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-user"
                     @click="toOnePerson(scope.row)"
                     v-hasPermi="['communityUnit:community_houses_personnel:remove']">一人一档</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div slot="footer"
         class="dialog-footer">
      <el-button type="primary"
                 v-if="addFormVisible"
                 @click="submitForm">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
  </div>
</template>

<script>
import {
  listCommunity_houses_personnel,
  getCommunity_houses_personnel,
  delCommunity_houses_personnel,
  addCommunity_houses_personnel,
  updateCommunity_houses_personnel,
  exportCommunity_houses_personnel,
} from "@/api/communityUnit/community_houses_personnel";
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import { listCommunity_building } from "@/api/communityUnit/community_building";
import { listCommunity_houses } from "@/api/communityUnit/community_houses";
import {
  selectPerInformByHousesId,
  listPersonnel_information,
} from "@/api/communityUnit/personnel_information";
export default {
  // name: "Community_houses_personnel",
  props: {
    // 表单参数
    houseInfo: {
      type: Object,
    },
  },
  data () {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 社区房屋和人员关联表格数据
      community_houses_personnelList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 性别字典
      sexOptions: [],
      // 居住类型字典
      typeOfResidenceOptions: [],
      // 是否是房主,(0否1是,默认0)字典
      homeownerOptions: [],
      // 住户与房主关系字典
      relationshipWithHomeownerOptions: [],
      // 查询参数
      queryParams: {
        // pageNum: 1,
        // pageSize: 10,
        // communityHousesId: null,
        personnelId: null,
        delFlag: "0",
        // startTime: null,
        // endTime: null,
        // typeOfResidence: null,
        // homeowner: null,
        // communityId: null,
        // communityChildId: null,
        // communityBuildingId: null,
        // relationshipWithHomeowner: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        personnelId: [
          { required: true, message: "请输入社区", trigger: "blur" },
        ],
      },
      // 添加form项显示
      addFormVisible: false,
      // 社区列表
      communityOptions: [],
      // 小区列表
      communityChildOptions: [],
      // 楼栋列表
      communityBuildingOptions: [],
      // 房屋列表
      communityHousesOptions: [],
      // 小区列表全集
      allCommunityChildOptions: [],
      // 楼栋列表全集
      allCommunityBuildingOptions: [],
      // 房屋列表全集
      allCommunityHousesOptions: [],
      // 远程人人员搜索
      loading_remotePersonInfo: false,
      // 远程搜索人员列表
      personOptions: [],
    };
  },
  watch: {
    // 传入人员信息
    houseInfo: {
      handler: function (info) {
        if (info && info.id && info.id.length > 0) {
          //有效信息
          this.form.communityHousesId = info.id;
          this.form.communityId = info.communityId;
          this.form.communityChildId = info.communityChildId;
          this.form.communityBuildingId = info.communityBuildingId;
          console.log("communityHousesId:", info.id);
          console.log("communityId:", info.communityId);
          console.log("communityChildId:", info.communityChildId);
          console.log("communityBuildingId:", info.communityBuildingId);
          this.getList();
        }
      },
      deep: true,
      immediate: true, //路由发生变化时界面还未重绘完成，刷新布局没有意义，当前已经在重绘时处理
    },
  },
  created () {
    this.getCommunityDictory();
    this.getAllCommunityChildDictory();
    this.getAllCommunityBuildingDictory();
    this.getAllCommunityHousesDictory();
    this.getDicts("base_type_of_residence").then((response) => {
      this.typeOfResidenceOptions = response.data;
    });
    this.getDicts("base_yes_no").then((response) => {
      this.homeownerOptions = response.data;
    });
    this.getDicts("base_relationship_resident_homeowner").then((response) => {
      this.relationshipWithHomeownerOptions = response.data;
    });
    this.getDicts("sys_user_sex").then((response) => {
      this.sexOptions = response.data;
    });
  },
  mounted () {
    this.$Notice.$on("addFormItemVisible", () => (this.addFormVisible = false));
  },
  methods: {
    /** 根据社区房屋查询人员列表 */
    getList () {
      this.loading = true;
      let queryDate = {
        houseId: this.form.communityHousesId,
        delFlag: "0",
      };
      selectPerInformByHousesId(queryDate).then((response) => {
        this.community_houses_personnelList = response.rows;
        // this.total = response.total;
        this.loading = false;
      });
    },
    // 性别字典翻译
    sexFormat (row, column) {
      return this.selectDictLabel(this.sexOptions, row.sex);
    },
    // 居住类型字典翻译
    typeOfResidenceFormat (row, column) {
      return this.selectDictLabel(
        this.typeOfResidenceOptions,
        row.typeOfResidence
      );
    },
    // 是否是房主,(0否1是,默认0)字典翻译
    homeownerFormat (row, column) {
      return this.selectDictLabel(this.homeownerOptions, row.homeowner);
    },
    // 住户与房主关系字典翻译
    relationshipWithHomeownerFormat (row, column) {
      return this.selectDictLabel(
        this.relationshipWithHomeownerOptions,
        row.relationshipWithHomeowner
      );
    },
    // 取消按钮
    cancel () {
      this.addFormVisible = false;
      this.$Notice.$emit("personhouserelatedvisible");
    },
    //跳转到一人一档
    toOnePerson (row) {
      this.cancel();
      this.$router.push({ path: '/communityUnit/personnel_archive', query: { username: row.name, communityId: row.communityId, communityChildId: row.communityChildId } });
    },
    // 表单重置
    reset () {
      this.form = {
        id: null,
        communityHousesId: null,
        personnelId: null,
        startTime: null,
        endTime: null,
        typeOfResidence: null,
        homeowner: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        remark: null,
        communityId: null,
        communityChildId: null,
        communityBuildingId: null,
        relationshipWithHomeowner: null,
        relationTime: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery () {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery () {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange (selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd () {
      // this.reset();
      this.addFormVisible = true;
      // this.open = true;
      // this.title = "添加社区房屋和人员关联";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const id = row.id || this.ids;
      getCommunity_houses_personnel(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改社区房屋和人员关联";
      });
    },
    /* 对时间进行操作 */
    setDate () {
      let related_time = this.form.relationTime;
      this.form.startTime = related_time[0];
      this.form.endTime = related_time[1];
    },
    /** 提交按钮 */
    submitForm () {
      let self = this;
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (self.form.id != null) {
            updateCommunity_houses_personnel(this.form).then((response) => {
              if (response.code === 200) {
                self.msgSuccess("修改成功");
                self.addFormVisible = false;
                // self.$Notice.$emit('personhouserelatedvisible')
                self.getList();
              }
            });
          } else {
            addCommunity_houses_personnel(self.form).then((response) => {
              if (response.code === 200) {
                self.msgSuccess("新增成功");
                self.addFormVisible = false;
                // self.$Notice.$emit('personhouserelatedvisible')
                self.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      const ids = row.comHousesPerId || this.ids;
      this.$confirm(
        '是否确认删除社区房屋和人员关联编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delCommunity_houses_personnel(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有社区房屋和人员关联数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportCommunity_houses_personnel(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () { });
    },
    getCommunityDictory () {
      this.communityOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
      };
      listCommunity(param).then((response) => {
        this.communityOptions = response.rows;
      });
    },
    getAllCommunityChildDictory () {
      this.allCommunityChildOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: null,
      };
      listCommunity_child(param).then((response) => {
        this.allCommunityChildOptions = response.rows;
      });
    },
    getAllCommunityBuildingDictory () {
      this.allCommunityBuildingOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityChildId: null,
      };
      listCommunity_building(param).then((response) => {
        this.allCommunityBuildingOptions = response.rows;
      });
    },
    getAllCommunityHousesDictory () {
      this.allCommunityHousesOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityBuildingId: null,
      };
      listCommunity_houses(param).then((response) => {
        this.allCommunityHousesOptions = response.rows;
      });
    },
    communityNameFormat (row, column, cellValue) {
      if (this.communityOptions && this.communityOptions.length > 0) {
        let rows = this.communityOptions.filter((item) => {
          return item.id === cellValue;
        });
        if (rows && rows.length > 0) {
          return rows[0].name;
        }
      }
      return "";
    },
    communityChildNameFormat (row, column, cellValue) {
      if (
        this.allCommunityChildOptions &&
        this.allCommunityChildOptions.length > 0
      ) {
        let rows = this.allCommunityChildOptions.filter((item) => {
          return item.id === cellValue;
        });
        if (rows && rows.length > 0) {
          return rows[0].name;
        }
      }
      return "";
    },
    communityBuildingNameFormat (row, column, cellValue) {
      if (
        this.allCommunityBuildingOptions &&
        this.allCommunityBuildingOptions.length > 0
      ) {
        let rows = this.allCommunityBuildingOptions.filter((item) => {
          return item.id === cellValue;
        });
        if (rows && rows.length > 0) {
          return rows[0].name;
        }
      }
      return "";
    },
    communityHousesNameFormat (row, column, cellValue) {
      if (
        this.allCommunityHousesOptions &&
        this.allCommunityHousesOptions.length > 0
      ) {
        let rows = this.allCommunityHousesOptions.filter((item) => {
          return item.id === cellValue;
        });
        if (rows && rows.length > 0) {
          return rows[0].name;
        }
      }
      return "";
    },
    remotePersonInfoMethod (name) {
      this.loading_remotePersonInfo = true;
      let param = {
        name: name,
        communityId: this.form.communityId,
        communityChildId: this.form.communityChildId
      };
      listPersonnel_information(param).then((response) => {
        this.personOptions = response.rows;
        this.loading_remotePersonInfo = false;
      });
    },
  },
  computed: {
    isForeign: function () {
      // `this` 指向 vm 实例
      let domesticList = ["156", "344", "446", "158"];
      return !(
        this.queryParams.nationality &&
        this.queryParams.nationality !== "" &&
        domesticList.includes(this.queryParams.nationality)
      );
    },
    isForeign_form: function () {
      // `this` 指向 vm 实例
      let domesticList = ["156", "344", "446", "158"];
      return !(
        this.form.nationality &&
        this.form.nationality !== "" &&
        domesticList.includes(this.form.nationality)
      );
    },
  },
};
</script>

<style scoped>
.personHouseFormTableItem {
  /* width: 61%; */
}
.personHouseFormTable {
  /* width: 40%; */
}
</style>