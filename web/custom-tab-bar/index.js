// custom-tab-bar/index.js
import { stoneBehavior } from '../models/behavior'

Page({
  options: {
    "styleIsolation": "shared"
  },
  behaviors: [stoneBehavior],
  /**
   * 页面的初始数据
   */
  data: {
    "list": [
      {
        "pagePath": "/pages/team/index",
        "text": "组织结构",
        "iconPath": "../icons/team.png",
        "selectedIconPath": "../icons/_team.png",
        "info": ""
      },
      {
        "pagePath": "/pages/laws/index",
        "text": "小立法",
        "iconPath": "../icons/laws.png",
        "selectedIconPath": "../icons/_laws.png",
        "info": ""
      },
      {
        "pagePath": "/pages/divide/index",
        "text": "分配",
        "iconPath": "../icons/divide.png",
        "selectedIconPath": "../icons/_divide.png",
        "info": ""
      },
      {
        "pagePath": "/pages/mine/index",
        "text": "我的",
        "iconPath": "../icons/mine.png",
        "selectedIconPath": "../icons/_mine.png",
        "info": ""
      }
    ]
  },

  /**
   * tabBar切换
   */
  onChange(event) {
    this.updateActive(event.detail)
    wx.switchTab({
      url: String(this.data.list[event.detail].pagePath),
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    console.log(this.data)
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