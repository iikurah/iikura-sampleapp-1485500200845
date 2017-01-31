package db;

import java.sql.Connection;
import java.sql.SQLException;

public class UserRec extends Record {
	
	/** ���[�U�[ID */
	private String strUserId_;
	/** ���[�U�[�� */
	private String strUserName_;
	/** �p�X���[�h */
	private String strPassword_;
	
	/**
	 * ���[�U�[ID���擾���܂��B
	 * @return�@���[�U�[ID
	 */
    public String getUserId() {
    	return strUserId_;
    }
    /**
	 * ���[�U�[�����擾���܂��B
	 * @return�@���[�U�[��
	 */
    public String getUserName() {
    	return strUserName_;
    }
    /**
	 * �p�X���[�h���擾���܂��B
	 * @return�@�p�X���[�h
	 */
    public String getPassword() {
    	return strPassword_;
    }
    
    
	/**
	 * ���[�U�[ID���w�肵�ă��[�U�[�����擾���܂��B
	 * @param conn DB�R�l�N�V����
	 * @param strUserId ���[�U�[ID
	 * @return ���[�U�[���
	 * @throws BPMException 
	 */
	public static UserRec loadByUserId(Connection conn, String strUserId) throws Exception {
	
		UserQuery uq = new UserQuery();
		
		try{
		uq.searchByUserId(conn, strUserId);
		
		if(uq.next()) {
			UserRec userRec = new UserRec();
			userRec.strUserId_ = uq.result_.getString(UserTable.COL_USER_ID);
			userRec.strUserName_ = uq.result_.getString(UserTable.COL_USER_NAME);
			userRec.strPassword_ = uq.result_.getString(UserTable.COL_PASSWORD);
			return userRec;
		}
		
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally{
			if(uq!=null) uq.close();
		}
		
		return null;
	}

}

