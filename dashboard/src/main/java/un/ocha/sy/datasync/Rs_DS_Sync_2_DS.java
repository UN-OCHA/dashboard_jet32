package un.ocha.sy.datasync;

 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import un.ocha.sy.dashboard.MyApp;
import un.ocha.sy.dashboard.util.*;;

/**
 * This class is used to manage computer parts inventory. It is a improvement
 * to V1_inventory.java
 * 
 * @author 308tube
 */
@Path("Rs_DS_Sync_2_DS")
public class Rs_DS_Sync_2_DS{

	/**
	 * This method will return the specific brand of PC parts the user is looking for.  
	 * It uses a QueryParam bring in the data to the method.
	 * 
	 * Example would be:
	 * http://localhost:7001/com.youtube.rest/api/v2/inventory?brand=ASUS
	 * 
	 * @param brand - product brand name
	 * @return - json array results list from the database
	 * @throws Exception
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response returnBrandParts( )	throws Exception {
		
		
		Connection conn=null;
		boolean x =true;
		try {
			String[] Stet_list={"truncate appds.01_online_iao_delivered;                                                                                ",
								"truncate appds.04__05online_ia_requests;                                                                               ",
								"truncate appds.06_online_target_approved_reached;                                                                      ",
								"truncate appds.07_online_mountainreach;                                                                                ",
								"truncate appds.1_31qrycnvs_all;                                                                                        ",
								"truncate appds.1_34QryCnvs_Locations;                                                                                  ",
								"truncate appds.1_3QryCnvs_All;                                                                                         ",
								"truncate appds.2_4_1_Online_QryCnvs_All_ByAccessStatus;                                                                ",
								"truncate appds.QryAccess_Total;                                                                                        ",
								"truncate appds.ocha_dashboard_webtag;                                                                                  ",
								"insert appds.01_online_iao_delivered select * from appsync.01_online_iao_delivered;                                    ",
								"insert appds.04__05online_ia_requests select * from appsync.04__05online_ia_requests;                                  ",
								"insert appds.06_online_target_approved_reached select * from appsync.06_online_target_approved_reached;                ",
								"insert appds.07_online_mountainreach select * from appsync.07_online_mountainreach;                                    ",
								"insert appds.1_31qrycnvs_all select * from appsync.1_31qrycnvs_all;                                                    ",
								"insert appds.1_34QryCnvs_Locations select * from appsync.1_34qrycnvs_locations;                                        ",
								"insert appds.1_3QryCnvs_All select * from appsync.1_3qrycnvs_all;                                                      ",
								"insert appds.2_4_1_Online_QryCnvs_All_ByAccessStatus select * from appsync.2_4_1_online_qrycnvs_all_byaccessstatus;    ",
								"insert appds.QryAccess_Total select * from appsync.qryaccess_total;                                                    ",
								"insert appds.ocha_dashboard_webtag select * from appsync.ocha_dashboard_webtag;                                        "};	 
			
				
			
			conn =MyApp.ocha_ds().getConnection();
			
			for (int i = 0; i < Stet_list.length; i++) {
				
				Statement xstm = conn.createStatement();
				x=x&xstm.execute(Stet_list[i]);
				xstm.close();
				
			}
				
			conn.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
			return Response.status(500).entity("Server was not able to process your request").build();
		}finally {
			// Release connection back to the pool
			if (conn != null) {
				conn.close();
			}
			conn = null; // prevent any future access
		}
		return Response.ok(x).build();
	}
}