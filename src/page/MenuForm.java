package page;

import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.UserRec;

import servlet.LoginUser;

public class MenuForm extends HtmlForm {

	public static void printForm(PrintWriter pw,
								HttpServletRequest req,
								HttpServletResponse resp,
								ResourceBundle resource) throws Exception {
		
		LoginUser loginUser = LoginUser.getLoginUser(req);
		UserRec userRec = loginUser.getUserRec();
		
		pw.println("<html>");
		pw.println("<body>");
		
		if(userRec.getUserId().equals("-100")) {
			pw.println("<font size=\"2\">���j���[�̃y�[�W(�Ǘ���)</font>");
		}else{
			pw.println("<font size=\"2\">���j���[�̃y�[�W</font>");
		}
		pw.println("<br>");
		pw.println("<br>");
		
		if(userRec.getUserId().equals("-100")) {
			pw.println("<font size=\"2\"><a href=\"" + req.getRequestURI() + "?page=user_list\">1)���[�U�[�����Ǘ�����</a></font>");
			pw.println("<br>");
			pw.println("<font size=\"2\"><a href=\"" + req.getRequestURI() + "?page=enquete_item_list\">2)�A���P�[�g���ڂ��Ǘ�����</a></font>");
			pw.println("<br>");
			pw.println("<font size=\"2\"><a href=\"" + req.getRequestURI() + "?page=enquete_result_multiple\">3)�A���P�[�g���ʂ��W�v����</a></font>");
			pw.println("<br>");
			pw.println("<font size=\"2\"><a href=\"" + req.getRequestURI() + "?page=enquete_result_average\">4)�A���P�[�g���ʂ̕��ς�����</a></font>");
			pw.println("<br>");
			pw.println("<font size=\"2\"><a href=\"" + req.getRequestURI() + "?page=enquete_result_histogram_select\">5)�q�X�g�O�����\��</a></font>");
		}else{
			pw.println("<font size=\"2\">" + userRec.getUserName() + "����A�������܂����H</font>");
			pw.println("<br>");
			pw.println("<font size=\"2\"><a href=\"" + req.getRequestURI() + "?page=enquete_entry\">1)�A���P�[�g�ɉ񓚂���</a></font>");
			pw.println("<br>");
			pw.println("<font size=\"2\"><a href=\"" + req.getRequestURI() + "?page=enquete_history\">2)�ߋ��̃A���P�[�g�񓚂��Q�Ƃ���</a></font>");
		}
		
		pw.println("<br>");
		pw.println("<br>");
		pw.println("<font size=\"2\"><a href=\"" + req.getRequestURI() + "?page=login&cmd=logout\">���O�A�E�g</a></font>");
		
		pw.println("</body>");
		pw.println("</html>");
		
	}

}
