package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EnqueteResultDetQuery extends Query {

	
	private String getCols() {
		return EnqueteResultDetTable.FULL_COL_ENQUETE_RESULT_ID + "," + EnqueteResultDetTable.FULL_COL_ENQUETE_RESULT_DET_ID + "," + 
				EnqueteResultDetTable.FULL_COL_ITEM_ID + "," + EnqueteResultDetTable.FULL_COL_ITEM_DET_ID;
	}
	
	public void searchByEnqueteResultDetId(Connection conn, long nEnqueteResultDetId) throws Exception {
		
		PreparedStatement stmt = null;
		String sql = "select " + getCols() + " from " + EnqueteResultDetTable.TABLE_NAME +
						" where " + EnqueteResultDetTable.FULL_COL_ENQUETE_RESULT_DET_ID + "=?"; 
		
		stmt = conn.prepareStatement(sql);
		stmt.setLong(1, nEnqueteResultDetId);
		ResultSet rs = stmt.executeQuery();
		
		result_ = rs;
		statement_ = stmt;
	
	}

	public void searchByEnqueteResultId(Connection conn, long nEnqueteResultId) throws Exception {
		
		PreparedStatement stmt = null;
		String sql = "select " + getCols() + " from " + EnqueteResultDetTable.TABLE_NAME +
						" where " + EnqueteResultDetTable.FULL_COL_ENQUETE_RESULT_ID + "=?"; 
		
		stmt = conn.prepareStatement(sql);
		stmt.setLong(1, nEnqueteResultId);
		ResultSet rs = stmt.executeQuery();
		
		result_ = rs;
		statement_ = stmt;
	
	}
	
}