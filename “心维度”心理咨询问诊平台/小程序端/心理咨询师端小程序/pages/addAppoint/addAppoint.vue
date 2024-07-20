
<template>
  <view class="container">
    <view class="topPage">发布预约</view>
    <view class="top">
      <text class="topic">选择咨询方式：</text>
      <view class="radio-group" value>
        <label class="radio-label" @click="selectOption('线下咨询')">
          <view :class="['radio-circle', { 'radio-selected': consult_way === '线下咨询' }]">
            <text class="radio-text">线下咨询</text>
          </view>
        </label>
        <label class="radio-label" @click="selectOption('语音咨询')">
          <view :class="['radio-circle', { 'radio-selected': consult_way === '语音咨询' }]">
            <text class="radio-text">语音咨询</text>
          </view>
        </label>
      </view>
    </view>
		<view class="input-area">
		 <textarea inputmode="text" class="input" maxlength="2000" placeholder="请设置咨询价格" placeholder-class="inputtext" v-model="price" focus="true"></textarea>
		</view>
      <view class="input-area">
        <textarea inputmode="text" class="input" maxlength="2000" placeholder="请设置开始时间" placeholder-class="inputtext" v-model="start_time" focus="true"></textarea>
      </view>
	  <view class="input-area">
	    <textarea inputmode="text" class="input" maxlength="2000" placeholder="请设置结束时间" placeholder-class="inputtext" v-model="finish_time" focus="true"></textarea>
	  </view>
      <button class="publish-btn" @click="publishPost" :disabled="loading">发布预约</button>
    </view>
</template>

<script>
export default {
  data() {
    return {
      radioValue: '求助', // 默认选中的值
      postContent: '', // 信息
      selectedFile: null, // 选中的文件
      c_id: getApp().globalData.c_id,
	  price:'',
	  consult_way:'线下咨询',
	  start_time:'',
	  finish_time:'',
      loading: false // 添加 loading 状态
    };
  },
  methods: {
    selectOption(value) {
      this.consult_way = value;
    },
    handleFileSelected(event) {
      this.selectedFile = event.detail.file;
      console.log(this.selectedFile);
    },
    async publishPost() {
      if (!this.price.trim()) {
        uni.showToast({
          title: '请设置价格信息',
          icon: 'none'
        });
        return;
      }
	  if (!this.start_time.trim()) {
	    uni.showToast({
	      title: '请设置开始时间',
	      icon: 'none'
	    });
	    return;
	  }
	  if (!this.finish_time.trim()) {
	    uni.showToast({
	      title: '请设置结束时间',
	      icon: 'none'
	    });
	    return;
	  }
      this.loading = true;
      
      try {
        const res = await this.postAppoint();
        console.log(res);
        uni.showToast({
          title: '发布成功',
          icon: 'success'
        });
		await uni.$emit('pagecountChange');
		await uni.navigateBack(-1);
		
		// 清空输入框和文件选择
        this.price = '';
		this.start_time='';
		this.finish_time='';
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
	postAppoint(){
		console.log('success');
		this.submitted = true;  
		uni.request({
		    url: 'http://localhost:3000/putProduction',
		    method: 'POST',
		    data: {
		      price: this.price,
		      consult_way: this.consult_way,
			  start_time: this.start_time,
			  finish_time: this.finish_time,
			  c_id:this.c_id,
			  create_time:this.getCurrentDate(),
		    },
		  });
	},
	getCurrentDate() {
	      const now = new Date();
	      const year = now.getFullYear();
	      const month = (now.getMonth() + 1).toString().padStart(2, '0');
	      const day = now.getDate().toString().padStart(2, '0');
	      return `${year}-${month}-${day}`;
	    },
    uploadFile(file) {
      return new Promise((resolve, reject) => {
        uni.uploadFile({
          url: 'http://localhost:3000/uploadFile', // 上传文件接口
          filePath: file.path,
          name: 'file',
          success: (uploadFileRes) => {
            const data = JSON.parse(uploadFileRes.data);
            if (data.url) {
              resolve(data.url);
            } else {
              reject('上传失败');
            }
          },
          fail: (err) => {
            console.log(err);
            reject(err);
          }
        });
      });
    },

    formatDate(date) {
      const pad = (n) => (n < 10 ? '0' + n : n);
      return `${date.getFullYear()}-${pad(date.getMonth() + 1)}-${pad(date.getDate())} ${pad(date.getHours())}:${pad(date.getMinutes())}:${pad(date.getSeconds())}`;
    }
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
  width: 110px;
  height: 30px;
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
  margin-bottom: 10px;
}

.input {
  margin-left: 20%;
  width: 50%;
  height: 40rpx;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
}

.inputtext {
  margin-top: 1px;
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
