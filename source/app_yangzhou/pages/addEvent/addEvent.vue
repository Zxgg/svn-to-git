<template>
		<view class="content">
			<view class="back"></view>
			<uni-drawer ref="showLeft" mode="left" :width="320" @change="change($event,'showLeft')">
				<view class="drawerView">
					<ly-tree :tree-data="data"
						:props="defaultProps" 
						ref="tree" 
						node-key="value" 
						show-radio 
						checkOnClickNode
						@check="handleCheck"
					/>
				</view>
			</uni-drawer>
			<view class="info_">
				<view class="info_view">社区名称：
					<view class="uni-list-cell-db">
						<picker @change="communityChange" :value="communityIndex" :range="communityList" range-key="name">
							<view class="uni-input">{{communityList[communityIndex].name}}</view>
						</picker>
					</view>
				</view>
				<view class="info_view">小区名称：
					<view class="uni-list-cell-db">
						<picker @change="communityChildChange" :value="communityChildIndex" :range="communityChildList" range-key="name">
							<view class="uni-input">{{communityChildList[communityChildIndex].name}}</view>
						</picker>
					</view>
				</view>
				<view class="info_view">事件名称：
					<view class="uni-input-wrapper" style="width: 240px;">
					    <input class="uni-input" focus placeholder="请输入事件名称" style="font-size: 14px;" v-model="event.eventName" />
					</view>
				</view>
				<view class="info_view" @click="openDrawer">事件类型：{{getEventType(event.eventType)}}
				</view>
				<!-- <uni-data-picker class="info_view" placeholder="请选择班级" popup-title="请选择所在地区" :localdata="dataTree" v-model="classes">
				</uni-data-picker> -->
				<view class="info_view">事件地址：
				<!-- {{event.address}} -->
					<view class="uni-input-wrapper" style="width: 240px;">
						<input class="uni-input" focus placeholder="请输入事件地址" style="font-size: 14px;" v-model="event.address" />
					</view>
				</view>
				<view class="info_view">事件程度：
					<view class="uni-list-cell-db">
						<picker @change="degreeChange" :value="baseEventDgreeIndex" :range="baseEventDgreeType" range-key="dictLabel">
							<view class="uni-input">{{baseEventDgreeType[baseEventDgreeIndex].dictLabel}}</view>
						</picker>
					</view>
				</view>
				<view class="info_view">上报人姓名：
					<view class="uni-input-wrapper" style="width: 240px;">
						<input class="uni-input" focus placeholder="请输入上报人姓名" style="font-size: 14px;" v-model="event.createBy" />
					</view>
				</view>
				<view class="info_view">上报人手机号：
					<view class="uni-input-wrapper" style="width: 240px;">
						<input class="uni-input" focus placeholder="请输入上报人手机号" maxlength="11" type="number" style="font-size: 14px;" v-model="event.createByPhone" />
					</view>
				</view>
				<view class="info_view" v-if=" type != '2' ">上报时间：{{event.reportingTime}}</view>
				<view class="info_view"  v-if=" type != '2' ">事件状态：{{getEventStatus(event.eventStatus)}}</view>
				
				<view class="info_view">
					上报证明：
					<view class="upload_view">
						<view class="uni-uploader-info">{{imageList.length}}/5</view>
						<view class="uni-uploader-info " @click="clearImageList()">
							<!-- <button class="button button_blue" @click="clearImageList()" ></button> -->
							清空
						</view>
					</view>
				</view>
				<view class="uni-list list-pd">
					<view class="uni-list-cell cell-pd">
						<view class="uni-uploader">
							<view class="uni-uploader-body">
								<view class="uni-uploader__files">
									<block v-for="(image,index) in imageList" :key="index">
										<view class="uni-uploader__file">
											<image class="uni-uploader__img" :src="image" :data-src="image" @tap="previewImage(imageList,index)"></image>
										</view>
									</block>
									<view class="uni-uploader__input-box">
										<view class="uni-uploader__input" @tap="chooseImage"></view>
									</view>
								</view>
							</view>
						</view>
					</view>
				</view>
				
				<view class="info_text_view">内容:
					<view class="info_text">
						<textarea placeholder="请输入内容..." placeholder-style="color: #999999;font-size: 20rpx;" class="textArea"  v-model="event.content"/>
					</view>
				</view>
				<!-- <view class="info_text_view">备注:
					<view class="info_text">
						<textarea placeholder="请输入备注..." placeholder-style="color: #999999;font-size: 20rpx;" class="textArea"  v-model="event.remarks"/>
					</view>
				</view> -->
				<!-- <progress percent="uploadPercent" active stroke-width="3" v-if="uploadFlag"/> -->
				<view class="button" @click="beginSbmit()"  v-if=" type != '1' ">立即上报</view>
				<view v-if="eventState == '5' | eventState == '6'">
				<view class="back"></view>
				<view class="info_view">处置人：{{rusultInfo.createBy}}</view>
				<view class="info_view">处置结果：{{rusultInfo.remarks}}</view>
				<view class="info_view">附件：</view>
				<block v-for="(image,index) in resultImgList" :key="index">
					<view class="uni-uploader__file">
						<image class="uni-uploader__img" :src="image" :data-src="image" @tap="previewImage(resultImgList,index)"></image>
					</view>
				</block>
				</view>
			</view>
		</view>
