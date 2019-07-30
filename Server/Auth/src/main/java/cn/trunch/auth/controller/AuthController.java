package cn.trunch.auth.controller;

import cn.trunch.auth.entity.Message;
import cn.trunch.auth.service.AuthService;
import cn.trunch.auth.util.QRCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "auth")
public class AuthController {

    @Autowired
    AuthService authService;

    // 客户端获取token
    @RequestMapping(value = "/token")
    @ResponseBody
    public Message getToken(HttpServletRequest request) {
        return authService.addAuthInfo(request);
    }

    //token对应的二维码图像
    @RequestMapping(value = "/img/{token}")
    public void getQRCodeImg(@PathVariable("token") String token, HttpServletResponse response) {
        try {
            // 传入图像链接，还能够生成带logo的二维码
            QRCodeUtil.encode(token, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //客户端和手机端获取token相关信息
    @RequestMapping(value = "/info/{token}")
    @ResponseBody
    public Message infoToken(@PathVariable("token") String token, String userId, boolean isScan) {
        return authService.getAuthInfo(token, userId, isScan);
    }

    // 手机端使用token进行验证
    @RequestMapping(value = "/use/{token}")
    @ResponseBody
    public Message useToken(@PathVariable("token") String token, String userId) {
        return authService.setAuthState(token, userId);
    }
}
