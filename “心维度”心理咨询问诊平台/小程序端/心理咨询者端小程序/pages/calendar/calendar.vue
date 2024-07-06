<template>
    <view class="body">
		
		<uni-search-bar @confirm="search" :focus="true" v-model="query"  >
		           </uni-search-bar>
       	
       	    <button @click="search">搜索</button>  
     
      <!-- #ifndef MP-ALIPAY -->
      <view class="uni-list1" >
      		<view class="uni-list-cell-db"  >
      			<picker mode="multiSelector" @columnchange="bindMultiPickerColumnChange" :value="multiIndex" :range="multiArray">
      				<view class="uni-input">{{multiArray[0][multiIndex[0]]}}，{{multiArray[1][multiIndex[1]]}}，{{multiArray[2][multiIndex[2]]}}</view>
      			</picker>
      		</view>
			<view class="uni-list-cell-right">
				<picker @change="bindPickerChange" :value="index" :range="array" range-key="name">
					<view class="uni-input">{{array[index].name}}</view>
				</picker>
			</view>
      </view>
      <!-- #endif -->
	  <view class="uni-list">
	  	<checkbox-group @change="checkboxChange">
	  		<label class="uni-list-cell uni-list-cell-pd" v-for="item in items" :key="item.value">
	  			<view>
	  				<checkbox :value="item.value" :checked="item.checked" />
	  			</view>
	  			<view>{{item.name}}</view>
	  		</label>
	  	</checkbox-group>
	  </view>
		
		<view class="uni-select-cy" :style="{ 'z-index': zindex }">
		  <view class="uni-select-cy-select" :class="{ active: active }" @click.stop="handleSelect">
		    <!-- 禁用mask -->
		    <view class="uni-disabled" v-if="disabled"></view>
		    <!-- 清空 -->
		    <view class="close-icon close-postion" v-if="realValue.length && !active && !disabled && showClearIcon">
		      <text @click.stop="handleRemove(null)"></text>
		    </view>
		    <!-- 显示框 -->
		    <view class="uni-select-multiple" v-show="realValue.length">
		      <view class="uni-select-multiple-item" v-for="(item, index) in realValue" :key="index">
		        {{ item }}
		        <view class="close-icon" v-if="showValueClear"><text @click.stop="handleRemove(index)"></text> </view>
		      </view>
		    </view>
		    <!-- 为空时的显示文案 -->
		    <view v-if="realValue.length == 0 && showplaceholder">{{ placeholder }}</view>
		    <!-- 禁用图标 -->
		    <view class="uni-select-cy-icon" :class="{ disabled: disabled }"><text></text></view>
		  </view>
		  <!-- 下拉选项 -->
		  <scroll-view class="uni-select-cy-options" :scroll-y="true" v-show="active">
		    <template>
		      <view
		        class="uni-select-cy-item"
		         :class="{ active: realValue.includes(item[svalue]) }"
		        v-for="(item, index) in options"
		        :key="index"
		        @click.stop="handleChange(index, item)"
		      >
		        {{item}}
		      </view>
		    </template>
		  </scroll-view>
		</view>
		
		<view class="page container">
			<view class="example-body1">
				<uni-datetime-picker v-model="datetimerange" type="datetimerange" rangeSeparator="至" />
			</view>
		</view>
		
    </view>
</template>


