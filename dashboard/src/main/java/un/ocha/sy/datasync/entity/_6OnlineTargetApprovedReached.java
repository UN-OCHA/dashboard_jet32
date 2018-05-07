package un.ocha.sy.datasync.entity;

 

/**
 * The persistent class for the 06_online_target_approved_reached database table.
 * 
 */
//@Entity
//@Table(name="06_online_target_approved_reached")
//@NamedQuery(name="_6OnlineTargetApprovedReached.findAll", query="SELECT _ FROM _6OnlineTargetApprovedReached _")
public class _6OnlineTargetApprovedReached   {
	 
	private double approvedBeneficiaries;

	private double conditional_Approval_Beneficiaries;

	private short convoy_Month;

	private short convoy_Year;

	private double cumulative_Reached;

	private String month_Text;

	private double net_Reached;

	private double requested_targeted_people;

	public _6OnlineTargetApprovedReached() {
	}

	public double getApprovedBeneficiaries() {
		return this.approvedBeneficiaries;
	}

	public void setApprovedBeneficiaries(double approvedBeneficiaries) {
		this.approvedBeneficiaries = approvedBeneficiaries;
	}

	public double getConditional_Approval_Beneficiaries() {
		return this.conditional_Approval_Beneficiaries;
	}

	public void setConditional_Approval_Beneficiaries(double conditional_Approval_Beneficiaries) {
		this.conditional_Approval_Beneficiaries = conditional_Approval_Beneficiaries;
	}

	public short getConvoy_Month() {
		return this.convoy_Month;
	}

	public void setConvoy_Month(short convoy_Month) {
		this.convoy_Month = convoy_Month;
	}

	public short getConvoy_Year() {
		return this.convoy_Year;
	}

	public void setConvoy_Year(short convoy_Year) {
		this.convoy_Year = convoy_Year;
	}

	public double getCumulative_Reached() {
		return this.cumulative_Reached;
	}

	public void setCumulative_Reached(double cumulative_Reached) {
		this.cumulative_Reached = cumulative_Reached;
	}

	public String getMonth_Text() {
		return this.month_Text;
	}

	public void setMonth_Text(String month_Text) {
		this.month_Text = month_Text;
	}

	public double getNet_Reached() {
		return this.net_Reached;
	}

	public void setNet_Reached(double net_Reached) {
		this.net_Reached = net_Reached;
	}

	public double getRequested_targeted_people() {
		return this.requested_targeted_people;
	}

	public void setRequested_targeted_people(double requested_targeted_people) {
		this.requested_targeted_people = requested_targeted_people;
	}

}