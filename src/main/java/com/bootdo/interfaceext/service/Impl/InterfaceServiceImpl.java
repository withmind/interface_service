package com.bootdo.interfaceext.service.Impl;

import com.bootdo.interfaceext.dao.InterfaceDao;
import com.bootdo.interfaceext.service.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.interfaceext.vo.InterfaceVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaos on 2019/8/15.
 */
@Service
public class InterfaceServiceImpl implements InterfaceService {

    @Autowired
    private InterfaceDao interfaceDao;

    public List<Map<String, Object>> list(InterfaceVO interfaceVo, Map<String, Object> params){

        String hospitalid = interfaceVo.getHospitalId();
        String action = interfaceVo.getAction();
        List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
        params.put("action",action);
        result = interfaceDao.commentQuery(params);
        return result;
    }
}
