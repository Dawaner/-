package code;

public class PayData {
	
	private String Dpayid;//֧����ʽ���
	private String Dpayname;//֧����ʽ����
	
	
	public PayData() {
		super();
		// TODO �Զ����ɵĹ��캯�����
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
