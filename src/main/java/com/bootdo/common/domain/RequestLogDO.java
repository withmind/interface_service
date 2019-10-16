package com.bootdo.common.domain;

import java.util.Date;
import java.sql.Timestamp;
/**
 * Created by zhaos on 2019/8/18.
 */
public class RequestLogDO {

    long id;
    String  SERVER_IP ;// 服务器IP
    String  VISIT_IP ;//用户访问IP
    String  HOSPITAL_NUM;//医院ID
    String  HOSPITAL_NAME;//医院名称
    String  URI;//访问URI
    String  atcion;//action
    String  PARAMETER;//参数
    int SUCCESS_FLG;//成功标志位
    Date  START_TIME;//开始时间
    Date  END_TIME;//结束时间
    long START_TIME_MILLIS;//开始时间(毫秒)
    long END_TIME_MILLIS;//结束时间(毫秒)
    long USE_MILLIS;//耗时(毫秒)

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSERVER_IP() {
        return SERVER_IP;
    }

    public void setSERVER_IP(String SERVER_IP) {
        this.SERVER_IP = SERVER_IP;
    }

    public String getVISIT_IP() {
        return VISIT_IP;
    }

    public void setVISIT_IP(String VISIT_IP) {
        this.VISIT_IP = VISIT_IP;
    }

    public String getHOSPITAL_NUM() {
        return HOSPITAL_NUM;
    }

    public void setHOSPITAL_NUM(String HOSPITAL_NUM) {
        this.HOSPITAL_NUM = HOSPITAL_NUM;
    }

    public String getHOSPITAL_NAME() {
        return HOSPITAL_NAME;
    }

    public void setHOSPITAL_NAME(String HOSPITAL_NAME) {
        this.HOSPITAL_NAME = HOSPITAL_NAME;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public String getAtcion() {
        return atcion;
    }

    public void setAtcion(String atcion) {
        this.atcion = atcion;
    }

    public String getPARAMETER() {
        return PARAMETER;
    }

    public void setPARAMETER(String PARAMETER) {
        this.PARAMETER = PARAMETER;
    }

    public int getSUCCESS_FLG() {
        return SUCCESS_FLG;
    }

    public void setSUCCESS_FLG(int SUCCESS_FLG) {
        this.SUCCESS_FLG = SUCCESS_FLG;
    }

    public Date getSTART_TIME() {
        return START_TIME;
    }

    public void setSTART_TIME(Date START_TIME) {
        this.START_TIME = START_TIME;
    }

    public Date getEND_TIME() {
        return END_TIME;
    }

    public void setEND_TIME(Date END_TIME) {
        this.END_TIME = END_TIME;
    }

    public long getSTART_TIME_MILLIS() {
        return START_TIME_MILLIS;
    }

    public void setSTART_TIME_MILLIS(long START_TIME_MILLIS) {
        this.START_TIME_MILLIS = START_TIME_MILLIS;
        setSTART_TIME(new Timestamp(this.START_TIME_MILLIS));
    }

    public long getEND_TIME_MILLIS() {
        return END_TIME_MILLIS;
    }

    public void setEND_TIME_MILLIS(long END_TIME_MILLIS) {
        this.END_TIME_MILLIS = END_TIME_MILLIS;
        setEND_TIME(new Timestamp(this.END_TIME_MILLIS));
    }

    public long getUSE_MILLIS() {
        return USE_MILLIS;
    }

    public void setUSE_MILLIS(long USE_MILLIS) {
        this.USE_MILLIS = USE_MILLIS;
    }
}
