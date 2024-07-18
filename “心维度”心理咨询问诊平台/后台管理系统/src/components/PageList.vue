<template>
  <div>
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>帖子列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="box-card">
      <!-- 排序控件 -->
      <div style="margin-bottom: 10px;">
        <el-button-group>
          <!-- 时间排序 -->
          <el-button @click="sortByTime('asc')">时间↑</el-button>
          <el-button @click="sortByTime('desc')">时间↓</el-button>
          <!-- 点赞数排序 -->
          <el-button @click="sortByGoodCount('asc')">点赞数↑</el-button>
          <el-button @click="sortByGoodCount('desc')">点赞数↓</el-button>
          <!-- 评论数排序 -->
          <el-button @click="sortByTxtCount('asc')">评论数↑</el-button>
          <el-button @click="sortByTxtCount('desc')">评论数↓</el-button>
        </el-button-group>
      </div>
      
      <el-container>
        <el-main>
          <el-row :gutter="20">
            <el-col :span="8" v-for="page in sortedPages" :key="page.id">
              <el-card>
                <router-link :to="'/page-detail/'+page.id">
                  <img :src="page.image" alt="图片描述" style="width: 100%; max-width: 300px;">
                  <h3>{{ page.pagetxt }}</h3>
                  <p>点赞数: {{ page.goodcount }}</p>
                  <p>评论数: {{ page.txtcount }}</p>
                  <p>{{ formatDate(page.publishTime) }}</p>
                </router-link>
              </el-card>
            </el-col>
          </el-row>
        </el-main>
      </el-container>
    </el-card>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'PageList',
  data() {
    return {
      pages: [],
      sortBy: null,
      sortOrder: 'asc', // 默认升序
    };
  },
  mounted() {
    this.getPages();
  },
  methods: {
    getPages() {
      axios.get('http://localhost:8081/Page/findAll').then((res) => {
        this.pages = res.data;
        this.sortPages(); // 加载数据后进行排序
      });
    },
    formatDate(date) {
      const options = { year: 'numeric', month: 'short', day: 'numeric', hour: '2-digit', minute: '2-digit' };
      return new Date(date).toLocaleDateString(undefined, options);
    },
    // 根据指定字段和排序方式排序页面数据
    sortPages() {
      const field = this.sortBy;
      const order = this.sortOrder === 'asc' ? 1 : -1;
      this.pages.sort((a, b) => {
        if (a[field] < b[field]) return -order;
        if (a[field] > b[field]) return order;
        return 0;
      });
    },
    // 根据时间排序
    sortByTime(order) {
      this.sortBy = 'publishTime';
      this.sortOrder = order;
      this.sortPages();
    },
    // 根据点赞数排序
    sortByGoodCount(order) {
      this.sortBy = 'goodcount';
      this.sortOrder = order;
      this.sortPages();
    },
    // 根据评论数排序
    sortByTxtCount(order) {
      this.sortBy = 'txtcount';
      this.sortOrder = order;
      this.sortPages();
    },
  },
  computed: {
    // 返回排序后的页面数据
    sortedPages() {
      return this.pages;
    },
  },
};
</script>

<style scoped>
.el-card {
  margin-bottom: 20px;
}
</style>



<!-- <template>
  <div>
	  <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
	    <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
	    <el-breadcrumb-item>帖子列表</el-breadcrumb-item>
	  </el-breadcrumb>
<el-card class="box-card">	  
    <el-container>
      <el-main>
        <el-row :gutter="20">
          <el-col :span="8" v-for="page in pages" :key="page.id">
            <el-card>
              <router-link :to="'/page-detail/'+page.id">
			    <img :src="page.image" alt="图片描述" style="width: 100%; max-width: 300px;">
                <h3>{{ page.pagetxt }}</h3>
				<p>点赞数: {{ page.goodcount }}</p>
				<p>评论数: {{ page.txtcount }}</p>
                <p>{{ formatDate(page.publishTime) }}</p>
              </router-link>
            </el-card>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
	</el-card>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'PageList',
  data() {
    return {
      pages: [],
    };
  },
  mounted() {
    this.getPages();
  },
  methods: {
    getPages() {
      axios.get('http://localhost:8081/Page/findAll').then((res) => {
		  console.log(res.data)
        // this.pages = res.data;
		 this.pages = res.data.sort((a, b) => new Date(b.publishTime) - new Date(a.createTime));
      });
    },
    formatDate(date) {
      const options = { year: 'numeric', month: 'short', day: 'numeric', hour: '2-digit', minute: '2-digit' };
      return new Date(date).toLocaleDateString(undefined, options);
    },
  },
};
</script>

<style scoped>
.el-card {
  margin-bottom: 20px;
}
</style> -->