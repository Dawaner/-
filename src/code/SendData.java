package code;

public class SendData {

	private String Dsendid;//������ʽ���
	private String Dsendname;//������ʽ����
	
	public SendData() {
		super();
		// TODO �Զ����ɵĹ��캯�����
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
