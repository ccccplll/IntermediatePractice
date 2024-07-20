<template>
    <view>
        <view class="uni-list">
            <view class="uni-list-cell" hover-class="uni-list-cell-hover" v-for="(value, key) in listData" :key="key" @click="goDetail(value)">
                <view class="uni-media-list">
                    <image class="uni-media-list-logo" :src="value.cover"></image>
                    <view class="uni-media-list-body">
                        <view class="uni-media-list-text-top">标题：{{ value.title }}</view>
						<view class="author" @click="jumptoCon">作者：{{value.author_name}}</view>
                        <view class="uni-media-list-text-bottom">
                            <text>点赞数：{{ value.goodNum }}</text>
                            <text>{{ value.published_at }}</text>
                        </view>
                    </view>
                </view>
            </view>
        </view>
    </view>
</template>

<script>
    
    export default {
        data() {
            return {
                listData: [{
                        id: "1",                       
                        title: "「粒子狂热」：被误解成潮牌的运动穿着品牌",
                        author_name: "王咪",
                        cover: "https://img.36krcdn.com/20191230/v2_37635ef22df24e96aa7f26e192036c2b_img_png",
                        published_at: "2024-7-20 15:20:00"
                    },
                    {
                        id: "2",                       
                        title: "为什么12306时不时要崩那么一下？",
                        author_name: "张云",
                        cover: "https://img.36krcdn.com/20191230/v2_02c342a62f91498b99c7f2b5aa22ff1c_img_png",
                        published_at: "2024-7-20 15:22:00"
                    },
                    {
                        id: "3",
                        title: "「倒闭、被坑、降薪、失业，2019我为什么还在坚持",
                        author_name: "李琳",
                        cover: "https://img.36krcdn.com/20191230/v2_43cbd298bed24a18bd023802258f20c8_img_png",
                        published_at: "2024-7-20 15:26:00"
                    }
                ],
            };
        },
        onLoad() {
			this.getAllEssay();
        },
        methods: {
            goDetail: function(e) {
                let detail = {
                    author_name: e.author_name,
                    cover: e.cover,
                    id: e.id,
                    published_at: e.published_at,
                    title: e.title,
					content:e.content,
					goodNum:e.goodNum
                };
                uni.navigateTo({
                    url: '../essayDetail/essayDetail?detailDate=' + encodeURIComponent(JSON.stringify(detail))
                });
            },
			getAllEssay(){
				uni.request({
				  url: 'http://localhost:3000/getAllEssay',
				  method: 'GET',
				  success: (res) => {
				    console.log(res);
				    this.listData = []; // 清空订单列表
				    res.data.forEach(item => {
				      const order = {
				        id: item.id,
				        title: item.title,
				        author_name: item.trueName,
				        cover: item.img_src,
				        published_at: item.time,
						goodNum:item.good,
						content:item.content
				      };
				      this.listData.push(order);
				    });
				  },
				  fail: (err) => {
				    console.log(err);
				  }
				});
			},
			jumptoCon(){
			    this.navigateTo({
					URL:''
				})	
			},
			getMyEssay(){
				
			}
        }
    };
</script>
   <style lang="scss">
   .container {
       padding: 20upx; /* Adjust as needed */
       background-color: #fff;
   }
   
   .uni-media-list{
       display: flex;
       flex-direction: row;
       //border: 1rpx solid #333;
       background-color: #fff;
       border-radius: 8upx;
       box-shadow: 0 0 30rpx rgba(0, 0, 0 ,0.1);
       //justify-content: ;
       margin-bottom: 20rpx;
   }
   
   .uni-media-list-logo {
       width: 220upx;
       height: 180upx;
       border-radius: 8upx;
       object-fit: cover;
   }
   
   .uni-media-list-body {
       display: flex;
       flex-direction: column;
       justify-content: space-between;
       margin-left: 20upx; /* Adjust as needed */
   }
   
   .uni-media-list-text-top {
       font-size: 28upx;
       overflow: hidden;
       color: #333;
       margin-top: 10upx; /* Adjust as needed */
   }
   
   .author {
       font-size: 24upx;
       color: cornflowerblue;
       margin-top: 10upx; /* Adjust as needed */
       cursor: pointer;
   }
   
   .uni-media-list-text-bottom {
       display: flex;
       justify-content: space-between;
       font-size: 22upx;
       color: #666;
       margin-top: 10upx; /* Adjust as needed */
   }
   </style>









<!--
	<template>
			<view class="select-area">
				<view class="select-top">
					<view class="course-table">
						<template v-for="(item, index) in changeList" :key="index">  
						    <view :class="['table-item', item.default ? 'table-item-active' : '']"  
						         :style="{borderRadius: index == 0 ? '64rpx 0 0 64rpx' : '0 64rpx 64rpx 0'}"  
						         @click="changeStatus(item)">{{ item.title }}</view>  
						</template>
					</view>
				</view>
			</view>

