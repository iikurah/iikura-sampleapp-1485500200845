package page;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.EnqueteItemDetRec;
import db.EnqueteItemRec;
import db.EnqueteResultDetRec;

public class EnqueteEntryForm extends HtmlForm {

	public static void printForm(PrintWriter pw,
								HttpServletRequest req,
								HttpServletResponse resp,
								ResourceBundle resource,
								Connection conn,
								EnqueteItemRec[] items,
								EnqueteResultDetRec[] erdRecs) throws Exception {
		
		pw.println("<html>");
		pw.println("<head>");
		printTitleTag(pw);
		printMetaTag(pw, resource);
		pw.println("<script language=\"JavaScript\">");
		pw.println("function checkInput() {");
		pw.println("	if(confirm(\"回答を送付します。よろしいですか？\")) {");
		pw.println("		document.myform.submit();");
		pw.println("	}");
		pw.println("}");
		pw.println("</script>");
		pw.println("</head>");
		
		pw.println("<body>");
		if(erdRecs!=null) {
			pw.println("<font size=\"2\">アンケート回答(更新)のページ</font>");
		}else{
			pw.println("<font size=\"2\">アンケート回答のページ</font>");
		}
		pw.println("<br>");
		pw.println("<br>");
		pw.println("<a href=\"" + req.getRequestURI() + "?page=menu\"><font size=\"2\">メニューに戻る</font></a>");
		
		printFormTag(pw, "myform", req.getRequestURI());
		
		pw.println("<table width=\"500\" border=\"1\" cellspacing=\"0\">");
		
		String[] cats = getCategory(resource);
		
		for(int k=0; k<cats.length; k++) {
			
			EnqueteItemRec[] itemsInCat = EnqueteItemRec.searchByCategoryId(items, k+1);
			pw.println("<tr>");
			pw.println("<td colspan=\"2\" bgcolor=\"#9ACD32\">");
			pw.println("<font size=\"2\"><b>" + Integer.toString(k+1) + ")" + cats[k] + "</b></font>");
			pw.println("</td></tr>");
			
			for(int i=0; i<itemsInCat.length; i++) {
			  
				EnqueteItemDetRec[] options = EnqueteItemDetRec.loadByItemId(conn, itemsInCat[i].getItemId());	
				pw.println("<tr>");
				pw.println("<td width=\"200\" valign=\"top\">");
				pw.println("<font size=\"2\">[" + Integer.toString(k+1) + "-" + Integer.toString(i+1) + "]" + itemsInCat[i].getItemName() + "</font>");
				pw.println("</td>");
				pw.println("<td width=\"300\">");
				pw.println("<font size=\"2\">");
				for(int j=0; j<options.length; j++) {
					pw.println("<input type=\"radio\" name=\"opt" + itemsInCat[i].getItemId() + "\" value=\"" + options[j].getItemDetId() + "\"" + (isChecked(itemsInCat[i].getItemId(), options[j].getItemDetId(), erdRecs, (j==0))? " checked" : "") + ">");
					pw.println("<font size=\"2\">" + options[j].getItemDetName() + "</font><br>");
				}
				pw.println("</td></tr>");
				
			}
		}
		
		pw.println("</table>");
		
		pw.println("<br>");
		pw.println("<input type=\"button\" value=\"回答を送信する\" onClick=\"javascript:checkInput();\">");
		
		pw.println("<input type=\"hidden\" name=\"page\" value=\"enquete_entry\">");
		if(erdRecs!=null) {
			pw.println("<input type=\"hidden\" name=\"cmd\" value=\"upd\">");
			pw.println("<input type=\"hidden\" name=\"enquete_result_id\" value=\"" + erdRecs[0].getEnqueteResultId() + "\">");
		}else{
			pw.println("<input type=\"hidden\" name=\"cmd\" value=\"ent\">");
		}
		 
		pw.println("</form>");
		pw.println("</body>");
		pw.println("</html>");
		
	}
	
	private static boolean isChecked(long nItemId, long nItemDetId, EnqueteResultDetRec[] erdRecs, boolean bforceCheck) {
		
		if(erdRecs==null) {
			if(bforceCheck) {
				return true;
			}else{
				return false;
			}
		}
		
		for(int i=0; i<erdRecs.length; i++) {
			if(erdRecs[i].getItemDetId()==nItemDetId && erdRecs[i].getItemId()==nItemId) {
				return true;
			}
		}
		
		return false;
		
	}
	
}
