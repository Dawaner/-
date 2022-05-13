package code;

public class PayData {
	
	private String Dpayid;//支付方式编号
	private String Dpayname;//支付方式名称
	
	
	public PayData() {
		super();
		// TODO 自动生成的构造函数存根
	}

	
	public PayData(String dpayid, String dpayname) {
		super();
		this.Dpayid = dpayid;
		this.Dpayname = dpayname;
	}




	public String getPayId() {
		return Dpayid;
	}
	public void setPayId(String Dpayid) {
		this.Dpayid = Dpayid;
	}
	public String getPayName() {
		return Dpayname;
	}
	public void setPayName(String Dpayname) {
		this.Dpayname = Dpayname;

	}
	
}
