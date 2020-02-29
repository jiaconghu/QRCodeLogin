# QRCodeLogin
二维码扫码登录；服务器端、网页前端、移动端源码；

----
## 项目介绍
- 一个能够实现扫码登录的demo，不妨小小**Star**一下吧 >.< ~，[**简书地址**](https://www.jianshu.com/p/d5a7a3081bbf)请戳。
- 本项目一共包含三份源码，即**服务器端**、**网页前端**和**移动端**。
- **项目 [~~在线体验~~](http://github.trunch.cn/) 请戳，APP安装包 [~~本地下载~~](http://res.trunch.cn/auth/auth.apk) 或 [网盘下载](https://pan.baidu.com/s/1Q76TcKfwkvH2czYfTYrqJg) 请戳：**
> - 网页和APP默认登录账号：123456，密码：123456。
> - Android 6.0以上的手机使用APP时请前往设置开启权限。
#### 服务器端
- **说明：** 项目通过SpringBoot搭建而成，采用了Mybatis持久层框架，数据库使用的是MySQL。
- **功能：** 主要功能包括生成二维码图片、获取用户登录的IP地址和地理位置（通过百度提供的接口进行解析）等。
- **源码：** **对应Server文件夹**，里面包含了**sql文件**。运行前需要在源码的**yml配置文件**中修改成自己本地的数据库。
#### 网页前端
- **说明：** 项目基于Vue.js开发，借助vue-cli脚手架进行搭建，使用了Vue Router、axios等。
- **功能：** 主要用于二维码的获取、以及扫码登录过程中的动态效果展示。
- **源码：** **对应Web文件夹**，由于没有上传较大的**node_modules**，所以克隆到本地后要先进行下载。运行前需要将访问的**IP地址**改成自己本地服务器的IP地址。
#### 移动端
- **说明：** 项目基于Android系统开发，使用了一些优秀的开源依赖，比如OkHttp、ZXing等。
- **功能：** 主要功能包括二维码的扫描和解析、以及和账号登录有关的功能等。
- **源码：** **对应Android文件夹**，源码没有针对Android6.0以上的版本**动态申请权限**。运行前记得改成自己本地服务器的**IP地址**（不是127.0.0.1了，一般是计算机在局域网的IP地址，DOS窗口输入ipconfig命令），对应要修改的文件是在源码http包的ApiUtil类中。

---
## 效果展示
#### 网页前端
> [动图请戳 >>>](https://upload-images.jianshu.io/upload_images/15955542-895dd1e14847420a.gif) 
<img src="https://github.com/HeyJC/QRCodeLogin/blob/master/Images/web-loadcode.gif?raw=true" width="700" align=center />

---
> [动图请戳 >>>](https://upload-images.jianshu.io/upload_images/15955542-73b9422c0d2c63fd.gif) 
<img src="https://github.com/HeyJC/QRCodeLogin/blob/master/Images/web-scancode.gif?raw=true" width="700" align=center />

---
#### 移动端
> [动图请戳 >>>](https://upload-images.jianshu.io/upload_images/15955542-ae1898781a012dfd.gif) 
<img src="https://github.com/HeyJC/QRCodeLogin/blob/master/Images/app-scancode.gif?raw=true" width="250" align=center />
