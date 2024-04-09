let base = 'http://127.0.0.1:8888/api'

function fetchData(url,data,method="GET"){
  return new Promise((resolve,reject)=>{
    wx.request({
      url: base+url,
      method,
      header:{
        // 'X-LC-Id': '自己的id', 
        // 'X-LC-Key': ' 自己的key', 
        'Content-Type': ' application/json'
      },
      data,
      success:(res)=>{
        resolve(res)    //此处的res会交给then
      },
      fail:(err)=>{
        reject(err)  //此处的err会交给catch
      }
    })
  })
}

function post(url,data={}){
  return fetchData(url,data,'POST')
}

function get(url,data={}){
  return fetchData(url,data,'GET')
}

module.exports = {
  fetchData,
  post,
  get
}