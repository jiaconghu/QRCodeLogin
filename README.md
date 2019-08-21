# QRCodeLogin
二维码扫码登录；服务器端、网页端、移动端源码；

----
## 项目介绍
- 一个能够完整实现扫码登录的demo，客官喜欢请给个**Star**吧 >.< mua~，[**简书地址**](https://www.jianshu.com/p/d5a7a3081bbf)请戳。
- 本项目一共包含三份源码，即**服务器端**、**网页端**和**移动端**。
- **项目 [在线体验](http://github.trunch.cn/) 请戳，APP安装包 [本地下载](http://res.trunch.cn/auth/auth.apk) 或 [网盘下载](https://pan.baidu.com/s/1Q76TcKfwkvH2czYfTYrqJg) 请戳：**
> - 网页和APP默认登录账号：123456，密码：123456。
> - Android 6.0以上的手机使用APP时请前往设置开启权限。
##### 服务器端
- **说明：**项目通过SpringBoot搭建而成，配合Mybatis持久层框架，使用了MySQL数据库。
- **功能：**主要功能包括二维码图片生成、用户登录IP和地理位置获取等。
- **源码：**文件夹下包含了**sql文件**，运行前需要在项目的**yml配置文件**中改成自己的数据库连接。
##### 网页端
- **说明：**项目基于Vue.js进行开发，通过vue-cli脚手架搭建而成，使用了Vue Router路由。
- **功能：**主要用于二维码的获取、以及扫码登录过程中的动态效果的展示。
- **源码：**由于没有上传较大的**node_modules**文件夹，所以克隆到本地后要先进行下载；本地运行时，需要将访问的IP地址改成自己本地服务器的**IP地址**（127.0.0.1）。
##### 移动端
- **说明：**项目基于Android系统开发，使用了一些优秀的开源依赖，比如OkHttp、ZXing等。
- **功能：**主要功能包括二维码的扫描和解析、以及与账号登录相关的操作等。
- **源码：**项目没有针对Android6.0以上的版本**动态申请权限**；本地运行时，记得改成自己本地服务器的**IP地址**（不是127.0.0.1了，一般是局域网IP地址，DOS窗口输入ipconfig命令），具体要修改的文件是在项目源码http包 > ApiUtil类中。

---
## 效果展示
#### 网页端
> [动图请戳 >>>](https://upload-images.jianshu.io/upload_images/15955542-895dd1e14847420a.gif) 
<img src="https://github.com/HeyJC/Images/blob/master/web-loadcode.gif?raw=true" width="700" align=center />

---
> [动图请戳 >>>](https://upload-images.jianshu.io/upload_images/15955542-73b9422c0d2c63fd.gif) 
<img src="https://github.com/HeyJC/Images/blob/master/web-scancode.gif?raw=true" width="700" align=center />

---
#### 移动端
> [动图请戳 >>>](https://upload-images.jianshu.io/upload_images/15955542-ae1898781a012dfd.gif) 
<img src="https://github.com/HeyJC/Images/blob/master/app-scancode.gif?raw=true" width="250" align=center />
