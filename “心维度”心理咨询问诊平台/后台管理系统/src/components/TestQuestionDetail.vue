<template>
  <div>
	  <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
	    <el-breadcrumb-item :to="{ path: '/SystemIndex' }">首页</el-breadcrumb-item>
		<el-breadcrumb-item :to="{ path: '/TestQuestionList' }">问题列表</el-breadcrumb-item>
	    <el-breadcrumb-item>问题详情</el-breadcrumb-item>
	  </el-breadcrumb>
    
    <el-card>
      <el-row>
        <el-col :span="3">ID:</el-col>
        <el-col :span="21">{{ localId }}</el-col>
      </el-row>
      <el-row>
        <el-col :span="3">问题描述:</el-col>
        <el-col :span="21">{{ questionContext }}</el-col>
      </el-row>
      <el-row>
        <el-col :span="3">日期:</el-col>
        <el-col :span="21">{{ formatDate(date) }}</el-col>
      </el-row>
      <el-row>
        <el-col :span="3">A选项:</el-col>
        <el-col :span="10">{{ a }}</el-col>
        <el-col :span="3">分数:</el-col>
        <el-col :span="8">{{ scoreA }}</el-col>
      </el-row>
      <el-row>
        <el-col :span="3">B选项:</el-col>
        <el-col :span="10">{{ b }}</el-col>
        <el-col :span="3">分数:</el-col>
        <el-col :span="8">{{ scoreB }}</el-col>
      </el-row>
      <el-row>
        <el-col :span="3">C选项:</el-col>
        <el-col :span="10">{{ c }}</el-col>
        <el-col :span="3">分数:</el-col>
        <el-col :span="8">{{ scoreC }}</el-col>
      </el-row>
      <el-row>
        <el-col :span="3">D选项:</el-col>
        <el-col :span="10">{{ d }}</el-col>
        <el-col :span="3">分数:</el-col>
        <el-col :span="8">{{ scoreD }}</el-col>
      </el-row>
    </el-card>
    <!-- 其他详情信息 -->
  </div>
</template>

<script>
	import axios from 'axios';	
export default {
  name: 'TestQuestionDetail',
  data() {
    return {
      localId: '', // 本地 ID
      questionContext: '', // 问题描述
      date: '', // 日期
      a: '', // A 选项
      b: '', // B 选项
      c: '', // C 选项
      d: '', // D 选项
      scoreA: '', // A 分数
      scoreB: '', // B 分数
      scoreC: '', // C 分数
      scoreD: '', // D 分数
    };
  },
  mounted() {
    // 在组件创建时获取详情数据
    this.fetchDetail();
  },
  methods: {
    fetchDetail() {
      axios.get(`http://localhost:8081/TestQuestion/list/${this.$route.params.id}`)
        .then(response => {
          const detail = response.data[0]; // 从数组中获取第一个对象
          if (detail) {
            this.localId = detail.id;
            this.questionContext = detail.questionContext;
            this.date = detail.date;
            this.a = detail.a;
            this.b = detail.b;
            this.c = detail.c;
            this.d = detail.d;
            this.scoreA = detail.scoreA;
            this.scoreB = detail.scoreB;
            this.scoreC = detail.scoreC;
            this.scoreD = detail.scoreD;
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
  }
};
</script>

<style scoped>
.el-row {
  margin-bottom: 20px; /* 调整行间距 */
}
</style>


<!-- <template>
  <div>
    <h1>问题详情</h1>
    <div>ID: {{ localId }}</div>
    <div>问题描述: {{ questionContext }}</div>
    <div>日期: {{ date }}</div>
	<div>A选项: {{ a }} 分数: {{ scoreA }} </div>
	<div>B选项: {{ b }} 分数: {{ scoreB }} </div>
	<div>C选项: {{ c }} 分数: {{ scoreC }} </div>
	<div>D选项: {{ d }} 分数: {{ scoreD }} </div>
  </div>
</template> -->

<!-- <script>
import axios from 'axios';	
export default {
	name: 'TestQuestionDetail',
  props: ['id'],
  data() {
    return {
	  localId:this.$route.params.id,
      questionContext: null,
      date: null,
	  a: null,
	  b: null,
	  c: null,
	  d: null,
	  scoreA: null,
	  scoreB: null,
	  scoreC: null,
	  scoreD: null,
      // 其他详情信息
    };
  },
  created() {
    // 在这里请求详情数据
	this.localId = this.$route.params.id;
    this.fetchDetail();
  },
  methods: {
    fetchDetail() {
      // 使用实际的 API 请求数据
      axios.get(`http://localhost:8081/TestQuestion/list/${this.$route.params.id}`)
        .then(response => {
          const detail = response.data[0];
		  console.log(response.data);
		  console.log('Response data:', detail);
		  // console.log(detail);
            if (detail.date) {
			  console.log(detail);
              this.questionContext = detail.questionContext;
              this.date = formatDate(detail.date);
			  this.scoreA = detail.scoreA;
              this.scoreB = detail.scoreB;
              this.scoreC = detail.scoreC;
              this.scoreD = detail.scoreD;
			  this.a = detail.a;
			  this.b = detail.b;
			  this.c = detail.c;
			  this.d = detail.d;
              // 其他详情信息赋值
            }
          // 其他详情信息赋值
        })
        .catch(err => {
          console.error('Error fetching detail:', err);
        });
    }
  }
};
</script -->>
