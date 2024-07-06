<template>
	<view>
		<uni-section title="咨询时间" style="inline">
		      <uni-data-select v-model="value" :localdata="range" @change="change"
		      ></uni-data-select>
		    </uni-section>
		<uni-section title="咨询方式" style="inline">
			      <uni-data-select v-model="value1" :localdata="range1" @change="change"
			      ></uni-data-select>
		 </uni-section>
	</view>
	<view class="line" >
		<view class="delete">
	        <navigator url="../mine/mine">
	            <button type="default" plain="true">取消</button>
	        </navigator>
		</view>
		<view class="delete">
	        <navigator url="../dingdan/dingdan">
	            <button type="default" plain="true">付款</button>
	        </navigator>
	    </view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				value: 0,
			    range: [
				 { value: 0, text: "7月6日 9:00-10:00" },
				 { value: 1, text: "7月7日 8:00-10:00" },
				 { value: 2, text: "7月8日 13:00-15:00" },
				 ],
				 value1: 0,
				 range1: [
				  { value: 0, text: "语音" },
				  { value: 1, text: "线下" },
				 ],
			}
		},
		methods: {
			change(e) {
		      console.log("e:", e);
			},
		}
	}
</script>

<style lang="scss">
  .text {
    font-size: 12px;
    color: #666;
    margin-top: 5px;
  }

  .uni-px-5 {
    padding-left: 10px;
    padding-right: 10px;
  }

  .uni-pb-5 {
    padding-bottom: 10px;
  }
  .line{
	  display:flex;
	  justify-content: space-around;
	  flex-direction: row;
	  align-items: center;
	  margin-top:20%;
  }
  .delete{
	 width:45%;
	  
  }
</style>
