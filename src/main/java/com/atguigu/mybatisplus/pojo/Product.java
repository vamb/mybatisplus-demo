package com.atguigu.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  private Long id;

  private String name;

  private Integer price;

  // 用于乐观锁的字段
  @Version
  private Integer version;
}
