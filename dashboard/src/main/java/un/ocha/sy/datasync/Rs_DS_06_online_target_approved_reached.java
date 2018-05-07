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
import un.ocha.sy.datasync.entity._405onlineIaRequest;
import un.ocha.sy.datasync.entity._6OnlineTargetApprovedReached;

@Path("Rs_DS_06_online_target_approved_reached")
public class Rs_DS_06_online_target_approved_reached {
	
	 

		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response returnBrandParts(ArrayList<_6OnlineTargetApprovedReached> json_row)
					throws Exception {
			

			
			Connection conn=null;
			//return a error is brand is missing from the url string
			if(json_row.size()==0 ) {
				return Response.status(400).entity("Error: please specify brand for this search").build();
			}
			
			try {
			conn =MyApp.ocha_ds().getConnection();
		 
			conn.prepareStatement("truncate appsync.06_online_target_approved_reached ").execute();
			PreparedStatement  q_stm=null;
			_6OnlineTargetApprovedReached _row=null;
			
			 for (Iterator<_6OnlineTargetApprovedReached> iterator = json_row.iterator(); iterator.hasNext();) {
				  _row = iterator.next();
	 
				  q_stm = conn.prepareStatement(
					"INSERT INTO appsync.06_online_target_approved_reached ( "+
							"  Convoy_Year,                            "+
							"  Convoy_Month  ,                           "+
							"  Month_Text      ,                          "+
							"  Requested_targeted_people,                  "+
							"ApprovedBeneficiaries ,"+
							"Conditional_Approval_Beneficiaries ,"+
							"Net_Reached ,"+
							"  Cumulative_Reached          )                         "+
							"values                                        "+
							"(?,?,?,?,?,?,?,? )                         "
					);
					 
			
				
				q_stm.setShort(1,  _row.getConvoy_Year() );
				q_stm.setShort(2, _row.getConvoy_Month() );
				q_stm.setString(3, _row.getMonth_Text());
				q_stm.setDouble(4,  _row.getRequested_targeted_people());
				q_stm.setDouble(5,  _row.getApprovedBeneficiaries());
				q_stm.setDouble(6,  _row.getConditional_Approval_Beneficiaries());
				q_stm.setDouble(7,  _row.getNet_Reached());
				q_stm.setDouble(8,  _row.getCumulative_Reached());
				
				 
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
 