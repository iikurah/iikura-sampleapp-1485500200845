package page;

import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import servlet.LoginUser;

public class EnqueteResultForm extends HtmlForm {

	public static void printForm(PrintWriter pw,
								HttpServletRequest req,
								HttpServletResponse resp,
								ResourceBundle resource,
								double[] dPoint) throws Exception {
		
		
		/*pw.println("<html>");
		pw.println("<head>");
		printTitleTag(pw);
		printMetaTag(pw, resource);
		
		pw.println("<body>");
		pw.println("<font size=\"2\">診断結果のページ</font>");
		pw.println("<br>");
		pw.println("<br>");
		
		pw.println("<font size=\"2\"><a href=\"" + req.getRequestURI() + "?page=login&cmd=logout\">ログアウトする</a></font>");
		pw.println("<br>");
		pw.println("<font size=\"2\"><a href=\"" + req.getRequestURI() + "?page=menu\">メニュー画面に戻る</a></font>");
		pw.println("<br>");*/
		
		LoginUser lu = LoginUser.getLoginUser(req);
		String strName = lu.getUserRec().getUserName();
		
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		String[] cats = getCategory(resource);
		
		for(int i=0; i<cats.length; i++) {
			ds.addValue(dPoint[i], strName, (i+1) + ")" + cats[i]);
		}
     
	    GaugeSpiderWebPlot sp = new GaugeSpiderWebPlot();
	    sp.setMaxValue(getMaxValue(dPoint));
	    sp.setDataset(ds);
	     
	    JFreeChart chart = new JFreeChart("診断結果", JFreeChart.DEFAULT_TITLE_FONT, sp, true);
	    
	    //chartをBufferedImageに変換する
	    BufferedImage bufferedImage = chart.createBufferedImage(500, 500);    
	    resp.setContentType("image/jpeg");
	    ServletOutputStream sos = resp.getOutputStream();
	    ChartUtilities.writeChartAsJPEG(sos, chart,600,400);
	    
	}
	
	
	private static double getMaxValue(double[] d) {
		
		double max=0;
		for(int i=0; i<d.length; i++) {
			if(d[i]>max) max=d[i];
		}
		return max;
		
	}
	
}
