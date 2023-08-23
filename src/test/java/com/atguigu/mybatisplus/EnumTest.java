package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.enums.SexEnum;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EnumTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  public void testSexEnum() {
    User user = new User();
    user.setName("Enum");
    user.setAge(22);
    user.setSex(SexEnum.MALE);
    userMapper.insert(user);
  }
}
