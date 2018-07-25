package com.erhi.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tem<T> {
	/***
	 * 通过内部接口来提供一个结果集的持有者.
	 * @author Administrator
	 *
	 * @param <T>
	 */
	
	public T testQuery(String sql,ResultSetHandler<T> handler,Object...params) {
		try {
			//--  获取链接
			Connection conn = DBHelper.getInstance().getConnection();
			//-- 获取预处理对象PreparedStatement对象
			PreparedStatement pStatement = conn.prepareStatement(sql);
			//-- 好事成双
			int count = pStatement.getParameterMetaData().getParameterCount();
			for (int i = 0; i < count; i++) {
				pStatement.setObject(i+1, params[i]);
			}
			ResultSet rSet= pStatement.executeQuery();			
			//-- 甩锅.甩给调用该方法的人
			return handler.handler(rSet);
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
