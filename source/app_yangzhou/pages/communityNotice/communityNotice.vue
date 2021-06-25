<template>
	<view 
		class="main">
		<view class="table">
			<view class="tr_td" 
			v-for="(item,index) in communityNoticeTable" :key="index">
				<view v-if="item.type === '通知'"
				class="type" >
					{{item.type}}
				</view>
				<view v-if="item.type === '公告'"
				class="type2" >
					{{item.type}}
				</view>
				<view class="">
					<view class="td" @tap="toDetail(item.id)">
						{{item.title}}
					</view>
					<view class="td">
						{{item.status}}
					</view>
					<view class="td">
						{{item.createTime}}
					</view>
				</view>
			</view>
		</view>
	</view>
</template>
<script>
	import {
		communityNoticeApi,getDicts
	} from '../../util/api.js'
	import {
		selectDictLabel
	} from '../../static/format.js'
	export default {
		data() {
			return {
				noticeList:[],
				//字典翻译
				typeList:[],
				noticeOptions:[],
				communityNoticeTable:[],
				form:{
					// type:'通知',
					title:'',
				},
			}
		},
		created() {
			getDicts("sys_notice_type").then((response) => {
				 this.noticeOptions = response.data
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
			     this.noticeList = list_;
			    });
			getDicts("sys_notice_status").then(response => {
			      this.typeList = response.data;
			    });
		},
		mounted() {
			this.getCommunityNotice()
		},
		methods: {
			// formReset: function(e) {
			//     console.log('清空数据')
			// },
			toDetail(id){
				// console.log('id',id);
				uni.navigateTo({ 
					url:'./communityNoticesDetail?id='+id
				})
			},
			type_Change(e){
				this.form.type = e.orignItem.code
				console.log(this.form.type,'this.form.type')
			},
			typeFormat: function (value) {
			      return selectDictLabel(this.typeList, value);
			    },
			noticeFormat: function (value) {
			      return selectDictLabel(this.noticeOptions, value);
			    },
			getCommunityNotice(){
				let self = this
				communityNoticeApi().then((res) => {
					if(res.code === 200){
						res.rows.forEach(data => {
							data.status = self.typeFormat(data.status)
							data.type = self.noticeFormat(data.type)
						})
							this.communityNoticeTable=res.rows
							console.log('this.communityNoticeTable',this.communityNoticeTable)
					}
				})
			},
			// search(){
				
			// 	this.getCommunityNotice(this.form)
			// },
		}
	}
</script>

<style lang="scss" scoped>
@import "@/static/communityNotice.scss";
</style>
