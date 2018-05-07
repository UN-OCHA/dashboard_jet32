package un.ocha.sy.dataync.entity;

 


/**
 * The persistent class for the qryaccess_total database table.
 * 
 */
//@Entity
//@Table(name="qryaccess_total")
//@NamedQuery(name="QryaccessTotal.findAll", query="SELECT q FROM QryaccessTotal q")
public class QryaccessTotal   {
	 
	private String access_Status;

	private double sumOfEstimated_PIN_HTR_subdistrict;

	public QryaccessTotal() {
	}

	public String getAccess_Status() {
		return this.access_Status;
	}

	public void setAccess_Status(String access_Status) {
		this.access_Status = access_Status;
	}

	public double getSumOfEstimated_PIN_HTR_subdistrict() {
		return this.sumOfEstimated_PIN_HTR_subdistrict;
	}

	public void setSumOfEstimated_PIN_HTR_subdistrict(double sumOfEstimated_PIN_HTR_subdistrict) {
		this.sumOfEstimated_PIN_HTR_subdistrict = sumOfEstimated_PIN_HTR_subdistrict;
	}

}