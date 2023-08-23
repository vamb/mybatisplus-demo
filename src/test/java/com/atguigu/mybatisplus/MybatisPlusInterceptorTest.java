package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisPlusInterceptorTest {

  @Autowired
  private UserMapper userMapper;

  // 分页查询数据
  // SELECT id,name,age,email,deleted FROM user WHERE deleted=0 LIMIT ?,?
  @Test
  public void testPagination() {
    Page<User> userPage = new Page<>(1, 5);

    userMapper.selectPage(userPage, null);

    List<User> userList = userPage.getRecords();
    userList.forEach(System.out::println);
    System.out.println("当前页：" + userPage.getCurrent());
    System.out.println("每页显示的条数：" + userPage.getSize());
    System.out.println("总记录数：" + userPage.getTotal());
    System.out.println("总页数：" + userPage.getPages());
    System.out.println("是否有上一页：" + userPage.hasPrevious());
    System.out.println("是否有下一页：" + userPage.hasNext());
  }

  // 通过 xml 自定义映射方法去查询user的pagination数据
  @Test
  public void testSelfPage() {
    Page<User> userPage = new Page<>(1, 5);
    userMapper.selectPageVo(userPage, 20);
    //获取分页数据
    List<User> list = userPage.getRecords();
    list.forEach(System.out::println);
    System.out.println("当前页："+userPage.getCurrent());
    System.out.println("每页显示的条数："+userPage.getSize());
    System.out.println("总记录数："+userPage.getTotal());
    System.out.println("总页数："+userPage.getPages());
    System.out.println("是否有上一页："+userPage.hasPrevious());
    System.out.println("是否有下一页："+userPage.hasNext());
  }
}
