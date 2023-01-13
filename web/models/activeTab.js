import { observable, action } from 'mobx-miniprogram'

export const activeTab = observable({
  activeKey: 0,
  updateActive: action(function(tabKey) {
    this.activeKey = tabKey
  })
})