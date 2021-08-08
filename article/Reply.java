package article;

public class Reply {

	private int id;
	private int parentId;
	private String body;
	private int memberId;
	private String regDate;
	private String nickname;
	
	public Reply(int id, int parentId, String body, int memberId, String regDate, String nickname) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.body = body;
		this.memberId = memberId;
		this.regDate = regDate;
		this.nickname = nickname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
