package com.example.tobyspring3.dao;

import static java.lang.System.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class NUserDao extends UserDao{

	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Map<String, String> env = getenv();
		String dbHost = env.get("DB_HOST");
		String dbUser = env.get("DB_USER");
		String dbPassword = env.get("DB_PASSWORD");
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(dbHost, dbUser, dbPassword);
	}
}
