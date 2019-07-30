package cn.trunch.auth.dao;

import cn.trunch.auth.entity.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
    @Select("select user_password from user where user_id = #{userId}")
    String getUserPassword(String userId);

    @Select("SELECT * FROM user WHERE user_id = #{userId}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userPassword", column = "user_password"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userAvatar", column = "user_avatar"),
            @Result(property = "userPhone", column = "user_phone")
    })
    User getUserInfo(String userId);
}
