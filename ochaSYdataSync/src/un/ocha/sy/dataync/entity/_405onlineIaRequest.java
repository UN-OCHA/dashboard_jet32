package un.ocha.sy.dataync.entity;

 

/**
 * The persistent class for the 04__05online_ia_requests database table.
 * 
 */
//@Entity
//@Table(name="04__05online_ia_requests")
//@NamedQuery(name="_405onlineIaRequest.findAll", query="SELECT _ FROM _405onlineIaRequest _")
public class _405onlineIaRequest   {
	 
	private int month;

	private String month_Text;

	private String request_Status;

	//@Column(name="`SumOfNumber of requests`")
	private double sumOfNumber_of_requests;

	private int year;

	public _405onlineIaRequest() {
	}

	public int getMonth() {
		return this.month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getMonth_Text() {
		return this.month_Text;
	}

	public void setMonth_Text(String month_Text) {
		this.month_Text = month_Text;
	}

	public String getRequest_Status() {
		return this.request_Status;
	}

	public void setRequest_Status(String request_Status) {
		this.request_Status = request_Status;
	}

	public double getSumOfNumber_of_requests() {
		return this.sumOfNumber_of_requests;
	}

	public void setSumOfNumber_of_requests(double sumOfNumber_of_requests) {
		this.sumOfNumber_of_requests = sumOfNumber_of_requests;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}