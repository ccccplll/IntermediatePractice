<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 用户列表卡片 -->
    <el-card class="box-card">
      <el-form :inline="true" :model="pageParam" class="demo-form-inline">

        <el-form-item label="用户名" label-width="70px">
          <el-input clearable v-model="pageParam.name" placeholder="请输入用户名"></el-input>
        </el-form-item>
		<el-form-item label="ID" label-width="70px">
		  <el-input clearable v-model="pageParam.id" placeholder="请输入ID"></el-input>
		</el-form-item>
        <el-form-item label="邮箱" label-width="70px">
          <el-input clearable v-model="pageParam.email" placeholder="请输入邮箱"></el-input>
        </el-form-item>
		
		<el-form-item label="位置" label-width="70px">
          <el-select clearable v-model="pageParam.city" placeholder="请选择">
            <el-option
              v-for="item in cities"
              :key="item.value"
              :label="item.label"
              :value="item.value">
              <span style="float: left">{{ item.label }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
            </el-option>
          </el-select>
        </el-form-item>
		
		<el-form-item label="性别" label-width="70px">
		  <el-select clearable v-model="pageParam.sex" placeholder="请选择">
		    <el-option
		      v-for="item in sexs"
		      :key="item.value"
		      :label="item.label"
		      :value="item.value">
		      <span style="float: left">{{ item.label }}</span>
		      <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
		    </el-option>
		  </el-select>
		</el-form-item>
		
		<el-form-item label="年龄" label-width="70px">
		  <el-input clearable v-model="pageParam.age" placeholder="请输入年龄"></el-input>
		</el-form-item>
<!--        <el-form-item label-width="70px">
          <el-radio v-model="formInline.radio" label="1">备选项</el-radio>
          <el-radio v-model="formInline.radio" label="2">备选项</el-radio>
          <el-radio v-model="formInline.radio" label="3">备选项</el-radio>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item> -->
        <el-form-item style="margin-left: 10px">
          <el-button icon="el-icon-refresh" @click="onreset">重置</el-button>
          <el-button type="primary" icon="el-icon-search" @click="getUsersList">查询</el-button>
          <el-button type="success" icon="el-icon-plus">添加</el-button>
          <el-button type="warning" icon="el-icon-download">导出</el-button>
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
          prop="age"
          label="年龄"
          width="50">
        </el-table-column>
		<el-table-column
		  prop="sex"
		  label="性别"
		  width="80">
		</el-table-column>
        <el-table-column
          prop="city"
          label="城市">
        </el-table-column>
		<el-table-column
		  label="删除"
		  width="100">
		  <template slot-scope="scope">
		    <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
		  </template>
		</el-table-column>
      </el-table>
     <el-pagination
        style="padding-top: 15px"
        @size-change="getUsersList"
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
  name: 'UserManagement',
  data () {
    return {
      
	   usersList: [],
	        //分页设置
	    pageParam: {
			    pageNum: 1,
				pageSize: 5,			
	            name: null,
	            city: null,
	            age: null,
				sex: null,
				id: null,
	          },
	  total: 0,//总条目数
      pageSizes: [5, 8, 10, 15],//每页显示条目数列表
	  sexs:[{
		  value:'male',
		  label:'男'
	  },{
		  value:'female',
		  label:'女'
	  }],
      cities: [{
        value: 'Beijing',
        label: '北京'
      }, {
        value: 'Shanghai',
        label: '上海'
      }, {
        value: 'Nanjing',
        label: '南京'
      }, {
        value: 'Chengdu',
        label: '成都'
      }, {
        value: 'Shenzhen',
        label: '深圳'
      }, {
        value: 'Guangzhou',
        label: '广州'
      }],
      value: '',
      tableData: [],
    }
  },
  mounted() {
	  this.getUsersList();
    },
  methods: {
	 onreset() {
	      this. pageParam= {
			    pageNum:1,
	            pageSize: 5,
	            name: null,
	            city: null,
	            age: null,
				sex: null,
				id: null,
	          };
		
	      this.getUsersList(); // 可选：重置后重新查询数据
	},
	getUsersList() {
		const queryParams = {
		name: this.pageParam.name,
		city: this.pageParam.city,
		age: this.pageParam.age,
		pageNum: this.pageParam.pageNum,
		pageSize: this.pageParam.pageSize,
		sex: this.pageParam.sex,
		id: this.pageParam.id,
		};
	       axios.post('http://localhost:8081/User/search',queryParams)
	               .then(response => {
					console.log(this.pageParam.pageNum)
					console.log(response.data);
	                let resultMap = response.data;
					
					console.log(resultMap.usersList);
	                this.tableData = resultMap.usersList;
	                this.total = resultMap.total;
					console.log(resultMap.total);
	               })
	               .catch(err => {
	                 alert(err);
	               });
	},
	handleCurrentChange(page) {
	    this.pageParam.pageNum = page;
	    this.getUsersList();
	},
	handleDelete(index, row) {
		const uid = row.id;
	      axios.post(`http://localhost:8081/User/delete/${uid}`)
	        .then(response => {
	          this.tableData.splice(index, 1);
	          this.total--;
	          console.log('User deleted:', row);
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
