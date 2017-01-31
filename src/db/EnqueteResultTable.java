package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * �A���P�[�g�񓚌��ʃe�[�u��
 * @author Hisayoshi IIKURA
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EnqueteResultTable extends Table {

	/** �e�[�u���� */
	public static final String TABLE_NAME = "ENQUETE_RESULT";
	/** �A���P�[�g��ID�J������ */
	public static final String COL_ENQUETE_RESULT_ID = "ENQUETE_RESULT_ID";
	/** ���[�U�[ID�J������ */
	public static final String COL_USER_ID = "USER_ID";
	/** �񓚓����J������ */
	public static final String COL_CREATE_DATE = "CREATE_DATE";
	
	/** �A���P�[�g��ID�J������(FULL) */
	public static final String FULL_COL_ENQUETE_RESULT_ID = TABLE_NAME + "." + COL_ENQUETE_RESULT_ID;
	/** ���[�U�[ID�J������(FULL) */
	public static final String FULL_COL_USER_ID = TABLE_NAME + "." + COL_USER_ID;
	/** �񓚓����J������(FULL) */
	public static final String FULL_COL_CREATE_DATE = TABLE_NAME + "." + COL_CREATE_DATE;
	

	/**
	 * �������e��o�^���܂��B
	 * @param conn DB�R�l�N�V���� 
	 * @param items �������e���͏��
	 * @return �������e�R�[�h
	 * @throws SQLException
	 */
	public static long insert(Connection conn, String strUserId) throws SQLException {
		
		String strSql = "insert into " + TABLE_NAME + "(" + COL_ENQUETE_RESULT_ID + 
							"," + COL_USER_ID + "," + COL_CREATE_DATE + ") " +
							"values(?,?,?)";
	
		PreparedStatement stmt = conn.prepareStatement(strSql);
		
		long nNextCode = getNextCode(conn, COL_ENQUETE_RESULT_ID, TABLE_NAME);
		stmt.setLong(1, nNextCode);
		stmt.setString(2, strUserId);
		stmt.setDate(3, convert(new java.util.Date()));
		
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
	/*public static long delete(Connection conn, long nActionCode) throws SQLException {
		
		return delete(conn, TABLE_NAME, COL_ACTION_CODE, nActionCode);
	}*/
	
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