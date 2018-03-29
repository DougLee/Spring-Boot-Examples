package com.example.springbootshiro.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by Douglee on 2018/3/29.
 */
public interface CommonMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
