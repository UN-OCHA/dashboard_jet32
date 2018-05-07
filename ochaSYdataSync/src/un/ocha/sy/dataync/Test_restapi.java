package un.ocha.sy.dataync;

import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.ws.rs.core.NewCookie;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

public class Test_restapi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(System.currentTimeMillis() );
		SSLContext ctx=null;
		try {
		    ctx = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e1) {
		    e1.printStackTrace();
		}
		ClientConfig config=new DefaultClientConfig();
		config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(null,ctx));
		WebResource service=Client.create(new DefaultClientConfig()).resource("http://iaoperation.ochasyria.org/dashboard/") ;
		
		
		  Builder test = service.path("webapi/rs/login")
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
		
			System.out.println(  " ocha_tkn : "+ocha_tkn);
   
		 
	}

}
