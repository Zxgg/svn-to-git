<template style="background-color: #F5F5F5">
	<view class="content">
		<view class="example-info_">基金会（含慈善组织）新设立登记</view>
		<view class="person_">个人基本信息</view>
		<view class="person_picker">
			<view class="person_line_name">申报人类型</view>
			<view class="uni-list-cell-db">
				<picker @change="bindPickerChange" :value="index" :range="sbrlx" range-key="name">
					<view class="uni-select">{{sbrlx[index].name}}</view>
				</picker>
			</view>
		</view>
		<view class="person_line">
			<view class="person_line_name">申报人</view>
			<input type="text" placeholder="请输入申报人姓名" class="search_input" v-model="sbrxm"/>
		</view>
		<view class="person_picker">
			<view class="person_line_name">证照类型</view>
			<view class="uni-list-cell-db">
				<picker @change="bindPickerChange" :value="index" :range="zzlx" range-key="name">
					<view class="uni-select">{{zzlx[index].name}}</view>
				</picker>
			</view>
		</view>
		<view class="person_line">
			<view class="person_line_name">申报人证照编号</view>
			<input type="text" placeholder="请输入申报人证照编号" class="search_input" v-model="sbrzjbh"/>
		</view>
		<view class="person_picker">
			<view class="person_line_name">联系人证照类型</view>
			<view class="uni-list-cell-db">
				<picker @change="bindPickerChange" :value="index" :range="lxrzzlx" range-key="name">
					<view class="uni-select">{{lxrzzlx[index].name}}</view>
				</picker>
			</view>
		</view>
		<view class="person_line">
			<view class="person_line_name">联系人证件</view>
			<input type="text" placeholder="请输入联系人身份证号" class="search_input" v-model="lxrzj"/>
		</view>
		<view class="person_line">
			<view class="person_line_name">联系人姓名</view>
			<input type="text" placeholder="请输入联系人姓名" class="search_input" v-model="lxrxm"/>
		</view>
		<view class="person_line">
			<view class="person_line_name">联系人手机</view>
			<input type="text" placeholder="请输入联系人手机" class="search_input" v-model="lxrsj"/>
		</view>
		<view class="person_line">
			<view class="person_line_name">联系人电话</view>
			<input type="text" placeholder="请输入联系人电话" class="search_input" v-model="lxrdh"/>
		</view>
		<view class="person_line">
			<view class="person_line_name">联系人地址</view>
			<input type="text" placeholder="请输入联系人地址" class="search_input" v-model="lxrdz"/>
		</view>
		<view class="person_line">
			<view class="person_line_name">邮编</view>
			<input type="text" placeholder="请输入邮编" class="search_input" v-model="yb"/>
		</view>
		<view class="person_line">
			<view class="person_line_name">备注</view>
			<input type="text" placeholder="请输入备注" class="search_input" v-model="bz"/>
		</view>
		<view class="person_radio">
			<view class="person_line_name">审批结果送到</view>
			<radio-group>
				<label class="radio" >
					<radio value="r1" checked="true" style="transform:scale(0.5)"/>是
				</label>
				<label class="radio" >
					<radio value="r2" style="transform:scale(0.5)"/>否
				</label>
			</radio-group>
		</view>
		<view class="person_radio">
			<view class="person_line_name">申报材料寄送</view>
			<radio-group>
				<label class="radio">
					<radio style="transform:scale(0.5)" checked="true"/>是
				</label>
				<label class="radio">
					<radio style="transform:scale(0.5)" />否
				</label>
			</radio-group>
		</view>
		
		<view class="view_next" @click="goNext">下一步</view>
	</view>
</template>

<script>
	import xflSelect from '../../components/xfl-select/xfl-select.vue';
	export default {
		data() {
			return {
				zzlx: [{name:'个人'}],
				sbrlx: [{name:'身份证'}],
				lxrzzlx: [{name:'身份证'}],
				index: 0,
				searchValue: '',
				sbrxm: '',
				sbrzjbh: '',
				lxrzj: '',
				lxrxm: '',
				lxrsj: '',
				lxrdh: '',
				lxrdz: '',
				yb: '',
				bz: '',
			}
		},
		onLoad() {

		},
		onReady() {

			
		},
		methods: {
			goNext(){
				uni.navigateTo({
					url:"/pages/addMaterials/addMaterials"
				})
			},
		}
	}
</script>

<style lang="scss">
	.content{
		
		width: 750rpx;
		// padding: 0 24rpx;
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
			background-color: #FFFFFF;
			padding: 2px 12px;
			margin: 10px 0;
		}
		
		.person_picker{
			display: flex;
			flex-direction: row;
			justify-content: space-around;
			// margin-top: 10px;
			margin: 16px 0;
			height: 28px;
			
			.person_line_name{
				font-size: 14px;
				line-height: 28px;
				margin-left: 12px;
				width: 100px;
			}
			
			.uni-list-cell-db{
				width: 200px;
				margin-left: 80px;
				// padding-left: 80px;
				.uni-select{
					height: 28px;
					font-size: 14px;
					line-height: 28px;
					width: 100%;
				}
			}
		
		}
		
		.person_radio{
			display: flex;
			flex-flow: row;
			margin: 16px 0;
			height: 28px;
			
			.person_line_name{
				font-size: 14px;
				line-height: 28px;
				margin-left: 12px;
				width: 160px;
			}
		}
		
		.person_line{
			display: flex;
			flex-direction: row;
			justify-content: space-around;
			// margin-top: 10px;
			margin: 16px 0;
			height: 28px;
			
			.uni-list-cell-db{
				width: 245px;
				// margin-left: 80px;
				.uni-select{
					height: 28px;
					font-size: 14px;
					line-height: 28px;
					width: 100%;
				}
			}
			
			
			.person_line_name{
				font-size: 14px;
				line-height: 28px;
			}
			
			.search_input{
				font-size: 14px;
				width: 245px;
				color: #979797;
				text-align: center;
				height: 100%;
				border: 1px solid #e5e5e5;
				border-radius: 4px;
			}
			
			
			
		}
		
		.view_next{
			height: 35px;
			width: 150px;
			background: #999999;
			border-radius: 18px;
			text-align: center;
			line-height: 35px;
			margin: auto;
		}
	}
	
</style>