package page;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Vector;

public class HtmlForm {

	/**
	 * meta�^�O���o�͂��܂��B
	 * @param pw PrintWriter
	 * @param resource ���\�[�X�o���h��
	 */
	protected static void printMetaTag(PrintWriter pw, ResourceBundle resource) {
		
		String strCharSet = resource.getString("meta.tag.encode");
		pw.print("<meta http-equive=\"Content-Type\" content=\"text/html; charset=" + strCharSet + "\">");
	}
	
	/**
	 * title�^�O���o�͂��܂��B
	 * @param pw PrintWriter
	 */
	protected static void printTitleTag(PrintWriter pw) {
		
		pw.print("<title>�����A�v��</title>");
	}
	
	/**
	 * form�^�O���o�͂��܂��B
	 * @param pw PrintWriter
	 * @param strFormName form��
	 * @param uri action�����Ɏw�肷��uri
	 */
	protected static void printFormTag(PrintWriter pw, String strFormName, String uri) {
		
		printFormTag(pw, strFormName, uri, false);
	}
	
	/**
	 * form�^�O���o�͂��܂��B
	 * @param pw PrintWriter
	 * @param strFormName form��
	 * @param uri action�����Ɏw�肷��uri
	 * @param bUtn �N���C�A���g�̌�ID���o�͂��邩�ǂ��� true:���M���� false:���M���Ȃ�
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
