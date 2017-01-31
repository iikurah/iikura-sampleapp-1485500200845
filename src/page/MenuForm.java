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
			pw.println("<font size=\"2\">メニューのページ(管理者)</font>");
		}else{
			pw.println("<font size=\"2\">メニューのページ</font>");
		}
		pw.println("<br>");
		pw.println("<br>");
		
		if(userRec.getUserId().equals("-100")) {
			pw.println("<font size=\"2\"><a href=\"" + req.getRequestURI() + "?page=user_list\">1)ユーザー情報を管理する</a></font>");
			pw.println("<br>");
			pw.println("<font size=\"2\"><a href=\"" + req.getRequestURI() + "?page=enquete_item_list\">2)アンケート項目を管理する</a></font>");
			pw.println("<br>");
			pw.println("<font size=\"2\"><a href=\"" + req.getRequestURI() + "?page=enquete_result_multiple\">3)アンケート結果を集計する</a></font>");
			pw.println("<br>");
			pw.println("<font size=\"2\"><a href=\"" + req.getRequestURI() + "?page=enquete_result_average\">4)アンケート結果の平均を見る</a></font>");
			pw.println("<br>");
			pw.println("<font size=\"2\"><a href=\"" + req.getRequestURI() + "?page=enquete_result_histogram_select\">5)ヒストグラム表示</a></font>");
		}else{
			pw.println("<font size=\"2\">" + userRec.getUserName() + "さん、何をしますか？</font>");
			pw.println("<br>");
			pw.println("<font size=\"2\"><a href=\"" + req.getRequestURI() + "?page=enquete_entry\">1)アンケートに回答する</a></font>");
			pw.println("<br>");
			pw.println("<font size=\"2\"><a href=\"" + req.getRequestURI() + "?page=enquete_history\">2)過去のアンケート回答を参照する</a></font>");
		}
		
		pw.println("<br>");
		pw.println("<br>");
		pw.println("<font size=\"2\"><a href=\"" + req.getRequestURI() + "?page=login&cmd=logout\">ログアウト</a></font>");
		
		pw.println("</body>");
		pw.println("</html>");
		
	}

}
