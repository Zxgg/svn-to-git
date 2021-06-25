<template>
	<view class="main">
		<view class="add">
			<view class="addinside">
				<image src="/static/icon/xj.png"
				class="xj"
				@click="addshow"
				/>
				<text>新建</text>
			</view>
		</view>
		<view class="addnote" 
		v-show="!flag" >
			<form 
			@submit="addFormSubmit" >
				添加投诉意见记录
				<view class="complaint_name">
					投诉人姓名
					<input type="text"
					 name='opinionPersonName'
					 v-model="addForm.opinionPersonName"
					 placeholder="请输入投诉人姓名" />
				</view>
				<view class="complaint_concent">
					投诉事项
					<textarea placeholder="请输入内容" 
					name='opinionContent'
					v-model="addForm.opinionContent"
					style="height: 130rpx; width: 100%; padding-left: 30rpx;"/>
				</view>
				<view class="btn2">
					<button type="default" size="mini" form-type="submit">确定</button>
					<button type="default" size="mini" @click="cancel">取消</button>
				</view>
			</form>
		</view>
		<view class="table" v-if="flag">
			<view class="tr_dian">
				<view class="dianchild">
					•
				</view>
				<view class="dianchild">
					•
				</view>
				<view class="dianchild">
					•
				</view>
				<view class="dianchild">
					•
				</view>
			</view>
			<view class="tr_th">
				<view class="th">
					投诉人姓名
				</view>
				<view class="th">
					投诉事项
				</view>
				<view class="th">
					是否已经处理
				</view>
				<view class="th">
					处理人姓名
				</view>
			</view>
				<view class="tr_td"
				v-for="(item,index) in complaintList" :key='index'>
					<view class="td">
						{{item.opinionPersonName}}
					</view>
					<view class="td">
						{{item.opinionContent}}
					</view>
					<view class="td">
						{{item.deal}}
					</view>
					<view class="td">
						{{item.dealPersonName}}
					</view>
				</view>
		</view>
	</view>
</template>
<script>
	import { addcomplaintsApi,getDicts,complaintApi } from '@/util/api.js'
	import {
		selectDictLabel
	} from '../../static/format.js'
	export default {
		data() {
			return {
				noticelist:[],
				flag:true,
				form:{
					opinionPersonName:'',
					// deal:'是',
				},
				dealOptions:[],
				addForm:{},
				complaintList:[],
			}
		},
		created () {
		    getDicts("base_yes_no").then(response => {
				this.dealOptions = response.data;
				var obj = {};
				var list_ = [];
				response.data.forEach(item => {
					obj.value = item.dictLabel;
					obj.code = item.dictValue
					obj.disabled = false;
					obj.type = item.type
					list_.push(obj);
					obj = {};
				});
				this.noticelist = list_;
		    });
		  },
		mounted() {
			this.getComplaint(this.form)
		},
		methods: {
			addFormSubmit: function(e) {
				addcomplaintsApi(this.addForm).then((res) => {
					if(res.code === 200){
						uni.showToast({
						    title: '提交成功',
						    duration: 2000
						})
						this.flag = !this.flag;
					}else{
						alert('提交失败')
					}
				})
            },
            formReset: function(e) {
                console.log('清空数据')
            },
			addshow(){
				this.flag = !this.flag;
			},
			cancel(){
				this.flag = !this.flag;
			},
			yes_no_Change(e){
				this.form.deal = e.newVal
				//this.form.sex = e.orignItem.code
				console.log(e.newVal,'newVal')
			},
			dealFormat: function (value) {
			      return selectDictLabel(this.dealOptions, value);
			    },
			getComplaint(){
				let self = this
				complaintApi(this.form).then((res) => {
					if(res.code === 200){
						res.rows.forEach(data => {
							data.deal = self.dealFormat(data.deal)
						})
							this.complaintList=res.rows
					}
				})
			},
		}
	}
</script>

<style lang="scss" scoped>
@import "@/static/complaints.scss";
</style>
