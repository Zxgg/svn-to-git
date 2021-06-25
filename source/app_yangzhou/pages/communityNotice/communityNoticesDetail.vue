<template>
	<view class="noticeDetailmain">
		<view class="noticeDetail" >
			<u-parse :content="article" />
		</view>
	</view>
</template>
<script>
	import { communityNoticeDetailApi } from '../../util/api.js'
	export default{
		data() {
			return{
				communityNoticeTable:null,
				id:null,
				article: 'article'
			}
		},
		onLoad: function (option){
			this.id = option.id;
		},
		mounted() {
			this.getCommunityNotice(this.id);
		},
		methods:{
			getCommunityNotice(id){
				communityNoticeDetailApi(id).then((res) => {
					if(res.code === 200){
					  this.communityNoticeTable=res.data
					  this.article = res.data.content
					}
				})
			},
		}
	}
</script>

<style>
	@import url("/components/gaoyia-parse/parse.css");
	@import '@/static/communityNotice.scss';
</style>
