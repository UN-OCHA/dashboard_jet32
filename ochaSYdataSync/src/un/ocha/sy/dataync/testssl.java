package un.ocha.sy.dataync;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

public class testssl {

	public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {
		
		HostnameVerifier hostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
		ClientConfig config = new DefaultClientConfig();
		SSLContext ctx = SSLContext.getInstance("SSL");
		TrustManager[] myTrustManager = null;
		ctx.init(null, myTrustManager, null);
		config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(hostnameVerifier, ctx));
		Client client = Client.create(config);

	}

}