<script>
   export default {
	   name: 'select-cy',
	   props: {
	     // 是否显示全部清空按钮
	     showClearIcon: {
	       type: Boolean,
	       default: true,
	     },
	     // 是否显示单个删除
	     showValueClear: {
	       type: Boolean,
	       default: true,
	     },
	     zindex: {
	       type: Number,
	       default: 999,
	     },
	     // 禁用组件
	     disabled: {
	       type: Boolean,
	       default: false,
	     },
	     value: {
	       type: Array,
	       default() {
	         return [];
	       },
	     },
	     placeholder: {
	       type: String,
	       default: '请选择你的困扰',
	     },
	     showplaceholder: {
	       type: Boolean,
	       default: true,
	     },
	     slabel: {
	       type: String,
	   	  default() {
	   	   return [];
	   	  },
	     },
	     svalue: {
	       type: String,
	   	 default() {
	   	   return [];
	   	 },
	     },
	   },
   	data() {
   		return {
			query:'',
			array: [{name:'价格/时'},{name: '500元以下'}, {name:'500-700元'}, {name:'700-900元'},{name:'900元以上'}],
			index: 0,
   			multiArray: [
   				['国家','中国'],
   				['省份', '湖北','山东','山西','广东','广西'],
   				['城市', '武汉', '襄阳']
   			],
   			multiIndex: [0, 0, 0],
			
			single: '',
			datetimesingle: '',
			range: ['2024-01-1', '2024-12-30'],
			datetimerange: [],
			start: Date.now() - 1000000000,
			end: Date.now() + 1000000000,
			
			searchValue: '',
			
			active: true, // 组件是否激活，
			changevalue: [], // 搜索框同步
			realValue: [],
			options:['心理健康','学业发展','职场相关','人际关系','睡眠问题','情感障碍','家庭暴力','完美主义','成长创伤'],
			
			
   		}
   	},
	mounted() {
	  // 初始化
	  this.init();
	},
	watch: {
		datetimesingle(newval) {
			console.log('单选:', this.datetimesingle);
		},
		range(newval) {
			console.log('范围选:', this.range);
		},
		datetimerange(newval) {
			console.log('范围选:', this.datetimerange);
		}
	},
	mounted() {
		setTimeout(() => {
			this.datetimesingle = Date.now() - 2*24*3600*1000
			this.single = '2024-7-3'
			this.datetimerange = ["2024-07-01 0:00:00", "2024-7-30 23:59:59"]
		},3000)
	},
	
   	methods: {
		
		search() {
			console.log(this.query);
		 uni.request({
		 url: "http://localhost:3000/",
		 method: 'get',
		 data:{
			 query:this.query
		 },
		 success: res => {
		 console.log('数据搜索成功',res.data);
			}
			})
		},
		
   		bindPickerChange: function(e) {
   			console.log('picker发送选择改变，携带值为：' + e.detail.value)
   			this.index = e.detail.value
   		},
   		bindMultiPickerColumnChange: function(e) {
   			console.log('修改的列为：' + e.detail.column + '，值为：' + e.detail.value)
   			this.multiIndex[e.detail.column] = e.detail.value
   			switch (e.detail.column) {
   				case 0: //拖动第1列
   					switch (this.multiIndex[0]) {
   						case 1:
   							this.multiArray[1] = ['湖北', '湖南','山东','山西','广东','广西']
   							this.multiArray[2] = ['武汉', '黄冈', '襄阳']
   							break
   						//case 1:
   							//this.multiArray[1] = ['英国', '法国']
   							//this.multiArray[2] = ['伦敦', '曼彻斯特']
   							//break
   					}
   					this.multiIndex.splice(1, 1, 0)
   					this.multiIndex.splice(2, 1, 0)
   					break
   				case 1: //拖动第2列
   					switch (this.multiIndex[0]) { //判断第一列是什么
   						case 1:
   							switch (this.multiIndex[1]) {
   								case 0:
   									this.multiArray[2] = ['武汉', '黄冈', '襄阳']
   									break
   								case 1:
   									this.multiArray[2] = ['长沙','湘潭','益阳']
   									break
								case 2:
									this.multiArray[2] = ['济南','青岛','烟台']
									break
   							}
   							break
   						//case 1:
   							//switch (this.multiIndex[1]) {
   							//	case 0:
   								//	this.multiArray[2] = ['伦敦', '曼彻斯特']
   								//	break
   								//case 1:
   								//	this.multiArray[2] = ['巴黎', '马赛']
   								//	break
   							//}
   							//break
   					}
   					this.multiIndex.splice(2, 1, 0)
   					break
   			}
   			this.$forceUpdate()
   		},
		change(e) {
			this.single = e
			console.log('change事件:', this.single = e);
		},
		changeLog(e) {
			console.log('change事件:', e);
		},
		maskClick(e){
			console.log('maskClick事件:', e);
		},
		close() {
		  this.active = true;
		},
		init() {
		  if (this.value.length > 0) {
		    this.changevalue = this.options.forEach((item) => {
		      this.value.forEach((i) => {
		        if (item[this.svalue] === i[this.svalue]) {
		          return item;
		        }
		      });
		    });
		    this.realValue = this.value;
		  } else {
		    this.changevalue = [];
		    this.realValue = [];
		  }
		},
		// 点击展示选项
		handleSelect() {
		  if (this.disabled) return;
		  this.active = !this.active;
		},
		// 移除数据
		handleRemove(index) {
		  if (index === null) {
		    this.realValue = [];
		    this.changevalue = [];
		  } else {
		    this.realValue.splice(index, 1);
		    this.changevalue.splice(index, 1);
		  }
		  this.$emit('change', this.changevalue, this.realValue);
		},
		// 点击组件列
		handleChange(index, item) {
		  
		    this.realValue.push(item);
		 
		  //console.log(this.realValue, 'this.realValue');
		//  this.$emit('change', this.changevalue, this.realValue);
		},
		
   	}
   }
