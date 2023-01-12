import { configure } from 'mobx-miniprogram'
export { activeTab } from './activeTab'
export { userInfo } from './userInfo'

configure({ enforceActions: "observed" });