package code;

public class SendData {

	private String Dsendid;//发货方式编号
	private String Dsendname;//发货方式名称
	
	public SendData() {
		super();
		// TODO 自动生成的构造函数存根
	}

	
	public SendData(String dsendid, String dsendname) {
		super();
		this.Dsendid = dsendid;
		this.Dsendname = dsendname;
	}
	
	public String getSendId() {
		return Dsendid;
	}
	public void setSendId(String Dsendid) {
		this.Dsendid = Dsendid;
	}
	public String getSendName() {
		return Dsendname;
	}
	public void setSendName(String Dsendname) {
		this.Dsendname = Dsendname;
	}

	
}
