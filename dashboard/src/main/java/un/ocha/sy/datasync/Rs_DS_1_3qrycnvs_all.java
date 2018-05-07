package un.ocha.sy.datasync;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import un.ocha.sy.dashboard.MyApp;
import un.ocha.sy.datasync.entity._1OnlineIaoDelivered;
import un.ocha.sy.datasync.entity._31qrycnvsAll;
import un.ocha.sy.datasync.entity._34qrycnvsLocation;
import un.ocha.sy.datasync.entity._3qrycnvsAll;

@Path("Rs_DS_1_3qrycnvs_all")
public class Rs_DS_1_3qrycnvs_all {
	
	 

		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response returnBrandParts(ArrayList<_3qrycnvsAll> json_row)
					throws Exception {
			

			
			Connection conn=null;
			//return a error is brand is missing from the url string
			if(json_row.size()==0 ) {
				return Response.status(400).entity("Error: please specify brand for this search").build();
			}
			
			try {
			conn =MyApp.ocha_ds().getConnection();
			
			
			
			
			
			
		 
			conn.prepareStatement("truncate appsync.1_3qrycnvs_all ").execute();

			PreparedStatement  q_stm=null;
			_3qrycnvsAll _row=null;
			 
			 for (Iterator<_3qrycnvsAll> iterator = json_row.iterator(); iterator.hasNext();) {
				  _row = iterator.next();
			
				 
		
				   q_stm = conn.prepareStatement(
					"INSERT INTO appsync.1_3qrycnvs_all ( "+
							" ConvoyCode,"+
							" Convoy_Date,"+
							" Convoy_Type,"+
							" MultipleConvoys,"+
							" CalculationMethod,"+
							" CalcRef_Code,"+
							" Total_Locations,"+
							" SumOfNumber_of_Trucks ,"+
							" CnvyLocation_Target_Beneficiaries ,"+
							" CnvyLocation_Total_Beneficiaries_Reached)"+
							"values                                        "+
							"(?,?,?,?,?,?,?,?,?,?)                         "
					);
					 
			
				q_stm.setString(1,  _row.getConvoyCode() );
				q_stm.setDate(2, java.sql.Date.valueOf(_row.getConvoy_Date()) );
				q_stm.setString(3,  _row.getConvoy_Type() );
				q_stm.setByte(4,  _row.getMultipleConvoys());
				q_stm.setString(5, _row.getCalculationMethod() );
				q_stm.setString(6,  _row.getCalcRef_Code());
				q_stm.setInt(7, _row.getTotal_Locations());
				q_stm.setDouble(8,  _row.getSumOfNumber_of_Trucks());
				q_stm.setDouble(9,  _row.getCnvyLocation_Target_Beneficiaries());
				q_stm.setDouble(10, _row.getCnvyLocation_Total_Beneficiaries_Reached() );
 
				q_stm.execute();
				q_stm.close();
			}
			
					
			 conn.close(); 	 
				 
			}
			catch (Exception e) {
				e.printStackTrace();
				return Response.status(500).entity("Server was not able to process your request").build();
			}finally {
				// Release connection back to the pool
				if (conn != null) {
					conn.close();
				}
				conn = null; // prevent any future access
			}
			return Response.ok(json_row.size()).build();
		}
	}
 