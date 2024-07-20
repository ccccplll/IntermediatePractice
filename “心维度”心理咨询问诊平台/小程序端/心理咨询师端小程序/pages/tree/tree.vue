<template>
  <view class="container">
    <image class="full-screen-image" src="../../static/6f7cbfb990924fed0400a564125b920e.jpg" mode="scaleToFill"></image>
	<view v-if="showCloud"><image src="../../static/cloud.png" mode="scaleToFill" class="cloud-img" @click="getMess"></image></view>
    <view class="bottom-right-images">
      <div>
        <!-- 第一张图片，点击时切换showPopup1的值 -->
        <img
          src="../../static/xinfengxinjianyouxiangxianhuaxinfeng.png"
          class="bottom-image"
          @click="togglePopup"
        />
      </div>
    </view>

    <view class="test">
      <!-- 第二张图片，使用v-if根据showPopup1的值决定是否渲染，并应用居中样式 -->
      <img
        v-if="showPopup1"
        class="centered-image"
        src="../../static/58adeda04887ea4fafdebef7d9fd831d.jpg"
      />
      <scroll-view :scroll-y="true" class="scroll">
        <view class="text-on-image" v-if="showPopup3 && sentMessages.length > 0">
          <view class="fasong">
            <text class="sent-label">已发送:</text>
            <text class="message-spacing" space="emsp">{{ sentMessages[currentIndex] }}</text>
          </view>
          <view class="fasong">
            <text class="reply-label">收件箱:</text>
            <text space="emsp">{{ receivedMessages[currentIndex] || '暂无回信' }}</text>
          </view>
        </view>
      </scroll-view>
    </view>

    <img v-if="showPopup2" class="bcentered-image" src="../../static/xuanzeqixiayige_o.png" @click="nextMessage" />
    <img v-if="showPopup2" class="bcentered-image1" src="../../static/1.png" @click="beforeMessage" />

  </view>

  <image class="center-image" src="../../static/plane.png" @click="showPopup5" :class="{ 'flying': isFlying }"@animationend="handleAnimationEnd"  mode="widthFix"                       ></image>
  <img v-if="showPopup1111" class="centered-image" src="../../static/6.jpg" />

  <view class="popup" v-if="showPopup">
    <view class="popup-content">
      <textarea v-model="messageContent" placeholder="请输入回信内容..."></textarea>
      <view class="but">
        <button @click="sendMessage">发送</button>
        <button @click="resetMessage">取消</button>
      </view>
    </view>
  </view>
  
  <view class="popup" v-if="showLaixing">
    <view class="popup-content">
	        <text class="ttttt">{{comeMessage}}</text>
      <view class="but">
        <button @click="showPopup5">写回信</button>
        <button @click="resetMessage">取消</button>
      </view>
    </view>
  </view>

</template>

