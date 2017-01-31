package servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import db.UserRec;

public class LoginUser {

	private UserRec userRec_ = null;
	
	public UserRec getUserRec() {
		return userRec_;
	}
	
	public void setUserRec(UserRec userRec) {
		userRec_ = userRec;
	}
	
	public static LoginUser getLoginUser(HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession(true);
		LoginUser user = (LoginUser)session.getAttribute("login_user");
		return user;
	}
	
}
