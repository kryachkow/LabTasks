package com.my;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import org.apache.logging.log4j.*;

@WebServlet("/test")
public class Test extends HttpServlet {

	private static final Logger log = LogManager.getLogger(Test.class);

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("Test#doGet");
		
		try (Connection con = DBUtils.getInstance().getConnection()) {
			resp.getWriter().println(con);
			log.debug("con = " + con);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}	
	
}