package un.ocha.sy.dataync.entity;

 
import java.util.Date;


/**
 * The persistent class for the 1_3qrycnvs_all database table.
 * 
 */
//@Entity
//@Table(name="1_3qrycnvs_all")
//@NamedQuery(name="_3qrycnvsAll.findAll", query="SELECT _ FROM _3qrycnvsAll _")
public class _3qrycnvsAll   {
	 
	private String calcRef_Code;

	private String calculationMethod;

	private double cnvyLocation_Target_Beneficiaries;

	private double cnvyLocation_Total_Beneficiaries_Reached;

	//@Temporal(TemporalType.TIMESTAMP)
	private String convoy_Date;

	private String convoy_Type;

	private String convoyCode;

	private byte multipleConvoys;

	private double sumOfNumber_of_Trucks;

	private int total_Locations;

	public _3qrycnvsAll() {
	}

	public String getCalcRef_Code() {
		return this.calcRef_Code;
	}

	public void setCalcRef_Code(String calcRef_Code) {
		this.calcRef_Code = calcRef_Code;
	}

	public String getCalculationMethod() {
		return this.calculationMethod;
	}

	public void setCalculationMethod(String calculationMethod) {
		this.calculationMethod = calculationMethod;
	}

	public double getCnvyLocation_Target_Beneficiaries() {
		return this.cnvyLocation_Target_Beneficiaries;
	}

	public void setCnvyLocation_Target_Beneficiaries(double cnvyLocation_Target_Beneficiaries) {
		this.cnvyLocation_Target_Beneficiaries = cnvyLocation_Target_Beneficiaries;
	}

	public double getCnvyLocation_Total_Beneficiaries_Reached() {
		return this.cnvyLocation_Total_Beneficiaries_Reached;
	}

	public void setCnvyLocation_Total_Beneficiaries_Reached(double cnvyLocation_Total_Beneficiaries_Reached) {
		this.cnvyLocation_Total_Beneficiaries_Reached = cnvyLocation_Total_Beneficiaries_Reached;
	}

	public String getConvoy_Date() {
		return this.convoy_Date;
	}

	public void setConvoy_Date(String convoy_Date) {
		this.convoy_Date = convoy_Date;
	}

	public String getConvoy_Type() {
		return this.convoy_Type;
	}

	public void setConvoy_Type(String convoy_Type) {
		this.convoy_Type = convoy_Type;
	}

	public String getConvoyCode() {
		return this.convoyCode;
	}

	public void setConvoyCode(String convoyCode) {
		this.convoyCode = convoyCode;
	}

	public byte getMultipleConvoys() {
		return this.multipleConvoys;
	}

	public void setMultipleConvoys(byte multipleConvoys) {
		this.multipleConvoys = multipleConvoys;
	}

	public double getSumOfNumber_of_Trucks() {
		return this.sumOfNumber_of_Trucks;
	}

	public void setSumOfNumber_of_Trucks(double sumOfNumber_of_Trucks) {
		this.sumOfNumber_of_Trucks = sumOfNumber_of_Trucks;
	}

	public int getTotal_Locations() {
		return this.total_Locations;
	}

	public void setTotal_Locations(int total_Locations) {
		this.total_Locations = total_Locations;
	}

}