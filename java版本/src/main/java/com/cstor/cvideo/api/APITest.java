package com.cstor.cvideo.api;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zkk
 * @version V1.0
 * cVideo视频接口-java版本
 * @date ${date} ${time}
 */
public class APITest {


    public static String APP_KEY = "KJHSADFN11AZA" ;  // 申请到的APP_KEY

    public static String APP_SECRET = "SEECRECAFDSFD";// 申请到的APP_SECRET

    public static void main(String args[]){

        // 时间戳字段 (表示1970年01月01日00时00分00秒起至现在的总秒数。单位 : 秒)
        Long timestamp = System.currentTimeMillis()/1000 ;

        // 申请的APP_KEY字段，用来表示访问者身份
        String appkey = APP_KEY;

        // 进行加密后的token。加密方法见apimd5()方法注释
        String token = apimd5();
        // 最终的 url
        String url = "http://127.0.0.1:80/sso/api/v1/real/urls?id=10000000789&" +
                "centertype=0&timestamp=#{timestamp}&appkey=#{appkey}&token=#{token}";
    }


    /**
     * 1. 将参数以key的字母顺序进行排序
     * 2. 参数key+value组成字符换
     * 2. APP_SECRET + 步骤2中的字符串进行MD5加密
     * @return
     */
    public static  String apimd5(){
        Map<String, String> map = new TreeMap<String, String>();
        map.put("centertype", 1+"");
        map.put("id", 10000002+"");

        // 1.将参数以key的字母顺序进行排序
        map = sortMapByKey(map);
        StringBuffer stringBuffer = new StringBuffer();

        // 2.参数key+value组成字符换
        for (Map.Entry<String, String> entry : map.entrySet()) {
            stringBuffer.append( entry.getKey()).append(entry.getValue());
        }

        // 3.APP_SECRET + 步骤2中的字符串进行MD5加密
        return encryToMD5(APP_SECRET + stringBuffer.toString() ) ;
    }


    /**
     * MD5 加密
     * @param str
     * @return
     */
    public static String encryToMD5(String str){
        return  DigestUtils.md5Hex(str);
    }

    /**
     * Map按key进行排序
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<String, String>(
                new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }
}
class MapKeyComparator implements Comparator<String> {
    @Override
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}
