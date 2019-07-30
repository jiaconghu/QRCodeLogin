package cn.trunch.auth.http;


public class ApiUtil {
    public static String USER_ID = ""; // 用户ID
    public static String USER_TOKEN = ""; // 用户TOKEN
    public static String USER_AVATAR = ""; // 用户头像
    public static String USER_NAME = ""; // 用户昵称


    public final static String IP_PORT = "http://192.168.137.1/";
    public final static String LOGIN = IP_PORT + "login";
    public final static String TOKEN_INFO = IP_PORT + "auth/info/";
    public final static String TOKEN_USE = IP_PORT + "auth/use/";
}
