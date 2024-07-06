<template>
	<view>
		<text>{{serverData}}</text>
	</view>
	<view class="container">
	  <button @click="insert">插入数据</button>
	  <button @click="select1">固有查询</button>
	
	  <h1></h1>
	  <textarea>{{ result }}</textarea>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello',
				serverData:'',
				result: ''
			}
		},
		onLoad() {
		 uni.request({
		 url: "http://localhost:3000/",
		 method: 'get',
		 success: res => {
		 console.log(res.data);
		 this.serverData=res.data;
			}
			})
					

		},
		methods: {
        
	},
	}
</script>

<style>
	
</style>
