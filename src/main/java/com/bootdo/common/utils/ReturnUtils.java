package com.bootdo.common.utils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaos on 2019/8/17.
 */
public class ReturnUtils implements Serializable {
    private static final long serialVersionUID = 1L;

    boolean success = true;
    String resultCode= "";

    PageUtils data = null;

  /*  public ReturnUtils(List<?> list, int total,String resultCode,boolean seccess) {
        data = new PageUtils(list,total);
        this.resultCode = resultCode;
        this.success = seccess;
    }*/

    public void seccessReturn(List<?> list, int total,String resultCode) {
        success = true;
        data = new PageUtils(list,total);
        this.resultCode = resultCode;
    }

    public void failReturn(String resultCode) {
        success = false;
        this.resultCode = resultCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public PageUtils getData() {
        return data;
    }

    public void setData(PageUtils data) {
        this.data = data;
    }
}
