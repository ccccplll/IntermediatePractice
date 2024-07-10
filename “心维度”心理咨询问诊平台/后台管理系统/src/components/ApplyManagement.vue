<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/SystemIndex' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>资格审核管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 用户列表卡片 -->
    <el-card class="box-card">
      <el-form :inline="true" :model="pageParam" class="demo-form-inline">
        <el-form-item label="选择日期">
	        <el-date-picker
	          v-model="pageParam.date"
	          type="date"
	          placeholder="选择日期">
	        </el-date-picker>
	    </el-form-item>
        <el-form-item label="姓名" label-width="70px">
          <el-input clearable v-model="pageParam.name" placeholder="请输入咨询师姓名"></el-input>
        </el-form-item>
		<el-form-item label="咨询师ID" label-width="70px">
		  <el-input clearable v-model="pageParam.c_id" placeholder="请输入咨询师ID"></el-input>
		</el-form-item>
		<el-form-item label="申请ID" label-width="70px">
		  <el-input clearable v-model="pageParam.id" placeholder="请输入申请ID"></el-input>
		</el-form-item>
        <el-form-item label="邮箱" label-width="70px">
          <el-input clearable v-model="pageParam.email" placeholder="请输入邮箱"></el-input>
        </el-form-item>
		
		<el-form-item label="审核情况" label-width="70px">
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
          <el-button type="primary" icon="el-icon-search" @click="getApplysList">查询</el-button>
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
          prop="name"
          label="姓名"
          width="100">
        </el-table-column>
        <el-table-column
          prop="astate"
          label="审核情况">
        </el-table-column>
		<el-table-column
		  label="不通过"
		  width="100">
		  <template slot-scope="scope">
		    <el-button size="mini" type="danger" @click="handleUnpass(scope.$index, scope.row)">不通过</el-button>
		  </template>
		</el-table-column>
		<el-table-column
		  label="通过"
		  width="100">
		  <template slot-scope="scope">
		    <el-button size="mini" type="danger" @click="handlePass(scope.$index, scope.row)">通过</el-button>
		  </template>
		</el-table-column>
      </el-table>
     <el-pagination
        style="padding-top: 15px"
        @size-change="getApplysList"
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

  name: 'ApplyManagement',
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
	            name: null,
	            state: 'toBeReviewed',
				id: null,
				c_id:null,
				date:null,
	          },
	  total: 0,//总条目数
      pageSizes: [5, 7, 10, 15],//每页显示条目数列表
      states: [{
        value: 'pass',
        label: '通过'
      }, {
        value: 'toBeReviewed',
        label: '待审核'
      },{
        value: 'unpass',
        label: '未通过'
      },
	  ],
      value: '',
      tableData: [],
    }
  },
  mounted() {
	  this.getApplysList();
    },
  methods: {
	 onreset() {
	      this. pageParam= {
			    pageNum: 1,
				pageSize: 5,			
	            name: null,
	            state: 'toBeReviewed',
				id: null,
				c_id:null,
	          };
		
	      this.getApplysList(); // 可选：重置后重新查询数据
	},
	getApplysList() {
		const queryParams = {
		name: this.pageParam.name,
		astate: this.pageParam.state,
		// a_state: null,
		pageNum: this.pageParam.pageNum,
		pageSize: this.pageParam.pageSize,
		id: this.pageParam.id,
		cid: this.pageParam.c_id,
		date:this.pageParam.date,
		};
	       axios.post('http://localhost:8081/Apply/search',queryParams)
	               .then(response => {
					console.log(this.pageParam.pageNum)
					console.log(response.data);
	                let resultMap = response.data;
					
					console.log(resultMap.applysList);
	                this.tableData = resultMap.applysList;
	                this.total = resultMap.total;
					console.log(resultMap.total);
	               })
	               .catch(err => {
	                 alert(err);
	               });
	},
	handleCurrentChange(page) {
	    this.pageParam.pageNum = page;
	    this.getApplysList();
	},
	
	handleUnpass(index, row) {
		const uid = row.id;
	      axios.post(`http://localhost:8081/Apply/Unpass/${uid}`)
	        .then(response => {
	          this.tableData.splice(index, 1);
//	          this.total--;
	          console.log('Apply Unpass:', row);
	        })
	        .catch(err => {
	          alert(err);
	        });
	},
	
	handlePass(index, row) {
		const uid = row.id;
	      axios.post(`http://localhost:8081/Apply/Pass/${uid}`)
	        .then(response => {
	          this.tableData.splice(index, 1);
//	          this.total--;
	          console.log('Apply Pass:', row);
	        })
	        .catch(err => {
	          alert(err);
	        });
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
