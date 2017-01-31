package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * �A���P�[�g���ڏ��e�[�u��
 * @author Hisayoshi IIKURA
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EnqueteItemTable extends Table {

	/** �e�[�u���� */
	public static final String TABLE_NAME = "ENQUETE_ITEM";
	/** ����ID�J������ */
	public static final String COL_ITEM_ID = "ITEM_ID";
	/** ���ږ��J������ */
	public static final String COL_ITEM_NAME = "ITEM_NAME";
	/** �J�e�S���[�t���O�J������ */
	public static final String COL_CATEGORY_FLG = "CATEGORY_FLG";
	
	/** ����ID�J������(FULL) */
	public static final String FULL_COL_ITEM_ID = TABLE_NAME + "." + COL_ITEM_ID;
	/** ���ږ��J������(FULL) */
	public static final String FULL_COL_ITEM_NAME = TABLE_NAME + "." + COL_ITEM_NAME;
	/** �J�e�S���[�t���O�J������(FULL) */
	public static final String FULL_COL_CATEGORY_FLG = TABLE_NAME + "." + COL_CATEGORY_FLG;

	
	/**
	 * �A���P�[�g���ڂ�o�^���܂��B
	 * @param conn DB�R�l�N�V���� 
	 * @param strItemName �A���P�[�g���ږ�
	 * @param nCategoryFlg �J�e�S���[���ʎq
	 * @return �A���P�[�g����ID
	 * @throws SQLException
	 */
	public static long insert(Connection conn, String strItemName, long nCategoryFlg) throws SQLException {

		String strSql = "insert into " + TABLE_NAME + "(" + COL_ITEM_ID + 
							"," + COL_ITEM_NAME + "," + COL_CATEGORY_FLG + ") " +
							"values(?,?,?)";
		
		long nNextCode = getNextCode(conn, COL_ITEM_ID, TABLE_NAME);
		PreparedStatement stmt = conn.prepareStatement(strSql);
		stmt.setLong(1, nNextCode);
		stmt.setString(2, strItemName);
		stmt.setLong(3, nCategoryFlg);
		
		try{
			stmt.executeUpdate();		
		}finally{
			if(stmt!=null) stmt.close();
		}
		
		return nNextCode;
	}	

	/**
	 * �A���P�[�g���ڂ��X�V���܂��B
	 * @param conn DB�R�l�N�V���� 
	 * @param strItemName �A���P�[�g���ږ�
	 * @param nCategoryFlg �J�e�S���[���ʎq
	 * @param nItemId �A���P�[�g����ID
	 * @return �A���P�[�g����ID
	 * @throws SQLException
	 */
	public static long update(Connection conn, String strItemName, long nCategoryFlg, long nItemId) throws SQLException {

		String strSql = "update " + TABLE_NAME + " set " + 
							COL_ITEM_NAME + "=?, " + COL_CATEGORY_FLG + "=? " +
							"where " + COL_ITEM_ID + "=?"; 
		
		PreparedStatement stmt = conn.prepareStatement(strSql);
		
		stmt.setString(1, strItemName);
		stmt.setLong(2, nCategoryFlg);
		stmt.setLong(3, nItemId);
		
		try{
			stmt.executeUpdate();		
		}finally{
			if(stmt!=null) stmt.close();
		}
		
		return nItemId;
	}	
	
	/**
	 * �A���P�[�g���ڂ��폜���܂��B
	 * @param conn DB�R�l�N�V����
	 * @param nItemId �A���P�[�gID
	 * @return �A���P�[�gID
	 * @throws SQLException
	 */
	public static long delete(Connection conn, long nItemId) throws SQLException {
		
		String strSql = "delete from " + TABLE_NAME + " where " + COL_ITEM_ID  + "=?";
		
		PreparedStatement stmt = conn.prepareStatement(strSql);
		stmt.setLong(1, nItemId);
		
		try{
			stmt.executeUpdate();		
		}finally{
			if(stmt!=null) stmt.close();
		}
		
		return nItemId;
	}
	
	/**
	 * �A���P�[�g���ڂ̏��Ԃ�ύX���܂��B
	 * @param conn�@DB�R�l�N�V����
	 * @param nItemId �������e�R�[�h
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
	
	/**
	 * �\�������X�V���܂��B
	 * @param conn DB�R�l�N�V����
	 * @param nOrder �\����
	 * @param nActionCode �������e�R�[�h
	 * @return �������e�R�[�h
	 * @throws SQLException
	 */
	/*public static long updateOrder(Connection conn,
									long nOrder,
									long nActionCode) throws SQLException {
		
		return updateOrder(conn, TABLE_NAME, COL_ACTION_CODE, COL_ACTION_ORDER, nOrder, nActionCode);
	}*/
}