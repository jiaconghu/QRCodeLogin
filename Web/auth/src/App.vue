<template>
  <div id="app">
    <div class="main_body">
      <div class="login_box">
        <div class="login_close"></div>
        <router-view></router-view>
      </div>
      <p class="power_by">© 2019 - 2019 Trunch All Rights Reserved</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App'
}

</script>

<style>
  * {
    margin: 0;
    padding: 0;
  }

  body {
    background: url("./assets/img/mainbg.jpg") no-repeat 50%;
    background-size: cover;
  }

  body,
  html {
    height: 100%;
  }

  .login_box {
    position: absolute;
    width: 380px;
    height: 540px;
    top: 50%;
    left: 50%;
    margin-left: -190px;
    margin-top: -270px;
    border-radius: 6px;
    background-color: #fff;
    box-shadow: 0 2px 10px #999;
  }

  .login_close {
    position: absolute;
    top: 0;
    right: 0;
    width: 42px;
    height: 52px;
    background: url(./assets/img/close.png) no-repeat right top;
    background-size: 100% 100%;
    cursor: hand;
  }

  .power_by {
    position: absolute;
    bottom: 60px;
    right: 60px;
    color: #d3d3d3;
    font-size: 12px;
    line-height: 1.6;
  }
</style>
