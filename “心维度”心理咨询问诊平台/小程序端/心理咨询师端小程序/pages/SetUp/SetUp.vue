<template>  
  <view class="settings-container">  
    <view class="settings-header">  
      <text class="settings-title">心维度</text>  
    </view>  
    <view class="settings-section">  
      <text class="setting-label">接收推送通知</text>  
      <switch class="switch-control" :checked="isPushNotificationsEnabled" @change="handlePushNotificationsChange" />  
    </view>  
    <view class="settings-section">  
      <text class="setting-label">接收个性化展示</text>  
      <switch class="switch-control" :checked="isPersonalizedShowcaseEnabled" @change="handlePersonalizedShowcaseChange" />  
    </view>  
    <view class="settings-section">  
      <text class="setting-label">软件升级</text>  
      <text class="setting-subtitle">当前版本 7.2.5</text>  
    </view>  
    <view class="settings-section" @tap="clearCache">  
      <text class="setting-label">清理缓存</text>  
      <text class="setting-subtitle">{{ cacheSize }}</text>  
    </view>  
    <view class="settings-footer">  
      <button class="back-button" @click="goBack">返回</button>  
      <button class="confirm-button" @click="confirmSettings">确定</button>  
    </view>  
  </view>  
</template>  

<script>  
export default {  
  data() {  
    return {  
      isPushNotificationsEnabled: true,  
      isPersonalizedShowcaseEnabled: true,  
      cacheSize: '5.27MB'  
    };  
  },  
  methods: {  
    handlePushNotificationsChange(event) {  
      this.isPushNotificationsEnabled = event.detail.value;  
      // 这里可以添加处理推送通知开关变化的逻辑  
    },  
    handlePersonalizedShowcaseChange(event) {  
      this.isPersonalizedShowcaseEnabled = event.detail.value;  
      // 这里可以添加处理个性化展示开关变化的逻辑  
    },  
    clearCache() {  
      // 清理缓存的逻辑  
      this.cacheSize = '0MB';  
      uni.showToast({  
        title: '缓存已清理',  
        icon: 'success'  
      });  
    },  
    goBack() {  
      // 导航回上一个页面的逻辑  
      uni.navigateBack();  
    },  
    confirmSettings() {  
      // 确认设置后的逻辑处理  
      console.log('Settings confirmed');  
      uni.showToast({  
        title: '设置已保存',  
        icon: 'success'  
      });  
    }  
  }  
};  
</script>  

<style>  
.settings-container {  
  padding: 20px;  
  background-color: #f5f5f5;  
  min-height: 100vh;  
}  
  
.settings-header {  
  display: flex;  
  justify-content: center;  
  align-items: center;  
  margin-bottom: 20px;  
}  
  
.settings-title {  
  font-size: 24px;  
  font-weight: bold;  
  color: #333;  
}  
  
.settings-section {  
  display: flex;  
  justify-content: space-between;  
  align-items: center;  
  padding: 15px;  
  background-color: #fff;  
  border-radius: 8px;  
  margin-bottom: 15px;  
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);  
}  
  
.switch-control {  
  margin-left: 10px;  
}  
  
.setting-label {  
  font-size: 18px;  
  color: #333;  
}  
  
.setting-subtitle {  
  font-size: 14px;  
  color: #999;  
}  
  
.settings-footer {  
  display: flex;  
  justify-content: space-between;  
  margin-top: 30px;  
}  
  
.back-button, .confirm-button {  
  padding: 10px 20px;  
  border-radius: 8px;  
  background-color: #009688;  
  color: #fff;  
  font-size: 16px;  
  text-align: center;  
}  
  
.back-button {  
  background-color: #ccc;  
}  
  
.confirm-button {  
  background-color: #1AAD19;  
}  
</style>

