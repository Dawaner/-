package code;

public class BookData {
	
	private String Dbookid;//ͼ����
	private String Dbookname;//ͼ������
	private String Dbookprice;//����
	private String Dbooksynopsis;//���ݼ��
	private String Dbooksales;//�������
	
	
	public BookData() {
		super();
	}
	
	public BookData(String dbookid, String dbookname, String dbookprice, 
			String dbooksynopsis, String dbooksales) {
		super();
		// TODO Auto-generated constructor stub
		this.Dbookid = dbookid;
		this.Dbookname = dbookname;
		this.Dbookprice = dbookprice;
		this.Dbooksales = dbooksales;
		this.Dbooksynopsis = dbooksynopsis;
		
	}



	public String getBookId() {
		return Dbookid;
	}
	public void setBookId(String Dbookid) {
		this.Dbookid = Dbookid;
	}
	public String getBookName() {
		return Dbookname;
	}
	public void setBookName(String Dbookname) {
		this.Dbookname = Dbookname;
	}
	public String getBookPrice() {
		return Dbookprice;
	}
	public void setBookPrice(String Dbookprice) {
		this.Dbookprice = Dbookprice;
	}
	public String getBookSynopsis() {
		return Dbooksynopsis;
	}
	public void setBookSynopsis(String Dbooksynopsis) {
		this.Dbooksynopsis = Dbooksynopsis;
	}
	public String getBookSales() {
		return Dbooksales;
	}
	public void setBookSales(String Dbooksales) {
		this.Dbooksales = Dbooksales;
	}
	


	
	
	
}
