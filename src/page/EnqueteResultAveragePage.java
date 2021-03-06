package page;

import java.io.PrintWriter;
import db.EnqueteItemDetRec;
import db.EnqueteItemRec;
import db.EnqueteResultDetRec;
import db.EnqueteResultRec;

public class EnqueteResultAveragePage extends HtmlPage {

	public void print(PrintWriter pw) throws Exception {
		
		String strGuestUserId = resource_.getString("guestUserId");
		EnqueteResultRec[] results = EnqueteResultRec.loadByUserId(conn_, strGuestUserId);
		
		EnqueteItemRec[] items = EnqueteItemRec.loadAll(conn_);
		EnqueteItemDetRec[] itemDetRecs = EnqueteItemDetRec.loadAll(conn_);
		
		long nTotal = 0;
		String[] cats = HtmlForm.getCategory(resource_);
		double[] dPoint = new double[cats.length];
		
		for(int iResult=0; iResult<results.length; iResult++) {
			EnqueteResultDetRec[] resopts = EnqueteResultDetRec.loadByEnqueteResultId(conn_, results[iResult].getEnqueteResultId());
			for(int i=0; i<cats.length; i++) {
				nTotal = 0;
				EnqueteItemRec[] itemsInCat = EnqueteItemRec.searchByCategoryId(items, i+1);
				for(int j=0; j<itemsInCat.length; j++) {
					EnqueteResultDetRec resultDet = EnqueteResultDetRec.searchResultDetRecByItemId(resopts, itemsInCat[j].getItemId());
					if(resultDet!=null) {
						EnqueteItemDetRec eidRec = EnqueteItemDetRec.searchEnqueteItemDet(itemDetRecs, itemsInCat[j].getItemId(), resultDet.getItemDetId());
						if(eidRec!=null){
							long nPoint = eidRec.getPoint();
							nTotal = nTotal + nPoint;
						}
					}
				}
				dPoint[i] = dPoint[i]  + (double)nTotal;
			}
		}
		
		for(int i=0; i<cats.length; i++) {
			dPoint[i] = dPoint[i]/results.length;
		}
		
		EnqueteResultAverageForm.printForm(pw, req_, resp_, resource_, dPoint, results.length);
	}
}
