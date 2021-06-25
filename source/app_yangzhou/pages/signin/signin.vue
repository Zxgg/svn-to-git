<template>
	<view class="main">
			<uni-forms ref="form" 
				:value="form" 
				:rules="rules">
				<uni-forms-item
					label="现住地区划分"
					name="community"
					required>
						<xfl-select
							:list="communityList" 
							:clearable="false" 
							:listShow="false" 
							:isCanInput="true" 
							placeholder='请选择社区'
							:style_Container="'height: 25px; font-size: 14px;'" 
							:selectHideType="'hideAll'"
							@change='community_Change' />
				</uni-forms-item>
				<uni-forms-item
				label="所属小区"
				name="communityChild"
					required>
					<xfl-select
						:clearable="false" 
						:listShow="false" 
						:isCanInput="true" 
						:form='form'
						:list="communityChildList" 
						:style_Container="'height: 25px; font-size: 14px;'" 
						placeholder='请选择小区'
						:selectHideType="'hideAll'" 
						@change='communityChild_Change'/>
				</uni-forms-item>
				<uni-forms-item
				label="人口类型"
				name="personType"
				required>
					<xfl-select
						:list='personTypeList' 
						:clearable="false" 
						:listShow="false" 
						:isCanInput="true"
						:style_Container="'height: 25px; font-size: 14px;'" 
						:selectHideType="'hideAll'" 
						placeholder='请选择人口类型' 
						@change='personType_Change' />
				</uni-forms-item>
				<uni-forms-item 
				label="姓名" 
				name="name"
				required>
				<input 
				class="input" 
				v-model="form.name"
				type="text" 
				placeholder="请输入用户名" 
				placeholder-style="font-size:14px;color:#ACACAC;"
				@input="binddata('name',$event.detail.value)" />
				</uni-forms-item>
				<uni-forms-item
				label="曾用名" 
				name="oldName">
				<input 
				class="input" 
				v-model="form.oldName"
				type="text" 
				placeholder="请输入曾用名"
				placeholder-style="font-size:14px;color:#ACACAC;" 
				@input="binddata('oldName',$event.detail.value)" />
				</uni-forms-item>
				<uni-forms-item
				label="性别"
				name="sex"
				required>
					<xfl-select
						:list="sexList" 
						:clearable="false" 
						:listShow="false" 
						:isCanInput="true" 
						placeholder='请选择性别'
						:style_Container="'height: 25px; font-size: 14px;'" 
						:selectHideType="'hideAll'" 
						@change='sex_Change' />
				</uni-forms-item>
				<uni-forms-item
				label="民族"
				name="nation"
				required>
					<xfl-select
						:list="nationList" 
						:clearable="false" 
						:listShow="false" 
						:isCanInput="true" 
						placeholder="请选择民族"
						:style_Container="'height: 25px; font-size: 14px;'" 
						:selectHideType="'hideAll'" 
						@change='nation_Change' />
				</uni-forms-item>
				<view class="nationality" v-if="form.personType === '03'">
				<uni-forms-item
					label="国籍"
					name="nationality">
						<xfl-select
								:list="nationalityList" 
								:clearable="false" 
								:listShow="false" 
								:isCanInput="true"
								:style_Container="'height: 25px; font-size: 14px;'" 
								placeholder="请选择国籍" 
								:selectHideType="'hideAll'" 
								@change='nationality_Change'/>
				</uni-forms-item>
				</view>
			<uni-forms-item
				label="出生日期"
				name="brithDate"
				required>
				<picker
					class="picker_date" 
					mode="date" 
					:value="date"
					@change="brithDate_Change">
				<view class="brithDate" >{{date}}</view>
				</picker>
			</uni-forms-item>
			<uni-forms-item
				label="证件类型">
				<xfl-select
					:list="idTypeList" 
					:clearable="false" 
					:listShow="false" 
					:isCanInput="true"
					:style_Container="'height: 25px; font-size: 14px;'" 
					placeholder='请选择证件类型'
					:selectHideType="'hideAll'" 
					@change='idType_Change' />
			</uni-forms-item>
			<uni-forms-item
				label="证件号码" 
				name="identificationNumber"
				required>
				<input 
				class="identificationNumber" 
				v-model="form.identificationNumber"
				type="text" 
				placeholder="请输入证件号码"
				placeholder-style="font-size:14px;color:#ACACAC;" 
				@input="binddata('identificationNumber',$event.detail.value)" />
			</uni-forms-item>
			<view class="id_time">
				<uni-forms-item
					label="证件有效期开始时间"
					name="idcardStartDate">
					<picker
						class="picker_date" 
						mode="date" 
						:value="form.idcardStartDate"
						@change="idcardStartDate_Change">
					<view class="idcardStartDate">{{form.idcardStartDate}}</view>
					</picker>
				</uni-forms-item>
				<uni-forms-item
					label="证件有效期结束时间"
					name="idcardEndDate">
					<picker
						class="picker_date" 
						mode="date" 
						:value="form.idcardEndDate"
						@change="idcardEndDate_Change">
					<view class="idcardEndDate">{{form.idcardEndDate}}</view>
					</picker>
				</uni-forms-item>
			</view>
			<uni-forms-item
				v-if="form.personType != '03'"
				label="是否已婚"
				name="marital"
				required>
				<xfl-select
					:list="marryList" 
					:clearable="false" 
					:listShow="false" 
					:isCanInput="true"
					:style_Container="'height: 25px; font-size: 14px;'" 
					placeholder="请选择是否已婚"
					placeholder-style="font-size:14px;color:#ACACAC;" 
					:selectHideType="'hideAll'" 
					@change='marital_Change' />
			</uni-forms-item>
			<uni-forms-item
				label="宗教信仰"
				name="religion">
				<xfl-select
					:list="religionList" 
					:clearable="false" 
					:listShow="false" 
					:isCanInput="true"
					:style_Container="'height: 25px; font-size: 14px;'" 
					placeholder="请选择宗教信仰"
					placeholder-style="font-size:14px;color:#ACACAC;" 
					:selectHideType="'hideAll'" 
					@change='religion_Change'/>
			</uni-forms-item>
			<view class="foreigners" v-if="form.personType != '03'">
				<uni-forms-item
					label="政治面貌"
					name="political"
					required>
					<xfl-select
						:list="politicalList" 
						:clearable="false" 
						:listShow="false" 
						:isCanInput="true"
						:style_Container="'height: 25px; font-size: 14px;'" 
						placeholder="请选择政治面貌"
						placeholder-style="font-size:14px;color:#ACACAC;" 
						:selectHideType="'hideAll'"
						 @change='political_Change' />
				</uni-forms-item>
				<uni-forms-item
					label="籍贯"
					name="location"
					required>
					<input
					class="input" 
					v-model="form.location"
					type="text" 
					placeholder="请输入籍贯" 
					placeholder-style="font-size:14px;color:#ACACAC;"
					@input="binddata('location',$event.detail.value)" />
				</uni-forms-item>
				
				<uni-forms-item
					label="户籍门(楼)详址" 
					name="residenceDetail"
					required>
					<input 
					class="residenceDetail" 
					v-model="form.residenceDetail"
					type="text" 
					placeholder="请输入户籍详址"
					placeholder-style="font-size:14px;color:#ACACAC;" 
					@input="binddata('residenceDetail',$event.detail.value)" />
				</uni-forms-item>
				<uni-forms-item
					label="户号" 
					name="doorNo"
					required>
					<input 
					class="doorNo" 
					v-model="form.doorNo"
					type="text" 
					placeholder="请输入户号"
					placeholder-style="font-size:14px;color:#ACACAC;" 
					@input="binddata('doorNo',$event.detail.value)" />
				</uni-forms-item>
				<uni-forms-item
					label="户主" 
					name="householdName">
					<input 
					class="householdName" 
					v-model="form.householdName"
					type="text" 
					placeholder="请输入户主姓名"
					placeholder-style="font-size:14px;color:#ACACAC;" 
					@input="binddata('householdName',$event.detail.value)" />
				</uni-forms-item>
				<uni-forms-item
					label="户主公民身份证号码" 
					name="householdIdc">
					<input 
					class="householdIdc" 
					v-model="form.householdIdc"
					type="text" 
					placeholder="请输入户主身份证号码"
					placeholder-style="font-size:14px;color:#ACACAC;" 
					@input="binddata('householdIdc',$event.detail.value)" />
				</uni-forms-item>
				<uni-forms-item
					label="与户主关系"
					name="householdRelation"
					required>
					<xfl-select
						:list="householdRelationList" 
						:clearable="false" 
						:listShow="false" 
						:isCanInput="true"
						:style_Container="'height: 25px; font-size: 14px;'" 
						placeholder='请选择与户主关系'
						placeholder-style="font-size:14px;color:#ACACAC;"
						:selectHideType="'hideAll'"
						@change='householdRelation_Change'/>
				</uni-forms-item>
				<uni-forms-item
					label="户主联系方式" 
					name="householdPhone">
					<input 
					class="householdPhone" 
					v-model="form.householdPhone"
					type="text" 
					placeholder="请输入户主联系方式"
					placeholder-style="font-size:14px;color:#ACACAC;" 
					@input="binddata('householdPhone',$event.detail.value)" />
				</uni-forms-item>
			</view>
			<uni-forms-item
				label="现住地"
				name="address"
				required>
				<wangding-pickerAddress
					class="address" 
					@change="address_Change">
					<view class="native">
						{{address}}
					</view>
				</wangding-pickerAddress>
			</uni-forms-item>
			<uni-forms-item
				label="现住门(楼)详址" 
				name="addressDetail"
				required>
				<input 
				class="addressDetail" 
				v-model="form.addressDetail"
				type="text" 
				placeholder="请输入现住详址"
				placeholder-style="font-size:14px;color:#ACACAC;" 
				@input="binddata('addressDetail',$event.detail.value)" />
			</uni-forms-item>
			<uni-forms-item
				label="学历"
				name="degree"
				required>
				<xfl-select
					:list="degreeList" 
					:clearable="false" 
					:listShow="false" 
					:isCanInput="true"
				    :style_Container="'height: 25px; font-size: 14px;'" 
					placeholder='请选择学历'
					placeholder-style="font-size:14px;color:#ACACAC;"
					:selectHideType="'hideAll'" 
					@change='degree_Change'/>
			</uni-forms-item>
			<uni-forms-item
				label="职业类别" 
				name="profession"
				required>
				<input 
				class="profession" 
				v-model="form.profession"
				type="text" 
				placeholder="请输入职业类别"
				placeholder-style="font-size:14px;color:#ACACAC;" 
				@input="binddata('profession',$event.detail.value)" />
			</uni-forms-item>
			<uni-forms-item
				label="职业信息" 
				name="professioDetail"
				required>
				<input 
				class="professioDetail" 
				v-model="form.professioDetail"
				type="text" 
				placeholder="请输入职业信息"
				placeholder-style="font-size:14px;color:#ACACAC;" 
				@input="binddata('professioDetail',$event.detail.value)" />
			</uni-forms-item>
			<uni-forms-item
				label="联系方式" 
				name="phoneNumber"
				required>
				<input 
				class="phoneNumber" 
				v-model="form.phoneNumber"
				type="text" 
				placeholder="请输入手机号码"
				placeholder-style="font-size:14px;color:#ACACAC;" 
				@input="binddata('phoneNumber',$event.detail.value)" />
			</uni-forms-item>
			<view 
				class="populationPerson" 
				v-if="form.personType === '02'">>
				<uni-forms-item
					label="最后入境">
					<picker
						mode="date" 
						value="form.arrivalProvinceDate"
						@change="arrivalProvinceDate_Change">
					<view 
					class="arrivalProvinceDate">
					{{form.arrivalProvinceDate}}
					</view>
					</picker>
				</uni-forms-item>
				<uni-forms-item
					label="流入原因" 
					name="floatingReasons">
					<input 
					class="floatingReasons" 
					v-model="form.floatingReasons"
					type="text" 
					placeholder="请填写流入原因"
					placeholder-style="font-size:14px;color:#ACACAC;" 
					@input="binddata('floatingReasons',$event.detail.value)" />
				</uni-forms-item>
				<uni-forms-item
					label="流入地办证类型"
					name="cardType">
					<xfl-select
						:list="cardTypeList" 
						:clearable="false" 
						:listShow="false" 
						:isCanInput="true"
					    :style_Container="'height: 25px; font-size: 14px;'" 
						placeholder='请选择流入地办证类型'
						placeholder-style="font-size:14px;color:#ACACAC;"
						:selectHideType="'hideAll'" 
						@change='cardType_Change'/>
				</uni-forms-item>
				<uni-forms-item
					label="流入地办证机关" 
					name="cardHandlingAgency">
					<input 
					class="cardHandlingAgency" 
					v-model="form.cardHandlingAgency"
					type="text" 
					placeholder="请输入流入地办证机关"
					placeholder-style="font-size:14px;color:#ACACAC;" 
					@input="binddata('cardHandlingAgency',$event.detail.value)" />
				</uni-forms-item>
				<uni-forms-item
					label="流入地证件号码" 
					name="cardNum">
					<input 
					class="cardNum" 
					v-model="form.cardNum"
					type="text" 
					placeholder="请输入流入地证件号码"
					placeholder-style="font-size:14px;color:#ACACAC;" 
					@input="binddata('cardNum',$event.detail.value)" />
				</uni-forms-item>
				<uni-forms-item
					label="流入地证件登记日期">
					<picker
						mode="date" 
						value="form.cardRecordDate"
						@change="cardRecordDate_Change">
					<view 
					class="cardRecordDate">
					{{form.cardRecordDate}}
					</view>
					</picker>
				</uni-forms-item>
			</view>
			<view 
				class="foreigners_bottom"
				v-if="form.personType === '03'">
				<uni-forms-item
					label="最后入国境">
					<picker
						mode="date" 
						value="form.arrivalCountryDate"
						@change="arrivalCountryDate_Change">
					<view 
					class="arrivalCountryDate">
					{{form.arrivalCountryDate}}
					</view>
					</picker>
				</uni-forms-item>
				<uni-forms-item
					label="预计离开日期">
					<picker
						mode="date" 
						value="form.departureDate"
						@change="departureDate_Change">
					<view 
					class="departureDate">
					{{form.departureDate}}
					</view>
					</picker>
				</uni-forms-item>
			</view>
			<uni-forms-item
				label="人脸图像">
						<view>
							<imgUpload :count="count" @obtain_img="obtain_img" :url="imgurl"></imgUpload>
						</view>
			</uni-forms-item>
			
			<view class="uni-btn-v">
				<button @click="formSubmit" size="mini">提交</button>
				<image src="../../static/icon/cz.png"  @click="formReset"></image>
			</view>
			</uni-forms>
		<!-- </bjx-form> -->
	</view>
