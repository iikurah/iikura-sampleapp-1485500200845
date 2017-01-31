package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * アンケート項目選択肢情報テーブル
 * @author Hisayoshi IIKURA
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EnqueteItemDetTable extends Table {

	/** テーブル名 */
	public static final String TABLE_NAME = "ENQUETE_ITEM_DET";
	/** 項目IDカラム名 */
	public static final String COL_ITEM_ID = "ITEM_ID";
	/** 選択肢IDカラム名 */
	public static final String COL_ITEM_DET_ID = "ITEM_DET_ID";
	/** 選択肢名カラム名 */
	public static final String COL_ITEM_DET_NAME = "ITEM_DET_NAME";
	/** 重み付けカラム名 */
	public static final String COL_POINT = "POINT";
	
	/** 項目IDカラム名(FULL) */
	public static final String FULL_COL_ITEM_ID = TABLE_NAME + "." + COL_ITEM_ID;
	/** 項目IDカラム名(FULL) */
	public static final String FULL_COL_ITEM_DET_ID = TABLE_NAME + "." + COL_ITEM_DET_ID;
	/** 選択肢名カラム名(FULL) */
	public static final String FULL_COL_ITEM_DET_NAME = TABLE_NAME + "." + COL_ITEM_DET_NAME;
	/** 重み付けカラム名(FULL) */
	public static final String FULL_COL_POINT = TABLE_NAME + "." + COL_POINT;

	
	/**
	 * アンケート項目選択肢を登録します。
	 * @param conn DBコネクション 
	 * @param items 活動内容入力情報
	 * @return 活動内容コード
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
	 * 活動内容を更新します。
	 * @param conn DBコネクション 
	 * @param items 活動内容入力情報
	 * @param nActionCode 活動内容コード
	 * @return 活動内容コード
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
	 * アンケート項目選択肢を削除します。
	 * @param conn DBコネクション
	 * @param nItemDetId アンケート項目選択肢ID
	 * @return アンケート項目選択肢ID
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
	
}