<view v-if="tabIndex === 1" style="padding: 32rpx">
<view class="container">
	<view class="tab-bar">
	  <text class="tab-item" :class="{ active: currentFilter === 'allEssay' }" @tap="filterOrders('allEssay')">推荐文章</text>
	  <text class="tab-item" :class="{ active: currentFilter === 'myEssay' }" @tap="filterOrders('myEssay')">我的文章</text>
	</view>
    <uni-list>
    		<uni-list-item direction="row" v-for="item in info" :key="item.id" :title="item.title" :note="item.user_name + ' '+item.last_modify_date">
    			<template v-slot:header>
    				<image class="image-1" :src="item.avatar" mode="aspectFill"></image>
    			</template>
    		</uni-list-item>
    	</uni-list>
  </view>
</view>


<view v-if="tabIndex === 0" style="padding: 32rpx">
	<view class="container">
	  <view class="tab-bar">
	    <text class="tab-item" :class="{ active: currentFilter === 'all' }" @tap="filterOrders('all')">全部</text>
	    <text class="tab-item" :class="{ active: currentFilter === 'on' }" @tap="filterOrders('confirmed')">上架中</text>
	    <text class="tab-item" :class="{ active: currentFilter === 'get' }" @tap="filterOrders('processing')">已接取</text>
	    <text class="tab-item" :class="{ active: currentFilter === 'out' }" @tap="filterOrders('completed')">已失效</text>
	  </view>
	  <view class="order-list">
	    <block v-for="(order, index) in filteredOrders" :key="order.id">
	      <navigator :url="'/pages/publishDetail/publishDetail?id=' + order.id" class="order-item" @longpress="deleteOrder(index)">
	        <view class="order-info">
	          <view class="id">{{ publishings.id }}</view>
	          <view class="time">时长: {{ publishings.duration }}分钟</view>
	          <view class="price">价格: ¥{{ publishings.price }}</view>
	          <view class="created_at">{{ publishings.created_at }}</view>
	        </view>
	        <view class="order-status">
	          <text v-if="order.status === 'on'" class="status-confirmed">上架中</text>
	          <text v-if="order.status === 'get'" class="status-processing">已接取</text>
	          <text v-if="order.status === 'out'" class="status-completed">已失效</text>
	        </view>
	      </navigator>
	    </block>
	  </view>
	</view>
	</view>
	</template>
	
	
