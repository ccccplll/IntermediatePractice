<template>
  <view class="top">
    <navigator url="/pages/UserProfile/UserProfile">
      <image src="../../static/个人信息  1.png" mode="aspectFill"></image>
    </navigator>
    <view class="touxiang">
      <view class="n1" @click="chooseImage">
        <image :src="consultant[0].avatar"></image>
      </view>
      <view class="name">{{ consultant[0].name }}</view>
      <view class="address">{{consultant[0].certi}}</view>
    </view>
  </view>
  <view class="middle">
    <view class="test">
      <navigator url="/pages/myessay/myessay">
        <image src="../../static/知识.png" mode="aspectFit"></image>
      </navigator>
      <text>文章</text>
    </view>
    <view class="consult">
      <navigator url="/pages/money/money"> 
        <image src="../../static/书包.png" mode="aspectFit"></image>
      </navigator>
      <text>收益</text>
    </view>
    <view class="order">
      <navigator url="/pages/tree/tree">
        <image src="../../static/书桌课桌.png"mode="aspectFit"></image>
      </navigator>
      <text>树洞</text>
    </view>
    <view class="grow">
      <navigator url='/pages/myData/myData'>
        <image src="../../static/成绩成就资历.png" mode="aspectFit"></image>
      </navigator>
      <text>数据</text>
    </view>
  </view>
  <view class="bottom">
    <view class="list">
      <view class="row">
        <view class="left">
          <image src="../../static/耳机.png" mode="aspectFill"></image>
          <view class="text">资质审核</view>
        </view>
        <view class="right">
          <image src="../../static/右箭.png" mode="aspectFill"></image>
        </view>
        <button @click="apply">资质审核</button>
      </view>

      <view class="row">
        <view class="left">
          <image src="../../static/联系客服.png" mode="aspectFill"></image>
          <view class="text">联系客服</view>
        </view>
        <view class="right">
          <image src="../../static/右箭.png" mode="aspectFill"></image>
        </view>
        <button open-type="contact">联系客服</button>
      </view>
      <view class="row">
        <view class="left">
          <image src="../../static/投诉反馈.png" mode="aspectFill"></image>
          <view class="text">投诉反馈</view>
        </view>
        <view class="right">
          <image src="../../static/右箭.png" mode="aspectFill"></image>
        </view>
        <button @click="reflect">投诉反馈</button>
      </view>
	  <view class="row">
	    <view class="left">
	      <image src="../../static/设置.png" mode="aspectFill"></image>
	      <view class="text">设置中心</view>
	    </view>
	    <view class="right">
	      <image src="../../static/右箭.png" mode="aspectFill"></image>
	    </view>
	    <button @click="setup">设置中心</button>
	  </view>
      <view class="row">
        <view class="left">
          <image src="../../static/关于.png" mode="aspectFill"></image>
          <view class="text">关于我们</view>
        </view>
        <view class="right">
          <image src="../../static/右箭.png" mode="aspectFill"></image>
        </view>
        <button @click="about">关于我们</button>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      consultant:[]
    };
  },
  onLoad() {
    // 页面加载时从本地存储中获取头像地址
	this.getAllInfo();
	getApp().globalData.accumulatedHour = this.consultant[0].accumulatedHour;
	
  },
  created() {
    uni.$on('cityStatusChanged1', this.getAllInfo());
  },
  methods: {
	getAllInfo(){
		uni.request({
		  url: 'http://localhost:3000/getAllInfo',
		  method: 'GET',
		  data: { c_id: getApp().globalData.c_id},
			success: (res) => {
			  console.log(res);
			  this.consultant = []; // 清空订单列表
			  res.data.forEach(item => {
			    const order = {
			      c_id: getApp().globalData.c_id,
				  address:item.address,
			      certi:item.certificationStatus,
			      name: item.trueName,
			      accumulatedHour: item.accumulatedHour,
			      sex:item.sex,
				  age:item.age,
				  city:item.city,
			      phoneNumber:item.phoneNumber,
				  email:item.email,
				  strength:item.strength,
				  introduction:item.introduction,
				  avatar:item.avatar,
			    };
			    this.consultant.push(order);
			  });
		  },
		  fail(err) {
		    console.log(err);
		  }
		});
	},
    chooseImage() {
      uni.chooseImage({
        count: 1,
        success: (res) => {
          const tempFilePaths = res.tempFilePaths;
          if (tempFilePaths.length > 0) {
            this.avatarUrl = tempFilePaths[0]; // 更新页面上的头像显示
            console.log(this.avatarUrl);
            // 将新的头像地址保存到本地存储中
            uni.setStorageSync('avatarUrl', this.avatarUrl);
          }
        }
      });
    },
    calendar() {
      uni.switchTab({
        url: "/pages/calendar/calendar"
      });
    },
    apply() {
      uni.navigateTo({
        url: "/pages/certification/certification"
      });
    },
    setup() {
      uni.navigateTo({
        url: "/pages/SetUp/SetUp"
      });
    },
    reflect() {
      uni.navigateTo({
        url: "/pages/response/response"
      });
    },
    about() {
      uni.navigateTo({
        url: "/pages/aboutUs/aboutUs"
      });
    }
  }
}
</script>

