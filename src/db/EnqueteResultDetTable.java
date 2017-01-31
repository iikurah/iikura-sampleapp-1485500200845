package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * �A���P�[�g�񓚌��ʃe�[�u��
 * @author Hisayoshi IIKURA
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EnqueteResultDetTable extends Table {

	/** �e�[�u���� */
	public static final String TABLE_NAME = "ENQUETE_RESULT_DET";
	/** �A���P�[�g�񓚍���ID�J������ */
	public static final String COL_ENQUETE_RESULT_DET_ID = "ENQUETE_RESULT_DET_ID";
	/** �A���P�[�g��ID�J������ */
	public static final String COL_ENQUETE_RESULT_ID = "ENQUETE_RESULT_ID";
	/** ����ID�J������ */
	public static final String COL_ITEM_ID = "ITEM_ID";
	/** �I�����J������ */
	public static final String COL_ITEM_DET_ID = "ITEM_DET_ID";
	
	/** �A���P�[�g�񓚍���ID�J������(FULL) */
	public static final String FULL_COL_ENQUETE_RESULT_DET_ID = TABLE_NAME + "." + COL_ENQUETE_RESULT_DET_ID;
	/** �A���P�[�g��ID�J������(FULL) */
	public static final String FULL_COL_ENQUETE_RESULT_ID = TABLE_NAME + "." + COL_ENQUETE_RESULT_ID;
	/** ����ID�J������(FULL) */
	public static final String FULL_COL_ITEM_ID = TABLE_NAME + "." + COL_ITEM_ID;
	/** �I�����J������(FULL) */
	public static final String FULL_COL_ITEM_DET_ID = TABLE_NAME + "." + COL_ITEM_DET_ID;
	

	/**
	 * �������e��o�^���܂��B
	 * @param conn DB�R�l�N�V���� 
	 * @param items �������e���͏��
	 * @return �������e�R�[�h
	 * @throws SQLException
	 */
	public static long insert(	Connection conn, 
								long nEnqueteresultId, 
								long nItemId, 
								long nItemDetId) throws SQLException {

		String strSql = "insert into " + TABLE_NAME + "(" + COL_ENQUETE_RESULT_DET_ID + "," +  
				COL_ENQUETE_RESULT_ID + "," + COL_ITEM_ID + "," + COL_ITEM_DET_ID + ") " +
				"values(?,?,?,?)";

		PreparedStatement stmt = conn.prepareStatement(strSql);
		
		long nNextCode = getNextCode(conn, COL_ENQUETE_RESULT_DET_ID, TABLE_NAME);
		stmt.setLong(1, nNextCode);
		stmt.setLong(2, nEnqueteresultId);
		stmt.setLong(3, nItemId);
		stmt.setLong(4, nItemDetId);

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
	/*public static long update(Connection conn, CustomizeRecItem[] items, long nActionCode) throws SQLException {

		return update(conn, items, TABLE_NAME, COL_ACTION_CODE, nActionCode);
	}*/	
	
	/**
	 * �������e���폜���܂��B
	 * @param conn DB�R�l�N�V����
	 * @param nActionCode �������e�R�[�h
	 * @return�@�������e�R�[�h
	 * @throws SQLException
	 */
	public static long deleteByEnqueteResultId(Connection conn, long nEnqueteResultId) throws SQLException {
		
		String strSql = "delete from " + TABLE_NAME + " where " + COL_ENQUETE_RESULT_ID + "=?";
		
		PreparedStatement stmt = conn.prepareStatement(strSql);
		stmt.setLong(1, nEnqueteResultId);
		
		try{
			stmt.executeUpdate();		
		}finally{
			if(stmt!=null) stmt.close();
		}
		
		return nEnqueteResultId;
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