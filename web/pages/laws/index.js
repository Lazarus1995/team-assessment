// pages/laws/index.js
const util = require('../../utils/util.js')
import { stoneBehavior } from '../../models/behavior'
let app = getApp()

Page({
  behaviors: [stoneBehavior],
  /**
   * 页面的初始数据
   */
  data: {
    siderTabActive: 0,
    siderTabCurrent: 0,
    activeCollapse: [],
    lawsList: [],
    chooseDepartmentId: '',
    // score
    membersList: [],
    scoreLaw: [],
    scoreLawsScore: [],
    scoreLawsIndex: [],
    scoreDialog: false,
    scoreUserName: '',
    scoreUserId: '',
    scoreType: '',
    scoreLawContent: '',
    scoreLawScore: '',
    scoreLawId: '',
    scoreSupply: '',
    scorePicsList: [],
    // modify
    modifyPopup: false,
    modeifyLawId: '',
    modeifyLawScore: 0,
    modifyLawContent: '',
    // create
    createPopup: false,
    createLawScore: 0,
    createDepartmentId: 0,
  },

  /**
   * 侧边栏班组切换
   */
  switchTab(e) {
    let that = this
    let id = e.target.id
    if (this.data.siderTabCurrent == id) {
      return false
    } else {
      that.setData({
        siderTabCurrent: id
      })
    }
    that.setData({
      siderTabActive: id
    })
  },

  /**
   * 折叠状态切换
   */
  changeCollapse(event) {
    this.setData({
      activeCollapse: event.detail
    })
  },

  /**
   * 修改Popup状态切换
   */
  showModifyPopup(e) {
    let _data = e.target.dataset
    this.setData({
      modifyPopup: true,
      modifyLawContent: _data.content,
      modeifyLawScore: _data.score,
      modeifyLawId: _data.id
    })
  },

  /**
   * 新增Popup状态切换
   */
  showCreatePopup(e) {
    this.setData({
      createPopup: true,
      createDepartmentId: e.target.dataset.id
    })
  },

  /**
   * 修改评分Diglog状态切换
  */
  showScoreDialog(e) {
    let _data = e.target.dataset
    this.setData({
      scoreDialog: true,
      scoreUserName: _data.name,
      scoreUserId: _data.id,
      chooseDepartmentId: _data.department
    })
  },

  /**
   * 关闭Popup
   */
  closePopup(e) {
    this.setData({
      modifyPopup: false,
      createPopup: false
    })
  },

  /**
   * 关闭Dialog
   */
  closeScoreDialog(e) {
    this.setData({ scoreDialog: false })
  },
  

  /**
   * 小立法分数修改
   */
  modifyScore(event) {
    this.setData({ modeifyLawScore: event.detail })
  },

  /**
   * 小立法分数新建
   */
  createScore(event) {
    this.setData({
      createLawScore: event.detail
    })
  },

  radioChange(e) {
    this.setData({ scoreType: e.detail.value })
    this.queryLawByDepartment(this.data.chooseDepartmentId)
  },

  /**
   * 请求小立法
   */
  queryLaw() {
    wx.request({
      url: app.globalData.url + '/api/law/list',
      header: {
        token: wx.getStorageSync('token')
      },
      data: {
        departmentId: 19,
      },
      success: (res) => {
        if (res.data) {
          console.log(res.data)
          let _data = res.data.result
          this.setData({
            lawsList: util.formatLaws(_data)
          })
        }
      }
    })
  },

  /**
   * 修改小立法
   */
  handleModifyLaw() {
    let modeifyInfo = {
      lawId: this.data.modeifyLawId,
      lawScore: this.data.modeifyLawScore,
      lawContent: this.data.modifyLawContent
    }
    wx.showLoading({
      title: '',
    })
    wx.request({
      url: app.globalData.url + '/api/law/update',
      method: 'POST',
      header: {
        token: wx.getStorageSync('token')
      },
      data: {
        ...modeifyInfo,
        userId: this.data.userId || 1
      },
      success: (res) => {
        wx.hideLoading()
        if (res.statusCode == 200) {
          wx.showToast({
            title: '修改成功',
            icon: 'success'
          })
          this.queryLaw()
        } else {
          wx.showToast({
            title: '修改失败',
            icon: 'error'
          })
        }
      },
      fail() {
        wx.showToast({
          title: '接口调用失败',
        })
      }
    })
    this.setData({ modifyPopup: false })
  },

  /**
   * 删除小立法
   */
  deleteLaw(e) {
    wx.showLoading({
      title: '',
    })
    wx.request({
      url: app.globalData.url + '/api/law/delete',
      method: 'POST',
      header: {
        token: wx.getStorageSync('token')
      },
      data: {
        // userId: wx.getStorageSync('key')
        lawId: e.target.dataset.id
      },
      success: (res) => {
        wx.hideLoading()
        if (res.statusCode == 200) {
          wx.showToast({
            title: '删除成功',
            icon: 'success'
          })
          this.queryLaw()
        } else {
          wx.showToast({
            title: '删除失败',
            icon: 'error'
          })
        }
      },
      fail() {
        wx.showToast({
          title: '接口调用失败',
          icon: 'error'
        })
      }
    })
  },

  /**
   * 新增小立法
   */
  createLaw(e) {
    console.log(e.detail.value, this.data.createDepartmentId, this.data.createLawScore)
    wx.showLoading({
      title: '',
    })
    wx.request({
      url: app.globalData.url + '/api/law/add',
      method: 'POST',
      header: {
        token: wx.getStorageSync('token')
      },
      data: {
        /**
         * departmentId, lawType, lawScore, lawContent
         */
        ...e.detail.value,
        departmentId: this.data.createDepartmentId,
        lawScore: this.data.createLawScore,
        // departmentId: '',
        // userId: wx.getStorageSync('key')
      },
      success: (res) => {
        wx.hideLoading()
        if (res.statusCode == 200) {
          wx.showToast({
            title: '添加成功',
            icon: 'success'
          })
          this.queryLaw()
        } else {
          wx.showToast({
            title: '添加失败',
            icon: 'error'
          })
        }
      },
      fail() {
        wx.showToast({
          title: '接口调用失败',
          icon: 'error'
        })
      }
    })
    this.setData({ createPopup: false })
  },

  /**
   * 每日评分
   */
  submitScore() {
    wx.showLoading({
      title: '',
      icon: 'none'
    })
    console.log(this.data)
    wx.uploadFile({
      filePath: this.data.scorePicsList[0].url,
      name: 'file',
      header: {
        "content-type": "multipart/form-data",
        "token": wx.getStorageSync('token')
      },
      url: app.globalData.url + '/api/logLawProcess/addProcess',
      formData: {
        lawId: this.data.scoreLawId,
        userId: this.data.scoreUserId,
        lawType: this.data.scoreType,
        content: this.data.scoreLawContent,
        createUserId: this.data.userId || 1,
        lawScore: this.data.scoreLawScore,
      },
      success: (res) => {
        console.log(res)
        if (res.statusCode == 200) {
          wx.showToast({
            title: '评分成功',
            icon: 'success'
          })
        } else {
          wx.showToast({
            title: '评分失败',
            icon: 'error'
          })
        }
      },
      fail() {
        wx.showToast({
          title: '接口调用失败',
          icon: 'error'
        })
      }
    })
    wx.hideLoading()
    this.setData({ scoreDialog: false })
  },

  /**
   * 请求组员
   */
  queryMember() {
    let departmentId = 19
    wx.request({
      url: app.globalData.url + '/api/userScore/list',
      header: {
        token: wx.getStorageSync('token')
      },
      data: {
        departmentId: departmentId
      },
      success: (res) => {
        if (res.data) {
          let _data = res.data.result
          this.setData({
            membersList: util.formatMember(_data)
          })
        }
      }
    })
  },

  /**
   * 获取评分人员当前部门条例
   */
  queryLawByDepartment(id) {
    wx.request({
      url: app.globalData.url + '/api/law/list',
      header: {
        token: wx.getStorageSync('token')
      },
      data: {
        departmentId: id,
        lawType: this.data.scoreType
      },
      success: (res) => {
        if (res.data) {
          let _data = res.data.result, content = [], score = [], index = []
          _data.map(val => {
            content.push(val.lawContent)
            score.push(val.lawScore)
            index.push(val.lawId)
          })
          this.setData({
            scoreLaw: content,
            scoreLawsScore: score,
            scoreLawsIndex: index
          })
        }
      }
    })
  },

  bindPickerChange(e) {
    let idx = e.detail.value
    this.setData({
      scoreLawContent: this.data.scoreLaw[idx],
      scoreLawScore: this.data.scoreLawsScore[idx],
      scoreLawId: this.data.scoreLawsIndex[idx]
    })
  },

  afterPicRead(e) {
    let picsList = this.data.scorePicsList
    if (!picsList) {
      picsList = []
    }
    picsList = picsList.concat(e.detail.file)
    this.setData({
      scorePicsList: picsList
    })
  },

  afterPicDel(e) {
    let picsList = this.data.scorePicsList
    picsList.splice(e.detail.index, 1)
    this.setData({
      scorePicsList: picsList
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.queryLaw()
    this.queryMember()
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