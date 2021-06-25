<template>
  <div class="Main"
       ref="MainView">
    <el-form ref="form"
             :model="form"
             :rules="rules"
             label-width="100px">
      <!-- 选择社区 -->
      <el-form-item label="社区名称"
                    prop="communityId">
        <el-select v-model="form.communityId"
                   :disabled="!showAllHouse"
                   filterable
                   @change="getCommunityChildDictory"
                   placeholder="请选择社区">
          <el-option v-for="dict in communityOptions"
                     :key="dict.id"
                     :label="dict.name"
                     :value="dict.id" />
        </el-select>
        <el-button size="mini"
                   type="text"
                   icon="el-icon-refresh"
                   :disabled="!showAllHouse"
                   @click="getCommunityDictory"
                   v-hasPermi="['communityUnit:community:query']">刷新</el-button>
      </el-form-item>
      <el-form-item label="小区名称"
                    prop="communityChildId">
        <el-select v-model="form.communityChildId"
                   :disabled="!showAllHouse"
                   filterable
                   @change="setcommunityBuildingDictory"
                   placeholder="请选择小区">
          <el-option v-for="dict in communityChildOptions"
                     :key="dict.id"
                     :label="dict.name"
                     :value="dict.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="社区楼栋"
                    prop="communityBuildingId">
        <el-select v-model="form.communityBuildingId"
                   :disabled="!showAllHouse"
                   filterable
                   placeholder="请选择楼栋">
          <el-option v-for="dict in communityBuildingOptions"
                     :key="dict.id"
                     :label="dict.name"
                     :value="dict.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="单元"
                    prop="unit">
        <el-input v-model="form.unit"
                  v-if="!unitMult"
                  class="inputWidth"
                  placeholder="请输入单元" />
        <el-input v-model="unitStart"
                  v-if="unitMult"
                  class="inputShortWidth"
                  placeholder="请输入开始单元" />
        <span v-if="unitMult"
              class="HorizontalMargin">至</span>
        <el-input v-model="unitEnd"
                  v-if="unitMult"
                  class="inputShortWidth"
                  placeholder="请输入结束单元" />
        <el-switch v-model="unitMult"
                   class="HorizontalMargin"
                   inactive-text=""
                   active-color="#11bbf3"
                   inactive-color="#0099BF"
                   active-text="多个单元">
        </el-switch>
        <!-- <el-button size="mini"
                   type="text"
                   icon="el-icon-copy-document"
                   @click="setUnit"
                   v-hasPermi="['communityUnit:community:query']">{{unitTitle}}</el-button> -->
      </el-form-item>
      <el-form-item label="楼层"
                    prop="floor">
        <el-input v-model="form.floor"
                  v-if="!floorMult"
                  class="inputWidth"
                  placeholder="请输入楼层" />
        <el-input v-model="floorStart"
                  v-if="floorMult"
                  class="inputShortWidth"
                  placeholder="请输入开始楼层" />
        <span v-if="floorMult"
              class="HorizontalMargin">至</span>
        <el-input v-model="floorEnd"
                  v-if="floorMult"
                  class="inputShortWidth"
                  placeholder="请输入结束楼层" />
        <el-switch v-model="floorMult"
                   class="HorizontalMargin"
                   inactive-text=""
                   active-text="多个楼层">
        </el-switch>
        <!-- <el-button size="mini"
                   type="text"
                   icon="el-icon-copy-document"
                   @click="setFloor"
                   v-hasPermi="['communityUnit:community:query']">{{floorTitle}}</el-button> -->
      </el-form-item>
      <el-form-item label="门牌号"
                    prop="houseNumber">
        <el-input v-model="form.houseNumber"
                  class="inputWidth"
                  placeholder="请输入门牌号" />
        <span class="HorizontalMargin">系统会按（楼层+门牌号）的方式命名最终门牌号</span>
      </el-form-item>
      <el-form-item label="电话"
                    prop="phone">
        <el-input v-model="form.phone"
                  placeholder="请输入电话" />
      </el-form-item>
      <el-form-item label="厅室"
                    prop="houseStructure">
        <el-select v-model="form.houseStructure"
                   placeholder="请选择厅室">
          <el-option v-for="dict in hallRoomOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="建筑面积"
                    prop="buildingArea">
        <el-input v-model="form.buildingArea"
                  placeholder="请输入面积(平方米)" />
      </el-form-item>
      <el-form-item label="套内面积"
                    prop="area">
        <el-input v-model="form.area"
                  placeholder="请输入面积(平方米)" />
      </el-form-item>
      <el-form-item label="是否有电梯">
        <el-radio-group v-model="form.elevator">
          <el-radio v-for="dict in baseYesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue">{{ dict.dictLabel }}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="房屋建设时间"
                    prop="constructionTime">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="form.constructionTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择房屋建设时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="产权时间"
                    prop="housePropertyRight">
        <el-input v-model="form.housePropertyRight"
                  class="inputLongWidth"
                  placeholder="请输入产权时间" />
      </el-form-item>
      <el-form-item label="使用性质"
                    prop="natureOfUse">
        <el-select v-model="form.natureOfUse"
                   placeholder="请选择使用性质">
          <el-option v-for="dict in usePropertyOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="是否出租"
                    prop="rent">
        <el-radio-group v-model="form.rent"
                        @change="setRentalPurposeHave()">
          <el-radio v-for="dict in baseYesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue">{{ dict.dictLabel }}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="出租用途"
                    prop="rentalPurpose"
                    v-if="rentalPurposeShow">
        <el-select v-model="form.rentalPurpose"
                   placeholder="请选择出租用途">
          <el-option v-for="dict in rentalUseOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="隐患类型"
                    prop="hazardType">
        <el-select v-model="form.hazardType"
                   placeholder="请选择隐患类型">
          <el-option v-for="dict in hiddenDangerOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="备注"
                    prop="remark">
        <el-input v-model="form.remark"
                  type="textarea"
                  placeholder="请输入内容" />
      </el-form-item>
      <el-form-item label="经度"
                    prop="longitud">
        <el-input v-model="form.longitud"
                  placeholder="请输入经度" />
      </el-form-item>
      <el-form-item label="纬度"
                    prop="latitude">
        <el-input v-model="form.latitude"
                  placeholder="请输入纬度" />
      </el-form-item>
      <el-form-item label="照片列表"
                    prop="pics">
        <imageListUpload :upLoadUrl="fileUpLoadUrl"
                         :key="form.id"
                         :uploadState='uploadState'
                         :filesJson.sync="form.pics" />
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_building } from "@/api/communityUnit/community_building";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import imageListUpload from "@/components/upload/uploadImageList";
import configInfo from "@/webconfig/configInfo";
// import cameraTreeLazy from '@/components/cameraTree/cameraTree_lazy'
export default {
  components: { imageListUpload },
  props: {
    showAllHouse: {
      type: Boolean,
      default: true,
    },
    building: {
      type: Object,
      default: null,
    },
    // 是否有电梯(0否1是,默认0)字典
    baseYesNoOptions: {
      type: Array,
      default () {
        return [];
      },
    },
    // 隐患类型字典
    hiddenDangerOptions: {
      type: Array,
      default () {
        return [];
      },
    },
    //厅室
    hallRoomOptions: {
      type: Array,
      default () {
        return [];
      },
    },
    //使用性质
    usePropertyOptions: {
      type: Array,
      default () {
        return [];
      },
    },
    //出租用途
    rentalUseOptions: {
      type: Array,
      default () {
        return [];
      },
    },
    // 表单校验
    rules: {
      type: Object,
      default: null,
    },
  },
  data () {
    return {
      that: this,
      form: {
        id: null,
        name: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        remark: null,
        communityId: null,
        communityName: null,
        communityChildId: null,
        communityChildName: null,
        communityBuildingId: null,
        serialNumber: null,
        buildingName: null,
        unit: null,
        floor: null,
        houseNumber: null,
        phone: null,
        houseStructure: "0",
        area: null,
        constructionTime: null,
        housePropertyRight: "70",
        natureOfUse: "0",
        rent: "0",
        rentalPurpose: null,
        hazardType: "00",
        longitud: null,
        latitude: null,
        pics: null,
        elevator: "1",
      },
      //文件上传地址
      fileUpLoadUrl: null,
      //社区列表
      communityOptions: [],
      //小区列表
      communityChildOptions: [],
      //社区楼栋列表
      communityBuildingOptions: [],
      //当前楼栋名称
      currentBuildingName: null,
      //当前楼栋序号
      currentSerialNumber: null,
      //是否是多单元
      unitMult: false,
      unitStart: 1,
      unitEnd: 6,
      unitMax: 6, //最大单元数
      //设置单元按钮标题
      unitTitle: "多单元",
      //是否是多楼层
      floorMult: false,
      floorStart: 1,
      floorEnd: 8,
      floorMax: 8, //最大单元数
      //设置单元按钮标题
      floorTitle: "多楼层",
      //出租类型是否必填
      rentalPurposeShow: false,
      // 上传图片状态
      uploadState: true
    };
  },
  created () {
    this.getCommunityDictory();
    this.fileUpLoadUrl = configInfo.FileUpLoadUri;
  },
  mounted () {

  },
  filters: {},
  methods: {
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
    setRentalPurposeHave () {
      if (this.form.rent == 1) {
        this.rentalPurposeShow = true;
      } else {
        this.rentalPurposeShow = false;
      }
    },
    getCommunityChildDictory (communityId) {
      this.communityChildOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId,
      };
      listCommunity_child(param).then((response) => {
        this.communityChildOptions = response.rows;
      });
    },
    setcommunityBuildingDictory (communityChildId) {
      this.communityBuildingOptions = [];
      if (!(communityChildId && communityChildId.length > 0)) {
        return false;
      }
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: null,
        communityChildId: communityChildId,
      };
      if (this.form.communityId) {
        param.communityId = this.form.communityId;
      }

      listCommunity_building(param).then((response) => {
        this.communityBuildingOptions = response.rows;
      });
    },
    setBuildInfo (communityChildId) {
      this.currentBuildingName = "";
      this.currentSerialNumber = "";
      let rows = this.communityBuildingOptions.filter((item) => {
        return item.id === communityChildId;
      });
      if (rows && rows.length > 0) {
        this.currentBuildingName = rows[0].name;
        this.currentSerialNumber = rows[0].serialNumber;
      }
    },
    setUnit () {
      this.unitMult = !this.unitMult;
      this.unitTitle = this.unitMult ? "单个单元" : "多个单元";
    },
    setFloor () {
      this.floorMult = !this.floorMult;
      this.floorTitle = this.floorTitle ? "单个楼层" : "多个楼层";
    },
    getHouseList () {
      let houseList = [];
      let unitList = [];
      let floorList = [];
      let houseNumber = this.form.houseNumber;
      this.setBuildInfo(this.form.communityBuildingId);
      if (this.unitMult) {
        for (
          let unitIndex = this.unitStart;
          unitIndex <= this.unitEnd;
          unitIndex++
        ) {
          unitList.push(unitIndex + "");
        }
      } else {
        unitList.push(this.form.unit);
      }
      if (this.floorMult) {
        for (
          let floorIndex = this.floorStart;
          floorIndex <= this.floorEnd;
          floorIndex++
        ) {
          floorList.push(floorIndex + "");
        }
      } else {
        floorList.push(this.form.floor);
      }
      unitList.forEach((unitItem) => {
        floorList.forEach((floorItem) => {
          // let house = this.$cf.cloneObj(this.form)
          let house = JSON.parse(JSON.stringify(this.form));
          house.unit = unitItem;
          house.floor = floorItem;
          house.houseNumber = floorItem + "" + houseNumber;
          house.name = house.houseNumber;
          house.buildingName = this.currentBuildingName;
          house.serialNumber = this.currentSerialNumber;
          houseList.push(house);
        });
      });
      // for (let unitIndex = this.unitStart; unitIndex <= this.unitEnd; unitIndex++) {
      //   for (let floorIndex = this.floorStart; floorIndex <= this.floorEnd; floorIndex++) {
      //     let house = this.form
      //     house.unit = unitIndex + ''
      //     house.floor = floorIndex + ''
      //     house.houseNumber = floorIndex + '' + house.houseNumber
      //     houseList.push(house)
      //   }
      // }
      return houseList;
    },
  },
  mounted () { },
  watch: {
    //作为弹窗使用时会传入上级建筑楼栋信息
    building: {
      handler: function (info) {
        if (info && info.communityId) {
          this.form.communityId = info.communityId;
          this.getCommunityChildDictory(this.form.communityId);
        }
        if (info && info.communityName) {
          this.form.communityName = info.communityName;
        }
        if (info && info.communityChildId) {
          this.form.communityChildId = info.communityChildId;
          this.setcommunityBuildingDictory(this.form.communityChildId);
        }
        if (info && info.communityChildName) {
          this.form.communityChildName = info.communityChildName;
        }
        if (info && info.communityBuildingId) {
          this.form.communityBuildingId = info.communityBuildingId;
        }
        if (info && info.communityBuildingName) {
          this.form.communityBuildingName = info.communityBuildingName;
        }
        if (info && info.floor) {
          this.form.floor = info.floor;
          this.floorEnd = info.floor;
          this.unitMax = info.floor;
        }
        if (info && info.unit) {
          this.form.unit = info.unit;
          this.unitEnd = info.unit;
          this.floorMax = info.unit;
        }
      },
      deep: true,
      immediate: true, //路由发生变化时界面还未重绘完成，刷新布局没有意义，当前已经在重绘时处理
    },
  },
};
</script>
<style lang="scss" scoped>
.Main {
  width: 100%;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
}
</style>