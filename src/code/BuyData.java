package code;

public class BuyData {

	private String Dbuyid;//订单编号
	private String Dbuypayid;//支付方式编号
	private String Dbuysendid;//发货方式编号
	private String Dbuytime;//购买日期
	
	public BuyData() {
		super();
	}
	
	public BuyData(String dbuyid, String dbuypayid, String dbuysendid, 
			String dbuytime) {
		super();
		// TODO Auto-generated constructor stub
		this.Dbuyid = dbuyid;
		this.Dbuypayid = dbuypayid;
		this.Dbuysendid = dbuysendid;
		this.Dbuytime = dbuytime;
		
	}
	
	public String getBuyId() {
		return Dbuyid;
	}
	public void setBuyId(String Dbuyid) {
		this.Dbuyid = Dbuyid;
	}
	public String getBuyPayId() {
		return Dbuypayid;
	}
	public void setBuyPayId(String Dbuypayid) {
		this.Dbuypayid = Dbuypayid;
	}
	public String getBuySendId() {
		return Dbuysendid;
	}
	public void setBuySendId(String Dbuysendid) {
		this.Dbuysendid = Dbuysendid;
	}
	public String getBuyTime() {
		return Dbuytime;
	}
	public void setBuyTime(String Dbuytime) {
		this.Dbuytime = Dbuytime;
	}
	
	
	
}
