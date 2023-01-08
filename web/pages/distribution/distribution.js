// pages/distribution/distribution.js

import { MockData } from './mock'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    average: 0,
    queryData: {
      userInfo: MockData,
      totalScore: MockData.length * 80
    },
    visible: false
  },

  onChange(event) {
    this.setData({
      average: this.toFix(event.detail / this.data.queryData.totalScore, 5)
    })
  },

  toFix(num, val) {
    return Math.floor(num * Math.pow(10, val)) / Math.pow(10, val)
  },

  handleCalculation() {
    this.data.queryData['userInfo'].forEach((item, index) => {
      this.setData({
        ["queryData.userInfo[" + index + "].issued_amount"]: this.toFix(item.score * this.data.average, 2)
      })
    })
    this.setData({
      visible: true
    })
  },

  downloadFile() {
    wx.downloadFile({
      url: 'https://www.gjtool.cn/pdfh5/git.pdf',
      header: header,
      timeout: 0,
      success: (result) => {
        if (result.statusCode === 200) {
          const filePath = res.temFilePath
          wx.openDocument({
            filePath: filePath,
            showMenu: true,
            success: function(res) {
              console.log('Open file success!')
            }
          })
        }
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