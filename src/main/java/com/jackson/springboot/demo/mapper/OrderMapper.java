package com.jackson.springboot.demo.mapper;

import com.jackson.springboot.demo.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhoujunhui
 * @description: TODO
 * @date 2021/5/26 10:17
 */
public interface OrderMapper {
    @Select("<script>select id, task_id as taskId,version  , name,create_time AS createTime from t_order </script>")
    List<Order> selectAll();
    @Select("<script>select id, task_id as taskId,version  , name,create_time AS createTime  from t_order where id = #{id} </script>")
    Order selectById(@Param("id") Long id);

    @Select("<script>select id, task_id as taskId,version  , name,create_time AS createTime  from t_order where task_id = #{taskId} </script>")
    Order selectByTaskId(@Param("taskId") Long taskId);

    @Insert("<script>insert into t_order (id,task_id,version,name,create_time) values(#{id},#{taskId},#{version},#{name},#{createTime})</script>")
    int insertOne(Order order);
}
