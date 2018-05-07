package un.ocha.sy.dataync.json;

import java.sql.SQLException;

public class ggggg {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		 
		Gene_Json jsondata = new Gene_Json(new MSaccess_db("C:/Users/User/Desktop/OCHA - IA Operations V4.2.19-4.accdb"));
		System.out.println(jsondata.Jstr_ocha_dashboard_webtag);
		

	}

}
