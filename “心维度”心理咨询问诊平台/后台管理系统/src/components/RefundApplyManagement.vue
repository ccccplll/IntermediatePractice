<template>
  <div>
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>退款审核</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="box-card">
      <el-container>
        <el-main>
          <el-row :gutter="20">
            <el-col :span="8" v-for="refund in refunds" :key="refund.id">
              <el-card>
                <div class="header">
                  <h3>{{ "预约编号：" + refund.aid }}</h3>
                  <el-button 
                    type="primary" 
                    icon="el-icon-check" 
                    @click.native="passRefund(refund.id)"
                    size="mini"
                  ></el-button>                 
                </div>
                <div class="content">
                  <p>理由:</p>
                  <p>{{ refund.context }}</p>
                  <p>{{ formatDate(refund.submitTime) }}</p>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </el-main>
      </el-container>
    </el-card>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'RefundApplyManagement',
  data() {
    return {
      refunds: [],
    };
  },
  mounted() {
    this.getRefunds();
  },
  methods: {
    getRefunds() {
      axios.get('http://localhost:8081/Refund/findAll').then((res) => {
        this.refunds = res.data;
      });
    },
    passRefund(refundId) {
      axios.post('http://localhost:8081/Refund/refund/' + refundId).then((res) => {
        this.getRefunds();
      }).catch(error => {
        console.error("请求失败:", error);
      });
    },
    formatDate(date) {
      const d = new Date(date);
      const year = d.getFullYear();
      const month = (d.getMonth() + 1).toString().padStart(2, '0');
      const day = d.getDate().toString().padStart(2, '0');
      const hours = d.getHours().toString().padStart(2, '0');
      const minutes = d.getMinutes().toString().padStart(2, '0');
      return `${year}-${month}-${day} ${hours}:${minutes}`;
    }
  },
};
</script>

<style scoped>
.el-card {
  margin: 10px;
  border-radius: 20px;
  border-color: #C0C4CC;
  width: 100%; /* 控制宽度 */
  max-width: 100%;
  transition: margin-top 0.3s, margin 0.3s;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.content p {
  margin: 0;
  font-size: 14px;
  color: #606266;
}

.content p + p {
  margin-top: 5px;
}
</style>





<!-- <template>
  <div>
	  <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
	    <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
	    <el-breadcrumb-item>退款审核</el-breadcrumb-item>
	  </el-breadcrumb>
<el-card class="box-card">	  
    <el-container>
      <el-main>
        <el-row :gutter="20">
          <el-col :span="8" v-for="refund in refunds" :key="refund.id">
                <el-card>
                   <div class="header">
                     <h3>{{ "预约编号："+refund.aid }}</h3>
                     <el-button 
                       type="primary" 
                       icon="el-icon-check" 
                       @click="passRefund(refund.id)"
                       size="mini"
                     >
                       通过
                     </el-button>
                   </div>
                   <div class="content">
					 <p> 理由: </p>
                     <p>{{ refund.context }}</p>
                     <p>{{ formatDate(refund.submitTime) }}</p>
                   </div>
                 </el-card>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
	</el-card>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'RefundApplyManagement',
  data() {
    return {
      refunds: [],
    };
  },
  mounted() {
    this.getRefunds();
  },
  methods: {
    getRefunds() {
      axios.get('http://localhost:8081/Refund/findAll').then((res) => {
        // this.announcements = res.data;
		 this.refunds = res.data;
      });
    },
	passRefund(refundId) {
	      axios.post('http://localhost:8081/Refund/refund/' + refundId).then((res) => {
	    });
		this.getRefunds();
	},
	formatDate(date) {
	      const d = new Date(date);
	      const year = d.getFullYear();
	      const month = (d.getMonth() + 1).toString().padStart(2, '0');
	      const day = d.getDate().toString().padStart(2, '0');
	      const hours = d.getHours().toString().padStart(2, '0');
	      const minutes = d.getMinutes().toString().padStart(2, '0');
	      return `${year}-${month}-${day} ${hours}:${minutes}`;
	}
  },
};
</script>

<style scoped>
.el-card {
 margin: 10px;
  border-radius: 20px;
  border-color: #C0C4CC;
  width: 3000px; /* 控制宽度 */
  max-width: 100%;
  transition: margin-top 0.3s, margin 0.3s;
}

/* .el-card:hover {
 margin-top: -10px;
  margin-left: 30px;
  margin-right: 30px;
} */

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.content p {
  margin: 0;
  font-size: 14px;
  color: #606266;
}

.content p + p {
  margin-top: 5px;
}
</style>

 -->
