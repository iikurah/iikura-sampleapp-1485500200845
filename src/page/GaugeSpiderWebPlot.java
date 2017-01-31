package page;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.PlotState;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.TableOrder;

public class GaugeSpiderWebPlot extends SpiderWebPlot {

	/**
	 * �ڐ���̕�����
	 */
	private int axisGaugeCount = 4;

	/**
	 * �ڐ���̃X�^�C��
	 */
	private BasicStroke gaugeStroke = new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1f, new float[] { 3f }, 0);

	public void setAxisDivideCount(int axisDivideCount) {
		this.axisGaugeCount = axisDivideCount;
	}

	public void setGaugeStroke(BasicStroke gaugeStroke) {
		this.gaugeStroke = gaugeStroke;
	}

	@Override
	public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {

		CategoryDataset dataset = getDataset();

		// adjust for insets...
		RectangleInsets insets = getInsets();
		insets.trim(area);

		if (info != null) {
			info.setPlotArea(area);
			info.setDataArea(area);
		}

		drawBackground(g2, area);
		drawOutline(g2, area);

		Shape savedClip = g2.getClip();

		g2.clip(area);
		Composite originalComposite = g2.getComposite();
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getForegroundAlpha()));

		if (!DatasetUtilities.isEmptyOrNull(dataset)) {
			int seriesCount = 0, catCount = 0;

			if (getDataExtractOrder() == TableOrder.BY_ROW) {
				seriesCount = dataset.getRowCount();
				catCount = dataset.getColumnCount();
			} else {
				seriesCount = dataset.getColumnCount();
				catCount = dataset.getRowCount();
			}

			// ensure we have a maximum value to use on the axes
			if (getMaxValue() == DEFAULT_MAX_VALUE)
				calculateMaxValue(seriesCount, catCount);

			// Next, setup the plot area

			// adjust the plot area by the interior spacing value

			double gapHorizontal = area.getWidth() * getInteriorGap();
			double gapVertical = area.getHeight() * getInteriorGap();

			double X = area.getX() + gapHorizontal / 2;
			double Y = area.getY() + gapVertical / 2;
			double W = area.getWidth() - gapHorizontal;
			double H = area.getHeight() - gapVertical;

			double headW = area.getWidth() * this.headPercent;
			double headH = area.getHeight() * this.headPercent;

			// make the chart area a square
			double min = Math.min(W, H) / 2;
			X = (X + X + W) / 2 - min;
			Y = (Y + Y + H) / 2 - min;
			W = 2 * min;
			H = 2 * min;

			Point2D centre = new Point2D.Double(X + W / 2, Y + H / 2);
			Rectangle2D radarArea = new Rectangle2D.Double(X, Y, W, H);

			// �O�g�̃|���S���Ɩڐ���̃��X�g����������
			Polygon outSidePolygon = new Polygon();
			List<Polygon> axisGauges = new ArrayList<Polygon>(axisGaugeCount);
			for (int i = 0; i < axisGaugeCount; i++) {
				axisGauges.add(new Polygon());
			}

			// draw the axis and category label
			for (int cat = 0; cat < catCount; cat++) {
				double angle = getStartAngle()
						+ (getDirection().getFactor() * cat * 360 / catCount);

				Point2D endPoint = getWebPoint(radarArea, angle, 1);

				// �O�g�ɒl��ǉ�
				outSidePolygon.addPoint((int) endPoint.getX(), (int) endPoint.getY());
				// �ڐ���̃|���S���ɒl��ǉ�
				addGaugePoint(axisGauges, centre, endPoint);

				// 1 = end of axis
				Line2D line = new Line2D.Double(centre, endPoint);
				g2.setPaint(getAxisLinePaint());
				g2.setStroke(getAxisLineStroke());
				g2.draw(line);
				drawLabel(g2, radarArea, 0.0, cat, angle, 360.0 / catCount);
			}

			// �O�g�̕`��
			g2.setPaint(getAxisLinePaint());
			g2.setStroke(getAxisLineStroke());
			g2.draw(outSidePolygon);

			// �ڐ���̕`��
			for (Polygon gauge : axisGauges) {
				g2.setPaint(getAxisLinePaint());
				g2.setStroke(gaugeStroke);
				g2.draw(gauge);
			}

			// Now actually plot each of the series polygons..
			for (int series = 0; series < seriesCount; series++) {
				drawRadarPoly(g2, radarArea, centre, info, series, catCount,
						headH, headW);
			}
		} else {
			drawNoDataMessage(g2, area);
		}
		g2.setClip(savedClip);
		g2.setComposite(originalComposite);
		drawOutline(g2, area);
	}

	/**
	 * �ڐ���Ƀ|�C���g��ǉ�����
	 */
	private void addGaugePoint(List<Polygon> axisGauges, Point2D center, Point2D end) {

		double centerX = center.getX();
		double centerY = center.getY();
		double endX = end.getX();
		double endY = end.getY();

		for (int j = 0; j < axisGaugeCount; j++) {
			Polygon polygon = axisGauges.get(j);
			int gaugeX = (int) (centerX + (j + 1) * (endX - centerX) / (axisGaugeCount + 1));
			int gaugeY = (int) (centerY + (j + 1) * (endY - centerY) / (axisGaugeCount + 1));
			polygon.addPoint(gaugeX, gaugeY);
		}
	}

	//�s����X�[�p�[�N���X����q��
	private void calculateMaxValue(int seriesCount, int catCount) {
		double v = 0;
		Number nV = null;

		for (int seriesIndex = 0; seriesIndex < seriesCount; seriesIndex++) {
			for (int catIndex = 0; catIndex < catCount; catIndex++) {
				nV = getPlotValue(seriesIndex, catIndex);
				if (nV != null) {
					v = nV.doubleValue();
					if (v > getMaxValue()) {
						setMaxValue(v);
					}
				}
			}
		}
	}

}