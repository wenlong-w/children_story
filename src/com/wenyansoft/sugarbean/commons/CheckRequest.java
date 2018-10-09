package com.wenyansoft.sugarbean.commons;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wenyansoft.system.commons.StoryConstant;

public class CheckRequest {

	protected Log log = LogFactory.getLog(getClass());
	
	public boolean doCheck(HttpServletRequest request, HttpServletResponse response) throws IOException{
		boolean result = true;
		/*String refer = request.getHeader("referer");
		System.out.println("refer:"+refer);
		log.info("用户refer:"+refer);
		String UserAgent = request.getHeader("User-Agent");
		System.out.println("UserAgent:"+UserAgent);
		log.info("用户UserAgent:"+UserAgent);
		String kk = "servicewechat.com";*/
		
		boolean vt = verifyToken(request, response);
		if(!vt){
			result = false;
			System.out.println("验证不通过！！！");
		}
		return result;
	}
	
	/**
	 * 安全校验
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public boolean verifyToken (HttpServletRequest request, HttpServletResponse response)
			throws  IOException {
		boolean result = false;
		String timeMillis = request.getParameter("timeMillis");
		//服务器端验证码
		String key = StoryConstant.SUGARBEAN_VERIFY_KEY;
		//客户端的用户昵称 + 客户端时间戳 + 本地的password 组成本地token验证码
		String localTokenBuffer = new StringBuffer("")
				.append(timeMillis).append(key).toString();
//		System.out.println("localTokenBuffer===:"+localTokenBuffer);
		String localToken = "";
		try {
			localToken = MD5Encode(localTokenBuffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//客户端token
		String token = request.getParameter("token");
//		System.out.println("localToken===:"+localToken);
//		System.out.println("token===:"+token);
		//本地token与客户端token对比验证
//		if (localToken.equals(token)) {
		if (localToken.equals(token)) {
			//验证通过
			result = true ;
		} else {
			log.info("验证失败，原始token:"+token+"  本地token:"+localToken);
			//验证失败
			result = false ;
		}
		return result;
	}
	/**
	 * 后台MD5加密，utf8编码
	 * @param str
	 * @return
	 */
	public static String MD5Encode(String str) {  
        MessageDigest messageDigest = null;  
        try {  
            messageDigest = MessageDigest.getInstance("MD5");  
            messageDigest.reset();  
            messageDigest.update(str.getBytes("UTF-8"));  
        } catch (NoSuchAlgorithmException e) {  
            System.out.println("NoSuchAlgorithmException caught!");  
            System.exit(-1);  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        byte[] byteArray = messageDigest.digest();  
        StringBuffer md5StrBuff = new StringBuffer();  
        for (int i = 0; i < byteArray.length; i++) {  
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
            else  
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
        }  
        return md5StrBuff.toString();  
    }  
}
