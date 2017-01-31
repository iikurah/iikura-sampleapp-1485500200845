package page;

import java.io.PrintWriter;

public class MenuPage extends HtmlPage {

	public void print(PrintWriter pw) throws Exception {
		
		MenuForm.printForm(pw, req_, resp_, resource_);
	}

}
