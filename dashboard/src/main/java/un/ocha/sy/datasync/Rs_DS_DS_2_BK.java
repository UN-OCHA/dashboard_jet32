package un.ocha.sy.datasync;

 
import java.sql.Connection;
import java.sql.Statement;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import un.ocha.sy.dashboard.MyApp;
 

/**
 * This class is used to manage computer parts inventory. It is a improvement
 * to V1_inventory.java
 * 
 * @author 308tube
 */
@Path("Rs_DS_DS_2_BK")
public class Rs_DS_DS_2_BK{

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
			String[] Stet_list=
					   {"truncate appbk.01_online_iao_delivered;                                                                                ",
						"truncate appbk.04__05online_ia_requests;                                                                               ",
						"truncate appbk.06_online_target_approved_reached;                                                                      ",
						"truncate appbk.07_online_mountainreach;                                                                                ",
						"truncate appbk.1_31qrycnvs_all;                                                                                        ",
						"truncate appbk.1_34qrycnvs_locations;                                                                                  ",
						"truncate appbk.1_3qrycnvs_all;                                                                                         ",
						"truncate appbk.2_4_1_online_qrycnvs_all_byaccessstatus;                                                                ",
						"truncate appbk.qryaccess_total;                                                                                        ",
						"truncate appbk.ocha_dashboard_webtag;                                                                                  ",
						"insert appbk.01_online_iao_delivered select * from appds.01_online_iao_delivered;                                      ",
						"insert appbk.04__05online_ia_requests select * from appds.04__05online_ia_requests;                                    ",
						"insert appbk.06_online_target_approved_reached select * from appds.06_online_target_approved_reached;                  ",
						"insert appbk.07_online_mountainreach select * from appds.07_online_mountainreach;                                      ",
						"insert appbk.1_31qrycnvs_all select * from appds.1_31qrycnvs_all;                                                      ",
						"insert appbk.1_34qrycnvs_locations select * from appds.1_34QryCnvs_Locations;                                          ",
						"insert appbk.1_3qrycnvs_all select * from appds.1_3QryCnvs_All;                                                        ",
						"insert appbk.2_4_1_online_qrycnvs_all_byaccessstatus select * from appds.2_4_1_Online_QryCnvs_All_ByAccessStatus;      ",
						"insert appbk.qryaccess_total select * from appds.QryAccess_Total;                                                      ",
						"insert appbk.ocha_dashboard_webtag select * from appds.ocha_dashboard_webtag;                                          "};	 
			
				
			
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