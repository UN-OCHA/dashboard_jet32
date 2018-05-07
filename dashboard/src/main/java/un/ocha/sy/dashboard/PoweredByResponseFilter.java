package un.ocha.sy.dashboard;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class PoweredByResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		responseContext.getHeaders().add("DashBoard-Powered-By", "Tamer Consultancy");
		responseContext.getHeaders().add("Powered-By-Email", "test.tamerATgmail.com");
		
	}

}
