package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
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
    User user = new User();
    user.setId(0L);
    user.setName("admin");
    user.setAge(22);
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

  // 通过 queryWrapper 来查询数据
  @Test
  public void testQueryWrapperSelect() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("name", "ad")
            .between("age", 20, 30)
            .isNotNull("email");
    List<User> list = userMapper.selectList(queryWrapper);
    list.forEach(System.out::println);
  }

  // 通过 queryWrapper 来更新数据
  @Test
  public void testQueryWrapperUpdate() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("name", "a").
            gt("age", 20)
            .or()
            .isNull("email");
    User user = new User();
    user.setAge(18);
    user.setEmail("userNewEmail@163.com");
    int result = userMapper.update(user, queryWrapper);
    System.out.println("受影响的行数" + result);
  }

  // 通过 queryWrapper 来查询特性的列的相关数据
  @Test
  public void testQueryWrapperSelectColumn() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("name", "age");
    List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
    maps.forEach(System.out::println);
  }

  // 通过在 queryWrapper 里面写 SQL 来实现查询
  @Test
  public void testQWInSql() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.inSql("id", "select id from user where id <= 3");
    List<User> list = userMapper.selectList(queryWrapper);
    list.forEach(System.out::println);
  }

  // 通过 updateWrapper 来实现对数据的修改
  @Test
  public void testUpdateWrapperLambda() {
    UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
    updateWrapper
            .set("age", 19)
            .set("email", "updateWrapper@163.com")
            .like("name", "a")
            .and(i -> i.gt("age", 20).or().isNull("email"));
    int result = userMapper.update(null, updateWrapper);
    System.out.println(result);
  }

  // 通过 queryMapper condition 条件判断 - 1
  // SELECT id,name,age,email,deleted FROM user WHERE deleted=0 AND (age >= ? AND age <= ?)
  @Test
  public void testQWConditionV1() {
    String name = null;
    Integer ageBegin = 20;
    Integer ageEnd = 23;

    QueryWrapper<User> queryWrapper = new QueryWrapper<>();

    if(StringUtils.isNotBlank(name)) {
      queryWrapper.like("name", "a");
    }

    if(ageBegin != null) {
      queryWrapper.ge("age", ageBegin);
    }

    if(ageEnd != null) {
      queryWrapper.le("age", ageEnd);
    }

    List<User> userList = userMapper.selectList(queryWrapper);
    userList.forEach(System.out::println);
  }

  // 通过 queryMapper condition 条件判断 - 1
  // SELECT id,name,age,email,deleted FROM user WHERE deleted=0 AND (age >= ? AND age <= ?)
  @Test
  public void testQWConditionV2() {
    String name = null;
    Integer ageBegin = 20;
    Integer ageEnd = 23;

    QueryWrapper<User> queryWrapper = new QueryWrapper<>();

    queryWrapper
            .like(StringUtils.isNotBlank(name), "name", "a")
            .ge(ageBegin != null, "age", ageBegin)
            .le(ageEnd != null, "age", ageEnd);

    List<User> userList = userMapper.selectList(queryWrapper);
    userList.forEach(System.out::println);
  }

  // 通过 lambada 表达式，实现条件查询
  // SELECT id,name,age,email,deleted FROM user WHERE deleted=0 AND (name LIKE ? AND age >= ? AND age <= ?)
  @Test
  public void testLambdaQWSelect() {
    String username = "a";
    Integer ageBegin = 10;
    Integer ageEnd = 24;

    LambdaQueryWrapper<User> lmQueryWrapper = new LambdaQueryWrapper<>();

    lmQueryWrapper
            .like(StringUtils.isNotBlank(username), User::getName, username)
            .ge(ageBegin != null, User::getAge, ageBegin)
            .le(ageEnd != null, User::getAge, ageEnd);

    List<User> users = userMapper.selectList(lmQueryWrapper);
    users.forEach(System.out::println);
  }

  // 通过 lambada 表达式，实现更新操作
  // UPDATE user SET age=?,email=? WHERE deleted=0 AND (name LIKE ? AND (age < ? OR email IS NULL))
  @Test
  public void testLambdaUpdateWrapper() {
    LambdaUpdateWrapper<User> lmUpdateWrapper = new LambdaUpdateWrapper<>();
    lmUpdateWrapper
            .set(User::getAge, 18)
            .set(User::getEmail, "lambdaUpdateWrapper@163.com")
            .like(User::getName, "a")
            .and(i -> i.lt(User::getAge, 24).or().isNull(User::getEmail));

    User user = new User();
    int result = userMapper.update(user, lmUpdateWrapper);
    System.out.println("受影响的行数：" + result);
  }


}
