// pages/task/task.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    waitCount: 3,
    waitList: [
      {
        taskName: '组员评分',
        taskTime: '2023-01-07 07:00 - 2023-01-07 23:59',
        status: '待完成'
      },
      {
        taskName: '组员评分',
        taskTime: '2023-01-07 07:00 - 2023-01-07 23:59',
        status: '待完成'
      },
      {
        taskName: '组员评分',
        taskTime: '2023-01-07 07:00 - 2023-01-07 23:59',
        status: '待完成'
      }
    ],
    doing: 0,
    doingList: [],
    finish: 20,
    finishList: [],
    active: 0,
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
    let list = []
    for (let i = 0; i < this.data.finish; i++) {
      list.push(
        {
          taskName: '组员评分',
          taskTime: '2023-01-07 07:00 - 2023-01-07 23:59',
          status: '已完成'
        }
      )
    }
    this.setData({
      finishList: list
    })
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