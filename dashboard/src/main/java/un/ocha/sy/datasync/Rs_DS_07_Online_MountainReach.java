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
import un.ocha.sy.datasync.entity._6OnlineTargetApprovedReached;
import un.ocha.sy.datasync.entity._7OnlineMountainreach;

@Path("Rs_DS_07_Online_MountainReach")
public class Rs_DS_07_Online_MountainReach {
	
	 

		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response returnBrandParts(ArrayList<_7OnlineMountainreach> json_row)
					throws Exception {
			

			
			Connection conn=null;
			//return a error is brand is missing from the url string
			if(json_row.size()==0 ) {
				return Response.status(400).entity("Error: please specify brand for this search").build();
			}
			
			try {
			conn =MyApp.ocha_ds().getConnection();
		 
			conn.prepareStatement("truncate appsync.07_online_mountainreach ").execute();
			
			PreparedStatement  q_stm=null;
			_7OnlineMountainreach _row=null;
			
			 for (Iterator<_7OnlineMountainreach> iterator = json_row.iterator(); iterator.hasNext();) {
				  _row = iterator.next();
	
				  q_stm = conn.prepareStatement(
					"INSERT INTO appsync.07_online_mountainreach ( "+
							" Year_Number   ,"+
							" Month_Number  ,"+
							" HTR_IAConvoys ,"+
							" HTR_UNRWA     ,"+
							" Bsg_Air_Drops ,"+
							" Bsg_IAConvoys ,"+
							" Bsg_UNRWA     )"+
							"values                                        "+
							"(?,?,?,?,?,?,? )                         "
					);
					 
			
				
				q_stm.setInt(1,    _row.getYear_Number() );
				q_stm.setInt(2,    _row.getMonth_Number() );
				q_stm.setDouble(3, _row.gethtr_IAConvoys());
				q_stm.setDouble(4, _row.getHtrUnrwa());
				q_stm.setDouble(5, _row.getBsg_Air_Drops());
				q_stm.setDouble(6, _row.getBsg_IAConvoys());
				q_stm.setDouble(7, _row.getBsg_UNRWA());
				 
				 
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
 