package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EnqueteItemQuery extends Query {

	
	private String getCols() {
		return EnqueteItemTable.FULL_COL_ITEM_ID + "," + EnqueteItemTable.FULL_COL_ITEM_NAME + "," + EnqueteItemTable.FULL_COL_CATEGORY_FLG;
	}
	
	/**
	 * �A�C�e��ID����A�C�e��������������
	 * @param conn DB�R�l�N�V����
	 * @param nItemId �A�C�e��ID
	 * @throws Exception
	 */
	public void searchByItemId(Connection conn, long nItemId) throws Exception {
		
		PreparedStatement stmt = null;
		String sql = "select " + getCols() + " from " + EnqueteItemTable.TABLE_NAME +
						" where " + EnqueteItemTable.FULL_COL_ITEM_ID + "=?"; 
		
		stmt = conn.prepareStatement(sql);
		stmt.setLong(1, nItemId);
		ResultSet rs = stmt.executeQuery();
		
		result_ = rs;
		statement_ = stmt;
	
	}
	
	public void searchAll(Connection conn) throws Exception {
		
		PreparedStatement stmt = null;
		String sql = "select " + getCols() + " from " + EnqueteItemTable.TABLE_NAME;
						
		stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		result_ = rs;
		statement_ = stmt;
	
	}

	
	
}