<template>
		<view 
				<uni-forms ref="form"
					:value="form" 
					:rules="rules">
					<uni-forms-item
						label="访客姓名" 
						name="name"
						required>
						<input 
						class="input" 
						v-model="form.name"
						type="text" 
						placeholder="请输入访客姓名" 
						placeholder-style="font-size:14px;color:#ACACAC;"
						@input="binddata('name',$event.detail.value)" />
					</uni-forms-item>
					<uni-forms-item
						label="性别"
						name="sex">
							<xfl-select
								:list="sexList" 
								:clearable="false" 
								:listShow="false" 
								:isCanInput="true" 
								placeholder='请选择性别'
								placeholder-style="font-size:14px;color:#ACACAC;"
								:style_Container="'height: 25px; font-size: 14px;'" 
								:selectHideType="'hideAll'" 
								@change='sex_Change' />
					</uni-forms-item>
					<uni-forms-item
						label="身份证号" 
						name="identificationNumber"
						required>
						<input 
						class="identificationNumber" 
						v-model="form.identificationNumber"
						type="number" 
						placeholder="请输入证件号码"
						placeholder-style="font-size:14px;color:#ACACAC;" 
						@input="binddata('identificationNumber',$event.detail.value)" />
					</uni-forms-item>
					<uni-forms-item
						label="手机号码" 
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
						<uni-forms-item
								label="社区名称"
								name="targetPersonCommunity"
								required>
									<xfl-select
										:clearable="false" 
										:list="communityList" 
										:isCanInput="true" 
										:listShow="false" 
										:style_Container="'height: 25px; font-size: 14px;'" 
										placeholder='请选择社区'
										:selectHideType="'hideAll'"
										@change='community_Change'>
									</xfl-select>
						</uni-forms-item>
					<uni-forms-item
							label="小区名称"
							name="targetPersonCommunityChild"
							required>
								<xfl-select
									:clearable="false" 
									:list="communityChildList" 
									:isCanInput="true" 
									:listShow="false" 
									:style_Container="'height: 25px; font-size: 14px;'" 
									placeholder='请选择小区'
									:selectHideType="'hideAll'"
									@change='communityChild_Change'>
								</xfl-select>
					</uni-forms-item>
					<uni-forms-item
						label="拜访业主" 
						name="targetPersonName"
						required>
						<input 
						class="input" 
						v-model="form.targetPersonName"
						type="text" 
						placeholder="请输入业主姓名" 
						placeholder-style="font-size:14px;color:#ACACAC;"
						@input="binddata('targetPersonName',$event.detail.value)" />
					</uni-forms-item>
					<uni-forms-item
						label="拜访具体地址" 
						name="targetPersonHouseAddress"
						required>
						<input 
						class="input" 
						v-model="form.targetPersonHouseAddress"
						type="text" 
						placeholder="请输入具体地址" 
						placeholder-style="font-size:14px;color:#ACACAC;"
						@input="binddata('targetPersonHouseAddress',$event.detail.value)" />
					</uni-forms-item>
					<uni-forms-item
						label="拜访开始时间" 
						name="visitStartTime">
						<xp-picker
							@confirm="timeConfirmstart"
							name="visitStartTime"
							mode='ymdhis'>
							<view class="visittime">
								{{form.visitStartTime}}
							</view>
							</xp-picker>
					</uni-forms-item>
					<uni-forms-item
						label="拜访结束时间" 
						name="visitEndTime">
						<xp-picker
							@confirm="timeConfirmend"
							name="visitEndTime"
							mode='ymdhis'>
							<view class="visittime">
								{{form.visitEndTime}}
							</view></xp-picker>
					</uni-forms-item>	
				<uni-forms-item
					label="人脸图像">
							<view>
								<imgUpload :count="count" @obtain_img="obtain_img" :url="imgurl"></imgUpload>
							</view>
				</uni-forms-item>
				<view 
					class="btn">
					<button 
						size="mini"
						type="default" 
						@click="formSubmit">
					提交</button>
					<button 
						size="mini"
						type="default" 
						@click="cancel">
					取消</button>
				</view>
			</uni-forms>
		</view>
</template>

