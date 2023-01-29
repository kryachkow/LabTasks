package com.my;

import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

import com.my.web.db.DBManager;

// it is not necessary to use this listener!!!
@WebListener
public class ContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// configure logging subsystem
		String path = sce.getServletContext()
				.getRealPath("WEB-INF/log4j.log");
		System.setProperty("log4j.path", path);
		

		// WEB-INF/log4j.log <--

		// log4j2.properties
		// log4j2.xml
		
		// read from system properties
		
		// check if database connection exists!

		try {
			DBManager.getInstance().getConnection().close();
		} catch (SQLException ex) {
			throw new IllegalStateException("Cannot obtain a connection from DB", ex);
		}
	}
}
