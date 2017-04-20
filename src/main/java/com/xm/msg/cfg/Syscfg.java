package com.xm.msg.cfg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Syscfg {

	public String syscfgPath = "D:/person/syscfg/xmmessage";
	
	public static final String url = "jdbc:mysql://127.0.0.1:3306/xmmessage?useUnicode=true&characterEncoding=UTF-8";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "83618273011";
	
	public static Connection conn = null;
	public static Statement st = null;
	
	public static void sysInit() {
		try {
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, password);
			st = conn.createStatement();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sysExit() {
		try {
			conn.close();
			st.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
