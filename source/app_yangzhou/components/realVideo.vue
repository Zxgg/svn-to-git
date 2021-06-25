<template>
	<view class="PageContent">
		<view class="videoView">
			<!--  #ifdef APP-PLUS || APP-NVUE || APP-PLUS-NVUE || APP-VUE -->
			<video id='video1' ref="video1" class="video" :src="src" autoplay="true" duration="" :controls="showControls" :show-play-btn="showControls" 
			:show-fullscreen-btn="showControls" :danmu-list="list"
			 danmu-btn="false" enable-danmu="false" loop="false" muted="false" initial-time="0" direction="-90" show-mute-btn="true"
			 :show-progress="showProgress"
			 @play="onstart" @pause="onpause" @ended="onfinish" @error="onfail" @waiting="waiting" @timeupdate="timeupdate"
			 @fullscreenchange="fullscreenchange"></video>
			 <!--  #endif -->
			 <!--  #ifndef APP-PLUS || APP-NVUE || APP-PLUS-NVUE || APP-VUE -->
			 <view class="video-js" id='videoView' ref="video" style="width: 100%;height: 100%;">
			 </view>
			 <!--  #endif -->
		</view>
		<view>
			<view class="centerView">
				<view class="_flex">
					<image class="image_dang" src="../../static/icon-dangqian.png"></image>
					<p @click="showOrgSelectView('orgSelectView')">{{currentOrgName}}</p>
				</view>
				<view class="btn_ stopV"  @click="closeVideo()" v-if="playBack && playBack.src &&playBack.src.length>0">停止播放</view>
			</view>
			<uni-drawer ref="orgSelectView" mode="left" :width="320" @change="orgSelectViewChange($event,'orgSelectView')">
				<view class="drawerView">
					<ly-tree ref="orgTree"  show-radio highlight-current expand-current-node-parent  :tree-data="orgTree" :props="props" node-key="code" 
					@node-expand="handleNodeExpand"
					 @node-click="handleNodeClick" 
					 @radio-change="handleRadioChange">
					</ly-tree>
				</view>
			</uni-drawer>
		</view>
		<view class="td_view">
			<scroll-view :scroll-top="scrollTop" scroll-y="true" style="height:100%" @scrolltoupper="upper"
			 @scrolltolower="lower" @scroll="scroll">
				<!-- <view class="td_" > -->
					<view class="view_row " v-for="(item,index) in cameraList" :key="index">
						<view class="view_tr">
							<view class="td_bt td_bt_left" @tap="toDetail(item.id)">
								<image class="cameraIcon" v-if="item.uiStatus != null && item.uiStatus==1" src="../../static/shipinIcon.png" />
								<image class="cameraIcon" v-else src="../../static/shipinIcon_gray.png" />
								<view class="cap_name">{{TextLimit(item.captureName,18)}}</view>
							</view>
							<view class="td_bt td_bt_right">
								<view @click="playRealVideoClick(index,item.id,item.gbCode)" class="btn_ " :class="playReal && playBack.id===item.id?'btn_selected':'btn_noSelect'">实时视频</view>
								<view @click="showHistoryPlayOption(index,item.id,item.gbCode)" class="btn_ "  :class="playHistory && playBack.id===item.id?'btn_selected':'btn_noSelect'">历史回放</view>
							</view>
						</view>
						<!-- 输入历史时间段 -->
						<view class="divStackPanel VerticalMargin tr_td " v-if="showHistoryTimeSelect && selectId===item.id">
							<view class="HorizontalMargin historyInfoTxt historyInfoTitle">
								时间段
							</view>
							<view class="HorizontalMargin historyInfoTxt">
								<picker mode="date" :value="date_start" :start="BeginDate" :end="EndDate" @change="bindDateChange_start">
									<view class="uni-input">{{date_start}}</view>
								</picker>
							</view>
							<view class="HorizontalMargin historyInfoTxt">
								<picker mode="time" :value="time_start" start="09:01" end="21:01" @change="bindTimeChange_start">
									<view class="uni-input">{{time_start}}</view>
								</picker>
							</view>
							<view class="HorizontalMargin historyInfoTxt">
								~
							</view>
							<view class="HorizontalMargin historyInfoTxt">
								<picker mode="date" :value="date_end" :start="BeginDate" :end="EndDate" @change="bindDateChange_end">
									<view class="uni-input">{{date_end}}</view>
								</picker>
							</view>
							<view class="HorizontalMargin historyInfoTxt">
								<picker mode="time" :value="time_end" start="09:01" end="21:01" @change="bindTimeChange_end">
									<view class="uni-input">{{time_end}}</view>
								</picker>
							</view>
							<view>
								<image class="iconBtn" src="/static/play-on.png" v-if="playHistory && playBack.id===item.id" @click="playHistoryVideoCLick(item.gbCode)" />
								<image class="iconBtn" src="/static/play-off.png" v-else  @click="playHistoryVideoCLick(item.id,item.gbCode)" />
							</view>
						</view>
					</view>
					
				<!-- </view> -->
				
			</scroll-view>
		</view>
		<tabBar :pagePath="'/pages/flvVideo/realVideo'" :role="YDRole"></tabBar>
	</view>
