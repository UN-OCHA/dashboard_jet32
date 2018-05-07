package un.ocha.sy.dashboard;

 
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import un.ocha.sy.dashboard.util.*;;

/**
 * This class is used to manage computer parts inventory. It is a improvement
 * to V1_inventory.java
 * 
 * @author 308tube
 */
@Path("rs_q01_subpcode")
public class Rs_q01_subpcode{

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
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrandParts(
				@QueryParam("q_from_date") String sdate1,@QueryParam("q_to_date") String sdate2 ,@QueryParam("q_pcode") String p_pcode)
				throws Exception {
		
		String returnString = null;
		JSONArray json = new JSONArray();
		
		Connection conn=null;
		
		try {
				 
			
				
			//return a error is brand is missing from the url string
			if(sdate1 == null || sdate2==null ) {
				return Response.status(400).entity("Error: please specify brand for this search").build();
			}
			conn =MyApp.ocha_ds().getConnection();
			
			PreparedStatement  q_stm = conn.prepareStatement(
							"SELECT DATE_FORMAT(`01_online_iao_delivered`.Convoy_Date ,'%Y-%m-%d') AS Convoy_Date,                 "+
							"       `01_online_iao_delivered`.Community_PCODE,                   "+
							"       `01_online_iao_delivered`.Location,                          "+
							"       ((`01_online_iao_delivered`.`Total Operations`)+0) AS Total_Operations ,     "+
							"       `01_online_iao_delivered`.Convoy_Type          ,              "+							
							"       `01_online_iao_delivered`.Access_Status          ,              "+							
							"       `01_online_iao_delivered`.Total_Reach                        "+
							"  FROM appds.`01_online_iao_delivered` `01_online_iao_delivered`    "+
							" WHERE (`01_online_iao_delivered`.Convoy_Date BETWEEN ?  "+
							"                                                  AND ? )"+
							" AND        `01_online_iao_delivered`.Community_PCODE = ?            "+
							" order by Convoy_Date           "
					);
			
			q_stm.setDate(1, java.sql.Date.valueOf(sdate1));
			q_stm.setDate(2, java.sql.Date.valueOf(sdate2));
			q_stm.setString(3, p_pcode);
			
			
			
			json = new ToJSON().toJSONArray(q_stm.executeQuery());
			returnString = json.toString();
			q_stm.close();
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
		return Response.ok(returnString).build();
	}
}