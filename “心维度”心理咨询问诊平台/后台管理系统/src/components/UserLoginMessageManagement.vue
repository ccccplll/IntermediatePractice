<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/SystemIndex' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户登录信息</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 用户列表卡片 -->
    <el-card class="box-card">
      <el-form :inline="true" :model="pageParam" class="demo-form-inline">
        <el-form-item label="选择日期">
	        <el-date-picker
	          v-model="pageParam.loginTime"
	          type="date"
	          placeholder="选择日期">
	        </el-date-picker>
	    </el-form-item>
		
<!--        <el-form-item label-width="70px">
          <el-radio v-model="formInline.radio" label="1">备选项</el-radio>
          <el-radio v-model="formInline.radio" label="2">备选项</el-radio>
          <el-radio v-model="formInline.radio" label="3">备选项</el-radio>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item> -->
        <el-form-item style="margin-left: 10px">
          <el-button icon="el-icon-refresh" @click="onreset">重置</el-button>
          <el-button type="primary" icon="el-icon-search" @click="getUserLoginMessagesList">查询</el-button>
          <el-button type="success" icon="el-icon-plus">添加</el-button>
          <el-button type="warning" icon="el-icon-download">导出</el-button>
        </el-form-item>
      </el-form>
      <!-- 表格内容显示区域   -->
      <el-table :data="tableData"
        border
        style="width: 100%; height: 400px">
		<el-table-column
		  prop="ip"
		  label="IP">
		</el-table-column>
        <el-table-column
          prop="device"
          label="设备">
        </el-table-column>
		<el-table-column
          prop="uid"
          label="用户ID">         
        </el-table-column>
		<el-table-column width="200"
		    prop="loginTime"
		    label="登录时间"
		    :formatter="formatTime">
		</el-table-column>
      </el-table>
     <el-pagination
        style="padding-top: 15px"
        @size-change="getUserLoginMessagesList"
        @current-change="handleCurrentChange"
        :page-size.sync="pageParam.pageSize"
        :current-page.sync="pageParam.pageNum"
        :page-sizes="pageSizes"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
        >
      </el-pagination> 
 </el-card>
  </div>
  

</template>

<script>
	import axios from 'axios'
export default {
  name: 'UserLoginMessageManagement',
  data () {
    return {
      
	   usersList: [],
	        //分页设置
	    pageParam: {
			    pageNum: 1,
				pageSize: 4,			
	            loginTime: null,
	          },
	  total: 0,//总条目数
      pageSizes: [4, 8, 10, 15],//每页显示条目数列表
      value: '',
      tableData: [],
    }
  },
  mounted() {
	  this.getUserLoginMessagesList();
    },
  methods: {
	 onreset() {
	      this. pageParam= {
			    pageNum:1,
	            pageSize: 4,
				loginTime: null,
	          };
		
	      this.getUserLoginMessagesList(); // 可选：重置后重新查询数据
	},
	getUserLoginMessagesList() {
		const queryParams = {	
		pageNum: this.pageParam.pageNum,
		pageSize: this.pageParam.pageSize,
		loginTime: this.formatDate(this.pageParam.loginTime),
		};
	       axios.post('http://localhost:8081/UserLoginMessage/search',queryParams)
	               .then(response => {
					console.log(this.pageParam.loginTime)
					console.log(this.pageParam.pageNum)
					console.log(response.data);
	                let resultMap = response.data;
					
					console.log(resultMap.userLoginMessagesList);
	                this.tableData = resultMap.userLoginMessagesList;
	                this.total = resultMap.total;
					console.log(resultMap.total);
	               })
	               .catch(err => {
	                 alert(err);
	               });
	},
	handleCurrentChange(page) {
	    this.pageParam.pageNum = page;
	    this.getUserLoginMessagesList();
	},
	formatDate(date) {
	      if (!date) return '';
	      const d = new Date(date);
	      let month = '' + (d.getMonth() + 1);
	      let day = '' + d.getDate();
	      const year = d.getFullYear();
	
	      if (month.length < 2) month = '0' + month;
	      if (day.length < 2) day = '0' + day;
	
	      return [year, month, day].join('-');
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
	onDetail(row) {
		console.log(row.id); // 调试：打印 row.id 以确保获取正确
		      if (row && row.id) {
		        this.$router.push(`/UserDetail/${row.id}`);
		      } else {
		        console.error('Row ID is missing');
		      }
	},
    onSubmit () {
      console.log('submit!')
    },
    handleSizeChange (val) {
      console.log(`每页 ${val} 条`)
    },	  
  }
}

</script>
