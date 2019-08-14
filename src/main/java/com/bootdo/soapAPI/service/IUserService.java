package com.bootdo.soapAPI.service;

import org.activiti.engine.impl.util.json.XML;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;


/**
 * 该接口是服务端提供对外服务的接口，服务的主要代码由其实现类来完成。
 * @author lemon
 *
 */
@WebService  // 使用在类上面，标记类是 WebService 服务提供对象
public interface IUserService {
    @WebMethod  // 使用在方法上面，标记方法 是 WebService 服务提供方法
    String patientInfoRegister(String patientInfo);
    @WebMethod
    String  patientInfoUpdate(String  patientInfo);
    @WebMethod
    String queryPatientInfo(String patientInfo);
}
