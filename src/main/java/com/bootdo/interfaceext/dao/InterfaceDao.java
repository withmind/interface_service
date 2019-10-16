package com.bootdo.interfaceext.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by zhaos on 2019/8/17.
 */
@Mapper
public interface InterfaceDao {

    List<Map<String,Object>> commentQuery(Map<String,Object> map);
}
