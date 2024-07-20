<template>
	<view class="top">倾听者{{trueName}}您好！在本平台您已接收咨询者{{accumulatedHour}}</view>
	
	
  <view class="container">
    <scroll-view class="comment-list" style="height: calc(100vh - 250rpx);" scroll-y>
        <view class="comment-title">
          {{ commentTitle }}
        </view>
        <view v-for="(c, index) in commentList" :key="index" class="comment-root" @click="goToReplyComment(c)">
          <view class="comment-wrapper">
            <image :src="userAvatarUrl" class="comment-avatar"></image>
            <view class="comment-info">
              <view class="comment-user-name">用户**{{ c.u_id }}</view>
              <view class="comment-create-time">{{ c.createTime }}</view>
              <view class="comment-content">
                <text>{{ c.content }}</text>
              </view>
            </view>
          </view>
        </view>
    </scroll-view>
	</view>
</template>

<script>
export default {
  data() {
    return {
		accumulatedHour:getApp().globalData.accumulatedHour,
		trueName:getApp().globalData.trueName,
	  userAvatarUrl:'https://avater1-1327800397.cos.ap-guangzhou.myqcloud.com/avater/12.png',
      commentTitle: '评论',
      commentList: [],
	  newComment:'',
    };
  },
   onLoad(options) {
	this.fetchComments();
  },
  methods: {
	fetchComments(){
		uni.request({
			url:'http://localhost:3000/getPageComments',
			method:'POST',
			data:{c_id:getApp().globalData.c_id},
			success: (res) => {
				console.log(res);
				this.commentList=[];
				this.commentList = res.data.map((item) => {
					return{
						u_id:item.u_id,
						createTime:item.time,
						content:item.content,
					};
				});
			},
			fail: (err) => {
				console.log(err);
			}
		})
	},
    goToReplyComment(c) {
      // Implementation for navigating to reply to a comment
    },
  },
};
</script>

<style lang="scss">
.container {
  height: 100%;
  background-color: #f5f5f5;
  padding-bottom: 40rpx;
}


.comment-list {
  padding: 10px;
  height: calc(100vh - 250rpx);/* 计算评论区高度，减去顶部和底部的预留空间 */
  //margin-bottom: 40rpx; 
}

.comment-title {
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 10px;
}

.comment-root {
  margin-bottom: 10px;
  padding: 10px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0px 2px 5px 0px rgba(0, 0, 0, 0.1);
  cursor: pointer;
}

.comment-wrapper {
  display: flex;
  align-items: flex-start;
}

.comment-avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  margin-right: 10px;
}

.comment-info {
  flex: 1;
}

.comment-user-name {
  font-size: 14px;
  font-weight: bold;
}

.comment-create-time {
  font-size: 12px;
  color: #999;
  margin-top: 2px;
}

.comment-content {
  margin-top: 5px;
  padding-right: 10px;
}

.comment-publish-area {
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  background-color: white;
  padding: 10px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-top: 1px solid #ccc;
}

.comment-publish-input-wrapper {
  flex: 1;
}

.comment-publish-input {
  width: 90%;
  height: 30px;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 14px;
  resize: none;
}

.comment-publish-button {
  background-color: #3399ff;
  color: white;
  padding: 8px 20px;
  border-radius: 4px;
  font-size: 14px;
  text-align: center;
  cursor: pointer;
}

.comment-publish-button.disabled {
  opacity: 0.6;
  pointer-events: none;
}
</style>

