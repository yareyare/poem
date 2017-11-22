package com.ivy.crawler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2017/11/2.
 */
public class MatchId {

    /**
     * 匹配div的id中是否含有数字
     * 含有数字返回true
     * */
    public static boolean matchId(String id){
        String regexId = "(\\d+)";
        Pattern patternId = Pattern.compile(regexId);
        Matcher mNum = patternId.matcher(id);
        if (mNum.find()) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        boolean b = matchId("yizhushangquan");
        System.out.println(b);
    }
}
