package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.Date;

public class EnqueteResultRec extends Record {
	
	/** アンケート回答ID */
	private long nEnqueteResultId_;
	/** ユーザーID */
	private String strUserId_;
	/** 回答日時 */
	private Date dCreateDate_;
	
	/**
	 * アンケート回答IDを取得します。
	 * @return　アンケート回答ID
	 */
    public long getEnqueteResultId() {
    	return nEnqueteResultId_;
    }
	/**
	 * ユーザーIDを取得します。
	 * @return　ユーザーID
	 */
    public String getUserId() {
    	return strUserId_;
    }
    /**
	 * アンケート回答日時を取得します。
	 * @return　アンケート回答日時
	 */
    public Date getCreateDate() {
    	return dCreateDate_;
    }
  
    
	/**
	 * ユーザーIDを指定してアンケート回答結果を取得します。
	 * @param conn DBコネクション
	 * @param strUserId ユーザーID
	 * @return アンケート回答結果
	 * @throws Exception 
	 */
	public static EnqueteResultRec[] loadByUserId(Connection conn, String strUserId) throws Exception {
	
		EnqueteResultQuery erq = new EnqueteResultQuery();
		Vector vec = new Vector();
		
		try{
			erq.searchByUserId(conn, strUserId);
		
			while(erq.next()) {
				EnqueteResultRec erRec = new EnqueteResultRec();
				erRec.nEnqueteResultId_ = erq.result_.getLong(EnqueteResultTable.COL_ENQUETE_RESULT_ID);
				erRec.strUserId_ = erq.result_.getString(EnqueteResultTable.COL_USER_ID);
				
				Timestamp tCreateDate = erq.result_.getTimestamp(EnqueteResultTable.COL_CREATE_DATE);
				long lCreateDate = tCreateDate.getTime();
				erRec.dCreateDate_ = new Date(lCreateDate);
			
				vec.add(erRec);
			}
		
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally{
			if(erq!=null) erq.close();
		}
		
		EnqueteResultRec[] erRecs = new EnqueteResultRec[vec.size()];
		return (EnqueteResultRec[])vec.toArray(erRecs);
	}
	
	public static EnqueteResultRec loadByEnqueteResultId(Connection conn, long nEnqueteResultId) throws Exception {
		
		EnqueteResultQuery erq = new EnqueteResultQuery();
		
		try{
			erq.searchByEnqueteResultId(conn, nEnqueteResultId);
		
			if(erq.next()) {
				EnqueteResultRec erRec = new EnqueteResultRec();
				erRec.nEnqueteResultId_ = erq.result_.getLong(EnqueteResultTable.COL_ENQUETE_RESULT_ID);
				erRec.strUserId_ = erq.result_.getString(EnqueteResultTable.COL_USER_ID);
				
				Timestamp tCreateDate = erq.result_.getTimestamp(EnqueteResultTable.COL_CREATE_DATE);
				long lCreateDate = tCreateDate.getTime();
				erRec.dCreateDate_ = new Date(lCreateDate);
				return erRec;
			}
		
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally{
			if(erq!=null) erq.close();
		}
		
		return null;
	}
	
}

