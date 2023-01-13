// pages/task/index.js
import { stoneBehavior } from '../../models/behavior'
const util = require("../../utils/util")
let app = getApp()

Page({
  behaviors: [stoneBehavior],
  /**
   * 页面的初始数据
   */
  data: {
    waitList: [],
    doing: [],
    finishList: []
  },

  queryFinish() {
    let id = this.data.userId || 1
    wx.request({
      url: app.globalData.url + '/api/logLawProcess/getList/' + id,
      header: {
        token: wx.getStorageSync('token')
      },
      success: (res) => {
        if (res.statusCode == 200) {
          console.log(res)
          let _data = res.data.result, tmp = []
          _data.map(val => {
            tmp.push({
              taskName: '组员评分',
              taskTime: val.createTime,
              status: '已完成',
              content: val.content
            })
          })
          this.setData({
            finishList: tmp
          })
        }
      },
      fail() {
        wx.showToast({
          title: '获取失败',
          icon: 'error'
        })
      }
    })
  },

  queryWait() {
    wx.request({
      url: app.globalData.url + '/api/logRating/getTodo',
      header: {
        token: wx.getStorageSync('token')
      },
      data: {
        userId: this.data.userId || 1,
        type: 0
      },
      success: (res) => {
        if (res.statusCode == 200) {
          let _data = res.data.result
          if (_data.length > 0) {
            this.setData({
              waitList: [{
                taskName: '组员评分',
                taskTime: _data[0].createTime,
                status: _data[0].ratingStatus == 0 ? '未完成' : ''
              }],
            })
          }
        }
      },
      fail() {
        wx.showToast({
          title: '获取失败',
          icon: 'error'
        })
      }
    })
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.queryWait()
    this.queryFinish()
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