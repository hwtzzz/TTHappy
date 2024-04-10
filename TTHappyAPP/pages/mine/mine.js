// pages/mine/mine.js
Page({
    creation:function() {
        wx.switchTab({
            url: '/pages/index/index',
        })
    },
    revise:function() {
        wx.switchTab({
            url: '/pages/index/index',
        })
    },
    clean:function() {
        wx.showToast({
            title: "缓存清除成功!",
            icon: 'none',
            duration: 1500
        })
    },
    update:function() {
        wx.showToast({
            title: "已经是最高版本!",
            icon: 'none',
            duration: 1500
        })
    },
    about:function() {
        wx.showToast({
            title: "TTHappy超级有趣的兴趣社区",
            icon: 'none',
            duration: 2000
        })
    },

    /**
     * 页面的初始数据
     */
    data: {

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