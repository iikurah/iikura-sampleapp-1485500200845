package page;

import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
import servlet.LoginUser;
import db.UserRec;

public class LoginPage extends HtmlPage {

	/**
	 * ���O�C����ʂ�\������
	 */
	public void print(PrintWriter pw) throws Exception {
		
		String cmd = getStringParameter("cmd");
		String strErrorMsg = null;
		
		if(cmd.equals("auth")) {
			String strUserId = req_.getParameter("id");
			String strPassword = req_.getParameter("password");
		
			UserRec userRec = UserRec.loadByUserId(conn_, strUserId);
			if(userRec==null) {
				strErrorMsg = "���[�U�[��񂪑��݂��܂���B";
				//throw new Exception("���[�U�[��񂪑��݂��܂���B");
			}else{
				if(strPassword.equals(userRec.getPassword())) {
					
					LoginUser lu = new LoginUser();
					lu.setUserRec(userRec);
					HttpSession session = req_.getSession(true);
					session.setAttribute("login_user", lu);
					resp_.sendRedirect(resp_.encodeURL(req_.getRequestURI() + "?page=menu"));
					return;
				}else{
					strErrorMsg = "�p�X���[�h���Ⴂ�܂��B";
					//throw new Exception("�p�X���[�h���Ⴂ�܂��B");
				}
			}
			
		}else if(cmd.equals("logout")) {
			HttpSession session = req_.getSession(true);
			session.invalidate();
		}
		LoginForm.printForm(pw, req_, resp_, resource_, strErrorMsg);
	}
}
