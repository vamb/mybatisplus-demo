package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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

  @Test
  public void testInsert() {
    User user = new User();
    user.setName("张三");
    user.setAge(12);
    user.setEmail("zhangsan@163.com");
    int rest = userMapper.insert(user);
    System.out.println("rest"+rest);
    System.out.println("user"+user.getId());
  }

  // 通过 ID 删除数据
  @Test
  public void testDeleteById() {
    userMapper.deleteById(0);
  }

  // 通过 Entity 的 ID 删除数据
  @Test
  public void testDeleteEntityId() {
    User user = new User();
    user.setId(0);
    userMapper.deleteById(user);
  }

  // 通过 List 来 batchDelete （批量删除）
  @Test
  public void testDeleteBatchIds() {
    List<Integer> list = new ArrayList<>();
    list.add(0);
    userMapper.deleteBatchIds(list);
  }


}
