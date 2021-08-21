package article;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/article")
public class FrontController2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 인코딩 설정
		// 2. ContentType 설정
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 3. DB에서 게시물 목록 가져와서
		DBUtil db = new DBUtil();
		
		ArrayList<Article> articles = db.getArticleList();
		
		request.setAttribute("articles", articles);
		
		// 4. request에 데이터 세팅 후 jstlTest.jsp로 포워딩
		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);

		
		
		
		
	}

}
