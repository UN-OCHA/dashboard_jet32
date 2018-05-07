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
import un.ocha.sy.datasync.entity._405onlineIaRequest;

@Path("Rs_DS_05online_ia_requests")
public class Rs_DS__05online_ia_requests {
	
	 

		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response returnBrandParts(ArrayList<_405onlineIaRequest> json_row)
					throws Exception {
			

			
			Connection conn=null;
			//return a error is brand is missing from the url string
			if(json_row.size()==0 ) {
				return Response.status(400).entity("Error: please specify brand for this search").build();
			}
			
			try {
			conn =MyApp.ocha_ds().getConnection();
		 
			conn.prepareStatement("truncate appsync.04__05online_ia_requests ").execute();
	
			PreparedStatement  q_stm=null;
			_405onlineIaRequest _row=null;
			
			 for (Iterator<_405onlineIaRequest> iterator = json_row.iterator(); iterator.hasNext();) {
				  
				 _row = iterator.next();
	
				  q_stm = conn.prepareStatement(
					"INSERT INTO appsync.04__05online_ia_requests ( "+
							"  Year,                            "+
							"  Month  ,                           "+
							"  Month_Text      ,                          "+
							"  `SumOfNumber of requests`,                  "+
							"  Request_Status          )                         "+
							"values                                        "+
							"(?,?,?,?,? )                         "
					);
					 
			
				
				q_stm.setInt(1,  _row.getYear() );
				q_stm.setInt(2, _row.getMonth() );
				q_stm.setString(3, _row.getMonth_Text());
				q_stm.setDouble(4,  _row.getSumOfNumber_of_requests());
				q_stm.setString(5, _row.getRequest_Status() );
				 
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
 