</script>

<style lang="scss" scoped>

.search{
  
  height:100rpx;
  display:flex;
  align-items: center;
  justify-content: center;
}
.search-input{
  height:80rpx;
  background-color: #f3f3f3;
  width:90%;
  border-radius: 40rpx;
  color: #000;
  position: relative;
  display: flex;
  align-items: center;
}
.search-content{
  position:absolute;
  left:50%;
  transform: translateX(-50%);
  display: flex;
  align-items: center;
}
.search-content image{
  width:30rpx;
  height:30rpx;
}
.uni-picker-tips {
	font-size: 12px;
	color: #666;
	margin-bottom: 15px;
	padding: 0 15px;
	/* text-align: right; */
}
.uni-list1{
			display:flex;
			//justify-content: space-around;
			flex-direction: row;
			align-items: center;
			margin-top: 2%;
			margin-bottom: 4%;
			height: 40%;
		}
.uni-list-cell-db{
	border: 1px solid #dbdbdb;
	width: 40%;
	border-radius: 8rpx;
	margin-right: 14%;
	margin-left: 2.5%;
	
}
.uni-list-cell-right{
	border: 1px solid #dbdbdb;
	width: 40%;
	border-radius: 8rpx;
	//margin-top: 2%;
	
}
.example-body1{
	margin-top: 2%;
	background-color: #fff;
	padding: 10px;
}
.uni-input{
	font-size: 16;
	color: #7a7474;
}

