package page;

import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginForm extends HtmlForm {

	public static void printForm(PrintWriter pw,
								HttpServletRequest req,
								HttpServletResponse resp,
								ResourceBundle resource,
								String strErrorMsg) {
		
		pw.println("<html>");
		pw.println("<head>");
		printTitleTag(pw);
		printMetaTag(pw, resource);
		
		pw.println("<script language=\"JavaScript\">");
		pw.println("function checkInput() {");
		pw.println("	if(document.myform.id.value==\"\") {");
		pw.println("		alert(\"ユーザーIDを入力してください。\");");
		pw.println("		return;");
		pw.println("	}");
		pw.println("	if(document.myform.password.value==\"\") {");
		pw.println("		alert(\"パスワードを入力してください。\");");
		pw.println("		return;");
		pw.println("	}");
		pw.println("	document.myform.submit();");
		pw.println("}");
		pw.println("</script>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<font size=\"2\">ログインのページ</font>");
		pw.println("<br>");
		pw.println("<br>");
		
		if(strErrorMsg!=null) {
			pw.println("<font size=\"2\" color=\"red\">" + strErrorMsg + "</font>");
			pw.println("<br>");
		}
		
		pw.println("<font size=\"2\">アンケートにご協力をお願いいたします。<br>ログインIDとパスワードについては講演者より説明がございます。</font>");
		
		printFormTag(pw, "myform", req.getRequestURI());
		
		pw.println("<br>");
		pw.println("<table width=\"200\">");
		pw.println("<tr><td width=\"100\" align=\"right\">");
		pw.println("<font size=\"2\">ログインID：</font></td>");
		pw.println("<td width=\"100\">");
		pw.println("<input type=\"text\" maxlength=\"10\" name=\"id\" value=\"\">");
		pw.println("</td></tr>");
		pw.println("<tr><td width=\"100\" align=\"right\">");
		pw.println("<font size=\"2\">パスワード：</font></td>");
		pw.println("<td width=\"100\">");
		pw.println("<input type=\"password\" maxlength=\"10\" name=\"password\" value=\"\">");
		pw.println("</td></tr>");
		pw.println("</table>");
		pw.println("<br>");
		
		pw.println("<input type=\"button\" value=\"ログイン\" onClick=\"javascript:checkInput();\">");
		
		pw.println("<input type=\"hidden\" name=\"page\" value=\"login\">");
		pw.println("<input type=\"hidden\" name=\"cmd\" value=\"auth\">");
		pw.println("</form>");
		
		pw.println("</body>");
		pw.println("</html>");
		
	}
}
