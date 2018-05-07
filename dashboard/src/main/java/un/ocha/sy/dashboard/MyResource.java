package un.ocha.sy.dashboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.naming.NamingException;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("/rs")
public class MyResource {

	@Path("/login")
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
		public Response check_Login(@Context HttpHeaders testcon) throws SQLException {
		String randstr=getSaltString();
		System.out.println("run myresources 1");
		
		String c_user =testcon.getHeaderString("ocha_user");
		String c_pw =testcon.getHeaderString("ocha_user_pw");
		
		System.out.println(c_user);
		System.out.println(c_pw);
		Connection conn =null;
		try {
			 conn =MyApp.ocha_ds().getConnection();
			
			    Statement stm = conn.createStatement();
				String query = "SELECT count(1) AS exi  ,max(u_id) as id FROM appds.users users  WHERE (users.u_email = '"+c_user+"') AND (users.u_password = '"+c_pw+"')";
				ResultSet rs = stm.executeQuery(query);
				
				// Store and return result of the query
				int  user_check=0;
				int user_id=0;
				while (rs.next()) {
					  user_check=rs.getShort(1);
					  user_id=rs.getShort(2);
					  
					System.out.println(user_check);
					System.out.println(user_id);
				}
				stm.close();
				if (user_check==1){
					
					PreparedStatement  add_tok_stm = conn.prepareStatement("INSERT INTO appds.u_token(u_id, u_token, t_created_date) VALUES (?, ?, ?)");
					//String randstr=getSaltString();
					add_tok_stm.setInt(1, user_id);
					add_tok_stm.setString(2, randstr);
					add_tok_stm.setTimestamp(3, getCurrentTimeStamp());
					add_tok_stm.executeUpdate();
					add_tok_stm.close();
					conn.close();
				}
				else
				{	
					Response unauthorizedStatus = Response
				            .status(Response.Status.UNAUTHORIZED)
				            .entity("User cannot access the resource.")
				            .build();
					conn.close();
					return unauthorizedStatus;
				}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			//e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			//e.printStackTrace();
		} finally {
			// Release connection back to the pool
			if (conn != null) {
				conn.close();
			}
			conn = null; // prevent any future access
		}
		Response authorizedStatus = Response.ok().cookie(new NewCookie("ocha_tkn", randstr, "/", "", "comment", 144000, false)).build();
		
		return authorizedStatus;
		 
	}
	

	@Path("/logout")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
		public Response check_Logout(@Context HttpHeaders logoutRQ,@CookieParam("ocha_tkn") Cookie cookie) throws SQLException {
		
	
		System.out.println("run myresources logout");
		String ck_tkn=cookie.getValue();
		String ch_tkn =logoutRQ.getHeaderString("ocha_tkn");
		
		
		
		System.out.println(ch_tkn);
		System.out.println(ck_tkn);
		Connection conn =null;
		
		try {
			conn=MyApp.ocha_ds().getConnection();
			
			
					//logout useing cookie tkn
					PreparedStatement  del_tok_stm = conn.prepareStatement("delete from appds.u_token where  u_token = ? ");
					//String randstr=getSaltString();
				
					del_tok_stm.setString(1, ck_tkn);
					del_tok_stm.executeUpdate();
					
					
					//logout useing header tkn
					del_tok_stm = conn.prepareStatement("delete from appds.u_token where  u_token = ? ");
					
					del_tok_stm.setString(1, ch_tkn);
					del_tok_stm.executeUpdate();
					conn.close();
						
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			//e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			//e.printStackTrace();
		}
		
		finally {
			// Release connection back to the pool
			if (conn != null) {
				conn.close();
			}
			conn = null; // prevent any future access
		}
		Response authorizedStatus = Response.ok().build();
		
		return authorizedStatus;
		 
	}
	
	
	private static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}
	
	protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	
}