<script>
	import imgUpload from '@/components/linzq-imgUpload/linzq-imgUpload.vue'
	import {
		getDicts,
		communityApi,
		communityChildApi,
		addpersonVisitApi
	} from '@/util/api.js'
	import configInfo from '../../config/configInfo.js'
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
				sexList:[],
				communityList:[],
				communityChildList:[],
				serverUrl: '',
				serverUrlDeleteImage: '',
				form:{
					name:null,
					phoneNumber:null,
					imageData : [],
					identificationNumber:null,
					targetPersonCommunity:null,
					targetPersonCommunityChild:null,
					targetPersonCommunityId:null,
					targetPersonCommunityChildId:null,
					targetPersonName:null,
					visitStartTime:currentDate,
					visitEndTime:currentDate,
				},
				rules: {
					targetPersonCommunity: {
						rules: [{
						        required: true,
						        errorMessage: '请选择社区',
						    }]
					},
					targetPersonCommunityChild: {
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
					identificationNumber: {
						rules: [{
						        required: true,
						    }]
					},
					phoneNumber: {
						rules: [{
						        required: true,
						    }]
					},
					targetPersonName: {
						rules: [{
						        required: true,
								
						    }]
					},
					targetPersonHouseAddress: {
						rules: [{
						        required: true,
						    }]
					},
				}
			}
		},
		created(){
			//字典
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
			},
		mounted(){
			this.getCommunity()
			// this.getCommunityChild()
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
			//时间
			timeConfirmstart(e){
				this.form.visitStartTime = e.value
			},
			timeConfirmend(e){
				this.form.visitEndTime = e.value
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
			//所有插件change事件
			community_Change(e) {
				this.form.targetPersonCommunity = e.newVal
				this.form.targetPersonCommunityId = this.communityList[e.index].id
				communityChildApi(this.form.targetPersonCommunityId).then((res) => {
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
				console.log(this.form.targetPersonCommunity,'社区')
			},
			communityChild_Change(e) {
				this.form.targetPersonCommunityChild = e.newVal
				this.form.targetPersonCommunityChildId = this.communityChildList[e.index].id
				console.log(this.form.targetPersonCommunityChild,'小区')
			},
			visitStartTime_Change: function(e) {
				this.form.visitStartTime = e.target.value
				console.log(this.form.visitStartTime, '拜访开始时间')
			},
			visitEndTime_Change: function(e) {
				this.form.visitEndTime = e.target.value
				console.log(this.form.visitEndTime, '拜访结束时间')
			},
			sex_Change(e) {
				this.form.sex = e.orignItem.code
				// console.log(this.form.sex, '性别')
				console.log(e.orignItem.code,'e.orignItem性别编码')
			},
			//提交表单
			formSubmit: function(e) {
				var form = {}
				let self = this
				form.sex = self.form.sex
				console.log(form.pics,'this.form')
				form.targetPersonCommunity = self.form.targetPersonCommunity
				form.targetPersonCommunityChild  = self.form.targetPersonCommunityChild
				form.targetPersonCommunityId = self.form.targetPersonCommunityId
				form.targetPersonCommunityChildId  = self.form.targetPersonCommunityChildId
				form = self.form
				form.pics = JSON.stringify(self.form.imageData);
				this.$refs.form.submit().then(res=>{
					console.log(form,'表单')
				                addpersonVisitApi(this.form).then(res => {
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
				// this.$refs.form.validate(val => {
				// 	if(valid){
				// 		form.sex = self.form.sex
				// 		form.targetPersonCommunity = self.form.community
				// 		form.targetPersonCommunityChild  = self.form.communityChild
				// 		form.targetPersonCommunityId = self.form.communityId
				// 		form.targetPersonCommunityChildId  = self.form.communityChildId
				// 		form.visitStartTime = self.form.visitStartTime
				// 		form.visitEndTime = self.form.visitEndTime
				// 		self.form = form
				// 		console.log(form,'提交的form')
				// 		addpersonVisitApi(this.form).then((res) => {
				// 			if(res.code === 200){
				// 				uni.showToast({
				// 				                    title: '提交成功',
				// 				                    duration: 1000
				// 				                })
				// 			}else{
				// 				alert('提交失敗')
				// 			}
				// 		}).catch((err) => {
							
				// 		})
				// 	}
				// })
				},
			//取消提交
			cancel(){
				console.log('取消')
			},
			getDate(type) {
				const date = new Date();
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();
				let hours = date.getHours();
				let minutes = date.getMinutes()
				let seconds = date.getSeconds()
			
				if (type === 'start') {
					year = year - 60;
				} else if (type === 'end') {
					year = year + 2;
				}
				month = month > 9 ? month : '0' + month;;
				day = day > 9 ? day : '0' + day;
				hours = hours > 9 ? hours : '0' + hours
				minutes = minutes > 9 ? minutes : '0' + minutes
				seconds = seconds > 9 ? seconds : '0' + seconds
				return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
			},
		}
	}
</script>

<style lang="scss" scoped>
@import "@/static/makingAnApointment.scss";
</style>
