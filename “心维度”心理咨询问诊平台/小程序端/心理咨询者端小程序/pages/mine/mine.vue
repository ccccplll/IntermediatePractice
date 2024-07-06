<template>  
  <view class="container">  
    <image src="../../static/head.png" class="image-style"></image>  
  </view>  
  <view class="jiange">
  <text class="size">{{name}}</text> 
  </view>
  <view style="display:inline" class="jiange" >
	  <text class="color1">{{title}}</text>
	  <text class="color1">{{time}}</text>
      <text class="color1">{{money}}</text>
  </view>
  <view class="jiange">
     <text class="size1">个人简介</text>
  </view>
  <view class="jiange1">
     <text>{{jianjie}}</text>
  </view>
  <view class="jiange">
     <text class="size1">擅长领域</text>
  </view>
  <view class="jiange1">
     <text>{{shanchang}}</text>
  </view>
  <view class="jiange">
     <text class="size1">近一周可约时间</text>
  </view>
  <view class="jiange1" v-for="(item) in shijian">
     <text>{{item}}</text>
  </view>
  <view class="jiange">
     <text class="size1">联系方式</text>
  </view>
  <view class="jiange1">
     <text>{{phone}}</text>
  </view>
  <view class="jiange">
	  <navigator url="../applyask/applyask">
         <button type="default" plain="true">预约咨询</button>
	 </navigator>
  </view>
</template>  
  
<script>  
export default {  
  data() {  
    return {  
      name: '武会青'  ,
	  title:'成熟咨询师',
	  time:'5000+小时经验',
	  money:'500元/时',
	  jianjie:"国家二级心理咨询师 曾任高校心理学教师 应用心理学学士 发展与教育心理学硕士",
	  shanchang:"抑郁、焦虑、复杂性创伤、边缘性人格特质、性别认同、亲密关系危机、家庭冲突",
	  shijian:['7月6日：9:00-10:00','7月7日：8:00-10:00','7月8日：13:00-15:00'],
	  phone:'19653000509'
    }  
  },  
  methods: {  
      
  }  
}  
</script>  
  
<style lang="scss">  
.container {  
  display: flex;  
  justify-content: center;  
  align-items: center;  
  height: 30%;  
  flex-direction: column; 
}  
  
.image-style {  
  width: 100px; 
  height: 100px; 
} 
 .jiange{
	 margin-top: 3%;
	 margin-bottom: 3%;
	 margin-left: 1.5%;
 }
 .jiange1{
 	 margin-top: 3%;
 	 margin-bottom: 3%;
	 margin-left: 3%;
 }
.color1{
	background-color: #f9f28b;
	border-color: ghostwhite;
	width:30%;
	margin-top: 3%;
	border-radius: 20%;
	margin-left: 4%;
	justify-content: center;  
}
.size{
	font-size: 23px;
	margin-bottom: 3%;
}
.size1{
	font-size: 18px;
	padding-top: 3%;
}
</style>