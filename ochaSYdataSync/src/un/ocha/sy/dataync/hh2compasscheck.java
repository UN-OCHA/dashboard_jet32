package un.ocha.sy.dataync;
import java.sql.*;
import java.util.prefs.Preferences;
import javax.swing.JProgressBar;
//import oracle.jdbc.driver.OracleDriver;

public class hh2compasscheck extends Thread{
    int hdrnum=0;
    int itemnu=0;
    int descnum=0;
    String erphost;
    String erpport;
    String erpsid;
    String erpusername;
    String erppassword;
    String hhserver;
    String hhusername;
    String hhpassword;
    String postdatefrom;
    String postdateto;
    hh2erpframe h2ef;
    
  public  hh2compasscheck(String postdate1,String postdate2,hh2erpframe h2eframe ){
  
        Preferences p = Preferences.userRoot();
        this.erphost=p.get("erphost",""); 
        this.erpport=p.get("erpport","");
        this.erpsid=p.get("erpsid",""); 
        this.erpusername=p.get("erpusername","");
        this.erppassword=p.get("erppassword","");
        this.hhserver=p.get("hhserver","");
        this.hhusername=p.get("hhusername","");
        this.hhpassword=p.get("hhpassword","");
        postdatefrom=postdate1;
        postdateto=postdate2;
        this.h2ef=h2eframe;
  }
    
      //public static void main(String argv[]) {
       public void run() {
    try {
    
        Class.forName ("oracle.jdbc.driver.OracleDriver");
        String urlerp;
       // urlerp = "jdbc:oracle:thin:@mainserv:1521:compass";
        urlerp = "jdbc:oracle:thin:@"+this.erphost+":"+this.erpport+":"+this.erpsid;
       // Connection conerp = DriverManager.getConnection(urlerp,"hh2erp","compass");
        Connection conerp = DriverManager.getConnection(urlerp,this.erpusername,this.erppassword);
        
        conerp.setAutoCommit(false);
        Statement stmterp = conerp.createStatement();
        
        Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
        String url = "jdbc:odbc:"+this.hhserver;
        Connection con = DriverManager.getConnection(url,this.hhusername,this.hhpassword);
       
      
        
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery
        (
        
        "SELECT     \n" + 
        "bu000.Number,  \n" + 
        "bt000.Abbrev, \n" + 
        "bu000.Date\n" + 
        "FROM         \n" + 
        "bu000 INNER JOIN bt000 ON bu000.TypeGUID = bt000.GUID \n" + 
        "where \n" + 
        "    bu000.Date >='"+postdatefrom+"' \n" + 
        "and bu000.Date <='"+postdateto+"'\n"
        );      
        ResultSet rsroom ;
        String resalttxt="";
    while (rs.next()) {
    
            String vNumber = rs.getString(1);
            String vAbbrev = rs.getString(2);
            Date   vdate = rs.getDate(3);        
            String hdr_stat1 =  "select count(*)  \n" + 
                                "from\n" + 
                                " IV_INTERFACE_HDR \n" + 
                                "where  \n" + 
                                "INTR_TRX_NO = ? \n" + 
                                " and\n" + 
                                "INTR_TRX_CODE =?";  
                                
            PreparedStatement  hd_insert = conerp.prepareStatement(hdr_stat1);
            hd_insert.setString(1, vNumber); 
            hd_insert.setString(2, vAbbrev);             
            rsroom= hd_insert.executeQuery();
            rsroom.next();
            if (rsroom.getInt(1)== 0)
            {
           resalttxt=resalttxt+vNumber+" = "+vAbbrev+" =  "+vdate.toString()+" \n";
            } 
            hd_insert.close(); 
                                    
        }
        resalttxt=resalttxt+"Done..... \n";
        this.h2ef.jTextArea1.setText(resalttxt);
        
        stmt.close();
        con.close();
        stmterp.close();
        conerp.close();
    } catch (java.lang.Exception ex) {
    
          
            ex.printStackTrace();
        }

    }

}