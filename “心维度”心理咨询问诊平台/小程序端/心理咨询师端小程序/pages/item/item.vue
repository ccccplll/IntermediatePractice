<template>
  <view class="container">
    <view class="search-bar">
      <input type="text" placeholder="心维度 搜索订单" class="search-input" v-model="searchQuery" @input="searchOrders"/>
    </view>
    <view class="tab-bar">
      <text class="tab-item" :class="{ active: currentFilter === 'all' }" @tap="filterOrders('all')">全部</text>
      <text class="tab-item" :class="{ active: currentFilter === 'pending' }" @tap="filterOrders('pending')">待支付</text>
      <text class="tab-item" :class="{ active: currentFilter === 'confirmed' }" @tap="filterOrders('confirmed')">待完成</text>
      <text class="tab-item" :class="{ active: currentFilter === 'processing' }" @tap="filterOrders('processing')">已完成</text>
      <text class="tab-item" :class="{ active: currentFilter === 'completed' }" @tap="filterOrders('completed')">已失效</text>
    </view>
    <view class="order-list">
      <block v-for="(order, index) in filteredOrders" :key="order.id">
        <navigator :url="'/pages/orderDetail/orderDetail?id=' + order.id" class="order-item" @longpress="deleteOrder(index)">
          <view class="order-info">
            <view class="id">{{ order.id }}</view>
            <view class="name">{{ order.name }}</view>
            <view class="time">时长: {{ order.duration }}分钟</view>
            <view class="price">价格: ¥{{ order.price }}</view>
            <view class="created_at">{{ order.created_at }}</view>
          </view>
          <view class="order-status">
            <text v-if="order.status === 'pending'" class="status-pending">待支付</text>
            <text v-if="order.status === 'confirmed'" class="status-confirmed">待完成</text>
            <text v-if="order.status === 'processing'" class="status-processing">已完成</text>
            <text v-if="order.status === 'completed'" class="status-completed">已失效</text>
          </view>
        </navigator>
      </block>
    </view>
  </view>
</template>

