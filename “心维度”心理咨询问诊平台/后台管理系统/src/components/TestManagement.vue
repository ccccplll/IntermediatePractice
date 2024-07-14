<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>测试结果管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 测试结果列表卡片 -->
    <el-card class="box-card">
      <el-form :inline="true" :model="pageParam" class="demo-form-inline">
        <el-form-item label="测试名称" label-width="70px">
          <el-input clearable v-model="pageParam.name" placeholder="请输入测试名称"></el-input>
        </el-form-item>
        <el-form-item label="ID" label-width="70px">
          <el-input clearable v-model="pageParam.id" placeholder="请输入ID"></el-input>
        </el-form-item>
        <el-form-item label="分数" label-width="70px">
          <el-input clearable v-model="pageParam.score" placeholder="请输入分数"></el-input>
        </el-form-item>
<!--        <el-form-item label="心理状况" label-width="70px">
          <el-input clearable v-model="pageParam.condition" placeholder="请输入条件"></el-input>
        </el-form-item> -->
		
		<el-form-item label="心理状况" label-width="70px">
		  <el-select clearable v-model="pageParam.condition" placeholder="请选择">
		    <el-option
		      v-for="item in conditions"
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
          <el-button type="primary" icon="el-icon-search" @click="getTestResults">查询</el-button>
          <el-button type="success" icon="el-icon-plus">添加</el-button>
          <el-button type="warning" icon="el-icon-download">导出</el-button>
        </el-form-item>
      </el-form>
      <!-- 表格内容显示区域 -->
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
<!--        <el-table-column label="操作" width="100">
          <template slot-scope="scope">
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column> -->
      </el-table>
      <el-pagination
        style="padding-top: 15px"
        @size-change="getTestResults"
        @current-change="handleCurrentChange"
        :page-size.sync="pageParam.pageSize"
        :current-page.sync="pageParam.pageNum"
        :page-sizes="pageSizes"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
      ></el-pagination>
    </el-card>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'TestManagement',
  data() {
    return {
      testResults: [],
      pageParam: {
        pageNum: 1,
        pageSize: 5,
        name: null,
        id: null,
        condition: null,
      },
	  conditions:[{
	  		  value:'差',
	  		  label:'差'
	  },{
	  		  value:'一般',
	  		  label:'一般'
	  },{
	  		  value:'良好',
	  		  label:'良好'
	  },{
	  		  value:'优秀',
	  		  label:'优秀'
	  }],
      total: 0,
      pageSizes: [5, 10, 15],
      tableData: [],
    };
  },
  mounted() {
    this.getTestResults();
  },
  methods: {
    onreset() {
      this.pageParam = {
        pageNum: 1,
        pageSize: 5,
        name: null,
        id: null,
        condition: null,
      };
      this.getTestResults();
    },
    getTestResults() {
      const queryParams = {
        name: this.pageParam.name,
        id: this.pageParam.id,
        condition: this.pageParam.condition,
        pageNum: this.pageParam.pageNum,
        pageSize: this.pageParam.pageSize,
      };
      axios.post('http://localhost:8081/Test/search', queryParams)
        .then(response => {
          let resultMap = response.data;
          this.tableData = resultMap.testsList.sort((a, b) => b.id - a.id); // 按 id 倒序排列
          this.total = resultMap.total;
		  console.log(resultMap.total);
		  console.log(this.tableData);
        })
        .catch(err => {
          alert(err);
        });
    },
    handleCurrentChange(page) {
      this.pageParam.pageNum = page;
      this.getTestResults();
    },
    getAnnouncements() {
      axios.get('http://localhost:8081/Announcement/findAll').then((res) => {
        // this.announcements = res.data;
		 this.announcements = res.data.sort((a, b) => new Date(b.time) - new Date(a.time));
      });
    },
    formatDate(date) {
      const options = { year: 'numeric', month: 'short', day: 'numeric', hour: '2-digit', minute: '2-digit' };
      return new Date(date).toLocaleDateString(undefined, options);
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
  },
};
</script>

<style scoped>
.demo-form-inline {
  margin-bottom: 20px;
}
</style>
