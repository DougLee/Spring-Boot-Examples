package com.example.springbootshiro.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by Douglee on 2018/3/29.
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
