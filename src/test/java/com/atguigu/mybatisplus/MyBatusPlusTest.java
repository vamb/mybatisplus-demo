package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class MyBatusPlusTest {

  @Autowired
  private UserMapper userMapper;

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

  //通过多个id批量删除
  @Test
  public void testDeleteBatchIds(){
  //DELETE FROM user WHERE id IN ( ? , ? , ? )
    List<Long> idList = Arrays.asList(0L);
    int result = userMapper.deleteBatchIds(idList);
    System.out.println("受影响行数："+result);
  }

  //根据map集合中所设置的条件删除记录
  @Test
  public void testDeleteByMap() {
    //DELETE FROM user WHERE name = ? AND age = ?
    Map<String, Object> map = new HashMap<>();
    map.put("age", 12);
    map.put("name", "张三");
    int result = userMapper.deleteByMap(map);
    System.out.println("受影响行数："+result);
  }

  // 通过 entity 并通过 entity 的 ID 来修改数据
  @Test
  public void testUpdateById() {
    User user = new User(0L, "admin", 22, null);
    //UPDATE user SET name=?, age=? WHERE id=?
    int result = userMapper.updateById(user);
    System.out.println("受影响行数："+result);
  }


  // 通过 ID 查询用户数据
  @Test
  public void testSelectById() {
    User user = userMapper.selectById(0L);
    System.out.println(user);
  }

  // 通过　ID List　查询数据
  @Test
  public void testSelectBatchIds() {
    List<Long> idList = Arrays.asList(0L, 1L, 2L);
    List<User> userList = userMapper.selectBatchIds(idList);
    System.out.println(userList);
  }
  // 通过map条件查询用户信息
  @Test
  public void testSelectByMap(){
  //SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
    Map<String, Object> map = new HashMap<>();
    map.put("age", 22);
    map.put("name", "admin");
    List<User> list = userMapper.selectByMap(map);
    list.forEach(System.out::println);
  }

  // 查询所有数据
  @Test
  public void testSelectList() {
    List<User> userList = userMapper.selectList(null);
    userList.forEach(System.out::println);
  }


}
