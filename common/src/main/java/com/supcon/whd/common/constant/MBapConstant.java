package com.supcon.whd.common.constant;

public class MBapConstant {
    int UNHANDLE = 0;
    int HANDLE = 1;

    float KEY_RADIO = 1.1f;

//    interface ViewAction{
//        int CONTENT_CLEAN = -1;
//        int NORMAL = 0;
//        int CONTENT_COPY = 1;
//    }
//
//    interface WorkFlow{
//
//        int SAVE_BTN = 0;
//        int MIDDLE_BTN = SAVE_BTN+1;
//        int SUBMIT_BTN = MIDDLE_BTN+1;
//        int SAVE_LOCAL_BTN = SUBMIT_BTN+1;
//    }

    public interface SPKey{

        String LOGIN = "login";
        String IP = "ip";
        String PORT = "port";
        String URL = "url";
        String USER_NAME = "username";
        String PWD  = "pwd";
        String JSESSIONID = "JSESSIONID";
        String CASTGC  = "CASTGC";
        String SUPOS_TICKET = "SUPOS_TICKET";


        String URL_ENABLE = "URL_ENABLE";


    }

    interface Cache{

        String WORDS_CACHE = "wordsCache";

    }
}
