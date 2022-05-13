package code;

public class TypeData {

	private String Dtypeid;//种类编号
	private String Dtypename;//种类名称
	
	public TypeData() {
		super();
	}
	
	public TypeData(String dtypeid, String dtypename) {
		super();
		this.Dtypeid = dtypeid;
		this.Dtypename = dtypename;
	}
	
	public String getTypeId() {
		return Dtypeid;
	}
	public void setTypeId(String Dtypeid) {
		this.Dtypeid = Dtypeid;
	}
	public String getTypeName() {
		return Dtypename;
	}
	public void setTypeName(String Dtypename) {
		this.Dtypename = Dtypename;

	}
	
}
