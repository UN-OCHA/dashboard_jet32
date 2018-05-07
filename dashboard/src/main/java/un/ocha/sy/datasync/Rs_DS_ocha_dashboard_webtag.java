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
import un.ocha.sy.datasync.entity.Ocha_dashboard_webtag;
import un.ocha.sy.datasync.entity.QryaccessTotal;
import un.ocha.sy.datasync.entity._1OnlineIaoDelivered;
import un.ocha.sy.datasync.entity._34qrycnvsLocation;
import un.ocha.sy.datasync.entity._405onlineIaRequest;

@Path("Rs_DS_ocha_dashboard_webtag")
public class Rs_DS_ocha_dashboard_webtag {
	
	 

		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response returnBrandParts(ArrayList<Ocha_dashboard_webtag> json_row)
					throws Exception {
			

			
			Connection conn=null;
			//return a error is brand is missing from the url string
			if(json_row.size()==0 ) {
				return Response.status(400).entity("Error: please specify brand for this search").build();
			}
			
			try {
			conn =MyApp.ocha_ds().getConnection();
		 
			conn.prepareStatement("truncate appsync.ocha_dashboard_webtag ").execute();
			
			PreparedStatement  q_stm=null;
			Ocha_dashboard_webtag _row=null;
			
			 for (Iterator<Ocha_dashboard_webtag> iterator = json_row.iterator(); iterator.hasNext();) {
				  _row = iterator.next();
	
				   q_stm = conn.prepareStatement(
					"INSERT INTO appsync.ocha_dashboard_webtag ( "+
							"  text_id      ,                          "+
							"  text_desc      ,                          "+
							"  text_value)                  "+
							"values                                        "+
							"(?,?,? )                         "
					);
					 
			
				
				q_stm.setString(1,  _row.getText_id() );
				q_stm.setString(2, _row.getText_desc() );
				q_stm.setString(3, _row.getText_value());
 
				 
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
 