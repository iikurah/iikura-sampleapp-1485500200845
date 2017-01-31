package page;

import java.io.PrintWriter;
import java.util.Vector;

import db.EnqueteItemDetRec;
import db.EnqueteItemRec;
import db.EnqueteResultDetRec;
import db.EnqueteResultRec;

public class EnqueteResultHistogramPage extends HtmlPage {

	public void print(PrintWriter pw) throws Exception {
		
		String strGuestUserId = resource_.getString("guestUserId");
		
		EnqueteResultRec[] results = EnqueteResultRec.loadByUserId(conn_, strGuestUserId);
		
		EnqueteItemRec[] items = EnqueteItemRec.loadAll(conn_);
		EnqueteItemDetRec[] itemDetRecs = EnqueteItemDetRec.loadAll(conn_);
		
		String[] catCodeList = req_.getParameterValues("category");

		Vector vec = new Vector();
		double nTotal = 0;
		
		for(int iCat=0 ; iCat<catCodeList.length; iCat++) {
			double[] nTotalDist = new double[results.length];
			long nCatCode = Long.parseLong(catCodeList[iCat]);	
			EnqueteItemRec[] itemsInCat = EnqueteItemRec.searchByCategoryId(items, nCatCode);
			for(int iResult=0; iResult<results.length; iResult++) {
				nTotal = 0;
				EnqueteResultDetRec[] resopts = EnqueteResultDetRec.loadByEnqueteResultId(conn_, results[iResult].getEnqueteResultId());
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
				nTotalDist[iResult] = nTotal;
			}
			vec.add(nTotalDist);
		}
		
		EnqueteResultHistogramForm.printForm(pw, req_, resp_, resource_, vec, results.length, catCodeList);
	}
}
