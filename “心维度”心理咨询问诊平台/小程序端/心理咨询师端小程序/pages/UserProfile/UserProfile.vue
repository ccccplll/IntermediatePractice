<template>
  <view class="profile">
    <view class="item" @click="goToEdit1('avatar')">
      <text class="label">头像</text>
      <image class="avatar" :src="consultant[0].avatar"></image>
    </view>
    <view class="item" @click="goToEdit2('name')">
      <text class="label">名字</text>
      <text class="value">{{ consultant[0].name }}</text>
    </view>
    <view class="item" @click="goToEdit3('city')">
      <text class="label">城市</text>
      <text class="value">{{ consultant[0].city }}</text>
    </view>
    <view class="item" @click="goToEdit4('phonenumber')">
      <text class="label">联系方式</text>
      <text class="value">{{ consultant[0].phoneNumber }}</text>
    </view>
	<view class="item" @click="goToEdit4('phonenumber')">
	  <view><text class="label">邮箱地址</text></view>
	  <text class="value">{{ consultant[0].email }}</text>
	</view>
	<view class="item" @click="goToEdit4('phonenumber')">
	  <text class="value">擅长领域&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;{{ consultant[0].strength }}</text>
	</view>
	<view class="item" @click="goToEdit5('moreInfo')">
	  <text class="value">  {{ consultant[0].introduction }}</text>
	</view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      consultant: [{
      }],
    };
  },
  onLoad() {
  	this.getAllInfo();
  },
  methods: {
    goToEdit1(field) {
      uni.navigateTo({
        url: `/pages/editProFile/editavatar/editavatar`
      });
    },
	goToEdit2(field) {
	  uni.navigateTo({
	    url: `/pages/editProFile/editName/editName`});
	},
	goToEdit3(field) {
	  uni.navigateTo({
	    url: `/pages/editProFile/editCity/editCity`
	  });
	},
	goToEdit4(field) {
	  uni.navigateTo({
	    url: `/pages/editProFile/editPhonenumber/editPhonenumber`
	  });
	},
	goToEdit5(field) {
	  uni.navigateTo({
	    url: `/pages/editProFile/editinfo/editinfo`
	  });
	},
	getCid(){
		  this.c_id = getApp().globalData.c_id;
	  },
	fetchOrder(c_id) {
	  uni.request({
		  url: 'http://localhost:3000/getConsultant?c_id='+ encodeURIComponent(c_id) ,
	    method: 'GET',
	    success: (res) => {
	      console.log(res);
	      this.user.avatar =
		  	  this.applyFilters();
	    },
	    fail: (err) => {
	      console.log(err);
	    }
	  });
	  
	},
	formatDate(date) {
	  const pad = (n) => n < 10 ? '0' + n : n;
	  return `${date.getFullYear()}-${pad(date.getMonth() + 1)}-${pad(date.getDate())} ${pad(date.getHours())}:${pad(date.getMinutes())}:${pad(date.getSeconds())}`;
	},
	filterOrders(status) {
	  this.currentFilter = status;
	  this.applyFilters();
	},
	searchOrders() {
	  this.applyFilters();
	},
	applyFilters() {
	  this.filteredOrders = this.orders.filter(order => {
	    const matchesStatus = this.currentFilter === 'all' || order.status === this.currentFilter;
	    const matchesSearchQuery = !this.searchQuery || (order.name && order.name.includes(this.searchQuery));
	    return matchesStatus && matchesSearchQuery;
	  });
	},
	 deleteOrder(index) {
		 if(this.orders[index].status==="待确认"||this.orders[index].status==="待支付")
	      this.orders.splice(index, 1);
	      this.applyFilters();
	},
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
  },
};
</script>

<style scoped>
.profile {
  padding: 20px;
}
.item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 0;
  border-bottom: 1px solid #ccc;
}
.label {
  font-size: 16px;
}
.value {
  font-size: 16px;
}
.avatar {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}
</style>
