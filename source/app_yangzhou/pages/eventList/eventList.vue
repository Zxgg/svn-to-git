<template>
	<view class="content">
		<!-- <view></view> -->
		<view class="add">
			<view class="addinside">
				<image src="/static/icon/xj2.png" class="xj" @click="goInfo('',2)"/>
				<text>新建</text>
			</view>
		</view>
		<view class="border_"></view>
		<view class="search">
			<view class="search_view">
				<view class="search-left">
					<input type="text" placeholder="请输入事件名称" class="search_input" v-model="searchValue"/>
					<image src="../../static/images/sousuo.png" @click="search()"></image>
				</view>
				<view class="search_cancel" @click="cancel()">取消</view>
			</view>
			<view class="uni-list-cell1">
				<view class="qizhi">
					起止时间:
				</view>
				 <view class="date_flex">
					<image src="../../static/images/qi.png" class="date_img"/>
					<picker mode="date" :value="startTime" @change="bindStartDateChange">
						<view class="date_font">{{startTime}}</view>
					</picker>
				</view>
				
				<view class="uni-list-cell-db date_flex">
					<image src="../../static/images/zhi.png" class="date_img"/>
					<picker mode="date" :value="endTime" @change="bindEndDateChange">
						<view class="date_font">{{endTime}}</view>
					</picker>
				</view>
				<view class="date_sure" @click="search()">确认</view>
			</view>
		</view>
		<view class="list_td" v-for="(item,index) in eventList">
			<view class="event_center">
				<view class="first_flex">
					<view class="event_info_font">事件名称：{{item.eventName}}</view>
					<image src="../../static/images/jiantou.png" class="go_img" @click.stop="goInfo(item.id,1)" />
				</view>
				<view class="event_info_font">事件类型：{{getEventType(item.eventType)}}</view>
				<view class="event_info_font">事件程度：{{getDegree(item.eventDegree)}}</view>
				<view class="event_info_font">上报时间：{{item.reportingTime}}</view>
			</view>
			<view class="event_bottom">
				<view class="squre_flex">
					<view :class="getStateSqure(item.eventStatus)"></view>
					<view :class="getState(item.eventStatus)">{{getEventStatus(item.eventStatus)}}</view>
				</view>
				<view class="squre_flex">
					<view class="event_close" @click="closeClick(item,index)" v-if="item.eventStatus === '1'">删除</view>
					<view class="event_edit"  @click="goInfo(item.id,3)" v-if="item.eventStatus === '1'">编辑</view>
				</view>
			</view>
		</view>
		<view class="uni-loadmore" v-if="showLoadMore">{{loadMoreText}}</view>
	</view>
</template>

