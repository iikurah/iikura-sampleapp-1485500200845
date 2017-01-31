package db;

/**
 * ���[�U�[���e�[�u��
 * @author Hisayoshi IIKURA
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserTable extends Table {

	/** �e�[�u���� */
	public static final String TABLE_NAME = "ENQ_USER";
	/** ���[�U�[ID�J������ */
	public static final String COL_USER_ID = "USER_ID";
	/** ���[�U�[���J������ */
	public static final String COL_USER_NAME = "USER_NAME";
	/** �p�X���[�h�J������ */
	public static final String COL_PASSWORD = "PASSWORD";
	
	/** ���[�U�[ID�J������(FULL) */
	public static final String FULL_COL_USER_ID = TABLE_NAME + "." + COL_USER_ID;
	/** ���[�U�[���J������(FULL) */
	public static final String FULL_COL_USER_NAME = TABLE_NAME + "." + COL_USER_NAME;
	/** �p�X���[�h�J������(FULL) */
	public static final String FULL_COL_PASSWORD = TABLE_NAME + "." + COL_PASSWORD;

	
	/**
	 * �������e��o�^���܂��B
	 * @param conn DB�R�l�N�V���� 
	 * @param items �������e���͏��
	 * @return �������e�R�[�h
	 * @throws SQLException
	 */
	/*public static long insert(Connection conn, CustomizeRecItem[] items) throws SQLException {

		LoginUserGroup lug = LoginUserGroup.getInstance();
		CustomizeRecItemLong actCode = new CustomizeRecItemLong();
		actCode.setExtRec(lug.searchExtInfoRecByExtCode(EXTCODE_ACTION_CODE));
		actCode.setValue(new Long(0));
		
		items = CustomizeRecItem.merge(new CustomizeRecItem[]{actCode}, items);
		return insert(conn, items, TABLE_NAME, COL_ACTION_CODE, COL_ACTION_ORDER);
	}*/	

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