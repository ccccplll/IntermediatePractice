<template>
    <view>
        <view class="banner">
            <image class="banner-img" :src="banner.cover"></image>
            <view class="banner-title">{{banner.title}}</view>
        </view>
        <view class="article-meta">
            <text class="article">{{banner.author_name}}</text>
            <text class="article">发表于</text>
            <text class="article">{{banner.published_at}}</text>
        </view>
		<view class="essay">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{banner.content}}</view>
    </view>
</template>
<script>
    export default {
        data() {
            return {}
        },
        onLoad(event) {
            console.log(event);
            this.banner = JSON.parse(decodeURIComponent(event.detailDate));
            //详情标题
            uni.setNavigationBarTitle({
                title: this.banner.title
            });
        },
        methods: {
        }
    }
</script>
<style>
    .banner {
        height: 360upx;
        overflow: hidden;
        position: relative;
        background-color: #ccc;
    }
    .banner-img {
        width: 100%;
    }
    .banner-title {
        max-height: 84upx;
        overflow: hidden;
        position: absolute;
        left: 30upx;
        bottom: 30upx;
        width: 90%;
        font-size: 32upx;
        font-weight: 400;
        line-height: 42upx;
        color: white;
        z-index: 11;
    }
	.essay{
		font-size: 30upx;
		color: #000000;
	}
	.article{
		color:#ccc;
		font-size: 24upx;
	}
</style>