<script>
export default {
  data() {
    return {
	  isFlying: false, // 控制动画是否播放
	  showLaixing: false,
	  showCloud: true,
      showPopup1111: false,
      sentMessages: [],
      receivedMessages: [],
	  comeMessage:'',
      currentIndex: 0,
      showPopup2: false,
      showPopup: false,
      showPopup1: false,
      showPopup3: false,
      messageContent: '',
      messages: [],
      messages1: [],
      showTextarea: true,
	  id:''
    };
  },

  methods: {
	   handleAnimationEnd(event) {  
	       // 确保 event.target 存在  
	       if (!event.target) {  
	           console.error('事件目标不存在');  
	           return;  
	       }  
	     
	       // 检查事件目标是否包含特定的类并且动画名称是否正确  
	       if (event.target.classList && event.target.classList.contains('center-image') && event.animationName === 'fly') {  
	           // 假设 isFlying 是组件的状态之一，用于控制动画是否在进行  
	           // 注意：这里假设 this 指向是正确的  
	           this.isFlying = false; // 停止动画  
	     
	           // 可以添加额外的逻辑，比如重置动画状态、显示完成信息等  
	           console.log('纸飞机动画结束');  
	       }  
	   } ,
    showPopup5() {
      this.showPopup1111 = true;
      this.showPopup = true;
	  this.showCloud = false;
	  this.showLaixing = false;
	  
    },
    nextMessage() {
      if (this.sentMessages.length > this.currentIndex + 1) {
        this.currentIndex++;
      }
    },
    beforeMessage() {
      if (this.currentIndex - 1 >= 0) {
        this.currentIndex--;
      }
    },
    togglePopup() {
      this.showPopup1 = !this.showPopup1;
      this.showPopup2 = !this.showPopup2;
      this.showPopup3 = !this.showPopup3;
	  	  this.showCloud = !this.showCloud;

      // 在这里更新发送和接收的消息
      this.updateMessages();
    },
    updateMessages() {
      // 使用uni.request更新消息数据
      // 示例代码，需要根据实际情况调整
          uni.request({
            url: "http://localhost:3000/receive",
            method: 'get',
            data: {
              c_id: getApp().globalData.c_id,
            },
            success: res => {
              if (res.data && Array.isArray(res.data)) {
                this.sentMessages = [];
                this.receivedMessages = [];
                res.data.forEach(item => {
                  if (item.send) {
                    this.sentMessages.push(item.send);
                  }
                  if (item.receive) {
                    this.receivedMessages.push(item.receive);
                  }
                });
                console.log("已更新发送和接收消息");
              }
            }
          });
        },
    sendMessage() {
      // 发送信件逻辑，例如使用uni.request发送数据到服务器
      console.log('发送信件:', this.messageContent);
      this.send(); // 调用发送消息的方法
      uni.showToast({
        title: '发送成功',
        icon: 'success',
        duration: 2000
      });
      this.showPopup1111 = false;
      this.showPopup = false;
	  		this.showCloud = true;
	  this.isFlying = true;  
	  
    },
    resetMessage() {
      // 重置信件内容逻辑
      this.showPopup = false;
      this.showPopup1111 = false;
	  		this.showCloud = true;
			this.showLaixing = false;
    },
	getMess(){
		uni.request({
		  url: 'http://localhost:3000/randomGet',
		  method: 'GET',
		  headers: {
		    'content-type': 'application/json'
		  },
		  success: (res) => {
			  this.id = res.data[0].id;
			  this.comeMessage = res.data[0].send;
			  console.log(this.id);
		    console.log('消息发送成功');
		    // 可以在这里处理发送成功后的逻辑
		  },
		  fail: (err) => {
		    console.error('发送消息失败', err);
		  }
		});
		this.showCloud = false;
		this.showLaixing = true;
      this.showPopup1111 = true;
	},
    send() {
      // 发送消息到服务器的逻辑
          uni.request({
            url: 'http://localhost:3000/send',
            method: 'POST',
            data: {
				id:this.id,
              c_id: getApp().globalData.c_id,
              messageContent: this.messageContent,
            },
            headers: {
              'content-type': 'application/json'
            },
            success: (res) => {
              console.log('消息发送成功');
              // 可以在这里处理发送成功后的逻辑
            },
            fail: (err) => {
              console.error('发送消息失败', err);
            }
          });
        }
		}
};
</script>

<style lang="scss" scoped>
// 样式部分按需调整，确保文本能够正常显示
</style>

