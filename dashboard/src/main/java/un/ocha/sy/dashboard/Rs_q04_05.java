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
@Path("rs_q04_05")
public class Rs_q04_05{

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
				@QueryParam("q_from_date") String sdate1,@QueryParam("q_to_date") String sdate2)
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
					"SELECT str_to_date(CONCAT('01/',                                            " +
							"                          `04__05online_ia_requests`.`Month`,               " +
							"                          '/',                                              " +
							"                          `04__05online_ia_requests`.`Year`),               " +
							"                   '%d/%m/%Y')                                              " +
							"          AS to_date,                                                       " +
							"       `04__05online_ia_requests`.`Year`,                                   " +
							"       `04__05online_ia_requests`.Month,                                    " +
							"       `04__05online_ia_requests`.Month_Text,                               " +
							"       `04__05online_ia_requests`.`SumOfNumber of requests`,                " +
							"       `04__05online_ia_requests`.Request_Status                            " +
							"  FROM appds.`04__05online_ia_requests` `04__05online_ia_requests`          " +
							" WHERE (str_to_date(CONCAT('01/',                                           " +
							"                           `04__05online_ia_requests`.`Month`,              " +
							"                           '/',                                             " +
							"                           `04__05online_ia_requests`.`Year`),              " +
							"                    '%d/%m/%Y') BETWEEN ? " +
							"                                    AND ?)"  
					);
			
			q_stm.setDate(1, java.sql.Date.valueOf(sdate1));
			q_stm.setDate(2, java.sql.Date.valueOf(sdate2));
			
			
			
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