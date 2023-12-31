package com.atguigu.mybatisplus.pojo;

import com.atguigu.mybatisplus.enums.SexEnum;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@TableName("user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

  private long id;

  @TableField("name")
  private String name;

  private SexEnum sex;

  private Integer age;

  private String email;

  @TableLogic
  private Integer deleted;

}
