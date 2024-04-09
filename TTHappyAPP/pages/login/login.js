// pages/login/login.js
const request = require('../../api/request')
Page({

    /**
     * 页面的初始数据
     */
    data: {
        //用户需要提交的数据
        form: {
            tel: "", // 手机号
            password: ""
        }
    },
    toRegister: function () {
        wx.navigateTo({
            url: "/pages/register/register"
        })
    },
    formSubmit(e) {
        let tel = e.detail.value.tel
        let password = e.detail.value.password
        if (!tel || !password) {
            wx.showToast({
                title: '请填写完整',
                icon: 'error',
                duration: 1000
            })
        } else {
            //将前端数据放在data里面
            this.setData({
                //存入手机号
                'form.tel': e.detail.value.tel,
                //存入手机号
                'form.password': e.detail.value.password
            })
            let data = {
                "tel":this.data.form.tel,
                "password":this.data.form.password
            }
            request.post('/user/login',data).then(res=>{
                if (res.data.success == true) {
                    console.log(res.data);
                    wx.switchTab({
                        url: '/pages/index/index',
                    })
                }else{
                    console.log(res.data);
                    wx.showToast({
                        title: res.data.message,
                        icon: 'none',
                        duration: 1000
                    })
                }
              })
        }

    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {

    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom() {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {

    }
})