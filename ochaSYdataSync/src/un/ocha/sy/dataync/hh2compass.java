package un.ocha.sy.dataync;
import java.sql.*;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
//import oracle.jdbc.driver.OracleDriver;

public class hh2compass extends Thread{
    int hdrnum=0;
    int itemnu=0;
    int descnum=0;
    String erphost;
    String erpport;
    String erpsid;
    String erpusername;
    String erppassword;
    String sdhost;
    String sdport;
    String sdsid;
    String sdusername;
    String sdpassword;
    String postdate;
    String erpcin;
    String SDLOC;
    hh2erpframe h2ef;
    
  public  hh2compass(String postdate1,hh2erpframe h2eframe ){
  
        Preferences p = Preferences.userRoot();
        this.erphost=p.get("erphost",""); 
        this.erpport=p.get("erpport","");
        this.erpsid=p.get("erpsid",""); 
        this.erpusername=p.get("erpusername","");
        this.erppassword=p.get("erppassword","");
        this.sdhost=p.get("sdhost","");
        this.sdport=p.get("sdport","");
        this.sdsid=p.get("sdsid","");
        this.sdusername=p.get("sdusername","");
        this.sdpassword=p.get("sdpassword","");
        this.erpcin=p.get("CIN","");
        this.SDLOC=p.get("SDLOC","");
        postdate=postdate1;
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
        Statement stmtlookerp = conerp.createStatement();
        Statement stmtmaxerp = conerp.createStatement();
    //    Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
        String url = "jdbc:oracle:thin:@"+this.sdhost+":"+this.sdport+":"+this.sdsid;
        Connection con = DriverManager.getConnection(url,this.sdusername,this.sdpassword);
        Connection con1 = DriverManager.getConnection(url,this.sdusername,this.sdpassword);
        Statement stmttemp = con.createStatement();
        Statement stmttemp1 = con.createStatement();
        Statement stmtDecodeitem1 = con1.createStatement();
        String maxnum =  ("SELECT  \n" + 
        "count(*) \n" + 
        "FROM \n" + 
        "PSD_INVN_HDR, \n" + 
        "PERSON, \n" + 
        "LOAD_TYPE, \n" + 
        "LOCATION\n" + 
        "WHERE \n" + 
        "      PSD_INVN_HDR.FROM_DT=TO_DATE('"+postdate+"', 'mm/dd/yyyy')\n" + 
        " AND  ((PERSON.PERSON_ID=PSD_INVN_HDR.S_PERSON_ID)\n" + 
        " AND (LOAD_TYPE.LOAD_TYPE_ID=PSD_INVN_HDR.LOAD_TYPE_ID)\n" + 
        " AND (LOCATION.LOCATION_ID(+)=PSD_INVN_HDR.R_LOC_ID))");
        ResultSet rstemp = stmttemp.executeQuery        (maxnum);
        String maxnum1 = "SELECT  \n" + 
                         "count(count(*)) \n" + 
                         "FROM \n" + 
                         "SETTL_HD, \n" + 
                         "PCALDR, \n" + 
                         "INV_DTL, \n" + 
                         "INV_HDR, \n" + 
                         "INV_TRANS_TYPE\n" + 
                         "WHERE PCALDR.FROM_DT=TO_DATE('"+postdate+"', 'mm/dd/yyyy')\n" + 
                         " AND  SETTL_HD.PERIOD_CD=PCALDR.PERIOD_CD\n" + 
                         " AND SETTL_HD.PERIOD_YR_NR=PCALDR.PERIOD_YR_NR\n" + 
                         " AND INV_DTL.INVOICE_ID=INV_HDR.INVOICE_ID\n" + 
                         " AND INV_DTL.INV_TYP_CD=INV_HDR.INV_TYP_CD\n" + 
                         " AND INV_DTL.LOCATION_ID=INV_HDR.LOCATION_ID\n" + 
                         " AND INV_HDR.SETTLE_ID=SETTL_HD.SETTLE_ID\n" + 
                         " AND INV_DTL.INT_I_TRN_ID=INV_TRANS_TYPE.TRN_TYPE_ID\n" + 
                         "GROUP BY \n" + 
                         "INV_HDR.CUSTOMER_ID, \n" + 
                         "INV_TRANS_TYPE.TRN_TYP_NM_LL, \n" + 
                         "INV_DTL.INVOICE_ID, \n" + 
                         "INV_DTL.INV_TYP_CD, \n" + 
                         "INV_DTL.LOCATION_ID, \n" + 
                         "INV_DTL.INT_I_TRN_ID, \n" + 
                         "INV_HDR.R_LOC_ID, \n" + 
                         "INV_HDR.S_PERSON_ID\n";
        
        ResultSet rstemp1 = stmttemp1.executeQuery        (maxnum1);
        rstemp.next();
        rstemp1.next();
       int max1= rstemp.getInt(1);
       int max2= rstemp1.getInt(1);
        this.h2ef.jProgressBar3.setMaximum(max1+max2);
        rstemp.close();
        rstemp1.close();
        
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery
        ("SELECT ALL \n" + 
        "PSD_INVN_HDR.LOAD_TYPE_ID, \n" + 
        "LOAD_TYPE.LOAD_TYPE_NM_LL, \n" + 
                //      "PERSON.INT_GL_CD, \n" + 
        "PERSON.PERSON_ID, \n" +
        "PSD_INVN_HDR.LOAD_NR, \n" + 
        "PSD_INVN_HDR.FROM_DT, \n" + 
        "PSD_INVN_HDR.R_LOC_ID, \n" + 
        "LOCATION.INT_GL_CD, \n" + 
        "LOCATION.LOCATION_NM_LL\n" + //8
        "FROM \n" + 
        "PSD_INVN_HDR, \n" + 
        "PERSON, \n" + 
        "LOAD_TYPE, \n" + 
        "LOCATION\n" + 
        "WHERE \n" + 
        "      PSD_INVN_HDR.FROM_DT=TO_DATE('"+postdate+"', 'mm/dd/yyyy')\n" + 
        " AND  ((PERSON.PERSON_ID=PSD_INVN_HDR.S_PERSON_ID)\n" + 
        " AND (LOAD_TYPE.LOAD_TYPE_ID=PSD_INVN_HDR.LOAD_TYPE_ID)\n" + 
        " AND (LOCATION.LOCATION_ID(+)=PSD_INVN_HDR.R_LOC_ID))");
        
       // "WHERE (((bu000.Date)='4/1/2006'))");
        
       
        
        Statement stmtMax = conerp.createStatement();
        ResultSet rsMax = stmtMax.executeQuery("select nvl(max(INTR_SEQ),0)+1 from iv_interface_hdr");
        rsMax.next();
        int maxItem = rsMax.getInt(1);
        rsMax.close();
        this.h2ef.jProgressBar3.setValue(0);
        ResultSet rsroom ;
    while (rs.next()) {
            this.h2ef.transnm.setText("->"+this.hdrnum);
       /*     if (this.hdrnum > 104)
            {   
            this.h2ef.transnm.setText("->"+this.hdrnum);
            }**/
            this.h2ef.transnm1.setText("->"+this.itemnu);
            this.h2ef.transnm2.setText("->"+this.descnum);
            this.h2ef.jProgressBar3.setValue(this.h2ef.jProgressBar3.getValue()+1);
            
            
            Date vdate = rs.getDate(5);
            String vAbbrev = rs.getString(1);
            String vName = rs.getString(2);
            String vNumber = rs.getString(4);
            String vPayType = "0";
            String vcurNumber = "1";
            int vCurrencyVal = 1;
            String vcostCode = "";
            String v2wh = rs.getString(6); 
            String vcustnum ="";
            if ( vAbbrev.equals("6") || vAbbrev.equals("7") ) {  vcustnum = v2wh;}
            String vsalsmanacc = rs.getString(3);
            String to_date =" to_date('"+vdate+"','yyyy-mm-dd') ";
              
            String hdr_statcheck =  "select count(*)  \n" + 
                                "from\n" + 
                                " IV_INTERFACE_HDR \n" + 
                                "where  \n" + 
                                "INTR_TRX_NO = ? \n" + 
                                " and\n" + 
                                "INTR_TRX_CODE =?";  
        if (vAbbrev.equals("13") )
            {
                vAbbrev="7";
                vName="œŒÊ·  Ê“Ì⁄";
            }
            if (vAbbrev.equals("14") )
            {
                vAbbrev="6";
                vName="Œ—ÊÃ  Ê“Ì⁄";
            }               
            PreparedStatement  hd_insertcheck = conerp.prepareStatement(hdr_statcheck);
            hd_insertcheck.setString(1, vNumber); 
            hd_insertcheck.setString(2, vAbbrev);             
            rsroom= hd_insertcheck.executeQuery();
            rsroom.next();
            int val= rsroom.getInt(1);
            int save = 0x6ABCD;
            rsroom.close();
            hd_insertcheck.close();
           
            if (val== 0)
            {
            if (vAbbrev.equals("13") )
                {
                    vAbbrev="7";
                    vName="œŒÊ·  Ê“Ì⁄";
                }
                if (vAbbrev.equals("14") )
                {
                    vAbbrev="6";
                    vName="Œ—ÊÃ  Ê“Ì⁄";
                }
              
            if ( vAbbrev.equals("6") || vAbbrev.equals("7") ) {  vcustnum = "2";}
            String hdr_stat1 ="insert into IV_INTERFACE_HDR (\n" + 
                                "INTR_SEQ, \n" +
                                "INTR_FILE_NAME,\n" + 
                                "INTR_FROM_DATE,\n" + 
                                "INTR_TO_DATE,\n" + 
                                "INTR_IMPORT_DATE,\n" + 
                                "INTR_TRX_CODE,\n" + 
                                "INTR_TRX_NAME, \n" + 
                                "INTR_TRX_DATE, \n" + 
                                "INTR_TRX_NO,\n" + 
                                "INTR_DELEGATE_ID,\n" + //10
                                "INTR_CUSTOMER_ID,\n" + //
                                "INTR_CASH_FLAG, \n" + 
                                "INTR_CURRENCY_ID,\n" + 
                                "INTR_EXCHANG_RATE,\n" + 
                                "INTR_COST_CENTER, \n" + //14
                                "INTR_DUE_DATE, \n" + 
                                "INTR_CIN)\n" + 
                                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
            
                                    PreparedStatement  hd_insert = conerp.prepareStatement(hdr_stat1);
                                    hd_insert.setInt(1, maxItem);
                                    hd_insert.setString(2, "");   
                                    hd_insert.setDate(3, vdate);   
                                    hd_insert.setDate(4, vdate);   
                                    hd_insert.setDate(5, vdate);   
                                    hd_insert.setString(6, vAbbrev);   
                                    hd_insert.setString(7, vName);   
                                    hd_insert.setDate(8, vdate);   
                                    hd_insert.setString(9, vNumber);   
                                    hd_insert.setString(10, vsalsmanacc);   
                                    hd_insert.setString(11, vcustnum);  
                                      if(  Integer.valueOf( vPayType)==1)
                                      {hd_insert.setInt(12, -1);}
                                      else
                                      {hd_insert.setInt(12, 0);}
                                    hd_insert.setInt(13, Double.valueOf(vcurNumber).intValue());   
                                    hd_insert.setInt(14, vCurrencyVal);   
                                    hd_insert.setString(15, vcostCode);   
                                    hd_insert.setDate(16, vdate);
                                    hd_insert.setString(17, this.erpcin);  
                                    hd_insert.execute();
                                    hd_insert.close(); 
                                    
                                    Statement stmtitem1 = con1.createStatement();
                                   
                                    ResultSet rsitem1 = stmtitem1.executeQuery
                                    ("SELECT ALL \n" + 
                                    "LD_DTL_A1.ITEM_ID, \n" + 
                                    "LD_DTL_A1.LOAD_QTY,\n" + 
                                    "(select \n" + 
                                    "		pr1.PRICE_AM \n" + 
                                    "		from \n" + 
                                    "		prc_zone pr1\n" + 
                                    "		where \n" + 
                                    "		pr1.EFFECTIVE_DT = \n" + 
                                    "				(select \n" + 
                                    "					max( pr2.EFFECTIVE_DT ) \n" + 
                                    "					from  prc_zone pr2 \n" + 
                                    "					where  pr2.ITEM_ID = pr1.ITEM_ID and \n" + 
                                    "									EFFECTIVE_DT   <= "+to_date+") \n" + 
                                    "		and pr1.ITEM_ID =LD_DTL_A1.ITEM_ID\n" + 
                                    "		) pr\n" + 
                                    "FROM LD_DTL LD_DTL_A1\n" + 
                                    "WHERE LD_DTL_A1.LOAD_NR="+vNumber);
                                    
                                    
                                   
                                    int itemcounter=1;
                                    String vstorcode =  "1";
                                    if(v2wh==null)
                                    {
                                    v2wh="1";
                                    }
                                    if (vAbbrev.equals("1") || vAbbrev.equals("7") ||vAbbrev.equals("13") )          
                                    {          
                                     vstorcode =  v2wh;
                                     v2wh="1";
                                    }  
                                 
                                    while (rsitem1.next()) {
                                                  String vitemcode =  rsitem1.getString(1);
                                                  
                                                    if(maxItem >save){ while (true) { } }
                                                    double vitemqty =  rsitem1.getDouble(2);
                                                    String vitemunit =  "1";
                                                    double vitemprice =  rsitem1.getDouble(3);
                                                    double vitembousqty = 0;
                                                    int vitemdisc =  0;
                                       
                                                   String vdecodeitem="select ITEM_ID, nvl(nvl(fqty,mqty),1) qty , nvl(nvl(xpr,xdpr),1) xxpr from \n" + 
                                                   "(select \n" + 
                                                   "level,ITEM_DS_LL,M_PCKAGE_QTY,F_PCKAGE_QTY,\n" + 
                                                   "mainitem.ITEM_ID,\n" + 
                                                   "(SELECT distinct  ITEM_x1.f_PCKAGE_QTY\n" + 
                                                   "FROM ITEM ITEM_x1, ITEM ITEM_xA1\n" + 
                                                   "WHERE ITEM_xA1.ITEM_ID=mainitem.ITEM_ID\n" + 
                                                   " AND  (ITEM_x1.F_ITEM_ID=ITEM_xA1.ITEM_ID))  fqty,\n" + 
                                                   " (SELECT distinct  ITEM_x1.m_PCKAGE_QTY\n" + 
                                                   "FROM ITEM ITEM_x1, ITEM ITEM_xA1\n" + 
                                                   "WHERE ITEM_xA1.ITEM_ID=mainitem.ITEM_ID\n" + 
                                                   " AND  (ITEM_x1.m_ITEM_ID=ITEM_xA1.ITEM_ID))  mqty,\n" + 
                                                   " (select \n" + 
                                                   "		pr1.PRICE_AM \n" + 
                                                   "		from \n" + 
                                                   "		prc_zone pr1\n" + 
                                                   "		where \n" + 
                                                   "		pr1.EFFECTIVE_DT = \n" + 
                                                   "				(select \n" + 
                                                   "					max( pr2.EFFECTIVE_DT ) \n" + 
                                                   "					from  prc_zone pr2 \n" + 
                                                   "					where  pr2.ITEM_ID = pr1.ITEM_ID and \n" + 
                                                   "									EFFECTIVE_DT   <=  "+to_date+" ) \n" + 
                                                   "		and pr1.ITEM_ID =mainitem.ITEM_ID\n" + 
                                                   "		) xpr,\n" + 
                                                   " (select \n" + 
                                                   "		pr1.DEPOSIT_AM \n" + 
                                                   "		from \n" + 
                                                   "		dep_zone pr1\n" + 
                                                   "		where \n" + 
                                                   "		pr1.EFFECTIVE_DT = \n" + 
                                                   "				(select \n" + 
                                                   "					max( pr2.EFFECTIVE_DT ) \n" + 
                                                   "					from  dep_zone pr2 \n" + 
                                                   "					where  pr2.ITEM_ID = pr1.ITEM_ID and \n" + 
                                                   "									EFFECTIVE_DT   <=  "+to_date+" ) \n" + 
                                                   "		and pr1.ITEM_ID =mainitem.ITEM_ID\n" + 
                                                   "		) xdpr\n" + 
                                                   "from  item mainitem \n" + 
                                                   "start with ITEM_ID in (select \n" + 
                                                   "											ITEM_ID\n" + 
                                                   "											from psd. item\n" + 
                                                   "											start with ITEM_ID='"+vitemcode+"'\n" + 
                                                   "											connect by prior f_item_id=item_id)\n" + 
                                                   "connect by prior m_item_id=item_id)\n" + 
                                                   "where xpr is not null or xdpr is not null					"; 
                                                    
                                                  ResultSet rsitem1decode = stmtDecodeitem1.executeQuery(vdecodeitem);
                                                  while (rsitem1decode.next()){ 
                                                  
                                                  
                                                     double vpric= rsitem1decode.getDouble("xxpr");
                                                     
                                                     double vdqty= rsitem1decode.getDouble("qty");
                                                    
                                                     String vitemid= rsitem1decode.getString("item_id");
                                                    
                                                      String     hdr_stat;
                                                     if(vitemid.equals(vitemcode)){           
                                                                    hdr_stat ="insert into IV_INTERFACE_TRX (TR_HDR_SEQ,\n" + 
                                                                                    " TR_DTL_SEQ,\n" + 
                                                                                    " TR_ITEM_CODE,\n" + 
                                                                                //    " TR_ITEM_REV,\n" + 
                                                                                    " TR_WARHOUSE_CODE,\n" + 
                                                                                    " TR_TRX_QTY,\n" + 
                                                                                    " TR_PRICE,\n" + 
                                                                                    " TR_DISCOUNT,\n" + 
                                                                                    " TR_WH_FIXED_FLAG,\n" + 
                                                                                    " TR_WH_FIXED_USER,\n" + 
                                                                                    " TR_WH_FIXED_DATE,\n" + 
                                                                                    " TR_NOTE,\n" + 
                                                                                    " TR_STATUS,\n" + 
                                                                                    " TR_ERR_RESON,\n" + 
                                                                                    " TR_TO_WH,\n" + 
                                                                                    " TR_GIFT_QTY,\n" + 
                                                                                    " TR_UOM, \n" + 
                                                                                    " TR_CIN) values (\n" + 
                                                                                    maxItem+", \n" + 
                                                                                    itemcounter+", \n" + 
                                                                                    "'"+vitemcode+"', \n" + 
                                                                             //       "null, \n" + 
                                                                                    "'"+vstorcode+"', \n" + 
                                                                                    vitemqty+", \n" + 
                                                                                    vitemprice+", \n" + 
                                                                                    vitemdisc+", \n" + 
                                                                                    "null, \n" + 
                                                                                    "null, \n" + 
                                                                                    "null, \n" + 
                                                                                    "null, \n" + 
                                                                                    "null, \n" + 
                                                                                    "null, \n" + 
                                                                                   "'"+ v2wh+"', \n" + 
                                                                                    vitembousqty+", \n" + 
                                                                                    "to_char(to_number('"+vitemunit+"')), \n" +
                                                                                   "'"+ this.erpcin+"')";   
                                                     }
                                                     else
                                                     {              hdr_stat ="insert into IV_INTERFACE_TRX (TR_HDR_SEQ,\n" + 
                                                                                    " TR_DTL_SEQ,\n" + 
                                                                                    " TR_ITEM_CODE,\n" + 
                                                                                //    " TR_ITEM_REV,\n" + 
                                                                                    " TR_WARHOUSE_CODE,\n" + 
                                                                                    " TR_TRX_QTY,\n" + 
                                                                                    " TR_PRICE,\n" + 
                                                                                    " TR_DISCOUNT,\n" + 
                                                                                    " TR_WH_FIXED_FLAG,\n" + 
                                                                                    " TR_WH_FIXED_USER,\n" + 
                                                                                    " TR_WH_FIXED_DATE,\n" + 
                                                                                    " TR_NOTE,\n" + 
                                                                                    " TR_STATUS,\n" + 
                                                                                    " TR_ERR_RESON,\n" + 
                                                                                    " TR_TO_WH,\n" + 
                                                                                    " TR_GIFT_QTY,\n" + 
                                                                                    " TR_UOM, \n" + 
                                                                                    " TR_CIN) values (\n" + 
                                                                                    maxItem+", \n" + 
                                                                                    itemcounter+", \n" + 
                                                                                    "'"+vitemid+"', \n" + 
                                                                             //       "null, \n" + 
                                                                                    "'"+vstorcode+"', \n" + 
                                                                                    vitemqty*vdqty+", \n" + 
                                                                                    vpric+", \n" + 
                                                                                    0+", \n" + 
                                                                                    "null, \n" + 
                                                                                    "null, \n" + 
                                                                                    "null, \n" + 
                                                                                    "null, \n" + 
                                                                                    "null, \n" + 
                                                                                    "null, \n" + 
                                                                                   "'"+ v2wh+"', \n" + 
                                                                                    vitembousqty+", \n" + 
                                                                                    "to_char(to_number('"+vitemunit+"')), \n" +
                                                                                   "'"+ this.erpcin+"')"; 
                                                                                   }
                                                            stmterp.executeUpdate(hdr_stat);
                                                            
                                                            itemcounter=itemcounter+1;
                                                            this.itemnu=this.itemnu+1;
                                                  }
                                    }             
                                    stmtitem1.close();
                          
                             
                                    itemcounter=1;
                     
            }
                          
                          
                          
                          
        
        maxItem=maxItem+1;
        this.hdrnum=this.hdrnum+1;
        } //while for inventory transaction
        //
        //
         Statement stmtnetload = con.createStatement();
         ResultSet rsnetload = stmtnetload.executeQuery
         ("SELECT ALL \n" + 
         "PSD_NETLOD_HDR.LOAD_TYPE_ID, \n" + 
         "LOAD_TYPE.LOAD_TYPE_NM_LL, \n" + 
         "PERSON.PERSON_ID, \n" +
         "PSD_NETLOD_HDR.SETTLE_ID, \n" + 
         "PSD_NETLOD_HDR.FROM_DT, \n" + 
         "PSD_NETLOD_HDR.R_LOC_ID, \n" + 
         "LOCATION.INT_GL_CD, \n" + 
         "LOCATION.LOCATION_NM_LL\n" + //8
         "FROM \n" + 
         "PSD_NETLOD_HDR, \n" + 
         "PERSON, \n" + 
         "LOAD_TYPE, \n" + 
         "LOCATION\n" + 
         "WHERE \n" + 
         "      PSD_NETLOD_HDR.FROM_DT=TO_DATE('"+postdate+"', 'mm/dd/yyyy')\n" + 
         " AND  ((PERSON.PERSON_ID=PSD_NETLOD_HDR.S_PERSON_ID)\n" + 
         " AND (LOAD_TYPE.LOAD_TYPE_ID=PSD_NETLOD_HDR.LOAD_TYPE_ID)\n" + 
         " AND (LOCATION.LOCATION_ID(+)=PSD_NETLOD_HDR.R_LOC_ID))");
         
         // "WHERE (((bu000.Date)='4/1/2006'))");
         while (rsnetload.next()) {
                 this.h2ef.transnm.setText("->"+this.hdrnum);
                 this.h2ef.transnm1.setText("->"+this.itemnu);
                 this.h2ef.transnm2.setText("->"+this.descnum);
                 this.h2ef.jProgressBar3.setValue(this.h2ef.jProgressBar3.getValue()+1);
                 
                 
                 Date vdate = rsnetload.getDate(5);
                 String vAbbrev = rsnetload.getString(1);
                 String vName = rsnetload.getString(2);
                 String vNumber = rsnetload.getString(4);
                 String vPayType = "0";
                 String vcurNumber = "1";
                 int vCurrencyVal = 1;
                 String vcostCode = "";
                 String v2wh = rsnetload.getString(6); 
                 String vcustnum ="";
                 if ( vAbbrev.equals("6") || vAbbrev.equals("7") ) {  vcustnum = v2wh;}
                 String vsalsmanacc = rsnetload.getString(3);
                 String to_date =" to_date('"+vdate+"','yyyy-mm-dd') ";
                   
                 String hdr_statcheck =  "select count(*)  \n" + 
                                     "from\n" + 
                                     " IV_INTERFACE_HDR \n" + 
                                     "where  \n" + 
                                     "INTR_TRX_NO = ? \n" + 
                                     " and\n" + 
                                     "INTR_TRX_CODE =?";  
                                     
                 PreparedStatement  hd_insertcheck = conerp.prepareStatement(hdr_statcheck);
                 hd_insertcheck.setString(1, vNumber); 
                 hd_insertcheck.setString(2, vAbbrev);             
                 rsroom= hd_insertcheck.executeQuery();
                 rsroom.next();
                 int val= rsroom.getInt(1);
                 int save = 0x6ABCD;
                 rsroom.close();
                 hd_insertcheck.close();
                
                 if (val== 0)
                 {
                 String hdr_stat1 ="insert into IV_INTERFACE_HDR (\n" + 
                                     "INTR_SEQ, \n" +
                                     "INTR_FILE_NAME,\n" + 
                                     "INTR_FROM_DATE,\n" + 
                                     "INTR_TO_DATE,\n" + 
                                     "INTR_IMPORT_DATE,\n" + 
                                     "INTR_TRX_CODE,\n" + 
                                     "INTR_TRX_NAME, \n" + 
                                     "INTR_TRX_DATE, \n" + 
                                     "INTR_TRX_NO,\n" + 
                                     "INTR_DELEGATE_ID,\n" + //10
                                     "INTR_CUSTOMER_ID,\n" + //
                                     "INTR_CASH_FLAG, \n" + 
                                     "INTR_CURRENCY_ID,\n" + 
                                     "INTR_EXCHANG_RATE,\n" + 
                                     "INTR_COST_CENTER, \n" + //14
                                     "INTR_DUE_DATE, \n" + 
                                     "INTR_CIN)\n" + 
                                     "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
                 
                                         PreparedStatement  hd_insert = conerp.prepareStatement(hdr_stat1);
                                         hd_insert.setInt(1, maxItem);
                                         hd_insert.setString(2, "");   
                                         hd_insert.setDate(3, vdate);   
                                         hd_insert.setDate(4, vdate);   
                                         hd_insert.setDate(5, vdate);   
                                         hd_insert.setString(6, vAbbrev);   
                                         hd_insert.setString(7, vName);   
                                         hd_insert.setDate(8, vdate);   
                                         hd_insert.setString(9, vNumber);   
                                         hd_insert.setString(10, vsalsmanacc);   
                                         hd_insert.setString(11, vcustnum);  
                                           if(  Integer.valueOf( vPayType)==1)
                                           {hd_insert.setInt(12, -1);}
                                           else
                                           {hd_insert.setInt(12, 0);}
                                         hd_insert.setInt(13, Double.valueOf(vcurNumber).intValue());   
                                         hd_insert.setInt(14, vCurrencyVal);   
                                         hd_insert.setString(15, vcostCode);   
                                         hd_insert.setDate(16, vdate);
                                         hd_insert.setString(17, this.erpcin);  
                                         hd_insert.execute();
                                         hd_insert.close(); 
                                         
                                         Statement stmtitem1 = con1.createStatement();
                                        
                                         ResultSet rsitem1 = stmtitem1.executeQuery
                                         ("SELECT ALL \n" + 
                                         "LD_DTL_A1.ITEM_ID, \n" + 
                                         "LD_DTL_A1.LD_QTY,\n" + 
                                         "(select \n" + 
                                         "           pr1.PRICE_AM \n" + 
                                         "           from \n" + 
                                         "           prc_zone pr1\n" + 
                                         "           where \n" + 
                                         "           pr1.EFFECTIVE_DT = \n" + 
                                         "                           (select \n" + 
                                         "                                   max( pr2.EFFECTIVE_DT ) \n" + 
                                         "                                   from  prc_zone pr2 \n" + 
                                         "                                   where  pr2.ITEM_ID = pr1.ITEM_ID and \n" + 
                                         "                                                                   EFFECTIVE_DT   <= "+to_date+") \n" + 
                                         "           and pr1.ITEM_ID =LD_DTL_A1.ITEM_ID\n" + 
                                         "           ) pr\n" + 
                                         "FROM ROUTE_LD_INV LD_DTL_A1\n" + 
                                         "WHERE LD_DTL_A1.SETTLE_ID="+vNumber);
                                         
                                         
                                        
                                         int itemcounter=1;
                                         String vstorcode =  "1";
                                         if(v2wh==null)
                                         {
                                         v2wh="damplant";
                                         }
                                         if (vAbbrev.equals("1") || vAbbrev.equals("7") ||vAbbrev.equals("13") )          
                                         {          
                                          vstorcode =  v2wh;
                                          v2wh="1";
                                         }     
                                         while (rsitem1.next()) {
                                                       String vitemcode =  rsitem1.getString(1);
                                                         if(maxItem >save){while (true) {}}
                                                         double vitemqty =  rsitem1.getDouble(2);
                                                         String vitemunit =  "1";
                                                         double vitemprice =  rsitem1.getDouble(3);
                                                         double vitembousqty = 0;
                                                         int vitemdisc =  0;
                                            
                                                        String vdecodeitem="select ITEM_ID, nvl(nvl(fqty,mqty),1) qty , nvl(nvl(xpr,xdpr),1) xxpr from \n" + 
                                                        "(select \n" + 
                                                        "level,ITEM_DS_LL,M_PCKAGE_QTY,F_PCKAGE_QTY,\n" + 
                                                        "mainitem.ITEM_ID,\n" + 
                                                        "(SELECT distinct  ITEM_x1.f_PCKAGE_QTY\n" + 
                                                        "FROM ITEM ITEM_x1, ITEM ITEM_xA1\n" + 
                                                        "WHERE ITEM_xA1.ITEM_ID=mainitem.ITEM_ID\n" + 
                                                        " AND  (ITEM_x1.F_ITEM_ID=ITEM_xA1.ITEM_ID))  fqty,\n" + 
                                                        " (SELECT distinct  ITEM_x1.m_PCKAGE_QTY\n" + 
                                                        "FROM ITEM ITEM_x1, ITEM ITEM_xA1\n" + 
                                                        "WHERE ITEM_xA1.ITEM_ID=mainitem.ITEM_ID\n" + 
                                                        " AND  (ITEM_x1.m_ITEM_ID=ITEM_xA1.ITEM_ID))  mqty,\n" + 
                                                        " (select \n" + 
                                                        "            pr1.PRICE_AM \n" + 
                                                        "            from \n" + 
                                                        "            prc_zone pr1\n" + 
                                                        "            where \n" + 
                                                        "            pr1.EFFECTIVE_DT = \n" + 
                                                        "                            (select \n" + 
                                                        "                                    max( pr2.EFFECTIVE_DT ) \n" + 
                                                        "                                    from  prc_zone pr2 \n" + 
                                                        "                                    where  pr2.ITEM_ID = pr1.ITEM_ID and \n" + 
                                                        "                                                                    EFFECTIVE_DT   <=  "+to_date+" ) \n" + 
                                                        "            and pr1.ITEM_ID =mainitem.ITEM_ID\n" + 
                                                        "            ) xpr,\n" + 
                                                        " (select \n" + 
                                                        "            pr1.DEPOSIT_AM \n" + 
                                                        "            from \n" + 
                                                        "            dep_zone pr1\n" + 
                                                        "            where \n" + 
                                                        "            pr1.EFFECTIVE_DT = \n" + 
                                                        "                            (select \n" + 
                                                        "                                    max( pr2.EFFECTIVE_DT ) \n" + 
                                                        "                                    from  dep_zone pr2 \n" + 
                                                        "                                    where  pr2.ITEM_ID = pr1.ITEM_ID and \n" + 
                                                        "                                                                    EFFECTIVE_DT   <=  "+to_date+" ) \n" + 
                                                        "            and pr1.ITEM_ID =mainitem.ITEM_ID\n" + 
                                                        "            ) xdpr\n" + 
                                                        "from  item mainitem \n" + 
                                                        "start with ITEM_ID in (select \n" + 
                                                        "                                                                                    ITEM_ID\n" + 
                                                        "                                                                                    from psd. item\n" + 
                                                        "                                                                                    start with ITEM_ID='"+vitemcode+"'\n" + 
                                                        "                                                                                    connect by prior f_item_id=item_id)\n" + 
                                                        "connect by prior m_item_id=item_id)\n" + 
                                                        "where xpr is not null or xdpr is not null                                   "; 
                                                         
                                                       ResultSet rsitem1decode = stmtDecodeitem1.executeQuery(vdecodeitem);
                                                       while (rsitem1decode.next()){ 
                                                       
                                                       
                                                          double vpric= rsitem1decode.getDouble("xxpr");
                                                          
                                                          double vdqty= rsitem1decode.getDouble("qty");
                                                         
                                                          String vitemid= rsitem1decode.getString("item_id");
                                                          
                                                           String     hdr_stat;
                                                          if(vitemid.equals(vitemcode)){        
                                                              if (vitemprice==0){vitemprice=vpric;}
                                                                         hdr_stat ="insert into IV_INTERFACE_TRX (TR_HDR_SEQ,\n" + 
                                                                                         " TR_DTL_SEQ,\n" + 
                                                                                         " TR_ITEM_CODE,\n" + 
                                                                                     //    " TR_ITEM_REV,\n" + 
                                                                                         " TR_WARHOUSE_CODE,\n" + 
                                                                                         " TR_TRX_QTY,\n" + 
                                                                                         " TR_PRICE,\n" + 
                                                                                         " TR_DISCOUNT,\n" + 
                                                                                         " TR_WH_FIXED_FLAG,\n" + 
                                                                                         " TR_WH_FIXED_USER,\n" + 
                                                                                         " TR_WH_FIXED_DATE,\n" + 
                                                                                         " TR_NOTE,\n" + 
                                                                                         " TR_STATUS,\n" + 
                                                                                         " TR_ERR_RESON,\n" + 
                                                                                         " TR_TO_WH,\n" + 
                                                                                         " TR_GIFT_QTY,\n" + 
                                                                                         " TR_UOM, \n" + 
                                                                                         " TR_CIN) values (\n" + 
                                                                                         maxItem+", \n" + 
                                                                                         itemcounter+", \n" + 
                                                                                         "'"+vitemcode+"', \n" + 
                                                                                  //       "null, \n" + 
                                                                                         "'"+vstorcode+"', \n" + 
                                                                                         vitemqty+", \n" + 
                                                                                         vitemprice+", \n" + 
                                                                                         vitemdisc+", \n" + 
                                                                                         "null, \n" + 
                                                                                         "null, \n" + 
                                                                                         "null, \n" + 
                                                                                         "null, \n" + 
                                                                                         "null, \n" + 
                                                                                         "null, \n" + 
                                                                                        "'"+ v2wh+"', \n" + 
                                                                                         vitembousqty+", \n" + 
                                                                                         "to_char(to_number('"+vitemunit+"')), \n" +
                                                                                        "'"+ this.erpcin+"')";   
                                                          }
                                                          else
                                                          {              hdr_stat ="insert into IV_INTERFACE_TRX (TR_HDR_SEQ,\n" + 
                                                                                         " TR_DTL_SEQ,\n" + 
                                                                                         " TR_ITEM_CODE,\n" + 
                                                                                     //    " TR_ITEM_REV,\n" + 
                                                                                         " TR_WARHOUSE_CODE,\n" + 
                                                                                         " TR_TRX_QTY,\n" + 
                                                                                         " TR_PRICE,\n" + 
                                                                                         " TR_DISCOUNT,\n" + 
                                                                                         " TR_WH_FIXED_FLAG,\n" + 
                                                                                         " TR_WH_FIXED_USER,\n" + 
                                                                                         " TR_WH_FIXED_DATE,\n" + 
                                                                                         " TR_NOTE,\n" + 
                                                                                         " TR_STATUS,\n" + 
                                                                                         " TR_ERR_RESON,\n" + 
                                                                                         " TR_TO_WH,\n" + 
                                                                                         " TR_GIFT_QTY,\n" + 
                                                                                         " TR_UOM, \n" + 
                                                                                         " TR_CIN) values (\n" + 
                                                                                         maxItem+", \n" + 
                                                                                         itemcounter+", \n" + 
                                                                                         "'"+vitemid+"', \n" + 
                                                                                  //       "null, \n" + 
                                                                                         "'"+vstorcode+"', \n" + 
                                                                                         vitemqty*vdqty+", \n" + 
                                                                                         vpric+", \n" + 
                                                                                         0+", \n" + 
                                                                                         "null, \n" + 
                                                                                         "null, \n" + 
                                                                                         "null, \n" + 
                                                                                         "null, \n" + 
                                                                                         "null, \n" + 
                                                                                         "null, \n" + 
                                                                                        "'"+ v2wh+"', \n" + 
                                                                                         vitembousqty+", \n" + 
                                                                                         "to_char(to_number('"+vitemunit+"')), \n" +
                                                                                        "'"+ this.erpcin+"')"; 
                                                                                        }
                                                                 stmterp.executeUpdate(hdr_stat);
                                                                 
                                                                 itemcounter=itemcounter+1;
                                                                 this.itemnu=this.itemnu+1;
                                                       }
                                         }             
                                         stmtitem1.close();
                               
                                  
                                         itemcounter=1;
                          
                 }
                               
                               
                               
                               
             
             maxItem=maxItem+1;
             this.hdrnum=this.hdrnum+1;
             }
        //netload transaction the End
        //
        //
        ////////////////////////////////////  
        
        //////////////////////////////////
        // start sales transaction
        /////////////////////////////////
        
         Statement stmtinv = con.createStatement();
         ResultSet rsinv = stmtinv.executeQuery
         ("SELECT ALL \n" + 
         "INV_TRANS_TYPE.TRN_TYP_NM_LL, \n" + //1
         "INV_DTL.INVOICE_ID,\n" + //2
         "INV_DTL.INV_TYP_CD, \n" + //3
         "INV_DTL.LOCATION_ID, \n" + //4
         "INV_DTL.INT_I_TRN_ID, \n" + //5
         "INV_HDR.CUSTOMER_ID, \n" + //6
         "INV_HDR.R_LOC_ID, \n" + //7
         "INV_HDR.S_PERSON_ID, \n" + //8
         "PCALDR.FROM_DT \n"+//9
         "FROM \n" + 
         "SETTL_HD, \n" + 
         "PCALDR, \n" + 
         "INV_DTL, \n" + 
         "INV_HDR, \n" + 
         "INV_TRANS_TYPE\n" + 
         "WHERE PCALDR.FROM_DT=TO_DATE('"+postdate+"', 'mm/dd/yyyy')\n" + 
         " AND  SETTL_HD.PERIOD_CD=PCALDR.PERIOD_CD\n" + 
         " AND SETTL_HD.PERIOD_YR_NR=PCALDR.PERIOD_YR_NR\n" + 
         " AND INV_DTL.INVOICE_ID=INV_HDR.INVOICE_ID\n" + 
         " AND INV_DTL.INV_TYP_CD=INV_HDR.INV_TYP_CD\n" + 
         " AND INV_DTL.LOCATION_ID=INV_HDR.LOCATION_ID\n" + 
         " AND INV_HDR.SETTLE_ID=SETTL_HD.SETTLE_ID\n" + 
         " AND INV_DTL.INT_I_TRN_ID=INV_TRANS_TYPE.TRN_TYPE_ID\n" + 
         "GROUP BY \n" + 
         "INV_HDR.CUSTOMER_ID, \n" + 
         "INV_TRANS_TYPE.TRN_TYP_NM_LL, \n" + 
         "INV_DTL.INVOICE_ID, \n" + 
         "INV_DTL.INV_TYP_CD, \n" + 
         "INV_DTL.LOCATION_ID, \n" + 
         "INV_DTL.INT_I_TRN_ID, \n" + 
         "INV_HDR.R_LOC_ID, \n" + 
         "PCALDR.FROM_DT, \n" + 
         "INV_HDR.S_PERSON_ID\n" +
         " order by INV_DTL.INVOICE_ID,decode(INV_DTL.INT_I_TRN_ID,'3','99',INV_DTL.INT_I_TRN_ID) \n");
 
         
         

         while (rsinv.next()) {
             this.h2ef.transnm.setText("->"+this.hdrnum);
             this.h2ef.transnm1.setText("->"+this.itemnu);
             this.h2ef.transnm2.setText("->"+this.descnum);
             this.h2ef.jProgressBar3.setValue(this.h2ef.jProgressBar3.getValue()+1);


             Date vdate;
             vdate = rsinv.getDate(9);
             String vAbbrev = rsinv.getString(5);
             String vName = rsinv.getString(1);
             String vNumber = rsinv.getString(2);
             String vPayType = rsinv.getString(3);
             String vcurNumber = "1";
             int vCurrencyVal = 1;
             String vcostCode = "";
             String vcustnum = rsinv.getString(6);
             String vsalsmanacc = rsinv.getString(8);
             String to_date =" to_date('"+vdate+"','yyyy-mm-dd') ";
             String v2wh = rsinv.getString(7);  
             
             String vAddNumber = vNumber;
             String vAdd_Abbrev;
             if(  Integer.valueOf( vPayType)==1)
             {
                        
             }
             else
             {
           
             vAddNumber=vNumber+'+';
             }
             vAdd_Abbrev=vAbbrev;
             
             if(vAbbrev.equals("5")){
             vAbbrev="55";
             vAdd_Abbrev="5";
             }
             
             if(vAbbrev.equals("6")){
             vAbbrev="66";
             vAdd_Abbrev="6";
             }
             
             String hdr_statcheck =  "select count(*)  \n" + 
                                     "from\n" + 
                                     " IV_INTERFACE_HDR \n" + 
                                     "where  \n" + 
                                     "INTR_TRX_NO = ? \n" + 
                                     " and\n" + 
                                     "INTR_TRX_CODE =?";  
                                 
             PreparedStatement  hd_insertcheck = conerp.prepareStatement(hdr_statcheck);
             
             //add
              if(  Integer.valueOf( vPayType)==1)
              {
                  hd_insertcheck.setString(1, vNumber);          
              }
              else
              {
                  hd_insertcheck.setString(1, vAddNumber); 
             
              }
             
             hd_insertcheck.setString(2, vAbbrev);             
             rsroom= hd_insertcheck.executeQuery();
             rsroom.next();
             int val= rsroom.getInt(1);
             
             rsroom.close();
             hd_insertcheck.close();
             int v_add_2_2_hdr=0;
             if (val== 0)
             {
              v_add_2_2_hdr=0;
             String hdr_stat1 ="insert into IV_INTERFACE_HDR (\n" + 
                                 "INTR_SEQ, \n" +
                                 "INTR_FILE_NAME,\n" + 
                                 "INTR_FROM_DATE,\n" + 
                                 "INTR_TO_DATE,\n" + 
                                 "INTR_IMPORT_DATE,\n" + 
                                 "INTR_TRX_CODE,\n" + 
                                 "INTR_TRX_NAME, \n" + 
                                 "INTR_TRX_DATE, \n" + 
                                 "INTR_TRX_NO,\n" + 
                                 "INTR_DELEGATE_ID,\n" + //10
                                 "INTR_CUSTOMER_ID,\n" + //
                                 "INTR_CASH_FLAG, \n" + 
                                 "INTR_CURRENCY_ID,\n" + 
                                 "INTR_EXCHANG_RATE,\n" + 
                                 "INTR_COST_CENTER, \n" + //14
                                 "INTR_DUE_DATE, \n" + 
                                 "INTR_CIN)\n" + 
                                 "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
             
                                     PreparedStatement  hd_insert = conerp.prepareStatement(hdr_stat1);
                                    
                                     if(  Integer.valueOf( vPayType)==1)
                                     {
                                     hd_insert.setInt(12, -1);
                                    
                                     }
                                     else
                                     {
                                     hd_insert.setInt(12, 0);
                                     
                                     }
                                     hd_insert.setInt(1, maxItem);
                                     hd_insert.setString(2, "");   
                                     hd_insert.setDate(3, vdate);   
                                     hd_insert.setDate(4, vdate);   
                                     hd_insert.setDate(5, vdate);   
                                     hd_insert.setString(6, vAbbrev);   
                                     hd_insert.setString(7, vName);   
                                     hd_insert.setDate(8, vdate);   
                                     //add
                                      if(  Integer.valueOf( vPayType)==1)
                                      {
                                            
                                        hd_insert.setString(9, vNumber);        
                                      }
                                      else
                                      {
                                          
                                        hd_insert.setString(9,vAddNumber );
                                      }
                                      
                                     hd_insert.setString(10, vsalsmanacc);   
                                     hd_insert.setString(11, vcustnum);  
                                      
                                     hd_insert.setInt(13, Double.valueOf(vcurNumber).intValue());   
                                     hd_insert.setInt(14, vCurrencyVal);   
                                     hd_insert.setString(15, vcostCode);   
                                     hd_insert.setDate(16, vdate);
                                    hd_insert.setString(17, this.erpcin);
                                     hd_insert.execute();
                                     hd_insert.close(); 
                                     
                                     //add
                                     String vstmItem = "SELECT ALL \n" + 
                                     "INV_DTL.INVOICE_ID, \n" + //1
                                     "INV_DTL.ITEM_ID, \n" + //2
                                     "INV_DTL.INT_I_TRN_ID, \n" + //3
                                     "INV_DTL.INV_ITEM_QTY, \n" + //4
                                     "INV_DTL.I_UNIT_PR_AM, \n" + //5
                                     "INV_DTL.C_UNIT_PR_AM, \n" + //6
                                     "INV_DTL.C_TOT_PR_AM, \n" + //7
                                     "INV_DTL.I_TOT_PR_AM, \n" + //8
                                     "INV_DTL.DSP_SEQ_NR, \n" + //9
                                     "INV_DTL.I_DISC_AM, \n" + //10
                                     "INV_DTL.DEP_RED_AM, \n" + //11
                                     "INV_DTL.I_TOT_DEP_AM, \n" + //12
                                     "INV_DTL.I_DEP_AM, \n" + //12
                                     "INV_DTL.C_DISC_AM, \n" + //14
                                     "INV_DTL.STD_UNIT_PR_AM, \n" +//15 
                                     "INV_DTL.STD_DEP_AM, \n" + //16
                                     "INV_DTL.I_INDSC_AM\n" + //17
                                     "FROM \n" + 
                                     "INV_DTL\n" + 
                                     "WHERE ( INV_DTL.INT_I_TRN_ID='"+vAdd_Abbrev+"'  AND \n" + 
                                     "				INV_DTL.INVOICE_ID='"+vNumber+"' AND \n" + 
                                     "                          INV_DTL.INV_TYP_CD='"+vPayType+"'AND \n"+
                                     "				INV_DTL.LOCATION_ID='"+this.SDLOC+"')";
                                     Statement stmtitem1 = con1.createStatement();
                                     ResultSet rsitem1 = stmtitem1.executeQuery(vstmItem);
                                     
                                     
                 
                                    
                                     int itemcounter=1;
                                     String vstorcode =  "1";
                                     String vpromocode ="";
                              
                                     while (rsitem1.next()) {
                                                    
                                                    String vitemcode =  rsitem1.getString(2);
                                                    double vitembousqty, vitemqty ;
                                                    double vitemdisc =  0;
                                                    
                                                    //add
                                                    String v_promo_id_stm ="SELECT ALL INV_DSC_DTL.PROMOTION_ID\n" + 
                                                    "FROM INV_DSC_DTL\n" + 
                                                    "WHERE (INV_DSC_DTL.LOCATION_ID='"+this.SDLOC+"'\n" + 
                                                    " AND INV_DSC_DTL.INV_TYP_CD='"+vPayType+"'\n" + 
                                                    " AND INV_DSC_DTL.INVOICE_ID='"+vNumber+"'\n" + 
                                                    " AND INV_DSC_DTL.ITEM_ID='"+vitemcode+"'\n" + 
                                                 //   " AND INV_DSC_DTL.SEQ_NR=1\n" + 
                                                    " AND INV_DSC_DTL.INT_I_TRN_ID='"+vAdd_Abbrev+"') ORDER BY  INV_DSC_DTL.SEQ_NR";
                                                    Statement stmtpromoid = con1.createStatement();
                                                    ResultSet rspromo = stmtpromoid.executeQuery(v_promo_id_stm);
                                                  //  while (rspromo.next()){ vpromocode =  rspromo.getString(1); }
                                                  if( rspromo.next()){ vpromocode =  rspromo.getString(1);}
                                                    stmtpromoid.close();
                                                    if(vAbbrev.equals("3")||vAbbrev.equals("4"))
                                                        { 
                                                        vitemqty=0;
                                                        vitembousqty = rsitem1.getDouble(4);
                                                        vitemdisc =  0;
                                                        }
                                                    else 
                                                        { 
                                                        vitembousqty =0;
                                                        vitemqty =  rsitem1.getDouble(4);
                                                        vitemdisc =   Math.abs(rsitem1.getDouble(10));
                                                       
                                                        }
                                                     
                                                     String vitemunit =  "1";
                                                     double vitemprice =  rsitem1.getDouble(5);
                                                     
                                                    
                                                     
                                        
                                         
                                                     String vdecodeitem="select ITEM_ID, nvl(nvl(fqty,mqty),1) qty , nvl(nvl(xpr,xdpr),1) xxpr from \n" + 
                                                     "(select \n" + 
                                                     "level,ITEM_DS_LL,M_PCKAGE_QTY,F_PCKAGE_QTY,\n" + 
                                                     "mainitem.ITEM_ID,\n" + 
                                                     "(SELECT distinct  ITEM_x1.f_PCKAGE_QTY\n" + 
                                                     "FROM ITEM ITEM_x1, ITEM ITEM_xA1\n" + 
                                                     "WHERE ITEM_xA1.ITEM_ID=mainitem.ITEM_ID\n" + 
                                                     " AND  (ITEM_x1.F_ITEM_ID=ITEM_xA1.ITEM_ID))  fqty,\n" + 
                                                     " (SELECT distinct  ITEM_x1.m_PCKAGE_QTY\n" + 
                                                     "FROM ITEM ITEM_x1, ITEM ITEM_xA1\n" + 
                                                     "WHERE ITEM_xA1.ITEM_ID=mainitem.ITEM_ID\n" + 
                                                     " AND  (ITEM_x1.m_ITEM_ID=ITEM_xA1.ITEM_ID))  mqty,\n" + 
                                                     " (select \n" + 
                                                     "            pr1.PRICE_AM \n" + 
                                                     "            from \n" + 
                                                     "            prc_zone pr1\n" + 
                                                     "            where \n" + 
                                                     "            pr1.EFFECTIVE_DT = \n" + 
                                                     "                            (select \n" + 
                                                     "                                    max( pr2.EFFECTIVE_DT ) \n" + 
                                                     "                                    from  prc_zone pr2 \n" + 
                                                     "                                    where  pr2.ITEM_ID = pr1.ITEM_ID and \n" + 
                                                     "                                                                    EFFECTIVE_DT   <=  "+to_date+" ) \n" + 
                                                     "            and pr1.ITEM_ID =mainitem.ITEM_ID\n" + 
                                                     "            ) xpr,\n" + 
                                                     " (select \n" + 
                                                     "            pr1.DEPOSIT_AM \n" + 
                                                     "            from \n" + 
                                                     "            dep_zone pr1\n" + 
                                                     "            where \n" + 
                                                     "            pr1.EFFECTIVE_DT = \n" + 
                                                     "                            (select \n" + 
                                                     "                                    max( pr2.EFFECTIVE_DT ) \n" + 
                                                     "                                    from  dep_zone pr2 \n" + 
                                                     "                                    where  pr2.ITEM_ID = pr1.ITEM_ID and \n" + 
                                                     "                                                                    EFFECTIVE_DT   <=  "+to_date+" ) \n" + 
                                                     "            and pr1.ITEM_ID =mainitem.ITEM_ID\n" + 
                                                     "            ) xdpr\n" + 
                                                     "from  item mainitem \n" + 
                                                     "start with ITEM_ID in (select \n" + 
                                                     "                                                                                    ITEM_ID\n" + 
                                                     "                                                                                    from psd. item\n" + 
                                                     "                                                                                    start with ITEM_ID='"+vitemcode+"'\n" + 
                                                     "                                                                                    connect by prior f_item_id=item_id)\n" + 
                                                     "connect by prior m_item_id=item_id)\n" + 
                                                     "where xpr is not null or xdpr is not null                                   "; 
                                                      
                                                    ResultSet rsitem1decode = stmtDecodeitem1.executeQuery(vdecodeitem);
                                                    while (rsitem1decode.next()){ 
                                                    
                                                    
                                                       double vpric= rsitem1decode.getDouble("xxpr");
                                                       
                                                       double vdqty= rsitem1decode.getDouble("qty");
                                                      
                                                       String vitemid= rsitem1decode.getString("item_id");
                                                           
                                                        String     hdr_stat;
                                                       if(vitemid.equals(vitemcode)){           
                                                                         
                                                                         
                                                                         
                                                                 if (vitemprice==0){vitemprice=vpric;}   
                                                                     hdr_stat ="insert into IV_INTERFACE_TRX (TR_HDR_SEQ,\n" + 
                                                                     " TR_DTL_SEQ,\n" + 
                                                                     " TR_ITEM_CODE,\n" + 
                                                                 //    " TR_ITEM_REV,\n" + 
                                                                     " TR_WARHOUSE_CODE,\n" + 
                                                                     " TR_TRX_QTY,\n" + 
                                                                     " TR_PRICE,\n" + 
                                                                     " TR_DISCOUNT,\n" + 
                                                                     " TR_WH_FIXED_FLAG,\n" + 
                                                                     " TR_WH_FIXED_USER,\n" + 
                                                                     " TR_WH_FIXED_DATE,\n" + 
                                                                     " TR_NOTE,\n" + 
                                                                     " TR_STATUS,\n" + 
                                                                     " TR_ERR_RESON,\n" + 
                                                                     " TR_TO_WH,\n" + 
                                                                     " TR_GIFT_QTY,\n" + 
                                                                     " TR_UOM, \n" + 
                                                                     "TR_GIFT_CODE , \n"+
                                                                     " TR_CIN) values (\n" + 
                                                                     maxItem+", \n" + 
                                                                     itemcounter+", \n" + 
                                                                     "'"+vitemcode+"', \n" + 
                                                              //       "null, \n" + 
                                                                     "'"+v2wh+"', \n" + 
                                                                     vitemqty+", \n" + 
                                                                     vitemprice+", \n" + 
                                                                     vitemdisc+", \n" + 
                                                                     "null, \n" + 
                                                                     "null, \n" + 
                                                                     "null, \n" + 
                                                                     "null, \n" + 
                                                                     "null, \n" + 
                                                                     "null, \n" + 
                                                                     "null, \n" + 
                                                                     vitembousqty+", \n" + 
                                                                     "to_char(to_number('"+vitemunit+"')), \n" +
                                                                     "'"+vpromocode+"', \n" + 
                                                                     "'"+ this.erpcin+"')";   
                                                                  
                                                       }
                                                       else
                                                       {
                                                                       if(vAbbrev.equals("3"))
                                                                       {
                                                                            //add
                                                                            String v_stmtlookerp;
                                                                             if(  Integer.valueOf( vPayType)==1)
                                                                             {
                                                                                 v_stmtlookerp ="SELECT INTR_SEQ\n" + 
                                                                           "FROM IV_INTERFACE_HDR\n" + 
                                                                           "WHERE  INTR_TRX_CODE='15'  AND \n" + 
                                                                           "             INTR_TRX_NO='"+vNumber+"'";    
                                                                                    
                                                                             }
                                                                             else
                                                                             {
                                                                                v_stmtlookerp ="SELECT INTR_SEQ\n" + 
                                                                           "FROM IV_INTERFACE_HDR\n" + 
                                                                           "WHERE  INTR_TRX_CODE='15'  AND \n" + 
                                                                           "             INTR_TRX_NO='"+vAddNumber+"'";  
                                                                             
                                                                             }
                                                                           
                                                                           
                                                                           ResultSet rsstmtlookerp = stmtlookerp.executeQuery(v_stmtlookerp);
                                                                           int v_temp_seq=0;
                                                                           while (rsstmtlookerp.next()){ v_temp_seq =  rsstmtlookerp.getInt(1); }
                                                                           if (v_temp_seq==0)
                                                                                {
                                                                                    String hdr_stat_add ="insert into IV_INTERFACE_HDR (\n" + 
                                                                                                        "INTR_SEQ, \n" +
                                                                                                        "INTR_FILE_NAME,\n" + 
                                                                                                        "INTR_FROM_DATE,\n" + 
                                                                                                        "INTR_TO_DATE,\n" + 
                                                                                                        "INTR_IMPORT_DATE,\n" + 
                                                                                                        "INTR_TRX_CODE,\n" + 
                                                                                                        "INTR_TRX_NAME, \n" + 
                                                                                                        "INTR_TRX_DATE, \n" + 
                                                                                                        "INTR_TRX_NO,\n" + 
                                                                                                        "INTR_DELEGATE_ID,\n" + //10
                                                                                                        "INTR_CUSTOMER_ID,\n" + //
                                                                                                        "INTR_CASH_FLAG, \n" + 
                                                                                                        "INTR_CURRENCY_ID,\n" + 
                                                                                                        "INTR_EXCHANG_RATE,\n" + 
                                                                                                        "INTR_COST_CENTER, \n" + //14
                                                                                                        "INTR_DUE_DATE, \n" + 
                                                                                                        "INTR_CIN)\n" + 
                                                                                                        "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
                                                                                    
                                                                                                            PreparedStatement  hd_insert_add = conerp.prepareStatement(hdr_stat_add);
                                                                                                            hd_insert_add.setInt(1, maxItem+1);
                                                                                                            hd_insert_add.setString(2, "");   
                                                                                                            hd_insert_add.setDate(3, vdate);   
                                                                                                            hd_insert_add.setDate(4, vdate);   
                                                                                                            hd_insert_add.setDate(5, vdate);   
                                                                                                            hd_insert_add.setString(6, "15");   
                                                                                                            hd_insert_add.setString(7, "»Ì⁄");   
                                                                                                            hd_insert_add.setDate(8, vdate);   
                                                                                                            //add
                                                                                                             if(  Integer.valueOf( vPayType)==1)
                                                                                                             {
                                                                                                                 hd_insert_add.setString(9, vNumber);     
                                                                                                                  
                                                                                                             }
                                                                                                             else
                                                                                                             {
                                                                                                                 hd_insert_add.setString(9, vAddNumber);   
                                                                                                               
                                                                                                             }
                                                                                                              
                                                                                                            hd_insert_add.setString(10, vsalsmanacc);   
                                                                                                            hd_insert_add.setString(11, vcustnum);  
                                                                                                              if(  Integer.valueOf( vPayType)==1)
                                                                                                              {hd_insert_add.setInt(12, -1);}
                                                                                                              else
                                                                                                              {hd_insert_add.setInt(12, 0);}
                                                                                                            hd_insert_add.setInt(13, Double.valueOf(vcurNumber).intValue());   
                                                                                                            hd_insert_add.setInt(14, vCurrencyVal);   
                                                                                                            hd_insert_add.setString(15, vcostCode);   
                                                                                                            hd_insert_add.setDate(16, vdate);
                                                                                                           hd_insert_add.setString(17, this.erpcin);
                                                                                                            hd_insert_add.execute();
                                                                                                            hd_insert_add.close();
                                                                                                            
                                                                                                            v_temp_seq=maxItem+1;
                                                                                                            v_add_2_2_hdr=1;
                                                                                }
                                                                           String v_stmtmaxerp ="SELECT  MAX(TR_DTL_SEQ) maxline\n" + 
                                                                               "FROM IV_INTERFACE_TRX\n" + 
                                                                               "WHERE TR_HDR_SEQ = "+v_temp_seq;
                                                                           
                                                                           ResultSet rsstmtmaxerp = stmtmaxerp.executeQuery(v_stmtmaxerp);
                                                                           int v_templine_seq=0 ;
                                                                           while (rsstmtmaxerp.next()){ v_templine_seq =  rsstmtmaxerp.getInt(1); }
                                                                           v_templine_seq = v_templine_seq+1;
                                                                           
                                                                           hdr_stat ="insert into IV_INTERFACE_TRX (TR_HDR_SEQ,\n" + 
                                                                                     " TR_DTL_SEQ,\n" + 
                                                                                     " TR_ITEM_CODE,\n" + 
                                                                                 //    " TR_ITEM_REV,\n" + 
                                                                                     " TR_WARHOUSE_CODE,\n" + 
                                                                                     " TR_TRX_QTY,\n" + 
                                                                                     " TR_PRICE,\n" + 
                                                                                     " TR_DISCOUNT,\n" + 
                                                                                     " TR_WH_FIXED_FLAG,\n" + 
                                                                                     " TR_WH_FIXED_USER,\n" + 
                                                                                     " TR_WH_FIXED_DATE,\n" + 
                                                                                     " TR_NOTE,\n" + 
                                                                                     " TR_STATUS,\n" + 
                                                                                     " TR_ERR_RESON,\n" + 
                                                                                     " TR_TO_WH,\n" + 
                                                                                     " TR_GIFT_QTY,\n" + 
                                                                                     " TR_UOM, \n" + 
                                                                                     " TR_GIFT_CODE , \n"+
                                                                                     " TR_CIN) values (\n" + 
                                                                                     v_temp_seq+", \n" + 
                                                                                     v_templine_seq+", \n" + 
                                                                                     "'"+vitemid+"', \n" + 
                                                                              //       "null, \n" + 
                                                                                     "'"+v2wh+"', \n" + 
                                                                                     vitembousqty*vdqty+", \n" + 
                                                                                     vpric+", \n" + 
                                                                                      0+", \n" + 
                                                                                     "null, \n" + 
                                                                                     "null, \n" + 
                                                                                     "null, \n" + 
                                                                                     "null, \n" + 
                                                                                     "null, \n" + 
                                                                                     "null, \n" + 
                                                                                     "null, \n" + 
                                                                                     vitemqty+", \n" + 
                                                                                     "to_char(to_number('"+vitemunit+"')), \n" +
                                                                                     "'"+vpromocode+"', \n" + 
                                                                                   "'"+ this.erpcin+"')"; 
                                                                       }
                                                                       else
                                                                       {
                                                                           hdr_stat ="insert into IV_INTERFACE_TRX (TR_HDR_SEQ,\n" + 
                                                                                     " TR_DTL_SEQ,\n" + 
                                                                                     " TR_ITEM_CODE,\n" + 
                                                                                 //    " TR_ITEM_REV,\n" + 
                                                                                     " TR_WARHOUSE_CODE,\n" + 
                                                                                     " TR_TRX_QTY,\n" + 
                                                                                     " TR_PRICE,\n" + 
                                                                                     " TR_DISCOUNT,\n" + 
                                                                                     " TR_WH_FIXED_FLAG,\n" + 
                                                                                     " TR_WH_FIXED_USER,\n" + 
                                                                                     " TR_WH_FIXED_DATE,\n" + 
                                                                                     " TR_NOTE,\n" + 
                                                                                     " TR_STATUS,\n" + 
                                                                                     " TR_ERR_RESON,\n" + 
                                                                                     " TR_TO_WH,\n" + 
                                                                                     " TR_GIFT_QTY,\n" + 
                                                                                     " TR_UOM, \n" + 
                                                                                     "TR_GIFT_CODE , \n"+
                                                                                     " TR_CIN) values (\n" + 
                                                                                     maxItem+", \n" + 
                                                                                     itemcounter+", \n" + 
                                                                                     "'"+vitemid+"', \n" + 
                                                                              //       "null, \n" + 
                                                                                     "'"+v2wh+"', \n" + 
                                                                                     vitemqty*vdqty+", \n" + 
                                                                                     vpric+", \n" + 
                                                                                      0+", \n" + 
                                                                                     "null, \n" + 
                                                                                     "null, \n" + 
                                                                                     "null, \n" + 
                                                                                     "null, \n" + 
                                                                                     "null, \n" + 
                                                                                     "null, \n" + 
                                                                                     "null, \n" + 
                                                                                     vitembousqty+", \n" + 
                                                                                     "to_char(to_number('"+vitemunit+"')), \n" +
                                                                                     "'"+vpromocode+"', \n" + 
                                                                                   "'"+ this.erpcin+"')"; 
                                                                       }  
                                                         
                                                                   }
                                                                   if (this.itemnu==3996)
                                                                   {
                                                                   int xxxx=0;
                                                                   }
                                                           stmterp.executeUpdate(hdr_stat);
                                                 itemcounter=itemcounter+1;
                                                 this.itemnu=this.itemnu+1;
                                                 
                                                       }
                                     }             
                                     stmtitem1.close();
                           
                              
                                     itemcounter=1;
                      
             }
                           
                           
                           
                           
         if(v_add_2_2_hdr==1){maxItem=maxItem+2;}else{ maxItem=maxItem+1;}
        
         this.hdrnum=this.hdrnum+1;
       
         } //while for invoices transaction
         
        
        
        
        
        conerp.commit();
        stmt.close();
        con.close();
        stmterp.close();
        conerp.close();
        JOptionPane.showMessageDialog(null,"The Operation complete successfully","SD2ERP",JOptionPane.INFORMATION_MESSAGE);
    } catch (java.lang.Exception ex) {
    
            this.h2ef.transnm.setText("0");
            this.h2ef.transnm1.setText("0");
            this.h2ef.transnm2.setText("0");
            JOptionPane.showMessageDialog(null,"The Operation NOT complete","SD2ERP",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }

}