<style lang="scss" scoped>
	
	.sent-label, .reply-label {  
	  font-weight: bold; /* 字体加粗 */  
	  font-size: 18px; /* 字体大小 */  
	  color: #333; /* 字体颜色 */  
	  /* 你可以添加更多CSS属性，如 font-family 来设置字体类型 */  
	  font-family: 'Times New Roman', Times, serif; /* 设置字体类型 */  
	}  
	  
	/* 如果需要区分两个标签的样式，可以为它们分别设置不同的样式 */  
	.sent-label { 
	  font-size: 18px; /* 字体大小 */  
	  color: green; /* 已发送标签的特定颜色 */  
	}  
	  
	.reply-label { 
	  font-size: 18px; /* 字体大小 */  
	  color: green; /* 回信标签的特定颜色 */  
	}  
	
	
	.scroll{
		margin-bottom: 90px;
		height:400px;
	}
	.test{
		display: flex; 
		 width:100%;
		
	}
	
	
	
	
	.centered-image {  
	  position: fixed; 
	  top: 50%;  
	  left: 50%;  
	  transform: translate(-50%, -50%);   
	  z-index: 1000;
	  width:95%;
	  height:70%;
	}  
	.bcentered-image {
		width:8%;
		height: 6%;
	  position: fixed; 
	  top: 50%;  
	  left: 50%;  
	  transform: translate(400%, 330%); 
	  z-index: 2000; 
	 
	}  
	.bcentered-image1 {
	  width:8%;
	  height: 6%;
	  position: fixed; 
	  top: 50%;  
	  left: 50%;  
	  transform: translate(-490%, 330%); 
	  z-index: 2000; 
	 
	}  
	



 .popup {  
   position: fixed;  
   top: 0;  
   left: 0;  
   width: 100%;  
   height: 100%;  
   display: flex;  
   justify-content: center;  
   align-items: center;  
  // background-color: rgba(0, 0, 0, 0.5);  
   z-index: 2000;
   
 }  
 .popup-content {  
  // background-color: white;  
   padding: 20px;  
   border-radius: 10px; 
   width: 280px;
   z-index: 2000;
 }  
 .popup-content1 {
   background-color: white;  
   padding: 20px;  
   border-radius: 10px; 
   z-index: 3;
   width: 70%;
 }  
 
 
.container {  
    width: 100%;  
    height: 100vh;  
    position: relative;  
    overflow: hidden; 
    display: flex;  
    justify-content: center; 
    align-items: center; 
}  
  
.full-screen-image {  
    width: 100%;  
    height: 100%;  
    position: absolute;  
    top: 0;  
    left: 0;  
    z-index: 0; /* 确保背景图片不会覆盖其他内容 */  
}  
  
.bottom-right-images {  
    //position: absolute; 
	position: fixed;
    bottom: 10px;  
    right: 10px;  
    display: flex;  
    flex-direction: column;  
    align-items: flex-end; 
	 
    z-index: 3000;  
}  
  
.bottom-image {  
    width: 80px;  
    height: 50px;  
    margin-bottom: 10px; 
	 z-index: 3000;
	 
}  
  
.center-image {  
    position: absolute; 
    top: 50%; 
    left: 50%;  
    transform: translate(-50%, -0%); 
	transition: transform 0.5s ease; /* 可选，平滑过渡效果 */
	 
    z-index: 2;
    width: 80px; 
    height: 70px; 
}
.center-image.flying{
	 
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

@keyframes flyIn{
	0% {
	  transform: translateX(0) translateY(0) rotate(0deg);  
	}  
	100% {  
	  transform: translateX(calc(100vw + 100px)) translateY(-50px) rotate(10deg); /* 飞出屏幕 */  
	}  
}
.text-on-image {  
   position: absolute;
   display: flex;  
   flex-direction: column;
   justify-content: center; 
   align-items: flex-start; 
   height: 620px; 
   width:80%;
   margin-left: 10%;
   z-index:2000;
   font-family: 'Times New Roman', Times, serif;
   }
.text{
	font-size: 10;
}

.fasong{
	display: flex;
	flex-direction: column;
	margin-bottom: 24rpx;
}
.ttttt{
	font-size: 10px;
	width: 200px;
}
.but{
	display: flex;
	flex-direction: row;
	justify-content: center;
	margin-top: 30%;
	button{
		width: 30%;
	}
} 

.cloud-img{
	position: absolute;
	width: 200px;
	height: 150px;  
	top:0%;
	z-index: 300000;
}

.laixing{
	
}
</style>

