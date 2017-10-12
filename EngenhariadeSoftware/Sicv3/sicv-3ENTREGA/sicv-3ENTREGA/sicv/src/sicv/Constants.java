package sicv;


public class Constants {

	public static final String ICON_HREF = "";
	public static String NOT_INFORMED ="NOT INFORMED";
	public static int MAX_OCCURRENCES_BY_AGENT = 3;
	public static String PATH_XML_POSITION = "C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\sicv\\html-pages\\position\\positions.xml";
	public static final String DB_URL = "jdbc:odbc:sicv";
	public static final String DB_LOGIN = "";
	public static final String DB_PASS = "";
	public static final String DB_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	
	
	
	public static final String SERVER_NAME = "localhost";
	public static final String SYSTEM_NAME = "Sicv";
	public static final String FORM_PATH = "C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\sicv\\html-pages\\";
	public static final String SERVLET_SERVER_PATH = "localhost:8080/sicv/";
	public static final String SYSTEM_ROOT = "http://" + SERVLET_SERVER_PATH + "sicvwebserver?file=";
	public static final String SYSTEM_ACTION = "http://" + SERVLET_SERVER_PATH + "sicvservlet";
	public static final String SYSTEM_INDEX = SYSTEM_ROOT + "index.html";
	public static final String SYSTEM_INDEX_ADMINISTRATOR = SYSTEM_ACTION + "?operation=LoginMenu";
	public static final String SYSTEM_LOGIN = SYSTEM_ROOT + "Login.html";
	public static final String SYSTEM_QUERIES = SYSTEM_ROOT + "QueriesMenu.html";
	public static final String PERSISTENCE_UNIT_NAME = "JavaApplicationAgendaFachadaPU";

	
	public static boolean isPersistent() {
		return true;
	}
}
