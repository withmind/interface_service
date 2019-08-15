package com.bootdo.soapAPI.service.impl;

import com.bootdo.common.utils.GenUtils;
import org.apache.commons.configuration.Configuration;
import org.junit.Test;

public class UserServiceImplTest {


    private Configuration config = GenUtils.getMessageConfig();

    @Test
    public void patientInfoRegister()  {
//        Configuration config = new PropertiesConfiguration("message.properties");
        String a1 = (String) config.getProperty("empiurl");
        String a2 = (String) config.getProperty("empiurl.patientInfoRegister");
        System.out.println(a1+a2);
    }

    @Test
    public void patientInfoUpdate() {
    }

    @Test
    public void queryPatientInfo() {
    }
}