</template>

<script>
	// #ifdef APP-PLUS || APP-NVUE || APP-PLUS-NVUE || APP-VUE 
	import appVideoMixJS from '@/pages/component/video/appVideo.js'
	// #endif
	// #ifndef APP-PLUS || APP-NVUE || APP-PLUS-NVUE || APP-VUE 
	import flvVideoMixJS from '@/pages/component/video/flvVideo.js'
	// #endif
	
	
	
	import configInfo from '@/config/configInfo.js'
	import LyTree from '@/components/ly-tree/ly-tree.vue'
	import {
		textLimit,
		getHlsStreamByCameraGBCodeForRecord,
		getHlsHistoryStreamByCameraCodeForContorl 
	} from '@/common/utilsFucn.js'
	import {
		getCameraList,
		getDeviceList,
		getRealVideoBySN,
		getHistoryVideoBySN
	} from '@/api/camera.js'
	import {
		findAllOrgTreeWeb
	} from '@/api/org.js'
	import {
		mapState,
		mapMutations
	} from 'vuex';
	export default {
		// #ifdef APP-PLUS || APP-NVUE || APP-PLUS-NVUE || APP-VUE
		mixins: [appVideoMixJS],
		// #endif
		// #ifndef APP-PLUS || APP-NVUE || APP-PLUS-NVUE || APP-VUE
		mixins: [flvVideoMixJS],
		// #endif
		components: {
			LyTree
		},
		data() {
			return {
				testVideoUrl_flv: 'http://10.16.183.203:10000/sms/34020000002000000001/flv/hls/34020000002000000237_34020000001320000001.flv',
				fil: true,
				title: '视频云',
				tableHeight: 700,
				cameraList: [],
				selectIndex: -1, //当前选中的行
				playIndex: -1, //当前播放历史视频的设备的index
				playHistory: false,
				playReal: false,
				showProgress:false,//显示进度条
				playBack:{
					id:'',
					stat:'',
					src:'',
					index:'',
					gbCode:'',
					sn:'',
					start:'',
					end:''
				},//恢复用的数据记录，当开始播放时设置此值，停止播放时清除此值
				selectId:'',
				showHistoryTimeSelect: false, //是否显示选择历史视频时间段的窗口
				currentPage: 0,
				pageSize: 5000,//设备单次最大加载量
				scrollTop: 0,
				old: {
					scrollTop: 0
				},
				list: [],
				
				date_start: '',
				date_end: '',
				BeginDate: '', //日期范围
				EndDate: '', //日期范围
				time_start: '12:01', //开始时间
				time_end: '12:05', //结束时间
				
				currentOrgName:'选择设备组',
				orgIsReady: false,
				orgTree: [], //组织机构树
				orgSelectViewOpen: false,
				currentSelectOrgCode:null,
				props: {
					label: 'name', // 指把数据中的‘personName’当做label也就是节点名称
					children: 'children', // 指把数据中的‘childs’当做children当做子节点数据
					isLeaf: 'isLeaf'
				}
			}
		},
		computed: {
			...mapState({
				YDTokenUserInfo: state => state.YDTokenUserInfo,
				YDRole:state=>state.YDTokenUserInfo && state.YDTokenUserInfo.role ?state.YDTokenUserInfo.role+'':''
			})
		},		
		watch: {
			YDTokenUserInfo: {
				immediate: true,
				handler(user) {
					if(user && user.role &&  user.role.length>0){
						//有用户信息
						console.log('用户信息已经取到',user)
						console.log('当前用户角色',this.YDRole)
						this.phone = user.phone;
						// this.initData()
						// this.switchTab('0')
					}else{
						console.log('用户信息未取到',user)
					}
				}
			}
		},
		mounted() {
			this.init()
		},
		onShow: function() {
			console.log('realVideo Show')
			this.tryReLoadUrl()
		},
		onHide: function() {
			console.log('realVideo Hide')
			this.stopVideo()
		},
		methods: {
			init() {
				this.init_data()
				this.init_orgTree()
			},
			init_data() {
				this.title = '实时视频'
				this.date_start = this.getDate({
					format: true
				})
				this.date_end = this.getDate({
					format: true
				})
				this.BeginDate = this.getDate('start') //日期范围
				this.EndDate = this.getDate('end') //日期范围
			},
			init_orgTree() {
				let self = this
				findAllOrgTreeWeb().then((response) => {
					if (response && response.status === '200' && response.data && response.data.length > 0) {
						self.orgTree = response.data
						self.orgIsReady = true
					} else {
						self.orgTree = []
						uni.showToast({
							title: '获取组织机构失败',
							duration: 1000
						})
					}
				})
			},
			getCameraByOrgCode(code,pageIndex=1){
				let self = this
				let queryData = {
				        currentPage:pageIndex,
				        pageSize: this.pageSize,
				        body: {
				          'orgId': code
				        }
				      }
				getDeviceList(queryData).then((response) => {
					// let jsonData = JSON.stringify(response)
					if (response && response.status === '200' && response.data && response.data.length > 0) {
						uni.showToast({
							title: '获取 '+response.data.length+' 个设备',
							duration: 1000
						})
						self.cameraList = response.data
						console.log('设备信息：',response.data)
					} else if(response && response.status === '200' && response.data && response.data.length === 0){
						uni.showToast({
							title: '获取 0 个设备',
							duration: 1000
						})
						self.cameraList = response.data
					}
					else {
						self.cameraList=[]
						uni.showToast({
							title: '获取设备失败',
							duration: 1000
						})
					}
				})
			},
			showOrgSelectView(name) {
				this.$refs[name].open()
				this.$nextTick(() => {
					if(this.currentSelectOrgCode && this.currentSelectOrgCode.length>0)
					this.$refs.orgTree.setCheckedKeys([this.currentSelectOrgCode])
				});
			},
			closeOrgSelectView(name) {
				this.$refs[name].close()
			},
			orgSelectViewChange(stat, name) {
				this.orgSelectViewOpen = stat
			},
			// uni-app中emit触发的方法只能接受一个参数，所以会回传一个对象，打印对象即可见到其中的内容
			handleNodeClick(obj) {
				// console.log('handleNodeClick', JSON.stringify(obj));
			},
			handleNodeExpand(obj) {
				// console.log('handleNodeExpand', JSON.stringify(obj));
			},
			handleRadioChange(obj) {
				if(obj.checked){
					let selectNodeCode=obj.node.key
					let selectNodeName=obj.node.label
					let selectNodeLevel=obj.node.level
					let selectNodeParentId=obj.node.parentId
					this.currentSelectOrgCode=selectNodeCode
					this.getCameraByOrgCode(this.currentSelectOrgCode)
					this.currentOrgName='当前设备组: '+selectNodeName
				}else{
					// let selectNodeCode=obj.node.key
					// let selectNodeName=obj.node.label
					// let selectNodeLevel=obj.node.level
					// let selectNodeParentId=obj.node.parentId
					// console.log('取消选中节点：', selectNodeLevel,selectNodeName,selectNodeCode);
				}
				
			},
			changePlayVideoStat(stat = '') {
				switch (stat) {
					case 'real':
						this.playHistory = false;
						this.playReal = true;
						this.showProgress=false;
						break;
					case 'history':
						this.playHistory = true;
						this.playReal = false;
						this.showProgress=true;
						break;
					default:
						this.playHistory = false;
						this.playReal = false;
						this.showProgress=false;
						break;
				}
			},
			getDate(type) {
				const date = new Date();

				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();

				if (type === 'start') {
					year = year - 10;
				} else if (type === 'end') {
					year = year + 10;
				}
				month = month > 9 ? month : '0' + month;;
				day = day > 9 ? day : '0' + day;
				return `${year}-${month}-${day}`;
			},
			bindDateChange_start(e) {
				this.date_start = e.detail.value
			},
			bindDateChange_end(e) {
				this.date_end = e.detail.value
			},
			bindTimeChange_start(e) {
				this.time_start = e.detail.value
			},
			bindTimeChange_end(e) {
				this.time_end = e.detail.value
			},
			showHistoryPlayOption(index, id,gbCode) {
				// uni.showToast({
				// 	title: '功能正在完善！',
				// 	duration: 1000
				// })
				// return false
				if(id.length>0 && this.selectId===id && this.showHistoryTimeSelect){
					console.log('关闭历史')
					this.showHistoryTimeSelect = false
				}else{
					console.log('显示历史',this.playBack.id,'=》》',id)
					this.selectIndex = index
					this.selectId=id
					this.showHistoryTimeSelect = true
				}
				
			},
			TextLimit(text, maxLength = 6) {
				return textLimit(text, maxLength)
			},
			getCameraIconPath(type) {
				if (!type) return ''
				let path = ''
				type = type + ''
				switch (type) {
					case '1':
						path = '/static/constantly_video.svg'
						break;
					case '2':path = '/static/constantly_video.svg'
						break;
					default:path = '/static/constantly_video.svg'
						break;
				}
				return path
			},
			choseImageSuccess(tempFilePaths) {},
			async playRealVideoClick(index, id,gbCode) {
				console.log('play real video:' + gbCode)
				this.selectIndex = index
				// if(configInfo.env==='1'){
				// 	gbCode='34020000001320000001'
				// }
				this.playBack.index=index
				this.playBack.id=id
				this.playBack.gbCode=gbCode
				this.playIndex = this.selectIndex
				this.changePlayVideoStat('real')
				this.showHistoryTimeSelect=false
				let returnData=await getHlsStreamByCameraGBCodeForRecord(gbCode)
				let url=returnData.url
				// if(configInfo.env==='1' && this.playBack.src===url){
				// 	url=this.src_other
				// }
				// url = this.src_other
				this.playBack.src=url
				this.playVideo(url)
				
			},
			async playHistoryVideoCLick(id,gbCode,start='',end='') {
				console.log('play history video:' + gbCode)
				if(start.length<1){
					start = this.date_start + ' ' + this.time_start
				}
				if(end.length<1){ 
					end = this.date_end + ' ' + this.time_end
				}
				// if(configInfo.env==='1'){
				// 	gbCode='34020000001320000001'
				// }
				this.playBack.start=start
				this.playBack.end=end
				this.playBack.id=id
				this.playBack.gbCode=gbCode
				this.playIndex = this.selectIndex
				this.changePlayVideoStat('history')
				// let url = getHistoryVideoBySN(sn, start, end).data
				let returnData=await getHlsHistoryStreamByCameraCodeForContorl(gbCode,start,end)
				let url=returnData.url
				this.playBack.src=url
				this.playVideo(url)
			},
			playVideo(url) {
				// if (!this.videoInitFlag) {
				// 	alert('视频播放组件初始化失败！')
				// 	return false
				// }
				this.setUrl(url)
				this.play()
			},
			//关闭视频，手动取消播放
			closeVideo(){
				this.playBack={
					id:'',
					stat:'',
					src:'',
					index:'',
					gbCode:'',
					sn:'',
					start:'',
					end:''
				}
				this.stopVideo()
				this.changePlayVideoStat()
			},
			stopVideo(){
				if(this.playHistory || this.playReal){
					this.src=''
					this.stop()
				}
			},
			tryReLoadUrl(){
				if(this.playBack.src &&this.playBack.src.length>0){
					//有需要恢复的视频
					this.playVideo(this.playBack.src)
					// this.playVideo(this.src_other)
					// setTimeout(()=>{
					// 	this.playVideo(this.playBack.src)
					// },350)
				}
			},
			/*滚动条 */
			upper: function(e) {
				// console.log(e)
			},
			lower: function(e) {
				// console.log(e)
			},
			scroll: function(e) {
				// console.log(e)
				this.old.scrollTop = e.detail.scrollTop
			},
			/*滚动条 */
		}
	}
