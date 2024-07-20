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
		<view class="bottom-button-container">
		      <button class="bottom-button" @click="addEssay">+</button>
		    </view>

</template>

<script>
    
    export default {
		props: {
					//是否禁用拖动
					disabled: {
						type: Boolean,
						default: false
					},
					//是否自动停靠
					canDocking: {
						type: Boolean,
						default: true
					},
					//按钮默认位置离底部距离（px）
					bottomPx: {
						type: Number,
						default: 30
					},
					//按钮默认位置离右边距离（px）
					rightPx: {
						type: Number,
						default: 0
					},
				},
        data() {
            return {
                listData: [
                ],
				c_id:getApp().globalData.c_id
            };
        },
        onLoad() {
			this.getMyEssay();
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
			addEssay(){
				uni.navigateTo({
					url:'/pages/addEssay/addEssay'
				})
				
			},
			jumptoCon(){
			    this.navigateTo({
					URL:''
				})	
			},
			getMyEssay(){
				uni.request({
				  url: 'http://localhost:3000/getMyEssay',
				  method: 'GET',
				  data:{c_id:getApp().globalData.c_id},
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
						content:item.content,
						state:item.state,
				      };
				      this.listData.push(order);
				    });
				  },
				  fail: (err) => {
				    console.log(err);
				  }
				});
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
  
  .bottom-button-container {
    position: absolute;
    width: 100%;
    bottom: 15px;
    display: flex;
    justify-content: center;
  }
   
  .bottom-button {
    width: 50px;
    height: 50px;
    border-radius: 50%;
    background-color: #007aff;
    color: white;
    font-size: 20px;
    box-shadow: 0 1px 10px rgba(0, 0, 0, 0.1);
  }
</style>
