package com.bootdo.soapAPI.service.impl;

import com.bootdo.common.utils.GenUtils;
import com.bootdo.soapAPI.service.IUserService;
import com.bootdo.util.httpClient.HttpClientUtils;
import net.sf.json.JSONObject;
import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebService;


@WebService(endpointInterface = "com.bootdo.soapAPI.service.IUserService", serviceName = "userService")
// 注解设置 endPointInterface 接口服务完整类名， servicename 服务名称
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private Configuration config = GenUtils.getMessageConfig();


    @Override
    public String patientInfoRegister(String registerInfo) {
        String url = (String) config.getProperty("empiurl")
                + config.getProperty("empiurl.patientInfoRegister");
        return httpPost(url, registerInfo);
    }

    @Override
    public String patientInfoUpdate(String updateInfo) {
        String url = config.getProperty("empiurl").toString()
                + config.getProperty("empiurl.patientInfoUpdate").toString();
        return httpPost(url, updateInfo);
    }

    @Override
    public String queryPatientInfo(String patientInfo) {
        String url = config.getProperty("empiurl").toString()
                + config.getProperty("empiurl.queryPatientInfo").toString();
        return httpPost(url, patientInfo);
    }

    private String httpPost(String url , String message){
        JSONObject returnJson ;
        try {
            returnJson = HttpClientUtils.httpPost(url, message);
            if(message != null){
                if(200 != (Integer) returnJson.get("code")){
                    logger.error("发送失败");
                }
                logger.info("发送成功");
                logger.info("返回值："+returnJson.get("data"));
            }
        }catch (Exception e){
            return "请求失败，无返回信息";
        }

        System.out.println(returnJson.get("data"));
        return returnJson.get("data").toString();
    }
}
