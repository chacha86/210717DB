package article;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

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
		} catch(Exception e) {
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
			ResultSet rs = stmt.executeQuery("select * from article");
			
			while(rs.next()) {
				
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String body = rs.getString("body");
				int memberId = rs.getInt("memberId");
				String regDate = rs.getString("regDate");
				int hit = rs.getInt("hit");
				
				Article a = new Article(id, title, body, memberId, regDate, hit);				
				
				articles.add(a);
			}
		} catch(Exception e) {
			System.out.println("문제 발생!!");
		}
		
		
		return articles;
	}
	
	// 3. 게시물 상세
	public Article getArticleById(String id) {
		
		//String id = request.getParameter("id");
		Article a = null;
		
		try {			
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from article where id = " + id);
			
			if(rs.next()) {
				
				int aid = rs.getInt("id");
				String title = rs.getString("title");
				String body = rs.getString("body");
				int memberId = rs.getInt("memberId");
				String regDate = rs.getString("regDate");
				int hit = rs.getInt("hit");
				
				a = new Article(aid, title, body, memberId, regDate, hit);
			}
		} catch(Exception e) {
			System.out.println("문제 발생!!");
		}
			
		return a;
	}
}
