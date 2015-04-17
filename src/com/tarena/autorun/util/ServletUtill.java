package com.tarena.autorun.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ServletUtill {
	public static void setEncoding(HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
	}
	public static void setJson(HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("application/json; charset=UTF-8"); 
	}
	
	public static void json_ok(HttpServletResponse response,String msg){
		setJson(response);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append("{back:1,msg:'"+msg+"'}");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void json_err(HttpServletResponse response,String msg){
		setJson(response);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append("{back:0,msg:'"+msg+"'}");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
