package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

public class EnqueteItemDetRec extends Record {
	
	/** 選択肢ID */
	private long nItemDetId_;
	/** 項目ID */
	private long nItemId_;
	/** 選択肢名 */
	private String strItemDetName_;
	/** 重み付け */
	private long nPoint_;
	
	/**
	 * 選択肢IDを取得します。
	 * @return　選択肢ID
	 */
    public long getItemDetId() {
    	return nItemDetId_;
    }
	/**
	 * 項目IDを取得します。
	 * @return　項目ID
	 */
    public long getItemId() {
    	return nItemId_;
    }
    /**
	 * 選択肢名を取得します。
	 * @return　選択肢名
	 */
    public String getItemDetName() {
    	return strItemDetName_;
    }
    /**
	 * 重み付けを取得します。
	 * @return　重み付け
	 */
    public long getPoint() {
    	return nPoint_;
    }
    
    /**
	 * 選択肢IDを指定して選択肢情報を取得します。
	 * @param conn DBコネクション
	 * @param nItemDetId 選択肢ID
	 * @return 選択肢情報
	 * @throws Exception 
	 */
	public static EnqueteItemDetRec loadByItemDetId(Connection conn, long nItemDetId) throws Exception {
	
		EnqueteItemDetQuery eidq = new EnqueteItemDetQuery();
		
		try{
			eidq.searchByItemDetId(conn, nItemDetId);
		
			if(eidq.next()) {
				EnqueteItemDetRec eidRec = new EnqueteItemDetRec();
				eidRec.nItemDetId_ = eidq.result_.getLong(EnqueteItemDetTable.COL_ITEM_DET_ID);
				eidRec.nItemId_ = eidq.result_.getLong(EnqueteItemDetTable.COL_ITEM_ID);
				eidRec.strItemDetName_ = eidq.result_.getString(EnqueteItemDetTable.COL_ITEM_DET_NAME);
				eidRec.nPoint_ = eidq.result_.getLong(EnqueteItemDetTable.COL_POINT);
				
			}
		
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally{
			if(eidq!=null) eidq.close();
		}
		
		return null;
	}
	
	/**
	 * 項目IDを指定して項目情報を取得します。
	 * @param conn DBコネクション
	 * @param nItemId 項目ID
	 * @return 項目情報
	 * @throws Exception 
	 */
	public static EnqueteItemDetRec[] loadByItemId(Connection conn, long nItemId) throws Exception {
	
		EnqueteItemDetQuery eidq = new EnqueteItemDetQuery();
		Vector vec = new Vector();
		
		try{
			eidq.searchByItemId(conn, nItemId);
		
			while(eidq.next()) {
				EnqueteItemDetRec eidRec = new EnqueteItemDetRec();
				
				eidRec.nItemDetId_ = eidq.result_.getLong(EnqueteItemDetTable.COL_ITEM_DET_ID);
				eidRec.nItemId_ = eidq.result_.getLong(EnqueteItemDetTable.COL_ITEM_ID);
				eidRec.strItemDetName_ = eidq.result_.getString(EnqueteItemDetTable.COL_ITEM_DET_NAME);
				eidRec.nPoint_ = eidq.result_.getLong(EnqueteItemDetTable.COL_POINT);
				
				vec.add(eidRec);
			}
		
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally{
			if(eidq!=null) eidq.close();
		}
		
		EnqueteItemDetRec[] eidRecs = new EnqueteItemDetRec[vec.size()];
		return (EnqueteItemDetRec[])vec.toArray(eidRecs);
		
	}
	
	public static EnqueteItemDetRec[] loadAll(Connection conn) throws Exception {
		
		EnqueteItemDetQuery eidq = new EnqueteItemDetQuery();
		Vector vec = new Vector();
		
		try{
			eidq.searchAll(conn);
		
			while(eidq.next()) {
				EnqueteItemDetRec eidRec = new EnqueteItemDetRec();
				
				eidRec.nItemDetId_ = eidq.result_.getLong(EnqueteItemDetTable.COL_ITEM_DET_ID);
				eidRec.nItemId_ = eidq.result_.getLong(EnqueteItemDetTable.COL_ITEM_ID);
				eidRec.strItemDetName_ = eidq.result_.getString(EnqueteItemDetTable.COL_ITEM_DET_NAME);
				eidRec.nPoint_ = eidq.result_.getLong(EnqueteItemDetTable.COL_POINT);
				
				vec.add(eidRec);
			}
		
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally{
			if(eidq!=null) eidq.close();
		}
		
		EnqueteItemDetRec[] eidRecs = new EnqueteItemDetRec[vec.size()];
		return (EnqueteItemDetRec[])vec.toArray(eidRecs);
		
	}
	
	
	public static EnqueteItemDetRec searchEnqueteItemDet(EnqueteItemDetRec[] eidRecs, long nItemId, long nItemDetId) {
		
		if(eidRecs==null) return null;
		
		for(int i=0; i<eidRecs.length; i++) {
			if(eidRecs[i].getItemId()==nItemId && eidRecs[i].getItemDetId()==nItemDetId) {
				return eidRecs[i];
			}
		}
		
		return null;
	}
	
	public static EnqueteItemDetRec[] searchByItemId(EnqueteItemDetRec[] eidRecs, long nItemId) {
		
		if(eidRecs==null) return null;
		
		Vector vec = new Vector();
		
		for(int i=0; i<eidRecs.length; i++) {
			if(eidRecs[i].getItemId()==nItemId) {
				vec.add(eidRecs[i]);
			}
		}
		
		EnqueteItemDetRec[] ret = new EnqueteItemDetRec[vec.size()];
		return (EnqueteItemDetRec[] )vec.toArray(ret);
	}


}

