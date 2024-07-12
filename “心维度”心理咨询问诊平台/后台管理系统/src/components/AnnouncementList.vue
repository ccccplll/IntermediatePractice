<template>
  <div>
	  <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
	    <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
	    <el-breadcrumb-item>公告列表</el-breadcrumb-item>
	  </el-breadcrumb>
<el-card class="box-card">	  
    <el-container>
      <el-main>
        <el-row :gutter="20">
          <el-col :span="8" v-for="announcement in announcements" :key="announcement.id">
            <el-card>
              <router-link :to="'/announcement-detail/'+announcement.id">
                <h3>{{ announcement.context }}</h3>
                <p>{{ formatDate(announcement.time) }}</p>
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
  name: 'AnnouncementList',
  data() {
    return {
      announcements: [],
    };
  },
  mounted() {
    this.getAnnouncements();
  },
  methods: {
    getAnnouncements() {
      axios.get('http://localhost:8081/Announcement/findAll').then((res) => {
        // this.announcements = res.data;
		 this.announcements = res.data.sort((a, b) => new Date(b.time) - new Date(a.time));
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
</style>

















<!-- <template>
  <div>
    <h2>公告列表</h2>
    <div v-for="announcement in announcements" :key="announcement.id">
      <router-link :to="'/announcement-detail/'+announcement.id">
        <h3>{{announcement.context}}</h3>
      </router-link>
    </div>
  </div>
</template>
<script>
import axios from 'axios'
export default {
	
  name: 'AnnouncementList',
  data() {
    return {
      announcements: [],
    }
  },
  mounted() {
    this.getAnnouncements();
     },
  methods: {
    getAnnouncements() {
      axios.get('http://localhost:8081/Announcement/findAll').then((res) => {
        this.announcements = res.data;
      });
    },
  },
};
</script -->>
