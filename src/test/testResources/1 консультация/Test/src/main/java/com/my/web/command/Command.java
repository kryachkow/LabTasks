package com.my.web.command;

import javax.servlet.http.*;

public interface Command {

	String execute(HttpServletRequest req,
			HttpServletResponse resp) throws Exception;

}
