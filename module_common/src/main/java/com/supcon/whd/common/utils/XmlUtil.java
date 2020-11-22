package com.supcon.whd.common.utils;

public class XmlUtil {

    public static String getStringByTag(String xml, String tag){

        String[] temp = {"<"+tag+">", "</"+tag+">"};


        int startIndex = xml.indexOf(temp[0])+temp[0].length();
        int endIndex = xml.indexOf(temp[1]);

        if(startIndex >= endIndex){

            return "";

        }

        return xml.substring(startIndex,endIndex);


    }

}
