// pages/laws/laws.js

Page({

  /**
   * 页面的初始数据
   */
  data: {
    active: 0,
    lawsList: [],
    dialogVisible: false,
    popupVisible: false,
    fileList: [],
    formData: {},
    rulesList: [],
    modifyInfo: {}
  },

  // {
  //   index: 'A',
  //   userList: []
  // }

  showDialog(event) {
    this.setData({
      dialogVisible: true,
      formData: {"username": event.target.dataset.name, "currentDate": this.formatTime()}
    });
  },

  closeDialog() {
    this.setData({ dialogVisible: false })
  },

  showPopup(event) {
    this.setData({
      popupVisible: true,
      modifyInfo: this.data.rulesList[event.target.dataset.index]
    })
  },

  closePopup() {
    this.setData({ popupVisible: false })
  },

  formatTime() {
    let date = new Date(),
    year = date.getFullYear(),
    month = date.getMonth() + 1,
    day = date.getDate()
    
    if (month < 10) month = `0${month}`
    if (day < 10) day = `0${day}`

    return `${year}-${month}-${day}`
  },

  modifyRules(e) {
    console.log(e.target.value)
  },

  modifyScore(event) {
    console.log(event.detail)
  },

  modifyInfo(event) {
    console.log(event.datail)
  },

  deleteRules(event) {
    const index = event.target.dataset.index
    this.data.rulesList.splice(index, 1)
    this.setData({
      rulesList: this.data.rulesList
    })
  },

  deleteImage(event) {
    const index = event.detail.index
    this.data.fileList.splice(index, 1)
    this.setData({
      fileList: this.data.fileList
    })
  },

  saveData(e) {
    this.setData({ dialogVisible: false })
  },

  // async afterRead(event) {
  //   const { file } = event.detail;
  //   // 当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
  //   let uploadPromiseTask = []
  //   for (let i = 0; i < file.length; i++) {
  //     uploadPromiseTask.push(file[i].url)
  //   }
  //   Promise.all(uploadPromiseTask).then(res => {
  //     this.setData({
  //       fileList: this.data.fileList.concat(res)
  //     })
  //     wx.hideLoading()
  //   })
  //   wx.uploadFile({
  //     url: 'https://example.weixin.qq.com/upload', // 仅为示例，非真实的接口地址
  //     filePath: file.url,
  //     name: 'file',
  //     formData: { user: 'test' },
  //     success(res) {
  //       // 上传完成需要更新 fileList
  //       const { fileList = [] } = this.data;
  //       fileList.push({ ...file, url: res.data });
  //       this.setData({ fileList });
  //     },
  //   });
  // },

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

  mockData(indexList) {
    let data = [], users = ['张三', '李四', '王五']
    indexList.forEach((val, idx) => {
      let userList = []
      users.map((v) => {
        userList.push(val + ' ' + v)
      })
      data.push(
        {
          index: val,
          userList: userList
        }
      )
    })
    return data
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    this.setData({
      lawsList: this.mockData(['A', 'B', 'C', 'D', 'E', 'F']),
      rulesList: [
        {
          info: '一、请安全正确佩戴安全帽',
          score: 1
        },
        {
          info: '二、高空作业请系好安全绳',
          score: 2
        }
      ]
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