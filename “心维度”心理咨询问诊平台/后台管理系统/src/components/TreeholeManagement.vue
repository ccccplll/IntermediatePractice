<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>树洞管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 用户列表卡片 -->
    <el-card class="box-card">
      <el-form :inline="true" :model="pageParam" class="demo-form-inline">
 
		<el-form-item label="状态" label-width="70px">
		  <el-select clearable v-model="pageParam.state" placeholder="请选择">
		    <el-option
		      v-for="item in states"
		      :key="item.value"
		      :label="item.label"
		      :value="item.value">
		      <span style="float: left">{{ item.label }}</span>
		      <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
		    </el-option>
		  </el-select>
		</el-form-item>
        <el-form-item style="margin-left: 10px">
          <el-button icon="el-icon-refresh" @click="onreset">重置</el-button>
          <el-button type="primary" icon="el-icon-search" @click="getTreeholesList">查询</el-button>
        </el-form-item>
      </el-form>
      <!-- 表格内容显示区域   -->
      <el-table :data="tableData"
        border
        style="width: 100%; height: 800px">
        <el-table-column
          prop="uname"
          label="咨询者昵称"
          width="100">
        </el-table-column>
		<el-table-column
          prop="send"
          label="发信"
          width="500">
        </el-table-column>
        <el-table-column
          prop="cname"
          label="咨询师姓名"
          width="100">
        </el-table-column>
		<el-table-column
          prop="receive"
          label="回信"
          width="500">
        </el-table-column>
      </el-table>
     <el-pagination
        style="padding-top: 15px"
        @size-change="getTreeholesList"
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
  name: 'TreeholeManagement',
  components: {
          'el-date-picker': DatePicker,
        },
  data () {
    return {
		states:[{
				  value:'未回信',
				  label:'未回信'
		},{
				  value:'已回信',
				  label:'已回信'
		}],
		 form: {
		        date: '',
		      },	   
	        //分页设置
	    pageParam: {
			    pageNum: 1,
				pageSize: 4,	
				state:null,
	          },
	  total: 0,//总条目数
      pageSizes: [4, 8, 10, 15],//每页显示条目数列表
      value: '',
      tableData: [],
    }
  },
  mounted() {
	  this.getTreeholesList();
    },
  methods: {
	 onreset() {
	      this. pageParam= {
			    pageNum: 1,
				pageSize: 4,	
				state:null,				
	          };
		
	      this.getTreeholesList(); // 可选：重置后重新查询数据
	},
	getTreeholesList() {
		const queryParams = {
		state: this.pageParam.state,
		pageNum: this.pageParam.pageNum,
		pageSize: this.pageParam.pageSize,
		};
	       axios.post('http://localhost:8081/Treehole/search',queryParams)
	               .then(response => {
					console.log(this.pageParam.pageNum)
					console.log(response.data);
	                let resultMap = response.data;
					
					console.log(resultMap.data);
	                this.tableData = resultMap.treeholesList;
	                this.total = resultMap.total;
					console.log(resultMap.total);
	               })
	               .catch(err => {
	                 alert(err);
	               });
	},
	handleCurrentChange(page) {
	    this.pageParam.pageNum = page;
	    this.getTreeholesList();
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
