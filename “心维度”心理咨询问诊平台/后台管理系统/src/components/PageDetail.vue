<!-- <template>
  <div>
    <h2>帖子详情</h2>
    <img :src="page.image" alt="图片描述" style="width: 100%; max-width: 300px;">
    <h3>{{ page.pagetxt }}</h3>
    <p>点赞数: {{ page.goodcount }}</p>
    <p>评论数: {{ page.txtcount }}</p>
    <p>{{ formatDate(page.publishTime) }}</p>

    <div v-if="comments.length > 0">
      <h4>评论内容：</h4>
      <ul>
        <li v-for="comment in comments" :key="comment.id">
		  <img :src="comment.avatar" alt="头像" style="width: 100%; max-width: 50px;">
		 
		  {{ comment.name }}:
          {{ comment.content }}
        </li>
      </ul>
    </div>

    <button @click="deletePage">删除</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'PageDetail',
  data() {
    return {
      page: {},
      comments: [], // 存储评论内容
    };
  },
  mounted() {
    this.getPage();
    this.getComments();
  },
  methods: {
    formatDate(date) {
      const options = { year: 'numeric', month: 'short', day: 'numeric', hour: '2-digit', minute: '2-digit' };
      return new Date(date).toLocaleDateString(undefined, options);
    },
    getPage() {
      axios.get('http://localhost:8081/Page/findById/' + this.$route.params.id).then((res) => {
        this.page = res.data;
      });
    },
    getComments() {
      axios.get('http://localhost:8081/PageContent/findCommentsByPageId/' + this.$route.params.id).then((res) => {
        this.comments = res.data;
		console.log(this.comments)
		console.log(this.comments)
      });
    },
    deletePage() {
      axios.delete('http://localhost:8081/Page/deleteById/' + this.$route.params.id).then((res) => {
        if (res.data === 1) {
          this.$router.push('/');
        } else {
          alert('删除失败！');
        }
      });
    },
  },
};
</script>

<style scoped>
/* 可以添加样式 */
</style> -->

<template>
  <div class="page-detail">
	  <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
	    <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
		<el-breadcrumb-item :to="{ path: '/PageList' }">帖子管理</el-breadcrumb-item>
	    <el-breadcrumb-item>帖子详情</el-breadcrumb-item>
	  </el-breadcrumb>
    <h2>帖子详情</h2>
    <div class="page-content">
      <img :src="page.image" alt="图片描述" class="page-image">
      <div class="page-text">
        <h3>{{ page.pagetxt }}</h3>
        <p>点赞数: {{ page.goodcount }}</p>
        <p>评论数: {{ page.txtcount }}</p>
        <p>{{ formatDate(page.publishTime) }}</p>
      </div>
    </div>
    <button class="delete-button" @click="deletePage">删除</button>
    <div v-if="comments.length > 0" class="comments-section">
      <h4>评论内容：</h4>
      <ul class="comments-list">
        <li v-for="comment in comments" :key="comment.id" class="comment-item">
          <img :src="comment.avatar" alt="头像" class="comment-avatar">
          <div class="comment-text">
            <strong>{{ comment.name }}</strong>: {{ comment.content }}
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'PageDetail',
  data() {
    return {
      page: {},
      comments: [], // 存储评论内容
    };
  },
  mounted() {
    this.getPage();
    this.getComments();
  },
  methods: {
    formatDate(date) {
      const options = { year: 'numeric', month: 'short', day: 'numeric', hour: '2-digit', minute: '2-digit' };
      return new Date(date).toLocaleDateString(undefined, options);
    },
    getPage() {
      axios.get('http://localhost:8081/Page/findById/' + this.$route.params.id).then((res) => {
        this.page = res.data;
      });
    },
    getComments() {
      axios.get('http://localhost:8081/PageContent/findCommentsByPageId/' + this.$route.params.id).then((res) => {
        this.comments = res.data;
        console.log(this.comments);
      });
    },
    deletePage() {
      axios.delete('http://localhost:8081/Page/deleteById/' + this.$route.params.id).then((res) => {
        if (res.data === 1) {
          this.$router.push('/');
        } else {
          alert('删除失败！');
        }
      });
    },
  },
};
</script>

<style scoped>
.page-detail {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.page-content {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.page-image {
  width: 100%;
  max-width: 300px;
  margin-right: 20px;
  border-radius: 8px;
}

.page-text h3 {
  margin: 0;
  font-size: 1.5em;
  margin-bottom: 10px;
}

.page-text p {
  margin: 5px 0;
}

.delete-button {
  background: #ff4d4f;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
}

.delete-button:hover {
  background: #ff7875;
}

.comments-section {
  margin-top: 20px;
}

.comments-list {
  list-style: none;
  padding: 0;
}

.comment-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.comment-avatar {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  margin-right: 10px;
}

.comment-text {
  background: #f5f5f5;
  padding: 10px;
  border-radius: 5px;
}
</style>
