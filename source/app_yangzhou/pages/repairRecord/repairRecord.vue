<template>
	<view 
		class="main">
		<view class="add">
			<view class="addinside">
				<image src="/static/icon/xj2.png"
				class="xj"
				@click="addshow"
				/>
				<text>新建</text>
			</view>
		</view>
		<view class="addnote" 
			  v-show="!flag" >
			<form 
			ref='addform'
			:addform='addform'
			 @submit="addSubmit">
				添加报事报修记录
				<view class="repairname">
					报修人姓名
					<input type="text" 
					name='repairPersonName'
					placeholder="请输入报修人姓名"
					 v-model="addform.repairPersonName"/>
				</view>
				<view class="repairconcent">
					报修事项
					<textarea placeholder="请输入内容" 
					v-model="addform.repairContent"
					name='repairContent'
					style="height: 130rpx; width: 100%; padding-left: 30rpx;"/>
				</view>
				<view class="btn2">
					<button type="default"
					size="mini"
					form-type="submit">确定</button>
					<button type="default" 
					size="mini" 
					@click="cancel">取消</button>
				</view>
			</form>
		</view>
		<view class="table" v-if="flag" >
			<view class="tr_dian" >
				<view class="dianchild">
					•­­­­­­­
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
					报修人姓名
				</view>
				<view class="th">
					报修事项
				</view>
				<view class="th">
					是否已经处理
				</view>
				<view class="th">
					处理人姓名
				</view>
			</view>
			 <view class="tr_td"
			v-for="(item,index) in repairList" :key="index">
				<view class="td">
					{{item.repairPersonName}}
				</view>
				<view class="td">
					{{item.repairContent}}
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
	import { addrepairRecordApi,repairRecordApi,getDicts } from '@/util/api.js'
	import {
		selectDictLabel
	} from '../../static/format.js'
	export default {
		data() {
			return {
				//字典
				dealOptions:[],
				flag:true,
				form:{},
				repairList:[],
				addform:{
					repairContent:null,
					repairPersonName:null
				},
			}
		},
		created () {
		    getDicts("base_yes_no").then(response => {
		      this.dealOptions = response.data;
		    });
		 },
		mounted() {
			this.getRepairRecord()
		},
		methods: {
            Reset: function(e) {
                console.log('清空数据')
            },
			addshow(){
				this.flag = !this.flag;
			},
			addSubmit: function(e) {
			    addrepairRecordApi(this.addform).then((res) => {
					if(res.code === 200){
						uni.showToast({
						    title: '提交成功',
						    duration: 2000
						})
					}else{
						alert('提交失败')
					}
				})
				this.flag = !this.flag;
			},
			cancel(){
				this.flag = !this.flag;
			},
			// yes_no_Change(e){
			// 	this.form.yes_no = e.newVal
			// 	console.log(e.newVal,'newVal')
			// }
			ye_no_Format: function (value) {
			      return selectDictLabel(this.dealOptions, value);
			    },
			getRepairRecord(){
				let self = this
				repairRecordApi(this.form).then((res) => {
					if(res.code === 200){
						res.rows.forEach(data => {
							data.deal = self.ye_no_Format(data.deal)
						})
							this.repairList=res.rows
					}
				})
			},
			search(){
				this.getRepairRecord(this.form)
			},
		}
	}
</script>

<style lang="scss" scoped>
@import "@/static/repairRecord.scss";
</style>
