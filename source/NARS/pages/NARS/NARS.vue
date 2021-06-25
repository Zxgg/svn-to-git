<template>
	<view class="main">
		<bjx-form labelType="inline" :rules="rules" labelWidth="100" :form="form" ref="form" @submit="formSubmit">
			<view class="community" style="margin-bottom: 30rpx; margin-left: 20rpx;">
				社区
				<xfl-select :list="communityList" :clearable="false" :showItemNum="5" :listShow="false" :isCanInput="true" name="communityId"
				 @change="communityChange" :style_Container="'height: 25px; width:70%; font-size: 14px; position:absolute; right:5%;'"
				 :initValue="initcommunityIdValue" :selectHideType="'hideAll'" value='value' />
			</view>
			<view class="village" style="margin-bottom: 30rpx; margin-left: 20rpx;">
				所属小区
				<xfl-select :list="communityChildList" :clearable="false" :showItemNum="5" :listShow="false" :isCanInput="true"
				 name="communityChildId" @change="villageChange" :style_Container="'height: 25px; width:70%; font-size: 14px; position:absolute; right:5%;'"
				 :initValue="initcommunityChildIdValue" :selectHideType="'hideAll'" value='value' />
			</view>
			<view class="name" style="margin-left: 20rpx; margin-right: 20rpx;">
				<bjx-form-item labelType="block" label="姓名" prop="name" :label-right="form.name.length+'/20'">
					<input v-model="form.name" class="input" name="name" placeholder="姓名" />
				</bjx-form-item>
			</view>
			<view class="radio_sex" style="margin-left: 20rpx; margin-right: 20rpx;">
				性别
				<radio-group @change="sex_Change"
							 name="sex">
				           <label v-for="dict in sexOptions" :key="dict.dictValue">
				               <radio :value="dict.dictLabel" :checked="dict.dictLabel==form.sex" 
												style="transform:scale(0.7)"/>{{dict.dictLabel}}
				           </label>
				 </radio-group>
			</view>
			<view class="phone" style="margin-left: 20rpx; margin-right: 20rpx;">
				<bjx-form-item labelType="block" label="手机号码" prop="phone" :label-right="form.phone.length+'/11'">
					<input v-model="form.phone" class="input" name="phone" placeholder="手机号码" type="number" />
				</bjx-form-item>
			</view>
			<view class="id" style="margin-left: 20rpx; margin-right: 20rpx;">
				<bjx-form-item labelType="block" label="身份证号" :label-right="form.idCard.length+'/18'" prop='idCard'>
					<input v-model="form.idCard" class="input" name="idCard" placeholder="身份证号" type="number" />
				</bjx-form-item>
			</view>
			<view class="address" style="margin-left: 20rpx; margin-right: 20rpx;">
				<bjx-form-item labelType="block" label="当前住址" prop='addressArea'>
					<pickerAddress name="addressArea" @change="change">{{txt}}</pickerAddress>
				</bjx-form-item>
				<bjx-form-item labelType="block" label='居住详址' prop='addressDetail'>
					<textarea name="addressDetail" v-model="form.addressDetail" />
					</bjx-form-item>
		</view>
		<view class="workplace" style="margin-left: 20rpx; margin-right: 20rpx;">
			<bjx-form-item labelType="block" label="工作地点" prop='addressJobArea'> 
			<pickerAddress name="addressJobArea" @change="changeJob">{{txt_job}}</pickerAddress>
			</bjx-form-item>
			<bjx-form-item labelType="block"  label='工作详址' prop='addressJobDetail'> 
			<textarea name="addressJobDetail" v-model="form.addressJobDetail" />
			</bjx-form-item>
		</view>
		<view class="dcheck" style="margin-top: 20rpx;">
			是否是防疫绿码
			<radio-group @change="epidemicGreenCode_change"
						 name="epidemicGreenCode">
			           <label v-for="dict in epidemicGreenCodeOptions" :key="dict.dictValue">
			               <radio :value="dict.dictLabel" :checked="dict.dictLabel==form.epidemicGreenCode" 
											style="transform:scale(0.7)"/>{{dict.dictLabel}}
			           </label>
			 </radio-group>
		</view>
		<view class="dcheck">
			是否合租
			<radio-group @change="roommates_change"
						 name="roommates">
			           <label v-for="dict in roommatesOptions" :key="dict.dictValue">
			               <radio :value="dict.dictLabel" :checked="dict.dictLabel==form.roommates" 
											style="transform:scale(0.7)"/>{{dict.dictLabel}}
			           </label>
			 </radio-group>
		</view>
		<view class="dcheck">
			合租/同住人员是否是疑似症状
			<radio-group @change="roommatesSuspectedSymptoms_change"
						 name="roommatesSuspectedSymptoms">
			           <label v-for="dict in roommatesSuspectedSymptomsOptions" :key="dict.dictValue">
			               <radio :value="dict.dictLabel" :checked="dict.dictLabel==form.roommatesSuspectedSymptoms" 
											style="transform:scale(0.7)"/>{{dict.dictLabel}}
			           </label>
			 </radio-group>
		</view>
		<view class="dcheck">
			合租/同住人员是否是病患接触史
			<radio-group @change="roommatesPatientExposureHistory_change"
						 name="roommatesPatientExposureHistory">
			           <label v-for="dict in roommatesSuspectedSymptomsOptions" :key="dict.dictValue">
			               <radio :value="dict.dictLabel" :checked="dict.dictLabel==form.roommatesPatientExposureHistory" 
											style="transform:scale(0.7)"/>{{dict.dictLabel}}
			           </label>
			 </radio-group>
		</view>
		<view class="dcheck">
			14天内是否离开本市
			<view class="leaveLocal">
				 <radio-group @change="leaveLocal_change" name='leaveLocal'>
				            <label v-for="dict in leaveLocalOptions" :key="dict.dictValue">
				                <radio :value="dict.dictLabel" :checked="dict.dictLabel==form.leaveLocal" 
								style="transform:scale(0.7)"/>{{dict.dictLabel}}
				            </label>
				  </radio-group>
				<button type="primary" size="mini"
				v-if="form.leaveLocal == '是'"
				@click="handleAdd_leaveLocal">+新增记录</button>
			</view>
			<bjx-form labelType="inline"  class="leaveLocal_add"
								ref="form_leaveInformation"
			                   :form="form_leaveInformation"
							   v-if="form.leaveLocal=='是' && open_form_leaveInformation"
							   :rules="rules_leaveInformation">
				新增14天内出行信息
					<bjx-form-item labelType="inline" label="到达城市" prop='arriveCityArea'>
						<pickerAddress  name="arriveCityArea" 
						@change="arrivalChange">{{txt_arrival}}</pickerAddress>
					</bjx-form-item>
					<picker mode="date" :value="date" :start="startDate" :end="endDate" @change="bindDateChange" class="arrvieDate">
						到达日期
						<view class="arrivedate" v-model="form_leaveInformation.arriveData">{{date}}</view>
					</picker>
					<checkbox-group @change="arriveTransportationTypes_Change">
						<view class="arriveTransportationTypes">
							交通工具类型
						</view>
					                <label v-for="dict in transportationTypesOptions" :key="dict.dictLabel">
					                        <checkbox :value="dict.dictLabel" 
											:checked="dict.dictLabel==form.arriveTransportationTypes"
											style="transform:scale(0.8)">{{dict.dictLabel}}</checkbox>
					                </label>
					            </checkbox-group>
					<view class="arriveTransportationValues">
						交通工具车次
						<input class="jtcc"
						 v-model="form_leaveInformation.arriveTransportationValues"
						 placeholder="请输入交通工具车次,多个交通工具用空格隔开" 
						 />
					</view>    
					<picker mode="date" :value="date" 
					:start="startDate" :end="endDate" 
					@change="leaveDateChange">
						离开日期
						<view class="leavedate" v-model="form_leaveInformation.leaveData">{{leavedate}}</view>
					</picker>
					<view class="btn">
						<button type="primary"  @click="submitForm_leaveInformation">确定</button>
						<button type="default"  @click="cancel_leaveInformation">取消</button>
					</view>
			</bjx-form>
			<bjx-form labelType="inline"
				:form="form"
				labelWidth="100" 
				class="leaveLocal_show"
				v-if="form.leaveLocal==='是'"
				:rules="rules">
					<bjx-form-item
						labelType="block" 
						label="14天内出行信息"
						prop="leaveInformation"
						>
					<view class="leaveInformation_show"   v-for="item in form.leaveInformation" 
					v-if="form.leaveInformation && form.leaveInformation.length > 0">
						<view class="">
							{{item.arriveCityArea}}
						</view>
						<view class="">
							{{item.arriveData}}
						</view>
						<view>
						  — {{item.leaveData}}({{item.arriveTransportationValues}})
						</view>
						<view class="del" @click="remove"  >
							x
						</view>
					</view>
					</bjx-form-item>
			</bjx-form>
		</view>
		<view class="dcheck">
			14天内是否去过中高风险区
			<radio-group @change="havaBeenRiskArea_change"
						 name="havaBeenRiskArea">
			           <label v-for="dict in havaBeenRiskAreaOptions" :key="dict.dictValue">
			               <radio :value="dict.dictLabel" :checked="dict.dictLabel==form.havaBeenRiskArea" 
											style="transform:scale(0.7)"/>{{dict.dictLabel}}
			           </label>
			 </radio-group>
			 <bjx-form
			   :form='form'
			   v-if="form.havaBeenRiskArea === '是'"
			   :rules="rules"
			   labelType="block" >
			  <bjx-form-item class="riskarea"
			  label='14天内去过的中高风险区'
			  prop='havaBeenRiskArea'>
			 	 <ld-select :multiple="true" :list="options"
			 	        list-key="dictLabel" value-key="dictLabel"
			 	        placeholder="请选择去过的中高风险区"
			 	        clearable
			 	        v-model="form.havaBeenRiskAreaInformation"
			 	        @change="selectChange"></ld-select>
				</bjx-form-item>
				</bjx-form>
		</view>
		<view class="dcheck">
			14天内同住人员是否去过中高风险区
			<radio-group @change="roommatesHavaBeenRiskArea_change"
						 name="roommatesHavaBeenRiskArea">
			           <label v-for="dict in roommatesHavaBeenRiskAreaOptions" :key="dict.dictValue">
			               <radio :value="dict.dictLabel" :checked="dict.dictLabel==form.roommatesHavaBeenRiskArea" 
											style="transform:scale(0.7)"/>{{dict.dictLabel}}
			           </label>
			 </radio-group>
			<bjx-form
				:form='form'
			    v-if="form.roommatesHavaBeenRiskArea === '是'"
			   :rules="rules"
			   labelType="block">
			   <bjx-form-item class="riskarea"
			   label='同住人员14天内去过的中高风险区'
			   prop='roommatesHavaBeenRiskArea'>
			   <ld-select :multiple="true" :list="options"
			          list-key="dictLabel" value-key="dictLabel"
			          placeholder="请选择去过的中高风险区"
			          clearable
			          v-model="form.roommatesHavaBeenRiskAreaInformation"
			          @change="selectChange2"></ld-select>
			 </bjx-form-item>
			 </bjx-form>
		</view>
		<view class="dcheck">
			是否14天内回国
				<radio-group @change="backHome_change"
							 name="backHome">
				           <label v-for="dict in backHomeOptions" :key="dict.dictValue">
				               <radio :value="dict.dictLabel" :checked="dict.dictLabel==form.backHome" 
												style="transform:scale(0.7)"/>{{dict.dictLabel}}
				           </label>
				 </radio-group>
		</view>
		<view class="dcheck">
			同住人员是否14天内回国
			<radio-group @change="roommatesBackHome_change"
						 name="roommatesBackHome">
			           <label v-for="dict in roommatesBackHomeOptions" :key="dict.dictValue">
			               <radio :value="dict.dictLabel" :checked="dict.dictLabel==form.roommatesBackHome" 
											style="transform:scale(0.7)"/>{{dict.dictLabel}}
			           </label>
			 </radio-group>
		</view>
		<view class="dcheck">
			是否被要求医学观察
			<radio-group @change="medicalWatch_change"
						 name="medicalWatch">
			           <label v-for="dict in medicalWatchOptions" :key="dict.dictValue">
			               <radio :value="dict.dictLabel" :checked="dict.dictLabel==form.medicalWatch" 
											style="transform:scale(0.7)"/>{{dict.dictLabel}}
			           </label>
			 </radio-group>
		</view>
		<view class="dcheck">
			是否做过核酸检测
			<radio-group @change="nucleicAcidTesting_change"
						 name="nucleicAcidTesting">
			           <label v-for="dict in nucleicAcidTestingOptions" :key="dict.dictValue">
			               <radio :value="dict.dictLabel" :checked="dict.dictLabel==form.nucleicAcidTesting" 
											style="transform:scale(0.7)"/>{{dict.dictLabel}}
			           </label>
			 </radio-group>
		</view>
		<view  class="vaccinetwo">
			是否已经注射第一针疫苗
			<radio-group @change="injectionVaccineOne_change"
						 name="injectionVaccineOne">
			           <label v-for="dict in injectionVaccineOneOptions" :key="dict.dictValue">
			               <radio :value="dict.dictLabel" :checked="dict.dictLabel==form.injectionVaccineOne" 
											style="transform:scale(0.7)"/>{{dict.dictLabel}}
			           </label>
			 </radio-group>
			 <bjx-form
			   :form="form"
			   v-if="form.injectionVaccineOne === '是'"
			   :rules="rules"
			   labelType="inline">
			  <bjx-form-item
			  style="font-size: 25rpx;"
			  label='第一针疫苗 注射时间'
			  prop='injectionVaccineOne'>
				<picker mode="date" v-if="form.injectionVaccineOne === '是'"
				:value="injectionVaccineOneTime" :start="startDate" :end="endDate" 
				@change="oneDateChange">
					<view style="text-align: center;">{{injectionVaccineOneTime}}</view>
				</picker>
				</bjx-form-item>
				</bjx-form>
		</view>
		<view class="vaccinetwo">
			是否已经注射第二针疫苗
			<radio-group @change="injectionVaccineTwo_change"
						 name="injectionVaccineTwo">
			           <label v-for="dict in injectionVaccineTwoOptions" :key="dict.dictValue">
			               <radio :value="dict.dictLabel" :checked="dict.dictLabel==form.injectionVaccineTwo" 
											style="transform:scale(0.7)"/>{{dict.dictLabel}}
			           </label>
			 </radio-group>
			 <bjx-form
			   :form="form"
			   v-if="form.injectionVaccineTwo === '是'"
			   :rules="rules"
			   labelType="inline">
			  <bjx-form-item 
			  style="font-size: 25rpx;"
			  label='第二针疫苗 注射时间'
			  prop='injectionVaccineTwo'>
			 <picker mode="date"
			 :value="injectionVaccineTwoTime" :start="startDate" :end="endDate" 
			 @change="twoDateChange">
			 	<view style="text-align: center;">{{injectionVaccineTwoTime}}</view>
			 </picker>
			 </bjx-form-item>
			 </bjx-form>
		</view>
		<view class="notebox">
			备注
			<textarea class="note" placeholder="请输入内容" style="margin-top: 15rpx;"/>
		</view>
		<view class="btn">
		<button form-type="submit" >提交</button>
		<button form-type="reset" >重置</button>
		</view>
	</bjx-form>
	</view>
