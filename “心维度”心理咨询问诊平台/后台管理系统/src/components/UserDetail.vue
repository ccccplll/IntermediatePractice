<template>
  <div class="user-profile">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>用户简介</span>
        </div>
        <div class="user-info">
          <el-row>
            <el-col :span="8" class="avatar-col">
              <img :src="avatar" alt="头像" class="avatar">
            </el-col>
            <el-col :span="16">
              <el-row>
                <el-col :span="8"><strong>姓名:</strong></el-col>
                <el-col :span="16">{{name }}</el-col>
              </el-row>
              <el-row>
                <el-col :span="8"><strong>年龄:</strong></el-col>
                <el-col :span="16">{{age }}</el-col>
              </el-row>
              <el-row>
                <el-col :span="8"><strong>性别:</strong></el-col>
                <el-col :span="16">{{sex }}</el-col>
              </el-row>
              <el-row>
                <el-col :span="8"><strong>城市:</strong></el-col>
                <el-col :span="16">{{city }}</el-col>
              </el-row>
              <el-row>
                <el-col :span="8"><strong>状态:</strong></el-col>
                <el-col :span="16">{{ucondition }}</el-col>
              </el-row>
              <el-row>
                <el-col :span="8"><strong>邮箱:</strong></el-col>
                <el-col :span="16">{{ email }}</el-col>
              </el-row>
              <el-row>
                <el-col :span="8"><strong>电话号码:</strong></el-col>
                <el-col :span="16">{{ phoneNumber }}</el-col>
              </el-row>
            </el-col>
          </el-row>
        </div>
		<el-table :data="tableData" border style="width: 100%; height: 400px">
		        <el-table-column prop="id" label="ID" width="60"></el-table-column>
		        <el-table-column prop="name" label="用户昵称" width="80"></el-table-column>
		        <el-table-column prop="score" label="分数" width="80"></el-table-column>
		        <el-table-column prop="condition" label="心理状况"></el-table-column>
				<el-table-column width="100"
				          prop="time"
				          label="时间"
				          :formatter="formatTime">
				        </el-table-column>
		      </el-table>
		      <el-pagination
		        style="padding-top: 15px"
		        @size-change="getTestResults"
		        @current-change="handleCurrentChange"
		        :page-size.sync="pageSize"
		        :current-page.sync="pageNum"
		        :page-sizes="pageSizes"
		        :total="total"
		        layout="total, sizes, prev, pager, next, jumper"
		      ></el-pagination>
      </el-card>
    </div>
</template>
sort
<script>
	import axios from 'axios';	
export default {
  name: 'UserDetail',
  data() {
    return {
        // id:null,
        name:null,
        city:null,
        age:null,
        ucondition:null,
        sex:null,
        email:null,
        phoneNumber:null,
        avatar:null,
		tableData: [],
		pageSizes:[5,8,10],
		pageNum: 1,
		pageSize: 5,
    };
  },
  mounted() {
    // 在组件创建时获取详情数据
    this.fetchDetail();
	this.getTestResults();
  },
  methods: {
    fetchDetail() {
      axios.get(`http://localhost:8081/User/getUser/${this.$route.params.id}`)
        .then(response => {
          const detail = response.data; // 从数组中获取第一个对象
          if (detail) {   
            this.name = detail.name;
            this.city = detail.city;
            this.age = detail.age;
            this.ucondition = detail.ucondition;
			console.log(detail);
            this.sex = detail.sex;
            this.email = detail.email;
            this.phoneNumber = detail.phoneNumber;
            this.avatar = detail.avatar;
          }
        })
        .catch(err => {
          console.error('Error fetching detail:', err);
        });
    },
	formatDate(cellValue) {
	      if (!cellValue) return '';
	      const date = new Date(cellValue);
	      const y = date.getFullYear();
	      const m = date.getMonth() + 1;
	      const d = date.getDate();
	      return `${y}-${m < 10 ? '0' + m : m}-${d < 10 ? '0' + d : d}`;
	    },
	getTestResults() {
		  const queryParams = {
		    uid: this.$route.params.id,		   
			// uid: 11,
		    pageNum: this.pageNum,
		    pageSize: this.pageSize,
		  };
		  axios.post('http://localhost:8081/Test/search', queryParams)
		    .then(response => {
		      let resultMap = response.data;
			  console.log(response.data);
		      this.tableData = resultMap.testsList.sort((a, b) => b.id - a.id); // 按 id 倒序排列
		      this.total = resultMap.total;
			  console.log(resultMap.total);
			  console.log(this.tableData);
		    })
		    .catch(err => {
		      alert(err);
		    });
		},
		formatTime(row, column, cellValue) {
		      if (!cellValue) return '';
		      const date = new Date(cellValue);
		      const y = date.getFullYear();
		      const m = date.getMonth() + 1;
		      const d = date.getDate();
		      const h = date.getHours();
		      const min = date.getMinutes();
		      const s = date.getSeconds();
		      return `${y}-${m < 10 ? '0' + m : m}-${d < 10 ? '0' + d : d} ${h < 10 ? '0' + h : h}:${min < 10 ? '0' + min : min}:${s < 10 ? '0' + s : s}`;
		    },
		handleCurrentChange(page) {
		    this.pageNum = page;
		    this.getTestResults();
		},
	
  }
};
</script>

<style scoped>
.el-row {
  margin-bottom: 20px; /* 调整行间距 */
}
.avatar {
  width: 100px; /* 你可以根据需要调整宽度 */
  height: 100px; /* 你可以根据需要调整高度 */
  border-radius: 50%;
}

</style>