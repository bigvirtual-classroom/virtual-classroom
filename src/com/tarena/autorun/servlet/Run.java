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
			// 编译
			build(request, response);
		} else if (url.indexOf("/run/go") > -1) {
			// 运行
			gorun(request, response);
		} else if (url.indexOf("/run/bgo") > -1) {
			// 编译运行
			buildAndRun(request, response);
		}
	}

	private void gorun(HttpServletRequest request, HttpServletResponse response) {
		String className = request.getParameter("className");
		String userName = (String) request.getSession().getAttribute("username");
		try {
			String back = MainUtil.runJava(userName, className);
			ServletUtill.json_ok(response, back);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buildAndRun(HttpServletRequest request,
			HttpServletResponse response) {
		String code = request.getParameter("code");
		String className = request.getParameter("className");
		String userName = (String) request.getSession().getAttribute("username");
		try {
			MainUtil.saveJavaFile(userName, code);
			String back = MainUtil.build2(userName, className);
			ServletUtill.json_ok(response, back);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void build(HttpServletRequest request, HttpServletResponse response) {
		//获取
		String code = request.getParameter("code");
		String className = request.getParameter("className");
		String userName = (String) request.getSession().getAttribute("username");
		try {
			MainUtil.saveJavaFile(userName, code);
			int back = MainUtil.build(userName, className);
			if(back==0){
				ServletUtill.json_ok(response, "OK");
			}else{
				ServletUtill.json_err(response, "编译出错");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
