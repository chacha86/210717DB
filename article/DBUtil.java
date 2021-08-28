package article;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBUtil {

	// DB주소, 드라이버, 계정, 비밀번호
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/k1?serverTimezone=UTC";
	String user = "sbsst";
	String password = "sbs123414";

	// 1. 접속
	Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("문제 발생했음!");
		}

		return conn;
	}

	// 1. 게시물 목록
	public ArrayList<Article> getArticleList() {
		ArrayList<Article> articles = new ArrayList<Article>();

		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();

			String sql = "SELECT a.*, m.nickname, COUNT(ar.id) rcnt " + "FROM article a " + "INNER JOIN `member` m "
					+ "ON a.memberId = m.id " + "LEFT OUTER JOIN articleReply ar " + "ON a.id = ar.parentId "
					+ "GROUP BY a.id";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				int id = rs.getInt("id");
				String title = rs.getString("title");
				String body = rs.getString("body");
				int memberId = rs.getInt("memberId");
				String regDate = rs.getString("regDate");
				int hit = rs.getInt("hit");
				String nickname = rs.getString("nickname");
				int rcnt = rs.getInt("rcnt");

				Article a = new Article(id, title, body, memberId, regDate, hit, nickname, rcnt);

				articles.add(a);
			}
		} catch (Exception e) {
			System.out.println("문제 발생!!");
		}

		return articles;
	}

	// 3. 게시물 상세
	public Article getArticleById(String id) {

		// String id = request.getParameter("id");
		Article a = null;

		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			String sql = "SELECT a.*, m.nickname " + "FROM article a " + "INNER JOIN `member` m "
					+ "ON a.memberId = m.id " + "WHERE a.id = " + id;

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				int aid = rs.getInt("id");
				String title = rs.getString("title");
				String body = rs.getString("body");
				int memberId = rs.getInt("memberId");
				String regDate = rs.getString("regDate");
				int hit = rs.getInt("hit");
				String nickname = rs.getString("nickname");

				a = new Article(aid, title, body, memberId, regDate, hit, nickname, 0);
			}
		} catch (Exception e) {
			System.out.println("문제 발생!!");
		}

		return a;
	}

	// 4. 수정
	public void updateArticle(String title, String body, String id) {

		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			String sql = "UPDATE article " + "SET title = '" + title + "', " + "`body` = '" + body + "' "
					+ "WHERE id = " + id;

			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("문제 발생");
		}
	}

	// 5. 삭제
	public void deleteArticle(String id) {

		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			String sql = "DELETE FROM article " + "WHERE id = " + id;

			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("문제 발생");
		}
	}

	// 6. 댓글 목록 가져오기
	public ArrayList<Reply> getArticleReplyByArticleId(int aid) {

		ArrayList<Reply> replies = new ArrayList<Reply>();

		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();

			String sql = "SELECT ar.*, m.nickname " + "FROM articleReply ar " + "INNER JOIN `member` m "
					+ "ON ar.memberId = m.id " + "WHERE parentId = " + aid;
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				int id = rs.getInt("id");
				int parentId = rs.getInt("parentId");
				String body = rs.getString("body");
				int memberId = rs.getInt("memberId");
				String regDate = rs.getString("regDate");
				String nickname = rs.getString("nickname");

				Reply r = new Reply(id, parentId, body, memberId, regDate, nickname);

				replies.add(r);
			}
		} catch (Exception e) {
			System.out.println("문제 발생!!");
		}

		return replies;
	}

	// 7.댓글 저장
	public void addReply(String rbody, String aid) {

		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();

			String sql = "INSERT INTO articleReply " + "SET parentId = " + aid + ", " + "`body` = '" + rbody + "', "
					+ "memberId = 1, " + "regDate = NOW()";

			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("댓글 등록 중 문제 발생");
		}
	}

	// 8. 게시물 추가
	public void addArticle(String title, String body) {

		try {

			Connection conn = getConnection();
			Statement stmt = conn.createStatement();

			String sql = "INSERT INTO article " + "SET title = '" + title + "', " + "`body` = '" + body + "', "
					+ "memberId = 1, " + "regDate = NOW(), " + "hit = 0 ";

			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("게시물 추가 중 문제 발생!!");
		}
	}

	// 9. 회원정보 등록
	public void addMember(String loginId, String loginPw, String nickname) {
		try {

			Connection conn = getConnection();
			Statement stmt = conn.createStatement();

			String sql = "INSERT INTO `member` " + "SET loginId = '" + loginId + "', " + "loginPw = '" + loginPw + "', "
					+ "nickname = '" + nickname + "', " + "regDate = NOW() ";

			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("게시물 추가 중 문제 발생!!");
		}
	}

	// 10. 로그인 체크
	public Member getMemberByLoginIdAndLoginPw(String inputedId, String inputedPw) {
		
		Member m = null;		
		
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT * " +
					"FROM `member` " +
					"WHERE loginId = '" + inputedId + "' " +
					"AND loginPw = '" + inputedPw + "' ";
			
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				int mid = rs.getInt("id");
				String loginId = rs.getString("loginId");
				String loginPw = rs.getString("loginPw");				
				String regDate = rs.getString("regDate");
				String nickname = rs.getString("nickname");

				m = new Member(mid, loginId, loginPw, nickname, regDate);
			}
			
		} catch(Exception e) {
			System.out.println("회원 정보 조회중 문제 발생!!");
		}
		
		return m;
	}
	
}
