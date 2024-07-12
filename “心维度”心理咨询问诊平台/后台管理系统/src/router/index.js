//引入依赖及组件
import Vue from 'vue'
import Router from 'vue-router' //引入vue-router
Vue.use(Router)					//注册vue-router


import SystemIndexVue from '../components/SystemIndex'
import ConsultantManagementVue from '../components/ConsultantManagement'
import ApplyManagementVue from '../components/ApplyManagement'
import AdvertisingManagementVue from '../components/AdvertisingManagement'
import UserManagementVue from '../components/UserManagement'
import AppointmentManagementVue from '../components/AppointmentManagement'
import AnnouncementListVue from '../components/AnnouncementList';
import AddAnnouncementVue from '../components/AddAnnouncement';
import AnnouncementDetailVue from '../components/AnnouncementDetail';
import TestManagementVue from '../components/TestManagement';
import TestQuestionManagementVue from '../components/TestQuestionManagement';
import TestQuestionListVue from '../components/TestQuestionList';
//import LoginVue from '../components/Login'
//import RegisterVue from '../components/Register'
/*import AnnouncementManagementVue from '../components/AnnouncementManagement'
import CommentManagementVue from '../components/CommentManagement'
import DataManagementVue from '../components/DataManagement'
import OrderManagementVue from '../components/OrderManagement'
import PersonInformationVue from '../components/PersonInformation'
import SystemManagementVue from '../components/SystemManagement'
import TestDataManagementVue from '../components/TestDataManagement'
import TestManagementVue from '../components/TestManagement'*/

export default new Router({
  routes: [
    {
      path: '/',
      name: 'SystemIndex',
      component: SystemIndexVue
    },
    {
      path: '/SystemIndex',
      name: 'SystemIndex',
      component: SystemIndexVue
    },
    {
      path: '/ApplyManagement',
      name: 'ApplyManagement',
      component: ApplyManagementVue
    },
	{
		path: '/AdvertisingManagement',
		name: 'AdvertisingManagement',
		component: AdvertisingManagementVue
	},
	{
		path: '/ConsultantManagement',
		name: 'ConsultantManagement',
		component: ConsultantManagementVue
	},
	{
		path: '/AppointmentManagement',
		name: 'AppointmentManagement',
		component: AppointmentManagementVue
	},
	{
		path: '/UserManagement',
		name: 'UserManagement',
		component: UserManagementVue
	},
	{
		path: '/AnnouncementList',
		name: 'AnnouncementList',
		component: AnnouncementListVue
	},
	{
		path: '/AddAnnouncement',
		name: 'AddAnnouncement',
		component: AddAnnouncementVue
	},
	{
	    path: '/announcement-detail/:id',
		name: 'AnnouncementDetail',
	    component: AnnouncementDetailVue,
	},
	{
		path: '/TestManagement',
		name: 'TestManagement',
		component: TestManagementVue
	},
	{
		path: '/TestQuestionManagement',
		name: 'TestQuestionManagement',
		component: TestQuestionManagementVue
	},
	{
		path: '/TestQuestionList',
		name: 'TestQuestionList',
		component: TestQuestionListVue
	},
	/*{
		path: '/LoginVue',
		name: 'LoginVue',
		component: LoginVue
	},
	{
		path: '/Register',
		name: 'Register',
		component: RegisterVue
	},
	/*{
		path: '/AnnouncementManagement',
		name: 'AnnouncementManagement',
		component: AnnouncementManagementVue
	},
	{
		path: '/CommentManagement',
		name: 'CommentManagement',
		component: CommentManagementVue
	},

	{
		path: '/DataManagement',
		name: 'DataManagement',
		component: DataManagementVue
	},
	{
		path: '/OrderManagement',
		name: 'OrderManagement',
		component: OrderManagementVue
	},
	{
		path: '/PersonInformation',
		name: 'PersonInformation',
		component: PersonInformationVue
	},
	{
		path: '/AdvertisingManagement',
		name: 'AdvertisingManagement',
		component: SystemManagementVue
	},
	{
		path: '/TestDataManagement',
		name: 'TestDataManagement',
		component: TestDataManagementVue
	},
	{
		path: '/TestMangement',
		name: 'TestMangement',
		component: TestMangementVue
	},*/
  ]
})