</template>

<script>
	var moment = require('moment');
	import LyTree from '@/components/ly-tree/ly-tree.vue'
	import { addEvent, showEvent, getDicts, communityApi, communityChildApi, editEvent, divisionTree, getgroupstatus } from '@/util/api.js'
	import { getDictLabel} from '@/common/utilsFucn.js'
	import configInfo from '@/config/configInfo.js';
	import * as TranslateImage from '@/common/translate-image.js';
	var sourceType = [
		['camera'],
		['album'],
		['camera', 'album']
	]
	var sizeType = [
		['compressed'],
		['original'],
		['compressed', 'original']
	]
	export default {
		components: {
			LyTree
		},
		data() {
			return {
				data: [],
				defaultProps: {
					children: 'children',
					label: 'label'
				},
				event:{
					"eventName":"",
					"address":"",
					"eventType":'',
				},
				rusultInfo:{
				},
				picList:[],
				imageList: [],
				newImageList:[],
				oldImageList:[],
				resultImgList:[],
				sourceTypeIndex: 2,
				sourceType: ['拍照', '相册', '拍照或相册'],
				sizeTypeIndex: 0,
				sizeType: ['压缩', '原图', '压缩或原图'],
				count: [1, 2, 3, 4, 5],
				id:'',
				type:'',
				eventState:'1',
				//事件类型
				baseEventType:[],
				
				//事件程度
				baseEventDgreeType:[{"dictLabel":11}],
				baseEventDgreeIndex:0,
				eventDgree:'1',
				//事件状态
				baseEventStatusSon:[],
				// eventStatusSonIndex:0,
				eventStatus:1,
				//社区
				communityList:[{"name":"11"}],
				communityIndex:0,
				communityId:'',
				//小区
				communityChildList:[{"name":"11"}],
				communityChildIndex:0,
				communityChildId:'',
				
				uploadPercent:0,//上传进度 0-100
				uploadFlag:false,//上传开始标志 
				uploadCount:0,//上传成功计数
			}
		},
		onLoad(e) {
			this.id = e.id;
			//type==1 查看 type==2 新增 type==3 编辑
			this.type = e.type;
		},
		created(){
			//获取社区
			this.getCommunity();
			// //获取社区事件类型字典
			this.getDicts('base_event_type',1);
			// //获取社区事件程度字典
			this.getDicts('base_event_degree_type',2);
			// // 获取事件状态字典
			this.getDicts('base_event_status_son',3);
			this.divisionTree();
			// debugger;
			if(this.type != '2'){
				this.showInfo();
			}
			
		},
		methods: {
			
			beginSbmit(){
				// debugger;
				let self = this;
				if(!self.event.eventName){
					// console.log('事件名称不能为空');
					uni.showModal({
						content: "上报事件名称不能为空！！",
						confirmText: "确定",
						showCancel: false,
						// cancelText: "取消",	
					})
					return;
				}
				if(!self.event.address){
					// console.log('事件名称不能为空');
					uni.showModal({
						content: "上报事件地址不能为空！！",
						confirmText: "确定",
						showCancel: false,
						// cancelText: "取消",	
					})
					return;
				}
				if(!self.event.createBy){
					// console.log('事件名称不能为空');
					uni.showModal({
						content: "上报人姓名不能为空！！",
						confirmText: "确定",
						showCancel: false,
						// cancelText: "取消",	
					})
					return;
				}
				if(!self.event.createByPhone){
					// console.log('事件名称不能为空');
					uni.showModal({
						content: "上报人手机号不能为空！！",
						confirmText: "确定",
						showCancel: false,
						// cancelText: "取消",	
					})
					return;
				}
				// debugger;
				if(this.newImageList.length > 0){
					this.uploadImg();
				}else{
					this.submit();
				}
			},
			submit(){
				// debugger;
				let self = this;
				if(this.newImageList.length > 0){
					let disposeImageUrls = "";
					let oldLength = this.oldImageList.length;
					for(var num = 0;num<oldLength;num++){
						var url = this.oldImageList[num];
						// console.log(url);
						// if(num<(length-1)){
						disposeImageUrls = disposeImageUrls + url +",";
						// }else{
							// disposeImageUrls = disposeImageUrls + url;
						// }
					}
					
					let length = this.picList.length;
					for(var num = 0;num<length;num++){
						var url = this.picList[num];
						// console.log(url);
						if(num<(length-1)){
							disposeImageUrls = disposeImageUrls + url +",";
						}else{
							disposeImageUrls = disposeImageUrls + url;
						}
					}
					self.event.fileId = disposeImageUrls;
					// return;
				}
				// debugger;
				
				// console.log('disposeImageUrls',disposeImageUrls);
				// return null;
				//type==2 新增 type==3 编辑
				self.event.communityId=self.communityList[self.communityIndex].id;
				self.event.communityChildId=self.communityChildList[self.communityChildIndex].id;
				self.event.eventDegree=self.baseEventDgreeType[self.baseEventDgreeIndex].dictValue;
				self.event.dealWithType='2';
				
				if(self.type == 2){
					let date = moment().format("YYYY-MM-DD HH:mm:ss");
					self.event.eventStatus=0;
					self.event.reportingTime=date;
					addEvent(self.event).then((res) => {
						// let self = this;
						if(res.code === 200){
							uni.showToast({
								title: '上报成功',
								icon: 'success',
								duration: 1000
							})
							setTimeout(() => {
								//跳转到工单页面
								uni.reLaunch({
								   url: '/pages/eventList/eventList'
								});
							}, 1000);
							// this.communityNoticeTable=res.rows
							// console.log(this.communityNoticeTable,'this.communityNoticeTable')
						}
					})
				}
				if(self.type == 3){
					editEvent(self.event).then((res) => {
						// let self = this;
						if(res.code === 200){
							uni.showToast({
								title: '修改成功',
								icon: 'success',
								duration: 1000
							})
							setTimeout(() => {
								//跳转到工单页面
								uni.reLaunch({
								   url: '/pages/eventList/eventList'
								});
							}, 1000);
							// this.communityNoticeTable=res.rows
							// console.log(this.communityNoticeTable,'this.communityNoticeTable')
						}
					})
				}		
			},
			showInfo(){
				if(this.id){
					let self = this;
					// debugger;
					showEvent(this.id).then((res) => {
						if(res.code === 200){
							self.event = res.data;
							console.log('res111',res);
							self.setData(self.event);
						}
					})
				}
			},
			getDicts(dictType,type){
				getDicts(dictType).then((res) => {
					let self = this;
					if (res.code == '200') {
						console.log('rows',res.data);
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
			
			getEventType(type){
				// debugger;
				if(type == ""){
					return "请选择事件类型";
				}
				// debugger;
				return getDictLabel(this.baseEventType,type)
			},
			getDegree(type){
				return getDictLabel(this.baseEventDgreeType,type)
			},
			getEventStatus(type){
				return getDictLabel(this.baseEventStatusSon,type)
			},
			getCommunity(){
				communityApi().then((res) => {
					console.log(res);
					this.communityList = res.rows;
					let communityId = this.communityList[this.communityIndex].id;
					this.getCommunityChild(communityId);
				})
			},
			communityChange(e) {
				// debugger;
				this.communityIndex = e.detail.value
				let communityId = this.communityList[this.communityIndex].id;
				this.getCommunityChild(communityId);
			},
			degreeChange(e){
				this.baseEventDgreeIndex = e.detail.value;
			},
			communityChildChange(e){
				this.communityChildIndex = e.detail.value;
			},
			getCommunityChild(communityId){
				// debugger;
				communityChildApi(communityId).then((res) => {
					this.communityChildList = res.rows;
					console.log('111',res);
				})
			},
			setData(event){
				//社区
				let communityId = event.communityId;
				communityChildApi(communityId).then((res) => {
					this.communityChildList = res.rows;
					console.log('111',res);
					// debugger;
					let communityChildId = event.communityChildId;
					let length1 = this.communityChildList.length;
					
					for(var i=0;i<length1;i++){
						let id = this.communityChildList[i].id;
						console.log(id);
						if(communityChildId === id){
							this.communityChildIndex = i;
							console.log('j',i);
							break;
						}
					}
				})
				
				//小区
				let length2 = this.communityList.length;
				for(var i=0;i<length2;i++){
					let community_id = this.communityList[i].id;
					if(communityId == community_id){
						this.communityIndex = i;
						console.log('i',i);
						break;
					}
				}
				
				// debugger;
				//事件程度
				let length3 = this.baseEventDgreeType.length;
				let event_degree = this.event.eventDegree;
				console.log(this.baseEventDgreeType);
				for(var i=0;i<length3;i++){
					let dictValue = this.baseEventDgreeType[i].dictValue;
					if(event_degree == dictValue){
						this.baseEventDgreeIndex = i;
						console.log('m',i);
						break;
					}
				}
				// debugger;
				//附件
				let file_arr = event.fileId;
				this.imageList = file_arr.split(",");
				this.oldImageList = file_arr.split(",");
				console.log(this.imageList);
				
				//判断是否需要查询处置或者审核的结果
				// debugger;
				this.eventState = event.eventStatus;
				if(this.eventState == '5' | this.eventState == '6'){
					this.getgroupstatus(this.id);
				}
			},
			bindMultiPickerColumnChange: function(e) {
				console.log('修改的列为：' + e.detail.column + '，值为：' + e.detail.value)
				this.multiIndex[e.detail.column] = e.detail.value
				console.log('当前选择为',this.multiIndex);
			},
			handleCheck(obj) {
				this.event.eventType = obj.data.value;
			},
			openDrawer(){
				this.$refs['showLeft'].open();
			},
			divisionTree(){
				// debugger;
				divisionTree().then((res) => {
					this.data = res.data;
					// console.log('tree',res);
				})
			},
			change(e, type) {
				console.log((type === 'showLeft' ? '左窗口' : '右窗口') + (e ? '打开' : '关闭'));
				this[type] = e
			},
			previewImage(pathList,current){
				// debugger;
				// 预览图片
				uni.previewImage({
					current:current,
					urls: pathList,
				});
			},
			chooseImage: async function() {
				// debugger;
				// #ifdef APP-PLUS
				// TODO 选择相机或相册时 需要弹出actionsheet，目前无法获得是相机还是相册，在失败回调中处理
				if (this.sourceTypeIndex !== 2) {
					let status = await this.checkPermission();
					if (status !== 1) {
						return;
					}
				}
				// #endif
				let self=this
				if (this.imageList.length === 5) {
					uni.showModal({
						content: "附件最多可以上传5张图片！！",
						confirmText: "确定",
						cancelText: "取消"
					})
					return;
				}
				// debugger;
				uni.chooseImage({
					sourceType: sourceType[self.sourceTypeIndex],
					sizeType: sizeType[self.sizeTypeIndex],
					count: self.imageList.length + self.count[self.countIndex] > 5 ? 5 - self.imageList.length : self.count[self.countIndex],
					success: (res) => {
						for(var i=0;i<res.tempFilePaths.length;i++){
							// TranslateImage.translate(res.tempFilePaths[i],(url)=>{
							// 	self.imageList.push(url);
							// });
							self.imageList.push(res.tempFilePaths[i]);
							self.newImageList.push(res.tempFilePaths[i]);
						}
					},fail: (err) => {
						// console.log("err: ", err);
						// #ifdef APP-PLUS
						if (err['code'] && err.code !== 0 && self.sourceTypeIndex === 2) {
							self.checkPermission(err.code);
						}
						// #endif
						// #ifdef MP
						if (err.errMsg.indexOf('cancel') !== '-1') {
							return;
						}
						uni.getSetting({
							success: (res) => {
								let authStatus = false;
								switch (self.sourceTypeIndex) {
									case 0:
										authStatus = res.authSetting['scope.camera'];
										break;
									case 1:
										authStatus = res.authSetting['scope.album'];
										break;
									case 2:
										authStatus = res.authSetting['scope.album'] && res.authSetting['scope.camera'];
										break;
									default:
										break;
								}
								if (!authStatus) {
									uni.showModal({
										title: '授权失败',
										content: '应用需要从您的相机或相册获取图片，请在设置界面打开相关权限',
										success: (res) => {
											if (res.confirm) {
												uni.openSetting()
											}
										}
									})
								}
							}
						})
						// #endif
					}
				})
			},
			clearImageList(){
				// this.picList.splice(0,this.picList.length)
				this.picList=[]
				this.imageList=[]
			},
			
			uploadImg(){
				// debugger;
				let self = this;
				self.uploadFlag=true;
				for(var i=0;i<self.newImageList.length;i++){
					let imageSrc = self.newImageList[i];
					console.log(imageSrc);
					// return;
					let url = configInfo.imageUrl;
					// console.log('url',url);
					uni.uploadFile({
						url: url,
						filePath: imageSrc,
						fileType: 'image',
						name: 'file',
						success: (resp) => {
							// debugger;
							console.log(resp);
							if(resp.statusCode == '200'){
								let data = JSON.parse(resp.data);
								console.log(data);
								self.picList.push(data.data);
								self.uploadCount++;
								// self.uploadPercent = (self.uploadCount*100)/self.imageList.length;
								if(self.uploadCount == self.newImageList.length){
									self.submit();
								}
							}else if(resp){
								uni.showToast({
									title: '上传失败：'+resp.msg,
									icon: 'success',
									duration: 1000
								})
								
							}
							
						},
						fail: (err) => {
							// console.log('uploadImage fail', err);
							uni.showModal({
								content: err.errMsg,
								showCancel: false
							});
							
						}
					});
				}
				
			},
			getgroupstatus(id){
				let self = this;
				// debugger;
				getgroupstatus(id).then((res) => {
					console.log('res',res);
					let data = res.data;
					let result = "";
					if(self.eventState == '5'){
						result = data[1];
					}else if(self.eventState == '6'){
						result = data[2];
					}
					self.rusultInfo = result;
					// self.rusultInfo = result[result.length-1];
					console.log('rusultInfo',self.rusultInfo);
					let fileId_ = self.rusultInfo.fileId;
					// let fileIds = fileId;
					// debugger;
					if(fileId_){
						let fileId= JSON.parse(fileId_);
						for(let i=0;i<fileId.length;i++){
							self.resultImgList.push(fileId[i].url);
						}
					}
					
					// 
					console.log('resultImgList',self.resultImgList);
					// if (res.status == '200') {
					// 	uni.showToast({
					// 		title: '处置成功',
					// 		icon: 'success',
					// 		duration: 1000
					// 	})
					// 	self.state = '3';
					// 	self.canProcess = false;
					// 	setTimeout(() => {
					// 		//跳转到工单页面
					// 		uni.reLaunch({
					// 		   url: '/pages/workerOrderMangement/myJobOrder?tableId=2'
					// 		});
					// 	}, 1000);
						
					// 	// uni.navigateTo({
					// 	// 	url: '/pages/workerOrderMangement/workerOrderMangement?tableId=2'
					// 	// })
					// }
				});
			},
			processt(){
				if(!this.processtFlag){
					this.processtFlag=true
				}else{
					return false
				}	
				this.alarm.disposeDescribe = this.disposeDescribe;
				let disposeImageUrls = "";
				let length = this.picList.length;
				for(var num = 0;num<length;num++){
					var url = this.picList[num];
					// console.log(url);
					if(num<(length-1)){
						disposeImageUrls = disposeImageUrls + url +",";
					}else{
						disposeImageUrls = disposeImageUrls + url;
					}
				}
				this.alarm.disposeImageUrls = disposeImageUrls;
				this.alarm.alarmReason = this.alarmReason;
				this.alarm.state = 3;
				let self=this
				// console.log('this.alarm',this.alarm);
				updateOrderDetails(this.alarm).then((res) => {
					console.log('res',res);
					if (res.status == '200') {
						uni.showToast({
							title: '处置成功',
							icon: 'success',
							duration: 1000
						})
						self.state = '3';
						self.canProcess = false;
						setTimeout(() => {
							//跳转到工单页面
							uni.reLaunch({
							   url: '/pages/workerOrderMangement/myJobOrder?tableId=2'
							});
						}, 1000);
						
						// uni.navigateTo({
						// 	url: '/pages/workerOrderMangement/workerOrderMangement?tableId=2'
						// })
					}
				});
			},
		}
	}
</script>
<style lang="scss">
	uni-page-body{
		background: #FAFBFC;
		width: 750rpx;
		.content{
			// width: 100%;
			// margin-top: 20rpx;
			.back{
				width: 100%;
				height: 24rpx;
			}
			.info_{
				// margin-top: 20rpx;
				width: 702rpx;
				// height: 907rpx;
				// height: 100%;
				background: #FFFFFF;
				box-shadow: 0px 1px 1px 0px rgba(216,216,216,1);
				border-radius: 20rpx;
				margin: auto;
				padding-bottom: 80rpx;
				.info_view{
					// width: 608px;
					margin-left: 25rpx;
					height: 84rpx;
					font-size: 28rpx;
					font-family: PingFangSC-Regular, PingFang SC;
					font-weight: 400;
					color: #333333;
					line-height: 84rpx;
					border-bottom: 4rpx solid #FAFBFC;
					display: flex;
					
					
					.upload_view{
						display: flex;
						flex-direction: row;
						margin-left: 195px;
						width: 73px;
						justify-content: space-around;
					}
				}
				
				.info_text_view{
					width: 100%;
					display: flex;
					flex-direction: row;
					// justify-content: space-around;
					font-size: 28rpx;
					font-family: PingFangSC-Regular, PingFang SC;
					font-weight: 400;
					color: #333333;
					padding: 20rpx;
					border-bottom: 4rpx solid #FAFBFC;
					.info_text{
						width: 518rpx;
						height: 171rpx;
						background: #FAFBFC;
						border-radius: 20px;
						margin-left: 31rpx;
						
						.textArea{
							width: 100%;
							height: 100%;
						}
					}
				}
				
				.button{
					margin-top: 124rpx;
					margin-left:227rpx;
					width: 247rpx;
					height: 63rpx;
					background: #0091FF;
					border-radius: 32px;
					font-size: 28rpx;
					font-family: PingFangSC-Regular, PingFang SC;
					font-weight: 400;
					color: #FFFFFF;
					text-align: center;
					line-height: 63rpx;
				
				}
			}
			
		}
		
		.drawerView {
			padding: 15px;
			background-color: rgba($color: #ffffff, $alpha: 0.6);
			// height: 400px;
		}
	}
	

</style>
