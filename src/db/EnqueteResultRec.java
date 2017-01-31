package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.Date;

public class EnqueteResultRec extends Record {
	
	/** �A���P�[�g��ID */
	private long nEnqueteResultId_;
	/** ���[�U�[ID */
	private String strUserId_;
	/** �񓚓��� */
	private Date dCreateDate_;
	
	/**
	 * �A���P�[�g��ID���擾���܂��B
	 * @return�@�A���P�[�g��ID
	 */
    public long getEnqueteResultId() {
    	return nEnqueteResultId_;
    }
	/**
	 * ���[�U�[ID���擾���܂��B
	 * @return�@���[�U�[ID
	 */
    public String getUserId() {
    	return strUserId_;
    }
    /**
	 * �A���P�[�g�񓚓������擾���܂��B
	 * @return�@�A���P�[�g�񓚓���
	 */
    public Date getCreateDate() {
    	return dCreateDate_;
    }
  
    
	/**
	 * ���[�U�[ID���w�肵�ăA���P�[�g�񓚌��ʂ��擾���܂��B
	 * @param conn DB�R�l�N�V����
	 * @param strUserId ���[�U�[ID
	 * @return �A���P�[�g�񓚌���
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

