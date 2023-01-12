// pages/activity/history.js
import { stoneBehavior } from '../../models/behavior'
const app = getApp()

Page({
  behaviors: [stoneBehavior],
  /**
   * 页面的初始数据
   */
  data: {
    activityList: [],
  },

  getActivityList() {
    wx.showLoading({
      title: '加载中...',
    })
    wx.request({
      url: app.globalData.url + '/api/log/list',
      header: {
        token: wx.getStorageSync('token')
      },
      data: {
        userId: this.data.userId || 1
      },
      success: (res) => {
        if (res.statusCode == 200) {
          console.log(res)
          this.setData({
            activityList: res.data.result
          })
        } else {
          wx.showToast({
            title: '获取历史失败',
            icon: 'error'
          })
        }
      },
      fail() {
        wx.showToast({
          title: '接口调用失败',
          icon: 'error'
        })
      }
    })
    wx.hideLoading()
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.getActivityList()
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