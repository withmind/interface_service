package com.bootdo.common.service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhaos on 2019/8/18.
 */
public interface BasHospitalService {
    public List<Map<String,Object>> queryHospital(String hospitalId);
}
