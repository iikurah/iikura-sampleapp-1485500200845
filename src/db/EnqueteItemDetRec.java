package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

public class EnqueteItemDetRec extends Record {
	
	/** �I����ID */
	private long nItemDetId_;
	/** ����ID */
	private long nItemId_;
	/** �I������ */
	private String strItemDetName_;
	/** �d�ݕt�� */
	private long nPoint_;
	
	/**
	 * �I����ID���擾���܂��B
	 * @return�@�I����ID
	 */
    public long getItemDetId() {
    	return nItemDetId_;
    }
	/**
	 * ����ID���擾���܂��B
	 * @return�@����ID
	 */
    public long getItemId() {
    	return nItemId_;
    }
    /**
	 * �I���������擾���܂��B
	 * @return�@�I������
	 */
    public String getItemDetName() {
    	return strItemDetName_;
    }
    /**
	 * �d�ݕt�����擾���܂��B
	 * @return�@�d�ݕt��
	 */
    public long getPoint() {
    	return nPoint_;
    }
    
    /**
	 * �I����ID���w�肵�đI���������擾���܂��B
	 * @param conn DB�R�l�N�V����
	 * @param nItemDetId �I����ID
	 * @return �I�������
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
	 * ����ID���w�肵�č��ڏ����擾���܂��B
	 * @param conn DB�R�l�N�V����
	 * @param nItemId ����ID
	 * @return ���ڏ��
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

