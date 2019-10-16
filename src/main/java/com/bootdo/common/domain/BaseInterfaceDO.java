package com.bootdo.common.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhaos on 2019/8/18.
 */
public class BaseInterfaceDO  implements Serializable {

    private static final long serialVersionUID = 1L;
    long ID;//主键
    String interface_action;//接口action
    String interface_name;//接口名称
    String interface_ds_code;//接口数据源编码
    String interface_comment;//接口备注
    Date CREATE_DATE;//创建时间
    String CREATE_PERSON;//创建人姓名
    Date UPDATE_DATE;//更新时间
    String UPDATE_PERSON;//更新人

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getInterface_action() {
        return interface_action;
    }

    public void setInterface_action(String interface_action) {
        this.interface_action = interface_action;
    }

    public String getInterface_name() {
        return interface_name;
    }

    public void setInterface_name(String interface_name) {
        this.interface_name = interface_name;
    }

    public String getInterface_ds_code() {
        return interface_ds_code;
    }

    public void setInterface_ds_code(String interface_ds_code) {
        this.interface_ds_code = interface_ds_code;
    }

    public String getInterface_comment() {
        return interface_comment;
    }

    public void setInterface_comment(String interface_comment) {
        this.interface_comment = interface_comment;
    }

    public Date getCREATE_DATE() {
        return CREATE_DATE;
    }

    public void setCREATE_DATE(Date CREATE_DATE) {
        this.CREATE_DATE = CREATE_DATE;
    }


    public String getCREATE_PERSON() {
        return CREATE_PERSON;
    }

    public void setCREATE_PERSON(String CREATE_PERSON) {
        this.CREATE_PERSON = CREATE_PERSON;
    }

    public Date getUPDATE_DATE() {
        return UPDATE_DATE;
    }

    public void setUPDATE_DATE(Date UPDATE_DATE) {
        this.UPDATE_DATE = UPDATE_DATE;
    }

    public String getUPDATE_PERSON() {
        return UPDATE_PERSON;
    }

    public void setUPDATE_PERSON(String UPDATE_PERSON) {
        this.UPDATE_PERSON = UPDATE_PERSON;
    }
}
