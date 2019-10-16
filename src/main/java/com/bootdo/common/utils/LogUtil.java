package com.bootdo.common.utils;

import com.bootdo.common.domain.RequestLogDO;
import com.bootdo.common.service.LogService;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Map;

/**
 * Created by zhaos on 2019/8/18.
 */
public class LogUtil {

    public void saveInterfaceRequestSuccessInfo(LogService logService,HttpServletRequest request, String action,String hospitalid,
                                                       String hospitalName, Map<String, Object> params,long startTime, long endTime){
        saveInterfaceRequestInfo(logService,request,action,hospitalid,hospitalName,params,1,startTime,endTime);
    }

    public void saveInterfaceRequestErrorInfo(LogService logService,HttpServletRequest request, String action, String hospitalid,
                                                     String hospitalName, Map<String, Object> params,long startTime, long endTime){
        saveInterfaceRequestInfo(logService,request,action,hospitalid,hospitalName,params,2,startTime,endTime);
    }

    private void saveInterfaceRequestInfo(LogService logService,HttpServletRequest request, String action,String hospitalid,
                                                String hospitalName, Map<String, Object> params,int successFlg,long startTime, long endTime){
        String uri = request.getRequestURI();
        String strParams = JSONUtils.beanToJson(params);
        String serverIp = getServerIp(request);
        String visitIp = getVisitIp(request);

        RequestLogDO requestLogDO = new RequestLogDO();

        requestLogDO.setSTART_TIME_MILLIS(startTime);
        requestLogDO.setEND_TIME_MILLIS(endTime);
        requestLogDO.setAtcion(action);
        requestLogDO.setVISIT_IP(visitIp);
        requestLogDO.setSERVER_IP(serverIp);
        requestLogDO.setHOSPITAL_NUM(hospitalid);
        requestLogDO.setHOSPITAL_NAME(hospitalName);
        requestLogDO.setURI(uri);
        requestLogDO.setPARAMETER(strParams);
        requestLogDO.setSUCCESS_FLG(successFlg);
        requestLogDO.setUSE_MILLIS(endTime - startTime);


        logService.saveInterfaceRecord(requestLogDO);
    }

    private String getServerIp(HttpServletRequest request){
        InetAddress inet = null;
        String ip = "";
        try {
            inet = InetAddress.getLocalHost();
            ip = inet.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (request!=null && (isNullOrEmpty(ip) || "unknown".equalsIgnoreCase(ip))) {
            ip = request.getLocalAddr();
        }
        return ip;
    }

    private String getVisitIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (isNullOrEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (isNullOrEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isNullOrEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 多重代理
        if (!isNullOrEmpty(ip) && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    private boolean isNullOrEmpty(String string)
    {
        if(string == null || string.trim().equals("")){
            return true;
        }
        String checkStr = string.trim();
        checkStr = checkStr.replaceAll("'", "");
        checkStr = checkStr.replaceAll("\"", "");
        return  checkStr.equalsIgnoreCase("null");
    }

}
