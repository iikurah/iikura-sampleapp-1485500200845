package page;

import java.io.PrintWriter;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EnqueteResultHistogramSelectForm extends HtmlForm {

	public static void printForm(PrintWriter pw,
								HttpServletRequest req,
								HttpServletResponse resp,
								ResourceBundle resource,
								String[] catList) throws Exception {
			
		pw.println("<html>");
		pw.println("<head>");
		printTitleTag(pw);
		printMetaTag(pw, resource);
		pw.println("<script language=\"JavaScript\">");
		pw.println("function goToHistogram() {");
		pw.println("	document.myform.page.value=\"enquete_result_histogram\";");
		pw.println("	document.myform.submit();");
		pw.println("}");
		pw.println("</script>"); 
		pw.println("</head>");
				
		pw.println("<font size=\"2\">カテゴリ選択ページ</font>");

		pw.println("<br>");
		pw.println("<br>");
		
		printFormTag(pw, "myform", req.getRequestURI());
		
		pw.println("<a href=\"" + req.getRequestURI() + "?page=login&cmd=logout\"><font size=\"2\">ログアウト</font></a>");
		
		pw.println("<br>");
		pw.println("<br>");
		
		for(int i=0; i<catList.length; i++) {
			pw.println("<input type=\"checkbox\" name=\"category\" value=\"" + (i+1) + "\">");
			pw.println("<font size=\"2\">" + catList[i] + "</font>");
			pw.println("<br>");
		}
		
		pw.println("<br>");
		
		pw.println("<input type=\"button\" value=\"ヒストグラム表示\" onClick=\"javascript:goToHistogram();\">");
		
		pw.println("<input type=\"hidden\" name=\"page\" value=\"\">");
		pw.println("</form>");
		pw.println("</body>");
		pw.println("</html>");
	    
	}
	
}
