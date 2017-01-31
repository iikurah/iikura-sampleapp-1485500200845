package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

public class EnqueteItemRec extends Record {
	
	/** ����ID */
	private long nItemId_;
	/** ���ږ� */
	private String strItemName_;
	/** �J�e�S���[ID */
	private long nCategoryId_;
	
	/**
	 * ����ID���擾���܂��B
	 * @return�@����ID
	 */
    public long getItemId() {
    	return nItemId_;
    }
    /**
	 * ���ږ����擾���܂��B
	 * @return�@���ږ�
	 */
    public String getItemName() {
    	return strItemName_;
    }
    /**
	 * �J�e�S���[ID���擾���܂��B
	 * @return�@�J�e�S���[ID
	 */
    public long getCategoryId() {
    	return nCategoryId_;
    }
    
    
	/**
	 * ����ID���w�肵�č��ڏ����擾���܂��B
	 * @param conn DB�R�l�N�V����
	 * @param nItemId ����ID
	 * @return ���ڏ��
	 * @throws Exception 
	 */
	public static EnqueteItemRec loadByItemId(Connection conn, long nItemId) throws Exception {
	
		EnqueteItemQuery eiq = new EnqueteItemQuery();
		
		try{
			eiq.searchByItemId(conn, nItemId);
		
			if(eiq.next()) {
				EnqueteItemRec eiRec = new EnqueteItemRec();
				eiRec.nItemId_ = eiq.result_.getLong(EnqueteItemTable.COL_ITEM_ID);
				eiRec.strItemName_ = eiq.result_.getString(EnqueteItemTable.COL_ITEM_NAME);
				eiRec.nCategoryId_ = eiq.result_.getLong(EnqueteItemTable.COL_CATEGORY_FLG);
				
				return eiRec;
			}
		
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally{
			if(eiq!=null) eiq.close();
		}
		
		return null;
	}
	
	public static EnqueteItemRec[] loadAll(Connection conn) throws Exception {
		
		EnqueteItemQuery eiq = new EnqueteItemQuery();
		Vector vec = new Vector();
		
		try{
			eiq.searchAll(conn);
		
			while(eiq.next()) {
				EnqueteItemRec eiRec = new EnqueteItemRec();
				eiRec.nItemId_ = eiq.result_.getLong(EnqueteItemTable.COL_ITEM_ID);
				eiRec.strItemName_ = eiq.result_.getString(EnqueteItemTable.COL_ITEM_NAME);
				eiRec.nCategoryId_ = eiq.result_.getLong(EnqueteItemTable.COL_CATEGORY_FLG);
				vec.add(eiRec);
			}
		
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally{
			if(eiq!=null) eiq.close();
		}
		
		EnqueteItemRec[] eiRecs = new EnqueteItemRec[vec.size()];
		return (EnqueteItemRec[])vec.toArray(eiRecs);
	}
	
	public static EnqueteItemRec[] searchByCategoryId(EnqueteItemRec[] itemRecs, long nCategoryId) {
		
		if(itemRecs==null) return new EnqueteItemRec[0];
		
		Vector vec = new Vector();
		
		for(int i=0; i<itemRecs.length; i++) {
			if(itemRecs[i].getCategoryId()==nCategoryId) {
				vec.add(itemRecs[i]);
			}
		}
		
		EnqueteItemRec[] searchedItemRecs = new EnqueteItemRec[vec.size()];
		return (EnqueteItemRec[])vec.toArray(searchedItemRecs);
		
	}

}

