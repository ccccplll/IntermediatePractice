<template>
	<view>
		<swiper indicator-dots="true" indicator-color="rgba(255, 255, 127, 0.3)" indicator-active-color="#ffaa00" circular="true" autoplay="true" interval="2000">
		            <swiper-item><image src="https://5b0988e595225.cdn.sohucs.com/images/20200509/233967aced044c7ba43475a7a72bdc34.jpeg" mode="aspectFit" class="sw-image"></image></swiper-item>
		            <swiper-item><image src="https://5b0988e595225.cdn.sohucs.com/images/20190510/25fce2c7c2694586a8846ed34a06553b.jpeg" mode="aspectFit" class="sw-image"></image></swiper-item>
		            <swiper-item><image src="https://img2.baidu.com/it/u=1068411658,2144683128&fm=253&fmt=auto&app=120&f=JPEG?w=720&h=360" mode="aspectFit" class="sw-image"></image></swiper-item>
		            <swiper-item><image src="https://img1.baidu.com/it/u=3074064256,4083116261&fm=253&fmt=auto&app=120&f=JPEG?w=713&h=399" mode="aspectFit" class="sw-image"></image></swiper-item>
		        </swiper>

		<view style="padding: 20rpx;">
			<!-- 公告 -->
			<view class="box">
				<uni-icons type="sound" size="20" style="position: relative; top: 5rpx;"></uni-icons>
				<text style="margin-left: 5rpx;">{{ notice }}</text>
			</view>

			<!-- 商家分类 -->
			<view class="box" style="margin: 20rpx 0;">
				<view style="display: flex;">
					<view class="category-item" @click="naviToAdd">
						<image src="../../static/doctor.png"mode="widthFix" style="width: 50%;"></image>
						<text>发布预约</text>
					</view>
					<view class="category-item" @click="naviToCer">
						<image src="../../static/胶囊药.png" mode="widthFix" style="width: 50%;"></image>
						<text>资格认证</text>
					</view>
					<view class="category-item" @click="naviToCom">
						<image src="../../static/test.png"mode="widthFix" style="width: 50%;"></image>
						<text>用户社区</text>
					</view>
					<view class="category-item" @click="naviToTree">
						<image src="../../static/heart.png" mode="widthFix" style="width: 50%;"></image>
						<text>树洞来信</text>
					</view>
				</view>
			</view>

			<!-- 商家列表 -->
			<view class="box" style="color: orange; font-size: 32rpx; font-weight: bold; margin-bottom: 10rpx; margin-left: 300rpx;">
				待完成订单
			</view>
			<view>
			<view class="order-list">
			  <block v-for="(order, index) in orders" :key="order.id">
			    <navigator :url="'/pages/orderDetail/orderDetail?id=' + order.id" class="order-item" @longpress="deleteOrder(index)">
			      <view class="order-info">
			        <view class="id">{{ order.id }}</view>
			        <view class="name">{{ order.name }}</view>
			        <view class="time">时长: {{ order.duration }}分钟</view>
			        <view class="price">价格: ¥{{ order.price }}</view>
			        <view class="created_at">{{ order.created_at }}</view>
			      </view>
			      <view class="order-status">
			        <text class="status-confirmed">待完成</text>
			      </view>
			    </navigator>
			  </block>
			</view>
			</view>
			</view>
		
		</view>
</template>

<script>
	export default {
		data() {
			return {
				banners: [],
				noticeList: [],
				notice: '',
				businessList: [],
				c_id:getApp().globalData.c_id,
				seeMessage:getApp().globalData.seeMessage,
				orders: [],
				filteredOrders: [],
			}
		},
		onLoad() {
			this.fetchOrder();
		},
		methods: {
			  judge(seeMessage){
				if(seeMessage == '未看过'){
					uni.setTabBarBadge({
						index:1,
					});
					console.log("success")
				}
				else{
					uni.removeTabBarBadge({
						index:1,
					});
					console.log("false")
				}

			},
			search(res) {
				let value = res.value
				uni.navigateTo({
					url: '/pages/search/search?name=' + value
				})
			},
			goToDeatil(businessId) {
				uni.navigateTo({
					url: '/pages/detail/detail?businessId=' + businessId
				})
			},
			naviToAdd(){
				uni.navigateTo({
					url:'/pages/addAppoint/addAppoint'
				})
			},
			naviToCom(){
				uni.navigateTo({
					url:'/pages/community/community'
				})
			},
			naviToTree(){
				uni.navigateTo({
					url:'/pages/tree/tree'
				})
			},
			naviToCer(){
				uni.navigateTo({
					url:'/pages/certification/certification'
				})
			},
			fetchOrder() {
			    uni.request({
					  url: 'http://localhost:3000/getOrderA',
			      method: 'GET',
				  data:{
					  c_id:getApp().globalData.c_id
				  },
			      success: (res) => {
			        console.log(res);
			        this.orders = []; // 清空订单列表
			        res.data.forEach(item => {
			          let time = item.time / 60;
			          const order = {
			            id: item.p_id,
			            name: item.name,
			            created_at: this.formatDate(new Date(item.create_time)),
			            duration: time,
			            price: item.price,
			          };
			          this.orders.push(order);
			        });
			      },
			      fail: (err) => {
			        console.log(err);
			      }
			    });
			
			  },
			  formatDate(date) {
			    const pad = (n) => n < 10 ? '0' + n : n;
			    return `${date.getFullYear()}-${pad(date.getMonth() + 1)}-${pad(date.getDate())} ${pad(date.getHours())}:${pad(date.getMinutes())}:${pad(date.getSeconds())}`;
			  },
			  filterOrders(status) {
			    this.currentFilter = status;
			  },

			mounted() {
			  this.fetchOrder(this.c_id); // 在mounted生命周期中调用fetchOrder方法
			  // 注册事件监听
			  uni.$on('orderStatusChanged', this.fetchOrder);
			},
			beforeDestroy() {
			  // 移除事件监听
			  uni.$off('orderStatusChanged', this.fetchOrder);
			},
			refreshData(){
				uni.request({
				  url: 'http://localhost:3000/refreshData?c_id='+encodeURIComponent(this.c_id),
				  method: 'POST',
				  success: (res) => {
					  const app = getApp(); // 获取App实例
					  if (app.globalData) {  
					  	app.globalData.seeMessage = res.seeMessage;
						console.log('yes');
					  }  
				    console.log(res);
					},
					fail: (err) => {
					  console.log(err);
					}
				});
			},
		},
};
</script>

