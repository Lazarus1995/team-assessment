// pages/divide/index.js
import { stoneBehavior } from '../../models/behavior'

let app = getApp()

Page({
  behaviors: [stoneBehavior],
  /**
   * 页面的初始数据
   */
  data: {
    totalSalary: 0,
    salaryInfo: [],
    visible: false,
    showDialog: false
  },

  /**
   * 获取键入数值
   */
  onChange(event) {
    this.setData({
      totalSalary: event.detail
    })
  },

  /**
   * 根据输入总金额返回组员实际下发金额数据
   */
  handleCalculation() {
    if (!this.data.totalSalary) {
      wx.showToast({
        title: '请填写下发总金额',
        icon: 'none'
      })
      return
    }

    if (isNaN(this.data.totalSalary) || Number(this.data.totalSalary) < 0) {
      wx.showToast({
        title: '请使用正确格式填写下发总金额',
        icon: 'none'
      })
      return
    }

    wx.showLoading({
      title: '',
    })
    wx.request({
      url: app.globalData.url + '/api/userScore/list/',
      header: {
        token: wx.getStorageSync('token')
      },
      data: {
        totalSalary: this.data.totalSalary,
        userId: this.data.userId || 1,
        departmentId: this.data.departmentId || 19,
      },
      success: (res) => {
        wx.hideLoading()
        if (res.statusCode == 200) {
          this.setData({
            salaryInfo: res.data.result,
            visible: true
          })
        }
      },
      fail: () => {
        wx.hideLoading()
        wx.showToast({
          title: '接口调用失败',
          icon: 'error'
        })
      }
    })
  },

  /**
   * 下载当月下发文件
   */
  downloadFile() {
    this.setData({
      showDialog: true
    })
    const fileExtName = ''
    const randfile = new Date().getTime() + fileExtName
    const newPath = `${wx.env.USER_DATA_PATH}/${randfile}`
    wx.downloadFile({
      url: app.globalData.url + '/api/logLawProcess/downloadbeta',
      filePath: newPath,
      header: {
        "content-type": "application/xlsx",
        "token": wx.getStorageSync('token')
      },
      success: (result) => {
        console.log(result)
        wx.openDocument({
          filePath: result.filePath,
          showMenu: true,
          fileType: 'xlsx',
          success(res) {
            console.log('Open file success!')
          }
        })
      },
      fail: (res) => {
        wx.hideLoading();
      },
      complete: (res) => {},
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