</template>
<script>
	// import imgUpload from '@/components/linzq-imgUpload/linzq-imgUpload.vue'
	import imgUpload from '@/components/linzq-imgUpload/linzq-imgUpload.vue'
	import xflSelect from '../../components/xfl-select/xfl-select.vue';
	import configInfo from '../../config/configInfo.js'
	import {
		getDicts,
		communityApi,
		communityChildApi,
		addpersonRegistrationApi,
	} from '@/util/api.js'
	export default {
		components: {
			imgUpload
		},
		data() {
			const currentDate = this.getDate({
				format: true
			})
			return {
				count: 1,
				imgurl: configInfo.DBRestAPIEndPointUri+'/app/uploadImageSample',
				pageState: 1,
				communityChildList:[],
				communityList: [],
				native: '请选择籍贯',
				address:'请选择现住地',
				date: currentDate,
				sexList: [],
				personTypeList: [],
				marryList: [],
				idTypeList: [],
				politicalList:[],
				religionList:[],
				householdRelationList:[],
				degreeList:[],
				cardTypeList:[],
				nationalityList:[],
				nationList:[],
				form: {
					personType:null,
					arrivalCountryDate:currentDate,
					departureDate:currentDate,
					arrivalProvinceDate:currentDate,
					cardRecordDate:currentDate,
					idcardStartDate:currentDate,
					idcardEndDate:currentDate,
					community:null,
					communityChild:null,
					brithDate:null,
					communityChildId:null,
					communityId: null,
					marital:null,
					political:null,
					location:null,
					householdRelation:null,
					degree:null,
					address:null,
					imageData:[],
					pics:""
				},
				rules: {
					community: {
						rules: [{
						        required: true,
						        errorMessage: '请选择社区',
						    }]
					},
					communityChild: {
						rules: [{
						        required: true,
						        errorMessage: '请选择小区',
						    }]
					},
					name: {
                    rules: [{
                            required: true,
                        }]
					},
					personType: {
						rules: [{
						        required: true,
						    }]
					},
					sex: {
						rules: [{
						        required: true,
						    }]
					},
					identificationNumber: {
						rules: [{
						        required: true,
						    }]
					},
					marital: {
						rules: [{
						        required: true,
						    }]
					},
					political: {
						rules: [{
						        required: true,
						    }]
					},
					brithDate: {
						rules: [{
						        required: true,
						    }]
					},
					location: {
						rules: [{
						        required: true,
						    }]
					},
					residenceDetail: {
						rules: [{
						        required: true,
						    }]
					},
					doorNo: {
						rules: [{
						        required: true,
						    }]
					},
					householdRelation: {
						rules: [{
						        required: true,
						    }]
					},
					degree:{
						rules: [{
						        required: true,
						    }]
					},
					addressDetail:{
						rules: [{
						        required: true,
						    }]
					},
					profession:{
						rules: [{
						        required: true,
						    }]
					},
					professioDetail:{
						rules: [{
						        required: true,
						    }]
					},
					phoneNumber:{
						rules: [{
						        required: true,
						    }]
					},
					nationality:{
						rules: [{
						        required: true,
						    }]
					},
					address:{
						rules: [{
						        required: true,
						    }]
					},
					nation:{
						rules: [{
						        required: true,
						    }]
					},
				},
			}
		},
		created() {
			//字典
			getDicts("base_person_type").then((response) => {
				var obj = {};
				var list_ = [];
				response.data.forEach(item => {
					obj.value = item.dictLabel;
					obj.code = item.dictValue
					obj.disabled = false;
					list_.push(obj);
					obj = {};
				});
				this.personTypeList = list_;
			});
			getDicts("sys_user_sex").then(response => {
				var obj = {};
				var list_ = [];
				response.data.forEach(item => {
					obj.value = item.dictLabel;
					obj.code = item.dictValue
					obj.disabled = false;
					list_.push(obj);
					obj = {};
				});
				this.sexList = list_;
			});
			getDicts("base_idCard").then((response) => {
				var obj = {};
				var list_ = [];
				response.data.forEach(item => {
					obj.value = item.dictLabel;
					obj.code = item.dictValue
					obj.disabled = false;
					list_.push(obj);
					obj = {};
				});
				this.idTypeList = list_;
			});
			getDicts("base_yes_no").then((response) => {
				var obj = {};
				var list_ = [];
				response.data.forEach(item => {
					obj.value = item.dictLabel;
					obj.code = item.dictValue
					obj.disabled = false;
					list_.push(obj);
					obj = {};
				});
				this.marryList = list_;
			});
			getDicts("base_political").then((response) => {
			    var obj = {};
			    var list_ = [];
			    response.data.forEach(item => {
			    	obj.value = item.dictLabel;
					obj.code = item.dictValue
			    	obj.disabled = false;
			    	list_.push(obj);
			    	obj = {};
			    });
			    this.politicalList = list_;
			    });
			getDicts("base_religion").then((response) => {
			     var obj = {};
			     var list_ = [];
			     response.data.forEach(item => {
			     	obj.value = item.dictLabel;
					obj.code = item.dictValue
			     	obj.disabled = false;
			     	list_.push(obj);
			     	obj = {};
			     });
			     this.religionList = list_;
			    });
			getDicts("base_family_relation").then((response) => {
				 var obj = {};
			     var list_ = [];
			     response.data.forEach(item => {
			     	obj.value = item.dictLabel;
					obj.code = item.dictValue
			     	obj.disabled = false;
			     	list_.push(obj);
			     	obj = {};
			     });
			     this.householdRelationList = list_;
			    });
			getDicts("base_degree").then((response) => {
			     var obj = {};
			     var list_ = [];
			     response.data.forEach(item => {
			     	obj.value = item.dictLabel;
					obj.code = item.dictValue
			     	obj.disabled = false;
			     	list_.push(obj);
			     	obj = {};
			     });
			     this.degreeList = list_;
			    });
			getDicts("base_nationality").then((response) => {
			     var obj = {};
			     var list_ = [];
			     response.data.forEach(item => {
			     	obj.value = item.dictLabel;
					obj.code = item.dictValue
			     	obj.disabled = false;
			     	list_.push(obj);
			     	obj = {};
			     });
			     this.nationalityList = list_;
			    });
			//流入地办证类型字典
			getDicts("base_degree").then((response) => {
			     var obj = {};
			     var list_ = [];
			     response.data.forEach(item => {
			     	obj.value = item.dictLabel;
					obj.code = item.dictValue
			     	obj.disabled = false;
			     	list_.push(obj);
			     	obj = {};
			     });
			     this.cardTypeList = list_;
			    });
			getDicts("base_nation").then((response) => {
			     var obj = {};
			     var list_ = [];
			     response.data.forEach(item => {
			     	obj.value = item.dictLabel;
					obj.code = item.dictValue
			     	obj.disabled = false;
			     	list_.push(obj);
			     	obj = {};
			     });
			     this.nationList = list_;
			    });
		},
		computed: {
			// startDate() {
			// 	return this.getDate('start');
			// },
			// endDate() {
			// 	return this.getDate('end');
			// }
		},
		mounted(){
			this.getCommunity()
		},
		methods: {
			obtain_img(url) {
				let imgstr = {
					uid:"",
					status:"success",
					name:"",
					url:url
				}
				this.form.imageData.push(imgstr)
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
			formSubmit: function(e) {
				var form = {}
				let self = this
				form = self.form
				form.pics = JSON.stringify(self.form.imageData);
				this.$refs.form.submit().then(res=>{
				                addpersonRegistrationApi(form).then(res => {
									if(res.code === 200){
										uni.showToast({
											title: '提交成功',
											duration: 1000
													    })
												}else{
													alert('提交失敗')
												}
								})
				            }).catch(err =>{
				                console.log('表单错误信息：', err);
				            })
			},
			formReset: function(e) {
				console.log('清空数据')
			},
			//所有插件change事件
			community_Change(e) {
				this.form.community = e.newVal
				this.form.communityId = this.communityList[e.index].id
				console.log(this.form.communityId, '社区')
				communityChildApi(this.form.communityId).then((res) => {
					var obj = {};
					var list_ = [];
					res.rows.forEach(item => {
						obj.value = item.name;
						obj.id = item.id
						obj.disabled = false;
						list_.push(obj);
						obj = {};
					});
					this.communityChildList = list_;
				})
			},
			communityChild_Change(e) {
				this.form.communityChild = e.newVal
				this.form.communityChildId = this.communityChildList[e.index].id
				console.log(this.form.communityChild, '小区')
			},
			personType_Change(e) {
				this.form.personType = e.orignItem.code
				console.log(this.form.personType, '人口类型')
			},
			sex_Change(e) {
				this.form.sex = e.orignItem.code
				// console.log(this.form.sex, '性别')
				console.log(e.orignItem.code,'e.orignItem性别编码')
			},
			idType_Change(e) {
				this.form.idType = e.orignItem.code
				console.log(this.form.idType, '证件类型')
			},
			marital_Change(e) {
				this.form.marital = e.orignItem.code
				console.log(this.form.marital, '是否已婚')
			},
			brithDate_Change: function(e) {
				this.date = e.target.value
				this.form.brithDate = this.date
				console.log(this.form.brithDate, '生日')
			},
			arrivalProvinceDate_Change: function(e) {
				this.form.arrivalProvinceDate = e.target.value
				console.log(this.form.arrivalProvinceDate, '最后入境')
			},
			cardRecordDate_Change: function(e) {
				this.form.cardRecordDate = e.target.value
				console.log(this.form.cardRecordDate, '流入地证件登记日期')
			},
			idcardStartDate_Change: function(e) {
				this.form.idcardStartDate = e.target.value
				console.log(this.form.idcardStartDate, '证件有效期开始时间')
			},
			idcardEndDate_Change: function(e) {
				this.form.idcardEndDate = e.target.value
				console.log(this.form.idcardEndDate, '证件有效期结束时间')
			},
			arrivalCountryDate_Change: function(e) {
				this.form.arrivalCountryDate = e.target.value
				// this.form.arrivalCountryDate = this.date
				console.log(this.form.arrivalCountryDate, '最后入国境日期')
			},
			departureDate_Change: function(e) {
				this.form.departureDate = e.target.value
				// this.form.departureDate = this.date
				console.log(this.form.departureDate, '预计离开日期')
			},
			political_Change(e) {
				this.form.political = e.orignItem.code
				console.log(this.form.political, '政治面貌')
			},
			// native_Change(data) {
			// 	this.native = data.data.join('')
			// 	this.form.location = this.native
			// 	console.log(this.form.location,'籍贯');
			// },
			address_Change(data) {
				this.address = data.data.join('')
				this.form.address = this.address
				console.log(this.form.address,'现住地');
			},
			religion_Change(e) {
				this.form.religion =  e.orignItem.code
				console.log(this.form.religion,'宗教信仰');
			},
			nation_Change(e) {
				this.form.nation = e.orignItem.code
				console.log(this.form.nation,'民族');
			},
			householdRelation_Change(e) {
				this.form.householdRelation = e.orignItem.code
				console.log(this.form.householdRelation,'与户主关系');
			},
			degree_Change(e) {
				this.form.degree = e.orignItem.code
				console.log(this.form.degree,'学历');
			},
			nationality_Change(e) {
				this.form.nationality = e.orignItem.code
				console.log(this.form.nationality,'国籍');
			},
			//流入地办证类型change事件
			cardType_Change(e) {
				this.form.cardType = e.orignItem.code
				console.log(this.form.cardType,'办证类型');
			},
			//api获取数据
			getCommunity(){
				communityApi().then((res) => {
					var obj = {};
					var list_ = [];
					res.rows.forEach(item => {
						obj.value = item.name;
						obj.disabled = false;
						obj.id = item.id
						list_.push(obj);
						obj = {};
					});
					this.communityList = list_;
				})
			},
			
		}
	}
</script>

<style lang="scss" scoped>
	@import "@/static/signin.scss";
</style>
