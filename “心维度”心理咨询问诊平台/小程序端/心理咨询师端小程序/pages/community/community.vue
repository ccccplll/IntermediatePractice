<template>
  <view class="container">
    <view class="box">
      <input type="text" placeholder="搜索帖子" class="search-input" v-model="searchValue" @input="searchCards" />
    </view>
    <view class="tabs">
      <text class="tab-item" :class="{ active: currentTab === 'all' }" @tap="filterCard('all')">全部</text>
      <text class="tab-item" :class="{ active: currentTab === 'help' }" @tap="filterCard('help')">求助</text>
      <text class="tab-item" :class="{ active: currentTab === 'marriage' }" @tap="filterCard('marriage')">婚姻</text>
      <text class="tab-item" :class="{ active: currentTab === 'emotion' }" @tap="filterCard('emotion')">情感</text>
      <text class="tab-item" :class="{ active: currentTab === 'family' }" @tap="filterCard('family')">家庭</text>
      <text class="tab-item" :class="{ active: currentTab === 'campus' }" @tap="filterCard('campus')">校园</text>
    </view>
    <view class="card-list">
      <uni-section v-for="(card, index) in filteredCards" :key="card.id">
        <navigator :url="'/pages/post/post?id=' + card.id" class="uni-card">
          <view class="item-top">
            <image :src="card.avatar" class="avatar"></image>
            <text class="name">用户**{{card.u_id}}</text>
            <text class="time">{{card.publish_time}}</text>
          </view>
          <view class="item-middle">
            <text>{{card.pagetxt}}</text>
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
			  <image :src="card.goodSrc" mode="aspectFill"></image>
              <text class="item-text">点赞{{card.goodcount}}</text>
            </view>
            <view class="item-talk" @click="actionsClick('评论', card)">
			  <image src="../../static/评论_comments.png"></image>
              <text class="item-text">评论{{card.txtcount}}</text>
            </view>
          </view>
        </navigator>
      </uni-section>
    </view>
    <uni-fab ref="fab" :pattern="pattern" :content="content" :horizontal="horizontal" :vertical="vertical" :direction="direction" @fabClick="fabClick" @trigger="trigger" />
  </view>
</template>

<script>
export default {
  async onLoad() {
    await this.fetchId();
    await this.fetchLiked();
    this.fetchCard();
  },
  data() {
    return {
      id: '',
      liked: [],
      searchValue: '',
      currentTab: 'all',
      cards: [],
      filteredCards: [],
      content: [{
        iconPath: '../../static/star.png',
        selectedIconPath: '../../static/star-active.png',
        text: '发布帖子',
        active: false
      }],
      pattern: {},
      horizontal: 'right',
      vertical: 'bottom',
      direction: 'horizontal'
    };
  },
  watch: {

    cards: 'applyFilters',
    searchValue: 'applyFilters',
    currentTab: 'applyFilters'
  },
  mounted() {
  	uni.$on('pagecountChange',this.fetchCard);
  },
  beforeDestroy() {
  	uni.$off('pagecountChange',this.fetchCard);
  },
  methods: {
    fetchId() {
      return new Promise((resolve, reject) => {
        uni.request({
          url: 'http://localhost:3000/selectInfor',
          method: 'GET',
          data: { name: getApp().globalData.userName },
          success: (res) => {
            this.id = res.data[0].id;
            resolve();
          },
          fail: (err) => {
            console.log(err);
            reject(err);
          }
        });
      });
    },
    fetchLiked() {
      return new Promise((resolve, reject) => {
        uni.request({
          url: 'http://localhost:3000/getAllGood',
          method: 'GET',
          data: { u_id: this.id },
          success: (res) => {
            this.liked = res.data;
            resolve();
          },
          fail: (err) => {
            console.log(err);
            reject(err);
          }
        });
      });
    },
    fetchCard() {
      uni.request({
        url: 'http://localhost:3000/getPages',
        method: 'GET',
        success: (res) => {
          this.cards = res.data.map((item) => {
            const likedItem = this.liked.find(like => like.page_id === item.id);
            const liked = likedItem ? likedItem.liked : 0;
            const goodSrc = this.getSrc(liked);
            return {
              avatar: this.getAvatar(),
              id: item.id,
              u_id: item.u_id,
              image: item.image,
              pagetxt: item.pagetxt,
              goodcount: item.goodcount,
              publish_time: this.formatDate(new Date(item.publish_time)),
              state: this.mapState(item.state),
              txtcount: item.txtcount,
              liked: liked,
              goodSrc: goodSrc,
            };
          });
          this.applyFilters();
        },
        fail: (err) => {
          console.log(err);
        }
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
        '求助': 'help',
        '婚姻': 'marriage',
        '情感': 'emotion',
        '家庭': 'family',
        '校园': 'campus'
      };
      return stateMap[state] || 'all';
    },
    filterCard(state) {
      this.currentTab = state;
    },
    applyFilters() {
      this.filteredCards = this.cards.filter(card => {
        const matchesStatus = this.currentTab === 'all' || card.state === this.currentTab;
        const matchesSearchQuery = !this.searchValue || card.pagetxt.includes(this.searchValue);
        return matchesStatus && matchesSearchQuery;
      });
    },
    searchCards() {
      this.applyFilters();
    },
    getSrc(liked) {
      return liked === 1 ? '../../static/喜欢_like (1).png' : '../../static/喜欢_like.png';
    },
    toggleLike(card) {
      card.liked = (card.liked === 1) ? 0 : 1;
      card.goodcount += (card.liked === 1) ? 1 : -1;
      card.goodSrc = this.getSrc(card.liked);
      console.log(card.goodcount);
      this.changeLiked(card);
      this.changeGood(card);
      console.log(card.goodcount);
    },
    changeLiked(card) {
      uni.request({
        url: 'http://localhost:3000/changeLiked',
        method: 'POST',
        data: {
          u_id: this.id,
          page_id: card.id,
        },
        success: (res) => {
          console.log("likechange");
        },
        fail: (err) => {
          console.log(err);
        }
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
          console.log("goodc");
          console.log(res);
        },
        fail: (err) => {
          console.log(err);
        }
      });
    },
    actionsClick(action, card) {
      console.log(`${action} clicked for post ${card.id}`);
    },
    fabClick() {
      console.log('Floating Action Button clicked');
    },
    trigger(e) {
		console.log(e);
		this.content[e.index].active = !e.item.active;
		uni.navigateTo({
			url: '/pages/addPost/addPost'
		});
    },
  }
};
</script>


<style lang="scss">
  .container {
    padding: 20rpx;
    background-color: #f5f5f5;
  }
  .box {
    margin-bottom: 20rpx;
  }
  .search-input {
    width: 100%;
    padding: 20rpx;
    border: 1px solid #ccc;
    border-radius: 10rpx;
    background-color: #fff;
    font-size: 28rpx;
  }
  .tabs {
    display: flex;
    justify-content: space-around;
    margin-bottom: 20rpx;
  }
  .tab-item {
    padding: 20rpx;
    color: #666;
    font-size: 32rpx;
  }
  .tab-item.active {
    color: #007AFF;
    border-bottom: 4rpx solid #007AFF;
  }
  .card-list {
    display: flex;
    flex-direction: column;
  }
  .uni-card {
    background-color: #fff;
    padding: 20rpx;
    margin-bottom: 10rpx;
    border-radius: 20rpx;
    box-shadow: 0 4rpx 8rpx rgba(0, 0, 0, 0.1);
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
</style>
