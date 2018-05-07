package un.ocha.sy.dataync.json;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;

import un.ocha.sy.dataync.entity.Ocha_dashboard_webtag;
import un.ocha.sy.dataync.entity.QryaccessTotal;
import un.ocha.sy.dataync.entity._1OnlineIaoDelivered;
import un.ocha.sy.dataync.entity._31qrycnvsAll;
import un.ocha.sy.dataync.entity._34qrycnvsLocation;
import un.ocha.sy.dataync.entity._3qrycnvsAll;
import un.ocha.sy.dataync.entity._405onlineIaRequest;
import un.ocha.sy.dataync.entity._41OnlineQrycnvsAllByaccessstatus;
import un.ocha.sy.dataync.entity._6OnlineTargetApprovedReached;
import un.ocha.sy.dataync.entity._7OnlineMountainreach;

public class Gene_Json {
	
	MSaccess_db datasource;
	
public	String Jstr_1onlineioade;
	
public   String Jstr_05online_ia_requests;

public	String Jstr_06_online_target_approved_reached;

public	String Jstr_07_online_mountainreach;

public   String Jstr_31qrycnvsAll;

public   String Jstr_1_34qrycnvs_locations;

public	String Jstr_1_3qrycnvs_all;

public	String Jstr_2_4_1_online_qrycnvs_all_byaccessstatus;

public	String Jstr_QryaccessTotal;

public String Jstr_ocha_dashboard_webtag;

public String [][] json_Cup =new String[10][2];

ObjectMapper mapper;

	
	public Gene_Json(MSaccess_db datas) {
	
		this.datasource=datas;	
		 this.mapper = new ObjectMapper();
		 this.gj_qryaccess_total() ;
		 this.gj_ocha_dashboard_webtag();
		 this.gj_05online_ia_requests();
		 this.gj_06_online_target_approved_reached();
		 this.gj_07_online_mountainreach();
		 this.gj_1_31qrycnvs_all();
		 this.gj_1_34qrycnvs_locations();
		 this.gj_1_3qrycnvs_all();
		 this.gj_1onlineioade();
		 this.gj_2_4_1_online_qrycnvs_all_byaccessstatus();
		 
		 
		
	}
	
