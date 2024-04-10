const request = require('../../api/request')
Page({

    /**
     * 页面的初始数据
     */
    data: {
        nickname: '',
        tel: '',
        userList: []
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        // 获取本地存储数据
        var user = wx.getStorageSync('user');
        this.setData({
            nickname: user.nickname,
            tel: user.tel
        })
        // 获取所有用户信息
        // request.post('/user/getAllUser').then(res => {
        //     if (res.data.success) {
        //         this.setData({
        //             userList : res.data.result
        //         })
        //         console.log(this.data.userList);
        //     }
        // })
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    }
})