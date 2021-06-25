<template>
  <div class="app-container">
    <div class="main HorizontalLeft ">

      <!-- 添加或修改疫情信息收集对话框 -->
      <!-- <el-dialog :title="title"
               :visible.sync="open"
               width="800px"
               append-to-body> -->
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="240px">
        <el-form-item label="社区名称"
                      prop="communityChildId">
          <el-select v-model="form.communityId"
                     filterable
                     clearable
                     :disabled="form.id && form.id.length>0 && form.checked && form.checked === '1'"
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
                     :disabled="form.id && form.id.length>0 && form.checked && form.checked === '1'"
                     placeholder="请选择小区">
            <el-option v-for="dict in communityChildOptions2"
                       :key="dict.id"
                       :label="dict.name"
                       :value="dict.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="姓名"
                      prop="name">
          <el-input v-model="form.name"
                    placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="form.sex">
            <el-radio v-for="dict in sexOptions"
                      :key="dict.dictLabel"
                      :label="dict.dictLabel">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="手机号"
                      prop="phone">
          <el-input v-model="form.phone"
                    placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="身份证号码"
                      prop="idCard">
          <el-input v-model="form.idCard"
                    placeholder="请输入身份证号码" />
        </el-form-item>
        <el-form-item label="当前住址"
                      prop="addressArea">
          <v-distpicker :province.sync="address.provincial"
                        :city.sync="address.citie"
                        :area.sync="address.countie"
                        :code.sync="address.code"></v-distpicker>
          <el-input v-model="addressArea"
                    v-show="false"
                    placeholder="请输入住址的省市区" />
          <el-input class="VerticalMargin"
                    v-model="form.addressDetail"
                    type="textarea"
                    placeholder="请输入现住详址" />
        </el-form-item>
        <el-form-item label="工作地点"
                      prop="addressJobArea">
          <v-distpicker :province.sync="addressJob.provincial"
                        :city.sync="addressJob.citie"
                        :area.sync="addressJob.countie"
                        :code.sync="addressJob.code"></v-distpicker>
          <el-input v-model="addressJobArea"
                    v-show="false"
                    placeholder="请输入工作地点的省市区" />
          <el-input class="VerticalMargin"
                    v-model="form.addressJobDetail"
                    type="textarea"
                    placeholder="请输入工作地点详址" />
        </el-form-item>
        <el-form-item label="是否有防疫绿码">
          <el-radio-group v-model="form.epidemicGreenCode">
            <el-radio v-for="dict in epidemicGreenCodeOptions"
                      :key="dict.dictLabel"
                      :label="dict.dictLabel">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否合租">
          <el-radio-group v-model="form.roommates">
            <el-radio v-for="dict in roommatesOptions"
                      :key="dict.dictLabel"
                      :label="dict.dictLabel">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="合租/同住人员是否有疑似症状">
          <el-radio-group v-model="form.roommatesSuspectedSymptoms">
            <el-radio v-for="dict in roommatesSuspectedSymptomsOptions"
                      :key="dict.dictLabel"
                      :label="dict.dictLabel">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="合租/同住人员是否有病患接触史">
          <el-radio-group v-model="form.roommatesPatientExposureHistory">
            <el-radio v-for="dict in roommatesPatientExposureHistoryOptions"
                      :key="dict.dictLabel"
                      :label="dict.dictLabel">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="14天内是否离开本市">
          <el-radio-group v-model="form.leaveLocal">
            <el-radio v-for="dict in leaveLocalOptions"
                      :key="dict.dictLabel"
                      :label="dict.dictLabel">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
          <el-button type="primary"
                     v-if="form.leaveLocal==='是'"
                     icon="el-icon-plus"
                     size="mini"
                     @click="handleAdd_leaveLocal">新增记录</el-button>
        </el-form-item>
        <el-form-item label="新增14天内出行信息"
                      v-if="form.leaveLocal==='是' && open_form_leaveInformation">
          <!-- 到达城市  到达日期 交通工具类型 交通工具车次  离开日期 交通工具 交通工具类型 交通工具车次-->
          <el-form ref="form_leaveInformation"
                   :model="form_leaveInformation"
                   :rules="rules_leaveInformation"
                   label-width="100px">
            <el-form-item label="到达城市"
                          prop="arriveCityArea">
              <el-input v-show="false"
                        v-model="arriveCityArea"
                        placeholder="请输入到达城市" />
              <v-distpicker :province.sync="arriveCity.provincial"
                            :city.sync="arriveCity.citie"
                            :area.sync="arriveCity.countie"
                            :code.sync="arriveCity.code"></v-distpicker>
            </el-form-item>
            <el-form-item label="到达日期"
                          prop="arriveData">
              <el-date-picker clearable
                              size="small"
                              style="width: 200px"
                              v-model="form_leaveInformation.arriveData"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="选择到达日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="交通工具类型"
                          prop="arriveTransportationTypes">
              <el-checkbox-group v-model="form_leaveInformation.arriveTransportationTypes">
                <el-checkbox v-for="dict in transportationTypesOptions"
                             :label="dict.dictLabel"></el-checkbox>
              </el-checkbox-group>
            </el-form-item>
            <el-form-item label="交通工具车次"
                          prop="arriveTransportationValues">
              <el-input class="VerticalMargin"
                        v-model="form_leaveInformation.arriveTransportationValues"
                        placeholder="请输入交通工具车次,多个交通工具用空格隔开" />
            </el-form-item>
            <el-form-item label="离开日期"
                          prop="leaveData">
              <el-date-picker clearable
                              size="small"
                              style="width: 200px"
                              v-model="form_leaveInformation.leaveData"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="选择离开日期">
              </el-date-picker>
            </el-form-item>
            <div class="dialog-footer">
              <el-button type="primary"
                         @click="submitForm_leaveInformation">确 定</el-button>
              <el-button @click="cancel_leaveInformation">取 消</el-button>
            </div>
          </el-form>
        </el-form-item>
        <el-form-item label="14天内出行信息"
                      v-if="form.leaveLocal==='是'"
                      prop="leaveInformation">
          <el-table stripe
                    :show-header="false"
                    :data="form.leaveInformation">
            <el-table-column label="简要信息"
                             fit
                             align="center"
                             class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-row class="textLeft HorizontalMargin">
                  <span>{{getShowAddressInfo(scope.row)}}</span>
                </el-row>
              </template>
            </el-table-column>
            <el-table-column label="操作"
                             width="55"
                             align="center"
                             class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button size="mini"
                           type="text"
                           icon="el-icon-delete"
                           @click="handleDelete_leaveLocal(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
        <el-form-item label="14天内是否去过中高风险区">
          <el-radio-group v-model="form.havaBeenRiskArea">
            <el-radio v-for="dict in havaBeenRiskAreaOptions"
                      :key="dict.dictLabel"
                      :label="dict.dictLabel">{{dict.dictLabel}}</el-radio>
          </el-radio-group>

        </el-form-item>
        <el-form-item label="14天内去过的中高风险区"
                      v-if="form.havaBeenRiskArea==='是'"
                      prop="havaBeenRiskAreaInformation">
          <el-select v-model="form.havaBeenRiskAreaInformation"
                     multiple
                     filterable
                     clearable
                     placeholder="请选择去过的中高风险区"
                     style="width:280px;">
            <el-option-group v-for="group in beenRiskAreaInformationGroup"
                             :key="group.value"
                             :label="group.label">
              <el-option v-for="item in group.options"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
              </el-option>
            </el-option-group>
          </el-select>
        </el-form-item>
        <el-form-item label="14天内同住人员是否去过中高风险区">
          <el-radio-group v-model="form.roommatesHavaBeenRiskArea">
            <el-radio v-for="dict in roommatesHavaBeenRiskAreaOptions"
                      :key="dict.dictLabel"
                      :label="dict.dictLabel">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="同住人员14天内去过的中高风险区"
                      v-if="form.roommatesHavaBeenRiskArea==='是'"
                      prop="roommatesHavaBeenRiskAreaInformation">
          <el-select v-model="form.roommatesHavaBeenRiskAreaInformation"
                     multiple
                     filterable
                     clearable
                     placeholder="请选择去过的中高风险区"
                     style="width:280px;">
            <el-option-group v-for="group in beenRiskAreaInformationGroup"
                             :key="group.value"
                             :label="group.label">
              <el-option v-for="item in group.options"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
              </el-option>
            </el-option-group>
          </el-select>
          <!-- <el-input v-model="form.roommatesHavaBeenRiskAreaInformation"
                    type="textarea"
                    placeholder="请输入内容" /> -->
        </el-form-item>
        <el-form-item label="是否14天内回国">
          <el-radio-group v-model="form.backHome">
            <el-radio v-for="dict in backHomeOptions"
                      :key="dict.dictLabel"
                      :label="dict.dictLabel">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="同住人员是否14天内回国">
          <el-radio-group v-model="form.roommatesBackHome">
            <el-radio v-for="dict in roommatesBackHomeOptions"
                      :key="dict.dictLabel"
                      :label="dict.dictLabel">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否被要求医学观察">
          <el-radio-group v-model="form.medicalWatch">
            <el-radio v-for="dict in medicalWatchOptions"
                      :key="dict.dictLabel"
                      :label="dict.dictLabel">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否做过核酸检测  ">
          <el-radio-group v-model="form.nucleicAcidTesting">
            <el-radio v-for="dict in nucleicAcidTestingOptions"
                      :key="dict.dictLabel"
                      :label="dict.dictLabel">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否已经注射第一针疫苗">
          <el-radio-group v-model="form.injectionVaccineOne">
            <el-radio v-for="dict in injectionVaccineOneOptions"
                      :key="dict.dictLabel"
                      :label="dict.dictLabel">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="第一针疫苗 注射时间"
                      v-if="form.injectionVaccineOne==='是'"
                      prop="injectionVaccineOneTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.injectionVaccineOneTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择第一针疫苗 注射时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否已经注射第二针疫苗">
          <el-radio-group v-model="form.injectionVaccineTwo">
            <el-radio v-for="dict in injectionVaccineTwoOptions"
                      :key="dict.dictLabel"
                      :label="dict.dictLabel">{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="第二针疫苗 注射时间"
                      v-if="form.injectionVaccineTwo==='是'"
                      prop="injectionVaccineTwoTime">
          <el-date-picker clearable
                          size="small"
                          style="width: 200px"
                          v-model="form.injectionVaccineTwoTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择第二针疫苗 注射时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注"
                      prop="remark">
          <el-input v-model="form.remark"
                    type="textarea"
                    placeholder="请输入内容" />
        </el-form-item>

      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button type="primary"
                   @click="submitForm">确 定</el-button>
        <el-button @click="cancel">重 置</el-button>
      </div>
      <!-- </el-dialog> -->
    </div>
  </div>
</template>

<script>
import { listPerson_epidemic_state_record, getPerson_epidemic_state_record, delPerson_epidemic_state_record, addPerson_epidemic_state_record, updatePerson_epidemic_state_record, exportPerson_epidemic_state_record } from "@/api/epidemic/person_epidemic_state_record";
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import VDistpicker from '@/components/address_distpicke'
export default {
  name: "Person_epidemic_state_record",
  components: { VDistpicker },
  data () {
    return {
      // 疫情信息收集表格数据
      person_epidemic_state_recordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 性别字典
      sexOptions: [],
      // 是否有防疫绿码字典
      epidemicGreenCodeOptions: [],
      // 是否合租字典
      roommatesOptions: [],
      // 合租/同住人员是否有疑似症状字典
      roommatesSuspectedSymptomsOptions: [],
      // 合租/同住人员是否有病患接触史字典
      roommatesPatientExposureHistoryOptions: [],
      // 14天内是否离开本市字典
      leaveLocalOptions: [],
      // 14天内是否去过中高风险区字典
      havaBeenRiskAreaOptions: [],
      // 14天内同住人员是否去过中高风险区字典
      roommatesHavaBeenRiskAreaOptions: [],
      // 是否14天内回国字典
      backHomeOptions: [],
      // 同住人员是否14天内回国字典
      roommatesBackHomeOptions: [],
      // 是否被要求医学观察字典
      medicalWatchOptions: [],
      // 是否做过核酸检测  字典
      nucleicAcidTestingOptions: [],
      // 是否已经注射第一针疫苗字典
      injectionVaccineOneOptions: [],
      // 是否已经注射第二针疫苗字典
      injectionVaccineTwoOptions: [],
      //交通工具类型
      transportationTypesOptions: [],
      //中高风险区
      beenRiskAreaInformationOptions: [],
      //中高风险区选项分组
      beenRiskAreaInformationGroup: [],
      // 社区
      communityOptions: [],
      //小区列表全集
      allCommunityChildOptions: [],
      //小区列表
      communityChildOptions: [],
      //小区列表
      communityChildOptions2: [],
      // 表单参数
      form: {},
      //到达城市表单
      form_leaveInformation: {},
      //到达城市表单是否显示
      open_form_leaveInformation: false,
      //住址
      address: {
        provincial: null,
        citie: null,
        countie: null,
        code: null
      },
      //工作地址
      addressJob: {
        provincial: null,
        citie: null,
        countie: null,
        code: null
      },
      // 到达地址
      arriveCity: {
        provincial: null,
        citie: null,
        countie: null,
        code: null
      },
      // 表单校验
      rules: {
        name: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        sex: [
          { required: true, message: "性别不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "手机号不能为空", trigger: "blur" },
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur",
          },
        ],
        idCard: [
          { required: true, message: "身份证号码不能为空", trigger: "blur" },
          {
            pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
            message: "你的身份证格式不正确",
          },
        ],
        addressDetail: [
          { required: true, message: "现住详址不能为空", trigger: "blur" }
        ],
        epidemicGreenCode: [
          { required: true, message: "是否有防疫绿码不能为空", trigger: "blur" }
        ],
        roommates: [
          { required: true, message: "是否合租不能为空", trigger: "blur" }
        ],
        roommatesSuspectedSymptoms: [
          { required: true, message: "合租/同住人员是否有疑似症状不能为空", trigger: "blur" }
        ],
        roommatesPatientExposureHistory: [
          { required: true, message: "合租/同住人员是否有病患接触史不能为空", trigger: "blur" }
        ],
        leaveLocal: [
          { required: true, message: "14天内是否离开本市不能为空", trigger: "blur" }
        ],
        leaveInformation: [
          { required: true, message: "14天内出行信息不能为空", trigger: "blur" }
        ],
        havaBeenRiskArea: [
          { required: true, message: "14天内是否去过中高风险区不能为空", trigger: "blur" }
        ],
        havaBeenRiskAreaInformation: [
          { required: true, message: "14天内去过中高风险区的记录不能为空", trigger: "blur" }
        ],
        roommatesHavaBeenRiskArea: [
          { required: true, message: "14天内同住人员是否去过中高风险区不能为空", trigger: "blur" }
        ],
        roommatesHavaBeenRiskAreaInformation: [
          { required: true, message: "同住人员14天内去过中高风险区的记录不能为空", trigger: "blur" }
        ],
        backHome: [
          { required: true, message: "是否14天内回国不能为空", trigger: "blur" }
        ],
        roommatesBackHome: [
          { required: true, message: "同住人员是否14天内回国不能为空", trigger: "blur" }
        ],
        medicalWatch: [
          { required: true, message: "是否被要求医学观察不能为空", trigger: "blur" }
        ],
        nucleicAcidTesting: [
          { required: true, message: "是否做过核酸检测  不能为空", trigger: "blur" }
        ],
        injectionVaccineOne: [
          { required: true, message: "是否已经注射第一针疫苗不能为空", trigger: "blur" }
        ],
        injectionVaccineOneTime: [
          { required: true, message: "第一针疫苗 注射时间不能为空", trigger: "blur" }
        ],
        injectionVaccineTwo: [
          { required: true, message: "是否已经注射第二针疫苗不能为空", trigger: "blur" }
        ],
        injectionVaccineTwoTime: [
          { required: true, message: "第二针疫苗 注射时间不能为空", trigger: "blur" }
        ],
        addressArea: [
          { required: true, message: "请选择省市区", trigger: "blur" }
        ],
        addressJobArea: [
          { required: true, message: "请选择省市区", trigger: "blur" }
        ],
      },
      // 到达城市表单校验
      rules_leaveInformation: {
        arriveCityArea: [
          { required: true, message: "到达城市不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created () {
    this.getCommunityDictory()
    // this.getList();
    this.getDicts("sys_user_sex").then(response => {
      this.sexOptions = response.data;
    });
    this.getDicts("base_epidemic_green_code").then(response => {
      this.epidemicGreenCodeOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.roommatesOptions = response.data;
      this.roommatesSuspectedSymptomsOptions = response.data;
      this.roommatesPatientExposureHistoryOptions = response.data;
      this.leaveLocalOptions = response.data;
      this.havaBeenRiskAreaOptions = response.data;
      this.roommatesHavaBeenRiskAreaOptions = response.data;
      this.backHomeOptions = response.data;
      this.roommatesBackHomeOptions = response.data;
      this.medicalWatchOptions = response.data;
      this.injectionVaccineOneOptions = response.data;
      this.injectionVaccineTwoOptions = response.data;
    });
    this.getDicts("yq_nucleic_acid_amplification_testing").then(response => {
      this.nucleicAcidTestingOptions = response.data;
    });
    this.getDicts("base_transportation_type").then(response => {
      this.transportationTypesOptions = response.data;
    });
    this.getDicts("yq_medium_and_high_risk_area").then(response => {
      this.beenRiskAreaInformationOptions = response.data;
      this.getBeenRiskAreaInformationGroup(this.beenRiskAreaInformationOptions)
    });
  },
  mounted () {
    // const isMobile = this.$_isMobile()
    // if (isMobile) {

    // }
    this.addressJobArea = ''
    this.reset_LeaveInformation()
  },
  methods: {
    getBeenRiskAreaInformationGroup (beenRiskAreaInformationList) {
      this.beenRiskAreaInformationGroup = []
      if (beenRiskAreaInformationList && beenRiskAreaInformationList.length > 0) {
        let areaGroup = []
        for (let index = 0; index < beenRiskAreaInformationList.length; index++) {
          const element = beenRiskAreaInformationList[index];
          let area = this.$cf.getAddressArea(element.dictLabel)
          let sheng = area[0]
          if (area && area.length > 0) {
            let groupIndex = this.$cf.findIndex(areaGroup, 'label', sheng)
            if (groupIndex < 0) {
              //还没有这个省
              areaGroup.push({
                label: sheng,
                options: []
              })
              groupIndex = areaGroup.length - 1
            }
            let label = element.dictLabel.substr(area[0].length, element.dictLabel.length - area[0].length)
            if (!(label && label.length > 0)) label = element.dictLabel
            areaGroup[groupIndex].options.push({
              value: element.dictLabel,
              label: label
            })
          }
        }
        this.beenRiskAreaInformationGroup = areaGroup
      }
    },
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
    getCommunityChildDictory2 (communityId) {
      this.communityChildOptions2 = []
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId
      }
      listCommunity_child(param).then(response => {
        this.communityChildOptions2 = response.rows;
      });
    },
    // 取消按钮
    cancel () {
      this.reset()
    },
    // 表单重置
    reset () {
      this.form = {
        id: null,
        name: null,
        sex: '男',
        phone: null,
        idCard: null,
        addressDetail: null,
        addressJobDetail: null,
        communityChildId: null,
        communityChildName: null,
        epidemicGreenCode: "有",
        roommates: "否",
        roommatesSuspectedSymptoms: "否",
        roommatesPatientExposureHistory: "否",
        leaveLocal: "否",
        leaveInformation: [],
        havaBeenRiskArea: "否",
        havaBeenRiskAreaInformation: [],//去过的中高风险区
        roommatesHavaBeenRiskArea: "否",
        roommatesHavaBeenRiskAreaInformation: [],//去过的中高风险区
        backHome: "否",
        roommatesBackHome: "否",
        medicalWatch: "否",
        nucleicAcidTesting: "未检测",
        injectionVaccineOne: "否",
        injectionVaccineOneTime: null,
        injectionVaccineTwo: "否",
        injectionVaccineTwoTime: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        remark: null,
        addressArea: null,
        addressJobArea: null,
        communityId: null,
        communityName: null
      };
      this.resetForm("form");
      this.addressJobArea = ''
      this.reset_LeaveInformation()
    },
    // 到达城市 表单重置
    reset_LeaveInformation () {
      this.form_leaveInformation = {
        arriveCityArea: null,
        arriveData: null,
        arriveTransportationTypes: [],//到达时乘坐的交通工具类型
        arriveTransportationValues: null,//到达时乘坐的交通工具车次
        leaveData: null,
        leaveTransportationTypes: null,//离开时乘坐的交通工具类型
        leaveTransportationValues: null,//离开时乘坐的交通工具车次
      }
    },
    //新增一条出行地点
    handleAdd_leaveLocal () {
      this.reset_LeaveInformation()
      this.open_form_leaveInformation = true
    },
    //删除一条通行地点
    handleDelete_leaveLocal (row) {
      if (this.form && this.form.leaveInformation && this.form.leaveInformation.length > 0) {
        this.form.leaveInformation.remove(row)
      }
    },
    //提交一条出行地点
    submitForm_leaveInformation () {
      let self = this
      this.$refs["form_leaveInformation"].validate(valid => {
        if (valid) {
          // form.leaveInformation=[] form_leaveInformation
          self.form.leaveInformation.push({
            id: self.$cf.Guid(),
            arriveCityArea: self.form_leaveInformation.arriveCityArea,
            arriveData: self.form_leaveInformation.arriveData,
            arriveTransportationTypes: self.form_leaveInformation.arriveTransportationTypes,//到达时乘坐的交通工具类型
            arriveTransportationValues: self.form_leaveInformation.arriveTransportationValues,//到达时乘坐的交通工具车次
            leaveData: self.form_leaveInformation.leaveData,
            leaveTransportationTypes: self.form_leaveInformation.leaveTransportationTypes,//离开时乘坐的交通工具类型
            leaveTransportationValues: self.form_leaveInformation.leaveTransportationValues,//离开时乘坐的交通工具车次
          })
          this.open_form_leaveInformation = false
        }
      })
    },
    //取消新增出行地点
    cancel_leaveInformation () {
      this.open_form_leaveInformation = false
    },
    //展示出行地点信息
    getShowAddressInfo (row) {
      if (row && row.arriveCityArea) {
        return row.arriveCityArea + ' ' + row.arriveData + ' -- ' + row.leaveData
      } else return ''
    },
    getLeaveInformationStringFromList () {

    },
    //将展示的字段转换成数据库可以存储的
    getDBEpidemicInfoFromShow (form) {
      if (form.leaveInformation && form.leaveInformation.length > 0)
        form.leaveInformation = JSON.stringify(form.leaveInformation)
      form.havaBeenRiskAreaInformation = this.$cf.getStringFromList(form.havaBeenRiskAreaInformation)
      form.roommatesHavaBeenRiskAreaInformation = this.$cf.getStringFromList(form.roommatesHavaBeenRiskAreaInformation)
    },
    /** 提交按钮 */
    submitForm () {
      let self = this
      this.$refs["form"].validate(valid => {
        if (valid) {
          self.getDBEpidemicInfoFromShow(self.form)
          if (self.form.id != null) {
            updatePerson_epidemic_state_record(self.form).then(response => {
              if (response.code === 200) {
                self.msgSuccess("修改成功");
                self.open = false;
                self.getList();
              }
            });
          } else {
            addPerson_epidemic_state_record(self.form).then(response => {
              if (response.code === 200) {
                self.msgSuccess("新增成功");
                self.open = false;
                self.getList();
              }
            });
          }
        }
      });
    }
  },
  computed: {
    addressArea: {
      // getter
      get: function () {
        if (this.address && this.address.provincial && this.address.citie && this.address.countie) {
          this.form.addressArea = this.address.provincial + this.address.citie + this.address.countie

        } else {
          this.form.addressArea = null
        }
        return this.form.addressArea
      },
      // setter
      set: function (newValue) {
        let areas = this.$cf.getAddressArea(newValue)
        if (areas && areas.length > 2) {
          this.address.provincial = areas[0]
          this.address.citie = areas[1]
          this.address.countie = areas[2]
        }
      }

    },
    addressJobArea: {
      // getter
      get: function () {
        if (this.addressJob && this.addressJob.provincial && this.addressJob.citie && this.addressJob.countie) {
          this.form.addressJobArea = this.addressJob.provincial + this.addressJob.citie + this.addressJob.countie
        } else {
          this.form.addressJobArea = null
        }
        return this.form.addressJobArea
      },
      // setter
      set: function (newValue) {
        let areas = this.$cf.getAddressArea(newValue)
        if (areas && areas.length > 2) {
          this.addressJob.provincial = areas[0]
          this.addressJob.citie = areas[1]
          this.addressJob.countie = areas[2]
        }
      }
    },
    arriveCityArea () {
      if (this.arriveCity && this.arriveCity.provincial && this.arriveCity.citie && this.arriveCity.countie) {
        this.form_leaveInformation.arriveCityArea = this.arriveCity.provincial + this.arriveCity.citie + this.arriveCity.countie
      } else {
        this.form_leaveInformation.arriveCityArea = null
      }
      return this.form_leaveInformation.arriveCityArea
    },
  }
};
</script>
<style scoped>
.main {
  width: 800px;
  height: 100%;
  margin-bottom: 20px;
}
</style>
