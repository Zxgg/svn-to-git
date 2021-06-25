<template>
	<view class="main">
		 <uni-forms ref="form" :value="form" :rules="rules">
		            <uni-forms-item label="姓名" name="name">
		                <input type="text" v-model="form.name" placeholder="请输入姓名" @input="binddata('name',$event.detail.value)" />
		            </uni-forms-item>
		            <uni-forms-item label="手机" name="phoneNumber">
		                <input class="input" v-model="form.phoneNumber" type="text" placeholder="请输入手机号码" @input="binddata('phoneNumber',$event.detail.value)" />
		            </uni-forms-item>
					<uni-forms-item label="身份证号" name="idCard">
					    <input v-model="form.idCard" class="input" name="idCard" placeholder="身份证号" type="number" @input="binddata('idCard',$event.detail.value)"/>
					</uni-forms-item>
					<uni-forms-item label="通行目的" name="trafficPurpose" class="trafficbox">
						<textarea v-model="form.trafficPurpose" placeholder="请输入通行目的" />
					</uni-forms-item>
					<view>
						人脸图像
					    <robby-image-upload 
						v-model="form.imageData" 
						@delete="deleteImage" @add="addImage" 
						:server-url-delete-image="serverUrlDeleteImage" 
						:server-url="serverUrl" />
					            <view v-for="(item,index) in form.imageData" 
								:key="index" name="imageData">
					                {{index}}. {{item.substr(-14)}}
					            </view>
					</view>
		            <button @click="submit">提交</button>
					<button type="default" @click="cancel">取消</button>
		 </uni-forms>
	</view>
</template>

<script>
	import configInfo from "@/config/configInfo.js";
	export default {
		data() {
			return {
				serverUrl: '',
				serverUrlDeleteImage: '',
				// form:{
				// 	name:null,
				// 	phone:null,
				// 	imageData : [],
				// 	idCard:null,
				// 	trafficPurpose:null
				// },
				// rules: {
				// 	name: {
				// 		required: true,
				// 		rule: 'type:string|length:~,20'
				// 	},
				// phone: {
				// 	required: true,
				// 	rule:'^1(3|4|5|6|7|8|9)d{9}$'
				// },
				// idCard: {
				// 	required: true,
				// 	rule: '^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$'
				// },
				// trafficPurpose:{
				// 	required: true,
				// }
				// }
				form: {
                // name: 'LiMing',
                // phoneNumber:''
            },
            rules: {
                name: {
                    rules: [{
                            required: true,
                            errorMessage: '请输入姓名',
                        },
                        // {
                        //     minLength: 3,
                        //     maxLength: 5,
                        //     errorMessage: '姓名长度在 {minLength} 到 {maxLength} 个字符',
                        // }
                    ]
                },
                phoneNumber: {
                    rules: [{
						required: true,
                        // format: 'email',
                        errorMessage: '请输入正确的手机号码',
                    }]
                },
                idCard: {
                    rules: [{
						required: true,
                        errorMessage: '请输入正确的身份证号码',
                    }]
                },
				trafficPurpose: {
                    rules: [{
						required: true,
                        errorMessage: '请输入通行目的',
                    }]
                },
            }
			}
		},
		mounted() {
			this.serverUrl = configInfo.FileUpLoadUri;
		},
		methods: {
			deleteImage: function(e){
			                console.log(e)
			            },
			addImage: function(e){
			                console.log(e)
			            },
			// //提交表单
			// formSubmit: function(e) {
			// 	console.log('form发生了submit事件，携带数据为：' + JSON.stringify(e.detail.value))
			// 	},
			//取消提交
			cancel(){
				console.log('取消')
			},
			/**
         * 复写 binddata 方法，如果只是为了校验，无复杂自定义操作，可忽略此方法
         * @param {String} name 字段名称
         * @param {String} value 表单域的值
         */
        // binddata(name,value){
        // 通过 input 事件设置表单指定 name 的值
        //   this.$refs.form.setValue(name, value)
        // },
        // 触发提交表单
        submit() {
            this.$refs.form.submit().then(res=>{
                console.log('表单数据信息：', res);
            }).catch(err =>{
                console.log('表单错误信息：', err);
            })
        }
		}
	}
</script>

<style lang="scss" scoped>
@import "@/static/temporaryRegister.scss";
</style>
