package cn.tohuangshuai.common.util;

import java.util.UUID;
//返回随机字符串
public class IdUtil {

    public static String getUId(){
        String UId = UUID.randomUUID().toString().replace("-","");
        return UId;
    }

    public static void main(String[] args) {
        System.out.println(getUId());
    }

}
