# QRCodeLogin
二维码扫码登录；服务器端、网页端、移动端源码；

----
## 项目介绍
- 一个二维码扫码登录的demo，能够完整的实现用户扫码登录的过程，[**源码地址**](https://github.com/HeyJC/QRCodeLogin)。
- 项目一共包含三分源码：**服务器端**、**网页端**和**移动端**，分别放在根目录的三个文件夹下。
- **项目网页端 [体验地址](http://github.trunch.cn/) 请戳，APP安装包 [文件地址](http://res.trunch.cn/auth/auth.apk) 或 [网盘地址](https://pan.baidu.com/s/1Q76TcKfwkvH2czYfTYrqJg) 请戳：**
> - 网页和APP默认登录账号：123456，密码：123456。
> - APP未针对Android 6.0以上的手机动态申请权限，所以安装后请前往设置手动开启。
> - 由于服务器性能较差，访问较慢，喜欢的话可以把源码克隆到本地运行。
#### 服务器端
- 用于和网页端及移动端进行数据交互，主要功能包括生成二维码、获取登录位置等。
- 项目通过SpringBoot和Mybatis搭建而成，使用的是MySQL数据库。
- 源码文件夹中包含了**sql文件**，运行前记得修改源码**yml配置**文件中的数据库连接。
#### 网页端
- 主要用于二维码的展示，用户可以在网页端获取二维码，以在网页端进行登录。
- 项目基于Vue.js进行开发，通过vue-cli脚手架搭建而成，使用了Vue Router路由。
- 源码中没有上传较大的**node_modules**文件夹，所以克隆到本地后记得先执行npm install或cnpm install命令进行下载。
- 当一切准备就绪时，记得将访问的接口地址改成自己本地服务器的**IP地址**（一般是localhost）。
#### 移动端
- 主要用于二维码的解析，用户在移动端登录账号后，扫描网页端的二维码即可进行登录。
- 项目基于Android系统开发，使用了一些优秀的开源依赖，包括OkHttp、ZXing等。
- 兼容Android4.0以上的版本，6.0以上运行前请前往设置手动开启**权限**。
- 本地运行时，记得改成自己服务器的**IP地址**（不是localhost，一般是局域网IP地址，输入ipconfig命令就可看到），具体修改位置是在项目源码http包 > ApiUtil类中。

---
## 效果展示
#### 网页端
- [动图请戳 >>>](https://upload-images.jianshu.io/upload_images/15955542-895dd1e14847420a.gif?imageMogr2/auto-orient/strip) 
<img src="https://github.com/HeyJC/Images/blob/master/web-loadcode.gif?raw=true" width="700" align=center />
- [动图请戳 >>>](https://upload-images.jianshu.io/upload_images/15955542-73b9422c0d2c63fd.gif?imageMogr2/auto-orient/strip) 
<img src="https://github.com/HeyJC/Images/blob/master/web-scancode.gif?raw=true" width="700" align=center />

---
#### 移动端
- [动图请戳 >>>](https://upload-images.jianshu.io/upload_images/15955542-ae1898781a012dfd.gif?imageMogr2/auto-orient/strip) 
<img src="https://github.com/HeyJC/Images/blob/master/app-scancode.gif?raw=true" width="250" align=center />
