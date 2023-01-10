const CONFIG = require('config.js')
// const AUTH = require('utils/auth')
// app.js
App({
  onLaunch() {
    const that = this;
    const updateManager = wx.getUpdateManager()
    updateManager.onUpdateReady(function () {
      wx.showModal({
        title: '更新提示',
        content: '新版本已经准备好，是否重启应用？',
        success(res) {
          if (res.confirm) {
            // 新的版本已经下载好，调用 applyUpdate 应用新版本并重启
            updateManager.applyUpdate()
          }
        }
      })
    });

    /**
     * 初次加载判断网络情况
     * 无网络状态下根据实际情况进行调整
     */
    wx.getNetworkType({
      success(res) {
        const networkType = res.networkType
        if (networkType === 'none') {
          that.globalData.isConnected = false
          wx.showToast({
            title: '当前无网络',
            icon: 'loading',
            duration: 2000
          })
        }
      }
    });

    /**
     * 监听网络状态变化
     * 可根据业务需求进行调整
     */
    wx.onNetworkStatusChange(function(res) {
      if (!res.isConnected) {
        that.globalData.isConnected = false
        wx.showToast({
          title: '网络已断开',
          icon: 'loading',
          duration: 2000
        })
      } else {
        that.globalData.isConnected = true
        wx.hideToast()
      }
    });
  },

  // 自动登录
  onShow() {
    // const session_key = wx.getStorageSync('session_key')
    // if (session_key == null || session_key == "") {
    //   let that = this
    //   wx.login({
    //     success: (res) => {
    //       if (res.code) {
    //         wx.request({
    //           url: 'http://192.168.101.2:80/api/user/WXLogin',
    //           data: {
    //             code: res.code
    //           },
    //           success: function(res) {
    //             let data = res.data.result
    //             that.globalData.session_key = data.session_key
    //             wx.setStorageSync('session_key', data.session_key)
    //           }
    //         })
    //       } else {
    //         console.log('获取用户登录状态失败!', res.errMsg)
    //       }
    //     },
    //   })
    // } else {
    //   this.globalData.session_key = session_key
    // }

    // AUTH.checkHasLogined().then(isLogined => {
    //   if (!isLogined) {
    //     AUTH.authorize().then( aaa => {
    //       AUTH.bindSeller()
    //     })
    //   } else {
    //     AUTH.bindSeller()
    //   }
    // })
  },

  globalData: {
    userInfo: null,
    session_key: ''
  }
})
