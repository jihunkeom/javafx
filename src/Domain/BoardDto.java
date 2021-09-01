package Domain;

public class BoardDto {
	
	// DTO: 페이지간 이동객체  
	
	private int bno;
	private String btitle;
	private String bcontents;
	private String bwriter;
	private String bdate;
	private int bcount;
	
	public BoardDto() {
		// TODO Auto-generated constructor stub
	}

	public BoardDto(int bno, String btitle, String bcontents, String bwriter, String bdate, int bcount) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bcontents = bcontents;
		this.bwriter = bwriter;
		this.bdate = bdate;
		this.bcount = bcount;
	}
	

	public BoardDto(String btitle, String bcontents, String bwriter) {
		super();
		this.btitle = btitle;
		this.bcontents = bcontents;
		this.bwriter = bwriter;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontents() {
		return bcontents;
	}

	public void setBcontents(String bcontents) {
		this.bcontents = bcontents;
	}

	public String getBwriter() {
		return bwriter;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public int getBcount() {
		return bcount;
	}

	public void setBcount(int bcount) {
		this.bcount = bcount;
	}
	
	
	
	

}
