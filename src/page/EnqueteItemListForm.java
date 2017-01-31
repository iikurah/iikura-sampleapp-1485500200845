package page;

import java.io.PrintWriter;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.EnqueteItemDetRec;
import db.EnqueteItemRec;

public class EnqueteItemListForm extends HtmlForm {

	/**
	 * 画面を表示する
	 * @param pw
	 * @param req
	 * @param resp
	 * @param resource
	 * @param items
	 * @param options
	 * @throws Exception
	 */
	public static void printForm(PrintWriter pw,
								HttpServletRequest req,
								HttpServletResponse resp,
								ResourceBundle resource,
								EnqueteItemRec[] items,
								EnqueteItemDetRec[] options) throws Exception {
		
		pw.println("<html>");
		pw.println("<head>");
		printTitleTag(pw);
		printMetaTag(pw, resource);
		pw.println("<script language=\"JavaScript\">");
		pw.println("function goToEntry() {");
		pw.println("	document.myform.page.value=\"enquete_item_edit\";");
		pw.println("	document.myform.submit();");
		pw.println("}");
		pw.println("</script>"); 
		pw.println("</head>");
				
		pw.println("<font size=\"2\">アンケート項目一覧のページ</font>");

		pw.println("<br>");
		pw.println("<br>");
		
		printFormTag(pw, "myform", req.getRequestURI());
		
		pw.println("<input type=\"button\" value=\"項目の新規追加\" onClick=\"javascript:goToEntry();\">");
		pw.println("<a href=\"" + req.getRequestURI() + "?page=login&cmd=logout\"><font size=\"2\">ログアウト</font></a>");
		
		pw.println("<table width=\"500\">");
		
		String cats[]  = getCategory(resource);
		
		for(int i=0; i<items.length; i++) {
			pw.println("<tr>");
			pw.println("<td><font size=\"2\"><a href=\"" + req.getRequestURI() + "?page=enquete_item_edit&item_id=" + items[i].getItemId() + "\">" + items[i].getItemName() + "</a></font></td>");
			pw.println("<td><font size=\"2\">" + cats[(int)items[i].getCategoryId()-1] + "</font></td>");
			pw.println("</tr>");
			
			EnqueteItemDetRec[] opts = EnqueteItemDetRec.searchByItemId(options, items[i].getItemId());
			
			pw.println("<tr>");
			pw.println("<td colspan=\"2\"><font size=\"2\">");
			for(int j=0; j<opts.length; j++) {
				pw.println("[" + opts[j].getItemDetName() + "]<" +  opts[j].getPoint() +">");
			}
			pw.println("</font></td>");
			pw.println("</tr>");
			
		}
		
		pw.println("</table>");		 
		
		pw.println("<input type=\"hidden\" name=\"page\" value=\"enquete_item_list\">");
		pw.println("</form>");
		pw.println("</body>");
		pw.println("</html>");
		
	}

}
