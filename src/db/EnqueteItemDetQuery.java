package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EnqueteItemDetQuery extends Query {

	private String getCols() {
		return EnqueteItemDetTable.FULL_COL_ITEM_ID + "," + EnqueteItemDetTable.FULL_COL_ITEM_DET_ID + "," 
				+ EnqueteItemDetTable.FULL_COL_ITEM_DET_NAME + "," + EnqueteItemDetTable.FULL_COL_POINT;
	}
	
	/**
	 * �A�C�e���I����ID����A�C�e���I����������������
	 * @param conn DB�R�l�N�V����
	 * @param nItemDetId �A�C�e���I����ID
	 * @throws Exception
	 */
	public void searchByItemDetId(Connection conn, long nItemDetId) throws Exception {
		
		PreparedStatement stmt = null;
		String sql = "select " + getCols() + " from " + EnqueteItemDetTable.TABLE_NAME +
						" where " + EnqueteItemDetTable.FULL_COL_ITEM_DET_ID + "=?"; 
		
		stmt = conn.prepareStatement(sql);
		stmt.setLong(1, nItemDetId);
		ResultSet rs = stmt.executeQuery();
		
		result_ = rs;
		statement_ = stmt;
	
	}
	
	public void searchByItemId(Connection conn, long nItemId) throws Exception {
		
		PreparedStatement stmt = null;
		String sql = "select " + getCols() + " from " + EnqueteItemDetTable.TABLE_NAME + 
						" where " + EnqueteItemDetTable.FULL_COL_ITEM_ID + "=?"; 
		
		stmt = conn.prepareStatement(sql);
		stmt.setLong(1, nItemId);
		ResultSet rs = stmt.executeQuery();
		
		result_ = rs;
		statement_ = stmt;
	
	}

	
	public void searchAll(Connection conn) throws Exception {
	
		PreparedStatement stmt = null;
		String sql = "select " + getCols() + " from " + EnqueteItemDetTable.TABLE_NAME;
					
		stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		result_ = rs;
		statement_ = stmt;
	
	}

	
	
}