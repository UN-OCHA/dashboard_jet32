package un.ocha.sy.dashboard;

import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
//import javax.sql.DataSource;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

@ApplicationPath("webapi")
public class MyApp extends Application {
	DataSource dataSource ;
	
	
	public static DataSource ocha_ds()throws NamingException, SQLException{
		
		///jdbc pool test
		/**
		 * Get initial context that has references to all configurations and
		 * resources defined for this web application.
		 */
		Context initialContext = new InitialContext();

		/**
		 * Get Context object for all environment naming (JNDI), such as
		 * Resources configured for this web application.
		 */
		Context environmentContext = (Context) initialContext
				.lookup("java:comp/env");
		/**
		 * Name of the Resource we want to access.
		 */
		String dataResourceName = "jdbc/JCGExampleDB1";
		/**
		 * Get the data source for the MySQL to request a connection.
		 */
		DataSource dataSource = (DataSource) environmentContext
				.lookup(dataResourceName);
		/**
		 * Request a Connection from the pool of connection threads.
		 */
		
		return dataSource;
	}
	
public static void ocha_ds_test()throws NamingException, SQLException{
		
	PoolProperties p = new PoolProperties();
    p.setUrl("jdbc:mysql://localhost:3306/appds");
    p.setDriverClassName("com.mysql.jdbc.Driver");
    p.setUsername("admin");
    p.setPassword("admin");
    p.setJmxEnabled(true);
    p.setTestWhileIdle(false);
    p.setTestOnBorrow(true);
    p.setValidationQuery("SELECT 1");
    p.setTestOnReturn(false);
    p.setValidationInterval(30000);
    p.setTimeBetweenEvictionRunsMillis(30000);
    p.setMaxActive(100);
    p.setInitialSize(10);
    p.setMaxWait(10000);
    p.setRemoveAbandonedTimeout(300);
    p.setMinEvictableIdleTimeMillis(30000);
    p.setMinIdle(10);
    p.setLogAbandoned(true);
    p.setRemoveAbandoned(true);
    p.setJdbcInterceptors(
           "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
           + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer;"
            + "org.apache.tomcat.jdbc.pool.interceptor.ResetAbandonedTimer");
    
   DataSource dataSource = new DataSource();
  dataSource.setPoolProperties(p);
  
  Context ctx = new InitialContext();
  
  Context environmentContext = (Context) ctx.lookup("java:comp/env");
  
  
  environmentContext.bind("jdbc/appdsDB", dataSource);   
	
  return ;
	}
	
}