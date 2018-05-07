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
@Path("rs_q02_people_reached")
public class Rs_q02_people_reached{

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
							 " SELECT "+ 
							 " Sum(2_3QryCnvs_All_Cumulative_Transitional.SumOfTrucks) AS Trucks,"+ 
							 " Sum(2_3QryCnvs_All_Cumulative_Transitional.SumOfTarget_Benericiaries) AS SumOfSumOfTarget_Benericiaries,"+ 
							 " Sum(2_3QryCnvs_All_Cumulative_Transitional.Net_Beneficiaries_Reached) AS Net_Reached,"+ 
							 " Sum(2_3QryCnvs_All_Cumulative_Transitional.Cumulative_Beneficiaries_Reached) AS CnvyLocation_Total_Cumulative_Beneficiaries"+ 
							 " FROM "+ 
							 " (SELECT "+ 
							 "	2_3QryCnvs_All_Cumulative_NewMethod.Location, "+ 
							 " Sum(2_3QryCnvs_All_Cumulative_NewMethod.Trucks) AS SumOfTrucks, "+ 
							 " Max(Net_Normal_Max+Net_Multi_SUM+Net_Multi_MAX) AS Net_Beneficiaries_Reached,"+ 
							 " Sum(Cumulative_Normal_SUM+Cumulative_Multi_SUM+Cumulative_Multi_MAX) AS Cumulative_Beneficiaries_Reached,"+ 
							 " Sum(2_3QryCnvs_All_Cumulative_NewMethod.Target_Beneficiaries) AS SumOfTarget_Benericiaries"+ 
							 " FROM ( SELECT "+ 
							 " ASCII(1_31qrycnvs_all.MultipleConvoys) AS MultipleConvoys, "+ 
							 " 1_31qrycnvs_all.CalculationMethod, "+ 
							 " 1_31qrycnvs_all.CalcRef_Code, "+ 
							 " Sum(1_31qrycnvs_all.SumOfNumber_of_Trucks) AS Trucks, "+ 
							 " Sum(1_31qrycnvs_all.CnvyLocation_Target_Beneficiaries) AS Target_Beneficiaries, "+ 
							 " 1_34qrycnvs_locations.Location, "+ 
							 "if( 1_31qrycnvs_all.Convoy_Type ='Air Drops',if(Min(Year( Convoy_Date ))=2016,if(Sum(if(IsNull( CalculationMethod )=1, CnvyLocation_Total_Beneficiaries_Reached ,0))<=110000,Sum(if(IsNull( CalculationMethod )=1, CnvyLocation_Total_Beneficiaries_Reached ,0)),110000),if(Min(Year( Convoy_Date ))=2017,if(Sum(if(IsNull( CalculationMethod )=1, CnvyLocation_Total_Beneficiaries_Reached ,0))<=93500,Sum(if(IsNull( CalculationMethod )=1, CnvyLocation_Total_Beneficiaries_Reached ,0)),93500),Sum(if(IsNull( CalculationMethod )=1, CnvyLocation_Total_Beneficiaries_Reached ,0)))),Max(if(IsNull( CalculationMethod )=1, CnvyLocation_Total_Beneficiaries_Reached ,0))) AS Net_Normal_Max, "+
							 " Sum(If((ASCII(1_31qrycnvs_all.MultipleConvoys)=1) And (CalculationMethod='SUM'),CnvyLocation_Total_Beneficiaries_Reached,0)) AS Net_Multi_SUM, "+ 
							 " Max(If((ASCII(1_31qrycnvs_all.MultipleConvoys)=1) And (CalculationMethod='MAX'),CnvyLocation_Total_Beneficiaries_Reached,0)) AS Net_Multi_MAX, "+ 
							 " 1_31qrycnvs_all.Convoy_Type, "+ 
							 " Sum(If(ISNULL(CalculationMethod)=1,CnvyLocation_Total_Beneficiaries_Reached,0)) AS Cumulative_Normal_SUM, "+ 
							 " Sum(If((ASCII(1_31qrycnvs_all.MultipleConvoys)=1) And (CalculationMethod='SUM'),CnvyLocation_Total_Beneficiaries_Reached,0)) AS Cumulative_Multi_SUM, "+ 
							 " Max(If((ASCII(1_31qrycnvs_all.MultipleConvoys)=1) And (CalculationMethod='MAX'),CnvyLocation_Total_Beneficiaries_Reached,0)) AS Cumulative_Multi_MAX"+ 
							 " FROM 1_31qrycnvs_all AS 1_31qrycnvs_all INNER JOIN 1_34QryCnvs_Locations  AS 1_34qrycnvs_locations ON 1_31qrycnvs_all.ConvoyCode = 1_34qrycnvs_locations.ConvoyCode"+ 
							 " WHERE (((1_31qrycnvs_all.Convoy_Date) Between  ? And ? ))"+ 
							 " GROUP BY "+ 
							 " MultipleConvoys, "+ 
							 " 1_31qrycnvs_all.CalculationMethod, "+ 
							 " 1_31qrycnvs_all.CalcRef_Code, "+ 
							 " 1_34qrycnvs_locations.Location, "+ 
							 " 1_31qrycnvs_all.Convoy_Type) AS 2_3QryCnvs_All_Cumulative_NewMethod"+ 
							 " GROUP BY "+ 
							 " 2_3QryCnvs_All_Cumulative_NewMethod.Location) "+ 
							 " AS 2_3QryCnvs_All_Cumulative_Transitional "
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