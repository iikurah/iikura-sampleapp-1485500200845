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
	
		//�q�X�g�O�����𐶐�����
		JFreeChart chart = ChartFactory.createHistogram(
			                    //�E�O���t�^�C�g��
			                    "�|�C���g���z(" + nNum + "��)",
			                    //�E�J�e�S�������x��
			                    "�_��",
			                    //�E�l�����x��
			                    "��",
			                    //�E�f�[�^�Z�b�g
			                    dataset,
			                    //�E�O���t�̏o�͕���
			                    PlotOrientation.VERTICAL,
			                    //�E�}��̕\���L��
			                    true,
			                    //�E�c�[���`�b�v�̕\���L��
			                    false,
			                    //�EURL���I�����̗L��
			                    false);
	    
	    //chart��BufferedImage�ɕϊ�����
	    resp.setContentType("image/jpeg");
	    ServletOutputStream sos = resp.getOutputStream();
	    ChartUtilities.writeChartAsJPEG(sos, chart, 600, 400);
	    
	}

}
