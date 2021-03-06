package com.wenyansoft.system.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tl.commons.util.FileUtility;
import org.tl.commons.util.PropWorker;

import com.wenyansoft.system.commons.StoryConstant;

public class InitServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public InitServlet() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		String wypath = FileUtility.getAbsolutePathFromClassPath(
				"/wenyansoft.properties", InitServlet.class);
		try {
			PropWorker storyPW = new PropWorker(wypath);
			String appId = storyPW.getValue("everyday_appid");
			StoryConstant.EVERYDAY_APPID = appId;
			String appSecret = storyPW.getValue("everyday_appsecret");
			StoryConstant.EVERYDAY_APPSECRET = appSecret;
			String verifyKey = storyPW.getValue("everyday_verify_key");
			StoryConstant.EVERYDAY_VERIFY_KEY = verifyKey;
			
			
			String sugarVerifyKey = storyPW.getValue("sugarbean_verify_key");
			StoryConstant.SUGARBEAN_VERIFY_KEY= sugarVerifyKey;
			String sugarAppID = storyPW.getValue("sugarbean_appid");
			StoryConstant.SUGARBEAN_APPID = sugarAppID;
			String sugarAppSecret = storyPW.getValue("sugarbean_appsecret");
			StoryConstant.SUGARBEAN_APPSECRET = sugarAppSecret;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
