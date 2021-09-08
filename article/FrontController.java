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

	DBUtil db = new DBUtil();
	Pagination pagination = new Pagination(10, 5, db.getTotalCountOfArticles());
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 인코딩 설정
		// 2. ContentType 설정
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String uri = request.getRequestURI();
		// split - 문자열을 구분자를 정해서 자를 수 있다.
		String[] uriBits = uri.split("/");
		
		String action = uriBits[2];
		
		if(action.equals("list.do")) {
			list(request, response);		
		}
		else if(action.equals("detail.do")) {
			detail(request, response);
		}
		else if(action.equals("showUpdate.do")) {
			showUpdate(request, response);
		}
		else if(action.equals("update.do")) {
			update(request, response);
		}
		else if(action.equals("delete.do")) {
			delete(request, response);
		}
		else if(action.equals("showAdd.do")) {
			showAdd(request, response);
		}
		else if(action.equals("add.do")) {
			add(request, response);
		}
		else if(action.equals("addReply.do")) {
			addreply(request, response);
		}
		else if(action.equals("showMember.do")) {
			sendView(request, response, "/jsp/article/addMemberForm.jsp");
		}
		else if(action.equals("addMember.do")) {
			addMember(request, response);
		}
		else if(action.equals("showLogin.do")) {
			sendView(request, response, "/jsp/member/loginForm.jsp");
		}
		else if(action.equals("login.do")) {
			login(request, response);
		}
		else if(action.equals("logout.do")) {
			logout(request, response);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate(); // session에 저장된 모든 데이터 삭제
		
		redirectView(response, "/article/list.do");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {

		// 로그인을 시도하는 유저의 로그인 정보를 파라미터로 받아서 DB에 있는지 체크
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		
		Member m = db.getMemberByLoginIdAndLoginPw(loginId, loginPw);
		
		// 로그인 성공
		if(m != null) {
			
			// request 저장소는 하나의 요청 처리에 대해 동일한 데이터를 저장하고 제공한다.				
			// session 저장소는 모든 요청 처리에 대해 동일한 데이터를 저장하고 제공한다.
			
			// session 저장소에 로그인 정보를 저장.
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", m.getNickname());				
			
			redirectView(response, "/article/list.do");
			
		}
		// 로그인 실패
		else {
			request.setAttribute("errorMsg", "로그인 실패. 잘못된 회원정보입니다.");
			
			sendView(request, response, "/jsp/error/error.jsp");
		}
		
	}

	private void addMember(HttpServletRequest request, HttpServletResponse response) {
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		String nickname = request.getParameter("nickname");
		
		db.addMember(loginId, loginPw, nickname);
		redirectView(response, "/article/list.do");
	}

	private void showAdd(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String user = (String)session.getAttribute("loginUser");
		
		if(user != null) {
			sendView(request, response, "/jsp/article/addForm.jsp");			
		}
		else {

			sendView(request, response, "/jsp/article/error.jsp");
		}		
	}

	private void addreply(HttpServletRequest request, HttpServletResponse response) {
		String rbody = request.getParameter("rbody");
		String aid = request.getParameter("aid");
		
		db.addReply(rbody, aid);
		
		redirectView(response, "/article/detail.do?id=" + aid);
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		
		db.deleteArticle(id);
		redirectView(response, "/article/list.do");
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		
		db.addArticle(title, body);
		redirectView(response, "/article/list.do");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		String id = request.getParameter("id");
		
		db.updateArticle(title, body, id);
		redirectView(response, "/article/detail.do?id=" + id);
		
	}

	private void showUpdate(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Article article = db.getArticleById(id);
		request.setAttribute("article", article);
		
		sendView(request, response, "/jsp/article/updateForm.jsp");
	}

	private void detail(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Article article = db.getArticleById(id);
		ArrayList<Reply> replies = db.getArticleReplyByArticleId(article.getId());
		
		request.setAttribute("article", article);
		request.setAttribute("replies", replies);
		
		sendView(request, response, "/jsp/article/detail.jsp");
	}

	private void list(HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("pageNum") != null) {
			pagination.setCurrentPageNo(Integer.parseInt(request.getParameter("pageNum")));
		}
		pagination.setTotalCountOfItems(db.getTotalCountOfArticles());
		ArrayList<Article> articles = db.getArticleList(pagination);
		request.setAttribute("articles", articles);
		request.setAttribute("page", pagination);
		
		sendView(request, response, "/jsp/article/index.jsp");
	}

	public void sendView(HttpServletRequest request, HttpServletResponse response, String path) {
		
		try {
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);			
		} catch(Exception e) {
			System.out.println("포워딩 중 문제 발생!");
		}
	}
	
	public void redirectView(HttpServletResponse response, String path) {
		
		try {
			response.sendRedirect(path);			
		} catch(Exception e) {
			System.out.println("리다이렉트 중 문제 발생!");
		}
	}
	
}
