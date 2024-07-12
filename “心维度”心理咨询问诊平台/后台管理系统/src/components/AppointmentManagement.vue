<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/SystemIndex' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>预约管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 用户列表卡片 -->
    <el-card class="box-card">
      <el-form :inline="true" :model="pageParam" class="demo-form-inline">

        <el-form-item label="咨询者昵称" label-width="100px">
          <el-input clearable v-model="pageParam.uname" placeholder="请输入咨询者昵称"></el-input>
        </el-form-item>
		<el-form-item label="咨询师姓名" label-width="100px">
		  <el-input clearable v-model="pageParam.cname" placeholder="请输入咨询师姓名"></el-input>
		</el-form-item>
		<el-form-item label="ID" label-width="70px">
		  <el-input clearable v-model="pageParam.id" placeholder="请输入ID"></el-input>
		</el-form-item>
		
		<el-form-item label="方式" label-width="70px">
          <el-select clearable v-model="pageParam.consultWay" placeholder="请选择">
            <el-option
              v-for="item in consultWays"
              :key="item.value"
              :label="item.label"
              :value="item.value">
              <span style="float: left">{{ item.label }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
            </el-option>
          </el-select>
        </el-form-item>
		
		<el-form-item label="情况" label-width="70px">
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
		

<!--        <el-form-item label-width="70px">
          <el-radio v-model="formInline.radio" label="1">备选项</el-radio>
          <el-radio v-model="formInline.radio" label="2">备选项</el-radio>
          <el-radio v-model="formInline.radio" label="3">备选项</el-radio>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item> -->
        <el-form-item style="margin-left: 10px">
          <el-button icon="el-icon-refresh" @click="onreset">重置</el-button>
          <el-button type="primary" icon="el-icon-search" @click="getAppointmentsList">查询</el-button>
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
          prop="uname"
          label="咨询者昵称"
          width="100">
        </el-table-column>
		<el-table-column
		  prop="cname"
		  label="咨询师姓名"
		  width="100">
		</el-table-column>
        <el-table-column
          prop="consultWay"
          label="方式">
        </el-table-column>
        <el-table-column
          prop="state"
          label="情况">
        </el-table-column>
<!-- 		<el-table-column
		  label="删除"
		  width="100">
		  <template slot-scope="scope">
		    <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
		  </template>
		</el-table-column> -->
      </el-table>
     <el-pagination
        style="padding-top: 15px"
        @size-change="getAppointmentsList"
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
  name: 'AppointmentManagement',
  data () {
    return {
      
	   usersList: [],
	        //分页设置
	    pageParam: {
			    pageNum: 1,
				pageSize: 5,			
	            cname: null,
				uname: null,
				id: null,
				state: null,
				consultWay: null,
	          },
	  total: 0,//总条目数
      pageSizes: [5, 8, 10, 15],//每页显示条目数列表
	  states:[{
		  value:'已预约',
		  label:'已预约'
	  },{
		  value:'已完成',
		  label:'已完成'
	  },{
		  value:'已取消',
		  label:'已取消'
	  }],
      consultWays: [{
        value: '在线咨询',
        label: '在线咨询'
      }, {
        value: '电话咨询',
        label: '电话咨询'
      }, {
        value: '视频咨询',
        label: '视频咨询'
      }],
      value: '',
      tableData: [],
    }
  },
  mounted() {
	  this.getAppointmentsList();
    },
  methods: {
	 onreset() {
	      this. pageParam= {
			    pageNum: 1,
				pageSize: 5,			
	            cname: null,
				uname: null,
				id: null,
				state: null,
				consultWay: null,
	          };
		
	      this.getAppointmentsList(); // 可选：重置后重新查询数据
	},
	getAppointmentsList() {
		const queryParams = {
		cname: this.pageParam.cname,
		uname: this.pageParam.uname,
		id: this.pageParam.id,
		state: this.pageParam.state,
		pageNum: this.pageParam.pageNum,
		pageSize: this.pageParam.pageSize,
		consultWay: this.pageParam.consultWay,
		};
	       axios.post('http://localhost:8081/Appointment/search',queryParams)
	               .then(response => {
					console.log(this.pageParam.pageNum)
					console.log(response.data);
	                let resultMap = response.data;
					
					console.log(resultMap.appointmentsList);
	                this.tableData = resultMap.appointmentsList;
					console.log(this.tableData);
	                this.total = resultMap.total;
					console.log(resultMap.total);
	               })
	               .catch(err => {
	                 alert(err);
	               });
	},
	handleCurrentChange(page) {
	    this.pageParam.pageNum = page;
	    this.getAppointmentsList();
	},
	handleDelete(index, row) {
		const uid = row.id;
	      axios.post(`http://localhost:8081/Appointment/delete/${uid}`)
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