</script>

<style lang="scss" >
	@import "@/styles/table.scss";
	@import "@/styles/userDiv.scss";
	@import "@/styles/button.scss";
	.uni-list::before{
		background-color: transparent;
	}
	.uni-list:after{
		background-color: transparent;
	}
	.uni-list--border:before{
		background-color: transparent;
	}
	.uni-list--border:after{
		background-color: transparent;
	}
	
	.videoView {
		width: 100%;
		height: 210px;
		background-color: black;
	}
	
	.video {
		width: 750rpx;
		/* #ifdef H5 */
		width: 100%;
		/* #endif */
		height: 400rpx;
		background-color: #808080;
	}

	.btn {
		margin-top: 5px;
		margin-bottom: 5px;
	}

	.videoView {
		width: 750rpx;
		/* #ifdef H5 */
		width: 100%;
		/* #endif */
		height: 400rpx;
		// 
	}

	.page_title_view {
		padding: 2px;
		text-align: center;

		border-top-width: 0px;
		border-left-width: 0px;
		border-right-width: 0px;
		border-bottom-width: 1px;
		border-style: solid;
		border-bottom-color: #0091ff;
		/* #ifdef H5 */
		border-bottom: 1px solid #0091ff;
		/* #endif */
	}

	.page_title {
		padding: 2px;
		padding: 0 20px;
		// text-align: center;
		font-size: 16px;
		// height: 35px;//不写高度  自动适应
		line-height: 44px;
		font-weight: 600;
		color: #0091ff;
		/* #ifdef H5 */
		display: inline-block;
		box-sizing: border-box;
		/* #endif */



	}

	.row_noWarp {
		font-size: 14px;
		background-color: #ffffff;
		/* #ifndef APP-PLUS-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		justify-content: flex-start;
		flex-wrap: wrap;
		padding-left: 20rpx;
		padding-right: 20rpx;
		padding-top: 0rpx;
		padding-bottom: 0rpx;
	}
	// .uni-drawer__content .uni-drawer--left .uni-drawer__content--visible{
	// 	background-color: rgba($color: #ffffff, $alpha: 0.6);
	// }
	.drawerView {
		padding: 15px;
		background-color: rgba($color: #ffffff, $alpha: 0.6);
	}

	.word-btn--hover {
		background-color: #4ca2ff;
	}

	.uni-drawer {
		position: absolute;
		top: 266px !important
	}


	.iconBtn {
		width: 40px;
		height: 40px;
		line-height:15rpx;
		border-top-width: 0px;
		border-bottom-width: 0px;
		border-left-width: 0px;
		border-right-width: 0px;
	}
	.cameraIcon{
		width: 40rpx;
		height: 40rpx;
	}
	.playVideo{
		border-top-left-radius: 20px;
		border-top-right-radius: 20px;
		border-bottom-right-radius: 20px;
		border-bottom-left-radius: 20px;
		background-clip: padding-box;
	}
	.stopVideoView{
		float: right;
		position: relative;
		
	}
	.yuanquan {
	    width: 35px;
		height: 30px;
		background-color: #d9efff;
	    border-top-left-radius: 20px;
	    border-top-right-radius: 20px;
	    border-bottom-right-radius: 20px;
	    border-bottom-left-radius: 20px;
	    background-clip: padding-box;
	}
	.word-btn-white{
		color: #0091ff;
		font-weight: 400;
		font-size: 14px;
	}
	.historyInfoTxt{
		font-size: 12px !important;
	}
	.historyInfoTitle{
		color: rgba(0, 0, 0, 0.25);
	}
	.uni-input{
		font-size: 12px !important;
	}
	.PageContent{
		width: 100%;
		height: 100%;
		overflow: hidden;
	}
	uni-page-body{
		height: 100%!important;
	}
	
	.org-image{
		width: 40rpx;
		height: 40rpx;
	}
	
	.view_tr{
		
		display: flex;
		// height: 40px;
		// line-height: 40px;
		flex-direction: row;
		justify-content: space-between;
		margin-top: 5px;
		border-bottom: 1px solid #e8e5e5;
		padding: 0 12px
	}
	.view_row{
		width: 750rpx;
	}
	.btn_{
		width: 72px;
		height: 26px;
		line-height: 26px;
		border-radius: 14px;
		font-size: 12px;
		text-align: center;
		margin-left: 10px;
		font-weight: 400;
		padding: 0 4px;			
		font-family: PingFangSC-Regular, PingFang SC;
	}
	
	.btn_selected{
		background-color: #0091FF;
		color: #FFFFFF;
	}
	
	.btn_noSelect{
		color: #0091FF;
		background-color: #FFFFFF;
		border: 1px solid;
	}
	
	.td_bt{
		display: flex;
		flex-direction: row;
		height: 40px;
		line-height: 40px;
		font-size: 12px;
	}
	
	.td_bt_left{
		margin-top: 10px;
	}
	
	.td_bt_right{
		margin-top: 8px;
	}
	
	.td_{
		height: 40px;
		width: 100%;
		display: flex;
		flex-direction: row;
		// border-bottom: 1px soid red;
		line-height: 40px;
		border-bottom: 1px solid #e8e5e5;
		// text-align: center;
	}
	
	.cap_name{
		margin-left: 6px;
		font-size: 12px;
	}
	
	.centerView{
		height: 46px;
		padding: 0 15px;
		display: flex;
		flex-direction: row;
		background-color: #FFFFFF;
		border-bottom: solid 4px #e8e5e5;
		justify-content: space-between;
	}
	
	.centerView p{
		font-size: 12px;
		line-height: 46px;
		font-weight: 400;
		color: #666666;
		margin-left: 10px;
	}
	
	.image_dang{
		width: 16px;
		height: 20px;
		margin-top: 12px;
	}
	
	._flex{
		display: flex;
		flex-direction: row;
	}
	
	.stopV{
		margin-top: 10px;
		background-color: #ea6b6b;
		color: #FFFFFF
	}
	
	.td_view{
		background-color: #FFFFFF;
		height: calc(100% - 250px);
		overflow-y: auto;
	}
</style>
