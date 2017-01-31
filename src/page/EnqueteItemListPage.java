package page;

import java.io.PrintWriter;
import servlet.LoginUser;
import db.EnqueteItemDetRec;
import db.EnqueteItemRec;

public class EnqueteItemListPage extends HtmlPage {

	public void print(PrintWriter pw) throws Exception {

		String cmd = getStringParameter("cmd");
		//
		LoginUser lu = LoginUser.getLoginUser(req_);
		String strUserId = lu.getUserRec().getUserId();
	
		EnqueteItemRec[] items = EnqueteItemRec.loadAll(conn_);
		EnqueteItemDetRec[] options = EnqueteItemDetRec.loadAll(conn_);
		
		EnqueteItemListForm.printForm(pw, req_, resp_, resource_, items, options);
	}

}
