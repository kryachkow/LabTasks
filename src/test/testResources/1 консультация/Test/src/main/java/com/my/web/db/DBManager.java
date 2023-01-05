package com.my.web.db;

import java.sql.*;

import com.my.web.db.entity.User;

public class DBManager {
	

	private static DBManager instance;

	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	///////////////////////////////////

	private static final String CONNECTION_URL = 
			"jdbc:mysql://localhost:3306/testdb?user=test&password=test";

	private static final String SQL_FIND_USER_BY_LOGIN = 
			"select * from users where login=?";
	
	public User findUser(String login) {
		try (Connection con = getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return extractUser(rs);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		return user;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(CONNECTION_URL);
	}
	

}
