package com.bootdo.interfaceext.service;

import com.bootdo.interfaceext.vo.InterfaceVO;

import java.util.List;
import java.util.Map;

/**
 * Created by zhaos on 2019/8/15.
 */
public interface InterfaceService {

    public List<Map<String, Object>> list(InterfaceVO interfaceVO, Map<String, Object> params);
}