<style lang="scss" scoped>
.top {
  background-image: linear-gradient(180deg, powderblue, #fff);
  display: flex;
  flex-direction: column;
  padding: 5px 5px;
  navigator {
    width: 35px;
    height: 35px;
    border-radius: 20%;
    overflow: hidden;
    right: 0;
    bottom: 0;
    image {
      width: 100%;
      height: 100%;
    }
  }
  .touxiang {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    .n1 {
      width: 150rpx;
      height: 150rpx;
      border-radius: 100%;
      overflow: hidden;
      image {
        width: 100%;
        height: 100%;
      }
    }
    .name {
      font-size: 40rpx;
      color: #000;
      padding: 5px 0;
    }
    .address {
      font-size: 28rpx;
      color: #aaa;
    }
  }
}
.middle {
  width: 690rpx;
  margin: 50rpx auto;
  border-radius: 20rpx;
  box-shadow: 0 0 30rpx rgba(0, 0, 0, 0.05);
  display: flex;
  flex-direction: row;
  align-items: flex-end;
  justify-content: space-around;
  border: 2px solid skyblue;
  background: #fff;
  .order {
    flex-direction: column;
    padding: 0rpx 0px;
    justify-content: center;
    navigator {
      width: 35px;
      height: 35px;
      border-radius: 20%;
      overflow: hidden;
      image {
        width: 100%;
        height: 100%;
      }
    }
    text {
      width: 50px;
      height: 10px;
      font-size: 25rpx;
      margin: 20rpx;
      padding-top: 20rpx;
    }
  }
  .test {
    flex-direction: column;
    padding: 0 0px;
    navigator {
      width: 35px;
      height: 35px;
      border-radius: 20%;
      overflow: hidden;
      image {
        width: 100%;
        height: 100%;
      }
    }
    text {
      width: 50px;
      height: 10px;
      font-size: 25rpx;
      padding: 5rpx;
    }
  }
  .consult {
    flex-direction: column;
    padding: 0 0px;
    navigator {
      width: 35px;
      height: 35px;
      border-radius: 20%;
      overflow: hidden;
      margin-top: 10rpx;
      image {
        width: 100%;
        height: 100%;
      }
    }
    text {
      width: 50px;
      height: 10px;
      font-size: 25rpx;
      padding: 10rpx;
    }
  }
  .grow {
    flex-direction: column;
    padding: 0rpx 0px;
    justify-content: center;
    navigator {
      width: 35px;
      height: 35px;
      border-radius: 20%;
      overflow: hidden;
      image {
        width: 100%;
        height: 100%;
      }
    }
    text {
      width: 50px;
      height: 10px;
      font-size: 25rpx;
      padding: 5rpx;
    }
  }
}
.bottom {
  width: 690rpx;
  margin: 50rpx auto;
  border: 1px solid #eee;
  border-radius: 10rpx;
  box-shadow: 0 0 30rpx rgba(0, 0, 0, 0.05);
  .list {
    .row {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 0 30rpx;
      height: 100rpx;
      position: relative;
      border-bottom: 1px solid #eee;
      //&:last-child{border-bottom: 0;}
      .left {
        display: flex;
        align-items: center;
        image {
          width: 30px;
          height: 30px;
          border-radius: 20%;
          overflow: hidden;
        }
        .text {
          padding-left: 20rpx;
          font-size: 28rpx;
          color: #aaa;
        }
      }
      .right {
        display: flex;
        align-items: center;
        image {
          width: 25px;
          height: 25px;
          border-radius: 20%;
          overflow: hidden;
        }
      }
      button {
        position: absolute;
        top: 0;
        left: 0;
        height: 100rpx;
        width: 100%;
        opacity: 0;
      }
    }
  }
}
</style>



















<!--<template>

		<view class="box" style="display: flex; align-items: center; margin-bottom: 20rpx;">
			<image :src="user.avatar" style="width: 150rpx; border-radius: 50%;" mode="widthFix"></image>
			<view style="flex: 1; margin-left: 30rpx;">
				<view style="margin-bottom: 20rpx; font-size: 32rpx;">{{ user.username }}</view>
				<view style="color: #888;">{{ user.name }}</view>
			</view>
		</view>
  <div class="my-page">
    
    <div class="personal-info">
      <img :src="user.avatar" alt="头像" class="avatar" @click="goToUserProfile" />
      <div class="info">
        <p class="name">{{ user.name }}</p>
        <p>{{ user.gender }}</p>
      </div>
    </div>

    <div class="certification">
      <p>认证状态: <span v-if="user.isCertified" class="certified">已认证</span><span v-else class="uncertified">未认证</span></p>
      <span v-if="!user.isCertified" class="navigator" @click="goToCertification">去认证</span>
    </div>


    <div class="order-history">
      <h3>历史订单</h3>
      <div v-if="orders.length">
        <div v-for="order in orders" :key="order.id" class="order-item">
          <p>{{ order.name }} - {{ order.status }}</p>
        </div>
      </div>
      <div v-else>
        <p>您还没有历史订单</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: {
        avatar: 'https://via.placeholder.com/100', // 头像占位符
        name: '张三',
        gender: '男',
        isCertified: false, // 认证状态
      },
      orders: [
        { id: 1, name: '订单1', status: '已完成' },
        { id: 2, name: '订单2', status: '已取消' },
      ],
    };
  },
  methods: {
    goToUserProfile() {
      uni.navigateTo({
        url: '/pages/UserProfile/UserProfile'
      });
      console.log('跳转到用户信息页面');
    },
    goToCertification() {
      uni.navigateTo({
        url: '/pages/certification/certification'
      });
      console.log('跳转到认证页面');
    },
  },
};
</script>

