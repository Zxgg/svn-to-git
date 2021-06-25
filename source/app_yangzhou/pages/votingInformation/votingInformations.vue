<template>
	<view class="vote2_options">
		<view class="vote2_options_th">
			<view class="">
				排列序号
			</view>
			<view class="">
				名称
			</view>
			<view class="">
				状态
			</view>
			<view class="">
				备注
			</view>
		</view>
		<view class="vote2_options_td">
			<view class="">
				{{voteDetailData.toShow}}
			</view>
			<view class="">
				{{voteDetailData.name}}
			</view>
			<view class="">
				{{voteDetailData.stat}}
			</view>
			<view class="">
				{{voteDetailData.remark}}
			</view>
		</view>
			
	</view>
</template>

<script>
	import { votingDetailApi,getDicts,votingApi } from '../../util/api.js'
	import {
		selectDictLabel
	} from '../../static/format.js'
	export default {
		data() {
			return{
				id:null,
				voteDetailData:[],
				open:false,
				// 投票项的选项 表格数据
				vote_optionList: [],
			}
		},
		created() {
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
		},
		onLoad: function (option){
			this.id = option.id;
			this.getVoteDetail(this.id)
			// this.getVoteOptions()
		},
		methods:{
			//字典翻译
			stat_Format: function (value) {
			      return selectDictLabel(this.statOptions, value);
			    },
			getVoteDetail(id){
				votingDetailApi(id).then(res => {
					if(res.code === 200){
						this.voteDetailData = res.data
						this.voteDetailData.stat = this.stat_Format(this.voteDetailData.stat)
						console.log(this.voteDetailData,'this.voteDetailData')
					}
				})
			},
		}
	}
</script>

<style lang="scss" scoped>
@import "@/static/votingInformation.scss";
</style>
