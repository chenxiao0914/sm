package cn.com.mapper;

import org.apache.ibatis.annotations.Select;

import cn.com.pojo.User;

public interface UserMapper {
	@Select("select * from user where username=#{username} and password=#{password}")
	User selOne(User user);
}
