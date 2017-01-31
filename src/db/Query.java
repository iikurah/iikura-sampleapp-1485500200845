package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

public class Query {

	/** ResulrSetのオブジェクト */
	public ResultSet result_;
	
	/** PreparedStatementのオブジェクト */
	public PreparedStatement statement_;
	
	/**
	 * ResultSet.next()を実行します。
	 * @return true:次のレコード存在  false:次のレコード存在しない
	 * @throws SQLException
	 */
	public boolean next() throws SQLException {
		if(result_.next())
			return true;
		else
			return false;
	}

	/**
	 * ResultSetとPreparedStatementをcloseします。
	 * @throws SQLException
	 */
	public void close() throws SQLException{
		if(result_!=null) result_.close();
		if(statement_!=null) statement_.close();	
	}

	
	/**
	 * java.util.Dateのオブジェクトをjava.sql.Dateに変換します。
	 * @param date java.util.Dateのオブジェクト
	 * @return java.sql.Dateのオブジェクト
	 */
	protected java.sql.Date convert(java.util.Date date) {
		
		long longDate = date.getTime();
		return new java.sql.Date(longDate);
	}
	
	
}