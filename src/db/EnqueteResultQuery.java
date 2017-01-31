package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EnqueteResultQuery extends Query {

	
	private String getCols() {
		return EnqueteResultTable.FULL_COL_ENQUETE_RESULT_ID + "," + EnqueteResultTable.FULL_COL_USER_ID + "," + EnqueteResultTable.FULL_COL_CREATE_DATE;
	}
	
	public void searchByEnqueteResultId(Connection conn, long nEnqueteResultId) throws Exception {
		
		PreparedStatement stmt = null;
		String sql = "select " + getCols() + " from " + EnqueteResultTable.TABLE_NAME +
						" where " + EnqueteResultTable.FULL_COL_ENQUETE_RESULT_ID + "=?"; 
		
		stmt = conn.prepareStatement(sql);
		stmt.setLong(1, nEnqueteResultId);
		ResultSet rs = stmt.executeQuery();
		
		result_ = rs;
		statement_ = stmt;
	
	}
	
	public void searchByUserId(Connection conn, String strUserId) throws Exception {
		
		PreparedStatement stmt = null;
		String sql = "select " + getCols() + " from " + EnqueteResultTable.TABLE_NAME +
						" where " + EnqueteResultTable.FULL_COL_USER_ID + "=?"; 
		
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, strUserId);
		ResultSet rs = stmt.executeQuery();
		
		result_ = rs;
		statement_ = stmt;
	
	}
	
}