package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserQuery extends Query {

	
	private String getCols() {
		return UserTable.FULL_COL_USER_ID + "," + UserTable.FULL_COL_USER_NAME + "," + UserTable.FULL_COL_PASSWORD;
	}
	
	public void searchByUserId(Connection conn, String strUserId) throws Exception {
		
		PreparedStatement stmt = null;
		String sql = "select " + getCols() + " from " + UserTable.TABLE_NAME +
						" where " + UserTable.FULL_COL_USER_ID + "=?"; 
		
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, strUserId);
		ResultSet rs = stmt.executeQuery();
		
		result_ = rs;
		statement_ = stmt;
	
	}
	
	
}