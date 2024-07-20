<template>  
  <view class="login-container">
    <view class="login-header">  
      <text class="title">意见反馈</text>  
    </view>  
    <view class="login-body">  
      <input type="text" class="input-item" placeholder="请输入用户名" v-model="name" />  
      <input type="text" class="input-item" placeholder="请输入邮箱" v-model="email" />  
  	  <input type="text" class="input-item" placeholder="请输入反馈内容" v-model="message" />
      <button class="login-btn" @click="submitFeedback()">提交反馈</button>  
    </view>  
  </view>  
</template>  
  
  
<script>  
export default {  
  name: 'Feedback',  
  data() {  
    return {  
        name: '',  
        email: '',  
        message: ''  ,
      submitted: false  
    }
  },  
  methods: {  
    submitFeedback() {  
	  console.log('success');
	  this.submitted = true;  
      uni.request({
          url: 'http://localhost:3000/insertBug',
          method: 'POST',
          data: {
            name: this.name,
            bug: this.message,
      	    email: this.email,
          },
        });
	 this.email = '';
	 this.name = '';
	 this.message = '';
		}
      },
};  
</script>  
  
<style scoped>  
.feedback-page {  
  padding: 20px;  
  max-width: 600px;  
  margin: 0 auto;  
}  
  
h1 {  
  color: #333;  
  text-align: center;  
}  
  
.form-group {  
  margin-bottom: 15px;  
}  
  
.form-group label {  
  display: block;  
  margin-bottom: 5px;  
}  
  
.form-group input,  
.form-group textarea {  
  width: 100%;  
  padding: 8px;  
  box-sizing: border-box;  
}  
  
button {  
  width: 100%;  
  padding: 10px;  
  background-color: #007bff;  
  color: white;  
  border: none;  
  cursor: pointer;  
}  
  
button:hover {  
  background-color: #0056b3;  
}  
  
.submission-message {  
  margin-top: 20px;  
  text-align: center;  
}  

.login-container {  
  display: flex;  
  flex-direction: column;  
  align-items: center;  
  justify-content: center;  
  height: 100vh;  
  background-color: #f2f2f2;  
  background-size: cover;
}  
  
.login-header {  
  margin-bottom: 20px;  
}  
  
.title {  
  font-size: 24px;  
  font-weight: bold;  
  color: #3c3c3c;  
}  
  
.input-item {  
  margin-bottom: 15px;  
  padding: 10px;  
  border: 1px solid #ccc;  
  border-radius: 5px;  
  font-size: 16px;  
}  
  
.login-btn {  
  padding: 10px 20px;  
  background-color: #007aff;  
  color: #fff;  
  border: none;  
  border-radius: 5px;  
  font-size: 16px;  
}  
  
.register {  
  margin-top: 20px;  
  color: #07a3f6;  
  font-size: 16px;  
  cursor: pointer;  
}
</style>
