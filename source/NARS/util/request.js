// const request = (options) => {
//     return new Promise((resolve, reject) => {
//         uni.request({
//                 url: options,
//                 method: options.method || 'GET',
//                 data: options.data || {}
//             })
//             .then((res) => {
//                 resolve(res) //数据请求成功
//             })
//             .catch((err) => {
//                 uni.showToast({
//                     title: '请求接口失败'
//                 })
//                 reject(err) //数据请求失败
//             })
//     })
// }
//先获取配置文件


// export default request
//先把接口暴露出去
export default{
	//我们先定一个uni-app方法 以便于以下操作使用uni-app调取接口时便利
	request(options){
		///我们使用Promise方法来实现调用接口时后面多个.then()的方法
		//只有Promise能实现如同$axios后面连续多个.then()的方法
	 	return new Promise((reslove,reject)=>{
			uni.request({
				...options,
				success:res=>{
					//判断我们在使用封装的自定义时第三个参数是否为native
					//当native为true时 我们返回原数据
					if(options.native){
						reslove(res)
					}
					//当native为false时 我们直接返回data中的数据
					if(res.statusCode === 200){
						reslove(res.data)
					}else{
						//加入接口参数错误或接口地址错误时 我们返回原错误提示
						reject(res)
					}
				}
			})
		})
	},
	//在方法中 第二个参数和第三个参数用ES6新语法来添加默认值
	//接口调取get方法
	get(url,data={},options={}){
		//我们把传过来的参数赋给options，这样我们在使用uni-app
		//this.request()方法时 传递一个参数就可以
		options.url = url;
		options.data = data;
		options.method = 'get';
		//调用上面自己定义的this.request()方法传递参数
		return this.request(options)
	},
	//接口调取post方法
	post(url,data={},options={}){
		// debugger
		options.url = url;
		options.data = data;
		options.method = 'post';
		return this.request(options)
	}
}