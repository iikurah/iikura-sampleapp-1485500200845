package page;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HtmlPage {

	HttpServletRequest req_ = null;
	HttpServletResponse resp_ = null;
	Connection conn_= null;
	ResourceBundle resource_ = null;
	
	public void prepare(HttpServletRequest req, HttpServletResponse resp, Connection conn, ResourceBundle res) {
		req_ = req;
		resp_ = resp;
		conn_ = conn;
		resource_ = res;
	}
	
	public void print(PrintWriter pw) throws Exception {
		
	}
	
	protected String getStringParameter(String paramName) {
		
		String param = req_.getParameter(paramName);
		String prm = "";
		
		if(param!=null) {
			try{
				prm = new String(param.getBytes("iso-8859-1"), "UTF-8");
			} catch(UnsupportedEncodingException uex) {
				uex.printStackTrace();
			}
			return prm;
		}else{
			return "";
		}
		
	}
	
	protected long getLongParameter(String paramName) {
		
		String param = req_.getParameter(paramName);
		if(param!=null && param.length()>0) {
			return Long.parseLong(param);
		}else{
			return 0;
		}
		
	}

}
