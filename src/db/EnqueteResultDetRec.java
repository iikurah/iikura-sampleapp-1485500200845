package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.Date;

public class EnqueteResultDetRec extends Record {
	
	/** アンケート回答項目ID */
	private long nEnqueteResultDetId_;
	/** アンケート回答ID */
	private long nEnqueteResultId_;
	/** 項目ID */
	private long nItemId_;
	/** 選択肢ID */
	private long nItemDetId_;
	
	/**
	 * アンケート回答項目IDを取得します。
	 * @return　アンケート回答項目ID
	 */
    public long getEnqueteResultDetId() {
    	return nEnqueteResultDetId_;
    }
	/**
	 * アンケート回答IDを取得します。
	 * @return　アンケート回答ID
	 */
    public long getEnqueteResultId() {
    	return nEnqueteResultId_;
    }
	/**
	 * 項目IDを取得します。
	 * @return　項目ID
	 */
    public long getItemId() {
    	return nItemId_;
    }
    /**
	 * 選択肢IDを取得します。
	 * @return　選択肢ID
	 */
    public long getItemDetId() {
    	return nItemDetId_;
    }
  
    
	
	public static EnqueteResultDetRec[] loadByEnqueteResultId(Connection conn, long nEnqueteResultId) throws Exception {
		
		EnqueteResultDetQuery erdq = new EnqueteResultDetQuery();
		Vector vec = new Vector();
		
		try{
			erdq.searchByEnqueteResultId(conn, nEnqueteResultId);
		
			while(erdq.next()) {
				EnqueteResultDetRec erdRec = new EnqueteResultDetRec();
				erdRec.nEnqueteResultDetId_ = erdq.result_.getLong(EnqueteResultDetTable.COL_ENQUETE_RESULT_DET_ID);
				erdRec.nEnqueteResultId_ = erdq.result_.getLong(EnqueteResultDetTable.COL_ENQUETE_RESULT_ID);
				erdRec.nItemId_ = erdq.result_.getLong(EnqueteResultDetTable.COL_ITEM_ID);
				erdRec.nItemDetId_ = erdq.result_.getLong(EnqueteResultDetTable.COL_ITEM_DET_ID);
				vec.add(erdRec);
			}
		
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally{
			if(erdq!=null) erdq.close();
		}
		
		EnqueteResultDetRec[] erdRecs = new EnqueteResultDetRec[vec.size()];
		return (EnqueteResultDetRec[])vec.toArray(erdRecs);
		
	}
	
	public static EnqueteResultDetRec searchResultDetRecByItemId(EnqueteResultDetRec[] resultDetRecs, long nItemId) {
		
		if(resultDetRecs==null) return null;
		
		for(int i=0; i<resultDetRecs.length; i++) {
			if(resultDetRecs[i].getItemId()==nItemId) {
				return resultDetRecs[i];
			}
		}
		
		return null;
		
	}
	

}

