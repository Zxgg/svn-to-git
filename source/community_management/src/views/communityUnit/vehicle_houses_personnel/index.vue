<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch"
             label-width="68px">
      <el-form-item label="姓名"
                    prop="personnelId">
        <el-input v-model="queryParams.personnelName"
                  placeholder="请输入姓名"
                  clearable
                  disabled
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>

      <el-form-item>
        <el-button type="cyan"
                   icon="el-icon-search"
                   size="mini"
                   @click="handleQuery">搜索</el-button>
        <!-- <el-button icon="el-icon-refresh"
                   size="mini"
                   @click="resetQuery">重置</el-button> -->
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAdd"
                   v-hasPermi="['communityUnit:vehicle_houses_personnel:add']">新增</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="10"
            class="commonBorder VerticalPadding HorizontalPadding"
            v-if="open">
      <el-form ref="form"
               :inline="true"
               :model="form"
               :rules="rules"
               label-width="80px">
        <!-- <el-form-item label="人员ID"
                      prop="personnelId">
          <el-input v-model="form.personnelId"
                    placeholder="请输入人员ID" />
        </el-form-item> -->
        <el-form-item label="人员房屋"
                      prop="communityHousesId">
          <!-- <el-input v-model="form.communityHousesId"
                    disabled
                    placeholder="请输入人员姓名" /> -->
          <el-select v-model="form.communityHousesId"
                     filterable
                     placeholder="请选择人员房屋">
            <el-option v-for="dict in personOptions"
                       :key="dict.houseId"
                       :label="dict.serialNumber+'号楼' + dict.unit+'单元' + dict.houseNumber"
                       :value="dict.houseId" />
          </el-select>
        </el-form-item>

        <!-- <el-form-item label="车辆ID"
                      prop="vehicleId">
          <el-input v-model="form.vehicleId"
                    placeholder="请输入车辆ID" />
        </el-form-item> -->
        <el-form-item label="车牌号码"
                      prop="vehicleId">
          <!-- <el-input v-model="form.plate"
                    :disabled="form.id && form.id.length>0 && form.checked && form.checked === '1'"
                    placeholder="请输入车牌号码" /> -->
          <el-select v-model="form.vehicleId"
                     :disabled="form.id && form.id.length>0 && form.checked && form.checked === '1'"
                     filterable
                     allow-create
                     remote
                     :remote-method="remoteVehicleInfoMethod"
                     :loading="loading_remoteVehicleInfo"
                     @change="setTargetVehicle"
                     placeholder="请输入或选择车牌号码">
            <el-option v-for="dict in vehicleOptions"
                       :key="dict.id"
                       :label="dict.plate"
                       :value="dict.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车型"
                      prop="model">
          <el-select v-model="form.model"
                     :disabled="form.vehicleId && form.vehicleId.length>0 && form.plate && form.plate.length > 0"
                     placeholder="请选择车型">
            <el-option v-for="dict in modelOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车品牌"
                      prop="brand">
          <el-input v-model="form.brand"
                    :disabled="form.vehicleId && form.vehicleId.length>0 && form.plate && form.plate.length > 0"
                    placeholder="请输入车品牌" />
        </el-form-item>
        <el-form-item label="具体型号"
                      prop="specificBrand">
          <el-input v-model="form.specificBrand"
                    :disabled="form.vehicleId && form.vehicleId.length>0 && form.plate && form.plate.length > 0"
                    placeholder="请输入具体品牌型号" />
        </el-form-item>
        <el-form-item label="座位数"
                      prop="numberOfSeats">
          <el-input v-model="form.numberOfSeats"
                    :disabled="form.vehicleId && form.vehicleId.length>0 && form.plate && form.plate.length > 0"
                    placeholder="请输入座位数" />
        </el-form-item>
        <el-form-item label="购买时间"
                      prop="buyTime">
          <el-date-picker clearable
                          :disabled="form.vehicleId && form.vehicleId.length>0 && form.plate && form.plate.length > 0"
                          size="small"
                          style="width: 200px"
                          v-model="form.buyTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择购买时间">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="使用类型"
                      prop="typeOfResidence">
          <!-- <el-input v-model="form.typeOfResidence"
                    placeholder="请输入车辆使用类型" /> -->
          <el-select v-model="form.typeOfResidence"
                     placeholder="请选择车辆使用类型">
            <el-option v-for="dict in typeOfResidenceOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="是否是车主,(0否1是,默认1)"
                      prop="homeowner">
          <el-input v-model="form.homeowner"
                    placeholder="请输入是否是车主,(0否1是,默认1)" />
        </el-form-item> -->
        <el-form-item label="车位号码"
                      prop="parkingSpaceNumber">
          <el-input v-model="form.parkingSpaceNumber"
                    placeholder="请输入车位号码" />
        </el-form-item>
        <el-form-item label="备注"
                      prop="remark">
          <el-input v-model="form.remark"
                    type="textarea"
                    placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div class="dialog-footer HorizontalMargin">
        <el-button type="primary"
                   @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-row>
    <!-- <el-row :gutter="10"
            class="mb8">
      <el-col :span="1.5">
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAdd"
                   v-hasPermi="['communityUnit:vehicle_houses_personnel:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['communityUnit:vehicle_houses_personnel:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['communityUnit:vehicle_houses_personnel:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['communityUnit:vehicle_houses_personnel:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading"
              :data="vehicle_houses_personnelList"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <!-- <el-table-column label="人员ID"
                       align="center"
                       prop="personnelId" /> -->
      <el-table-column label="车牌"
                       width="120"
                       align="center"
                       prop="plate" />
      <el-table-column label="车辆信息"
                       align="center"
                       prop="vehicleId"
                       :formatter="vehicleFormat" />
      <el-table-column label="车辆使用类型"
                       align="center"
                       width="120"
                       prop="typeOfResidence"
                       :formatter="usedTypeFormat" />
      <el-table-column label="车位号码"
                       align="center"
                       width="80"
                       prop="parkingSpaceNumber" />
      <el-table-column label="关联房屋"
                       align="center"
                       width="280"
                       :formatter="communityHousesIdFormat">
        <template slot-scope="scope">
          <span>{{ communityHousesIdFormat(scope.row) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注"
                       align="center"
                       prop="remark" />
      <el-table-column label="操作"
                       align="center"
                       width="120"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <!-- <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['communityUnit:vehicle_houses_personnel:edit']">修改</el-button> -->
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['communityUnit:vehicle_houses_personnel:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- <pagination v-show="total>0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" /> -->

    <!-- 添加或修改车辆和社区房屋和人员关联 对话框 -->
    <!-- <el-dialog :title="title"
               :visible.sync="open"
               width="500px"
               append-to-body>
      
    </el-dialog> -->
  </div>
</template>

<script>
import { listVehicle_houses_personnel, getVehicle_houses_personnel, delVehicle_houses_personnel, addVehicle_houses_personnel, updateVehicle_houses_personnel, exportVehicle_houses_personnel } from "@/api/communityUnit/vehicle_houses_personnel";
import { listVehicle_information, getVehicle_information, delVehicle_information, addVehicle_information, updateVehicle_information, exportVehicle_information, updateVehicleCheck } from "@/api/communityUnit/vehicle_information";
import { getInfoByName } from "@/api/communityUnit/personnel_information";
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_child } from "@/api/communityUnit/community_child";
export default {
  name: "Vehicle_houses_personnel",
  props: {
    // 表单参数
    personInfo: {
      type: Object,
      default () {
        return null
      }
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
      // 社区
      communityOptions: [],
      //小区列表
      communityChildOptions: [],
      // 车辆和社区房屋和人员关联 表格数据
      vehicle_houses_personnelList: [],
      // 车型字典
      modelOptions: [],
      // 车辆使用类型字典
      typeOfResidenceOptions: [],
      // 加载备选车辆列表
      loading_remoteVehicleInfo: false,
      // 备选车辆列表
      vehicleOptions: [],
      //备选人员房屋信息表
      personOptions: [],
      //远程加载loading
      loading_remotePersonInfo: false,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        communityId: null,
        communityChildId: null,
        communityHousesId: null,
        personnelId: null,
        personnelName: null,
        vehicleId: null,
        typeOfResidence: null,
        // homeowner: null,
        parkingSpaceNumber: null,
        delFlag: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        vehicleId: [
          { required: true, message: "车牌号码不能为空", trigger: "blur" }
        ],
        model: [
          { required: true, message: "车型不能为空", trigger: "change" }
        ],
        brand: [
          { required: true, message: "车品牌不能为空", trigger: "blur" }
        ],
        numberOfSeats: [
          { required: true, message: "座位数不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created () {
    this.getCommunityDictory()
    this.getAllCommunityChildDictory()
    this.getDicts("base_vehicle_model").then(response => {
      this.modelOptions = response.data;
    });
    this.getDicts("base_vehicle_use").then(response => {
      this.typeOfResidenceOptions = response.data;
    });
  },
  mounted () {
    this.getList();
  },
  methods: {
    getCommunityDictory () {
      this.communityOptions = []
      let param = {
        pageNum: 1,
        pageSize: 100000
      }
      listCommunity(param).then(response => {
        this.communityOptions = response.rows;
      });
    },
    getAllCommunityChildDictory () {
      this.communityChildOptions = []
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: null
      }
      listCommunity_child(param).then(response => {
        this.communityChildOptions = response.rows;
      });
    },
    remotePersonInfoMethod (name) {
      this.loading_remotePersonInfo = true
      let param = {
        name: name,
        communityId: null,
        communityChildId: null
      }
      // this.queryParams.personnelId = info.id;
      //     this.queryParams.personnelName = info.name;
      getInfoByName(param).then(response => {
        this.personOptions = response.rows
        this.loading_remotePersonInfo = false
        //去掉同名的其他人
        if (this.queryParams.personnelId && this.queryParams.personnelId.length > 0) {
          let rows = response.rows.filter(item => {
            return item.id === this.queryParams.personnelId
          })
          this.personOptions = rows
        }
      })

    },
    selectDefaultPersonHouse () {
      if (this.personOptions && this.personOptions.length === 1) {
        this.form.communityHousesId = this.personOptions[0].houseId
      }
    },
    vehicleFormat (row, column) {
      let infoMsg = ''
      if (row.numberOfSeats && row.numberOfSeats > 0) {
        infoMsg += row.numberOfSeats + '座' + ' '
      }
      if (this.modelOptions && this.modelOptions.length > 0) {
        infoMsg += this.selectDictLabel(this.modelOptions, row.model) + ' '
      }
      if (row.brand) infoMsg += row.brand + ' '
      if (row.specificBrand) infoMsg += row.specificBrand + ' '
      return infoMsg
    },
    usedTypeFormat (row, column, cellValue) {
      return this.selectDictLabel(this.typeOfResidenceOptions, cellValue)
    },
    communityHousesIdFormat (row) {
      let communityName = this.getCommunityName(row.communityId)
      let communityChildName = this.getCommunityChildName(row.communityChildId)
      let houseInfo = row.communityHousesId
      if (this.personOptions && this.personOptions.length > 0) {
        let rows = this.personOptions.filter(item => {
          return item.houseId === row.communityHousesId
        })
        if (rows && rows.length > 0) {
          let dict = rows[0]
          houseInfo = communityName + communityChildName + dict.serialNumber + '号楼' + dict.unit + '单元' + dict.houseNumber
        }
      }
      return houseInfo

    },
    getCommunityName (communityId) {
      let name = communityId
      //获取社区名称
      if (this.communityOptions && this.communityOptions.length > 0) {
        let rows = this.communityOptions.filter(item => {
          return item.id === communityId
        })
        if (rows && rows.length > 0) {
          name = rows[0].name
        }
      }
      return name
    },
    getCommunityChildName (communityChildId) {
      let name = communityChildId
      //获取小区名称
      if (this.communityChildOptions && this.communityChildOptions.length > 0) {
        let rows = this.communityChildOptions.filter(item => {
          return item.id === communityChildId
        })
        if (rows && rows.length > 0) {
          name = rows[0].name
        }
      }
      return name
    },
    remoteVehicleInfoMethod (plate) {
      this.loading_remoteVehicleInfo = true
      let param = {
        pageNum: 1,
        pageSize: 100,
        plate: plate,
        checked: null
      }
      listVehicle_information(param).then(response => {
        this.vehicleOptions = response.rows;
        this.loading_remoteVehicleInfo = false;
      });
    },
    setTargetVehicle (targetVehicleId) {
      if (targetVehicleId && targetVehicleId.length > 0) {
        let target = this.vehicleOptions.filter(item => {
          return item.id === targetVehicleId
        })
        if (target && target.length > 0) {
          this.form.plate = target[0].plate
          this.form.model = target[0].model
          this.form.brand = target[0].brand
          this.form.specificBrand = target[0].specificBrand
          this.form.numberOfSeats = target[0].numberOfSeats
          this.form.parkingSpaceNumber = target[0].parkingSpaceNumber
          this.form.buyTime = target[0].buyTime
        } else {
          this.form.plate = null
        }
      }
    },
    /** 查询车辆和社区房屋和人员关联 列表 */
    getList () {
      this.loading = true;
      listVehicle_houses_personnel(this.queryParams).then(response => {
        this.vehicle_houses_personnelList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel () {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset () {
      this.form = {
        id: null,
        communityId: null,
        communityChildId: null,
        communityHousesId: null,
        personnelId: null,
        //显示用
        personnelName: null,
        vehicleId: null,
        //车辆信息，如果是新增车辆才会用到
        plate: null,
        model: null,
        brand: null,
        specificBrand: null,
        numberOfSeats: null,
        parkingSpaceNumber: null,
        buyTime: null,
        // 车辆信息，如果是新增车辆才会用到
        typeOfResidence: '1',
        // homeowner: null,
        parkingSpaceNumber: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        remark: null
      };
      this.resetForm("form");

    },
    // 表单附加
    attach () {
      if (this.queryParams.personnelId) {
        this.form.personnelId = this.queryParams.personnelId
      }
      if (this.queryParams.personnelName) {
        this.form.personnelName = this.queryParams.personnelName
      }
    },
    /** 搜索按钮操作 */
    handleQuery () {
      this.queryParams.pageNum = 1;
      this.getList();
      this.remotePersonInfoMethod(this.queryParams.personnelName)
    },
    /** 重置按钮操作 */
    resetQuery () {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange (selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset();
      this.attach();
      this.selectDefaultPersonHouse()
      this.open = true;
      this.title = "添加车辆和社区房屋和人员关联 ";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const id = row.id || this.ids
      let param = {
        id: id
      }
      listVehicle_houses_personnel(param).then(response => {
        if (response.rows && response.rows.length > 0) {
          this.form = response.rows[0];
          this.attach();
          this.remoteVehicleInfoMethod(this.form.plate)
          this.open = true;
          this.title = "修改车辆和社区房屋和人员关联 ";
        }
      });

      // getVehicle_houses_personnel(id).then(response => {
      //   this.form = response.data;
      //   this.attach();
      //   this.remoteVehicleInfoMethod(this.form.plate)
      //   this.open = true;
      //   this.title = "修改车辆和社区房屋和人员关联 ";
      // });
    },
    /* 附加选中房屋的社区小区信息 */
    attachFormHouseInfo () {
      if (!(this.form.communityHousesId && this.form.communityHousesId)) return false
      let houseInfo = this.form.communityHousesId
      if (this.personOptions && this.personOptions.length > 0) {
        let rows = this.personOptions.filter(item => {
          return item.houseId === this.form.communityHousesId
        })
        if (rows && rows.length > 0) {
          this.form.communityId = rows[0].communityId
          this.form.communityChildId = rows[0].communityChildId
        }
      }
    },
    /* 检查车辆是否已经进行关联 */
    async checkVehicleRelationInfo () {
      //查询当前车辆是否已经有关联关系
      let param = {
        vehicleId: this.form.vehicleId
      }
      let data = await listVehicle_houses_personnel(param)
      return data ? data.rows : data
    },
    /** 提交按钮 */
    submitForm () {
      let self = this
      this.$refs["form"].validate(async valid => {
        if (valid) {
          self.attachFormHouseInfo()
          if (self.form.vehicleId != null && self.form.plate != null) {
            //新增车辆关联
            let checkResult = await self.checkVehicleRelationInfo()
            if (checkResult && checkResult.length > 0) {
              self.$confirm('是否确认删除此车辆和其他人员、房屋的 ' + checkResult.length + ' 条关联关系', "警告", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
              }).then(function () {
                let ids = checkResult.map(item => item.id)
                return delVehicle_houses_personnel(ids);
              }).then(() => {
                self.msgSuccess("删除成功");
                addVehicle_houses_personnel(self.form).then(response => {
                  if (response.code === 200) {
                    self.msgSuccess("新增成功");
                    self.open = false;
                    self.getList();
                  }
                });
              }).catch(function () { });
            } else {
              addVehicle_houses_personnel(this.form).then(response => {
                if (response.code === 200) {
                  self.msgSuccess("新增成功");
                  self.open = false;
                  self.getList();
                }
              });
            }

          } else {
            //新增车辆
            self.form.plate = self.form.vehicleId
            self.form.vehicleId = null
            addVehicle_information(self.form).then(response => {
              if (response.code === 200) {
                self.form.vehicleId = response.data
                addVehicle_houses_personnel(self.form).then(response => {
                  if (response.code === 200) {
                    self.msgSuccess("新增成功");
                    self.open = false;
                    self.getList();
                  }
                });
              }
            });
          }
        }
      });
    },

    /** 删除按钮操作 */
    handleDelete (row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除车辆和社区房屋和人员关联 编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delVehicle_houses_personnel(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有车辆和社区房屋和人员关联 数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportVehicle_houses_personnel(queryParams);
      }).then(response => {
        this.download(response.msg);
      }).catch(function () { });
    }
  },
  watch: {
    //作为弹窗使用时会传入上级建筑楼栋信息
    personInfo: {
      handler: function (info) {
        console.log('关联车辆，人员：' + info.name)
        if (info && info.id) {
          this.resetForm("queryForm");
          this.queryParams.personnelId = info.id;
          this.queryParams.personnelName = info.name;
          this.handleQuery()
        }
      },
      deep: true,
      immediate: true//路由发生变化时界面还未重绘完成，刷新布局没有意义，当前已经在重绘时处理
    }
  },
};
</script>
