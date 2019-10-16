package com.bootdo.common.service.impl;

import com.bootdo.common.dao.BasHospitalDao;
import com.bootdo.common.service.BasHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaos on 2019/8/18.
 */
@Service
public class BasHospitalServiceImpl implements BasHospitalService {

    @Autowired
    private BasHospitalDao basHospitaleDao;

    public List<Map<String,Object>> queryHospital(String hospitalId){
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        result = basHospitaleDao.queryHospital(hospitalId);
        return result;
    }
}
