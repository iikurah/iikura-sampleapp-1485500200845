package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * アンケート項目情報テーブル
 * @author Hisayoshi IIKURA
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EnqueteItemTable extends Table {

	/** テーブル名 */
	public static final String TABLE_NAME = "ENQUETE_ITEM";
	/** 項目IDカラム名 */
	public static final String COL_ITEM_ID = "ITEM_ID";
	/** 項目名カラム名 */
	public static final String COL_ITEM_NAME = "ITEM_NAME";
	/** カテゴリーフラグカラム名 */
	public static final String COL_CATEGORY_FLG = "CATEGORY_FLG";
	
	/** 項目IDカラム名(FULL) */
	public static final String FULL_COL_ITEM_ID = TABLE_NAME + "." + COL_ITEM_ID;
	/** 項目名カラム名(FULL) */
	public static final String FULL_COL_ITEM_NAME = TABLE_NAME + "." + COL_ITEM_NAME;
	/** カテゴリーフラグカラム名(FULL) */
	public static final String FULL_COL_CATEGORY_FLG = TABLE_NAME + "." + COL_CATEGORY_FLG;

	
	/**
	 * アンケート項目を登録します。
	 * @param conn DBコネクション 
	 * @param strItemName アンケート項目名
	 * @param nCategoryFlg カテゴリー識別子
	 * @return アンケート項目ID
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
	 * アンケート項目を更新します。
	 * @param conn DBコネクション 
	 * @param strItemName アンケート項目名
	 * @param nCategoryFlg カテゴリー識別子
	 * @param nItemId アンケート項目ID
	 * @return アンケート項目ID
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
	 * アンケート項目を削除します。
	 * @param conn DBコネクション
	 * @param nItemId アンケートID
	 * @return アンケートID
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
	 * アンケート項目の順番を変更します。
	 * @param conn　DBコネクション
	 * @param nItemId 活動内容コード
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