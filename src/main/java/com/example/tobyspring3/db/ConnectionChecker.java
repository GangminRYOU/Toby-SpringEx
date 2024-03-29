package com.example.tobyspring3.db;

import static java.lang.System.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class ConnectionChecker {
	public void check() throws SQLException, ClassNotFoundException {
		Map<String, String> env = getenv();
		String dbHost = env.get("DB_HOST");
		String dbUser = env.get("DB_USER");
		String dbPassword = env.get("DB_PASSWORD");
		Class.forName("com.mysql.cj.jdbc.Driver");
		//workbench에서 아이디 비밀번호 호스트 정보 누르고 들어가는거랑 같다.
		Connection con = DriverManager.getConnection(dbHost, dbUser, dbPassword);

		// 쿼리를 만들고 실행하는 코드
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SHOW DATABASES");
		rs = st.getResultSet();
		//rs에 결과가 없을때 까지 NULL이 나올때까지 loop을 돈다.
		while (rs.next()) {
			//
			String str = rs.getString(1);
			System.out.println(str);
		}
	}

	public void add() throws ClassNotFoundException, SQLException {
		Map<String, String> env = getenv();
		String dbHost = env.get("DB_HOST");
		String dbUser = env.get("DB_USER");
		String dbPassword = env.get("DB_PASSWORD");
		Class.forName("com.mysql.cj.jdbc.Driver");
		//workbench에서 아이디 비밀번호 호스트 정보 누르고 들어가는거랑 같다.
		Connection con = DriverManager.getConnection(dbHost, dbUser, dbPassword);
		PreparedStatement psmt = con.prepareStatement("INSERT into users(id, name, password) VALUES (?, ?, ?)");
		psmt.setString(1, "1");
		psmt.setString(2, "Gangmin");
		psmt.setString(3, "12345678");
		psmt.executeUpdate();
	}
	public void select() throws SQLException, ClassNotFoundException{
		Map<String, String> env = getenv();
		String dbHost = env.get("DB_HOST");
		String dbUser = env.get("DB_USER");
		String dbPassword = env.get("DB_PASSWORD");
		Class.forName("com.mysql.cj.jdbc.Driver");
		//workbench에서 아이디 비밀번호 호스트 정보 누르고 들어가는거랑 같다.
		Connection con = DriverManager.getConnection(dbHost, dbUser, dbPassword);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from users limit 10");
		rs = st.getResultSet();
		while(rs.next()){
			String str = rs.getString(1);
			String str2 = rs.getString(2);
			String str3 = rs.getString(3);
			System.out.println(str + str2 + str3);
		}
	}

public void update() throws SQLException, ClassNotFoundException{
	Map<String, String> env = getenv();
	String dbHost = env.get("DB_HOST");
	String dbUser = env.get("DB_USER");
	String dbPassword = env.get("DB_PASSWORD");
	Class.forName("com.mysql.cj.jdbc.Driver");
	//workbench에서 아이디 비밀번호 호스트 정보 누르고 들어가는거랑 같다.
	Connection con = DriverManager.getConnection(dbHost, dbUser, dbPassword);
	Statement st = con.createStatement();
	PreparedStatement pstmt = con.prepareStatement("UPDATE users SET name=? WHERE id=?");
	pstmt.setString(1, "GangminRYOU");
	pstmt.setString(2, "1");
	pstmt.executeUpdate();
	}

	public void delete() throws SQLException, ClassNotFoundException{
		Map<String, String> env = getenv();
		String dbHost = env.get("DB_HOST");
		String dbUser = env.get("DB_USER");
		String dbPassword = env.get("DB_PASSWORD");
		Class.forName("com.mysql.cj.jdbc.Driver");
		//workbench에서 아이디 비밀번호 호스트 정보 누르고 들어가는거랑 같다.
		Connection con = DriverManager.getConnection(dbHost, dbUser, dbPassword);
		Statement st = con.createStatement();
		PreparedStatement pstmt = con.prepareStatement("DELETE from users WHERE id=?");
		pstmt.setString(1, "1");
		pstmt.executeUpdate();
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		ConnectionChecker cc = new ConnectionChecker();
		cc.check();
		cc.add();
		cc.select();
		cc.update();
		cc.select();
		cc.delete();
		cc.select();
	}
}
