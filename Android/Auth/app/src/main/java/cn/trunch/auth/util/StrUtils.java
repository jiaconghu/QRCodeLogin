package cn.trunch.auth.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName StrUtils
 * @Author HWG
 * @Time 2019/4/17 21:38
 */

public class StrUtils {
    public static String randomNum(boolean numberFlag, int length){
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWSYZ";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);
        return retStr;
    }

    public static String timeStamp(){
        SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return df.format(new Date());
    }
}