<style scoped>
.my-page {
  padding: 20px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; /* Use a clean and modern font */
}

.personal-info {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  margin-right: 20px;
  cursor: pointer; /* Add pointer cursor on avatar */
  transition: transform 0.3s ease; /* Smooth hover effect */
}

.avatar:hover {
  transform: scale(1.1); /* Zoom effect on hover */
}

.info {
  color: #333; /* Darker text color for better readability */
}

.info .name {
  font-size: 1.5rem; /* Larger font size for name */
  font-weight: bold;
  margin-bottom: 5px;
}

.certification {
  padding: 10px;
  border: 1px solid #ddd;
  margin-bottom: 20px;
  background-color: #f7f7f7;
  text-align: center; /* Center text for better alignment */
}

.certification p {
  margin: 0;
  font-weight: bold;
}

.certification .navigator {
  color: #007bff;
  cursor: pointer;
  text-decoration: underline; /* Underline link for clarity */
}

.certified {
  color: green;
}

.uncertified {
  color: red;
}

.order-history {
  margin-top: 20px;
}

.order-history h3 {
  font-size: 1.2rem;
  margin-bottom: 10px;
  color: #333;
}

.order-item {
  padding: 10px;
  border: 1px solid #ddd;
  margin-bottom: 10px;
  background-color: #f9f9f9;
  border-radius: 4px; /* Rounded corners for order items */
  transition: background-color 0.3s ease; /* Smooth background color change on hover */
}

.order-item:hover {
  background-color: #f0f0f0; /* Lighter background on hover */
}
</style>-->
