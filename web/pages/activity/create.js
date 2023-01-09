// pages/activity/create.js
import WXAPI from 'apifm-wxapi'
import util from '../../utils/util'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    theme: '',
    currentDate: util.formatTime(new Date()),
    description: '',
    picsList: []
  },

  async saveActivity() {
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

    const extJsonStr = {}
    extJsonStr['主题'] = this.data.theme
    extJsonStr['日期'] = this.data.currentDate
    extJsonStr['描述'] = this.data.description
    // ? 是否添加其他用户标识 

    // 图片上传-批量
    if (this.data.picsList) {
      for (let index = 0; index < this.data.picsList.length; index++) {
        const pic = this.data.picsList[index];
        const res = await WXAPI.uploadFile(wx.getStorageSync('token'), pic.url)
        if (res.code == 0) {
          extJsonStr['file' + index] = res.data.url
        }
      }
    }

    const res = await WXAPI.addComment({
      token: wx.getStorageSync('token'),
      type: 1,
      extJsonStr: JSON.stringify(extJsonStr),
      content: this.data.content
    })

    console.log(res)

    if (res.code === 0) {
      wx.showToast({
        title: '提交成功',
        icon: 'success'
      })
      setTimeout(() => {
        wx.navigateBack({
          delta: 0,
        })
      }, 1000);
    } else {
      wx.showToast({
        title: res.msg,
        icon: 'error'
      })
    }
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

  descriptionInput() {

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