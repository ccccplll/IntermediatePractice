<template>
  <div class="profile-page">
    <!-- 头部 -->
    <div class="header">
      <navigator class="back-button" url="/pages/home/home">
        <img src="https://img.icons8.com/ios-filled/50/000000/left.png" alt="返回" class="back-icon" />
      </navigator>
      <h1>头像</h1>
    </div>

    <!-- 个人信息 -->
    <div class="personal-info">
      <div class="avatar-wrapper">
        <img :src="avatarUrl" alt="头像" class="avatar" />
        <input type="file" id="avatar" class="avatar-input" @change="onFileChange" />
      </div>
      <button class="confirm-button" @click="confirmChange">确认</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      avatarUrl: 'https://via.placeholder.com/100', // 默认头像URL
    };
  },
  methods: {
    onFileChange(event) {
      const file = event.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          this.avatarUrl = e.target.result;
        };
        reader.readAsDataURL(file);
      }
    },
    confirmChange() {
      // 确认按钮点击逻辑
      console.log('头像已确认更改');
      // 这里可以添加上传头像到服务器的逻辑
    },
  },
};
</script>

<style scoped>
.profile-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  background-color: #fff;
  height: 100vh;
}

.header {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  width: 100%;
  padding: 10px 20px;
  border-bottom: 1px solid #eee;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: #f8f8f8;
  position: fixed;
  top: 0;
  z-index: 1000;
}

.back-button {
  margin-right: 20px;
}

.back-icon {
  width: 24px;
  height: 24px;
}

.header h1 {
  font-size: 18px;
  margin: 0;
  font-weight: 500;
  color: #333;
}

.personal-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  margin-top: 80px; /* to offset the fixed header */
}

.avatar-wrapper {
  position: relative;
  width: 100px;
  height: 100px;
  margin-bottom: 20px;
}

.avatar {
  width: 100%;
  height: 100%;
  border-radius: 50%;
  object-fit: cover;
  border: 2px solid #ddd;
}

.avatar-input {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  cursor: pointer;
}

.confirm-button {
  padding: 10px 30px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  transition: background-color 0.3s;
}

.confirm-button:hover {
  background-color: #0056b3;
}
</style>