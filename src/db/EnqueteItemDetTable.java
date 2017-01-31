package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * �A���P�[�g���ڑI�������e�[�u��
 * @author Hisayoshi IIKURA
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EnqueteItemDetTable extends Table {

	/** �e�[�u���� */
	public static final String TABLE_NAME = "ENQUETE_ITEM_DET";
	/** ����ID�J������ */
	public static final String COL_ITEM_ID = "ITEM_ID";
	/** �I����ID�J������ */
	public static final String COL_ITEM_DET_ID = "ITEM_DET_ID";
	/** �I�������J������ */
	public static final String COL_ITEM_DET_NAME = "ITEM_DET_NAME";
	/** �d�ݕt���J������ */
	public static final String COL_POINT = "POINT";
	
	/** ����ID�J������(FULL) */
	public static final String FULL_COL_ITEM_ID = TABLE_NAME + "." + COL_ITEM_ID;
	/** ����ID�J������(FULL) */
	public static final String FULL_COL_ITEM_DET_ID = TABLE_NAME + "." + COL_ITEM_DET_ID;
	/** �I�������J������(FULL) */
	public static final String FULL_COL_ITEM_DET_NAME = TABLE_NAME + "." + COL_ITEM_DET_NAME;
	/** �d�ݕt���J������(FULL) */
	public static final String FULL_COL_POINT = TABLE_NAME + "." + COL_POINT;

	
	/**
	 * �A���P�[�g���ڑI������o�^���܂��B
	 * @param conn DB�R�l�N�V���� 
	 * @param items �������e���͏��
	 * @return �������e�R�[�h
	 * @throws SQLException
	 */
	public static long insert(Connection conn, long nItemId, String strItemDetName, long nPoint) throws SQLException {

		String strSql = "insert into " + TABLE_NAME + "(" + COL_ITEM_DET_ID + 
								"," + COL_ITEM_ID + "," + COL_ITEM_DET_NAME + "," + COL_POINT + ") " +
								"values(?,?,?,?)";

		long nNextCode = getNextCode(conn, COL_ITEM_DET_ID, TABLE_NAME);
		PreparedStatement stmt = conn.prepareStatement(strSql);
		stmt.setLong(1, nNextCode);
		stmt.setLong(2, nItemId);
		stmt.setString(3, strItemDetName);
		stmt.setLong(4, nPoint);
		
		try{
			stmt.executeUpdate();		
		}finally{
			if(stmt!=null) stmt.close();
		}
		
		return nNextCode;
	}	

	/**
	 * �������e���X�V���܂��B
	 * @param conn DB�R�l�N�V���� 
	 * @param items �������e���͏��
	 * @param nActionCode �������e�R�[�h
	 * @return �������e�R�[�h
	 * @throws SQLException
	 */
	public static long update(Connection conn, String strItemDetName, long nPoint, long nItemDetId) throws SQLException {

		String strSql = "update " + TABLE_NAME + " set " + 
							COL_ITEM_DET_NAME + "=?, " + COL_POINT + "=? " +
							"where " + COL_ITEM_DET_ID + "=?"; 
		
		PreparedStatement stmt = conn.prepareStatement(strSql);
		
		stmt.setString(1, strItemDetName);
		stmt.setLong(2, nPoint);
		stmt.setLong(3, nItemDetId);
		
		try{
			stmt.executeUpdate();		
		}finally{
			if(stmt!=null) stmt.close();
		}
		
		return nItemDetId;
	}	
	
	/**
	 * �A���P�[�g���ڑI�������폜���܂��B
	 * @param conn DB�R�l�N�V����
	 * @param nItemDetId �A���P�[�g���ڑI����ID
	 * @return �A���P�[�g���ڑI����ID
	 * @throws SQLException
	 */
	public static long delete(Connection conn, long nItemDetId) throws SQLException {
		
			String strSql = "delete from " + TABLE_NAME + " where " + COL_ITEM_DET_ID  + "=?";
		
		PreparedStatement stmt = conn.prepareStatement(strSql);
		stmt.setLong(1, nItemDetId);
		
		try{
			stmt.executeUpdate();		
		}finally{
			if(stmt!=null) stmt.close();
		}
		
		return nItemDetId;
	}
	
	/**
	 * �������e�̏��Ԃ�ύX���܂��B
	 * @param conn�@DB�R�l�N�V����
	 * @param nActionCode �������e�R�[�h
	 * @param bGoUp ��������Ɉړ����邩  true:��Ɉړ� false:���Ɉړ�
	 * @throws SQLException
	 */
	/*public static long updateOrder(Connection conn, 
								long nActionCode, 
								boolean bGoUp) throws SQLException, Exception {

		PreparedStatement stmt = null;
		int nActionOrder = 0;

		try{
			int index = 0;
			UserRec[] actRecs = UserRec.loadAll(conn);
			for(int iAct=0; iAct<actRecs.length; iAct++) {
				if(actRecs[iAct].getActionCode()==nActionCode) index = iAct;
			}

			if(bGoUp && index==0) return 0;
			if(!bGoUp && index==actRecs.length-1) return 0;

			if(bGoUp){
				updateOrder(conn, actRecs[index-1].getActionCode(), actRecs[index].getActionCode());
			}else{
				updateOrder(conn, actRecs[index].getActionCode(), actRecs[index+1].getActionCode());
			}
		}finally{
			if(stmt!=null) stmt.close();
		}
		return nActionCode;
	}*/
	
}