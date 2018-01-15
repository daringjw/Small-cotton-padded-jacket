package com.jinkun.care.helper;

/**
 * Created by coderwjq on 2017/8/25 11:28.
 */

public class DataTransHelper {
    public static Integer getElderSex(String text) {
        if (text.equals("男")) {
            return 0;
        } else {
            return 1;
        }
    }

    public static Integer getSensus(String text) {
        if (text.equals("戶籍")) {
            return 0;
        } else {
            return 1;
        }
    }
}
