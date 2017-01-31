package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Table {

	/** �u�g�p����v�ɑΉ�����l */
	public static final int USE = 1;
	/** �u�g�p���Ȃ��v�ɑΉ�����l */
	public static final int NOT_USE = 0;

	/** ���ǂɑΉ�������ǃt���O */
	public static final int REFER_FLG_UNREAD = 0;
	/** ���ǂɑΉ�������ǃt���O */
	public static final int REFER_FLG_READ = 1;
	
	/** ���폜�ɑΉ�����폜�t���O */
	public static final int DELETE_FLG_UNDELETED = 0;
	/** �폜�ɑΉ�����폜�t���O */
	public static final int DELETE_FLG_DELETED = 1;
	
	
	/**
	 * �v���C�}���L�[�𔭔Ԃ��܂��B
	 * @param conn DB�R�l�N�V����
	 * @param strColName �J������
	 * @param strTableName �e�[�u����
	 * @return �v���C�}���L�[
	 * @throws BPMException
	 */
	public static long getNextCode(	Connection conn, 
									String strColName, 
									String strTableName) throws SQLException {
		
		return getNextCode(conn, strColName, strTableName, 0);
	}
	
	/**
	 * �v���C�}���L�[�𔭔Ԃ��܂��B
	 * @param conn DB�R�l�N�V����
	 * @param strColName �J������
	 * @param strTableName �e�[�u����
	 * @param nMinValue ���Ԃ����l�̍ŏ��l
	 * @return �v���C�}���L�[
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
	 * �\�����𔭔Ԃ��܂��B
	 * @param conn DB�R�l�N�V����
	 * @param strColName �J������
	 * @param strTableName �e�[�u����
	 * @return �\����
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
	 * java.util.Date�̃I�u�W�F�N�g��java.sql.Date�ɕϊ����܂��B
	 * @param date java.util.Date�̃I�u�W�F�N�g
	 * @return java.sql.Date�̃I�u�W�F�N�g
	 */
	protected static java.sql.Date convert(java.util.Date date) {
		
		long longDate = date.getTime();
		return new java.sql.Date(longDate);
	}
}
