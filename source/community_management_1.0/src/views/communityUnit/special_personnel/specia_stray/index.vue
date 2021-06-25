<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="创建人" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入创建人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新人" prop="updateBy">
        <el-input
          v-model="queryParams.updateBy"
          placeholder="请输入更新人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="人员ID" prop="personnelId">
        <el-input
          v-model="queryParams.personnelId"
          placeholder="请输入人员ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="来源类型" prop="sourceType">
        <el-select v-model="queryParams.sourceType" placeholder="请选择来源类型" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="是否有效" prop="enable">
        <el-input
          v-model="queryParams.enable"
          placeholder="请输入是否有效"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否属于留守儿童" prop="isStayChildren">
        <el-select v-model="queryParams.isStayChildren" placeholder="请选择是否属于留守儿童" clearable size="small">
          <el-option
            v-for="dict in isStayChildrenOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否享受低保补助" prop="isEnjoySubsidy">
        <el-select v-model="queryParams.isEnjoySubsidy" placeholder="请选择是否享受低保补助" clearable size="small">
          <el-option
            v-for="dict in isEnjoySubsidyOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="学习状况" prop="studySituation">
        <el-select v-model="queryParams.studySituation" placeholder="请选择学习状况" clearable size="small">
          <el-option
            v-for="dict in studySituationOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="未入学原因" prop="unenrolledReason">
        <el-input
          v-model="queryParams.unenrolledReason"
          placeholder="请输入未入学原因"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="辍学原因" prop="dropReason">
        <el-select v-model="queryParams.dropReason" placeholder="请选择辍学原因" clearable size="small">
          <el-option
            v-for="dict in dropReasonOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="辍学其他原因" prop="dropOtherReason">
        <el-input
          v-model="queryParams.dropOtherReason"
          placeholder="请输入辍学其他原因"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="就业情况" prop="jobSituation">
        <el-select v-model="queryParams.jobSituation" placeholder="请选择就业情况" clearable size="small">
          <el-option
            v-for="dict in jobSituationOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="就业情况其他" prop="jobSituationOther">
        <el-input
          v-model="queryParams.jobSituationOther"
          placeholder="请输入就业情况其他"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="未就业或失业原因" prop="notJobReason">
        <el-select v-model="queryParams.notJobReason" placeholder="请选择未就业或失业原因" clearable size="small">
          <el-option
            v-for="dict in notJobReasonOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="未就业或失业其他原因" prop="notJobOtherReason">
        <el-input
          v-model="queryParams.notJobOtherReason"
          placeholder="请输入未就业或失业其他原因"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="监护情况" prop="custodySituation">
        <el-select v-model="queryParams.custodySituation" placeholder="请选择监护情况" clearable size="small">
          <el-option
            v-for="dict in custodySituationOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="监护情况原因" prop="custodySituationReason">
        <el-input
          v-model="queryParams.custodySituationReason"
          placeholder="请输入监护情况原因"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否有不良行为" prop="isUnhealthyAction">
        <el-select v-model="queryParams.isUnhealthyAction" placeholder="请选择是否有不良行为" clearable size="small">
          <el-option
            v-for="dict in isUnhealthyActionOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="不良行为其他类型" prop="unhealthyActionType">
        <el-select v-model="queryParams.unhealthyActionType" placeholder="请选择不良行为其他类型" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="是否有违法犯罪经历" prop="isCrimeExperience">
        <el-select v-model="queryParams.isCrimeExperience" placeholder="请选择是否有违法犯罪经历" clearable size="small">
          <el-option
            v-for="dict in isCrimeExperienceOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="家庭主要经济来源" prop="mainSource">
        <el-select v-model="queryParams.mainSource" placeholder="请选择家庭主要经济来源" clearable size="small">
          <el-option
            v-for="dict in mainSourceOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="其他经济来源" prop="otherSource">
        <el-input
          v-model="queryParams.otherSource"
          placeholder="请输入其他经济来源"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属类别" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择所属类别" clearable size="small">
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['special_personnel:specia_stray:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['special_personnel:specia_stray:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['special_personnel:specia_stray:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['special_personnel:specia_stray:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="specia_strayList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="唯一标识" align="center" prop="id" />
      <el-table-column label="创建单位" align="center" prop="deptId" />
      <el-table-column label="更新单位" align="center" prop="updateDeptId" />
      <el-table-column label="人员ID" align="center" prop="personnelId" />
      <el-table-column label="来源类型" align="center" prop="sourceType" />
      <el-table-column label="是否有效" align="center" prop="enable" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="是否属于留守儿童" align="center" prop="isStayChildren" :formatter="isStayChildrenFormat" />
      <el-table-column label="是否享受低保补助" align="center" prop="isEnjoySubsidy" :formatter="isEnjoySubsidyFormat" />
      <el-table-column label="学习状况" align="center" prop="studySituation" :formatter="studySituationFormat" />
      <el-table-column label="未入学原因" align="center" prop="unenrolledReason" />
      <el-table-column label="辍学原因" align="center" prop="dropReason" :formatter="dropReasonFormat" />
      <el-table-column label="辍学其他原因" align="center" prop="dropOtherReason" />
      <el-table-column label="就业情况" align="center" prop="jobSituation" :formatter="jobSituationFormat" />
      <el-table-column label="就业情况其他" align="center" prop="jobSituationOther" />
      <el-table-column label="未就业或失业原因" align="center" prop="notJobReason" :formatter="notJobReasonFormat" />
      <el-table-column label="未就业或失业其他原因" align="center" prop="notJobOtherReason" />
      <el-table-column label="监护情况" align="center" prop="custodySituation" :formatter="custodySituationFormat" />
      <el-table-column label="监护情况原因" align="center" prop="custodySituationReason" />
      <el-table-column label="是否有不良行为" align="center" prop="isUnhealthyAction" :formatter="isUnhealthyActionFormat" />
      <el-table-column label="不良行为其他类型" align="center" prop="unhealthyActionType" />
      <el-table-column label="是否有违法犯罪经历" align="center" prop="isCrimeExperience" :formatter="isCrimeExperienceFormat" />
      <el-table-column label="家庭主要经济来源" align="center" prop="mainSource" :formatter="mainSourceFormat" />
      <el-table-column label="其他经济来源" align="center" prop="otherSource" />
      <el-table-column label="所属类别" align="center" prop="type" :formatter="typeFormat" />
      <el-table-column label="其它需要说明的问题" align="center" prop="otherProblem" />
      <el-table-column label="家庭成员基本情况" align="center" prop="memberSituation" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['special_personnel:specia_stray:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['special_personnel:specia_stray:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改闲散流浪人员信息 对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="创建单位" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入创建单位" />
        </el-form-item>
        <el-form-item label="更新单位" prop="updateDeptId">
          <el-input v-model="form.updateDeptId" placeholder="请输入更新单位" />
        </el-form-item>
        <el-form-item label="删除标志" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
        </el-form-item>
        <el-form-item label="人员ID" prop="personnelId">
          <el-input v-model="form.personnelId" placeholder="请输入人员ID" />
        </el-form-item>
        <el-form-item label="来源类型" prop="sourceType">
          <el-select v-model="form.sourceType" placeholder="请选择来源类型">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="是否有效" prop="enable">
          <el-input v-model="form.enable" placeholder="请输入是否有效" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="是否属于留守儿童" prop="isStayChildren">
          <el-select v-model="form.isStayChildren" placeholder="请选择是否属于留守儿童">
            <el-option
              v-for="dict in isStayChildrenOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否享受低保补助" prop="isEnjoySubsidy">
          <el-select v-model="form.isEnjoySubsidy" placeholder="请选择是否享受低保补助">
            <el-option
              v-for="dict in isEnjoySubsidyOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学习状况" prop="studySituation">
          <el-select v-model="form.studySituation" placeholder="请选择学习状况">
            <el-option
              v-for="dict in studySituationOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="未入学原因" prop="unenrolledReason">
          <el-input v-model="form.unenrolledReason" placeholder="请输入未入学原因" />
        </el-form-item>
        <el-form-item label="辍学原因" prop="dropReason">
          <el-select v-model="form.dropReason" placeholder="请选择辍学原因">
            <el-option
              v-for="dict in dropReasonOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="辍学其他原因" prop="dropOtherReason">
          <el-input v-model="form.dropOtherReason" placeholder="请输入辍学其他原因" />
        </el-form-item>
        <el-form-item label="就业情况" prop="jobSituation">
          <el-select v-model="form.jobSituation" placeholder="请选择就业情况">
            <el-option
              v-for="dict in jobSituationOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="就业情况其他" prop="jobSituationOther">
          <el-input v-model="form.jobSituationOther" placeholder="请输入就业情况其他" />
        </el-form-item>
        <el-form-item label="未就业或失业原因" prop="notJobReason">
          <el-select v-model="form.notJobReason" placeholder="请选择未就业或失业原因">
            <el-option
              v-for="dict in notJobReasonOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="未就业或失业其他原因" prop="notJobOtherReason">
          <el-input v-model="form.notJobOtherReason" placeholder="请输入未就业或失业其他原因" />
        </el-form-item>
        <el-form-item label="监护情况" prop="custodySituation">
          <el-select v-model="form.custodySituation" placeholder="请选择监护情况">
            <el-option
              v-for="dict in custodySituationOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="监护情况原因" prop="custodySituationReason">
          <el-input v-model="form.custodySituationReason" placeholder="请输入监护情况原因" />
        </el-form-item>
        <el-form-item label="是否有不良行为" prop="isUnhealthyAction">
          <el-select v-model="form.isUnhealthyAction" placeholder="请选择是否有不良行为">
            <el-option
              v-for="dict in isUnhealthyActionOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="不良行为其他类型" prop="unhealthyActionType">
          <el-select v-model="form.unhealthyActionType" placeholder="请选择不良行为其他类型">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="是否有违法犯罪经历" prop="isCrimeExperience">
          <el-select v-model="form.isCrimeExperience" placeholder="请选择是否有违法犯罪经历">
            <el-option
              v-for="dict in isCrimeExperienceOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="家庭主要经济来源" prop="mainSource">
          <el-select v-model="form.mainSource" placeholder="请选择家庭主要经济来源">
            <el-option
              v-for="dict in mainSourceOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="其他经济来源" prop="otherSource">
          <el-input v-model="form.otherSource" placeholder="请输入其他经济来源" />
        </el-form-item>
        <el-form-item label="所属类别" prop="type">
          <el-select v-model="form.type" placeholder="请选择所属类别">
            <el-option
              v-for="dict in typeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="其它需要说明的问题" prop="otherProblem">
          <el-input v-model="form.otherProblem" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="家庭成员基本情况" prop="memberSituation">
          <el-input v-model="form.memberSituation" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSpecia_stray, getSpecia_stray, delSpecia_stray, addSpecia_stray, updateSpecia_stray, exportSpecia_stray } from "@/api/special_personnel/specia_stray";

export default {
  name: "Specia_stray",
  data() {
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
      // 闲散流浪人员信息 表格数据
      specia_strayList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否属于留守儿童字典
      isStayChildrenOptions: [],
      // 是否享受低保补助字典
      isEnjoySubsidyOptions: [],
      // 学习状况字典
      studySituationOptions: [],
      // 辍学原因字典
      dropReasonOptions: [],
      // 就业情况字典
      jobSituationOptions: [],
      // 未就业或失业原因字典
      notJobReasonOptions: [],
      // 监护情况字典
      custodySituationOptions: [],
      // 是否有不良行为字典
      isUnhealthyActionOptions: [],
      // 是否有违法犯罪经历字典
      isCrimeExperienceOptions: [],
      // 家庭主要经济来源字典
      mainSourceOptions: [],
      // 所属类别字典
      typeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        createBy: null,
        updateBy: null,
        personnelId: null,
        sourceType: null,
        enable: null,
        remark: null,
        isStayChildren: null,
        isEnjoySubsidy: null,
        studySituation: null,
        unenrolledReason: null,
        dropReason: null,
        dropOtherReason: null,
        jobSituation: null,
        jobSituationOther: null,
        notJobReason: null,
        notJobOtherReason: null,
        custodySituation: null,
        custodySituationReason: null,
        isUnhealthyAction: null,
        unhealthyActionType: null,
        isCrimeExperience: null,
        mainSource: null,
        otherSource: null,
        type: null,
        otherProblem: null,
        memberSituation: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        delFlag: [
          { required: true, message: "删除标志不能为空", trigger: "blur" }
        ],
        personnelId: [
          { required: true, message: "人员ID不能为空", trigger: "blur" }
        ],
        enable: [
          { required: true, message: "是否有效不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("base_yes_no").then(response => {
      this.isStayChildrenOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.isEnjoySubsidyOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.studySituationOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.dropReasonOptions = response.data;
    });
    this.getDicts("sys_normal_disable").then(response => {
      this.jobSituationOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.notJobReasonOptions = response.data;
    });
    this.getDicts("sys_normal_disable").then(response => {
      this.custodySituationOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.isUnhealthyActionOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.isCrimeExperienceOptions = response.data;
    });
    this.getDicts("sys_normal_disable").then(response => {
      this.mainSourceOptions = response.data;
    });
    this.getDicts("sys_normal_disable").then(response => {
      this.typeOptions = response.data;
    });
  },
  methods: {
    /** 查询闲散流浪人员信息 列表 */
    getList() {
      this.loading = true;
      listSpecia_stray(this.queryParams).then(response => {
        this.specia_strayList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 是否属于留守儿童字典翻译
    isStayChildrenFormat(row, column) {
      return this.selectDictLabel(this.isStayChildrenOptions, row.isStayChildren);
    },
    // 是否享受低保补助字典翻译
    isEnjoySubsidyFormat(row, column) {
      return this.selectDictLabel(this.isEnjoySubsidyOptions, row.isEnjoySubsidy);
    },
    // 学习状况字典翻译
    studySituationFormat(row, column) {
      return this.selectDictLabel(this.studySituationOptions, row.studySituation);
    },
    // 辍学原因字典翻译
    dropReasonFormat(row, column) {
      return this.selectDictLabel(this.dropReasonOptions, row.dropReason);
    },
    // 就业情况字典翻译
    jobSituationFormat(row, column) {
      return this.selectDictLabel(this.jobSituationOptions, row.jobSituation);
    },
    // 未就业或失业原因字典翻译
    notJobReasonFormat(row, column) {
      return this.selectDictLabel(this.notJobReasonOptions, row.notJobReason);
    },
    // 监护情况字典翻译
    custodySituationFormat(row, column) {
      return this.selectDictLabel(this.custodySituationOptions, row.custodySituation);
    },
    // 是否有不良行为字典翻译
    isUnhealthyActionFormat(row, column) {
      return this.selectDictLabel(this.isUnhealthyActionOptions, row.isUnhealthyAction);
    },
    // 是否有违法犯罪经历字典翻译
    isCrimeExperienceFormat(row, column) {
      return this.selectDictLabel(this.isCrimeExperienceOptions, row.isCrimeExperience);
    },
    // 家庭主要经济来源字典翻译
    mainSourceFormat(row, column) {
      return this.selectDictLabel(this.mainSourceOptions, row.mainSource);
    },
    // 所属类别字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        createBy: null,
        deptId: null,
        createTime: null,
        updateBy: null,
        updateDeptId: null,
        updateTime: null,
        delFlag: null,
        personnelId: null,
        sourceType: null,
        enable: null,
        remark: null,
        isStayChildren: null,
        isEnjoySubsidy: null,
        studySituation: null,
        unenrolledReason: null,
        dropReason: null,
        dropOtherReason: null,
        jobSituation: null,
        jobSituationOther: null,
        notJobReason: null,
        notJobOtherReason: null,
        custodySituation: null,
        custodySituationReason: null,
        isUnhealthyAction: null,
        unhealthyActionType: null,
        isCrimeExperience: null,
        mainSource: null,
        otherSource: null,
        type: null,
        otherProblem: null,
        memberSituation: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加闲散流浪人员信息 ";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSpecia_stray(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改闲散流浪人员信息 ";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSpecia_stray(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addSpecia_stray(this.form).then(response => {
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
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除闲散流浪人员信息 编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delSpecia_stray(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有闲散流浪人员信息 数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportSpecia_stray(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
