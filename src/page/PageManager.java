package page;

import javax.servlet.http.HttpServletRequest;

public class PageManager {

	public static HtmlPage getPage(HttpServletRequest req) {
		
		String param = req.getParameter("page");
		if(param==null) {
			return new LoginPage();
		}
		
		if(param.equals("login")) {
			return new LoginPage();
		}else if(param.equals("menu")){
			return new MenuPage();
		}else if(param.equals("enquete_entry")){
			return new EnqueteEntryPage();
		}else if(param.equals("enquete_history")){
			return new EnqueteHistoryPage();
		}else if(param.equals("enquete_result")){
			return new EnqueteResultPage();
		}else if(param.equals("enquete_item_list")){
			return new EnqueteItemListPage();
		}else if(param.equals("enquete_item_edit")){
			return new EnqueteItemEditPage();
		}else if(param.equals("enquete_result_average")){
			return new EnqueteResultAveragePage();
		}else if(param.equals("enquete_result_multiple")){
			return new EnqueteResultMultiplePage();
		}else if(param.equals("enquete_result_histogram_select")){
			return new EnqueteResultHistogramSelectPage();
		}else if(param.equals("enquete_result_histogram")){
			return new EnqueteResultHistogramPage();
		}else{
			return new LoginPage();
		}
	}
}
