<h1>QRCodeLogin</h1>
<p>二维码扫码登录；服务器端、网页端、移动端源码；</p>
<hr />
<h2>项目介绍</h2>
<ul>
	<li>一个二维码扫码登录的demo，能够完整的实现用户扫码登录的过程，<a href="https://github.com/HeyJC/QRCodeLogin">Github地址。</a></li>
	<li>项目一共包含三份源码：服务器端、网页端和移动端，分别放在根目录三个子文件夹下。</li>
</ul>
<h5>服务器端</h5>
<ul>
	<li>通过SpringBoot和Mybatis搭建而成，使用的是MySQL数据库。</li>
	<li>用于和网页端及移动端进行数据交互，主要功能包括二维码生成、用户地理位置获取等。</li>
</ul>
<h5>网页端</h5>
<ul>
	<li>基于Vue.js进行开发，项目通过vue-cli脚手架搭建而成，使用了Vue Router路由。</li>
	<li>主要的功能是二维码的展示，用户可以在网页端获取二维码，并在网页端进行登录。</li>
</ul>
<h5>移动端</h5>
<ul>
	<li>基于Android系统开发，使用了一些优秀的开源依赖，包括OkHttp、ZXing等</li>
	<li>主要用于解析二维码，用户在移动端登录账号后，扫描网页端的二维码可即在网页端进行登录。</li>
</ul>
<h2>效果展示</h2>
<h5>网页</h5>
<div align="center">
<img src="https://upload-images.jianshu.io/upload_images/15955542-2d7e66b3bd66e932.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240"
 width="600px">
<img src="https://upload-images.jianshu.io/upload_images/15955542-0f08978967ec2388.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240"
 width="600px">
 </div>
<h5>移动端</h5>
<div align="center">
<img src="https://upload-images.jianshu.io/upload_images/15955542-1aa884269d0fa3aa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240"
 width="200px" style="border: 1px solid #999999;">
<img src="https://upload-images.jianshu.io/upload_images/15955542-e75cb7be9c69c911.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240"
 width="200px" style="margin-left: 10px;border: 1px solid #999999;">
 </div>
