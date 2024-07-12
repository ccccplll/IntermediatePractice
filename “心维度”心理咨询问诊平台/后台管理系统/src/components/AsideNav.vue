<template>
  <div class="aside" style="height: 100%; width: 100%;">
    <el-row class="tac" style="height: 100%; width: 100%;">
      <el-col :span="12" style="height: 100%; width: 100%;">
        <el-menu 
          default-active="this.$route.path" router
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          style="height: 100%; width: 100%;">
		  
		  <el-menu-item index="/SystemIndex">
		    <i class="el-icon-menu"></i>
		    <span slot="title">系统主页</span>
		  </el-menu-item>
		  <el-menu-item index="/PersonInformation">
		    <i class="el-icon-setting"></i>
		    <span slot="title">个人信息</span>
		  </el-menu-item>
          <el-submenu>
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>用户管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/UserManagement">咨询者账号管理</el-menu-item>
              <el-menu-item index="/ConsultantManagement">咨询师账号管理</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
		  <el-submenu>
		              <template slot="title">
		                <i class="el-icon-location"></i>
		                <span>测评管理</span>
		              </template>
		              <el-menu-item-group>
		                <el-menu-item index="/TestManagement">测评结果管理</el-menu-item>
		                <el-menu-item index="/TestDataManagement">测评数据管理</el-menu-item>
						<el-menu-item index="/TestQuestionManagement">测评问题管理</el-menu-item>
						<el-menu-item index="/TestQuestionList">测评问题列表</el-menu-item>
		              </el-menu-item-group>
		  </el-submenu>

          <el-menu-item index="/ApplyManagement">
            <i class="el-icon-document"></i>
            <span slot="title">审核管理</span>
          </el-menu-item>
          <el-menu-item index="/AppointmentManagement">
            <i class="el-icon-setting"></i>
            <span slot="title">预约管理</span>
          </el-menu-item>
		  <el-submenu>
		              <template slot="title">
		                <i class="el-icon-location"></i>
		                <span>通知管理</span>
		              </template>
		              <el-menu-item-group>
		                <el-menu-item index="/CommentManagement">评论管理</el-menu-item>
		            
						<el-menu-item index="/AdvertisingManagement">广告管理</el-menu-item>
		              </el-menu-item-group>
		  </el-submenu>
		  <el-submenu>
		              <template slot="title">
		                <i class="el-icon-location"></i>
		                <span>公告管理</span>
		              </template>
		              <el-menu-item-group>
		                <el-menu-item index="/AnnouncementList">公告列表</el-menu-item>
		                <el-menu-item index="/AddAnnouncement">添加公告</el-menu-item>
		              </el-menu-item-group>
		  </el-submenu>
          <el-menu-item index="/DataManagement">
            <i class="el-icon-setting"></i>
            <span slot="title">数据中心</span>
          </el-menu-item>
		  <el-menu-item index="/SystemManagement">
		    <i class="el-icon-setting"></i>
		    <span slot="title">系统管理</span>
		  </el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>

<script>

export default {
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>
