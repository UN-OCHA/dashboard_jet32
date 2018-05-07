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

@Path("Rs_DS_01online")
public class Rs_DS_01online {
	
	 

		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response returnBrandParts(ArrayList<_1OnlineIaoDelivered> json_row)
					throws Exception {
			

			
			Connection conn=null;
			//return a error is brand is missing from the url string
			if(json_row.size()==0 ) {
				return Response.status(400).entity("Error: please specify brand for this search").build();
			}
			
			try {
			conn =MyApp.ocha_ds().getConnection();
		 
			conn.prepareStatement("truncate appsync.01_online_iao_delivered ").execute();
			
			PreparedStatement  q_stm=null;
			_1OnlineIaoDelivered _row=null;
			
			 for (Iterator<_1OnlineIaoDelivered> iterator = json_row.iterator(); iterator.hasNext();) {
				 
				 _row = iterator.next();
			     q_stm = conn.prepareStatement(
					"INSERT INTO appsync.01_online_iao_delivered ( "+
							"  Longitude_WGS84,                            "+
							"  Latitude_WGS84  ,                           "+
							"  Convoy_Date      ,                          "+
							"  Community_PCODE   ,                         "+
							"  Location          ,                         "+
							"  Access_Status     ,                         "+
							"  OperationMonth    ,                         "+
							"  OperationYear     ,                         "+
							"  `Total Operations` ,                        "+
							"  Convoy_Type       ,                         "+
							"  Total_Reach    )                            "+
							"values                                        "+
							"(?,?,?,?,?,?,?,?,?,?,?)                         "
					);
					 
			
				
				q_stm.setBigDecimal(1,  _row.getLongitude_WGS84() );
				q_stm.setBigDecimal(2, _row.getLatitude_WGS84() );
				q_stm.setDate(3, java.sql.Date.valueOf(_row.getConvoy_Date()));
				q_stm.setString(4,  _row.getCommunity_PCODE());
				q_stm.setString(5, _row.getLocation() );
				q_stm.setString(6, _row.getAccess_Status());
				q_stm.setShort(7,  _row.getOperationMonth());
				q_stm.setShort(8, _row.getOperationYear());
				q_stm.setDouble(9,  _row.getTotal_Operations());
				q_stm.setString(10,  _row.getConvoy_Type());
				q_stm.setDouble(11, _row.getTotal_Reach() );
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
 