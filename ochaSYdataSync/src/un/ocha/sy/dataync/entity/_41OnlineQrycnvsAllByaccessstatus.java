package un.ocha.sy.dataync.entity;

 
import java.util.Date;


/**
 * The persistent class for the 2_4_1_online_qrycnvs_all_byaccessstatus database table.
 * 
 */
//@Entity
//@Table(name="2_4_1_online_qrycnvs_all_byaccessstatus")
//@NamedQuery(name="_41OnlineQrycnvsAllByaccessstatus.findAll", query="SELECT _ FROM _41OnlineQrycnvsAllByaccessstatus _")
public class _41OnlineQrycnvsAllByaccessstatus {
 

	private String calcRef_Code;

	private String calculationMethod;

	private double cnvyLocation_Total_Beneficiaries_Reached;

	//@Temporal(TemporalType.TIMESTAMP)
	private String convoy_Date;

	private String convoy_Type;

	private String convoyCode;

	private byte multipleConvoys;

	private double sumOfNumber_of_Trucks;

	private String updated_Access_Status;

	public _41OnlineQrycnvsAllByaccessstatus() {
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

	public String getUpdated_Access_Status() {
		return this.updated_Access_Status;
	}

	public void setUpdated_Access_Status(String updated_Access_Status) {
		this.updated_Access_Status = updated_Access_Status;
	}

}