	@SuppressWarnings("serial")
	Gene_Json gj_1onlineioade(){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	List<_1OnlineIaoDelivered> json_table=new ArrayList<_1OnlineIaoDelivered>();
	
	Statement s;
	try {
		s = this.datasource.ms_connaction.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM [01_online_iao_delivered]");
	
				while(rs.next()) {
					_1OnlineIaoDelivered table_row  = new _1OnlineIaoDelivered();   
					
					table_row.setCommunity_PCODE(rs.getString("community_PCODE") );
					table_row.setConvoy_Date(df.format(rs.getDate("convoy_Date")));
					table_row.setConvoy_Type(rs.getString("convoy_Type"));
					table_row.setLatitude_WGS84(rs.getBigDecimal("latitude_WGS84"));
					table_row.setLocation(rs.getString("location"));
					table_row.setAccess_Status(rs.getString("Access_Status"));
					table_row.setLongitude_WGS84(rs.getBigDecimal("longitude_WGS84"));
					table_row.setOperationMonth(rs.getShort("operationMonth"));
					table_row.setOperationYear(rs.getShort("operationYear"));
					table_row.setTotal_Operations(rs.getInt("Total Operations"));
					table_row.setTotal_Reach(rs.getDouble("total_Reach"));
					json_table.add(table_row);
					
				} 
			 
				
				this.Jstr_1onlineioade= mapper.writeValueAsString(json_table);
				this.json_Cup[0][0]="webapi/Rs_DS_01online";
				this.json_Cup[0][1]=this.Jstr_1onlineioade;
				
				
				
	} catch (SQLException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return this;
	
}
	
	@SuppressWarnings("serial")
	Gene_Json gj_05online_ia_requests(){
		
		  
	List<_405onlineIaRequest> json_table=new ArrayList<_405onlineIaRequest>();
	
	Statement s;
	try {
		s = this.datasource.ms_connaction.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM [04__05online_ia_requests]");
	
	
				while(rs.next()) {
					_405onlineIaRequest table_row  = new _405onlineIaRequest();   
					
					table_row.setYear (rs.getInt("Year") );
					table_row.setMonth ( rs.getInt("Plan_Month_Sequence") );
					table_row.setMonth_Text (rs.getString("Month_Text"));
					table_row.setSumOfNumber_of_requests (rs.getDouble("SumOfNumber of requests"));
					table_row.setRequest_Status (rs.getString("Request_Status"));
					
					json_table.add(table_row);
					
				} 
			 
				
				this.Jstr_05online_ia_requests = mapper.writeValueAsString(json_table);
				this.json_Cup[1][0]="webapi/Rs_DS_05online_ia_requests";
				this.json_Cup[1][1]=this.Jstr_05online_ia_requests;
				s.close();
				
	} catch (SQLException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return this;
	
}

	@SuppressWarnings("serial")
	Gene_Json gj_06_online_target_approved_reached(){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	List<_6OnlineTargetApprovedReached> json_table=new ArrayList<_6OnlineTargetApprovedReached>();
	
	Statement s;
	try {
		s = this.datasource.ms_connaction.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM [06_online_target_approved_reached]");

				while(rs.next()) {
					_6OnlineTargetApprovedReached table_row  = new _6OnlineTargetApprovedReached();   
					
					table_row.setConvoy_Year(rs.getShort("Convoy_Year") );
					table_row.setConvoy_Month(rs.getShort("Convoy_Month"));
					table_row.setMonth_Text(rs.getString("Month_Text"));
					table_row.setRequested_targeted_people(rs.getDouble("Requested_targeted_people"));
					table_row.setApprovedBeneficiaries(rs.getDouble("ApprovedBeneficiaries"));
					table_row.setConditional_Approval_Beneficiaries(rs.getDouble("Conditional_Approval_Beneficiaries"));
					table_row.setNet_Reached(rs.getDouble("Net_Reached"));
					table_row.setCumulative_Reached(rs.getDouble("Cumulative_Reached"));
			 
					json_table.add(table_row);
					
				} 
			 
				
				this.Jstr_06_online_target_approved_reached = mapper.writeValueAsString(json_table);
				this.json_Cup[2][0]="webapi/Rs_DS_06_online_target_approved_reached";
				this.json_Cup[2][1]=this.Jstr_06_online_target_approved_reached;
				s.close();
				
				
	} catch (SQLException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return this;
	
}
	
	
	@SuppressWarnings("serial")
	Gene_Json gj_07_online_mountainreach(){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	List<_7OnlineMountainreach> json_table=new ArrayList<_7OnlineMountainreach>();
	
	PreparedStatement s;
	try {
		s = this.datasource.ms_connaction.prepareStatement("SELECT * FROM [07_Online_MountainReach]");
													   
		ResultSet rs = s.executeQuery();

				while(rs.next()) {
					_7OnlineMountainreach table_row  = new _7OnlineMountainreach();   
					
					table_row.setYear_Number(rs.getShort("Year_Number") );
					table_row.setMonth_Number(rs.getShort("Month_Number"));
					table_row.setHTR_IAConvoys(rs.getDouble("HTR_IAConvoys"));
					table_row.setHtrUnrwa(rs.getDouble("HTR_UNRWA"));
					table_row.setBsg_Air_Drops(rs.getDouble("Bsg_Air_Drops"));
					table_row.setBsg_IAConvoys(rs.getDouble("Bsg_IAConvoys"));
					table_row.setBsg_UNRWA(rs.getDouble("Bsg_UNRWA"));
					 
			 
					json_table.add(table_row);
					
				} 
			 
				
				this.Jstr_07_online_mountainreach= mapper.writeValueAsString(json_table);
				
				this.json_Cup[3][0]="webapi/Rs_DS_07_Online_MountainReach";
				this.json_Cup[3][1]=this.Jstr_07_online_mountainreach;
				s.close();
				
	} catch (SQLException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return this;
	
}

	
	@SuppressWarnings("serial")
	Gene_Json gj_1_31qrycnvs_all(){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	List<_31qrycnvsAll> json_table=new ArrayList<_31qrycnvsAll>();
	
	Statement s;
	try {
		s = this.datasource.ms_connaction.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM [1_31qrycnvs_all]");
	
		

				while(rs.next()) {
					_31qrycnvsAll table_row  = new _31qrycnvsAll();   
					
					table_row.setConvoyCode(rs.getString("ConvoyCode") );
					table_row.setConvoy_Date(df.format(rs.getDate("Convoy_Date")));
					table_row.setConvoy_Type(rs.getString("Convoy_Type"));
					table_row.setMultipleConvoys(rs.getByte("MultipleConvoys"));
					table_row.setCalculationMethod(rs.getString("CalculationMethod"));
					table_row.setCalcRef_Code(rs.getString("CalcRef_Code"));
					table_row.setTotal_Locations(rs.getInt("Total_Locations"));
					table_row.setSumOfNumber_of_Trucks(rs.getDouble("SumOfNumber_of_Trucks"));
					table_row.setCnvyLocation_Target_Beneficiaries(rs.getDouble("CnvyLocation_Target_Beneficiaries"));
					table_row.setCnvyLocation_Total_Beneficiaries_Reached(rs.getDouble("CnvyLocation_Total_Beneficiaries_Reached"));
					table_row.setLocation_ID(rs.getInt("Location_ID"));
					json_table.add(table_row);
					
				} 
				 
				
				this.Jstr_31qrycnvsAll = mapper.writeValueAsString(json_table);
				this.json_Cup[4][0]="webapi/Rs_DS_1_31qrycnvs_all";
				this.json_Cup[4][1]=this.Jstr_31qrycnvsAll;
				
				s.close();
				
				
	} catch (SQLException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return this;
	
}
	
	@SuppressWarnings("serial")
	Gene_Json gj_1_34qrycnvs_locations(){
		
		  
	List<_34qrycnvsLocation> json_table=new ArrayList<_34qrycnvsLocation>();
	
	Statement s;
	try {
		s = this.datasource.ms_connaction.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM [1_34qrycnvs_locations]");
	
	
				while(rs.next()) {
					_34qrycnvsLocation table_row  = new _34qrycnvsLocation();   
	
					table_row.setConvoyCode (rs.getString("ConvoyCode") );
					table_row.setCommunity_PCODE ( rs.getString("Community_PCODE") );
					table_row.setLocation (rs.getString("Location"));
					table_row.setConvoy_Type (rs.getString("Convoy_Type"));
					 
					
					json_table.add(table_row);
					
				} 
				 
				
				this.Jstr_1_34qrycnvs_locations = mapper.writeValueAsString(json_table);
				this.json_Cup[5][0]="webapi/Rs_DS_1_34qrycnvs_locations";
				this.json_Cup[5][1]=this.Jstr_1_34qrycnvs_locations;
				s.close();
				
				
	} catch (SQLException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return this;
	
}
	@SuppressWarnings("serial")
	Gene_Json gj_1_3qrycnvs_all(){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	List<_3qrycnvsAll> json_table=new ArrayList<_3qrycnvsAll>();
	
	Statement s;
	try {
		s = this.datasource.ms_connaction.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM [1_3qrycnvs_all]");
	
		
		


				while(rs.next()) {
					_3qrycnvsAll table_row  = new _3qrycnvsAll();   
					
					table_row.setConvoyCode(rs.getString("ConvoyCode") );
					table_row.setConvoy_Date(df.format(rs.getDate("Convoy_Date")));
					table_row.setConvoy_Type(rs.getString("Convoy_Type"));
					table_row.setMultipleConvoys(rs.getByte("MultipleConvoys"));
					table_row.setCalculationMethod(rs.getString("CalculationMethod"));
					table_row.setCalcRef_Code(rs.getString("CalcRef_Code"));
					table_row.setTotal_Locations(rs.getInt("Total_Locations"));
					table_row.setSumOfNumber_of_Trucks(rs.getDouble("SumOfNumber_of_Trucks"));
					table_row.setCnvyLocation_Target_Beneficiaries(rs.getDouble("CnvyLocation_Target_Beneficiaries"));
					table_row.setCnvyLocation_Total_Beneficiaries_Reached(rs.getDouble("CnvyLocation_Total_Beneficiaries_Reached"));
					
					json_table.add(table_row);
					
				} 
				 
				
				this.Jstr_1_3qrycnvs_all = mapper.writeValueAsString(json_table);
				this.json_Cup[6][0]="webapi/Rs_DS_1_3qrycnvs_all";
				this.json_Cup[6][1]=this.Jstr_1_3qrycnvs_all;
				s.close();
				
				
	} catch (SQLException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return this;
	
}

	@SuppressWarnings("serial")
	Gene_Json gj_2_4_1_online_qrycnvs_all_byaccessstatus(){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	List<_41OnlineQrycnvsAllByaccessstatus> json_table=new ArrayList<_41OnlineQrycnvsAllByaccessstatus>();
	
	Statement s;
	try {
		s = this.datasource.uc_ms_connaction.createStatement();
		//ResultSet rs = s.executeQuery("SELECT * FROM [2_4_1_online_qrycnvs_all_byaccessstatus]");
		ResultSet rs = s.executeQuery(" SELECT  ConvoyCode,                                                                                     "
								  	+"  Convoy_Date,                                                                                          "
									//+"  Convoy_Type ,                                                                                          "
									+"  MultipleConvoys,                                                                                      "
									+"  CalculationMethod,                                                                                    "
									+"  CalcRef_Code,                                                                                         "
									+"  Updated_Access_Status,                                                                            "
									+"  SumOfNumber_of_Trucks,                                                       "
									+"  CnvyLocation_Total_Beneficiaries_Reached                      "
									+"  FROM [2_4_1_online_qrycnvs_all_byaccessstatus]  "	);
	
		


int fff=0;
				while(rs.next()) {
					_41OnlineQrycnvsAllByaccessstatus table_row  = new _41OnlineQrycnvsAllByaccessstatus();   
					System.out.println(fff++);
					table_row.setConvoyCode(rs.getString("ConvoyCode") );
					table_row.setConvoy_Date(df.format(rs.getDate("Convoy_Date")));
//					table_row.setConvoy_Type(rs.getString("Convoy_Type"));
					table_row.setConvoy_Type("Convoy_Type");
					table_row.setMultipleConvoys(rs.getByte("MultipleConvoys"));
					table_row.setCalculationMethod(rs.getString("CalculationMethod"));
					table_row.setCalcRef_Code(rs.getString("CalcRef_Code"));
					table_row.setUpdated_Access_Status(rs.getString("Updated_Access_Status"));
					table_row.setSumOfNumber_of_Trucks(rs.getDouble("SumOfNumber_of_Trucks"));
					table_row.setCnvyLocation_Total_Beneficiaries_Reached(rs.getDouble("CnvyLocation_Total_Beneficiaries_Reached"));
					
					json_table.add(table_row);
					
				} 
			 
				
				this.Jstr_2_4_1_online_qrycnvs_all_byaccessstatus = mapper.writeValueAsString(json_table);
				this.json_Cup[7][0]="webapi/Rs_DS_2_4_1_online_qrycnvs_all_byaccessstatus";
				this.json_Cup[7][1]=this.Jstr_2_4_1_online_qrycnvs_all_byaccessstatus;
				s.close();
				
				
	} catch (SQLException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return this;
	
}
	
	@SuppressWarnings("serial")
	Gene_Json gj_qryaccess_total(){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	List<QryaccessTotal> json_table=new ArrayList<QryaccessTotal>();
	
	Statement s;
	try {
		s = this.datasource.ms_connaction.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM [QryAccess_Total]");
	
		



				while(rs.next()) {
					QryaccessTotal table_row  = new QryaccessTotal();   
					
					
					table_row.setAccess_Status(rs.getString("Access_Status"));
					table_row.setSumOfEstimated_PIN_HTR_subdistrict(rs.getDouble("SumOfEstimated_PIN_HTR_subdistrict"));
					
					json_table.add(table_row);
					
				} 
		
				
					this.Jstr_QryaccessTotal = mapper.writeValueAsString(json_table);
					this.json_Cup[8][0]="webapi/Rs_DS_QryaccessTotal";
					this.json_Cup[8][1]=this.Jstr_QryaccessTotal;
				
					s.close();
					
				
	} catch (SQLException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return this;
	
}	
	
	@SuppressWarnings("serial")
	Gene_Json gj_ocha_dashboard_webtag(){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	List<Ocha_dashboard_webtag> json_table=new ArrayList<Ocha_dashboard_webtag>();
	
	Statement s;
	try {
		s = this.datasource.ms_connaction.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM [ocha_dashboard_webtag]");
	
		



				while(rs.next()) {
					Ocha_dashboard_webtag table_row  = new Ocha_dashboard_webtag();   
					
					
					table_row.setText_id(rs.getString("text_id"));
					table_row.setText_desc(rs.getString("text_desc"));
					table_row.setText_value(rs.getString("text_value"));
					 
					
					json_table.add(table_row);
					
				} 
				
		
				
				
				
				
					this.Jstr_ocha_dashboard_webtag = mapper.writeValueAsString(json_table);
					this.json_Cup[9][0]="webapi/Rs_DS_ocha_dashboard_webtag";
					this.json_Cup[9][1]=this.Jstr_ocha_dashboard_webtag;
				
				
					s.close();
					
				
	} catch (SQLException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return this;
	
}	
		
}
