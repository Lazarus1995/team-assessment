// pages/mine/index.js
import { stoneBehavior } from '../../models/behavior'
let app = getApp()

Page({
  behaviors: [stoneBehavior],
  /**
   * 页面的初始数据
   */
  data: {
    loginStaus: wx.getStorageSync('token') ? true : false,
    userInfo: {}
  },

  queryDepartmentName(id) {
    wx.request({
      url: app.globalData.url + '/api/user/get/' + id,
      header: {
        token: wx.getStorageSync('token')
      },
      success: (res) => {
        this.setData({
          userInfo: {...this.data.userInfo, departmentName: res.data.result.departmentName}
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.setData({
      userInfo: {
        userName: this.data.userName,
        departmentName: this.data.departmentName,
        avatarUrl: this.data.avatarUrl
      }
    })
    this.queryDepartmentName(this.data.userId || 1)
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})