package cn.trunch.auth.controller;

import cn.trunch.auth.util.QRCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "")
    public void generateQRCode4Product(HttpServletResponse response) {

    }
}