<script>
	export default{
		
		data(){
			return{
			tabIndex: 0,
			changeList: [{
					id: 0,
					title: '预约',
					default: true},
							{
					id: 1,
					title: '文章',
					default: false}
							],
			searchQuery: '',
			orders: [],
			publishings:[],
			filteredOrders: [],
			currentFilter: 'all',
			essayFilter:'allEssay',
			c_id:''	,
				info: [{
									"user_name": "未来汽车日报",
									"title": "为什么自动驾驶诉讼不断？",
									"avatar": "https://img.36krcdn.com/20200410/v2_9c3331af67e64994aa97a27fffb1a380_img_png?x-oss-process=image/resize,m_mfit,w_520,h_300/crop,w_520,h_300,g_center",
									"last_modify_date": "2020-04-11 17:11:09",
								},
								{
									"user_name": "36氪深度服务",
									"title": "2020数字中国创新大赛-数字政府赛道21强出炉，四大赛题紧贴政府数字化发展需求",
									"avatar": "https://img.36krcdn.com/20200411/v2_16417a06088947debe0450950f8fc813_img_png",
									"last_modify_date": "2020-04-11 17:03:18",
								}, {
									"user_name": "未来汽车日报",
									"title": "地方政府救市哪家强？广州补贴上万元，广深杭新增指标超5万",
									"avatar": "https://img.36krcdn.com/20200410/v2_6905947498bc4ec0af228afed409f771_img_png?x-oss-process=image/resize,m_mfit,w_520,h_300/crop,w_520,h_300,g_center",
									"last_modify_date": "2020-04-11 16:11:11",
								}
							]
			}
		},
		onLoad(){
			    this.getCid();
				this.fetchPublish(this.c_id);
		},
		methods:{
			changeStatus(item) {
					let obj = this.changeList.find(v => v.default)
					if (obj) {
						obj.default = false
						item.default = true
					}
					this.tabIndex = item.id
					if (this.tabIndex == 0 && this.token) {
						this.getScheduleList()
						this.fetchPublish(this.c_id)
					} else if (this.tabIndex == 1 && this.token) {
						this.getPracticeList()
						this.fetchEssay()
					}
				},
				getCid(){
					  this.c_id = getApp().globalData.c_id;
				  },
				fetchEssay() {
				  uni.request({
					url: 'http://localhost:3000/getEssay',
				    method: 'GET',
				    success: (res) => {
				      console.log(res);
				      this.orders = []; // 清空订单列表
				      res.data.forEach(item => {
				        const order = {
				          id: item.id,
				          title: item.title,
				          name: item.trueName,
				          created_at: this.formatDate(new Date(item.create_time)),
				          type: item.type,
						  good:item.good,
						  text:item.content,
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
				
				
				
				
				fetchPublish(c_id) {
				  uni.request({
					url: 'http://localhost:3000/getPublish?c_id=' + encodeURIComponent(c_id),
				    method: 'GET',
				    success: (res) => {
				      console.log(res);
				      this.publishings = []; // 清空订单列表
				      res.data.forEach(item => {
				        const publishing = {
				          id: item.p_id,
				          price: item.price,
				          start: item.start_time,
				          created_at: this.formatDate(new Date(item.create_time)),
				          consult_way: item.consult_way,
						  state:item.is_appointed,
						  finish:item.finish_time,
				        };
				        this.publishings.push(publishing);
				      });
					  	  this.applyPublishings();
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
				applyPublishings() {
				  this.filteredOrders = this.publishings.filter(order => {
				    const matchesStatus = this.currentFilter === 'all' || order.status === this.currentFilter;
				    const matchesSearchQuery = !this.searchQuery || (order.name && order.name.includes(this.searchQuery));
				    return matchesStatus && matchesSearchQuery;
				  });
				},
		},
		}
</script>


<style>
	
	.select-area {
		padding: 0 0 12rpx 0;
		background-color: #fff;
	}
	 
	.select-top {
		position: relative;
		width: 100%;
		height: 88rpx;
	 
		.course-table {
			position: absolute;
			top: 50%;
			left: 50%;
			transform: translate(-50%, -50%);
			display: flex;
			align-items: center;
			width: 408rpx;
			height: 64rpx;
			background-color: #fff;
			border-radius: 64rpx;
			border: 2rpx solid #5990F5;
			overflow: hidden;
			.table-item {
				letter-spacing: 2rpx;
				width: 50%;
				line-height: 64rpx;
				font-size: 28rpx;
				font-family: PingFang SC, PingFang SC;
				color: #5990F5;
				text-align: center;
			}
			.table-item-active {
				color: #fff;
				border: 2rpx solid transparent;
				background-color: #5990F5;
			}
		}
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
	
	.tab-item:active {
	  background-color: #ddd;
	}
	
	.order-list {
	  display: flex;
	  flex-direction: column;
	}
	
	.order-item {
	  display: flex;
	  justify-content: space-between;
	  align-items: center;
	  padding: 10px;
	  border-bottom: 1px solid #eee;
	}
	
	.order-status {
	  width: 80px;
	  text-align: center;
	  font-size: 36rpx;
	}
	
	.status-pending {
	  color: #007AFF;
	}
	
	.status-processing {
	  color: #FFA500;
	}
	
	.status-completed {
	  color: #00C853;
	}
	
	.status-confirmed {
	  color: #4CAF50;
	}
	
	/* 通用样式，为所有text元素设置一个基本的字体样式 */  
	.order-info text {  
	    font-family: Arial, sans-serif; /* 设置字体 */  
	    color: #333; /* 设置文字颜色 */  
	    margin-bottom: 10px; /* 设置元素之间的间距 */  
	}  
	  
	/* 订单名称样式 */  
	.name {  
	    font-size: 18px; /* 字体大小 */  
	    font-weight: bold; /* 加粗 */  
	}  
	  
	/* 订单生成时间样式 */  
	.created_at {  
	    color: #666; /* 稍微深一点的灰色 */  
	}  
	  
	/* 订单时长样式 */  
	.time {  
	    color: #008000; /* 绿色，表示时长可能是一个重要的信息点 */  
	}  
	  
	/* 价格样式 */  
	.price {  
	    color: #FF0000; /* 红色，突出价格信息 */  
	    font-weight: bold; /* 加粗 */  
	}  
	  
	/* 订单号样式 */  
	.id {  
	    /*color: #0000FF;  蓝色，表示这是一个链接或唯一标识符 */  
	    font-family: monospace; /* 使用等宽字体，使数字看起来更整齐 */  
		font-size: 30rpx;
	}  
	  
	/* 额外的样式，增加整个订单信息区块的边框和内边距 */  
	.order-info {  
	    /*border: 1px solid #ccc;  边框 */  
	    /*padding: 20px;  内边距 */  
	    margin-top: 0px; /* 与上方内容的间距 */  
	    /*border-radius: 5px;  边框圆角 */  
	    /*background-color: #f9f9f9;  背景色，稍微浅一点 */ 
		flex: 1;
		font-size: 36rpx;
	}
	
	.content {
			display: flex;
			flex-direction: column;
			align-items: center;
			justify-content: center;
		}
	
		.image-1 {
			flex-shrink: 0;
			margin-right: 10px;
			width: 125px;
			height: 75px;
			border-radius: 6px;
			overflow: hidden;
			border: 1px #f5f5f5 solid;
		}
	
</style>-->