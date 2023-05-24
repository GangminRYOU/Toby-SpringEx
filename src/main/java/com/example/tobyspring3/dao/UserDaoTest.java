package com.example.tobyspring3.dao;

import static java.lang.System.*;

import java.sql.SQLException;

import com.example.tobyspring3.domain.User;

public class UserDaoTest {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		ConnectionMaker cm = new DConnectionMaker();
		UserDao userDao = new UserDao(cm);
		User user = new User();
		user.setId("8");
		user.setName("Minsu");
		user.setPassword("1231235");
		userDao.add(user);
		user = userDao.get("6");
		out.println(user.getId());
		out.println(user.getName());
		out.println(user.getPassword());
	}
}
