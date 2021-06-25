<template>
  <div class="app-container">
    <el-form :model="queryParams"
             ref="queryForm"
             :inline="true"
             v-show="showSearch"
             label-width="68px">
      <!-- 
      <el-form-item label="小区id" prop="communityChildId">
        <el-input
          v-model="queryParams.communityChildId"
          placeholder="请输入小区id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="小区名称"
                    prop="communityChildName">
        <el-input v-model="queryParams.communityChildName"
                  placeholder="请输入小区名称"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!--      
      <el-form-item label="社区id" prop="communityId">
        <el-input
          v-model="queryParams.communityId"
          placeholder="请输入社区id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="社区名称"
                    prop="communityName">
        <el-input v-model="queryParams.communityName"
                  placeholder="请输入社区名称"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="设备编码" prop="deviceId">
        <el-input
          v-model="queryParams.deviceId"
          placeholder="请输入设备编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="在逃人员编号" prop="escapedCriminalNumber">
        <el-input
          v-model="queryParams.escapedCriminalNumber"
          placeholder="请输入在逃人员编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="民族代码" prop="ethicCode">
        <el-input
          v-model="queryParams.ethicCode"
          placeholder="请输入民族代码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->

      <!-- <el-form-item label="人脸消失时间" prop="faceDisAppearTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.faceDisAppearTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择人脸消失时间">
        </el-date-picker>
      </el-form-item> -->
      <el-form-item label="姓名"
                    prop="name">
        <el-input v-model="queryParams.name"
                  placeholder="请输入姓名"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="性别"
                    prop="genderCode">
        <el-select v-model="queryParams.genderCode"
                   clearable
                   placeholder="请选择性别">
          <el-option v-for="dict in sexOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="证件号码"
                    prop="idnumber">
        <el-input v-model="queryParams.idnumber"
                  placeholder="请输入证件号码"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="出现时间"
                    prop="faceAppearTime">
        <el-date-picker clearable
                        size="small"
                        style="width: 200px"
                        v-model="queryParams.faceAppearTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择人脸出现时间">
        </el-date-picker>
      </el-form-item>
      <!-- <el-form-item label="证件种类" prop="idType">
        <el-select v-model="queryParams.idType" placeholder="请选择证件种类" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item> -->
      <!--      
      <el-form-item label="是否涉案人员" prop="isCriminalInvolved">
        <el-input
          v-model="queryParams.isCriminalInvolved"
          placeholder="请输入是否涉案人员"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="是否在押人员" prop="isDetainees">
        <el-input
          v-model="queryParams.isDetainees"
          placeholder="请输入是否在押人员"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!--      
      <el-form-item label="是否涉外人员" prop="isForeigner">
        <el-input
          v-model="queryParams.isForeigner"
          placeholder="请输入是否涉外人员"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="是否涉恐人员" prop="isSuspectedTerrorist">
        <el-input
          v-model="queryParams.isSuspectedTerrorist"
          placeholder="请输入是否涉恐人员"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!--      
      <el-form-item label="位置标记时间" prop="locationMarkTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.locationMarkTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择位置标记时间">
        </el-date-picker>
      </el-form-item> -->

      <!-- <el-form-item label="国籍代码" prop="nationalityCode">
        <el-input
          v-model="queryParams.nationalityCode"
          placeholder="请输入国籍代码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->

      <el-form-item label="相似度"
                    prop="similaritydegree">
        <el-input v-model="queryParams.similaritydegree"
                  placeholder="请输入相似度"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>

      <el-form-item label="设备名称"
                    prop="locationMarkAddr">
        <el-input v-model="queryParams.locationMarkAddr"
                  placeholder="请输入设备名称"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan"
                   icon="el-icon-search"
                   size="mini"
                   @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh"
                   size="mini"
                   @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10"
            class="mb8">
      <el-col :span="1.5">
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   @click="handleAdd"
                   v-hasPermi="['isstechadmin:alarm_personnel:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   :disabled="single"
                   @click="handleUpdate"
                   v-hasPermi="['isstechadmin:alarm_personnel:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDelete"
                   v-hasPermi="['isstechadmin:alarm_personnel:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['isstechadmin:alarm_personnel:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading"
              :data="alarm_personnelList"
              :height="dataHeight"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"
                       width="55"
                       align="center" />
      <!-- <el-table-column label="社区id"
                       align="center"
                       prop="communityId" /> -->
      <el-table-column label="社区名称"
                       align="center"
                       width="140"
                       prop="communityName" />
      <!-- <el-table-column label="小区id"
                       align="center"
                       prop="communityChildId" /> -->
      <el-table-column label="小区名称"
                       align="center"
                       width="140"
                       prop="communityChildName" />
      <el-table-column label="人脸出现时间"
                       align="center"
                       prop="faceAppearTime"
                       width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.faceAppearTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="姓名"
                       align="center"
                       width="140"
                       prop="name" />
      <el-table-column label="性别"
                       align="center"
                       width="80"
                       prop="genderCode"
                       :formatter="sexFormat" />
      <el-table-column label="证件种类"
                       align="center"
                       width="80"
                       prop="idType" />
      <el-table-column label="证件号码"
                       align="center"
                       width="180"
                       prop="idnumber" />
      <el-table-column label="国籍代码"
                       align="center"
                       prop="nationalityCode" />
      <el-table-column label="相似度"
                       align="center"
                       width="80"
                       prop="similaritydegree">
        <template slot-scope="scope">
          <span>{{ Math.round(scope.row.similaritydegree*100)/ 100  }}</span>
        </template>
      </el-table-column>
      <el-table-column label="设备名称"
                       align="center"
                       width="180"
                       prop="locationMarkAddr" />
      <el-table-column label="照片"
                       align="center"
                       prop="pics">
        <template slot-scope="scope">
          <el-image v-if="scope.row.pics && scope.row.pics !== ''"
                    style="width: 100px; height: 80px"
                    :src="getImgUrl(scope.row.pics)"
                    fit="fill"></el-image>
          <!-- scope.row.pics -->
          <div v-else>无图</div>
        </template>
      </el-table-column>
      <el-table-column label="操作"
                       align="center"
                       width="180"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['isstechadmin:alarm_personnel:edit']">修改</el-button>
          <el-button size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['isstechadmin:alarm_personnel:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList" />

    <!-- 添加或修改告警人员对话框 -->
    <el-dialog :title="title"
               :visible.sync="open"
               width="800px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="180px">
        <!-- <el-form-item label="社区id"
                      prop="communityId">
          <el-input v-model="form.communityId"
                    placeholder="请输入社区id" />
        </el-form-item> -->
        <el-form-item label="社区名称"
                      prop="communityName">
          <el-input v-model="form.communityName"
                    placeholder="请输入社区名称" />
        </el-form-item>
        <el-form-item label="小区名称"
                      prop="communityChildName">
          <el-input v-model="form.communityChildName"
                    placeholder="请输入小区名称" />
        </el-form-item>
        <!--    <el-form-item label="设备编码"
                      prop="deviceId">
          <el-input v-model="form.deviceId"
                    placeholder="请输入设备编码" />
        </el-form-item> -->

        <el-form-item label="人脸出现时间"
                      prop="faceAppearTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.faceAppearTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择人脸出现时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="人脸消失时间"
                      prop="faceDisAppearTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.faceDisAppearTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择人脸消失时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="姓名"
                      prop="name">
          <el-input v-model="form.name"
                    placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="曾用名"
                      prop="usedName">
          <el-input v-model="form.usedName"
                    placeholder="请输入曾用名" />
        </el-form-item>
        <el-form-item label="性别代码"
                      prop="genderCode">
          <!-- <el-input v-model="form.genderCode"
                    placeholder="请输入性别代码" /> -->
          <el-select v-model="form.genderCode"
                     placeholder="请选择性别">
            <el-option v-for="dict in sexOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="国籍代码"
                      prop="nationalityCode">
          <el-input v-model="form.nationalityCode"
                    placeholder="请输入国籍代码" />
        </el-form-item> -->
        <!-- <el-form-item label="证件种类"
                      prop="idType">
          <el-select v-model="form.idType"
                     placeholder="请选择证件种类">
            <el-option label="请选择字典生成"
                       value="" />
          </el-select>
        </el-form-item> -->
        <el-form-item label="证件号码"
                      prop="idnumber">
          <el-input v-model="form.idnumber"
                    placeholder="请输入证件号码" />
        </el-form-item>
        <el-form-item label="人脸相似度"
                      prop="similaritydegree">
          <el-input v-model="form.similaritydegree"
                    placeholder="请输入人脸相似度">
            <template slot-scope="{ item }">
              <span>{{ Math.round(scope.row.similaritydegree*100)/ 100  }}</span>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item label="设备名称"
                      prop="locationMarkAddr">
          <el-input v-model="form.locationMarkAddr"
                    placeholder="请输入设备名称" />
        </el-form-item>
        <el-form-item label="照片"
                      prop="pics">
          <!-- <el-input v-model="form.pics"
                    type="textarea"
                    placeholder="请输入内容" /> -->
          <el-image v-if="form.pics && form.pics !== ''"
                    style="width: 100px; height: 80px"
                    :src="getImgUrl(form.pics)"
                    fit="fill"></el-image>
          <div v-else>无图</div>
        </el-form-item>
        <!--    <el-form-item label="位置标记时间"
                      prop="locationMarkTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.locationMarkTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择位置标记时间">
          </el-date-picker>
        </el-form-item> -->

        <!-- <el-form-item label="籍贯省市县代码"
                      prop="nativeCityCode">
          <el-input v-model="form.nativeCityCode"
                    placeholder="请输入籍贯省市县代码" />
        </el-form-item> -->
        <!--         
        <el-form-item label="居住地行政区划"
                      prop="residenceAdminDivision">
          <el-input v-model="form.residenceAdminDivision"
                    placeholder="请输入居住地行政区划" />
        </el-form-item> -->

        <!--        
        <el-form-item label="是否已经审核确认,(0否1是,默认0)"
                      prop="checked">
          <el-input v-model="form.checked"
                    placeholder="请输入是否已经审核确认,(0否1是,默认0)" />
        </el-form-item> -->

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
import { listAlarm_personnel, getAlarm_personnel, delAlarm_personnel, addAlarm_personnel, updateAlarm_personnel, exportAlarm_personnel } from "@/api/passManage/alarm_personnel";
import configInfo from "@/webconfig/configInfo";
export default {
  name: "Alarm_personnel",
  data () {
    return {
      that: this,
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
      //小区列表
      communityChildOptions2: [],
      // 告警人员表格数据
      alarm_personnelList: [],
      // 性别字典
      sexOptions: [],
      // 国籍字典
      nationalityOptions: [],
      // 证件类型代码字典
      idTypeOptions: [],
      // 民族字典
      nationOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
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
        similaritydegree: null,
        skinColor: null,
        sourceId: null,
        suspectedTerroristNumber: null,
        usedName: null,
        victimType: null,
        wrinklePouch: null,
        personId: null,
        sourceTableName: null,
        checked: null,
        pics: null,
        locationMarkAddr: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        faceId: [
          { required: true, message: "人脸ID不能为空", trigger: "blur" }
        ],
        personId: [
          { required: true, message: "对应的人员表里的人员ID不能为空", trigger: "blur" }
        ],
        delFlag: [
          { required: true, message: "删除标志不能为空", trigger: "blur" }
        ],
        checked: [
          { required: true, message: "是否已经审核确认,(0否1是,默认0)不能为空", trigger: "blur" }
        ],
      },
      // 搜索表单控件高度
      formHeight: 0
    };
  },
  created () {
    // this.getCommunityDictory();
    // this.getList();
    this.getDicts("sys_user_sex").then((response) => {
      this.sexOptions = response.data;
    });
    this.getDicts("base_nationality").then((response) => {
      this.nationalityOptions = response.data;
    });
    this.getDicts("base_idCard").then((response) => {
      this.idTypeOptions = response.data;
    });
    this.getDicts("base_nation").then((response) => {
      this.nationOptions = response.data;
    });
  },
  methods: {
    // getCommunityDictory () {
    //   this.communityOptions = [];
    //   let param = {
    //     pageNum: 1,
    //     pageSize: 100000,
    //   };
    //   listCommunity(param).then((response) => {
    //     this.communityOptions = response.rows;
    //   });
    // },
    // getCommunityChildDictory (communityId) {
    //   this.communityChildOptions = [];
    //   let param = {
    //     pageNum: 1,
    //     pageSize: 100000,
    //     communityId: communityId,
    //   };
    //   listCommunity_child(param).then((response) => {
    //     this.communityChildOptions = response.rows;
    //   });
    // },
    // getCommunityChildDictory2 (communityId) {
    //   this.communityChildOptions2 = [];
    //   let param = {
    //     pageNum: 1,
    //     pageSize: 100000,
    //     communityId: communityId,
    //   };
    //   listCommunity_child(param).then((response) => {
    //     this.communityChildOptions2 = response.rows;
    //   });
    // },
    getImgUrl (url) {
      let rest = url
      if (url !== undefined && url !== null && url.indexOf('http') === 0) {
        rest = url
      } else {
        rest = configInfo.imgEchoAddress + url
      }
      return rest
    },
    /** 查询告警人员列表 */
    getList () {
      this.loading = true;
      listAlarm_personnel(this.queryParams).then(response => {
        this.alarm_personnelList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 性别字典翻译
    sexFormat (row, column) {
      return this.selectDictLabel(this.sexOptions, row.genderCode);
    },
    // 国籍字典翻译
    nationalityFormat (row, column) {
      return this.selectDictLabel(this.nationalityOptions, row.nationality);
    },
    // 证件类型代码字典翻译
    idTypeFormat (row, column) {
      return this.selectDictLabel(this.idTypeOptions, row.idType);
    },
    // 民族字典翻译
    nationFormat (row, column) {
      return this.selectDictLabel(this.nationOptions, row.nation);
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
        similaritydegree: null,
        skinColor: null,
        sourceId: null,
        suspectedTerroristNumber: null,
        usedName: null,
        victimType: null,
        wrinklePouch: null,
        personId: null,
        delFlag: null,
        sourceTableName: null,
        checked: null,
        pics: null,
        locationMarkAddr: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset();
      this.open = true;
      this.title = "添加告警人员";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      let self = this
      self.reset();
      const id = row.id || self.ids
      getAlarm_personnel(id).then(response => {
        self.form = response.data;
        self.open = true;
        self.title = "修改告警人员";
      });
    },
    /** 提交按钮 */
    submitForm () {
      let self = this
      self.$refs["form"].validate(valid => {
        if (valid) {
          if (self.form.id != null) {
            updateAlarm_personnel(self.form).then(response => {
              if (response.code === 200) {
                self.msgSuccess("修改成功");
                self.open = false;
                self.getList();
              }
            });
          } else {
            addAlarm_personnel(self.form).then(response => {
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
      let self = this
      const ids = row.id || self.ids;
      this.$confirm('是否确认删除告警人员编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delAlarm_personnel(ids);
      }).then(() => {
        self.getList();
        self.msgSuccess("删除成功");
      }).catch(function () { });
    },
    /** 导出按钮操作 */
    handleExport () {
      let self = this
      const queryParams = self.queryParams;
      self.$confirm('是否确认导出所有告警人员数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportAlarm_personnel(queryParams);
      }).then(response => {
        self.download(response.msg);
      }).catch(function () { });
    }
  },
  computed: {
    dataHeight () {
      let searchFormHeight = 0
      if (this.showSearch) searchFormHeight = this.formHeight
      return this.$store.getters.dataEleHeight - searchFormHeight - 160
    }
  },
  mounted () {
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight
    })
    this.fileUpLoadUrl = configInfo.FileUpLoadUri;
    this.getList();
  },
};
</script>
