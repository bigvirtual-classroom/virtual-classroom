package com.tarena.autorun.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tarena.autorun.util.MainUtil;
import com.tarena.autorun.util.ServletUtill;

/**
 * Servlet implementation class Run
 */
public class Run extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		dowithURL(url, request, response);
	}

	private void dowithURL(String url, HttpServletRequest request,
			HttpServletResponse response) {
		if (url.indexOf("/run/build") > -1) {
			String code = request.getParameter("code");
			String className = request.getParameter("className");
			String userName = (String) request.getSession().getAttribute("username");
			// 编译
			String msg = build(code, className,userName);
			ServletUtill.json_ok(response, msg);
		} else if (url.indexOf("/run/go") > -1) {
			String className = request.getParameter("className");
			String userName = (String) request.getSession().getAttribute("username");
			// 运行
			String gorun = gorun(className, userName);
			ServletUtill.json_ok(response, gorun);
			
		} else if (url.indexOf("/run/bgo") > -1) {
			String code = request.getParameter("code");
			String className = request.getParameter("className");
			String userName = (String) request.getSession().getAttribute("username");
			// 编译运行
			String back = buildAndRun(code, className,userName);
			ServletUtill.json_ok(response, back);
		}
	}

	private String gorun(String className, String  userName) {

		try {
			String back = MainUtil.runJava(userName, className);
			return back;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String buildAndRun(String code,String className,String userName) {

		try {
			MainUtil.saveJavaFile(userName, code);
			String back = MainUtil.build2(userName, className);
			return  back;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String build(String code,String className,String userName) {
		//获取
/*		String code = request.getParameter("code");
		String className = request.getParameter("className");
		String userName = (String) request.getSession().getAttribute("username");*/
		try {
			MainUtil.saveJavaFile(userName, code);
			int back = MainUtil.build(userName, className);
			if(back==0){
				return  "OK";
			}else{
				return "编译出错";
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
