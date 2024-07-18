<!-- <template>
  <div id="app">
    <Users />
  </div>
</template>



<script>
import Users from './components/UserManagement.vue';

export default {
  name: 'App',
  components: {
    Users
  }
}
</script>

<template>
  <div id="app">
    <Applys />
  </div>
</template>



<script>
import Applys from './components/ApplyManagement.vue';

export default {
  name: 'App',
  components: {
    Applys
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style -->>

<!-- <template>
  <div id="app">
    <Users />
  </div>
</template>



<script>
import Users from './components/UserManagement.vue';

export default {
  name: 'App',
  components: {
    Users
  }
}
</script> -->

<template>
	
  <!--<div id="app">
    <Applys />
  </div>-->
  <div class="app">
      <!-- 头部 -->
      <myHeader></myHeader>
  
      <el-container 
        style="height: 100vh; 
        border: 1px solid #eee; 
        margin: 0;
        border: 0;
        padding: 0;">     <!-- style： 隐藏滚动条  去掉白边-->
        <!-- 侧边栏 -->
        <el-aside style="width: 200px;">
          <asideNav></asideNav>
        </el-aside>
        <!-- 主页面 -->
        <el-main>
          <router-view></router-view>  
        </el-main>
      </el-container>
    </div>
</template>



<script>
import Applys from './components/ApplyManagement';
import myHeader from "./components/myHeader";
import AsideNav from "./components/AsideNav";
import SystemIndexVue from './components/SystemIndex';
import AdvertisingManagementVue from './components/AdvertisingManagement';
import ConsultantManagementVue from './components/ConsultantManagement';
import UserManagementVue from './components/UserManagement.vue';
import AppointmentManagementVue from './components/AppointmentManagement';
import AnnouncementListVue from './components/AnnouncementList';
import AddAnnouncementVue from './components/AddAnnouncement';
import AnnouncementDetailVue from './components/AnnouncementDetail';
import TestManagementVue from './components/TestManagement';
import TestQuestionManagementVue from './components/TestQuestionManagement';
import TestQuestionListVue from './components/TestQuestionList';
import TestQuestionDetailVue from './components/TestQuestionDetail.vue';
import UserDetailVue from './components/UserDetail.vue';
import PageListVue from './components/PageList';
import PageDetailVue from './components/PageDetail';
import CommentManagementVue from './components/CommentManagement';
import BugManagementVue from './components/BugManagement';
import EssayManagementVue from './components/EssayManagement';
import UserLoginMessageManagementVue from './components/UserLoginMessageManagement';
import loginVue from './components/login.vue';
import TreeholeManagementVue from './components/TreeholeManagement';
import RefundApplyManagementVue from './components/RefundApplyManagement';
//import RegisterVue from './components/Register.vue';
/*import AnnouncementManagementVue from './components/AnnouncementManagement';
import CommentManagementVue from './components/CommentManagement';
import DataManagementVue from './components/DataManagement';

import PersonInformationVue from './components/PersonInformation';
import SystemManagementVue from './components/SystemManagement';
import TestDataManagementVue from './components/TestDataManagement';
*/


export default {
  name: 'App',
  components: {
    Applys,
	myHeader,
	AsideNav,
	AdvertisingManagementVue,
	SystemIndexVue,
	ConsultantManagementVue,
	AppointmentManagementVue,
	UserManagementVue,
	AnnouncementListVue,
	AddAnnouncementVue,
	AnnouncementDetailVue,
	TestManagementVue,
	TestQuestionManagementVue,
	TestQuestionListVue,
	TestQuestionDetailVue,
	UserDetailVue,
	PageListVue,
	PageDetailVue,
	CommentManagementVue,
	BugManagementVue,
	EssayManagementVue,
	UserLoginMessageManagementVue,
	loginVue,
	TreeholeManagementVue,
	RefundApplyManagementVue,
  }
}
</script>

<style>
	html,
	  body{
	    height: 100%;
	    margin: 0;
	    border: 0;
	    padding: 0;
	  },
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
