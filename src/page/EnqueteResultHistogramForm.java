package page;

import java.io.PrintWriter;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

public class EnqueteResultHistogramForm extends HtmlForm {

	public static void printForm(PrintWriter pw,
								HttpServletRequest req,
								HttpServletResponse resp,
								ResourceBundle resource,
								Vector nTotalDist,
								int nNum,
								String[] catCodeList) throws Exception {
			
		String[] cats = getCategory(resource);
		ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
		
		HistogramDataset dataset = new HistogramDataset();
		for(int i=0; i<nTotalDist.size(); i++) {
			String strName = cats[Integer.parseInt(catCodeList[i])-1];
			double[] values = (double[])nTotalDist.elementAt(i);
			dataset.addSeries(strName, values, 10);
		}
	
		//ヒストグラムを生成する
		JFreeChart chart = ChartFactory.createHistogram(
			                    //・グラフタイトル
			                    "ポイント分布(" + nNum + "件)",
			                    //・カテゴリ軸ラベル
			                    "点数",
			                    //・値軸ラベル
			                    "個数",
			                    //・データセット
			                    dataset,
			                    //・グラフの出力方向
			                    PlotOrientation.VERTICAL,
			                    //・凡例の表示有無
			                    true,
			                    //・ツールチップの表示有無
			                    false,
			                    //・URL動的生成の有無
			                    false);
	    
	    //chartをBufferedImageに変換する
	    resp.setContentType("image/jpeg");
	    ServletOutputStream sos = resp.getOutputStream();
	    ChartUtilities.writeChartAsJPEG(sos, chart, 600, 400);
	    
	}

}
