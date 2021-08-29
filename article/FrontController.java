package article;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("*.do")
public class FrontController extends HttpServlet {
	
	Member loginedMember = null; // 로그인 정보 유지
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 인코딩 설정
		// 2. ContentType 설정
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		DBUtil db = new DBUtil();
		
		String uri = request.getRequestURI();
		// split - 문자열을 구분자를 정해서 자를 수 있다.
		String[] uriBits = uri.split("/");
		
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
		else if(action.equals("showUpdate.do")) {
			String id = request.getParameter("id");
			Article article = db.getArticleById(id);
			request.setAttribute("article", article);
			
			RequestDispatcher rd = request.getRequestDispatcher("/updateForm.jsp");
			rd.forward(request, response);
		}
		else if(action.equals("update.do")) {
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			String id = request.getParameter("id");
			
			db.updateArticle(title, body, id);
			
			// 포워딩 : 요청 처리 위임. A에서 작업한 요청 결과를 B에 전달. B가 마무리.
			// 리다이렉트 : 새로운 요청. A에서 작업한 결과 싹다 무시. B에 새로운 요청
			
			response.sendRedirect("/article/detail.do?id=" + id);
		}
		else if(action.equals("delete.do")) {
			String id = request.getParameter("id");
			
			db.deleteArticle(id);
			response.sendRedirect("/article/list.do");
		}
		else if(action.equals("showAdd.do")) {
			
			// 로그인 되어 있을 때만
		
			RequestDispatcher rd = request.getRequestDispatcher("/addForm.jsp");
			rd.forward(request, response);				
		}
		else if(action.equals("add.do")) {
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			
			db.addArticle(title, body);
			response.sendRedirect("/article/list.do");
		}
		else if(action.equals("addReply.do")) {
			String rbody = request.getParameter("rbody");
			String aid = request.getParameter("aid");
			
			db.addReply(rbody, aid);
			
			response.sendRedirect("/article/detail.do?id=" + aid);
		}
		else if(action.equals("showMember.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("/addMemberForm.jsp");
			rd.forward(request, response);
		}
		else if(action.equals("addMember.do")) {
			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");
			String nickname = request.getParameter("nickname");
			
			db.addMember(loginId, loginPw, nickname);
			response.sendRedirect("/article/list.do");
		}
		else if(action.equals("showLogin.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("/loginForm.jsp");
			rd.forward(request, response);
		}
		else if(action.equals("login.do")) {
			
			// 로그인을 시도하는 유저의 로그인 정보를 파라미터로 받아서 DB에 있는지 체크
			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");
			
			Member m = db.getMemberByLoginIdAndLoginPw(loginId, loginPw);
			
			// 로그인 성공
			if(m != null) {
				
				// session 저장소에 로그인 정보를 저장.
				HttpSession session = request.getSession();
				
				session.setAttribute("loginUser", m.getNickname());				
				
				ArrayList<Article> articles = db.getArticleList();
				request.setAttribute("articles", articles);
				
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
				
			}
			// 로그인 실패
			else {
				request.setAttribute("errorMsg", "로그인 실패. 잘못된 회원정보입니다.");
				
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response); 
			}
			
		}
	}

}
