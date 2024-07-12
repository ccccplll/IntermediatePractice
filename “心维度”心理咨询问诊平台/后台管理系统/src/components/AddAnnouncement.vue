<template>
  <div>
	  <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
	  	    <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
	  	    <el-breadcrumb-item>添加公告</el-breadcrumb-item>
	  	</el-breadcrumb>
	  <el-card class="box-card">	  
    <el-container>
      <el-main>
        <el-form @submit.prevent="handleSubmit" label-width="80px">
          <el-form-item label="公告内容">
            <el-input
              type="textarea"
              v-model="announcement.context"
              placeholder="请输入公告内容"
              rows="5"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="handleSubmit">提交</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
	</el-card>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: 'AddAnnouncement',
  data() {
    return {
      announcement: {
        context: '',
        time: '',
      },
    };
  },
  methods: {
    handleSubmit() {
      this.announcement.time = new Date().toISOString(); 
      axios.post('http://localhost:8081/Announcement/insert', this.announcement).then((res) => {
        if (res.data === 1) {
          this.$router.push('/');
        } else {
          this.$notify.error({
            title: '错误',
            message: '添加失败！',
          });
        }
      });
    },
  },
};
</script>

<style scoped>
.el-container {
  padding: 20px;
}
.el-header {
  background-color: #f5f5f5;
  padding: 10px;
  border-bottom: 1px solid #ebeef5;
}
.el-main {
  padding: 20px;
}
</style>





<!-- <template>
  <div>
    <h2>添加公告</h2>
    <form @submit.prevent="handleSubmit">
      <textarea v-model="announcement.context" placeholder="请输入公告内容"></textarea>
      <button type="submit">提交</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'AddAnnouncement',
  data() {
    return {
      announcement: {
        context: '',
		time: '',
      },
    };
  },
  methods: {
    handleSubmit() {
		 this.announcement.time = new Date().toISOString(); 
      axios.post('http://localhost:8081/Announcement/insert', this.announcement).then((res) => {
        if (res.data === 1) {
           this.$router.push('/');
        } else {
          alert('添加失败！');
        }
      });
    },
  },
};
</script -->>
