package com.erhi.tools;

import java.sql.ResultSet;

public interface ResultSetHandler <T>{
	public T handler(ResultSet rSet);
}
	

