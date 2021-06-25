<template>
	<view class="">
		<button type="default"
		@click="add_vote">+ 新增投票</button>
		<view class="add_vote_record" v-if="open">
			添加报投票记录
			<view class="add_vote_name">
				投票人姓名
				<input type="text" placeholder="请输入投票人姓名" />
			</view>
			<view class="add_vote_options">
				投票选项
				<xfl-select style="margin-top: 10rpx;border: 1px solid #dcdfe6;"
					:list="vote_optionList"
					:clearable="false"
					:style_Container="'height:24px;font-size: 13px'"
					:selectHideType="'hideAll'"
					@change='vote_options_Change'
					name='add_vote_options'>
				</xfl-select>
			</view>
			<view class="add_vote_concent">
				备注
				<textarea placeholder="请输入内容" 
				style="height: 130rpx; width: 100%; padding-left: 30rpx;"/>
			</view>
			<view class="add_vote_btn">
				<button type="default" size="mini" @click="open_sure">确定</button>
				<button type="default" size="mini" @click="open_cancel">取消</button>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data(){
			return {
				vote_optionList:[],
				open:false
			}
		},
		methods:{
			vote_options_Change(e){
				this.vote_options = e.newVal
				console.log(e.newVal,'newVal')
			},
			add_vote(){
				this.open = !this.open;
			},
			open_sure(e){
				console.log(e,'e')
			},
			open_cancel(){
				this.open = !this.open;
			},
		}
	}
</script>

<style>
</style>
