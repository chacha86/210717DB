package article;
// DTO, VO
public class Article {

	private int id;
	private String title;
	private String body;
	private int memberId;
	private String regDate;
	private int hit;
	private String nickname;
	private int rcnt;
	

	public Article(int id, String title, String body, int memberId, String regDate, int hit, String nickname, int rcnt) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.memberId = memberId;
		this.regDate = regDate;
		this.hit = hit;
		this.nickname = nickname;
		this.rcnt = rcnt;
	}
	
	public int getRcnt() {
		return rcnt;
	}



	public void setRcnt(int rcnt) {
		this.rcnt = rcnt;
	}



	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}	
}
