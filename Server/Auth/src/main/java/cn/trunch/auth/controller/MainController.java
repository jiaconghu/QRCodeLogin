package cn.trunch.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class MainController {

    @RequestMapping(value = "")
    @ResponseBody
    public String main() {
        return "This is TRUNCH";
    }
}
