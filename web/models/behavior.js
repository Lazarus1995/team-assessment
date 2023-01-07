import { BehaviorWithStore } from 'mobx-miniprogram-bindings'
import { activeTab, user } from './index'

export const stoneBehavior = BehaviorWithStore({
  storeBindings: [
    {
      store: activeTab,
      fields: ["activeKey"],
      actions: ["updateActive"]
    },
    {
      store: user,
      fields: ["task", "message"],
      actions: ["updateTask", "updateMsg"],
    }
  ]
});