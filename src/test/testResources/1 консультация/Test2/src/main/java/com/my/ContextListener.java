package com.my;

import javax.servlet.*;
import javax.servlet.annotation.*;

import org.apache.logging.log4j.*;

@WebListener
public class ContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext ctx = sce.getServletContext();
		String path = ctx.getRealPath("/WEB-INF/log4j2.log");
		System.setProperty("logFile", path);
		
		final Logger log = LogManager.getLogger(ContextListener.class);
		log.debug("path = " + path);
		
		// check data source
		DBUtils.getInstance();
	}

}
