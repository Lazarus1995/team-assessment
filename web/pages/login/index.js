// pages/login/index.js
import { stoneBehavior } from '../../models/behavior'
const util = require('../../utils/util')
let app = getApp()
Page({
  behaviors: [stoneBehavior],
  /**
   * 页面的初始数据
   */
  data: {
    phone: '',
    password: ''
  },

  signIn() {
    console.log(this.data)
    if (!this.data.phone && !util.checkPhone(this.data.phone)) {
      wx.showToast({
        title: '请输入手机号码',
        icon: 'error'
      })``
      return
    }

    if (!util.checkPhone(this.data.phone)) {
      wx.showToast({
        title: '手机号码格式错误',
        icon: 'error'
      })
      return
    }

    if (!this.data.password) {
      wx.showToast({
        title: '请输入登录密码',
        icon: 'error'
      })
      return
    }

    wx.showLoading({
      title: '登录中',
    })
    
    wx.request({
      url: app.globalData.url + '/api/user/login',
      method: "POST",
      data: {
        phone: this.data.phone,
        password: this.data.password
      },
      success: (res) => {
        let _data = res.data.result
        if (_data) {
          wx.showToast({
            title: '登录成功',
            icon: 'success'
          }, wx.switchTab({
            url: '/pages/team/index',
          }))
          this.updateUserName(_data.userName)
          this.updateId(_data.userId)
          this.updateDepartment(_data.departmentId)
          this.updateDepartmentName(_data.departmentName)
          this.updatePhone(_data.phone)
          this.updateAvatar(_data.avatarUrl)
          wx.setStorageSync('token', _data.token)
          this.getWaitTaskCount(_data.userId)
        } else {
          wx.hideLoading()
          wx.showToast({
            title: '登录失败',
            icon: 'error'
          })
        }
      }
    })
  },

  getWaitTaskCount(userId) {
    wx.request({
      url: app.globalData.url + '/api/logLawProcess/getList/' + userId,
      header: {
        token: wx.getStorageSync('token')
      },
      success: (res) => {
        if (res.statusCode == 200) {
          let _data = res.data.result
          this.updateTask(_data.length)
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
    const token = wx.getStorageSync('token')
    if (token) {
      wx.switchTab({
        url: '/pages/team/index',
      })
    }
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