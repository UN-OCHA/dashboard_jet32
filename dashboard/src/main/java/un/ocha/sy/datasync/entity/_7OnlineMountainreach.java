package un.ocha.sy.datasync.entity;

 

/**
 * The persistent class for the 07_online_mountainreach database table.
 * 
 */
//@Entity
//@Table(name="07_online_mountainreach")
//@NamedQuery(name="_7OnlineMountainreach.findAll", query="SELECT _ FROM _7OnlineMountainreach _")
public class _7OnlineMountainreach  {
 

	private double bsg_Air_Drops;

	private double bsg_IAConvoys;

	private double bsg_UNRWA;

	private double htr_IAConvoys;

	//@Column(name="HTR_UNRWA")
	private double htrUnrwa;

	private int month_Number;

	private int year_Number;

	public _7OnlineMountainreach() {
	}

	public double getBsg_Air_Drops() {
		return this.bsg_Air_Drops;
	}

	public void setBsg_Air_Drops(double bsg_Air_Drops) {
		this.bsg_Air_Drops = bsg_Air_Drops;
	}

	public double getBsg_IAConvoys() {
		return this.bsg_IAConvoys;
	}

	public void setBsg_IAConvoys(double bsg_IAConvoys) {
		this.bsg_IAConvoys = bsg_IAConvoys;
	}

	public double getBsg_UNRWA() {
		return this.bsg_UNRWA;
	}

	public void setBsg_UNRWA(double bsg_UNRWA) {
		this.bsg_UNRWA = bsg_UNRWA;
	}

	public double gethtr_IAConvoys() {
		return this.htr_IAConvoys;
	}

	public void sethtr_IAConvoys(double HTR_IAConvoys) {
		this.htr_IAConvoys = HTR_IAConvoys;
	}

	public double getHtrUnrwa() {
		return this.htrUnrwa;
	}

	public void setHtrUnrwa(double htrUnrwa) {
		this.htrUnrwa = htrUnrwa;
	}

	public int getMonth_Number() {
		return this.month_Number;
	}

	public void setMonth_Number(int month_Number) {
		this.month_Number = month_Number;
	}

	public int getYear_Number() {
		return this.year_Number;
	}

	public void setYear_Number(int year_Number) {
		this.year_Number = year_Number;
	}

}