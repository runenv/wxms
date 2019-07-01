package com.ecp888.wxms.utils.typeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.sql.CLOB;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class OracleClobTypeHandler implements TypeHandler<Object>{

	@Override
	public void setParameter(PreparedStatement ps, int i, Object parameter,
			JdbcType jdbcType) throws SQLException {
		
		CLOB clob = CLOB.getEmptyCLOB();
	    clob.setString(1, (String) parameter);
	    //clob.setCharacterStream(i);
	    ps.setClob(i, clob);
	}

	@Override
	public Object getResult(ResultSet rs, String columnName)
			throws SQLException {
		
		CLOB clob = (CLOB) rs.getClob(columnName);
	    return (clob == null || clob.length() == 0) ? 
	    		null : clob.getSubString((long) 1, (int) clob.length());
	}

	@Override
	public Object getResult(ResultSet rs, int columnIndex) throws SQLException {

		return null;
	}

	@Override
	public Object getResult(CallableStatement cs, int columnIndex)
			throws SQLException {

		return null;
	}

}
