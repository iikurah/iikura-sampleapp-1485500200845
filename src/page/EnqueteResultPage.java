package page;

import java.io.PrintWriter;

import db.EnqueteItemDetRec;
import db.EnqueteItemRec;
import db.EnqueteResultDetRec;
import db.EnqueteResultRec;

public class EnqueteResultPage extends HtmlPage {

	public void print(PrintWriter pw) throws Exception {
		
		long nEnqueteResultId = getLongParameter("enquete_result_id");
		EnqueteResultRec result = EnqueteResultRec.loadByEnqueteResultId(conn_, nEnqueteResultId);
		EnqueteResultDetRec[] resopts = EnqueteResultDetRec.loadByEnqueteResultId(conn_, nEnqueteResultId);
		EnqueteItemRec[] items = EnqueteItemRec.loadAll(conn_);
		EnqueteItemDetRec[] itemDetRecs = EnqueteItemDetRec.loadAll(conn_);
		
		long nTotal = 0;
		String[] cats = HtmlForm.getCategory(resource_);
		
		double[] dPoint = new double[cats.length];
		for(int i=0; i<cats.length; i++) {
			nTotal = 0;
			EnqueteItemRec[] itemsInCat = EnqueteItemRec.searchByCategoryId(items, i+1);
			for(int j=0; j<itemsInCat.length; j++) {
				EnqueteResultDetRec resultDet = EnqueteResultDetRec.searchResultDetRecByItemId(resopts, itemsInCat[j].getItemId());
				EnqueteItemDetRec eidRec = EnqueteItemDetRec.searchEnqueteItemDet(itemDetRecs, itemsInCat[j].getItemId(), resultDet.getItemDetId());
				long nPoint = eidRec.getPoint();
				nTotal = nTotal + nPoint;
			}
			dPoint[i] = (double)nTotal;
		}
		
		EnqueteResultForm.printForm(pw, req_, resp_, resource_, dPoint);
	}
}
