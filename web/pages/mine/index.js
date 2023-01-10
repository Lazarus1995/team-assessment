// pages/mine/index.js
import { stoneBehavior } from '../../models/behavior'
Page({
  behaviors: [stoneBehavior],
  /**
   * 页面的初始数据
   */
  data: {
    loginStatus: wx.getStorageSync('session_key') ? true : false,
    userInfo: {
      // username: "张三",
      // status: "在岗",
      // apartment: "二建-15号线-一标段-一工区-始发井工点-文明施工班组",
      // userAvatar: "https://img.yzcdn.cn/vant/cat.jpeg",
    }
  },

  signOut() {
    wx.showLoading({
      title: '',
    })
    wx.removeStorageSync('session_key')

    wx.hideLoading()
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    let _status = ""
    wx.request({
      url: 'http://192.168.101.4:8090/api/user/get/1',
      success: (res) => {
        let _data = res.data.result
        if (_data.status == 0) {
          _data.status = "禁用"
        } else if (_data.status == 1) {
          _data.status = "在职"
        } else {
          _data.status = "删除"
        }
        this.setData({
          userInfo: _data
        })
      }
    })
    console.log(this.data.userInfo)
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