package com.bootdo.interfaceext.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.service.BasHospitalService;
import com.bootdo.common.service.BasInterfaceService;
import com.bootdo.common.service.LogService;
import com.bootdo.common.utils.ExceptionUtils;
import com.bootdo.common.utils.LogUtil;
import com.bootdo.common.utils.ReturnUtils;
import com.bootdo.interfaceext.service.InterfaceService;
import com.bootdo.interfaceext.vo.InterfaceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaos on 2019/8/15.
 */
@Controller
@RequestMapping("/interface")
public class InterfaceController extends BaseController {

    public final Logger log = LoggerFactory.getLogger(InterfaceController.class);

    @Autowired
    private InterfaceService interfaceService;

    @Autowired
    private LogService logService;

    @Autowired
    private BasInterfaceService basInterfaceService;

    @Autowired
    private BasHospitalService basHospitalService;

    @ResponseBody
    @PostMapping("/{hospitalid}/{action}")
    public ReturnUtils list(HttpServletRequest request, HttpServletResponse response,
                            @PathVariable("hospitalid") String hospitalid, @PathVariable("action") String action, @RequestParam Map<String, Object> params) {
        // 查询列表数据
        //Query query = new Query(params);
       // List<TaskDO> taskScheduleJobList = taskScheduleJobService.list(query);
       // int total = taskScheduleJobService.count(query);
       // PageUtils pageUtils = new PageUtils(taskScheduleJobList, total);
       // return pageUtils;
        long startTime = System.currentTimeMillis();
        long endTime;
        LogUtil logUtil = new LogUtil();

        String hospitalName = "";

        ReturnUtils returnUtils = null;
        try{
            List<Map<String, Object>> result = null;

            InterfaceVO interfaceVO = new InterfaceVO();
            interfaceVO.setAction(action);

            //获取医院信息
            List<Map<String,Object>> basHospitalList =  basHospitalService.queryHospital(hospitalid);

            if(basHospitalList.size() >= 1){
                hospitalName = basHospitalList.get(0).get("HOSNAME") + "";

                //获取接口信息
                List<Map<String,Object>> baseInterfaceList = basInterfaceService.queryInterface(action);

                if(baseInterfaceList.size() >= 1){
                    Map baseInterfaceMap = baseInterfaceList.get(0);
                    String dbCode = baseInterfaceMap.get("interface_ds_code") + "";

                    interfaceVO.setDbCode(dbCode);
                    interfaceVO.setHospitalId(hospitalid);
                    result = interfaceService.list(interfaceVO,params);

                    int total = result.size();

                    endTime = System.currentTimeMillis();
                    logUtil.saveInterfaceRequestSuccessInfo(logService,request,action,hospitalid,hospitalName,params,startTime,endTime);

                    returnUtils = new ReturnUtils();
                    returnUtils.seccessReturn(result,total,"查询成功！");
                }else{
                    returnUtils = new ReturnUtils();
                    returnUtils.failReturn("接口不存在");
                }
            }else {
                returnUtils = new ReturnUtils();
                returnUtils.failReturn("医院不存在");
            }

        }catch(Throwable e){
            log.error(e.getMessage());
            log.error(ExceptionUtils.getExceptionAllinformation(e));

            endTime = System.currentTimeMillis();
            logUtil.saveInterfaceRequestErrorInfo(logService,request,action,hospitalid,hospitalName,params,startTime,endTime);

            returnUtils = new ReturnUtils();
            returnUtils.failReturn("系统内部错误！");
        }finally{

        }


       // returnUtils.failReturn("入参错误");
        return returnUtils;
    }

}
