<template>
	<view class="resultmain">
		<view class="show">
			<view class="table">
				<view class="tr_td" 
				v-for="(item,index) in voting_list"
				:key="index">
					<view class="stat">
						{{item.stat}}
					</view>
					<view class="td1" 
					@tap="toDetail(item.id)">
						{{item.name}}
					</view>
					<view class="td2" >
						{{item.type}}
					</view>
					<view class="td3">
						投票开始时间：{{item.startTime}}
					</view>
					<view class="td3">
						投票结束时间：{{item.endTime}}
					</view>
						<navigator class="vote_txt td" 
						url="./voteResult">投票结果</navigator>
				</view>
			</view>
		</view>
	</view>
</template>
<script>
	import { votingApi,getDicts,votingDetailApi } from '@/util/api.js'
	import {
		selectDictLabel
	} from '../../static/format.js'
	export default {
		data() {
			return {
				vote_type_list:[],
				vote_state_list:[],
				vote_stat_list:[],
				voting_options_list:[
					'1.沿河安装白炽灯一百米一台',
					'2.沿河安装LED路灯五十米一台',
					'3.不安装路灯'
				],
				show:true,
				open:true,
				form:{},
				vote_record_form:{
					vote_options:null,
				},
				voting_list:[],
				//字典
				// 投票类型字典
				typeOptions: [],
				// 投票状态(0是创建，1是正在进行投票，2是投票截至)字典
				statOptions: [],
				// 状态(是否正常使用）字典
				statusOptions: [],
			}
		},
		created() {
			//投票类型
			getDicts("base_vote_type").then(response => {
			      this.typeOptions = response.data;
				  var obj = {};
				  var list_ = [];
				  response.data.forEach(item => {
				  	obj.value = item.dictLabel;
				  	obj.code = item.dictValue
				  	obj.disabled = false;
				  	list_.push(obj);
				  	obj = {};
				  });
				  this.vote_type_list = list_;
			    });
			//投票状态
			getDicts("base_vote_stat").then(response => {
			      this.statOptions = response.data;
				  var obj = {};
				  var list_ = [];
				  response.data.forEach(item => {
				  	obj.value = item.dictLabel;
				  	obj.code = item.dictValue
				  	obj.disabled = false;
				  	list_.push(obj);
				  	obj = {};
				  });
				  this.vote_state_list = list_;
			    });
			//状态
			getDicts("sys_normal_disable").then(response => {
			      this.statusOptions = response.data;
			    });
		},
		mounted() {
			this.getVonting()
			// this.getVontingDetail()
		},
		methods: {
			//字典翻译
			type_Format: function (value) {
			      return selectDictLabel(this.typeOptions, value);
			    },
			status_Format: function (value) {
			      return selectDictLabel(this.statusOptions, value);
			    },
			stat_Format: function (value) {
			      return selectDictLabel(this.statOptions, value);
			    },
			formSubmit: function(e) {
                var form = e.detail.value
				form.vote_type = this.form.vote_type
				form.vote_state = this.form.vote_state
				form.vote_options = this.form.vote_options
                console.log('form发生了submit事件，携带数据为：' + JSON.stringify(e.detail.value))
                uni.showModal({
                    content: '表单数据内容：' + JSON.stringify(form),
                    showCancel: false
                });
            },
            formReset: function(e) {
                console.log('清空数据')
            },
			vote_record_formSubmit: function(e) {
			    var vote_record_form = e.detail.value
				vote_record_form.vote_project = this.vote_record_form.vote_type
				vote_record_form.vote_state = this.vote_record_form.vote_state
				form.vote_options = this.form.vote_options
			    console.log('form发生了submit事件，携带数据为：' + JSON.stringify(e.detail.value))
			    uni.showModal({
			        content: '表单数据内容：' + JSON.stringify(form),
			        showCancel: false
			    });
			},
			vote_record_formReset: function(e) {
			    console.log('清空数据')
			},
			sure(e){
				console.log(e,'e')
			},
			vote_type_Change(e){
				this.form.type = e.orignItem.code
				console.log(e.newVal,'newVal')
			},
			vote_state_Change(e){
				this.form.stat = e.orignItem.code
				console.log(this.form.status,'newVal')
			},
			vote_options_Change(e){
				this.form.vote_options = e.newVal
				console.log(e.newVal,'newVal')
			},
			open_sure(e){
				console.log(e,'e')
			},
			open_cancel(){
				this.open = !this.open;
			},
			vote_record_return(){
				this.show = !this.show;
			},
			toDetail(id){
				uni.navigateTo({
					url:'./votingInformations?id=' + id
				})
			},
			getVonting(){
				let self = this
				votingApi(this.form).then((res) => {
					console.log(res,'res')
					console.log(this.form)
					if(res.code === 200){
						res.rows.forEach(data => {
							data.stat = self.stat_Format(data.stat)
							data.status = self.status_Format(data.status)
							data.type = self.type_Format(data.type)
						})
							this.voting_list = res.rows
					}
				})
			},
			// searchBtn(){
			// 	this.getVonting()
			// }
		}
	}
</script>

<style lang="scss" scoped>
@import "@/static/votingInformation.scss";
</style>
