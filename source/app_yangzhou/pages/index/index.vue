<template>
	<view class="content">
		<view class="topbanner">
			<image src="../../static/images/bg.png" />
		</view>
		<view class="swipertxt">
			<text>通知公告</text>
			<swiper 
			autoplay="true" 
			interval="5000"
			circular='true'
			duration='10000'
			vertical
			style="height: 50rpx; margin-left: 20%;">
				<swiper-item 
				v-for="(item,index) in communityNoticeTable" :key="index">
				{{item.title}}
				</swiper-item>
			</swiper>
		</view>
		<view class="main">
			<view class="row1">
				<view
				class="signin" @tap='toSignin'>
				<image src="../../static/images/dlzc.png" />
					<view >
						登记注册
					<image src="../../static/icon/jt.png" />
					</view>
				</view>
				
				<view 
				class="visitor" @tap='toMakingAnApointment'>
				<image src="../../static/images/fkyy.png" />
					<view class="">
						访客预约
					<image src="../../static/icon/jt.png" />
					</view>
				</view>
			</view>
			<view class="row2">
				<view
				class="communityNotice" @tap='toCommunityNotice'>
				<image src="../../static/images/sqgg.png" />
					<view class="">
						社区公告
						<image src="../../static/icon/jt.png" />
					</view>
				</view>
				 
				 <view class="vote" @tap='toVotingInformation'>
					<image src="../../static/images/tpgl.png" />
						<view class="">
							投票管理
							<image src="../../static/icon/jt.png" />
						</view>
				 </view>
			</view> 
			<view class="row3">
				<view class="repairRecord" @tap='toRepairRecord'>
					<image src="../../static/images/bsbx.png" />
						<view class="">
							报事报修
							<image src="../../static/icon/jt.png" />
						</view>
				 </view>
				<view class="complaints" @tap='toComplaints'>
					<image src="../../static/images/tsyj.png" />
						<view class="">
							投诉意见
							<image src="../../static/icon/jt.png" />
						</view>
				</view>
			</view>
			<view class="row3">
				<view class="repairRecord" @tap='toEventList'>
					<image src="../../static/images/sjsb.png" />
				 		<view class="">
				 			事件上报
							<image src="../../static/icon/jt.png" />
				 		</view>
				 </view>
				
				<view class="complaints" @tap='toAdminiAudit'>
					<image src="../../static/images/xzsh.png" />
						<view class="">
							行政审核
							<image src="../../static/icon/jt.png" />
						</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		communityNoticeApi
	} from '../../util/api.js'
	export default {
		data() {
			return {
				indicatorDots: true,
				autoplay: true,
				interval: 5000,
				duration: 3000,
				communityNoticeTable:[]
			}
		},
		onLoad() {
			this.getCommunityNotice()
		},
		methods: {
			toSignin(){
				uni.navigateTo({ 
					url:"/pages/signin/signin"
				})
			},
			toMakingAnApointment(){
				uni.navigateTo({
					url:"/pages/makingAnApointment/makingAnApointment"
				})
			},
			toCommunityNotice(){
				uni.navigateTo({
					url:"/pages/communityNotice/communityNotice"
				})
			},
			toVotingInformation(){
				uni.navigateTo({
					url:"/pages/votingInformation/votingInformation"
				})
			},
			toRepairRecord(){
				uni.navigateTo({
					url:"/pages/repairRecord/repairRecord"
				})
			},
			toEventList(){
				uni.navigateTo({
					url:"/pages/eventList/eventList"
				})
			},
			toComplaints(){
				uni.navigateTo({
					url:"/pages/complaints/complaints"
				})
			},
			toAdminiAudit(){
				uni.navigateTo({
					url:"/pages/adminiAudit/adminiAudit"
				})
			},
			getCommunityNotice(){
				let self = this
				communityNoticeApi(this.form).then((res) => {
					if(res.code === 200){
						this.communityNoticeTable=res.rows
						console.log(this.communityNoticeTable,'this.communityNoticeTable')
					}
				})
			},
		}
	}
</script>
<style>
	@import "@/static/index.scss";
</style>
