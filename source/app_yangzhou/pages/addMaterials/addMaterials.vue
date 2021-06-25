<template>
	<view class="content">
		<view class="upload_view">
			<view class="example-info_">基金会（含慈善组织）新设立登记</view>
			<view class="person_">申报材料</view>
			<view>
				<view class="back_"></view>
				<view class="upload_info">
					<p>1、 授权委托书<span class="explain">【必备】</span></p>
					<p>申请人自备</p>
				</view>
				<uni-file-picker limit="5" file-mediatype="all" :fileInfo="fileInfo"></uni-file-picker>
			</view>
			<view>
				<view class="back_"></view>
				<view class="upload_info">
					<p>2、 住所证明<span class="explain">【必备】</span></p>
					<p>申请人自备</p>
				</view>
				<uni-file-picker limit="5" file-mediatype="all" :fileInfo="fileInfo"></uni-file-picker>
			</view>
			<view>
				<view class="back_"></view>
				<view class="upload_info">
					<p>3、 验资报告<span class="explain">【必备】</span></p>
					<p>申请人自备</p>
				</view>
				<uni-file-picker limit="5" file-mediatype="all" :fileInfo="fileInfo"></uni-file-picker>
			</view>
			<view>
				<view class="back_"></view>
				<view class="upload_info">
					<p>4、 社会组织办事机构备案表<span class="explain">【必备】</span></p>
					<p>申请人自备</p>
				</view>
				<uni-file-picker limit="5" file-mediatype="all" :fileInfo="fileInfo"></uni-file-picker>
			</view>
			<view>
				<view class="back_"></view>
				<view class="upload_info">
					<p>5、 社会组织理事（董事）监事备案表<span class="explain">【必备】</span></p>
					<p>申请人自备</p>
				</view>
				<uni-file-picker limit="5" file-mediatype="all" :fileInfo="fileInfo"></uni-file-picker>
			</view>
			<view>
				<view class="back_"></view>
				<view class="upload_info">
					<p>6、 社会组织负责人备案<span class="explain">【必备】</span></p>
					<p>申请人自备</p>
				</view>
				<uni-file-picker limit="5" file-mediatype="all" :fileInfo="fileInfo"></uni-file-picker>
			</view>
			<view>
				<view class="back_"></view>
				<view class="upload_info">
					<p>7、 社会组织法定代表人登记表<span class="explain">【必备】</span></p>
					<p>申请人自备</p>
				</view>
				<uni-file-picker limit="5" file-mediatype="all" :fileInfo="fileInfo"></uni-file-picker>
			</view>
			<view>
				<view class="back_"></view>
				<view class="upload_info">
					<p>8、基金会章程<span class="explain">【必备】</span></p>
					<p>申请人自备</p>
				</view>
				<uni-file-picker limit="5" file-mediatype="all" :fileInfo="fileInfo"></uni-file-picker>
			</view>
			<view>
				<view class="back_"></view>
				<view class="upload_info">
					<p>9、社会组织章程核准表<span class="explain">【必备】</span></p>
					<p>申请人自备</p>
				</view>
				<uni-file-picker limit="5" file-mediatype="all" :fileInfo="fileInfo"></uni-file-picker>
			</view>
			<view>
				<view class="back_"></view>
				<view class="upload_info">
					<p>10、社会组织法人登记表<span class="explain">【必备】</span></p>
					<p>申请人自备</p>
				</view>
				<uni-file-picker limit="5" file-mediatype="all" :fileInfo="fileInfo"></uni-file-picker>
			</view>
			<view>
				<view class="back_"></view>
				<view class="upload_info">
					<p>11、业务主管单位批准文件<span class="explain">【必备】</span></p>
					<p>申请人自备</p>
				</view>
				<uni-file-picker limit="5" file-mediatype="all" :fileInfo="fileInfo"></uni-file-picker>
			</view>
			<view>
				<view class="back_"></view>
				<view class="upload_info">
					<p>12、基金会章程草案<span class="explain">【必备】</span></p>
					<p>申请人自备</p>
				</view>
				<uni-file-picker limit="5" file-mediatype="all" :fileInfo="fileInfo"></uni-file-picker>
			</view>
			<view>
				<view class="back_"></view>
				<view class="upload_info">
					<p>13、可行性报告<span class="explain">【必备】</span></p>
					<p>申请人自备</p>
				</view>
				<uni-file-picker limit="5" file-mediatype="all" :fileInfo="fileInfo"></uni-file-picker>
			</view>
			<view>
				<view class="back_"></view>
				<view class="upload_info">
					<p>14、社会组织名称核准申请表<span class="explain">【必备】</span></p>
					<p>申请人自备</p>
				</view>
				<uni-file-picker limit="5" file-mediatype="all" :fileInfo="fileInfo"></uni-file-picker>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				fileInfo:'提交电子文件或纸质文件'
			}
		},
		methods: {

		}
	}
</script>

<style lang="scss">
	@import '@/common/uni-nvue.scss';
	
	.content{
		width: 750rpx;
		.example-info_{
			text-align: center;
			font-size: 16px;
			width: 100%;
			font-weight: 600;
			background-color: #FFFFFF;
		}
		
		.person_{
			font-size: 14px;
			color: #999999;
			// background-color: #FFFFFF;
			padding: 2px 12px;
			margin: 10px 0;
		}
		
		.upload_view{
			// width: 100%;
			// padding: 0px 12px;
			background-color: #FFFFFF;
			
			.back_{
				height: 20px;
				width: 375px;
				background-color: #f4efef;
			}
			
			.upload_info{
				padding: 0px 12px;
				display: flex;
				flex-direction: row;
				justify-content: space-around;
				height: 30px;
				font-size: 12px;
				line-height: 30px;
					
				.explain{
					color: #FF3333;
				}
				
				.file_explain{
					color: #999999;
				}
			}
		}
	}
</style>
