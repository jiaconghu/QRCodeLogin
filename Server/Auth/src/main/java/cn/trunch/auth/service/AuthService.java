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
        // 通过UUID生成随机的token
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        // 通过IPUtil获取客户端的真实IP地址
        String ip = IPUtil.getIpAddress(request);
        // 本地测试的时候,上面获取的IP为127.0.0.1
        // 所以无法通过局域网获得地理位置，因此手动改为外网IP
        String fakeip = "49.74.160.84";
        // 通过获取到的客户端IP地址确定客户端所在地理位置
        String address;

        // 使用baidu接口获取IP地址
        String url = "http://api.map.baidu.com/location/ip?ip=" + fakeip + "&ak=nSxiPohfziUaCuONe4ViUP2N&coor=bd09ll";
        // 通过Http工具访问接口
        String result = HttpUtil.doGet(url);
        // 对返回的数据进行解析
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject != null) {
            Integer status = jsonObject.getInteger("status");
            if (status == 0) {
                address = jsonObject.getJSONObject("content").getString("address");
                System.out.println(address);
            } else {
                address = "江苏省南京市"; // 解析失败，默认地址
            }
        } else {
            address = "江苏省南京市"; // 获取baidu接口数据失败
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
            return new Message(201, "获取口令信息失败", new Auth());
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
        HashMap<String, Integer> hashMap = new HashMap<>();
        if (userId != null && (state == 0 || state == 2)) { // token状态为0，等待验证
            // TODO 要判断token的时间是否已经过期，可以通过时间戳相减获得
            System.out.println("===" + (System.currentTimeMillis() - auth.getAuthTime().getTime()));
            authDao.setAuthState(authToken, 1, userId);
            message.setCode(200);
            message.setMessage("使用口令成功");
            hashMap.put("state", 1);
        } else { // token状态为1或3，失效
            message.setCode(201);
            message.setMessage("使用口令失败");
            hashMap.put("state", 0);
        }
        message.setData(hashMap);
        return message;
    }
}
