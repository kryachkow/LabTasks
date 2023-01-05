package com.my.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.my.web.command.*;

@WebServlet("/controller")
public class Controller extends HttpServlet {
	
	// http://localhost:8080/Test/controller?command=login
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// address to go to
		String address = "error.jsp";

		// (1) obtain a command name
		String commandName = req.getParameter("command");
		
		// (2) obtain a command
		Command command = CommandContainer.getCommand(commandName);
		
		// (3) do action
		try {
			address = command.execute(req, resp);
		} catch (Exception ex) {
			req.setAttribute("errorMessage", ex.getMessage());
		}
		
		// (4) go to a view layer
		req.getRequestDispatcher(address).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String address = "error.jsp";
		String commandName = req.getParameter("command");
		System.out.println("commandName ==> " + commandName);
		
		Command command = CommandContainer.getCommand(commandName);
		System.out.println("command ==> " + command);

		try {
			address = command.execute(req, resp);
		} catch (Exception ex) {
			ex.printStackTrace();
			req.getSession().setAttribute("errorMessage", ex.getMessage());
		}
		resp.sendRedirect(address);
	}

}
