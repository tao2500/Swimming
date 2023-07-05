// 时间戳节流
export const throttle = (fun, time = 3000) => {
  let lastTime = 0
  return function () {
    let _this = this
    let _arguments = arguments
    let now = new Date().getTime()
    if (now - lastTime > time) {
      fun.apply(_this, _arguments)
      lastTime = now
    }
  }
}
