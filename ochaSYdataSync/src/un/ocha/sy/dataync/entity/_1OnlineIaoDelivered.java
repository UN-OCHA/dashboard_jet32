package un.ocha.sy.dataync.entity;


import java.math.BigDecimal;
 
 




/**
 * The persistent class for the 01_online_iao_delivered database table.
 * 
 */
//@Entity
//@Table(name="01_online_iao_delivered")
//@NamedQuery(name="_1OnlineIaoDelivered.findAll", query="SELECT _ FROM _1OnlineIaoDelivered _")
public class _1OnlineIaoDelivered {


	private String community_PCODE;

	//@Temporal(TemporalType.TIMESTAMP)
	//@JsonbDateFormat("dd.MM.yyyy")
	private String convoy_Date;

	private String convoy_Type;

	private BigDecimal latitude_WGS84;

	private String Access_Status;
	
	public String getAccess_Status() {
		return Access_Status;
	}

	public void setAccess_Status(String access_Status) {
		Access_Status = access_Status;
	}

	private String location;

	private BigDecimal longitude_WGS84;

	private short operationMonth;

	private short operationYear;

	//@Column(name="`Total Operations`")
	private int total_Operations;

	private double total_Reach;

	public _1OnlineIaoDelivered() {
	}

	public String getCommunity_PCODE() {
		return this.community_PCODE;
	}

	public void setCommunity_PCODE(String community_PCODE) {
		this.community_PCODE = community_PCODE;
	}

	public String  getConvoy_Date() {
		return this.convoy_Date;
	}

	public void setConvoy_Date(String instant) {
		this.convoy_Date = instant;
	}

	public String getConvoy_Type() {
		return this.convoy_Type;
	}

	public void setConvoy_Type(String convoy_Type) {
		this.convoy_Type = convoy_Type;
	}

	public BigDecimal getLatitude_WGS84() {
		return this.latitude_WGS84;
	}

	public void setLatitude_WGS84(BigDecimal latitude_WGS84) {
		this.latitude_WGS84 = latitude_WGS84;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BigDecimal getLongitude_WGS84() {
		return this.longitude_WGS84;
	}

	public void setLongitude_WGS84(BigDecimal longitude_WGS84) {
		this.longitude_WGS84 = longitude_WGS84;
	}

	public short getOperationMonth() {
		return this.operationMonth;
	}

	public void setOperationMonth(short operationMonth) {
		this.operationMonth = operationMonth;
	}

	public short getOperationYear() {
		return this.operationYear;
	}

	public void setOperationYear(short operationYear) {
		this.operationYear = operationYear;
	}

	public int getTotal_Operations() {
		return this.total_Operations;
	}

	public void setTotal_Operations(int total_Operations) {
		this.total_Operations = total_Operations;
	}

	public double getTotal_Reach() {
		return this.total_Reach;
	}

	public void setTotal_Reach(double total_Reach) {
		this.total_Reach = total_Reach;
	}

}