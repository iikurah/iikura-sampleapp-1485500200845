package page;

import java.io.PrintWriter;
import servlet.LoginUser;
import db.EnqueteItemDetRec;
import db.EnqueteItemDetTable;
import db.EnqueteItemRec;
import db.EnqueteItemTable;

public class EnqueteItemEditPage extends HtmlPage {

	public void print(PrintWriter pw) throws Exception {

		String cmd = getStringParameter("cmd");
		//
		LoginUser lu = LoginUser.getLoginUser(req_);
		String strUserId = lu.getUserRec().getUserId();
		
		long nItemId = getLongParameter("item_id");
	
		if(cmd.equals("upd")) {
			String strItemName = getStringParameter("item_name");
			long nCategoryFlg = getLongParameter("category_flg");
			System.out.println("çXêVèàóù");
			EnqueteItemTable.update(conn_, strItemName, nCategoryFlg, nItemId);
			
			EnqueteItemDetRec[] options = EnqueteItemDetRec.loadByItemId(conn_, nItemId);
			for(int i=0; i<options.length; i++) {
				String strItemDetName = getStringParameter("opt_name_" + options[i].getItemDetId());
				long nPoint = getLongParameter("opt_point_" + options[i].getItemDetId());
				EnqueteItemDetTable.update(conn_, strItemDetName, nPoint, options[i].getItemDetId());
			}
			for(int i=0; i<5-options.length; i++) {
				String strItemDetName = getStringParameter("opt_name_A" + i);
				long nPoint = getLongParameter("opt_point_A" + i);	
				if(strItemDetName!=null && strItemDetName.length()>0) {
					EnqueteItemDetTable.insert(conn_, nItemId, strItemDetName, nPoint);
				}
			}
			resp_.sendRedirect(req_.getRequestURI() + "?page=enquete_item_list");
		}else if(cmd.equals("ent")) {
			String strItemName = getStringParameter("item_name");
			long nCategoryFlg = getLongParameter("category_flg");
			System.out.println("ìoò^èàóù");
			nItemId = EnqueteItemTable.insert(conn_, strItemName, nCategoryFlg);
			for(int i=0; i<5; i++) {
				String strItemDetName = getStringParameter("opt_name_A" + i);
				long nPoint = getLongParameter("opt_point_A" + i);	
				if(strItemDetName!=null && strItemDetName.length()>0) {
					EnqueteItemDetTable.insert(conn_, nItemId, strItemDetName, nPoint);
				}
			}
			resp_.sendRedirect(req_.getRequestURI() + "?page=enquete_item_list");
		}
		
		EnqueteItemRec item = EnqueteItemRec.loadByItemId(conn_, nItemId);
		EnqueteItemDetRec[] options = EnqueteItemDetRec.loadByItemId(conn_, nItemId);
		
		EnqueteItemEditForm.printForm(pw, req_, resp_, resource_, item, options);
	}

}
