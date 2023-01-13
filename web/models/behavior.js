import { BehaviorWithStore } from 'mobx-miniprogram-bindings'
import { activeTab, userInfo } from './index'

export const stoneBehavior = BehaviorWithStore({
  storeBindings: [
    {
      store: activeTab,
      fields: ["activeKey"],
      actions: ["updateActive"]
    },
    {
      store: userInfo,
      fields: ["userName", "userId", "departmentId", "departmentName", "phoneNumber", "avatarUrl", "taskNum", "messageNum"],
      actions: ["updateUserName", "updateId", "updateDepartment", "updateDepartmentName", "updatePhone", "updateAvatar", "updateTask", "updateMsg"],
    }
  ]
});