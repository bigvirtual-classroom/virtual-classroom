package com.tarena.autorun.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.sun.tools.javac.Main;

public class MainUtil {
	public static void saveJavaFile(String dirs,String content) throws IOException{
		//保存 java文件.
		//Step1: 解析类名
		String className = content.substring(content.indexOf("public class ")+"public class ".length(),content.indexOf("{") ).trim();
		//Step2: 设置文件名称
		String fileName = className + ".java";
		String dirPath = AppConfig.SOURCE_DIR + File.separator+ dirs;
		File dir = new File(dirPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		File file = new File(dir+File.separator + fileName);
		if(!file.exists()){
			file.createNewFile();
		}
		FileOutputStream oStream = new FileOutputStream(file);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(oStream,"UTF-8"));
		writer.append(content);
		writer.close();
		oStream.close();
	}
	
	public static int build(String dirs,String className){
		int back = Main.compile(new String[] { AppConfig.SOURCE_DIR+dirs+File.separator+className+".java" });
		return back;
	}
	public static String build2(String dirs,String className){
		StringBuilder back = new StringBuilder();
		String cmd = "javac "+AppConfig.SOURCE_DIR+dirs+File.separator+className+".java";
		Process process=null;
		try {
			// 使用Runtime来执行command，生成Process对象
			process = AppConfig.runtime.exec(cmd);
			// 取得命令结果的输出流
			InputStream is = process.getInputStream();
			// 用一个读输出流类去读
			InputStreamReader isr = new InputStreamReader(is);
			// 用缓冲器读行
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				back.append(line);
			}
			is.close();
			isr.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return back.toString();
	}
	
	public static String runJava(String dirs,String className){
		//运行
		StringBuilder back = new StringBuilder();
		String cmd = "java -cp "+AppConfig.SOURCE_DIR+dirs+" "+className;
		Process process=null;
		try {
			// 使用Runtime来执行command，生成Process对象
			process = AppConfig.runtime.exec(cmd);
			// 取得命令结果的输出流
			InputStream is = process.getInputStream();
			String encoding = System.getProperty("file.encoding"); 
			if(System.getProperty("os.name").toUpperCase().indexOf("WINDOWS")>-1)
				encoding="GBK";
			else encoding = "UTF-8";
			// 用一个读输出流类去读
			InputStreamReader isr = new InputStreamReader(is,encoding);
			// 用缓冲器读行
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				back.append(line+"<br>");
			}
			is.close();
			isr.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return back.toString();
	}
	public static void main(String[] args) throws IOException {
		AppConfig.inital();
		//Test
		String className = "Test";
		StringBuilder code = new StringBuilder();
		code.append("public class Test{");
		code.append("public static void main(String[] args){");
		code.append("System.out.println(\"Hello word\");");
		code.append("}");
		code.append("}");
		saveJavaFile("test",code.toString());
		build("test",className);
		String back = runJava("test",className);
		System.out.println(back);
	}
}
