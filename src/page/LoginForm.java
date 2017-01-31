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
		pw.println("		alert(\"���[�U�[ID����͂��Ă��������B\");");
		pw.println("		return;");
		pw.println("	}");
		pw.println("	if(document.myform.password.value==\"\") {");
		pw.println("		alert(\"�p�X���[�h����͂��Ă��������B\");");
		pw.println("		return;");
		pw.println("	}");
		pw.println("	document.myform.submit();");
		pw.println("}");
		pw.println("</script>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<font size=\"2\">���O�C���̃y�[�W</font>");
		pw.println("<br>");
		pw.println("<br>");
		
		if(strErrorMsg!=null) {
			pw.println("<font size=\"2\" color=\"red\">" + strErrorMsg + "</font>");
			pw.println("<br>");
		}
		
		pw.println("<font size=\"2\">�A���P�[�g�ɂ����͂����肢�������܂��B<br>���O�C��ID�ƃp�X���[�h�ɂ��Ă͍u���҂��������������܂��B</font>");
		
		printFormTag(pw, "myform", req.getRequestURI());
		
		pw.println("<br>");
		pw.println("<table width=\"200\">");
		pw.println("<tr><td width=\"100\" align=\"right\">");
		pw.println("<font size=\"2\">���O�C��ID�F</font></td>");
		pw.println("<td width=\"100\">");
		pw.println("<input type=\"text\" maxlength=\"10\" name=\"id\" value=\"\">");
		pw.println("</td></tr>");
		pw.println("<tr><td width=\"100\" align=\"right\">");
		pw.println("<font size=\"2\">�p�X���[�h�F</font></td>");
		pw.println("<td width=\"100\">");
		pw.println("<input type=\"password\" maxlength=\"10\" name=\"password\" value=\"\">");
		pw.println("</td></tr>");
		pw.println("</table>");
		pw.println("<br>");
		
		pw.println("<input type=\"button\" value=\"���O�C��\" onClick=\"javascript:checkInput();\">");
		
		pw.println("<input type=\"hidden\" name=\"page\" value=\"login\">");
		pw.println("<input type=\"hidden\" name=\"cmd\" value=\"auth\">");
		pw.println("</form>");
		
		pw.println("</body>");
		pw.println("</html>");
		
	}
}
