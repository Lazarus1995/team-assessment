const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return `${[year, month, day].map(formatNumber).join('/')} ${[hour, minute, second].map(formatNumber).join(':')}`
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : `0${n}`
}

const formatLaws = (array) => {
  let belong = [], allBelong = [], result = [], id = [], _id = []
  array.forEach((val) => {
    belong.push(val.belong)
    id.push(val.departmentId)
  })
  allBelong = Array.from(new Set(belong))
  _id = Array.from(new Set(id))
  allBelong.map((val, idx) => {
    let temp = [], obj = {
      departmentId: _id[idx],
      belong: val,
      children: []
    }
    array.map(i => {
      if (val == i.belong) {
        temp.push(i)
      }
    })
    obj.children = formatLawsResult(temp)
    result.push(obj)
  })
  return result
}

const formatLawsResult = (array) => {
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
}

const formatMember = (array) => {
  let belong = [], allBelong = [], result = []
  array.forEach((val) => {
    belong.push(val.departmentName)
  })
  allBelong = Array.from(new Set(belong))
  allBelong.map(val => {
    let obj = {
      belong: val,
      children: []
    }
    array.map(i => {
      if (val == i.departmentName) {
        obj.children.push(i)
      }
    })
    result.push(obj)
  })
  return result
}

const checkPhone = (phone) => {
  return /^1[3|4|5|6|7|8|9][0-9]\d{8}$/.test(phone)
}

module.exports = {
  formatTime,
  formatLaws,
  formatMember,
  checkPhone
}
