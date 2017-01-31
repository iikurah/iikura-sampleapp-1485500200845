package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import page.HtmlPage;
import page.PageManager;

public class MainServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		ResourceBundle res = ResourceBundle.getBundle("servlet.resource");
		
		try {
			String strClassName = res.getString("jdbc.driver");
			String strDBUrl = res.getString("jdbc.url");
			String strDBUserName = res.getString("jdbc.username");
			String strDBPassword = res.getString("jdbc.password");	
			Class.forName(strClassName);
			conn = DriverManager.getConnection(strDBUrl, strDBUserName, strDBPassword);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter pw = null;
		String pageParam = req.getParameter("page");
		if((pageParam!=null && pageParam.equals("enquete_result")) || 
			(pageParam!=null && pageParam.equals("enquete_result_average")) ||
			(pageParam!=null && pageParam.equals("enquete_result_multiple")) ||
			(pageParam!=null && pageParam.equals("enquete_result_histogram"))) {
			//
		}else{
			pw = resp.getWriter();
		}
		
		HtmlPage page = PageManager.getPage(req);
		page.prepare(req, resp, conn, res);
		
		try {
			page.print(pw);
			
			try{
				conn.commit();
			}catch(SQLException sqlex){
				sqlex.printStackTrace();
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
			
			try{
				conn.rollback();
			}catch(SQLException sqlex){
				sqlex.printStackTrace();
			}
			
		}
		
		if((pageParam!=null && pageParam.equals("enquete_result")) || 
				(pageParam!=null && pageParam.equals("enquete_result_average")) ||
				(pageParam!=null && pageParam.equals("enquete_result_multiple")) ||
				(pageParam!=null && pageParam.equals("enquete_result_histogram"))) {
				//
			}else{
				pw.flush();
				pw.close();
			}
	}

	
}
