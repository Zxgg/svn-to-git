<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch"
             label-width="68px">
      <el-form-item label="社区名称"
                    prop="communityId">
        <el-select v-model="queryParams.communityId"
                   filterable
                   clearable
                   @change="getCommunityChildDictory"
                   placeholder="请选择社区">
          <el-option v-for="dict in communityOptions"
                     :key="dict.id"
                     :label="dict.name"
                     :value="dict.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="小区名称"
                    prop="communityChildId">
        <el-select v-model="queryParams.communityChildId"
                   filterable
                   clearable
                   placeholder="请选择小区">
          <el-option v-for="dict in communityChildOptions"
                     :key="dict.id"
                     :label="dict.name"
                     :value="dict.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="人员类型"
                    prop="sourceTableName">
        <el-select v-model="queryParams.sourceTableName"
                   placeholder="请选择人员类型"
                   clearable
                   size="small">
          <el-option v-for="dict in sourceTableNameOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="姓名"
                    prop="name">
        <el-input v-model="queryParams.name"
                  placeholder="请输入姓名"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="性别"
                    prop="genderCode">
        <el-select v-model="queryParams.genderCode"
                   placeholder="请选择性别"
                   clearable
                   size="small">
          <el-option v-for="dict in genderCodeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item> -->
      <el-form-item label="证件号码"
                    prop="idnumber">
        <el-input v-model="queryParams.idnumber"
                  placeholder="请输入证件号码"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="关爱类型"
                    prop="type">
        <el-select v-model="caringType"
                   placeholder="请选择关爱类型"
                   clearable
                   size="small">
          <el-option v-for="dict in caringTypeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="位置标记时间"
                    label-width="auto"
                    prop="locationMarkTime">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="queryParams.locationMarkTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择位置标记时间">
        </el-date-picker>
      </el-form-item> -->
      <!--       
      <el-form-item label="居住地行政区划" prop="residenceAdminDivision">
        <el-input
          v-model="queryParams.residenceAdminDivision"
          placeholder="请输入居住地行政区划"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!--      
      <el-form-item label="对应的人员表里的人员ID" prop="personId">
        <el-input
          v-model="queryParams.personId"
          placeholder="请输入对应的人员表里的人员ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->

      <el-form-item>
        <el-button type="cyan"
                   icon="el-icon-search"
                   size="mini"
                   @click="handleQuery">搜索</el-button>
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAdd"
                   v-hasPermi="['passManage:pass_personnel:add']">新增</el-button>
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['passManage:pass_personnel:remove']">删除</el-button>
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
                   v-hasPermi="['passManage:pass_personnel:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['passManage:pass_personnel:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['passManage:pass_personnel:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['passManage:pass_personnel:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading"
              stripe
              :data="pass_personnelList"
              :height="dataHeight"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <el-table-column label="姓名"
                       align="center"
                       prop="name" />
      <el-table-column label="性别"
                       align="center"
                       prop="genderCode"
                       :formatter="genderCodeFormat" />
      <!-- <el-table-column label="社区id"
                       align="center"
                       prop="communityId" /> -->
      <el-table-column label="社区名称"
                       align="center"
                       prop="communityName" />
      <!-- <el-table-column label="小区id"
                       align="center"
                       prop="communityChildId" /> -->
      <el-table-column label="小区名称"
                       align="center"
                       prop="communityChildName" />
      <!-- <el-table-column label="楼栋id"
                       align="center"
                       prop="communityBuildingId" /> -->
      <!-- <el-table-column label="楼栋名称"
                       align="center"
                       prop="communityBuildingName" /> -->
      <!-- <el-table-column label="房屋id"
                       align="center"
                       prop="communityHousesId" /> -->
      <!-- <el-table-column label="房屋名称"
                       align="center"
                       prop="communityHousesName" /> -->
      <!-- 
      <el-table-column label="设备编码"
                       align="center"
                       prop="deviceId" /> -->
      <el-table-column label="设备名称"
                       align="center"
                       prop="location_mark_addr" />
      <!-- <el-table-column label="人脸出现时间" align="center" prop="faceAppearTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.faceAppearTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="人脸消失时间" align="center" prop="faceDisAppearTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.faceDisAppearTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->

      <!-- <el-table-column label="眼镜颜色" align="center" prop="glassColor" /> -->
      <!-- <el-table-column label="发色" align="center" prop="hairColor" /> -->
      <el-table-column label="证件号码"
                       align="center"
                       prop="idnumber" />
      <!-- <el-table-column label="证件种类" align="center" prop="idType" :formatter="idTypeFormat" /> -->
      <el-table-column label="位置标记时间"
                       align="center"
                       prop="locationMarkTime"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.locationMarkTime) }}</span>
        </template>
      </el-table-column>

      <!-- <el-table-column label="国籍代码" align="center" prop="nationalityCode" :formatter="nationalityCodeFormat" /> -->
      <!-- <el-table-column label="居住地行政区划" align="center" prop="residenceAdminDivision" /> -->
      <el-table-column label="操作"
                       align="center"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-location-information"
                     @click="passLocation(scope.row)">定位</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['passManage:pass_personnel:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['passManage:pass_personnel:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <el-dialog :title="title"
               :visible.sync="gisOpen"
               append-to-body
               :before-close="closeDialog">
      <gisView :passLocationObject="passLocation_"
               :passTrackObject=null
               style="height:600px;width:800px; margin:0 auto" />
    </el-dialog>

    <!-- 添加或修改人员通行对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="600px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="120px">
        <el-form-item label="社区名称"
                      prop="communityChildId">
          <el-select v-model="form.communityId"
                     filterable
                     clearable
                     :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                     @change="getCommunityChildDictory2"
                     placeholder="请选择社区">
            <el-option v-for="dict in communityOptions"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="小区名称"
                      prop="communityChildId">
          <el-select v-model="form.communityChildId"
                     filterable
                     clearable
                     :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                     placeholder="请选择小区">
            <el-option v-for="dict in communityChildOptions2"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="姓名"
                      prop="name">
          <!-- <el-input v-model="form.name"
                    placeholder="请输入姓名" /> -->
          <el-select v-model="form.personId"
                     filterable
                     :disabled="
              form.id &&
              form.id.length > 0 &&
              form.checked &&
              form.checked === '1'
            "
                     @change="setTargetPerson"
                     remote
                     :remote-method="remotePersonInfoMethod"
                     :loading="loading_remotePersonInfo"
                     placeholder="请选择人员姓名">
            <el-option v-for="dict in personOptions"
                       :key="dict.id"
                       :label="
                dict.name +
                ' ' +
                dict.phoneNumber +
                ' ' +
                dict.serialNumber +
                '号楼' +
                dict.unit +
                '单元' +
                dict.houseNumber
              "
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="曾用名"
                      prop="usedName">
          <el-input v-model="form.usedName"
                    placeholder="请输入曾用名" />
        </el-form-item>
        <el-form-item label="性别"
                      prop="genderCode">
          <el-select v-model="form.genderCode"
                     placeholder="请选择性别">
            <el-option v-for="dict in genderCodeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="国籍代码"
                      prop="nationalityCode">
          <el-select v-model="form.nationalityCode"
                     placeholder="请选择国籍代码">
            <el-option v-for="dict in nationalityCodeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="民族代码"
                      prop="ethicCode">
          <el-input v-model="form.ethicCode"
                    placeholder="请输入民族代码" />
        </el-form-item>
        <el-form-item label="证件种类"
                      prop="idType">
          <el-select v-model="form.idType"
                     placeholder="请选择证件种类">
            <el-option v-for="dict in idTypeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="证件号码"
                      prop="idnumber">
          <el-input v-model="form.idnumber"
                    placeholder="请输入证件号码" />
        </el-form-item>
        <el-form-item label="籍贯省市县代码"
                      prop="nativeCityCode">
          <el-input v-model="form.nativeCityCode"
                    placeholder="请输入籍贯省市县代码" />
        </el-form-item>
        <el-form-item label="居住地行政区划"
                      prop="residenceAdminDivision">
          <el-input v-model="form.residenceAdminDivision"
                    placeholder="请输入居住地行政区划" />
        </el-form-item>
        <el-form-item label="眼镜颜色"
                      prop="glassColor">
          <el-input v-model="form.glassColor"
                    placeholder="请输入眼镜颜色" />
        </el-form-item>
        <el-form-item label="眼镜款式"
                      prop="glassStyle">
          <el-input v-model="form.glassStyle"
                    placeholder="请输入眼镜款式" />
        </el-form-item>
        <el-form-item label="发色"
                      prop="hairColor">
          <el-input v-model="form.hairColor"
                    placeholder="请输入发色" />
        </el-form-item>
        <el-form-item label="发型"
                      prop="hairStyle">
          <el-input v-model="form.hairStyle"
                    placeholder="请输入发型" />
        </el-form-item>
        <el-form-item label="信息分类"
                      prop="infoKind">
          <el-input v-model="form.infoKind"
                    placeholder="请输入信息分类" />
        </el-form-item>
        <el-form-item label="职业类别代码"
                      prop="jobCategory">
          <el-input v-model="form.jobCategory"
                    placeholder="请输入职业类别代码" />
        </el-form-item>
        <el-form-item label="人脸出现时间"
                      prop="faceAppearTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.faceAppearTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择人脸出现时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="人脸消失时间"
                      prop="faceDisAppearTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.faceDisAppearTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择人脸消失时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="帽子颜色"
                      prop="capColor">
          <el-input v-model="form.capColor"
                    placeholder="请输入帽子颜色" />
        </el-form-item>
        <el-form-item label="帽子款式"
                      prop="capStyle">
          <el-input v-model="form.capStyle"
                    placeholder="请输入帽子款式" />
        </el-form-item>
        <el-form-item label="左上角X坐标"
                      prop="leftTopX">
          <el-input v-model="form.leftTopX"
                    placeholder="请输入左上角X坐标" />
        </el-form-item>
        <el-form-item label="左上角Y坐标"
                      prop="leftTopY">
          <el-input v-model="form.leftTopY"
                    placeholder="请输入左上角Y坐标" />
        </el-form-item>
        <el-form-item label="右下角X坐标"
                      prop="rightBtmX">
          <el-input v-model="form.rightBtmX"
                    placeholder="请输入右下角X坐标" />
        </el-form-item>
        <el-form-item label="右下角Y坐标"
                      prop="rightBtmY">
          <el-input v-model="form.rightBtmY"
                    placeholder="请输入右下角Y坐标" />
        </el-form-item>
        <el-form-item label="位置标记时间"
                      prop="locationMarkTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.locationMarkTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择位置标记时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="人脸相似度"
                      prop="similaritydegree">
          <el-input v-model="form.similaritydegree"
                    placeholder="请输入人脸相似度" />
        </el-form-item>
        <el-form-item label="来源标识"
                      prop="sourceId">
          <el-input v-model="form.sourceId"
                    placeholder="请输入来源标识" />
        </el-form-item>
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
import {
  listPass_personnel,
  getPass_personnel,
  delPass_personnel,
  addPass_personnel,
  updatePass_personnel,
  exportPass_personnel,
} from "@/api/passManage/pass_personnel";
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import { getInfoByName } from "@/api/communityUnit/personnel_information";
import gisView from '@/views/GISView/GISView'
export default {
  name: "Pass_personnel",
  components: { gisView },
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
      // 人员通行表格数据
      pass_personnelList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      gisOpen: false,
      // 性别字典
      genderCodeOptions: [],
      // 证件种类字典
      idTypeOptions: [],
      // 国籍代码字典
      nationalityCodeOptions: [],
      // 关爱类型字典
      caringTypeOptions: [],
      // 社区
      communityOptions: [],
      //小区列表
      communityChildOptions: [],
      //小区列表
      communityChildOptions2: [],
      //远程搜索  加载人员列表
      loading_remotePersonInfo: false,
      // 人员搜索列表
      personOptions: [],
      //人员类型表列表
      sourceTableNameOptions: [],
      //删除提示
      deleteNotices: [],
      //关爱人员类型，为空标识查询所有人员（包括关爱和非关爱），为具体类型值时表示查询具体类型的关爱人员通行记录
      caringType: null,
      passType: 1,
      passLocation_: null,
      // passLocation_: {
      //   cameraSn: "",
      //   passAddress: "",
      //   passImage: "",
      //   passName: "",
      //   passTime: null,
      //   passType: 1,
      // },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        faceId: null,
        accompanyNumber: null,
        acneStain: null,
        ageLowerLimit: null,
        ageUpLimit: null,
        passType: 1,
        alias: null,
        attitude: null,
        bodySpeciallMark: null,
        capColor: null,
        capStyle: null,
        chineseAccentCode: null,
        communityBuildingId: null,
        communityBuildingName: null,
        communityChildId: null,
        communityChildName: null,
        communityHousesId: null,
        communityHousesName: null,
        communityId: null,
        communityName: null,
        corpseConditionCode: null,
        crimeCharacterCode: null,
        crimeMethod: null,
        criminalInvolvedSpecilisationCode: null,
        detaineesIdentity: null,
        detaineesSpecialIdentity: null,
        detentionHouseCode: null,
        deviceId: null,
        escapedCriminalNumber: null,
        ethicCode: null,
        eyebrowStyle: null,
        faceAppearTime: null,
        faceDisAppearTime: null,
        faceStyle: null,
        facialFeature: null,
        freckleBirthmark: null,
        genderCode: null,
        glassColor: null,
        glassStyle: null,
        hairColor: null,
        hairStyle: null,
        idnumber: null,
        idType: null,
        immigrantTypeCode: null,
        infoKind: null,
        injuredDegree: null,
        isCriminalInvolved: null,
        isDetainees: null,
        isDriver: null,
        isForeigner: null,
        isSuspectedTerrorist: null,
        isSuspiciousPerson: null,
        isVictim: null,
        jobCategory: null,
        leftTopX: null,
        leftTopY: null,
        lipStyle: null,
        locationMarkTime: null,
        memberTypeCode: null,
        mustacheStyle: null,
        name: null,
        nationalityCode: null,
        nativeCityCode: null,
        noseStyle: null,
        otherFeature: null,
        passportType: null,
        physicalFeature: null,
        residenceAdminDivision: null,
        respiratorColor: null,
        rightBtmX: null,
        rightBtmY: null,
        scarDimple: null,
        similaritydegree: null,
        skinColor: null,
        sourceId: null,
        suspectedTerroristNumber: null,
        usedName: null,
        victimType: null,
        wrinklePouch: null,
        personId: null,
        sourceTableName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        personId: [
          {
            required: true,
            message: "对应的人员表里的人员ID不能为空",
            trigger: "blur",
          },
        ],
      },
      // 搜索表单控件高度
      formHeight: 0,
    };
  },
  created () {
    this.changePage()
    this.getCommunityDictory();
    this.getDicts("sys_user_sex").then((response) => {
      this.genderCodeOptions = response.data;
    });
    this.getDicts("base_idCard").then((response) => {
      this.idTypeOptions = response.data;
    });
    this.getDicts("base_nationality").then((response) => {
      this.nationalityCodeOptions = response.data;
    });
    this.getDicts("base_type_of_caring").then((response) => {
      this.caringTypeOptions = response.data;
    });
    // this.sourceTableNameOptions = [
    //   {
    //     dictValue: "base_personnel_information",
    //     dictLabel: "住户",
    //   },
    //   {
    //     dictValue: "base_visitor_personnel_information",
    //     dictLabel: "预约访客",
    //   },
    //   {
    //     dictValue: "base_temp_visitor_personnel_information",
    //     dictLabel: "临时访客",
    //   },
    // ];
  },
  mounted () {
    // this.getList();
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight;
    });
  },
  computed: {
    dataHeight () {
      let searchFormHeight = 0;
      if (this.showSearch) searchFormHeight = this.formHeight;
      return this.$store.getters.dataEleHeight - searchFormHeight - 115;
    },
  },
  watch: {
    $route () {
      this.changePage();
    }
  },
  methods: {
    changePage () {
      let type = this.$route.path.split('passType=')[1];
      if (type) {
        this.passType = type;
        if (this.passType == 1) {
          this.sourceTableNameOptions = [
            {
              dictValue: "base_personnel_information",
              dictLabel: "住户",
            },
            {
              dictValue: "base_visitor_personnel_information",
              dictLabel: "预约访客",
            },
            {
              dictValue: "base_temp_visitor_personnel_information",
              dictLabel: "临时访客",
            },
          ]
        } else {
          this.sourceTableNameOptions = [
            {
              dictValue: "base_visitor_personnel_information",
              dictLabel: "预约访客",
            },
            {
              dictValue: "base_temp_visitor_personnel_information",
              dictLabel: "临时访客",
            },
          ]
        }
        this.getList();
      }
      // this.queryParams.passType = type;

      // console.log('type', type);
    },
    closeDialog (done) {
      console.log('dialog close ..');
      // this.passTrack = [];
      this.passLocation_ = null;
      this.gisOpen = false;
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
    getCommunityChildDictory2 (communityId) {
      this.communityChildOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId,
      };
      listCommunity_child(param).then((response) => {
        this.communityChildOptions2 = response.rows;
      });
    },
    remotePersonInfoMethod (name) {
      this.loading_remotePersonInfo = true;
      let param = {
        name: name,
        communityId: null,
        communityChildId: null,
      };
      if (this.form.communityId) {
        param.communityId = this.form.communityId;
      }
      if (this.form.communityChildId) {
        param.communityChildId = this.form.communityChildId;
      }
      getInfoByName(param).then((response) => {
        if (response && response.rows && response.rows.length > 0)
          response.rows = this.unique(response.rows, "id");
        this.personOptions = response.rows;
        this.loading_remotePersonInfo = false;
      });
    },
    setTargetPerson (targetPersonId) {
      if (targetPersonId && targetPersonId.length > 0) {
        let target = this.personOptions.filter((item) => {
          return item.id === targetPersonId;
        });
        if (target && target.length > 0) {
          this.form.name = target[0].name;
          this.form.usedName = target[0].oldName;
          this.form.genderCode = target[0].sex;
          this.form.nationalityCode = target[0].nationality;
          this.form.ethicCode = target[0].nation;
          this.form.idType = target[0].idType;
          this.form.idnumber = target[0].identificationNumber;
          this.form.nativeCityCode = target[0].location;
          this.form.residenceAdminDivision = target[0].address;
        }
      }
    },
    /** 查询人员通行列表 */
    getList () {
      this.loading = true;
      this.queryParams.passType = this.passType;
      listPass_personnel(this.queryParams).then((response) => {
        this.pass_personnelList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 性别字典翻译
    genderCodeFormat (row, column) {
      return this.selectDictLabel(this.genderCodeOptions, row.genderCode);
    },
    // 证件种类字典翻译
    idTypeFormat (row, column) {
      return this.selectDictLabel(this.idTypeOptions, row.idType);
    },
    // 国籍代码字典翻译
    nationalityCodeFormat (row, column) {
      return this.selectDictLabel(
        this.nationalityCodeOptions,
        row.nationalityCode
      );
    },
    // 取消按钮
    cancel () {
      this.open = false;
      this.reset();
    },
    //通行数据定位
    passLocation (row) {
      let passLoacation = { //通行记录定位
        cameraSn: row.deviceId, //设备编号
        passType: 1, //通行类型 1、人 2、车
        passTime: row.locationMarkTime, //抓拍时间
        passImage: row.pics, //抓拍图片
        passName: row.name, //车牌号或者人的姓名
        passAddress: '', //抓拍地点
      };
      this.passLocation_ = passLoacation;
      this.gisOpen = true
      console.log('location11', this.passLocation_);
      // this.$Notice.$emit('passLoacation', passLoacation);
    },
    // 表单重置
    reset () {
      this.form = {
        faceId: null,
        accompanyNumber: null,
        acneStain: null,
        ageLowerLimit: null,
        ageUpLimit: null,
        alias: null,
        attitude: null,
        bodySpeciallMark: null,
        capColor: null,
        capStyle: null,
        chineseAccentCode: null,
        communityBuildingId: null,
        communityBuildingName: null,
        communityChildId: null,
        communityChildName: null,
        communityHousesId: null,
        communityHousesName: null,
        communityId: null,
        communityName: null,
        corpseConditionCode: null,
        crimeCharacterCode: null,
        crimeMethod: null,
        criminalInvolvedSpecilisationCode: null,
        detaineesIdentity: null,
        detaineesSpecialIdentity: null,
        detentionHouseCode: null,
        deviceId: null,
        escapedCriminalNumber: null,
        ethicCode: null,
        eyebrowStyle: null,
        faceAppearTime: null,
        faceDisAppearTime: null,
        faceStyle: null,
        facialFeature: null,
        freckleBirthmark: null,
        genderCode: null,
        glassColor: null,
        glassStyle: null,
        hairColor: null,
        hairStyle: null,
        idnumber: null,
        idType: null,
        immigrantTypeCode: null,
        infoKind: null,
        injuredDegree: null,
        isCriminalInvolved: null,
        isDetainees: null,
        isDriver: null,
        isForeigner: null,
        isSuspectedTerrorist: null,
        isSuspiciousPerson: null,
        isVictim: null,
        jobCategory: null,
        leftTopX: null,
        leftTopY: null,
        lipStyle: null,
        locationMarkTime: null,
        memberTypeCode: null,
        mustacheStyle: null,
        name: null,
        nationalityCode: null,
        nativeCityCode: null,
        noseStyle: null,
        otherFeature: null,
        passportType: null,
        physicalFeature: null,
        residenceAdminDivision: null,
        respiratorColor: null,
        rightBtmX: null,
        rightBtmY: null,
        scarDimple: null,
        similaritydegree: 0.0,
        skinColor: null,
        sourceId: null,
        suspectedTerroristNumber: null,
        usedName: null,
        victimType: null,
        wrinklePouch: null,
        personId: null,
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
      this.deleteNotices = selection.map((item) => item.name);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset();
      this.open = true;
      this.title = "添加人员通行";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const id = row.id || this.ids;
      getPass_personnel(id).then((response) => {
        this.form = response.data;
        this.remotePersonInfoMethod(this.form.name);
        this.getCommunityChildDictory2(this.form.communityId);
        this.open = true;
        this.title = "修改人员通行";
      });
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.faceId != null) {
            updatePass_personnel(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addPass_personnel(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      const faceIds = row.id || this.ids;
      let deleteNotice = row.name || this.deleteNotices;
      this.$confirm(
        '是否确认删除人员姓名为"' + deleteNotice + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delPass_personnel(faceIds);
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
      this.$confirm("是否确认导出所有人员通行数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportPass_personnel(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () { });
    },
  },
};
</script>
