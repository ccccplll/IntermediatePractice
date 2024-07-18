<template>
  <div>
	  <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
	    <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
	    <el-breadcrumb-item>漏洞反馈</el-breadcrumb-item>
	  </el-breadcrumb>
<el-card class="box-card">	  
    <el-container>
      <el-main>
        <el-row :gutter="20">
          <el-col :span="8" v-for="bug in bugs" :key="bug.id">
            <el-card>
              <!-- <router-link :to="'/bug-detail/'+bug.id"> -->
                <h3>{{ bug.content }}</h3>
                <!-- <p>{{ formatDate(bug.time) }}</p> -->
				来自用户：<h3>{{ bug.name }}</h3>
				 <button @click="deleteBug(bug.id)">已解决</button>
              <!-- </router-link> -->
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
  name: 'BugManagement',
  data() {
    return {
      bugs: [],
    };
  },
  mounted() {
    this.getBugs();
  },
  methods: {
    getBugs() {
      axios.get('http://localhost:8081/Bug/findAll').then((res) => {
        // this.announcements = res.data;
		 this.bugs = res.data;
      });
    },
	deleteBug(bugId) {
	      axios.delete('http://localhost:8081/Bug/deleteById/' + bugId).then((res) => {
	        if (res.data === 1) {
	          this.getBugs(); // 重新获取漏洞反馈数据，更新页面
	        } else {
	          alert('删除失败！');
	        }
	    });
	},
  },
};
</script>

<style scoped>
.el-card {
  margin-bottom: 20px;
}
</style>















