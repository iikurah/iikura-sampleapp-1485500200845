package page;

import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.EnqueteItemDetRec;
import db.EnqueteItemRec;

public class EnqueteItemEditForm extends HtmlForm {

	public static void printForm(PrintWriter pw,
								HttpServletRequest req,
								HttpServletResponse resp,
								ResourceBundle resource,
								EnqueteItemRec item,
								EnqueteItemDetRec[] options) throws Exception {
		
		boolean bUpdate = true;
		if(item==null) {
			bUpdate = false;
		}
		
		pw.println("<html>");
		pw.println("<head>");
		printTitleTag(pw);
		printMetaTag(pw, resource);
		pw.println("<script language=\"JavaScript\">");
		pw.println("function backToList() {");
		pw.println("	document.myform.page.value=\"enquete_item_list\";");
		pw.println("	document.myform.submit();");
		pw.println("}");
		pw.println("function updateItem() {");
		pw.println("	if(confirm(\"変更します。よろしいですか？\")) {");
		pw.println("		document.myform.submit();");
		pw.println("	}");
		pw.println("}");
		pw.println("</script>"); 
		pw.println("</head>");
		
		pw.println("<body>");
		pw.println("<font size=\"2\">アンケート項目編集のページ</font>");

		pw.println("<br>");
		pw.println("<br>");
		
		printFormTag(pw, "myform", req.getRequestURI());
		
		pw.println("<table width=\"500\">");
		
			pw.println("<tr>");
			if(bUpdate) {
				pw.println("<td><input type=\"text\" name=\"item_name\" size=\"50\" maxlength=\"100\" value=\"" + item.getItemName() + "\"></td>");
			}else{
				pw.println("<td><input type=\"text\" name=\"item_name\" size=\"50\" maxlength=\"100\" value=\"\"></td>");
			}
			pw.println("<td>");
			
			String[] cats = getCategory(resource);
			
			pw.println("<select name=\"category_flg\">");
			for(int i=0; i<cats.length; i++) {
				if(bUpdate && item.getCategoryId()==(i+1)) {
					pw.println("<option value=\"" +(i+1) + "\" selected>" + cats[i] + "</option>");
				}else{
					pw.println("<option value=\"" + (i+1) + "\">" + cats[i] + "</option>");
				}
			}
			
			pw.println("</select>");
			pw.println("</td>");
			pw.println("</tr>");
		
			for(int j=0; j<options.length; j++) {
				pw.println("<tr>");
				pw.println("<td colspan=\"2\">");
				pw.println("<input type=\"text\" name=\"opt_name_" + options[j].getItemDetId() + "\" size=\"40\" value=\"" + options[j].getItemDetName() + "\">");
				pw.println("<input type=\"text\" name=\"opt_point_" + options[j].getItemDetId() + "\" size=\"3\" value=\"" + options[j].getPoint() + "\">");
				pw.println("</td>");
				pw.println("</tr>");
			}
			
			if(options.length<5) {
				for(int j=0; j<5-options.length; j++) {
					pw.println("<tr>");
					pw.println("<td colspan=\"2\">");
					pw.println("<input type=\"text\" name=\"opt_name_A" + j + "\" size=\"40\" value=\"\">");
					pw.println("<input type=\"text\" name=\"opt_point_A"  + j + "\" size=\"3\" value=\"\">");
					pw.println("</td>");
					pw.println("</tr>");
				}
			}
			
		pw.println("</table>");		 
		
		pw.println("<input type=\"hidden\" name=\"page\" value=\"enquete_item_edit\">");
		if(bUpdate) {
			pw.println("<input type=\"hidden\" name=\"cmd\" value=\"upd\">");
			pw.println("<input type=\"hidden\" name=\"item_id\" value=\"" + item.getItemId() + "\">");
		}else{
			pw.println("<input type=\"hidden\" name=\"cmd\" value=\"ent\">");
		}
		
		if(bUpdate) {
			pw.println("<input type=\"button\" value=\"変更する\" onClick=\"javascirpt:updateItem();\">");
		}else{
			pw.println("<input type=\"button\" value=\"登録する\" onClick=\"javascirpt:updateItem();\">");
		}
		pw.println("<input type=\"button\" value=\"キャンセル\" onClick=\"javascirpt:backToList();\">");
		pw.println("</form>");
		pw.println("</body>");
		pw.println("</html>");
		
	}

}