<style lang="scss">
	.category-item {
		flex: 1;
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		align-items: center;
		grid-gap: 10rpx;
	}
	.uni-searchbar {
		padding: 10rpx !important;
	}
	.swiper{
	        width:100vw;
	        height:150px;
	        border:2px solid green;
	        swiper-item{
	            width: 100%;
	            height:100%;
	            .sw-image{
	                width: 100%;
	                height:100%;
	            }
		}
	}
	.sw-image{
		width: 100vw;
		height:150px;
	}
	.container {
	  padding: 10px;
	}
	
	.search-bar {
	  display: flex;
	  align-items: center;
	  margin-bottom: 10px;
	}
	
	.search-input {
	  flex: 1;
	  padding: 5px;
	  border-radius: 5px;
	  border: 1px solid #ccc;
	}
	
	.tab-bar {
	  display: flex;
	  justify-content: space-around;
	  margin-bottom: 10px;
	}
	
	.tab-item {
	  padding: 5px 10px;
	  border-radius: 5px;
	  cursor: pointer;
	  user-select: none;
	  font-size: 34rpx;
	}
	
	.tab-item.active {
	  background-color: #eee;
	}
	.container {
	  padding: 10px;
	  background-color: #f5f5f5;
	}
	
	.search-bar {
	  margin-bottom: 20px;
	}
	
	.search-input {
	  width: 100%;
	  padding: 10px;
	  border: 1px solid #ccc;
	  border-radius: 5px;
	}
	
	.tab-bar {
	  display: flex;
	  justify-content: space-around;
	  margin-bottom: 20px;
	}
	
	.tab-item {
	  flex: 1;
	  text-align: center;
	  padding: 10px;
	  color: #666;
	  border-bottom: 2px solid transparent;
	  transition: border-color 0.3s, color 0.3s;
	  font-size: 24rpx;
	}
	
	.tab-item.active {
	  color: #007aff;
	  border-bottom: 2px solid #007aff;
	}
	
	.order-list {
	  margin-top: 20px;
	}
	
	.order-item {
	  display: flex;
	  flex-direction: column;
	  background-color: #fff;
	  padding: 15px;
	  margin-bottom: 15px;
	  border-radius: 10px;
	  box-shadow: 0 1px 3px rgba(0,0,0,0.1);
	}
	
	.main {
	  display: flex;
	  align-items: center;
	}
	
	.touxiang {
	  margin-right: 10px;
	}
	
	.touxiang image {
	  width: 60px;
	  height: 60px;
	  border-radius: 50%;
	}
	
	.order-info {
	  flex: 1;
	}
	
	.name {
	  font-size: 18px;
	  font-weight: bold;
	  margin-bottom: 5px;
	}
	
	.time,
	.price,
	.created_at {
	  font-size: 14px;
	  color: #666;
	}
	
	.actions {
	  display: flex;
	  justify-content: space-between;
	  align-items: center;
	  margin-top: 10px;
	}
	
	.order-status {
	  flex: 1;
	}
	
	.status-confirmed {
	  color: #ff9800;
	}
	
	.status-processing {
	  color: #03a9f4;
	}
	
	.status-completed {
	  color: #4caf50;
	}
	
	.status-pending {
	  color: #f44336;
	}
	
	.buttons {
	  display: flex;
	}
	
	.buttons button {
	  margin-left: 10px;
	  padding: 0px 15px;
	  font-size: 14px;
	  color: #fff;
	  background-color: #007aff;
	  border: none;
	  border-radius: 20px;
	  box-shadow: 0 3px 6px rgba(0,0,0,0.1);
	  transition: background-color 0.3s, transform 0.2s;
	}
	
	.buttons button:first-child {
	  margin-left: 0;
	}
	
	.buttons button:hover {
	  background-color: #005bb5;
	  transform: translateY(-2px);
	}
	
	.buttons button:active {
	  background-color: #003f8c;
	  transform: translateY(0);
	}
	
	.buttons button:disabled {
	  background-color: #ccc;
	  cursor: not-allowed;
	}
</style>