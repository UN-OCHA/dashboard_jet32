package un.ocha.sy.datasync.entity;

 


/**
 * The persistent class for the qryaccess_total database table.
 * 
 */
//@Entity
//@Table(name="qryaccess_total")
//@NamedQuery(name="QryaccessTotal.findAll", query="SELECT q FROM QryaccessTotal q")
public class Ocha_dashboard_webtag   {
	 
	private String text_id;
	private String text_desc;
	private String text_value;
	
	
	public String getText_id() {
		return text_id;
	}


	public void setText_id(String text_id) {
		this.text_id = text_id;
	}


	public String getText_desc() {
		return text_desc;
	}


	public void setText_desc(String text_desc) {
		this.text_desc = text_desc;
	}


	public String getText_value() {
		return text_value;
	}


	public void setText_value(String text_value) {
		this.text_value = text_value;
	}


	public Ocha_dashboard_webtag() {
	}

	

}