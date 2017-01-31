package page;

import java.io.PrintWriter;

import servlet.LoginUser;

import db.EnqueteResultRec;

public class EnqueteHistoryPage extends HtmlPage {

	public void print(PrintWriter pw) throws Exception {

		LoginUser user = LoginUser.getLoginUser(req_);
		EnqueteResultRec[] results = EnqueteResultRec.loadByUserId(conn_, user.getUserRec().getUserId());
		
		EnqueteHistoryForm.printForm(pw, req_, resp_, resource_, results);
	}
}
