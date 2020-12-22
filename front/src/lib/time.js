export default {
  getDayDistance (timeA, timeB) {
    const millSeconds = timeB.getTime() - timeA.getTime()
    return millSeconds / 24 / 60 / 60 / 1000
  },
  advanceDays (time, day) {
    const totSeconds = time.getTime() + day * 24 * 60 * 1000
    return new Date(totSeconds)
  }
}
