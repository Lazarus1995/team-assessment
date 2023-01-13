import { observable, action } from "mobx-miniprogram";

export const userInfo = observable({
  userName: '',
  userId: '',
  departmentId: '',
  departmentName: '',
  phoneNumber: '',
  avatarUrl: '',
  taskNum: 0,
  messageNum: 0,

  updateUserName: action(function (name) {
    this.userName = name
  }),

  updateId: action(function (id) {
    this.userId = id
  }),

  updateDepartment: action(function (d_id) {
    this.departmentId = d_id
  }),

  updateDepartmentName: action(function (name) {
    this.departmentName = name
  }),

  updatePhone: action(function (phone) {
    this.phoneNumber = phone
  }),

  updateAvatar: action(function (url) {
    this.avatarUrl = url
  }),

  updateTask: action(function (number) {
    this.taskNum = number
  }),

  updateMsg: action(function(number) {
    this.messageNum = number
  })
});
