<template>
  <div>
	  <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
	    <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
	    <el-breadcrumb-item>文章审核</el-breadcrumb-item>
	  </el-breadcrumb>
<el-card class="box-card">	  
    <el-container>
      <el-main>
        <el-row :gutter="20">
          <el-col :span="8" v-for="essay in essays" :key="essay.id">
            <el-card>
              <!-- <router-link :to="'/essay-detail/'+bug.id"> -->
			  {{ essay.type }}
                <h3>{{ essay.title }}</h3>
				{{ essay.content }}
				<el-image
				  :src="essay.image"
				  alt="审核资料"
				  style="width: 50px; height: 50px; "
				  :preview-src-list="[essay.image]"
				  preview-teleported>
				</el-image>
                <!-- <p>{{ formatDate(bug.time) }}</p> -->
				<!-- 来自用户：<h3>{{ essay.name }}</h3> -->
				 <button @click="passEssay(essay.id)">通过</button>
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
  name: 'EssayManagement',
  data() {
    return {
      essays: [],
    };
  },
  mounted() {
    this.getEssays();
  },
  methods: {
    getEssays() {
      axios.get('http://localhost:8081/Essay/findAll').then((res) => {
        // this.announcements = res.data;
		 this.essays = res.data;
      });
    },
	passEssay(essayId) {
	      axios.post('http://localhost:8081/Essay/Pass/' + essayId).then((res) => {
	    });
		this.getEssays();
	},
  },
};
</script>

<style scoped>
.el-card {
  margin-bottom: 20px;
}
</style>















