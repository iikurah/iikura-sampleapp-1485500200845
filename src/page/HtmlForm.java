package page;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Vector;

public class HtmlForm {

	/**
	 * metaタグを出力します。
	 * @param pw PrintWriter
	 * @param resource リソースバンドル
	 */
	protected static void printMetaTag(PrintWriter pw, ResourceBundle resource) {
		
		String strCharSet = resource.getString("meta.tag.encode");
		pw.print("<meta http-equive=\"Content-Type\" content=\"text/html; charset=" + strCharSet + "\">");
	}
	
	/**
	 * titleタグを出力します。
	 * @param pw PrintWriter
	 */
	protected static void printTitleTag(PrintWriter pw) {
		
		pw.print("<title>金元アプリ</title>");
	}
	
	/**
	 * formタグを出力します。
	 * @param pw PrintWriter
	 * @param strFormName form名
	 * @param uri action属性に指定するuri
	 */
	protected static void printFormTag(PrintWriter pw, String strFormName, String uri) {
		
		printFormTag(pw, strFormName, uri, false);
	}
	
	/**
	 * formタグを出力します。
	 * @param pw PrintWriter
	 * @param strFormName form名
	 * @param uri action属性に指定するuri
	 * @param bUtn クライアントの個体IDを出力するかどうか true:送信する false:送信しない
	 */
	protected static void printFormTag(PrintWriter pw, String strFormName, String uri, boolean bUtn) {
		
		pw.println("<form name=\"" + strFormName + "\" action=\"" + uri + "\" method=\"post\"" + (bUtn? " utn" : "") + ">");
	}
	
	
	protected static String dateFormat(java.util.Date date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(date);
	
	}
	
	public static String[] getCategory(ResourceBundle resource) {
		
		String org = resource.getString("questionCategory");
		try{
			org = new String(org.getBytes("iso-8859-1"), "UTF-8");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		Vector vec = new Vector();
		StringTokenizer token = new StringTokenizer(org, ",");
		while(token.hasMoreElements()) {
			vec.add((String)token.nextToken());
		}
		String[] ret = new String[vec.size()];
		return (String[])vec.toArray(ret);
	}
	
}
