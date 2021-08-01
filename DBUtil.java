import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
	void list() {
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
				
			}
		} catch(Exception e) {
			System.out.println("문제 발생!!");
		}
	}
}
