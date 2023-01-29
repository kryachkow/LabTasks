package com.my.web.command;

import javax.servlet.http.*;

import com.my.web.db.DBManager;
import com.my.web.db.entity.User;

public class LoginCommand implements Command {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String login = req.getParameter("login");
		System.out.println("login ==> " + login);

		String password = req.getParameter("password");
		
		User user = DBManager.getInstance().findUser(login);
		System.out.println("user ==> " + user);
		
		if (user != null && user.getPassword().equals(password)) {
			req.getSession().setAttribute("currentUser", user);
			return "admin.jsp";
		} 
		
		req.getSession().setAttribute("errorMessage", "Illegal login or password!");
		return "login.jsp";
	}

}
