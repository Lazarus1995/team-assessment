// pages/laws/index.js
import { lawsData, memberData } from './data'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    siderTabActive: 0,
    siderTabCurrent: 0,
    activeCollapse: [],
    lawsList: [],
    membersList: [],
    dialogVisible: false,
    memberScore: {},
    memberName: '',
    scoreType: '',
    lawInfo: '',
    supDescription: '',
    pictureList: [],
    modifyPopup: false,
    modifyLawContent: '',
    modeifyLawScore: 0,
    modeifyLawId: ''
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
   * 格式化后端请求数据
   */
  formatLaws(array) {
    let belong = [], allBelong = [], result = []
    array.forEach((val) => {
      belong.push(val.belong)
    })
    allBelong = Array.from(new Set(belong))
    allBelong.map(val => {
      let temp = [], obj = {
        belong: val,
        children: []
      }
      array.map(i => {
        if (val == i.belong) {
          temp.push(i)
        }
      })
      obj.children = this.formatLawsResult(temp)
      result.push(obj)
    })
    return result
  },
  formatLawsResult(array) {
    let addObj = {
      type: '加分项',
      icon: 'smile',
      color: '#07c160',
      children: []
    }, minusObj = {
      type: '扣分项',
      icon: 'warning',
      color: '#FF976A',
      children: []
    }, vetoObj = {
      type: '否决项',
      icon: 'clear',
      color: '#ee0a24',
      children: []
    };
    let result = []
    array.map(val => {
      if (val.lawType == 0) {
        vetoObj.children.push(val)
      } else if (val.lawType == 1) {
        addObj.children.push(val)
      } else {
        minusObj.children.push(val)
      }
    })
    result.push(addObj, minusObj, vetoObj)
    return result
  },
  formatMember(array) {
    let belong = [], allBelong = [], result = []
    array.forEach((val) => {
      belong.push(val.belong)
    })
    allBelong = Array.from(new Set(belong))
    allBelong.map(val => {
      let obj = {
        belong: val,
        children: []
      }
      array.map(i => {
        if (val == i.belong) {
          obj.children.push(i)
        }
      })
      result.push(obj)
    })
    return result
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
   * 每日评分Dialog状态切换
   */
  showScoreDialog(e) {
    this.setData({
      dialogVisible: true,
      memberName: e.target.dataset.name
    });
  },
  closeScoreDialog() {
    this.setData({ dialogVisible: false })
  },

  /**
   * 图片相关
   */
  afterPicRead(e) {
    let pictureList = this.data.pictureList
    if (!pictureList) {
      pictureList = []
    }
    pictureList = pictureList.concat(e.detail.file)
    this.setData({
      pictureList
    })
  },
  afterPicDel(e) {
    let pictureList = this.data.pictureList
    pictureList.splice(e.detail.index, 1)
    this.setData({
      pictureList
    })
  },

  /**
   * 每日评分提交
   */
  submitScore() {
    if (!this.data.scoreType) {
      wx.showToast({
        title: '请选择本次评分类别',
        icon: 'none'
      })
      return
    }

    if (!this.data.lawInfo) {
      wx.showToast({
        title: '请选择本次评分条例',
        icon: 'none'
      })
      return
    }

    if (this.data.pictureList.length == 0) {
      wx.showToast({
        title: '请上传一张本次评分的图片',
        icon: 'none'
      })
      return
    }
    console.log(this.data)
  },

  showModifyPopup(e) {
    this.setData({
      modifyPopup: true,
      modifyLawContent: e.target.dataset.content,
      modeifyLawScore: e.target.dataset.score,
      modeifyLawId: e.target.dataset.id
    })
  },

  modifyScore(event) {
    this.setData({ modeifyLawScore: event.detail })
  },

  /**
   * 修改小立法
   */
  handleModifyLaw() {
    console.log(this.data.modeifyLawId, this.data.modeifyLawScore, this.data.modifyLawContent)
    wx.showLoading({
      title: '',
    })
    wx.request({
      url: 'http://192.168.101.4:8090/api/law/update',
      method: 'POST',
      timeout: 10000,
      data: {
        lawId: this.data.modeifyLawId,
        lawScore: this.data.modeifyLawScore,
        lawContent: this.data.modifyLawContent
        // userId: wx.getStorageSync('key')
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
      url: 'http://192.168.101.4:8090/api/law/delete',
      method: 'POST',
      timeout: 10000,
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
   * 请求小立法
   */
  queryLaw() {
    let departmentId = 9
    wx.request({
      url: 'http://192.168.101.4:8090/api/law/list/' + departmentId,
      success: (res) => {
        let _data = res.data.result
        this.setData({
          lawsList: this.formatLaws(_data)
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // 请求数据
    // let departmentId = wx.getStorageSync('departmentId')
    this.queryLaw()
    this.setData({
      // lawsList: this.formatLaws(lawsData),
      membersList: this.formatMember(memberData)
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