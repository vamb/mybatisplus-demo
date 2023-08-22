package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisplusServiceTest {

  @Autowired
  private UserService userService;

  @Test
  public void testCount() {
    System.out.println("user count" + userService.count());
  }
}
