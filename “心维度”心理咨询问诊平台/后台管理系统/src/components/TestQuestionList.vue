<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/SystemIndex' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>问题列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="box-card">
      <el-form :inline="true" :model="pageParam" class="demo-form-inline">
        <el-form-item label="选择日期">
	        <el-date-picker
	          v-model="pageParam.date"
	          type="date"
	          placeholder="选择日期">
	        </el-date-picker>
	    </el-form-item>
		

        <el-form-item style="margin-left: 10px">
          <el-button icon="el-icon-refresh" @click="onreset">重置</el-button>
          <el-button type="primary" icon="el-icon-search" @click="getTestQuestionsList">查询</el-button>
		  <el-button type="primary" icon="el-icon-search" @click="onInsert">新增测评问题</el-button>
        </el-form-item>
      </el-form>
      <!-- 表格内容显示区域   -->
      <el-table :data="tableData"
        border
        style="width: 100%; height: 400px">
        <el-table-column
          prop="id"
          label="ID"
          width="60">
        </el-table-column>
        <el-table-column
          prop="questionContext"
          label="问题描述"
          width="200">
        </el-table-column>
		<el-table-column width="100"
		          prop="date"
		          label="日期"
		          :formatter="formatTime">
		        </el-table-column>
      </el-table>
     <el-pagination
        style="padding-top: 15px"
        @size-change="getTestQuestionsList"
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
	import { DatePicker } from 'element-ui';
	import axios from 'axios'
	
export default {

  name: 'TestQuestionManagement',
  components: {
	        'el-date-picker': DatePicker,
	      },
  data () {
    return {
      
	   usersList: [],
	        //分页设置
	    pageParam: {
			    pageNum: 1,
				pageSize: 5,			
				date:null,
	          },
	  total: 0,//总条目数
      pageSizes: [5, 7, 10, 15],//每页显示条目数列表

      value: '',
      tableData: [],
    }
  },
  mounted() {
	  this.getTestQuestionsList();
    },
  methods: {
	 onreset() {
	      this. pageParam= {
			    pageNum: 1,
				pageSize: 5,
				date: null,
	          };
		
	      this.getTestQuestionsList(); // 可选：重置后重新查询数据
	},
	getTestQuestionsList() {
		const queryParams = {
		pageNum: this.pageParam.pageNum,
		pageSize: this.pageParam.pageSize,
		date:this.pageParam.date,
		};
	       axios.post('http://localhost:8081/TestQuestion/search',queryParams)
	               .then(response => {
					console.log(this.pageParam.pageNum)
					console.log(response.data);
	                let resultMap = response.data;
					
					console.log(resultMap.testQuestionsList);
	                this.tableData = resultMap.testQuestionsList;
	                this.total = resultMap.total;
					console.log(resultMap.total);
	               })
	               .catch(err => {
	                 alert(err);
	               });
	},
	handleCurrentChange(page) {
	    this.pageParam.pageNum = page;
	    this.getTestQuestionsList();
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
	      return `${y}-${m < 10 ? '0' + m : m}-${d < 10 ? '0' + d : d}`;
	    },
    onSubmit () {
      console.log('submit!')
    },
	onInsert() {
	          this.$router.push('/TestQuestionManagement'); // 跳转到 TestQuestionManagement 页面
	    },
    handleSizeChange (val) {
      console.log(`每页 ${val} 条`)
    },	  
  }
}

</script>
