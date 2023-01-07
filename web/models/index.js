import { configure } from 'mobx-miniprogram'
export { activeTab } from './activeTab'
export { user } from './user'

configure({ enforceActions: "observed" });