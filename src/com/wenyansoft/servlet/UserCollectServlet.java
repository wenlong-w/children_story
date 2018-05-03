package com.wenyansoft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

import com.wenyansoft.commons.CheckRequest;
import com.wenyansoft.ibatis.service.UserCollectService;

public class UserCollectServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserCollectServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			CheckRequest crq = new CheckRequest();
			boolean dcResult = crq.doCheck(request, response);
			if (!dcResult) {
				return;
			}
			//������
			String methodName = request.getParameter("methodName");
			if (StringUtils.isBlank(methodName)) return;
			//ִ�з���
			execute(methodName,new Object[]{request,response});
		} catch (Exception e) {
			//���ظ�ʽ
			response.setContentType("text/javascript; charset=utf-8");
			PrintWriter out = response.getWriter();
			JSONObject jsonResult = new JSONObject();
			jsonResult.put("result", false);
			jsonResult.put("message", "�ӿڵ����쳣��");
			jsonResult.put("value", e.getStackTrace());
			//������ظ�ǰ̨
			out.print(jsonResult.toString());
			out.flush();
			out.close();
			
			e.printStackTrace();
		}
	}
	/**
	 * ͨ������ִ�з���methodName
	 * @param methodName
	 * @param parameterTypes
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute (String methodName, Object...parameterTypes) {
		
		Class userCollectServletcalss = UserCollectService.class;
		
		Method method = null;

		//ͨ�������ȡ����
		try {
			method = userCollectServletcalss.getMethod(methodName,
					new Class[]{HttpServletRequest.class,HttpServletResponse.class});
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
		
		try {
			//ִ����Ӧ����
			method.invoke(UserCollectService.getInstance(), parameterTypes);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		System.out.println("init");
	}

}
