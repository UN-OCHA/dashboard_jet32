package un.ocha.sy.dataync.json;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MSaccess_db {
	
	String database_location;
	public Connection ms_connaction;
	public Connection uc_ms_connaction;

	public MSaccess_db(String database_location) throws ClassNotFoundException, SQLException {
		super();
		this.database_location = database_location;
		
		
		//C:/Users/User/Desktop/OCHA - IA Operations V4.2.19-4.accdb
		
		//try {
			
			 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			    
			     
			    String database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=";
//			    database+= database_location + ";DriverID=22;READONLY=true;}"; // add on to the end 
			    database+= database_location + ";READONLY=true;}"; // add on to the end 
			    
			    this.ms_connaction = DriverManager.getConnection(database);
			    this.uc_ms_connaction = DriverManager.getConnection("jdbc:ucanaccess://"+database_location+";memory=true");
			        
		//} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
	}
	

}
