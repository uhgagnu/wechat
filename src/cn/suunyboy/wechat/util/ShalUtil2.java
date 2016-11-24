package cn.suunyboy.wechat.util;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @Title ShalUtil2
 * @Description cn.suunyboy.wechat.util
 * @author HU GUANG
 * @email 13977300942@163.com
 * @version 1.0
 * @time 2016-11-11 上午1:17:35
 */
public class ShalUtil2 {

	private static String token = "demo";
	
	public static boolean checkSignature(String signature, String timestamp, String nonce){
		String[] strArr = new String[]{token,timestamp,nonce};
		//1.字典排序
		Arrays.sort(strArr);
		System.out.println("strArr:"+strArr);
		//2.生成字符串
		StringBuilder sb = new StringBuilder();
		int strLen = strArr.length;
		for(int i=0; i<strLen; i++){
			sb.append(strArr[i]);          
		}
		
		//3.加密
		String strTemp = getSha1(sb.toString());
		//4.比较
		if (signature!=null) {
			return signature.equals(strTemp);
		}
		return false;
	}
	
    private static String getSha1(String str){
        if(str==null||str.length()==0){
            return null;
        }
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];      
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
}
