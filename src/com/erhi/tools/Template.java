package com.erhi.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Template{
	/**
	 * 测试写修改:
	 * 		增加
	 * 		删除
	 * 		更新
	 * 
	 * Bug:如果可变参,写多了或少了.会出事.
	 * 		使用元数据.根据sql语句中的问号来执行替换参数问题
	 * Java中有三种元数据.这里我们使用参数元数据
	 * 			
	 * 
	 */

	public boolean updateSQL(String sql,Object...params) {
		try {
			//-- 获取Connection 
			Connection conn = DBHelper.getInstance().getConnection();
			//-- 设置自动提交
			conn.setAutoCommit(true);
			//-- 通过链接获取PreparedStatement;
			PreparedStatement pStatement = conn.prepareStatement(sql);
			//-- 替换占位符
			/*
			for (int i = 0; i < params.length; i++) {
				pStatement.setObject(i+1, params[i]);
			}*/
			
			//-- 获取SQL语句中占位符的数量
			int count = pStatement.getParameterMetaData().getParameterCount();
			for (int i = 0; i < count; i++) {
				pStatement.setObject(i+1, params[i]);
			}

			//-- 执行sql语句
			pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return true;
	}
	
}
