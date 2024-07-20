<template>  
  <view class="login-container">  
    <view class="login-header">  
      <text class="title">注册</text>  
    </view>  
    <view class="login-body">  
      <input type="text" class="input-item" placeholder="请输入用户名" v-model="username" />  
      <input type="password" class="input-item" placeholder="请输入密码" v-model="password" />  
	  <input type="text" class="input-item" placeholder="请输入邮箱地址" v-model="email" />
      <button class="login-btn" @click="register">注册</button>  
    </view>  
    <view class="register" @click="login">已注册，去登录</view>
  </view>  
</template> 

<script>
export default {
  data() {
    return {
      username: '',
      password: '',  
	  email: '',
    }
  },
  methods: {
    register() {
      const that = this;
      uni.request({
        url: 'http://localhost:3000/getPassword',
        method: 'POST',  // 使用POST方法
        data: { userName: this.username },
        success(res) {
		  console.log(res);
          if (res.data && res.data.length > 0) {
            uni.showToast({
              title: '用户名已被使用',
              icon: 'none',
              duration: 2000
            });
          } else {
            that.insertUser();
          }
        },
        fail(err) {
          console.log(err);
        }
      });
    },
    insertUser() {
      uni.request({
        url: 'http://localhost:3000/insertUser',
        method: 'POST',
        data: {
          username: this.username,
          password: this.password,
		  email: this.email,
        },
        success(res) {
          console.log(res);
          if (res.statusCode === 200) {
            uni.showToast({
              title: '注册成功',
              icon: 'success',
              duration: 2000
            });
            // 注册成功后跳转到登录页面
            uni.navigateTo({
              url: '/pages/login/login',
            });
          } else {
            uni.showToast({
              title: '注册失败',
              icon: 'none',
              duration: 2000
            });
          }
        },
        fail(err) {
          console.log(err);
          uni.showToast({
            title: '注册失败',
            icon: 'none',
            duration: 2000
          });
        }
      })
    },
    login() {
      uni.navigateTo({
        url: '/pages/login/login',
      });
    }
  }
}
</script>

<style scoped>  
.login-container {  
  display: flex;  
  flex-direction: column;  
  align-items: center;  
  justify-content: center;  
  height: 100vh;  
  background-color: #f2f2f2;  
  background-image: linear-gradient(rgba(54, 0, 0, 0.5), rgba(45, 0, 68, 0.1)),url('../../static/register_bg1.jpg');
  background-size: cover;
}  
  
.login-header {  
  margin-bottom: 20px;  
}  
  
.title {  
  font-size: 24px;  
  font-weight: bold;  
  color: #e0e0e0;  
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