<script>
	var moment = require('moment');
	import { getEventListPage, getDicts, removeEvent } from '@/util/api.js'
	import { getDictLabel, dataFormat, getLastMonth } from '@/common/utilsFucn.js'
	export default {
		data() {
			return {
				eventList:[],
				pageNum:1,
				totalPage:0,
				pageSize:82,
				loadMoreText: '加载中...',
				showLoadMore: false,
				baseEventType:[],
				baseEventDgreeType:[],
				baseEventStatusSon:[],
				searchValue:'',
				startTime:'2021-04-01',
				endTime:'2021-04-01',
			}
		},
		created() {
			
			//获取社区事件类型字典
			this.getDicts('base_event_type',1);
			//获取社区事件程度字典
			this.getDicts('base_event_degree_type',2);
			//获取事件状态字典
			this.getDicts('base_event_status_son',3);
			this.startTime = this.getLastMonth(1);
			this.endTime = this.dataFormat(new Date());
			console.log('this.startTime',this.startTime);
			this.refresh();
		},
		onUnload() {
			// console.log('onUnload',this.timer);
			// clearInterval(this.timer);
			this.reset();
		},
		onReachBottom() {
			console.log("onReachBottom");
			if (this.pageNum > this.totalPage) {
				this.loadMoreText = "没有更多数据了!"
				return;
			}
			this.showLoadMore = true;
			setTimeout(() => {
				this.initData();
			}, 300);
		},
		methods: {
			goInfo(id,type){
				uni.navigateTo({
					url:"/pages/addEvent/addEvent?id=" + id +"&type=" + type
				})
			},
			initData() {
				let	data = {
					"pageNum": this.pageNum,
					"pageSize": this.pageSize,
					"eventName":this.searchValue,
					"beginTime":this.startTime + " 00:00:00",
					"endTimes":this.endTime + " 23:59:59",
					"orderByColumn":"create_time",
					"isAsc":"desc",
				}
				// debugger;
				getEventListPage(data).then((res) => {
					// debugger
					let self = this;
					if (res.code == '200') {
						// console.log('rows',res.rows);
						let data = res.rows;
						let total = res.total;
						self.totalPage = Math.ceil(total/self.pageSize);
						self.eventList = self.eventList.concat(data);
						self.pageNum++;
						// this.eventList = res.rows;
					}
				});
			},
			refresh(){
				this.reset();
				this.initData();//获取一页数据
			},
			reset(){
				this.eventList = [],
				this.loadMoreText = "加载更多",
				this.showLoadMore = false;
				this.pageNum = 1;
				this.totalPage = 0;
				this.totalCount = 0;
			},
			getDicts(dictType,type){
				getDicts(dictType).then((res) => {
					let self = this;
					if (res.code == '200') {
						// console.log('rows',res.data);
						let data = res.data;
						if(type == 1){
							self.baseEventType = data;
						}else if(type == 2){
							self.baseEventDgreeType = data;
						}else if(type == 3){
							self.baseEventStatusSon = data;
						}
					}
				});
			},
			dataFormat(date){
				return dataFormat(date,"yyyy-MM-dd");
			},
			getLastMonth(n){
				return moment(new Date()).subtract(n,'months').format('YYYY-MM-DD');
				// return getLastMonth(n);
			},
			search(){
				// this.refreshData();
				this.reset();
				this.initData();//获取一页数据
			},
			getEventType(type){
				// console.log(this.baseEventType);
				return getDictLabel(this.baseEventType,type)
			},
			getDegree(type){
				console.log(this.baseEventDgreeType);
				return getDictLabel(this.baseEventDgreeType,type)
			},
			getEventStatus(type){
				console.log(this.baseEventStatusSon)
				return getDictLabel(this.baseEventStatusSon,type)
			},
			getStateSqure(state){
				let className = 'state_squre_yipaifa';
				switch(state){
					case '1' : className='state_squre_weipaifa';break;
					case '2' : className='state_squre_yipaifa';break;
					case '3' : className='state_squre_weichuzhi';break;
					case '4' : className='state_squre_yichuzhi';break;
					case '5' : className='state_squre_weishenhe';break;
					case '6' : className='state_squre_yishenhe';break;
				}
				return className;
			},
			getState(state){
				let className = 'state_yipaifa';
				switch(state){
					case '1' : className='state_weipaifa';break;
					case '2' : className='state_yipaifa';break;
					case '3' : className='state_weichuzhi';break;
					case '4' : className='state_yichuzhi';break;
					case '5' : className='state_weishenhe';break;
					case '6' : className='state_yishenhe';break;
				}
				return className;
			},
			cancel(){
				this.searchValue ='';
				this.reset();
				this.initData();//获取一页数据
			},
			bindStartDateChange: function(e) {
				this.startTime = e.detail.value 
			},
			bindEndDateChange: function(e) {
				this.endTime = e.detail.value 
			},
			closeClick(item,index) {
				uni.showModal({
					title: '提示',
					content: '确定删除该事件',
					success: res => {
						if (res.confirm) {
							this.closeEvent(item,index);
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
			},
			closeEvent(item,index){
				removeEvent(item.id).then((res) =>{
					console.log(res);
					if (res.code == '200') {
						uni.showToast({
							title: '删除成功',
							icon: 'success',
							duration: 1000
						})
					}
					this.eventList.splice(index, 1);
					this.onReachBottom();
				})
			}
		}
	}
</script>

<style lang="scss">
	.content{
		width: 750rpx;
		
		.search{
			width: 100%;
			border-bottom: 2px solid #F4F5F9;
			padding-bottom: 4px;
			
			.search_view{
				background-color: #FFFFFF;
				line-height: 64px;
				height: 64px;
				font-size: 12px;
				font-family: PingFangSC-Regular, PingFang SC;
				font-weight: 400;
				display: flex;
				flex-direction: row;
				padding: 18px 0;
				
				
				.search-left{
					display: flex;
					flex-direction: row;
					border-radius: 14px;
					background-color: #F5F6F7;
					height: 28px;
					margin: auto;
					.search_input{
						width: 274px;
						color: #979797;
						text-align: center;
						height: 100%;
					}
					image{
						width: 28px;
						height: 28px;
					}
				}
				
				.search_cancel{	
					color: #666666;
					margin: auto;
				}
			}
			
			.uni-list-cell1 {
			    display: flex;
			    flex-direction: row;
				text-align: center;
				justify-content: space-between;
				
				.qizhi{
					width: 52px;
					height: 15px;
					font-size: 11px;
					font-family: PingFangSC-Regular, PingFang SC;
					font-weight: 400;
					color: #7FA3AC;
					line-height: 15px; 
					margin-left: 24px;
				}
				
				.date_flex{
					display: flex;
					flex-direction: row;
					margin-left: 2px;
					
					.date_img{
						height: 14px;
						width: 14px;
					}
				}
					
				
				
				.date_font{
					width: 78px;
					height: 15px;
					font-size: 11px;
					font-family: PingFangSC-Regular, PingFang SC;
					font-weight: 400;
					color: #999999;
					line-height: 15px;
				}
				
				.date_sure{
					width: 43px;
					height: 19px;
					background: #7FA3AC;
					border-radius: 10px;
					font-size: 12px;
					font-family: PingFangSC-Regular, PingFang SC;
					font-weight: 400;
					color: #FFFFFF;
					line-height: 19px;
					margin-top: -4px;
					margin-right: 22px;
				}
			}
		}
		
		.add {
			margin: 0;
			padding: 0;
			padding-left: 50rpx;
			padding-right: 50rpx;
			background:#E5EDEE;
			padding-bottom: 100rpx;
			padding-top: 100rpx;
			
			.addinside{
				background-color: white;
				width: 100rpx;
				height: 100rpx;
				margin-bottom: 30rpx;
				margin: 0 auto;
				text-align: center;
				color: #81A5AE;
				
				.xj{
					width: 100rpx;
					height: 100rpx;
				}
			}
		}
		
		.border_{
			width: 100%;
			height: 2px;
			background-color: #F4F5F9;
		}
		
		.list_td{
			// width: 100%;
			padding: 33rpx 25rpx;
			border-bottom: 1px solid #F4F5F9;
			
			.event_center{
				.first_flex{
					display: flex;
					flex-direction: row;
					justify-content: space-between;
					
					.go_img{
						width: 24rpx;
						height: 24rpx;
						margin-top: 8rpx;
					}
				}
				.event_info_font{
					// width: 610px;
					height: 37rpx;
					font-size: 26rpx;
					font-family: PingFangSC-Light, PingFang SC;
					font-weight: 300;
					color: #666666;
					line-height: 37rpx;
					margin-top: 3rpx;
				}
			}
			
			.event_bottom{
				display: flex;
				flex-direction: row;
				height: 40rpx;
				margin-top: 33rpx;
				justify-content: space-between;
				
				.squre_flex{
					display: flex;
					flex-direction: row;
				}
				
				//已派发
				.state_squre_yipaifa{
					width: 20rpx;
					height: 20rpx;
					background: #44D7B6;
					border-radius: 50%;
					margin-top: 10rpx;
				}
				.state_yipaifa{
					height: 40rpx;
					font-size: 28rpx;
					font-family: PingFangSC-Regular, PingFang SC;
					font-weight: 400;
					color: #44D7B6;
				}
				
				//未派发
				.state_squre_weipaifa{
					width: 20rpx;
					height: 20rpx;
					background: #BFBFBF;
					border-radius: 50%;
					margin-top: 10rpx;
				}
				.state_weipaifa{
					height: 40rpx;
					font-size: 28rpx;
					font-family: PingFangSC-Regular, PingFang SC;
					font-weight: 400;
					color: #BFBFBF;
				}
				
				
				//已处置
				.state_squre_yichuzhi{
					width: 20rpx;
					height: 20rpx;
					background: #32C5FF;
					border-radius: 50%;
					margin-top: 10rpx;
				}
				.state_yichuzhi{
					height: 40rpx;
					font-size: 28rpx;
					font-family: PingFangSC-Regular, PingFang SC;
					font-weight: 400;
					color: #32C5FF;
				}
				
				//未处置
				.state_squre_weichuzhi{
					width: 20rpx;
					height: 20rpx;
					background: #F7B500;
					border-radius: 50%;
					margin-top: 10rpx;
				}
				.state_weichuzhi{
					height: 40rpx;
					font-size: 28rpx;
					font-family: PingFangSC-Regular, PingFang SC;
					font-weight: 400;
					color: #F7B500;
				}
				
				//已审核
				.state_squre_yishenhe{
					width: 20rpx;
					height: 20rpx;
					background: #0091FF;
					border-radius: 50%;
					margin-top: 10rpx;
				}
				.state_yishenhe{
					height: 40rpx;
					font-size: 28rpx;
					font-family: PingFangSC-Regular, PingFang SC;
					font-weight: 400;
					color: #0091FF;
				}
				
				//未审核
				.state_squre_weishenhe{
					width: 20rpx;
					height: 20rpx;
					background: #F36957;
					border-radius: 50%;
					margin-top: 10rpx;
				}
				.state_weishenhe{
					height: 40rpx;
					font-size: 28rpx;
					font-family: PingFangSC-Regular, PingFang SC;
					font-weight: 400;
					color: #F36957;
				}
				
				.event_edit{
					width: 86rpx;
					height: 38rpx;
					background: #7FA3AC;
					border-radius: 21rpx;
					text-align: center;
					font-size: 24rpx;
					font-family: PingFangSC-Regular, PingFang SC;
					font-weight: 400;
					color: #FFFFFF;
					line-height: 38rpx;
					margin-left: 12px;
				}
				
				.event_close{
					width: 86rpx;
					height: 38rpx;
					background: #FFD662;
					border-radius: 19rpx;
					text-align: center;
					font-size: 24rpx;
					font-family: PingFangSC-Regular, PingFang SC;
					font-weight: 400;
					color: #FFFFFF;
					line-height: 38rpx;
				}
				
				.event_show{
					margin-left: 440rpx;
					width: 133rpx;
					height: 42rpx;
					background: #0091FF;
					border-radius: 21rpx;
					text-align: center;
					font-size: 24rpx;
					font-family: PingFangSC-Regular, PingFang SC;
					font-weight: 400;
					color: #FFFFFF;
					line-height: 42rpx;
				}
			}
			
		}
		
		.uni-loadmore{
			height:80rpx;
			line-height:80rpx;
			text-align:center;
			padding-bottom:30rpx;
			font-size: 12px;
			color: #999999;
		}
	}
</style>
