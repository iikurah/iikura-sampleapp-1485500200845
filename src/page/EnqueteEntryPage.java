package page;

import java.io.PrintWriter;

import servlet.LoginUser;

import db.EnqueteItemRec;
import db.EnqueteResultDetRec;
import db.EnqueteResultDetTable;
import db.EnqueteResultTable;

public class EnqueteEntryPage extends HtmlPage {

	public void print(PrintWriter pw) throws Exception {

		String cmd = getStringParameter("cmd");
		//
		LoginUser lu = LoginUser.getLoginUser(req_);
		String strUserId = lu.getUserRec().getUserId();
		EnqueteResultDetRec[] erdRecs = null;
				
		if(cmd.equals("ent")) {
			//DB“o˜^ˆ—(“o˜^‚µ‚½ID‚ğ‚à‚ç‚¤)
			long nEnqueteResultId = EnqueteResultTable.insert(conn_, strUserId);
			System.out.println("ƒAƒ“ƒP[ƒgŒ‹‰Ê“o˜^ˆ—");
			
			EnqueteItemRec[] items = EnqueteItemRec.loadAll(conn_);
			for(int i=0; i<items.length; i++) {
				long nItemDetId = getLongParameter("opt" + items[i].getItemId());
				long nEnqueteResultDetId = EnqueteResultDetTable.insert(conn_, nEnqueteResultId, items[i].getItemId(), nItemDetId);
			}
			
			resp_.sendRedirect(req_.getRequestURI() + "?page=enquete_result&enquete_result_id=" + nEnqueteResultId);
			return;
		}else if(cmd.equals("pre_upd")) {
			long nEnqueteResultId = getLongParameter("enquete_result_id");
			erdRecs = EnqueteResultDetRec.loadByEnqueteResultId(conn_, nEnqueteResultId);
			System.out.println("lggf" + erdRecs.length);
		}else if(cmd.equals("upd")) {
			long nEnqueteResultId = getLongParameter("enquete_result_id");
			nEnqueteResultId = EnqueteResultDetTable.deleteByEnqueteResultId(conn_, nEnqueteResultId);
			EnqueteItemRec[] items = EnqueteItemRec.loadAll(conn_);
			for(int i=0; i<items.length; i++) {
				long nItemDetId = getLongParameter("opt" + items[i].getItemId());
				long nEnqueteResultDetId = EnqueteResultDetTable.insert(conn_, nEnqueteResultId, items[i].getItemId(), nItemDetId);
			}
			resp_.sendRedirect(req_.getRequestURI() + "?page=enquete_result&enquete_result_id=" + nEnqueteResultId);
			return;
		}
		
		EnqueteItemRec[] items = EnqueteItemRec.loadAll(conn_);
		EnqueteEntryForm.printForm(pw, req_, resp_, resource_, conn_, items, erdRecs);
	}

}
