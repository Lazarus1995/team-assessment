// pages/mine/info.js
import WXAPI from 'apifm-wxapi'
const AUTH = require('../../utils/auth')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    avatarUrl: undefined,
    avatarUrlTmpFile: undefined,
    gender: undefined,
    genderArray: ['男', '女'],
    genderIndex: -1
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.getUserApiInfo()
  },

  getPhoneNumber(e) {
    if (!e.detail.errMsg || e.detail.errMsg != "getPhoneNumber:ok") {
      wx.showModal({
        title: '提示',
        content: e.detail.errMsg,
        showCancel: false
      })
      return;
    }
    this._getPhoneNumber(e)
  },

  async _getPhoneNumber(e) {
    let res
    const extConfigSync = wx.getExtConfigSync()
    if (extConfigSync.subDomain) {
      res = await WXAPI.wxappServiceBindMobile({
        token: wx.getStorageSync('token'),
        code: this.data.code,
        encryptedData: e.detail.encryptedData,
        iv: e.detail.iv,
      })
    } else {
      res = await WXAPI.bindMobileWxappV2(wx.getStorageSync('token'), e.detail.code)
    }
    if (res.code === 10002) {
      AUTH.login(this)
      return
    }
    if (res.code == 0) {
      wx.showToast({
        title: '绑定成功',
        icon: 'success',
        duration: 2000
      })
      this.getUserApiInfo();
    } else {
      wx.showModal({
        title: '提示',
        content: res.msg,
        showCancel: false
      })
    }
  },
  async getUserApiInfo() {
    const res = await WXAPI.userDetail(wx.getStorageSync('token'))
    if (res.code == 0) {
      let _data = {}
      _data.apiUserInfoMap = res.data
      _data.nick = res.data.base.nick
      _data.avatarUrl = res.data.base.avatarUrl
      if (!res.data.base.gender) {
        _data.gender = '未知'
      }
      if (res.data.base.gender == 1) {
        _data.gender = '男'
      }
      if (res.data.base.gender == 2) {
        _data.gender = '女'
      }
      
      this.setData(_data)
    }
  },

  async formSubmit(e) {
    console.log(e);
    const postData = {
      token: wx.getStorageSync('token'),
      nick: this.data.nick
    }
    if (this.data.avatarUrlTmpFile) {
      const res = await WXAPI.uploadFile(wx.getStorageSync('token'), this.data.avatarUrlTmpFile)
      if (res.code == 0) {
        postData.avatarUrl = res.data.url
      }
    }
    if (this.data.genderIndex != -1) {
      postData.gender = this.data.genderIndex * 1 + 1
    }
    postData.extJsonStr = JSON.stringify(e.detail.value)
    console.log(postData);
    const res = await WXAPI.modifyUserInfo(postData)
    if (res.code != 0) {
      wx.showToast({
        title: res.msg,
        icon: 'none'
      })
    }
    wx.showToast({
      title: '编辑成功',
    })
    setTimeout(() => {
      wx.navigateBack({
        delta: 0,
      })
    }, 1000);
  },

  bindPickerChange: function(e) {
    this.setData({
      genderIndex: e.detail.value,
      gender: this.data.genderArray[e.detail.value]
    })
  },

  chooseImage() {
    const _this = this
    wx.chooseImage({
      count: 1,
      success (res) {
        _this.setData({
          avatarUrl: res.tempFilePaths[0],
          avatarUrlTmpFile: res.tempFilePaths[0]
        })
      }
    })
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