package un.ocha.sy.dataync.json;

import java.util.List;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

public class JerseyClientPost {

  public static void main(String[] args) {

	try {

		Client client = Client.create();

		
		
		
		String[][] input= new Gene_Json(new MSaccess_db("C:/Users/User/Desktop/OCHA - IA Operations V4.4.accdb")).json_Cup;
		
		
        
		
        
        WebResource webResource = client.resource("http://iaoperation.ochasyria.org/dashboard/");

		Builder test = webResource.path("webapi/rs/login")
							.header("ocha_user", "test")
							.header("ocha_user_pw", "basil2017");
		
		 ClientResponse dfdfd = test.head();
		 String ocha_tkn = null;
		 List<NewCookie> cookielist = dfdfd.getCookies();
		 for (   NewCookie cok :cookielist) {
			if (cok.getName().equals("ocha_tkn")) {
				ocha_tkn=cok.getValue();
			}
			
		}
		 
		 Cookie auth = new Cookie("ocha_tkn", ocha_tkn);
        
        
        for (int i = 0; i < 10; i++) {
			ClientResponse response = webResource.path(input[i][0]).type("application/json").cookie(auth)
		   .post(ClientResponse.class, input[i][1]);

		if (response.getStatus() != 200) {
		//	throw new RuntimeException("Failed : HTTP error code : "
		//	     + response.getStatus() +response.getLocation()+"  table : "+i );
		}
      System.out.println(response.getStatus() +" "+ input[i][0]);
		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);

		}
        
	  } catch (Exception e) {

		e.printStackTrace();

	  }

	}
}