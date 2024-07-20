<template>
  <view class="container">
    <view class="top">
      <text class="topic">文章类型：</text>
      <view class="radio-group">
        <label class="radio-label" @click="selectOption('科普')">
          <view :class="['radio-circle', { 'radio-selected': radioValue === '科普' }]">
            <text class="radio-text">科普</text>
          </view>
        </label>
        <label class="radio-label" @click="selectOption('论坛')">
          <view :class="['radio-circle', { 'radio-selected': radioValue === '论坛' }]">
            <text class="radio-text">论坛</text>
          </view>
        </label>
        <label class="radio-label" @click="selectOption('讲座')">
          <view :class="['radio-circle', { 'radio-selected': radioValue === '讲座' }]">
            <text class="radio-text">讲座</text>
          </view>
        </label>
        <label class="radio-label" @click="selectOption('贴士')">
          <view :class="['radio-circle', { 'radio-selected': radioValue === '贴士' }]">
            <text class="radio-text">贴士</text>
          </view>
        </label>
      </view>
    </view>
    <view class="main-content">
      <view class="input-area">
	    <textarea inputmode="text" class="input-title" maxlength="2000" placeholder="请输入文章标题" placeholder-class="inputtext" v-model="title" focus="true"></textarea>
        <textarea inputmode="text" class="input" maxlength="2000" placeholder="请输入文章内容" placeholder-class="inputtext" v-model="content" focus="true"></textarea>
      </view>
      <uni-file-picker limit="1" title="最多选择1张图片" @file-selected="handleFileSelected"></uni-file-picker>
      <button class="publish-btn" @click="publishPost" :disabled="loading">发布帖子</button>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      radioValue: '科普', // 默认选中的值
      content: '', // 信息
	  title:'',
      selectedFile: null, // 选中的文件
      id: '',
	  e_id:'',
      loading: false ,// 添加 loading 状态,
	  image:'https://pic3.zhimg.com/v2-ea09f2f374140162e7cdff5071cfa49a_r.jpg'
    };
  },
  async onLoad() {
  },
  methods: {
    selectOption(value) {
      this.radioValue = value;
    },
    handleFileSelected(event) {
      this.selectedFile = event.detail.file;
      console.log(this.selectedFile);
    },
    async publishPost() {
      if (!this.title.trim()) {
        uni.showToast({
          title: '请输入文章标题',
          icon: 'none'
        });
        return;
      }
      if (!this.content.trim()) {
        uni.showToast({
          title: '请输入文章内容',
          icon: 'none'
        });
        return;
      }
      this.loading = true;
      try {
		  uni.request({
		    url: 'http://localhost:3000/addEssay',
		    method: 'POST',
		    data: {
		      c_id: getApp().globalData.c_id,
			  title:this.title,
			  content:this.content,
			  type:this.radioValue,
			  time:this.getCurrentDate(),
			  image:this.image,
		    },
			success(res) {
			  this.e_id = res.insertedId;
			  console.log(res);

			},
			fail(err) {
			  console.log(err);
			  }
			  });
        uni.showToast({
          title: '发布成功',
          icon: 'success'
        });
		await uni.$emit('pagecountChange');
		await uni.navigateBack(-1);
		// 清空输入框和文件选择
        this.selectedFile = null;
      } catch (error) {
        console.log(error);
        uni.showToast({
          title: '发布失败',
          icon: 'none'
        });
      } finally {
        this.loading = false;
      }
    },
	getCurrentDate() {
	      const now = new Date();
	      const year = now.getFullYear();
	      const month = (now.getMonth() + 1).toString().padStart(2, '0');
	      const day = now.getDate().toString().padStart(2, '0');
	      return `${year}-${month}-${day}`;
	    },
    }
};
</script>

<style lang="scss">
.container {
  margin-top: 16px;
}

.topPage {
  width: 100vw;
  font-size: 20px;
  font-weight: bold;
  text-align: center;
  margin-bottom: 20px;
}

.top {
  padding: 5px;
  margin-bottom: 20px;
  margin-left: 10px;
  display: flex;
  flex-direction: row;
  box-shadow: 0 0 30rpx rgba(0, 0, 0, 0.05);
  border: 2px solid #fff;
  background: #fff;
}

.topic {
  font-size: 12px;
  margin-top: 5px;
}

.radio-group {
  display: flex;
  flex-wrap: wrap;
  gap: 5px;
}

.radio-label {
  font-size: 12px;
  display: flex;
  align-items: center;
  cursor: pointer;
}

.radio-circle {
  width: 40px;
  height: 20px;
  border-radius: 20%;
  padding: 0 5px;
  border: 2px solid #ccc;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: background-color 0.3s;
}

.radio-selected {
  border-color: #3399ff;
  background-color: #3399ff;
}

.radio-text {
  color: #333;
}

.radio-selected .radio-text {
  color: #fff;
}

.main-content {
  background-color: white;
  padding: 5px;
  border-radius: 8px;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
}

.input-area {
  margin-bottom: 20px;
}

.input {
  width: 100%;
  height: 500rpx;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
  
}

.input-title {
  width: 100%;
  height: 60rpx;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
}

.inputtext {
  margin-top: 10px;
}

.publish-btn {
  width: 100%;
  padding: 10px;
  background-color: #3399ff;
  color: white;
  text-align: center;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  margin-top: 60rpx;
}

.publish-btn:disabled {
  background-color: #ccc;
}

</style>

