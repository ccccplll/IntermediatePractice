post.vue

<template>
  <view class="container">
    <view class="top">
      <view class="item-top">
        <image :src="card.avatar" class="avatar"></image>
        <text class="name">用户**{{card.u_id}}</text>
        <text class="time">{{ card.publish_time }}</text>
      </view>
      <view class="item-middle">
        <text>{{ card.pagetxt }}</text>
        <image :src="card.image" class="card-image" mode="widthFix"></image>
      </view>
      <view class="item-bottom">
        <view class="item-state">
          <text v-if="card.state === 'help'" class="help">求助</text>
          <text v-if="card.state === 'marriage'" class="marriage">婚姻</text>
          <text v-if="card.state === 'emotion'" class="emotion">情感</text>
          <text v-if="card.state === 'family'" class="family">家庭</text>
          <text v-if="card.state === 'campus'" class="campus">校园</text>
        </view>
        <view class="item-good" @click.stop="toggleLike(card)">
          <image :src="card.goodSrc"></image>
          <text class="item-text">点赞 {{ card.goodcount }}</text>
        </view>
        <view class="item-talk">
          <image src="../../static/评论_comments.png"></image>
          <text class="item-text">评论 {{ card.txtcount }}</text>
        </view>
      </view>
    </view>

    <view class="line"></view>

    <scroll-view class="comment-list" style="height: calc(100vh - 250rpx);" scroll-y>
        <view class="comment-title">
          {{ commentTitle }}
        </view>
        <view v-for="(c, index) in commentList" :key="index" class="comment-root" @click="goToReplyComment(c)">
          <view class="comment-wrapper">
            <image :src="c.userAvatarUrl" class="comment-avatar"></image>
            <view class="comment-info">
              <view class="comment-user-name">咨询师**{{ this.c_id }}</view>
              <view class="comment-create-time">{{ c.createTime }}</view>
              <view class="comment-content">
                <text>{{ c.content }}</text>
              </view>
            </view>
          </view>
        </view>
      </scroll-view>


    <view class="comment-publish-area">
      <view class="comment-publish-input-wrapper">
        <textarea v-model="newComment" class="comment-publish-input" placeholder="写评论..."></textarea>
      </view>
      <view class="comment-publish-button" @click="publishComment">发送</view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      card: {
        avatar: '',
        id: '',
        u_id: '',
        image: '',
        pagetxt: '',
        goodcount: '',
        publish_time: '',
        state: '',
        txtcount: '',
        liked: '',
        goodSrc: '',
      },
      liked: '',
      commentTitle: '评论区',
      commentList: [],
	  newComment:'',
	  c_id:getApp().globalData.c_id,
    };
  },
  async onLoad(options) {
    this.card.id = options.id;
    await this.fetchId();
    await this.fetchLiked();
    this.fetchCard();
	this.fetchComments();
  },
  methods: {
	fetchComments(){
		uni.request({
			url:'http://localhost:3000/getPageComments',
			method:'GET',
			data:{t_id:this.card.id},
			success: (res) => {
				console.log(res);
				this.commentList=[];
				this.commentList = res.data.map((item) => {
					return{
						userAvatarUrl: this.getAvatar(),
						u_id:item.u_id,
						createTime:this.formatDate(new Date(item.create_time)),
						content:item.content,
					};
				});
			},
			fail: (err) => {
				console.log(err);
			}
		})
	},
    fetchId() {
      return new Promise((resolve, reject) => {
        uni.request({
          url: 'http://localhost:3000/selectInfor',
          method: 'GET',
          data: { name: getApp().globalData.userName },
          success: (res) => {
            this.card.u_id = res.data[0].id;
            resolve();
          },
          fail: (err) => {
            console.log(err);
            reject(err);
          },
        });
      });
    },
    fetchLiked() {
      return new Promise((resolve, reject) => {
        uni.request({
          url: 'http://localhost:3000/getGood',
          method: 'GET',
          data: {
            u_id: this.card.u_id,
            page_id: this.card.id,
          },
          success: (res) => {
            this.liked = res.data.liked;
            resolve();
          },
          fail: (err) => {
            console.log(err);
            reject(err);
          },
        });
      });
    },
    fetchCard() {
      uni.request({
        url: 'http://localhost:3000/getOnePages',
        method: 'GET',
        data: { id: this.card.id },
        success: (res) => {
          this.card = {
            ...this.card,
            avatar: this.getAvatar(),
            image: res.data[0].image,
            pagetxt: res.data[0].pagetxt,
            goodcount: res.data[0].goodcount,
            publish_time: this.formatDate(new Date(res.data[0].publish_time)),
            state: this.mapState(res.data[0].state),
            txtcount: res.data[0].txtcount,
            liked: this.liked,
            goodSrc: this.getSrc(this.liked),
          };
        },
        fail: (err) => {
          console.log(err);
        },
      });
    },
    getAvatar() {
      return '../../static/可爱粽子.png';
    },
	formatDate(date) {
      const pad = (n) => n < 10 ? '0' + n : n;
      return `${date.getFullYear()}-${pad(date.getMonth() + 1)}-${pad(date.getDate())} ${pad(date.getHours())}:${pad(date.getMinutes())}:${pad(date.getSeconds())}`;
    },
    mapState(state) {
      const stateMap = {
        求助: 'help',
        婚姻: 'marriage',
        情感: 'emotion',
        家庭: 'family',
        校园: 'campus',
      };
      return stateMap[state] || 'all';
    },
    getSrc(liked) {
      return liked === 1 ? '../../static/喜欢_like (1).png' : '../../static/喜欢_like.png';
    },
    toggleLike(card) {
      card.liked = card.liked === 1 ? 0 : 1;
      card.goodcount += card.liked === 1 ? 1 : -1;
      card.goodSrc = this.getSrc(card.liked);
      this.changeLiked(card);
      this.changeGood(card);
    },
    changeLiked(card) {
      uni.request({
        url: 'http://localhost:3000/changeLiked',
        method: 'POST',
        data: {
          u_id: this.card.u_id,
          page_id: card.id,
        },
        success: () => {
          console.log('likechange');
        },
        fail: (err) => {
          console.log(err);
        },
      });
    },
    changeGood(card) {
      uni.request({
        url: 'http://localhost:3000/changeGood',
        method: 'POST',
        data: {
          id: card.id,
          goodcount: card.goodcount,
        },
        success: (res) => {
          console.log('goodc');
          console.log(res);
        },
        fail: (err) => {
          console.log(err);
        },
      });
    },
    publishComment() {
      uni.request({
      	url:'http://localhost:3000/insertNewComment',
		method:'POST',
		data:{
			t_id:this.card.id,
			u_id:this.card.u_id,
			create_time:this.formatDate(new Date()),
			content:this.newComment,
		},
		success: (res) => {
			console.log(res);
			this.fetchComments();
			this.card.txtcount++;
			this.updateTxtcount();
			this.newComment=[];
		},
		fail: (err) => {
			console.log(err);
		}
      })
    },
	updateTxtcount(){
		uni.request({
			url:'http://localhost:3000/updateTxtcount',
			method:'POST',
			data:{
				id:this.card.id,
				txtcount:this.card.txtcount,
			},
			success: (res) => {
				console.log(res);
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

.top {
  margin: 2%;
  background-color: white;
  padding: 10px;
  border-radius: 8px;
  box-shadow: 0px 2px 5px 0px rgba(0, 0, 0, 0.1);
}

  .item-top {
    display: flex;
    align-items: center;
    margin-bottom: 20rpx;
  }
  .avatar {
    width: 80rpx;
    height: 80rpx;
    border-radius: 40rpx;
    margin-right: 20rpx;
  }
  .name {
    font-weight: bold;
    font-size: 32rpx;
    margin-right: auto;
  }
  .time {
    color: #999;
    font-size: 24rpx;
  }
  .item-middle {
    display: flex;
    flex-direction: column;
    margin-bottom: 20rpx;
    margin-left: 10rpx;
    font-size: 28rpx;
    color: #333;
  }
  .card-image {
    width: 50%;
    border-radius: 10rpx;
    margin-top: 10rpx;
  }
  .item-bottom {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .item-state {
    display: flex;
    align-items: center;
    font-size: 24rpx;
  }
  .help, .marriage, .emotion, .family, .campus {
    background-color: #007AFF;
    color: #fff;
    padding: 6rpx 12rpx;
    border-radius: 10rpx;
    margin-right: 20rpx;
  }
  .item-good, .item-talk {
    display: flex;
    align-items: center;
    font-size: 24rpx;
  }
  .item-good image {
    width: 35rpx;
    height: 35rpx;
  }
  .item-talk image {
    width: 35rpx;
    height: 35rpx;
  }
  .item-text {
    margin-left: 8rpx;
    color: #999;
  }

.line {
  height: 1px;
  background-color: #ccc;
  margin: 10px 0;
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

