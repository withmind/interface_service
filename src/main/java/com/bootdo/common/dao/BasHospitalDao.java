package com.bootdo.common.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by zhaos on 2019/8/18.
 */
@Mapper
public interface BasHospitalDao {
    public List<Map<String,Object>> queryHospital(String hospitalId);
}
