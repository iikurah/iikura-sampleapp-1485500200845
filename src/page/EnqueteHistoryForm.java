package page;

import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.EnqueteResultRec;

public class EnqueteHistoryForm extends HtmlForm {

	public static void printForm(PrintWriter pw,
								HttpServletRequest req,
								HttpServletResponse resp,
								ResourceBundle resource,
								EnqueteResultRec[] results) throws Exception {
		
		pw.println("<html>");
		pw.println("<head>");
		printTitleTag(pw);
		printMetaTag(pw, resource);
		
		pw.println("<body>");
		pw.println("<font size=\"2\">アンケート回答履歴</font>");
		pw.println("<br>");
		pw.println("<br>");
		
		if(results.length>0) {
			for(int i=0; i<results.length; i++) {
				pw.println("<font size=\"2\">");
				pw.println("<a href=\"" + req.getRequestURI() + "?page=enquete_entry&cmd=pre_upd&enquete_result_id=" + results[i].getEnqueteResultId() + "\">" + (Integer.toString(i+1)) + ") " + dateFormat(results[i].getCreateDate()) + " 回答</a>");
				pw.println("</font>");
				pw.println("<br>");
			}
		}else{
			pw.println("<font size=\"2\">回答履歴はありません。</font>");
		}
	
		pw.println("</body>");
		pw.println("</html>");
	}
}
