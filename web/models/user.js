import { observable, action } from "mobx-miniprogram";

export const user = observable({
  task: 3,
  message: 1,

  updateTask: action(function () {
    this.task -= 1
  }),

  updateMsg: action(function() {
    this.message -= 1
  })
});
