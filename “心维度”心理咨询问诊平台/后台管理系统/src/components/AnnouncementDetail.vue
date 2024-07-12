<template>
  <div>
    <h2>公告详情</h2>
    <h3>{{announcement.context}}</h3>
    <button @click="deleteAnnouncement">删除</button>
  </div>
</template>

<script>
	import axios from 'axios'
export default {
  name: 'AnnouncementDetail',
  data() {
    return {
      announcement: {},
    }
  },
  mounted() {
    this.getAnnouncement();
  },
  methods: {
    getAnnouncement() {
      axios.get('http://localhost:8081/Announcement/findById/' + this.$route.params.id).then((res) => {
        this.announcement = res.data;
      });
    },
    deleteAnnouncement() {
      axios.delete('http://localhost:8081/Announcement/deleteById/' + this.$route.params.id).then((res) => {
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
