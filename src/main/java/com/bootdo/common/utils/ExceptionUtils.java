package com.bootdo.common.utils;

public class ExceptionUtils {
    public static String getExceptionAllinformation(Throwable ex) {
        String sOut = "";
        StackTraceElement[] trace = ex.getStackTrace();
        for (StackTraceElement s : trace) {
            sOut += "\tat " + s + "\r\n";
        }
        return sOut;
    }
}
