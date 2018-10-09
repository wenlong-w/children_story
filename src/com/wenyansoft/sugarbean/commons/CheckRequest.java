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
		log.info("�û�refer:"+refer);
		String UserAgent = request.getHeader("User-Agent");
		System.out.println("UserAgent:"+UserAgent);
		log.info("�û�UserAgent:"+UserAgent);
		String kk = "servicewechat.com";*/
		
		boolean vt = verifyToken(request, response);
		if(!vt){
			result = false;
			System.out.println("��֤��ͨ��������");
		}
		return result;
	}
	
	/**
	 * ��ȫУ��
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public boolean verifyToken (HttpServletRequest request, HttpServletResponse response)
			throws  IOException {
		boolean result = false;
		String timeMillis = request.getParameter("timeMillis");
		//����������֤��
		String key = StoryConstant.SUGARBEAN_VERIFY_KEY;
		//�ͻ��˵��û��ǳ� + �ͻ���ʱ��� + ���ص�password ��ɱ���token��֤��
		String localTokenBuffer = new StringBuffer("")
				.append(timeMillis).append(key).toString();
//		System.out.println("localTokenBuffer===:"+localTokenBuffer);
		String localToken = "";
		try {
			localToken = MD5Encode(localTokenBuffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//�ͻ���token
		String token = request.getParameter("token");
//		System.out.println("localToken===:"+localToken);
//		System.out.println("token===:"+token);
		//����token��ͻ���token�Ա���֤
//		if (localToken.equals(token)) {
		if (localToken.equals(token)) {
			//��֤ͨ��
			result = true ;
		} else {
			log.info("��֤ʧ�ܣ�ԭʼtoken:"+token+"  ����token:"+localToken);
			//��֤ʧ��
			result = false ;
		}
		return result;
	}
	/**
	 * ��̨MD5���ܣ�utf8����
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
