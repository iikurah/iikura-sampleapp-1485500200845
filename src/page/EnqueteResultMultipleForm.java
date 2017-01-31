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
import db.UserRec;

public class EnqueteResultMultipleForm extends HtmlForm {

	public static void printForm(PrintWriter pw,
								HttpServletRequest req,
								HttpServletResponse resp,
								ResourceBundle resource,
								double[][] dPoint,
								int nNum,
								UserRec userRec) throws Exception {
			
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		String[] cats = getCategory(resource);
		
		for(int i=0; i<nNum; i++) {
			String strName = userRec.getUserName() + "‚³‚ñ" + (i+1) + "‰ñ–Ú";
		    for(int j=0; j<cats.length; j++) {
		    	ds.addValue(dPoint[i][j], strName, (j+1) + ")" + cats[j]);
		    }
		}
		
	    GaugeSpiderWebPlot sp = new GaugeSpiderWebPlot();
	    sp.setMaxValue(getMaxValue(dPoint));
	    sp.setDataset(ds);
	    
	    JFreeChart chart = new JFreeChart("f’fŒ‹‰Ê(—š—ð)", JFreeChart.DEFAULT_TITLE_FONT, sp, true);
	    
	    //chart‚ðBufferedImage‚É•ÏŠ·‚·‚é
	    BufferedImage bufferedImage = chart.createBufferedImage(500, 500);    
	    resp.setContentType("image/jpeg");
	    ServletOutputStream sos = resp.getOutputStream();
	    ChartUtilities.writeChartAsJPEG(sos, chart,600,400);
	    
	}
	
	private static double getMaxValue(double[][] d) {
		
		double max=0;
		for(int i=0; i<d.length; i++) {
			for(int j=0;j<d[i].length; j++)
				if(d[i][j]>max) max=d[i][j];
		}
		return max;
		
	}
	
}
