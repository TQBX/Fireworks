package com.smday.fireworks.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * created by Summer-day
 */
public class JdbcDemo {
	
	
	public static void main(String[] args) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/fireworks?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
		Connection conn = DriverManager.getConnection(url, "root", "123456");
		String sql = "select * from ums_role where id = 1";
		PreparedStatement stat = conn.prepareStatement(sql);
		ResultSet resultSet = stat.executeQuery();
		while(resultSet.next()){
			//获取数据
			Integer id = resultSet.getInt(1);
			String name = resultSet.getString("name");
			String description = resultSet.getString("description");
			System.out.println("id ==> "+id);
			System.out.println("name ==> "+name);
			System.out.println("description ==> "+description);
		}
		
		
	}
}
