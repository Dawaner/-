package code;

public class TypeData {

	private String Dtypeid;//������
	private String Dtypename;//��������
	
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
