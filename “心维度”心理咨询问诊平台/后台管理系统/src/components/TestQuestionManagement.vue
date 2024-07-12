<template>
  <div>
	  <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
	    <el-breadcrumb-item :to="{ path: '/SystemIndex' }">首页</el-breadcrumb-item>
		<el-breadcrumb-item :to="{ path: '/TestQuestionList' }">问题列表</el-breadcrumb-item>
	    <el-breadcrumb-item>新增测评问题</el-breadcrumb-item>
	  </el-breadcrumb>
	  <el-card class="box-card">
    <el-form :model="form" ref="form" label-width="120px">
<!--      <el-form-item label="日期">
        <el-date-picker v-model="form.date" type="date" placeholder="选择日期"></el-date-picker>
      </el-form-item> -->
        
      <el-form-item label="问题内容">
        <el-input v-model="form.questionContext"></el-input>
      </el-form-item>
      <el-form-item label="选项 A">
        <el-input v-model="form.a"></el-input>
      </el-form-item>
      <el-form-item label="选项 B">
        <el-input v-model="form.b"></el-input>
      </el-form-item>
      <el-form-item label="选项 C">
        <el-input v-model="form.c"></el-input>
      </el-form-item>
      <el-form-item label="选项 D">
        <el-input v-model="form.d"></el-input>
      </el-form-item>
	  <el-row>
              <el-col :span="6">
                <el-form-item label="分数 A">
                  <el-input v-model="form.scoreA" style="width: 40px;"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="分数 B">
                  <el-input v-model="form.scoreB" style="width: 40px;"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="分数 C">
                  <el-input v-model="form.scoreC" style="width: 40px;"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="分数 D">
                  <el-input v-model="form.scoreD" style="width: 40px;"></el-input>
                </el-form-item>
              </el-col>
        </el-row>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">提交</el-button>
      </el-form-item>
    </el-form>
	</el-card>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      form: {
        date: new Date().toISOString().substr(0, 10),
        scoreA: null,
        scoreB: null,
        scoreC: null,
        scoreD: null,
        questionContext: '',
        a: '',
        b: '',
        c: '',
        d: ''
      }
    };
  },
  methods: {
    onSubmit() {
      axios.post('http://localhost:8081/TestQuestion/addTestQuestion', this.form)
        .then(response => {
          this.$message.success('Test question added successfully');
          this.$refs.form.resetFields();
		  this.form={
        date: new Date().toISOString().substr(0, 10),
        scoreA: null,
        scoreB: null,
        scoreC: null,
        scoreD: null,
        questionContext: '',
        a: '',
        b: '',
        c: '',
        d: ''}
        })
        .catch(error => {
          this.$message.error('Error adding test question');
        });
		
    }
  }
};
</script>