.search-result {
		padding-top: 10px;
		padding-bottom: 20px;
		text-align: center;
	}

	.search-result-text {
		text-align: center;
		font-size: 16;
		color:#666;
	}

	.example-body {
		/* #ifndef APP-NVUE */
		display: block;
		/* #endif */
		padding: 0px;
	}

	.uni-mt-10 {
		margin-top: 10px;
	}
	.uni-select-cy {
		margin-left: 2.5%;
		margin-right: 2.5%;
		//margin-bottom: 3%;
	  position: relative;
	  z-index: 999;
	  .uni-select-mask {
	    width: 100%;
	    height: 100%;
	  }
	  /* 删除按钮样式*/
	  .close-icon {
	    height: 100%;
	    width: 20px;
	    display: flex;
	    align-items: center;
	    justify-content: center;
	    z-index: 3;
	    cursor: pointer;
	    text {
	      position: relative;
	      background: #c0c4cc;
	      width: 13px;
	      height: 13px;
	      border-radius: 50%;
	      border: 1px solid #bbb;
	      &::before,
	      &::after {
	        content: '';
	        position: absolute;
	        left: 20%;
	        top: 50%;
	        height: 1px;
	        width: 60%;
	        transform: rotate(45deg);
	        background-color: #909399;
	      }
	      &::after {
	        transform: rotate(-45deg);
	      }
	    }
	  }
	  //所有情况的定位
	  .close-postion {
	    position: absolute;
	    right: 35px;
	    top: 0;
	    height: 100%;
	    width: 15px;
	  }
	  /* 多选盒子 */
	  .uni-select-multiple {
	    overflow-x: auto;
	    display: flex;
	    .uni-select-multiple-item {
	      background: #f4f4f5;
	      margin-right: 5px;
	      padding: 2px 4px;
	      border-radius: 4px;
	      color: #909399;
	      display: flex;
	    }
	  }
	  // select部分
	  .uni-select-cy-select {
	    user-select: none;
	    position: relative;
	    z-index: 3;
	    height: 36px;
	    padding: 0 30px 0 10px;
	    box-sizing: border-box;
	    border-radius: 4px;
	    border: 1px solid rgb(229, 229, 229);
	    display: flex;
	    align-items: center;
	    font-size: 14px;
	    color: #999;
	    .uni-disabled {
	      position: absolute;
	      left: 0;
	      width: 100%;
	      height: 100%;
	      z-index: 19;
	      cursor: no-drop;
	      background: rgba(255, 255, 255, 0.5);
	    }
	    .uni-select-cy-input {
	      font-size: 14px;
	      color: #999;
	      display: block;
	      width: 96%;
	      overflow: hidden;
	      text-overflow: ellipsis;
	      white-space: nowrap;
	      line-height: 30px;
	      box-sizing: border-box;
	      &.active {
	        color: #333;
	      }
	    }
		
	    .uni-select-cy-icon {
	      cursor: pointer;
	      position: absolute;
	      right: 0;
	      top: 0;
	      height: 100%;
	      width: 30px;
	      display: flex;
	      align-items: center;
	      justify-content: center;
	      &::before {
	        content: '';
	        width: 1px;
	        height: 100%;
	        position: absolute;
	        left: 0;
	        top: 0;
	        background-color: #e5e5e5;
	      }
	      text {
	        display: block;
	        width: 0;
	        height: 0;
	        border-width: 12rpx 12rpx 0;
	        border-style: solid;
	        border-color: #bbb transparent transparent;
	        transition: 0.3s;
	      }
	      &.disabled {
	        cursor: no-drop;
	        text {
	          width: 20rpx;
	          height: 20rpx;
	          border: 2px solid #ff0000;
	          border-radius: 50%;
	          transition: 0.3s;
	          position: relative;
	          z-index: 999;
	          &::after {
	            content: '';
	            position: absolute;
	            top: 50%;
	            left: 0;
	            width: 100%;
	            height: 2px;
	            margin-top: -1px;
	            background-color: #ff0000;
	            transform: rotate(45deg);
	          }
	        }
	      }
	    }
	    &.active .uni-select-cy-icon {
	      text {
	        transform: rotate(180deg);
	      }
	    }
	  }
	  // options部分
	  .uni-select-cy-options {
	    user-select: none;
	    position: absolute;
	    top: calc(100% + 5px);
	    left: 0;
	    width: 100%;
	    height: 500rpx;
	    border-radius: 4px;
	    border: 1px solid rgb(229, 229, 229);
	    background: #fff;
	    padding: 5px 0;
	    box-sizing: border-box;
	    z-index: 9;
	    .uni-select-cy-item {
	      padding: 0 10px;
	      box-sizing: border-box;
	      cursor: pointer;
	      line-height: 2.5;
	      transition: 0.3s;
	      font-size: 14px;
	      &.active {
	        color: #409eff;
	        background-color: #f5f7fa &hover {
	          color: #409eff;
	          background-color: #f5f7fa;
	        }
	      }
	      &:hover {
	        background-color: #f5f5f5;
	      }
	    }
	  }
	}
</style>