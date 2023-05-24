package com.example.tobyspring3.dao;

import static java.lang.System.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.example.tobyspring3.domain.User;

public class UserDao {
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Map<String, String> env = getenv();
		String dbHost = env.get("DB_HOST");
		String dbUser = env.get("DB_USER");
		String dbPassword = env.get("DB_PASSWORD");
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(dbHost, dbUser, dbPassword);
	}
	public void add(User user) throws SQLException, ClassNotFoundException {
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into users (id, name, password) values(?, ?, ?)");
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getPassword());
		pstmt.executeUpdate();
		conn.close();
		pstmt.close();
	}
	public User get(String id) throws SQLException, ClassNotFoundException {
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select id, name, password from users where id=?");
		pstmt.setString(1, id);
		//select이기 때문에 결과값
		ResultSet resultSet = pstmt.executeQuery();
		//ctrl + enter와 같다. 쿼리 실행
		resultSet.next();
		User user = new User();
		user.setId(resultSet.getString("id"));
		user.setName(resultSet.getString("name"));
		user.setPassword(resultSet.getString("password"));
		pstmt.close();
		conn.close();
		return  user;
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		UserDao ud = new UserDao();
		User user = new User();
		user.setId("3");
		user.setName("EunJeong");
		user.setPassword("13242345");
		ud.add(user);
		user = ud.get("3");
		out.println(user.getId());
		out.println(user.getName());
		out.println(user.getPassword());
	}
}
