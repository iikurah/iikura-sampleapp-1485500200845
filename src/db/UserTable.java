package db;

/**
 * ユーザー情報テーブル
 * @author Hisayoshi IIKURA
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserTable extends Table {

	/** テーブル名 */
	public static final String TABLE_NAME = "ENQ_USER";
	/** ユーザーIDカラム名 */
	public static final String COL_USER_ID = "USER_ID";
	/** ユーザー名カラム名 */
	public static final String COL_USER_NAME = "USER_NAME";
	/** パスワードカラム名 */
	public static final String COL_PASSWORD = "PASSWORD";
	
	/** ユーザーIDカラム名(FULL) */
	public static final String FULL_COL_USER_ID = TABLE_NAME + "." + COL_USER_ID;
	/** ユーザー名カラム名(FULL) */
	public static final String FULL_COL_USER_NAME = TABLE_NAME + "." + COL_USER_NAME;
	/** パスワードカラム名(FULL) */
	public static final String FULL_COL_PASSWORD = TABLE_NAME + "." + COL_PASSWORD;

	
	/**
	 * 活動内容を登録します。
	 * @param conn DBコネクション 
	 * @param items 活動内容入力情報
	 * @return 活動内容コード
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
	 * 活動内容を更新します。
	 * @param conn DBコネクション 
	 * @param items 活動内容入力情報
	 * @param nActionCode 活動内容コード
	 * @return 活動内容コード
	 * @throws SQLException
	 */
	/*public static long update(Connection conn, CustomizeRecItem[] items, long nActionCode) throws SQLException {

		return update(conn, items, TABLE_NAME, COL_ACTION_CODE, nActionCode);
	}*/	
	
	/**
	 * 活動内容を削除します。
	 * @param conn DBコネクション
	 * @param nActionCode 活動内容コード
	 * @return　活動内容コード
	 * @throws SQLException
	 */
	/*public static long delete(Connection conn, long nActionCode) throws SQLException {
		
		return delete(conn, TABLE_NAME, COL_ACTION_CODE, nActionCode);
	}*/
	
	/**
	 * 活動内容の順番を変更します。
	 * @param conn　DBコネクション
	 * @param nActionCode 活動内容コード
	 * @param bGoUp 順序を上に移動するか  true:上に移動 false:下に移動
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
	 * 表示順を更新します。
	 * @param conn DBコネクション
	 * @param nOrder 表示順
	 * @param nActionCode 活動内容コード
	 * @return 活動内容コード
	 * @throws SQLException
	 */
	/*public static long updateOrder(Connection conn,
									long nOrder,
									long nActionCode) throws SQLException {
		
		return updateOrder(conn, TABLE_NAME, COL_ACTION_CODE, COL_ACTION_ORDER, nOrder, nActionCode);
	}*/
}