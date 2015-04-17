package com.tarena.autorun.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tarena.autorun.util.AppConfig;

/**
 * Application Lifecycle Listener implementation class InitalConfig
 *
 */
public class InitalConfig implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public InitalConfig() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	//Inital config .
    	AppConfig.inital();
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
