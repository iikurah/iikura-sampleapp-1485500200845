package page;

import java.io.PrintWriter;

public class EnqueteResultHistogramSelectPage extends HtmlPage {

	public void print(PrintWriter pw) throws Exception {
		
		String[] catList = HtmlForm.getCategory(resource_);
		EnqueteResultHistogramSelectForm.printForm(pw, req_, resp_, resource_, catList);
	}
}
