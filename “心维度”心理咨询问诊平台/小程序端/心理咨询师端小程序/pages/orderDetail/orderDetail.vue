<template>
  <view class="container">
    <view class="order-info">
      <text class="title">订单信息</text>
      <view class="detail-item">
        <text>订单状态：</text>
        <text class="detail-value status unpaid">{{ order.status }}</text>
      </view>
      <view class="detail-item1">
        <text>咨询者昵称：</text>
        <text class="detail-value1">{{ order.serverName }}</text>
		<button class="cButton" @click="consultant">kong</button>
      </view>
      <view class="detail-item">
        <text>费用：</text>
        <text class="detail-value price">{{ order.price }}</text>
      </view>
      <view class="detail-item">
        <text>服务时长：</text>
        <text class="detail-value">{{ order.time }}分钟</text>
      </view>
      <view class="detail-item">
        <text>服务时间：</text>

      </view>
	  <view class="detail-item">
		<text class="detail-value">{{ order.startTime }} 至 {{ order.endTime }}</text>
	  </view>
      <view class="detail-item">
        <text>订单编号：</text>
        <text class="detail-value">{{ id }}</text>
      </view>
	  <view class="detail-item">
	    <text>咨询方式：</text>
	    <text class="detail-value">{{ order.consult_way }}</text>
	  </view>
      <!--<button v-if="order.status === '待支付'" class="pay-button" @click="payOrder">立即支付</button>-->
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      order: {},
      id: ''
    };
  },
  onLoad(option) {
    if (option.id) {
      this.id = option.id;
      this.fetchOrderDetails();
    }
  },
  methods: {
	consultant(){
		uni.navigateTo({
			url:'/pages/consultant/consultant',
		})
	},
    fetchOrderDetails() {
      uni.request({
        url: 'http://localhost:3000/getOrderDetails',
        method: 'GET',
        data: { p_id: this.id },
        success: (res) => {
          console.log(res);
          let time = res.data[0].time / 60;
          this.order = {
            status: res.data[0].state,
            serverName: res.data[0].name,
            price: res.data[0].price,
            time: time,
            startTime: this.formatDate(new Date(res.data[0].start_time)),
            endTime: this.formatDate(new Date(res.data[0].finish_time)),
			consult_way:res.data[0].consult_way,
          };
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
    /*payOrder() {
      uni.showToast({
        title: '支付中...',
        icon: 'loading',
        duration: 2000
      });
      setTimeout(() => {
        // 模拟支付成功，更新订单状态
        uni.request({
          url: 'http://localhost:3000/updateOrderStatus',
          method: 'POST',
          data: {
            p_id: this.id,
            newStatus: '待完成'
          },
          success: (res) => {
            if (res.data.success) {
              uni.showToast({
                title: '支付成功',
                icon: 'success'
              });
              // 更新本地订单状态
              this.order.status = '待完成';
              // 发送事件通知订单列表页面
              uni.$emit('orderStatusChanged');
            } else {
              uni.showToast({
                title: '支付失败',
                icon: 'none'
              });
            }
          },
          fail: (err) => {
            console.log(err);
            uni.showToast({
              title: '支付失败',
              icon: 'none'
            });
          }
        });
      }, 2000);
    }*/
  }
};
</script>

<style lang="scss">
    .container {
      padding: 0;
      background-color: #f0f0f0;
      font-family: 'Roboto', sans-serif;
      width: 100vw;
      height: 100vh;
    }
    
    .order-info {
      margin: 10rpx;
      background-color: #ffffff;
      padding: 15px;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      height: 95vh;
    }
    
    .title {
      font-size: 20px;
      font-weight: bold;
      margin-bottom: 10px;
      align-items: center;
    }
    
    .detail-item {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin: 20px 0;
    }
    
    .detail-item2{
        display: flex;
        flex-direction: column;
        margin: 20px 0;
    }
    
    .server{
        margin-bottom: 10px;
    }
    .detail-server{
        font-weight: bold;
        font-size: 14px;
        margin-left: 60rpx;
    }
    
    .detail-item1 {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin: 10px 0;
      position: relative;
    }
    
    .detail-value {
      font-weight: bold;
      font-size: 14px;
    }
    
    .cButton {
      position: absolute;
      top: 0;
      left: 0;
      height: 100%;
      width: 100%;
      opacity: 0;
      cursor: pointer;
    }
    
    .status.unpaid {
      color: #ff4500;
    }
    
    .price {
      color: #008000;
    }
    
    .pay-button {
      margin-top: 80px;
      background-color: #4caf50;
      color: #ffffff;
      border: none;
      padding: 12px 24px;
      font-size: 18px;
      border-radius: 4px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }
    
    .pay-button:hover {
      background-color: #45a049;
    }
    
</style>

