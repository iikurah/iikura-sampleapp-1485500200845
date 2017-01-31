package db;

import java.sql.Connection;
import java.sql.SQLException;

public class UserRec extends Record {
	
	/** ユーザーID */
	private String strUserId_;
	/** ユーザー名 */
	private String strUserName_;
	/** パスワード */
	private String strPassword_;
	
	/**
	 * ユーザーIDを取得します。
	 * @return　ユーザーID
	 */
    public String getUserId() {
    	return strUserId_;
    }
    /**
	 * ユーザー名を取得します。
	 * @return　ユーザー名
	 */
    public String getUserName() {
    	return strUserName_;
    }
    /**
	 * パスワードを取得します。
	 * @return　パスワード
	 */
    public String getPassword() {
    	return strPassword_;
    }
    
    
	/**
	 * ユーザーIDを指定してユーザー情報を取得します。
	 * @param conn DBコネクション
	 * @param strUserId ユーザーID
	 * @return ユーザー情報
	 * @throws BPMException 
	 */
	public static UserRec loadByUserId(Connection conn, String strUserId) throws Exception {
	
		UserQuery uq = new UserQuery();
		
		try{
		uq.searchByUserId(conn, strUserId);
		
		if(uq.next()) {
			UserRec userRec = new UserRec();
			userRec.strUserId_ = uq.result_.getString(UserTable.COL_USER_ID);
			userRec.strUserName_ = uq.result_.getString(UserTable.COL_USER_NAME);
			userRec.strPassword_ = uq.result_.getString(UserTable.COL_PASSWORD);
			return userRec;
		}
		
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally{
			if(uq!=null) uq.close();
		}
		
		return null;
	}

}

