// pages/team/team.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
<<<<<<< HEAD
    swiperList: ['red', 'yellow', 'blue'],
    teamStructure: {
      name: "二建分",
      children: [
        {
          name: "15号线",
          "children": [

          ]
        }, {
          name: "7号线",
          children: [
            {
              name: ""
            }
          ]
        }
      ]
    }
  },
=======
    treeList: {
      departmentName: '一标段',
      id: 0,
      children: [
        {
          "id": 9,
          "departmentName": "一工区",
          "parentId": 4,
          "children": [
            {
              "id": 14,
              "departmentName": "始发井工点",
              "parentId": 9,
              "children": [
                {
                  "id": 18,
                  "departmentName": "开挖支护班组",
                  "parentId": 14,
                  "children": null,
                  "leaf": true
                },
                {
                  "id": 19,
                  "departmentName": "文明施工班组",
                  "parentId": 14,
                  "children": null,
                  "leaf": true
                }
              ],
              "leaf": false
            },
            {
              "id": 15,
              "departmentName": "富锦路站工点",
              "parentId": 9,
              "children": [
                {
                  "id": 20,
                  "departmentName": "维护结构班组",
                  "parentId": 15,
                  "children": null,
                  "leaf": true
                },
                {
                  "id": 21,
                  "departmentName": "开挖支护班组",
                  "parentId": 15,
                  "children": null,
                  "leaf": true
                },
                {
                  "id": 22,
                  "departmentName": "文明施工班组",
                  "parentId": 15,
                  "children": null,
                  "leaf": true
                }
              ],
              "leaf": false
            },
            {
              "id": 16,
              "departmentName": "丹山南站工点",
              "parentId": 9,
              "children": null,
              "leaf": true
            },
            {
              "id": 17,
              "departmentName": "数字化钢筋加工厂",
              "parentId": 9,
              "children": [
                {
                  "id": 23,
                  "departmentName": "数字化钢筋加工班组",
                  "parentId": 17,
                  "children": null,
                  "leaf": true
                }
              ],
              "leaf": false
            }
          ],
          "leaf": false
        },
        {
          "id": 10,
          "departmentName": "二工区",
          "parentId": 4,
          "children": null,
          "leaf": true
        },
        {
          "id": 11,
          "departmentName": "三工区",
          "parentId": 4,
          "children": null,
          "leaf": true
        },
        {
          "id": 12,
          "departmentName": "四工区",
          "parentId": 4,
          "children": null,
          "leaf": true
        },
        {
          "id": 13,
          "departmentName": "五工区",
          "parentId": 4,
          "children": null,
          "leaf": true
        }
      ]
    },
  },

>>>>>>> 42743731fd91d5b167efb6adc6b239b05a3aada4
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