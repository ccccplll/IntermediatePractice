<template>  
  <view class="container">  
    <image class="paper-plane" :class="{ 'flying': isFlying }" @animationend="handleAnimationEnd" src="/static/plane.png" mode="widthFix" @click="naviToGet"></image>  
  </view>  
</template>  
  
<script>  
export default {  
  data() {  
    return {  
      isFlying: false, // 控制动画是否播放  
    };  
  },  
  methods: {  
    handleAnimationEnd(event) {  
      // 检查是否是纸飞机的动画结束  
      if (event.target.classList.contains('paper-plane') && event.animationName === 'fly') {  
        // 根据实际情况判断是否需要停止动画，这里我们假设动画结束后就停止  
        this.isFlying = false; // 停止动画  
      }  
    },  
    // 可以添加一个方法来重新开始动画（如果需要的话）  
    startFlying() {  
      this.isFlying = true;  
      // 注意：如果动画已经结束并移除了类，这里需要再次添加类来触发动画  
      // 但由于我们使用了:class绑定，所以只需更改数据即可  
    }  
  }  
}  
</script>  
  
<style scoped>  
.container {  
  display: flex;  
  justify-content: center;  
  align-items: center;  
  height: 100vh;  
  overflow: hidden; 
}  
  
.paper-plane {  
  width: 100px; /* 根据图片大小调整 */  
  transition: transform 0.5s ease; /* 可选，平滑过渡效果 */  
}  
  
.paper-plane.flying {  
  animation: fly 5s forwards; /* 使用forwards使动画停在最后一帧 */  
}  
  
@keyframes fly {  
  0% {  
    transform: translateX(0) translateY(0) rotate(0deg);  
  }  
  100% {  
    transform: translateX(calc(100vw + 100px)) translateY(-50px) rotate(10deg); /* 飞出屏幕 */  
  }  
}  
</style>