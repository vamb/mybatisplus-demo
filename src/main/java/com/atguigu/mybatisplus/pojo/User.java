package com.atguigu.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@TableName("user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private long id;
  private String name;
  private Integer age;
  private String email;

}
