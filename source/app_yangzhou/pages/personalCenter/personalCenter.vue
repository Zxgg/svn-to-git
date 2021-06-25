<template>
	<view class="main">
		<view class="top">
			<image :src="src" mode=""></image>
			<view class="">
				<view class="proprietor">
					新业主
					<text>注销</text>
				</view>
				<view class="personalData">
					<text>个人资料</text>|<text>切换账户</text>
				</view>
			</view>
		</view>
			<view class="middle">
				<view class="owner">
					<image src="../../static/icon/rz.png"></image>
					<text>业主认证</text>
					<image src="../../static/icon/more.png" @tap='toOwnerDetail'></image>
				</view>
				<view class="owner">
					<image src="../../static/icon/xq.png" mode=""></image>
					<text>我的小区</text>
					<image src="../../static/icon/more.png"  @tap='toCommunityDetail'></image>
				</view>
				<view class="owner"toPropertyFeeDetail>
					<image src="../../static/icon/wyf.png" mode=""></image>
					<text>我的物业费</text>
					<image src="../../static/icon/more.png"  @tap='toPropertyFeeDetail'></image>
				</view>
			</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				src:'/static/images/xx3.jpg'
			}
		},
		methods: {
			toOwnerDetail(){
				uni.navigateTo({ 
					url:'../ownerCertification/ownerCertification'
				})
			},
			toCommunityDetail(){
				uni.navigateTo({ 
					url:'../myCommunity/myCommunity'
				})
			},
			toPropertyFeeDetail(){
				uni.navigateTo({ 
					url:'../toPropertyFeeDetail/toPropertyFeeDetail'
				})
			},
		}
	}
</script>

<style lang="scss" scoped>
@import "@/static/personalCenter.scss"
</style>
