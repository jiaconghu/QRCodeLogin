package cn.trunch.auth.dao;

import cn.trunch.auth.entity.Auth;
import org.apache.ibatis.annotations.*;

public interface AuthDao {
    @Insert("INSERT INTO auth (auth_token, auth_ip, auth_address)" +
            "VALUES (#{authToken}, #{authIp}, #{authAddress})")
    Integer addAuthInfo(String authToken, String authIp, String authAddress);

    @Select("SELECT * FROM auth WHERE auth_token = #{authToken}")
    @Results({
            @Result(property = "authToken", column = "auth_token"),
            @Result(property = "authTime", column = "auth_time"),
            @Result(property = "authIp", column = "auth_ip"),
            @Result(property = "authAddress", column = "auth_address"),
            @Result(property = "authState", column = "auth_state"),
            @Result(property = "userId", column = "user_id")
    })
    Auth getAuthInfo(String authToken);

    @Update("UPDATE auth SET auth_state = #{authState}, user_id = #{userId} WHERE auth_token = #{authToken}")
    Integer setAuthState(String authToken, Integer authState, String userId);
}
