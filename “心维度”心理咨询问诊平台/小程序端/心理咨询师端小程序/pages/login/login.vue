<template>  
  <view class="login-container"   @refresherpulling="onPulling">  
    <view class="login-header">  
      <text class="title">登录</text>  
    </view>  
    <view class="login-body">  
      <input type="text" class="input-item" placeholder="请输入用户名" v-model="trueName" />  
      <input type="password" class="input-item" placeholder="请输入密码" v-model="password" />  
      <button class="login-btn" @click="login">登录</button>  
    </view>  
    <view class="register" @click="register">去注册</view>
  </view>  
</template>  
  
<script>  
export default {  
  data() {  
    return {  
      trueName: '王咪',  
      password: 'consultant1pwd',  
    };  
  },  
  methods: {  
    login() {  
      const that = this;  // 保存上下文
      uni.request({
        url: 'http://localhost:3000/getPassword',
        method: 'POST', // 通常使用POST
        data: { trueName: that.trueName },
        success(res) {
          console.log(res);
          if (res.data && res.data.length > 0) {
            if (res.data[0].password !== that.password) {
              uni.showToast({
                title: '密码错误',
                icon: 'none',
                duration: 2000
              });
            } else {
              const app = getApp(); // 获取App实例
              if (app.globalData) {  
                app.globalData.trueName = that.trueName; // 更新全局变量  
				app.globalData.c_id = res.data[0].id;
				app.globalData.certificationStatus = res.data[0].certificationStatus;
				app.globalData.seeMessage = res.data[0].seemessage;
              }  
			  uni.$emit('cityStatusChanged');
              // 假设登录成功，你可以在这里进行页面跳转等操作  
              uni.switchTab({ url: '/pages/MainPage/MainPage?'});  
            } }
			else {
            uni.showToast({
              title: '用户名错误',
              icon: 'none',
              duration: 2000
            });
          }
        },
        fail(err) {
          console.log(err);
        }
      });
    },
	
    register() {
      uni.navigateTo({
        url: '/pages/Register/Register',
      })
    }
  },  
};  
</script>  
  
<style >  

.login-container {  
  background-image: linear-gradient(rgba(78, 235, 235, 0.5), rgba(255, 255, 255, 0.1)),url('../../static/login_bg1.jpg');
  background-size: cover;
  display: flex;  
  flex-direction: column;  
  align-items: center;  
  justify-content: center;  
  height: 100vh;  
  background-color: #ffffff;  
}  
  
.login-header {  
  margin-bottom: 20px;  
}  
  
.title {  
  font-size: 24px;  
  font-weight: bold;  
  color: #000000;  
}  
  
.input-item {  
  margin-bottom: 15px;  
  padding: 10px;  
  border: 1px solid #565656;  
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
  color: #0367c3;  
  font-size: 16px;  
  cursor: pointer;  
}
</style>


<style>

</style>
