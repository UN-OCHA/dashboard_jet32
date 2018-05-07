package un.ocha.sy.dataync;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class testaccess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn;
		try {
			
	 
			conn = DriverManager.getConnection(
			        "jdbc:ucanaccess://C:/Users/User/Desktop/OCHA - IA Operations V4.2.19-4.accdb");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM [07_Online_MountainReach]");
			while (rs.next()) {
				 System.out.print("{");
				for (int i = 1; i < rs.getMetaData().getColumnCount() ; i++) {
					System.out.print(rs.getMetaData().getColumnTypeName(i));
					System.out.println("\"" + rs.getMetaData().getColumnLabel(i)+ "\":\"" +rs.getString(i)+"\",");
				}
			    System.out.print("},");
			    System.out.println("");
			    break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
