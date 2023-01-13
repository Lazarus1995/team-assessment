// pages/activity/create.js
import { stoneBehavior } from '../../models/behavior'
const util = require("../../utils/util")
const app = getApp()

Page({
  behaviors: [stoneBehavior],
  /**
   * 页面的初始数据
   */
  data: {
    theme: '',
    currentDate: util.formatTime(new Date()),
    description: '',
    picsList: []
  },

  saveActivity() {
    if (!this.data.theme) {
      wx.showToast({
        title: '请填写本次活动主题',
        icon: 'none'
      })
      return
    }
    if (!this.data.description) {
      wx.showToast({
        title: '请简单描述本次活动内容',
        icon: 'none'
      })
      return
    }
    console.log(this.data.picsList)
    wx.uploadFile({
      filePath: this.data.picsList[0].url,
      name: 'file',
      header: {
        "content-type": "multipart/form-data",
        "token": wx.getStorageSync('token')
      },
      url: app.globalData.url + '/api/log/add',
      formData: {
        logUserId: this.data.userId || 1,
        theme: this.data.theme,
        createTime: this.data.currentDate,
        content: this.data.description,
      },
      success: (res) => {
        if (res.statusCode == 200) {
          wx.showToast({
            title: '新建成功',
            icon: 'success'
          }, wx.redirectTo({
            url: '/pages/activity/history',
          }))
        } else {
          wx.showToast({
            title: '新建失败',
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

  afterPicRead(e) {
    let picsList = this.data.picsList
    if (!picsList) {
      picsList = []
    }
    picsList = picsList.concat(e.detail.file)
    this.setData({
      picsList
    })
  },
  afterPicDel(e) {
    let picsList = this.data.picsList
    picsList.splice(e.detail.index, 1)
    this.setData({
      picsList
    })
  },

  descriptionInput() {},

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

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