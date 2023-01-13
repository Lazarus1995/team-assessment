// pages/info/index.js
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

  bindPickerChange: function(e) {
    this.setData({
      genderIndex: e.detail.value,
      gender: this.data.genderArray[e.detail.value]
    })
  },

  async formSubmit(e) {
    console.log(e);
    const postData = {
      token: wx.getStorageSync('token'),
      nick: this.data.nick
    }
    if (this.data.avatarUrlTmpFile) {
      // const res = await WXAPI.uploadFile(wx.getStorageSync('token'), this.data.avatarUrlTmpFile)
      if (res.code == 0) {
        postData.avatarUrl = res.data.url
      }
    }
    if (this.data.genderIndex != -1) {
      postData.gender = this.data.genderIndex * 1 + 1
    }
    postData.extJsonStr = JSON.stringify(e.detail.value)
    console.log(postData);
    // const res = await WXAPI.modifyUserInfo(postData)
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