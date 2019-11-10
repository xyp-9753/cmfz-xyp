package com.baizhi.util;

import java.util.Random;

public class AliyunGetCode {
    public static String SuiJiCode(){
        String sources = "0123456789";
        Random rand = new Random();
        StringBuffer flag = new StringBuffer();
        for (int j = 0; j < 6; j++) 	{
            flag.append(sources.charAt(rand.nextInt(9)) + "");
        }
        return flag.toString();

    }
}
