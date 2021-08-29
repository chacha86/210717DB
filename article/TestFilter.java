package article;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns={"/article/showAdd.do", "/article/detail.do"})
public class TestFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// 로그인 체크 필터
		// 1. 로그인 체크
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession();
		String user = (String)session.getAttribute("loginUser");
		
		// 2. 로그인 X
		if(user == null) {
			request.setAttribute("errorMsg", "로그인 하지 않았습니다.");
			RequestDispatcher rd = httpRequest.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
			return;
		}
		
		chain.doFilter(request, response); // 서블릿에 요청을 넘겨라
	}

}
