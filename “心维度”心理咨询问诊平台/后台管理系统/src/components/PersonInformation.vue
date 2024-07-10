<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 用户列表卡片 -->
    <el-card class="box-card">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">

        <el-form-item label="用户名" label-width="70px">
          <el-input clearable v-model="formInline.name" placeholder="请输入用户名"></el-input>
        </el-form-item>
		<el-form-item label="ID" label-width="70px">
		  <el-input clearable v-model="formInline.id" placeholder="请输入ID"></el-input>
		</el-form-item>
        <el-form-item label="邮箱" label-width="70px">
          <el-input clearable v-model="formInline.email" placeholder="请输入邮箱"></el-input>
        </el-form-item>
		
		<el-form-item label="位置" label-width="70px">
          <el-select clearable v-model="formInline.city" placeholder="请选择">
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
		  <el-select clearable v-model="formInline.sex" placeholder="请选择">
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
		  <el-input clearable v-model="formInline.age" placeholder="请输入年龄"></el-input>
		</el-form-item>
<!--        <el-form-item label-width="70px">
          <el-radio v-model="formInline.radio" label="1">备选项</el-radio>
          <el-radio v-model="formInline.radio" label="2">备选项</el-radio>
          <el-radio v-model="formInline.radio" label="3">备选项</el-radio>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item> -->
        <el-form-item style="margin-left: 10px">
          <el-button icon="el-icon-refresh" @click="onreset">重置</el-button>
          <el-button type="primary" icon="el-icon-search" @click="queryData">查询</el-button>
          <el-button type="success" icon="el-icon-plus">添加</el-button>
          <el-button type="warning" icon="el-icon-download">导出</el-button>
        </el-form-item>
      </el-form>
      <!-- 表格内容显示区域   -->
      <el-table :data="tableData"
        border
        style="width: 100%; height: 400px">

        <el-table-column
          prop="name"
          label="姓名"
          width="180">
        </el-table-column>
		<el-table-column
          prop="age"
          label="年龄"
          width="180">
        </el-table-column>
        <el-table-column
          prop="address"
          label="地址">
        </el-table-column>
      </el-table>
      <!--   分页 -->
      <el-pagination
        style="padding-top: 15px"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage4"
        :page-sizes="[100, 200, 300, 400]"
        :page-size="100"
        layout="total, sizes, prev, pager, next, jumper"
        :total="400">
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
      formInline: {
        name: '',
		id:'',
        email: '',
        city: '',
        radio: '1',
		sex:'',

      },
      currentPage4: 1,
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
	  this.queryData();
    },
  methods: {
	 onreset() {
	      this.formInline = {
	        user: '',
	        address: '',
	        age: '',
	      };
	      this.queryData(); // 可选：重置后重新查询数据
	},
    onSubmit () {
      console.log('submit!')
    },
    handleSizeChange (val) {
      console.log(`每页 ${val} 条`)
    },
    handleCurrentChange (val) {
      console.log(`当前页: ${val}`)
    },
    useData() {
      axios.get("http://localhost:8081/list").then(response => {
		console.log(response.data);
        this.tableData = response.data; // 假设 response.data 是要更新的数据数组
      }).catch(error => {
        console.error('Error fetching data:', error);
      });
    },
	
	queryData() {
	    // 只发送需要的字段
	    const queryParams = {
	      name: this.formInline.name,
	      address: this.formInline.address,
	      age: this.formInline.age,
	    };	    
	    axios.post("http://localhost:8081/search", queryParams).then(response => {
	      console.log(response.data);
	      this.tableData = response.data.list;
	      this.total = response.data.total;
	    }).catch(error => {
	      console.error('Error fetching data:', error);
	    });
	  },
  }
}

</script>
