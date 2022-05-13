package code;

public class BuyerData {
	
	private String Dbuyername;//姓名
	private String Dbuyergender;//性别
	private String Dbuyerage;//年龄
	private String Dbuyertele;//联系方式
	private String Dbuyerid;//会员编号

	public BuyerData() {
		super();
	}
	
	public BuyerData(String dbuyername, String dbuyergender, String dbuyerage, 
			String dbuyertele, String dbuyerid) {
		super();
		this.Dbuyername = dbuyername;
		this.Dbuyergender = dbuyergender;
		this.Dbuyerage = dbuyerage;
		this.Dbuyertele = dbuyertele;
		this.Dbuyerid = dbuyerid;
	}

	public String getBuyerName() {
		return Dbuyername;
	}
	public void setBuyerName(String Dbuyername) {
		this.Dbuyername = Dbuyername;
	}
	public String getBuyerGender() {
		return Dbuyergender;
	}
	public void setBuyerGender(String Dbuyergender) {
		this.Dbuyergender = Dbuyergender;
	}
	public String getBuyerAge() {
		return Dbuyerage;
	}
	public void setBuyerAge(String Dbuyerage) {
		this.Dbuyerage = Dbuyerage;
	}
	public String getBuyerTele() {
		return Dbuyertele;
	}
	public void setBuyerTele(String Dbuyertele) {
		this.Dbuyertele = Dbuyertele;
	}
	public String getBuyerId() {
		return Dbuyerid;
	}
	public void setBuyerId(String Dbuyerid) {
		this.Dbuyerid = Dbuyerid;
	}
	
}
