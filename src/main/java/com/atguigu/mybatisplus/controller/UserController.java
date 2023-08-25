package com.atguigu.mybatisplus.controller;

import com.atguigu.mybatisplus.pojo.User;
import com.atguigu.mybatisplus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@Validated
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/all")
  public List<User> allUsers() {
    return userService.list();
  }

}