<script>
export default {
	onLoad() {
	    this.getCid();
		
		this.applyFilters();
	},
  data() {
    return {
      searchQuery: '',
      orders: [],
      filteredOrders: [],
      currentFilter: 'all',
	  c_id:''
    };
  },
  methods: {
	  getCid(){
		  this.c_id = getApp().globalData.c_id;
	  },
    fetchOrder(c_id) {
      uni.request({
		  url: 'http://localhost:3000/getOrder?c_id='+ encodeURIComponent(c_id) ,
        method: 'GET',
        success: (res) => {
          console.log(res);
          this.orders = []; // 清空订单列表
          res.data.forEach(item => {
            let state;
            if (item.state === "待支付") {
              state = 'pending';
            } else if (item.state === "待完成") {
              state = 'confirmed';
            } else if (item.state === "已完成") {
              state = 'processing';
            } else if (item.state === "已失效") {
              state = 'completed';
            }
            let time = item.time / 60;
            const order = {
              id: item.p_id,
              status: state,
              name: item.name,
              created_at: this.formatDate(new Date(item.create_time)),
              duration: time,
              price: item.price,
            };
            this.orders.push(order);
          });
		  	  this.applyFilters();
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
      this.applyFilters();
    },
    searchOrders() {
      this.applyFilters();
    },
    applyFilters() {
      this.filteredOrders = this.orders.filter(order => {
        const matchesStatus = this.currentFilter === 'all' || order.status === this.currentFilter;
        const matchesSearchQuery = !this.searchQuery || (order.name && order.name.includes(this.searchQuery));
        return matchesStatus && matchesSearchQuery;
      });
    },
	 deleteOrder(index) {
		 if(this.orders[index].status==="待确认"||this.orders[index].status==="待支付")
	      this.orders.splice(index, 1);
	      this.applyFilters();
	}
  },
  mounted() {
    this.fetchOrder(this.c_id); // 在mounted生命周期中调用fetchOrder方法
    // 注册事件监听
    uni.$on('orderStatusChanged', this.fetchOrder);
  },
  beforeDestroy() {
    // 移除事件监听
    uni.$off('orderStatusChanged', this.fetchOrder);
  }
};
</script>

<style>
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










<!--<template>
  <div class="order-status">
    <div class="header">
      <div class="search-box">
        <input
          type="text"
          placeholder="搜索预约"
          v-model="searchQuery"
          @input="searchOrders"
        />
        <button @click="searchOrders">搜索</button>
      </div>
    </div>
    <div class="tabs">
      <div
        :class="{ active: currentTab === tab }"
        v-for="tab in tabs"
        :key="tab"
        @click="selectTab(tab)"
        class="tab-item"
      >
        {{ tab }}
      </div>
    </div>
    <div class="content">
      <div v-if="filteredOrders.length">
        <div v-for="order in filteredOrders" :key="order.id" class="order-item">
			<button @click="viewOrderDetail(order.id)">
          <p>{{ order.name }} - {{ order.status }}</p>
			</button>
        </div>
      </div>
      <div v-else>
        <p>您还没有相关的预约</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
	  		onLoad() {
				let c_id = getApp().globalData.c_id;
				uni.request({
				url: 'http://localhost:3000/getAllProduction ? c_id='+encodeURIComponent(c_id),
				method: 'get',
				success: res => {
				   console.log(res.data);
	  			}
	  			})
	  		},
  data() {
    return {
      searchQuery: '',
      currentTab: '待完成',
      tabs: ['已发布', '待确认', '待支付', '待完成', '已完成'],
      orders: [
        { id: '', name: '', status: '',startTime:'',finishiTime:'',price:'',consultWay:'' },
      ],
    };
    },
  computed: {
    filteredOrders() {
      let orders = this.orders;

      // 过滤当前标签
      if (this.currentTab !== '全部') {
        orders = orders.filter(order => order.status === this.currentTab);
      }

      // 过滤搜索查询
      if (this.searchQuery) {
        orders = orders.filter(order =>
          order.name.includes(this.searchQuery)
        );
      }

      return orders;
    },
  },
  methods: {
	viewOrderDetail(orderId) {
	        uni.navigateTo({
	          url: '/pages/orderDetail/orderDetail'
	        });
	      },
    searchOrders() {
      // 这个方法主要用于触发输入框变化的计算属性
      console.log('Searching for:', this.searchQuery);
    },
    selectTab(tab) {
      this.currentTab = tab;
    },
	getAllInfor(tab){
		let c_id = getApp().globalData.c_id;
		uni.request({
		url: 'http://localhost:3000/getAllInfor ? c_id='+encodeURIComponent(c_id),
		method: 'get',
		success: res => {
		   console.log(res.data);
		}
		})
	}
  },
};
</script>

<style scoped>


.order-status {
  text-align: center;
  padding: 20px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  background-color: #f4f4f9;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  max-width: 600px;
  margin: auto;
}

.header {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.search-box {
  display: flex;
  align-items: center;
  width: 100%;
  max-width: 400px;
  border-radius: 20px;
  overflow: hidden;
  box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
  background-color: white;
}

.search-box input {
  padding: 10px 15px;
  font-size: 16px;
  width: 100%;
  border: none;
  outline: none;
  border-top-left-radius: 20px;
  border-bottom-left-radius: 20px;
}

.search-box button {
  padding: 10px 20px;
  font-size: 16px;
  background-color: #007bff;
  border: none;
  color: white;
  cursor: pointer;
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  white-space: nowrap;
}

.tabs {
  display: flex;
  justify-content: space-between; /* 调整为均匀分布 */
  margin-bottom: 20px;
  background-color: #ffafc7;
  border-radius: 10px;
  padding: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.tab-item {
  flex: 1; /* 确保标签均匀分布 */
  text-align: center;
  padding: 10px 0;
  cursor: pointer;
  transition: all 0.3s ease;
  border-radius: 20px;
}

.tab-item.active {
  font-weight: bold;
  color: white;
  background-color: #007bff;
}

.tab-item:hover {
  background-color: #0056b3;
  color: white;
}

.content {
  padding: 20px;
  background-color: #ffffff;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.order-item {
  padding: 15px;
  border: 1px solid #ccc;
  margin-bottom: 10px;
  border-radius: 10px;
  transition: all 0.3s ease;
  background-color: #f9f9f9;
}

.order-item:hover {
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.order-item p {
  margin: 0;
}
</style>
-->