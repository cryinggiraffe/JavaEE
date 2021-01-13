package com.example.ssm_docker.mapper;
import com.example.ssm_docker.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from t_user where id=#{id}")
    public User getUser(Integer id);

    @Insert("insert into t_user (lastName, email) values (#{lastName}, #{email})")
    public void addUser(@Param("lastName")String lastName, @Param("email")String email);
}
