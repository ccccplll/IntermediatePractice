
<template>
	<view>
		<view class="list-item" v-for="(item,index) in messageList" :key="index" @click="connect(item)">
			<view class="avatar">
				<text class="round" v-if="!item.read"></text>
				<image :src="item.avatar" mode="widthFix"></image>
			</view>
			<view class="content">
				<view class="title">
					<text class="name">{{item.type}}</text>
					<text class="time">{{ item.create_time }}</text>
				</view>
				<view class="txt">{{ item.content }}</view>
			</view>
 
		</view>
	</view>
</template>
 
<script>
	export default {
		data() {
			return {
				messageList:[
					{
						id:'1',
						content:'666',
						create_time:'2024-7-1',
						type:'树洞通知',
						avatar:'/static/树洞来信.png',
						read:'1'
					}
				],
				c_id:'',
				options: [{
					text: '取消',
					style: {
						backgroundColor: '#007aff'
					}
				}, {
					text: '确认',
					style: {
						backgroundColor: '#dd524d'
					}
				}],
			};
		},
        onRefresh(){
				this.getMessage();
			},
		onLoad(){
			this.c_id = getApp().globalData.c_id;
			this.removeRed();
			this.getMessage();
		    uni.removeTabBarBadge({
		    	index:1,
		    });
			
		},
		methods: {
			onRefresh(){
				this.getMessage();
			},
			removeRed(){
				uni.request({
				  url: 'http://localhost:3000/updateSee?c_id=',
				  method: 'POST',
				  data:{
					  c_id:this.c_id,
					  seeMessage:'已看过'
				  },
				  success: (res) => {
				    console.log(res);
					},
					fail: (err) => {
					  console.log(err);
					}
				});
			},
			connect(item) {
				let url1;
				this.readMessage(item.id);
				this.getMessage();
				if(item.type=='树洞来信'){
					uni.navigateTo({
						url:'/pages/tree/tree'
					})
				}
				else if(item.type=='系统通知'){
					uni.switchTab({
						url:'/pages/messageList/messageList'
					})
				}
				else if(item.type=='预约提醒'){
					uni.switchTab({
						url:'/pages/item/item?state=' + encodeURIComponent('comfirmed')
					})
				}
				else if(item.type=='退款提醒'){
					uni.switchTab({
						url:'/pages/item/item?state=' + encodeURIComponent('comfirmed')
					})
				}
			},
			readMessage(id){
				uni.request({
				  url: 'http://localhost:3000/readConsultant?id='+encodeURIComponent(id),
				  method: 'POST',
				  success: (res) => {
					  
				  },
				  fail: (err) => {
				    console.log(err);
				  }
				});
			},
			getMessage(){
				uni.request({
				  url: 'http://localhost:3000/getMessage?c_id='+encodeURIComponent(this.c_id),
				  method: 'GET',
				  success: (res) => {
				    console.log(res);
				    this.messageList = []; // 清空订单列表
				    res.data.forEach(item => {
				      const message = {
				        id: item.id,
						content:item.context,
						read:item.is_read,
						type:item.type,
						avatar:'/static/' + item.type + '.png',
						creat_time:item.creat_time,
				      };
				      this.messageList.push(message);
				    });
				  },
				  fail: (err) => {
				    console.log(err);
				  }
				});
			}
		}
	}
</script>
 
<style lang="scss" scoped>
	.page {
		padding: 0 32rpx;
		color: #333;
	}
 
	.list-item {
		display: flex;
		padding: 30rpx 0;
		border-bottom: 1px solid #ccced3;
 
		.avatar {
			width: 90rpx;
			height: 90rpx;
			border-radius: 10rpx;
			margin-right: 20rpx;
			position: relative;
 
			.round {
				position: absolute;
				width: 14rpx;
				height: 14rpx;
				border-radius: 50%;
				background: #ef5656;
				top: -4rpx;
				right: -4rpx;
				z-index: 1;
			}
 
			image {
				width: 100%;
				height: 100%;
				border-radius: 10rpx;
			}
		}
 
		.content {
			flex: 1;
 
			.title {
				display: flex;
				justify-content: space-between;
 
				.name {
					font-weight: bold;
				}
 
				.time {
					color: #999;
					font-size: 24rpx;
				}
			}
 
			.txt {
				margin-top: 10rpx;
				overflow: hidden;
				text-overflow: ellipsis;
				display: -webkit-box;
				-webkit-line-clamp: 1;
				-webkit-box-orient: vertical;
				text-align: left;
				color: #999;
				font-size: 26rpx;
			}
		}
	}
</style>