</template>

<script>
	import calendar from '../../components/lx-calendar/lx-calendar.vue'
	import { getDicts,addPerson_epidemic_state_record} from '@/util/api.js'
	import pickerAddress from '../../components/wangding-pickerAddress/wangding-pickerAddress.vue'
	import ldSelect from '@/components/ld-select/ld-select.vue'
	import bjxForm from '@/components/bjx-form/bjx-form.vue'
	import bjxFormItem from '@/components/bjx-form/bjx-form-item.vue'

	export default {
		components: {
			bjxForm,
			bjxFormItem,
			pickerAddress,
			calendar,
			ldSelect 
		},
		data() {
			return {
				dictValue:'',
				options: [],
				indicatorStyle: `height: 50px;`,
				date: '选择到达日期',
				leavedate: '选择离开日期',
				injectionVaccineOneTime:'选择第一针疫苗注射时间',
				injectionVaccineTwoTime:'选择第二针疫苗注射时间',
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
				communityId:null,
				communityChildId:null,
				communityName:null,
				communityChildName:null,
				createBy:null,
				communityList:[],
				isShowSelect: false,
				communityChildList:[],
				addressArea:null,
				arrivalArea:null,
				addressJobArea:null,
				initcommunityIdValue:null,
				initcommunityChildIdValue:null,
				txt: '选择地址',
				txt_job:'选择工作地址',
				txt_arrival:'请选择到达城市',
				//到达城市表单
				form_leaveInformation: {
					arriveCityArea:null,
					arriveData: null,
					arriveTransportationTypes: null,
					arriveTransportationValues: null,
					leaveData:null
				},
				//到达城市表单是否显示
				open_form_leaveInformation: false,
				form: {
					injectionVaccineOneTime:'',
					injectionVaccineTwoTime:'',
					arrivalArea:'',
					addressArea:'',
					addressJobArea:'',
					addressDetail:'',
					addressJobDetail:'',
					communityId: null,
					communityChildId:null,
					communityName:null,
					communityChildName:null,
					riskAreaList:null,
					roomRiskAreaList:null,
					name: '',
					age: '',
					sex:'男',
					phone:'',
					idCard:'',
					createBy:'',
					havaBeenRiskAreaInformation: [],
					roommatesHavaBeenRiskAreaInformation: [],
					epidemicGreenCode:'有',
					roommatesSuspectedSymptoms:'否',
					roommatesPatientExposureHistory:'否',
					leaveLocal:'否',
					havaBeenRiskArea:'否',
					roommatesHavaBeenRiskArea:'否',
					roommatesRiskList:[],
					backHome:'否',
					roommates:'否',
					roommatesBackHome:'否',
					medicalWatch:'否',
					nucleicAcidTesting:'未检测',
					injectionVaccineOne:'否',
					injectionVaccineTwo:'否',
					leaveInformation:[],
				},
				rules: {
					name: {
						required: true,
						rule: 'type:string|length:~,20'
					},
					age: {
						required: true,
						rule: 'type:number|between:0,120'
					},
					sex: {
						required: true,
						// rule: 'type:number|between:0,120'
					},
					phone: {
						required: true,
						rule:'^1(3|4|5|6|7|8|9)d{9}$'
					},
					idCard: {
						required: true,
						rule: '^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$'
					},
					addressArea: {
						required: true
					},
					addressJobArea: {
						required: true
					},
					addressDetail:{
						required: true
					},
					addressJobDetail:{
						required: true
					},
					leaveInformation:{
						required: true
					},
					havaBeenRiskArea:{
						required: true
					},
					injectionVaccineTwo:{
						required: true
					},
					injectionVaccineOne:{
						required: true
					},
					roommatesHavaBeenRiskArea:{
						required: true
					},
				},
				// 到达城市表单校验
				    rules_leaveInformation: {
						arriveCityArea: 
						  { required: true, trigger: "blur" }
						}
			}
		},
		created() {
		//字典
		getDicts("sys_user_sex").then(response => {
			      this.sexOptions = response.data;
			    });
		getDicts("base_epidemic_green_code").then(response => {
				  this.epidemicGreenCodeOptions = response.data;
				});
		getDicts("base_yes_no").then(response => {
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
		getDicts("yq_nucleic_acid_amplification_testing").then(response => {
			  this.nucleicAcidTestingOptions = response.data;
			});
		getDicts("base_transportation_type").then(response => {
		      this.transportationTypesOptions = response.data;
		    });
		getDicts("yq_medium_and_high_risk_area").then(response => {
		      this.beenRiskAreaInformationOptions = response.data;
			  this.options = this.beenRiskAreaInformationOptions
			  })
		},
		computed: {
		        startDate() {
		            return this.getDate('start');
		        },
		        endDate() {
		            return this.getDate('end');
		        },
				data(){return this.binData;}
		    },
		methods: {
			sex_Change(e){
				this.form.sex = e.detail.value
			},
			arriveTransportationTypes_Change(e){
				this.form_leaveInformation.arriveTransportationTypes = e.detail.value
			},
			epidemicGreenCode_change(e){
				this.form.epidemicGreenCode = e.detail.value
			},
			roommates_change(e){
				this.form.roommates = e.detail.value
			},
			roommatesSuspectedSymptoms_change(e){
				this.form.roommatesSuspectedSymptoms = e.detail.value
			},
			roommatesPatientExposureHistory_change(e){
				this.form.roommatesPatientExposureHistory = e.detail.value
			},
			leaveLocal_change(e){
				this.form.leaveLocal = e.detail.value
			},
			havaBeenRiskArea_change(e){
				this.form.havaBeenRiskArea = e.detail.value
			},
			roommatesHavaBeenRiskArea_change(e){
				this.form.roommatesHavaBeenRiskArea = e.detail.value
			},
			backHome_change(e){
				this.form.backHome = e.detail.value
			},
			roommatesBackHome_change(e){
				this.form.roommatesBackHome = e.detail.value
			},
			medicalWatch_change(e){
				this.form.medicalWatch = e.detail.value
			},
			nucleicAcidTesting_change(e){
				this.form.nucleicAcidTesting = e.detail.value
			},
			injectionVaccineOne_change(e){
				this.form.injectionVaccineOne = e.detail.value
			},
			injectionVaccineTwo_change(e){
				this.form.injectionVaccineTwo = e.detail.value
			},
			riskAreaList_change(e){
				this.form.riskAreaList = e.detail.value
			},
			//到达日期，离开日期
			bindDateChange: function(e) {
			            this.date = e.target.value
						this.form_leaveInformation.arriveData=this.date
			        },
			leaveDateChange: function(e) {
			            this.leavedate = e.target.value
						this.form_leaveInformation.leaveData=this.leavedate
			        },
			//疫苗日期
			oneDateChange: function(e) {
			            this.injectionVaccineOneTime = e.target.value
						this.form.injectionVaccineOneTime=this.injectionVaccineOneTime
			        },
			twoDateChange: function(e) {
			            this.injectionVaccineTwoTime = e.target.value
			            this.form.injectionVaccineTwoTime=this.injectionVaccineTwoTime
			        },		
			//下拉框
			arrowDown() {
					this.isShowSelect = !this.isShowSelect;
				  },
			select(dict, index) {
					this.isShowSelect = false;
					this.form.riskAreaList = dict.dictLabel;
				  },
			roomarrowDown() {
					this.isShowSelect = !this.isShowSelect;
				  },
			roomselect(dict, index) {
					this.isShowSelect = false;
					this.form.roomRiskAreaList = dict.dictLabel;
				  },
			communityChange(val){
				// console.log('new:'+val.newVal)
			},
			villageChange(val){
				// console.log('new:'+val.newVal)
			},
			change(data){
			                this.txt = data.data.join('')
							this.form.addressArea=this.txt
			            },
			changeJob(data) {
						    this.txt_job = data.data.join('')
							this.form.addressJobArea=this.txt_job
						},
			arrivalChange(data) {
					        this.txt_arrival = data.data.join('')
							this.form_leaveInformation.arriveCityArea=this.txt_arrival
					    },
			selectChange(val){
			                this.form.havaBeenRiskAreaInformation = val
							console.log(this.form.havaBeenRiskAreaInformation,'this.form.havaBeenRiskAreaInformation')
			            },
			selectChange2(val){
			                this.form.roommatesHavaBeenRiskAreaInformation = val
							console.log(this.form.roommatesHavaBeenRiskAreaInformation,'this.form.select2')
			            },
					formSubmit: function(e) {
					                var form = e.detail.value
									let self=this
									this.$refs.form.validate(valid => {
										if(valid){
											form.communityId=self.communityId
											form.communityChildId=self.communityChildId
											form.communityName=self.communityName
											form.communityChildName=self.communityChildName
											form.createBy=self.createBy
											form.leaveInformation=self.form.leaveInformation
											form.havaBeenRiskAreaInformation=self.form.havaBeenRiskAreaInformation
											form.roommatesHavaBeenRiskAreaInformation=self.form.roommatesHavaBeenRiskAreaInformation
											form.injectionVaccineOneTime=self.form.injectionVaccineOneTime
											form.injectionVaccineTwoTime=self.form.injectionVaccineTwoTime
											self.form=form
											self.form.addressArea=self.txt
											self.form.addressJobArea=self.txt_job
											self.getDBEpidemicInfoFromShow(self.form)
											console.log('form发生了submit事件，携带数据为：' + JSON.stringify(e.detail.value))
												addPerson_epidemic_state_record(this.form).then((res) => {
													if(res.code === 200){
														uni.showToast({
														                    title: '提交成功',
														                    duration: 1000
														                })
														 setTimeout(() => {
														                   uni.navigateTo({
														                                      url:"../success/success",
														                   																			 })
														                }, 700);
													}else{
														alert('提交失败')
													}
												  }).catch((err) => {
												      alert('未能提交')
												  })
										}else{
										}
									})
									
					            },
								//重置
								formReset: function(e) {
											console.log('重置')
								            },
				//将展示的字段转换成数据库可以存储的
				getDBEpidemicInfoFromShow (form) {
				  if (form)
					form.leaveInformation = JSON.stringify(form.leaveInformation)
				  form.havaBeenRiskAreaInformation = this.$cf.getStringFromList(form.havaBeenRiskAreaInformation)
				  form.roommatesHavaBeenRiskAreaInformation = this.$cf.getStringFromList(form.roommatesHavaBeenRiskAreaInformation)
				},
				getDate(type) {
					const date = new Date();
					let year = date.getFullYear();
					let month = date.getMonth() + 1;
					let day = date.getDate();
					if (type === 'start') {
					    year = year - 60;
					        } else if (type === 'end') {
					           year = year + 2;
					            }
					            month = month > 9 ? month : '0' + month;;
					            day = day > 9 ? day : '0' + day;
					            return `${year}-${month}-${day}`;
					        },
				//新增一条出行地点
				handleAdd_leaveLocal () {
				  this.open_form_leaveInformation = true
				},
				//提交一条出行地点
			    submitForm_leaveInformation (e) {
			      this.$refs.form_leaveInformation.validate(valid => {
			        if (valid) {
						this.form_leaveInformation.arriveCityArea=this.txt_arrival
						// this.form.leaveInformation.push(this.form_leaveInformation)
						this.form.leaveInformation.push({
							arriveCityArea:this.form_leaveInformation.arriveCityArea,
							arriveData: this.form_leaveInformation.arriveData,
							arriveTransportationTypes: this.form_leaveInformation.arriveTransportationTypes,
							arriveTransportationValues: this.form_leaveInformation.arriveTransportationValues,
							leaveData:this.form_leaveInformation.leaveData
						})
						
						this.open_form_leaveInformation = false
						this.txt_arrival='请选择到达城市'
						this.date = '请选择到达日期'
						this.form_leaveInformation.arriveTransportationValues = ''
						this.leavedate = '请选择离开日期'
			        }
			      })
			    },
				//删除出行展示
				remove(e){
					let delList = this.form.leaveInformation
					delList.forEach(i => {
						delList.splice(i,1)
					})
					console.log('删除成功')
				},
				//取消新增出行地点
				cancel_leaveInformation () {
				  this.open_form_leaveInformation = false
				  },
		},
		onLoad(){
		   // this.getcommunity();
		   const queryList = this.$route.query ;
		   // console.log(queryList,'queryList')
		  if(queryList && queryList.communityId){
			   this.communityId=queryList.communityId
			   this.communityChildId=queryList.communityChildId
			   this.communityName=queryList.communityName
			   this.communityChildName=queryList.communityChildName
			   this.createBy=queryList.createBy
			   this.communityList.push(this.communityName)
			   this.communityChildList.push(this.communityChildName)
			   this.initcommunityIdValue=this.communityName
			   this.initcommunityChildIdValue=this.communityChildName
		   }
		}
	}
</script>

<style lang="scss" scoped>
@import "@/static/nars.scss";
</style>
