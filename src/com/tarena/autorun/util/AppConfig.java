package com.tarena.autorun.util;


public class AppConfig {
	public static String SOURCE_DIR;
	public static Cfs CONFIG;
	public final static String CLASS_DIR="classes";
	public static Runtime runtime;
	public static void inital(){
		System.err.println("--> inital config begining .");
		try {
			CONFIG = new Cfs("config.properties");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("Inital config error ! ==> CONFIG");
		}
		try {
			SOURCE_DIR = CONFIG.getAsString("source.dir");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("Inital config error ! ==> SOURCE_DIR");
		}
		try {
			runtime = Runtime.getRuntime();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("Inital config error ! ==> SOURCE_DIR");
		}
		try {
			SOURCE_DIR = CONFIG.getAsString("source.dir");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("Inital config error ! ==> SOURCE_DIR");
		}
		System.err.println("--> inital config successfully .");
	}
	
	public static void main(String[] args) {
		System.out.println("Test ==>");
		inital();
		System.out.println("Source DIR : " + SOURCE_DIR);
	}
}
