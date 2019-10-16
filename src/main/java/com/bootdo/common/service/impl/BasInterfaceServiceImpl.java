package com.bootdo.common.service.impl;

import com.bootdo.common.dao.BasInterfaceDao;
import com.bootdo.common.service.BasInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaos on 2019/8/18.
 */
@Service
public class BasInterfaceServiceImpl implements BasInterfaceService {
    @Autowired
    private BasInterfaceDao basInterfaceDao;

    @Override
    public List<Map<String,Object>> queryInterface(String action) {
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        result = basInterfaceDao.queryInterface(action);
        return result;
    }

}
