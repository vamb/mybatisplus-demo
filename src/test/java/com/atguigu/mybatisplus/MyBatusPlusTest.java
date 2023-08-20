package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyBatusPlusTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  public void testSelectList() {
    List<User> userList = userMapper.selectList(null);
    userList.forEach(System.out::println);
  }
}
