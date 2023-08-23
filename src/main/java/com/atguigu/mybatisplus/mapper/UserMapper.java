package com.atguigu.mybatisplus.mapper;

import com.atguigu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

  /**
   * 通过年龄查询用户信息并且分页
   * @param page MybatisPlus 所提供的分页对象，必须位于第一个参数的位置
   * @param age 年龄限制条件
   * @return
   */
  Page<User> selectPageVo(@Param("page") Page<User> page, @Param("age") Integer age);
}
