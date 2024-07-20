<template>
  <view class="container">
    <view class="header">
      <navigator class="back" url="../profile/profile">
        <text class="back-icon">〈</text>
      </navigator>
      <text class="title">更改名字</text>
      <button class="save" :disabled="!name" @click="saveName">保存</button>
    </view>
    <view class="content">
      <input class="name-input" v-model="name" placeholder="请输入新的名字" />
      <view class="tip">好名字可以让你的朋友更容易记住你。</view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      name: ''
    };
  },
  onLoad(options) {
    this.name = options.name || '木匾';
  },
  methods: {
    saveName() {
	   uni.setStorageSync('name', this.name);
      // 保存逻辑，例如发送到服务器或本地存储
      console.log('保存名字:', this.name);
      uni.showToast({
        title: '保存成功',
        icon: 'success',
        duration: 2000
      });
      // 返回到个人信息页面
      setTimeout(() => {
        uni.navigateBack();
      }, 2000);
    }
  }
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  height: 100%;
}
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  border-bottom: 1px solid #ccc;
}
.back {
  flex: 1;
}
.back-icon {
  font-size: 24px;
}
.title {
  flex: 3;
  text-align: center;
  font-size: 18px;
}
.save {
  flex: 1;
  background-color: transparent;
  color: #007aff;
  border: none;
  font-size: 16px;
}
.save:disabled {
  color: #ccc;
}
.content {
  flex: 1;
  padding: 20px;
}
.name-input {
  width: 100%;
  padding: 10px;
  margin-bottom: 20px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
}
.tip {
  color: #999;
  font-size: 14px;
}
</style>
