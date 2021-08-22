package article;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.do")
public class FrontController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 인코딩 설정
		// 2. ContentType 설정
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 3. DB에서 게시물 목록 가져와서
		DBUtil db = new DBUtil();
		
		//String action = request.getParameter("action");
		
		// 주소 정보 가져오기 -> URL == URI
		String uri = request.getRequestURI();
		System.out.println("자르기 전 : " + uri);
		
		// split - 문자열을 구분자를 정해서 자를 수 있다.
		String[] uriBits = uri.split("/");
		
//		for(int i = 0; i < uriBits.length; i++) {
//			System.out.println(uriBits[i]);
//		}
		String action = uriBits[2];
		
		if(action.equals("list.do")) {
			ArrayList<Article> articles = db.getArticleList();
			request.setAttribute("articles", articles);
			
			// 4. request에 데이터 세팅 후 jstlTest.jsp로 포워딩
			
			// 경로 :
			// 절대경로 : root 경로를 기준으로 목적지 찾는 방식 (/detail.jsp)
			// 상대경로 : 현재 경로 기준으로 목적지 찾는 방식 (detail.jsp)
			
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);			
		}
		else if(action.equals("detail.do")) {			
			String id = request.getParameter("id");
			Article article = db.getArticleById(id);
			ArrayList<Reply> replies = db.getArticleReplyByArticleId(article.getId());
			
			request.setAttribute("article", article);
			request.setAttribute("replies", replies);
			
			RequestDispatcher rd = request.getRequestDispatcher("/detail.jsp");
			rd.forward(request, response);
		}
		
		
		
		
	}

}
