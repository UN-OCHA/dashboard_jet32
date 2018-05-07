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
import un.ocha.sy.datasync.entity._34qrycnvsLocation;
import un.ocha.sy.datasync.entity._405onlineIaRequest;
import un.ocha.sy.datasync.entity._7OnlineMountainreach;

@Path("Rs_DS_1_34qrycnvs_locations")
public class Rs_DS_1_34qrycnvs_locations {
	
	 

		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response returnBrandParts(ArrayList<_34qrycnvsLocation> json_row)
					throws Exception {
			

			
			Connection conn=null;
			//return a error is brand is missing from the url string
			if(json_row.size()==0 ) {
				return Response.status(400).entity("Error: please specify brand for this search").build();
			}
			
			try {
			conn =MyApp.ocha_ds().getConnection();
		 
			conn.prepareStatement("truncate appsync.1_34qrycnvs_locations ").execute();
			
			
			PreparedStatement  q_stm=null;
			_34qrycnvsLocation _row=null;
			 
			
			for (Iterator<_34qrycnvsLocation> iterator = json_row.iterator(); iterator.hasNext();) {
				  _row = iterator.next();
	
				   q_stm = conn.prepareStatement(
					"INSERT INTO appsync.1_34qrycnvs_locations ( "+
							"  ConvoyCode,                            "+
							"  Community_PCODE  ,                           "+
							"  Location      ,                          "+
							"  Convoy_Type)                  "+
							"values                                        "+
							"(?,?,?,? )                         "
					);
					 
			
				
				q_stm.setString(1,  _row.getConvoyCode() );
				q_stm.setString(2, _row.getCommunity_PCODE() );
				q_stm.setString(3, _row.getLocation());
				q_stm.setString(4, _row.getConvoy_Type() );
				 
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
 