<template>  
  <div class="admin-dashboard">  
    <img src="../assets/logo.png"/>  
    <div class="title-style">欢迎登录“心维度”后台管理系统</div>  
    <footer class="footer">  
      <p>© 2024 心维度后台管理系统</p>  
    </footer>  
    <div id="mychart" style="width: 700px; height: 400px;"></div>  
    <div id="mychart2" style="width: 700px; height: 400px;"></div> <!-- 新增的图表容器 -->  
  </div>  
</template>  
  
<script>  
import * as echarts from 'echarts';  
import axios from 'axios';

export default {  
  name: 'echart',  
  mounted() {  
    this.drawLine();  
    this.drawLineChart(); // 调用新增的方法来渲染折线图  
  },  
  methods: {  
    drawLine() {  
      let myChart = echarts.init(document.getElementById('mychart'));  
      axios.get('http://localhost:3000/api/administrator/selectAdministrator'
        
      ).then(response => {  
        let data = response.data;  
        let trueNames = data.map(item => item.trueName);  
        let accumulatedHours = data.map(item => item.accumulatedHour);  
          
        let option = {  
          backgroundColor: 'white',  
          title: {  
            text: '服务记录'  
          },  
          legend: {  
            data: ["累计时间"]  
          },  
          tooltip: {  
            trigger: 'axis'  
          },  
          xAxis: {  
            name: '心理咨询师',  
            data: trueNames,  
            axisLabel: {  
              show: true,  
              textStyle: {  
                color: 'black',  
                fontSize: 16  
              }  
            }  
          },  
          yAxis: {  
            name: '小时',  
            axisLabel: {  
              show: true,  
              textStyle: {  
                color: 'black',  
                fontSize: 16  
              }  
            }  
          },  
          series: [  
            {  
              name: '累计时间',  
              type: 'bar',  
              color: ['#00ff00'],  
              data: accumulatedHours  
            }  
          ]  
        };  
          
        myChart.setOption(option, true);  
      }).catch(error => {  
        console.error('Error fetching data: ', error);  
      });  
    },  
    drawLineChart() {  
      let myChart2 = echarts.init(document.getElementById('mychart2'));  
           axios.get('http://localhost:3000/api/administrator/select_logintimesAdministrator')
             .then(response => {  
               let data = response.data;  
               let dates = data.map(item => item.date);  
               let visitCounts = data.map(item => item.visit_count);  
               
               let option = {  
                 backgroundColor: 'white',  
                 title: { text: '每日访问量' },  
                 legend: { data: ["访问次数"] },  
                 tooltip: { trigger: 'axis' },  
                 xAxis: {  
                   name: '日期',  
                   data: dates,  
                   axisLabel: {  
                     show: true,  
                     textStyle: { color: 'black', fontSize: 16 }  
                   }  
                 },  
                 yAxis: {  
                   name: '次数',  
                   axisLabel: {  
                     show: true,  
                     textStyle: { color: 'black', fontSize: 16 }  
                   }  
                 },  
                 series: [{  
                   name: '访问次数',  
                   type: 'line',  
                   color: ['#0000ff'],  
                   data: visitCounts  
                 }]  
               };  
               
               myChart2.setOption(option, true);  
             })  
             .catch(error => {  
               console.error('Error fetching data: ', error);  
             });  
    }  
  }  
};  
</script>  
  
<style scoped>  
/* ...（之前的样式） */  
</style>
