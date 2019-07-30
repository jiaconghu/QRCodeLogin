# QRCodeLogin
二维码扫码登录；服务器端、网页端、移动端源码；
## 项目介绍
- 一个二维码扫码登录的demo，能够完整的实现用户扫码登录的过程，[Github地址](https://github.com/HeyJC/QRCodeLogin)
。
- 项目一共包含三份源码：服务器端、网页端和移动端，分别放在根目录三个子文件夹下。
##### 服务器端
- 通过SpringBoot和Mybatis搭建而成，使用了MySQL数据库。
- 用于和网页端及移动端进行数据交互，功能主要包括生成二维码、获得用户登录地理位置、向网页端和移动端提供必要的数据等。
##### 网页端
- 基于Vue.js进行开发，项目通过vue-cli脚手架搭建，使用了Vue Router路由。
- 主要向用户展示二维码，用户可以在网页端获取二维码，并在网页端进行登录。
##### 移动端
- 主要用于解析二维码，用户在移动端登录账号后，扫描网页端的二维码可即在网页端进行登录。
- 移动端基于Android开发而成，使用了一些的开源依赖，包括OkHttp等。
## 效果图
##### 网页端
![](https://upload-images.jianshu.io/upload_images/15955542-2d7e66b3bd66e932.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/15955542-0f08978967ec2388.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
##### 移动端
![](https://upload-images.jianshu.io/upload_images/15955542-e75cb7be9c69c911.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/15955542-1aa884269d0fa3aa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



