package cn.trunch.auth.service;

import cn.trunch.auth.dao.AuthDao;
import cn.trunch.auth.entity.Auth;
import cn.trunch.auth.entity.Message;
import cn.trunch.auth.util.HttpUtil;
import cn.trunch.auth.util.IPUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.UUID;

@Service
public class AuthService {

    @Resource
    AuthDao authDao;

    public Message addAuthInfo(HttpServletRequest request) {
        // 通过UUID生成随机token
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        // 通过IPUtil获取客户端的真实IP地址
        String ip = IPUtil.getIpAddress(request);
        // 通过客户端IP地址确定客户端所在位置
        String address;
        String url = "http://api.map.baidu.com/location/ip?ip=49.74.160.84&ak=nSxiPohfziUaCuONe4ViUP2N&coor=bd09ll";
        String result = HttpUtil.doGet(url);
        System.out.println(result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        Integer status = jsonObject.getInteger("status");
        if (status != 0) {
            //本地测试的时候IP为127.0.0.1，无法通过局域网获得地理位置，所以这里默认为测试所在地
            address = "江苏省南京市";
        } else {
            address = jsonObject.getJSONObject("content").getString("address");
            System.out.println(address);
        }
        // 将token相关信息存入数据库中
        authDao.addAuthInfo(token, ip, address);
        // 将token返回给客户端
        return new Message(200, "获取口令成功", token);
    }

    public Message getAuthInfo(String authToken, String userId, boolean isScan) {
        Auth auth = authDao.getAuthInfo(authToken);
        // 为空则获取信息失败
        if (auth == null) {
            return new Message(200, "获取口令信息失败", new Auth());
        }
        //手机端访问，如果token等待验证或正在验证，则将token的state和userId更新
        if (isScan && (auth.getAuthState() == 0 || auth.getAuthState() == 2)) {
            authDao.setAuthState(authToken, 2, userId);
        }
        return new Message(200, "获取口令信息成功", auth);
    }

    public Message setAuthState(String authToken, String userId) {
        //tokenState：0等待验证，1验证成功，2正在验证，3验证失败（过期）
        Integer state = 3; // 默认token为3，不存在
        Auth auth = authDao.getAuthInfo(authToken);
        if (null != auth) {
            state = auth.getAuthState(); // 获得token的状态
        }
        Message message = new Message();
        message.setCode(200);
        message.setMessage("使用口令成功");
        HashMap<String, Integer> hashMap = new HashMap<>();
        if (userId != null && (state == 0 || state == 2)) { // token状态为0，等待验证
            // TODO 要判断token的时间是否已经过期，可以通过时间戳相减获得
            System.out.println("===" + (System.currentTimeMillis() - auth.getAuthTime().getTime()));
            authDao.setAuthState(authToken, 1, userId);
            hashMap.put("state", 1);
        } else { // token状态为1或3，失效
            hashMap.put("state", 0);
        }
        message.setData(hashMap);
        return message;
    }
}
