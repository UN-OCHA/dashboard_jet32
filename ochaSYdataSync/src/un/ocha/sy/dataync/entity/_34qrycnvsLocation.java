package un.ocha.sy.dataync.entity;

 

/**
 * The persistent class for the 1_34qrycnvs_locations database table.
 * 
 */
//@Entity
//@Table(name="1_34qrycnvs_locations")
//@NamedQuery(name="_34qrycnvsLocation.findAll", query="SELECT _ FROM _34qrycnvsLocation _")
public class _34qrycnvsLocation   {
	 

	private String community_PCODE;

	private String convoy_Type;

	private String convoyCode;

	private String location;

	public _34qrycnvsLocation() {
	}

	public String getCommunity_PCODE() {
		return this.community_PCODE;
	}

	public void setCommunity_PCODE(String community_PCODE) {
		this.community_PCODE = community_PCODE;
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

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}