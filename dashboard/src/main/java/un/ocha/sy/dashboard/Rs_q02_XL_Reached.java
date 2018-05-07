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
@Path("rs_q02_xl_reached")
public class Rs_q02_XL_Reached{

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
					" SELECT  " + 
							 " Sum(2_8_0_OnlineQryCnvs_Net_Reached_ByAccessStatus.`Net Beneficiaries Reached`) AS Total_Net_Reached_XL_Locations " + 
							 " FROM  " + 
							 " (SELECT  " + 
							 " '2016-1-1' AS StartDate, " + 
							 " '2016-12-31' AS EndDate, " + 
							 " 2_4_2_Online_QryCnvs_All_ByAccessStatus_NewMethod.Location, " + 
							 " 2_4_2_Online_QryCnvs_All_ByAccessStatus_NewMethod.Access_Status,  " + 
							 " Max(Net_Normal_Max+Net_Multi_SUM+Net_Multi_MAX) AS 'Net Beneficiaries Reached',  " + 
							 " Sum(Cumulative_Normal_SUM+Cumulative_Multi_SUM+Cumulative_Multi_MAX) AS 'Cumulative Beneficiaries Reached' " + 
							 " FROM  " + 
							 " ( " + 
							 " SELECT  " + 
							 " 2_4_1_Online_QryCnvs_All_ByAccessStatus.MultipleConvoys,  " + 
							 " 2_4_1_Online_QryCnvs_All_ByAccessStatus.CalculationMethod,  " + 
							 " 2_4_1_Online_QryCnvs_All_ByAccessStatus.CalcRef_Code,  " + 
							 " 1_34QryCnvs_Locations.Location,  " + 
							 " 2_4_1_Online_QryCnvs_All_ByAccessStatus.Updated_Access_Status AS Access_Status,  " + 
							 " if( 2_4_1_Online_QryCnvs_All_ByAccessStatus.Convoy_Type ='Air Drops',if(Min(Year( Convoy_Date ))=2016,if(Sum(if(IsNull( CalculationMethod )=1, CnvyLocation_Total_Beneficiaries_Reached ,0))<=110000,Sum(if(IsNull( CalculationMethod )=1, CnvyLocation_Total_Beneficiaries_Reached ,0)),110000),if(Min(Year( Convoy_Date ))=2017,if(Sum(if(IsNull( CalculationMethod )=1, CnvyLocation_Total_Beneficiaries_Reached ,0))<=93500,Sum(if(IsNull( CalculationMethod )=1, CnvyLocation_Total_Beneficiaries_Reached ,0)),93500),Sum(if(IsNull( CalculationMethod )=1, CnvyLocation_Total_Beneficiaries_Reached ,0)))),Max(if(IsNull( CalculationMethod )=1, CnvyLocation_Total_Beneficiaries_Reached ,0))) AS Net_Normal_Max,  " + 
							 " Sum(If((ASCII(MultipleConvoys)=1) And (CalculationMethod='SUM'),CnvyLocation_Total_Beneficiaries_Reached,0)) AS Net_Multi_SUM,  " + 
							 " Max(If((ASCII(MultipleConvoys)=1) And (CalculationMethod='MAX'),CnvyLocation_Total_Beneficiaries_Reached,0)) AS Net_Multi_MAX,  " + 
							 " 2_4_1_Online_QryCnvs_All_ByAccessStatus.Convoy_Type,  " + 
							 " Sum(If(ISNULL(CalculationMethod)=1,CnvyLocation_Total_Beneficiaries_Reached,0)) AS Cumulative_Normal_SUM,  " + 
							 " Sum(If((ASCII(MultipleConvoys)=1) And (CalculationMethod='SUM'),CnvyLocation_Total_Beneficiaries_Reached,0)) AS Cumulative_Multi_SUM,  " + 
							 " Max(If((ASCII(MultipleConvoys)=1) And (CalculationMethod='MAX'),CnvyLocation_Total_Beneficiaries_Reached,0)) AS Cumulative_Multi_MAX " + 
							 " FROM  " + 
							 " 2_4_1_Online_QryCnvs_All_ByAccessStatus INNER JOIN 1_34QryCnvs_Locations ON 2_4_1_Online_QryCnvs_All_ByAccessStatus.ConvoyCode = 1_34QryCnvs_Locations.ConvoyCode " + 
							 " WHERE (((2_4_1_Online_QryCnvs_All_ByAccessStatus.Convoy_Date) Between ? And ? )) " + 
							 " GROUP BY  " + 
							 " 2_4_1_Online_QryCnvs_All_ByAccessStatus.MultipleConvoys,  " + 
							 " 2_4_1_Online_QryCnvs_All_ByAccessStatus.CalculationMethod,  " + 
							 " 2_4_1_Online_QryCnvs_All_ByAccessStatus.CalcRef_Code,  " + 
							 " 1_34QryCnvs_Locations.Location,  " + 
							 " 2_4_1_Online_QryCnvs_All_ByAccessStatus.Updated_Access_Status,  " + 
							 " 2_4_1_Online_QryCnvs_All_ByAccessStatus.Convoy_Type " + 
							 " ) " + 
							 " AS  " + 
							 " 2_4_2_Online_QryCnvs_All_ByAccessStatus_NewMethod " + 
							 " GROUP BY  " + 
							 " StartDate,  " + 
							 " EndDate,  " + 
							 " 2_4_2_Online_QryCnvs_All_ByAccessStatus_NewMethod.Location,  " + 
							 " 2_4_2_Online_QryCnvs_All_ByAccessStatus_NewMethod.Access_Status) " + 
							 " AS 2_8_0_OnlineQryCnvs_Net_Reached_ByAccessStatus " + 
							 " WHERE (((2_8_0_OnlineQryCnvs_Net_Reached_ByAccessStatus.Access_Status)='Priority XL')) " 
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