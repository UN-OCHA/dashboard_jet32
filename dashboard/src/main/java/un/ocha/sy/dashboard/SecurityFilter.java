package un.ocha.sy.dashboard;



import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.NamingException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
	

	private static final String SECURED_URL_PREFIX = "login";
	
	private static final String SECURED_URL_PREFIX2 = "rs_q02";
	private static final String SECURED_URL_PREFIX3 = "rs_q01";
	private static final String SECURED_URL_PREFIX4 = "ocha_dashboard_webtag";
	
	//@CookieParam("ocha_tkn") Cookie cookie;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		
		if (!requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX) 
				&& !requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX2) 
				&& !requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX3) 
				&& !requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX4)) {

			String c_h_tkn =requestContext.getHeaderString("ocha_tkn");
		
		Map<String,Cookie> ck_list=requestContext.getCookies();
		String c_c_tkn=null;
		for (Map.Entry<String, Cookie> entry : ck_list.entrySet()) {
			if (entry.getKey().equals("ocha_tkn")) {
				 c_c_tkn=entry.getValue().getValue();
			}
					   
		}
		
		
		Connection conn=null;
		try {
			 
			conn =MyApp.ocha_ds().getConnection();
			
			PreparedStatement  chk_tok_stm = conn.prepareStatement("select count(1) from appds.u_token where  u_token = ? or u_token = ? ");
			
		
			chk_tok_stm.setString(1, c_h_tkn);
			chk_tok_stm.setString(2, c_c_tkn);
			
			//test
			System.out.println(c_h_tkn);
			System.out.println(c_c_tkn);
			
			ResultSet rs = chk_tok_stm.executeQuery();
			
			int  user_check=0;
			
			while (rs.next()) {
				user_check=rs.getShort(1);
				
				//test
				System.out.println(user_check);
				
			}
			chk_tok_stm.close();
			conn.close();
			
			if(user_check==1 ||user_check==2){
				return;
			}
			
			Response unauthorizedStatus = Response
		            .status(Response.Status.UNAUTHORIZED)
		            .entity("User cannot access the resource.")
		            .build();

			requestContext.abortWith(unauthorizedStatus);

			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}finally {
			// Release connection back to the pool
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
			}
			conn = null; // prevent any future access
		}
		
		
		}	
		
	}

}