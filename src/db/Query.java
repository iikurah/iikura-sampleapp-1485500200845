package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

public class Query {

	/** ResulrSet�̃I�u�W�F�N�g */
	public ResultSet result_;
	
	/** PreparedStatement�̃I�u�W�F�N�g */
	public PreparedStatement statement_;
	
	/**
	 * ResultSet.next()�����s���܂��B
	 * @return true:���̃��R�[�h����  false:���̃��R�[�h���݂��Ȃ�
	 * @throws SQLException
	 */
	public boolean next() throws SQLException {
		if(result_.next())
			return true;
		else
			return false;
	}

	/**
	 * ResultSet��PreparedStatement��close���܂��B
	 * @throws SQLException
	 */
	public void close() throws SQLException{
		if(result_!=null) result_.close();
		if(statement_!=null) statement_.close();	
	}

	
	/**
	 * java.util.Date�̃I�u�W�F�N�g��java.sql.Date�ɕϊ����܂��B
	 * @param date java.util.Date�̃I�u�W�F�N�g
	 * @return java.sql.Date�̃I�u�W�F�N�g
	 */
	protected java.sql.Date convert(java.util.Date date) {
		
		long longDate = date.getTime();
		return new java.sql.Date(longDate);
	}
	
	
}