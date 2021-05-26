package com.jackson.springboot.demo.mapper;

import com.jackson.springboot.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhoujunhui
 * @description: TODO
 * @date 2021/5/10 15:50
 */
public interface UserMapper {

    @Select("<script>select id, name , create_time AS createTime from t_user </script>")
    List<User> selectAll();
    @Select("<script>select id, name , create_time AS createTime  from t_user where id = #{id} </script>")
    User selectById(@Param("id") Long id);
}
