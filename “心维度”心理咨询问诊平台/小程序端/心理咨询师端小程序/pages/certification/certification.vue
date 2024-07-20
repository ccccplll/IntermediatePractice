<template>
       <!-- <view class="container">
<view class="input-area">
	    <textarea inputmode="text" class="input-title" maxlength="2000" placeholder="请输入文章标题" placeholder-class="inputtext" v-model="title" focus="true"></textarea>
        <textarea inputmode="text" class="input" maxlength="2000" placeholder="请输入文章内容" placeholder-class="inputtext" v-model="content" focus="true"></textarea>
      </view>-->
      <uni-file-picker limit="1" title="请提交相关资质证书图片" @file-selected="handleFileSelected"></uni-file-picker>
      <button class="publish-btn" @click="apply" :disabled="loading">提交认证</button>
</template>

<script>
export default {
  data() {
    return {
	  image:'https://p4.itc.cn/images01/20230626/a7a62b721ceb4fbca7932e429d9528a1.png'
    };
  },
  async onLoad() {
  },
  methods: {
    async apply() {
      try {
		  uni.request({
		    url: 'http://localhost:3000/putApply',
		    method: 'POST',
		    data: {
		      c_id: getApp().globalData.c_id,
			  date:this.getCurrentDate(),
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