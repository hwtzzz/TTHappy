const app = getApp()
const defaultAvatarUrl = 'https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0'
const request = require('../../api/request')


Page({
    /**
     * 页面的初始数据
     */
    data: {
        avatarUrl: defaultAvatarUrl,
        theme: wx.getSystemInfoSync().theme,
        //用户需要提交的数据
        form: {
            nickname: "", // 用户昵称
            imgBase64: defaultAvatarUrl, // 设置一个默认头像
            tel: "", // 手机号
            password: ""
        }
    },
    toLogin:function() {
        wx.navigateTo({
            url: "/pages/login/login"
        })
    },
    onChooseAvatar(e) {
        const {
            avatarUrl
        } = e.detail

        this.setData({
            avatarUrl,
        })
        this.base64(avatarUrl, "png").then(res => {
            this.setData({
                'form.imgBase64': res
            })
        })
        console.log(this.data.form.imgBase64);
    },
    // 图片转64
    base64(url, type) {
        return new Promise((resolve, reject) => {
            wx.getFileSystemManager().readFile({
                filePath: url, //选择图片返回的相对路径
                encoding: 'base64', //编码格式
                success: res => {
                    // resolve('data:image/' + type.toLocaleLowerCase() + ';base64,' + res.data)
                    resolve(res.data)
                },
                fail: res => reject(res.errMsg)
            })
        })
    },
    formSubmit(e) {
        //判断是否留空
        if (!e.detail.value.nickname || !e.detail.value.tel || !e.detail.value.repassword || !e.detail.value.password) {
            wx.showToast({
                title: '请填写完整',
                icon: 'error',
                duration: 1000
            })
        }
        //判断昵称长度
        if (e.detail.value.nickname.length > 15 || e.detail.value.nickname.length < 3) {
            wx.showToast({
                title: '昵称3~15位',
                icon: 'error',
                duration: 1000
            })
        }
        //判断手机号长度
        if (e.detail.value.tel.length > 11 || e.detail.value.tel.length < 7) {
            wx.showToast({
                title: '手机号7~11位',
                icon: 'error',
                duration: 1000
            })
        }
        //只需要判断一个密码即可
        if (e.detail.value.password.length > 15 || e.detail.value.password.length < 6) {
            wx.showToast({
                title: '密码7~11位',
                icon: 'error',
                duration: 1000
            })
        }
        //判断密码是否一致
        if (e.detail.value.repassword != e.detail.value.password) {
            wx.showToast({
                title: '密码不一致',
                icon: 'error',
                duration: 1000
            })
        }
        //将前端数据放在data里面
        this.setData({
            //存入昵称
            'form.nickname': e.detail.value.nickname,
            //存入手机号
            'form.tel': e.detail.value.tel,
            //存入手机号
            'form.password': e.detail.value.password
        })
        let data = {
            'nickname': this.data.form.nickname,
            'tel': this.data.form.tel,
            'password': this.data.form.password,
            'avatar': this.data.form.imgBase64
        }
        //登录后端传值
        request.post('/user/register', data).then(res => {
            if (res.data.success) {
                //成功跳转到登录页面
                wx.navigateTo({
                    url: '/pages/login/login',
                })
            }else{
                wx.showToast({
                    title: res.data.message,
                    icon: 'none',
                    duration: 1000
                })
            }
        })

    },


    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        wx.onThemeChange((result) => {
            this.setData({
                theme: result.theme
            })
        })
    }
})