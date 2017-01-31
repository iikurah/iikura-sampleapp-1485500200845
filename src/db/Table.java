package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Table {

	/** 「使用する」に対応する値 */
	public static final int USE = 1;
	/** 「使用しない」に対応する値 */
	public static final int NOT_USE = 0;

	/** 未読に対応する既読フラグ */
	public static final int REFER_FLG_UNREAD = 0;
	/** 既読に対応する既読フラグ */
	public static final int REFER_FLG_READ = 1;
	
	/** 未削除に対応する削除フラグ */
	public static final int DELETE_FLG_UNDELETED = 0;
	/** 削除に対応する削除フラグ */
	public static final int DELETE_FLG_DELETED = 1;
	
	
	/**
	 * プライマリキーを発番します。
	 * @param conn DBコネクション
	 * @param strColName カラム名
	 * @param strTableName テーブル名
	 * @return プライマリキー
	 * @throws BPMException
	 */
	public static long getNextCode(	Connection conn, 
									String strColName, 
									String strTableName) throws SQLException {
		
		return getNextCode(conn, strColName, strTableName, 0);
	}
	
	/**
	 * プライマリキーを発番します。
	 * @param conn DBコネクション
	 * @param strColName カラム名
	 * @param strTableName テーブル名
	 * @param nMinValue 発番される値の最小値
	 * @return プライマリキー
	 * @throws SQLException
	 */
	protected static long getNextCode(	Connection conn, 
										String strColName, 
										String strTableName, 
										long nMinValue) throws SQLException {

          PreparedStatement stmt = null;
          ResultSet rs = null;
          int nMaxCode = 0;

        try{

          String sql = "select max(" + strColName + ") as max_code from " + strTableName;

          stmt = conn.prepareStatement(sql);
          rs = stmt.executeQuery();

          if(rs.next()) {
            nMaxCode = rs.getInt("max_code");
          }

       }catch(SQLException ex){ 
    	  ex.printStackTrace();
       }finally{
    	  try{
    		  if(rs!=null) rs.close();
    		  if(stmt!=null) stmt.close();
    	  }catch(SQLException ex){
    		 ex.printStackTrace();
          } 
       }

       if((nMaxCode+1)<nMinValue) {
       		return nMinValue;
       }else{
       	return (nMaxCode+1);
       }
       
	}
	
	/**
	 * 表示順を発番します。
	 * @param conn DBコネクション
	 * @param strColName カラム名
	 * @param strTableName テーブル名
	 * @return 表示順
	 * @throws BPMException
	 */
	/*protected static int getNextOrderCode(Connection conn, 
											String strColName, 
											String strTableName) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        int nMaxCode = 0;

     try{

        String sql = "select max(" + strColName + ") as max_code from " + strTableName;

        BPMLogger.writeLogSql(sql);
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        if(rs.next()) {
          nMaxCode = rs.getInt("max_code");
        }

     }catch(SQLException ex){
    	 //throw new BPMException(BPMException.ERROR_CODE_DB_ACCESS);
     }finally{
    	 try{
    		 if(rs!=null) rs.close();
    		 if(stmt!=null) stmt.close();
    	 }catch(SQLException ex){
    		 //throw new BPMException(BPMException.ERROR_CODE_DB_ACCESS);
   		 }
     }

     return (nMaxCode + 1);

	}*/
	
	/**
	 * java.util.Dateのオブジェクトをjava.sql.Dateに変換します。
	 * @param date java.util.Dateのオブジェクト
	 * @return java.sql.Dateのオブジェクト
	 */
	protected static java.sql.Date convert(java.util.Date date) {
		
		long longDate = date.getTime();
		return new java.sql.Date(longDate);
	}
}
