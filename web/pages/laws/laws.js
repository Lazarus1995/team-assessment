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
<<<<<<< HEAD
    rulesList: [],
=======
    activeCollapse: [0],
    rulesList: [
      
    ],
    rl: [
      {
        type: '加分项',
        icon: 'smile',
        color: '#07c160',
        children: [],
      },
      {
        type: '扣分项',
        icon: 'warning',
        color: '#FF976A',
        children: []
      },
      {
        type: '否决项',
        icon: 'clear',
        color: '#ee0a24',
        children: []
      }
    ],
>>>>>>> 42743731fd91d5b167efb6adc6b239b05a3aada4
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
<<<<<<< HEAD

  deleteImage(event) {
    const index = event.detail.index
    this.data.fileList.splice(index, 1)
    this.setData({
      fileList: this.data.fileList
=======
  
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
>>>>>>> 42743731fd91d5b167efb6adc6b239b05a3aada4
    })
  },

  saveData(e) {
<<<<<<< HEAD
    this.setData({ dialogVisible: false })
=======
    console.log(e)
    // this.setData({ dialogVisible: false })
  },

  changeCollapse(event) {
    this.setData({
      activeCollapse: event.detail
    })
>>>>>>> 42743731fd91d5b167efb6adc6b239b05a3aada4
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
<<<<<<< HEAD

=======
    // 小立法列表请求
    // args: departmentId
    // wx.request({
    //   url: 'http://192.168.101.2:80/api/law/list/' + 1,
    //   success: (res) => {
    //     console.log(res)
    //     let _data = res.data.result
    //     _data.forEach((val, idx) => {
    //       if (val.lawType == 0) {
    //         // 否决
    //         this.data.rulesList[2].children.push(val)
    //       } else if (val.lawType == 1) {
    //         // 加分
    //         this.data.rulesList[0].children.push(val)
    //       } else {
    //         // 减分
    //         this.data.rulesList[1].children.push(val)
    //       }
    //     })
    //   }
    // })
    // this.setData({
    //   rulesList: this.data.rulesList
    // })
    // console.log(this.data.rulesList)
>>>>>>> 42743731fd91d5b167efb6adc6b239b05a3aada4
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
<<<<<<< HEAD
=======
    let _rulesList = this.data.rl
    wx.request({
      url: 'http://192.168.101.2:80/api/law/list/' + 1,
      success: (res) => {
        console.log(res)
        let _data = res.data.result
        _data.forEach((val, idx) => {
          if (val.lawType == 0) {
            // 否决
            _rulesList[2].children.push(val)
          } else if (val.lawType == 1) {
            // 加分
            _rulesList[0].children.push(val)
          } else {
            // 减分
            _rulesList[1].children.push(val)
          }
        })
      }
    })
    console.log("after", _rulesList)
    // this.setData({
    //   rulesList: _rulesList
    // })
>>>>>>> 42743731fd91d5b167efb6adc6b239b05a3aada4
    this.setData({
      lawsList: this.mockData(['A', 'B', 'C', 'D', 'E', 'F']),
      rulesList: [
        {
<<<<<<< HEAD
          info: '一、请安全正确佩戴安全帽',
          score: 1
        },
        {
          info: '二、高空作业请系好安全绳',
          score: 2
        }
      ]
    })
=======
          type: '加分项',
          icon: 'smile',
          color: '#07c160',
          children: [
            {
              info: '一、安全正确佩戴安全帽',
              score: 1,
              createTime: null,
              createUserId: null,
              departmentId: 1,
              lawContent: "小立法内容7",
              lawId: 161176096475618500,
              lawMonthCount: 0,
              lawScore: 1,
              lawType: 1,
              updateTime: null,
              updateUserId: null,
            },
            {
              info: '二、高空作业正确系好安全绳',
              score: 2,
              createTime: null,
createUserId: null,
departmentId: 1,
lawContent: "小立法内容7",
lawId: 161176096475618500,
lawMonthCount: 0,
lawScore: 1,
lawType: 1,
updateTime: null,
updateUserId: null,
            }
          ]
        },
        {
          type: '扣分项',
          icon: 'warning',
          color: '#FF976A',
          children: [
            {
              info: '一、未安全正确佩戴安全帽',
              score: 1
            },
            {
              info: '二、高空作业未正确系好安全绳',
              score: 2
            }
          ]
        },
        {
          type: '否决项',
          icon: 'clear',
          color: '#ee0a24',
          children: [
            {
              info: '一、未准时上工5次以上',
              score: 100
            },
            {
              info: '二、携带危险物品进去工地',
              score: 100
            }
          ]
        }
      ]
    })
    console.log("after", this.data.rulesList)
>>>>>>> 42743731fd91d5b167efb6adc6b239b05a3aada4
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